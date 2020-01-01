package pack.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("FilmRepoImpl")
public class FilmRepoImpl<T> implements Repo<T> {
    @Override
    public int save(T t) {
        return 0;
    }

    @Override
    public T read(int id) {
        return null;
    }

    @Override
    public int update(T t) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public List<T> readAll() {
        return null;
    }
}
