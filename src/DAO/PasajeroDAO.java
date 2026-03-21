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
        if (!archivo.exists()) return lista;
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(";");
                if (d.length == 4) {
                    LocalDate fecha = LocalDate.parse(d[3]);
                    Pasajero p = switch (d[2].toLowerCase()) {
                        case "regular"     -> new PasajeroRegular(d[0], d[1], fecha);
                        case "estudiante"  -> new PasajeroEstudiante(d[0], d[1], fecha);
                        case "adultomayor" -> new PasajeroAdultoMayor(d[0], d[1], fecha);
                        default            -> null;
                    };
                    if (p != null) lista.add(p);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar pasajeros: " + e.getMessage());
        }
        return lista;
    }
}
