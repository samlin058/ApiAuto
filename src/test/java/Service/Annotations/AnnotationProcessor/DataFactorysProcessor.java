package Service.Annotations.AnnotationProcessor;

import Service.Annotations.AnnotationLibrary.DataFactory;
import Service.Annotations.AnnotationLibrary.DataFactorys;
import Service.Util.Universal.StringHandler;
import org.testng.IInvokedMethod;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class DataFactorysProcessor {

    public static void Before(IInvokedMethod method) {
        Method testMethod = method.getTestMethod().getConstructorOrMethod().getMethod();
        if (testMethod.isAnnotationPresent(DataFactory.class)) {
            processDependence(testMethod.getAnnotation(DataFactory.class));
        } else if (testMethod.isAnnotationPresent(DataFactorys.class)) {
            DataFactory[] dataFactoryAnnotations = testMethod.getAnnotationsByType(DataFactory.class);
            for (DataFactory dataFactoryAnnotation : dataFactoryAnnotations) {
                processDependence(dataFactoryAnnotation);
            }
        }
    }

    private static void processDependence(DataFactory dataFactory) {
        String methodName = dataFactory.method();
        String className = dataFactory.className();
        String[] param = dataFactory.param();
        String[] sql = dataFactory.sql();
        invokeMethod(className, methodName, param);
        checkAndExecuteSql(sql);
    }

    private static void invokeMethod(String className, String methodName, String[] param) {
        try {
            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            Object instance = constructor.newInstance();
            Method method = clazz.getDeclaredMethod(methodName, List.class);
            method.invoke(instance, Arrays.asList(param));
            if (instance instanceof AutoCloseable) {
                ((AutoCloseable) instance).close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkAndExecuteSql(String[] sql) {
        List<String> stringList = Arrays.asList(sql);
        if (stringList.size() == 1) {
            for (String a : stringList) {
                if (!StringHandler.isEmpty(a)) {
                    executeSql(sql);
                }
            }
        } else {
            //TODO
            executeSql(sql);
        }
    }

    private static void executeSql(String[] sql) {
        // TODO 具体执行的sql
    }

}

