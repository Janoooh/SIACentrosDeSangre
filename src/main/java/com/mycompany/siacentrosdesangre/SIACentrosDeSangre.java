package com.mycompany.siacentrosdesangre;

import java.io.*;

public class SIACentrosDeSangre {

    public static void main(String[] args) throws IOException {
        CentroDeSangre centro = new CentroDeSangre(Herramientas.lector("Ingrese nombre del nuevo centro de sangre : "));
        Menu menusConsola = new Menu();
        centro.crearStockSangre();
        menusConsola.mostrarMenuPrincipal(centro);
        
    }
}