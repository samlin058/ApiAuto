package Service.Annotations.AnnotationLibrary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME) // 指定注解在运行时保留
@Target(ElementType.METHOD) // 指定注解可作用于方法
public @interface Mapping {
    String sub_path();
    String requestType();
    String contentTpye()  default "";

}

