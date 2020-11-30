/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.AtividadesDAO;
import DAO.MateriaDAO;
import Model.Atividades;
import Model.Materia;
import java.awt.Color;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.plaf.metal.MetalBorders;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;

/**
 *
 * @author felip
 */
public class CadastroAtividade extends javax.swing.JFrame {
    
    List<Materia> lista= new ArrayList<>();
    boolean salvar= true;
    int id;
    DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate date= LocalDate.now();
    
    
    public CadastroAtividade() {
        initComponents();
        setLocationRelativeTo(null);
        salvar= true;
        txData.setText(date.format(formatter));
        cbMateria.removeAllItems();
        cbMateria.addItem("SELECIONE A MATERIA");
        
        preencheCombo();
        setResizable(false);

    }
    
    public CadastroAtividade(Atividades atv){

        initComponents();
        setLocationRelativeTo(null);
        salvar= false; 
        preencheCombo();
        id= atv.getId();               
        preencheCampos(atv);
        setResizable(false);//txData.setText(date.format(formatter));
    }
    
    public void preencheCombo(){
        
            
        try{
            cbMateria.removeAllItems();
            cbMateria.addItem("SELECIONE A MATERIA");
            
            lista= MateriaDAO.pesquisarCombo();
            
            for(Materia at: lista){
                cbMateria.addItem(at.getNome());
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void preencheCampos(Atividades ativ){
        txTitulo.setText(ativ.getTitulo());
        txDesc.setText(ativ.getDesc());
        txData.setText(date.format(formatter));
        txData.setText(ativ.getData());
        cbMateria.setSelectedItem(ativ.getMateria().getNome());
        
    }
    
    public Atividades retornaObjeto(){
        Atividades atv= new Atividades();
        atv.setTitulo(txTitulo.getText());
        atv.setDesc(txDesc.getText());
        Materia materiaSelec= lista.get(cbMateria.getSelectedIndex()-1);
        atv.setMateria(materiaSelec);
        atv.setId(id);
        atv.setData(txData.getText());
        
        return atv;
    }
    
    public boolean validaCampos(){
        boolean check= true;
        
        txTitulo.setText(txTitulo.getText().trim());
                
        if(txTitulo.getText().equals("")){
            
            //ele ira validar se o tx esta preenchido
            check = false;
            
            //deixa a borda vermelha se nao preenchido nada
            txTitulo.setBorder(BorderFactory.createLineBorder(Color.red));
            JOptionPane.showMessageDialog(null,"Preencher Campos Obrigatorios");
        }else {
            
             txTitulo.setBorder(MetalBorders.getTextFieldBorder());
        }
        
        if(txData.getText().equals("")){
            
            //ele ira validar se o tx esta preenchido
            check = false;
            
            //deixa a borda vermelha se nao preenchido nada
            txData.setBorder(BorderFactory.createLineBorder(Color.red));
            JOptionPane.showMessageDialog(null,"Preencher Campos Obrigatorios");
        }else {
            
             txData.setBorder(MetalBorders.getTextFieldBorder());
        }
        
        if(txDesc.getText().equals("")){
            
            //ele ira validar se o tx esta preenchido
            check = false;
            
            //deixa a borda vermelha se nao preenchido nada
            txDesc.setBorder(BorderFactory.createLineBorder(Color.red));
            JOptionPane.showMessageDialog(null,"Preencher Campos Obrigatorios");
        }else {
            
             txDesc.setBorder(MetalBorders.getTextFieldBorder());
        }
        
        if(cbMateria.getSelectedIndex() == 0){
            check= false;
            cbMateria.setBorder(BorderFactory.createLineBorder(Color.red));
            JOptionPane.showMessageDialog(null,"Preencher Campos Obrigatorios");
        }else{
            cbMateria.setBorder(MetalBorders.getTextFieldBorder());
        }
        
        return check;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txTitulo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txData = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txDesc = new javax.swing.JTextArea();
        btnSave = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        cbMateria = new javax.swing.JComboBox<>();
        lblAdd = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Atividades");
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jPanel1.setBackground(new java.awt.Color(240, 240, 240));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setText("Cadastro de Atividades");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Título");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Matéria");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Data prevista:");

        try {
            txData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txDataActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setText("Descrição da Atividade");

        txDesc.setColumns(20);
        txDesc.setLineWrap(true);
        txDesc.setRows(5);
        jScrollPane1.setViewportView(txDesc);

        btnSave.setBackground(new java.awt.Color(240, 240, 240));
        btnSave.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/disk.png"))); // NOI18N
        btnSave.setText("Salvar");
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnSaveMouseReleased(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(240, 240, 240));
        btnCancelar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/cancel.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCancelarMouseReleased(evt);
            }
        });

        lblAdd.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        lblAdd.setText(" +");
        lblAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblAddMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jSeparator1))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel2))
                    .addComponent(txTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbMateria, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txData, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbMateria, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txData, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblAddMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAddMouseReleased
        new CadastroMaterias().setVisible(true);
    }//GEN-LAST:event_lblAddMouseReleased

    private void btnCancelarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseReleased
        this.dispose();
    }//GEN-LAST:event_btnCancelarMouseReleased

    private void btnSaveMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseReleased
        if(validaCampos()){
            if(salvar){
                try {
                    AtividadesDAO.insertDB(retornaObjeto());
                    txData.setText("");
                    txDesc.setText("");
                    txTitulo.setText("");
                    JOptionPane.showMessageDialog(null, "Dados registrados com sucesso!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }else{
                try {
                    AtividadesDAO.updateDB(retornaObjeto());
                    this.dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_btnSaveMouseReleased

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        preencheCombo();
    }//GEN-LAST:event_formWindowGainedFocus

    private void txDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txDataActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastroAtividade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroAtividade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroAtividade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroAtividade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroAtividade().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cbMateria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAdd;
    private javax.swing.JFormattedTextField txData;
    private javax.swing.JTextArea txDesc;
    private javax.swing.JTextField txTitulo;
    // End of variables declaration//GEN-END:variables
}
