package pro.shamil.provectus.myrest.annotation;

import java.lang.annotation.*;

/**
 * Created by regal on 11.02.17.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(RestResources.class)
public @interface RestResource {
    String path() default "";
}
