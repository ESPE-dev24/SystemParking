package parking.Models;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;
import parking.Carro;
import parking.ConnectDatabase;
import parking.Parking;


public class ParkingRepository implements RepositoryPark<Parking,Carro>{
    private final MongoCollection<Document> collection;

    public ParkingRepository() {
        MongoDatabase database = ConnectDatabase.getDatabase();
        this.collection = database.getCollection("Parking");
    }
    
    @Override 
    public Parking save(Parking park,Carro carro){
        List<ObjectId> ids = Arrays.asList(carro.getIdVehiculo());
        Document doc = new Document("id_park",park.getIdPark())
                .append("vehiculos",ids)
                .append("numero_espacios",park.size_espacios());
        
        this.collection.insertOne(doc);
        return park;
    }
    
    @Override
    public  Carro findByIdCar(Parking park ,Carro car ){
        Document filtro = new Document("id_park",park.getIdPark()).append("vehiculos",car.getIdVehiculo());
        Document doc = this.collection.find(filtro).first();
        
        if(doc == null ) return null;
        return car;
    }
    
    @Override 
    public Parking findByIdPark(Parking park){
        Document filtro = new Document("id_park",park.getIdPark());
        Document doc = this.collection.find(filtro).first();
        if(doc == null ) return null;
        return park;
    }
    
    @Override 
    public Parking addVehiculoToPark(Parking park,Carro car){
        Document query = new Document("id_park",park.getIdPark());
        Document update = new Document("$push", new Document("vehiculos",car.getIdVehiculo()));
        this.collection.updateOne(query, update);
        return park;
    }
    
}
