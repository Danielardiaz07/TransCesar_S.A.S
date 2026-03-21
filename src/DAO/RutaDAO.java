/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import model.Ruta;
import java.io.*;
import java.util.*;
/**
 *
 * @author Jose Rodriguez
 */
public class RutaDAO {
    private static final String ARCHIVO = "data/rutas.txt";

    public void guardar(Ruta ruta) {
        new File("data").mkdirs();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            bw.write(ruta.serializar());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error guardando ruta: " + e.getMessage());
        }
    }

    public List<Ruta> cargarTodas() {
        List<Ruta> lista = new ArrayList<>();
        File f = new File(ARCHIVO);
        if (!f.exists()) return lista;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.isBlank()) {
                    Ruta r = Ruta.deserializar(linea);
                    if (r != null) lista.add(r);
                }
            }
        } catch (IOException e) {
            System.out.println("Error cargando rutas: " + e.getMessage());
        }
        return lista;
    }
}
