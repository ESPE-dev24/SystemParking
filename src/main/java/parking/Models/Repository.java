package parking.Models;
import java.util.List;
import org.bson.types.ObjectId;

public interface Repository<T> {
    ObjectId save(T entity);
    T findById(String id);
    List<T> findAll();
    T update(T entity);
    void delete(String id);
}
