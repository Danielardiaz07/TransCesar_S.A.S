/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package transcesar_s.a.s;

/**
 *
 * @author DANIELA ROJAS
 */
import view.Menu;
import service.VehiculoService;
import service.PersonaService;
import service.TicketService;

public class main {
    public static void main(String[] args) {
       
        VehiculoService vehiculoService = new VehiculoService();
        PersonaService personaService = new PersonaService();
        TicketService ticketService = new TicketService();

        vehiculoService.cargarDatos();
        personaService.cargarDatos();
        ticketService.cargarDatos();

        Menu menu = new Menu();
        menu.mostrar();
        
    }
    
}
