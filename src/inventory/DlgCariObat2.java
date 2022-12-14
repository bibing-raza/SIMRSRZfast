/*
  Dilarang keras menggandakan/mengcopy/menyebarkan/membajak/mendecompile 
  Software ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami 
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
 */
package inventory;

import fungsi.WarnaTable2;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import keuangan.Jurnal;
import simrskhanza.DlgCariBangsal;

/**
 *
 * @author dosen
 */
public final class DlgCariObat2 extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private riwayatobat Trackobat = new riwayatobat();
    private PreparedStatement psobat, pscarikapasitas, psobatasuransi, psstok, psrekening;
    private ResultSet rsobat, carikapasitas, rsstok, rsrekening;
    private Jurnal jur = new Jurnal();
    private double x = 0, y = 0, embalase, kenaikan, tuslah, stokbarang, ttlhpp, ttljual, stokbarang2;
    private int jml = 0, i = 0, urut = 0;
    private boolean[] pilih;
    private double[] jumlah, harga, eb, ts, stok, beli;
    private String[] kodebarang, namabarang, kodesatuan, letakbarang, namajenis, industri, aturan, kategori, golongan;
    private DlgBarang barang = new DlgBarang(null, false);
    private String Suspen_Piutang_Obat_Ranap = "", Obat_Ranap = "", HPP_Obat_Rawat_Inap = "", Persediaan_Obat_Rawat_Inap = "", bangsal = "", status = "";
    private WarnaTable2 warna = new WarnaTable2();
    private DlgCariBangsal caribangsal = new DlgCariBangsal(null, false);
    public DlgAturanPakai aturanpakai = new DlgAturanPakai(null, false);

    /**
     * Creates new form DlgPenyakit Creates new form DlgPenyakit
     *
     * @param parent
     * @param modal
     */
    public DlgCariObat2(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(10, 2);
        setSize(656, 250);

        Object[] row = {"K", "Jumlah", "Kode", "Nama Barang", "Satuan", "Letak Barang",
            "Harga(Rp)", "Jenis Obat", "Embalase", "Tuslah", "Stok", "I.F.", "H.Beli",
            "Aturan Pakai", "Kategori", "Golongan"
        };
        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if ((colIndex == 0) || (colIndex == 1) || (colIndex == 8) || (colIndex == 9) || (colIndex == 13)) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Double.class,
                java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class,
                java.lang.Object.class, java.lang.Double.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbObat.setModel(tabMode);
        //tbPenyakit.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbPenyakit.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 16; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(45);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            } else if (i == 4) {
                column.setPreferredWidth(70);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(70);
            } else if (i == 7) {
                column.setPreferredWidth(70);
            } else if (i == 8) {
                column.setPreferredWidth(55);
            } else if (i == 9) {
                column.setPreferredWidth(55);
            } else if (i == 10) {
                column.setPreferredWidth(35);
            } else if (i == 11) {
                column.setPreferredWidth(110);
            } else if (i == 12) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 13) {
                column.setPreferredWidth(100);
            } else if (i == 14) {
                column.setPreferredWidth(100);
            } else if (i == 15) {
                column.setPreferredWidth(100);
            }
        }
        warna.kolom = 1;
        tbObat.setDefaultRenderer(Object.class, warna);
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    tampil();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    tampil();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    tampil();
                }
            });
        }
        jam();

        aturanpakai.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (aturanpakai.getTable().getSelectedRow() != -1) {
                    tbObat.setValueAt(aturanpakai.getTable().getValueAt(aturanpakai.getTable().getSelectedRow(), 0).toString(), tbObat.getSelectedRow(), 13);
                }
                tbObat.requestFocus();
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

        try {
            psrekening = koneksi.prepareStatement("select * from set_akun_ranap");
            try {
                rsrekening = psrekening.executeQuery();
                while (rsrekening.next()) {
                    Suspen_Piutang_Obat_Ranap = rsrekening.getString("Suspen_Piutang_Obat_Ranap");
                    Obat_Ranap = rsrekening.getString("Obat_Ranap");
                    HPP_Obat_Rawat_Inap = rsrekening.getString("HPP_Obat_Rawat_Inap");
                    Persediaan_Obat_Rawat_Inap = rsrekening.getString("Persediaan_Obat_Rawat_Inap");
                }
            } catch (Exception e) {
                System.out.println("Notif Rekening : " + e);
            } finally {
                if (rsrekening != null) {
                    rsrekening.close();
                }
                if (psrekening != null) {
                    psrekening.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
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

        Popup = new javax.swing.JPopupMenu();
        ppBersihkan = new javax.swing.JMenuItem();
        ppStok = new javax.swing.JMenuItem();
        TNoRw = new widget.TextBox();
        KdPj = new widget.TextBox();
        kelas = new widget.TextBox();
        TStok = new widget.TextBox();
        TNoRm = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        panelisi3 = new widget.panelisi();
        label9 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        BtnTambah = new widget.Button();
        BtnSeek5 = new widget.Button();
        BtnSimpan = new widget.Button();
        label13 = new widget.Label();
        BtnKeluar = new widget.Button();
        FormInput = new widget.PanelBiasa();
        jLabel5 = new widget.Label();
        DTPTgl = new widget.Tanggal();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        ChkJln = new widget.CekBox();
        label12 = new widget.Label();
        Jeniskelas = new widget.ComboBox();
        ChkNoResep = new widget.CekBox();

        Popup.setName("Popup"); // NOI18N

        ppBersihkan.setBackground(new java.awt.Color(255, 255, 255));
        ppBersihkan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBersihkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBersihkan.setText("Bersihkan Jumlah");
        ppBersihkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBersihkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBersihkan.setIconTextGap(8);
        ppBersihkan.setName("ppBersihkan"); // NOI18N
        ppBersihkan.setPreferredSize(new java.awt.Dimension(200, 25));
        ppBersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBersihkanActionPerformed(evt);
            }
        });
        Popup.add(ppBersihkan);

        ppStok.setBackground(new java.awt.Color(255, 255, 255));
        ppStok.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppStok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppStok.setText("Tampilkan Semua Stok");
        ppStok.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppStok.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppStok.setIconTextGap(8);
        ppStok.setName("ppStok"); // NOI18N
        ppStok.setPreferredSize(new java.awt.Dimension(200, 25));
        ppStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppStokActionPerformed(evt);
            }
        });
        Popup.add(ppStok);

        TNoRw.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N

        KdPj.setHighlighter(null);
        KdPj.setName("KdPj"); // NOI18N
        KdPj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPjKeyPressed(evt);
            }
        });

        kelas.setHighlighter(null);
        kelas.setName("kelas"); // NOI18N
        kelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kelasKeyPressed(evt);
            }
        });

        TStok.setHighlighter(null);
        TStok.setName("TStok"); // NOI18N
        TStok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TStokKeyPressed(evt);
            }
        });

        TNoRm.setHighlighter(null);
        TNoRm.setName("TNoRm"); // NOI18N
        TNoRm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRmKeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Obat, Alkes & BHP Medis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(Popup);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbObat.setForeground(new java.awt.Color(0, 0, 0));
        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(Popup);
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbObat);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 43));
        panelisi3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label9.setForeground(new java.awt.Color(0, 0, 0));
        label9.setText("Key Word :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(68, 23));
        panelisi3.add(label9);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setToolTipText("Alt+C");
        TCari.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(285, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi3.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setToolTipText("Alt+2");
        BtnCari.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(28, 23));
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
        panelisi3.add(BtnCari);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('2');
        BtnAll.setToolTipText("Alt+2");
        BtnAll.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(28, 23));
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
        panelisi3.add(BtnAll);

        BtnTambah.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambah.setMnemonic('3');
        BtnTambah.setToolTipText("Alt+3");
        BtnTambah.setName("BtnTambah"); // NOI18N
        BtnTambah.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahActionPerformed(evt);
            }
        });
        panelisi3.add(BtnTambah);

        BtnSeek5.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/011.png"))); // NOI18N
        BtnSeek5.setMnemonic('5');
        BtnSeek5.setToolTipText("Alt+5");
        BtnSeek5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BtnSeek5.setName("BtnSeek5"); // NOI18N
        BtnSeek5.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek5ActionPerformed(evt);
            }
        });
        BtnSeek5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek5KeyPressed(evt);
            }
        });
        panelisi3.add(BtnSeek5);

        BtnSimpan.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        panelisi3.add(BtnSimpan);

        label13.setForeground(new java.awt.Color(0, 0, 0));
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(50, 23));
        panelisi3.add(label13);

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('4');
        BtnKeluar.setToolTipText("Alt+4");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        panelisi3.add(BtnKeluar);

        internalFrame1.add(panelisi3, java.awt.BorderLayout.PAGE_END);

        FormInput.setBackground(new java.awt.Color(215, 225, 215));
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 43));
        FormInput.setLayout(null);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tanggal :");
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setPreferredSize(new java.awt.Dimension(68, 23));
        FormInput.add(jLabel5);
        jLabel5.setBounds(4, 10, 68, 23);

        DTPTgl.setEditable(false);
        DTPTgl.setForeground(new java.awt.Color(0, 0, 0));
        DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-09-2018" }));
        DTPTgl.setDisplayFormat("dd-MM-yyyy");
        DTPTgl.setName("DTPTgl"); // NOI18N
        DTPTgl.setOpaque(false);
        DTPTgl.setPreferredSize(new java.awt.Dimension(100, 23));
        DTPTgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTglKeyPressed(evt);
            }
        });
        FormInput.add(DTPTgl);
        DTPTgl.setBounds(75, 10, 100, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.setOpaque(false);
        cmbJam.setPreferredSize(new java.awt.Dimension(50, 23));
        cmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJamKeyPressed(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(178, 10, 50, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.setOpaque(false);
        cmbMnt.setPreferredSize(new java.awt.Dimension(50, 23));
        cmbMnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMntKeyPressed(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(231, 10, 50, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.setOpaque(false);
        cmbDtk.setPreferredSize(new java.awt.Dimension(50, 23));
        cmbDtk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtkKeyPressed(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(284, 10, 50, 23);

        ChkJln.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(195, 215, 195)));
        ChkJln.setForeground(new java.awt.Color(0, 0, 0));
        ChkJln.setBorderPainted(true);
        ChkJln.setBorderPaintedFlat(true);
        ChkJln.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkJln.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkJln.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkJln.setName("ChkJln"); // NOI18N
        ChkJln.setPreferredSize(new java.awt.Dimension(22, 23));
        ChkJln.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkJlnActionPerformed(evt);
            }
        });
        FormInput.add(ChkJln);
        ChkJln.setBounds(337, 10, 22, 23);

        label12.setForeground(new java.awt.Color(0, 0, 0));
        label12.setText("Tarif :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(50, 23));
        FormInput.add(label12);
        label12.setBounds(362, 10, 50, 23);

        Jeniskelas.setForeground(new java.awt.Color(0, 0, 0));
        Jeniskelas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kelas 1", "Kelas 2", "Kelas 3", "Utama/BPJS", "VIP", "VVIP", "Beli Luar", "Karyawan" }));
        Jeniskelas.setName("Jeniskelas"); // NOI18N
        Jeniskelas.setPreferredSize(new java.awt.Dimension(100, 23));
        Jeniskelas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JeniskelasItemStateChanged(evt);
            }
        });
        Jeniskelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JeniskelasKeyPressed(evt);
            }
        });
        FormInput.add(Jeniskelas);
        Jeniskelas.setBounds(415, 10, 100, 23);

        ChkNoResep.setBorder(null);
        ChkNoResep.setForeground(new java.awt.Color(0, 0, 0));
        ChkNoResep.setSelected(true);
        ChkNoResep.setText("No.Resep   ");
        ChkNoResep.setBorderPainted(true);
        ChkNoResep.setBorderPaintedFlat(true);
        ChkNoResep.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkNoResep.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkNoResep.setName("ChkNoResep"); // NOI18N
        ChkNoResep.setOpaque(false);
        ChkNoResep.setPreferredSize(new java.awt.Dimension(85, 23));
        ChkNoResep.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkNoResepItemStateChanged(evt);
            }
        });
        FormInput.add(ChkNoResep);
        ChkNoResep.setBounds(518, 10, 85, 23);

        internalFrame1.add(FormInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbObat.requestFocus();
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
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari, TCari);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if (tbObat.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            tbObat.requestFocus();
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if (akses.getkdbangsal().equals("")) {
            akses.setkdbangsal(bangsal);
        }

        if (akses.getkdbangsal().equals("")) {
            Valid.textKosong(TCari, "Lokasi");
        } else if (tbObat.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                try {
                    getData();
                    i = tbObat.getSelectedColumn();
                    if (i == 2) {
                        try {
                            stokbarang = 0;
                            psstok = koneksi.prepareStatement("select ifnull(stok,'0') from gudangbarang where kd_bangsal=? and kode_brng=?");
                            try {
                                psstok.setString(1, akses.getkdbangsal());
                                psstok.setString(2, tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
                                rsstok = psstok.executeQuery();
                                if (rsstok.next()) {
                                    stokbarang = rsstok.getDouble(1);
                                }
                            } catch (Exception e) {
                                stokbarang = 0;
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsstok != null) {
                                    rsstok.close();
                                }
                                if (psstok != null) {
                                    psstok.close();
                                }
                            }

                            tbObat.setValueAt(stokbarang, tbObat.getSelectedRow(), 10);
                            y = 0;
                            try {
                                y = Double.parseDouble(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString());
                                y = Math.round(Double.parseDouble(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString()));
                                tbObat.setValueAt(y,tbObat.getSelectedRow(), 1);
                            } catch (Exception e) {
                                y = 0;
                            }
                            if (stokbarang < y) {
                                JOptionPane.showMessageDialog(rootPane, "Maaf stok tidak mencukupi..!!");
                                tbObat.setValueAt("", tbObat.getSelectedRow(), 1);
                            }
                            tbObat.setValueAt(embalase, tbObat.getSelectedRow(), 8);
                            tbObat.setValueAt(tuslah, tbObat.getSelectedRow(), 9);
                        } catch (Exception e) {
                            tbObat.setValueAt(0, tbObat.getSelectedRow(), 10);
                        }
                        TCari.setText("");
                        TCari.requestFocus();
                    } else if (i == 8) {
                        try {
                            if (tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString().equals("0") || tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString().equals("") || tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString().equals("0.0") || tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString().equals("0,0")) {
                                tbObat.setValueAt(embalase, tbObat.getSelectedRow(), 8);
                            }
                        } catch (Exception e) {
                            tbObat.setValueAt(0, tbObat.getSelectedRow(), 8);
                        }
                    } else if (i == 9) {
                        try {
                            if (tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString().equals("0") || tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString().equals("") || tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString().equals("0.0") || tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString().equals("0,0")) {
                                tbObat.setValueAt(tuslah, tbObat.getSelectedRow(), 9);
                            }
                        } catch (Exception e) {
                            tbObat.setValueAt(0, tbObat.getSelectedRow(), 9);
                        }

                        TCari.setText("");
                        TCari.requestFocus();
                    } else if ((i == 10) || (i == 3)) {
                        TCari.setText("");
                        TCari.requestFocus();
                    }
                } catch (java.lang.NullPointerException e) {
                }
            } else if ((evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                if (tbObat.getSelectedRow() != -1) {
                    tbObat.setValueAt("", tbObat.getSelectedRow(), 1);
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                TCari.requestFocus();
            } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                i = tbObat.getSelectedColumn();
                if (i == 2) {
                    try {
                        stokbarang = 0;
                        psstok = koneksi.prepareStatement("select ifnull(stok,'0') from gudangbarang where kd_bangsal=? and kode_brng=?");
                        try {
                            psstok.setString(1, akses.getkdbangsal());
                            psstok.setString(2, tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
                            rsstok = psstok.executeQuery();
                            if (rsstok.next()) {
                                stokbarang = rsstok.getDouble(1);
                            }
                        } catch (Exception e) {
                            stokbarang = 0;
                            System.out.println("Notifikasi : " + e);
                        } finally {
                            if (rsstok != null) {
                                rsstok.close();
                            }
                            if (psstok != null) {
                                psstok.close();
                            }
                        }

                        tbObat.setValueAt(stokbarang, tbObat.getSelectedRow(), 10);

                        y = 0;
                        try {
                            y = Double.parseDouble(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString());
                        } catch (Exception e) {
                            System.out.println(e);
                            y = 0;
                        }

                        if (stokbarang < y) {
                            JOptionPane.showMessageDialog(rootPane, "Maaf stok tidak mencukupi..!!");
                            tbObat.setValueAt("", tbObat.getSelectedRow(), 1);
                        }

                        try {
                            if (tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString().equals("0") || tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString().equals("") || tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString().equals("0.0") || tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString().equals("0,0")) {
                                tbObat.setValueAt(embalase, tbObat.getSelectedRow(), 8);
                            }
                        } catch (Exception e) {
                            tbObat.setValueAt(0, tbObat.getSelectedRow(), 8);
                        }

                        try {
                            if (tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString().equals("0") || tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString().equals("") || tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString().equals("0.0") || tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString().equals("0,0")) {
                                tbObat.setValueAt(tuslah, tbObat.getSelectedRow(), 9);
                            }
                        } catch (Exception e) {
                            tbObat.setValueAt(0, tbObat.getSelectedRow(), 9);
                        }

                    } catch (Exception e) {
                        tbObat.setValueAt(0, tbObat.getSelectedRow(), 10);
                    }
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        //barang.setModal(true);
        barang.emptTeks();
        barang.isCek();
        barang.setSize(internalFrame1.getWidth() + 40, internalFrame1.getHeight() + 40);
        barang.setLocationRelativeTo(internalFrame1);
        barang.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnTambahActionPerformed

private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
    if (akses.getkdbangsal().equals("")) {
        akses.setkdbangsal(bangsal);
    }

    if (TNoRw.getText().trim().equals("")) {
        Valid.textKosong(TCari, "Data");
    } else if (akses.getkdbangsal().equals("")) {
        Valid.textKosong(TCari, "Lokasi");
    } else {
        try {
            isSetBangsal();
            koneksi.setAutoCommit(false);
            ttlhpp = 0;
            ttljual = 0;
            urut = 0;
            for (i = 0; i < tbObat.getRowCount(); i++) {
                if (Valid.SetAngka(tbObat.getValueAt(i, 1).toString()) > 0) {
                    if ((tbObat.getValueAt(i, 1).toString()) == null || (tbObat.getValueAt(i, 1).toString()).equals("")) {
                        y = 0;
                    } else {
                        y = Double.parseDouble(tbObat.getValueAt(i, 1).toString());
                    }
                    isStok(tbObat.getValueAt(i, 2).toString());
                    if (TStok.getText().equals("")) {
                        stokbarang2 = 0;
                    } else {
                        stokbarang2 = Double.parseDouble(TStok.getText());
                    }

                    if (stokbarang2 < y || TStok.getText().equals("")) {
                        JOptionPane.showMessageDialog(rootPane, "Maaf stok tidak mencukupi..!!");
                        tbObat.setValueAt("", tbObat.getSelectedRow(), 1);
                        ppBersihkanActionPerformed(evt);
                    } else {
                        urut++;
                        if (tbObat.getValueAt(i, 0).toString().equals("true")) {
                            pscarikapasitas = koneksi.prepareStatement("select IFNULL(kapasitas,1) from databarang where kode_brng=?");
                            try {
                                pscarikapasitas.setString(1, tbObat.getValueAt(i, 2).toString());
                                carikapasitas = pscarikapasitas.executeQuery();
                                if (carikapasitas.next()) {
//                                    if (Sequel.menyimpantf2("detail_pemberian_obat", "?,?,?,?,?,?,?,?,?,?,?,?", "data", 12, new String[]{
//                                        Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), TNoRw.getText(), tbObat.getValueAt(i, 2).toString(), tbObat.getValueAt(i, 12).toString(),
//                                        tbObat.getValueAt(i, 6).toString(), "" + (Double.parseDouble(tbObat.getValueAt(i, 1).toString()) / carikapasitas.getDouble(1)),
//                                        tbObat.getValueAt(i, 8).toString(), tbObat.getValueAt(i, 9).toString(), "" + Valid.SetAngka2(Double.parseDouble(tbObat.getValueAt(i, 8).toString())
//                                        + Double.parseDouble(tbObat.getValueAt(i, 9).toString()) + (Double.parseDouble(tbObat.getValueAt(i, 6).toString())
//                                        * (Double.parseDouble(tbObat.getValueAt(i, 1).toString()) / carikapasitas.getDouble(1)))), "Ranap", bangsal
                                    if (Sequel.menyimpantf2("detail_pemberian_obat", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "data", 15, new String[]{
                                        Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), TNoRw.getText(), tbObat.getValueAt(i, 2).toString(), tbObat.getValueAt(i, 12).toString(),
                                        tbObat.getValueAt(i, 6).toString(), "" + (Double.parseDouble(tbObat.getValueAt(i, 1).toString()) / carikapasitas.getDouble(1)),
                                        tbObat.getValueAt(i, 8).toString(), tbObat.getValueAt(i, 9).toString(), "" + (Double.parseDouble(tbObat.getValueAt(i, 8).toString())
                                        + Double.parseDouble(tbObat.getValueAt(i, 9).toString()) + (Double.parseDouble(tbObat.getValueAt(i, 6).toString())
                                        * (Double.parseDouble(tbObat.getValueAt(i, 1).toString()) / carikapasitas.getDouble(1)))), "Ranap", bangsal, "Belum", "-",String.valueOf(urut)
                                    }) == true) {
                                        isRawat();
                                        ttljual = ttljual + Double.parseDouble(tbObat.getValueAt(i, 8).toString())
                                                + Double.parseDouble(tbObat.getValueAt(i, 9).toString()) + (Double.parseDouble(tbObat.getValueAt(i, 6).toString())
                                                * (Double.parseDouble(tbObat.getValueAt(i, 1).toString()) / carikapasitas.getDouble(1)));
                                        ttlhpp = ttlhpp + (Double.parseDouble(tbObat.getValueAt(i, 12).toString())
                                                * (Double.parseDouble(tbObat.getValueAt(i, 1).toString()) / carikapasitas.getDouble(1)));
                                        Trackobat.catatRiwayat(tbObat.getValueAt(i, 2).toString(), 0, (Double.parseDouble(tbObat.getValueAt(i, 1).toString()) / carikapasitas.getDouble(1)), "Pemberian Obat", akses.getkode(), bangsal, "Simpan", Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
                                        Trackobat.catatRiwayatObat(tbObat.getValueAt(i, 2).toString(), 0, Double.parseDouble(tbObat.getValueAt(i, 1).toString()), "Pemberian Obat", akses.getkode(), bangsal, "Simpan", TNoRm.getText(), TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
                                        Sequel.menyimpan("gudangbarang", "'" + tbObat.getValueAt(i, 2).toString() + "','" + bangsal + "','-" + (Double.parseDouble(tbObat.getValueAt(i, 1).toString()) / carikapasitas.getDouble(1)) + "'",
                                                "stok=stok-'" + (Double.parseDouble(tbObat.getValueAt(i, 1).toString()) / carikapasitas.getDouble(1)) + "'", "kode_brng='" + tbObat.getValueAt(i, 2).toString() + "' and kd_bangsal='" + bangsal + "'");
                                    } else {
                                        //JOptionPane.showMessageDialog(null,"Gagal Menyimpan, Kemungkinan ada data sama/kapasitas tidak ditemukan..ATAS = !!" + tbObat.getValueAt(i,2).toString());
                                        JOptionPane.showMessageDialog(null, "Gagal Menyimpan, Kemungkinan ada data sama/kapasitas tidak ditemukan..!!");
                                    }
                                } else {
//                                    if (Sequel.menyimpantf("detail_pemberian_obat", "?,?,?,?,?,?,?,?,?,?,?,?", "data", 12, new String[]{
//                                        Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), TNoRw.getText(), tbObat.getValueAt(i, 2).toString(), tbObat.getValueAt(i, 12).toString(),
//                                        tbObat.getValueAt(i, 6).toString(), "" + Double.parseDouble(tbObat.getValueAt(i, 1).toString()),
//                                        tbObat.getValueAt(i, 8).toString(), tbObat.getValueAt(i, 9).toString(), "" + (Double.parseDouble(tbObat.getValueAt(i, 8).toString())
//                                        + Double.parseDouble(tbObat.getValueAt(i, 9).toString()) + (Double.parseDouble(tbObat.getValueAt(i, 6).toString())
//                                        * Double.parseDouble(tbObat.getValueAt(i, 1).toString()))), "Ranap", bangsal
                                    if (Sequel.menyimpantf("detail_pemberian_obat", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "data", 15, new String[]{
                                        Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), TNoRw.getText(), tbObat.getValueAt(i, 2).toString(), tbObat.getValueAt(i, 12).toString(),
                                        tbObat.getValueAt(i, 6).toString(), "" + Double.parseDouble(tbObat.getValueAt(i, 1).toString()),
                                        tbObat.getValueAt(i, 8).toString(), tbObat.getValueAt(i, 9).toString(), "" + (Double.parseDouble(tbObat.getValueAt(i, 8).toString())
                                        + Double.parseDouble(tbObat.getValueAt(i, 9).toString()) + (Double.parseDouble(tbObat.getValueAt(i, 6).toString())
                                        * Double.parseDouble(tbObat.getValueAt(i, 1).toString()))), "Ranap", bangsal, "Belum", "-",String.valueOf(urut)
                                    }) == true) {
                                        isRawat();
                                        ttljual = ttljual + Double.parseDouble(tbObat.getValueAt(i, 8).toString())
                                                + Double.parseDouble(tbObat.getValueAt(i, 9).toString()) + (Double.parseDouble(tbObat.getValueAt(i, 6).toString())
                                                * Double.parseDouble(tbObat.getValueAt(i, 1).toString()));
                                        ttlhpp = ttlhpp + (Double.parseDouble(tbObat.getValueAt(i, 12).toString())
                                                * Double.parseDouble(tbObat.getValueAt(i, 1).toString()));
                                        Trackobat.catatRiwayat(tbObat.getValueAt(i, 2).toString(), 0, Double.parseDouble(tbObat.getValueAt(i, 1).toString()), "Pemberian Obat", akses.getkode(), bangsal, "Simpan", Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
                                        Trackobat.catatRiwayatObat(tbObat.getValueAt(i, 2).toString(), 0, Double.parseDouble(tbObat.getValueAt(i, 1).toString()), "Pemberian Obat", akses.getkode(), bangsal, "Simpan", TNoRm.getText(), TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
                                        Sequel.menyimpan("gudangbarang", "'" + tbObat.getValueAt(i, 2).toString() + "','" + bangsal + "','-" + Double.parseDouble(tbObat.getValueAt(i, 1).toString()) + "'",
                                                "stok=stok-'" + Double.parseDouble(tbObat.getValueAt(i, 1).toString()) + "'", "kode_brng='" + tbObat.getValueAt(i, 2).toString() + "' and kd_bangsal='" + bangsal + "'");
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi Kapasitas : " + e);
                            } finally {
                                if (carikapasitas != null) {
                                    carikapasitas.close();
                                }
                                if (pscarikapasitas != null) {
                                    pscarikapasitas.close();
                                }
                            }
                        } else {
//                            if (Sequel.menyimpantf("detail_pemberian_obat", "?,?,?,?,?,?,?,?,?,?,?,?", "data", 12, new String[]{
//                                Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), TNoRw.getText(), tbObat.getValueAt(i, 2).toString(), tbObat.getValueAt(i, 12).toString(),
//                                tbObat.getValueAt(i, 6).toString(), "" + Double.parseDouble(tbObat.getValueAt(i, 1).toString()),
//                                tbObat.getValueAt(i, 8).toString(), tbObat.getValueAt(i, 9).toString(), "" + (Double.parseDouble(tbObat.getValueAt(i, 8).toString())
//                                + Double.parseDouble(tbObat.getValueAt(i, 9).toString()) + (Double.parseDouble(tbObat.getValueAt(i, 6).toString())
//                                * Double.parseDouble(tbObat.getValueAt(i, 1).toString()))), "Ranap", bangsal
                            if (Sequel.menyimpantf("detail_pemberian_obat", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "data", 15, new String[]{
                                Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), TNoRw.getText(), tbObat.getValueAt(i, 2).toString(), tbObat.getValueAt(i, 12).toString(),
                                tbObat.getValueAt(i, 6).toString(), "" + Double.parseDouble(tbObat.getValueAt(i, 1).toString()),
                                tbObat.getValueAt(i, 8).toString(), tbObat.getValueAt(i, 9).toString(), "" + (Double.parseDouble(tbObat.getValueAt(i, 8).toString())
                                + Double.parseDouble(tbObat.getValueAt(i, 9).toString()) + (Double.parseDouble(tbObat.getValueAt(i, 6).toString())
                                * Double.parseDouble(tbObat.getValueAt(i, 1).toString()))), "Ranap", bangsal, "Belum", "-",String.valueOf(urut)
                            }) == true) {
                                isRawat();
                                ttljual = ttljual + Valid.roundUp(Double.parseDouble(tbObat.getValueAt(i, 8).toString())
                                        + Double.parseDouble(tbObat.getValueAt(i, 9).toString()) + (Double.parseDouble(tbObat.getValueAt(i, 6).toString())
                                        * Double.parseDouble(tbObat.getValueAt(i, 1).toString())), 100);
                                ttlhpp = ttlhpp + (Double.parseDouble(tbObat.getValueAt(i, 12).toString())
                                        * Double.parseDouble(tbObat.getValueAt(i, 1).toString()));
                                Trackobat.catatRiwayat(tbObat.getValueAt(i, 2).toString(), 0, Double.parseDouble(tbObat.getValueAt(i, 1).toString()), "Pemberian Obat", akses.getkode(), bangsal, "Simpan", Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
                                Trackobat.catatRiwayatObat(tbObat.getValueAt(i, 2).toString(), 0, Double.parseDouble(tbObat.getValueAt(i, 1).toString()), "Pemberian Obat", akses.getkode(), bangsal, "Simpan", TNoRm.getText(), TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
                                Sequel.menyimpan("gudangbarang", "'" + tbObat.getValueAt(i, 2).toString() + "','" + bangsal + "','-" + Double.parseDouble(tbObat.getValueAt(i, 1).toString()) + "'",
                                        "stok=stok-'" + Double.parseDouble(tbObat.getValueAt(i, 1).toString()) + "'", "kode_brng='" + tbObat.getValueAt(i, 2).toString() + "' and kd_bangsal='" + bangsal + "'");
                            }
                        }
                    }
                    tbObat.setValueAt("", i, 1);
                }

            }
            Sequel.queryu("delete from tampjurnal");
            if (ttljual > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Obat_Ranap + "','Suspen Piutang Obat Ranap','" + ttljual + "','0'", "Rekening");
                Sequel.menyimpan("tampjurnal", "'" + Obat_Ranap + "','Pendapatan Obat Rawat Inap','0','" + ttljual + "'", "Rekening");
            }
            if (ttlhpp > 0) {
                Sequel.menyimpan("tampjurnal", "'" + HPP_Obat_Rawat_Inap + "','HPP Persediaan Obat Rawat Inap','" + ttlhpp + "','0'", "Rekening");
                Sequel.menyimpan("tampjurnal", "'" + Persediaan_Obat_Rawat_Inap + "','Persediaan Obat Rawat Inap','0','" + ttlhpp + "'", "Rekening");
            }
            jur.simpanJurnal(TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), "U", "PEMBERIAN OBAT RAWAT INAP PASIEN, DIPOSTING OLEH " + akses.getkode());

            koneksi.setAutoCommit(true);
            if (ChkNoResep.isSelected() == true) {
                DlgResepObat resep = new DlgResepObat(null, false);
                resep.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                resep.setLocationRelativeTo(internalFrame1);
                resep.emptTeks();
                resep.isCek();
                resep.setNoRm(TNoRw.getText(), DTPTgl.getDate(), DTPTgl.getDate(), cmbJam.getSelectedItem().toString(), cmbMnt.getSelectedItem().toString(), cmbDtk.getSelectedItem().toString());
                resep.tampil();
                resep.setAlwaysOnTop(true);
                resep.dokter.setAlwaysOnTop(true);
                resep.setVisible(true);
//                resep.setStatus(status);
            } else {
                dispose();
            }
            dispose();
        } catch (Exception ex) {
            System.out.println(ex);
            //JOptionPane.showMessageDialog(null,"Maaf, gagal menyimpan data. Kemungkinan ada data yang sama dimasukkan sebelumnya?\nKapasitas belum dimasukkan...!BAWAH");
            JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada data yang sama dimasukkan sebelumnya?\nKapasitas belum dimasukkan...!");
        }
    }
}//GEN-LAST:event_BtnSimpanActionPerformed

private void BtnSeek5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek5ActionPerformed
    DlgCariKonversi carikonversi = new DlgCariKonversi(null, false);
    carikonversi.setLocationRelativeTo(internalFrame1);
    carikonversi.setVisible(true);
}//GEN-LAST:event_BtnSeek5ActionPerformed

private void BtnSeek5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek5KeyPressed
// TODO add your handling code here:
}//GEN-LAST:event_BtnSeek5KeyPressed

private void ppBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBersihkanActionPerformed
    for (i = 0; i < tbObat.getRowCount(); i++) {
        tbObat.setValueAt("", i, 1);
        tbObat.setValueAt(0, i, 10);
        tbObat.setValueAt(0, i, 9);
        tbObat.setValueAt(0, i, 8);
    }
}//GEN-LAST:event_ppBersihkanActionPerformed

private void JeniskelasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JeniskelasItemStateChanged
    tampil();
}//GEN-LAST:event_JeniskelasItemStateChanged

private void JeniskelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JeniskelasKeyPressed
    Valid.pindah(evt, cmbDtk, TCari);
}//GEN-LAST:event_JeniskelasKeyPressed

private void DTPTglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTglKeyPressed
    Valid.pindah(evt, BtnKeluar, cmbJam);
}//GEN-LAST:event_DTPTglKeyPressed

private void cmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJamKeyPressed
    Valid.pindah(evt, DTPTgl, cmbMnt);
}//GEN-LAST:event_cmbJamKeyPressed

private void cmbMntKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMntKeyPressed
    Valid.pindah(evt, cmbJam, cmbDtk);
}//GEN-LAST:event_cmbMntKeyPressed

private void cmbDtkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtkKeyPressed
    Valid.pindah(evt, cmbMnt, Jeniskelas);
}//GEN-LAST:event_cmbDtkKeyPressed

private void ChkJlnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJlnActionPerformed
    // TODO add your handling code here:
}//GEN-LAST:event_ChkJlnActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Sequel.insertClosingStok();
        emptTeks();
        embalase = Sequel.cariIsiAngka("select embalase_per_obat from set_embalase");
        tuslah = Sequel.cariIsiAngka("select tuslah_per_obat from set_embalase");
        isSetBangsal();
    }//GEN-LAST:event_formWindowActivated

    private void ChkNoResepItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkNoResepItemStateChanged
        if (ChkNoResep.isSelected() == true) {
            DlgResepObat resep = new DlgResepObat(null, false);
            resep.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            resep.setLocationRelativeTo(internalFrame1);
            resep.emptTeks();
            resep.isCek();
            resep.setNoRm(TNoRw.getText(), DTPTgl.getDate(), DTPTgl.getDate(), cmbJam.getSelectedItem().toString(), cmbMnt.getSelectedItem().toString(), cmbDtk.getSelectedItem().toString());
            resep.tampil();
            resep.setVisible(true);
        }
    }//GEN-LAST:event_ChkNoResepItemStateChanged

    private void KdPjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPjKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdPjKeyPressed

    private void kelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kelasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kelasKeyPressed

    private void ppStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppStokActionPerformed
        if (akses.getkdbangsal().equals("")) {
            akses.setkdbangsal(bangsal);
        }
        if (akses.getkdbangsal().equals("")) {
            Valid.textKosong(TCari, "Lokasi");
        } else {
            isSetBangsal();
            for (i = 0; i < tbObat.getRowCount(); i++) {
                try {
                    stokbarang = 0;
                    psstok = koneksi.prepareStatement("select ifnull(stok,'0') from gudangbarang where kd_bangsal=? and kode_brng=?");
                    try {
                        psstok.setString(1, akses.getkdbangsal());
                        psstok.setString(2, tbObat.getValueAt(i, 2).toString());
                        rsstok = psstok.executeQuery();
                        if (rsstok.next()) {
                            stokbarang = rsstok.getDouble(1);
                        }
                    } catch (Exception e) {
                        stokbarang = 0;
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsstok != null) {
                            rsstok.close();
                        }
                        if (psstok != null) {
                            psstok.close();
                        }
                    }
                    tbObat.setValueAt(stokbarang, i, 10);
                } catch (Exception e) {
                    tbObat.setValueAt(0, i, 10);
                }
            }
        }
    }//GEN-LAST:event_ppStokActionPerformed

    private void TStokKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TStokKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TStokKeyPressed

    private void TNoRmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRmKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNoRmKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCariObat2 dialog = new DlgCariObat2(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Button BtnSeek5;
    private widget.Button BtnSimpan;
    private widget.Button BtnTambah;
    private widget.CekBox ChkJln;
    private widget.CekBox ChkNoResep;
    private widget.Tanggal DTPTgl;
    private widget.PanelBiasa FormInput;
    private widget.ComboBox Jeniskelas;
    private widget.TextBox KdPj;
    private javax.swing.JPopupMenu Popup;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TNoRm;
    private widget.TextBox TNoRw;
    private widget.TextBox TStok;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbMnt;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel5;
    private widget.TextBox kelas;
    private widget.Label label12;
    private widget.Label label13;
    private widget.Label label9;
    private widget.panelisi panelisi3;
    private javax.swing.JMenuItem ppBersihkan;
    private javax.swing.JMenuItem ppStok;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        isSetBangsal();
        jml = 0;
        for (i = 0; i < tbObat.getRowCount(); i++) {
            if (!tbObat.getValueAt(i, 0).toString().equals("")) {
                jml++;
            }
        }

        pilih = null;
        pilih = new boolean[jml];
        jumlah = null;
        jumlah = new double[jml];
        eb = null;
        eb = new double[jml];
        ts = null;
        ts = new double[jml];
        stok = null;
        stok = new double[jml];
        harga = null;
        harga = new double[jml];
        kodebarang = null;
        kodebarang = new String[jml];
        namabarang = null;
        namabarang = new String[jml];
        kodesatuan = null;
        kodesatuan = new String[jml];
        letakbarang = null;
        letakbarang = new String[jml];
        namajenis = null;
        namajenis = new String[jml];
        industri = null;
        industri = new String[jml];
        beli = null;
        beli = new double[jml];
        aturan = null;
        aturan = new String[jml];
        kategori = null;
        kategori = new String[jml];
        golongan = null;
        golongan = new String[jml];

        jml = 0;
        for (i = 0; i < tbObat.getRowCount(); i++) {
            if (!tbObat.getValueAt(i, 1).toString().equals("")) {
                pilih[jml] = Boolean.parseBoolean(tbObat.getValueAt(i, 0).toString());
                try {
                    jumlah[jml] = Double.parseDouble(tbObat.getValueAt(i, 1).toString());
                } catch (Exception e) {
                    jumlah[jml] = 0;
                }
                kodebarang[jml] = tbObat.getValueAt(i, 2).toString();
                namabarang[jml] = tbObat.getValueAt(i, 3).toString();
                kodesatuan[jml] = tbObat.getValueAt(i, 4).toString();
                letakbarang[jml] = tbObat.getValueAt(i, 5).toString();
                try {
                    harga[jml] = Double.parseDouble(tbObat.getValueAt(i, 6).toString());
                } catch (Exception e) {
                    harga[jml] = 0;
                }
                namajenis[jml] = tbObat.getValueAt(i, 7).toString();
                try {
                    eb[jml] = Double.parseDouble(tbObat.getValueAt(i, 8).toString());
                } catch (Exception e) {
                    eb[jml] = 0;
                }
                try {
                    ts[jml] = Double.parseDouble(tbObat.getValueAt(i, 9).toString());
                } catch (Exception e) {
                    ts[jml] = 0;
                }
                try {
                    stok[jml] = Double.parseDouble(tbObat.getValueAt(i, 10).toString());
                } catch (Exception e) {
                    stok[jml] = 0;
                }
                industri[jml] = tbObat.getValueAt(i, 11).toString();
                try {
                    beli[jml] = Double.parseDouble(tbObat.getValueAt(i, 12).toString());
                } catch (Exception e) {
                    beli[jml] = 0;
                }
                aturan[jml] = tbObat.getValueAt(i, 13).toString();
                kategori[jml] = tbObat.getValueAt(i, 14).toString();
                golongan[jml] = tbObat.getValueAt(i, 15).toString();
                jml++;
            }
        }

        Valid.tabelKosong(tabMode);

        for (i = 0; i < jml; i++) {
            tabMode.addRow(new Object[]{pilih[i], jumlah[i], kodebarang[i], namabarang[i],
                kodesatuan[i], letakbarang[i], harga[i], namajenis[i], eb[i], ts[i], stok[i], industri[i],
                beli[i], aturan[i], kategori[i], golongan[i]
            });
        }

        try {
            if (kenaikan > 0) {
//                if (var.getkdbangsal().equals("APT01") || var.getkdbangsal().equals("APT02")) {
//                    psobatasuransi = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,(databarang.h_beli+(databarang.h_beli*?)) as harga,"
//                            + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi on databarang.kdjns=jenis.kdjns "
//                            + " and industrifarmasi.kode_industri=databarang.kode_industri inner join golongan_barang inner join kategori_barang on databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode where databarang.nama_brng not like '(FR)%' and (databarang.status='1' and databarang.kode_brng like ? or "
//                            + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and databarang.nama_brng like ? or "
//                            + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and kategori_barang.nama like ? or "
//                            + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and golongan_barang.nama like ? or "
//                            + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and jenis.nama like ?) order by databarang.nama_brng");
//                } else {
//                    psobatasuransi = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,(databarang.h_beli+(databarang.h_beli*?)) as harga,"
//                            + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi on databarang.kdjns=jenis.kdjns "
//                            + " and industrifarmasi.kode_industri=databarang.kode_industri inner join golongan_barang inner join kategori_barang on databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode where databarang.status='1' and databarang.nama_brng not like '(FR)%' and databarang.kode_brng like ? or "
//                            + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and databarang.nama_brng like ? or "
//                            + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and kategori_barang.nama like ? or "
//                            + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and golongan_barang.nama like ? or "
//                            + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and jenis.nama like ? order by databarang.nama_brng");
//                }
                
//                if (var.getkdbangsal().equals("APT01") || var.getkdbangsal().equals("APT02")) {
//                    psobatasuransi = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,(databarang.h_beli+(databarang.h_beli*?)) as harga,"
//                            + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi on databarang.kdjns=jenis.kdjns "
//                            + " and industrifarmasi.kode_industri=databarang.kode_industri inner join golongan_barang inner join kategori_barang on databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode where (databarang.status='1' and databarang.kode_brng like ? or "
//                            + " databarang.status='1' and databarang.nama_brng like ? or "
//                            + " databarang.status='1' and kategori_barang.nama like ? or "
//                            + " databarang.status='1' and golongan_barang.nama like ? or "
//                            + " databarang.status='1' and jenis.nama like ?) order by databarang.nama_brng");
//                } else {
//                    psobatasuransi = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,(databarang.h_beli+(databarang.h_beli*?)) as harga,"
//                            + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi on databarang.kdjns=jenis.kdjns "
//                            + " and industrifarmasi.kode_industri=databarang.kode_industri inner join golongan_barang inner join kategori_barang on databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode where databarang.status='1' and databarang.kode_brng like ? or "
//                            + " databarang.status='1' and databarang.nama_brng like ? or "
//                            + " databarang.status='1' and kategori_barang.nama like ? or "
//                            + " databarang.status='1' and golongan_barang.nama like ? or "
//                            + " databarang.status='1' and jenis.nama like ? order by databarang.nama_brng");
//                }
                if (akses.getkdbangsal().equals("APT07")) {
                    psobatasuransi = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,(databarang.h_beli+(databarang.h_beli*?)) as harga,"
                            + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi on databarang.kdjns=jenis.kdjns "
                            + " and industrifarmasi.kode_industri=databarang.kode_industri inner join golongan_barang inner join kategori_barang on databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode where databarang.status='1' and databarang.nama_brng not like '(FR)%' and databarang.kode_brng like ? or "
                            + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and databarang.nama_brng like ? or "
                            + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and kategori_barang.nama like ? or "
                            + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and golongan_barang.nama like ? or "
                            + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and jenis.nama like ? order by databarang.nama_brng");
                } else {
                    psobatasuransi = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,(databarang.h_beli+(databarang.h_beli*?)) as harga,"
                            + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi on databarang.kdjns=jenis.kdjns "
                            + " and industrifarmasi.kode_industri=databarang.kode_industri inner join golongan_barang inner join kategori_barang on databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode where databarang.status='1' and databarang.kode_brng like ? or "
                            + " databarang.status='1' and databarang.nama_brng like ? or "
                            + " databarang.status='1' and kategori_barang.nama like ? or "
                            + " databarang.status='1' and golongan_barang.nama like ? or "
                            + " databarang.status='1' and jenis.nama like ? order by databarang.nama_brng");
                }

                try {
                    psobatasuransi.setDouble(1, kenaikan);
                    psobatasuransi.setString(2, "%" + TCari.getText().trim() + "%");
                    psobatasuransi.setString(3, "%" + TCari.getText().trim() + "%");
                    psobatasuransi.setString(4, "%" + TCari.getText().trim() + "%");
                    psobatasuransi.setString(5, "%" + TCari.getText().trim() + "%");
                    psobatasuransi.setString(6, "%" + TCari.getText().trim() + "%");
                    rsobat = psobatasuransi.executeQuery();
                    while (rsobat.next()) {
                        tabMode.addRow(new Object[]{false, "", rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
                            rsobat.getString("kode_sat"), rsobat.getString("letak_barang"), Valid.roundUp(rsobat.getDouble("harga"), 100),
                            rsobat.getString("nama"), 0, 0, 0, rsobat.getString("nama_industri"),
                            rsobat.getDouble("h_beli"), "", rsobat.getString("kategori"), rsobat.getString("golongan")
                        });
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rsobat != null) {
                        rsobat.close();
                    }
                    if (psobatasuransi != null) {
                        psobatasuransi.close();
                    }
                }
            } else {
//                if (var.getkdbangsal().equals("APT01") || var.getkdbangsal().equals("APT02")) {
//                    psobat = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,databarang.kelas1,"
//                            + " databarang.kelas2,databarang.kelas3,databarang.utama,databarang.vip,databarang.vvip,databarang.beliluar,databarang.karyawan,"
//                            + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi inner join golongan_barang inner join kategori_barang "
//                            + " on databarang.kdjns=jenis.kdjns and industrifarmasi.kode_industri=databarang.kode_industri and databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode "
//                            + " where MID(databarang.nama_brng,1,4) <> '(FR)' and (databarang.status='1' and databarang.kode_brng like ? or "
//                            + " databarang.status='1' and databarang.nama_brng like ? or "
//                            + " databarang.status='1' and kategori_barang.nama like ? or "
//                            + " databarang.status='1' and golongan_barang.nama like ? or "
//                            + " databarang.status='1' and jenis.nama like ?) order by databarang.nama_brng");
//                } else {
//                    psobat = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,databarang.kelas1,"
//                            + " databarang.kelas2,databarang.kelas3,databarang.utama,databarang.vip,databarang.vvip,databarang.beliluar,databarang.karyawan,"
//                            + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi inner join golongan_barang inner join kategori_barang"
//                            + " on databarang.kdjns=jenis.kdjns and industrifarmasi.kode_industri=databarang.kode_industri and databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode "
//                            + " where databarang.status='1' and databarang.kode_brng like ? or"
//                            + " databarang.status='1' and databarang.nama_brng like ? or"
//                            + " databarang.status='1' and kategori_barang.nama like ? or"
//                            + " databarang.status='1' and golongan_barang.nama like ? or"
//                            + " databarang.status='1' and jenis.nama like ? order by databarang.nama_brng");
//                }

//                if (var.getkdbangsal().equals("APT01") || var.getkdbangsal().equals("APT02")) {
//                    psobat = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,databarang.kelas1,"
//                            + " databarang.kelas2,databarang.kelas3,databarang.utama,databarang.vip,databarang.vvip,databarang.beliluar,databarang.karyawan,"
//                            + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi inner join golongan_barang inner join kategori_barang "
//                            + " on databarang.kdjns=jenis.kdjns and industrifarmasi.kode_industri=databarang.kode_industri and databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode "
//                            + " where (databarang.status='1' and databarang.kode_brng like ? or "
//                            + " databarang.status='1' and databarang.nama_brng like ? or "
//                            + " databarang.status='1' and kategori_barang.nama like ? or "
//                            + " databarang.status='1' and golongan_barang.nama like ? or "
//                            + " databarang.status='1' and jenis.nama like ?) order by databarang.nama_brng");
//                } else {
//                    psobat = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,databarang.kelas1,"
//                            + " databarang.kelas2,databarang.kelas3,databarang.utama,databarang.vip,databarang.vvip,databarang.beliluar,databarang.karyawan,"
//                            + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi inner join golongan_barang inner join kategori_barang"
//                            + " on databarang.kdjns=jenis.kdjns and industrifarmasi.kode_industri=databarang.kode_industri and databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode "
//                            + " where databarang.status='1' and databarang.kode_brng like ? or"
//                            + " databarang.status='1' and databarang.nama_brng like ? or"
//                            + " databarang.status='1' and kategori_barang.nama like ? or"
//                            + " databarang.status='1' and golongan_barang.nama like ? or"
//                            + " databarang.status='1' and jenis.nama like ? order by databarang.nama_brng");
//                }
                if (akses.getkdbangsal().equals("APT07")) {
                    psobat = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,databarang.kelas1,"
                            + " databarang.kelas2,databarang.kelas3,databarang.utama,databarang.vip,databarang.vvip,databarang.beliluar,databarang.karyawan,"
                            + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi inner join golongan_barang inner join kategori_barang "
                            + " on databarang.kdjns=jenis.kdjns and industrifarmasi.kode_industri=databarang.kode_industri and databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode "
                            + " where (databarang.status='1' and databarang.kode_brng like ? or "
                            + " databarang.status='1' and databarang.nama_brng like ? or "
                            + " databarang.status='1' and kategori_barang.nama like ? or "
                            + " databarang.status='1' and golongan_barang.nama like ? or "
                            + " databarang.status='1' and jenis.nama like ?) order by databarang.nama_brng");
                } else {
                    psobat = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,databarang.kelas1,"
                            + " databarang.kelas2,databarang.kelas3,databarang.utama,databarang.vip,databarang.vvip,databarang.beliluar,databarang.karyawan,"
                            + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi inner join golongan_barang inner join kategori_barang "
                            + " on databarang.kdjns=jenis.kdjns and industrifarmasi.kode_industri=databarang.kode_industri and databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode "
                            + " where MID(databarang.nama_brng,1,4) <> '(FR)' and (databarang.status='1' and databarang.kode_brng like ? or "
                            + " databarang.status='1' and databarang.nama_brng like ? or "
                            + " databarang.status='1' and kategori_barang.nama like ? or "
                            + " databarang.status='1' and golongan_barang.nama like ? or "
                            + " databarang.status='1' and jenis.nama like ?) order by databarang.nama_brng");
                }

                try {
                    psobat.setString(1, "%" + TCari.getText().trim() + "%");
                    psobat.setString(2, "%" + TCari.getText().trim() + "%");
                    psobat.setString(3, "%" + TCari.getText().trim() + "%");
                    psobat.setString(4, "%" + TCari.getText().trim() + "%");
                    psobat.setString(5, "%" + TCari.getText().trim() + "%");
                    rsobat = psobat.executeQuery();
                    while (rsobat.next()) {
                        if (Jeniskelas.getSelectedItem().equals("Kelas 1")) {
                            tabMode.addRow(new Object[]{false, "", rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
                                rsobat.getString("kode_sat"), rsobat.getString("letak_barang"), Valid.roundUp(rsobat.getDouble("kelas1"), 100),
                                rsobat.getString("nama"), 0, 0, 0, rsobat.getString("nama_industri"),
                                rsobat.getDouble("h_beli"), "", rsobat.getString("kategori"), rsobat.getString("golongan")
                            });
                        } else if (Jeniskelas.getSelectedItem().equals("Kelas 2")) {
                            tabMode.addRow(new Object[]{false, "", rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
                                rsobat.getString("kode_sat"), rsobat.getString("letak_barang"), Valid.roundUp(rsobat.getDouble("kelas2"), 100),
                                rsobat.getString("nama"), 0, 0, 0, rsobat.getString("nama_industri"),
                                rsobat.getDouble("h_beli"), "", rsobat.getString("kategori"), rsobat.getString("golongan")
                            });
                        } else if (Jeniskelas.getSelectedItem().equals("Kelas 3")) {
                            tabMode.addRow(new Object[]{false, "", rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
                                rsobat.getString("kode_sat"), rsobat.getString("letak_barang"), Valid.roundUp(rsobat.getDouble("kelas3"), 100),
                                rsobat.getString("nama"), 0, 0, 0, rsobat.getString("nama_industri"),
                                rsobat.getDouble("h_beli"), "", rsobat.getString("kategori"), rsobat.getString("golongan")
                            });
                        } else if (Jeniskelas.getSelectedItem().equals("Utama/BPJS")) {
                            tabMode.addRow(new Object[]{false, "", rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
                                rsobat.getString("kode_sat"), rsobat.getString("letak_barang"), Valid.roundUp(rsobat.getDouble("utama"), 100),
                                rsobat.getString("nama"), 0, 0, 0, rsobat.getString("nama_industri"),
                                rsobat.getDouble("h_beli"), "", rsobat.getString("kategori"), rsobat.getString("golongan")
                            });
                        } else if (Jeniskelas.getSelectedItem().equals("VIP")) {
                            tabMode.addRow(new Object[]{false, "", rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
                                rsobat.getString("kode_sat"), rsobat.getString("letak_barang"), Valid.roundUp(rsobat.getDouble("vip"), 100),
                                rsobat.getString("nama"), 0, 0, 0, rsobat.getString("nama_industri"),
                                rsobat.getDouble("h_beli"), "", rsobat.getString("kategori"), rsobat.getString("golongan")
                            });
                        } else if (Jeniskelas.getSelectedItem().equals("VVIP")) {
                            tabMode.addRow(new Object[]{false, "", rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
                                rsobat.getString("kode_sat"), rsobat.getString("letak_barang"), Valid.roundUp(rsobat.getDouble("vvip"), 100),
                                rsobat.getString("nama"), 0, 0, 0, rsobat.getString("nama_industri"),
                                rsobat.getDouble("h_beli"), "", rsobat.getString("kategori"), rsobat.getString("golongan")
                            });
                        } else if (Jeniskelas.getSelectedItem().equals("Beli Luar")) {
                            tabMode.addRow(new Object[]{false, "", rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
                                rsobat.getString("kode_sat"), rsobat.getString("letak_barang"), Valid.roundUp(rsobat.getDouble("beliluar"), 100),
                                rsobat.getString("nama"), 0, 0, 0, rsobat.getString("nama_industri"),
                                rsobat.getDouble("h_beli"), "", rsobat.getString("kategori"), rsobat.getString("golongan")
                            });
                        } else if (Jeniskelas.getSelectedItem().equals("Karyawan")) {
                            tabMode.addRow(new Object[]{false, "", rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
                                rsobat.getString("kode_sat"), rsobat.getString("letak_barang"), Valid.roundUp(rsobat.getDouble("karyawan"), 100),
                                rsobat.getString("nama"), 0, 0, 0, rsobat.getString("nama_industri"),
                                rsobat.getDouble("h_beli"), "", rsobat.getString("kategori"), rsobat.getString("golongan")
                            });
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rsobat != null) {
                        rsobat.close();
                    }
                    if (psobat != null) {
                        psobat.close();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void emptTeks() {
        TCari.setText("");
        TCari.requestFocus();
    }

    private void getData() {
        if (tbObat.getSelectedRow() != -1) {
            /*if(!tabMode.getValueAt(tbKamar.getSelectedRow(),0).toString().equals("")){
                if(Double.parseDouble(tbKamar.getValueAt(tbKamar.getSelectedRow(),0).toString())>Double.parseDouble(tbKamar.getValueAt(tbKamar.getSelectedRow(),6).toString())){
                        JOptionPane.showMessageDialog(null,"Maaf, Stok tidak cukup....!!!");
                        TCari.requestFocus();
                        tbKamar.setValueAt("", tbKamar.getSelectedRow(),0);  
                }                
            }*/

        }
    }

    public JTable getTable() {
        return tbObat;
    }

    public void isCek() {
        BtnTambah.setEnabled(akses.getobat());
        TCari.requestFocus();
    }

    public void setNoRm(String norwt, Date tanggal, String jam, String menit, String detik, boolean status) {
        TNoRw.setText(norwt);
        DTPTgl.setDate(tanggal);
        cmbJam.setSelectedItem(jam);
        cmbMnt.setSelectedItem(menit);
        cmbDtk.setSelectedItem(detik);
        ChkJln.setSelected(status);
        KdPj.setText(Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?", norwt));
        kelas.setText(Sequel.cariIsi(
                "select kamar.kelas from kamar inner join kamar_inap on kamar.kd_kamar=kamar_inap.kd_kamar "
                + "where no_rawat=? and stts_pulang='-' order by STR_TO_DATE(concat(kamar_inap.tgl_masuk,' ',jam_masuk),'%Y-%m-%d %H:%i:%s') desc limit 1", norwt));
        if (kelas.getText().equals("Kelas 1")) {
            Jeniskelas.setSelectedItem("Kelas 1");
        } else if (kelas.getText().equals("Kelas 2")) {
            Jeniskelas.setSelectedItem("Kelas 2");
        } else if (kelas.getText().equals("Kelas 3")) {
            Jeniskelas.setSelectedItem("Kelas 3");
        } else if (kelas.getText().equals("Kelas Utama")) {
            Jeniskelas.setSelectedItem("Utama/BPJS");
        } else if (kelas.getText().equals("Kelas VIP")) {
            Jeniskelas.setSelectedItem("VIP");
        } else if (kelas.getText().equals("Kelas VVIP")) {
            Jeniskelas.setSelectedItem("VVIP");
        }
        kenaikan = Sequel.cariIsiAngka("select (hargajual/100) from set_harga_obat_ranap where kd_pj='" + KdPj.getText() + "' and kelas='" + kelas.getText() + "'");
        TCari.requestFocus();
    }

    private void jam() {
        ActionListener taskPerformer = new ActionListener() {
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;

            @Override
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                // Membuat Date
                //Date dt = new Date();
                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if (ChkJln.isSelected() == true) {
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                } else if (ChkJln.isSelected() == false) {
                    nilai_jam = cmbJam.getSelectedIndex();
                    nilai_menit = cmbMnt.getSelectedIndex();
                    nilai_detik = cmbDtk.getSelectedIndex();
                }

                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                //tampil_jam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
                cmbJam.setSelectedItem(jam);
                cmbMnt.setSelectedItem(menit);
                cmbDtk.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }

    private void isRawat() {
        Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ", TNoRm, TNoRw.getText());

    }

    private void isStok(String a) {
        Sequel.cariIsi("select ifnull(stok,'0') from gudangbarang where kd_bangsal=? and kode_brng=?", TStok, akses.getkdbangsal(), a);
    }

    private void isSetBangsal() {
//        if (LocalTime.now().isAfter(LocalTime.parse("08:00:00")) && LocalTime.now().isBefore(LocalTime.parse("21:00:00"))) {
//            //JOptionPane.showMessageDialog(rootPane, "Apotek Sentral");
//            bangsal = Sequel.cariApotek();
//        } else {
//            //JOptionPane.showMessageDialog(rootPane, "Apotek IGD");
//            bangsal = Sequel.cariApotek2();
//        }
        bangsal = Sequel.cariApotek();
        akses.setkdbangsal(bangsal);
    }

    public void setStatus(String stat) {
        status = stat;
    }
}
