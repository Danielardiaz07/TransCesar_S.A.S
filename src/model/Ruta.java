/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Jose Rodriguez
 */
public class Ruta implements Imprimible {
    private String codigoRuta;
    private String ciudadOrigen;
    private String ciudadDestino;
    private double distanciaKm;
    private int tiempoEstimadoMinutos;

    public Ruta(String codigoRuta, String ciudadOrigen, String ciudadDestino, double distanciaKm, int tiempoEstimadoMinutos) {
        this.codigoRuta = codigoRuta;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.distanciaKm = distanciaKm;
        this.tiempoEstimadoMinutos = tiempoEstimadoMinutos;
    }

    public String getCodigoRuta() {
        return codigoRuta;
    }

    public void setCodigoRuta(String codigoRuta) {
        this.codigoRuta = codigoRuta;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public int getTiempoEstimadoMinutos() {
        return tiempoEstimadoMinutos;
    }

    public void setTiempoEstimadoMinutos(int tiempoEstimadoMinutos) {
        this.tiempoEstimadoMinutos = tiempoEstimadoMinutos;
    }
    
    @Override
    public void imprimirDetalle() {
        System.out.println("=== RUTA ===");
        System.out.println("Código    : " + codigoRuta);
        System.out.println("Origen    : " + ciudadOrigen);
        System.out.println("Destino   : " + ciudadDestino);
        System.out.printf ("Distancia : %.1f km%n", distanciaKm);
        System.out.printf ("Tiempo est.: %d min%n", tiempoEstimadoMinutos);
    }

    @Override
    public String toString() {
        return "Ruta{" + "codigoRuta=" + codigoRuta + ", ciudadOrigen=" + ciudadOrigen + ", ciudadDestino=" + ciudadDestino + ", distanciaKm=" + distanciaKm + ", tiempoEstimadoMinutos=" + tiempoEstimadoMinutos + '}';
    }
    
}
