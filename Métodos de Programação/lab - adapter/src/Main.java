import listToMap.ElementAdapter;
import listToMap.ListToMapAdapter;
import mapToList.MapToListAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        // Adaptação de Lista para Map
        List<Map<String, Integer>> mapList = List.of(
                new HashMap<>(),
                new ListToMapAdapter<>()
        );

        mapList.forEach(map -> {
            System.out.println("-".repeat(50));

            System.out.println();
            System.out.printf("Para o Map: " + map);
            System.out.println();

            map.put("Key-1", 50);
            map.put("Key-2", 30);
            map.put("Key-3", 10);

            System.out.println("ContainsKey 'Key-1': " + map.containsKey("Key-1"));
            System.out.println("ContainsValue 2: " + map.containsValue(2));
            System.out.println("Get 'Key-2': " + map.get("Key-2"));
            System.out.println("Remove 'Key-3': " + map.remove("Key-3"));
            System.out.println("Values: " + map.values());
            System.out.println("Size: " + map.size());
            System.out.println("IsEmpty: " + map.isEmpty());
            map.clear();
            System.out.println("Depois do clear() | IsEmpty:  " + map.isEmpty());

            System.out.println();
            System.out.println("-".repeat(50));
            System.out.println();
        });

        // -------------------------------------------------------------------------------


        // Adaptação de Map para Lista
        Map<String, Integer> map = new HashMap<>();
        map.put("Key-1", 50);
        map.put("Key-2", 30);
        map.put("Key-3", 10);

        List<Integer> list = new MapToListAdapter<>(map);

        System.out.println("-".repeat(50));
        System.out.println();
        System.out.println("Para a Lista: " + list);
        System.out.println();

        System.out.println("Contains '50': " + list.contains(50));
        System.out.println("Get(1): " + list.get(1));
        System.out.println("Size: " + list.size());
        System.out.println("IsEmpty: " + list.isEmpty());
        System.out.println("Remove '50': " + list.remove((Integer) 50));
        System.out.println("Size após remoção: " + list.size());
        list.clear();
        System.out.println("Depois do clear() | IsEmpty:  " + list.isEmpty());

        System.out.println();
        System.out.println("-".repeat(50));
        System.out.println();
    }

}