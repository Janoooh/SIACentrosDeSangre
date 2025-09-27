package Controlador;

import Vista.*;
import com.mycompany.siacentrosdesangre.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;

public class ControladorVentanas implements ActionListener{
    private CentroDeSangre centro;
    private VentanaMain main;
    
    private VentanaAgregar agregar;
    private VentanaAgregarCamp agregarCampania;
    private VentanaAgregarDonacion agregarDonacion;
    private VentanaAgregarDonante agregarDonante;
    private VentanaAgregarSangre agregarSangre;
    
    private VentanaMostrar mostrar;
    private VentanaMostrarCampanias mostrarCampanias;
    private VentanaMostrarDonaciones mostrarDonaciones;
    private VentanaMostrarPersonas mostrarPersonas;
    private VentanaMostrarDonantes mostrarDonantes;
    private VentanaMostrarFlebotomistas mostrarFlebotomistas;
    private VentanaMostrarTodo mostrarTodo;
    private VentanaMostrarInventarioSangre mostrarInventario;
    
    private VentanaModificar modificar;
    private VentanaModificarCampania modificarCampania;
    //private VentanaModificarDonante modificarDonante;
    //private VentanaModificarFlebotomista modificarFlebotomista;
    
    private VentanaBuscarCampania buscarCamp;
    private VentanaAviso aviso;
    private Campania campania;
    
    public void iniciar(CentroDeSangre centro){
        this.centro = centro;
        main = new VentanaMain();
        main.getBotonAgregarMain().addActionListener(this);
        main.getBotonMostrarMain().addActionListener(this);
        main.getBotonEliminarMain().addActionListener(this);
        main.getBotonModificarMain().addActionListener(this);
        
        main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        main.setVisible(true);
    }
    
    public void mandarAviso(String msg){
        aviso = new VentanaAviso(msg);
        aviso.getBotonAceptarAviso().addActionListener(this);
        aviso.setAlwaysOnTop(true);
        aviso.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        //evento pulsar el boton agregar en la ventana main
        if(evento.getSource() == main.getBotonAgregarMain()){
            agregar = new VentanaAgregar();
            agregar.getBotonAgreCampania().addActionListener(this);
            agregar.getBotonAgreDonacion().addActionListener(this);
            agregar.getBotonAgreSangre().addActionListener(this);
            agregar.getBotonAgreAtras().addActionListener(this);
            
            agregar.setAlwaysOnTop(true);
            agregar.setVisible(true);
            return;
        }
        
        //evento pulsar el boton agregar campania en la ventana agregar
        if(agregar != null && evento.getSource() == agregar.getBotonAgreCampania()){
            agregarCampania = new VentanaAgregarCamp();
            agregarCampania.getBotonConfirmarAgregarCamp().addActionListener(this);
            agregarCampania.getBotonCancelarAgregarCamp().addActionListener(this);
            
            agregarCampania.setAlwaysOnTop(true);
            agregarCampania.setVisible(true);
            return;
        }
        
        //evento pulsar el boton agregar en la ventana agregar campania
        if(agregarCampania != null && evento.getSource() == agregarCampania.getBotonConfirmarAgregarCamp()){
            String id = agregarCampania.getLlenadoId().getText();
            String localidad = agregarCampania.getLlenadoLocalidad().getText();

            try {
                centro.agregarCampaniaNueva(Integer.parseInt(id), localidad);
                mandarAviso("La campania ha sido creada exitosamente.");
                agregarCampania.dispose();
            }catch (IOException ex) {
                System.getLogger(ControladorVentanas.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }catch(DataDuplicateException e){
                mandarAviso("Hay una campania con ese id registrado.");
            }
            
            return;
        }
        
        //pulsar el boton aceptar en la ventana de error
        if(aviso != null && evento.getSource() == aviso.getBotonAceptarAviso()){
            aviso.dispose();
            return;
        }
        
        //evento pulsar el boton cancelar en la ventana agregar campania
        if(agregarCampania != null && evento.getSource() == agregarCampania.getBotonCancelarAgregarCamp()){
            agregarCampania.dispose();
            return;
        }
        
        //evento pulsar el boton agregar donacion en la ventana agregar
        if(agregar != null && evento.getSource() == agregar.getBotonAgreDonacion()){
            buscarCamp = new VentanaBuscarCampania();
            buscarCamp.getBotonConfirmarBuscarCamp().addActionListener(this);
            buscarCamp.getBotonCancelarBuscarCamp().addActionListener(this);
            
            buscarCamp.setAlwaysOnTop(true);
            buscarCamp.setVisible(true);
            return;
        }
        
        //evento pulsar el boton buscar en la ventana buscar campania
        if(buscarCamp != null && evento.getSource() == buscarCamp.getBotonConfirmarBuscarCamp()){
            int id = Integer.parseInt(buscarCamp.getLlenadoCampania().getText());
            
            try{
                campania = centro.buscarCampania(id);
                if(campania == null)throw new NotFoundException("Campania "+id+" no encontrada.");
                agregarDonacion = new VentanaAgregarDonacion();
                agregarDonacion.getBotonConfirmarAgreDonacion().addActionListener(this);
                agregarDonacion.getBotonCancelarAgreDonacion().addActionListener(this);
                agregarDonacion.setAlwaysOnTop(true);
                agregarDonacion.setVisible(true);
            }catch(NotFoundException e){
                mandarAviso(e.getMessage());
            }
            return;
        }
        
        //evento pulsar el boton cancelar en la ventana buscar campania
        if(buscarCamp != null && evento.getSource() == buscarCamp.getBotonCancelarBuscarCamp()){
            buscarCamp.dispose();
            return;
        }
        
        //evento pulsar el boton agregar en la ventana agregar donacion
        if(agregarDonacion != null && evento.getSource() == agregarDonacion.getBotonConfirmarAgreDonacion()){
            int id;
            String fecha, rutDonante, rutFlebo;
            
            id = Integer.parseInt(agregarDonacion.getLlenadoId().getText());
            fecha = agregarDonacion.getLlenadoFecha().getText();
            rutDonante = agregarDonacion.getLlenadoRutDonante().getText();
            rutFlebo = agregarDonacion.getLlenadoRutFlebotomista().getText();
            //Donacion aux = new Donacion()
            
            try {
                centro.agregarDonacionACampania(campania, id, fecha,rutDonante,rutFlebo);
                mandarAviso("La donacion se ha agregado exitosamente.");
                agregarDonacion.dispose();
            } catch (IOException ex) {
                System.getLogger(ControladorVentanas.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            } catch (NotFoundException e){
                mandarAviso(e.getMessage());
            } catch (DataDuplicateException e){
                mandarAviso(e.getMessage());
            }
            return;
        }
        
        //evento pulsar el boton cancelar en la ventana agregar donacion
        if(agregarDonacion != null && evento.getSource() == agregarDonacion.getBotonCancelarAgreDonacion()){
            agregarDonacion.dispose();
            return;
        }
        
        //Evento pulsar el boton agregar donante de la ventana agregar.
        if(agregar != null && evento.getSource() == agregar.getBotonAgreDonante()){
            agregarDonante = new VentanaAgregarDonante();
            agregarDonante.getBotonAceptarAgreDonante().addActionListener(this);
            agregarDonante.getBotonCancelarAgreDonante().addActionListener(this);
            
            agregarDonante.setAlwaysOnTop(true);
            agregarDonante.setVisible(true);
            return;
        }
        
        if(agregarDonante != null && evento.getSource() == agregarDonante.getBotonAceptarAgreDonante()){
            String rut, nombre, telefono, tipoSangre;
            int edad;
            
            rut = agregarDonante.getLlenadoRut().getText();
            nombre = agregarDonante.getLlenadoNombre().getText();
            telefono = agregarDonante.getLlenadoTelefono().getText();
            edad = Integer.parseInt(agregarDonante.getLlenadoEdad().getText());
            tipoSangre = agregarDonante.getLlenadoSangre().getText();
            try{
                centro.agregarPersona(rut, nombre, telefono, edad, 1, tipoSangre);
                mandarAviso("Persona agregada correctamente.");
            }catch(DataDuplicateException e){
                mandarAviso(e.getMessage());
            }catch(Exception e){
                mandarAviso("Error desconocido.");
            }
            return;
        }
        
        //evento pulsar el boton cancelar en la ventana agregar donante
        if(agregarDonante != null && evento.getSource() == agregarDonante.getBotonCancelarAgreDonante()){
            agregarDonante.dispose();
            return;
        }
        
        //Evento pulsar el boton agregar sangre al inventario de la ventana agregar
        if(agregar != null && evento.getSource() == agregar.getBotonAgreSangre()){
            agregarSangre = new VentanaAgregarSangre();
            agregarSangre.getBotonAceptarAgreSangre().addActionListener(this);
            agregarSangre.getBotonCancelarAgreSangre().addActionListener(this);
            
            agregarSangre.setAlwaysOnTop(true);
            agregarSangre.setVisible(true);
        }
        
        //evento pulsar el boton aceptar en la ventana agregar sangre
        if(agregarSangre != null && evento.getSource() == agregarSangre.getBotonAceptarAgreSangre()){
            if(!agregarSangre.getLlenarOneg().getText().isEmpty())
                centro.agregarStockSangre("O-", Integer.parseInt(agregarSangre.getLlenarOneg().getText()));
            if(!agregarSangre.getLlenarOpos().getText().isEmpty())
                centro.agregarStockSangre("O+", Integer.parseInt(agregarSangre.getLlenarOpos().getText()));
            if(!agregarSangre.getLlenarAneg().getText().isEmpty())
                centro.agregarStockSangre("A-", Integer.parseInt(agregarSangre.getLlenarAneg().getText()));
            if(!agregarSangre.getLlenarApos().getText().isEmpty())
                centro.agregarStockSangre("A+", Integer.parseInt(agregarSangre.getLlenarApos().getText()));
            if(!agregarSangre.getLlenarBneg().getText().isEmpty())
                centro.agregarStockSangre("B-", Integer.parseInt(agregarSangre.getLlenarBneg().getText()));
            if(!agregarSangre.getLlenarBpos().getText().isEmpty())
                centro.agregarStockSangre("B+", Integer.parseInt(agregarSangre.getLlenarBpos().getText()));
            if(!agregarSangre.getLlenarABneg().getText().isEmpty())
                centro.agregarStockSangre("AB-", Integer.parseInt(agregarSangre.getLlenarABneg().getText()));
            if(!agregarSangre.getLlenarABpos().getText().isEmpty())
                centro.agregarStockSangre("AB+", Integer.parseInt(agregarSangre.getLlenarABpos().getText()));
            mandarAviso("Sangre agregada correctamente.");
            return;
        } 
        
        //evento pulsar el boton cancelar en la ventana agregar sangre
        if(agregarSangre != null && evento.getSource() == agregarSangre.getBotonCancelarAgreSangre()){
            agregarSangre.dispose();
            return;
        }
        
        //evento pulsar el boton atras en la ventana agregar
        if(agregar != null && evento.getSource() == agregar.getBotonAgreAtras()){
            agregar.dispose();
            return;
        }
        
        //evento pulsar el boton mostrar en la ventana main
        if(evento.getSource() == main.getBotonMostrarMain()){
            mostrar = new VentanaMostrar();
            mostrar.getBotonMostrarCamp().addActionListener(this);
            mostrar.getBotonMostrarDonaciones().addActionListener(this);
            mostrar.getBotonMostrarSangre().addActionListener(this);
            mostrar.getBotonMostrarPersonas().addActionListener(this);
            mostrar.getBotonAtrasMostrar().addActionListener(this);
            
            mostrar.setAlwaysOnTop(true);
            mostrar.setVisible(true);
            return;
        }
        
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
                mostrarInventario = new VentanaMostrarInventarioSangre(centro.mostrStockSangre(centro));
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
            mostrarDonantes = new VentanaMostrarDonantes(centro.datosMostrarPersona(1));
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
            mostrarFlebotomistas = new VentanaMostrarFlebotomistas(centro.datosMostrarPersona(2));
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
        
        ////evento boton mostrar todos ventana mostrar personas
        if(mostrarPersonas != null && evento.getSource() == mostrarPersonas.getBotonMostrarTodo()){
            mostrarTodo = new VentanaMostrarTodo(centro.datosMostrarPersona());
            mostrarTodo.getBotonAtrasMostrarTodo().addActionListener(this);
            
            mostrarTodo.setAlwaysOnTop(true);
            mostrarTodo.setVisible(true);
            return;
        }
        
        ////evento boton atras ventana mostrar todo
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
            return;
        }
       
        if(evento.getSource() == main.getBotonModificarMain()){
            modificar = new VentanaModificar();
            modificar.getBotonModificarCampania().addActionListener(this);
            modificar.getBotonModificarDonante().addActionListener(this);
            modificar.getBotonModificarFlebotomista().addActionListener(this);
            modificar.getBotonAtrasModificar().addActionListener(this);
            
            modificar.setAlwaysOnTop(true);
            modificar.setVisible(true);
            return;
        }
    }
    
}
