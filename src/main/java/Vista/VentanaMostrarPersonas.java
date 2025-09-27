package Vista;

import javax.swing.JButton;

public class VentanaMostrarPersonas extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaMostrarPersonas.class.getName());

    public VentanaMostrarPersonas() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonMostrarDonantes = new javax.swing.JButton();
        botonMostrarFlebotomistas = new javax.swing.JButton();
        botonMostrarTodo = new javax.swing.JButton();
        botonAtrasMostrarPersonas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        botonMostrarDonantes.setText("Mostrar Donantes");

        botonMostrarFlebotomistas.setText("Mostrar flebotomistas");

        botonMostrarTodo.setText("Mostrar todo");

        botonAtrasMostrarPersonas.setText("Atrás");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonAtrasMostrarPersonas))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(botonMostrarFlebotomistas)
                        .addGap(0, 121, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(botonMostrarDonantes)
                .addGap(137, 137, 137))
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(botonMostrarTodo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(botonMostrarDonantes)
                .addGap(18, 18, 18)
                .addComponent(botonMostrarFlebotomistas)
                .addGap(18, 18, 18)
                .addComponent(botonMostrarTodo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addComponent(botonAtrasMostrarPersonas)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }
    
    public JButton getBotonMostrarDonantes(){
        return botonMostrarDonantes;
    }
    
    public JButton getBotonMostrarFlebotomistas(){
        return botonMostrarFlebotomistas;
    }
    
    public JButton getBotonMostrarTodo(){
        return botonMostrarTodo;
    }
    
    public JButton getBotonAtrasMostrarPersonas(){
        return botonAtrasMostrarPersonas;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAtrasMostrarPersonas;
    private javax.swing.JButton botonMostrarDonantes;
    private javax.swing.JButton botonMostrarFlebotomistas;
    private javax.swing.JButton botonMostrarTodo;
    // End of variables declaration//GEN-END:variables
}
