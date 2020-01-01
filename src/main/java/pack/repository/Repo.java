package pack.repository;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

public interface Repo<T> {
    int save(T t);
    T read(int id);
    int update(T t);
    int delete(int id);
    List<T> readAll();
}
