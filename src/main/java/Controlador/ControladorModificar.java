package Controlador;

import Vista.*;
import com.mycompany.siacentrosdesangre.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControladorModificar implements ActionListener{
    private CentroDeSangre centro;
    private VentanaModificar modificar;
    private VentanaModificarCampania modificarCampania;
    private VentanaModificarDonante modificarDonante;
    private VentanaModificarFlebotomista modificarFlebotomista;
    
    private VentanaBuscarCampania buscarCamp;
    private VentanaBuscarPersona buscarPersona;
    private VentanaAviso aviso;
    private Campania campania;
    private Persona persona;
    private boolean flag;//true = modificar donante ; false = modificar flebotomista
    
    
    public void iniciar(CentroDeSangre centro){
        this.centro = centro;
        modificar = new VentanaModificar();
        modificar.getBotonModificarCampania().addActionListener(this);
        modificar.getBotonModificarDonante().addActionListener(this);
        modificar.getBotonModificarFlebotomista().addActionListener(this);
        modificar.getBotonAtrasModificar().addActionListener(this);
            
        modificar.setAlwaysOnTop(true);
        modificar.setVisible(true);
    }
    
    public void mandarAviso(String msg){
        aviso = new VentanaAviso(msg);
        aviso.getBotonAceptarAviso().addActionListener(this);
        aviso.setAlwaysOnTop(true);
        aviso.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        
        //ventana principal pulsar modificar campania
        if(evento.getSource() == modificar.getBotonModificarCampania()){
            buscarCamp = new VentanaBuscarCampania();
            buscarCamp.getBotonConfirmarBuscarCamp().addActionListener(this);
            buscarCamp.getBotonCancelarBuscarCamp().addActionListener(this);
            
            buscarCamp.setAlwaysOnTop(true);
            buscarCamp.setVisible(true);
            return;
        }
        
        //pulsar el boton cancelar en la ventana buscar campania
        if(buscarCamp != null && evento.getSource() == buscarCamp.getBotonCancelarBuscarCamp()){
            buscarCamp.dispose();
            return;
        }
        
        //pulsar el boton aceptar en la ventana buscar campania
        if(buscarCamp != null && evento.getSource() == buscarCamp.getBotonConfirmarBuscarCamp()){
            String id = buscarCamp.getLlenadoCampania().getText();
            int idTrans;
            try{
                idTrans = Integer.parseInt(id);
                campania = centro.buscarCampania(idTrans);
                if(campania == null)throw new NotFoundException("Campania "+id+" no encontrada.");
                modificarCampania = new VentanaModificarCampania(Integer.toString(campania.getId()), campania.getLocalidad());
                modificarCampania.getBotonAceptar().addActionListener(this);
                modificarCampania.getBotonAtras().addActionListener(this);
                modificarCampania.setAlwaysOnTop(true);
                modificarCampania.setVisible(true);
            }catch(NotFoundException e){
                mandarAviso(e.getMessage());
            }catch(NumberFormatException e){
                mandarAviso("La ID debe ser numerica.");
            }
            return;
        }
        
        //pulsar atras ventana modificar campania
        if(modificarCampania != null && evento.getSource() == modificarCampania.getBotonAtras()){
            modificarCampania.dispose();
            return;
        }
        
        //pulsar aceptar ventana modificar campania
        if(modificarCampania != null && evento.getSource() == modificarCampania.getBotonAceptar()){
            String id = modificarCampania.getLlenadoid().getText();
            String localidad = modificarCampania.getLlenadoLocalidad().getText();
            int idTrans;
            try{
                idTrans = Integer.parseInt(id);
                if(campania.getId() == idTrans)
                    campania.setLocalidad(localidad);
                else{
                    if(centro.buscarCampania(idTrans) == null){
                        campania.setId(idTrans);
                        campania.setLocalidad(localidad);
                    }
                    else{
                        mandarAviso("Esa id ya existe en el sistema.");
                        return;
                    }
                }
                mandarAviso("Campañia modificada correctamente.");
            }catch(NumberFormatException e){
                mandarAviso("La ID debe ser un numero.");
                return;
            }
            
            modificarCampania.dispose();
            buscarCamp.dispose();
            return;
        }
        
        //pulsar boton modificar donante ventana principal
        if(evento.getSource() == modificar.getBotonModificarDonante()){
            buscarPersona = new VentanaBuscarPersona();
            buscarPersona.getBotonBuscarPersona().addActionListener(this);
            buscarPersona.getBotonCancelarBuscarPersona().addActionListener(this);
            flag = true;
            
            buscarPersona.setAlwaysOnTop(true);
            buscarPersona.setVisible(true);
            return;
        }
        
        //pulsar buscar, ventana buscar persona(para modificar donante)
        if(flag == true && buscarPersona != null && evento.getSource() == buscarPersona.getBotonBuscarPersona()){
            String rut = buscarPersona.getLlenadoRut().getText();
            persona = centro.buscarPersona(rut, 1);
            if(persona != null){
                modificarDonante = new VentanaModificarDonante(((Donante)persona).getInfo(false));
                modificarDonante.getBotonAceptar().addActionListener(this);
                modificarDonante.getBotonCancelar().addActionListener(this);
                
                modificarDonante.setAlwaysOnTop(true);
                modificarDonante.setVisible(true);
            }
            else
                mandarAviso("El rut no existe en el sistema.");
            return;
        }
        
        //pulsar aceptar en ventana modificar donante
        if(modificarDonante != null && evento.getSource() == modificarDonante.getBotonAceptar()){
            String rut = modificarDonante.getLlenadoRut().getText();
            String nombre = modificarDonante.getLlenadoNombre().getText();
            String telefono = modificarDonante.getLlenadoTelefono().getText();
            String edad = modificarDonante.getLlenadoEdad().getText();
            String tipoSangre= (String)modificarDonante.getLlenadoTipoSangre().getSelectedItem();
            int edadTrans;
            
            try{
                edadTrans = Integer.parseInt(edad);
                if(persona.getRut().equals(rut)){
                    persona.setNombre(nombre);
                    persona.setTelefono(telefono);
                    persona.setEdad(edadTrans);
                    ((Donante)persona).setTipoSangre(tipoSangre);
                }
                else{
                    if(centro.buscarPersona(rut, 1) == null){
                        persona.setRut(rut);
                        persona.setNombre(nombre);
                        persona.setTelefono(telefono);
                        persona.setEdad(edadTrans);
                        ((Donante)persona).setTipoSangre(tipoSangre);
                    }
                    else{
                        mandarAviso("Ese rut ya se encuentra en el sistema.");
                        return;
                    }
                }
                mandarAviso("Donante modificado correctamente.");

            }catch(NumberFormatException e){
                mandarAviso("La edad debe ser un numero.");
            }
            
            //falta verificar si todos estan bien, no se que hacer con el tipo de sangre
            modificarDonante.dispose();
            buscarPersona.dispose();
            return;
        }
        
        //pulsar cancelar, ventana modificar donante
        if(modificarDonante != null && evento.getSource() == modificarDonante.getBotonCancelar()){
            modificarDonante.dispose();
            return;
        }
        
        //pulsar cancelar ventana buscar persona
        if(buscarPersona != null && evento.getSource() == buscarPersona.getBotonCancelarBuscarPersona()){
            buscarPersona.dispose();
            return;
        }
        
        //pulsar modificar flebotomista, ventana principal
        if(evento.getSource() == modificar.getBotonModificarFlebotomista()){
            buscarPersona = new VentanaBuscarPersona();
            buscarPersona.getBotonBuscarPersona().addActionListener(this);
            buscarPersona.getBotonCancelarBuscarPersona().addActionListener(this);
            flag = false;
            
            buscarPersona.setAlwaysOnTop(true);
            buscarPersona.setVisible(true);
            return;
        }
        
        //pulsar buscar, ventana buscar persona
        if(flag == false && buscarPersona != null && evento.getSource() == buscarPersona.getBotonBuscarPersona()){
            String rut = buscarPersona.getLlenadoRut().getText();
            persona = centro.buscarPersona(rut, 2);
            if(persona != null){
                modificarFlebotomista = new VentanaModificarFlebotomista(((Flebotomista)persona).getInfo(false));
                modificarFlebotomista.getBotonAceptar().addActionListener(this);
                modificarFlebotomista.getBotonCancelar().addActionListener(this);
                
                modificarFlebotomista.setAlwaysOnTop(true);
                modificarFlebotomista.setVisible(true);
            }
            else
                mandarAviso("El rut no existe en el sistema.");
            return;
        }
        
        //pulsar aceptar, ventana modificar flebotomista
        if(modificarFlebotomista != null && evento.getSource() == modificarFlebotomista.getBotonAceptar()){
            String rut = modificarFlebotomista.getLlenadoRut().getText();
            String nombre = modificarFlebotomista.getLlenadoNombre().getText();
            String telefono = modificarFlebotomista.getLlenadoTelefono().getText();
            String edad = modificarFlebotomista.getLlenadoEdad().getText();
            String especialidad= modificarFlebotomista.getLlenadoEspecialidad().getText();
            String correo = modificarFlebotomista.getLlenadoCorreo().getText();
            int edadTrans;
            
            try{
                edadTrans = Integer.parseInt(edad);
                if(persona.getRut().equals(rut)){
                    persona.setNombre(nombre);
                    persona.setTelefono(telefono);
                    persona.setEdad(edadTrans);
                    ((Flebotomista)persona).setEspecialidad(especialidad);
                    ((Flebotomista)persona).setCorreo(correo);
                }
                else{
                    if(centro.buscarPersona(rut, 1) == null){
                        persona.setRut(rut);
                        persona.setNombre(nombre);
                        persona.setTelefono(telefono);
                        persona.setEdad(edadTrans);
                        ((Flebotomista)persona).setEspecialidad(especialidad);
                        ((Flebotomista)persona).setCorreo(correo);
                    }
                    else{
                        mandarAviso("Ese rut ya se encuentra en el sistema.");
                        return;
                    }
                }
                mandarAviso("Flebotomista modificado correctamente.");

            }catch(NumberFormatException e){
                mandarAviso("La edad debe ser un numero.");
            }
            //En este tambien faltan ver las verificaciones
            
            modificarFlebotomista.dispose();
            buscarPersona.dispose();
            return;
        }
        
        //pulsar cancelar ventana modificar flebotomista
        if(modificarFlebotomista != null && evento.getSource() == modificarFlebotomista.getBotonCancelar()){
            modificarFlebotomista.dispose();
            return;
        }
        
        //pulsar aceptar en la ventana de aviso
        if(aviso != null && evento.getSource() == aviso.getBotonAceptarAviso()){
            aviso.dispose();
            return;
        }
        
        //pulsar atras en el menu principal
        if(modificar != null && evento.getSource() == modificar.getBotonAtrasModificar()){
            modificar.dispose();
            return;
        }
    }
}
