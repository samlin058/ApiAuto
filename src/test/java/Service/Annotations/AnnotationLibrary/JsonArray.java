package Service.Annotations.AnnotationLibrary;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) // 指定注解在运行时保留
public @interface JsonArray {
}
