package parking.Models;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;
import parking.ConnectDatabase;
import parking.PagoEfectivo;


public class PagoEfectivoRepository implements Repository<PagoEfectivo> {
    
    private final MongoCollection<Document> collection;

    public PagoEfectivoRepository() {
        MongoDatabase database = ConnectDatabase.getDatabase();
        this.collection = database.getCollection("Pago");
    }
    
    @Override 
    public ObjectId save(PagoEfectivo pago ){
        Document doc = new Document("subtotal",pago.getSubtotal())
                .append("total", pago.getTotal())
                .append("iva", pago.getIva());
        InsertOneResult result = this.collection.insertOne(doc);
        if(result.getInsertedId() != null){
            return result.getInsertedId().asObjectId().getValue();
        }
        return null;
    }
    
    @Override 
    public List<PagoEfectivo> findAll(){
        List<PagoEfectivo> pagos = new ArrayList<>();
        for(Document doc: this.collection.find()){
            PagoEfectivo pago = new PagoEfectivo(
                    doc.getString("_id"),
                    doc.getDouble("subtotal"),
                    doc.getDouble("total"),
                    doc.getDouble("iva")
            );
            pagos.add(pago);
        }
        return pagos;
    }
    
    @Override 
    public PagoEfectivo findById(String id_pago){
        Document doc = this.collection.find(new Document("_id", new ObjectId(id_pago))).first();
        if(doc == null) return null;
        return new PagoEfectivo(
                doc.getString("_id"),
                doc.getDouble("subtotal"),
                doc.getDouble("total"),
                doc.getDouble("total")
        );
    }
    
    @Override 
    public void delete(String id_pago){
        this.collection.deleteOne(new Document("_id",new ObjectId(id_pago)));
    }
    
    @Override 
    public PagoEfectivo update(PagoEfectivo pago){
        
        Document query = new Document("_id", new ObjectId(pago.getId()));
        Document update = new Document("$set", new Document("subtotal",pago.getSubtotal()))
                .append("total", pago.getTotal())
                .append("iva",pago.getIva());
        this.collection.updateMany(query, update);
        return pago;
    }
}
