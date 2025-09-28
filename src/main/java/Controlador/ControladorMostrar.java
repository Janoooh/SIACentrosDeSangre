package Controlador;

import Vista.*;
import com.mycompany.siacentrosdesangre.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControladorMostrar implements ActionListener{
    private CentroDeSangre centro;
    private VentanaMostrar mostrar;
    private VentanaMostrarCampanias mostrarCampanias;
    private VentanaMostrarDonaciones mostrarDonaciones;
    private VentanaMostrarPersonas mostrarPersonas;
    private VentanaMostrarDonantes mostrarDonantes;
    private VentanaMostrarFlebotomistas mostrarFlebotomistas;
    private VentanaMostrarTodo mostrarTodo;
    private VentanaMostrarInventarioSangre mostrarInventario;
    
    public void iniciar(CentroDeSangre centro){
        this.centro = centro;
        mostrar = new VentanaMostrar();
        mostrar.getBotonMostrarCamp().addActionListener(this);
        mostrar.getBotonMostrarDonaciones().addActionListener(this);
        mostrar.getBotonMostrarSangre().addActionListener(this);
        mostrar.getBotonMostrarPersonas().addActionListener(this);
        mostrar.getBotonAtrasMostrar().addActionListener(this);
            
        mostrar.setAlwaysOnTop(true);
        mostrar.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        //evento pulsar el boton mostrar campanias en la ventana mostrar
        if(mostrar != null && evento.getSource() == mostrar.getBotonMostrarCamp()){
            try {
                mostrarCampanias = new VentanaMostrarCampanias(centro);
            } catch (IOException ex) {
                System.getLogger(ControladorVentanas.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            mostrarCampanias.getBotonAtrasMostrarCamp().addActionListener(this);
            
            mostrarCampanias.setAlwaysOnTop(true);
            mostrarCampanias.setVisible(true);
            return;
        }
        
        //evento pulsar el boton atras en la ventana mostrar campanias
        if(mostrarCampanias != null && evento.getSource() == mostrarCampanias.getBotonAtrasMostrarCamp()){
            mostrarCampanias.dispose();
            return;
        }
        
        //evento pulsar el boton mostrar donaciones en la ventana mostrar
        if(mostrar != null && evento.getSource() == mostrar.getBotonMostrarDonaciones()){
            
            try {
                mostrarDonaciones = new VentanaMostrarDonaciones(centro);
                
            } catch (IOException ex) {
                System.getLogger(ControladorVentanas.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            mostrarDonaciones.getBotonAtrasMostrarDonaciones().addActionListener(this);
            
            mostrarDonaciones.setAlwaysOnTop(true);
            mostrarDonaciones.setVisible(true);
            return;
        }
        
        //evento pulsar el boton atras en la ventana mostrar donaciones
        if(mostrarDonaciones != null && evento.getSource() == mostrarDonaciones.getBotonAtrasMostrarDonaciones()){
            mostrarDonaciones.dispose();
            return;
        }
        
        //evento pulsar el boton mostrar inventario de sangre en la ventana mostrar
        if(mostrar != null && evento.getSource() == mostrar.getBotonMostrarSangre()){
            
            try {
                mostrarInventario = new VentanaMostrarInventarioSangre(centro.mostrarStockSangre(centro,"datosSangre.txt"));
            } catch (IOException ex) {
                System.getLogger(ControladorVentanas.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            mostrarInventario.getBotonAtrasMostrarSangre().addActionListener(this);
            
            mostrarInventario.setAlwaysOnTop(true);
            mostrarInventario.setVisible(true);
            return;
        }
        
        //evento pulsar el boton atras en la ventana mostrar inventario de sangre
        if(mostrarInventario != null && evento.getSource() == mostrarInventario.getBotonAtrasMostrarSangre()){
            mostrarInventario.dispose();
            return;
        }
        
        //evento boton mostrar personas en ventana mostrar
        if(mostrar != null && evento.getSource() == mostrar.getBotonMostrarPersonas()){
            mostrarPersonas = new VentanaMostrarPersonas();
            mostrarPersonas.getBotonMostrarDonantes().addActionListener(this);
            mostrarPersonas.getBotonMostrarFlebotomistas().addActionListener(this);
            mostrarPersonas.getBotonMostrarTodo().addActionListener(this);
            mostrarPersonas.getBotonAtrasMostrarPersonas().addActionListener(this);
            
            mostrarPersonas.setAlwaysOnTop(true);
            mostrarPersonas.setVisible(true);
            return;
        }
        
        //evento boton mostrar donantes ventana mostrar personas
        if(mostrarPersonas != null && evento.getSource() == mostrarPersonas.getBotonMostrarDonantes()){
            mostrarDonantes = new VentanaMostrarDonantes(centro.datosMostrarPersona(1,false));
            mostrarDonantes.getBotonAtrasMostrarDonante().addActionListener(this);
            
            mostrarDonantes.setAlwaysOnTop(true);
            mostrarDonantes.setVisible(true);
            return;
        }
        
        //evento boton atras ventana mostrar donantes
        if(mostrarDonantes != null && evento.getSource() == mostrarDonantes.getBotonAtrasMostrarDonante()){
            mostrarDonantes.dispose();
            return;
        }
        
        //evento boton mostrar flebotomistas ventana mostrar personas
        if(mostrarPersonas != null && evento.getSource() == mostrarPersonas.getBotonMostrarFlebotomistas()){
            mostrarFlebotomistas = new VentanaMostrarFlebotomistas(centro.datosMostrarPersona(2, false));
            mostrarFlebotomistas.getBotonAtrasMostrarFlebotomistas().addActionListener(this);
            
            mostrarFlebotomistas.setAlwaysOnTop(true);
            mostrarFlebotomistas.setVisible(true);
            return;
        }
        
        //evento boton atras ventana mostrar flebotomistas
        if(mostrarFlebotomistas != null && evento.getSource() == mostrarFlebotomistas.getBotonAtrasMostrarFlebotomistas()){
            mostrarFlebotomistas.dispose();
            return;
        }
        
        //evento boton mostrar todos ventana mostrar personas
        if(mostrarPersonas != null && evento.getSource() == mostrarPersonas.getBotonMostrarTodo()){
            mostrarTodo = new VentanaMostrarTodo(centro.datosMostrarPersona(true));
            mostrarTodo.getBotonAtrasMostrarTodo().addActionListener(this);
            
            mostrarTodo.setAlwaysOnTop(true);
            mostrarTodo.setVisible(true);
            return;
        }
        
        //evento boton atras ventana mostrar todo
        if(mostrarTodo != null && evento.getSource() == mostrarTodo.getBotonAtrasMostrarTodo()){
            mostrarTodo.dispose();
            return;
        }
        
        //evento boton atras ventana mostrar personas
        if(mostrarPersonas != null && evento.getSource() == mostrarPersonas.getBotonAtrasMostrarPersonas()){
            mostrarPersonas.dispose();
            return;
        }
        
        //evento pulsar el boton atras en la ventana mostrar
        if(mostrar != null && evento.getSource() == mostrar.getBotonAtrasMostrar()){
            mostrar.dispose();
            mostrar = null;
            return;
        }
    }
}
