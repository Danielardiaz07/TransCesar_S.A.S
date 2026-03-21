package model;

public abstract class Vehiculo implements Imprimible {
    protected String placa;
    protected Ruta ruta;
    protected int capacidadMaxima;
    protected int pasajerosActuales;
    protected boolean disponible;
    protected double tarifaBase;

    public Vehiculo(String placa, Ruta ruta) {
        this.placa = placa;
        this.ruta = ruta;
        this.pasajerosActuales = 0;
        this.disponible = true;
    }
    
    public String getPlaca() { 
        return placa; 
    }
    public Ruta getRuta() { 
        return ruta; 
    }
    public int getCapacidad() { 
        return capacidadMaxima; 
    }
    public int getPasajerosActuales() { 
        return pasajerosActuales; 
    }
    public boolean isDisponible() { 
        return disponible; 
    }
    public double getTarifaBase() { 
        return tarifaBase; 
    }
    
    public void setPasajerosActuales(int n) { 
        this.pasajerosActuales = n; 
    }
    public void setDisponible(boolean d) { 
        this.disponible = d; 
    }

    public boolean tieneCupos() {
        return pasajerosActuales < capacidadMaxima;
    }

    public int getCuposDisponibles() {
        return capacidadMaxima - pasajerosActuales;
    }

    public void agregarPasajero() {
        if (tieneCupos()) 
            pasajerosActuales++;
    }
    
    public boolean tieneCuposDisponibles() {
    return tieneCupos();
    }

    public abstract String serializar();
}