package Controlador;

import Vista.*;
import com.mycompany.siacentrosdesangre.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorEliminar implements ActionListener{
    private CentroDeSangre centro;
    private VentanaEliminar eliminar;
    private VentanaEliminarPersona eliminarPersona;
    
    private VentanaBuscarCampania buscarCamp;
    private VentanaBuscarDonacion buscarDonacion;
    private VentanaAviso aviso;
    private Campania campania;
    private boolean flag; //true = eliminar campania ; false = eliminar donacion
    
    public void iniciar(CentroDeSangre centro){
        this.centro = centro;
        eliminar = new VentanaEliminar();
        eliminar.getBotonEliminarCampania().addActionListener(this);
        eliminar.getBotonEliminarDonacion().addActionListener(this);
        eliminar.getBotonEliminarPersona().addActionListener(this);
        eliminar.getBotonAtras().addActionListener(this);
          
        eliminar.setAlwaysOnTop(true);
        eliminar.setVisible(true);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        
        //pulsar boton eliminar campania en la ventana principal
        if(evento.getSource() == eliminar.getBotonEliminarCampania()){
            buscarCamp = new VentanaBuscarCampania();
            buscarCamp.getBotonConfirmarBuscarCamp().addActionListener(this);
            buscarCamp.getBotonCancelarBuscarCamp().addActionListener(this);
            flag = true;// el true es si se usa la ventana para el eliminar campania
            
            buscarCamp.setAlwaysOnTop(true);
            buscarCamp.setVisible(true);
            return;
        }
        
        //En la ventana buscar campania(de parte del eliminar campania) se pulsa confirmar
        if(flag == true && buscarCamp != null && evento.getSource() == buscarCamp.getBotonConfirmarBuscarCamp()){
            String id = buscarCamp.getLlenadoCampania().getText();
            int idTrans;
            try{
                idTrans = Integer.parseInt(id);
                centro.borrarCampania(idTrans);
                aviso = ControladorVentanas.mandarAviso("La campania ha sido eliminada exitosamente.",this);
                buscarCamp.dispose();
            }catch(NotFoundException e){
                aviso = ControladorVentanas.mandarAviso(e.getMessage(),this);
            }catch(NumberFormatException e){
                aviso = ControladorVentanas.mandarAviso("La ID debe ser numerica.",this);
            }catch(Exception e){
                System.out.println("ERROR :"+e.getMessage());
                System.exit(1);
            }
            return;
        }
        
        //ventana buscar campania pulsa cancelar
        if(buscarCamp != null && evento.getSource() == buscarCamp.getBotonCancelarBuscarCamp()){
            buscarCamp.dispose();
            return;
        }
        
        //pulsar boton eliminar donacion en la ventana principal
        if(evento.getSource() == eliminar.getBotonEliminarDonacion()){
            buscarCamp = new VentanaBuscarCampania();
            buscarCamp.getBotonConfirmarBuscarCamp().addActionListener(this);
            buscarCamp.getBotonCancelarBuscarCamp().addActionListener(this);
            flag = false; //el false es que se usa en el eliminar una donacion
            
            buscarCamp.setAlwaysOnTop(true);
            buscarCamp.setVisible(true);
            return;
        }
        
        //En la ventana buscar campania(de parte del eliminar donacion) se pulsa confirmar
        if(flag == false && buscarCamp != null && evento.getSource() == buscarCamp.getBotonConfirmarBuscarCamp()){
            String id = buscarCamp.getLlenadoCampania().getText();
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
                aviso = ControladorVentanas.mandarAviso(e.getMessage(),this);
            }catch(NumberFormatException e){
                aviso = ControladorVentanas.mandarAviso("La ID debe ser numerica.",this);
            }catch(Exception e){
                System.out.println("ERROR :"+e.getMessage());
                System.exit(1);
            }
            return;
        }
        
        //ventana buscar donacion pulsar buscar
        if(buscarDonacion != null && evento.getSource() == buscarDonacion.getBotonBuscar()){
            String id = buscarDonacion.getLlenadoIdDonacion().getText();
            int idTrans;
            try{
                idTrans = Integer.parseInt(id);
                centro.borrarDonacionDeCampania(idTrans, campania.getId());
                aviso = ControladorVentanas.mandarAviso("La donacion se eliminó correctamente.",this);
                buscarDonacion.dispose();
                buscarCamp.dispose();
            }catch(NotFoundException e){
                aviso = ControladorVentanas.mandarAviso(e.getMessage(),this);
            }catch(NumberFormatException e){
                aviso = ControladorVentanas.mandarAviso("La ID debe ser numerica.",this);
            }catch(Exception e){
                System.out.println("ERROR :"+e.getMessage());
                System.exit(1);
            }
            return;
        }
        
        //ventana buscar donacion, pulsa cancelar
        if(buscarDonacion != null && evento.getSource() == buscarDonacion.getBotonCancelar()){
            buscarDonacion.dispose();
            return;
        }
        
        //pulsar el boton eliminar persona en la ventana principal
        if(evento.getSource() == eliminar.getBotonEliminarPersona()){
            eliminarPersona = new VentanaEliminarPersona();
            eliminarPersona.getBotonEliminar().addActionListener(this);
            eliminarPersona.getBotonCancelar().addActionListener(this);
            
            eliminarPersona.setAlwaysOnTop(true);
            eliminarPersona.setVisible(true);
            return;
        }
        
        //en el menu eliminar persona se pulsa eliminar
        if(eliminarPersona != null && evento.getSource() == eliminarPersona.getBotonEliminar()){
            Persona eliminado = null;
            String rut = eliminarPersona.getLlenadoRut().getText();
            String opcion = (String)eliminarPersona.getOpciones().getSelectedItem();
            try{
                if(opcion.equals("Donante"))
                    eliminado = centro.borrarPersona(rut, 1);
                else
                    eliminado = centro.borrarPersona(rut, 2);
            }catch(NotFoundException e){
                aviso = ControladorVentanas.mandarAviso(e.getMessage(),this);
            }catch(Exception e){
                System.out.println("ERROR :"+e.getMessage());
                System.exit(1);
            }
            
            if(eliminado != null){
                aviso = ControladorVentanas.mandarAviso("El " + opcion + " fue eliminado exitosamente.",this);
                eliminarPersona.dispose();
            }
            return;
        }
        
        //en el menu eliminar persona se pulsa cancelar
        if(eliminarPersona != null && evento.getSource() == eliminarPersona.getBotonCancelar()){
            eliminarPersona.dispose();
            return;
        }
        
        //pulsar aceptar en el aviso
        if(aviso != null && evento.getSource() == aviso.getBotonAceptarAviso()){
            aviso.dispose();
            return;
        }
        
        //pulsar el boton atras en la ventana principal
        if(evento.getSource() == eliminar.getBotonAtras()){
            eliminar.dispose();
            eliminar = null;
            return;
        }
    }
}
