package Service.Annotations.AnnotationLibrary;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(Dependences.class)
public @interface Dependence {
    String project() default "";
    String Class() default "";
    String method() default "";
    // 只支持json字符串
    String param() default "";
    String keys() default "";
}