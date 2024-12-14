package parking;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.bson.types.ObjectId;

public abstract class Vehiculo  {
    
    public enum  TipoVehiculo {CARRO,MOTOCICLETA};
    private String placa,modelo,marca,propietarioVehiculo,descripcion;
    private Integer capacidadPasajeros;
    TipoVehiculo tipoVehiculo;
    private ObjectId id_vehiculo;
    
    Date  fechaEntrada = new Date();
    Date horaEntrada = new Date();
    SimpleDateFormat spanish = new SimpleDateFormat("dd/MM/YYYY",new  Locale("es"));
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss",new Locale("es"));

    public Vehiculo(Date horaEntrada,String placa, String propietarioVehiculo, String descripcion) {
        this.id_vehiculo = new ObjectId();
        this.horaEntrada = new Date();
        this.placa = placa;
        this.propietarioVehiculo = propietarioVehiculo;
        this.descripcion = descripcion;
    }
    
    public ObjectId getIdVehiculo(){
        return this.id_vehiculo;
    }
    
    public void setIdVehiculo(ObjectId id){
        this.id_vehiculo = id;
    }
    
    public void setTipoVehiculo(TipoVehiculo tipo){
        this.tipoVehiculo = tipo;
    }
    
    public TipoVehiculo getTipoVehiculo(){
        return this.tipoVehiculo;
    }
    
    public Date getFechaEntrada(){
        return this.fechaEntrada;
    }

    public String getHora(){
        return hora.format(horaEntrada);
    }
    
    public String getFechaEntradaString(){
        return this.spanish.format(fechaEntrada);
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public String getPropietarioVehiculo() {
        return propietarioVehiculo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setPropietarioVehiculo(String propietarioVehiculo) {
        this.propietarioVehiculo = propietarioVehiculo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCapacidadPasajeros(Integer capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }
    
    public void agregarVehiculo(String placa,String propietario){
        this.placa = placa.toUpperCase();
        this.propietarioVehiculo = propietario.toUpperCase();
    }
    
}
