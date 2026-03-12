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
import service.PersonaService;
import service.TicketService;
import java.util.Scanner;
public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private VehiculoService vehiculoService = new VehiculoService();
    private PersonaService personaService = new PersonaService();
    private TicketService ticketService = new TicketService();
    
    
    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║   TransCesar S.A.S - Sistema     ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║  1. Registrar vehículo           ║");
            System.out.println("║  2. Registrar conductor          ║");
            System.out.println("║  3. Registrar pasajero           ║");
            System.out.println("║  4. Vender ticket                ║");
            System.out.println("║  5. Listar tickets vendidos      ║");
            System.out.println("║  6. Ver estadísticas             ║");
            System.out.println("║  7. Salir                        ║");
            System.out.println("╚══════════════════════════════════╝");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            
    switch (opcion) {
                case 1: registrarVehiculo(); break;
                case 2: registrarConductor(); break;
                case 3: registrarPasajero(); break;
                case 4: venderTicket(); break;
                case 5: listarTickets(); break;
                case 6: verEstadisticas(); break;
                case 7: System.out.println("¡Hasta luego!"); break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 7);
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
        personaService.registrarConductor(cedula, nombre, licencia, categorias[cat - 1]);
        System.out.println("Conductor registrado correctamente.");
    }
    
     private void registrarPasajero() {
        System.out.println("\n-- Registrar Pasajero --");
        System.out.print("Cedula: ");
        String cedula = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("Tipo: 1. Regular  2. Estudiante  3. Adulto Mayor");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        personaService.registrarPasajero(cedula, nombre, tipo);
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
        ticketService.venderTicket(cedula, placa, origen, destino);
    }
    
    private void listarTickets() {
        System.out.println("\n-- Tickets Vendidos --");
        ticketService.listarTickets();
    }

    private void verEstadisticas() {
        System.out.println("\n-- Estadisticas --");
        ticketService.mostrarEstadisticas();
    }
  
}
