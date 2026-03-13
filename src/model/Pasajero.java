/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Jose Rodriguez
 */
public abstract class Pasajero extends Persona {
    protected String tipoPasajero;

    public Pasajero(String cedula, String nombre, String tipoPasajero) {
        super(cedula, nombre);
        this.tipoPasajero = tipoPasajero;
    }

    public String getTipoPasajero() {
        return tipoPasajero;
    }

    public void setTipoPasajero(String tipoPasajero) {
        this.tipoPasajero = tipoPasajero;
    }

    public abstract double calcularDescuento();
}
