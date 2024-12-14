package parking;
import java.time.LocalDateTime;
import java.util.Date;
import org.bson.types.ObjectId;
import java.time.LocalDateTime;

public class SystemParkingVehicule {

    public static void main(String[] args) {
        System.out.println("Inicio del Sistema Registro de Parqueadero");
        
        Date date_now = new Date();
        Parking park = new Parking(20,123);
        Cliente client = new Cliente("Andres Xavier","Jama Zambrano","1234567890");
        Carro car = new Carro(date_now, "ABC123","Toyota","Fortune",client.getCedula(), "Vehiculo color blanco");
        
        
        ObjectId id_carro = car.agregar_carro();
        car.setIdVehiculo(id_carro);
        client.agregar(id_carro);
        
        park.ingreso_carro(car); 
        LocalDateTime localTime = LocalDateTime.now();
        Ticket ticket = new Ticket(0,localTime, car);
        
        ticket.ImprimirTicket();
        
        ticket.
        
        
        
        
    }
}
