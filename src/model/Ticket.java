/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
/**
 *
 * @author Jose Rodriguez
 */
public class Ticket implements Imprimible,Calculable {
    private Pasajero pasajero;
    private Vehiculo vehiculo;
    private LocalDate fechaCompra;
    private String origen;
    private String destino;
    private double valorFinal;
        
    public Ticket(Pasajero pasajero, Vehiculo vehiculo, LocalDate fechaCompra, String origen, String destino) {
           this.pasajero = pasajero;
           this.vehiculo = vehiculo;
           this.fechaCompra = fechaCompra;
           this.origen = origen;
           this.destino = destino;
           this.valorFinal = calcularTotal();
       }
    
    public Ticket(Pasajero pasajero, Vehiculo vehiculo, LocalDate fechaCompra, String origen, String destino, double tarifaEfectiva) {
        this.pasajero = pasajero;
        this.vehiculo = vehiculo;
        this.fechaCompra = fechaCompra;
        this.origen = origen;
        this.destino = destino;
        this.valorFinal = tarifaEfectiva;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    @Override
    public double calcularTotal() {
        double tarifaBase = vehiculo.getTarifaBase();
        double descuento = pasajero.calcularDescuento();
        return tarifaBase - (tarifaBase * descuento);
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("=== TICKET ===");
        System.out.println("Pasajero: " + pasajero.getNombre() + " (" + pasajero.getCedula() + ")");
        System.out.println("Tipo pasajero: " + pasajero.getTipoPasajero());
        System.out.println("Vehículo: " + vehiculo.getClass().getSimpleName());
        System.out.println("Placa: " + vehiculo.getPlaca());
        System.out.println("Ruta: " + vehiculo.getRuta());
        System.out.println("Fecha compra: " + fechaCompra);
        System.out.println("Origen: " + origen);
        System.out.println("Destino: " + destino);
        System.out.println("Valor final: $" + valorFinal);
    }

    @Override
    public String toString() {
        return pasajero.getCedula() + ";" +
               vehiculo.getPlaca() + ";" +
               fechaCompra + ";" +
               origen + ";" +
               destino + ";" +
               valorFinal;
    }
}
