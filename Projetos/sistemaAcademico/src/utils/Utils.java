package utils;

import general.Subject;
import java.util.*;

public class Utils {

    public static <T extends Subject> Subject getSubjectWithBiggerName(List<T> subjects) {
        return subjects
                .stream()
                .reduce(subjects.get(0), (prev, curr) -> prev.getName().length() > curr.getName().length() ? prev : curr);
    }




}
