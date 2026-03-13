/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Jose Rodriguez
 */
public class PasajeroRegular extends Pasajero {
    public PasajeroRegular(String cedula, String nombre) {
        super(cedula, nombre, "Regular");
    }

    @Override
    public double calcularDescuento() {
        return 0.0;
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("=== PASAJERO REGULAR ===");
        System.out.println("Cédula: " + cedula);
        System.out.println("Nombre: " + nombre);
        System.out.println("Tipo: " + tipoPasajero);
        System.out.println("Descuento: " + (calcularDescuento() * 100) + "%");
    }

    @Override
    public String toString() {
        return cedula + ";" + nombre + ";" + tipoPasajero;
    }
}
