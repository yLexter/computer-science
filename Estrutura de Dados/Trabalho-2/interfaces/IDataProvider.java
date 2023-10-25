package interfaces;

import java.util.List;
import java.util.Map;

public interface IDataProvider<T extends Comparable<T>> {
    Map<String, List<List<T>>> getMassTestForInsert ();
    Map<String, List<List<T>>> getMassTestForSearch();
}
