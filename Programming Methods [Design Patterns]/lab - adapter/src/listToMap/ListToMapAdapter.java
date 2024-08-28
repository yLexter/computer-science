package listToMap;

import java.util.*;
import java.util.stream.Collectors;

public class ListToMapAdapter<K, V> implements Map<K, V> {

    private List<ElementAdapter<K, V>> adapterList;

    public ListToMapAdapter() {
        this.adapterList = new ArrayList<>();
    }

    public ListToMapAdapter(List<ElementAdapter<K, V>> adapterList) {
        this.adapterList = adapterList;
    }

    private ElementAdapter<K, V> getObject(Object key) {
        return adapterList
                .stream()
                .filter(element -> element.getKey().equals(key))
                .findAny()
                .orElse(null);
    }

    @Override
    public int size() {
        return adapterList.size();
    }

    @Override
    public boolean isEmpty() {
        return adapterList.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        for (var element : adapterList) {
            if (element.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (var element : adapterList) {
            if (element.getValue().equals(value)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public V get(Object key) {
        for (var element : adapterList) {
            if (element.getKey().equals(key)) {
                return element.getValue();
            }
        }

        return null;
    }

    @Override
    public V put(K key, V value) {
        var element = new ElementAdapter<>(key, value);

        if (containsKey(key)) {
            remove(key);
        }

        adapterList.add(element);

        return element.getValue();
    }

    @Override
    public V remove(Object key) {
        var element = getObject(key);

        if (element == null) {
            return null;
        }

        adapterList.remove(element);

        return element.getValue();
    }

    @Override
    public Collection<V> values() {
        return adapterList
                .stream()
                .map(ElementAdapter::getValue)
                .collect(Collectors.toList());
    }

    @Override
    public void clear() {
        adapterList = new ArrayList<>();
    }


    // Métodos não pedidos, mas obrigatórios na interface
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        throw new RuntimeException("Método não pedido");
    }

    @Override
    public Set<K> keySet() {
        throw new RuntimeException("Método não pedido");
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new RuntimeException("Método não pedido");
    }

    // -------------------------------------------
    @Override
    public String toString() {
        return "ListToMapAdapter";
    }
}
