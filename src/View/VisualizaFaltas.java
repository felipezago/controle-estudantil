/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.MateriaDAO;
import Model.Materia;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author felip
 */
public class VisualizaFaltas extends javax.swing.JFrame {
    
    static List<Materia> listaCombo= new ArrayList<>();
    static List<Materia> lista= new ArrayList<>();
    static List<Materia> listaQuant= new ArrayList<>();
    
    public VisualizaFaltas() {
        initComponents();
                
        setLocationRelativeTo(null);
        setResizable(false);
        cbMat.setVisible(false);
        rdQtAtual.setVisible(false);
        rdQtMax.setVisible(false);
        rdMat.setVisible(false);
        atualizaTabela();
        preencheCombo();
        lblVolta.setVisible(true);
        validaQuant();
        lblOk.setVisible(false);
    }
    
    public void valida(){
        int aux= 0;
        try {
            lista= MateriaDAO.selectDB();
            
            while(aux != tbFaltas.getRowCount()){
                
                aux++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
            
        
    }
    
    public void validaLinha(){
        if(tbFaltas.getSelectedRowCount()== 0){
            lblAdd.setEnabled(false);
            lblRemover.setEnabled(false);
        }else{
            
        }
    }
    
    public static void validaQuant(){
        int quant = 0, max = 0, atual= 0;

        try {
            listaQuant = MateriaDAO.selectDB();
            int auxiliar = 0;

            while (auxiliar != tbFaltas.getRowCount()) {
                Materia mat = lista.get(auxiliar);
                max= lista.get(auxiliar).getQtd_max_faltas();
                atual= lista.get(auxiliar).getQtd_atual_faltas();
                quant= max - atual;
                
                    if(quant < 10 && quant > 0){
                        JOptionPane.showMessageDialog(null, "<html><font size= 5 font color=red face=arial><b>CUIDADO, FALTAM "
                                + "<html><font color=blue face=arial><b>"+quant+" "
                        + " <html><font color=red face=arial><b>FALTAS PARA REPROVAR NA MATÉRIA: <html><font color=blue face=arial><b>"
                                + "'' " + mat.getNome().toUpperCase() + " ''",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
                    }else if(atual > max){
                       
                        JOptionPane.showMessageDialog(null, "<html><font size= 5 font color=red face=arial><b>VOCÊ JÁ ESTA REPROVADO "
                                + "POR FALTAS"
                        + " <html><font color=red face=arial><b>NA MATÉRIA: <html><font color=blue face=arial><b>"
                                + " " + mat.getNome().toUpperCase()+ "",
                        "Aviso", JOptionPane.WARNING_MESSAGE);                 
                    }
                    
                auxiliar++;
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
    
    public void atualizaTabela(){
        try {
            lista= MateriaDAO.selectDB();
            
            DefaultTableModel model= (DefaultTableModel) tbFaltas.getModel();
            model.setNumRows(0);
            
            
            for(Materia mat: lista){
                    
                model.addRow(new Object[]{

                    mat.getNome(),
                    mat.getQtd_max_faltas(),
                    mat.getQtd_atual_faltas()
                });
            }       

            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void atualizaTabelaPesq(String sql){
        try {
            lista= MateriaDAO.pesquisa(sql);
            
            DefaultTableModel model= (DefaultTableModel) tbFaltas.getModel();
            model.setNumRows(0);
            
            
            for(Materia mat: lista){
                    
                model.addRow(new Object[]{

                    mat.getNome(),
                    mat.getQtd_max_faltas(),
                    mat.getQtd_atual_faltas()
                });
            }       

            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void atualizaTabelaMat(){
        try {
            lista= MateriaDAO.ordenarMat();
            
            DefaultTableModel model= (DefaultTableModel) tbFaltas.getModel();
            model.setNumRows(0);
            
            
            for(Materia mat: lista){
                    
                model.addRow(new Object[]{

                    mat.getNome(),
                    mat.getQtd_max_faltas(),
                    mat.getQtd_atual_faltas()
                });
            }       

            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void atualizaTabelaQtMax(){
        try {
            lista= MateriaDAO.ordenarQtMax();
            
            DefaultTableModel model= (DefaultTableModel) tbFaltas.getModel();
            model.setNumRows(0);
            
            
            for(Materia mat: lista){
                    
                model.addRow(new Object[]{

                    mat.getNome(),
                    mat.getQtd_max_faltas(),
                    mat.getQtd_atual_faltas()
                });
            }       

            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void atualizaTabelaQtAtual(){
        try {
            lista= MateriaDAO.ordenarQtAtual();
            
            DefaultTableModel model= (DefaultTableModel) tbFaltas.getModel();
            model.setNumRows(0);
            
            
            for(Materia mat: lista){
                    
                model.addRow(new Object[]{

                    mat.getNome(),
                    mat.getQtd_max_faltas(),
                    mat.getQtd_atual_faltas()
                });
            }       

            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void preencheCombo(){
        try {
            listaCombo= MateriaDAO.pesquisarComboFaltas();
            for(Materia mat: listaCombo){
                cbMat.addItem(mat.getNome());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbFaltas = new javax.swing.JTable();
        lblAdd = new javax.swing.JLabel();
        lblRemover = new javax.swing.JLabel();
        lblOrder = new javax.swing.JLabel();
        lblPesq = new javax.swing.JLabel();
        lblVolta = new javax.swing.JLabel();
        cbMat = new javax.swing.JComboBox<>();
        rdMat = new javax.swing.JRadioButton();
        rdQtMax = new javax.swing.JRadioButton();
        rdQtAtual = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblOk = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Visualiza Faltas");
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jPanel2.setBackground(new java.awt.Color(240, 240, 240));
        jPanel2.setForeground(new java.awt.Color(204, 0, 0));

        tbFaltas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matéria", "Qtd. Max. Faltas", "Qtd. Atual Faltas"
            }
        ));
        tbFaltas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbFaltasMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbFaltas);

        lblAdd.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblAdd.setForeground(new java.awt.Color(0, 51, 102));
        lblAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/add.png"))); // NOI18N
        lblAdd.setText("Adicionar Faltas");
        lblAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblAddMouseReleased(evt);
            }
        });

        lblRemover.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblRemover.setForeground(new java.awt.Color(0, 51, 102));
        lblRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/delete.png"))); // NOI18N
        lblRemover.setText("Remover Faltas");
        lblRemover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblRemoverMouseReleased(evt);
            }
        });

        lblOrder.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblOrder.setForeground(new java.awt.Color(0, 51, 102));
        lblOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/sort-from-a-to-z-in-alphabetical-ascending-order - Copia.png"))); // NOI18N
        lblOrder.setText("Ordenar");
        lblOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblOrderMouseReleased(evt);
            }
        });

        lblPesq.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPesq.setForeground(new java.awt.Color(0, 51, 102));
        lblPesq.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/magnifier.png"))); // NOI18N
        lblPesq.setText("Pesquisar");
        lblPesq.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPesqMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblPesqMouseReleased(evt);
            }
        });

        lblVolta.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblVolta.setForeground(new java.awt.Color(0, 51, 102));
        lblVolta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/arrow_rotate_clockwise.png"))); // NOI18N
        lblVolta.setText("Voltar");
        lblVolta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblVoltaMouseReleased(evt);
            }
        });

        cbMat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODOS" }));
        cbMat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cbMatFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbMatFocusLost(evt);
            }
        });
        cbMat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cbMatMouseReleased(evt);
            }
        });

        buttonGroup1.add(rdMat);
        rdMat.setText("Ordenar por Matéria");
        rdMat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rdMatMouseReleased(evt);
            }
        });

        buttonGroup1.add(rdQtMax);
        rdQtMax.setText("Ordernar por Qt. max.");
        rdQtMax.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rdQtMaxMouseReleased(evt);
            }
        });

        rdQtAtual.setText("Order por Qt. Atual");
        rdQtAtual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rdQtAtualMouseReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("Selecione uma linha da tabela ");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("para adicionar/remover faltas");

        lblOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/checked (1).png"))); // NOI18N
        lblOk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblOkMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 756, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblOrder)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cbMat, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblOk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(rdMat)
                            .addComponent(rdQtMax)
                            .addComponent(rdQtAtual)
                            .addComponent(lblAdd)
                            .addComponent(lblRemover)
                            .addComponent(lblPesq)
                            .addComponent(jLabel3))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lblVolta, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addContainerGap())))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblAdd)
                .addGap(18, 18, 18)
                .addComponent(lblRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblOrder)
                .addGap(18, 18, 18)
                .addComponent(lblPesq)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbMat, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblOk, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(rdMat)
                .addGap(18, 18, 18)
                .addComponent(rdQtMax)
                .addGap(18, 18, 18)
                .addComponent(rdQtAtual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(28, 28, 28)
                .addComponent(lblVolta)
                .addGap(19, 19, 19))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbFaltasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFaltasMouseReleased
        if(tbFaltas.getSelectedRowCount()!= 0){
            lblAdd.setEnabled(true);
            lblRemover.setEnabled(true);
        }else{
            lblAdd.setEnabled(false);
            lblRemover.setEnabled(false);
        }
    }//GEN-LAST:event_tbFaltasMouseReleased

    private void lblAddMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAddMouseReleased
        String opc= "0";
        try{
            while(Integer.parseInt(opc)!= 2 || Integer.parseInt(opc)!= 4){

                opc= JOptionPane.showInputDialog("Digite a quantidade de faltas que deseja adicionar (2 ou 4)");
                if(Integer.parseInt(opc)== 2 || Integer.parseInt(opc)== 4 && Integer.parseInt(opc)!= 3){
                    Materia mat= lista.get(tbFaltas.getSelectedRow());
                    MateriaDAO.adicionarFaltas(Integer.parseInt(opc), mat.getId());
                    break;

                }else{
                    JOptionPane.showMessageDialog(null, "Por favor, digite um número válido!", "Aviso", JOptionPane.WARNING_MESSAGE);
                }

            }
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Por favor, digite um número válido!", "Aviso", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }catch (ArrayIndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null, "Selecione uma matéria da tabela para Adicionar faltas", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_lblAddMouseReleased

    private void lblRemoverMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRemoverMouseReleased
        String opc= "0";
        int max=0, atual=0, auxiliar=0;
        try{
            while(Integer.parseInt(opc)!= 2 || Integer.parseInt(opc)!= 4){

                opc= JOptionPane.showInputDialog("Digite a quantidade de faltas que deseja remover (2 ou 4)");
                if(Integer.parseInt(opc)== 2 || Integer.parseInt(opc)== 4 && Integer.parseInt(opc)!= 3){
                    Materia mat= lista.get(tbFaltas.getSelectedRow());
                    atual= lista.get(tbFaltas.getSelectedRow()).getQtd_atual_faltas();
                    auxiliar= atual - Integer.parseInt(opc);
                    if(auxiliar >= 0){
                        MateriaDAO.removerFaltas(Integer.parseInt(opc), mat.getId());
                    }else{
                        JOptionPane.showMessageDialog(null, "Erro, número de faltas não pode ser negativo", "Aviso", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                }else{
                    JOptionPane.showMessageDialog(null, "Por favor, digite um número válido!", "Aviso", JOptionPane.WARNING_MESSAGE);
                }

            }
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Por favor, digite um número válido!", "Aviso", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }catch (ArrayIndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null, "Selecione uma matéria da tabela para remover faltas", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_lblRemoverMouseReleased

    private void lblOrderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOrderMouseReleased
        if(rdMat.isVisible() && rdQtAtual.isVisible() && rdQtMax.isVisible() && lblVolta.isVisible()){
            rdMat.setVisible(false);
            rdQtAtual.setVisible(false);
            rdQtMax.setVisible(false);
            cbMat.setVisible(false);
            lblOk.setVisible(false);
        }else{
            rdMat.setVisible(true);
            rdQtAtual.setVisible(true);
            rdQtMax.setVisible(true);
            cbMat.setVisible(false);
            lblOk.setVisible(false);
        }

    }//GEN-LAST:event_lblOrderMouseReleased

    private void lblPesqMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPesqMouseClicked

    }//GEN-LAST:event_lblPesqMouseClicked

    private void lblPesqMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPesqMouseReleased
        if(cbMat.isVisible()){
            cbMat.setVisible(false);
            lblOk.setVisible(false);
            rdMat.setVisible(false);
            rdQtAtual.setVisible(false);
            rdQtMax.setVisible(false);
        }else{
            cbMat.setVisible(true);
            lblOk.setVisible(true);
            rdMat.setVisible(false);
            rdQtAtual.setVisible(false);
            rdQtMax.setVisible(false);
        }
        lblVolta.setVisible(true);

    }//GEN-LAST:event_lblPesqMouseReleased

    private void lblVoltaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVoltaMouseReleased
        if(cbMat.isVisible() || rdMat.isVisible()){
            rdMat.setVisible(false);
            rdMat.setVisible(false);
            rdQtAtual.setVisible(false);
            rdQtMax.setVisible(false);
            cbMat.setVisible(false);
            lblOk.setVisible(false);
            atualizaTabela();
            //lblVolta.setVisible(false);
        }else{
            this.dispose();
        }
    }//GEN-LAST:event_lblVoltaMouseReleased

    private void cbMatFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbMatFocusGained

    }//GEN-LAST:event_cbMatFocusGained

    private void cbMatFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbMatFocusLost

    }//GEN-LAST:event_cbMatFocusLost

    private void cbMatMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbMatMouseReleased

    }//GEN-LAST:event_cbMatMouseReleased

    private void rdMatMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdMatMouseReleased
        atualizaTabelaMat();
    }//GEN-LAST:event_rdMatMouseReleased

    private void rdQtMaxMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdQtMaxMouseReleased
        atualizaTabelaQtMax();
    }//GEN-LAST:event_rdQtMaxMouseReleased

    private void rdQtAtualMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdQtAtualMouseReleased
        atualizaTabelaQtAtual();
    }//GEN-LAST:event_rdQtAtualMouseReleased

    private void lblOkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOkMouseReleased
        System.out.println(cbMat.getSelectedItem());
        if(cbMat.getSelectedItem().equals("TODOS")){
            atualizaTabela();
        }else{
            atualizaTabelaPesq(cbMat.getSelectedItem().toString());
        }

    }//GEN-LAST:event_lblOkMouseReleased

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        atualizaTabela();
    }//GEN-LAST:event_formWindowGainedFocus

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
            java.util.logging.Logger.getLogger(VisualizaFaltas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisualizaFaltas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisualizaFaltas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisualizaFaltas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisualizaFaltas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbMat;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAdd;
    private javax.swing.JLabel lblOk;
    private javax.swing.JLabel lblOrder;
    private javax.swing.JLabel lblPesq;
    private javax.swing.JLabel lblRemover;
    private javax.swing.JLabel lblVolta;
    private javax.swing.JRadioButton rdMat;
    private javax.swing.JRadioButton rdQtAtual;
    private javax.swing.JRadioButton rdQtMax;
    private static javax.swing.JTable tbFaltas;
    // End of variables declaration//GEN-END:variables
}
