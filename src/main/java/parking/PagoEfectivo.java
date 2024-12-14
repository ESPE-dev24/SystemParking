package parking;

public class PagoEfectivo extends Pago{
    private final String id;
    

    public PagoEfectivo(String id, Double subtotal, Double total, Double iva) {
        super(subtotal, total, iva);
        this.id = id;
    }

    public String getId() {
        return id;
    }
    
    
    
}