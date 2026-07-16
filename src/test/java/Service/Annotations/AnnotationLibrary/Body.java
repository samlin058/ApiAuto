package Service.Annotations.AnnotationLibrary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 指定注解在运行时保留
@Target(ElementType.FIELD)
public @interface Body {
}
