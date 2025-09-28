package Controlador;

import Vista.*;
import com.mycompany.siacentrosdesangre.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class ControladorOtrasOpciones implements ActionListener {
    private CentroDeSangre centro;
    private VentanaOtrasOpciones Opciones;
    
    public void iniciar(CentroDeSangre centro){
        this.centro = centro;
        Opciones = new VentanaOtrasOpciones();
        Opciones.setAlwaysOnTop(true);
        Opciones.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        
    }
}
