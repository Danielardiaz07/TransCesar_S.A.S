package model;

public class Bus extends Vehiculo {

    public Bus(String placa, String ruta) {
        super(placa, ruta);
        this.capacidadMaxima = 45;
        this.tarifaBase = 15000;
    }

    @Override
    public void imprimirDetalle() {
        System.out.printf("[BUS] %s | Ruta: %s | Cupos: %d | Tarifa: $%.0f | Activo: %b%n",
            placa, ruta, getCuposDisponibles(), tarifaBase, disponible);
    }

    @Override
    public String serializar() {
        return "BUS;" + placa + ";" + ruta + ";"
             + pasajerosActuales + ";" + disponible;
    }
}