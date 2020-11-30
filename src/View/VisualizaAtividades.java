/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.AtividadesDAO;
import Model.Atividades;
import Model.Materia;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author felip
 */
public class VisualizaAtividades extends javax.swing.JFrame {

    /**
     * Creates new form VisualizaAtividades
     */
    
    List<Atividades> lista = new ArrayList<>();
    List<Atividades> listaDatas = new ArrayList<>();
    List<Atividades> listaStatus = new ArrayList<>();
    
    public VisualizaAtividades() {
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

        jScrollPane2 = new javax.swing.JScrollPane();
        tbAti = new javax.swing.JTable();
        lblInfo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txDesc = new javax.swing.JTextArea();
        lblAv1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblInfo1 = new javax.swing.JLabel();
        lblA = new javax.swing.JLabel();
        lblF = new javax.swing.JLabel();
        lblPesq = new javax.swing.JLabel();
        txPesquisa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txDesc1 = new javax.swing.JTextArea();
        btnStatus = new javax.swing.JButton();
        btnVolt1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbAti1 = new javax.swing.JTable();
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

        lblInfo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/icon.png"))); // NOI18N
        lblInfo.setText("Informações sobre os Status");
        lblInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblInfoMouseReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("Descriçao Completa");

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblInfo1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblInfo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/icon.png"))); // NOI18N
        lblInfo1.setText("Informações sobre os Status");
        lblInfo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblInfo1MouseReleased(evt);
            }
        });

        lblA.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblA.setForeground(new java.awt.Color(204, 0, 0));
        lblA.setText("Status A: Em Aberto");

        lblF.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblF.setForeground(new java.awt.Color(0, 153, 51));
        lblF.setText("Status F: Finalizado");

        lblPesq.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblPesq.setText("DIGITE O TITULO");

        txPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txPesquisaKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Descriçao Completa");

        txDesc1.setEditable(false);
        txDesc1.setBackground(new java.awt.Color(225, 225, 225));
        txDesc1.setColumns(20);
        txDesc1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txDesc1.setForeground(new java.awt.Color(0, 0, 0));
        txDesc1.setLineWrap(true);
        txDesc1.setRows(5);
        jScrollPane4.setViewportView(txDesc1);

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

        btnVolt1.setText("Voltar ao menu Principal");
        btnVolt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnVolt1MouseReleased(evt);
            }
        });

        tbAti1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tbAti1.setForeground(new java.awt.Color(0, 0, 0));
        tbAti1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Título", "Status", "Materia", "Data Prevista"
            }
        ));
        tbAti1.setGridColor(new java.awt.Color(255, 255, 255));
        tbAti1.setUpdateSelectionOnSort(false);
        tbAti1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAti1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbAti1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tbAti1MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbAti1MouseReleased(evt);
            }
        });
        tbAti1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbAti1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbAti1KeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tbAti1);

        btnAddi.setText("Adicionar");
        btnAddi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAddiMouseReleased(evt);
            }
        });
        btnAddi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddiActionPerformed(evt);
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

        rdTit.setText("Titulo");
        rdTit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rdTitMouseReleased(evt);
            }
        });

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

        rdStatus.setText("Status");
        rdStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rdStatusMouseReleased(evt);
            }
        });

        rdA.setText("Ambos");
        rdA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rdAMouseReleased(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lblA)
                                .addGap(35, 35, 35)
                                .addComponent(lblF)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblPesq)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(180, 180, 180))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lblInfo1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 917, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(43, 43, 43)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnAddi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnAlte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnPesqui, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(rdTit)
                                                    .addComponent(rdMat)
                                                    .addComponent(rdStatus))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(btnVolt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(64, 64, 64)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(rdA)
                                            .addComponent(rdF))))))
                        .addGap(180, 180, 180))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(btnStatus)
                        .addGap(128, 128, 128)
                        .addComponent(btnVolt1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblInfo1)
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblA)
                            .addComponent(lblF)
                            .addComponent(lblPesq)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnVolt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(rdF))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnVolt1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAddi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAlte, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPesqui, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdTit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdMat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdA)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1109, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddiMouseReleased

        new CadastroAtividade().setVisible(true);
        AtualizaTabela();
    }//GEN-LAST:event_btnAddiMouseReleased

    private void btnExcluirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcluirMouseExited
    }//GEN-LAST:event_btnExcluirMouseExited

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
            JOptionPane.showMessageDialog(null, "Pressione a linha da tabela para excluir ou modificar a mesma", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnExcluirMouseReleased

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed

    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnAlteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlteMouseExited

    }//GEN-LAST:event_btnAlteMouseExited

    private void btnAlteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlteMouseReleased
        try {
            Atividades atvSelec = new Atividades();
            atvSelec = lista.get(tbAti.getSelectedRow());
            //System.out.println(lista.toString());
            CadastroAtividade cad = new CadastroAtividade(atvSelec);
            cad.setVisible(true);
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Pressione a linha da tabela para excluir ou modificar a mesma", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        AtualizaTabela();
    }//GEN-LAST:event_btnAlteMouseReleased

    private void btnPesquiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPesquiMouseReleased

        btnAddi.setEnabled(false);
        btnAlte.setEnabled(false);
        btnExcluir.setEnabled(false);

        rdMat.setVisible(true);
        rdStatus.setVisible(true);
        rdTit.setVisible(true);
        btnVolt.setVisible(true);
    }//GEN-LAST:event_btnPesquiMouseReleased

    private void rdTitMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdTitMouseReleased
        txPesquisa.setText("");
        txPesquisa.setEnabled(true);
        lblPesq.setVisible(true);
        rdA.setVisible(false);
        rdF.setVisible(false);
        lblPesq.setText("DIGITE O TITULO");
        AtualizaTabelaTit();
    }//GEN-LAST:event_rdTitMouseReleased

    private void rdMatMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdMatMouseReleased
        txPesquisa.setText("");
        txPesquisa.setEnabled(true);
        lblPesq.setVisible(true);
        lblPesq.setText("DIGITE A MATERIA");
        rdA.setVisible(false);
        rdF.setVisible(false);
        AtualizaTabelaMat();

    }//GEN-LAST:event_rdMatMouseReleased

    private void rdMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdMatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdMatActionPerformed

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

    private void rdAMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdAMouseReleased
        AtualizaTabelaAmbos();

    }//GEN-LAST:event_rdAMouseReleased

    private void rdFMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdFMouseReleased
        AtualizaTabelaF();
        btnExcluir.setText("Excluir");
    }//GEN-LAST:event_rdFMouseReleased

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

    private void tbAtiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAtiMouseClicked
        Atividades atividadeSelecionada = lista.get(tbAti.getSelectedRow());
        txDesc.setText(atividadeSelecionada.getDesc());
    }//GEN-LAST:event_tbAtiMouseClicked

    private void tbAtiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAtiMouseEntered

    }//GEN-LAST:event_tbAtiMouseEntered

    private void tbAtiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAtiMouseExited

    }//GEN-LAST:event_tbAtiMouseExited

    private void tbAtiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAtiMouseReleased
        if (tbAti.isCellSelected(tbAti.getSelectedRow(), tbAti.getSelectedRow())) {
            btnAddi.setEnabled(true);
            btnAlte.setEnabled(true);
            btnExcluir.setEnabled(true);
        }
        validarStatus();

    }//GEN-LAST:event_tbAtiMouseReleased

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

    private void lblInfoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInfoMouseReleased
        lblA.setVisible(true);
        lblF.setVisible(true);
        btnStatus.setVisible(true);

    }//GEN-LAST:event_lblInfoMouseReleased

    private void lblInfo1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInfo1MouseReleased
        lblA.setVisible(true);
        lblF.setVisible(true);
        btnStatus.setVisible(true);

    }//GEN-LAST:event_lblInfo1MouseReleased

    private void txPesquisaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txPesquisaKeyTyped

        if (rdTit.isSelected()) {
            AtualizaTabelaTit();

        } else if (rdMat.isSelected()) {
            AtualizaTabelaMat();

        }
    }//GEN-LAST:event_txPesquisaKeyTyped

    private void btnStatusMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStatusMouseReleased
        lblA.setVisible(false);
        lblF.setVisible(false);
        btnStatus.setVisible(false);

        txPesquisa.setText("");
    }//GEN-LAST:event_btnStatusMouseReleased

    private void btnStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStatusActionPerformed

    private void btnVolt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVolt1MouseReleased
        this.dispose();
    }//GEN-LAST:event_btnVolt1MouseReleased

    private void tbAti1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAti1MouseClicked
        Atividades atividadeSelecionada = lista.get(tbAti.getSelectedRow());
        txDesc.setText(atividadeSelecionada.getDesc());
    }//GEN-LAST:event_tbAti1MouseClicked

    private void tbAti1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAti1MouseEntered

    }//GEN-LAST:event_tbAti1MouseEntered

    private void tbAti1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAti1MouseExited

    }//GEN-LAST:event_tbAti1MouseExited

    private void tbAti1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAti1MouseReleased
        if (tbAti.isCellSelected(tbAti.getSelectedRow(), tbAti.getSelectedRow())) {
            btnAddi.setEnabled(true);
            btnAlte.setEnabled(true);
            btnExcluir.setEnabled(true);
        }
        validarStatus();

    }//GEN-LAST:event_tbAti1MouseReleased

    private void tbAti1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAti1KeyPressed

    }//GEN-LAST:event_tbAti1KeyPressed

    private void tbAti1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAti1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            Atividades atv = lista.get(tbAti.getSelectedRow());
            txDesc.setText(atv.getDesc());
        }

        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            Atividades atv = lista.get(tbAti.getSelectedRow());
            txDesc.setText(atv.getDesc());

        }
    }//GEN-LAST:event_tbAti1KeyReleased

    private void btnAddiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddiActionPerformed

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
            java.util.logging.Logger.getLogger(VisualizaAtividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisualizaAtividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisualizaAtividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisualizaAtividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisualizaAtividades().setVisible(true);
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblA;
    private javax.swing.JLabel lblAv1;
    private javax.swing.JLabel lblF;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblInfo1;
    private javax.swing.JLabel lblPesq;
    private javax.swing.JRadioButton rdA;
    private javax.swing.JRadioButton rdF;
    private javax.swing.JRadioButton rdMat;
    private javax.swing.JRadioButton rdStatus;
    private javax.swing.JRadioButton rdTit;
    private javax.swing.JTable tbAti;
    private javax.swing.JTable tbAti1;
    private javax.swing.JTextArea txDesc;
    private javax.swing.JTextArea txDesc1;
    private javax.swing.JTextField txPesquisa;
    // End of variables declaration//GEN-END:variables
}
