package com.mycompany.siacentrosdesangre;

import Controlador.ControladorVentanas;
import java.io.*;

public class SIACentrosDeSangre {

    public static void main(String[] args) throws IOException {
        CentroDeSangre centro = new CentroDeSangre(""); 
        //Menu menusConsola = new Menu(); 
        centro.crearStockSangre(); //Se crea el stock de sangre del centro.
        //Se cargan la informacion guardada en los archivos de texto.
        //Herramientas.cargarDatos(centro, "datosCampania.txt","datosDonaciones.txt"); 
        //menusConsola.mostrarMenuPrincipal(centro); //Se ejecutan los menu.
        Herramientas.cargarPersonas(centro, "datosPersona.txt");
        Herramientas.cargarCampanias(centro, "datosCampania.txt");
        Herramientas.cargarDonaciones(centro, "datosDonacion.txt");
        Herramientas.cargarSangre(centro, "datosSangre.txt");
        ControladorVentanas ventanas = new ControladorVentanas();
        ventanas.iniciar(centro);
    }
}
