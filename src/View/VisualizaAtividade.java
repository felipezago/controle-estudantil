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
public class VisualizaAtividade extends javax.swing.JFrame {

    List<Atividades> lista = new ArrayList<>();
    List<Atividades> listaDatas = new ArrayList<>();
    List<Atividades> listaStatus = new ArrayList<>();
    public VisualizaAtividade() {
        
        initComponents();
        setLocationRelativeTo(null);
        //AtualizaTabela();
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
       // validarPendentes();
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
             if (tbAti.getRowCount() == 0) {
               JOptionPane.showMessageDialog(null, "Tabela não encontrou nenhum dado!");
               MouseEvent mouse = new MouseEvent(rdMat, WIDTH, WIDTH, WIDTH, WIDTH, WIDTH, WIDTH, rootPaneCheckingEnabled);
               btnVoltMouseReleased(mouse);

            }

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
                //JOptionPane.showMessageDialog(null, "Tabela não encontrou nenhum dado!");
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        lblInfo = new javax.swing.JLabel();
        lblA = new javax.swing.JLabel();
        lblF = new javax.swing.JLabel();
        lblPesq = new javax.swing.JLabel();
        txPesquisa = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btnVolt = new javax.swing.JButton();
        btnAddi = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnAlte = new javax.swing.JButton();
        btnPesqui = new javax.swing.JButton();
        rdTit = new javax.swing.JRadioButton();
        rdMat = new javax.swing.JRadioButton();
        rdStatus = new javax.swing.JRadioButton();
        rdA = new javax.swing.JRadioButton();
        rdF = new javax.swing.JRadioButton();
        btnVolt1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txDesc = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        btnStatus = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbAti = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Atividades");
        setPreferredSize(new java.awt.Dimension(1218, 740));
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jPanel2.setBackground(new java.awt.Color(240, 240, 240));
        jPanel2.setForeground(new java.awt.Color(240, 240, 240));
        jPanel2.setPreferredSize(new java.awt.Dimension(1218, 700));

        lblInfo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/icon.png"))); // NOI18N
        lblInfo.setText("Informação sobre os Status");
        lblInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblInfoMouseReleased(evt);
            }
        });

        lblA.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblA.setForeground(new java.awt.Color(0, 153, 0));
        lblA.setText("Status A: Em Aberto");

        lblF.setBackground(new java.awt.Color(153, 0, 0));
        lblF.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblF.setForeground(new java.awt.Color(204, 0, 0));
        lblF.setText("Status F: Finalizado");

        lblPesq.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPesq.setText("Digite o titulo");

        txPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txPesquisaActionPerformed(evt);
            }
        });
        txPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txPesquisaKeyTyped(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(240, 240, 240));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Configurações", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(240, 240, 240));

        btnVolt.setBackground(new java.awt.Color(240, 240, 240));
        btnVolt.setForeground(new java.awt.Color(0, 0, 0));
        btnVolt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/action_back.gif"))); // NOI18N
        btnVolt.setText("Voltar");
        btnVolt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnVoltMouseReleased(evt);
            }
        });

        btnAddi.setBackground(new java.awt.Color(240, 240, 240));
        btnAddi.setForeground(new java.awt.Color(0, 0, 0));
        btnAddi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/add.png"))); // NOI18N
        btnAddi.setText("Adicionar");
        btnAddi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAddiMouseReleased(evt);
            }
        });

        btnExcluir.setBackground(new java.awt.Color(240, 240, 240));
        btnExcluir.setForeground(new java.awt.Color(0, 0, 0));
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/delete.png"))); // NOI18N
        btnExcluir.setText("Finalizar");
        btnExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnExcluirMouseReleased(evt);
            }
        });

        btnAlte.setBackground(new java.awt.Color(240, 240, 240));
        btnAlte.setForeground(new java.awt.Color(0, 0, 0));
        btnAlte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/arrow_refresh.png"))); // NOI18N
        btnAlte.setText("Modificar");
        btnAlte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAlteMouseReleased(evt);
            }
        });

        btnPesqui.setBackground(new java.awt.Color(240, 240, 240));
        btnPesqui.setForeground(new java.awt.Color(0, 0, 0));
        btnPesqui.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/magnifier.png"))); // NOI18N
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
        rdMat.setText("Matéria");
        rdMat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rdMatMouseReleased(evt);
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
        rdA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdAActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdF);
        rdF.setText("F");
        rdF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rdFMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAddi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnVolt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnExcluir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                            .addComponent(btnPesqui, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                            .addComponent(btnAlte, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdStatus)
                                    .addComponent(rdMat)
                                    .addComponent(rdTit)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdF)
                                    .addComponent(rdA))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAddi, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAlte, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnPesqui, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(rdTit)
                .addGap(31, 31, 31)
                .addComponent(rdMat)
                .addGap(31, 31, 31)
                .addComponent(rdStatus)
                .addGap(31, 31, 31)
                .addComponent(rdA)
                .addGap(27, 27, 27)
                .addComponent(rdF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVolt, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnVolt1.setBackground(new java.awt.Color(240, 240, 240));
        btnVolt1.setForeground(new java.awt.Color(0, 0, 0));
        btnVolt1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/action_back.gif"))); // NOI18N
        btnVolt1.setText("Voltar ao menu Principal");
        btnVolt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnVolt1MouseReleased(evt);
            }
        });

        txDesc.setEditable(false);
        txDesc.setColumns(20);
        txDesc.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txDesc.setLineWrap(true);
        txDesc.setRows(5);
        jScrollPane1.setViewportView(txDesc);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setText("Descrição");

        btnStatus.setBackground(new java.awt.Color(240, 240, 240));
        btnStatus.setForeground(new java.awt.Color(0, 0, 0));
        btnStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/arrow_rotate_clockwise.png"))); // NOI18N
        btnStatus.setText("Esconder informações Status");
        btnStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnStatusMouseReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 0, 0));
        jLabel6.setText("Selecione a linha da tabela para visualizar sua Descrição");

        tbAti.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Titulo", "Matéria", "Status", "Data Prevista"
            }
        ));
        tbAti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAtiMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbAtiMouseReleased(evt);
            }
        });
        tbAti.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbAtiKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tbAti);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblA)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblF)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblInfo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblPesq)
                                        .addGap(9, 9, 9)))
                                .addComponent(txPesquisa))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(145, 145, 145)
                                        .addComponent(btnStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                                        .addGap(89, 89, 89)
                                        .addComponent(btnVolt1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6)
                                        .addGap(201, 201, 201)))))
                        .addGap(24, 24, 24))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblInfo)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblA)
                            .addComponent(lblF)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPesq)
                            .addComponent(txPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnVolt1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(15, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdAActionPerformed

    private void txPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txPesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txPesquisaActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        if(rdStatus.isSelected() && rdF.isSelected()){
            //AtualizaTabelaF();
        }else{
            AtualizaTabela();
        }
    }//GEN-LAST:event_formWindowGainedFocus

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
            JOptionPane.showMessageDialog(null, "<html><font color=black face=arial><b>"
                            + "FAVOR SELECIONAR UMA LINHA DA TABELA ANTES DE MODIFICAR/EXCLUIR",
                            "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        AtualizaTabela();
    }//GEN-LAST:event_btnAlteMouseReleased

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
            JOptionPane.showMessageDialog(null, "<html><font color=black face=arial><b>"
                            + "FAVOR SELECIONAR UMA LINHA DA TABELA ANTES DE MODIFICAR/EXCLUIR",
                            "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnExcluirMouseReleased

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

    private void tbAtiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAtiMouseClicked
        Atividades atividadeSelecionada = lista.get(tbAti.getSelectedRow());
        txDesc.setText(atividadeSelecionada.getDesc());
    }//GEN-LAST:event_tbAtiMouseClicked

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
            java.util.logging.Logger.getLogger(VisualizaAtividade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisualizaAtividade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisualizaAtividade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisualizaAtividade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisualizaAtividade().setVisible(true);
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblA;
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
