package parking;
import java.util.Date;

public class Pago {
    private Double subtotal,total,iva;
    private Date hora;
    private float horaSalida;
    private final float valorPorHora = (float)0.50;

    public Pago(Double subtotal, Double total, Double iva) {
        this.subtotal = subtotal;
        this.total = total;
        this.iva = iva;
    }
    
    public float calcularPago(){
        String horaString = hora.toString();
        float horas = Float.parseFloat(horaString);
        float pago  = ((horaSalida) - horas ) * valorPorHora;
        System.out.println(pago);
        return pago;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }
    
    
}