/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.PasajeroDAO;
import model.Pasajero;

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

        pasajeros.add(pasajero);
        pasajeroDAO.guardar(pasajero);
        return true;
    }

    public Pasajero buscarPasajeroPorCedula(String cedula) {
        for (Pasajero p : pasajeros) {
            if (p.getCedula().equals(cedula)) {
                return p;
            }
        }
        return null;
    }

    public List<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public void listarPasajeros() {
        if (pasajeros.isEmpty()) {
            System.out.println("No hay pasajeros registrados.");
            return;
        }

        for (Pasajero p : pasajeros) {
            p.imprimirDetalle();
            System.out.println("-------------------------");
        }
    }
}
