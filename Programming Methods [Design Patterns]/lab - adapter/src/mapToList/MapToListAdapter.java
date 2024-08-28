package mapToList;

import java.util.*;
import java.util.stream.Collectors;

public class MapToListAdapter<K, V> implements List<V> {
    private Map<K, V> map;
    private List<K> keys;

    public MapToListAdapter(Map<K, V> map) {
        this.map = map;
        this.keys = new ArrayList<>(map.keySet());
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsValue(o);
    }

    @Override
    public Iterator<V> iterator() {
        return map.values().iterator();
    }

    @Override
    public Object[] toArray() {
        return map.values().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return map.values().toArray(a);
    }

    @Override
    public boolean add(V v) {
        throw new UnsupportedOperationException("Add operation is not supported");
    }

    @Override
    public boolean remove(Object o) {
        K key = null;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (Objects.equals(entry.getValue(), o)) {
                key = entry.getKey();
                break;
            }
        }
        return key != null && map.remove(key) != null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return map.values().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends V> c) {
        throw new UnsupportedOperationException("AddAll operation is not supported");
    }

    @Override
    public boolean addAll(int index, Collection<? extends V> c) {
        throw new UnsupportedOperationException("AddAll operation is not supported");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object o : c) {
            if (remove(o)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Iterator<V> it = map.values().iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public V get(int index) {
        K key = keys.get(index);
        return map.get(key);
    }

    @Override
    public V set(int index, V element) {
        K key = keys.get(index);
        return map.put(key, element);
    }

    @Override
    public void add(int index, V element) {
        throw new UnsupportedOperationException("Add operation is not supported");
    }

    @Override
    public V remove(int index) {
        K key = keys.remove(index);
        return map.remove(key);
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        for (V value : map.values()) {
            if (Objects.equals(value, o)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        // Percorre a lista de chaves e verifica se o objeto está presente
        for (int i = keys.size() - 1; i >= 0; i--) {
            if (Objects.equals(o, keys.get(i))) {
                return i; // Retorna o índice da última ocorrência do objeto
            }
        }
        return -1; // Retorna -1 se o objeto não for encontrado na lista de chaves

    }

    @Override
    public ListIterator<V> listIterator() {
        return new ListIterator<>() {
            private final Iterator<V> it = map.values().iterator();
            private int index = 0;

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public V next() {
                index++;
                return it.next();
            }

            @Override
            public boolean hasPrevious() {
                return index > 0;
            }

            @Override
            public V previous() {
                throw new UnsupportedOperationException("Previous operation is not supported");
            }

            @Override
            public int nextIndex() {
                return index;
            }

            @Override
            public int previousIndex() {
                return index - 1;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove operation is not supported");
            }

            @Override
            public void set(V v) {
                throw new UnsupportedOperationException("Set operation is not supported");
            }

            @Override
            public void add(V v) {
                throw new UnsupportedOperationException("Add operation is not supported");
            }
        };
    }

    @Override
    public ListIterator<V> listIterator(int index) {
        throw new UnsupportedOperationException("ListIterator operation is not supported");
    }

    @Override
    public List<V> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("SubList operation is not supported");
    }

    @Override
    public String toString() {
        return "MapToListAdapter";
    }
}
