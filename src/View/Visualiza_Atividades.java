/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.AtividadesDAO;
import Model.Atividades;
import Model.Materia;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author felip
 */
public class Visualiza_Atividades extends javax.swing.JFrame {

    List<Atividades> lista = new ArrayList<>();
    List<Atividades> listaDatas = new ArrayList<>();
    List<Atividades> listaStatus = new ArrayList<>();

    public Visualiza_Atividades() {

        initComponents();
        setLocationRelativeTo(null);
        AtualizaTabela();
        txPesquisa.setEnabled(false);
        lblPesq.setVisible(false);
        rdA.setVisible(false);
        rdF.setVisible(false);
        rdMat.setVisible(false);
        rdStatus.setVisible(false);
        rdTit.setVisible(false);
        btnVolt.setVisible(false);
        lblAv.setVisible(false);
        lblA.setVisible(false);
        lblF.setVisible(false);
        btnStatus.setVisible(false);
        setResizable(false);
        validarPendentes();


    }
 
    public void validarStatus() {
        try {
            listaStatus= AtividadesDAO.retornaStatus();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        int auxiliar = 0;

            while (auxiliar != tbAti.getRowCount()) {
                Atividades atv= lista.get(tbAti.getSelectedRow());
                if (atv.getStatus().equals("F")) {
                    btnExcluir.setText("Excluir");
                } else {
                    btnExcluir.setText("Finalizar");
                }
                auxiliar++;

            }   
    }

    public void validarPendentes() {

        try {
            listaDatas = AtividadesDAO.retornaDatas();
            int auxiliar = 0;

            while (auxiliar != tbAti.getRowCount()) {

                if (lista.get(auxiliar).getData().equals(AtividadesDAO.prazoAtividade())) {
                    Atividades atvAtividades = lista.get(auxiliar);
                    JOptionPane.showMessageDialog(null, "<html><font color=red face=arial><b>A SEGUINTE ATIVIDADE: "
                            + "'' " + atvAtividades.getTitulo().toUpperCase() + " '' TEM SEU PRAZO PARA O DIA DE HOJE!",
                            "Aviso", JOptionPane.WARNING_MESSAGE);
                } else {

                }
                auxiliar++;

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public Atividades retornaObjetoTit() {
        Atividades atv = new Atividades();
        atv.setTitulo(txPesquisa.getText().toUpperCase());
        return atv;
    }

    public Atividades retornaObjetoMat() {
        Atividades atv = new Atividades();
        Materia mat = new Materia();
        mat.setNome(txPesquisa.getText().toUpperCase());
        atv.setMateria(mat);
        return atv;
    }

    public void AtualizaTabela() {
        try {
            lista = AtividadesDAO.pesquisar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        btnExcluir.setText("Finalizar");
        DefaultTableModel modelo = (DefaultTableModel) tbAti.getModel();
        modelo.setNumRows(0);
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM");
        for (Atividades atv : lista) {

            modelo.addRow(new Object[]{
                atv.getTitulo(),
                atv.getStatus(),
                atv.getMateria().getNome(),
                atv.getData()
            });
//            if (tbAti.getRowCount() == 0) {
//                JOptionPane.showMessageDialog(null, "Tabela não encontrou nenhum dado!");
//                MouseEvent mouse = new MouseEvent(rdMat, WIDTH, WIDTH, WIDTH, WIDTH, WIDTH, WIDTH, rootPaneCheckingEnabled);
//                btnVoltMouseReleased(mouse);
//
//            }

        }
    }

    public void AtualizaTabelaMat() {
        try {
            lista = AtividadesDAO.pesquisarMateria(retornaObjetoMat());
        } catch (SQLException s) {
            s.printStackTrace();
        }

        btnExcluir.setText("Finalizar");
        DefaultTableModel modelo = (DefaultTableModel) tbAti.getModel();
        modelo.setNumRows(0);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM");
        for (Atividades atv : lista) {

            modelo.addRow(new Object[]{
                atv.getTitulo(),
                atv.getStatus(),
                atv.getMateria().getNome(),
                atv.getData()
            });

            if (tbAti.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Tabela não encontrou nenhum dado!");
                MouseEvent mouse = new MouseEvent(rdMat, WIDTH, WIDTH, WIDTH, WIDTH, WIDTH, WIDTH, rootPaneCheckingEnabled);
                btnVoltMouseReleased(mouse);
            }

        }
    }

    public void AtualizaTabelaTit() {
        try {
            lista = AtividadesDAO.pesquisarTit(retornaObjetoTit());
        } catch (SQLException s) {
            s.printStackTrace();
        }
        btnExcluir.setText("Finalizar");
        DefaultTableModel modelo = (DefaultTableModel) tbAti.getModel();
        modelo.setNumRows(0);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM");
        for (Atividades atv : lista) {

            modelo.addRow(new Object[]{
                atv.getTitulo(),
                atv.getStatus(),
                atv.getMateria().getNome(),
                atv.getData()
            });
            if (tbAti.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Tabela não encontrou nenhum dado!");
                MouseEvent mouse = new MouseEvent(rdMat, WIDTH, WIDTH, WIDTH, WIDTH, WIDTH, WIDTH, rootPaneCheckingEnabled);
                btnVoltMouseReleased(mouse);
            }

        }
    }

    public void AtualizaTabelaAmbos() {

        try {
            lista = AtividadesDAO.pesquisarStatusAmbAtividades();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        btnExcluir.setText("Finalizar");
        DefaultTableModel modelo = (DefaultTableModel) tbAti.getModel();
        modelo.setNumRows(0);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM");
        for (Atividades atv : lista) {

            modelo.addRow(new Object[]{
                atv.getTitulo(),
                atv.getStatus(),
                atv.getMateria().getNome(),
                atv.getData()
            });
            if (tbAti.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Tabela não encontrou nenhum dado!");
                MouseEvent mouse = new MouseEvent(rdMat, WIDTH, WIDTH, WIDTH, WIDTH, WIDTH, WIDTH, rootPaneCheckingEnabled);
                btnVoltMouseReleased(mouse);
            }

        }
    }

    public void AtualizaTabelaF() {

        try {
            lista = AtividadesDAO.pesquisarStatusF();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        DefaultTableModel modelo = (DefaultTableModel) tbAti.getModel();
        modelo.setNumRows(0);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM");
        for (Atividades atv : lista) {

            modelo.addRow(new Object[]{
                atv.getTitulo(),
                atv.getStatus(),
                atv.getMateria().getNome(),
                atv.getData()
            });
       
            if (tbAti.getRowCount() == 0) {
                
                MouseEvent mouse = new MouseEvent(rdMat, WIDTH, WIDTH, WIDTH, WIDTH, WIDTH, WIDTH, rootPaneCheckingEnabled);
                btnVoltMouseReleased(mouse);
            }

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

        lblAviso = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbAti = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnAddi = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnAlte = new javax.swing.JButton();
        btnPesqui = new javax.swing.JButton();
        rdTit = new javax.swing.JRadioButton();
        rdMat = new javax.swing.JRadioButton();
        rdStatus = new javax.swing.JRadioButton();
        rdA = new javax.swing.JRadioButton();
        rdF = new javax.swing.JRadioButton();
        btnVolt = new javax.swing.JButton();
        txPesquisa = new javax.swing.JTextField();
        lblPesq = new javax.swing.JLabel();
        lblInfo = new javax.swing.JLabel();
        lblAv = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txDesc = new javax.swing.JTextArea();
        lblAv1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnVolt1 = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        lblA = new javax.swing.JLabel();
        lblF = new javax.swing.JLabel();
        btnStatus = new javax.swing.JButton();

        lblAviso.setBackground(new java.awt.Color(204, 0, 0));
        lblAviso.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblAviso.setForeground(new java.awt.Color(204, 0, 0));
        lblAviso.setText("*** SELECIONAR A LINHA DA TABELA PARA MODIFICAR OU EXCLUIR ***");

        jLabel1.setText("jLabel1");

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Atividades\n");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusCycleRoot(false);
        setPreferredSize(new java.awt.Dimension(1320, 781));
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jPanel3.setBackground(new java.awt.Color(240, 240, 240));
        jPanel3.setPreferredSize(new java.awt.Dimension(1322, 728));
        jPanel3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel3FocusGained(evt);
            }
        });

        tbAti.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tbAti.setForeground(new java.awt.Color(0, 0, 0));
        tbAti.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Título", "Status", "Materia", "Data Prevista"
            }
        ));
        tbAti.setGridColor(new java.awt.Color(255, 255, 255));
        tbAti.setUpdateSelectionOnSort(false);
        tbAti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAtiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbAtiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tbAtiMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbAtiMouseReleased(evt);
            }
        });
        tbAti.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbAtiKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbAtiKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tbAti);

        jPanel4.setBackground(new java.awt.Color(225, 225, 225));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnAddi.setText("Adicionar");
        btnAddi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAddiMouseReleased(evt);
            }
        });

        btnExcluir.setText("Finalizar");
        btnExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExcluirMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnExcluirMouseReleased(evt);
            }
        });
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnAlte.setText("Modificar");
        btnAlte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAlteMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAlteMouseReleased(evt);
            }
        });

        btnPesqui.setText("Pesquisar");
        btnPesqui.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnPesquiMouseReleased(evt);
            }
        });

        buttonGroup1.add(rdTit);
        rdTit.setText("Titulo");
        rdTit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rdTitMouseReleased(evt);
            }
        });

        buttonGroup1.add(rdMat);
        rdMat.setText(" Materia");
        rdMat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rdMatMouseReleased(evt);
            }
        });
        rdMat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdMatActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdStatus);
        rdStatus.setText("Status");
        rdStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rdStatusMouseReleased(evt);
            }
        });

        buttonGroup2.add(rdA);
        rdA.setText("Ambos");
        rdA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rdAMouseReleased(evt);
            }
        });

        buttonGroup2.add(rdF);
        rdF.setText("Somente F");
        rdF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rdFMouseReleased(evt);
            }
        });

        btnVolt.setText("Voltar");
        btnVolt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnVoltMouseReleased(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(btnAddi, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(btnPesqui, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(btnVolt, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(btnExcluir, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(btnAlte, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                        .add(0, 11, Short.MAX_VALUE)
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(rdMat)
                            .add(rdTit)
                            .add(rdStatus))
                        .add(15, 15, 15))
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(rdA)
                            .add(rdF))
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(btnAddi, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnExcluir, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(btnAlte, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(btnPesqui, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(rdTit)
                .add(18, 18, 18)
                .add(rdMat)
                .add(18, 18, 18)
                .add(rdStatus)
                .add(27, 27, 27)
                .add(rdA)
                .add(18, 31, Short.MAX_VALUE)
                .add(rdF)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 20, Short.MAX_VALUE)
                .add(btnVolt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnExcluir.getAccessibleContext().setAccessibleDescription("");

        txPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txPesquisaKeyTyped(evt);
            }
        });

        lblPesq.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblPesq.setText("DIGITE O TITULO");

        lblInfo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/icon.png"))); // NOI18N
        lblInfo.setText("Informações sobre os Status");
        lblInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblInfoMouseReleased(evt);
            }
        });

        lblAv.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblAv.setForeground(new java.awt.Color(204, 51, 0));
        lblAv.setText(" Pressione a linha da tabela para ver sua descrição completa");

        txDesc.setEditable(false);
        txDesc.setBackground(new java.awt.Color(225, 225, 225));
        txDesc.setColumns(20);
        txDesc.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txDesc.setForeground(new java.awt.Color(0, 0, 0));
        txDesc.setLineWrap(true);
        txDesc.setRows(5);
        jScrollPane1.setViewportView(txDesc);

        lblAv1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblAv1.setForeground(new java.awt.Color(204, 51, 0));
        lblAv1.setText("Pressione a linha da tabela para ver sua descrição completa");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("Descriçao Completa");

        btnVolt1.setText("Voltar ao menu Principal");
        btnVolt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnVolt1MouseReleased(evt);
            }
        });

        lblA.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblA.setForeground(new java.awt.Color(204, 0, 0));
        lblA.setText("Status A: Em Aberto");

        lblF.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblF.setForeground(new java.awt.Color(0, 153, 51));
        lblF.setText("Status F: Finalizado");

        btnStatus.setText("Esconder  Informações status");
        btnStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnStatusMouseReleased(evt);
            }
        });
        btnStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatusActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(787, 787, 787)
                .add(filler1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, Short.MAX_VALUE))
            .add(jPanel3Layout.createSequentialGroup()
                .add(15, 15, 15)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 940, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel3Layout.createSequentialGroup()
                                .add(lblInfo)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(lblA)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(lblF)
                                .add(72, 72, 72)
                                .add(lblPesq)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(txPesquisa, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 216, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(25, 25, 25))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel3Layout.createSequentialGroup()
                                .add(jLabel2)
                                .add(55, 55, 55)
                                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(lblAv)
                                    .add(lblAv1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 432, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .add(jPanel3Layout.createSequentialGroup()
                                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 567, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(btnStatus)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btnVolt1)))
                        .add(18, 18, 18)))
                .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .add(7, 7, 7)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel3Layout.createSequentialGroup()
                                .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(0, 0, Short.MAX_VALUE))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                                .add(0, 0, Short.MAX_VALUE)
                                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(btnStatus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(btnVolt1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                        .add(filler1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(lblInfo)
                            .add(lblA)
                            .add(lblF)
                            .add(lblPesq)
                            .add(txPesquisa, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 347, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(lblAv1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel2)
                            .add(lblAv))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18))))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 1096, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        
        if(rdStatus.isSelected() && rdF.isSelected()){
            //AtualizaTabelaF();
        }else{
            AtualizaTabela();
        }
        
    }//GEN-LAST:event_formWindowGainedFocus

    private void btnStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStatusActionPerformed

    private void btnStatusMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStatusMouseReleased
        lblA.setVisible(false);
        lblF.setVisible(false);
        btnStatus.setVisible(false);
        
        txPesquisa.setText("");
    }//GEN-LAST:event_btnStatusMouseReleased

    private void btnVolt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVolt1MouseReleased
        this.dispose();
    }//GEN-LAST:event_btnVolt1MouseReleased

    private void lblInfoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInfoMouseReleased
        lblA.setVisible(true);
        lblF.setVisible(true);
        btnStatus.setVisible(true);


    }//GEN-LAST:event_lblInfoMouseReleased

    private void txPesquisaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txPesquisaKeyTyped

        if (rdTit.isSelected()) {
            AtualizaTabelaTit();

        } else if (rdMat.isSelected()) {
            AtualizaTabelaMat();

        }
    }//GEN-LAST:event_txPesquisaKeyTyped

    private void btnVoltMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVoltMouseReleased

        btnAddi.setEnabled(true);
        btnAlte.setEnabled(true);
        btnExcluir.setEnabled(true);
        rdMat.setVisible(false);
        rdStatus.setVisible(false);
        rdTit.setVisible(false);
        rdA.setVisible(false);
        rdF.setVisible(false);
        btnVolt.setVisible(false);
        lblPesq.setVisible(false);
        txPesquisa.setEnabled(false);
        txPesquisa.setText("");
        btnExcluir.setText("Finalizar");
        AtualizaTabela();
    }//GEN-LAST:event_btnVoltMouseReleased

    private void rdFMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdFMouseReleased
        AtualizaTabelaF();
        btnExcluir.setText("Excluir");
    }//GEN-LAST:event_rdFMouseReleased

    private void rdAMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdAMouseReleased
        AtualizaTabelaAmbos();
        
    }//GEN-LAST:event_rdAMouseReleased

    private void rdStatusMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdStatusMouseReleased

        txPesquisa.setEnabled(false);
        lblPesq.setVisible(false);
        rdA.setVisible(true);
        rdF.setVisible(true);

        if (rdA.isSelected()) {
            AtualizaTabelaAmbos();
        } else if (rdF.isSelected()) {
            AtualizaTabelaF();
        }
        
        txPesquisa.setText("");

    }//GEN-LAST:event_rdStatusMouseReleased

    private void rdMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdMatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdMatActionPerformed

    private void rdMatMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdMatMouseReleased
        txPesquisa.setText("");
        txPesquisa.setEnabled(true);
        lblPesq.setVisible(true);
        lblPesq.setText("DIGITE A MATERIA");
        rdA.setVisible(false);
        rdF.setVisible(false);
        AtualizaTabelaMat();
        
    }//GEN-LAST:event_rdMatMouseReleased

    private void rdTitMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdTitMouseReleased
        txPesquisa.setText("");
        txPesquisa.setEnabled(true);
        lblPesq.setVisible(true);
        rdA.setVisible(false);
        rdF.setVisible(false);
        lblPesq.setText("DIGITE O TITULO");
        AtualizaTabelaTit();
    }//GEN-LAST:event_rdTitMouseReleased

    private void btnPesquiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPesquiMouseReleased

        btnAddi.setEnabled(false);
        btnAlte.setEnabled(false);
        btnExcluir.setEnabled(false);

        rdMat.setVisible(true);
        rdStatus.setVisible(true);
        rdTit.setVisible(true);
        btnVolt.setVisible(true);
    }//GEN-LAST:event_btnPesquiMouseReleased

    private void btnAlteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlteMouseReleased
        try {
            Atividades atvSelec = new Atividades();
            atvSelec = lista.get(tbAti.getSelectedRow());
            //System.out.println(lista.toString());
            CadastroAtividade cad = new CadastroAtividade(atvSelec);
            cad.setVisible(true);
        } catch (ArrayIndexOutOfBoundsException e) {
            lblAv.setVisible(true);
            lblAv.setText("Pressione a linha da tabela para excluir ou modificar a mesma");
        }
        AtualizaTabela();
    }//GEN-LAST:event_btnAlteMouseReleased

    private void btnAlteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlteMouseExited
        lblAv.setVisible(false);
    }//GEN-LAST:event_btnAlteMouseExited

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed

    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnExcluirMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcluirMouseReleased

        try {
            Atividades atvSel = lista.get(tbAti.getSelectedRow());

            int opc = JOptionPane.showConfirmDialog(null, "Você realmente deseja finalizar a atividade: " + atvSel.getTitulo() + "");

            if (opc == JOptionPane.YES_OPTION) {

                if (atvSel.getStatus().equals("F")) {

                    try {
                        AtividadesDAO.deleteDB(atvSel);
                        if (rdF.isSelected() || rdA.isSelected()) {
                            AtualizaTabelaF();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {

                    try {
                        AtividadesDAO.finalizaAtividade(atvSel);
                        AtualizaTabelaF();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            lblAv.setVisible(true);
            lblAv.setText("Pressione a linha da tabela para excluir ou modificar a mesma");
        }
    }//GEN-LAST:event_btnExcluirMouseReleased

    private void btnExcluirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcluirMouseExited
        lblAv.setVisible(false);
    }//GEN-LAST:event_btnExcluirMouseExited

    private void btnAddiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddiMouseReleased

        new CadastroAtividade().setVisible(true);
        AtualizaTabela();
    }//GEN-LAST:event_btnAddiMouseReleased

    private void tbAtiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAtiMouseReleased
        if (tbAti.isCellSelected(tbAti.getSelectedRow(), tbAti.getSelectedRow())) {
            btnAddi.setEnabled(true);
            btnAlte.setEnabled(true);
            btnExcluir.setEnabled(true);
        }
        validarStatus();

        
    }//GEN-LAST:event_tbAtiMouseReleased

    private void tbAtiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAtiMouseExited
        lblAv.setVisible(false);
    }//GEN-LAST:event_tbAtiMouseExited

    private void tbAtiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAtiMouseEntered

    }//GEN-LAST:event_tbAtiMouseEntered

    private void tbAtiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAtiMouseClicked
        Atividades atividadeSelecionada = lista.get(tbAti.getSelectedRow());
        txDesc.setText(atividadeSelecionada.getDesc());
    }//GEN-LAST:event_tbAtiMouseClicked

    private void jPanel3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel3FocusGained

    }//GEN-LAST:event_jPanel3FocusGained

    private void tbAtiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAtiKeyPressed

//
//        if(evt.getKeyCode()== KeyEvent.VK_UP){
//            Atividades atv= lista.get(tbAti.getSelectedRow());
//            txDesc.setText(atv.getDesc());
//        }else if(evt.getKeyCode()== KeyEvent.VK_UP){
//            Atividades atv= lista.get(tbAti.getSelectedRow());
//            txDesc.setText(atv.getDesc());
//            
//        }
    }//GEN-LAST:event_tbAtiKeyPressed

    private void tbAtiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAtiKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            Atividades atv = lista.get(tbAti.getSelectedRow());
            txDesc.setText(atv.getDesc());
        }

        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            Atividades atv = lista.get(tbAti.getSelectedRow());
            txDesc.setText(atv.getDesc());

        }
    }//GEN-LAST:event_tbAtiKeyReleased

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
            java.util.logging.Logger.getLogger(Visualiza_Atividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Visualiza_Atividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Visualiza_Atividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Visualiza_Atividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Visualiza_Atividades().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddi;
    private javax.swing.JButton btnAlte;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnPesqui;
    private javax.swing.JButton btnStatus;
    private javax.swing.JButton btnVolt;
    private javax.swing.JButton btnVolt1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblA;
    private javax.swing.JLabel lblAv;
    private javax.swing.JLabel lblAv1;
    private javax.swing.JLabel lblAviso;
    private javax.swing.JLabel lblF;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblPesq;
    private javax.swing.JRadioButton rdA;
    private javax.swing.JRadioButton rdF;
    private javax.swing.JRadioButton rdMat;
    private javax.swing.JRadioButton rdStatus;
    private javax.swing.JRadioButton rdTit;
    private javax.swing.JTable tbAti;
    private javax.swing.JTextArea txDesc;
    private javax.swing.JTextField txPesquisa;
    // End of variables declaration//GEN-END:variables
}
