package parking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import parking.Models.TicketRepository;
import parking.Carro;
import org.bson.types.ObjectId;

public class Ticket {
    
    private TicketRepository ticket_db;
    private int numeroTicket;
    private LocalDateTime fechaHoraEntrada,fechaHoraSalida;
    private Vehiculo vehiculo;
    private double costoTotal;

    public Ticket(int numeroTicket, LocalDateTime fechaHoraEntrada, Vehiculo vehiculo) {
        this.numeroTicket = numeroTicket;
        this.fechaHoraEntrada = fechaHoraEntrada;
        this.vehiculo = vehiculo;
        this.ticket_db = new TicketRepository();
    }
    
    public ObjectId agregar(Carro carro){
        Ticket ticket = this.ticket_db.findByIdPark(this);
        //        if(id != null ){
        //            this.ticket_db.save(this, carro);
        //        }
        //        return ticket;
    }
       
    public int getNumeroTicket() {
        return numeroTicket;
    }

    public void setNumeroTicket(int numeroTicket) {
        this.numeroTicket = numeroTicket;
    }

    public LocalDateTime getFechaHoraEntrada() {
        return fechaHoraEntrada;
    }

    public void setFechaHoraEntrada(LocalDateTime fechaHoraEntrada) {
        this.fechaHoraEntrada = fechaHoraEntrada;
    }

    public LocalDateTime getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }
    
    private String format_fecha(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formatString = this.fechaHoraEntrada.format(format);
        return formatString;
    }
    public void ImprimirTicket(){
        System.out.println("******** TICKET ********");
        System.out.println("******** ID "+this.numeroTicket+" ********");
        System.out.println("Hora Ingreso: "+ this.format_fecha());
        System.out.println("Hora Salida: ");
        System.out.println("******** VEHICULO ********");
        System.out.println("PLACA: "+ this.vehiculo.getPlaca());
        System.out.println("MARCA: "+ this.vehiculo.getMarca());
        System.out.println("MODELO: "+ this.vehiculo.getModelo());
        System.out.println("************************");
    }
    
    public void calcularCosto(){
        long tiempoEstacionado = ChronoUnit.MINUTES.between(fechaHoraEntrada,fechaHoraSalida);
        costoTotal = tiempoEstacionado * 0.50;
    }
    
}