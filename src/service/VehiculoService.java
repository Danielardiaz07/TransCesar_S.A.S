package service;

import dao.VehiculoDAO;
import model.*;
import java.util.*;

public class VehiculoService {

    private final VehiculoDAO dao = new VehiculoDAO();
    private final List<Vehiculo> vehiculos;

    public VehiculoService() {
        vehiculos = dao.cargarTodos();
    }

    public String registrarVehiculo(int tipo, String placa, String ruta) {
        for (Vehiculo v : vehiculos)
            if (v.getPlaca().equalsIgnoreCase(placa))
                return "ERROR: Ya existe el vehículo con placa " + placa;

        Vehiculo nuevo = switch (tipo) {
            case 1 -> new Buseta(placa, ruta);
            case 2 -> new MicroBus(placa, ruta);
            case 3 -> new Bus(placa, ruta);
            default -> null;
        };
        if (nuevo == null) 
            return "ERROR: Tipo de vehículo inválido.";

        vehiculos.add(nuevo);
        dao.guardar(nuevo);
        return "Vehículo registrado: " + nuevo.getPlaca();
    }

    public Vehiculo buscarPorPlaca(String placa) {
        return vehiculos.stream()
            .filter(v -> v.getPlaca().equalsIgnoreCase(placa))
            .findFirst().orElse(null);
    }

    public List<Vehiculo> listarVehiculos() { 
        return vehiculos; }
}