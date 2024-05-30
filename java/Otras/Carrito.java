/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Otras;

import java.util.ArrayList;
import java.util.List;
import models.Productos;

/**
 *
 * @author Rosío
 */
public class Carrito {
     private static List<Productos> carrito = new ArrayList<>();

    public static void AñadirAlCarrito(List<Productos> productos) {
        carrito.addAll(productos);
    }

    public static List<Productos> getCarrito() {
        return carrito;
    }
}
