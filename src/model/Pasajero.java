/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author Jose Rodriguez
 */
public abstract class Pasajero extends Persona {
    protected String tipoPasajero;
    protected LocalDate fechaNacimiento;

    public Pasajero(String cedula, String nombre, String tipoPasajero, LocalDate fechaNacimiento) {
        super(cedula, nombre);
        this.tipoPasajero = tipoPasajero;
        this.fechaNacimiento= fechaNacimiento;
    }
    
    public int calcularEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    public boolean esAdultoMayor() {
        return calcularEdad() >= 60;
    }

    public String getTipoPasajero() {
        return tipoPasajero;
    }

    public void setTipoPasajero(String tipoPasajero) {
        this.tipoPasajero = tipoPasajero;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
   
    public abstract double calcularDescuento();
}
