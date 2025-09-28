package Controlador;

import Vista.*;
import com.mycompany.siacentrosdesangre.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Controlador encargados de las ventanas dentro del menú mostrar.
 * Implementa el action listener para saber qué botones se pulsaron y en cuál ventana.
 */
public class ControladorMostrar implements ActionListener{
    private CentroDeSangre centro;
    private VentanaMostrar mostrar;
    private VentanaMostrarCampanias mostrarCampanias;//para todas la campañas
    private VentanaMostrarCampanias mostrarCampania; //una campaña especifica
    private VentanaMostrarDonaciones mostrarDonaciones; //todas las donaciones
    private VentanaMostrarDonaciones mostrarDonacion;//una donacion
    private VentanaMostrarPersonas mostrarPersonas;//mostrar todas las personas
    private VentanaMostrarPersonas mostrarPersona;//mostrar una persona
    private VentanaMostrarDonantes mostrarDonantes;//mostrar todos los donantes
    private VentanaMostrarDonantes mostrarDonante;//mostrar un donante
    private VentanaMostrarFlebotomistas mostrarFlebotomistas;//mostrar todos los flebotomistas
    private VentanaMostrarFlebotomistas mostrarFlebotomista;//mostrar flebotomista
    private VentanaMostrarTodo mostrarTodo;//mostrar todas las personas dentro del sistema
    private VentanaMostrarTodo mostrarUno;//mostar una persona dentro del sistema
    private VentanaMostrarInventarioSangre mostrarInventario;
    private VentanaBuscarCampania buscarCampania;
    private VentanaBuscarDonacion buscarDonacion;
    private VentanaBuscarPersona buscarPersona;
    private Campania campania;
    private Donacion donacion;
    private VentanaAviso aviso;
    private boolean aux; //true = mostrar campania - false = mostrar donacion
    private int flag;//1 = donante - 2 = flebotomista - 3 = todo
    private Persona persona;
    private Persona persona2;
    
    /**
     * Método que inicia la ventana principal del mostrar.
     * @param centro Clase más alta, necesaria para tener acceso a todos los datos.
     */
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
    
    public void mandarAviso(String msg){
        aviso = new VentanaAviso(msg);
        aviso.getBotonAceptarAviso().addActionListener(this);
        aviso.setAlwaysOnTop(true);
        aviso.setVisible(true);
    }
    
    /**
     * Método para saber hacia dónde se quiere dirigir el usuario dentro del menú.
     * Al pulsar un bóton se genera un evento, se ocupa la fuente de ese evento para crear o eliminar ventanas.
     * @param evento evento realizado por el usuario, en este caso es pulsar un botón.
     */
    @Override
    public void actionPerformed(ActionEvent evento) {
        //evento pulsar el boton mostrar campanias en la ventana mostrar
        if(evento.getSource() == mostrar.getBotonMostrarCamp()){
            mostrarCampanias = new VentanaMostrarCampanias(centro.datosMostrarCampanias());
            mostrarCampanias.getBotonAtrasMostrarCamp().addActionListener(this);
            mostrarCampanias.getBotonBuscarCamp().addActionListener(this);
            
            mostrarCampanias.setAlwaysOnTop(true);
            mostrarCampanias.setVisible(true);
            return;
        }
        
        //evento pulsar el boton atras en la ventana mostrar campanias
        if(mostrarCampanias != null && evento.getSource() == mostrarCampanias.getBotonAtrasMostrarCamp()){
            mostrarCampanias.dispose();
            return;
        }
        
        //pulsar buscar campania en ventana mostrar campañas
        if(mostrarCampanias != null && evento.getSource() == mostrarCampanias.getBotonBuscarCamp()){
            buscarCampania = new VentanaBuscarCampania();
            buscarCampania.getBotonConfirmarBuscarCamp().addActionListener(this);
            buscarCampania.getBotonCancelarBuscarCamp().addActionListener(this);
            aux = true;
            buscarCampania.setAlwaysOnTop(true);
            buscarCampania.setVisible(true);
            return;
        }
        
        //pulsar cancelar en ventana buscar campania
        if(buscarCampania != null && evento.getSource() == buscarCampania.getBotonCancelarBuscarCamp()){
            buscarCampania.dispose();
            return;
        }
        
        //pulsar confirmar en ventana buscar campania
        if(aux == true && buscarCampania != null && evento.getSource() == buscarCampania.getBotonConfirmarBuscarCamp()){
            String id = buscarCampania.getLlenadoCampania().getText();
            int idTrans;
            try{
                idTrans = Integer.parseInt(id);
                campania = centro.buscarCampania(idTrans);
                if(campania == null)throw new NotFoundException("Campania "+id+" no encontrada.");
                mostrarCampania = new VentanaMostrarCampanias(campania.getDatosCampania());
                mostrarCampania.getBotonAceptar().addActionListener(this);
                mostrarCampania.setAlwaysOnTop(true);
                mostrarCampania.setVisible(true);

            }catch(NotFoundException e){
                mandarAviso(e.getMessage());
            }catch(NumberFormatException e){
                mandarAviso("La ID debe ser numerica.");
            }
            return;
        }
        
        //pulsar aceptar en ventana mostrar campaña
        if(mostrarCampania != null && evento.getSource() == mostrarCampania.getBotonAceptar()){
            mostrarCampania.dispose();
            buscarCampania.dispose();
            return;
        }
        
        //evento pulsar el boton mostrar donaciones en la ventana mostrar
        if(evento.getSource() == mostrar.getBotonMostrarDonaciones()){
            mostrarDonaciones = new VentanaMostrarDonaciones(centro.datosMostrarDonaciones(false));
            mostrarDonaciones.getBotonAtrasMostrarDonaciones().addActionListener(this);
            mostrarDonaciones.getBotonBuscar().addActionListener(this);
            
            mostrarDonaciones.setAlwaysOnTop(true);
            mostrarDonaciones.setVisible(true);
            return;
        }
        
        //evento pulsar el boton atras en la ventana mostrar donaciones
        if(mostrarDonaciones != null && evento.getSource() == mostrarDonaciones.getBotonAtrasMostrarDonaciones()){
            mostrarDonaciones.dispose();
            return;
        }
        
        //pulsar buscar, ventana buscar donaciones.
        if(mostrarDonaciones != null && evento.getSource() == mostrarDonaciones.getBotonBuscar()){
            buscarCampania = new VentanaBuscarCampania();
            buscarCampania.getBotonConfirmarBuscarCamp().addActionListener(this);
            buscarCampania.getBotonCancelarBuscarCamp().addActionListener(this);
            aux = false;
            buscarCampania.setAlwaysOnTop(true);
            buscarCampania.setVisible(true);
            return;
        }
        
        //pulsar buscar, ventana buscar campania
        if(aux == false && buscarCampania != null && evento.getSource() == buscarCampania.getBotonConfirmarBuscarCamp()){
            String id = buscarCampania.getLlenadoCampania().getText();
            int idTrans;
            try{
                idTrans = Integer.parseInt(id);
                campania = centro.buscarCampania(idTrans);
                if(campania == null)throw new NotFoundException("Campania "+id+" no encontrada.");
                buscarDonacion = new VentanaBuscarDonacion();
                buscarDonacion.getBotonBuscar().addActionListener(this);
                buscarDonacion.getBotonCancelar().addActionListener(this);
                buscarDonacion.setAlwaysOnTop(true);
                buscarDonacion.setVisible(true);

            }catch(NotFoundException e){
                mandarAviso(e.getMessage());
            }catch(NumberFormatException e){
                mandarAviso("La ID debe ser numerica.");
            }
            return;
        }
        
        //pulsar boton cancelar, ventana buscar donacion
        if(buscarDonacion != null && evento.getSource() == buscarDonacion.getBotonCancelar()){
            buscarDonacion.dispose();
            return;
        }
        
        //pulsar boton buscar, ventana buscar donacion
        if(buscarDonacion != null && evento.getSource() == buscarDonacion.getBotonBuscar()){
            String id = buscarDonacion.getLlenadoIdDonacion().getText();
            int idTrans;
            try{
                idTrans = Integer.parseInt(id);
                donacion = campania.buscarDonacion(idTrans);
                if(donacion == null)throw new NotFoundException("Campania "+id+" no encontrada.");
                mostrarDonacion = new VentanaMostrarDonaciones(donacion.getDatosDonacion(idTrans,false));
                mostrarDonacion.getBotonAceptar().addActionListener(this);
                mostrarDonacion.setAlwaysOnTop(true);
                mostrarDonacion.setVisible(true);

            }catch(NotFoundException e){
                mandarAviso(e.getMessage());
            }catch(NumberFormatException e){
                mandarAviso("La ID debe ser numerica.");
            }
            return;
        }
        
        //pulsar aceptar, ventana mostar donacion
        if(mostrarDonacion != null && evento.getSource() == mostrarDonacion.getBotonAceptar()){
            mostrarDonacion.dispose();
            buscarDonacion.dispose();
            buscarCampania.dispose();
            return;
        }
        
        //evento pulsar el boton mostrar inventario de sangre en la ventana mostrar
        if(evento.getSource() == mostrar.getBotonMostrarSangre()){
            
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
        if(evento.getSource() == mostrar.getBotonMostrarPersonas()){
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
            mostrarDonantes.getBotonBuscar().addActionListener(this);
            
            mostrarDonantes.setAlwaysOnTop(true);
            mostrarDonantes.setVisible(true);
            return;
        }
        
        //evento boton atras ventana mostrar donantes
        if(mostrarDonantes != null && evento.getSource() == mostrarDonantes.getBotonAtrasMostrarDonante()){
            mostrarDonantes.dispose();
            return;
        }
        
        //pulsar buscar, ventana mostrar donantes
        if(mostrarDonantes != null && evento.getSource() == mostrarDonantes.getBotonBuscar()){
            buscarPersona = new VentanaBuscarPersona();
            buscarPersona.getBotonBuscarPersona().addActionListener(this);
            buscarPersona.getBotonCancelarBuscarPersona().addActionListener(this);
            flag = 1;
            
            buscarPersona.setAlwaysOnTop(true);
            buscarPersona.setVisible(true);
            return;
        }
        
        //pulsar atras, ventana buscar persona
        if(buscarPersona != null && evento.getSource() == buscarPersona.getBotonCancelarBuscarPersona()){
            buscarPersona.dispose();
            return;
        }
        
        //pulsar buscar, ventana buscar persona
        if(flag == 1 && buscarPersona != null && evento.getSource() == buscarPersona.getBotonBuscarPersona()){
            String rut = buscarPersona.getLlenadoRut().getText();
            persona = centro.buscarPersona(rut, 1);
            if(persona != null){
                mostrarDonante = new VentanaMostrarDonantes(persona.getInfo(false));
                mostrarDonante.getBotonAceptar().addActionListener(this);
                
                mostrarDonante.setAlwaysOnTop(true);
                mostrarDonante.setVisible(true);
            }
            else
                mandarAviso("El rut no existe en el sistema.");
            return;
        }
        
        //pulsar aceptar, ventana mostrar donante
        if(mostrarDonante != null && evento.getSource() == mostrarDonante.getBotonAceptar()){
            mostrarDonante.dispose();
            buscarPersona.dispose();
            return;
        }
        
        //evento boton mostrar flebotomistas ventana mostrar personas
        if(mostrarPersonas != null && evento.getSource() == mostrarPersonas.getBotonMostrarFlebotomistas()){
            mostrarFlebotomistas = new VentanaMostrarFlebotomistas(centro.datosMostrarPersona(2, false));
            mostrarFlebotomistas.getBotonAtrasMostrarFlebotomistas().addActionListener(this);
            mostrarFlebotomistas.getBotonBuscar().addActionListener(this);
            
            mostrarFlebotomistas.setAlwaysOnTop(true);
            mostrarFlebotomistas.setVisible(true);
            return;
        }
        
        //evento boton atras ventana mostrar flebotomistas
        if(mostrarFlebotomistas != null && evento.getSource() == mostrarFlebotomistas.getBotonAtrasMostrarFlebotomistas()){
            mostrarFlebotomistas.dispose();
            return;
        }
        
        //pulsar boton buscar, ventana mostrar flebotomistas
        if(mostrarFlebotomistas != null && evento.getSource() == mostrarFlebotomistas.getBotonBuscar()){
            buscarPersona = new VentanaBuscarPersona();
            buscarPersona.getBotonBuscarPersona().addActionListener(this);
            buscarPersona.getBotonCancelarBuscarPersona().addActionListener(this);
            flag = 2;
            
            buscarPersona.setAlwaysOnTop(true);
            buscarPersona.setVisible(true);
            return;
        }
        
        //pulsar boton buscar, ventana buscar persona
        if(flag == 2 && buscarPersona != null && evento.getSource() == buscarPersona.getBotonBuscarPersona()){
            String rut = buscarPersona.getLlenadoRut().getText();
            persona = centro.buscarPersona(rut, 2);
            if(persona != null){
                mostrarFlebotomista = new VentanaMostrarFlebotomistas(persona.getInfo(false));
                mostrarFlebotomista.getBotonAceptar().addActionListener(this);
                
                mostrarFlebotomista.setAlwaysOnTop(true);
                mostrarFlebotomista.setVisible(true);
            }
            else
                mandarAviso("El rut no existe en el sistema.");
            return;
        }
        
        //pulsar aceptar, ventana mostrar flebotomista
        if(mostrarFlebotomista != null && evento.getSource() == mostrarFlebotomista.getBotonAceptar()){
            mostrarFlebotomista.dispose();
            buscarPersona.dispose();
            return;
        }
        
        //evento boton mostrar todos ventana mostrar personas
        if(mostrarPersonas != null && evento.getSource() == mostrarPersonas.getBotonMostrarTodo()){
            mostrarTodo = new VentanaMostrarTodo(centro.datosMostrarPersona(true));
            mostrarTodo.getBotonAtrasMostrarTodo().addActionListener(this);
            mostrarTodo.getBotonBuscar().addActionListener(this);
            
            mostrarTodo.setAlwaysOnTop(true);
            mostrarTodo.setVisible(true);
            return;
        }
        
        //evento boton atras ventana mostrar todo
        if(mostrarTodo != null && evento.getSource() == mostrarTodo.getBotonAtrasMostrarTodo()){
            mostrarTodo.dispose();
            return;
        }
        
        //pulsar buscar, ventana mostrar todo
        if(mostrarTodo != null && evento.getSource() == mostrarTodo.getBotonBuscar()){
            buscarPersona = new VentanaBuscarPersona();
            buscarPersona.getBotonBuscarPersona().addActionListener(this);
            buscarPersona.getBotonCancelarBuscarPersona().addActionListener(this);
            flag = 3;
            
            buscarPersona.setAlwaysOnTop(true);
            buscarPersona.setVisible(true);
            return;
        }
        
        //pulsar buscar, ventana buscar persona
        if(flag == 3 && buscarPersona != null && evento.getSource() == buscarPersona.getBotonBuscarPersona()){
            String rut = buscarPersona.getLlenadoRut().getText();
            persona = centro.buscarPersona(rut, 1);
            persona2 = centro.buscarPersona(rut, 2);
            
            if(persona == null && persona2 == null)
                mandarAviso("El rut no existe en el sistema.");
            else{
                if(persona == null)
                    mostrarUno = new VentanaMostrarTodo(persona2.getInfo(true));
                else{
                    if(persona2 == null)
                        mostrarUno = new VentanaMostrarTodo(persona.getInfo(true));
                    else
                        mostrarUno = new VentanaMostrarTodo(persona.getInfo(true), persona2.getInfo(true));
                }
                mostrarUno.getBotonAceptar().addActionListener(this);
                
                mostrarUno.setAlwaysOnTop(true);
                mostrarUno.setVisible(true);
            }
                
            return;
        }
        
        //evento aceptar, ventana mostrar uno
        if(mostrarUno != null && evento.getSource() == mostrarUno.getBotonAceptar()){
            mostrarUno.dispose();
            buscarPersona.dispose();
            return;
        }
        
        //evento boton atras ventana mostrar personas
        if(mostrarPersonas != null && evento.getSource() == mostrarPersonas.getBotonAtrasMostrarPersonas()){
            mostrarPersonas.dispose();
            return;
        }
        
        //evento aceptar en aviso
        if(aviso != null && evento.getSource() == aviso.getBotonAceptarAviso()){
            aviso.dispose();
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
