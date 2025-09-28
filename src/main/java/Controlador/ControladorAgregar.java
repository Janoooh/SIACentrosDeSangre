package Controlador;

import Vista.*;
import com.mycompany.siacentrosdesangre.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControladorAgregar implements ActionListener{
    private CentroDeSangre centro;
    private VentanaAgregar agregar;
    private VentanaAgregarCamp agregarCampania;
    private VentanaAgregarDonacion agregarDonacion;
    private VentanaAgregarDonante agregarDonante;
    private VentanaAgregarFlebotomista agregarFlebotomista;
    private VentanaAgregarSangre agregarSangre;
    
    private VentanaBuscarCampania buscarCamp;
    private VentanaAviso aviso;
    private Campania campania;

    
    public void iniciar(CentroDeSangre centro){
        this.centro = centro;
        agregar = new VentanaAgregar();
        agregar.getBotonAgreCampania().addActionListener(this);
        agregar.getBotonAgreDonacion().addActionListener(this);
        agregar.getBotonAgreDonante().addActionListener(this);
        agregar.getBotonAgreFlebo().addActionListener(this);
        agregar.getBotonAgreSangre().addActionListener(this);
        agregar.getBotonAgreAtras().addActionListener(this);
            
        agregar.setAlwaysOnTop(true);
        agregar.setVisible(true);
    }
    
    public void mandarAviso(String msg){
        aviso = new VentanaAviso(msg);
        aviso.getBotonAceptarAviso().addActionListener(this);
        aviso.setAlwaysOnTop(true);
        aviso.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        //evento pulsar el boton agregar campania en la ventana agregar
        if(evento.getSource() == agregar.getBotonAgreCampania()){
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
            int idTrans;

            try {
                idTrans = Integer.parseInt(id);
                centro.agregarCampaniaNueva(idTrans, localidad);
                mandarAviso("La campania ha sido creada exitosamente.");
                agregarCampania.dispose();
            }catch (IOException ex) {
                System.getLogger(ControladorVentanas.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }catch(DataDuplicateException e){
                mandarAviso("Hay una campania con ese id registrado.");
            }catch(NumberFormatException e){
                mandarAviso("La ID debe ser un numero.");
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
        if(evento.getSource() == agregar.getBotonAgreDonacion()){
            buscarCamp = new VentanaBuscarCampania();
            buscarCamp.getBotonConfirmarBuscarCamp().addActionListener(this);
            buscarCamp.getBotonCancelarBuscarCamp().addActionListener(this);
            
            buscarCamp.setAlwaysOnTop(true);
            buscarCamp.setVisible(true);
            return;
        }
        
        //evento pulsar el boton buscar en la ventana buscar campania
        if(buscarCamp != null && evento.getSource() == buscarCamp.getBotonConfirmarBuscarCamp()){
            String id = buscarCamp.getLlenadoCampania().getText();
            int idTrans;
            try{
                idTrans = Integer.parseInt(id);
                campania = centro.buscarCampania(idTrans);
                if(campania == null)throw new NotFoundException("Campania "+id+" no encontrada.");
                agregarDonacion = new VentanaAgregarDonacion();
                agregarDonacion.getBotonConfirmarAgreDonacion().addActionListener(this);
                agregarDonacion.getBotonCancelarAgreDonacion().addActionListener(this);
                agregarDonacion.setAlwaysOnTop(true);
                agregarDonacion.setVisible(true);
            }catch(NotFoundException e){
                mandarAviso(e.getMessage());
            }catch(NumberFormatException e){
                mandarAviso("La ID debe ser numerica.");
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
            int idTrans;
            String id, fecha, rutDonante, rutFlebo;
            
            id = agregarDonacion.getLlenadoId().getText();
            fecha = agregarDonacion.getLlenadoFecha().getText();
            rutDonante = agregarDonacion.getLlenadoRutDonante().getText();
            rutFlebo = agregarDonacion.getLlenadoRutFlebotomista().getText();
            //Donacion aux = new Donacion()
            
            try {
                idTrans = Integer.parseInt(id);
                centro.agregarDonacionACampania(campania, idTrans, fecha,rutDonante,rutFlebo);
                mandarAviso("La donacion se ha agregado exitosamente.");
                agregarDonacion.dispose();
            } catch (IOException ex) {
                System.getLogger(ControladorVentanas.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            } catch (NotFoundException e){
                mandarAviso(e.getMessage());
            } catch (DataDuplicateException e){
                mandarAviso(e.getMessage());
            } catch (NumberFormatException e){
                mandarAviso("La ID debe ser numerica.");
            }
            return;
        }
        
        //evento pulsar el boton cancelar en la ventana agregar donacion
        if(agregarDonacion != null && evento.getSource() == agregarDonacion.getBotonCancelarAgreDonacion()){
            agregarDonacion.dispose();
            return;
        }
        
        //Evento pulsar el boton agregar donante de la ventana agregar.
        if(evento.getSource() == agregar.getBotonAgreDonante()){
            agregarDonante = new VentanaAgregarDonante();
            agregarDonante.getBotonAceptarAgreDonante().addActionListener(this);
            agregarDonante.getBotonCancelarAgreDonante().addActionListener(this);
            
            agregarDonante.setAlwaysOnTop(true);
            agregarDonante.setVisible(true);
            return;
        }
        
        //pulsar agregar , ventana agregar donante
        if(agregarDonante != null && evento.getSource() == agregarDonante.getBotonAceptarAgreDonante()){
            String rut, nombre, telefono, edad, tipoSangre;
            int edadTrans;
            
            rut = agregarDonante.getLlenadoRut().getText();
            nombre = agregarDonante.getLlenadoNombre().getText();
            telefono = agregarDonante.getLlenadoTelefono().getText();
            edad = agregarDonante.getLlenadoEdad().getText();
            tipoSangre = (String)agregarDonante.getLlenadoSangre().getSelectedItem();
            try{
                edadTrans = Integer.parseInt(edad);
                centro.agregarPersona(rut, nombre, telefono, edadTrans, 1, tipoSangre);
                mandarAviso("Persona agregada correctamente.");
            }catch(DataDuplicateException e){
                mandarAviso(e.getMessage());
            }catch(NumberFormatException e){
                mandarAviso("La edad debe ser un numero.");
            }
            return;
        }
        
        //evento pulsar el boton cancelar en la ventana agregar donante
        if(agregarDonante != null && evento.getSource() == agregarDonante.getBotonCancelarAgreDonante()){
            agregarDonante.dispose();
            return;
        }
        
        //pulsar agregar flebotomista ventana principal
        if(evento.getSource() == agregar.getBotonAgreFlebo()){
            agregarFlebotomista = new VentanaAgregarFlebotomista();
            agregarFlebotomista.getBotonAceptarAgreFlebo().addActionListener(this);
            agregarFlebotomista.getBotonCancelarAgreFlebo().addActionListener(this);
            agregarFlebotomista.setAlwaysOnTop(true);
            agregarFlebotomista.setVisible(true);
            return;
        }
        
        //pulsa aceptar, ventana agregar flebotomista
        if(agregarFlebotomista != null && evento.getSource() == agregarFlebotomista.getBotonAceptarAgreFlebo()){
            String rut, nom, tel, edad, especialidad, correo;
            int edadTrans;
            rut = agregarFlebotomista.getLlenadoRut().getText();
            nom = agregarFlebotomista.getLlenadoNombre().getText();
            tel = agregarFlebotomista.getLlenadoTelefono().getText();
            edad = agregarFlebotomista.getLlenadoEdad().getText();
            especialidad = agregarFlebotomista.getLlenadoEspecialidad().getText();
            correo = agregarFlebotomista.getLlenadoCorreo().getText();
            try{
                edadTrans = Integer.parseInt(edad);
                centro.agregarPersona(rut, nom, tel, edadTrans, 2, especialidad, correo);
                mandarAviso("Flebotomista agregado correctamente.");
            }catch(DataDuplicateException e){
                mandarAviso(e.getMessage());
            }catch(NumberFormatException e){
                mandarAviso("La edad debe ser un numero.");
            }
            return;
        }
        
        //pulsa cancelar ventana flebotomista
        if(agregarFlebotomista != null && evento.getSource() == agregarFlebotomista.getBotonCancelarAgreFlebo()){
            agregarFlebotomista.dispose();
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
            try{
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
            }catch(NumberFormatException e){
                mandarAviso("La cantidad debe ser un numero.");
            }
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
            agregar = null;
            return;
        }
    }
}
