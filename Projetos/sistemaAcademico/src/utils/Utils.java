package utils;
import java.lang.reflect.Field;
import general.Subject;

import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

public class Utils {

    public static <T extends Subject> Subject getSubjectWithBiggerName(List<T> subjects) {
        return subjects
                .stream()
                .reduce(subjects.get(0), (prev, curr) -> prev.getName().length() > curr.getName().length() ? prev : curr);
    }

    public static List<String> getClassAttributes(Object object) {

        Class<?> objectClass = object.getClass();

        List<String> atributtersSuperClass = new ArrayList<>(Arrays.stream(objectClass.getSuperclass().getDeclaredFields())
                        .filter(field -> !Modifier.isStatic(field.getModifiers()))
                        .map(Field::getName)
                        .toList());

        List<String> atributtersCurrentClass = Arrays.stream(objectClass.getDeclaredFields())
                 .filter(field -> !Modifier.isStatic(field.getModifiers()))
                 .map(Field::getName)
                 .toList();

        atributtersSuperClass.addAll(atributtersCurrentClass);

        return atributtersSuperClass;
    }




}
