package com.mycompany.siacentrosdesangre;

import java.io.*;

public class SIACentrosDeSangre {

    public static void main(String[] args) throws IOException {
        CentroDeSangre centro = new CentroDeSangre("");
        centro.crearStockSangre();
        Herramientas.cargarDatos(centro, "datosCentroSangre.txt");
        Menu menusConsola = new Menu();
        menusConsola.mostrarMenuPrincipal(centro);
        
    }
}