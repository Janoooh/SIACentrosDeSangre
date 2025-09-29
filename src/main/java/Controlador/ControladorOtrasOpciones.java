package Controlador;

import Vista.*;
import com.mycompany.siacentrosdesangre.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;

public class ControladorOtrasOpciones implements ActionListener {
    private CentroDeSangre centro;
    private VentanaOtrasOpciones opciones;
    private VentanaCampaniaSobreUmbral campSobreUmbral;
    private VentanaMostrarCampanias mostrarCampania;
    private VentanaAviso aviso;
    
    public void iniciar(CentroDeSangre centro){
        this.centro = centro;
        opciones = new VentanaOtrasOpciones();
        opciones.getBotonObtenerCampaniasUmbral().addActionListener(this);
        opciones.getBotonGenerarReporteCsv().addActionListener(this);
        opciones.getBotonOtrasOpcionesVolver().addActionListener(this);
        
        opciones.setAlwaysOnTop(true);
        opciones.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getSource() == opciones.getBotonObtenerCampaniasUmbral()){
            campSobreUmbral = new VentanaCampaniaSobreUmbral();
            campSobreUmbral.getBotonUmbralMostrar().addActionListener(this);
            campSobreUmbral.getBotonUmbralVolver().addActionListener(this);
            
            campSobreUmbral.setAlwaysOnTop(true);
            campSobreUmbral.setVisible(true);
            return;
        }
        
        if(evento.getSource() == opciones.getBotonGenerarReporteCsv()){
            centro.generarReporteCsv("reporteEstadistico.csv");
            aviso = ControladorVentanas.mandarAviso("Reporte generado, visualizar en la carpeta inicial del programa.",this);
            return;
        }
        
        //Boton para volver atras, en ventana opciones.
        if(evento.getSource() == opciones.getBotonOtrasOpcionesVolver()){
            opciones.dispose();
            opciones = null;
            return;
        }
        //Boton mostrar en campania sobre umbral.
        if(campSobreUmbral != null && evento.getSource() == campSobreUmbral.getBotonUmbralMostrar()){
            int umbral;
            String[][] datosCampUmbral;
            try{
                umbral = Integer.parseInt(campSobreUmbral.getLlenadoUmbral().getText());
                datosCampUmbral = centro.campaniasSobreUmbral(umbral);
                mostrarCampania = new VentanaMostrarCampanias(datosCampUmbral,false);
                mostrarCampania.getBotonAtrasMostrarCamp().addActionListener(this);
                
                mostrarCampania.setAlwaysOnTop(true);
                mostrarCampania.setVisible(true);
                
            }catch(NumberFormatException e){
                aviso = ControladorVentanas.mandarAviso("El umbral debe ser un numero.", this);
            }catch(Exception e){
                System.out.println("ERROR :"+e.getMessage());
            }
        }
        
        if(campSobreUmbral != null && evento.getSource() == campSobreUmbral.getBotonUmbralVolver()){
            campSobreUmbral.dispose();
            return;
        }
        
        //Boton aceptar aviso del aviso.
        if(aviso != null && evento.getSource() == aviso.getBotonAceptarAviso()){
            aviso.dispose();
            aviso = null;
            return;
        }
        
        if(mostrarCampania != null && evento.getSource() == mostrarCampania.getBotonAtrasMostrarCamp()){
            mostrarCampania.dispose();
            return;
        }
    }
}
