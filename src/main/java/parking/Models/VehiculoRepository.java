package parking.Models;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;
import parking.Carro;
import parking.ConnectDatabase;


public class VehiculoRepository implements Repository<Carro>{
    private final MongoCollection<Document> collection;
    
    public VehiculoRepository() {
        MongoDatabase database = ConnectDatabase.getDatabase();
        this.collection = database.getCollection("Vehiculo");
    }
    
    @Override
    public ObjectId  save(Carro vehiculo){
        Document doc = new Document("marca",vehiculo.getMarca())
                .append("modelo", vehiculo.getModelo())
                .append("placa",vehiculo.getPlaca());
        InsertOneResult result =  this.collection.insertOne(doc);
        
        if(result.getInsertedId() != null){
            return result.getInsertedId().asObjectId().getValue();
        }
        return null;
    }
    
    @Override 
    public List<Carro> findAll(){
        List<Carro> vehiculos = new  ArrayList<>();
        for (Document doc: collection.find()){
            Carro vehiculo = new Carro(
                    doc.getDate("hora_entrada"),
                    doc.getString("placa"),
                    doc.getString("marca"),
                    doc.getString("modelo"),
                    doc.getString("propietario_vehiculos"),
                    doc.getString("descripcion")
            );
            vehiculos.add(vehiculo);
        }
        return vehiculos;
    }
    
     
    public ObjectId findRetId(String placa){
        Document doc = collection.find(new Document("placa",placa)).first();
        if(doc == null) return null;
        ObjectId id = doc.getObjectId("_id");
        return id;
    }
    
    @Override 
    public Carro findById(String placa){
        Document doc = collection.find(new Document("placa",placa)).first();
        if(doc == null) return null;
        
        return new Carro(
                doc.getDate("hora_entrada"),
                doc.getString("placa"),
                doc.getString("marca"),
                doc.getString("modelo"),
                doc.getString("propietario_vehiculo"),
                doc.getString("descripcion")
        );
    }
    
    @Override
    public void delete(String placa ){
        collection.deleteOne(new Document("placa",placa));
    }
    
    @Override
    public Carro update(Carro carro ){
        Document query = new Document("placa",carro.getPlaca());
        Document update = new Document("$set", new Document("marca",carro.getMarca()))
                .append("modelo", carro.getModelo())
                .append("propietario_vehiculo",carro.getPropietarioVehiculo())
                .append("descripcion", carro.getDescripcion());
        collection.updateOne(query,update);
        return carro;
    }
}
