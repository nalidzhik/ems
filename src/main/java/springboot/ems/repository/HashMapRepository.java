package springboot.ems.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapRepository<T,ID> implements Repository<T,ID> {

    protected final Map<ID, T> repository = new HashMap<>();

    @Override
    public T get(ID id) {
        return repository.get(id);
    }

    @Override
    public T delete(ID id) {
        T t = repository.get(id);
        repository.remove(id);
        return t;
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(repository.values());
    }

    @Override
    public T save(ID id, T t) {
        repository.put(id, t);
        return repository.get(id);
    }
}
