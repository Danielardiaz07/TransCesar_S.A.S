/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.PasajeroDAO;
import model.*;
import java.time.LocalDate;
import java.util.List;
/**
 *
 * @author Jose Rodriguez
 */
public class PasajeroService {
    private List<Pasajero> pasajeros;
    private PasajeroDAO pasajeroDAO;

    public PasajeroService() {
        pasajeroDAO = new PasajeroDAO();
        pasajeros = pasajeroDAO.cargar();
    }

     public boolean registrarPasajero(String cedula, String nombre,
                                     LocalDate fechaNacimiento, String tipoSolicitado) {
        if (buscarPasajeroPorCedula(cedula) != null) {
            System.out.println("Ya existe un pasajero con esa cédula.");
            return false;
        }

        // Calcular edad para decidir el tipo real
        int edad = java.time.Period.between(fechaNacimiento, LocalDate.now()).getYears();
        Pasajero nuevo;

        if (edad >= 60) {
            nuevo = new PasajeroAdultoMayor(cedula, nombre, fechaNacimiento);
            System.out.println("ℹ El pasajero tiene " + edad
                + " años → categoría Adulto Mayor asignada automáticamente (descuento 30%).");
        } else {
            nuevo = switch (tipoSolicitado.toLowerCase()) {
                case "estudiante"  -> new PasajeroEstudiante(cedula, nombre, fechaNacimiento);
                default            -> new PasajeroRegular(cedula, nombre, fechaNacimiento);
            };
        }

        pasajeros.add(nuevo);
        pasajeroDAO.guardar(nuevo);
        return true;
    }

    public Pasajero buscarPasajeroPorCedula(String cedula) {
        return pasajeros.stream()
            .filter(p -> p.getCedula().equals(cedula))
            .findFirst().orElse(null);
    }

    public List<Pasajero> getPasajeros() { return pasajeros; }

    public void listarPasajeros() {
        if (pasajeros.isEmpty()) { System.out.println("No hay pasajeros registrados."); return; }
        pasajeros.forEach(p -> { p.imprimirDetalle(); System.out.println("---"); });
    }
}
