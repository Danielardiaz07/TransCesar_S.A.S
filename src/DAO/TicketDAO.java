/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.Pasajero;
import model.Ticket;
import model.Vehiculo;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Jose Rodriguez
 */
public class TicketDAO {
    private static final String ARCHIVO = "tickets.txt";

    public void guardar(Ticket ticket) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            bw.write(ticket.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar ticket: " + e.getMessage());
        }
    }

    public List<Ticket> cargar(List<Pasajero> pasajeros, List<Vehiculo> vehiculos) {
        List<Ticket> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO);

        if (!archivo.exists()) {
            return lista;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length >= 6) {
                    String cedulaPasajero = datos[0];
                    String placaVehiculo = datos[1];
                    LocalDate fecha = LocalDate.parse(datos[2]);
                    String origen = datos[3];
                    String destino = datos[4];

                    Pasajero pasajero = buscarPasajero(pasajeros, cedulaPasajero);
                    Vehiculo vehiculo = buscarVehiculo(vehiculos, placaVehiculo);

                    if (pasajero != null && vehiculo != null) {
                        Ticket ticket = new Ticket(pasajero, vehiculo, fecha, origen, destino);
                        lista.add(ticket);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar tickets: " + e.getMessage());
        }

        return lista;
    }

    private Pasajero buscarPasajero(List<Pasajero> pasajeros, String cedula) {
        for (Pasajero p : pasajeros) {
            if (p.getCedula().equals(cedula)) {
                return p;
            }
        }
        return null;
    }

    private Vehiculo buscarVehiculo(List<Vehiculo> vehiculos, String placa) {
        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return v;
            }
        }
        return null;
    }
}
