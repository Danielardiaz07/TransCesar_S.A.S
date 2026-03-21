package Service;

import DAO.ReservaDAO;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReservaService {
    private final List<Reserva> reservas;
    private final ReservaDAO reservaDAO;
    private final TicketService ticketService;

    public ReservaService(List<Pasajero> pasajeros, List<Vehiculo> vehiculos, TicketService ticketService) {
        this.reservaDAO = new ReservaDAO();
        this.reservas = reservaDAO.cargar(pasajeros, vehiculos);
        this.ticketService = ticketService;
    }

    public Reserva crearReserva(Pasajero pasajero, Vehiculo vehiculo, LocalDate fechaViaje) {
        if (pasajero == null) {
            System.out.println("El pasajero no existe.");
            return null;
        }
        if (vehiculo == null) {
            System.out.println("El vehículo no existe.");
            return null;
        }

        for (Reserva r : reservas) {
            if (r.getEstado() == EstadoReserva.ACTIVA &&
                r.getPasajero().getCedula().equals(pasajero.getCedula()) &&
                r.getVehiculo().getPlaca().equalsIgnoreCase(vehiculo.getPlaca()) &&
                r.getFechaViaje().equals(fechaViaje)) {
                System.out.println("El pasajero ya tiene una reserva activa en ese vehículo para esa fecha.");
                return null;
            }
        }

        long reservasActivas = contarReservasActivasPorVehiculo(vehiculo.getPlaca());
        int ocupacion = vehiculo.getPasajerosActuales() + (int) reservasActivas;
        if (ocupacion >= vehiculo.getCapacidad()) {
            System.out.println("El vehículo no tiene cupos disponibles (tickets + reservas activas llenos).");
            return null;
        }

        String codigo = generarCodigo();
        Reserva nueva = new Reserva(codigo, pasajero, vehiculo, null, fechaViaje, EstadoReserva.ACTIVA);

        reservas.add(nueva);
        reservaDAO.guardar(nueva);

        System.out.println("Reserva creada exitosamente. Código: " + codigo);
        return nueva;
    }

    public boolean cancelarReserva(String codigo) {
        Reserva reserva = buscarPorCodigo(codigo);

        if (reserva == null) {
            System.out.println("No se encontró una reserva con ese código.");
            return false;
        }
        if (reserva.getEstado() != EstadoReserva.ACTIVA) {
            System.out.println("La reserva no está activa (estado: " + reserva.getEstado() + ").");
            return false;
        }

        reserva.setEstado(EstadoReserva.CANCELADA);
        reservaDAO.actualizarTodo(reservas);
        System.out.println("Reserva " + codigo + " cancelada correctamente.");
        return true;
    }

    public Ticket convertirEnTicket(String codigo, String origen, String destino) {
        Reserva reserva = buscarPorCodigo(codigo);

        if (reserva == null) {
            System.out.println("No se encontró una reserva con ese código.");
            return null;
        }
        if (reserva.getEstado() != EstadoReserva.ACTIVA) {
            System.out.println("La reserva no está activa (estado: " + reserva.getEstado() + ").");
            return null;
        }
        if (reserva.estaVencida()) {
            reserva.setEstado(EstadoReserva.CANCELADA);
            reservaDAO.actualizarTodo(reservas);
            System.out.println("La reserva ha vencido (más de 24 horas). Fue cancelada automáticamente.");
            return null;
        }

        Ticket ticket = ticketService.venderTicket( reserva.getPasajero(), reserva.getVehiculo(), origen, destino );

        if (ticket != null) {
            reserva.setEstado(EstadoReserva.CONVERTIDA);
            reservaDAO.actualizarTodo(reservas);
            System.out.println("Reserva convertida en ticket exitosamente.");
        }

        return ticket;
    }

    public void listarReservasActivas() {
        List<Reserva> activas = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getEstado() == EstadoReserva.ACTIVA) {
                activas.add(r);
            }
        }

        if (activas.isEmpty()) {
            System.out.println("No hay reservas activas.");
            return;
        }

        System.out.println("=== RESERVAS ACTIVAS ===");
        for (Reserva r : activas) {
            System.out.println(r.toString());
            System.out.println("----------------------------");
        }
    }

    public void listarHistorialPasajero(String cedula) {
        List<Reserva> historial = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getPasajero().getCedula().equals(cedula)) {
                historial.add(r);
            }
        }

        if (historial.isEmpty()) {
            System.out.println("No hay reservas registradas para ese pasajero.");
            return;
        }

        System.out.println("=== HISTORIAL DE RESERVAS ===");
        for (Reserva r : historial) {
            System.out.println(r.toString());
            System.out.println("----------------------------");
        }
    }

    public int verificarReservasVencidas() {
        int canceladas = 0;

        for (Reserva r : reservas) {
            if (r.estaVencida()) {
                r.setEstado(EstadoReserva.CANCELADA);
                canceladas++;
            }
        }

        if (canceladas > 0) {
            reservaDAO.actualizarTodo(reservas);
        }

        return canceladas;
    }

    public Reserva buscarPorCodigo(String codigo) {
        for (Reserva r : reservas) {
            if (r.getCodigo().equalsIgnoreCase(codigo)) return r;
        }
        return null;
    }

    private long contarReservasActivasPorVehiculo(String placa) {
        return reservas.stream()
            .filter(r -> r.getEstado() == EstadoReserva.ACTIVA)
            .filter(r -> r.getVehiculo().getPlaca().equalsIgnoreCase(placa))
            .count();
    }

    private String generarCodigo() {
        return "RES-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public List<Reserva> getReservas() {
        return reservas;
    }
}