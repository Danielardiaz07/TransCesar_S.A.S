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
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void listarTickets() {
        if (tickets.isEmpty()) {
            System.out.println("No hay tickets vendidos.");
            return;
        }

        for (Ticket ticket : tickets) {
            ticket.imprimirDetalle();
            System.out.println("----------------------------");
        }
    }

    public double calcularTotalRecaudado() {
        double total = 0;

        for (Ticket ticket : tickets) {
            total += ticket.calcularTotal();
        }

        return total;
    }

    public void mostrarTotalRecaudado() {
        System.out.println("Total recaudado: $" + calcularTotalRecaudado());
    }

    public void mostrarCantidadPasajerosPorTipo() {
        int regulares = 0;
        int estudiantes = 0;
        int adultosMayores = 0;

        for (Ticket ticket : tickets) {
            String tipo = ticket.getPasajero().getTipoPasajero();

            switch (tipo.toLowerCase()) {
                case "regular":
                    regulares++;
                    break;
                case "estudiante":
                    estudiantes++;
                    break;
                case "adultomayor":
                    adultosMayores++;
                    break;
            }
        }

        System.out.println("=== CANTIDAD DE PASAJEROS POR TIPO ===");
        System.out.println("Regulares: " + regulares);
        System.out.println("Estudiantes: " + estudiantes);
        System.out.println("Adultos mayores: " + adultosMayores);
    }

    public Vehiculo obtenerVehiculoConMasTicketsVendidos() {
        if (tickets.isEmpty()) {
            return null;
        }

        Map<String, Integer> contadorPorPlaca = new HashMap<>();
        Map<String, Vehiculo> vehiculosMap = new HashMap<>();

        for (Ticket ticket : tickets) {
            String placa = ticket.getVehiculo().getPlaca();

            contadorPorPlaca.put(placa, contadorPorPlaca.getOrDefault(placa, 0) + 1);
            vehiculosMap.put(placa, ticket.getVehiculo());
        }

        String placaGanadora = null;
        int maxTickets = 0;

        for (String placa : contadorPorPlaca.keySet()) {
            int cantidad = contadorPorPlaca.get(placa);

            if (cantidad > maxTickets) {
                maxTickets = cantidad;
                placaGanadora = placa;
            }
        }

        return vehiculosMap.get(placaGanadora);
    }

    public void mostrarVehiculoConMasTicketsVendidos() {
        Vehiculo vehiculo = obtenerVehiculoConMasTicketsVendidos();

        if (vehiculo == null) {
            System.out.println("No hay tickets vendidos aún.");
            return;
        }

        System.out.println("=== VEHÍCULO CON MÁS TICKETS VENDIDOS ===");
        vehiculo.imprimirDetalle();
    }
}
