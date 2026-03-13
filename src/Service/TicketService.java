/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.TicketDAO;
import model.Pasajero;
import model.Ticket;
import model.Vehiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Jose Rodriguez
 */
public class TicketService {
    private List<Ticket> tickets;
    private TicketDAO ticketDAO;

    public TicketService(List<Pasajero> pasajeros, List<Vehiculo> vehiculos) {
        ticketDAO = new TicketDAO();
        tickets = ticketDAO.cargar(pasajeros, vehiculos);
    }

    public Ticket venderTicket(Pasajero pasajero, Vehiculo vehiculo, String origen, String destino) {
        if (pasajero == null) {
            System.out.println("El pasajero no existe.");
            return null;
        }

        if (vehiculo == null) {
            System.out.println("El vehículo no existe.");
            return null;
        }

        if (!vehiculo.tieneCuposDisponibles()) {
            System.out.println("El vehículo está lleno. No se puede vender el ticket.");
            return null;
        }

        Ticket ticket = new Ticket(pasajero, vehiculo, LocalDate.now(), origen, destino);

        // Aumentar pasajeros actuales del vehículo
        vehiculo.setPasajerosActuales(vehiculo.getPasajerosActuales() + 1);

        tickets.add(ticket);
        ticketDAO.guardar(ticket);

        System.out.println("Ticket vendido con éxito.");
        return ticket;
    }
}
