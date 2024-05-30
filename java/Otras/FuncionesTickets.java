/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Otras;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import models.Productos;
import models.Tickets;


/**
 *
 * @author rocio
 */
public class FuncionesTickets {

    public static void añadirTicket(){

    }
   public static void imprimirTicket(LocalDate fecha_hora_generacion,int tpv_id, String codigo_transaccion, int ticket_id) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechaFormateada = fecha_hora_generacion.format(formatter);

       
        StringBuilder ticketContent = new StringBuilder();
        ticketContent.append("******************************\n");
        ticketContent.append("         TICKET DE COMPRA      \n");
        ticketContent.append("******************************\n");
        ticketContent.append("Fecha: ").append(fechaFormateada).append("\n");
        ticketContent.append("TPV ID: ").append(tpv_id).append("\n");
        ticketContent.append("Código de Transacción: ").append(codigo_transaccion).append("\n");
        ticketContent.append("Ticket ID: ").append(ticket_id).append("\n");
        ticketContent.append("******************************\n");
        ticketContent.append("Gracias por su compra!\n");
        ticketContent.append("******************************\n");

      
        System.out.println(ticketContent.toString());
    }
   
public static void consultarVentasHoy(List<Tickets> tickets) {
        List<Integer> ticketsHoy = new ArrayList<>();
        for (Tickets ticket : tickets) {
            if (ticket.getFechaHoraGeneracion().equals(LocalDate.now())) {
                ticketsHoy.add(ticket.getTicketId());
            }
        }
        JOptionPane.showMessageDialog(null, ticketsHoy);
}
}

