package parking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import parking.Models.ParkingRepository;

public class Parking {
    
    List <Espacio> espacios;
    List <Carro> carros;
    PagoEfectivo pago;
    Integer id_park;
    ParkingRepository db_parking;

    public Parking(Integer numEspacios,Integer id_park) {
        this.db_parking = new ParkingRepository();
        this.espacios = new ArrayList<>();
        this.id_park = id_park;
        
        for(int i =0;i < numEspacios;i++){
            this.espacios.add(new Espacio());
        }
    }
    
    public Integer getIdPark(){
        return this.id_park;
    }
    public Integer size_espacios(){
        return this.espacios.size();
    }
    
    private boolean verificar_vehiculo(Carro carro){
        Carro car = this.db_parking.findByIdCar(this, carro); 
        System.out.println("Carro != null:" + car != null);
        return car != null;
    }
    
    private boolean verificar_parking(){
        Parking result = this.db_parking.findByIdPark(this);
        return result != null;
    }
    
    public void ingreso_carro(Carro carro){
        for(Espacio espacio: this.espacios){
            if(!espacio.ocupado){
                
                if(!this.verificar_vehiculo(carro)){
                    if(!this.verificar_parking()){
                        this.db_parking.save(this, carro);
                    }else {
                        // Update Parking - para agregar un carro
                         this.db_parking.addVehiculoToPark(this,carro);
                    }
                    
                    espacio.ocupar();
                    System.out.println("Vehiculo Agregado Exitosamente");
                    break;
                }
                
                System.out.println("Vehiculo No se Agro, por ya esta presente");
                break;
            }
        }
    }
    
    public void salirVehiculo(String placa){
        for(Carro carro : this.carros){
            if(carro.getPlaca().equals(placa)){
                Date hora_entrada = carro.getHoraEntrada();
                this.pago.calcularPago();
            }
        }
        
        
    }
    
    
}
