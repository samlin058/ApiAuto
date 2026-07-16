package Service.Util.Universal;

import Service.Pojo.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class FileHandler {
    private static final String TEMPLATE_PATH = "src/test/resources/TemplateFile";
    private static final String OUTPUT_PATH = "src/test/java/";

    public static void main(String[] args) {
        try {
            // 示例1：从文件路径编码（默认选项）
            String base64Default = FileHandler.encodeFromFile("src/test/resources/face.jpg");
            System.out.println("Base64编码（默认）: " + base64Default.substring(0, 50) + "...");

            // 示例2：从文件路径编码（自定义选项）
            FileHandler.Base64Options options = new FileHandler.Base64Options();
            options.setIncludeMimePrefix(true);
            options.setUrlSafe(false);
            options.setIncludeNewLines(false);
            options.setOutputFormat(FileHandler.ImageFormat.PNG); // 转换为PNG格式

            String base64Custom = FileHandler.encodeFromFile("src/test/resources/face.jpg", options);
            System.out.println("Base64编码（自定义）: " + base64Custom.substring(0, 50) + "...");

            // 示例3：从File对象编码
            File imageFile = new File("src/test/resources/face.jpg");
            String base64FromFile = FileHandler.encodeFromFile(imageFile);
            System.out.println("从File对象编码: " + base64FromFile.substring(0, 50) + "...");

            // 示例4：从输入流编码
            try (FileInputStream fis = new FileInputStream("src/test/resources/face.jpg")) {
                String base64FromStream = FileHandler.encodeFromStream(
                        fis,
                        FileHandler.ImageFormat.GIF
                );
                System.out.println("从输入流编码: " + base64FromStream.substring(0, 50) + "...");
            }

            // 示例5：验证Base64字符串
            boolean isValid = FileHandler.isValidImageBase64(base64Default);
            System.out.println("Base64是否有效: " + isValid);

            // 示例6：获取Base64的图片格式
            FileHandler.ImageFormat format = FileHandler.getFormatFromBase64(base64Default);
            System.out.println("图片格式: " + (format != null ? format.getExtension() : "未知"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String loadJsonFile(String jsonFilePath) {
        String reseult = null;
        try {
            Path path = Paths.get(jsonFilePath);
            reseult = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reseult;
    }

    /**
     * 指定路径的JSON文件读取数据，并将其解析为 Java 对象
     *
     * @param filePath
     * @return
     */
    public static Object parseJsonFile(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            try {
                return objectMapper.readValue(file, new TypeReference<Object>() {
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    /**
     * 根据模板和对应的数据，在指定目录下生成对应的文件
     *
     * @param template
     * @param data
     * @param outputDirectory
     * @param fileName
     */
    private static void generateFile(Template template, Map<String, Object> data, String outputDirectory, String fileName) {
        // 创建文件对象
        File outputFile = new File(outputDirectory, fileName);
        // 如果文件已经存在，生成带时间戳的文件名
        if (outputFile.exists()) {
            String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
            String extension = fileName.substring(fileName.lastIndexOf('.'));
            String timestamp = String.valueOf(System.currentTimeMillis());
            fileName = baseName + "_" + timestamp + extension;
            outputFile = new File(outputDirectory, fileName);
        }

        // 使用FileWriter生成文件
        try (Writer writer = new FileWriter(outputFile)) {
            template.process(data, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void generateFileFromTemplate(String templateName, Map<String, Object> data, String outputDirectory, String fileName) {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
            cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            Template template = cfg.getTemplate(templateName);
            generateFile(template, data, outputDirectory, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ao文件生成器
     *
     * @param aoFileModel
     */
    public static void aoFileGenerator(AoFileModel aoFileModel) {
        String outputDirectory = OUTPUT_PATH + "Data/ApisLayer/" + aoFileModel.getPackageName() + "/";
        String fileName = aoFileModel.getAoFileName() + ".java";
        File outputFile = new File(outputDirectory, fileName);
        if (outputFile.exists()) {
            if (!aoFileModel.getMethodList().isEmpty()) {
                // 如果已经要创建的AO已经存在，那获取已存在的方法，过滤以后再去生成对应的文件
                Class<?> clazz = null;
                try {
                    clazz = Class.forName("Data.ApisLayer." + aoFileModel.getPackageName() + "." + aoFileModel.getAoFileName());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Method[] methods = clazz.getDeclaredMethods();
                List<AoMethod> filterAoMethodList = new ArrayList<>();
                for (AoMethod aoMethod : aoFileModel.getMethodList()) {
                    String methodName = aoMethod.getMethodName();
                    Boolean flag = true;
                    for (Method method : methods) {
                        if (method.getName().equalsIgnoreCase(methodName)) {
                            flag = false;
                        }
                    }
                    if (flag) {
                        filterAoMethodList.add(aoMethod);
                    }
                }
                aoFileModel.setMethodList(filterAoMethodList);
                Map<String, Object> data = new HashMap<>();
                data.put("packageName", aoFileModel.getPackageName());
                data.put("fileName", aoFileModel.getAoFileName());
                data.put("methods", aoFileModel.getMethodList());
                StringHandler.jsonPrint("此次生成方法", aoFileModel.getMethodList());
                generateFileFromTemplate("javaAo.ftl", data, outputDirectory, fileName);
            } else {
                System.out.println(aoFileModel.getAoFileName() + "类，暂无方法添加");
            }
        } else {
            if (!aoFileModel.getMethodList().isEmpty()) {
                Map<String, Object> data = new HashMap<>();
                data.put("packageName", aoFileModel.getPackageName());
                data.put("fileName", aoFileModel.getAoFileName());
                data.put("methods", aoFileModel.getMethodList());
                StringHandler.jsonPrint("此次生成方法", aoFileModel.getMethodList());
                generateFileFromTemplate("javaAo.ftl", data, outputDirectory, fileName);
            } else {
                System.out.println(aoFileModel.getAoFileName() + "类，暂无方法添加");
            }
        }

    }

    public static void aoFileGenerator2(AoFileModel aoFileModel) {
        // 定义输出路径
        String outputDirectory = OUTPUT_PATH + "Data/ApisLayer/" + aoFileModel.getPackageName() + "/";
        String fileName = aoFileModel.getAoFileName() + ".java";
        File outputFile = new File(outputDirectory, fileName);

        // 检查是否有方法需要添加
        if (aoFileModel.getMethodList().isEmpty()) {
            System.out.println(aoFileModel.getAoFileName() + "类，暂无方法添加");
            return;
        }

        // 如果文件已存在，过滤方法
        if (outputFile.exists()) {
            try {
                String className = "Data.ApisLayer." + aoFileModel.getPackageName() + "." + aoFileModel.getAoFileName();
                Class<?> clazz = Class.forName(className);
                Method[] existingMethods = clazz.getDeclaredMethods();

                // 使用Set存储已存在的方法名称，提升匹配效率
                Set<String> existingMethodNames = Arrays.stream(existingMethods)
                        .map(Method::getName)
                        .collect(Collectors.toSet());

                // 过滤掉已存在的方法
                List<AoMethod> filteredMethods = aoFileModel.getMethodList().stream()
                        .filter(method -> !existingMethodNames.contains(method.getMethodName()))
                        .collect(Collectors.toList());

                // 更新方法列表
                aoFileModel.setMethodList(filteredMethods);

            } catch (ClassNotFoundException e) {
                throw new RuntimeException("无法加载类: " + e.getMessage(), e);
            }
        }

        // 如果仍有方法需要生成，构建模板数据并生成文件
        if (!aoFileModel.getMethodList().isEmpty()) {
            Map<String, Object> templateData = new HashMap<>();
            templateData.put("packageName", aoFileModel.getPackageName());
            templateData.put("fileName", aoFileModel.getAoFileName());
            templateData.put("methods", aoFileModel.getMethodList());

            // 调试信息
            StringHandler.jsonPrint("此次生成方法", aoFileModel.getMethodList());

            // 调用模板生成文件
            generateFileFromTemplate("javaAo.ftl", templateData, outputDirectory, fileName);
        } else {
            System.out.println(aoFileModel.getAoFileName() + "类，暂无新方法需要添加");
        }
    }


    public static void roFileGenerator2(RoFileModel roFileModel) {
        try {
            if (!roFileModel.getRoFieldList().isEmpty()) {
                Map<String, Object> data = new HashMap<>();
                data.put("packageName", roFileModel.getPackageName());
                data.put("ControllerName", roFileModel.getAoFileName());
                data.put("roFileName", roFileModel.getRoFileName());
                data.put("roFieldList", roFileModel.getRoFieldList());
                data.put("isJsonArray", roFileModel.getIsJsonArray());
                String outputDirectory = OUTPUT_PATH + "Data/Ro/" + roFileModel.getPackageName() + "/";
                String fileName = roFileModel.getRoFileName() + ".java";
                System.out.println(data);
                generateFileFromTemplate("javaRo.ftl", data, outputDirectory, fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void toFileGenerator2(ToFileModel toFileModel) {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("project", toFileModel.getProject());
            data.put("controller", toFileModel.getController());
            data.put("controllerLower", toFileModel.getControllerLower());
            data.put("testName", toFileModel.getTestName());
            data.put("methods", toFileModel.getMethods());

            String outputDirectory = OUTPUT_PATH + "Application/TestCase/" + toFileModel.getProject() + "/ApiTest";
            String fileName = toFileModel.getTestName() + ".java";
            generateFileFromTemplate("javaTo.ftl", data, outputDirectory, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * ro文件生成器
     *
     * @param roFileModel
     */
    public static void roFileGenerator(RoFileModel roFileModel) {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
            cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            Template template = cfg.getTemplate("javaRo.ftl");
            Map<String, Object> data = new HashMap<>();

            data.put("packageName", roFileModel.getPackageName());
            data.put("ControllerName", roFileModel.getAoFileName());
            data.put("roName", roFileModel.getRoFileName());
            data.put("roFieldList", roFileModel.getRoFieldList());
            data.put("isJsonArray", roFileModel.getIsJsonArray());

            String outputDirectory = OUTPUT_PATH + "Data/Ro/" + roFileModel.getPackageName() + "/";
            String fileName = roFileModel.getRoFileName() + ".java";
            generateFile(template, data, outputDirectory, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * to文件生成器
     *
     * @param toFileModel
     */
    public static void toFileGenerator(ToFileModel toFileModel) {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
            cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            Template template = cfg.getTemplate("javaTo.ftl");

            Map<String, Object> data = new HashMap<>();
            data.put("project", toFileModel.getProject());
            data.put("controller", toFileModel.getController());
            data.put("controllerLower", toFileModel.getControllerLower());
            data.put("testName", toFileModel.getTestName());
            data.put("methods", toFileModel.getMethods());

            String outputDirectory = OUTPUT_PATH + "Application/TestCase/" + toFileModel.getProject() + "/ApiTest";
            String fileName = toFileModel.getTestName() + ".java";
            generateFile(template, data, outputDirectory, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void toFileGenerator3(ToFileModel toFileModel) {
        String outputDirectory = OUTPUT_PATH + "Application/TestCase/" + toFileModel.getProject() + "/ApiTest";
        String fileName = toFileModel.getTestName() + ".java";
        File outputFile = new File(outputDirectory, fileName);

        // 检查是否有方法需要添加
        if (toFileModel.getMethods().isEmpty()) {
            System.out.println(toFileModel.getController() + "类，暂无方法添加");
            return;
        }
        // 如果文件已存在，过滤方法
        if (outputFile.exists()) {
            try {
                String className = "Application.TestCase." + toFileModel.getProject() + ".ApiTest." + toFileModel.getTestName();
                Class<?> clazz = Class.forName(className);
                Method[] existingMethods = clazz.getDeclaredMethods();

                // 使用Set存储已存在的方法名称，提升匹配效率
                Set<String> existingMethodNames = Arrays.stream(existingMethods)
                        .map(Method::getName)
                        .collect(Collectors.toSet());


                // 过滤掉已存在的方法
                List<toMethod> filteredMethods = toFileModel.getMethods().stream()
                        .filter(method -> !existingMethodNames.contains(method.getMethodName()))
                        .collect(Collectors.toList());

                // 更新方法列表
                toFileModel.setMethods(filteredMethods);

            } catch (ClassNotFoundException e) {
                throw new RuntimeException("无法加载类: " + e.getMessage(), e);
            }
        }

        // 如果仍有方法需要生成，构建模板数据并生成文件
        if (!toFileModel.getMethods().isEmpty()) {
            Map<String, Object> templateData = new HashMap<>();
            templateData.put("project", toFileModel.getProject());
            templateData.put("controller", toFileModel.getController());
            templateData.put("controllerLower", toFileModel.getControllerLower());
            templateData.put("testName", toFileModel.getTestName());
            templateData.put("methods", toFileModel.getMethods());

            // 调试信息
            StringHandler.jsonPrint("此次生成方法", toFileModel.getMethods());

            // 调用模板生成文件
            generateFileFromTemplate("javaTo.ftl", templateData, outputDirectory, fileName);
        } else {
            System.out.println(toFileModel.getController() + "类，暂无新方法需要添加");
        }
    }

    /**
     * 检查给定的包名或项目名称是否存在于项目名称列表中。如果存在，则返回列表中的匹配项；如果不存在，则返回传入的原始值。
     *
     * @param source
     * @return
     */
    public static String getPackageName(String source) {
        List<String> projectNameList = getProjectNameList();
        for (String s : projectNameList) {
            if (s.equalsIgnoreCase(source)) {
                return s;  // 找到匹配项，立即返回
            }
        }
        return source;  // 如果没有找到匹配项，则返回原始值
    }

    private static List<String> getProjectNameList() {
        String folderPath = OUTPUT_PATH + PropertiesHandle.configuration.getProperty("path.output.apis", "Data/ApisLayer/");
        List<String> fileName = new ArrayList<>();
        // 创建一个File对象
        File folder = new File(folderPath);
        // 检查文件夹是否存在
        if (folder.exists() && folder.isDirectory()) {
            // 获取文件夹中的所有文件夹
            File[] subFolders = folder.listFiles(File::isDirectory);
            // 遍历文件夹并输出文件夹名称
            if (subFolders != null) {
                for (File subFolder : subFolders) {
                    fileName.add(subFolder.getName());
                }
            }
        }
        return fileName;
    }

    /**
     * 根据传入的文件名，在指定的源代码目录中查找文件，并返回该文件相对于源代码目录的相对路径。
     * 如果文件未找到，方法会返回 "null" 字符串，并在控制台输出提示信息
     *
     * @param fileName
     * @return
     */
    public static String getFilePath(String fileName) {
        String srcDirectoryPath = getSrcDirectoryPath(); // 指定目录路径
        String filePath = searchFile(new File(srcDirectoryPath), fileName);
        if (filePath != null) {
            String relativePath = getRelativePath(srcDirectoryPath, filePath);
            return relativePath;
        } else {
            System.out.println("文件未找到,返回null");
        }
        return "null";
    }

    private static String searchFile(File directory, String targetFileName) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    String filePath = searchFile(file, targetFileName);
                    if (filePath != null) {
                        return filePath;
                    }
                } else if (file.getName().equals(targetFileName)) {
                    return file.getAbsolutePath();
                }
            }
        }
        return null;
    }

    private static String getSrcDirectoryPath() {
        String currentDirectory = System.getProperty("user.dir");
        File srcDirectory = new File(currentDirectory + File.separator + "src");
        if (srcDirectory.exists() && srcDirectory.isDirectory()) {
            return srcDirectory.getAbsolutePath();
        }
        return null;
    }

    private static String getRelativePath(String base, String path) {
        if (!path.startsWith(base)) {
            throw new IllegalArgumentException("文件路径不在项目中。");
        }
        // 使用 File.separator 替换字符串 "/" 或 "\"，以兼容不同操作系统
        String separator = File.separator.replace("\\", "\\\\");
        String relativePath = path.substring(base.length() + 1).replaceAll(separator, "/");
        return "src/" + relativePath; // 添加 "src" 到相对路径
    }

    /**
     * 文件夹是否存在
     *
     * @param fileName 文件夹名称
     */
    public static boolean folderIsExist(String fileName) {
        Boolean result = null;
        // 要检查的文件夹路径
        String folderPath = PropertiesHandle.configuration.getProperty("path.base.apis", "src/test/java/Data/ApisLayer/") + fileName;
        // 创建一个File对象，表示要检查的文件夹
        File folder = new File(folderPath);
        if (folder.exists() && folder.isDirectory()) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    /**
     * 根据文件是否存在进行删除
     * @param filePath 文件路径
     */
    public static void deleteFile(String filePath){
        // 创建文件对象
        File file = new File(filePath);
        // 判断文件是否存在
        if (file.exists()) {
            // 如果文件存在，则删除
            if (file.delete()) {
                System.out.println("文件删除成功");
            } else {
                System.out.println("文件删除失败");
            }
        } else {
            System.out.println("文件不存在");
        }
    }


    /**
     * 图片格式枚举
     */
    public enum ImageFormat {
        PNG("png", "image/png"),
        JPEG("jpeg", "image/jpeg"),
        JPG("jpg", "image/jpg"),
        GIF("gif", "image/gif"),
        BMP("bmp", "image/bmp"),
        WEBP("webp", "image/webp"),
        SVG("svg", "image/svg+xml");

        private final String extension;
        private final String mimeType;

        ImageFormat(String extension, String mimeType) {
            this.extension = extension;
            this.mimeType = mimeType;
        }

        public String getExtension() {
            return extension;
        }

        public String getMimeType() {
            return mimeType;
        }
    }

    /**
     * Base64配置选项
     */
    public static class Base64Options {
        private boolean includeMimePrefix = true;
        private boolean urlSafe = false;
        private boolean includeNewLines = false;
        private ImageFormat outputFormat = null;

        public Base64Options() {}

        public Base64Options(boolean includeMimePrefix, boolean urlSafe, boolean includeNewLines) {
            this.includeMimePrefix = includeMimePrefix;
            this.urlSafe = urlSafe;
            this.includeNewLines = includeNewLines;
        }

        // Getters and Setters
        public boolean isIncludeMimePrefix() { return includeMimePrefix; }
        public void setIncludeMimePrefix(boolean includeMimePrefix) { this.includeMimePrefix = includeMimePrefix; }

        public boolean isUrlSafe() { return urlSafe; }
        public void setUrlSafe(boolean urlSafe) { this.urlSafe = urlSafe; }

        public boolean isIncludeNewLines() { return includeNewLines; }
        public void setIncludeNewLines(boolean includeNewLines) { this.includeNewLines = includeNewLines; }

        public ImageFormat getOutputFormat() { return outputFormat; }
        public void setOutputFormat(ImageFormat outputFormat) { this.outputFormat = outputFormat; }
    }

    // ==================== 主要编码方法 ====================

    /**
     * 从文件路径编码图片为Base64
     * @param imagePath 图片文件路径
     * @return Base64编码字符串
     * @throws IOException 文件读取异常
     */
    public static String encodeFromFile(String imagePath) throws IOException {
        return encodeFromFile(imagePath, new Base64Options());
    }

    /**
     * 从文件路径编码图片为Base64（带选项）
     * @param imagePath 图片文件路径
     * @param options Base64编码选项
     * @return Base64编码字符串
     * @throws IOException 文件读取异常
     */
    public static String encodeFromFile(String imagePath, Base64Options options) throws IOException {
        Path path = Paths.get(imagePath);
        if (!Files.exists(path)) {
            throw new FileNotFoundException("图片文件不存在: " + imagePath);
        }

        byte[] imageBytes = Files.readAllBytes(path);
        ImageFormat format = detectImageFormat(path);

        return encodeBytes(imageBytes, format, options);
    }

    /**
     * 从File对象编码图片为Base64
     * @param imageFile 图片文件对象
     * @return Base64编码字符串
     * @throws IOException 文件读取异常
     */
    public static String encodeFromFile(File imageFile) throws IOException {
        return encodeFromFile(imageFile, new Base64Options());
    }

    /**
     * 从File对象编码图片为Base64（带选项）
     * @param imageFile 图片文件对象
     * @param options Base64编码选项
     * @return Base64编码字符串
     * @throws IOException 文件读取异常
     */
    public static String encodeFromFile(File imageFile, Base64Options options) throws IOException {
        if (!imageFile.exists()) {
            throw new FileNotFoundException("图片文件不存在: " + imageFile.getAbsolutePath());
        }

        byte[] imageBytes = Files.readAllBytes(imageFile.toPath());
        ImageFormat format = detectImageFormat(imageFile.toPath());

        return encodeBytes(imageBytes, format, options);
    }

    /**
     * 从输入流编码图片为Base64
     * @param inputStream 图片输入流
     * @param format 图片格式（如果为null则尝试自动检测）
     * @return Base64编码字符串
     * @throws IOException 流读取异常
     */
    public static String encodeFromStream(InputStream inputStream, ImageFormat format) throws IOException {
        return encodeFromStream(inputStream, format, new Base64Options());
    }

    /**
     * 从输入流编码图片为Base64（带选项）
     * @param inputStream 图片输入流
     * @param format 图片格式（如果为null则尝试自动检测）
     * @param options Base64编码选项
     * @return Base64编码字符串
     * @throws IOException 流读取异常
     */
    public static String encodeFromStream(InputStream inputStream, ImageFormat format, Base64Options options)
            throws IOException {
        // 为了检测格式，需要复制流或使用mark/reset
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, bytesRead);
        }
        buffer.flush();

        byte[] imageBytes = buffer.toByteArray();

        // 如果格式未指定，尝试从字节检测
        if (format == null) {
            try (ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes)) {
                format = detectImageFormatFromStream(bis);
            }
        }

        return encodeBytes(imageBytes, format, options);
    }

    /**
     * 从BufferedImage编码图片为Base64
     * @param image 图片对象
     * @param format 输出格式
     * @return Base64编码字符串
     * @throws IOException 编码异常
     */
    public static String encodeFromBufferedImage(BufferedImage image, ImageFormat format) throws IOException {
        return encodeFromBufferedImage(image, format, new Base64Options());
    }

    /**
     * 从BufferedImage编码图片为Base64（带选项）
     * @param image 图片对象
     * @param format 输出格式
     * @param options Base64编码选项
     * @return Base64编码字符串
     * @throws IOException 编码异常
     */
    public static String encodeFromBufferedImage(BufferedImage image, ImageFormat format, Base64Options options)
            throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        String formatName = format.getExtension();
        if (!ImageIO.write(image, formatName, outputStream)) {
            throw new IOException("不支持将图片写入格式: " + formatName);
        }

        byte[] imageBytes = outputStream.toByteArray();
        return encodeBytes(imageBytes, format, options);
    }

    /**
     * 从字节数组编码图片为Base64
     * @param imageBytes 图片字节数组
     * @param format 图片格式
     * @return Base64编码字符串
     */
    public static String encodeFromBytes(byte[] imageBytes, ImageFormat format) {
        return encodeBytes(imageBytes, format, new Base64Options());
    }

    // ==================== 私有辅助方法 ====================

    /**
     * 核心编码方法
     */
    private static String encodeBytes(byte[] imageBytes, ImageFormat format, Base64Options options) {
        // 应用输出格式选项（如果需要转换格式）
        byte[] finalBytes = imageBytes;
        ImageFormat finalFormat = format;

        if (options.getOutputFormat() != null && !options.getOutputFormat().equals(format)) {
            try {
                // 转换图片格式
                ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
                BufferedImage image = ImageIO.read(bis);

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ImageIO.write(image, options.getOutputFormat().getExtension(), bos);
                finalBytes = bos.toByteArray();
                finalFormat = options.getOutputFormat();
            } catch (IOException e) {
                // 如果转换失败，使用原始格式
                System.err.println("图片格式转换失败，使用原始格式: " + e.getMessage());
            }
        }

        // 配置Base64编码器
        Base64.Encoder encoder = options.isUrlSafe()
                ? Base64.getUrlEncoder()
                : Base64.getEncoder();

        if (!options.isIncludeNewLines()) {
            encoder = encoder.withoutPadding();
        }

        // 执行编码
        String base64 = encoder.encodeToString(finalBytes);

        // 添加MIME前缀
        if (options.isIncludeMimePrefix() && finalFormat != null) {
            return "data:" + finalFormat.getMimeType() + ";base64," + base64;
        }

        return base64;
    }

    /**
     * 从文件路径检测图片格式
     */
    private static ImageFormat detectImageFormat(Path path) throws IOException {
        String fileName = path.getFileName().toString().toLowerCase();

        // 根据文件扩展名检测
        if (fileName.endsWith(".png")) return ImageFormat.PNG;
        if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) return ImageFormat.JPEG;
        if (fileName.endsWith(".gif")) return ImageFormat.GIF;
        if (fileName.endsWith(".bmp")) return ImageFormat.BMP;
        if (fileName.endsWith(".webp")) return ImageFormat.WEBP;
        if (fileName.endsWith(".svg")) return ImageFormat.SVG;

        // 尝试使用ImageIO检测
        try (InputStream is = Files.newInputStream(path)) {
            return detectImageFormatFromStream(is);
        }
    }

    /**
     * 从输入流检测图片格式
     */
    private static ImageFormat detectImageFormatFromStream(InputStream is) throws IOException {
        // 使用ImageIO的ImageReader检测格式
        try (ImageInputStream imageInputStream = ImageIO.createImageInputStream(is)) {
            Iterator<ImageReader> readers = ImageIO.getImageReaders(imageInputStream);

            if (readers.hasNext()) {
                ImageReader reader = readers.next();
                String formatName = reader.getFormatName().toLowerCase();

                switch (formatName) {
                    case "png": return ImageFormat.PNG;
                    case "jpeg": return ImageFormat.JPEG;
                    case "gif": return ImageFormat.GIF;
                    case "bmp": return ImageFormat.BMP;
                    case "webp": return ImageFormat.WEBP;
                    default: throw new IOException("不支持的图片格式: " + formatName);
                }
            }
        }

        throw new IOException("无法检测图片格式");
    }

    // ==================== 工具方法 ====================

    /**
     * 验证Base64字符串是否为有效的图片数据
     * @param base64String Base64字符串
     * @return 是否为有效的图片Base64
     */
    public static boolean isValidImageBase64(String base64String) {
        if (base64String == null || base64String.isEmpty()) {
            return false;
        }

        try {
            // 移除可能的MIME前缀
            String pureBase64 = base64String;
            if (base64String.startsWith("data:")) {
                int base64Index = base64String.indexOf("base64,");
                if (base64Index != -1) {
                    pureBase64 = base64String.substring(base64Index + 7);
                }
            }

            // 解码验证
            byte[] decodedBytes = Base64.getDecoder().decode(pureBase64);

            // 尝试读取为图片
            ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
            BufferedImage image = ImageIO.read(bis);
            return image != null;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 从Base64字符串获取图片格式
     * @param base64String Base64字符串
     * @return 图片格式，如果无法检测则返回null
     */
    public static ImageFormat getFormatFromBase64(String base64String) {
        try {
            // 如果有MIME前缀，从其中提取
            if (base64String.startsWith("data:")) {
                int mimeEnd = base64String.indexOf(";");
                if (mimeEnd != -1) {
                    String mimeType = base64String.substring(5, mimeEnd);
                    for (ImageFormat format : ImageFormat.values()) {
                        if (format.getMimeType().equals(mimeType)) {
                            return format;
                        }
                    }
                }
            }

            // 解码并检测
            String pureBase64 = base64String;
            if (base64String.startsWith("data:")) {
                int base64Index = base64String.indexOf("base64,");
                if (base64Index != -1) {
                    pureBase64 = base64String.substring(base64Index + 7);
                }
            }

            byte[] decodedBytes = Base64.getDecoder().decode(pureBase64);
            try (ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes)) {
                return detectImageFormatFromStream(bis);
            }
        } catch (Exception e) {
            return null;
        }
    }

}

