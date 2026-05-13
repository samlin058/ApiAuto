package Service.Annotations.AnnotationLibrary;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(DataFactorys.class)
public @interface DataFactory {
    String className() default "";

    String method() default "";

    String[] param() default "";

    String[] sql() default "";
}





