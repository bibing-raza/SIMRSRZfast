package ipsrs;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import keuangan.Jurnal;
import kepegawaian.DlgCariPetugas;

public class DlgCariPengeluaranIpsrs extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Connection koneksi=koneksiDB.condb();
    private Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
    public  DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    public  DlgBarangIPSRS barang=new DlgBarangIPSRS(null,false);
    private PreparedStatement ps,ps2,psdetailpengeluaran,psradiologi,psradiologi2;
    private Jurnal jur=new Jurnal();
    private ResultSet rs,rs2;
    private double tagihan=0,ttltagihan=0,total=0;
    private int i=0;

    /** Creates new form DlgProgramStudi
     * @param parent
     * @param modal */
    public DlgCariPengeluaranIpsrs(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row={"Tgl.Keluar","No.Keluar","Keterangan","Petugas","Barang","Satuan","Jml","Harga(Rp)","Total(Rp)"};
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbDokter.setModel(tabMode);

        tbDokter.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbDokter.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbDokter.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(70);
            }else if(i==1){
                column.setPreferredWidth(110);
            }else if(i==2){
                column.setPreferredWidth(150);
            }else if(i==3){
                column.setPreferredWidth(180);
            }else if(i==4){
                column.setPreferredWidth(260);
            }else if(i==5){
                column.setPreferredWidth(80);
            }else if(i==6){
                column.setPreferredWidth(40);
            }else if(i==7){
                column.setPreferredWidth(90);
            }else if(i==8){
                column.setPreferredWidth(100);
            }
        }
        tbDokter.setDefaultRenderer(Object.class, new WarnaTable());

        NoKeluar.setDocument(new batasInput((byte)15).getKata(NoKeluar));
        kdptg.setDocument(new batasInput((byte)25).getKata(kdptg));
        kdbar.setDocument(new batasInput((byte)15).getKata(kdbar));
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));          
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
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("DlgCariPengeluaranIpsrs")){
                    if(petugas.getTable().getSelectedRow()!= -1){                   
                        kdptg.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                        nmptg.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                    }            
                    kdptg.requestFocus();
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        barang.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("DlgCariPengeluaranIpsrs")){
                    if(barang.getTable().getSelectedRow()!= -1){                   
                        kdbar.setText(barang.getTable().getValueAt(barang.getTable().getSelectedRow(),0).toString());                    
                        nmbar.setText(barang.getTable().getValueAt(barang.getTable().getSelectedRow(),1).toString());
                    }   
                    kdbar.requestFocus();
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        barang.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(akses.getform().equals("DlgCariPengeluaranIpsrs")){
                    if(e.getKeyCode()==KeyEvent.VK_SPACE){
                        barang.dispose();
                    }                
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        }); 
        
        barang.jenis.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("DlgCariPembelianIpsrs")){
                    if(barang.jenis.getTable().getSelectedRow()!= -1){                   
                        kdjenis.setText(barang.jenis.getTable().getValueAt(barang.jenis.getTable().getSelectedRow(),0).toString());                    
                        nmjenis.setText(barang.jenis.getTable().getValueAt(barang.jenis.getTable().getSelectedRow(),1).toString());
                    }   
                    kdjenis.requestFocus();
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        try {
            ps=koneksi.prepareStatement("select ipsrspengeluaran.tanggal,ipsrspengeluaran.no_keluar,ipsrspengeluaran.keterangan,ipsrspengeluaran.nip,petugas.nama "+
                    " from ipsrspengeluaran inner join petugas inner join kodesatuan  "+
                    " inner join ipsrsdetailpengeluaran inner join ipsrsbarang "+
                    " on ipsrsdetailpengeluaran.kode_brng=ipsrsbarang.kode_brng "+
                    " and ipsrsbarang.kode_sat=kodesatuan.kode_sat "+
                    " and ipsrspengeluaran.no_keluar=ipsrsdetailpengeluaran.no_keluar "+
                    " and ipsrspengeluaran.nip=petugas.nip"+
                    " where ipsrspengeluaran.tanggal between ? and ? and ipsrspengeluaran.no_keluar like ? and petugas.nama like ?  and ipsrsbarang.jenis like ? and ipsrsbarang.nama_brng like ? and ipsrspengeluaran.no_keluar like ? or "+
                    " ipsrspengeluaran.tanggal between ? and ? and ipsrspengeluaran.no_keluar like ? and petugas.nama like ?  and ipsrsbarang.jenis like ? and ipsrsbarang.nama_brng like ? and ipsrspengeluaran.keterangan like ? or "+
                    " ipsrspengeluaran.tanggal between ? and ? and ipsrspengeluaran.no_keluar like ? and petugas.nama like ?  and ipsrsbarang.jenis like ? and ipsrsbarang.nama_brng like ? and ipsrspengeluaran.nip like ? or "+
                    " ipsrspengeluaran.tanggal between ? and ? and ipsrspengeluaran.no_keluar like ? and petugas.nama like ?  and ipsrsbarang.jenis like ? and ipsrsbarang.nama_brng like ? and petugas.nama like ? or "+
                    " ipsrspengeluaran.tanggal between ? and ? and ipsrspengeluaran.no_keluar like ? and petugas.nama like ?  and ipsrsbarang.jenis like ? and ipsrsbarang.nama_brng like ? and ipsrsbarang.jenis like ? or "+
                    " ipsrspengeluaran.tanggal between ? and ? and ipsrspengeluaran.no_keluar like ? and petugas.nama like ?  and ipsrsbarang.jenis like ? and ipsrsbarang.nama_brng like ? and ipsrsdetailpengeluaran.kode_brng like ? or "+
                    " ipsrspengeluaran.tanggal between ? and ? and ipsrspengeluaran.no_keluar like ? and petugas.nama like ?  and ipsrsbarang.jenis like ? and ipsrsbarang.nama_brng like ? and ipsrsbarang.nama_brng like ? or "+
                    " ipsrspengeluaran.tanggal between ? and ? and ipsrspengeluaran.no_keluar like ? and petugas.nama like ?  and ipsrsbarang.jenis like ? and ipsrsbarang.nama_brng like ? and ipsrsdetailpengeluaran.kode_sat like ? or "+
                    " ipsrspengeluaran.tanggal between ? and ? and ipsrspengeluaran.no_keluar like ? and petugas.nama like ?  and ipsrsbarang.jenis like ? and ipsrsbarang.nama_brng like ? and kodesatuan.satuan like ? "+
                    " group by ipsrspengeluaran.no_keluar order by ipsrspengeluaran.tanggal,ipsrspengeluaran.no_keluar ");
            ps2=koneksi.prepareStatement("select ipsrsdetailpengeluaran.kode_brng,ipsrsbarang.nama_brng, "+
                    "ipsrsdetailpengeluaran.kode_sat,kodesatuan.satuan,ipsrsdetailpengeluaran.jumlah,ipsrsdetailpengeluaran.harga,ipsrsdetailpengeluaran.total "+
                    "from ipsrsdetailpengeluaran inner join ipsrsbarang inner join kodesatuan "+
                    " on ipsrsdetailpengeluaran.kode_brng=ipsrsbarang.kode_brng "+
                    " and ipsrsdetailpengeluaran.kode_sat=kodesatuan.kode_sat where "+
                    " ipsrsdetailpengeluaran.no_keluar=? and ipsrsbarang.nama_brng like ? and ipsrsbarang.jenis like ? and ipsrsdetailpengeluaran.kode_brng like ? or "+
                    " ipsrsdetailpengeluaran.no_keluar=? and ipsrsbarang.nama_brng like ? and ipsrsbarang.jenis like ? and ipsrsbarang.nama_brng like ? or "+
                    " ipsrsdetailpengeluaran.no_keluar=? and ipsrsbarang.nama_brng like ? and ipsrsbarang.jenis like ? and ipsrsdetailpengeluaran.kode_sat like ? or "+
                    " ipsrsdetailpengeluaran.no_keluar=? and ipsrsbarang.nama_brng like ? and ipsrsbarang.jenis like ? and ipsrsbarang.jenis like ? order by ipsrsdetailpengeluaran.kode_brng  ");
            psradiologi=koneksi.prepareStatement("select periksa_radiologi.tgl_periksa,periksa_radiologi.no_rawat,periksa_radiologi.jam,periksa_radiologi.nip,petugas.nama "+
                    " from periksa_radiologi inner join petugas inner join kodesatuan  "+
                    " inner join beri_bhp_radiologi inner join ipsrsbarang "+
                    " on beri_bhp_radiologi.kode_brng=ipsrsbarang.kode_brng "+
                    " and ipsrsbarang.kode_sat=kodesatuan.kode_sat "+
                    " and periksa_radiologi.no_rawat=beri_bhp_radiologi.no_rawat "+
                    " and periksa_radiologi.nip=petugas.nip"+
                    " where periksa_radiologi.tgl_periksa between ? and ? and periksa_radiologi.no_rawat like ? and petugas.nama like ?  and ipsrsbarang.jenis like ? and ipsrsbarang.nama_brng like ? and periksa_radiologi.no_rawat like ? or "+
                    " periksa_radiologi.tgl_periksa between ? and ? and periksa_radiologi.no_rawat like ? and petugas.nama like ?  and ipsrsbarang.jenis like ? and ipsrsbarang.nama_brng like ? and periksa_radiologi.jam like ? or "+
                    " periksa_radiologi.tgl_periksa between ? and ? and periksa_radiologi.no_rawat like ? and petugas.nama like ?  and ipsrsbarang.jenis like ? and ipsrsbarang.nama_brng like ? and periksa_radiologi.nip like ? or "+
                    " periksa_radiologi.tgl_periksa between ? and ? and periksa_radiologi.no_rawat like ? and petugas.nama like ?  and ipsrsbarang.jenis like ? and ipsrsbarang.nama_brng like ? and petugas.nama like ? or "+
                    " periksa_radiologi.tgl_periksa between ? and ? and periksa_radiologi.no_rawat like ? and petugas.nama like ?  and ipsrsbarang.jenis like ? and ipsrsbarang.nama_brng like ? and ipsrsbarang.jenis like ? or "+
                    " periksa_radiologi.tgl_periksa between ? and ? and periksa_radiologi.no_rawat like ? and petugas.nama like ?  and ipsrsbarang.jenis like ? and ipsrsbarang.nama_brng like ? and beri_bhp_radiologi.kode_brng like ? or "+
                    " periksa_radiologi.tgl_periksa between ? and ? and periksa_radiologi.no_rawat like ? and petugas.nama like ?  and ipsrsbarang.jenis like ? and ipsrsbarang.nama_brng like ? and ipsrsbarang.nama_brng like ? or "+
                    " periksa_radiologi.tgl_periksa between ? and ? and periksa_radiologi.no_rawat like ? and petugas.nama like ?  and ipsrsbarang.jenis like ? and ipsrsbarang.nama_brng like ? and beri_bhp_radiologi.kode_sat like ? or "+
                    " periksa_radiologi.tgl_periksa between ? and ? and periksa_radiologi.no_rawat like ? and petugas.nama like ?  and ipsrsbarang.jenis like ? and ipsrsbarang.nama_brng like ? and kodesatuan.satuan like ? "+
                    " group by periksa_radiologi.no_rawat order by periksa_radiologi.tgl_periksa,periksa_radiologi.no_rawat ");
            psradiologi2=koneksi.prepareStatement("select beri_bhp_radiologi.kode_brng,ipsrsbarang.nama_brng, "+
                    "beri_bhp_radiologi.kode_sat,kodesatuan.satuan,beri_bhp_radiologi.jumlah,beri_bhp_radiologi.harga,beri_bhp_radiologi.total "+
                    "from beri_bhp_radiologi inner join ipsrsbarang inner join kodesatuan "+
                    " on beri_bhp_radiologi.kode_brng=ipsrsbarang.kode_brng "+
                    " and beri_bhp_radiologi.kode_sat=kodesatuan.kode_sat where "+
                    " beri_bhp_radiologi.no_rawat=? and beri_bhp_radiologi.tgl_periksa=? and beri_bhp_radiologi.jam=? and ipsrsbarang.nama_brng like ? and ipsrsbarang.jenis like ? and beri_bhp_radiologi.kode_brng like ? or "+
                    " beri_bhp_radiologi.no_rawat=? and beri_bhp_radiologi.tgl_periksa=? and beri_bhp_radiologi.jam=? and ipsrsbarang.nama_brng like ? and ipsrsbarang.jenis like ? and ipsrsbarang.nama_brng like ? or "+
                    " beri_bhp_radiologi.no_rawat=? and beri_bhp_radiologi.tgl_periksa=? and beri_bhp_radiologi.jam=? and ipsrsbarang.nama_brng like ? and ipsrsbarang.jenis like ? and beri_bhp_radiologi.kode_sat like ? or "+
                    " beri_bhp_radiologi.no_rawat=? and beri_bhp_radiologi.tgl_periksa=? and beri_bhp_radiologi.jam=? and ipsrsbarang.nama_brng like ? and ipsrsbarang.jenis like ? and ipsrsbarang.jenis like ? order by beri_bhp_radiologi.kode_brng  ");
            psdetailpengeluaran=koneksi.prepareStatement("select kode_brng,jumlah,total from ipsrsdetailpengeluaran where no_keluar=? ");
        } catch (Exception e) {
            System.out.println(e);
        }
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
        ppHapus = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        scrollPane1 = new widget.ScrollPane();
        tbDokter = new widget.Table();
        jPanel1 = new javax.swing.JPanel();
        panelisi1 = new widget.panelisi();
        label10 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        label9 = new widget.Label();
        LTotal = new widget.Label();
        BtnAll = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        panelisi4 = new widget.panelisi();
        kdbar = new widget.TextBox();
        nmbar = new widget.TextBox();
        btnBarang = new widget.Button();
        label17 = new widget.Label();
        btnJenis = new widget.Button();
        nmjenis = new widget.TextBox();
        kdjenis = new widget.TextBox();
        label7 = new widget.Label();
        panelisi3 = new widget.panelisi();
        label15 = new widget.Label();
        NoKeluar = new widget.TextBox();
        label11 = new widget.Label();
        TglBeli1 = new widget.Tanggal();
        label13 = new widget.Label();
        kdptg = new widget.TextBox();
        nmptg = new widget.TextBox();
        btnPetugas = new widget.Button();
        label12 = new widget.Label();
        TglBeli2 = new widget.Tanggal();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        ppHapus.setBackground(new java.awt.Color(242, 242, 242));
        ppHapus.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppHapus.setForeground(new java.awt.Color(102, 51, 0));
        ppHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppHapus.setText("Hapus Data Stok Keluar");
        ppHapus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppHapus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppHapus.setIconTextGap(8);
        ppHapus.setName("ppHapus"); // NOI18N
        ppHapus.setPreferredSize(new java.awt.Dimension(200, 25));
        ppHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppHapusActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppHapus);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Cari Stok Keluar Barang Non Medis dan Penunjang ( Lab & RO ) ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(90, 120, 80))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane1.setComponentPopupMenu(jPopupMenu1);
        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbDokter.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbDokter.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDokter.setComponentPopupMenu(jPopupMenu1);
        tbDokter.setName("tbDokter"); // NOI18N
        scrollPane1.setViewportView(tbDokter);

        internalFrame1.add(scrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 100));
        jPanel1.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 56));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label10.setText("Key Word :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi1.add(label10);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(170, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi1.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('5');
        BtnCari.setToolTipText("Alt+5");
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
        panelisi1.add(BtnCari);

        label9.setText("Total :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(45, 30));
        panelisi1.add(label9);

        LTotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LTotal.setText("0");
        LTotal.setName("LTotal"); // NOI18N
        LTotal.setPreferredSize(new java.awt.Dimension(120, 30));
        panelisi1.add(LTotal);

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
        panelisi1.add(BtnAll);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelisi1.add(BtnPrint);

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
        panelisi1.add(BtnKeluar);

        jPanel1.add(panelisi1, java.awt.BorderLayout.PAGE_END);

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi4.setLayout(null);

        kdbar.setName("kdbar"); // NOI18N
        kdbar.setPreferredSize(new java.awt.Dimension(80, 23));
        kdbar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdbarKeyPressed(evt);
            }
        });
        panelisi4.add(kdbar);
        kdbar.setBounds(389, 10, 110, 23);

        nmbar.setEditable(false);
        nmbar.setName("nmbar"); // NOI18N
        nmbar.setPreferredSize(new java.awt.Dimension(207, 23));
        panelisi4.add(nmbar);
        nmbar.setBounds(501, 10, 230, 23);

        btnBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnBarang.setMnemonic('4');
        btnBarang.setToolTipText("Alt+4");
        btnBarang.setName("btnBarang"); // NOI18N
        btnBarang.setPreferredSize(new java.awt.Dimension(28, 23));
        btnBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarangActionPerformed(evt);
            }
        });
        panelisi4.add(btnBarang);
        btnBarang.setBounds(734, 10, 28, 23);

        label17.setText("Barang :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(65, 23));
        panelisi4.add(label17);
        label17.setBounds(325, 10, 60, 23);

        btnJenis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnJenis.setMnemonic('1');
        btnJenis.setToolTipText("Alt+1");
        btnJenis.setName("btnJenis"); // NOI18N
        btnJenis.setPreferredSize(new java.awt.Dimension(28, 23));
        btnJenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJenisActionPerformed(evt);
            }
        });
        panelisi4.add(btnJenis);
        btnJenis.setBounds(290, 10, 25, 23);

        nmjenis.setEditable(false);
        nmjenis.setName("nmjenis"); // NOI18N
        nmjenis.setPreferredSize(new java.awt.Dimension(207, 23));
        panelisi4.add(nmjenis);
        nmjenis.setBounds(108, 10, 180, 23);

        kdjenis.setName("kdjenis"); // NOI18N
        kdjenis.setPreferredSize(new java.awt.Dimension(207, 23));
        kdjenis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdjenisKeyPressed(evt);
            }
        });
        panelisi4.add(kdjenis);
        kdjenis.setBounds(45, 10, 61, 23);

        label7.setText("Jenis :");
        label7.setName("label7"); // NOI18N
        panelisi4.add(label7);
        label7.setBounds(0, 10, 42, 23);

        jPanel1.add(panelisi4, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 73));
        panelisi3.setLayout(null);

        label15.setText("No.Keluar :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi3.add(label15);
        label15.setBounds(0, 10, 80, 23);

        NoKeluar.setName("NoKeluar"); // NOI18N
        NoKeluar.setPreferredSize(new java.awt.Dimension(207, 23));
        NoKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoKeluarKeyPressed(evt);
            }
        });
        panelisi3.add(NoKeluar);
        NoKeluar.setBounds(84, 10, 219, 23);

        label11.setText("Tgl.Keluar :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label11);
        label11.setBounds(0, 40, 80, 23);

        TglBeli1.setEditable(false);
        TglBeli1.setDisplayFormat("dd-MM-yyyy");
        TglBeli1.setName("TglBeli1"); // NOI18N
        TglBeli1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglBeli1KeyPressed(evt);
            }
        });
        panelisi3.add(TglBeli1);
        TglBeli1.setBounds(84, 40, 95, 23);

        label13.setText("Petugas :");
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label13);
        label13.setBounds(305, 10, 80, 23);

        kdptg.setName("kdptg"); // NOI18N
        kdptg.setPreferredSize(new java.awt.Dimension(80, 23));
        kdptg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdptgKeyPressed(evt);
            }
        });
        panelisi3.add(kdptg);
        kdptg.setBounds(389, 10, 80, 23);

        nmptg.setEditable(false);
        nmptg.setName("nmptg"); // NOI18N
        nmptg.setPreferredSize(new java.awt.Dimension(207, 23));
        panelisi3.add(nmptg);
        nmptg.setBounds(471, 10, 260, 23);

        btnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas.setMnemonic('2');
        btnPetugas.setToolTipText("Alt+2");
        btnPetugas.setName("btnPetugas"); // NOI18N
        btnPetugas.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasActionPerformed(evt);
            }
        });
        panelisi3.add(btnPetugas);
        btnPetugas.setBounds(734, 10, 28, 23);

        label12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label12.setText("s.d.");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label12);
        label12.setBounds(179, 40, 27, 23);

        TglBeli2.setEditable(false);
        TglBeli2.setDisplayFormat("dd-MM-yyyy");
        TglBeli2.setName("TglBeli2"); // NOI18N
        TglBeli2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglBeli2KeyPressed(evt);
            }
        });
        panelisi3.add(TglBeli2);
        TglBeli2.setBounds(208, 40, 95, 23);

        internalFrame1.add(panelisi3, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
            dispose();  
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){            
            dispose();              
        }else{Valid.pindah(evt,BtnPrint,kdbar);}
}//GEN-LAST:event_BtnKeluarKeyPressed
/*
private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
    Valid.pindah(evt,BtnCari,Nm);
}//GEN-LAST:event_TKdKeyPressed
*/

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        akses.setform("DlgCariPengeluaranIpsrs");
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void TglBeli1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglBeli1KeyPressed
        Valid.pindah(evt,NoKeluar,TglBeli2);
    }//GEN-LAST:event_TglBeli1KeyPressed

    private void btnBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarangActionPerformed
        akses.setform("DlgCariPengeluaranIpsrs");
        barang.emptTeks();
        barang.isCek();
        barang.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
        barang.setLocationRelativeTo(internalFrame1);
        barang.setAlwaysOnTop(false);
        barang.setVisible(true);
    }//GEN-LAST:event_btnBarangActionPerformed

    private void NoKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoKeluarKeyPressed
        Valid.pindah(evt, BtnKeluar, TglBeli1);
    }//GEN-LAST:event_NoKeluarKeyPressed

    private void kdptgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdptgKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            Sequel.cariIsi("select nama from petugas where nip=?",nmptg,kdptg.getText());     
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            Sequel.cariIsi("select nama from petugas where nip=?",nmptg,kdptg.getText());
            TglBeli2.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Sequel.cariIsi("select nama from petugas where nip=?",nmptg,kdptg.getText());
            kdbar.requestFocus();       
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnPetugasActionPerformed(null);
        }
    }//GEN-LAST:event_kdptgKeyPressed

    private void kdbarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdbarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            Sequel.cariIsi("select nama_brng from ipsrsbarang where kode_brng=?", nmbar,kdbar.getText());
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){            
            Sequel.cariIsi("select nama_brng from ipsrsbarang where kode_brng=?", nmbar,kdbar.getText());
            kdjenis.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){            
            Sequel.cariIsi("select nama_brng from ipsrsbarang where kode_brng=?", nmbar,kdbar.getText());
            TCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnBarangActionPerformed(null);
        }
    }//GEN-LAST:event_kdbarKeyPressed

    private void TglBeli2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglBeli2KeyPressed
        Valid.pindah(evt, TglBeli1, kdptg);
    }//GEN-LAST:event_TglBeli2KeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tbDokter.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        NoKeluar.setText("");
        kdbar.setText("");
        nmbar.setText("");
        kdjenis.setText("");
        nmjenis.setText("");
        kdptg.setText("");
        nmptg.setText("");
        tampil();
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BtnCariActionPerformed(evt);
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary");
            int row=tabMode.getRowCount();
            for(i=0;i<row;i++){  
                Sequel.menyimpan("temporary","'0','"+
                                tabMode.getValueAt(i,0).toString()+"','"+
                                tabMode.getValueAt(i,1).toString()+"','"+
                                tabMode.getValueAt(i,2).toString()+"','"+
                                tabMode.getValueAt(i,3).toString()+"','"+
                                tabMode.getValueAt(i,4).toString()+"','"+
                                tabMode.getValueAt(i,5).toString()+"','"+
                                tabMode.getValueAt(i,6).toString()+"','"+
                                tabMode.getValueAt(i,8).toString()+"','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''","Transaksi Pengeluaran"); 
            }
            Sequel.menyimpan("temporary","'0','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''","Transaksi Pembelian"); 
            Sequel.menyimpan("temporary","'0','Jml.Total :','','','','','','','"+LTotal.getText()+"','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''","Transaksi Pembelian"); 
            Sequel.AutoComitTrue();
            Map<String, Object> param = new HashMap<>();    
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());   
                param.put("logo",Sequel.cariGambar("select logo from setting")); 
            Valid.MyReport("rptPengeluaranIpsrs.jasper","report","::[ Transaksi Pengeluaran Barang ]::",
                "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14 from temporary order by no asc",param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt,BtnAll,BtnAll);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

private void ppHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppHapusActionPerformed
  if(tbDokter.getValueAt(tbDokter.getSelectedRow(),1).toString().trim().equals("")){
      Valid.textKosong(TCari,tbDokter.getValueAt(tbDokter.getSelectedRow(),1).toString());
  }else{
     try {
         Sequel.AutoComitFalse();
         psdetailpengeluaran.setString(1,tbDokter.getValueAt(tbDokter.getSelectedRow(),1).toString());
         rs2=psdetailpengeluaran.executeQuery();
         total=0;
         while(rs2.next()){
            Sequel.mengedit("ipsrsbarang","kode_brng=?","stok=stok+?",2,new String[]{
                rs2.getString("jumlah"),rs2.getString("kode_brng")
            });
            total=total+rs2.getDouble("total");
         }         
         Sequel.queryu("delete from tampjurnal");
         Sequel.menyimpan("tampjurnal","?,?,?,?",4,new String[]{Sequel.cariIsi("select Stok_Keluar_Ipsrs from set_akun"),"PERSEDIAAN BARANG","0",""+total});
         Sequel.menyimpan("tampjurnal","?,?,?,?",4,new String[]{Sequel.cariIsi("select Kontra_Stok_Keluar_Ipsrs from set_akun"),"KAS DI TANGAN",""+total,"0"}); 
         jur.simpanJurnal(tbDokter.getValueAt(tbDokter.getSelectedRow(),1).toString(),Sequel.cariIsi("select current_date()"),"U","PEMBATALAN PENGGUNAAN BARANG NON MEDIS");
         Sequel.queryu2("delete from ipsrspengeluaran where no_keluar=?",1,new String[]{tbDokter.getValueAt(tbDokter.getSelectedRow(),1).toString()});
         Sequel.AutoComitTrue();
         tampil();
     } catch (SQLException ex) {
         System.out.println(ex);
     }      
  }     
}//GEN-LAST:event_ppHapusActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void btnJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJenisActionPerformed
        akses.setform("DlgCariPembelianIpsrs");
        barang.jenis.isCek();
        barang.jenis.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        barang.jenis.setLocationRelativeTo(internalFrame1);
        barang.jenis.setVisible(true);
    }//GEN-LAST:event_btnJenisActionPerformed

    private void kdjenisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdjenisKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            Sequel.cariIsi("select nm_jenis from ipsrsjenisbarang where kd_jenis=?", nmjenis,kdjenis.getText());
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kdbar.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            TCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnJenisActionPerformed(null);
        }
    }//GEN-LAST:event_kdjenisKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCariPengeluaranIpsrs dialog = new DlgCariPengeluaranIpsrs(new javax.swing.JFrame(), true);
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
    private widget.Button BtnPrint;
    private widget.Label LTotal;
    private widget.TextBox NoKeluar;
    private widget.TextBox TCari;
    private widget.Tanggal TglBeli1;
    private widget.Tanggal TglBeli2;
    private widget.Button btnBarang;
    private widget.Button btnJenis;
    private widget.Button btnPetugas;
    private widget.InternalFrame internalFrame1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.TextBox kdbar;
    private widget.TextBox kdjenis;
    private widget.TextBox kdptg;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label13;
    private widget.Label label15;
    private widget.Label label17;
    private widget.Label label7;
    private widget.Label label9;
    private widget.TextBox nmbar;
    private widget.TextBox nmjenis;
    private widget.TextBox nmptg;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private javax.swing.JMenuItem ppHapus;
    private widget.ScrollPane scrollPane1;
    private widget.Table tbDokter;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
       Valid.tabelKosong(tabMode);
        try{            
            ps.setString(1,Valid.SetTgl(TglBeli1.getSelectedItem()+""));
            ps.setString(2,Valid.SetTgl(TglBeli2.getSelectedItem()+""));
            ps.setString(3,"%"+NoKeluar.getText()+"%");
            ps.setString(4,"%"+nmptg.getText()+"%");
            ps.setString(5,"%"+kdjenis.getText()+"%");
            ps.setString(6,"%"+nmbar.getText()+"%");
            ps.setString(7,"%"+TCari.getText()+"%");
            ps.setString(8,Valid.SetTgl(TglBeli1.getSelectedItem()+""));
            ps.setString(9,Valid.SetTgl(TglBeli2.getSelectedItem()+""));
            ps.setString(10,"%"+NoKeluar.getText()+"%");
            ps.setString(11,"%"+nmptg.getText()+"%");
            ps.setString(12,"%"+kdjenis.getText()+"%");
            ps.setString(13,"%"+nmbar.getText()+"%");
            ps.setString(14,"%"+TCari.getText()+"%");
            ps.setString(15,Valid.SetTgl(TglBeli1.getSelectedItem()+""));
            ps.setString(16,Valid.SetTgl(TglBeli2.getSelectedItem()+""));
            ps.setString(17,"%"+NoKeluar.getText()+"%");
            ps.setString(18,"%"+nmptg.getText()+"%");
            ps.setString(19,"%"+kdjenis.getText()+"%");
            ps.setString(20,"%"+nmbar.getText()+"%");
            ps.setString(21,"%"+TCari.getText()+"%");
            ps.setString(22,Valid.SetTgl(TglBeli1.getSelectedItem()+""));
            ps.setString(23,Valid.SetTgl(TglBeli2.getSelectedItem()+""));
            ps.setString(24,"%"+NoKeluar.getText()+"%");
            ps.setString(25,"%"+nmptg.getText()+"%");
            ps.setString(26,"%"+kdjenis.getText()+"%");
            ps.setString(27,"%"+nmbar.getText()+"%");
            ps.setString(28,"%"+TCari.getText()+"%");
            ps.setString(29,Valid.SetTgl(TglBeli1.getSelectedItem()+""));
            ps.setString(30,Valid.SetTgl(TglBeli2.getSelectedItem()+""));
            ps.setString(31,"%"+NoKeluar.getText()+"%");
            ps.setString(32,"%"+nmptg.getText()+"%");
            ps.setString(33,"%"+kdjenis.getText()+"%");
            ps.setString(34,"%"+nmbar.getText()+"%");
            ps.setString(35,"%"+TCari.getText()+"%");
            ps.setString(36,Valid.SetTgl(TglBeli1.getSelectedItem()+""));
            ps.setString(37,Valid.SetTgl(TglBeli2.getSelectedItem()+""));
            ps.setString(38,"%"+NoKeluar.getText()+"%");
            ps.setString(39,"%"+nmptg.getText()+"%");
            ps.setString(40,"%"+kdjenis.getText()+"%");
            ps.setString(41,"%"+nmbar.getText()+"%");
            ps.setString(42,"%"+TCari.getText()+"%");
            ps.setString(43,Valid.SetTgl(TglBeli1.getSelectedItem()+""));
            ps.setString(44,Valid.SetTgl(TglBeli2.getSelectedItem()+""));
            ps.setString(45,"%"+NoKeluar.getText()+"%");
            ps.setString(46,"%"+nmptg.getText()+"%");
            ps.setString(47,"%"+kdjenis.getText()+"%");
            ps.setString(48,"%"+nmbar.getText()+"%");
            ps.setString(49,"%"+TCari.getText()+"%");
            ps.setString(50,Valid.SetTgl(TglBeli1.getSelectedItem()+""));
            ps.setString(51,Valid.SetTgl(TglBeli2.getSelectedItem()+""));
            ps.setString(52,"%"+NoKeluar.getText()+"%");
            ps.setString(53,"%"+nmptg.getText()+"%");
            ps.setString(54,"%"+kdjenis.getText()+"%");
            ps.setString(55,"%"+nmbar.getText()+"%");
            ps.setString(56,"%"+TCari.getText()+"%");
            ps.setString(57,Valid.SetTgl(TglBeli1.getSelectedItem()+""));
            ps.setString(58,Valid.SetTgl(TglBeli2.getSelectedItem()+""));
            ps.setString(59,"%"+NoKeluar.getText()+"%");
            ps.setString(60,"%"+nmptg.getText()+"%");
            ps.setString(61,"%"+kdjenis.getText()+"%");
            ps.setString(62,"%"+nmbar.getText()+"%");
            ps.setString(63,"%"+TCari.getText()+"%");
            rs=ps.executeQuery();
            ttltagihan=0;
            while(rs.next()){
                tabMode.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)+", "+rs.getString(5),"Stok Keluar :","","","","",""
                });                
                ps2.setString(1,rs.getString(2));
                ps2.setString(2,"%"+nmbar.getText()+"%");
                ps2.setString(3,"%"+kdjenis.getText()+"%");
                ps2.setString(4,"%"+TCari.getText()+"%");
                ps2.setString(5,rs.getString(2));
                ps2.setString(6,"%"+nmbar.getText()+"%");
                ps2.setString(7,"%"+kdjenis.getText()+"%");
                ps2.setString(8,"%"+TCari.getText()+"%");
                ps2.setString(9,rs.getString(2));
                ps2.setString(10,"%"+nmbar.getText()+"%");
                ps2.setString(11,"%"+kdjenis.getText()+"%");
                ps2.setString(12,"%"+TCari.getText()+"%");
                ps2.setString(13,rs.getString(2));
                ps2.setString(14,"%"+nmbar.getText()+"%");
                ps2.setString(15,"%"+kdjenis.getText()+"%");
                ps2.setString(16,"%"+TCari.getText()+"%");
                rs2=ps2.executeQuery();
                int no=1;
                tagihan=0;
                while(rs2.next()){
                    tagihan=tagihan+rs2.getDouble(7);
                    ttltagihan=ttltagihan+rs2.getDouble(7);
                    tabMode.addRow(new Object[]{"","","","",no+". "+rs2.getString(1)+", "+rs2.getString(2),
                                    rs2.getString(3)+", "+rs2.getString(4),rs2.getString(5),Valid.SetAngka(rs2.getDouble(6)),Valid.SetAngka(rs2.getDouble(7))});
                    no++;
                }
                tabMode.addRow(new Object[]{"","","","","Total ","","","",Valid.SetAngka(tagihan)});
            }
            
            psradiologi.setString(1,Valid.SetTgl(TglBeli1.getSelectedItem()+""));
            psradiologi.setString(2,Valid.SetTgl(TglBeli2.getSelectedItem()+""));
            psradiologi.setString(3,"%"+NoKeluar.getText()+"%");
            psradiologi.setString(4,"%"+nmptg.getText()+"%");
            psradiologi.setString(5,"%"+kdjenis.getText()+"%");
            psradiologi.setString(6,"%"+nmbar.getText()+"%");
            psradiologi.setString(7,"%"+TCari.getText()+"%");
            psradiologi.setString(8,Valid.SetTgl(TglBeli1.getSelectedItem()+""));
            psradiologi.setString(9,Valid.SetTgl(TglBeli2.getSelectedItem()+""));
            psradiologi.setString(10,"%"+NoKeluar.getText()+"%");
            psradiologi.setString(11,"%"+nmptg.getText()+"%");
            psradiologi.setString(12,"%"+kdjenis.getText()+"%");
            psradiologi.setString(13,"%"+nmbar.getText()+"%");
            psradiologi.setString(14,"%"+TCari.getText()+"%");
            psradiologi.setString(15,Valid.SetTgl(TglBeli1.getSelectedItem()+""));
            psradiologi.setString(16,Valid.SetTgl(TglBeli2.getSelectedItem()+""));
            psradiologi.setString(17,"%"+NoKeluar.getText()+"%");
            psradiologi.setString(18,"%"+nmptg.getText()+"%");
            psradiologi.setString(19,"%"+kdjenis.getText()+"%");
            psradiologi.setString(20,"%"+nmbar.getText()+"%");
            psradiologi.setString(21,"%"+TCari.getText()+"%");
            psradiologi.setString(22,Valid.SetTgl(TglBeli1.getSelectedItem()+""));
            psradiologi.setString(23,Valid.SetTgl(TglBeli2.getSelectedItem()+""));
            psradiologi.setString(24,"%"+NoKeluar.getText()+"%");
            psradiologi.setString(25,"%"+nmptg.getText()+"%");
            psradiologi.setString(26,"%"+kdjenis.getText()+"%");
            psradiologi.setString(27,"%"+nmbar.getText()+"%");
            psradiologi.setString(28,"%"+TCari.getText()+"%");
            psradiologi.setString(29,Valid.SetTgl(TglBeli1.getSelectedItem()+""));
            psradiologi.setString(30,Valid.SetTgl(TglBeli2.getSelectedItem()+""));
            psradiologi.setString(31,"%"+NoKeluar.getText()+"%");
            psradiologi.setString(32,"%"+nmptg.getText()+"%");
            psradiologi.setString(33,"%"+kdjenis.getText()+"%");
            psradiologi.setString(34,"%"+nmbar.getText()+"%");
            psradiologi.setString(35,"%"+TCari.getText()+"%");
            psradiologi.setString(36,Valid.SetTgl(TglBeli1.getSelectedItem()+""));
            psradiologi.setString(37,Valid.SetTgl(TglBeli2.getSelectedItem()+""));
            psradiologi.setString(38,"%"+NoKeluar.getText()+"%");
            psradiologi.setString(39,"%"+nmptg.getText()+"%");
            psradiologi.setString(40,"%"+kdjenis.getText()+"%");
            psradiologi.setString(41,"%"+nmbar.getText()+"%");
            psradiologi.setString(42,"%"+TCari.getText()+"%");
            psradiologi.setString(43,Valid.SetTgl(TglBeli1.getSelectedItem()+""));
            psradiologi.setString(44,Valid.SetTgl(TglBeli2.getSelectedItem()+""));
            psradiologi.setString(45,"%"+NoKeluar.getText()+"%");
            psradiologi.setString(46,"%"+nmptg.getText()+"%");
            psradiologi.setString(47,"%"+kdjenis.getText()+"%");
            psradiologi.setString(48,"%"+nmbar.getText()+"%");
            psradiologi.setString(49,"%"+TCari.getText()+"%");
            psradiologi.setString(50,Valid.SetTgl(TglBeli1.getSelectedItem()+""));
            psradiologi.setString(51,Valid.SetTgl(TglBeli2.getSelectedItem()+""));
            psradiologi.setString(52,"%"+NoKeluar.getText()+"%");
            psradiologi.setString(53,"%"+nmptg.getText()+"%");
            psradiologi.setString(54,"%"+kdjenis.getText()+"%");
            psradiologi.setString(55,"%"+nmbar.getText()+"%");
            psradiologi.setString(56,"%"+TCari.getText()+"%");
            psradiologi.setString(57,Valid.SetTgl(TglBeli1.getSelectedItem()+""));
            psradiologi.setString(58,Valid.SetTgl(TglBeli2.getSelectedItem()+""));
            psradiologi.setString(59,"%"+NoKeluar.getText()+"%");
            psradiologi.setString(60,"%"+nmptg.getText()+"%");
            psradiologi.setString(61,"%"+kdjenis.getText()+"%");
            psradiologi.setString(62,"%"+nmbar.getText()+"%");
            psradiologi.setString(63,"%"+TCari.getText()+"%");
            rs=psradiologi.executeQuery();
            while(rs.next()){
                tabMode.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)+", "+rs.getString(5),"Stok Keluar :","","","","",""
                });                
                psradiologi2.setString(1,rs.getString(2));
                psradiologi2.setString(2,rs.getString(1));
                psradiologi2.setString(3,rs.getString(3));
                psradiologi2.setString(4,"%"+nmbar.getText()+"%");
                psradiologi2.setString(5,"%"+kdjenis.getText()+"%");
                psradiologi2.setString(6,"%"+TCari.getText()+"%");
                psradiologi2.setString(7,rs.getString(2));
                psradiologi2.setString(8,rs.getString(1));
                psradiologi2.setString(9,rs.getString(3));
                psradiologi2.setString(10,"%"+nmbar.getText()+"%");
                psradiologi2.setString(11,"%"+kdjenis.getText()+"%");
                psradiologi2.setString(12,"%"+TCari.getText()+"%");
                psradiologi2.setString(13,rs.getString(2));
                psradiologi2.setString(14,rs.getString(1));
                psradiologi2.setString(15,rs.getString(3));
                psradiologi2.setString(16,"%"+nmbar.getText()+"%");
                psradiologi2.setString(17,"%"+kdjenis.getText()+"%");
                psradiologi2.setString(18,"%"+TCari.getText()+"%");
                psradiologi2.setString(19,rs.getString(2));
                psradiologi2.setString(20,rs.getString(1));
                psradiologi2.setString(21,rs.getString(3));
                psradiologi2.setString(22,"%"+nmbar.getText()+"%");
                psradiologi2.setString(23,"%"+kdjenis.getText()+"%");
                psradiologi2.setString(24,"%"+TCari.getText()+"%");
                rs2=psradiologi2.executeQuery();
                int no=1;
                tagihan=0;
                while(rs2.next()){
                    tagihan=tagihan+rs2.getDouble(7);
                    ttltagihan=ttltagihan+rs2.getDouble(7);
                    tabMode.addRow(new Object[]{"","","","",no+". "+rs2.getString(1)+", "+rs2.getString(2),
                                    rs2.getString(3)+", "+rs2.getString(4),rs2.getString(5),Valid.SetAngka(rs2.getDouble(6)),Valid.SetAngka(rs2.getDouble(7))});
                    no++;
                }
                tabMode.addRow(new Object[]{"","","","","Total ","","","",Valid.SetAngka(tagihan)});
            }
            LTotal.setText(""+Valid.SetAngka(ttltagihan));
        }catch(SQLException e){
            System.out.println("Notifikasi : "+e);
        }        
    }

    public void emptTeks() {
        kdbar.setText("");
        nmbar.setText("");
        kdbar.requestFocus();        
    }
    
    public void isCek(){
        BtnPrint.setEnabled(akses.getipsrs_stok_keluar());
    }
    
}
