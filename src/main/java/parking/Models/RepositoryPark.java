package parking.Models;

public interface RepositoryPark<T,V> {
    T save(T entity,V v);
    V findByIdCar(T entity,V v);
    T findByIdPark(T entity);
    T addVehiculoToPark(T e,V v);
}
