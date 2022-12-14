package simrskhanza;

import com.sun.jna.platform.win32.OaIdl;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import laporan.DlgDiagnosaPenyakit;
import simrskhanza.DlgCariDokter;

/**
 *
 * @author dosen
 */
public class DlgRingkasanPulangRanap extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, psPasien, psdiag1, psdiag2, pspros;
    private ResultSet rs, rsPasien, rsdiag1, rsdiag2, rspros;
    private int i = 0;
    public DlgCariDokter dokter = new DlgCariDokter(null, false);
    private String mencari = "", cari1 = "", cari2 = "", cari3 = "", cari4 = "", cari5 = "", cari6 = "",
            cari7 = "", cari8 = "", cari9 = "", cari10 = "", cari11 = "", cari12 = "", cari13 = "", cari14 = "",
            cari15 = "", cari16 = "", cari17 = "", cari18 = "", cari19 = "", cari20 = "", cari21 = "", cari22 = "",
            cari23 = "", cari24 = "", cari25 = "", cari26 = "", cari27 = "", cari28 = "", cari29 = "",
            cekringkasan = "", jamPulang = "", kontrolPoli = "", Tglpulang = "";

    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgRingkasanPulangRanap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode=new DefaultTableModel(null,new Object[]{
            "No. Rawat","No. RM","Nama Pasien","Tgl. Lahir","Jns. Kelamin","Tgl. Masuk","Tgl. Pulang","Ruang/Kelas Rawat",
            "Dokter Pengirim","Cara Bayar","Alasan Masuk Dirawat","Ringkasan Riwayat Penyakit","Pemeriksaan Fisik","Pemeriksaan Penunjang Diagnostik",
            "Terapi Pengobatan","Diagnosa Utama/Primer","Diagnosa Sekunder","Tindakan Prosedur","Kondisi Wkt. Pulang","Keadaan Umum","Kesadaran","GCS",
            "Tekanan Darah","Suhu","Nadi","Frekuensi Nafas","Catatan Penting","Terapi Pulang","Pengobatan Lanjutan","Dokter Luar","Tgl. Kontrol Poli",
            "Nama DPJP Pasien"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbRingkasan.setModel(tabMode);

        tbRingkasan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRingkasan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 32; i++) {
            TableColumn column = tbRingkasan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(350);
            } else if (i == 8) {
                column.setPreferredWidth(200);
            } else if (i == 9) {
                column.setPreferredWidth(150);
            } else if (i == 10) {
                column.setPreferredWidth(350);
            } else if (i == 11) {
                column.setPreferredWidth(350);
            } else if (i == 12) {
                column.setPreferredWidth(350);
            } else if (i == 13) {
                column.setPreferredWidth(350);
            } else if (i == 14) {
                column.setPreferredWidth(350);
            } else if (i == 15) {
                column.setPreferredWidth(350);
            } else if (i == 16) {
                column.setPreferredWidth(350);
            } else if (i == 17) {
                column.setPreferredWidth(350);
            } else if (i == 18) {
                column.setPreferredWidth(160);
            } else if (i == 19) {
                column.setPreferredWidth(350);
            } else if (i == 20) {
                column.setPreferredWidth(350);
            } else if (i == 21) {
                column.setPreferredWidth(90);
            } else if (i == 22) {
                column.setPreferredWidth(90);
            } else if (i == 23) {
                column.setPreferredWidth(90);
            } else if (i == 24) {
                column.setPreferredWidth(90);
            } else if (i == 25) {
                column.setPreferredWidth(90);
            } else if (i == 26) {
                column.setPreferredWidth(350);
            } else if (i == 27) {
                column.setPreferredWidth(350);
            } else if (i == 28) {
                column.setPreferredWidth(140);
            } else if (i == 29) {
                column.setPreferredWidth(200);
            } else if (i == 30) {
                column.setPreferredWidth(95);
            } else if (i == 31) {
                column.setPreferredWidth(200);
            }
        }
        tbRingkasan.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        TTensi.setDocument(new batasInput((byte)5).getKata(TTensi));
        TSuhu.setDocument(new batasInput((byte)5).getKata(TSuhu));
        TNadi.setDocument(new batasInput((byte)5).getKata(TNadi));
        TFrekuensiNafas.setDocument(new batasInput((byte)5).getKata(TFrekuensiNafas));
        
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {tampil();}
                @Override
                public void removeUpdate(DocumentEvent e) {tampil();}
                @Override
                public void changedUpdate(DocumentEvent e) {tampil();}
            });
        } 
        
        ChkInput.setSelected(false);
        isForm();
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRingkasanPulangRanap")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        TNmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());                        
                    }
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
        
    }
 
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnDiagnosa = new javax.swing.JMenuItem();
        MnCetakRingkasan = new javax.swing.JMenuItem();
        DTPCari1 = new widget.Tanggal();
        DTPCari2 = new widget.Tanggal();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbRingkasan = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.TabPane();
        internalFrame2 = new widget.InternalFrame();
        panelisi1 = new widget.panelisi();
        jLabel4 = new widget.Label();
        TNoRM = new widget.TextBox();
        jLabel5 = new widget.Label();
        TNoRW = new widget.TextBox();
        jLabel8 = new widget.Label();
        TNmPasien = new widget.TextBox();
        jLabel9 = new widget.Label();
        TTglLhr = new widget.TextBox();
        jLabel10 = new widget.Label();
        TJK = new widget.TextBox();
        jLabel11 = new widget.Label();
        TTglMsk = new widget.TextBox();
        jLabel12 = new widget.Label();
        TTglPulang = new widget.TextBox();
        jLabel13 = new widget.Label();
        TRuangrawat = new widget.TextBox();
        jLabel14 = new widget.Label();
        TCaraBayar = new widget.TextBox();
        jLabel15 = new widget.Label();
        TNmDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel43 = new widget.Label();
        Tdpjp = new widget.TextBox();
        internalFrame3 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        InputRingkasan = new widget.PanelBiasa();
        jLabel16 = new widget.Label();
        Scroll16 = new widget.ScrollPane();
        TAlasanDirawat = new widget.TextArea();
        jLabel17 = new widget.Label();
        jLabel18 = new widget.Label();
        Scroll17 = new widget.ScrollPane();
        TRingkasanRiwayat = new widget.TextArea();
        jLabel19 = new widget.Label();
        jLabel20 = new widget.Label();
        Scroll18 = new widget.ScrollPane();
        TPemeriksaanPenunjang = new widget.TextArea();
        jLabel21 = new widget.Label();
        jLabel22 = new widget.Label();
        jLabel23 = new widget.Label();
        Scroll19 = new widget.ScrollPane();
        TTerapiPengobatan = new widget.TextArea();
        jLabel24 = new widget.Label();
        Scroll20 = new widget.ScrollPane();
        TDiagUtama = new widget.TextArea();
        jLabel25 = new widget.Label();
        Scroll21 = new widget.ScrollPane();
        TDiagSekunder = new widget.TextArea();
        jLabel26 = new widget.Label();
        Scroll22 = new widget.ScrollPane();
        TTindakan = new widget.TextArea();
        jLabel27 = new widget.Label();
        Scroll23 = new widget.ScrollPane();
        TKeadaanumum = new widget.TextArea();
        jLabel28 = new widget.Label();
        jLabel29 = new widget.Label();
        Scroll24 = new widget.ScrollPane();
        TKesadaran = new widget.TextArea();
        jLabel30 = new widget.Label();
        TTensi = new widget.TextBox();
        jLabel31 = new widget.Label();
        TSuhu = new widget.TextBox();
        jLabel32 = new widget.Label();
        TNadi = new widget.TextBox();
        jLabel33 = new widget.Label();
        TFrekuensiNafas = new widget.TextBox();
        jLabel34 = new widget.Label();
        cmbLanjutan = new widget.ComboBox();
        jLabel35 = new widget.Label();
        TDokterLuar = new widget.TextBox();
        jLabel36 = new widget.Label();
        TglKontrol = new widget.Tanggal();
        jLabel37 = new widget.Label();
        Tgcs = new widget.TextBox();
        Scroll25 = new widget.ScrollPane();
        TCatatan = new widget.TextArea();
        jLabel39 = new widget.Label();
        Scroll26 = new widget.ScrollPane();
        TPemeriksaanFisik = new widget.TextArea();
        jLabel40 = new widget.Label();
        jLabel41 = new widget.Label();
        TKondisiPulang = new widget.TextBox();
        Scroll27 = new widget.ScrollPane();
        TTerapiPulang = new widget.TextArea();
        jLabel42 = new widget.Label();
        jLabel45 = new widget.Label();
        jLabel44 = new widget.Label();
        chkTglKontrol = new widget.CekBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnDiagnosa.setBackground(new java.awt.Color(255, 255, 255));
        MnDiagnosa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDiagnosa.setText("Diagnosa Pasien (ICD)");
        MnDiagnosa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDiagnosa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDiagnosa.setIconTextGap(5);
        MnDiagnosa.setName("MnDiagnosa"); // NOI18N
        MnDiagnosa.setPreferredSize(new java.awt.Dimension(180, 26));
        MnDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDiagnosaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDiagnosa);

        MnCetakRingkasan.setBackground(new java.awt.Color(255, 255, 255));
        MnCetakRingkasan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakRingkasan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakRingkasan.setText("Cetak Ringkasan Pulang");
        MnCetakRingkasan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakRingkasan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakRingkasan.setIconTextGap(5);
        MnCetakRingkasan.setName("MnCetakRingkasan"); // NOI18N
        MnCetakRingkasan.setPreferredSize(new java.awt.Dimension(180, 26));
        MnCetakRingkasan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakRingkasanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCetakRingkasan);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-11-2019" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(100, 23));

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-11-2019" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(100, 23));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ringkasan Pulang Pasien Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbRingkasan.setToolTipText("Silahkan klik untuk memilih data yang ataupun dihapus");
        tbRingkasan.setComponentPopupMenu(jPopupMenu1);
        tbRingkasan.setName("tbRingkasan"); // NOI18N
        tbRingkasan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRingkasanMouseClicked(evt);
            }
        });
        tbRingkasan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRingkasanKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbRingkasan);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnSimpan);

        BtnBatal.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        BtnBatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatalKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnBatal);

        BtnHapus.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnHapus);

        BtnGanti.setForeground(new java.awt.Color(0, 0, 0));
        BtnGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGanti.setMnemonic('G');
        BtnGanti.setText("Ganti");
        BtnGanti.setToolTipText("Alt+G");
        BtnGanti.setName("BtnGanti"); // NOI18N
        BtnGanti.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnGanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiActionPerformed(evt);
            }
        });
        BtnGanti.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnGantiKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnGanti);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnAll);

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnKeluar);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(450, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelGlass10.add(BtnCari);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass10.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(LCount);

        jPanel3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 295));
        PanelInput.setRequestFocusEnabled(false);
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setForeground(new java.awt.Color(0, 0, 0));
        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('M');
        ChkInput.setText(".: Input Data");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        FormInput.setForeground(new java.awt.Color(0, 0, 0));
        FormInput.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(400, 210));
        FormInput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FormInputMouseClicked(evt);
            }
        });

        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout());

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. RM :");
        jLabel4.setName("jLabel4"); // NOI18N
        panelisi1.add(jLabel4);
        jLabel4.setBounds(0, 8, 100, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setToolTipText("");
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        panelisi1.add(TNoRM);
        TNoRM.setBounds(105, 8, 75, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("No. Rawat :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelisi1.add(jLabel5);
        jLabel5.setBounds(183, 8, 70, 23);

        TNoRW.setEditable(false);
        TNoRW.setForeground(new java.awt.Color(0, 0, 0));
        TNoRW.setToolTipText("");
        TNoRW.setHighlighter(null);
        TNoRW.setName("TNoRW"); // NOI18N
        TNoRW.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRWKeyPressed(evt);
            }
        });
        panelisi1.add(TNoRW);
        TNoRW.setBounds(255, 8, 190, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl. Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        panelisi1.add(jLabel8);
        jLabel8.setBounds(0, 63, 100, 23);

        TNmPasien.setEditable(false);
        TNmPasien.setForeground(new java.awt.Color(0, 0, 0));
        TNmPasien.setToolTipText("");
        TNmPasien.setHighlighter(null);
        TNmPasien.setName("TNmPasien"); // NOI18N
        TNmPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNmPasienKeyPressed(evt);
            }
        });
        panelisi1.add(TNmPasien);
        TNmPasien.setBounds(105, 35, 340, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Nama Pasien :");
        jLabel9.setName("jLabel9"); // NOI18N
        panelisi1.add(jLabel9);
        jLabel9.setBounds(0, 35, 100, 23);

        TTglLhr.setEditable(false);
        TTglLhr.setForeground(new java.awt.Color(0, 0, 0));
        TTglLhr.setToolTipText("");
        TTglLhr.setHighlighter(null);
        TTglLhr.setName("TTglLhr"); // NOI18N
        TTglLhr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTglLhrKeyPressed(evt);
            }
        });
        panelisi1.add(TTglLhr);
        TTglLhr.setBounds(105, 63, 100, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Jns. Kelamin :");
        jLabel10.setName("jLabel10"); // NOI18N
        panelisi1.add(jLabel10);
        jLabel10.setBounds(210, 63, 70, 23);

        TJK.setEditable(false);
        TJK.setForeground(new java.awt.Color(0, 0, 0));
        TJK.setToolTipText("");
        TJK.setHighlighter(null);
        TJK.setName("TJK"); // NOI18N
        TJK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TJKKeyPressed(evt);
            }
        });
        panelisi1.add(TJK);
        TJK.setBounds(285, 63, 130, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Tgl. Masuk :");
        jLabel11.setName("jLabel11"); // NOI18N
        panelisi1.add(jLabel11);
        jLabel11.setBounds(0, 91, 100, 23);

        TTglMsk.setEditable(false);
        TTglMsk.setForeground(new java.awt.Color(0, 0, 0));
        TTglMsk.setToolTipText("");
        TTglMsk.setHighlighter(null);
        TTglMsk.setName("TTglMsk"); // NOI18N
        TTglMsk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTglMskKeyPressed(evt);
            }
        });
        panelisi1.add(TTglMsk);
        TTglMsk.setBounds(105, 91, 100, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tgl. Pulang :");
        jLabel12.setName("jLabel12"); // NOI18N
        panelisi1.add(jLabel12);
        jLabel12.setBounds(210, 91, 70, 23);

        TTglPulang.setEditable(false);
        TTglPulang.setForeground(new java.awt.Color(0, 0, 0));
        TTglPulang.setToolTipText("");
        TTglPulang.setHighlighter(null);
        TTglPulang.setName("TTglPulang"); // NOI18N
        TTglPulang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTglPulangKeyPressed(evt);
            }
        });
        panelisi1.add(TTglPulang);
        TTglPulang.setBounds(285, 91, 100, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Ruang/Kelas :");
        jLabel13.setName("jLabel13"); // NOI18N
        panelisi1.add(jLabel13);
        jLabel13.setBounds(0, 119, 100, 23);

        TRuangrawat.setEditable(false);
        TRuangrawat.setForeground(new java.awt.Color(0, 0, 0));
        TRuangrawat.setToolTipText("");
        TRuangrawat.setHighlighter(null);
        TRuangrawat.setName("TRuangrawat"); // NOI18N
        TRuangrawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRuangrawatKeyPressed(evt);
            }
        });
        panelisi1.add(TRuangrawat);
        TRuangrawat.setBounds(105, 119, 340, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Cara Bayar :");
        jLabel14.setName("jLabel14"); // NOI18N
        panelisi1.add(jLabel14);
        jLabel14.setBounds(0, 147, 100, 23);

        TCaraBayar.setEditable(false);
        TCaraBayar.setForeground(new java.awt.Color(0, 0, 0));
        TCaraBayar.setToolTipText("");
        TCaraBayar.setHighlighter(null);
        TCaraBayar.setName("TCaraBayar"); // NOI18N
        TCaraBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCaraBayarKeyPressed(evt);
            }
        });
        panelisi1.add(TCaraBayar);
        TCaraBayar.setBounds(105, 147, 340, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Dokter Pengirim :");
        jLabel15.setName("jLabel15"); // NOI18N
        panelisi1.add(jLabel15);
        jLabel15.setBounds(0, 203, 100, 23);

        TNmDokter.setForeground(new java.awt.Color(0, 0, 0));
        TNmDokter.setToolTipText("");
        TNmDokter.setHighlighter(null);
        TNmDokter.setName("TNmDokter"); // NOI18N
        TNmDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNmDokterKeyPressed(evt);
            }
        });
        panelisi1.add(TNmDokter);
        TNmDokter.setBounds(105, 203, 310, 23);

        BtnDokter.setForeground(new java.awt.Color(0, 0, 0));
        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('X');
        BtnDokter.setToolTipText("Alt+X");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        BtnDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokterKeyPressed(evt);
            }
        });
        panelisi1.add(BtnDokter);
        BtnDokter.setBounds(418, 203, 30, 23);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("DPJP Pasien :");
        jLabel43.setName("jLabel43"); // NOI18N
        panelisi1.add(jLabel43);
        jLabel43.setBounds(0, 175, 100, 23);

        Tdpjp.setEditable(false);
        Tdpjp.setForeground(new java.awt.Color(0, 0, 0));
        Tdpjp.setToolTipText("");
        Tdpjp.setHighlighter(null);
        Tdpjp.setName("Tdpjp"); // NOI18N
        Tdpjp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdpjpKeyPressed(evt);
            }
        });
        panelisi1.add(Tdpjp);
        Tdpjp.setBounds(105, 175, 340, 23);

        internalFrame2.add(panelisi1, java.awt.BorderLayout.CENTER);

        FormInput.addTab(".: Pasien Rawat Inap", internalFrame2);

        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout());

        Scroll1.setBackground(new java.awt.Color(238, 238, 238));
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        InputRingkasan.setBorder(null);
        InputRingkasan.setName("InputRingkasan"); // NOI18N
        InputRingkasan.setPreferredSize(new java.awt.Dimension(100, 465));
        InputRingkasan.setLayout(null);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Alasan Masuk Dirawat :");
        jLabel16.setName("jLabel16"); // NOI18N
        InputRingkasan.add(jLabel16);
        jLabel16.setBounds(0, 45, 130, 23);

        Scroll16.setName("Scroll16"); // NOI18N
        Scroll16.setOpaque(true);

        TAlasanDirawat.setColumns(20);
        TAlasanDirawat.setRows(5);
        TAlasanDirawat.setName("TAlasanDirawat"); // NOI18N
        TAlasanDirawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlasanDirawatKeyPressed(evt);
            }
        });
        Scroll16.setViewportView(TAlasanDirawat);

        InputRingkasan.add(Scroll16);
        Scroll16.setBounds(135, 45, 400, 55);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Ringkasan Riwayat ");
        jLabel17.setName("jLabel17"); // NOI18N
        InputRingkasan.add(jLabel17);
        jLabel17.setBounds(0, 105, 130, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Penyakit :");
        jLabel18.setName("jLabel18"); // NOI18N
        InputRingkasan.add(jLabel18);
        jLabel18.setBounds(0, 119, 130, 23);

        Scroll17.setName("Scroll17"); // NOI18N
        Scroll17.setOpaque(true);

        TRingkasanRiwayat.setColumns(20);
        TRingkasanRiwayat.setRows(5);
        TRingkasanRiwayat.setName("TRingkasanRiwayat"); // NOI18N
        TRingkasanRiwayat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRingkasanRiwayatKeyPressed(evt);
            }
        });
        Scroll17.setViewportView(TRingkasanRiwayat);

        InputRingkasan.add(Scroll17);
        Scroll17.setBounds(135, 105, 400, 55);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Pemeriksaan Penunjang");
        jLabel19.setName("jLabel19"); // NOI18N
        InputRingkasan.add(jLabel19);
        jLabel19.setBounds(0, 225, 130, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Diagnostik :");
        jLabel20.setName("jLabel20"); // NOI18N
        InputRingkasan.add(jLabel20);
        jLabel20.setBounds(0, 239, 130, 23);

        Scroll18.setName("Scroll18"); // NOI18N
        Scroll18.setOpaque(true);

        TPemeriksaanPenunjang.setColumns(20);
        TPemeriksaanPenunjang.setRows(5);
        TPemeriksaanPenunjang.setName("TPemeriksaanPenunjang"); // NOI18N
        TPemeriksaanPenunjang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPemeriksaanPenunjangKeyPressed(evt);
            }
        });
        Scroll18.setViewportView(TPemeriksaanPenunjang);

        InputRingkasan.add(Scroll18);
        Scroll18.setBounds(135, 225, 400, 55);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Terapi Pengobatan ");
        jLabel21.setName("jLabel21"); // NOI18N
        InputRingkasan.add(jLabel21);
        jLabel21.setBounds(0, 285, 130, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("selama dirawat & efek ");
        jLabel22.setName("jLabel22"); // NOI18N
        InputRingkasan.add(jLabel22);
        jLabel22.setBounds(0, 298, 130, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("samping (bila ada) :");
        jLabel23.setName("jLabel23"); // NOI18N
        InputRingkasan.add(jLabel23);
        jLabel23.setBounds(0, 312, 130, 23);

        Scroll19.setName("Scroll19"); // NOI18N
        Scroll19.setOpaque(true);

        TTerapiPengobatan.setColumns(20);
        TTerapiPengobatan.setRows(5);
        TTerapiPengobatan.setName("TTerapiPengobatan"); // NOI18N
        TTerapiPengobatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTerapiPengobatanKeyPressed(evt);
            }
        });
        Scroll19.setViewportView(TTerapiPengobatan);

        InputRingkasan.add(Scroll19);
        Scroll19.setBounds(135, 285, 400, 55);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Primer :");
        jLabel24.setName("jLabel24"); // NOI18N
        InputRingkasan.add(jLabel24);
        jLabel24.setBounds(0, 358, 130, 23);

        Scroll20.setName("Scroll20"); // NOI18N
        Scroll20.setOpaque(true);

        TDiagUtama.setColumns(20);
        TDiagUtama.setRows(5);
        TDiagUtama.setName("TDiagUtama"); // NOI18N
        TDiagUtama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiagUtamaKeyPressed(evt);
            }
        });
        Scroll20.setViewportView(TDiagUtama);

        InputRingkasan.add(Scroll20);
        Scroll20.setBounds(135, 345, 400, 55);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Diagnosa Sekunder :");
        jLabel25.setName("jLabel25"); // NOI18N
        InputRingkasan.add(jLabel25);
        jLabel25.setBounds(0, 405, 130, 23);

        Scroll21.setName("Scroll21"); // NOI18N
        Scroll21.setOpaque(true);

        TDiagSekunder.setColumns(20);
        TDiagSekunder.setRows(5);
        TDiagSekunder.setName("TDiagSekunder"); // NOI18N
        TDiagSekunder.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiagSekunderKeyPressed(evt);
            }
        });
        Scroll21.setViewportView(TDiagSekunder);

        InputRingkasan.add(Scroll21);
        Scroll21.setBounds(135, 405, 400, 55);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Tindakan Prosedur :");
        jLabel26.setName("jLabel26"); // NOI18N
        InputRingkasan.add(jLabel26);
        jLabel26.setBounds(540, 8, 110, 23);

        Scroll22.setName("Scroll22"); // NOI18N
        Scroll22.setOpaque(true);

        TTindakan.setColumns(20);
        TTindakan.setRows(5);
        TTindakan.setName("TTindakan"); // NOI18N
        TTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTindakanKeyPressed(evt);
            }
        });
        Scroll22.setViewportView(TTindakan);

        InputRingkasan.add(Scroll22);
        Scroll22.setBounds(655, 8, 400, 55);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Keadaan Umum :");
        jLabel27.setName("jLabel27"); // NOI18N
        InputRingkasan.add(jLabel27);
        jLabel27.setBounds(540, 68, 110, 23);

        Scroll23.setName("Scroll23"); // NOI18N
        Scroll23.setOpaque(true);

        TKeadaanumum.setColumns(20);
        TKeadaanumum.setRows(5);
        TKeadaanumum.setName("TKeadaanumum"); // NOI18N
        TKeadaanumum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKeadaanumumKeyPressed(evt);
            }
        });
        Scroll23.setViewportView(TKeadaanumum);

        InputRingkasan.add(Scroll23);
        Scroll23.setBounds(655, 68, 400, 55);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Diagnosa Utama/ ");
        jLabel28.setName("jLabel28"); // NOI18N
        InputRingkasan.add(jLabel28);
        jLabel28.setBounds(0, 345, 130, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Kesadaran :");
        jLabel29.setName("jLabel29"); // NOI18N
        InputRingkasan.add(jLabel29);
        jLabel29.setBounds(540, 128, 110, 23);

        Scroll24.setName("Scroll24"); // NOI18N
        Scroll24.setOpaque(true);

        TKesadaran.setColumns(20);
        TKesadaran.setRows(5);
        TKesadaran.setName("TKesadaran"); // NOI18N
        TKesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKesadaranKeyPressed(evt);
            }
        });
        Scroll24.setViewportView(TKesadaran);

        InputRingkasan.add(Scroll24);
        Scroll24.setBounds(655, 128, 400, 55);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Tanda Vital : Tekanan darah");
        jLabel30.setName("jLabel30"); // NOI18N
        InputRingkasan.add(jLabel30);
        jLabel30.setBounds(540, 189, 180, 23);

        TTensi.setForeground(new java.awt.Color(0, 0, 0));
        TTensi.setToolTipText("");
        TTensi.setHighlighter(null);
        TTensi.setName("TTensi"); // NOI18N
        TTensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTensiKeyPressed(evt);
            }
        });
        InputRingkasan.add(TTensi);
        TTensi.setBounds(725, 189, 70, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Suhu :");
        jLabel31.setName("jLabel31"); // NOI18N
        InputRingkasan.add(jLabel31);
        jLabel31.setBounds(802, 189, 40, 23);

        TSuhu.setForeground(new java.awt.Color(0, 0, 0));
        TSuhu.setToolTipText("");
        TSuhu.setHighlighter(null);
        TSuhu.setName("TSuhu"); // NOI18N
        TSuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TSuhuKeyPressed(evt);
            }
        });
        InputRingkasan.add(TSuhu);
        TSuhu.setBounds(845, 189, 70, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Nadi :");
        jLabel32.setName("jLabel32"); // NOI18N
        InputRingkasan.add(jLabel32);
        jLabel32.setBounds(922, 189, 30, 23);

        TNadi.setForeground(new java.awt.Color(0, 0, 0));
        TNadi.setToolTipText("");
        TNadi.setHighlighter(null);
        TNadi.setName("TNadi"); // NOI18N
        TNadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNadiKeyPressed(evt);
            }
        });
        InputRingkasan.add(TNadi);
        TNadi.setBounds(955, 189, 70, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Frekuensi Nafas :");
        jLabel33.setName("jLabel33"); // NOI18N
        InputRingkasan.add(jLabel33);
        jLabel33.setBounds(540, 216, 110, 23);

        TFrekuensiNafas.setForeground(new java.awt.Color(0, 0, 0));
        TFrekuensiNafas.setToolTipText("");
        TFrekuensiNafas.setHighlighter(null);
        TFrekuensiNafas.setName("TFrekuensiNafas"); // NOI18N
        TFrekuensiNafas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TFrekuensiNafasKeyPressed(evt);
            }
        });
        InputRingkasan.add(TFrekuensiNafas);
        TFrekuensiNafas.setBounds(655, 216, 60, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Pengobatan Lnjtn. :");
        jLabel34.setName("jLabel34"); // NOI18N
        InputRingkasan.add(jLabel34);
        jLabel34.setBounds(540, 245, 110, 23);

        cmbLanjutan.setForeground(new java.awt.Color(0, 0, 0));
        cmbLanjutan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Poliklinik", "RS Lain", "Puskesmas", "Dokter Luar" }));
        cmbLanjutan.setName("cmbLanjutan"); // NOI18N
        cmbLanjutan.setOpaque(false);
        cmbLanjutan.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbLanjutan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLanjutanActionPerformed(evt);
            }
        });
        cmbLanjutan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbLanjutanKeyPressed(evt);
            }
        });
        InputRingkasan.add(cmbLanjutan);
        cmbLanjutan.setBounds(655, 245, 110, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Nama Dokter :");
        jLabel35.setName("jLabel35"); // NOI18N
        InputRingkasan.add(jLabel35);
        jLabel35.setBounds(768, 245, 72, 23);

        TDokterLuar.setEditable(false);
        TDokterLuar.setForeground(new java.awt.Color(0, 0, 0));
        TDokterLuar.setToolTipText("");
        TDokterLuar.setHighlighter(null);
        TDokterLuar.setName("TDokterLuar"); // NOI18N
        TDokterLuar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDokterLuarKeyPressed(evt);
            }
        });
        InputRingkasan.add(TDokterLuar);
        TDokterLuar.setBounds(843, 245, 210, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Catatan Penting ");
        jLabel36.setName("jLabel36"); // NOI18N
        InputRingkasan.add(jLabel36);
        jLabel36.setBounds(540, 303, 110, 23);

        TglKontrol.setEditable(false);
        TglKontrol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-11-2019" }));
        TglKontrol.setDisplayFormat("dd-MM-yyyy");
        TglKontrol.setName("TglKontrol"); // NOI18N
        TglKontrol.setOpaque(false);
        TglKontrol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglKontrolKeyPressed(evt);
            }
        });
        InputRingkasan.add(TglKontrol);
        TglKontrol.setBounds(655, 274, 90, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("GCS :");
        jLabel37.setName("jLabel37"); // NOI18N
        InputRingkasan.add(jLabel37);
        jLabel37.setBounds(720, 216, 30, 23);

        Tgcs.setForeground(new java.awt.Color(0, 0, 0));
        Tgcs.setToolTipText("");
        Tgcs.setHighlighter(null);
        Tgcs.setName("Tgcs"); // NOI18N
        Tgcs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsKeyPressed(evt);
            }
        });
        InputRingkasan.add(Tgcs);
        Tgcs.setBounds(755, 216, 295, 23);

        Scroll25.setName("Scroll25"); // NOI18N
        Scroll25.setOpaque(true);

        TCatatan.setColumns(20);
        TCatatan.setRows(5);
        TCatatan.setName("TCatatan"); // NOI18N
        TCatatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCatatanKeyPressed(evt);
            }
        });
        Scroll25.setViewportView(TCatatan);

        InputRingkasan.add(Scroll25);
        Scroll25.setBounds(655, 303, 400, 55);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("(kondisi saat ini) :");
        jLabel39.setName("jLabel39"); // NOI18N
        InputRingkasan.add(jLabel39);
        jLabel39.setBounds(540, 318, 110, 23);

        Scroll26.setName("Scroll26"); // NOI18N
        Scroll26.setOpaque(true);

        TPemeriksaanFisik.setColumns(20);
        TPemeriksaanFisik.setRows(5);
        TPemeriksaanFisik.setName("TPemeriksaanFisik"); // NOI18N
        TPemeriksaanFisik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPemeriksaanFisikKeyPressed(evt);
            }
        });
        Scroll26.setViewportView(TPemeriksaanFisik);

        InputRingkasan.add(Scroll26);
        Scroll26.setBounds(135, 165, 400, 55);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Pemeriksaan Fisik :");
        jLabel40.setName("jLabel40"); // NOI18N
        InputRingkasan.add(jLabel40);
        jLabel40.setBounds(0, 165, 130, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Kondisi Wkt. Pulang :");
        jLabel41.setName("jLabel41"); // NOI18N
        InputRingkasan.add(jLabel41);
        jLabel41.setBounds(750, 274, 110, 23);

        TKondisiPulang.setEditable(false);
        TKondisiPulang.setForeground(new java.awt.Color(0, 0, 0));
        TKondisiPulang.setToolTipText("");
        TKondisiPulang.setHighlighter(null);
        TKondisiPulang.setName("TKondisiPulang"); // NOI18N
        TKondisiPulang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKondisiPulangKeyPressed(evt);
            }
        });
        InputRingkasan.add(TKondisiPulang);
        TKondisiPulang.setBounds(863, 274, 190, 23);

        Scroll27.setName("Scroll27"); // NOI18N
        Scroll27.setOpaque(true);

        TTerapiPulang.setColumns(20);
        TTerapiPulang.setRows(5);
        TTerapiPulang.setName("TTerapiPulang"); // NOI18N
        TTerapiPulang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTerapiPulangKeyPressed(evt);
            }
        });
        Scroll27.setViewportView(TTerapiPulang);

        InputRingkasan.add(Scroll27);
        Scroll27.setBounds(655, 363, 400, 55);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Terapi Pulang :");
        jLabel42.setName("jLabel42"); // NOI18N
        InputRingkasan.add(jLabel42);
        jLabel42.setBounds(540, 363, 110, 23);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel45.setText("* MOHON UNTUK TIDAK MENGGUNAKAN SINGKATAN DALAM PENGETIKAN DIAGNOSIS DAN TINDAKAN");
        jLabel45.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel45.setName("jLabel45"); // NOI18N
        InputRingkasan.add(jLabel45);
        jLabel45.setBounds(10, 0, 530, 23);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel44.setText("* MOHON UNTUK DIKETIK DENGAN RAPI DAN DAPAT DIBACA DENGAN JELAS");
        jLabel44.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel44.setName("jLabel44"); // NOI18N
        InputRingkasan.add(jLabel44);
        jLabel44.setBounds(10, 16, 530, 23);

        chkTglKontrol.setBackground(new java.awt.Color(238, 238, 238));
        chkTglKontrol.setBorder(null);
        chkTglKontrol.setForeground(new java.awt.Color(0, 0, 0));
        chkTglKontrol.setText("Tgl. Kontrol Poli :");
        chkTglKontrol.setBorderPainted(true);
        chkTglKontrol.setBorderPaintedFlat(true);
        chkTglKontrol.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkTglKontrol.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTglKontrol.setName("chkTglKontrol"); // NOI18N
        chkTglKontrol.setOpaque(false);
        chkTglKontrol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTglKontrolActionPerformed(evt);
            }
        });
        InputRingkasan.add(chkTglKontrol);
        chkTglKontrol.setBounds(540, 274, 110, 23);

        Scroll1.setViewportView(InputRingkasan);

        internalFrame3.add(Scroll1, java.awt.BorderLayout.CENTER);

        FormInput.addTab(".: Ringkasan Pulang", internalFrame3);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRW.getText().equals("")) {
            Valid.textKosong(TNoRW, "nomor rawat");
        } else if (TNmDokter.getText().trim().equals("")) {
            Valid.textKosong(TNmDokter, "dokter pengirim");
        } else {
            kontrolPoli = "";
            if (chkTglKontrol.isSelected() == false) {
                kontrolPoli = "0000-00-00";
            } else {
                kontrolPoli = Valid.SetTgl(TglKontrol.getSelectedItem() + "");
            }
            
            Sequel.menyimpan("ringkasan_pulang_ranap", "'" + TNoRW.getText() + "','" + TAlasanDirawat.getText() + "','" + TRingkasanRiwayat.getText() + "',"
                    + "'" + TPemeriksaanFisik.getText() + "','" + TPemeriksaanPenunjang.getText() + "','" + TTerapiPengobatan.getText() + "',"
                    + "'" + TDiagUtama.getText() + "','" + TDiagSekunder.getText() + "','" + TKeadaanumum.getText() + "','" + TKesadaran.getText() + "',"
                    + "'" + TTensi.getText() + "','" + TSuhu.getText() + "','" + TNadi.getText() + "','" + TFrekuensiNafas.getText() + "','" + TCatatan.getText() + "',"
                    + "'" + TTerapiPulang.getText() + "','" + cmbLanjutan.getSelectedItem().toString() + "','" + kontrolPoli + "',"
                    + "'" + TNmDokter.getText() + "','" + Tgcs.getText() + "','" + TTindakan.getText() + "','" + TDokterLuar.getText() + "'", "Ringkasan Pulang Pasien Rawat Inap");
            
            emptTeks();
            tampil();
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, TNmDokter, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        isForm(); 
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnGanti);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (TNoRW.getText().equals("")) {
            Valid.textKosong(TNoRW, "nomor rawat");
        } else if (TNmDokter.getText().trim().equals("")) {
            Valid.textKosong(TNmDokter, "dokter pengirim");
        } else {
            kontrolPoli = "";
            if (chkTglKontrol.isSelected() == false) {
                kontrolPoli = "0000-00-00";
            } else {
                kontrolPoli = Valid.SetTgl(TglKontrol.getSelectedItem() + "");
            }
            
            Sequel.mengedit("ringkasan_pulang_ranap", "no_rawat='" + TNoRW.getText() + "'", "alasan_masuk_dirawat='" + TAlasanDirawat.getText() + "', "
                    + "ringkasan_riwayat_penyakit='" + TRingkasanRiwayat.getText() + "', pemeriksaan_fisik='" + TPemeriksaanFisik.getText() + "', "
                    + "pemeriksaan_penunjang='" + TPemeriksaanPenunjang.getText() + "',terapi_pengobatan='" + TTerapiPengobatan.getText() + "',"
                    + "diagnosa_utama='" + TDiagUtama.getText() + "',diagnosa_sekunder='" + TDiagSekunder.getText() + "',keadaan_umum='" + TKeadaanumum.getText() + "',"
                    + "kesadaran='" + TKesadaran.getText() + "',tekanan_darah='" + TTensi.getText() + "',suhu='" + TSuhu.getText() + "',nadi='" + TNadi.getText() + "',"
                    + "frekuensi_nafas='" + TFrekuensiNafas.getText() + "',catatan_penting='" + TCatatan.getText() + "',terapi_pulang='" + TTerapiPulang.getText() + "',"
                    + "pengobatan_dilanjutkan='" + cmbLanjutan.getSelectedItem().toString() + "',tgl_kontrol_poliklinik='" + kontrolPoli + "',"
                    + "nm_dokter_pengirim='" + TNmDokter.getText() + "',GCS='" + Tgcs.getText() + "',tindakan_prosedur='" + TTindakan.getText() + "',"
                    + "dokter_luar_lanjutan='" + TDokterLuar.getText() + "' ");
            
            tampil();
            emptTeks();
        }
}//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnGantiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnGantiActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
}//GEN-LAST:event_BtnGantiKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnBatal, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
        emptTeks();        
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tampil();
            TCari.setText("");
        } else {
            Valid.pindah(evt, BtnCari, TNmDokter);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbRingkasanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRingkasanMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbRingkasanMouseClicked

    private void tbRingkasanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRingkasanKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbRingkasanKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
  isForm();
}//GEN-LAST:event_ChkInputActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void FormInputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FormInputMouseClicked

    }//GEN-LAST:event_FormInputMouseClicked

    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed

    }//GEN-LAST:event_TNoRMKeyPressed

    private void TNoRWKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRWKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNoRWKeyPressed

    private void TNmPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNmPasienKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNmPasienKeyPressed

    private void TTglLhrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTglLhrKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TTglLhrKeyPressed

    private void TJKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TJKKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TJKKeyPressed

    private void TTglMskKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTglMskKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TTglMskKeyPressed

    private void TTglPulangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTglPulangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TTglPulangKeyPressed

    private void TRuangrawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRuangrawatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TRuangrawatKeyPressed

    private void TCaraBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCaraBayarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TCaraBayarKeyPressed

    private void TNmDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNmDokterKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNmDokterKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        akses.setform("DlgRingkasanPulangRanap");
        dokter.isCek();
        dokter.TCari.requestFocus();
        dokter.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
        dokter.emptTeks();
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnDokterActionPerformed(null);
        }else{
//            Valid.pindah(evt,TanggalPeriksa,btnPenjab);
        }
    }//GEN-LAST:event_BtnDokterKeyPressed

    private void TAlasanDirawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlasanDirawatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TAlasanDirawatKeyPressed

    private void TRingkasanRiwayatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRingkasanRiwayatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TRingkasanRiwayatKeyPressed

    private void TPemeriksaanPenunjangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPemeriksaanPenunjangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TPemeriksaanPenunjangKeyPressed

    private void TTerapiPengobatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTerapiPengobatanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TTerapiPengobatanKeyPressed

    private void TDiagUtamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDiagUtamaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TDiagUtamaKeyPressed

    private void TDiagSekunderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDiagSekunderKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TDiagSekunderKeyPressed

    private void TTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTindakanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TTindakanKeyPressed

    private void TKeadaanumumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKeadaanumumKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TKeadaanumumKeyPressed

    private void TKesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKesadaranKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TKesadaranKeyPressed

    private void TTensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTensiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TTensiKeyPressed

    private void TSuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TSuhuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TSuhuKeyPressed

    private void TNadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNadiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNadiKeyPressed

    private void TFrekuensiNafasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFrekuensiNafasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFrekuensiNafasKeyPressed

    private void cmbLanjutanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbLanjutanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbLanjutanKeyPressed

    private void TDokterLuarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDokterLuarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TDokterLuarKeyPressed

    private void TglKontrolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglKontrolKeyPressed
//        Valid.pindah(evt, TKdPrw, cmbJam);
    }//GEN-LAST:event_TglKontrolKeyPressed

    private void TgcsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TgcsKeyPressed

    private void TCatatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCatatanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TCatatanKeyPressed

    private void TPemeriksaanFisikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPemeriksaanFisikKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TPemeriksaanFisikKeyPressed

    private void TKondisiPulangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKondisiPulangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TKondisiPulangKeyPressed

    private void TTerapiPulangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTerapiPulangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TTerapiPulangKeyPressed

    private void cmbLanjutanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLanjutanActionPerformed
        if (cmbLanjutan.getSelectedItem().toString().equals("Dokter Luar")) {
            TDokterLuar.requestFocus();
            TDokterLuar.setEditable(true);
        } else {
            TDokterLuar.setEditable(false);
            TDokterLuar.setText("");
        }
    }//GEN-LAST:event_cmbLanjutanActionPerformed

    private void MnDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDiagnosaActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu ringkasan pulang pasiennya...!!!");
            TCari.requestFocus();
        } else {
            DlgDiagnosaPenyakit resep = new DlgDiagnosaPenyakit(null, false);
            resep.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            resep.setLocationRelativeTo(internalFrame1);
            resep.isCek();
            resep.setNoRm(TNoRW.getText(), DTPCari1.getDate(), DTPCari2.getDate(), "Ranap");
            resep.tampil();
            resep.setVisible(true);
        }
    }//GEN-LAST:event_MnDiagnosaActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan pilih dulu salah satu ringkasan pulang pasien pada tabel....");
            tbRingkasan.requestFocus();
            tampil();
            emptTeks();
        } else {
            Valid.hapusTable(tabMode, TNoRW, "ringkasan_pulang_ranap", "ringkasan_pulang_ranap.no_rawat");
            tampil();
            emptTeks();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void TdpjpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdpjpKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TdpjpKeyPressed

    private void MnCetakRingkasanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakRingkasanActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu ringkasan pulang pasiennya...!!!");
            TCari.requestFocus();
        } else {
            jamPulang = "";
            Tglpulang = "";
            kontrolPoli = "";
            jamPulang = Sequel.cariIsi("select date_format(jam_keluar,'%h:%m %p') jam from kamar_inap where stts_pulang not in ('-','Pindah Kamar') and no_rawat='" + TNoRW.getText() + "'");
            Tglpulang = Sequel.cariIsi("select date_format(tgl_keluar,'%d %M %Y') tgl from kamar_inap where stts_pulang not in ('-','Pindah Kamar') and no_rawat='" + TNoRW.getText() + "'");
            
            if (chkTglKontrol.isSelected() == false) {
                kontrolPoli = "-";
            } else {
                kontrolPoli = TglKontrol.getSelectedItem().toString();
            }
            
            Sequel.queryu("delete from temporary_ringkasan_pulang");
            Sequel.menyimpan("temporary_ringkasan_pulang", "'0','"
                    + TNoRM.getText() + "','"
                    + TNoRW.getText() + "','"
                    + TNmPasien.getText() + "','"
                    + TTglLhr.getText() + "','"
                    + TJK.getText() + "','"
                    + TTglMsk.getText() + "','"
                    + TTglPulang.getText() + "','"
                    + TRuangrawat.getText() + "','"
                    + TCaraBayar.getText() + "','"
                    + Tdpjp.getText() + "','"
                    + TNmDokter.getText() + "','"
                    + TAlasanDirawat.getText() + "','"
                    + TRingkasanRiwayat.getText() + "','"
                    + TPemeriksaanFisik.getText() + "','"
                    + TPemeriksaanPenunjang.getText() + "','"
                    + TTerapiPengobatan.getText() + "',"
                    + "'','','','','','','','','','','','','','','','','','','','',''", "Ringkasan Pulang Pasien Rawat Inap");
            
            //simpan diagnosa utama ------>>
            try {
                psdiag1 = koneksi.prepareStatement("SELECT dp.kd_penyakit icd_utama, py.ciri_ciri diag_utama FROM diagnosa_pasien dp "
                        + "INNER JOIN penyakit py ON py.kd_penyakit = dp.kd_penyakit "
                        + "WHERE dp.no_rawat like '%" + TNoRW.getText() + "%' AND dp.prioritas = 1 AND dp. STATUS = 'ranap'");

                try {
                    rsdiag1 = psdiag1.executeQuery();
                    while (rsdiag1.next()) {
                        Sequel.menyimpan("temporary_ringkasan_pulang", "'0','','','','','','','','','','','','','','','',"
                                + "'','Diagnosa Utama    ',':','" + TDiagUtama.getText() + "','',"
                                + "'','','','','','','','','','','','','','','','',''", "Ringkasan Pulang");
                        Sequel.menyimpan("temporary_ringkasan_pulang", "'0','','','','','','','','','','','','','','','',"
                                + "'','','','(" + rsdiag1.getString("diag_utama") + ")','ICD 10 : " + rsdiag1.getString("icd_utama") + "','',"
                                + "'','','','','','','','','','','','','','','',''", "Ringkasan Pulang");
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
            
            //simpan diagnosa sekunder text
            Sequel.menyimpan("temporary_ringkasan_pulang", "'0','','','','','','','','','','','','','','','','',"
                    + "'Diagnosa Sekunder',':','" + TDiagSekunder.getText() + "',"
                    + "'','','','','','','','','','','','','','','','','',''", "Ringkasan Pulang");
            
            //simpan diagnosa sekunder ICD-10------------->>
            try {
                psdiag2 = koneksi.prepareStatement("SELECT dp.kd_penyakit icd_sekunder, py.ciri_ciri diag_sekunder FROM diagnosa_pasien dp "
                        + "INNER JOIN penyakit py ON py.kd_penyakit = dp.kd_penyakit "
                        + "WHERE dp.no_rawat like '%" + TNoRW.getText() + "%' AND dp.prioritas <> 1 AND dp. STATUS = 'ranap'");

                try {
                    rsdiag2 = psdiag2.executeQuery();
                    i = 1;
                    while (rsdiag2.next()) {
                        Sequel.menyimpan("temporary_ringkasan_pulang", "'0','','','','','','','','','','','','','','','','','','',"
                                + "'" + i + ". " + rsdiag2.getString("diag_sekunder") + "','ICD 10 : " + rsdiag2.getString("icd_sekunder") + "',"
                                + "'','','','','','','','','','','','','','','','',''", "Ringkasan Pulang");
                        i++;
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
            
            //simpan tindakan prosedur text
            Sequel.menyimpan("temporary_ringkasan_pulang", "'0','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Ringkasan Pulang");
            Sequel.menyimpan("temporary_ringkasan_pulang", "'0','','','','','','','','','','','','','','','','',"
                    + "'Tindakan Prosedur',':','" + TTindakan.getText() + "',"
                    + "'','','','','','','','','','','','','','','','','',''", "Ringkasan Pulang");           

            //simpan tindakan prosedur ------------->>
            try {
                pspros = koneksi.prepareStatement("SELECT pp.kode, i.deskripsi_panjang FROM prosedur_pasien pp INNER JOIN icd9 i ON i.kode = pp.kode "
                        + "WHERE pp.no_rawat like '%" + TNoRW.getText() + "%' AND pp. STATUS = 'ranap'");

                try {
                    rspros = pspros.executeQuery();
                    i = 1;
                    while (rspros.next()) {
                        Sequel.menyimpan("temporary_ringkasan_pulang", "'0','','','','','','','','','','','','','','','','','','',"
                                + "'" + i + ". " + rspros.getString("deskripsi_panjang") + "','ICD 9 CM : " + rspros.getString("kode") + "',"
                                + "'','','','','','','','','','','','','','','','',''", "Ringkasan Pulang");
                        i++;
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
            
            //simpan kondisi waktu pulang
            Sequel.menyimpan("temporary_ringkasan_pulang", "'0','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Ringkasan Pulang");
            Sequel.menyimpan("temporary_ringkasan_pulang", "'0','','','','','','','','','','','','','','','','',"
                    + "'Kondisi Waktu Pulang',':','" + TKondisiPulang.getText() + "',"
                    + "'','','','','','','','','','','','','','','','','',''", "Ringkasan Pulang");

            //simpan keadaan umum
            Sequel.menyimpan("temporary_ringkasan_pulang", "'0','','','','','','','','','','','','','','','','',"
                    + "'Keadaan Umum',':','" + TKeadaanumum.getText() + "',"
                    + "'','','','','','','','','','','','','','','','','',''", "Ringkasan Pulang");

            //simpan kesadaran
            Sequel.menyimpan("temporary_ringkasan_pulang", "'0','','','','','','','','','','','','','','','','',"
                    + "'Kesadaran',':','" + TKesadaran.getText() + ", GCS : " + Tgcs.getText() + "',"
                    + "'','','','','','','','','','','','','','','','','',''", "Ringkasan Pulang");

            //simpan tanda vital
            Sequel.menyimpan("temporary_ringkasan_pulang", "'0','','','','','','','','','','','','','','','','',"
                    + "'Tanda Vital',':','Tekanan Darah : " + TTensi.getText() + ", Suhu : " + TSuhu.getText() + ", Nadi : " + TNadi.getText() + ", Frekuensi Nafas : " + TFrekuensiNafas.getText() + "',"
                    + "'','','','','','','','','','','','','','','','','',''", "Ringkasan Pulang");
            
            //simpan catatan penting
            Sequel.menyimpan("temporary_ringkasan_pulang", "'0','','','','','','','','','','','','','','','','',"
                    + "'Catatan Penting',':','" + TCatatan.getText() + "',"
                    + "'','','','','','','','','','','','','','','','','',''", "Ringkasan Pulang");
            Sequel.menyimpan("temporary_ringkasan_pulang", "'0','','','','','','','','','','','','','','','','',"
                    + "'(Kondisi saat ini)','','','','','','','','','','','','','','','','','','','',''", "Ringkasan Pulang");            
            
            //simpan terapi pulang
            Sequel.menyimpan("temporary_ringkasan_pulang", "'0','','','','','','','','','','','','','','','','',"
                    + "'Terapi Pulang',':','Nama Obat (Dosis, Frekuensi, Jumlah, Cara Pemberian)',"
                    + "'','','','','','','','','','','','','','','','','',''", "Ringkasan Pulang");
            Sequel.menyimpan("temporary_ringkasan_pulang", "'0','','','','','','','','','','','','','','','','','',"
                    + "'','" + TTerapiPulang.getText() + "',"
                    + "'','','','','','','','','','','','','','','','','',''", "Ringkasan Pulang");
            
            //simpan pengobatan dilanjutkan
            Sequel.menyimpan("temporary_ringkasan_pulang", "'0','','','','','','','','','','','','','','','','',"
                    + "'Pengobatan dilanjutkan',':','" + cmbLanjutan.getSelectedItem().toString() + " " + TDokterLuar.getText() + "',"
                    + "'','','','','','','','','','','','','','','','','',''", "Ringkasan Pulang");
            
            //simpan tanggal kontrol poli
            Sequel.menyimpan("temporary_ringkasan_pulang", "'0','','','','','','','','','','','','','','','','',"
                    + "'Tanggal Kontrol Poliklinik',':','" + kontrolPoli + "',"
                    + "'','','','','','','','','','','','','','','','','',''", "Ringkasan Pulang");
            
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("tglRingkasan", "Martapura, " + Tglpulang);
            param.put("jamRingkasan", "Jam          : " + jamPulang + " WITA");
            param.put("drDPJP", Tdpjp.getText());
            Valid.MyReport("rptRingkasanPulangRanap.jasper", "report", "::[ Lembar Ringkasan Pulang Pasien Rawat Inap ]::",
                    "select * from temporary_ringkasan_pulang", param);
            this.setCursor(Cursor.getDefaultCursor());
            
            tampil();
            emptTeks();
        }
    }//GEN-LAST:event_MnCetakRingkasanActionPerformed

    private void chkTglKontrolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTglKontrolActionPerformed
        
    }//GEN-LAST:event_chkTglKontrolActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgRingkasanPulangRanap dialog = new DlgRingkasanPulangRanap(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    public widget.Button BtnDokter;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkInput;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    public widget.TabPane FormInput;
    private widget.PanelBiasa InputRingkasan;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnCetakRingkasan;
    private javax.swing.JMenuItem MnDiagnosa;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll16;
    private widget.ScrollPane Scroll17;
    private widget.ScrollPane Scroll18;
    private widget.ScrollPane Scroll19;
    private widget.ScrollPane Scroll20;
    private widget.ScrollPane Scroll21;
    private widget.ScrollPane Scroll22;
    private widget.ScrollPane Scroll23;
    private widget.ScrollPane Scroll24;
    private widget.ScrollPane Scroll25;
    private widget.ScrollPane Scroll26;
    private widget.ScrollPane Scroll27;
    private widget.TextArea TAlasanDirawat;
    private widget.TextBox TCaraBayar;
    public widget.TextBox TCari;
    private widget.TextArea TCatatan;
    private widget.TextArea TDiagSekunder;
    private widget.TextArea TDiagUtama;
    private widget.TextBox TDokterLuar;
    private widget.TextBox TFrekuensiNafas;
    private widget.TextBox TJK;
    private widget.TextArea TKeadaanumum;
    private widget.TextArea TKesadaran;
    private widget.TextBox TKondisiPulang;
    private widget.TextBox TNadi;
    public widget.TextBox TNmDokter;
    private widget.TextBox TNmPasien;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRW;
    private widget.TextArea TPemeriksaanFisik;
    private widget.TextArea TPemeriksaanPenunjang;
    private widget.TextArea TRingkasanRiwayat;
    private widget.TextBox TRuangrawat;
    private widget.TextBox TSuhu;
    private widget.TextBox TTensi;
    private widget.TextArea TTerapiPengobatan;
    private widget.TextArea TTerapiPulang;
    private widget.TextBox TTglLhr;
    private widget.TextBox TTglMsk;
    private widget.TextBox TTglPulang;
    private widget.TextArea TTindakan;
    private widget.TextBox Tdpjp;
    private widget.TextBox Tgcs;
    private widget.Tanggal TglKontrol;
    private widget.CekBox chkTglKontrol;
    private widget.ComboBox cmbLanjutan;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel39;
    private widget.Label jLabel4;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi1;
    private widget.Table tbRingkasan;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        cari1 = "rr.no_rawat like '%" + TCari.getText() + "%'";
        cari2 = "p.no_rkm_medis like '%" + TCari.getText() + "%'";
        cari3 = "p.nm_pasien like '%" + TCari.getText() + "%'";
        cari4 = "IF (p.jk = 'L','Laki-laki','Perempuan') like '%" + TCari.getText() + "%'";
        cari5 = "b.nm_bangsal like '%" + TCari.getText() + "%'";
        cari6 = "IF (rr.nm_dokter_pengirim = '','-',rr.nm_dokter_pengirim) like '%" + TCari.getText() + "%'";
        cari7 = "pj.png_jawab like '%" + TCari.getText() + "%'";
        cari8 = "rr.alasan_masuk_dirawat like '%" + TCari.getText() + "%'";
        cari9 = "rr.ringkasan_riwayat_penyakit like '%" + TCari.getText() + "%'";
        cari10 = "rr.pemeriksaan_fisik like '%" + TCari.getText() + "%'";
        cari11 = "rr.pemeriksaan_penunjang like '%" + TCari.getText() + "%'";
        cari12 = "rr.terapi_pengobatan like '%" + TCari.getText() + "%'";
        cari13 = "rr.diagnosa_utama like '%" + TCari.getText() + "%'";
        cari14 = "rr.diagnosa_sekunder like '%" + TCari.getText() + "%'";
        cari15 = "rr.tindakan_prosedur like '%" + TCari.getText() + "%'";
        cari16 = "ki.stts_pulang like '%" + TCari.getText() + "%'";
        cari17 = "rr.keadaan_umum like '%" + TCari.getText() + "%'";
        cari18 = "rr.kesadaran like '%" + TCari.getText() + "%'";
        cari19 = "rr.gcs like '%" + TCari.getText() + "%'";
        cari20 = "rr.tekanan_darah like '%" + TCari.getText() + "%'";
        cari21 = "rr.suhu like '%" + TCari.getText() + "%'";
        cari22 = "rr.nadi like '%" + TCari.getText() + "%'";
        cari23 = "rr.frekuensi_nafas like '%" + TCari.getText() + "%'";
        cari24 = "rr.catatan_penting like '%" + TCari.getText() + "%'";
        cari25 = "rr.terapi_pulang like '%" + TCari.getText() + "%'";
        cari26 = "rr.pengobatan_dilanjutkan like '%" + TCari.getText() + "%'";
        cari27 = "rr.dokter_luar_lanjutan like '%" + TCari.getText() + "%'";
        cari28 = "rr.tgl_kontrol_poliklinik like '%" + TCari.getText() + "%'";
        cari29 = "d.nm_dokter like '%" + TCari.getText() + "%'";
        
        Valid.tabelKosong(tabMode);
        try {
            mencari = cari1 + " or " + cari2 + " or " + cari3 + " or " + cari4 + " or " + cari5 + " or "
                    + cari6 + " or " + cari7 + " or " + cari8 + " or " + cari9 + " or " + cari10 + " or "
                    + cari11 + " or " + cari12 + " or " + cari13 + " or " + cari14 + " or " + cari15 + " or "
                    + cari16 + " or " + cari17 + " or " + cari18 + " or " + cari19 + " or " + cari20 + " or "
                    + cari21 + " or " + cari22 + " or " + cari23 + " or " + cari24 + " or " + cari25 + " or "
                    + cari26 + " or " + cari27 + " or " + cari28 + " or " + cari29;
            
            ps = koneksi.prepareStatement("SELECT rr.no_rawat, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgl_lhr, IF(p.jk='L','Laki-laki','Perempuan') jk, b.nm_bangsal, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_msk, "
                    + "DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') tgl_pulang, IF(rr.nm_dokter_pengirim='','-',rr.nm_dokter_pengirim) dr_pengirim, pj.png_jawab, rr.alasan_masuk_dirawat, rr.ringkasan_riwayat_penyakit, "
                    + "rr.pemeriksaan_fisik, rr.pemeriksaan_penunjang, rr.terapi_pengobatan, rr.diagnosa_utama, rr.diagnosa_sekunder, rr.tindakan_prosedur, ki.stts_pulang, rr.keadaan_umum, rr.kesadaran, rr.gcs, rr.tekanan_darah, "
                    + "rr.suhu, rr.nadi, rr.frekuensi_nafas, rr.catatan_penting, rr.terapi_pulang, rr.pengobatan_dilanjutkan, rr.dokter_luar_lanjutan dr_luar, rr.tgl_kontrol_poliklinik tgl_kontrol, d.nm_dokter dpjp FROM ringkasan_pulang_ranap rr "
                    + "INNER JOIN kamar_inap ki on ki.no_rawat=rr.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                    + "INNER JOIN reg_periksa rp on rp.no_rawat=rr.no_rawat INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN dpjp_ranap dr on dr.no_rawat=ki.no_rawat INNER JOIN dokter d on d.kd_dokter=dr.kd_dokter "
                    + "WHERE ki.stts_pulang not in ('-','Pindah Kamar') and (" + mencari + ") ORDER BY rr.no_rawat DESC LIMIT 50");
            
            try {                
                rs = ps.executeQuery();               
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgl_lhr"),
                        rs.getString("jk"),                        
                        rs.getString("tgl_msk"),
                        rs.getString("tgl_pulang"),
                        rs.getString("nm_bangsal"),
                        rs.getString("dr_pengirim"),
                        rs.getString("png_jawab"),
                        rs.getString("alasan_masuk_dirawat"),
                        rs.getString("ringkasan_riwayat_penyakit"),
                        rs.getString("pemeriksaan_fisik"),
                        rs.getString("pemeriksaan_penunjang"),
                        rs.getString("terapi_pengobatan"),
                        rs.getString("diagnosa_utama"),
                        rs.getString("diagnosa_sekunder"),
                        rs.getString("tindakan_prosedur"),
                        rs.getString("stts_pulang"),
                        rs.getString("keadaan_umum"),
                        rs.getString("kesadaran"),
                        rs.getString("gcs"),
                        rs.getString("tekanan_darah"),
                        rs.getString("suhu"),
                        rs.getString("nadi"),
                        rs.getString("frekuensi_nafas"),
                        rs.getString("catatan_penting"),
                        rs.getString("terapi_pulang"),
                        rs.getString("pengobatan_dilanjutkan"),
                        rs.getString("dr_luar"),
                        rs.getString("tgl_kontrol"),
                        rs.getString("dpjp")
                    });                    
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgRingkasanPulangRanap.tampil() : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText(""+tabMode.getRowCount());
    }

    public void emptTeks() {  
        FormInput.setSelectedIndex(0);
        TNoRM.setText("");
        TNoRW.setText("");
        TNmPasien.setText("");
        TTglLhr.setText("");
        TJK.setText("");
        TTglMsk.setText("");
        TTglPulang.setText("");
        TRuangrawat.setText("");
        TCaraBayar.setText("");
        Tdpjp.setText("");
        TNmDokter.setText("");
        TAlasanDirawat.setText("");
        TRingkasanRiwayat.setText("");
        TPemeriksaanFisik.setText("");
        TPemeriksaanPenunjang.setText("");
        TTerapiPengobatan.setText("");
        TDiagUtama.setText("");
        TDiagSekunder.setText("");
        TTindakan.setText("");
        TKeadaanumum.setText("");
        TKesadaran.setText("");
        TTensi.setText("");
        TSuhu.setText("");
        TNadi.setText("");
        TFrekuensiNafas.setText("");
        Tgcs.setText("");
        cmbLanjutan.setSelectedIndex(0);
        TDokterLuar.setText("");
        chkTglKontrol.setSelected(false);
        TKondisiPulang.setText("");
        TCatatan.setText("");
        TTerapiPulang.setText("");
        TglKontrol.setDate(new Date());
        DTPCari1.setDate(new Date());
        DTPCari2.setDate(new Date());
    }

    private void getData() {
        kontrolPoli = "";
        
        if (tbRingkasan.getSelectedRow() != -1) {
            TNoRW.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 0).toString());
            TNoRM.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 1).toString());
            TNmPasien.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 2).toString());
            TTglLhr.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 3).toString());
            TJK.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 4).toString());
            TTglMsk.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 5).toString());
            TTglPulang.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 6).toString());
            Sequel.cariIsi("select tgl_registrasi from reg_periksa where status_lanjut='ranap' and no_rawat='" + TNoRW.getText() + "'", DTPCari1);
            Sequel.cariIsi("select tgl_keluar from kamar_inap where stts_pulang not in ('-','Pindah Kamar') and no_rawat='" + TNoRW.getText() + "'", DTPCari2);
            TRuangrawat.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 7).toString());
            TCaraBayar.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 9).toString());
            TNmDokter.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 8).toString());
            TAlasanDirawat.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 10).toString());
            TRingkasanRiwayat.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 11).toString());
            TPemeriksaanFisik.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 12).toString());
            TPemeriksaanPenunjang.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 13).toString());
            TTerapiPengobatan.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 14).toString());
            TDiagUtama.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 15).toString());
            TDiagSekunder.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 16).toString());
            TTindakan.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 17).toString());
            TKondisiPulang.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 18).toString());
            TKeadaanumum.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 19).toString());
            TKesadaran.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 20).toString());
            TTensi.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 22).toString());
            TSuhu.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 23).toString());
            TNadi.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 24).toString());
            TFrekuensiNafas.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 25).toString());
            Tgcs.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 21).toString());
            cmbLanjutan.setSelectedItem(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 28).toString());
            TDokterLuar.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 29).toString());            
            TCatatan.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 26).toString());
            TTerapiPulang.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 27).toString());
            Tdpjp.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 31).toString());
            
            kontrolPoli = Sequel.cariIsi("select tgl_kontrol_poliklinik from ringkasan_pulang_ranap where no_rawat='" + TNoRW.getText() + "'");
            if (kontrolPoli.equals("")) {
                chkTglKontrol.setSelected(false);
                TglKontrol.setDate(new Date());
            } else {
                chkTglKontrol.setSelected(true);
                Valid.SetTgl(TglKontrol, tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 30).toString());
            }            
        }
    }
    
    public void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,295));
            FormInput.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
    
    public void setPasien(String norawat) {
        TNoRW.setText(norawat);
        TCari.setText(norawat);
        cekPasien();
    }
    
    public void cekPasien() {
        cekringkasan = "";
        cekringkasan = Sequel.cariIsi("select no_rawat from ringkasan_pulang_ranap where no_rawat='" + TNoRW.getText() + "'");

        try {
            psPasien = koneksi.prepareStatement("SELECT rp.no_rawat, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgl_lhr, "
                    + "IF(p.jk='L','Laki-laki','Perempuan') jk, b.nm_bangsal, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_msk, "
                    + "DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') tgl_pulang, IF(rr.nm_dokter_pengirim='','-',rr.nm_dokter_pengirim) dr_pengirim, "
                    + "pj.png_jawab, rr.alasan_masuk_dirawat, rr.ringkasan_riwayat_penyakit, "
                    + "rr.pemeriksaan_fisik, rr.pemeriksaan_penunjang, rr.terapi_pengobatan, rr.diagnosa_utama, rr.diagnosa_sekunder, "
                    + "rr.tindakan_prosedur, ki.stts_pulang, rr.keadaan_umum, rr.kesadaran, rr.gcs, rr.tekanan_darah, "
                    + "rr.suhu, rr.nadi, rr.frekuensi_nafas, rr.catatan_penting, rr.terapi_pulang, rr.pengobatan_dilanjutkan, rr.dokter_luar_lanjutan dr_luar, "
                    + "rr.tgl_kontrol_poliklinik tgl_kontrol, d.nm_dokter dpjp, ki.tgl_keluar, rp.tgl_registrasi FROM kamar_inap ki "
                    + "INNER JOIN kamar k ON k.kd_kamar = ki.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal INNER JOIN reg_periksa rp ON rp.no_rawat = ki.no_rawat "
                    + "INNER JOIN penjab pj ON pj.kd_pj = rp.kd_pj INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN dpjp_ranap dr on dr.no_rawat=ki.no_rawat "
                    + "INNER JOIN dokter d on d.kd_dokter=dr.kd_dokter LEFT JOIN ringkasan_pulang_ranap rr on rr.no_rawat=ki.no_rawat "
                    + "WHERE ki.stts_pulang not in ('-','Pindah Kamar') and rp.no_rawat like '%" + TNoRW.getText() + "%' ");
            
            try {
                rsPasien = psPasien.executeQuery();              
                while (rsPasien.next()) {
                    TNoRM.setText(rsPasien.getString("no_rkm_medis"));
                    TNmPasien.setText(rsPasien.getString("nm_pasien"));
                    TTglLhr.setText(rsPasien.getString("tgl_lhr"));
                    TJK.setText(rsPasien.getString("jk"));
                    Valid.SetTgl(DTPCari1, rsPasien.getString("tgl_registrasi"));
                    Valid.SetTgl(DTPCari2, rsPasien.getString("tgl_keluar"));
                    TTglMsk.setText(rsPasien.getString("tgl_msk"));
                    TTglPulang.setText(rsPasien.getString("tgl_pulang"));
                    TRuangrawat.setText(rsPasien.getString("nm_bangsal"));
                    TCaraBayar.setText(rsPasien.getString("png_jawab"));
                    TKondisiPulang.setText(rsPasien.getString("stts_pulang"));
                    Tdpjp.setText(rsPasien.getString("dpjp"));

                    if (cekringkasan.equals("")) {
                        TNmDokter.setText("");
                        TAlasanDirawat.setText("");
                        TRingkasanRiwayat.setText("");
                        TPemeriksaanFisik.setText("");
                        TPemeriksaanPenunjang.setText("");
                        TTerapiPengobatan.setText("");
                        TDiagUtama.setText("");
                        TDiagSekunder.setText("");
                        TTindakan.setText("");
                        TKeadaanumum.setText("");
                        TKesadaran.setText("");
                        TTensi.setText("");
                        TSuhu.setText("");
                        TNadi.setText("");
                        TFrekuensiNafas.setText("");
                        Tgcs.setText("");
                        cmbLanjutan.setSelectedIndex(0);
                        TDokterLuar.setText("");
                        chkTglKontrol.setSelected(false);
                        TglKontrol.setDate(new Date());                        
                        TCatatan.setText("");
                        TTerapiPulang.setText("");

                    } else {
                        TNmDokter.setText(rsPasien.getString("dr_pengirim"));
                        TAlasanDirawat.setText(rsPasien.getString("alasan_masuk_dirawat"));
                        TRingkasanRiwayat.setText(rsPasien.getString("ringkasan_riwayat_penyakit"));
                        TPemeriksaanFisik.setText(rsPasien.getString("pemeriksaan_fisik"));
                        TPemeriksaanPenunjang.setText(rsPasien.getString("pemeriksaan_penunjang"));
                        TTerapiPengobatan.setText(rsPasien.getString("terapi_pengobatan"));
                        TDiagUtama.setText(rsPasien.getString("diagnosa_utama"));
                        TDiagSekunder.setText(rsPasien.getString("diagnosa_sekunder"));
                        TTindakan.setText(rsPasien.getString("tindakan_prosedur"));
                        TKeadaanumum.setText(rsPasien.getString("keadaan_umum"));
                        TKesadaran.setText(rsPasien.getString("kesadaran"));
                        TTensi.setText(rsPasien.getString("tekanan_darah"));
                        TSuhu.setText(rsPasien.getString("suhu"));
                        TNadi.setText(rsPasien.getString("nadi"));
                        TFrekuensiNafas.setText(rsPasien.getString("frekuensi_nafas"));
                        Tgcs.setText(rsPasien.getString("gcs"));
                        cmbLanjutan.setSelectedItem(rsPasien.getString("pengobatan_dilanjutkan"));
                        TDokterLuar.setText(rsPasien.getString("dr_luar"));
                        chkTglKontrol.setSelected(true);
                        Valid.SetTgl(TglKontrol, rsPasien.getString("tgl_kontrol"));                        
                        TCatatan.setText(rsPasien.getString("catatan_penting"));
                        TTerapiPulang.setText(rsPasien.getString("terapi_pulang"));
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsPasien != null) {
                    rsPasien.close();
                }
                if (psPasien != null) {
                    psPasien.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
}
