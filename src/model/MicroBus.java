package model;

public class MicroBus extends Vehiculo {

    public MicroBus(String placa, String ruta) {
        super(placa, ruta);
        this.capacidadMaxima = 25;
        this.tarifaBase = 10000;
    }

    @Override
    public void imprimirDetalle() {
        System.out.printf("[MICROBUS] %s | Ruta: %s | Cupos: %d | Tarifa: $%.0f | Activo: %b%n",
            placa, ruta, getCuposDisponibles(), tarifaBase, disponible);
    }

    @Override
    public String serializar() {
        return "MICROBUS;" + placa + ";" + ruta + ";"
             + pasajerosActuales + ";" + disponible;
    }
}