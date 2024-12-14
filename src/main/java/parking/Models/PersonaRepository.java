package parking.Models;
import parking.Cliente;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.List;
import org.bson.Document;
import parking.ConnectDatabase;
import org.bson.types.ObjectId;
import com.mongodb.client.result.InsertOneResult;
import java.util.ArrayList;
import java.util.Arrays;

public class PersonaRepository implements Repository<Cliente> {
    private final MongoCollection<Document> collection;

    public PersonaRepository() {
        MongoDatabase database = ConnectDatabase.getDatabase();
        this.collection = database.getCollection("Persona");
    }
    
    @Override 
    public ObjectId save(Cliente cliente){
        List<ObjectId> ids_vehiculos = Arrays.asList(cliente.getIdsVehiculo());
        Document doc = new Document("nombre",cliente.getNombre())
                .append("apellido", cliente.getApellido())
                .append("cedula", cliente.getCedula())
                .append("vehiculos",ids_vehiculos);
        
        InsertOneResult result = this.collection.insertOne(doc);
        if(result.getInsertedId() != null){
            return result.getInsertedId().asObjectId().getValue();
        }
        return null;
    }
    
    public ObjectId findId(Cliente cliente){
        Document doc = new Document("cedula",cliente.getCedula());
        Document result = this.collection.find(doc).first();
        if(result != null ) {
            return result.getObjectId("_id");
        }
        return null;
    }
    
    @Override 
    public List<Cliente> findAll(){
        List<Cliente> clientes = new ArrayList<>();
        for(Document doc: this.collection.find()){
            Cliente cliente = new Cliente(
                    doc.getString("nombre"),
                    doc.getString("apellido"),
                    doc.getString("cedula")
            );
            clientes.add(cliente);
        }
        return clientes;
    }
    
    @Override 
    public Cliente findById(String cedula){
        Document doc = this.collection.find(new Document("cedula",cedula)).first();
        if(doc == null) return null;
        return new Cliente (
                doc.getString("nombre"),
                doc.getString("apellido"),
                doc.getString("cedula")
        );
    }
    
    @Override 
    public void delete(String cedula){
        this.collection.deleteOne(new Document("cedula",cedula));
    }
    
    @Override 
    public Cliente update(Cliente cliente){
        Document query = new Document("cedula",cliente.getCedula());
        Document update = new Document("$set",new Document("nombre",cliente.getNombre()))
                .append("apellido", cliente.getApellido());
        this.collection.updateOne(query, update);
        return cliente;
    }
}
