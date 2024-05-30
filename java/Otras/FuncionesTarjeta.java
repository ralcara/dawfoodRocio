/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Otras;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import models.Productos;
import models.Tickets;

/**
 *
 * @author rocio
 */
public class FuncionesTarjeta {
    public static String generarContrasena() {
        String caracteresMayuscula = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String caracteresMinuscula = "abcdefghijklmnopqrstuvwxyz";
        String caracteresNumeros = "0123456789";
        String caracteresEspeciales = "!@#$^&()_=+-*/%<>?[]{}";

        Random random = new Random();
        StringBuilder contraseña = new StringBuilder();

        int indiceMinuscula = random.nextInt(caracteresMinuscula.length());
        contraseña.append(caracteresMinuscula.charAt(indiceMinuscula));
        int indiceMayuscula = random.nextInt(caracteresMinuscula.length());
        contraseña.append(caracteresMayuscula.charAt(indiceMayuscula));
        int indiceNumeros = random.nextInt(caracteresNumeros.length());
        contraseña.append(caracteresNumeros.charAt(indiceNumeros));
        int indiceEspeciales = random.nextInt(caracteresEspeciales.length());
        contraseña.append(caracteresEspeciales.charAt(indiceEspeciales));

        for (int i = 1; i < 3; i++) {
            String caracteres = caracteresMayuscula + caracteresMinuscula + caracteresNumeros;
            int indice = random.nextInt(caracteres.length());
            char caracter = caracteres.charAt(indice);
            contraseña.append(caracter);
        }

        return contraseña.toString();
    }
     public static boolean verificarContrasena(String contrasenya) {
        boolean valida = true;

        do {
            String entradaUsuario = JOptionPane.showInputDialog("Introduce la contraseña: ");

            if (entradaUsuario.equals(contrasenya)) {
                JOptionPane.showMessageDialog(null, "Contraseña correcta");
                valida = true;
            } else {
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                valida = false;

            }

        } while (!valida);

        return valida;

    }
      public static void realizarTransaccion(List<Productos> carrito, List<Integer> cantidades, double precio, List<Tickets> tickets) {
        boolean esNumeroDeCuatroDigitos = false;
        boolean esFecha = false;
        boolean esCvv = false;
        String ultimosCuatroDigitos = "";
        String fecha = "";
        String cvv = "";
        List<Tarjeta> tarjetasValidas = crearTarjetasValidas();

        do {
            ultimosCuatroDigitos = JOptionPane.showInputDialog(null, "Ingrese los últimos 4 dígitos de su tarjeta: ");

            esNumeroDeCuatroDigitos = ultimosCuatroDigitos.matches("\\d{4}");

            if (!esNumeroDeCuatroDigitos) {
                JOptionPane.showMessageDialog(null, "Número de tarjeta inválido. Por favor, inténtelo de nuevo.");
            }

        } while (!esNumeroDeCuatroDigitos);

        do {
            fecha = JOptionPane.showInputDialog(null, "Ingrese la fecha de caducidad de su tarjeta (MM/YY): ");

            esFecha = fecha.matches("(0[1-9]|1[0-2])\\/([0-9]{2})");

            if (!esFecha) {
                JOptionPane.showMessageDialog(null, "Fecha de caducidad inválida. Por favor, inténtelo de nuevo.");
            }

        } while (!esFecha);

        do {
            cvv = JOptionPane.showInputDialog(null, "Ingrese el CVV de su tarjeta: ");

            esCvv = cvv.matches("\\d{3}");

            if (!esCvv) {
                JOptionPane.showMessageDialog(null, "CVV inválido. Por favor, inténtelo de nuevo.");
            }

        } while (!esCvv);

        Tarjeta t1 = new Tarjeta(ultimosCuatroDigitos, fecha, cvv);

        if (t1.comprobarTarjeta(tarjetasValidas)) {
            if (precio < t1.getSaldo()) {
                t1.setSaldo(precio - t1.getSaldo());
                Tickets ticket = new Tickets();
//               FuncionesTickets.imprimirTicket(LocalDate fecha_hora_generacion,int tpv_id, String codigo_transaccion, int ticket_id);
//                añadirTicket(tickets, ticket);
                carrito.clear();
            } else {
                JOptionPane.showMessageDialog(null, "Saldo insuficiente", "Error", JOptionPane.ERROR_MESSAGE);
                carrito.clear();
            }
        }
    }

    public static List<Tarjeta> crearTarjetasValidas() {
        List<Tarjeta> tarjetasValidas = new ArrayList<>();

        tarjetasValidas.add(new Tarjeta("1234", "01/28", "123"));
        tarjetasValidas.add(new Tarjeta("4542", "01/28", "986"));
        tarjetasValidas.add(new Tarjeta("9886", "10/30", "548"));
        tarjetasValidas.add(new Tarjeta("9821", "09/26", "897"));

        for (Tarjeta tarjetaValida : tarjetasValidas) {
            System.out.println(tarjetaValida.toString());
        }

        return tarjetasValidas;
    }
}
