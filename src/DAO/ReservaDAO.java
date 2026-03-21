package DAO;

import model.EstadoReserva;
import model.Pasajero;
import model.Reserva;
import model.Vehiculo;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    private static final String archivo = "reservas.txt";

    public void guardar(Reserva reserva) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(reserva.serializar());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar reserva: " + e.getMessage());
        }
    }

    public void actualizarTodo(List<Reserva> reservas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, false))) {
            for (Reserva r : reservas) {
                bw.write(r.serializar());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al actualizar reservas: " + e.getMessage());
        }
    }

    public List<Reserva> cargar(List<Pasajero> pasajeros, List<Vehiculo> vehiculos) {
        List<Reserva> lista = new ArrayList<>();
        File archivoReservas = new File(archivo);

        if (!archivoReservas.exists()) {
            return lista;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;
                String[] datos = linea.split(";");
                if (datos.length == 6) {
                    String codigo = datos[0];
                    String cedula = datos[1];
                    String placa = datos[2];
                    LocalDateTime fechaCreacion = LocalDateTime.parse(datos[3]);
                    LocalDate fechaViaje = LocalDate.parse(datos[4]);
                    EstadoReserva estado = EstadoReserva.valueOf(datos[5]);

                    Pasajero pasajero = buscarPasajero(pasajeros, cedula);
                    Vehiculo vehiculo = buscarVehiculo(vehiculos, placa);

                    if (pasajero != null && vehiculo != null) {
                        Reserva r = new Reserva(codigo, pasajero, vehiculo,
                                fechaCreacion, fechaViaje, estado, true);
                        lista.add(r);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar reservas: " + e.getMessage());
        }

        return lista;
    }

    private Pasajero buscarPasajero(List<Pasajero> pasajeros, String cedula) {
        for (Pasajero p : pasajeros) {
            if (p.getCedula().equals(cedula)) return p;
        }
        return null;
    }

    private Vehiculo buscarVehiculo(List<Vehiculo> vehiculos, String placa) {
        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) return v;
        }
        return null;
    }
}