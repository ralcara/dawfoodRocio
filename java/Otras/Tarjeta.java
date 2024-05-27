/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Otras;

import java.util.List;
import java.util.Random;

/**
 *
 * @author rocio
 */
public class Tarjeta {
    
    Random r1 = new Random();

    private String ultimosCuatroDigitos;
    private String fechaCaducidad;
    private String cvv;
    private String nombreTitular;
    private double saldo;

    public Tarjeta(String ultimosCuatroDigitos, String fechaCaducidad, String cvv) {
        this.ultimosCuatroDigitos = ultimosCuatroDigitos;
        this.fechaCaducidad = fechaCaducidad;
        this.cvv = cvv;
        this.saldo = r1.nextDouble(100, 1000);
    }

    public Tarjeta() {
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getUltimosCuatroDigitos() {
        return ultimosCuatroDigitos;
    }

    public void setUltimosCuatroDigitos(String ultimosCuatroDigitos) {
        this.ultimosCuatroDigitos = ultimosCuatroDigitos;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public boolean comprobarTarjeta(List<Tarjeta> tarjetasValidas) {

        for (Tarjeta tarjeta : tarjetasValidas) {
            if (this.ultimosCuatroDigitos.equals(tarjeta.getUltimosCuatroDigitos()) && this.fechaCaducidad.equals(tarjeta.getFechaCaducidad()) && this.cvv.equals(tarjeta.getCvv())) {
                return true;
            }
        }
        return false;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ultimosCuatroDigitos=").append(ultimosCuatroDigitos);
        sb.append(", fechaCaducidad=").append(fechaCaducidad);
        sb.append(", cvv=").append(cvv);
        sb.append(", saldo=").append(saldo);
        return sb.toString();
    }

}

