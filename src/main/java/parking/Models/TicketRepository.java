package parking.Models;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import parking.ConnectDatabase;
import parking.Ticket;
import parking.Carro;
import org.bson.types.ObjectId;

public class TicketRepository implements RepositoryPark<Ticket,Carro>{
    
    private final MongoCollection<Document> collection;

    public TicketRepository() {
        MongoDatabase database = ConnectDatabase.getDatabase();
        this.collection = database.getCollection("Ticket");
    }
    
    @Override
    public Ticket save(Ticket ticket,Carro carro){
        Document doc = new Document("numero_ticket",ticket.getNumeroTicket())
                .append("fecha_entrada",ticket.getFechaHoraEntrada())
                .append("vehiculo", carro.getIdVehiculo());
        
        this.collection.insertOne(doc);
        return ticket;
    }
    
    @Override 
    public Carro findByIdCar(Ticket ticket,Carro carro){
//        Document doc = new Document("")
        return carro;
    }
    
    @Override
    public Ticket findByIdPark(Ticket ticket){
        Document query = new Document("numero_ticket",ticket.getNumeroTicket());
        Document result = this.collection.find(query).first();
        if(result != null ) return ticket;
        return null;
    }
    
    @Override 
    public Ticket addVehiculoToPark(Ticket ticket,Carro carro){
        return ticket;
    }
    
}
