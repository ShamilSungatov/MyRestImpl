package pro.shamil.provectus.myrest.core.mapping;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by regal on 18.02.17.
 */
public abstract class AnnotationClassesResolver {
    private List<Class<? extends  Annotation> > annotationList;

    public AnnotationClassesResolver() {
        annotationList = new ArrayList<>();
    }

    public void findAnnotatedClasses(AbstractConfiguration configuration){
        if (annotationList.size() == 0){return;}

        Mapping mapping = new Mapping();
        String path = configuration.getPath();

        List<Class<?>> classes = mapping.find(path);

        Map<Annotation, List<Class<?>>> classMap = AnnotationClassesContainer.getInstance().getClassMap();
        for (Class<?> aClass : classes) {
            for (Annotation annotation : aClass.getAnnotations()) {
                if (annotationList.contains(annotation.annotationType())){
                    List<Class<?>> classesList = classMap.computeIfAbsent(annotation, annotation1 -> { return new ArrayList<Class<?>>();});

                    classesList.add( aClass);
                }
            }
        }

        for (List<Class<?>> classList : classMap.values()) {
            for (Class<?> aClass : classList) {
                resolve(aClass);
            }
        }
    }

    protected abstract void resolve(Class<?> clazz);

    public List<Class<? extends  Annotation>> getAnnotationList() {
        return annotationList;
    }
}
