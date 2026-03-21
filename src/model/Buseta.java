package model;

public class Buseta extends Vehiculo {
    
    public Buseta(String placa, Ruta ruta) {
        super(placa, ruta);
        this.capacidadMaxima = 19;
        this.tarifaBase = 8000;
    }

    @Override
    public void imprimirDetalle() {
        System.out.printf("[BUSETA] %s | Ruta: %s | Cupos: %d | Tarifa: $%.0f | Activo: %b%n",
            placa, ruta, getCuposDisponibles(), tarifaBase, disponible);
    }

    @Override
    public String serializar() {
        return "BUSETA;" + placa + ";" + ruta + ";"
             + pasajerosActuales + ";" + disponible;
    }    
}