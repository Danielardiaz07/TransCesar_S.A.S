/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;
import DAO.RutaDAO;
import model.Ruta;
import java.util.*;
/**
 *
 * @author Jose Rodriguez
 */
public class RutaService {
     private final List<Ruta> rutas;
    private final RutaDAO dao;

    public RutaService() {
        dao  = new RutaDAO();
        rutas = dao.cargarTodas();
    }

    public boolean registrarRuta(Ruta ruta) {
        if (buscarPorCodigo(ruta.getCodigoRuta()) != null) {
            System.out.println("Ya existe una ruta con ese código.");
            return false;
        }
        rutas.add(ruta);
        dao.guardar(ruta);
        return true;
    }

    public Ruta buscarPorCodigo(String codigo) {
        return rutas.stream()
            .filter(r -> r.getCodigoRuta().equalsIgnoreCase(codigo))
            .findFirst().orElse(null);
    }

    public List<Ruta> getRutas() { return rutas; }

    public void listarRutas() {
        if (rutas.isEmpty()) {
            System.out.println("No hay rutas registradas.");
            return;
        }
        rutas.forEach(r -> { r.imprimirDetalle(); System.out.println("-----------"); });
    }
}
