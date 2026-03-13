/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Jose Rodriguez
 */
public class PasajeroDAO {
    private static final String ARCHIVO = "pasajeros.txt";

    public void guardar(Pasajero pasajero) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            bw.write(pasajero.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar pasajero: " + e.getMessage());
        }
    }

    public List<Pasajero> cargar() {
        List<Pasajero> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO);

        if (!archivo.exists()) {
            return lista;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 3) {
                    String cedula = datos[0];
                    String nombre = datos[1];
                    String tipo = datos[2];

                    Pasajero pasajero = crearPasajeroPorTipo(cedula, nombre, tipo);
                    if (pasajero != null) {
                        lista.add(pasajero);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar pasajeros: " + e.getMessage());
        }

        return lista;
    }

    private Pasajero crearPasajeroPorTipo(String cedula, String nombre, String tipo) {
        switch (tipo.toLowerCase()) {
            case "regular":
                return new PasajeroRegular(cedula, nombre);
            case "estudiante":
                return new PasajeroEstudiante(cedula, nombre);
            case "adultomayor":
                return new PasajeroAdultoMayor(cedula, nombre);
            default:
                return null;
        }
    }
}
