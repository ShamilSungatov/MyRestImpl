package pro.shamil.provectus.myrest.core.mapping;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by regal on 18.02.17.
 */
public class AnnotationClassesContainer {

    private final static AnnotationClassesContainer INSTANCE = new AnnotationClassesContainer();

    public static AnnotationClassesContainer getInstance() {
        return INSTANCE;
    }

    private Map<Annotation, List<Class<?>>> classMap;

    private AnnotationClassesContainer() {
        classMap = new HashMap<>();
    }

    public Map<Annotation, List<Class<?>>> getClassMap() {
        return classMap;
    }
}
