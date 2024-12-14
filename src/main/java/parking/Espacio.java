package parking;


public class Espacio {
    int numero;
    TipoEspacio tipo;
    boolean ocupado;
    
    public void ocupar(){ this.ocupado = true;}
    public void desocupar(){ this.ocupado  = false;}

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public TipoEspacio getTipaao() {
        return tipo;
    }

    public void setTipo(TipoEspacio tipoEspacio) {
        this.tipo = tipoEspacio;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
    
    
    
}
