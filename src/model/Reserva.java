/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Jose Rodriguez
 */
public class Reserva {
     private String codigo;
    private Pasajero pasajero;
    private Vehiculo vehiculo;
    private LocalDateTime fechaCreacion;
    private LocalDate fechaViaje;
    private EstadoReserva estado;

    public Reserva(String codigo, Pasajero pasajero, Vehiculo vehiculo, LocalDateTime fechaCreacion, LocalDate fechaViaje, EstadoReserva estado) {
        this.codigo = codigo;
        this.pasajero = pasajero;
        this.vehiculo = vehiculo;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaViaje = fechaViaje;
        this.estado = EstadoReserva.ACTIVA;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaViaje() {
        return fechaViaje;
    }

    public void setFechaViaje(LocalDate fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }
    
    @Override
public String toString() {
    return "=== RESERVA ===" +
           "\nCódigo       : " + codigo +
           "\nPasajero     : " + pasajero.getNombre() +
           "\nVehículo     : " + vehiculo.getPlaca() +
           "\nFecha Creación: " + fechaCreacion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) +
           "\nFecha Viaje  : " + fechaViaje.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
           "\nEstado       : " + estado;
}
    
}
