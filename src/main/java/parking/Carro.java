package parking;

import java.util.Date;
import org.bson.types.ObjectId;
import parking.Models.VehiculoRepository;

public class Carro extends Vehiculo {
    private Integer numeroPuertas;
    private final VehiculoRepository vehiculo_db;
    ObjectId id_carro;
    
    public Carro(Date horaEntrada, String placa,String marca,String modelo, String propietarioVehiculo, String descripcion) {
        super(horaEntrada, placa, propietarioVehiculo, descripcion);
        this.vehiculo_db = new VehiculoRepository();
        this.setMarca(marca);
        this.setModelo(modelo);
    }
    
    public ObjectId agregar_carro(){
        ObjectId id = this.vehiculo_db.findRetId(this.getPlaca());
        if(id != null ) return id;
        return this.vehiculo_db.save(this);
    }
    
    public Date getHoraEntrada(){
        return this.horaEntrada;
    }
    
    public Integer getNumeroPuertas() {
        return numeroPuertas;
    }

    public void setNumeroPuertas(Integer numeroPuertas) {
        this.numeroPuertas = numeroPuertas;
    }
    
}
