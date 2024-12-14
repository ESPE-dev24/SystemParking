package parking;

import org.bson.types.ObjectId;
import parking.Models.PersonaRepository;

public class Cliente extends Persona {
    private final PersonaRepository cliente_db;
    private char licencia;
    private  ObjectId id_carro;

    public Cliente( String nombre, String apellido, String cedula) {
        super(nombre, apellido, cedula);
        this.cliente_db = new PersonaRepository();
    }
    
    public ObjectId agregar(ObjectId id_carro){
        this.id_carro = id_carro;
        ObjectId id = this.cliente_db.findId(this);
        if(id != null) return id;
        ObjectId id_ = this.cliente_db.save(this);
        return id_;
    }
    
    public ObjectId getIdsVehiculo(){
        return this.id_carro;
    }

    public char getLicencia() {
        return licencia;
    }

    public void setLicencia(char licencia) {
        this.licencia = licencia;
    }   
}
