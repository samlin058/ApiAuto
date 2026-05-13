package Service.Util.Universal;

import Service.Pojo.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static Service.Util.Universal.FileHandler.getFilePath;

public class InitHandler {
    public static void init(String jsonFile) {
        // 获取json文件里的对象信息
        List<AoFileModel> aoFileModels = InitHandler.extractAofromJsonStr(jsonFile);
        ArrayList<RoFileModel> roFileModels = InitHandler.extractRofromJsonStr(jsonFile);
        //StringHandler.jsonPrint("导入json文件里的ao", aoFileModels);
        //StringHandler.jsonPrint("导入json文件里的ro", roFileModels);
        // 过滤项目已存在的Ao&Ro
        List<AoFileModel> filterAoModels = filterExistingAo(aoFileModels);
        List<RoFileModel> filterRoModels = filterExistingRo(roFileModels);
        //StringHandler.jsonPrint("过滤后的Ao", filterAoModels);
        // StringHandler.jsonPrint("过滤后的Ro", filterRoModels);
        // 生成对应的文件
        for (AoFileModel aoFileModel : filterAoModels) {
            FileHandler.aoFileGenerator2(aoFileModel);
        }
        for (RoFileModel roFileModel : filterRoModels) {
            FileHandler.roFileGenerator2(roFileModel);
        }
        System.out.println("Ao&Ro生成脚本执行完成");
    }

    public static void addBatchCase() {
        /*
        1、获取要生成的ToModelList
        2、获取已生成的ToModelList
        3、过滤已生成的To
        4、生成To文件
        */
        // 1、获取要生成的ToModelList
        String getToPath = PropertiesHandle.configuration.getProperty("path.base.apis", "src/main/java/Data/Apis/");
        List<ToFileModel> toBeGeneratedTo = extractTofromDirPath(getToPath);
        //StringHandler.jsonPrint("此次要生成的To：", toBeGeneratedTo);
        // 2、过滤已生成的To
        List<ToFileModel> filterToModel = filterExistingTo(toBeGeneratedTo);
        //StringHandler.jsonPrint("过滤后：", filterToModel);
        // 3、生成To文件
        for (ToFileModel toFileModel : filterToModel) {
            FileHandler.toFileGenerator3(toFileModel);
        }
    }

    public static List<AoFileModel> extractAofromJsonStr(String JsonfileName) {
        // 提取json中的项目名（代码里的包名）
        String pkgName =  JsonHandler.readStringFromFilePath(FileHandler.getFilePath(JsonfileName), "$.info.title");
        // 获取生成Ao对象的json数据
        Object pathsValue = JsonHandler.readJsonFromFilePath(FileHandler.getFilePath(JsonfileName), "$.paths");
        JSONObject pathsJson = new JSONObject((Map<String, Object>) pathsValue);
        ArrayList<AoMethod> aoMethods = new ArrayList<>();
        for (String subPath : pathsJson.keySet()) {
            JSONObject requestWay = pathsJson.getJSONObject(subPath);
            Set<String> requestWaySet = requestWay.keySet();
            for (String requestWayString : requestWaySet) {
                // 为每个请求方法创建新的AoMethod对象
                AoMethod aoMethod = new AoMethod();
                aoMethod.setSubPath(subPath);
                aoMethod.setPackageName(FileHandler.getPackageName(pkgName));
                aoMethod.setRequestTpye(requestWayString);
                JSONObject requestContent = requestWay.getJSONObject(requestWayString);
                aoMethod.setMethodDescription(requestContent.getString("summary"));
                String descriptionValue = requestContent.getString("description");
                if (!StringHandler.isEmpty(descriptionValue)) {
                    String[] parts = descriptionValue.split("\\.");
                    if (parts.length == 2) {
                        String fileName = parts[0];
                        String methodName = parts[1];
                        aoMethod.setBelongController(fileName);
                        aoMethod.setMethodName(methodName);
                        Boolean flag = isParameterPresent(requestContent);
                        if (flag) {
                            aoMethod.setParamName(fileName + StringHandler.capitalizeFirstLetter(methodName) + "Ro");
                        } else {
                            aoMethod.setParamName("NULL");
                        }
                    }
                } else {
                    System.out.println("接口描述为null，请填写对应的接口名和方法名：" + subPath);
                }
                if (requestContent.containsKey("requestBody")) {
                    // 获取contentTpye的值
                    JSONObject contentJson = requestContent.getJSONObject("requestBody").getJSONObject("content");
                    // 获取content中的key，比如"application/json"
                    String contentType = contentJson.keySet().iterator().next();
                    // 设置aoMethod的contentType属性
                    aoMethod.setContentType(contentType);
                    String typeValue = JsonHandler.readStringByJsonPath("$.requestBody.content.*.schema.type", requestContent.toJSONString());
                    String typeString = StringHandler.trimString(typeValue, 2);
                    if ("array".equals(typeString)) {
                        aoMethod.setAoIsList("true");
                    } else {
                        aoMethod.setAoIsList("false");
                        Object properties = JsonHandler.readValueByJsonPath("$.requestBody.content.*.schema.properties", requestContent.toJSONString());
                        if (StringHandler.isEmpty(properties)) {
                            aoMethod.setParamName("NULL");
                        }
                    }
                } else {
                    aoMethod.setAoIsList("false");
                    // TODO parameters里参数字段in只有header的情况下，应该设置ParamName为空
                    JSONArray parameters = requestContent.getJSONArray("parameters");
                    Map<String, Integer> resultMap = new HashMap<>();
                    for (int i = 0; i < parameters.size(); i++) {
                        JSONObject jsonObject = parameters.getJSONObject(i);
                        String inValue = jsonObject.getString("in");
                        if (resultMap.containsKey(inValue)) {
                            resultMap.put(inValue, resultMap.get(inValue) + 1);
                        } else {
                            resultMap.put(inValue, 1);
                        }
                    }
                    /*if (resultMap.get("query") == null ) {
                        aoMethod.setParamName("NULL");
                    }*/
                }
                // 将每个AoMethod对象添加到列表中
                if (!StringHandler.isEmpty(aoMethod.getBelongController())) {
                    aoMethods.add(aoMethod);
                }
            }
        }
        return processAoMethods(aoMethods);
    }

    private static List<AoFileModel> processAoMethods(ArrayList<AoMethod> aoMethods) {
        // 使用Map按照belongController属性值分组
        Map<String, List<AoMethod>> groupedByController = aoMethods.stream()
                .collect(Collectors.groupingBy(aoMethod ->
                        aoMethod.getBelongController() == null ? "unknown" : aoMethod.getBelongController()
                ));
        //  Map<String, List<AoMethod>> groupedByController = aoMethods.stream().collect(Collectors.groupingBy(AoMethod::getBelongController));

        // 转换为AoFileModel对象
        List<AoFileModel> aoFileModels = groupedByController.entrySet().stream().map(entry -> {
            AoFileModel aoFileModel = new AoFileModel();
            // 获取第一个 AoMethod 对象的 packageName（假设列表不为空）
            String firstPackageName = entry.getValue().stream().findFirst().map(AoMethod::getPackageName).orElse(null);

            aoFileModel.setPackageName(firstPackageName);
            aoFileModel.setAoFileName(entry.getKey()); // 根据需求设置文件名
            aoFileModel.setMethodList(entry.getValue());
            return aoFileModel;
        }).collect(Collectors.toList());
        return aoFileModels;
    }

    public static ArrayList<RoFileModel> extractRofromJsonStr(String JsonfileName) {
        // 创建存储Ro的数据容器
        ArrayList<RoFileModel> roFileModels = new ArrayList<>();
        // 提取json中的项目名（代码里的包名）
        String pkgName = JsonHandler.readStringFromFilePath(getFilePath(JsonfileName), "$.info.title");
        // 获取生成Ro对象的json数据
        Object pathsValue = JsonHandler.readJsonFromFilePath(getFilePath(JsonfileName), "$.paths");
        JSONObject pathsJson = new JSONObject((Map<String, Object>) pathsValue);
        // 遍历每个接口下的信息
        for (String subPath : pathsJson.keySet()) {
            JSONObject subPathContent = pathsJson.getJSONObject(subPath);
            for (String requestWayString : subPathContent.keySet()) {
                // 为每个 paths + requestWayString 生成唯一的 RoFileModel
                RoFileModel roFileModel = new RoFileModel();
                roFileModel.setPackageName(FileHandler.getPackageName(pkgName));
                ArrayList<FieldInfo> fieldInfos = new ArrayList<>();

                JSONObject requestContent = subPathContent.getJSONObject(requestWayString);
                String descriptionValue = requestContent.getString("description");
                if (!StringHandler.isEmpty(descriptionValue)) {
                    String[] parts = descriptionValue.split("\\.");
                    if (parts.length == 2) {
                        String fileName = parts[0];
                        String methodName = parts[1];
                        // 设置Ao文件名（继承哪个Ao）
                        roFileModel.setAoFileName(fileName);
                        // 设置Ro文件名（java类名）
                        roFileModel.setRoFileName(fileName + StringHandler.capitalizeFirstLetter(methodName) + "Ro");
                    }
                }

                if (requestContent.containsKey("parameters")) {
                    JSONArray parameters = requestContent.getJSONArray("parameters");
                    if (!parameters.isEmpty()) {
                        for (Object parameter : parameters) {
                            JSONObject parameterItem = new JSONObject((Map<String, Object>) parameter);
                            FieldInfo fieldInfo = new FieldInfo();
                            String inValue = parameterItem.getString("in");
                            if ("query".equals(inValue) || "path".equals(inValue)) {
                                String field = null;
                                if ("path".equals(inValue)) {
                                    fieldInfo.setFieldAttribute("pathParameters");
                                    field = parameterItem.getString("name") + "_path";
                                } else {
                                    fieldInfo.setFieldAttribute("parameters");
                                    field = parameterItem.getString("name");
                                }
                                // 将部分字段中带有-替换成_,防止ro生成报错
                                String processingField = field.replace("-", "_");
                                fieldInfo.setFieldName(dealFiledCapital(processingField));
                                String fieldType = parameterItem.getJSONObject("schema").getString("type");
                                // 如果字段类型是数组，那么再去获取数组的类型
                                if ("array".equals(fieldType)) {
                                    String arrayStr = parameterItem.getJSONObject("schema").getJSONObject("items").getString("type") + "[]";
                                    String fieldTypeString = StringHandler.capitalizeFirstLetter(arrayStr);
                                    fieldInfo.setFieldType(fieldTypeString);
                                } else {
                                    fieldInfo.setFieldType(StringHandler.capitalizeFirstLetter(fieldType));
                                }
                            }
                            if ("header".equals(inValue)) {
                                fieldInfo.setFieldAttribute("header");
                                String field = parameterItem.getString("name");
                                // 将部分字段中带有-替换成_,防止ro生成报错
                                String processingField = field.replace("-", "_");
                                fieldInfo.setFieldName(dealFiledCapital(processingField));
                                String fieldType = parameterItem.getJSONObject("schema").getString("type");
                                fieldInfo.setFieldType(StringHandler.capitalizeFirstLetter(fieldType));
                            }
                            // 过滤掉parametersJson中不是in的值不是query的字段
                            if (fieldInfo.getFieldAttribute() != null) {
                                fieldInfos.add(fieldInfo);
                            }
                        }
                    }
                }

                if (requestContent.containsKey("requestBody")) {
                    String requestBodyStr = StringHandler.trimString(JsonHandler.readStringByJsonPath("$.requestBody.content.*.schema.type", requestContent.toJSONString()), 2);
                    if ("array".equals(requestBodyStr)) {
                        String itemsTpye = StringHandler.trimString(JsonHandler.readStringByJsonPath("$.requestBody.content.*.schema.items.type", requestContent.toJSONString()), 2);
                        if ("object".equalsIgnoreCase(itemsTpye)) {
                            String propertiesStr = StringHandler.trimString(JsonHandler.readStringByJsonPath("$.requestBody.content.*.schema.items.properties", requestContent.toJSONString()), 1);
                            extractedObjectJson(propertiesStr, fieldInfos, subPath);
                        }
                        if ("array".equalsIgnoreCase(itemsTpye)) {
                            System.out.println("请手动添加接口入参为数组的Ro,对应的接口：" + subPath);
                        }
                        roFileModel.setIsJsonArray("true");
                    } else if ("object".equals(requestBodyStr)) {
                        String propertiesStr = StringHandler.trimString(JsonHandler.readStringByJsonPath("$.requestBody.content.*.schema.properties", requestContent.toJSONString()), 1);
                        // TODO 和array不一样的结构
                        extractedObjectJson2(propertiesStr, fieldInfos, subPath);
                        roFileModel.setIsJsonArray("false");
                    }
                }

                roFileModel.setRoFieldList(fieldInfos);
                if (!StringHandler.isEmpty(roFileModel.getAoFileName())) {
                    roFileModels.add(roFileModel);
                }
            }
        }
        return roFileModels;
    }

    private static void extractedObjectJson(String propertiesStr, ArrayList<FieldInfo> fieldInfos, String subPath) {
        JSONObject propertiesJson = JSONObject.parseObject(propertiesStr);
        for (String field : propertiesJson.keySet()) {
            FieldInfo fieldInfo = new FieldInfo();
            // 将部分字段中带有-替换成_,防止ro生成报错
            String fieldValue = field.replace("-", "_");
            fieldInfo.setFieldName(dealFiledCapital(fieldValue));
            String format = propertiesJson.getJSONObject(field).getString("format");
            // requestBody下的字段类型
            String fieldType = propertiesJson.getJSONObject(field).getString("type");
            if ("object".equals(fieldType)) {
                fieldInfo.setFieldType("Object");
                fieldInfo.setFieldAttribute("requestBody");
                System.out.println("Ao对象(" + subPath + ")内嵌对象，请人工手动添加对应ro字段" + fieldValue + "的值：" + propertiesJson.getJSONObject(field).get("properties").toString());
            } else if ("array".equals(fieldType)) {
                fieldInfo.setFieldType("List<?>");
                fieldInfo.setFieldAttribute("requestBody");
            } else if ("string".equals(fieldType)) {
                fieldInfo.setFieldType("String");
                if (StringHandler.isEmpty(format) && "binary".equalsIgnoreCase(format)) {
                    fieldInfo.setFieldAttribute("file");
                } else {
                    fieldInfo.setFieldAttribute("requestBody");
                }
            } else {
                fieldInfo.setFieldType(StringHandler.capitalizeFirstLetter(fieldType));
                fieldInfo.setFieldAttribute("requestBody");
            }
            fieldInfos.add(fieldInfo);
        }
    }

    private static void extractedObjectJson2(String propertiesStr, ArrayList<FieldInfo> fieldInfos, String subPath) {
        JSONObject propertiesJson = JSONObject.parseObject(propertiesStr);
        for (String field : propertiesJson.keySet()) {
            FieldInfo fieldInfo = new FieldInfo();
            // 将部分字段中带有-替换成_,防止ro生成报错
            String fieldValue = field.replace("-", "_");
            fieldInfo.setFieldName(dealFiledCapital(fieldValue));
            // requestBody下的字段类型
            String fieldType = propertiesJson.getJSONObject(field).getString("type");
            String format = propertiesJson.getJSONObject(field).getString("format");
            if ("object".equals(fieldType)) {
                fieldInfo.setFieldType("Object");
                fieldInfo.setFieldAttribute("requestBody");
                System.out.println("Ao对象(" + subPath + ")内嵌对象，请人工手动添加对应ro字段" + fieldValue + "的值：");
            } else if ("array".equals(fieldType)) {
                fieldInfo.setFieldType("List<?>");
                fieldInfo.setFieldAttribute("requestBody");
            } else if ("string".equals(fieldType) && "binary".equalsIgnoreCase(format)) {
                fieldInfo.setFieldAttribute("file");
                fieldInfo.setFieldType("String");
            } else {
                fieldInfo.setFieldType(StringHandler.capitalizeFirstLetter(fieldType));
                fieldInfo.setFieldAttribute("requestBody");
            }
            fieldInfos.add(fieldInfo);
        }
    }

    public static List<ToFileModel> extractTofromDirPath(String directoryPath) {
        List<ToFileModel> toFileModelList = new ArrayList<>();
        File directory = new File(directoryPath);

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("指定的路径不存在或不是一个有效的目录：" + directoryPath);
            return toFileModelList;
        }

        for (File projectFolder : directory.listFiles(File::isDirectory)) {
            String projectName = projectFolder.getName();
            Path projectPath = Paths.get(directoryPath, projectName);

            try (Stream<Path> javaFiles = Files.walk(projectPath).filter(path -> path.toString().endsWith(".java"))) {
                javaFiles.forEach(javaFilePath -> {
                    try {
                        CompilationUnit compilationUnit = StaticJavaParser.parse(javaFilePath.toFile());
                        compilationUnit.findAll(ClassOrInterfaceDeclaration.class).forEach(clazz -> {
                            ToFileModel toFileModel = createToFileModel(projectName, clazz);
                            toFileModelList.add(toFileModel);
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return toFileModelList;
    }

    private static ToFileModel createToFileModel(String projectName, ClassOrInterfaceDeclaration clazz) {
        ToFileModel toFileModel = new ToFileModel();
        String className = clazz.getNameAsString();

        toFileModel.setProject(projectName);
        toFileModel.setTestName(className + "Test");
        toFileModel.setController(className);
        toFileModel.setControllerLower(StringHandler.lowercaseFirstLetter(className));
        toFileModel.setMethods(extractMethods(clazz));

        return toFileModel;
    }

    private static ToFileModel createToFileModel2(String projectName, ClassOrInterfaceDeclaration clazz) {
        ToFileModel toFileModel = new ToFileModel();
        String className = clazz.getNameAsString();

        toFileModel.setProject(projectName);
        toFileModel.setTestName(className);
        toFileModel.setController(className);
        toFileModel.setControllerLower(StringHandler.lowercaseFirstLetter(className));
        toFileModel.setMethods(extractMethods2(clazz));

        return toFileModel;
    }

    private static List<toMethod> extractMethods(ClassOrInterfaceDeclaration clazz) {
        List<toMethod> methods = new ArrayList<>();

        clazz.findAll(MethodDeclaration.class).forEach(method -> {
            toMethod methodInfo = new toMethod();
            methodInfo.setMethodName(method.getNameAsString());
            List<String> parameterTypes = method.getParameters().stream().map(param -> param.getType().asString()).collect(Collectors.toList());

            String roName = parameterTypes.isEmpty() ? "noRo" : getRoName(parameterTypes.get(0));
            methodInfo.setRoName(roName);
            methodInfo.setRoClazz(roName + ".class");

            methods.add(methodInfo);
        });

        return methods;
    }

    private static List<toMethod> extractMethods2(ClassOrInterfaceDeclaration clazz) {
        List<toMethod> methods = new ArrayList<>();

        clazz.findAll(MethodDeclaration.class).forEach(method -> {
            boolean hasTestAnnotation = method.getAnnotations().stream()
                    .anyMatch(annotation -> "Test".equals(annotation.getNameAsString()));
            if (hasTestAnnotation) {
                toMethod methodInfo = new toMethod();
                methodInfo.setMethodName(method.getNameAsString());
                methods.add(methodInfo);
            }
        });
        return methods;
    }

    private static String getRoName(String parameterType) {
        if (parameterType.contains("List")) {
            List<String> matches = StringHandler.getValueByRegex(parameterType, "<(.*?)>");
            return matches.isEmpty() ? "noRo" : matches.get(0);
        }
        return parameterType;
    }


    public static List<AoFileModel> extractAofromDirPath(String directoryPath) {
        List<AoFileModel> aoFileModelList = new ArrayList<>();
        File directory = new File(directoryPath);

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("指定的路径不存在或不是一个有效的目录：" + directoryPath);
            return aoFileModelList;
        }

        for (File projectFolder : directory.listFiles(File::isDirectory)) {
            String projectName = projectFolder.getName();
            Path projectPath = Paths.get(directoryPath, projectName);

            try (Stream<Path> javaFiles = Files.walk(projectPath).filter(path -> path.toString().endsWith(".java"))) {
                javaFiles.forEach(javaFilePath -> {
                    try {
                        CompilationUnit compilationUnit = StaticJavaParser.parse(javaFilePath.toFile());
                        compilationUnit.findAll(ClassOrInterfaceDeclaration.class).forEach(clazz -> {
                            AoFileModel aoFileModel = createAoFileModel(projectName, clazz);
                            aoFileModelList.add(aoFileModel);
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return aoFileModelList;
    }

    public static List<RoFileModel> extractRofromDirPath(String directoryPath) {
        List<RoFileModel> roFileModelList = new ArrayList<>();
        File directory = new File(directoryPath);

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("指定的路径不存在或不是一个有效的目录：" + directoryPath);
            return roFileModelList;
        }

        for (File projectFolder : directory.listFiles(File::isDirectory)) {
            String projectName = projectFolder.getName();
            Path projectPath = Paths.get(directoryPath, projectName);

            try (Stream<Path> javaFiles = Files.walk(projectPath).filter(path -> path.toString().endsWith(".java"))) {
                javaFiles.forEach(javaFilePath -> {
                    try {
                        CompilationUnit compilationUnit = StaticJavaParser.parse(javaFilePath.toFile());
                        compilationUnit.findAll(ClassOrInterfaceDeclaration.class).forEach(clazz -> {
                            RoFileModel roFileModel = createRoFileModel(projectName, clazz);
                            roFileModelList.add(roFileModel);
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return roFileModelList;
    }

    private static AoFileModel createAoFileModel(String projectName, ClassOrInterfaceDeclaration clazz) {
        AoFileModel aoFileModel = new AoFileModel();
        String className = clazz.getNameAsString();

        aoFileModel.setPackageName(projectName);
        aoFileModel.setAoFileName(className);
        aoFileModel.setMethodList(extractAoMethods(clazz));

        return aoFileModel;
    }

    private static List<AoMethod> extractAoMethods(ClassOrInterfaceDeclaration clazz) {
        List<AoMethod> methods = new ArrayList<>();

        clazz.findAll(MethodDeclaration.class).forEach(method -> {
            AoMethod methodInfo = new AoMethod();
            methodInfo.setMethodName(method.getNameAsString());
            List<String> parameterTypes = method.getParameters().stream().map(param -> param.getType().asString()).collect(Collectors.toList());

            String roName = parameterTypes.isEmpty() ? "noRo" : getRoName(parameterTypes.get(0));
            methodInfo.setParamName(roName);

            methods.add(methodInfo);
        });

        return methods;
    }

    private static RoFileModel createRoFileModel(String projectName, ClassOrInterfaceDeclaration clazz) {
        RoFileModel roFileModel = new RoFileModel();
        String className = clazz.getNameAsString();
        roFileModel.setPackageName(projectName);
        roFileModel.setRoFileName(className);
        if (clazz.getExtendedTypes().isNonEmpty()) {
            String parentClassName = clazz.getExtendedTypes().get(0).getNameAsString();
            roFileModel.setAoFileName(parentClassName);
        } else {
            roFileModel.setRoFileName("");
        }
        return roFileModel;
    }

    public static List<AoFileModel> filterExistingAo(List<AoFileModel> aoFileModels) {
        // 新建存储过滤后的数组对象
        List<AoFileModel> filterAoFileModels = new ArrayList<>();
        // 获取项目已有Ao信息
        List<AoFileModel> localAoFileModels = extractAofromDirPath(PropertiesHandle.configuration.getProperty("path.base.apis", "src/main/java/Data/Apis/"));

        // 获取当前存在项目Ao文件的标识（包名+Ao文件名+方法名）
        Set<String> existingoIdentifiers = new HashSet<>();
        for (AoFileModel localAoFileModel : localAoFileModels) {
            String existingsAo = localAoFileModel.getPackageName() + localAoFileModel.getAoFileName();
            List<AoMethod> aoMethodList = localAoFileModel.getMethodList();
            for (AoMethod aoMethod : aoMethodList) {
                existingoIdentifiers.add(existingsAo + aoMethod.getMethodName());
            }
        }
        // 遍历要生成的AoModel，和项目已有的Ao对比，去掉已有的方法
        for (AoFileModel aoFileModel : aoFileModels) {
            List<AoMethod> filterAoMethods = new ArrayList<>();
            for (AoMethod aoMethod : aoFileModel.getMethodList()) {
                boolean flag = true;
                String newAoIdentifiers = aoMethod.getPackageName() + aoMethod.getBelongController() + aoMethod.getMethodName();
                for (String existingoIdentifier : existingoIdentifiers) {
                    if (newAoIdentifiers.equalsIgnoreCase(existingoIdentifier)) {
                        flag = false;
                    }
                }
                if (flag) {
                    filterAoMethods.add(aoMethod);
                }
            }
            aoFileModel.setMethodList(filterAoMethods);

            filterAoFileModels.add(aoFileModel);
        }
        return filterAoFileModels;
    }

    /*
    public static List<AoFileModel> filterExistingAo(List<AoFileModel> aoFileModels) {
        // 获取项目已有Ao信息
        String directoryPath = PropertiesHandle.configuration.getProperty("path.base.apis", "src/main/java/Data/Apis/");
        List<AoFileModel> localAoFileModels = extractAofromDirPath(directoryPath);
        StringHandler.jsonPrint("本地的Ao", localAoFileModels);
        List<AoFileModel> filterAoFileModels = new ArrayList<>();
        // 对比新老ao信息，只留增量ao
        for (AoFileModel aoFileModel : aoFileModels) {
            for (AoFileModel localAoFileModel : localAoFileModels) {
                if (aoFileModel.getPackageName().equalsIgnoreCase(localAoFileModel.getPackageName())) {
                    if (aoFileModel.getAoFileName().equalsIgnoreCase(localAoFileModel.getAoFileName())) {
                        // 创建对比后的数据容器
                        AoFileModel filterAoFileModel = new AoFileModel();
                        filterAoFileModel.setPackageName(aoFileModel.getPackageName());
                        filterAoFileModel.setAoFileName(aoFileModel.getAoFileName());
                        // 相同项目相同类下对比方法
                        List<AoMethod> aoMethods = aoFileModel.getMethodList();
                        List<AoMethod> localAoMethods = localAoFileModel.getMethodList();
                        List<AoMethod> filterAoMothodList = new ArrayList<>();
                        for (AoMethod aoMethod : aoMethods) {
                            for (AoMethod localAoMethod : localAoMethods) {
                                AoMethod filterAoMethod = new AoMethod();
                                String aoMethodName = aoMethod.getMethodName();
                                String aoParamName = aoMethod.getParamName();
                                String localAoMethodName = localAoMethod.getMethodName();
                                String localAoParamName = localAoMethod.getParamName();
                                if (!(aoMethodName + aoParamName).equalsIgnoreCase((localAoMethodName + localAoParamName))) {
                                    filterAoMethod.setPackageName(aoMethod.getPackageName());
                                    filterAoMethod.setBelongController(aoMethod.getBelongController());
                                    filterAoMethod.setMethodDescription(aoMethod.getMethodDescription());
                                    filterAoMethod.setSubPath(aoMethod.getSubPath());
                                    filterAoMethod.setType(aoMethod.getType());
                                    filterAoMethod.setMethodName(aoMethod.getMethodName());
                                    filterAoMethod.setParamName(aoMethod.getParamName());
                                    filterAoMethod.setAoIsList(aoMethod.getAoIsList());
                                    filterAoMethod.setContentType(aoMethod.getContentType());
                                    filterAoMothodList.add(filterAoMethod);
                                }
                            }
                        }
                        filterAoFileModel.setMethodList(filterAoMothodList);
                        filterAoFileModels.add(filterAoFileModel);
                    }
                }
            }
        }
        return filterAoFileModels;
    }
    */

    public static List<RoFileModel> filterExistingRo(List<RoFileModel> roFileModelList) {
        List<RoFileModel> localRoFileModels = extractRofromDirPath("src/main/java/Data/Ro/");
        // 使用 Set 存储已有的 RoFileModel 唯一标识，优化查找效率
        Set<String> existingRoIdentifiers = localRoFileModels.stream().map(RoFileModel::getIdentifier).collect(Collectors.toSet());
        // 筛选增量的 RoFileModel
        return roFileModelList.stream().filter(roFileModel -> !existingRoIdentifiers.contains(roFileModel.getIdentifier())).collect(Collectors.toList());
        // 对比新老ro信息，只留增量ao
        /*List<RoFileModel> filterRoFileModels = new ArrayList<>();
        for (RoFileModel roFileModel : roFileModelList) {
            Boolean isNewRo  = true;
            String addNewRo = roFileModel.getPackageName()+roFileModel.getAoFileName()+roFileModel.getRoFileName();
            for (RoFileModel localRoFileModel : localRoFileModels) {
                String existingRo = localRoFileModel.getPackageName() + localRoFileModel.getAoFileName() + localRoFileModel.getRoFileName();
                if (addNewRo.equals(existingRo)) {
                    isNewRo = false;
                    break;
                }
            }
            if (isNewRo) {
                filterRoFileModels.add(roFileModel);
            }
        }
        return filterRoFileModels;*/
    }

    private static boolean isParameterPresent(JSONObject requestJson) {
        Boolean result = false;
        if (requestJson.containsKey("parameters")) {
            JSONArray parameters = requestJson.getJSONArray("parameters");
            for (Object parameter : parameters) {
                JSONObject parameterItem = new JSONObject((Map<String, Object>) parameter);
                String inValue = parameterItem.getString("in");
                if ("query".equalsIgnoreCase(inValue) || "header".equalsIgnoreCase(inValue) || "path".equalsIgnoreCase(inValue)) {
                    return true;
                }
            }
        }
        if (requestJson.containsKey("requestBody")) {
            Object properties = JsonHandler.readValueByJsonPath("$.requestBody.content.*.schema.properties", requestJson.toJSONString());
            if ("net.minidev.json.JSONArray".equals(properties.getClass().getName())) {
                // 检查是否为数组且长度为1，并且唯一元素是空的 LinkedHashMap
                if (((net.minidev.json.JSONArray) properties).size() == 1 && ((net.minidev.json.JSONArray) properties).get(0) instanceof LinkedHashMap) {
                    LinkedHashMap<?, ?> map = (LinkedHashMap<?, ?>) ((net.minidev.json.JSONArray) properties).get(0);
                    if (map.isEmpty()) {
                        return false;
                    }
                }
                return true;
            }
        }
        return result;
    }

    private static List<ToFileModel> extractTofromDirPath2(String directoryPath) {
        List<ToFileModel> toFileModelList = new ArrayList<>();
        File directory = new File(directoryPath);

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("指定的路径不存在或不是一个有效的目录：" + directoryPath);
            return toFileModelList;
        }
        for (File projectFolder : directory.listFiles(File::isDirectory)) {
            String projectName = projectFolder.getName();
            Path projectPath = Paths.get(directoryPath, projectName, "ApiTest");
            try (Stream<Path> javaFiles = Files.walk(projectPath).filter(path -> path.toString().endsWith(".java"))) {
                javaFiles.forEach(javaFilePath -> {
                    try {
                        CompilationUnit compilationUnit = StaticJavaParser.parse(javaFilePath.toFile());
                        compilationUnit.findAll(ClassOrInterfaceDeclaration.class).forEach(clazz -> {
                            ToFileModel toFileModel = createToFileModel2(projectName, clazz);
                            toFileModelList.add(toFileModel);
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return toFileModelList;
    }

    public static List<ToFileModel> filterExistingTo(List<ToFileModel> toFileModel) {
        // 新建存储过滤后的数组对象
        List<ToFileModel> filterToFileModels = new ArrayList<>();

        // 2、获取已生成的ToModelList
        String directoryPath = "src/main/java/Application/TestCase/";
        List<ToFileModel> existingToList = extractTofromDirPath2(directoryPath);
        // StringHandler.jsonPrint("已存在的to", existingToList);

        Set<String> existingToFlagSet = new HashSet<>();
        for (ToFileModel toFileModels : existingToList) {
            String existingFlag = toFileModels.getProject() + toFileModels.getController();
            for (toMethod method : toFileModels.getMethods()) {
                existingToFlagSet.add(existingFlag + method.getMethodName());
            }
        }

        // 遍历要生成的AoModel，和项目已有的Ao对比，去掉已有的方法
        for (ToFileModel tofile : toFileModel) {
            List<toMethod> filterToMethods = new ArrayList<>();
            String toFileModelFlag = tofile.getProject() + tofile.getTestName();
            for (toMethod toMethod : tofile.getMethods()) {
                boolean flag = true;
                String newAoIdentifiers = toFileModelFlag + toMethod.getMethodName();
                for (String existingoIdentifier : existingToFlagSet) {
                    if (newAoIdentifiers.equalsIgnoreCase(existingoIdentifier)) {
                        flag = false;
                    }
                }
                if (flag) {
                    filterToMethods.add(toMethod);
                }
                tofile.setMethods(filterToMethods);
            }
            filterToFileModels.add(tofile);
        }
        return filterToFileModels;
    }


    private static String dealFiledCapital(String field) {
        if (field.equalsIgnoreCase("version") || field.equalsIgnoreCase("code")) {
            return StringHandler.capitalizeFirstLetter(field);
        }
        return field;
    }

}
