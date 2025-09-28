package Controlador;

import Vista.*;
import com.mycompany.siacentrosdesangre.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 * Controlador principal de las ventanas.
 */
public class ControladorVentanas implements ActionListener{
    private CentroDeSangre centro;
    private VentanaMain main;
    private ControladorAgregar agregar;
    private ControladorMostrar mostrar;
    private ControladorEliminar eliminar;
    private ControladorModificar modificar;
    
    /**
     * Inicia la ventana principal.
     * @param centro es la entidad que contiene todos los datos del sistema.
     */
    public void iniciar(CentroDeSangre centro){
        this.centro = centro;
        main = new VentanaMain();
        main.getBotonAgregarMain().addActionListener(this);
        main.getBotonMostrarMain().addActionListener(this);
        main.getBotonEliminarMain().addActionListener(this);
        main.getBotonModificarMain().addActionListener(this);
        main.getBotonSalirMain().addActionListener(this);
        
        main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        main.setVisible(true);
    }
    
    /**
     * 
     * @param evento Representa un evento en las ventanas, 
     */
    @Override
    public void actionPerformed(ActionEvent evento) {
        //evento pulsar el boton agregar en la ventana main
        if(evento.getSource() == main.getBotonAgregarMain()){
            agregar = new ControladorAgregar();
            agregar.iniciar(centro);
            return;
        }
        
        //evento pulsar el boton mostrar en la ventana main
        if(evento.getSource() == main.getBotonMostrarMain()){
            mostrar = new ControladorMostrar();
            mostrar.iniciar(centro);
            return;
        }
        
        if(evento.getSource() == main.getBotonEliminarMain()){
            eliminar = new ControladorEliminar();
            eliminar.iniciar(centro);
            return;
        }
        
        if(evento.getSource() == main.getBotonModificarMain()){
            modificar = new ControladorModificar();
            modificar.iniciar(centro);
            return;
        }
        
        if(evento.getSource() == main.getBotonSalirMain()){
            //Aqui va el guardar datos.
            centro.guardarCampanias("datosCampania.txt");
            centro.guardarPersonas("datosPersona.txt");
            centro.guardarDonaciones("datosDonacion.txt");
            centro.guardarSangres("datosSangre.txt");
            System.exit(0);
        }
    }
}
