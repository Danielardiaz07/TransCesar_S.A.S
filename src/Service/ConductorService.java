/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import dao.ConductorDAO;
import model.Conductor;

import java.util.List;
/**
 *
 * @author Jose Rodriguez
 */
public class ConductorService {
    private List<Conductor> conductores;
    private ConductorDAO conductorDAO;

    public ConductorService() {
        conductorDAO = new ConductorDAO();
        conductores = conductorDAO.cargar();
    }

    public boolean registrarConductor(Conductor conductor) {
        if (buscarConductorPorCedula(conductor.getCedula()) != null) {
            System.out.println("Ya existe un conductor con esa cédula.");
            return false;
        }

        if (!conductor.tieneLicenciaValida()) {
            System.out.println("El conductor no tiene licencia registrada.");
            return false;
        }

        conductores.add(conductor);
        conductorDAO.guardar(conductor);
        return true;
    }
}
