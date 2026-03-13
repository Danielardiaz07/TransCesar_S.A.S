/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Jose Rodriguez
 */
public class Conductor extends Persona{
    private String numeroLicencia;
    private String categoriaLicencia;

    public Conductor(String numeroLicencia, String categoriaLicencia, String cedula, String nombre) {
        super(cedula, nombre);
        this.numeroLicencia = numeroLicencia;
        this.categoriaLicencia = categoriaLicencia;
    }

    public boolean tieneLicenciaValida() {
    return numeroLicencia != null && !numeroLicencia.isEmpty();
    }
    
    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public String getCategoriaLicencia() {
        return categoriaLicencia;
    }

    public void setCategoriaLicencia(String categoriaLicencia) {
        this.categoriaLicencia = categoriaLicencia;
    }

    @Override
    public String toString() {
        return "Conductor{" + "numeroLicencia=" + numeroLicencia + ", categoriaLicencia=" + categoriaLicencia + '}';
    }

    @Override
    public void imprimirDetalle() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
