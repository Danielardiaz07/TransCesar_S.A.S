/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.Conductor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Jose Rodriguez
 */
public class ConductorDAO {
    private static final String ARCHIVO = "conductores.txt";

    public void guardar(Conductor conductor) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            bw.write(conductor.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar conductor: " + e.getMessage());
        }
    }

    public List<Conductor> cargar() {
        List<Conductor> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO);

        if (!archivo.exists()) {
            return lista;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 4) {
                    Conductor c = new Conductor(datos[0], datos[1], datos[2], datos[3]);
                    lista.add(c);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar conductores: " + e.getMessage());
        }

        return lista;
    }
}
