package dao;

import model.*;
import java.io.*;
import java.util.*;

public class VehiculoDAO {

    private static final String BUSETA = "data/buseta.txt";
    private static final String MICROBUS = "data/microbus.txt";
    private static final String BUS = "data/bus.txt";

    public void guardar(Vehiculo v) {
        try (BufferedWriter bw =
                new BufferedWriter(new FileWriter(getArchivo(v), true))) {
            bw.write(v.serializar()); bw.newLine();
        } catch (IOException e) {
            System.out.println("Error guardando vehículo: " + e.getMessage());
        }
    }

    public List<Vehiculo> cargarTodos() {
        List<Vehiculo> lista = new ArrayList<>();
        lista.addAll(leer(BUSETA));
        lista.addAll(leer(MICROBUS));
        lista.addAll(leer(BUS));
        return lista;
    }

    private List<Vehiculo> leer(String ruta) {
        List<Vehiculo> lista = new ArrayList<>();
        File f = new File(ruta);
        if (!f.exists()) return lista;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.isBlank()) {
                    Vehiculo v = deserializar(linea);
                    if (v != null) lista.add(v);
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo vehículo: " + e.getMessage());
        }
        return lista;
    }

    private Vehiculo deserializar(String linea) {
        String[] p = linea.split(";");
        Vehiculo v = switch (p[0]) {
            case "BUSETA" -> new Buseta(p[1], p[2]);
            case "MICROBUS" -> new MicroBus(p[1], p[2]);
            case "BUS" -> new Bus(p[1], p[2]);
            default -> null;
        };
        if (v != null) {
            v.setPasajerosActuales(Integer.parseInt(p[3]));
            v.setDisponible(Boolean.parseBoolean(p[4]));
        }
        return v;
    }

    private String getArchivo(Vehiculo v) {
        if (v instanceof Buseta)   
            return BUSETA;
        if (v instanceof MicroBus) 
            return MICROBUS;
        
        return BUS;
    }
}