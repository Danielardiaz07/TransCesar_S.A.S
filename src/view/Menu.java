/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author DANIELA ROJAS
 */

import service.VehiculoService;
import Service.ConductorService;
import Service.PasajeroService;
import Service.TicketService;
import Service.ReservaService;
import model.Ticket;
import java.util.Scanner;
public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private service.VehiculoService vehiculoService = new service.VehiculoService();
    private Service.ConductorService conductorService = new Service.ConductorService();
    private Service.PasajeroService pasajeroService = new Service.PasajeroService();
    private Service.TicketService ticketService = new Service.TicketService(
    pasajeroService.getPasajeros(),
    vehiculoService.listarVehiculos()
);
    
    private Service.ReservaService reservaService = new Service.ReservaService(
    pasajeroService.getPasajeros(),
    vehiculoService.listarVehiculos(),
    ticketService
);
    
    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n=== TransCesar S.A.S - Sistema ===");
            System.out.println("1. Registrar vehiculo");
            System.out.println("2. Registrar conductor");
            System.out.println("3. Registrar pasajero");
            System.out.println("4. Vender ticket");
            System.out.println("5. Listar tickets vendidos");
            System.out.println("6. Ver estadisticas");
            System.out.println("7. Modulo de reportes");
            System.out.println("8. Modulo de reservas");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            
    switch (opcion) {
                case 1: registrarVehiculo(); break;
                case 2: registrarConductor(); break;
                case 3: registrarPasajero(); break;
                case 4: venderTicket(); break;
                case 5: listarTickets(); break;
                case 6: verEstadisticas(); break;
                case 7: mostrarMenuReportes(); break;
                case 8: mostrarMenuReservas(); break;
case 9:         System.out.println("Hasta luego."); break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 9);
    }
    
    private void registrarVehiculo() {
        System.out.println("\n-- Registrar Vehículo --");
        System.out.println("Tipo: 1. Buseta  2. MicroBus  3. Bus");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Placa: ");
        String placa = scanner.nextLine();
        System.out.print("Ruta: ");
        String ruta = scanner.nextLine();
        vehiculoService.registrarVehiculo(tipo, placa, ruta);
        System.out.println(" Vehículo registrado correctamente.");
    }
    
    private void registrarConductor() {
        System.out.println("\n-- Registrar Conductor --");
        System.out.print("Cedula: ");
        String cedula = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Numero de licencia: ");
        String licencia = scanner.nextLine();
        System.out.println("Categoria: 1.B1  2.B2  3.C1  4.C2");
        int cat = scanner.nextInt();
        scanner.nextLine();
        String[] categorias = {"B1", "B2", "C1", "C2"};
        conductorService.registrarConductor(new model.Conductor(licencia, categorias[cat - 1], cedula, nombre));
        System.out.println("Conductor registrado correctamente.");
    }
    
    private void registrarPasajero() {
    System.out.println("\n-- Registrar Pasajero --");
    System.out.print("Cedula: ");
    String cedula = scanner.nextLine();
    System.out.print("Nombre: ");
    String nombre = scanner.nextLine();
    System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
    java.time.LocalDate fechaNacimiento = java.time.LocalDate.parse(scanner.nextLine());
    System.out.println("Tipo: 1. Regular  2. Estudiante");
    int tipo = scanner.nextInt();
    scanner.nextLine();
    switch(tipo) {
        case 1: pasajeroService.registrarPasajero(new model.PasajeroRegular(cedula, nombre, fechaNacimiento)); break;
        case 2: pasajeroService.registrarPasajero(new model.PasajeroEstudiante(cedula, nombre, fechaNacimiento)); break;
        default: System.out.println("Tipo no valido.");
    }
    System.out.println("Pasajero registrado correctamente.");
}
     
    private void venderTicket() {
        System.out.println("\n-- Vender Ticket --");
        System.out.print("Cedula del pasajero: ");
        String cedula = scanner.nextLine();
        System.out.print("Placa del vehiculo: ");
        String placa = scanner.nextLine();
        System.out.print("Origen: ");
        String origen = scanner.nextLine();
        System.out.print("Destino: ");
        String destino = scanner.nextLine();
        ticketService.venderTicket(
        pasajeroService.buscarPasajeroPorCedula(cedula),
        vehiculoService.buscarPorPlaca(placa),
        origen, destino
);
    }
    
    private void listarTickets() {
        System.out.println("\n-- Tickets Vendidos --");
       ticketService.listarTickets();
       ticketService.mostrarTotalRecaudado();
    }

    private void verEstadisticas() {
        System.out.println("\n-- Estadisticas --");
        ticketService.mostrarCantidadPasajerosPorTipo();
        ticketService.mostrarVehiculoConMasTicketsVendidos();
        ticketService.mostrarTotalRecaudado();
    }
    
    private void mostrarMenuReportes() {
    int opcion;
    do {
        System.out.println("\n=== MODULO DE REPORTES ===");
        System.out.println("1. Tickets por fecha");
        System.out.println("2. Tickets por tipo de vehiculo");
        System.out.println("3. Tickets por tipo de pasajero");
        System.out.println("4. Resumen del dia actual");
        System.out.println("5. Volver");
        System.out.print("Seleccione: ");
        opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Ingrese la fecha (YYYY-MM-DD): ");
                String fecha = scanner.nextLine();
                java.time.LocalDate fechaBuscada = java.time.LocalDate.parse(fecha);
                for (Ticket t : ticketService.getTickets()) {
                    if (t.getFechaCompra().equals(fechaBuscada)) {
                        t.imprimirDetalle();
                        System.out.println("---");
                    }
                }
                break;
            case 2:
                System.out.print("Tipo (Buseta/MicroBus/Bus): ");
                String tipoV = scanner.nextLine();
                for (Ticket t : ticketService.getTickets()) {
                    if (t.getVehiculo().getClass().getSimpleName().equalsIgnoreCase(tipoV)) {
                        t.imprimirDetalle();
                        System.out.println("---");
                    }
                }
                break;
            case 3:
                System.out.print("Tipo (Regular/Estudiante/AdultoMayor): ");
                String tipoP = scanner.nextLine();
                for (Ticket t : ticketService.getTickets()) {
                    if (t.getPasajero().getTipoPasajero().equalsIgnoreCase(tipoP)) {
                        t.imprimirDetalle();
                        System.out.println("---");
                    }
                }
                break;
            case 4:
                java.time.LocalDate hoy = java.time.LocalDate.now();
                long total = ticketService.getTickets().stream()
                    .filter(t -> t.getFechaCompra().equals(hoy))
                    .count();
                double recaudado = ticketService.getTickets().stream()
                    .filter(t -> t.getFechaCompra().equals(hoy))
                    .mapToDouble(t -> t.getValorFinal())
                    .sum();
                System.out.println("Tickets vendidos hoy: " + total);
                System.out.println("Total recaudado hoy: $" + recaudado);
                break;
            case 5:
                System.out.println("Volviendo al menu principal.");
                break;
            default:
                System.out.println("Opcion no valida.");
        }
    } while (opcion != 5);
}
    
    private void mostrarMenuReservas() {
    int opcion;
    do {
        System.out.println("\n=== MODULO DE RESERVAS ===");
        System.out.println("1. Crear reserva");
        System.out.println("2. Cancelar reserva");
        System.out.println("3. Listar reservas activas");
        System.out.println("4. Historial de reservas de un pasajero");
        System.out.println("5. Convertir reserva en ticket");
        System.out.println("6. Verificar reservas vencidas");
        System.out.println("7. Volver");
        System.out.print("Seleccione: ");
        opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Cedula del pasajero: ");
                String cedula = scanner.nextLine();
                System.out.print("Placa del vehiculo: ");
                String placa = scanner.nextLine();
                System.out.print("Fecha del viaje (YYYY-MM-DD): ");
                java.time.LocalDate fechaViaje = java.time.LocalDate.parse(scanner.nextLine());
                reservaService.crearReserva(
                    pasajeroService.buscarPasajeroPorCedula(cedula),
                    vehiculoService.buscarPorPlaca(placa),
                    fechaViaje
                );
                break;
            case 2:
                System.out.print("Codigo de la reserva: ");
                String codigo = scanner.nextLine();
                reservaService.cancelarReserva(codigo);
                break;
            case 3:
                reservaService.listarReservasActivas();
                break;
            case 4:
                System.out.print("Cedula del pasajero: ");
                String cedulaH = scanner.nextLine();
                reservaService.listarHistorialPasajero(cedulaH);
                break;
            case 5:
                System.out.print("Codigo de la reserva: ");
                String codigoT = scanner.nextLine();
                System.out.print("Origen: ");
                String origen = scanner.nextLine();
                System.out.print("Destino: ");
                String destino = scanner.nextLine();
                reservaService.convertirEnTicket(codigoT, origen, destino);
                break;
            case 6:
                int canceladas = reservaService.verificarReservasVencidas();
                System.out.println("Reservas vencidas canceladas: " + canceladas);
                break;
            case 7:
                System.out.println("Volviendo al menu principal.");
                break;
            default:
                System.out.println("Opcion no valida.");
        }
    } while (opcion != 7);
}
    
    
    
  
}
