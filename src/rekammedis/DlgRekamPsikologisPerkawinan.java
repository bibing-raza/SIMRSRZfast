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
package rekammedis;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.HyperlinkEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariPetugas;
import simrskhanza.DlgCariDokter;
import simrskhanza.DlgCariPenyakit;

/**
 *
 * @author dosen
 */
public final class DlgRekamPsikologisPerkawinan extends javax.swing.JDialog {
    private final DefaultTableModel tabMode1, tabMode2, tabMode3, tabMode4, tabMode5,
            tabMode6, tabMode7, tabMode8, tabMode9, tabMode10, tabMode11, tabMode12,
            tabModeSL, tabModeSK, tabModeIL, tabModeIK;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6, ps7, ps8, ps9, ps10, ps11, ps12, ps13, ps14, ps15, ps16, ps17;
    private ResultSet rs, rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8, rs9, rs10, rs11, rs12, rs13, rs14, rs15, rs16, rs17, 
            rsc1, rsc2, rsc3, rsc4, rsc5, rsc6, rsc7, rsc8, rsc9, rsc10, rsc11, rsc12, rsc13;    
    private DlgMasterRencanaTritmenPsikologi tritmen = new DlgMasterRencanaTritmenPsikologi(null, false);
    private DlgCariPenyakit penyakit = new DlgCariPenyakit(null, false);
    private DlgCariDokter petugas = new DlgCariDokter(null, false);
    private int i = 0, x = 0, cekRPP = 0, pilihan = 0;
    private final Properties prop = new Properties();
    private String tglKedatangan = "", wktSimpan = "", jkel = "", dataMasalah = "", dataTritmen = "", nomorR = "";

    /* Creates new form DlgPerawatan
     *
     * @param parent
     * @param modal
     */
    public DlgRekamPsikologisPerkawinan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8, 1);
        setSize(885, 674);

        tabMode1 = new DefaultTableModel(null, new Object[]{
            "norawat", "tglreg", "No. RM", "Tgl. Kedatangan", "Nama Pasien/Klien", "Jns. Kelamin", "Tempat & Tgl. Lahir",
            "Agama", "Suku", "Pendidikan", "Pekerjaan", "Status Perkawinan", "Hobi/Kegemaran", "Alamat", "No. Hp (Kontak Yg. Bisa Dihubungi)"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbdatadiri.setModel(tabMode1);
        tbdatadiri.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbdatadiri.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 15; i++) {
            TableColumn column = tbdatadiri.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(100);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setPreferredWidth(90);
            } else if (i == 8) {
                column.setPreferredWidth(90);
            } else if (i == 9) {
                column.setPreferredWidth(100);
            } else if (i == 10) {
                column.setPreferredWidth(250);
            } else if (i == 11) {
                column.setPreferredWidth(130);
            } else if (i == 12) {
                column.setPreferredWidth(250);
            } else if (i == 13) {
                column.setPreferredWidth(250);
            } else if (i == 14) {
                column.setPreferredWidth(190);
            } 
        }
        tbdatadiri.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode2 = new DefaultTableModel(null, new Object[]{
            "No. RM", "Nama Pasien/Klien", "Jns. Pasangan", "Nama Pasangan", "Umur", "Pendidikan", "Pekerjaan", "Agama",
            "Anak Ke", "Alamat", "No. HP", "Pernikahan Ke", "Tmpt. & Tgl. Menikah", "Lama Pernikahan", "Lama Kenal Sebelum Nikah",
            "Lama Pacaran Sebelum Nikah", "umur", "anakke", "bersaudara", "tempatnikah", "tglnikah", "waktu_simpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbIdentitasPas.setModel(tabMode2);
        tbIdentitasPas.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbIdentitasPas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 22; i++) {
            TableColumn column = tbIdentitasPas.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(65);
            } else if (i == 1) {
                column.setPreferredWidth(250);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(110);
            } else if (i == 6) {
                column.setPreferredWidth(150);
            } else if (i == 7) {
                column.setPreferredWidth(110);
            } else if (i == 8) {
                column.setPreferredWidth(120);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(120);
            } else if (i == 12) {
                column.setPreferredWidth(160);
            } else if (i == 13) {
                column.setPreferredWidth(110);
            } else if (i == 14) {
                column.setPreferredWidth(220);
            } else if (i == 15) {
                column.setPreferredWidth(220);
            } else if (i == 16) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 17) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 18) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 19) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 20) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 21) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbIdentitasPas.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode3 = new DefaultTableModel(null, new Object[]{
            "No. RM", "Nama Pasien/Klien", "Anak Ke", "Nama Anak", "Jns. Kelamin", "Usia", "K/A/T", "Pekerjaan/Sekolah",
            "umur", "id_umur", "waktusimpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbIdentitasAnak.setModel(tabMode3);
        tbIdentitasAnak.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbIdentitasAnak.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 11; i++) {
            TableColumn column = tbIdentitasAnak.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(65);
            } else if (i == 1) {
                column.setPreferredWidth(250);
            } else if (i == 2) {
                column.setPreferredWidth(75);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(80);                
            } else if (i == 7) {
                column.setPreferredWidth(250);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbIdentitasAnak.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode4 = new DefaultTableModel(null, new Object[]{
            "norawat", "No. RM", "Nama Pasien/Klien", "Permasalahan Dlm. Pernikahan", "Sudah Brp. Lama Masalah Ini", 
            "Alasan Mencari Bantuan", "Permasalahan Dinilai", "Karena"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbKeluhan.setModel(tabMode4);
        tbKeluhan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbKeluhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbKeluhan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(130);
            } else if (i == 7) {
                column.setPreferredWidth(250);
            } 
        }
        tbKeluhan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode5 = new DefaultTableModel(null, new Object[]{
            "norawat", "No. RM", "Nama Pasien", "Harapan Pernikahan", "Perubahan Yang Diinginkan Dari Diri Sendiri",
            "Perubahan Yang Diinginkan Dari Pasangan", "Kemungkinan Perbaikan Pernikahan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbHarapan.setModel(tabMode5);
        tbHarapan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbHarapan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 7; i++) {
            TableColumn column = tbHarapan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(350);
            } else if (i == 4) {
                column.setPreferredWidth(350);
            } else if (i == 5) {
                column.setPreferredWidth(350);
            } else if (i == 6) {
                column.setPreferredWidth(350);
            } 
        }
        tbHarapan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeSL = new DefaultTableModel(null, new Object[]{
            "norawat", "No.", "Kelebihan Suami", "waktusimpan", "jnspasangan", "jnspenilaian"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tblebihSuami.setModel(tabModeSL);
        tblebihSuami.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tblebihSuami.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tblebihSuami.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(30);
            } else if (i == 2) {
                column.setPreferredWidth(380);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tblebihSuami.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeSK = new DefaultTableModel(null, new Object[]{
            "norawat", "No.", "Kekurangan Suami", "waktusimpan", "jnspasangan", "jnspenilaian"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbkurangSuami.setModel(tabModeSK);
        tbkurangSuami.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbkurangSuami.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbkurangSuami.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(30);
            } else if (i == 2) {
                column.setPreferredWidth(380);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbkurangSuami.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeIL = new DefaultTableModel(null, new Object[]{
            "norawat", "No.", "Kelebihan Istri", "waktusimpan", "jnspasangan", "jnspenilaian"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tblebihIstri.setModel(tabModeIL);
        tblebihIstri.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tblebihIstri.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tblebihIstri.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(30);
            } else if (i == 2) {
                column.setPreferredWidth(380);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tblebihIstri.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeIK = new DefaultTableModel(null, new Object[]{
            "norawat", "No.", "Kekurangan Istri", "waktusimpan", "jnspasangan", "jnspenilaian"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbkurangIstri.setModel(tabModeIK);
        tbkurangIstri.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbkurangIstri.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbkurangIstri.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(30);
            } else if (i == 2) {
                column.setPreferredWidth(380);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbkurangIstri.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode6 = new DefaultTableModel(null, new Object[]{
            "norawat","No. RM", "Nama Pasien", "Wawancara", "Observasi"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbPemeriksaan.setModel(tabMode6);
        tbPemeriksaan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPemeriksaan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbPemeriksaan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(350);
            } else if (i == 4) {
                column.setPreferredWidth(350);
            }
        }
        tbPemeriksaan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode7 = new DefaultTableModel(null, new Object[]{
            "norawat", "No. RM", "Nama Pasien", "Rencana Tes", "Tes Psikologis", "Waktu", "Tester", "tglrencanates"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbTesPsiko.setModel(tabMode7);
        tbTesPsiko.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTesPsiko.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbTesPsiko.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(150);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbTesPsiko.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode8 = new DefaultTableModel(null, new Object[]{
            "norawat", "No. RM", "Nama Pasien", "Manifestasi Fungsi Psikologis"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbManifestasi.setModel(tabMode8);
        tbManifestasi.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbManifestasi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbManifestasi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(450);
            } 
        }
        tbManifestasi.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode9 = new DefaultTableModel(null, new Object[]{
            "norawat", "No. RM", "Nama Pasien", "Kesan Awal", "Diagnosis Utama (dx)", "Diagnosis Banding (dd)"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbDiagnosis.setModel(tabMode9);
        tbDiagnosis.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagnosis.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbDiagnosis.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(300);
            } else if (i == 4) {
                column.setPreferredWidth(300);
            } else if (i == 5) {
                column.setPreferredWidth(300);
            } 
        }
        tbDiagnosis.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode10 = new DefaultTableModel(null, new Object[]{
            "norawat", "No. RM", "Nama Pasien", "Prognosis"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbPrognosis.setModel(tabMode10);
        tbPrognosis.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPrognosis.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbPrognosis.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(450);
            }
        }
        tbPrognosis.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode11 = new DefaultTableModel(null, new Object[]{
            "kdrencana", "Deskrpisi Rencana Tritmen", "Keterangan Lainnya", "waktusimpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbItemTritmen.setModel(tabMode11);
        tbItemTritmen.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbItemTritmen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbItemTritmen.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(260);
            } else if (i == 2) {
                column.setPreferredWidth(300);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbItemTritmen.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode12 = new DefaultTableModel(null, new Object[]{
            "norawat", "No. RM", "Nama Pasien", "Rencana Tritmen", "Pertemuan Selanjutnya", "Catatan Tambahan", "waktusimpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbTritmen.setModel(tabMode12);
        tbTritmen.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTritmen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 7; i++) {
            TableColumn column = tbTritmen.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(350);
            } else if (i == 4) {
                column.setPreferredWidth(350);
            } else if (i == 5) {
                column.setPreferredWidth(350);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbTritmen.setDefaultRenderer(Object.class, new WarnaTable());
        
        tritmen.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRekamPsikologisPerkawinan")) {
                    if (tritmen.getTable().getSelectedRow() != -1) {
                        kdtritmen.setText(tritmen.getTable().getValueAt(tritmen.getTable().getSelectedRow(), 0).toString());
                        nmtritmen.setText(tritmen.getTable().getValueAt(tritmen.getTable().getSelectedRow(), 1).toString());
                        
                        if (kdtritmen.getText().equals("RT0025")) {
                            tritmenLain.setEnabled(true);
                            tritmenLain.setText("");
                            tritmenLain.requestFocus();
                        } else {
                            tritmenLain.setEnabled(false);
                            tritmenLain.setText("");
                        }
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
        
        tritmen.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgRekamPsikologisPerkawinan")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        tritmen.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        penyakit.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRekamPsikologisPerkawinan")) {
                    if (penyakit.getTable().getSelectedRow() != -1) {
                        if (pilihan == 1) {
                            TDiagUtama.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 0).toString() + " - " + penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 2).toString());
                            TDiagUtama.requestFocus();
                        } else if (pilihan == 2) {
                            TDiagBanding.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 0).toString() + " - " + penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 2).toString());
                            TDiagBanding.requestFocus();
                        }
                    }                
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
        
        penyakit.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgRekamPsikologisPerkawinan")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        penyakit.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });  
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRekamPsikologisPerkawinan")) {
                    if (petugas.getTable().getSelectedRow() != -1) {
//                        kdptg1.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        TnmPsikolog.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
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
        
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        TnamaPas.setDocument(new batasInput((byte) 40).getKata(TnamaPas));
        TumurPas.setDocument(new batasInput((byte) 3).getOnlyAngka(TumurPas));        
        Tanakke.setDocument(new batasInput((byte) 3).getOnlyAngka(Tanakke));
        Tdarike.setDocument(new batasInput((byte) 3).getOnlyAngka(Tdarike));
        TnohpPas.setDocument(new batasInput((byte) 13).getOnlyAngka(TnohpPas));
        TPernikahan.setDocument(new batasInput((byte) 20).getKata(TPernikahan));
        TtmpMenikah.setDocument(new batasInput((byte) 100).getKata(TtmpMenikah));
        TlmNikah.setDocument(new batasInput((byte) 100).getKata(TlmNikah));
        TlmKenal.setDocument(new batasInput((byte) 100).getKata(TlmKenal));
        TlmPacaran.setDocument(new batasInput((byte) 100).getKata(TlmPacaran));        
        TanakKe.setDocument(new batasInput((byte) 3).getOnlyAngka(TanakKe));
        TumurAnak.setDocument(new batasInput((byte) 2).getOnlyAngka(TumurAnak));        
        TnmAnak.setDocument(new batasInput((byte) 40).getKata(TnmAnak));        
        
        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (TabPsikologis.getSelectedIndex() == 0) {
                        tampilDataDiri();
                    } 
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (TabPsikologis.getSelectedIndex() == 0) {
                        tampilDataDiri();
                    } 
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (TabPsikologis.getSelectedIndex() == 0) {
                        tampilDataDiri();
                    } 
                }
            });
        }
        
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
        } catch (Exception e) {
            System.out.println("rekammedis.DlgRekamPsikologisDewasa : " + e);
        }
        
        LoadHTML.setEditable(true);
        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule("table { font: 11px tahoma;}"
                + ".adaborder {"
                + "    width: 100%;"
                + "  border-collapse: collapse;"
                + "}"
                + ""
                + ".adaborder th{"
                + "  border-bottom: 1px solid #edf2e8;"
                + "  border-right: 1px solid #edf2e8;"
                + "  padding: 5px;"
                + "}"
                + ""
                + ".adaborder td{"
                + "  border-bottom: 1px solid #edf2e8;"
                + "  border-right: 1px solid #edf2e8;"
                + "  padding: 5px;"
                + "}"
                + ""
                + ".noborder th{"
                + "  border: 0px;"
                + "  padding: 5px;"
                + "}"
                + ""
                + ".noborder td{"
                + "  border: 0px;"
                + "  padding: 5px;"
                + "}"); 
        Document doc = kit.createDefaultDocument();
        LoadHTML.setDocument(doc);
        LoadHTML.setEditable(false);
        
        LoadHTML.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
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
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnHapus = new javax.swing.JMenuItem();
        WindowDataTritmen = new javax.swing.JDialog();
        internalFrame20 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        nmtritmen = new widget.TextBox();
        kdtritmen = new widget.TextBox();
        jLabel99 = new widget.Label();
        tritmenLain = new widget.TextBox();
        btnTritmen = new widget.Button();
        jLabel100 = new widget.Label();
        panelGlass17 = new widget.panelisi();
        BtnSimpan3 = new widget.Button();
        BtnEdit2 = new widget.Button();
        BtnCloseIn4 = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        TabPsikologis = new javax.swing.JTabbedPane();
        internalFrame10 = new widget.InternalFrame();
        panelGlass16 = new widget.panelisi();
        jLabel72 = new widget.Label();
        TPekerjaan = new widget.TextBox();
        jLabel73 = new widget.Label();
        TPendidikan = new widget.TextBox();
        jLabel74 = new widget.Label();
        TSttsKawin = new widget.TextBox();
        jLabel76 = new widget.Label();
        jLabel77 = new widget.Label();
        TnoTelp = new widget.TextBox();
        jLabel71 = new widget.Label();
        Ttl = new widget.TextBox();
        Scroll7 = new widget.ScrollPane();
        TAlamat = new widget.TextArea();
        jLabel102 = new widget.Label();
        TAgama = new widget.TextBox();
        jLabel103 = new widget.Label();
        TSuku = new widget.TextBox();
        jLabel104 = new widget.Label();
        THobi = new widget.TextBox();
        jLabel105 = new widget.Label();
        Scroll3 = new widget.ScrollPane();
        tbdatadiri = new widget.Table();
        internalFrame4 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        tbIdentitasPas = new widget.Table();
        panelGlass11 = new widget.panelisi();
        jLabel12 = new widget.Label();
        jLabel14 = new widget.Label();
        jLabel21 = new widget.Label();
        TpekerPas = new widget.TextBox();
        TumurPas = new widget.TextBox();
        TnamaPas = new widget.TextBox();
        jLabel17 = new widget.Label();
        cmbPasangan = new widget.ComboBox();
        jLabel30 = new widget.Label();
        cmbPendidikan = new widget.ComboBox();
        jLabel35 = new widget.Label();
        cmbagama = new widget.ComboBox();
        jLabel36 = new widget.Label();
        Tanakke = new widget.TextBox();
        jLabel37 = new widget.Label();
        Tdarike = new widget.TextBox();
        jLabel38 = new widget.Label();
        Scroll36 = new widget.ScrollPane();
        Talamatpasangan = new widget.TextArea();
        jLabel40 = new widget.Label();
        jLabel106 = new widget.Label();
        jLabel107 = new widget.Label();
        TnohpPas = new widget.TextBox();
        jLabel108 = new widget.Label();
        TPernikahan = new widget.TextBox();
        jLabel109 = new widget.Label();
        TtmpMenikah = new widget.TextBox();
        jLabel110 = new widget.Label();
        tgl_nikah = new widget.Tanggal();
        jLabel111 = new widget.Label();
        TlmNikah = new widget.TextBox();
        jLabel112 = new widget.Label();
        TlmKenal = new widget.TextBox();
        jLabel113 = new widget.Label();
        TlmPacaran = new widget.TextBox();
        internalFrame5 = new widget.InternalFrame();
        Scroll4 = new widget.ScrollPane();
        tbIdentitasAnak = new widget.Table();
        panelGlass12 = new widget.panelisi();
        jLabel5 = new widget.Label();
        TumurAnak = new widget.TextBox();
        jLabel7 = new widget.Label();
        jLabel9 = new widget.Label();
        TnmAnak = new widget.TextBox();
        jLabel10 = new widget.Label();
        TpekerjaanSek = new widget.TextBox();
        jLabel16 = new widget.Label();
        cmbJK = new widget.ComboBox();
        cmbKetAnak = new widget.ComboBox();
        cmbidumur = new widget.ComboBox();
        jLabel13 = new widget.Label();
        TanakKe = new widget.TextBox();
        internalFrame8 = new widget.InternalFrame();
        Scroll10 = new widget.ScrollPane();
        tbKeluhan = new widget.Table();
        panelGlass14 = new widget.panelisi();
        jLabel43 = new widget.Label();
        jLabel58 = new widget.Label();
        Scroll38 = new widget.ScrollPane();
        TpermasalahanNikah = new widget.TextArea();
        jLabel52 = new widget.Label();
        jLabel53 = new widget.Label();
        jLabel54 = new widget.Label();
        Scroll41 = new widget.ScrollPane();
        TsudahBerapa = new widget.TextArea();
        Scroll42 = new widget.ScrollPane();
        TalasanBantuan = new widget.TextArea();
        jLabel55 = new widget.Label();
        jLabel56 = new widget.Label();
        jLabel57 = new widget.Label();
        jLabel59 = new widget.Label();
        RSangatSeriusKP = new widget.RadioButton();
        RSeriusKP = new widget.RadioButton();
        RKurangSeriusKP = new widget.RadioButton();
        jLabel114 = new widget.Label();
        Scroll43 = new widget.ScrollPane();
        Tkarena = new widget.TextArea();
        internalFrame9 = new widget.InternalFrame();
        Scroll15 = new widget.ScrollPane();
        tbHarapan = new widget.Table();
        panelGlass15 = new widget.panelisi();
        jLabel25 = new widget.Label();
        jLabel29 = new widget.Label();
        Scroll12 = new widget.ScrollPane();
        Tharapan = new widget.TextArea();
        Scroll13 = new widget.ScrollPane();
        TperubahanSendiri = new widget.TextArea();
        Scroll14 = new widget.ScrollPane();
        TperubahanPasangan = new widget.TextArea();
        jLabel26 = new widget.Label();
        Scroll19 = new widget.ScrollPane();
        Tkemungkinan = new widget.TextArea();
        jLabel31 = new widget.Label();
        jLabel60 = new widget.Label();
        jLabel115 = new widget.Label();
        jLabel27 = new widget.Label();
        jLabel116 = new widget.Label();
        internalFrame11 = new widget.InternalFrame();
        panelGlass13 = new widget.panelisi();
        Scroll16 = new widget.ScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel32 = new widget.Label();
        Scroll5 = new widget.ScrollPane();
        tblebihSuami = new widget.Table();
        Scroll8 = new widget.ScrollPane();
        tbkurangSuami = new widget.Table();
        jLabel33 = new widget.Label();
        jPanel6 = new javax.swing.JPanel();
        Scroll9 = new widget.ScrollPane();
        tblebihIstri = new widget.Table();
        jLabel34 = new widget.Label();
        Scroll11 = new widget.ScrollPane();
        tbkurangIstri = new widget.Table();
        jLabel39 = new widget.Label();
        panelGlass19 = new widget.panelisi();
        jLabel117 = new widget.Label();
        TdeskripsiNilai = new widget.TextBox();
        jLabel118 = new widget.Label();
        cmbPasanganKL = new widget.ComboBox();
        cmbPenilaian = new widget.ComboBox();
        jLabel119 = new widget.Label();
        internalFrame12 = new widget.InternalFrame();
        Scroll17 = new widget.ScrollPane();
        tbPemeriksaan = new widget.Table();
        panelGlass27 = new widget.panelisi();
        jLabel47 = new widget.Label();
        jLabel48 = new widget.Label();
        Scroll20 = new widget.ScrollPane();
        Twawancara = new widget.TextArea();
        Scroll21 = new widget.ScrollPane();
        Tobservasi = new widget.TextArea();
        internalFrame15 = new widget.InternalFrame();
        Scroll27 = new widget.ScrollPane();
        tbTesPsiko = new widget.Table();
        panelGlass29 = new widget.panelisi();
        jLabel90 = new widget.Label();
        jLabel91 = new widget.Label();
        jLabel92 = new widget.Label();
        jLabel93 = new widget.Label();
        TTesPsiko = new widget.TextBox();
        TWaktu = new widget.TextBox();
        TTester = new widget.TextBox();
        tgl_rencana = new widget.Tanggal();
        internalFrame16 = new widget.InternalFrame();
        Scroll28 = new widget.ScrollPane();
        tbManifestasi = new widget.Table();
        panelGlass30 = new widget.panelisi();
        jLabel94 = new widget.Label();
        Scroll29 = new widget.ScrollPane();
        TManifes = new widget.TextArea();
        internalFrame17 = new widget.InternalFrame();
        Scroll30 = new widget.ScrollPane();
        tbDiagnosis = new widget.Table();
        panelGlass31 = new widget.panelisi();
        jLabel95 = new widget.Label();
        Scroll31 = new widget.ScrollPane();
        TKesan = new widget.TextArea();
        jLabel96 = new widget.Label();
        TDiagUtama = new widget.TextBox();
        TDiagBanding = new widget.TextBox();
        jLabel97 = new widget.Label();
        btnPenyakit = new widget.Button();
        btnPenyakit1 = new widget.Button();
        internalFrame18 = new widget.InternalFrame();
        Scroll32 = new widget.ScrollPane();
        tbPrognosis = new widget.Table();
        panelGlass32 = new widget.panelisi();
        jLabel98 = new widget.Label();
        Scroll33 = new widget.ScrollPane();
        Tprognosis = new widget.TextArea();
        internalFrame19 = new widget.InternalFrame();
        Scroll34 = new widget.ScrollPane();
        tbTritmen = new widget.Table();
        panelGlass33 = new widget.panelisi();
        Scroll6 = new widget.ScrollPane();
        tbItemTritmen = new widget.Table();
        jLabel44 = new widget.Label();
        BtnPilihTritmen = new widget.Button();
        BtnHapusItem1 = new widget.Button();
        BtnPerbaikiItem1 = new widget.Button();
        jLabel45 = new widget.Label();
        TPertemuan = new widget.TextBox();
        jLabel46 = new widget.Label();
        TCatatan = new widget.TextBox();
        internalFrame21 = new widget.InternalFrame();
        Scroll35 = new widget.ScrollPane();
        LoadHTML = new widget.editorpane();
        panelGlass22 = new widget.panelisi();
        panelGlass9 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass23 = new widget.panelisi();
        jLabel41 = new widget.Label();
        tgl1 = new widget.Tanggal();
        jLabel42 = new widget.Label();
        tgl2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        jLabel8 = new widget.Label();
        cmbLimit = new widget.ComboBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel19 = new widget.Label();
        LCount = new widget.Label();
        panelGlass24 = new widget.panelisi();
        jLabel3 = new widget.Label();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel11 = new widget.Label();
        tgl_datang = new widget.TextBox();
        jLabel70 = new widget.Label();
        Tjk = new widget.TextBox();
        TNoRW = new widget.TextBox();
        jLabel101 = new widget.Label();
        TnmPsikolog = new widget.TextBox();
        btnPetugas = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnHapus.setBackground(new java.awt.Color(242, 242, 242));
        MnHapus.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        MnHapus.setText("Hapus Data");
        MnHapus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapus.setIconTextGap(5);
        MnHapus.setName("MnHapus"); // NOI18N
        MnHapus.setPreferredSize(new java.awt.Dimension(130, 26));
        MnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHapus);

        WindowDataTritmen.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowDataTritmen.setName("WindowDataTritmen"); // NOI18N
        WindowDataTritmen.setUndecorated(true);
        WindowDataTritmen.setResizable(false);

        internalFrame20.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pilihan Rencana Tritmen ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame20.setName("internalFrame20"); // NOI18N
        internalFrame20.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame20.setLayout(new java.awt.BorderLayout());

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 70));
        panelGlass8.setLayout(null);

        nmtritmen.setEditable(false);
        nmtritmen.setForeground(new java.awt.Color(0, 0, 0));
        nmtritmen.setName("nmtritmen"); // NOI18N
        panelGlass8.add(nmtritmen);
        nmtritmen.setBounds(227, 10, 370, 23);

        kdtritmen.setEditable(false);
        kdtritmen.setForeground(new java.awt.Color(0, 0, 0));
        kdtritmen.setName("kdtritmen"); // NOI18N
        panelGlass8.add(kdtritmen);
        kdtritmen.setBounds(143, 10, 80, 23);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("Tritmen Lainnya : ");
        jLabel99.setName("jLabel99"); // NOI18N
        panelGlass8.add(jLabel99);
        jLabel99.setBounds(0, 37, 140, 23);

        tritmenLain.setForeground(new java.awt.Color(0, 0, 0));
        tritmenLain.setName("tritmenLain"); // NOI18N
        panelGlass8.add(tritmenLain);
        tritmenLain.setBounds(143, 37, 454, 23);

        btnTritmen.setForeground(new java.awt.Color(0, 0, 0));
        btnTritmen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnTritmen.setMnemonic('X');
        btnTritmen.setToolTipText("Alt+X");
        btnTritmen.setName("btnTritmen"); // NOI18N
        btnTritmen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTritmenActionPerformed(evt);
            }
        });
        panelGlass8.add(btnTritmen);
        btnTritmen.setBounds(600, 10, 28, 23);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("Nama Rencana Tritmen : ");
        jLabel100.setName("jLabel100"); // NOI18N
        panelGlass8.add(jLabel100);
        jLabel100.setBounds(0, 10, 140, 23);

        internalFrame20.add(panelGlass8, java.awt.BorderLayout.PAGE_START);

        panelGlass17.setName("panelGlass17"); // NOI18N
        panelGlass17.setPreferredSize(new java.awt.Dimension(44, 150));
        panelGlass17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 9, 5));

        BtnSimpan3.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan3.setMnemonic('S');
        BtnSimpan3.setText("Simpan");
        BtnSimpan3.setToolTipText("Alt+S");
        BtnSimpan3.setName("BtnSimpan3"); // NOI18N
        BtnSimpan3.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan3ActionPerformed(evt);
            }
        });
        panelGlass17.add(BtnSimpan3);

        BtnEdit2.setForeground(new java.awt.Color(0, 0, 0));
        BtnEdit2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit2.setMnemonic('G');
        BtnEdit2.setText("Ganti");
        BtnEdit2.setToolTipText("Alt+G");
        BtnEdit2.setName("BtnEdit2"); // NOI18N
        BtnEdit2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEdit2ActionPerformed(evt);
            }
        });
        panelGlass17.add(BtnEdit2);

        BtnCloseIn4.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn4.setMnemonic('U');
        BtnCloseIn4.setText("Tutup");
        BtnCloseIn4.setToolTipText("Alt+U");
        BtnCloseIn4.setName("BtnCloseIn4"); // NOI18N
        BtnCloseIn4.setPreferredSize(new java.awt.Dimension(73, 30));
        BtnCloseIn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn4ActionPerformed(evt);
            }
        });
        panelGlass17.add(BtnCloseIn4);

        internalFrame20.add(panelGlass17, java.awt.BorderLayout.CENTER);

        WindowDataTritmen.getContentPane().add(internalFrame20, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Rekam Psikologi Perkawinan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabPsikologis.setBackground(new java.awt.Color(169, 169, 250));
        TabPsikologis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        TabPsikologis.setForeground(new java.awt.Color(0, 0, 0));
        TabPsikologis.setTabPlacement(javax.swing.JTabbedPane.RIGHT);
        TabPsikologis.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TabPsikologis.setName("TabPsikologis"); // NOI18N
        TabPsikologis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabPsikologisMouseClicked(evt);
            }
        });

        internalFrame10.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame10.setBorder(null);
        internalFrame10.setName("internalFrame10"); // NOI18N
        internalFrame10.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass16.setName("panelGlass16"); // NOI18N
        panelGlass16.setPreferredSize(new java.awt.Dimension(44, 280));
        panelGlass16.setLayout(null);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Pekerjaan : ");
        jLabel72.setName("jLabel72"); // NOI18N
        panelGlass16.add(jLabel72);
        jLabel72.setBounds(0, 94, 130, 23);

        TPekerjaan.setEditable(false);
        TPekerjaan.setForeground(new java.awt.Color(0, 0, 0));
        TPekerjaan.setName("TPekerjaan"); // NOI18N
        panelGlass16.add(TPekerjaan);
        TPekerjaan.setBounds(133, 94, 592, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Pendidikan : ");
        jLabel73.setName("jLabel73"); // NOI18N
        panelGlass16.add(jLabel73);
        jLabel73.setBounds(0, 66, 130, 23);

        TPendidikan.setEditable(false);
        TPendidikan.setForeground(new java.awt.Color(0, 0, 0));
        TPendidikan.setName("TPendidikan"); // NOI18N
        panelGlass16.add(TPendidikan);
        TPendidikan.setBounds(133, 66, 210, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Status Perkawinan : ");
        jLabel74.setName("jLabel74"); // NOI18N
        panelGlass16.add(jLabel74);
        jLabel74.setBounds(0, 122, 130, 23);

        TSttsKawin.setEditable(false);
        TSttsKawin.setForeground(new java.awt.Color(0, 0, 0));
        TSttsKawin.setName("TSttsKawin"); // NOI18N
        panelGlass16.add(TSttsKawin);
        TSttsKawin.setBounds(133, 122, 210, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("Alamat : ");
        jLabel76.setName("jLabel76"); // NOI18N
        panelGlass16.add(jLabel76);
        jLabel76.setBounds(0, 178, 130, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("No. HP (Kontak Yang) : ");
        jLabel77.setName("jLabel77"); // NOI18N
        panelGlass16.add(jLabel77);
        jLabel77.setBounds(0, 234, 130, 23);

        TnoTelp.setForeground(new java.awt.Color(0, 0, 0));
        TnoTelp.setName("TnoTelp"); // NOI18N
        TnoTelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnoTelpKeyPressed(evt);
            }
        });
        panelGlass16.add(TnoTelp);
        TnoTelp.setBounds(133, 234, 210, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Tempat & Tgl. Lahir : ");
        jLabel71.setName("jLabel71"); // NOI18N
        panelGlass16.add(jLabel71);
        jLabel71.setBounds(0, 10, 130, 23);

        Ttl.setEditable(false);
        Ttl.setForeground(new java.awt.Color(0, 0, 0));
        Ttl.setName("Ttl"); // NOI18N
        panelGlass16.add(Ttl);
        Ttl.setBounds(133, 10, 592, 23);

        Scroll7.setName("Scroll7"); // NOI18N

        TAlamat.setEditable(false);
        TAlamat.setColumns(20);
        TAlamat.setRows(5);
        TAlamat.setName("TAlamat"); // NOI18N
        Scroll7.setViewportView(TAlamat);

        panelGlass16.add(Scroll7);
        Scroll7.setBounds(133, 178, 592, 50);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setText("Agama : ");
        jLabel102.setName("jLabel102"); // NOI18N
        panelGlass16.add(jLabel102);
        jLabel102.setBounds(0, 38, 130, 23);

        TAgama.setEditable(false);
        TAgama.setForeground(new java.awt.Color(0, 0, 0));
        TAgama.setName("TAgama"); // NOI18N
        panelGlass16.add(TAgama);
        TAgama.setBounds(133, 38, 280, 23);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("Suku : ");
        jLabel103.setName("jLabel103"); // NOI18N
        panelGlass16.add(jLabel103);
        jLabel103.setBounds(461, 38, 50, 23);

        TSuku.setEditable(false);
        TSuku.setForeground(new java.awt.Color(0, 0, 0));
        TSuku.setName("TSuku"); // NOI18N
        panelGlass16.add(TSuku);
        TSuku.setBounds(512, 38, 212, 23);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setText("Hobi / Kegemaran : ");
        jLabel104.setName("jLabel104"); // NOI18N
        panelGlass16.add(jLabel104);
        jLabel104.setBounds(0, 150, 130, 23);

        THobi.setForeground(new java.awt.Color(0, 0, 0));
        THobi.setName("THobi"); // NOI18N
        THobi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                THobiKeyPressed(evt);
            }
        });
        panelGlass16.add(THobi);
        THobi.setBounds(133, 150, 592, 23);

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setText("Bisa Dihubungi)  ");
        jLabel105.setName("jLabel105"); // NOI18N
        panelGlass16.add(jLabel105);
        jLabel105.setBounds(0, 248, 130, 23);

        internalFrame10.add(panelGlass16, java.awt.BorderLayout.PAGE_START);

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);
        Scroll3.setPreferredSize(new java.awt.Dimension(452, 402));

        tbdatadiri.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbdatadiri.setName("tbdatadiri"); // NOI18N
        tbdatadiri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbdatadiriMouseClicked(evt);
            }
        });
        tbdatadiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbdatadiriKeyPressed(evt);
            }
        });
        Scroll3.setViewportView(tbdatadiri);

        internalFrame10.add(Scroll3, java.awt.BorderLayout.CENTER);

        TabPsikologis.addTab("Data Diri Pasien / Klien", internalFrame10);

        internalFrame4.setBorder(null);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout());

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);
        Scroll2.setPreferredSize(new java.awt.Dimension(452, 401));
        Scroll2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Scroll2KeyPressed(evt);
            }
        });

        tbIdentitasPas.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbIdentitasPas.setName("tbIdentitasPas"); // NOI18N
        tbIdentitasPas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbIdentitasPasMouseClicked(evt);
            }
        });
        tbIdentitasPas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbIdentitasPasKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbIdentitasPas);

        internalFrame4.add(Scroll2, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 225));
        panelGlass11.setLayout(null);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Umur : ");
        jLabel12.setName("jLabel12"); // NOI18N
        panelGlass11.add(jLabel12);
        jLabel12.setBounds(0, 38, 130, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Pendidikan : ");
        jLabel14.setName("jLabel14"); // NOI18N
        panelGlass11.add(jLabel14);
        jLabel14.setBounds(230, 38, 80, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Pekerjaan : ");
        jLabel21.setName("jLabel21"); // NOI18N
        panelGlass11.add(jLabel21);
        jLabel21.setBounds(0, 66, 130, 23);

        TpekerPas.setForeground(new java.awt.Color(0, 0, 0));
        TpekerPas.setName("TpekerPas"); // NOI18N
        TpekerPas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpekerPasKeyPressed(evt);
            }
        });
        panelGlass11.add(TpekerPas);
        TpekerPas.setBounds(132, 66, 430, 23);

        TumurPas.setForeground(new java.awt.Color(0, 0, 0));
        TumurPas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TumurPas.setName("TumurPas"); // NOI18N
        TumurPas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumurPasKeyPressed(evt);
            }
        });
        panelGlass11.add(TumurPas);
        TumurPas.setBounds(132, 38, 50, 23);

        TnamaPas.setForeground(new java.awt.Color(0, 0, 0));
        TnamaPas.setName("TnamaPas"); // NOI18N
        TnamaPas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnamaPasKeyPressed(evt);
            }
        });
        panelGlass11.add(TnamaPas);
        TnamaPas.setBounds(252, 10, 310, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Jenis Pasangan : ");
        jLabel17.setName("jLabel17"); // NOI18N
        panelGlass11.add(jLabel17);
        jLabel17.setBounds(0, 10, 130, 23);

        cmbPasangan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPasangan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SUAMI", "ISTRI" }));
        cmbPasangan.setName("cmbPasangan"); // NOI18N
        cmbPasangan.setOpaque(false);
        cmbPasangan.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass11.add(cmbPasangan);
        cmbPasangan.setBounds(132, 10, 70, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("Tahun");
        jLabel30.setName("jLabel30"); // NOI18N
        panelGlass11.add(jLabel30);
        jLabel30.setBounds(188, 38, 40, 23);

        cmbPendidikan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPendidikan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "TIDAK SEKOLAH", "TK", "SD/MI", "SMP/SLTP/MTS", "SMA/SLTA/SMK/MAN", "PESANTREN", "D1", "D3", "D4", "S1", "S2", "S3" }));
        cmbPendidikan.setName("cmbPendidikan"); // NOI18N
        cmbPendidikan.setOpaque(false);
        cmbPendidikan.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass11.add(cmbPendidikan);
        cmbPendidikan.setBounds(312, 38, 140, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Agama : ");
        jLabel35.setName("jLabel35"); // NOI18N
        panelGlass11.add(jLabel35);
        jLabel35.setBounds(0, 94, 130, 23);

        cmbagama.setForeground(new java.awt.Color(0, 0, 0));
        cmbagama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Islam", "Hindu", "Budha", "Kristen Protestan", "Kristen Katolik", "Konghucu", "Kepercayaan Lain" }));
        cmbagama.setName("cmbagama"); // NOI18N
        cmbagama.setOpaque(false);
        cmbagama.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass11.add(cmbagama);
        cmbagama.setBounds(132, 94, 120, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Anak Ke : ");
        jLabel36.setName("jLabel36"); // NOI18N
        panelGlass11.add(jLabel36);
        jLabel36.setBounds(252, 94, 60, 23);

        Tanakke.setForeground(new java.awt.Color(0, 0, 0));
        Tanakke.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tanakke.setName("Tanakke"); // NOI18N
        Tanakke.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanakkeKeyPressed(evt);
            }
        });
        panelGlass11.add(Tanakke);
        Tanakke.setBounds(315, 94, 50, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("dari");
        jLabel37.setName("jLabel37"); // NOI18N
        panelGlass11.add(jLabel37);
        jLabel37.setBounds(365, 94, 30, 23);

        Tdarike.setForeground(new java.awt.Color(0, 0, 0));
        Tdarike.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tdarike.setName("Tdarike"); // NOI18N
        Tdarike.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdarikeKeyPressed(evt);
            }
        });
        panelGlass11.add(Tdarike);
        Tdarike.setBounds(396, 94, 50, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel38.setText("bersaudara");
        jLabel38.setName("jLabel38"); // NOI18N
        panelGlass11.add(jLabel38);
        jLabel38.setBounds(452, 94, 70, 23);

        Scroll36.setName("Scroll36"); // NOI18N
        Scroll36.setOpaque(true);

        Talamatpasangan.setColumns(20);
        Talamatpasangan.setRows(5);
        Talamatpasangan.setName("Talamatpasangan"); // NOI18N
        Talamatpasangan.setPreferredSize(new java.awt.Dimension(170, 110));
        Talamatpasangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalamatpasanganKeyPressed(evt);
            }
        });
        Scroll36.setViewportView(Talamatpasangan);

        panelGlass11.add(Scroll36);
        Scroll36.setBounds(132, 122, 430, 60);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Alamat : ");
        jLabel40.setName("jLabel40"); // NOI18N
        panelGlass11.add(jLabel40);
        jLabel40.setBounds(0, 122, 130, 23);

        jLabel106.setForeground(new java.awt.Color(0, 0, 0));
        jLabel106.setText("Nama : ");
        jLabel106.setName("jLabel106"); // NOI18N
        panelGlass11.add(jLabel106);
        jLabel106.setBounds(202, 10, 50, 23);

        jLabel107.setForeground(new java.awt.Color(0, 0, 0));
        jLabel107.setText("No. HP : ");
        jLabel107.setName("jLabel107"); // NOI18N
        panelGlass11.add(jLabel107);
        jLabel107.setBounds(0, 188, 130, 23);

        TnohpPas.setForeground(new java.awt.Color(0, 0, 0));
        TnohpPas.setName("TnohpPas"); // NOI18N
        TnohpPas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnohpPasKeyPressed(evt);
            }
        });
        panelGlass11.add(TnohpPas);
        TnohpPas.setBounds(132, 188, 180, 23);

        jLabel108.setForeground(new java.awt.Color(0, 0, 0));
        jLabel108.setText("Pernikahan Ke : ");
        jLabel108.setName("jLabel108"); // NOI18N
        panelGlass11.add(jLabel108);
        jLabel108.setBounds(562, 10, 115, 23);

        TPernikahan.setForeground(new java.awt.Color(0, 0, 0));
        TPernikahan.setName("TPernikahan"); // NOI18N
        TPernikahan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPernikahanKeyPressed(evt);
            }
        });
        panelGlass11.add(TPernikahan);
        TPernikahan.setBounds(678, 10, 250, 23);

        jLabel109.setForeground(new java.awt.Color(0, 0, 0));
        jLabel109.setText("Tempat Menikah : ");
        jLabel109.setName("jLabel109"); // NOI18N
        panelGlass11.add(jLabel109);
        jLabel109.setBounds(562, 38, 115, 23);

        TtmpMenikah.setForeground(new java.awt.Color(0, 0, 0));
        TtmpMenikah.setName("TtmpMenikah"); // NOI18N
        TtmpMenikah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtmpMenikahKeyPressed(evt);
            }
        });
        panelGlass11.add(TtmpMenikah);
        TtmpMenikah.setBounds(678, 38, 250, 23);

        jLabel110.setForeground(new java.awt.Color(0, 0, 0));
        jLabel110.setText("Tgl. Menikah : ");
        jLabel110.setName("jLabel110"); // NOI18N
        panelGlass11.add(jLabel110);
        jLabel110.setBounds(562, 66, 115, 23);

        tgl_nikah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21-10-2022" }));
        tgl_nikah.setDisplayFormat("dd-MM-yyyy");
        tgl_nikah.setName("tgl_nikah"); // NOI18N
        tgl_nikah.setOpaque(false);
        tgl_nikah.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass11.add(tgl_nikah);
        tgl_nikah.setBounds(678, 66, 90, 23);

        jLabel111.setForeground(new java.awt.Color(0, 0, 0));
        jLabel111.setText("Lama Pernikahan : ");
        jLabel111.setName("jLabel111"); // NOI18N
        panelGlass11.add(jLabel111);
        jLabel111.setBounds(562, 94, 115, 23);

        TlmNikah.setForeground(new java.awt.Color(0, 0, 0));
        TlmNikah.setName("TlmNikah"); // NOI18N
        TlmNikah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlmNikahKeyPressed(evt);
            }
        });
        panelGlass11.add(TlmNikah);
        TlmNikah.setBounds(678, 94, 250, 23);

        jLabel112.setForeground(new java.awt.Color(0, 0, 0));
        jLabel112.setText("Lama Berkenalan Sebelum Menikah : ");
        jLabel112.setName("jLabel112"); // NOI18N
        panelGlass11.add(jLabel112);
        jLabel112.setBounds(562, 122, 205, 23);

        TlmKenal.setForeground(new java.awt.Color(0, 0, 0));
        TlmKenal.setName("TlmKenal"); // NOI18N
        TlmKenal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlmKenalKeyPressed(evt);
            }
        });
        panelGlass11.add(TlmKenal);
        TlmKenal.setBounds(768, 122, 160, 23);

        jLabel113.setForeground(new java.awt.Color(0, 0, 0));
        jLabel113.setText("Lama Berpacaran Sebelum Menikah : ");
        jLabel113.setName("jLabel113"); // NOI18N
        panelGlass11.add(jLabel113);
        jLabel113.setBounds(562, 150, 205, 23);

        TlmPacaran.setForeground(new java.awt.Color(0, 0, 0));
        TlmPacaran.setName("TlmPacaran"); // NOI18N
        TlmPacaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlmPacaranKeyPressed(evt);
            }
        });
        panelGlass11.add(TlmPacaran);
        TlmPacaran.setBounds(768, 150, 160, 23);

        internalFrame4.add(panelGlass11, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Identitas Pasangan", internalFrame4);

        internalFrame5.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame5.setBorder(null);
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);
        Scroll4.setPreferredSize(new java.awt.Dimension(452, 311));

        tbIdentitasAnak.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbIdentitasAnak.setName("tbIdentitasAnak"); // NOI18N
        tbIdentitasAnak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbIdentitasAnakMouseClicked(evt);
            }
        });
        tbIdentitasAnak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbIdentitasAnakKeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbIdentitasAnak);

        internalFrame5.add(Scroll4, java.awt.BorderLayout.CENTER);

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 105));
        panelGlass12.setLayout(null);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Umur : ");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass12.add(jLabel5);
        jLabel5.setBounds(224, 38, 110, 23);

        TumurAnak.setForeground(new java.awt.Color(0, 0, 0));
        TumurAnak.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TumurAnak.setName("TumurAnak"); // NOI18N
        TumurAnak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumurAnakKeyPressed(evt);
            }
        });
        panelGlass12.add(TumurAnak);
        TumurAnak.setBounds(337, 38, 50, 23);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Jenis Kelamin : ");
        jLabel7.setName("jLabel7"); // NOI18N
        panelGlass12.add(jLabel7);
        jLabel7.setBounds(0, 38, 130, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Nama : ");
        jLabel9.setName("jLabel9"); // NOI18N
        panelGlass12.add(jLabel9);
        jLabel9.setBounds(224, 10, 110, 23);

        TnmAnak.setForeground(new java.awt.Color(0, 0, 0));
        TnmAnak.setName("TnmAnak"); // NOI18N
        TnmAnak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmAnakKeyPressed(evt);
            }
        });
        panelGlass12.add(TnmAnak);
        TnmAnak.setBounds(337, 10, 320, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Pekerjaan/Sekolah : ");
        jLabel10.setName("jLabel10"); // NOI18N
        panelGlass12.add(jLabel10);
        jLabel10.setBounds(224, 66, 110, 23);

        TpekerjaanSek.setForeground(new java.awt.Color(0, 0, 0));
        TpekerjaanSek.setName("TpekerjaanSek"); // NOI18N
        TpekerjaanSek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpekerjaanSekKeyPressed(evt);
            }
        });
        panelGlass12.add(TpekerjaanSek);
        TpekerjaanSek.setBounds(337, 66, 320, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Keterangan : ");
        jLabel16.setName("jLabel16"); // NOI18N
        panelGlass12.add(jLabel16);
        jLabel16.setBounds(0, 66, 130, 23);

        cmbJK.setForeground(new java.awt.Color(0, 0, 0));
        cmbJK.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Laki-laki", "Perempuan" }));
        cmbJK.setName("cmbJK"); // NOI18N
        cmbJK.setOpaque(false);
        cmbJK.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass12.add(cmbJK);
        cmbJK.setBounds(133, 38, 90, 23);

        cmbKetAnak.setForeground(new java.awt.Color(0, 0, 0));
        cmbKetAnak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kandung", "Angkat", "Tiri" }));
        cmbKetAnak.setName("cmbKetAnak"); // NOI18N
        cmbKetAnak.setOpaque(false);
        cmbKetAnak.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass12.add(cmbKetAnak);
        cmbKetAnak.setBounds(133, 66, 80, 23);

        cmbidumur.setForeground(new java.awt.Color(0, 0, 0));
        cmbidumur.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tahun", "Bulan", "Hari" }));
        cmbidumur.setName("cmbidumur"); // NOI18N
        cmbidumur.setOpaque(false);
        cmbidumur.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass12.add(cmbidumur);
        cmbidumur.setBounds(395, 38, 70, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Anak Ke : ");
        jLabel13.setName("jLabel13"); // NOI18N
        panelGlass12.add(jLabel13);
        jLabel13.setBounds(0, 10, 130, 23);

        TanakKe.setForeground(new java.awt.Color(0, 0, 0));
        TanakKe.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TanakKe.setName("TanakKe"); // NOI18N
        TanakKe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanakKeKeyPressed(evt);
            }
        });
        panelGlass12.add(TanakKe);
        TanakKe.setBounds(133, 10, 50, 23);

        internalFrame5.add(panelGlass12, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Identitas Anak", internalFrame5);

        internalFrame8.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame8.setBorder(null);
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll10.setName("Scroll10"); // NOI18N
        Scroll10.setOpaque(true);

        tbKeluhan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbKeluhan.setName("tbKeluhan"); // NOI18N
        tbKeluhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKeluhanMouseClicked(evt);
            }
        });
        tbKeluhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbKeluhanKeyPressed(evt);
            }
        });
        Scroll10.setViewportView(tbKeluhan);

        internalFrame8.add(Scroll10, java.awt.BorderLayout.CENTER);

        panelGlass14.setName("panelGlass14"); // NOI18N
        panelGlass14.setPreferredSize(new java.awt.Dimension(44, 242));
        panelGlass14.setLayout(null);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Permasalahan dalam   ");
        jLabel43.setName("jLabel43"); // NOI18N
        panelGlass14.add(jLabel43);
        jLabel43.setBounds(0, 10, 130, 23);

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("Pernikahan : ");
        jLabel58.setName("jLabel58"); // NOI18N
        panelGlass14.add(jLabel58);
        jLabel58.setBounds(0, 25, 130, 23);

        Scroll38.setName("Scroll38"); // NOI18N
        Scroll38.setOpaque(true);

        TpermasalahanNikah.setColumns(20);
        TpermasalahanNikah.setRows(5);
        TpermasalahanNikah.setName("TpermasalahanNikah"); // NOI18N
        TpermasalahanNikah.setPreferredSize(new java.awt.Dimension(170, 110));
        TpermasalahanNikah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpermasalahanNikahKeyPressed(evt);
            }
        });
        Scroll38.setViewportView(TpermasalahanNikah);

        panelGlass14.add(Scroll38);
        Scroll38.setBounds(132, 10, 430, 70);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("Sudah Berapa Lama   ");
        jLabel52.setName("jLabel52"); // NOI18N
        panelGlass14.add(jLabel52);
        jLabel52.setBounds(0, 85, 130, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Permasalahan Ini   ");
        jLabel53.setName("jLabel53"); // NOI18N
        panelGlass14.add(jLabel53);
        jLabel53.setBounds(0, 100, 130, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Terjadi : ");
        jLabel54.setName("jLabel54"); // NOI18N
        panelGlass14.add(jLabel54);
        jLabel54.setBounds(0, 115, 130, 23);

        Scroll41.setName("Scroll41"); // NOI18N
        Scroll41.setOpaque(true);

        TsudahBerapa.setColumns(20);
        TsudahBerapa.setRows(5);
        TsudahBerapa.setName("TsudahBerapa"); // NOI18N
        TsudahBerapa.setPreferredSize(new java.awt.Dimension(170, 110));
        TsudahBerapa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsudahBerapaKeyPressed(evt);
            }
        });
        Scroll41.setViewportView(TsudahBerapa);

        panelGlass14.add(Scroll41);
        Scroll41.setBounds(132, 85, 430, 70);

        Scroll42.setName("Scroll42"); // NOI18N
        Scroll42.setOpaque(true);

        TalasanBantuan.setColumns(20);
        TalasanBantuan.setRows(5);
        TalasanBantuan.setName("TalasanBantuan"); // NOI18N
        TalasanBantuan.setPreferredSize(new java.awt.Dimension(170, 110));
        TalasanBantuan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalasanBantuanKeyPressed(evt);
            }
        });
        Scroll42.setViewportView(TalasanBantuan);

        panelGlass14.add(Scroll42);
        Scroll42.setBounds(132, 161, 430, 70);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("Alasan Yang Membuat   ");
        jLabel55.setName("jLabel55"); // NOI18N
        panelGlass14.add(jLabel55);
        jLabel55.setBounds(0, 161, 130, 23);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Mencari Bantuan   ");
        jLabel56.setName("jLabel56"); // NOI18N
        panelGlass14.add(jLabel56);
        jLabel56.setBounds(0, 176, 130, 23);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Kali ini : ");
        jLabel57.setName("jLabel57"); // NOI18N
        panelGlass14.add(jLabel57);
        jLabel57.setBounds(0, 191, 130, 23);

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("Permasalahan Saat Ini Dinilai : ");
        jLabel59.setName("jLabel59"); // NOI18N
        panelGlass14.add(jLabel59);
        jLabel59.setBounds(560, 10, 170, 23);

        RSangatSeriusKP.setBackground(new java.awt.Color(240, 250, 230));
        RSangatSeriusKP.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(RSangatSeriusKP);
        RSangatSeriusKP.setText("Sangat Serius");
        RSangatSeriusKP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RSangatSeriusKP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        RSangatSeriusKP.setName("RSangatSeriusKP"); // NOI18N
        RSangatSeriusKP.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass14.add(RSangatSeriusKP);
        RSangatSeriusKP.setBounds(734, 10, 100, 23);

        RSeriusKP.setBackground(new java.awt.Color(240, 250, 230));
        RSeriusKP.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(RSeriusKP);
        RSeriusKP.setText("Serius");
        RSeriusKP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RSeriusKP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        RSeriusKP.setName("RSeriusKP"); // NOI18N
        RSeriusKP.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass14.add(RSeriusKP);
        RSeriusKP.setBounds(734, 38, 100, 23);

        RKurangSeriusKP.setBackground(new java.awt.Color(240, 250, 230));
        RKurangSeriusKP.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(RKurangSeriusKP);
        RKurangSeriusKP.setText("Kurang Serius");
        RKurangSeriusKP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RKurangSeriusKP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        RKurangSeriusKP.setName("RKurangSeriusKP"); // NOI18N
        RKurangSeriusKP.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass14.add(RKurangSeriusKP);
        RKurangSeriusKP.setBounds(734, 66, 100, 23);

        jLabel114.setForeground(new java.awt.Color(0, 0, 0));
        jLabel114.setText("Karena : ");
        jLabel114.setName("jLabel114"); // NOI18N
        panelGlass14.add(jLabel114);
        jLabel114.setBounds(560, 94, 70, 23);

        Scroll43.setName("Scroll43"); // NOI18N
        Scroll43.setOpaque(true);

        Tkarena.setColumns(20);
        Tkarena.setRows(5);
        Tkarena.setName("Tkarena"); // NOI18N
        Tkarena.setPreferredSize(new java.awt.Dimension(170, 110));
        Tkarena.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkarenaKeyPressed(evt);
            }
        });
        Scroll43.setViewportView(Tkarena);

        panelGlass14.add(Scroll43);
        Scroll43.setBounds(633, 95, 430, 70);

        internalFrame8.add(panelGlass14, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Keluhan / Permasalahan", internalFrame8);

        internalFrame9.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame9.setBorder(null);
        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll15.setName("Scroll15"); // NOI18N
        Scroll15.setOpaque(true);
        Scroll15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Scroll15KeyPressed(evt);
            }
        });

        tbHarapan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbHarapan.setName("tbHarapan"); // NOI18N
        tbHarapan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHarapanMouseClicked(evt);
            }
        });
        tbHarapan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbHarapanKeyPressed(evt);
            }
        });
        Scroll15.setViewportView(tbHarapan);

        internalFrame9.add(Scroll15, java.awt.BorderLayout.CENTER);

        panelGlass15.setName("panelGlass15"); // NOI18N
        panelGlass15.setPreferredSize(new java.awt.Dimension(44, 321));
        panelGlass15.setLayout(null);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Perubahan Yang Diinginkan  ");
        jLabel25.setName("jLabel25"); // NOI18N
        panelGlass15.add(jLabel25);
        jLabel25.setBounds(0, 86, 160, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Harapan Terhadap  ");
        jLabel29.setName("jLabel29"); // NOI18N
        panelGlass15.add(jLabel29);
        jLabel29.setBounds(0, 10, 160, 23);

        Scroll12.setName("Scroll12"); // NOI18N
        Scroll12.setOpaque(true);

        Tharapan.setColumns(20);
        Tharapan.setRows(5);
        Tharapan.setName("Tharapan"); // NOI18N
        Tharapan.setPreferredSize(new java.awt.Dimension(170, 230));
        Tharapan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TharapanKeyPressed(evt);
            }
        });
        Scroll12.setViewportView(Tharapan);

        panelGlass15.add(Scroll12);
        Scroll12.setBounds(162, 10, 830, 70);

        Scroll13.setName("Scroll13"); // NOI18N
        Scroll13.setOpaque(true);

        TperubahanSendiri.setColumns(20);
        TperubahanSendiri.setRows(5);
        TperubahanSendiri.setName("TperubahanSendiri"); // NOI18N
        TperubahanSendiri.setPreferredSize(new java.awt.Dimension(170, 230));
        TperubahanSendiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TperubahanSendiriKeyPressed(evt);
            }
        });
        Scroll13.setViewportView(TperubahanSendiri);

        panelGlass15.add(Scroll13);
        Scroll13.setBounds(162, 86, 830, 70);

        Scroll14.setName("Scroll14"); // NOI18N
        Scroll14.setOpaque(true);

        TperubahanPasangan.setColumns(20);
        TperubahanPasangan.setRows(5);
        TperubahanPasangan.setName("TperubahanPasangan"); // NOI18N
        TperubahanPasangan.setPreferredSize(new java.awt.Dimension(170, 230));
        TperubahanPasangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TperubahanPasanganKeyPressed(evt);
            }
        });
        Scroll14.setViewportView(TperubahanPasangan);

        panelGlass15.add(Scroll14);
        Scroll14.setBounds(162, 162, 830, 70);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Perubahan Yang Diinginkan  ");
        jLabel26.setName("jLabel26"); // NOI18N
        panelGlass15.add(jLabel26);
        jLabel26.setBounds(0, 162, 160, 23);

        Scroll19.setName("Scroll19"); // NOI18N
        Scroll19.setOpaque(true);

        Tkemungkinan.setColumns(20);
        Tkemungkinan.setRows(5);
        Tkemungkinan.setName("Tkemungkinan"); // NOI18N
        Tkemungkinan.setPreferredSize(new java.awt.Dimension(170, 230));
        Tkemungkinan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkemungkinanKeyPressed(evt);
            }
        });
        Scroll19.setViewportView(Tkemungkinan);

        panelGlass15.add(Scroll19);
        Scroll19.setBounds(162, 239, 830, 70);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Kemungkinan Perbaikan  ");
        jLabel31.setName("jLabel31"); // NOI18N
        panelGlass15.add(jLabel31);
        jLabel31.setBounds(0, 239, 160, 23);

        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setText("Dari Diri Sendiri : ");
        jLabel60.setName("jLabel60"); // NOI18N
        panelGlass15.add(jLabel60);
        jLabel60.setBounds(0, 101, 160, 23);

        jLabel115.setForeground(new java.awt.Color(0, 0, 0));
        jLabel115.setText("Pernikahan : ");
        jLabel115.setName("jLabel115"); // NOI18N
        panelGlass15.add(jLabel115);
        jLabel115.setBounds(0, 25, 160, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Dari Pasangan : ");
        jLabel27.setName("jLabel27"); // NOI18N
        panelGlass15.add(jLabel27);
        jLabel27.setBounds(0, 177, 160, 23);

        jLabel116.setForeground(new java.awt.Color(0, 0, 0));
        jLabel116.setText("Pernikahan : ");
        jLabel116.setName("jLabel116"); // NOI18N
        panelGlass15.add(jLabel116);
        jLabel116.setBounds(0, 254, 160, 23);

        internalFrame9.add(panelGlass15, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Harapan", internalFrame9);

        internalFrame11.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame11.setBorder(null);
        internalFrame11.setName("internalFrame11"); // NOI18N
        internalFrame11.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(44, 350));
        panelGlass13.setLayout(new java.awt.BorderLayout());

        Scroll16.setBackground(new java.awt.Color(238, 238, 238));
        Scroll16.setName("Scroll16"); // NOI18N
        Scroll16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Scroll16KeyPressed(evt);
            }
        });

        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(816, 330));
        jPanel4.setLayout(new java.awt.GridLayout(1, 2));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: Suami ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(250, 102));
        jPanel5.setLayout(null);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Kelebihan : ");
        jLabel32.setName("jLabel32"); // NOI18N
        jPanel5.add(jLabel32);
        jLabel32.setBounds(0, 18, 90, 23);

        Scroll5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Data kelebihan suami ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        tblebihSuami.setToolTipText("Silahkan pilih salah satu data yang mau dihapus/diperbaiki");
        tblebihSuami.setComponentPopupMenu(jPopupMenu1);
        tblebihSuami.setName("tblebihSuami"); // NOI18N
        tblebihSuami.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblebihSuamiMouseClicked(evt);
            }
        });
        tblebihSuami.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblebihSuamiKeyPressed(evt);
            }
        });
        Scroll5.setViewportView(tblebihSuami);

        jPanel5.add(Scroll5);
        Scroll5.setBounds(95, 18, 430, 150);

        Scroll8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Data kekurangan suami ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        Scroll8.setName("Scroll8"); // NOI18N
        Scroll8.setOpaque(true);

        tbkurangSuami.setToolTipText("Silahkan pilih salah satu data yang mau dihapus/diperbaiki");
        tbkurangSuami.setComponentPopupMenu(jPopupMenu1);
        tbkurangSuami.setName("tbkurangSuami"); // NOI18N
        tbkurangSuami.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbkurangSuamiMouseClicked(evt);
            }
        });
        tbkurangSuami.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbkurangSuamiKeyPressed(evt);
            }
        });
        Scroll8.setViewportView(tbkurangSuami);

        jPanel5.add(Scroll8);
        Scroll8.setBounds(95, 170, 430, 150);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Kekurangan : ");
        jLabel33.setName("jLabel33"); // NOI18N
        jPanel5.add(jLabel33);
        jLabel33.setBounds(0, 170, 90, 23);

        jPanel4.add(jPanel5);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: Istri ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel6.setName("jPanel6"); // NOI18N
        jPanel6.setOpaque(false);
        jPanel6.setPreferredSize(new java.awt.Dimension(350, 102));
        jPanel6.setLayout(null);

        Scroll9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Data kelebihan istri ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        Scroll9.setName("Scroll9"); // NOI18N
        Scroll9.setOpaque(true);

        tblebihIstri.setToolTipText("Silahkan pilih salah satu data yang mau dihapus/diperbaiki");
        tblebihIstri.setComponentPopupMenu(jPopupMenu1);
        tblebihIstri.setName("tblebihIstri"); // NOI18N
        tblebihIstri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblebihIstriMouseClicked(evt);
            }
        });
        tblebihIstri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblebihIstriKeyPressed(evt);
            }
        });
        Scroll9.setViewportView(tblebihIstri);

        jPanel6.add(Scroll9);
        Scroll9.setBounds(95, 18, 430, 150);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Kelebihan : ");
        jLabel34.setName("jLabel34"); // NOI18N
        jPanel6.add(jLabel34);
        jLabel34.setBounds(0, 18, 90, 23);

        Scroll11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Data kekurangan istri ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        Scroll11.setName("Scroll11"); // NOI18N
        Scroll11.setOpaque(true);

        tbkurangIstri.setToolTipText("Silahkan pilih salah satu data yang mau dihapus/diperbaiki");
        tbkurangIstri.setComponentPopupMenu(jPopupMenu1);
        tbkurangIstri.setName("tbkurangIstri"); // NOI18N
        tbkurangIstri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbkurangIstriMouseClicked(evt);
            }
        });
        tbkurangIstri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbkurangIstriKeyPressed(evt);
            }
        });
        Scroll11.setViewportView(tbkurangIstri);

        jPanel6.add(Scroll11);
        Scroll11.setBounds(95, 170, 430, 150);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Kekurangan : ");
        jLabel39.setName("jLabel39"); // NOI18N
        jPanel6.add(jLabel39);
        jLabel39.setBounds(0, 170, 90, 23);

        jPanel4.add(jPanel6);

        Scroll16.setViewportView(jPanel4);

        panelGlass13.add(Scroll16, java.awt.BorderLayout.CENTER);

        internalFrame11.add(panelGlass13, java.awt.BorderLayout.CENTER);

        panelGlass19.setName("panelGlass19"); // NOI18N
        panelGlass19.setPreferredSize(new java.awt.Dimension(44, 76));
        panelGlass19.setLayout(null);

        jLabel117.setForeground(new java.awt.Color(0, 0, 0));
        jLabel117.setText("Jenis Penilaian : ");
        jLabel117.setName("jLabel117"); // NOI18N
        panelGlass19.add(jLabel117);
        jLabel117.setBounds(213, 10, 100, 23);

        TdeskripsiNilai.setForeground(new java.awt.Color(0, 0, 0));
        TdeskripsiNilai.setName("TdeskripsiNilai"); // NOI18N
        panelGlass19.add(TdeskripsiNilai);
        TdeskripsiNilai.setBounds(133, 39, 454, 23);

        jLabel118.setForeground(new java.awt.Color(0, 0, 0));
        jLabel118.setText("Jenis Pasangan : ");
        jLabel118.setName("jLabel118"); // NOI18N
        panelGlass19.add(jLabel118);
        jLabel118.setBounds(0, 10, 130, 23);

        cmbPasanganKL.setForeground(new java.awt.Color(0, 0, 0));
        cmbPasanganKL.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SUAMI", "ISTRI" }));
        cmbPasanganKL.setName("cmbPasanganKL"); // NOI18N
        cmbPasanganKL.setOpaque(false);
        cmbPasanganKL.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass19.add(cmbPasanganKL);
        cmbPasanganKL.setBounds(133, 10, 70, 23);

        cmbPenilaian.setForeground(new java.awt.Color(0, 0, 0));
        cmbPenilaian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "KELEBIHAN", "KEKURANGAN" }));
        cmbPenilaian.setName("cmbPenilaian"); // NOI18N
        cmbPenilaian.setOpaque(false);
        cmbPenilaian.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass19.add(cmbPenilaian);
        cmbPenilaian.setBounds(316, 10, 100, 23);

        jLabel119.setForeground(new java.awt.Color(0, 0, 0));
        jLabel119.setText("Deskripsi Penilaian : ");
        jLabel119.setName("jLabel119"); // NOI18N
        panelGlass19.add(jLabel119);
        jLabel119.setBounds(0, 39, 130, 23);

        internalFrame11.add(panelGlass19, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Pemahaman Diri & Pasangan", internalFrame11);

        internalFrame12.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame12.setBorder(null);
        internalFrame12.setName("internalFrame12"); // NOI18N
        internalFrame12.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll17.setName("Scroll17"); // NOI18N
        Scroll17.setOpaque(true);
        Scroll17.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Scroll17KeyPressed(evt);
            }
        });

        tbPemeriksaan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPemeriksaan.setName("tbPemeriksaan"); // NOI18N
        tbPemeriksaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPemeriksaanMouseClicked(evt);
            }
        });
        tbPemeriksaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPemeriksaanKeyPressed(evt);
            }
        });
        Scroll17.setViewportView(tbPemeriksaan);

        internalFrame12.add(Scroll17, java.awt.BorderLayout.CENTER);

        panelGlass27.setName("panelGlass27"); // NOI18N
        panelGlass27.setPreferredSize(new java.awt.Dimension(44, 167));
        panelGlass27.setLayout(null);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Wawancara : ");
        jLabel47.setName("jLabel47"); // NOI18N
        panelGlass27.add(jLabel47);
        jLabel47.setBounds(0, 10, 130, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Observasi : ");
        jLabel48.setName("jLabel48"); // NOI18N
        panelGlass27.add(jLabel48);
        jLabel48.setBounds(0, 86, 130, 23);

        Scroll20.setName("Scroll20"); // NOI18N
        Scroll20.setOpaque(true);

        Twawancara.setColumns(20);
        Twawancara.setRows(5);
        Twawancara.setName("Twawancara"); // NOI18N
        Twawancara.setPreferredSize(new java.awt.Dimension(170, 230));
        Twawancara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TwawancaraKeyPressed(evt);
            }
        });
        Scroll20.setViewportView(Twawancara);

        panelGlass27.add(Scroll20);
        Scroll20.setBounds(133, 10, 830, 70);

        Scroll21.setName("Scroll21"); // NOI18N
        Scroll21.setOpaque(true);

        Tobservasi.setColumns(20);
        Tobservasi.setRows(5);
        Tobservasi.setName("Tobservasi"); // NOI18N
        Tobservasi.setPreferredSize(new java.awt.Dimension(170, 230));
        Tobservasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TobservasiKeyPressed(evt);
            }
        });
        Scroll21.setViewportView(Tobservasi);

        panelGlass27.add(Scroll21);
        Scroll21.setBounds(133, 86, 830, 70);

        internalFrame12.add(panelGlass27, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Pemeriksaan Psikologi Klinis", internalFrame12);

        internalFrame15.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame15.setBorder(null);
        internalFrame15.setName("internalFrame15"); // NOI18N
        internalFrame15.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll27.setName("Scroll27"); // NOI18N
        Scroll27.setOpaque(true);

        tbTesPsiko.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbTesPsiko.setName("tbTesPsiko"); // NOI18N
        tbTesPsiko.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTesPsikoMouseClicked(evt);
            }
        });
        tbTesPsiko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTesPsikoKeyPressed(evt);
            }
        });
        Scroll27.setViewportView(tbTesPsiko);

        internalFrame15.add(Scroll27, java.awt.BorderLayout.CENTER);

        panelGlass29.setName("panelGlass29"); // NOI18N
        panelGlass29.setPreferredSize(new java.awt.Dimension(44, 125));
        panelGlass29.setLayout(null);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("Rencana Tes : ");
        jLabel90.setName("jLabel90"); // NOI18N
        panelGlass29.add(jLabel90);
        jLabel90.setBounds(0, 10, 130, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Tes Psikologi : ");
        jLabel91.setName("jLabel91"); // NOI18N
        panelGlass29.add(jLabel91);
        jLabel91.setBounds(0, 38, 130, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("Waktu : ");
        jLabel92.setName("jLabel92"); // NOI18N
        panelGlass29.add(jLabel92);
        jLabel92.setBounds(0, 66, 130, 23);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setText("Tester : ");
        jLabel93.setName("jLabel93"); // NOI18N
        panelGlass29.add(jLabel93);
        jLabel93.setBounds(0, 94, 130, 23);

        TTesPsiko.setForeground(new java.awt.Color(0, 0, 0));
        TTesPsiko.setName("TTesPsiko"); // NOI18N
        TTesPsiko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTesPsikoKeyPressed(evt);
            }
        });
        panelGlass29.add(TTesPsiko);
        TTesPsiko.setBounds(133, 38, 390, 23);

        TWaktu.setForeground(new java.awt.Color(0, 0, 0));
        TWaktu.setName("TWaktu"); // NOI18N
        TWaktu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TWaktuKeyPressed(evt);
            }
        });
        panelGlass29.add(TWaktu);
        TWaktu.setBounds(133, 66, 390, 23);

        TTester.setForeground(new java.awt.Color(0, 0, 0));
        TTester.setName("TTester"); // NOI18N
        TTester.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTesterKeyPressed(evt);
            }
        });
        panelGlass29.add(TTester);
        TTester.setBounds(133, 94, 390, 23);

        tgl_rencana.setEditable(false);
        tgl_rencana.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21-10-2022" }));
        tgl_rencana.setDisplayFormat("dd-MM-yyyy");
        tgl_rencana.setName("tgl_rencana"); // NOI18N
        tgl_rencana.setOpaque(false);
        tgl_rencana.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass29.add(tgl_rencana);
        tgl_rencana.setBounds(133, 10, 90, 23);

        internalFrame15.add(panelGlass29, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Tes Psikologi", internalFrame15);

        internalFrame16.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame16.setBorder(null);
        internalFrame16.setName("internalFrame16"); // NOI18N
        internalFrame16.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll28.setName("Scroll28"); // NOI18N
        Scroll28.setOpaque(true);

        tbManifestasi.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbManifestasi.setName("tbManifestasi"); // NOI18N
        tbManifestasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbManifestasiMouseClicked(evt);
            }
        });
        tbManifestasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbManifestasiKeyPressed(evt);
            }
        });
        Scroll28.setViewportView(tbManifestasi);

        internalFrame16.add(Scroll28, java.awt.BorderLayout.CENTER);

        panelGlass30.setName("panelGlass30"); // NOI18N
        panelGlass30.setPreferredSize(new java.awt.Dimension(44, 125));
        panelGlass30.setLayout(null);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("Manifestasi Fungsi : ");
        jLabel94.setName("jLabel94"); // NOI18N
        panelGlass30.add(jLabel94);
        jLabel94.setBounds(0, 10, 130, 23);

        Scroll29.setName("Scroll29"); // NOI18N
        Scroll29.setOpaque(true);

        TManifes.setColumns(20);
        TManifes.setRows(5);
        TManifes.setName("TManifes"); // NOI18N
        TManifes.setPreferredSize(new java.awt.Dimension(170, 230));
        Scroll29.setViewportView(TManifes);

        panelGlass30.add(Scroll29);
        Scroll29.setBounds(133, 10, 860, 106);

        internalFrame16.add(panelGlass30, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Manifestasi Fungsi Psikologis", internalFrame16);

        internalFrame17.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame17.setBorder(null);
        internalFrame17.setName("internalFrame17"); // NOI18N
        internalFrame17.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll30.setName("Scroll30"); // NOI18N
        Scroll30.setOpaque(true);

        tbDiagnosis.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDiagnosis.setName("tbDiagnosis"); // NOI18N
        tbDiagnosis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDiagnosisMouseClicked(evt);
            }
        });
        tbDiagnosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDiagnosisKeyPressed(evt);
            }
        });
        Scroll30.setViewportView(tbDiagnosis);

        internalFrame17.add(Scroll30, java.awt.BorderLayout.CENTER);

        panelGlass31.setName("panelGlass31"); // NOI18N
        panelGlass31.setPreferredSize(new java.awt.Dimension(44, 145));
        panelGlass31.setLayout(null);

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("Kesan Awal : ");
        jLabel95.setName("jLabel95"); // NOI18N
        panelGlass31.add(jLabel95);
        jLabel95.setBounds(0, 10, 130, 23);

        Scroll31.setName("Scroll31"); // NOI18N
        Scroll31.setOpaque(true);

        TKesan.setColumns(20);
        TKesan.setRows(5);
        TKesan.setName("TKesan"); // NOI18N
        TKesan.setPreferredSize(new java.awt.Dimension(170, 230));
        TKesan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKesanKeyPressed(evt);
            }
        });
        Scroll31.setViewportView(TKesan);

        panelGlass31.add(Scroll31);
        Scroll31.setBounds(133, 10, 410, 70);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("Diagnosis Utama (dx) : ");
        jLabel96.setName("jLabel96"); // NOI18N
        panelGlass31.add(jLabel96);
        jLabel96.setBounds(0, 85, 130, 23);

        TDiagUtama.setForeground(new java.awt.Color(0, 0, 0));
        TDiagUtama.setName("TDiagUtama"); // NOI18N
        TDiagUtama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiagUtamaKeyPressed(evt);
            }
        });
        panelGlass31.add(TDiagUtama);
        TDiagUtama.setBounds(133, 85, 710, 23);

        TDiagBanding.setForeground(new java.awt.Color(0, 0, 0));
        TDiagBanding.setName("TDiagBanding"); // NOI18N
        TDiagBanding.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiagBandingKeyPressed(evt);
            }
        });
        panelGlass31.add(TDiagBanding);
        TDiagBanding.setBounds(133, 113, 710, 23);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("Diagnosis Banding (dd) : ");
        jLabel97.setName("jLabel97"); // NOI18N
        panelGlass31.add(jLabel97);
        jLabel97.setBounds(0, 113, 130, 23);

        btnPenyakit.setForeground(new java.awt.Color(0, 0, 0));
        btnPenyakit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPenyakit.setMnemonic('3');
        btnPenyakit.setToolTipText("Alt+3");
        btnPenyakit.setName("btnPenyakit"); // NOI18N
        btnPenyakit.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPenyakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenyakitActionPerformed(evt);
            }
        });
        panelGlass31.add(btnPenyakit);
        btnPenyakit.setBounds(845, 85, 28, 23);

        btnPenyakit1.setForeground(new java.awt.Color(0, 0, 0));
        btnPenyakit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPenyakit1.setMnemonic('3');
        btnPenyakit1.setToolTipText("Alt+3");
        btnPenyakit1.setName("btnPenyakit1"); // NOI18N
        btnPenyakit1.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPenyakit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenyakit1ActionPerformed(evt);
            }
        });
        panelGlass31.add(btnPenyakit1);
        btnPenyakit1.setBounds(845, 113, 28, 23);

        internalFrame17.add(panelGlass31, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Diagnosis (DSM / ICD / PPDGJ)", internalFrame17);

        internalFrame18.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame18.setBorder(null);
        internalFrame18.setName("internalFrame18"); // NOI18N
        internalFrame18.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll32.setName("Scroll32"); // NOI18N
        Scroll32.setOpaque(true);

        tbPrognosis.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPrognosis.setName("tbPrognosis"); // NOI18N
        tbPrognosis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPrognosisMouseClicked(evt);
            }
        });
        tbPrognosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPrognosisKeyPressed(evt);
            }
        });
        Scroll32.setViewportView(tbPrognosis);

        internalFrame18.add(Scroll32, java.awt.BorderLayout.CENTER);

        panelGlass32.setName("panelGlass32"); // NOI18N
        panelGlass32.setPreferredSize(new java.awt.Dimension(44, 145));
        panelGlass32.setLayout(null);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setText("Prognosis : ");
        jLabel98.setName("jLabel98"); // NOI18N
        panelGlass32.add(jLabel98);
        jLabel98.setBounds(0, 10, 130, 23);

        Scroll33.setName("Scroll33"); // NOI18N
        Scroll33.setOpaque(true);

        Tprognosis.setColumns(20);
        Tprognosis.setRows(5);
        Tprognosis.setName("Tprognosis"); // NOI18N
        Tprognosis.setPreferredSize(new java.awt.Dimension(170, 230));
        Scroll33.setViewportView(Tprognosis);

        panelGlass32.add(Scroll33);
        Scroll33.setBounds(133, 10, 820, 123);

        internalFrame18.add(panelGlass32, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Prognosis", internalFrame18);

        internalFrame19.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame19.setBorder(null);
        internalFrame19.setName("internalFrame19"); // NOI18N
        internalFrame19.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll34.setName("Scroll34"); // NOI18N
        Scroll34.setOpaque(true);

        tbTritmen.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbTritmen.setName("tbTritmen"); // NOI18N
        tbTritmen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTritmenMouseClicked(evt);
            }
        });
        tbTritmen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTritmenKeyPressed(evt);
            }
        });
        Scroll34.setViewportView(tbTritmen);

        internalFrame19.add(Scroll34, java.awt.BorderLayout.CENTER);

        panelGlass33.setName("panelGlass33"); // NOI18N
        panelGlass33.setPreferredSize(new java.awt.Dimension(44, 215));
        panelGlass33.setLayout(null);

        Scroll6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Item Rencana Tritmen ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbItemTritmen.setToolTipText("Silahkan pilih salah satu data yang mau dihapus/diperbaiki");
        tbItemTritmen.setName("tbItemTritmen"); // NOI18N
        tbItemTritmen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbItemTritmenMouseClicked(evt);
            }
        });
        tbItemTritmen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbItemTritmenKeyPressed(evt);
            }
        });
        Scroll6.setViewportView(tbItemTritmen);

        panelGlass33.add(Scroll6);
        Scroll6.setBounds(153, 10, 590, 140);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Rencana Tritmen : ");
        jLabel44.setName("jLabel44"); // NOI18N
        panelGlass33.add(jLabel44);
        jLabel44.setBounds(0, 10, 150, 23);

        BtnPilihTritmen.setForeground(new java.awt.Color(0, 0, 0));
        BtnPilihTritmen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPilihTritmen.setMnemonic('P');
        BtnPilihTritmen.setText("Pilihan Tritmen");
        BtnPilihTritmen.setToolTipText("Alt+P");
        BtnPilihTritmen.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnPilihTritmen.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnPilihTritmen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnPilihTritmen.setName("BtnPilihTritmen"); // NOI18N
        BtnPilihTritmen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPilihTritmenActionPerformed(evt);
            }
        });
        panelGlass33.add(BtnPilihTritmen);
        BtnPilihTritmen.setBounds(750, 17, 115, 27);

        BtnHapusItem1.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapusItem1.setMnemonic('H');
        BtnHapusItem1.setText("Hapus Item");
        BtnHapusItem1.setToolTipText("Alt+H");
        BtnHapusItem1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnHapusItem1.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnHapusItem1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnHapusItem1.setName("BtnHapusItem1"); // NOI18N
        BtnHapusItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusItem1ActionPerformed(evt);
            }
        });
        panelGlass33.add(BtnHapusItem1);
        BtnHapusItem1.setBounds(750, 50, 115, 27);

        BtnPerbaikiItem1.setForeground(new java.awt.Color(0, 0, 0));
        BtnPerbaikiItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnPerbaikiItem1.setMnemonic('G');
        BtnPerbaikiItem1.setText("Perbaiki Item");
        BtnPerbaikiItem1.setToolTipText("Alt+G");
        BtnPerbaikiItem1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnPerbaikiItem1.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnPerbaikiItem1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnPerbaikiItem1.setName("BtnPerbaikiItem1"); // NOI18N
        BtnPerbaikiItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPerbaikiItem1ActionPerformed(evt);
            }
        });
        panelGlass33.add(BtnPerbaikiItem1);
        BtnPerbaikiItem1.setBounds(750, 83, 115, 27);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("Pertemuan Selanjutnya : ");
        jLabel45.setName("jLabel45"); // NOI18N
        panelGlass33.add(jLabel45);
        jLabel45.setBounds(0, 153, 150, 23);

        TPertemuan.setForeground(new java.awt.Color(0, 0, 0));
        TPertemuan.setName("TPertemuan"); // NOI18N
        TPertemuan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPertemuanKeyPressed(evt);
            }
        });
        panelGlass33.add(TPertemuan);
        TPertemuan.setBounds(153, 153, 590, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Catatan Tambahan : ");
        jLabel46.setName("jLabel46"); // NOI18N
        panelGlass33.add(jLabel46);
        jLabel46.setBounds(0, 181, 150, 23);

        TCatatan.setForeground(new java.awt.Color(0, 0, 0));
        TCatatan.setName("TCatatan"); // NOI18N
        TCatatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCatatanKeyPressed(evt);
            }
        });
        panelGlass33.add(TCatatan);
        TCatatan.setBounds(153, 181, 590, 23);

        internalFrame19.add(panelGlass33, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Rencana Tritmen", internalFrame19);

        internalFrame21.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame21.setBorder(null);
        internalFrame21.setName("internalFrame21"); // NOI18N
        internalFrame21.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll35.setName("Scroll35"); // NOI18N
        Scroll35.setOpaque(true);

        LoadHTML.setEditable(false);
        LoadHTML.setBorder(null);
        LoadHTML.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML.setName("LoadHTML"); // NOI18N
        Scroll35.setViewportView(LoadHTML);

        internalFrame21.add(Scroll35, java.awt.BorderLayout.CENTER);

        TabPsikologis.addTab("Preview Rekam Psikologis", internalFrame21);

        internalFrame1.add(TabPsikologis, java.awt.BorderLayout.CENTER);
        TabPsikologis.getAccessibleContext().setAccessibleName("Data Diri Klien");
        TabPsikologis.getAccessibleContext().setAccessibleDescription("");

        panelGlass22.setName("panelGlass22"); // NOI18N
        panelGlass22.setPreferredSize(new java.awt.Dimension(44, 100));
        panelGlass22.setLayout(new java.awt.BorderLayout());

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 50));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 10));

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
        panelGlass9.add(BtnSimpan);

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
        panelGlass9.add(BtnBatal);

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
        panelGlass9.add(BtnHapus);

        BtnEdit.setForeground(new java.awt.Color(0, 0, 0));
        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelGlass9.add(BtnEdit);

        BtnPrint.setForeground(new java.awt.Color(0, 0, 0));
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
        panelGlass9.add(BtnPrint);

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
        panelGlass9.add(BtnKeluar);

        panelGlass22.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        panelGlass23.setName("panelGlass23"); // NOI18N
        panelGlass23.setPreferredSize(new java.awt.Dimension(44, 50));
        panelGlass23.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 10));

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Tanggal : ");
        jLabel41.setName("jLabel41"); // NOI18N
        jLabel41.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass23.add(jLabel41);

        tgl1.setEditable(false);
        tgl1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21-10-2022" }));
        tgl1.setDisplayFormat("dd-MM-yyyy");
        tgl1.setName("tgl1"); // NOI18N
        tgl1.setOpaque(false);
        tgl1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass23.add(tgl1);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("s.d");
        jLabel42.setName("jLabel42"); // NOI18N
        jLabel42.setPreferredSize(new java.awt.Dimension(20, 23));
        panelGlass23.add(jLabel42);

        tgl2.setEditable(false);
        tgl2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21-10-2022" }));
        tgl2.setDisplayFormat("dd-MM-yyyy");
        tgl2.setName("tgl2"); // NOI18N
        tgl2.setOpaque(false);
        tgl2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass23.add(tgl2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass23.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(240, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass23.add(TCari);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Limit Data :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass23.add(jLabel8);

        cmbLimit.setForeground(new java.awt.Color(0, 0, 0));
        cmbLimit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "50", "100", "200", "300", "400", "500", "1000", "Semua" }));
        cmbLimit.setName("cmbLimit"); // NOI18N
        cmbLimit.setOpaque(false);
        cmbLimit.setPreferredSize(new java.awt.Dimension(66, 23));
        panelGlass23.add(cmbLimit);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('6');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+6");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(130, 30));
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
        panelGlass23.add(BtnCari);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(110, 30));
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
        panelGlass23.add(BtnAll);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Record :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(60, 30));
        panelGlass23.add(jLabel19);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(100, 30));
        panelGlass23.add(LCount);

        panelGlass22.add(panelGlass23, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(panelGlass22, java.awt.BorderLayout.PAGE_END);

        panelGlass24.setName("panelGlass24"); // NOI18N
        panelGlass24.setPreferredSize(new java.awt.Dimension(44, 75));
        panelGlass24.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Pasien : ");
        jLabel3.setName("jLabel3"); // NOI18N
        panelGlass24.add(jLabel3);
        jLabel3.setBounds(0, 10, 130, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        panelGlass24.add(TNoRM);
        TNoRM.setBounds(276, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        panelGlass24.add(TPasien);
        TPasien.setBounds(350, 10, 430, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Tgl. Kedatangan : ");
        jLabel11.setName("jLabel11"); // NOI18N
        panelGlass24.add(jLabel11);
        jLabel11.setBounds(0, 38, 130, 23);

        tgl_datang.setEditable(false);
        tgl_datang.setForeground(new java.awt.Color(0, 0, 0));
        tgl_datang.setName("tgl_datang"); // NOI18N
        panelGlass24.add(tgl_datang);
        tgl_datang.setBounds(133, 38, 100, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Jenis Kelamin : ");
        jLabel70.setName("jLabel70"); // NOI18N
        panelGlass24.add(jLabel70);
        jLabel70.setBounds(235, 38, 80, 23);

        Tjk.setEditable(false);
        Tjk.setForeground(new java.awt.Color(0, 0, 0));
        Tjk.setName("Tjk"); // NOI18N
        panelGlass24.add(Tjk);
        Tjk.setBounds(317, 38, 90, 23);

        TNoRW.setEditable(false);
        TNoRW.setForeground(new java.awt.Color(0, 0, 0));
        TNoRW.setName("TNoRW"); // NOI18N
        panelGlass24.add(TNoRW);
        TNoRW.setBounds(133, 10, 140, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("Psikolog Pemeriksa : ");
        jLabel101.setName("jLabel101"); // NOI18N
        panelGlass24.add(jLabel101);
        jLabel101.setBounds(408, 38, 110, 23);

        TnmPsikolog.setEditable(false);
        TnmPsikolog.setForeground(new java.awt.Color(0, 0, 0));
        TnmPsikolog.setName("TnmPsikolog"); // NOI18N
        panelGlass24.add(TnmPsikolog);
        TnmPsikolog.setBounds(520, 38, 260, 23);

        btnPetugas.setForeground(new java.awt.Color(0, 0, 0));
        btnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas.setMnemonic('3');
        btnPetugas.setToolTipText("Alt+3");
        btnPetugas.setName("btnPetugas"); // NOI18N
        btnPetugas.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasActionPerformed(evt);
            }
        });
        panelGlass24.add(btnPetugas);
        btnPetugas.setBounds(783, 38, 28, 23);

        internalFrame1.add(panelGlass24, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        try {
            if (TNoRW.getText().trim().equals("") || TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                Valid.textKosong(TNoRW, "Pasien");
            } else {
                cekRPP = 0;
                cekRPP = Sequel.cariInteger("SELECT COUNT(-1) cek FROM psikolog_data_diri_klien pd "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pd.no_rawat WHERE rp.no_rkm_medis='" + TNoRM.getText() + "' and pd.rekam_psikologis='perkawinan'");
                if (TabPsikologis.getSelectedIndex() == 0) {                    
                    simpanDD();
                } else if (TabPsikologis.getSelectedIndex() == 1) {
                    if (cekRPP == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanIP();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 2) {
                    if (cekRPP == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanIA();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 3) {
                    if (cekRPP == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanKP();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 4) {
                    if (cekRPP == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanH();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 5) {
                    if (cekRPP == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanPDP();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 6) {
                    if (cekRPP == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanPPK();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 7) {
                    if (cekRPP == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanTP();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 8) {
                    if (cekRPP == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanMF();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 9) {
                    if (cekRPP == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanD();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 10) {
                    if (cekRPP == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanP();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 11) {
                    if (cekRPP == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanRT();
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Maaf, gagal proses menyimpan data,..!!");
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            BtnCariActionPerformed(null);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        cmbLimit.setSelectedIndex(0);
        if (TabPsikologis.getSelectedIndex() == 0) {
            emptTextDD();
        } else if (TabPsikologis.getSelectedIndex() == 1) {
            emptTextIP();
        } else if (TabPsikologis.getSelectedIndex() == 2) {
            emptTextIA();
        } else if (TabPsikologis.getSelectedIndex() == 3) {
            emptTextKP();
        } else if (TabPsikologis.getSelectedIndex() == 4) {
            emptTextH();
        } else if (TabPsikologis.getSelectedIndex() == 5) {
            emptTextPDP();
        } else if (TabPsikologis.getSelectedIndex() == 6) {
            emptTextPPK();
        } else if (TabPsikologis.getSelectedIndex() == 7) {
            emptTextTP();
        } else if (TabPsikologis.getSelectedIndex() == 8) {
            emptTextMF();
        } else if (TabPsikologis.getSelectedIndex() == 9) {
            emptTextD();
        } else if (TabPsikologis.getSelectedIndex() == 10) {
            emptTextP();
        } else if (TabPsikologis.getSelectedIndex() == 11) {
            emptTextRT();
        }
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnBatalActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (TNoRW.getText().trim().equals("") || TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (TabPsikologis.getSelectedIndex() == 0) {
                    hapusDD();
                } else if (TabPsikologis.getSelectedIndex() == 1) {
                    hapusIP();
                } else if (TabPsikologis.getSelectedIndex() == 2) {
                    hapusIA();
                } else if (TabPsikologis.getSelectedIndex() == 3) {
                    hapusKP();
                } else if (TabPsikologis.getSelectedIndex() == 4) {
                    hapusH();
                } else if (TabPsikologis.getSelectedIndex() == 5) {
                    hapusPDP();
                } else if (TabPsikologis.getSelectedIndex() == 6) {
                    hapusPPK();
                } else if (TabPsikologis.getSelectedIndex() == 7) {
                    hapusTP();
                } else if (TabPsikologis.getSelectedIndex() == 8) {
                    hapusMF();
                } else if (TabPsikologis.getSelectedIndex() == 9) {
                    hapusD();
                } else if (TabPsikologis.getSelectedIndex() == 10) {
                    hapusP();;
                } else if (TabPsikologis.getSelectedIndex() == 11) {
                    hapusRT();
                }
            }
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void TRespirasiActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        emptText();
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnKeluarActionPerformed(null);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        if (TabPsikologis.getSelectedIndex() == 0) {
            tampilDataDiri();
        } else if (TabPsikologis.getSelectedIndex() == 1) {
            tampilIdentitasPasangan();
        } else if (TabPsikologis.getSelectedIndex() == 2) {
            tampilIdentitasAnak();
        } else if (TabPsikologis.getSelectedIndex() == 3) {
            tampilKeluhanMasalah();
        } else if (TabPsikologis.getSelectedIndex() == 4) {
            tampilHarapan();
        } else if (TabPsikologis.getSelectedIndex() == 5) {
            tampilLebihSuami();
            tampilKurangSuami();
            tampilLebihIstri();
            tampilKurangIstri();
        } else if (TabPsikologis.getSelectedIndex() == 6) {
            tampilPemeriksaanKlinis();
        } else if (TabPsikologis.getSelectedIndex() == 7) {
            tampilTesPsikologi();
        } else if (TabPsikologis.getSelectedIndex() == 8) {
            tampilManifestasiFungsi();
        } else if (TabPsikologis.getSelectedIndex() == 9) {
            tampilDiagnosis();
        } else if (TabPsikologis.getSelectedIndex() == 10) {
            tampilPrognosis();
        } else if (TabPsikologis.getSelectedIndex() == 11) {
            tampilRencanaTritmen();
        } else if (TabPsikologis.getSelectedIndex() == 12) {
            if (TNoRW.getText().trim().equals("") || TNoRM.getText().trim().equals("")) {
                Valid.textKosong(TNoRW, "Pasien");
            } else {
                tampilDataDiri();
                if (tabMode1.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data diri klien/pasien sesuai tgl. kedatangan masih kosong...!!!!");
                } else {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    tampilPreview();
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }
        }
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnCari);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void TabPsikologisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabPsikologisMouseClicked
        if (TabPsikologis.getSelectedIndex() == 0) {            
            tampilDataDiri();
        } else if (TabPsikologis.getSelectedIndex() == 1) {
            tampilIdentitasPasangan();
        } else if (TabPsikologis.getSelectedIndex() == 2) {
            tampilIdentitasAnak();
        } else if (TabPsikologis.getSelectedIndex() == 3) {  
            tampilKeluhanMasalah();
        } else if (TabPsikologis.getSelectedIndex() == 4) {
            tampilHarapan();
        } else if (TabPsikologis.getSelectedIndex() == 5) {
            tampilLebihSuami();
            tampilKurangSuami();
            tampilLebihIstri();
            tampilKurangIstri();
        } else if (TabPsikologis.getSelectedIndex() == 6) {
            tampilPemeriksaanKlinis();
        } else if (TabPsikologis.getSelectedIndex() == 7) {
            tampilTesPsikologi();
        } else if (TabPsikologis.getSelectedIndex() == 8) {
            tampilManifestasiFungsi();
        } else if (TabPsikologis.getSelectedIndex() == 9) {
            tampilDiagnosis();
        } else if (TabPsikologis.getSelectedIndex() == 10) {
            tampilPrognosis();
        } else if (TabPsikologis.getSelectedIndex() == 11) {
            tampilRencanaTritmen();
        } else if (TabPsikologis.getSelectedIndex() == 12) {
            if (TNoRW.getText().trim().equals("") || TNoRM.getText().trim().equals("")) {
                Valid.textKosong(TNoRW, "Pasien");
            } else {
                tampilDataDiri();
                if (tabMode1.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data diri klien/pasien sesuai tgl. kedatangan masih kosong...!!!!");
                } else {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    tampilPreview();
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }
        }
    }//GEN-LAST:event_TabPsikologisMouseClicked

private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
    try {
        if (TNoRW.getText().trim().equals("") || TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else {
            if (TabPsikologis.getSelectedIndex() == 0) {
                gantiDD();
            } else if (TabPsikologis.getSelectedIndex() == 1) {
                gantiIP();
            } else if (TabPsikologis.getSelectedIndex() == 2) {
                gantiIA();
            } else if (TabPsikologis.getSelectedIndex() == 3) {
                gantiKP();
            } else if (TabPsikologis.getSelectedIndex() == 4) {
                gantiH();                               
            } else if (TabPsikologis.getSelectedIndex() == 5) {
                gantiPDP();
            } else if (TabPsikologis.getSelectedIndex() == 6) {
                gantiPPK();
            } else if (TabPsikologis.getSelectedIndex() == 7) {
                gantiTP();
            } else if (TabPsikologis.getSelectedIndex() == 8) {
                gantiMF();
            } else if (TabPsikologis.getSelectedIndex() == 9) {
                gantiD();
            } else if (TabPsikologis.getSelectedIndex() == 10) {
                gantiP();
            } else if (TabPsikologis.getSelectedIndex() == 11) {
                gantiRT();
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal Tersimpan, hubungi Admin");
    }
}//GEN-LAST:event_BtnEditActionPerformed

private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        BtnEditActionPerformed(null);
    }
}//GEN-LAST:event_BtnEditKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void TpekerPasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpekerPasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            cmbagama.requestFocus();
        }
    }//GEN-LAST:event_TpekerPasKeyPressed

    private void TumurPasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumurPasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPendidikan.requestFocus();
        }
    }//GEN-LAST:event_TumurPasKeyPressed

    private void TnmAnakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmAnakKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TumurAnak.requestFocus();
        }
    }//GEN-LAST:event_TnmAnakKeyPressed

    private void TumurAnakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumurAnakKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbidumur.requestFocus();
        }
    }//GEN-LAST:event_TumurAnakKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        TabPsikologis.setSelectedIndex(0);
        BtnCariActionPerformed(null);
    }//GEN-LAST:event_formWindowOpened

    private void tbdatadiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbdatadiriKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataDD();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbdatadiriKeyPressed

    private void tbdatadiriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdatadiriMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getDataDD();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbdatadiriMouseClicked

    private void tbIdentitasPasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbIdentitasPasKeyPressed
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataIP();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbIdentitasPasKeyPressed

    private void tbIdentitasPasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbIdentitasPasMouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getDataIP();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbIdentitasPasMouseClicked

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        if (TabPsikologis.getSelectedIndex() == 0) {
            tampilDataDiri();
            emptTextDD();
        } else if (TabPsikologis.getSelectedIndex() == 1) {
            tampilIdentitasPasangan();
            emptTextIP();
        } else if (TabPsikologis.getSelectedIndex() == 2) {
            tampilIdentitasAnak();
            emptTextIA();
        } else if (TabPsikologis.getSelectedIndex() == 3) {
            tampilKeluhanMasalah();
            emptTextKP();
        } else if (TabPsikologis.getSelectedIndex() == 4) {
            tampilHarapan();
            emptTextH();
        } else if (TabPsikologis.getSelectedIndex() == 5) {
            tampilLebihSuami();
            tampilKurangSuami();
            tampilLebihIstri();
            tampilKurangIstri();
            emptTextPDP();
        } else if (TabPsikologis.getSelectedIndex() == 6) {
            tampilPemeriksaanKlinis();
            emptTextPPK();
        } else if (TabPsikologis.getSelectedIndex() == 7) {
            tampilTesPsikologi();
            emptTextTP();
        } else if (TabPsikologis.getSelectedIndex() == 8) {
            tampilManifestasiFungsi();
            emptTextMF();
        } else if (TabPsikologis.getSelectedIndex() == 9) {
            tampilDiagnosis();
            emptTextD();
        } else if (TabPsikologis.getSelectedIndex() == 10) {
            tampilPrognosis();
            emptTextP();
        } else if (TabPsikologis.getSelectedIndex() == 11) {
            tampilRencanaTritmen();
            emptTextRT();
        } else if (TabPsikologis.getSelectedIndex() == 12) {
            if (TNoRW.getText().trim().equals("") || TNoRM.getText().trim().equals("")) {
                Valid.textKosong(TNoRW, "Pasien");
            } else {
                tampilDataDiri();
                if (tabMode1.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data diri klien/pasien sesuai tgl. kedatangan masih kosong...!!!!");
                } else {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    tampilPreview();
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }
        }
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
//            tampil12();
            TCari.setText("");
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void tbIdentitasAnakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbIdentitasAnakKeyPressed
        if (tabMode3.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataIA();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbIdentitasAnakKeyPressed

    private void tbIdentitasAnakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbIdentitasAnakMouseClicked
        if (tabMode3.getRowCount() != 0) {
            try {
                getDataIA();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbIdentitasAnakMouseClicked

    private void tbKeluhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbKeluhanKeyPressed
        if (tabMode4.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataKeluhan();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbKeluhanKeyPressed

    private void tbKeluhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKeluhanMouseClicked
        if (tabMode4.getRowCount() != 0) {
            try {
                getDataKeluhan();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbKeluhanMouseClicked

    private void Scroll2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Scroll2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Scroll2KeyPressed

    private void tbHarapanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbHarapanKeyPressed
        if (tabMode5.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataHarapan();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbHarapanKeyPressed

    private void tbHarapanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHarapanMouseClicked
        if (tabMode5.getRowCount() != 0) {
            try {
                getDataHarapan();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbHarapanMouseClicked

    private void tbPemeriksaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemeriksaanMouseClicked
        if (tabMode6.getRowCount() != 0) {
            try {
                getDataPPK();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPemeriksaanMouseClicked

    private void tbPemeriksaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPemeriksaanKeyPressed
        if (tabMode6.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataPPK();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPemeriksaanKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (TNoRW.getText().trim().equals("") || TNoRM.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else {
            tampilDataDiri();
            if (tabMode1.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data diri klien/pasien sesuai tgl. kedatangan masih kosong...!!!!");
            } else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                tampilPreview();
                cetakRPP();
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnAll);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void TnamaPasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnamaPasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TumurPas.requestFocus();
        }
    }//GEN-LAST:event_TnamaPasKeyPressed

    private void TpekerjaanSekKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpekerjaanSekKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TanakKe.requestFocus();
        }
    }//GEN-LAST:event_TpekerjaanSekKeyPressed

    private void Scroll15KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Scroll15KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Scroll15KeyPressed

    private void Scroll17KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Scroll17KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Scroll17KeyPressed

    private void tbTesPsikoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTesPsikoMouseClicked
        if (tabMode7.getRowCount() != 0) {
            try {
                getDataTesPsiko();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTesPsikoMouseClicked

    private void tbTesPsikoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTesPsikoKeyPressed
        if (tabMode7.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataTesPsiko();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbTesPsikoKeyPressed

    private void TTesPsikoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTesPsikoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, tgl_rencana, TWaktu);
        }
    }//GEN-LAST:event_TTesPsikoKeyPressed

    private void TWaktuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TWaktuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TTesPsiko, TTester);
        }
    }//GEN-LAST:event_TWaktuKeyPressed

    private void TTesterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTesterKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TWaktu, tgl_rencana);
        }
    }//GEN-LAST:event_TTesterKeyPressed

    private void tbManifestasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbManifestasiMouseClicked
        if (tabMode8.getRowCount() != 0) {
            try {
                getDataManifes();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbManifestasiMouseClicked

    private void tbManifestasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbManifestasiKeyPressed
        if (tabMode8.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataManifes();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbManifestasiKeyPressed

    private void tbDiagnosisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDiagnosisMouseClicked
        if (tabMode9.getRowCount() != 0) {
            try {
                getDataDiagnosis();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbDiagnosisMouseClicked

    private void tbDiagnosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagnosisKeyPressed
        if (tabMode9.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataDiagnosis();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbDiagnosisKeyPressed

    private void TDiagUtamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDiagUtamaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TKesan, TDiagBanding);
        }
    }//GEN-LAST:event_TDiagUtamaKeyPressed

    private void TDiagBandingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDiagBandingKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TDiagBanding, TKesan);
        }
    }//GEN-LAST:event_TDiagBandingKeyPressed

    private void btnPenyakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenyakitActionPerformed
        pilihan = 1;
        akses.setform("DlgRekamPsikologisPerkawinan");
        penyakit.isCek();
        penyakit.TCari.requestFocus();
        penyakit.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
    }//GEN-LAST:event_btnPenyakitActionPerformed

    private void btnPenyakit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenyakit1ActionPerformed
        pilihan = 2;
        akses.setform("DlgRekamPsikologisPerkawinan");
        penyakit.isCek();
        penyakit.TCari.requestFocus();
        penyakit.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
    }//GEN-LAST:event_btnPenyakit1ActionPerformed

    private void tbPrognosisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPrognosisMouseClicked
        if (tabMode10.getRowCount() != 0) {
            try {
                getDataPrognosis();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPrognosisMouseClicked

    private void tbPrognosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPrognosisKeyPressed
        if (tabMode10.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataPrognosis();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPrognosisKeyPressed

    private void tbTritmenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTritmenMouseClicked
        if (tabMode12.getRowCount() != 0) {
            try {
                getDataTritmen();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTritmenMouseClicked

    private void tbTritmenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTritmenKeyPressed
        if (tabMode12.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataTritmen();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbTritmenKeyPressed

    private void tbItemTritmenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbItemTritmenMouseClicked
        if (tabMode11.getRowCount() != 0) {
            try {
                getItemTritmen();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbItemTritmenMouseClicked

    private void tbItemTritmenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbItemTritmenKeyPressed
        if (tabMode11.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getItemTritmen();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbItemTritmenKeyPressed

    private void BtnPilihTritmenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPilihTritmenActionPerformed
        kdtritmen.setText("");
        nmtritmen.setText("");
        tritmenLain.setText("");
        BtnSimpan3.setEnabled(true);
        BtnEdit2.setEnabled(false);

        WindowDataTritmen.setSize(660, 137);
        WindowDataTritmen.setLocationRelativeTo(internalFrame1);
        WindowDataTritmen.setVisible(true);
        WindowDataTritmen.requestFocus();
    }//GEN-LAST:event_BtnPilihTritmenActionPerformed

    private void BtnHapusItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusItem1ActionPerformed
        if (tabMode11.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Jenis rencana tritmen yang dipilih/ditentukan belum ada...!!");
            BtnPilihTritmen.requestFocus();
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Belum ada data rencana tritmen yang dipilih pada tabel...!!!!");
            tbItemTritmen.requestFocus();
        } else if (tbItemTritmen.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data rencana tritmennya pada tabel...!!!!");
            tbItemTritmen.requestFocus();
        } else {
            tabMode11.removeRow(tbItemTritmen.getSelectedRow());
            kdtritmen.setText("");
            nmtritmen.setText("");
            tritmenLain.setText("");
            BtnHapusItem1.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusItem1ActionPerformed

    private void BtnPerbaikiItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerbaikiItem1ActionPerformed
        if (tabMode11.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Rencana tritmen yang dipilih/ditentukan belum ada...!!");
            BtnPilihTritmen.requestFocus();
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Belum ada data rencana tritmen yang dipilih pada tabel...!!!!");
            tbItemTritmen.requestFocus();
        } else if (tbItemTritmen.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data rencana tritmennya pada tabel...!!!!");
            tbItemTritmen.requestFocus();
        } else {
            BtnSimpan3.setEnabled(false);
            BtnEdit2.setEnabled(true);

            WindowDataTritmen.setSize(660, 137);
            WindowDataTritmen.setLocationRelativeTo(internalFrame1);
            WindowDataTritmen.setVisible(true);
            WindowDataTritmen.requestFocus();
        }
    }//GEN-LAST:event_BtnPerbaikiItem1ActionPerformed

    private void btnTritmenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTritmenActionPerformed
        akses.setform("DlgRekamPsikologisPerkawinan");
        tritmen.setSize(622, internalFrame1.getHeight() - 40);
        tritmen.setLocationRelativeTo(internalFrame1);
        tritmen.isCek();
        tritmen.emptTeks();
        tritmen.setVisible(true);
        tritmen.TCari.requestFocus();
    }//GEN-LAST:event_btnTritmenActionPerformed

    private void BtnSimpan3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan3ActionPerformed
        if (kdtritmen.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu jenis rencana tritmennya...!!!!");
        } else {
            if (kdtritmen.getText().equals("RT0025")) {
                if (tritmenLain.getText().length() < 2) {
                    JOptionPane.showMessageDialog(null, "Nama rencana tritmen lainnya harus diisi dulu...!!!!");
                    tritmenLain.requestFocus();
                } else {
                    tabMode11.addRow(new Object[]{kdtritmen.getText(), nmtritmen.getText(), tritmenLain.getText(), Sequel.cariIsi("select now()")});
                    kdtritmen.setText("");
                    nmtritmen.setText("");
                    tritmenLain.setText("");
                    btnTritmen.requestFocus();
                }

            } else {
                tabMode11.addRow(new Object[]{kdtritmen.getText(), nmtritmen.getText(), tritmenLain.getText(), Sequel.cariIsi("select now()")});
                kdtritmen.setText("");
                nmtritmen.setText("");
                tritmenLain.setText("");
                btnTritmen.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnSimpan3ActionPerformed

    private void BtnEdit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEdit2ActionPerformed
        if (kdtritmen.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu jenis rencana tritmennya...!!!!");
        } else {
            if (kdtritmen.getText().equals("RT0025")) {
                if (tritmenLain.getText().length() < 2) {
                    JOptionPane.showMessageDialog(null, "Nama rencana tritmen lainnya harus diisi dulu...!!!!");
                    tritmenLain.requestFocus();
                } else {
                    tabMode11.removeRow(tbItemTritmen.getSelectedRow());
                    tabMode11.addRow(new Object[]{kdtritmen.getText(), nmtritmen.getText(), tritmenLain.getText(), Sequel.cariIsi("select now()")});
                    BtnCloseIn4ActionPerformed(null);
                }
            } else {
                tabMode11.removeRow(tbItemTritmen.getSelectedRow());
                tabMode11.addRow(new Object[]{kdtritmen.getText(), nmtritmen.getText(), tritmenLain.getText(), Sequel.cariIsi("select now()")});
                BtnCloseIn4ActionPerformed(null);
            }
        }
    }//GEN-LAST:event_BtnEdit2ActionPerformed

    private void BtnCloseIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn4ActionPerformed
        kdtritmen.setText("");
        nmtritmen.setText("");
        tritmenLain.setText("");
        WindowDataTritmen.dispose();
    }//GEN-LAST:event_BtnCloseIn4ActionPerformed

    private void TPertemuanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPertemuanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, BtnPilihTritmen, TCatatan);
        }
    }//GEN-LAST:event_TPertemuanKeyPressed

    private void TCatatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCatatanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TPertemuan, BtnPilihTritmen);
        }
    }//GEN-LAST:event_TCatatanKeyPressed

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        akses.setform("DlgRekamPsikologisPerkawinan");
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void TharapanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TharapanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TperubahanSendiri.requestFocus();
        }
    }//GEN-LAST:event_TharapanKeyPressed

    private void TperubahanSendiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TperubahanSendiriKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TperubahanPasangan.requestFocus();
        }
    }//GEN-LAST:event_TperubahanSendiriKeyPressed

    private void TperubahanPasanganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TperubahanPasanganKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tkemungkinan.requestFocus();
        }
    }//GEN-LAST:event_TperubahanPasanganKeyPressed

    private void TkemungkinanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkemungkinanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tharapan.requestFocus();
        }
    }//GEN-LAST:event_TkemungkinanKeyPressed

    private void TKesanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKesanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TDiagUtama.requestFocus();
        }
    }//GEN-LAST:event_TKesanKeyPressed

    private void THobiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_THobiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            TnoTelp.requestFocus();
        }
    }//GEN-LAST:event_THobiKeyPressed

    private void TnoTelpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnoTelpKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            THobi.requestFocus();
        }
    }//GEN-LAST:event_TnoTelpKeyPressed

    private void TanakkeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanakkeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tdarike.requestFocus();
        }
    }//GEN-LAST:event_TanakkeKeyPressed

    private void TdarikeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdarikeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Talamatpasangan.requestFocus();
        }
    }//GEN-LAST:event_TdarikeKeyPressed

    private void TalamatpasanganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalamatpasanganKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TnohpPas.requestFocus();
        }
    }//GEN-LAST:event_TalamatpasanganKeyPressed

    private void TnohpPasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnohpPasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TPernikahan.requestFocus();
        }
    }//GEN-LAST:event_TnohpPasKeyPressed

    private void TPernikahanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPernikahanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtmpMenikah.requestFocus();
        }
    }//GEN-LAST:event_TPernikahanKeyPressed

    private void TtmpMenikahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtmpMenikahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tgl_nikah.requestFocus();
        }
    }//GEN-LAST:event_TtmpMenikahKeyPressed

    private void TlmNikahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlmNikahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TlmKenal.requestFocus();
        }
    }//GEN-LAST:event_TlmNikahKeyPressed

    private void TlmKenalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlmKenalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TlmPacaran.requestFocus();
        }
    }//GEN-LAST:event_TlmKenalKeyPressed

    private void TlmPacaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlmPacaranKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPasangan.requestFocus();
        }
    }//GEN-LAST:event_TlmPacaranKeyPressed

    private void TanakKeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanakKeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbJK.requestFocus();
        }
    }//GEN-LAST:event_TanakKeKeyPressed

    private void TpermasalahanNikahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpermasalahanNikahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TsudahBerapa.requestFocus();
        }
    }//GEN-LAST:event_TpermasalahanNikahKeyPressed

    private void TsudahBerapaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsudahBerapaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TalasanBantuan.requestFocus();
        }
    }//GEN-LAST:event_TsudahBerapaKeyPressed

    private void TalasanBantuanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalasanBantuanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            RSangatSeriusKP.requestFocus();
        }
    }//GEN-LAST:event_TalasanBantuanKeyPressed

    private void TkarenaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkarenaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TpermasalahanNikah.requestFocus();
        }
    }//GEN-LAST:event_TkarenaKeyPressed

    private void tblebihSuamiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblebihSuamiMouseClicked
        if (tabModeSL.getRowCount() != 0) {
            try {
                getDataSL();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tblebihSuamiMouseClicked

    private void tblebihSuamiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblebihSuamiKeyPressed
        if (tabModeSL.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataSL();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tblebihSuamiKeyPressed

    private void tbkurangSuamiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbkurangSuamiMouseClicked
        if (tabModeSK.getRowCount() != 0) {
            try {
                getDataSK();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbkurangSuamiMouseClicked

    private void tbkurangSuamiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbkurangSuamiKeyPressed
        if (tabModeSK.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataSK();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbkurangSuamiKeyPressed

    private void tblebihIstriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblebihIstriMouseClicked
        if (tabModeIL.getRowCount() != 0) {
            try {
                getDataIL();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tblebihIstriMouseClicked

    private void tblebihIstriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblebihIstriKeyPressed
        if (tabModeIL.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataIL();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tblebihIstriKeyPressed

    private void tbkurangIstriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbkurangIstriMouseClicked
        if (tabModeIK.getRowCount() != 0) {
            try {
                getDataIK();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbkurangIstriMouseClicked

    private void tbkurangIstriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbkurangIstriKeyPressed
        if (tabModeIK.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataIK();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbkurangIstriKeyPressed

    private void MnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusActionPerformed
        if (tabModeSL.getRowCount() == 0 && tabModeSK.getRowCount() == 0 && tabModeIL.getRowCount() == 0 && tabModeIK.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data pemahaman diri & pasangan pada tabel...!!!!");
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin akan menghapus data yang anda pilih..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                Sequel.queryu("delete from psikolog_perkawinan_pemahaman_pas where waktu_simpan='" + wktSimpan + "'");

                tampilLebihSuami();
                tampilKurangSuami();
                tampilLebihIstri();
                tampilKurangIstri();
                wktSimpan = "";
                nomorR = "";
            } else {
                tampilLebihSuami();
                tampilKurangSuami();
                tampilLebihIstri();
                tampilKurangIstri();
                wktSimpan = "";
                nomorR = "";
            }
        }
    }//GEN-LAST:event_MnHapusActionPerformed

    private void Scroll16KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Scroll16KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Scroll16KeyPressed

    private void TwawancaraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TwawancaraKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tobservasi.requestFocus();
        }
    }//GEN-LAST:event_TwawancaraKeyPressed

    private void TobservasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TobservasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Twawancara.requestFocus();
        }
    }//GEN-LAST:event_TobservasiKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgRekamPsikologisPerkawinan dialog = new DlgRekamPsikologisPerkawinan(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCloseIn4;
    private widget.Button BtnEdit;
    private widget.Button BtnEdit2;
    private widget.Button BtnHapus;
    private widget.Button BtnHapusItem1;
    private widget.Button BtnKeluar;
    private widget.Button BtnPerbaikiItem1;
    private widget.Button BtnPilihTritmen;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan3;
    private widget.Label LCount;
    private widget.editorpane LoadHTML;
    private javax.swing.JMenuItem MnHapus;
    private widget.RadioButton RKurangSeriusKP;
    private widget.RadioButton RSangatSeriusKP;
    private widget.RadioButton RSeriusKP;
    private widget.ScrollPane Scroll10;
    private widget.ScrollPane Scroll11;
    private widget.ScrollPane Scroll12;
    private widget.ScrollPane Scroll13;
    private widget.ScrollPane Scroll14;
    private widget.ScrollPane Scroll15;
    private widget.ScrollPane Scroll16;
    private widget.ScrollPane Scroll17;
    private widget.ScrollPane Scroll19;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll20;
    private widget.ScrollPane Scroll21;
    private widget.ScrollPane Scroll27;
    private widget.ScrollPane Scroll28;
    private widget.ScrollPane Scroll29;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll30;
    private widget.ScrollPane Scroll31;
    private widget.ScrollPane Scroll32;
    private widget.ScrollPane Scroll33;
    private widget.ScrollPane Scroll34;
    private widget.ScrollPane Scroll35;
    private widget.ScrollPane Scroll36;
    private widget.ScrollPane Scroll38;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll41;
    private widget.ScrollPane Scroll42;
    private widget.ScrollPane Scroll43;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane Scroll9;
    private widget.TextBox TAgama;
    private widget.TextArea TAlamat;
    private widget.TextBox TCari;
    private widget.TextBox TCatatan;
    private widget.TextBox TDiagBanding;
    private widget.TextBox TDiagUtama;
    private widget.TextBox THobi;
    private widget.TextArea TKesan;
    private widget.TextArea TManifes;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRW;
    private widget.TextBox TPasien;
    private widget.TextBox TPekerjaan;
    private widget.TextBox TPendidikan;
    private widget.TextBox TPernikahan;
    private widget.TextBox TPertemuan;
    private widget.TextBox TSttsKawin;
    private widget.TextBox TSuku;
    private widget.TextBox TTesPsiko;
    private widget.TextBox TTester;
    private widget.TextBox TWaktu;
    private javax.swing.JTabbedPane TabPsikologis;
    private widget.TextArea Talamatpasangan;
    private widget.TextArea TalasanBantuan;
    private widget.TextBox TanakKe;
    private widget.TextBox Tanakke;
    private widget.TextBox Tdarike;
    private widget.TextBox TdeskripsiNilai;
    private widget.TextArea Tharapan;
    private widget.TextBox Tjk;
    private widget.TextArea Tkarena;
    private widget.TextArea Tkemungkinan;
    private widget.TextBox TlmKenal;
    private widget.TextBox TlmNikah;
    private widget.TextBox TlmPacaran;
    private widget.TextBox TnamaPas;
    private widget.TextBox TnmAnak;
    private widget.TextBox TnmPsikolog;
    private widget.TextBox TnoTelp;
    private widget.TextBox TnohpPas;
    private widget.TextArea Tobservasi;
    private widget.TextBox TpekerPas;
    private widget.TextBox TpekerjaanSek;
    private widget.TextArea TpermasalahanNikah;
    private widget.TextArea TperubahanPasangan;
    private widget.TextArea TperubahanSendiri;
    private widget.TextArea Tprognosis;
    private widget.TextArea TsudahBerapa;
    private widget.TextBox Ttl;
    private widget.TextBox TtmpMenikah;
    private widget.TextBox TumurAnak;
    private widget.TextBox TumurPas;
    private widget.TextArea Twawancara;
    private javax.swing.JDialog WindowDataTritmen;
    private widget.Button btnPenyakit;
    private widget.Button btnPenyakit1;
    private widget.Button btnPetugas;
    private widget.Button btnTritmen;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.ComboBox cmbJK;
    private widget.ComboBox cmbKetAnak;
    private widget.ComboBox cmbLimit;
    private widget.ComboBox cmbPasangan;
    private widget.ComboBox cmbPasanganKL;
    private widget.ComboBox cmbPendidikan;
    private widget.ComboBox cmbPenilaian;
    private widget.ComboBox cmbagama;
    private widget.ComboBox cmbidumur;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame10;
    private widget.InternalFrame internalFrame11;
    private widget.InternalFrame internalFrame12;
    private widget.InternalFrame internalFrame15;
    private widget.InternalFrame internalFrame16;
    private widget.InternalFrame internalFrame17;
    private widget.InternalFrame internalFrame18;
    private widget.InternalFrame internalFrame19;
    private widget.InternalFrame internalFrame20;
    private widget.InternalFrame internalFrame21;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame8;
    private widget.InternalFrame internalFrame9;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel104;
    private widget.Label jLabel105;
    private widget.Label jLabel106;
    private widget.Label jLabel107;
    private widget.Label jLabel108;
    private widget.Label jLabel109;
    private widget.Label jLabel11;
    private widget.Label jLabel110;
    private widget.Label jLabel111;
    private widget.Label jLabel112;
    private widget.Label jLabel113;
    private widget.Label jLabel114;
    private widget.Label jLabel115;
    private widget.Label jLabel116;
    private widget.Label jLabel117;
    private widget.Label jLabel118;
    private widget.Label jLabel119;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel29;
    private widget.Label jLabel3;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel5;
    private widget.Label jLabel52;
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel60;
    private widget.Label jLabel7;
    private widget.Label jLabel70;
    private widget.Label jLabel71;
    private widget.Label jLabel72;
    private widget.Label jLabel73;
    private widget.Label jLabel74;
    private widget.Label jLabel76;
    private widget.Label jLabel77;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.Label jLabel90;
    private widget.Label jLabel91;
    private widget.Label jLabel92;
    private widget.Label jLabel93;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private widget.Label jLabel96;
    private widget.Label jLabel97;
    private widget.Label jLabel98;
    private widget.Label jLabel99;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.TextBox kdtritmen;
    private widget.TextBox nmtritmen;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass15;
    private widget.panelisi panelGlass16;
    private widget.panelisi panelGlass17;
    private widget.panelisi panelGlass19;
    private widget.panelisi panelGlass22;
    private widget.panelisi panelGlass23;
    private widget.panelisi panelGlass24;
    private widget.panelisi panelGlass27;
    private widget.panelisi panelGlass29;
    private widget.panelisi panelGlass30;
    private widget.panelisi panelGlass31;
    private widget.panelisi panelGlass32;
    private widget.panelisi panelGlass33;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbDiagnosis;
    private widget.Table tbHarapan;
    private widget.Table tbIdentitasAnak;
    private widget.Table tbIdentitasPas;
    private widget.Table tbItemTritmen;
    private widget.Table tbKeluhan;
    private widget.Table tbManifestasi;
    private widget.Table tbPemeriksaan;
    private widget.Table tbPrognosis;
    private widget.Table tbTesPsiko;
    private widget.Table tbTritmen;
    private widget.Table tbdatadiri;
    private widget.Table tbkurangIstri;
    private widget.Table tbkurangSuami;
    private widget.Table tblebihIstri;
    private widget.Table tblebihSuami;
    private widget.Tanggal tgl1;
    private widget.Tanggal tgl2;
    private widget.TextBox tgl_datang;
    private widget.Tanggal tgl_nikah;
    private widget.Tanggal tgl_rencana;
    private widget.TextBox tritmenLain;
    // End of variables declaration//GEN-END:variables

    public void isCek() {
        BtnSimpan.setEnabled(akses.getrekam_psikologis());
        BtnHapus.setEnabled(akses.getrekam_psikologis());
        BtnEdit.setEnabled(akses.getrekam_psikologis());
        BtnPrint.setEnabled(akses.getrekam_psikologis());
     }

    public void setNoRm(String norw, Date tanggal) {
        TNoRW.setText(norw);
        tgl1.setDate(tanggal);        
        isDataDiri();
        
        cekRPP = 0;
        cekRPP = Sequel.cariInteger("SELECT COUNT(-1) cek FROM psikolog_data_diri_klien pd "
                + "INNER JOIN reg_periksa rp ON rp.no_rawat=pd.no_rawat WHERE rp.no_rkm_medis='" + TNoRM.getText() + "' and pd.rekam_psikologis='perkawinan'");
        
        if (cekRPP > 0) {
            THobi.setText(Sequel.cariIsi("select hobi from psikolog_data_diri_klien where no_rawat='" + norw + "'"));
            TnoTelp.setText(Sequel.cariIsi("select no_hp_bisa_dihubungi from psikolog_data_diri_klien where no_rawat='" + norw + "'"));
        } else if (cekRPP == 0) {
            THobi.setText("");
            TnoTelp.setText(Sequel.cariIsi("select no_tlp from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
        }
        TabPsikologis.setSelectedIndex(0);
    }
    
    private void isDataDiri() {
        tglKedatangan = "";
        try {
            ps = koneksi.prepareStatement("SELECT rp.no_rawat, rp.tgl_registrasi, p.no_rkm_medis, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_dtg, "
                    + "p.nm_pasien, if(p.jk = 'L','Laki-laki','Perempuan') jk, CONCAT(p.tmp_lahir,', ',DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y'),' / ',rp.umurdaftar,' tahun') ttl, "
                    + "p.pekerjaan, p.pnd, p.stts_nikah, p.agama, CONCAT(p.alamat,', ', kl.nm_kel,', ', kc.nm_kec,', ',kb.nm_kab) almt, p.no_tlp, "
                    + "date_format(p.tgl_lahir,'%d-%m-%Y') tgl_lhr, d.nm_dokter, sb.nama_suku_bangsa FROM reg_periksa rp "
                    + "INNER JOIN pasien p on p.no_rkm_medis = rp.no_rkm_medis INNER JOIN dokter d on d.kd_dokter=rp.kd_dokter INNER JOIN kelurahan kl on kl.kd_kel = p.kd_kel "
                    + "INNER JOIN kecamatan kc on kc.kd_kec = p.kd_kec INNER JOIN kabupaten kb on kb.kd_kab = p.kd_kab INNER JOIN suku_bangsa sb on sb.id = p.suku_bangsa "
                    + "WHERE rp.no_rawat = '" + TNoRW.getText() + "'");
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    tglKedatangan = rs.getString("tgl_registrasi");
                    tgl_datang.setText(rs.getString("tgl_dtg"));
                    Tjk.setText(rs.getString("jk"));
                    TnmPsikolog.setText(rs.getString("nm_dokter"));
                    Ttl.setText(rs.getString("ttl"));
                    TAgama.setText(rs.getString("agama"));
                    TSuku.setText(rs.getString("nama_suku_bangsa"));
                    TPekerjaan.setText(rs.getString("pekerjaan"));
                    TPendidikan.setText(rs.getString("pnd"));
                    TSttsKawin.setText(rs.getString("stts_nikah"));
                    TAlamat.setText(rs.getString("almt"));
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilDataDiri() {
        Valid.tabelKosong(tabMode1);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps1 = koneksi.prepareStatement("SELECT rp.no_rawat, rp.tgl_registrasi, p.no_rkm_medis, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_dtg, "
                        + "p.nm_pasien, if(p.jk = 'L','Laki-laki','Perempuan') jk, CONCAT(p.tmp_lahir,', ',DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y'),' / ',rp.umurdaftar,' tahun') ttl, "
                        + "p.agama, sb.nama_suku_bangsa, p.pekerjaan, p.pnd, p.stts_nikah, pd.hobi, CONCAT(p.alamat,', ', kl.nm_kel,', ', kc.nm_kec,', ',kb.nm_kab) almt, "
                        + "pd.no_hp_bisa_dihubungi FROM psikolog_data_diri_klien pd INNER JOIN reg_periksa rp on rp.no_rawat = pd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis = rp.no_rkm_medis INNER JOIN kelurahan kl on kl.kd_kel = p.kd_kel "
                        + "INNER JOIN kecamatan kc on kc.kd_kec = p.kd_kec INNER JOIN kabupaten kb on kb.kd_kab = p.kd_kab INNER JOIN suku_bangsa sb on sb.id=p.suku_bangsa WHERE "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>= 15 and rp.sttsumur='Th' and rp.no_rawat like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>= 15 and rp.sttsumur='Th' and p.no_rkm_medis like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>= 15 and rp.sttsumur='Th' and p.nm_pasien like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>= 15 and rp.sttsumur='Th' and if(p.jk = 'L','Laki-laki','Perempuan') like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>= 15 and rp.sttsumur='Th' and CONCAT(p.tmp_lahir,', ',DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y'),' / ',rp.umurdaftar,' tahun') like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>= 15 and rp.sttsumur='Th' and p.pekerjaan like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>= 15 and rp.sttsumur='Th' and p.pnd like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>= 15 and rp.sttsumur='Th' and p.stts_nikah like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>= 15 and rp.sttsumur='Th' and CONCAT(p.alamat,', ', kl.nm_kel,', ', kc.nm_kec,', ',kb.nm_kab) like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>= 15 and rp.sttsumur='Th' and p.agama like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>= 15 and rp.sttsumur='Th' and sb.nama_suku_bangsa like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.hobi like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.no_hp_bisa_dihubungi like ? order by rp.tgl_registrasi desc");
            } else {
                ps1 = koneksi.prepareStatement("SELECT rp.no_rawat, rp.tgl_registrasi, p.no_rkm_medis, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_dtg, "
                        + "p.nm_pasien, if(p.jk = 'L','Laki-laki','Perempuan') jk, CONCAT(p.tmp_lahir,', ',DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y'),' / ',rp.umurdaftar,' tahun') ttl, "
                        + "p.agama, sb.nama_suku_bangsa, p.pekerjaan, p.pnd, p.stts_nikah, pd.hobi, CONCAT(p.alamat,', ', kl.nm_kel,', ', kc.nm_kec,', ',kb.nm_kab) almt, "
                        + "pd.no_hp_bisa_dihubungi FROM psikolog_data_diri_klien pd INNER JOIN reg_periksa rp on rp.no_rawat = pd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis = rp.no_rkm_medis INNER JOIN kelurahan kl on kl.kd_kel = p.kd_kel "
                        + "INNER JOIN kecamatan kc on kc.kd_kec = p.kd_kec INNER JOIN kabupaten kb on kb.kd_kab = p.kd_kab INNER JOIN suku_bangsa sb on sb.id=p.suku_bangsa WHERE "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>= 15 and rp.sttsumur='Th' and rp.no_rawat like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>= 15 and rp.sttsumur='Th' and p.no_rkm_medis like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>= 15 and rp.sttsumur='Th' and p.nm_pasien like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>= 15 and rp.sttsumur='Th' and if(p.jk = 'L','Laki-laki','Perempuan') like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>= 15 and rp.sttsumur='Th' and CONCAT(p.tmp_lahir,', ',DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y'),' / ',rp.umurdaftar,' tahun') like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>= 15 and rp.sttsumur='Th' and p.pekerjaan like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>= 15 and rp.sttsumur='Th' and p.pnd like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>= 15 and rp.sttsumur='Th' and p.stts_nikah like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>= 15 and rp.sttsumur='Th' and CONCAT(p.alamat,', ', kl.nm_kel,', ', kc.nm_kec,', ',kb.nm_kab) like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>= 15 and rp.sttsumur='Th' and p.agama like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>= 15 and rp.sttsumur='Th' and sb.nama_suku_bangsa like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.hobi like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.no_hp_bisa_dihubungi like ? "
                        + "order by rp.tgl_registrasi desc LIMIT " + cmbLimit.getSelectedItem().toString() + "");
            }
            try {
                ps1.setString(1, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(2, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(3, "%" + TCari.getText().trim() + "%");                
                ps1.setString(4, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(5, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(6, "%" + TCari.getText().trim() + "%");                
                ps1.setString(7, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(8, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(9, "%" + TCari.getText().trim() + "%");                
                ps1.setString(10, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(11, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(12, "%" + TCari.getText().trim() + "%");                
                ps1.setString(13, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(14, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(15, "%" + TCari.getText().trim() + "%");                
                ps1.setString(16, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(17, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(18, "%" + TCari.getText().trim() + "%");                
                ps1.setString(19, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(20, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(21, "%" + TCari.getText().trim() + "%");                
                ps1.setString(22, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(23, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(24, "%" + TCari.getText().trim() + "%");                
                ps1.setString(25, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(26, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(27, "%" + TCari.getText().trim() + "%");                
                ps1.setString(28, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(29, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(30, "%" + TCari.getText().trim() + "%");                
                ps1.setString(31, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(32, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(33, "%" + TCari.getText().trim() + "%");
                ps1.setString(34, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(35, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(36, "%" + TCari.getText().trim() + "%");
                ps1.setString(37, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(38, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(39, "%" + TCari.getText().trim() + "%");
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new Object[]{
                        rs1.getString("no_rawat"),
                        rs1.getString("tgl_registrasi"),
                        rs1.getString("no_rkm_medis"),                        
                        rs1.getString("tgl_dtg"),
                        rs1.getString("nm_pasien"),
                        rs1.getString("jk"),
                        rs1.getString("ttl"),
                        rs1.getString("agama"),
                        rs1.getString("nama_suku_bangsa"),
                        rs1.getString("pnd"),
                        rs1.getString("pekerjaan"),                        
                        rs1.getString("stts_nikah"),
                        rs1.getString("hobi"),
                        rs1.getString("almt"),
                        rs1.getString("no_hp_bisa_dihubungi")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs1 != null) {
                    rs1.close();
                }
                if (ps1 != null) {
                    ps1.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode1.getRowCount());
    }
    
    private void tampilIdentitasPasangan() {
        Valid.tabelKosong(tabMode2);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps2 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pip.pasangan, pip.nama, concat(pip.umur,' Tahun') umurpas, pip.pendidikan, pip.pekerjaan, "
                        + "pip.agama, concat(pip.anak_ke,' dari ',pip.dari_bersaudara,' bersaudara') anakke, pip.alamat, pip.no_hp, pip.pernikahan_ke, "
                        + "concat(pip.tmpt_menikah,', ',date_format(pip.tgl_menikah,'%d-%m-%Y')) ttm, pip.lama_menikah, pip.lama_kenal_sebelum_nikah, "
                        + "pip.lama_pacrn_sebelum_nikah, pip.umur, pip.anak_ke, pip.dari_bersaudara, pip.tmpt_menikah, pip.tgl_menikah, pip.waktu_simpan "
                        + "from psikolog_perkawinan_identitas_pas pip inner join pasien p on p.no_rkm_medis =pip.no_rkm_medis WHERE "
                        + "pip.no_rkm_medis like '%" + TNoRM.getText() + "%' and p.nm_pasien like ? or "
                        + "pip.no_rkm_medis like '%" + TNoRM.getText() + "%' and pip.pasangan like ? or "
                        + "pip.no_rkm_medis like '%" + TNoRM.getText() + "%' and pip.nama like ? or "
                        + "pip.no_rkm_medis like '%" + TNoRM.getText() + "%' and pip.pekerjaan like ? or "
                        + "pip.no_rkm_medis like '%" + TNoRM.getText() + "%' and pip.alamat like ? or "
                        + "pip.no_rkm_medis like '%" + TNoRM.getText() + "%' and pip.no_hp like ? order by pip.waktu_simpan desc");
            } else {
                ps2 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pip.pasangan, pip.nama, concat(pip.umur,' Tahun') umurpas, pip.pendidikan, pip.pekerjaan, "
                        + "pip.agama, concat(pip.anak_ke,' dari ',pip.dari_bersaudara,' bersaudara') anakke, pip.alamat, pip.no_hp, pip.pernikahan_ke, "
                        + "concat(pip.tmpt_menikah,', ',date_format(pip.tgl_menikah,'%d-%m-%Y')) ttm, pip.lama_menikah, pip.lama_kenal_sebelum_nikah, "
                        + "pip.lama_pacrn_sebelum_nikah, pip.umur, pip.anak_ke, pip.dari_bersaudara, pip.tmpt_menikah, pip.tgl_menikah, pip.waktu_simpan "
                        + "from psikolog_perkawinan_identitas_pas pip inner join pasien p on p.no_rkm_medis =pip.no_rkm_medis WHERE "
                        + "pip.no_rkm_medis like '%" + TNoRM.getText() + "%' and p.nm_pasien like ? or "
                        + "pip.no_rkm_medis like '%" + TNoRM.getText() + "%' and pip.pasangan like ? or "
                        + "pip.no_rkm_medis like '%" + TNoRM.getText() + "%' and pip.nama like ? or "
                        + "pip.no_rkm_medis like '%" + TNoRM.getText() + "%' and pip.pekerjaan like ? or "
                        + "pip.no_rkm_medis like '%" + TNoRM.getText() + "%' and pip.alamat like ? or "
                        + "pip.no_rkm_medis like '%" + TNoRM.getText() + "%' and pip.no_hp like ? order by pip.waktu_simpan desc "
                        + "LIMIT " + cmbLimit.getSelectedItem().toString() + "");
            }
            try {
                ps2.setString(1, "%" + TCari.getText().trim() + "%");
                ps2.setString(2, "%" + TCari.getText().trim() + "%");
                ps2.setString(3, "%" + TCari.getText().trim() + "%");
                ps2.setString(4, "%" + TCari.getText().trim() + "%");
                ps2.setString(5, "%" + TCari.getText().trim() + "%");
                ps2.setString(6, "%" + TCari.getText().trim() + "%");
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode2.addRow(new Object[]{
                        rs2.getString("no_rkm_medis"),
                        rs2.getString("nm_pasien"),
                        rs2.getString("pasangan"),
                        rs2.getString("nama"),
                        rs2.getString("umurpas"),
                        rs2.getString("pendidikan"),
                        rs2.getString("pekerjaan"),
                        rs2.getString("agama"),
                        rs2.getString("anakke"),
                        rs2.getString("alamat"),                        
                        rs2.getString("no_hp"),
                        rs2.getString("pernikahan_ke"),
                        rs2.getString("ttm"),
                        rs2.getString("lama_menikah"),
                        rs2.getString("lama_kenal_sebelum_nikah"),
                        rs2.getString("lama_pacrn_sebelum_nikah"),
                        rs2.getString("umur"),
                        rs2.getString("anak_ke"),
                        rs2.getString("dari_bersaudara"),
                        rs2.getString("tmpt_menikah"),
                        rs2.getString("tgl_menikah"),
                        rs2.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs2 != null) {
                    rs2.close();
                }
                if (ps2 != null) {
                    ps2.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode2.getRowCount());
    }
    
    private void tampilIdentitasAnak() {
        Valid.tabelKosong(tabMode3);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps3 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pia.anak_ke, pia.nama, pia.jk, concat(pia.umur,' ',pia.id_umur) usia, "
                        + "pia.keterangan, pia.pekerjaan_sekolah, pia.umur, pia.id_umur, pia.waktu_simpan "
                        + "FROM psikolog_perkawinan_identitas_anak pia INNER JOIN pasien p ON p.no_rkm_medis = pia.no_rkm_medis WHERE "
                        + "pia.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and p.no_rkm_medis like ? or "
                        + "pia.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and p.nm_pasien like ? or "
                        + "pia.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and pia.nama like ? or "
                        + "pia.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and concat(pia.umur,' ',pia.id_umur) like ? or "
                        + "pia.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and pia.pekerjaan_sekolah like ? order by pia.waktu_simpan desc");
            } else {
                ps3 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pia.anak_ke, pia.nama, pia.jk, concat(pia.umur,' ',pia.id_umur) usia, "
                        + "pia.keterangan, pia.pekerjaan_sekolah, pia.umur, pia.id_umur, pia.waktu_simpan "
                        + "FROM psikolog_perkawinan_identitas_anak pia INNER JOIN pasien p ON p.no_rkm_medis = pia.no_rkm_medis WHERE "
                        + "pia.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and p.no_rkm_medis like ? or "
                        + "pia.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and p.nm_pasien like ? or "
                        + "pia.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and pia.nama like ? or "
                        + "pia.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and concat(pia.umur,' ',pia.id_umur) like ? or "
                        + "pia.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and pia.pekerjaan_sekolah like ? "
                        + "order by pia.waktu_simpan desc limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {
                ps3.setString(1, "%" + TCari.getText().trim() + "%");
                ps3.setString(2, "%" + TCari.getText().trim() + "%");
                ps3.setString(3, "%" + TCari.getText().trim() + "%");
                ps3.setString(4, "%" + TCari.getText().trim() + "%");
                ps3.setString(5, "%" + TCari.getText().trim() + "%");
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    tabMode3.addRow(new Object[]{
                        rs3.getString("no_rkm_medis"),
                        rs3.getString("nm_pasien"),
                        rs3.getString("anak_ke"),
                        rs3.getString("nama"),
                        rs3.getString("jk"),
                        rs3.getString("usia"),
                        rs3.getString("keterangan"),
                        rs3.getString("pekerjaan_sekolah"),
                        rs3.getString("umur"),
                        rs3.getString("id_umur"),
                        rs3.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs3 != null) {
                    rs3.close();
                }
                if (ps3 != null) {
                    ps3.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode3.getRowCount());
    }
    
    private void tampilKeluhanMasalah() {
        Valid.tabelKosong(tabMode4);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps4 = koneksi.prepareStatement("SELECT pk.no_rawat, p.no_rkm_medis, p.nm_pasien, pk.masalah_dalam_nikah, pk.sudah_brp_lama, "
                        + "pk.alasan_bantuan, pk.permasalahan_dinilai, pk.karena FROM psikolog_perkawinan_keluhan pk "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pk.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and pk.no_rawat like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.no_rkm_medis like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.nm_pasien like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and pk.masalah_dalam_nikah like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and pk.sudah_brp_lama like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and pk.alasan_bantuan like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and pk.permasalahan_dinilai like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and pk.karena like ? order by rp.tgl_registrasi desc");
            } else {
                ps4 = koneksi.prepareStatement("SELECT pk.no_rawat, p.no_rkm_medis, p.nm_pasien, pk.masalah_dalam_nikah, pk.sudah_brp_lama, "
                        + "pk.alasan_bantuan, pk.permasalahan_dinilai, pk.karena FROM psikolog_perkawinan_keluhan pk "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pk.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and pk.no_rawat like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.no_rkm_medis like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.nm_pasien like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and pk.masalah_dalam_nikah like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and pk.sudah_brp_lama like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and pk.alasan_bantuan like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and pk.permasalahan_dinilai like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and pk.karena like ? order by rp.tgl_registrasi desc "
                        + "limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {
                ps4.setString(1, "%" + TCari.getText().trim() + "%");
                ps4.setString(2, "%" + TCari.getText().trim() + "%");
                ps4.setString(3, "%" + TCari.getText().trim() + "%");
                ps4.setString(4, "%" + TCari.getText().trim() + "%");                
                ps4.setString(5, "%" + TCari.getText().trim() + "%");
                ps4.setString(6, "%" + TCari.getText().trim() + "%");
                ps4.setString(7, "%" + TCari.getText().trim() + "%");
                ps4.setString(8, "%" + TCari.getText().trim() + "%");
                rs4 = ps4.executeQuery();
                while (rs4.next()) {
                    tabMode4.addRow(new Object[]{
                        rs4.getString("no_rawat"),
                        rs4.getString("no_rkm_medis"),
                        rs4.getString("nm_pasien"),
                        rs4.getString("masalah_dalam_nikah"),
                        rs4.getString("sudah_brp_lama"),
                        rs4.getString("alasan_bantuan"),
                        rs4.getString("permasalahan_dinilai"),
                        rs4.getString("karena")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs4 != null) {
                    rs4.close();
                }
                if (ps4 != null) {
                    ps4.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode4.getRowCount());
    }
    
    private void tampilHarapan() {
        Valid.tabelKosong(tabMode5);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps6 = koneksi.prepareStatement("SELECT pw.no_rawat, p.no_rkm_medis, p.nm_pasien, pw.harapan, pw.perubahan_diinginkan_diri_sendiri, "
                        + "pw.perubahan_diinginkan_keluarga, pw.permasalahan_saat_ini FROM psikolog_wawancara_klinis pw "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pw.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Perkawinan' and pw.no_rawat like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Perkawinan' and p.no_rkm_medis like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Perkawinan' and p.nm_pasien like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Perkawinan' and pw.permasalahan_saat_ini like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Perkawinan' and pw.harapan like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Perkawinan' and pw.perubahan_diinginkan_diri_sendiri like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Perkawinan' and pw.perubahan_diinginkan_keluarga like ? order by pw.no_rawat desc");
            } else {
                ps6 = koneksi.prepareStatement("SELECT pw.no_rawat, p.no_rkm_medis, p.nm_pasien, pw.harapan, pw.perubahan_diinginkan_diri_sendiri, "
                        + "pw.perubahan_diinginkan_keluarga, pw.permasalahan_saat_ini FROM psikolog_wawancara_klinis pw "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pw.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Perkawinan' and pw.no_rawat like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Perkawinan' and p.no_rkm_medis like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Perkawinan' and p.nm_pasien like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Perkawinan' and pw.permasalahan_saat_ini like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Perkawinan' and pw.harapan like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Perkawinan' and pw.perubahan_diinginkan_diri_sendiri like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Perkawinan' and pw.perubahan_diinginkan_keluarga like ? "
                        + "order by pw.no_rawat desc limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {
                ps6.setString(1, "%" + TCari.getText().trim() + "%");
                ps6.setString(2, "%" + TCari.getText().trim() + "%");
                ps6.setString(3, "%" + TCari.getText().trim() + "%");
                ps6.setString(4, "%" + TCari.getText().trim() + "%");                
                ps6.setString(5, "%" + TCari.getText().trim() + "%");
                ps6.setString(6, "%" + TCari.getText().trim() + "%");
                ps6.setString(7, "%" + TCari.getText().trim() + "%");
                rs6 = ps6.executeQuery();
                while (rs6.next()) {
                    tabMode5.addRow(new Object[]{
                        rs6.getString("no_rawat"),
                        rs6.getString("no_rkm_medis"),
                        rs6.getString("nm_pasien"),
                        rs6.getString("harapan"),
                        rs6.getString("perubahan_diinginkan_diri_sendiri"),
                        rs6.getString("perubahan_diinginkan_keluarga"),
                        rs6.getString("permasalahan_saat_ini")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs6 != null) {
                    rs6.close();
                }
                if (ps6 != null) {
                    ps6.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode5.getRowCount());
    }
    
    private void tampilLebihSuami() {
        Valid.tabelKosong(tabModeSL);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps7 = koneksi.prepareStatement("SELECT * FROM psikolog_perkawinan_pemahaman_pas WHERE "
                        + "no_rawat LIKE '%" + TNoRW.getText() + "%' and jenis_pasangan='SUAMI' and jenis_penilaian='KELEBIHAN' order by waktu_simpan");
            } else {
                ps7 = koneksi.prepareStatement("SELECT * FROM psikolog_perkawinan_pemahaman_pas WHERE "
                        + "no_rawat LIKE '%" + TNoRW.getText() + "%' and jenis_pasangan='SUAMI' and jenis_penilaian='KELEBIHAN' "
                        + "order by waktu_simpan limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {                
                rs7 = ps7.executeQuery();
                x = 1;
                while (rs7.next()) {
                    tabModeSL.addRow(new Object[]{
                        rs7.getString("no_rawat"),
                        x + ".",
                        rs7.getString("deskripsi_penilaian"),
                        rs7.getString("waktu_simpan"),
                        rs7.getString("jenis_pasangan"),
                        rs7.getString("jenis_penilaian")
                    });
                    x++;
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs7 != null) {
                    rs7.close();
                }
                if (ps7 != null) {
                    ps7.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }        
    }
    
    private void tampilKurangSuami() {
        Valid.tabelKosong(tabModeSK);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps8 = koneksi.prepareStatement("SELECT * FROM psikolog_perkawinan_pemahaman_pas WHERE "
                        + "no_rawat LIKE '%" + TNoRW.getText() + "%' and jenis_pasangan='SUAMI' and jenis_penilaian='KEKURANGAN' order by waktu_simpan");
            } else {
                ps8 = koneksi.prepareStatement("SELECT * FROM psikolog_perkawinan_pemahaman_pas WHERE "
                        + "no_rawat LIKE '%" + TNoRW.getText() + "%' and jenis_pasangan='SUAMI' and jenis_penilaian='KEKURANGAN' "
                        + "order by waktu_simpan limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {                
                rs8 = ps8.executeQuery();
                x = 1;
                while (rs8.next()) {
                    tabModeSK.addRow(new Object[]{
                        rs8.getString("no_rawat"),
                        x + ".",
                        rs8.getString("deskripsi_penilaian"),
                        rs8.getString("waktu_simpan"),
                        rs8.getString("jenis_pasangan"),
                        rs8.getString("jenis_penilaian")
                    });
                    x++;
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs8 != null) {
                    rs8.close();
                }
                if (ps8 != null) {
                    ps8.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }        
    }
    
    private void tampilLebihIstri() {
        Valid.tabelKosong(tabModeIL);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps9 = koneksi.prepareStatement("SELECT * FROM psikolog_perkawinan_pemahaman_pas WHERE "
                        + "no_rawat LIKE '%" + TNoRW.getText() + "%' and jenis_pasangan='ISTRI' and jenis_penilaian='KELEBIHAN' order by waktu_simpan");
            } else {
                ps9 = koneksi.prepareStatement("SELECT * FROM psikolog_perkawinan_pemahaman_pas WHERE "
                        + "no_rawat LIKE '%" + TNoRW.getText() + "%' and jenis_pasangan='ISTRI' and jenis_penilaian='KELEBIHAN' "
                        + "order by waktu_simpan limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {                
                rs9 = ps9.executeQuery();
                x = 1;
                while (rs9.next()) {
                    tabModeIL.addRow(new Object[]{
                        rs9.getString("no_rawat"),
                        x + ".",
                        rs9.getString("deskripsi_penilaian"),
                        rs9.getString("waktu_simpan"),
                        rs9.getString("jenis_pasangan"),
                        rs9.getString("jenis_penilaian")
                    });
                    x++;
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs9 != null) {
                    rs9.close();
                }
                if (ps9 != null) {
                    ps9.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }        
    }
    
    private void tampilKurangIstri() {
        Valid.tabelKosong(tabModeIK);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps10 = koneksi.prepareStatement("SELECT * FROM psikolog_perkawinan_pemahaman_pas WHERE "
                        + "no_rawat LIKE '%" + TNoRW.getText() + "%' and jenis_pasangan='ISTRI' and jenis_penilaian='KEKURANGAN' order by waktu_simpan");
            } else {
                ps10 = koneksi.prepareStatement("SELECT * FROM psikolog_perkawinan_pemahaman_pas WHERE "
                        + "no_rawat LIKE '%" + TNoRW.getText() + "%' and jenis_pasangan='ISTRI' and jenis_penilaian='KEKURANGAN' "
                        + "order by waktu_simpan limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {                
                rs10 = ps10.executeQuery();
                x = 1;
                while (rs10.next()) {
                    tabModeIK.addRow(new Object[]{
                        rs10.getString("no_rawat"),
                        x + ".",
                        rs10.getString("deskripsi_penilaian"),
                        rs10.getString("waktu_simpan"),
                        rs10.getString("jenis_pasangan"),
                        rs10.getString("jenis_penilaian")
                    });
                    x++;
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs10 != null) {
                    rs10.close();
                }
                if (ps10 != null) {
                    ps10.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }        
    }
    
    private void tampilPemeriksaanKlinis() {
        Valid.tabelKosong(tabMode6);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps11 = koneksi.prepareStatement("SELECT ppk.no_rawat, p.no_rkm_medis, p.nm_pasien, ppk.wawancara, ppk.observasi "
                        + "FROM psikolog_perkawinan_pemeriksaan_klinis ppk INNER JOIN reg_periksa rp ON rp.no_rawat=ppk.no_rawat "
                        + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "ppk.no_rawat LIKE '%" + TNoRW.getText() + "%' and ppk.no_rawat like ? or "
                        + "ppk.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.no_rkm_medis like ? or "
                        + "ppk.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.nm_pasien like ? or "
                        + "ppk.no_rawat LIKE '%" + TNoRW.getText() + "%' and ppk.wawancara like ? or "
                        + "ppk.no_rawat LIKE '%" + TNoRW.getText() + "%' and ppk.observasi like ? order by ppk.no_rawat desc");
            } else {
                ps11 = koneksi.prepareStatement("SELECT ppk.no_rawat, p.no_rkm_medis, p.nm_pasien, ppk.wawancara, ppk.observasi "
                        + "FROM psikolog_perkawinan_pemeriksaan_klinis ppk INNER JOIN reg_periksa rp ON rp.no_rawat=ppk.no_rawat "
                        + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "ppk.no_rawat LIKE '%" + TNoRW.getText() + "%' and ppk.no_rawat like ? or "
                        + "ppk.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.no_rkm_medis like ? or "
                        + "ppk.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.nm_pasien like ? or "
                        + "ppk.no_rawat LIKE '%" + TNoRW.getText() + "%' and ppk.wawancara like ? or "
                        + "ppk.no_rawat LIKE '%" + TNoRW.getText() + "%' and ppk.observasi like ? order by ppk.no_rawat desc "
                        + "limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {
                ps11.setString(1, "%" + TCari.getText().trim() + "%");
                ps11.setString(2, "%" + TCari.getText().trim() + "%");
                ps11.setString(3, "%" + TCari.getText().trim() + "%");
                ps11.setString(4, "%" + TCari.getText().trim() + "%");                
                ps11.setString(5, "%" + TCari.getText().trim() + "%");
                rs11 = ps11.executeQuery();
                while (rs11.next()) {
                    tabMode6.addRow(new Object[]{
                        rs11.getString("no_rawat"),
                        rs11.getString("no_rkm_medis"),
                        rs11.getString("nm_pasien"),
                        rs11.getString("wawancara"),
                        rs11.getString("observasi")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs11 != null) {
                    rs11.close();
                }
                if (ps11 != null) {
                    ps11.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode6.getRowCount());
    }
    
    private void tampilTesPsikologi() {
        Valid.tabelKosong(tabMode7);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps12 = koneksi.prepareStatement("SELECT pt.no_rawat, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(pt.rencana_tes,'%d-%m-%Y') tglRencana, "
                        + "pt.tes_psikologis, pt.waktu, pt.tester, pt.rencana_tes FROM psikolog_tes_psikologi pt "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pt.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='perkawinan' and pt.no_rawat like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='perkawinan' and p.no_rkm_medis like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='perkawinan' and p.nm_pasien like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='perkawinan' and pt.tes_psikologis like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='perkawinan' and pt.waktu like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='perkawinan' and pt.tester like ? "
                        + "order by pt.no_rawat desc");
            } else {
                ps12 = koneksi.prepareStatement("SELECT pt.no_rawat, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(pt.rencana_tes,'%d-%m-%Y') tglRencana, "
                        + "pt.tes_psikologis, pt.waktu, pt.tester, pt.rencana_tes FROM psikolog_tes_psikologi pt "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pt.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='perkawinan' and pt.no_rawat like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='perkawinan' and p.no_rkm_medis like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='perkawinan' and p.nm_pasien like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='perkawinan' and pt.tes_psikologis like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='perkawinan' and pt.waktu like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='perkawinan' and pt.tester like ? "
                        + "order by pt.no_rawat desc limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {
                ps12.setString(1, "%" + TCari.getText().trim() + "%");
                ps12.setString(2, "%" + TCari.getText().trim() + "%");
                ps12.setString(3, "%" + TCari.getText().trim() + "%");
                ps12.setString(4, "%" + TCari.getText().trim() + "%");                
                ps12.setString(5, "%" + TCari.getText().trim() + "%");
                ps12.setString(6, "%" + TCari.getText().trim() + "%");
                rs12 = ps12.executeQuery();
                while (rs12.next()) {
                    tabMode7.addRow(new Object[]{
                        rs12.getString("no_rawat"),
                        rs12.getString("no_rkm_medis"),
                        rs12.getString("nm_pasien"),                        
                        rs12.getString("tglRencana"),
                        rs12.getString("tes_psikologis"),
                        rs12.getString("waktu"),
                        rs12.getString("tester"),
                        rs12.getString("rencana_tes")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs12 != null) {
                    rs12.close();
                }
                if (ps12 != null) {
                    ps12.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode7.getRowCount());
    }
    
    private void tampilManifestasiFungsi() {
        Valid.tabelKosong(tabMode8);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps13 = koneksi.prepareStatement("SELECT pm.no_rawat, p.no_rkm_medis, p.nm_pasien, pm.manifestasi FROM psikolog_manifestasi_fungsi pm "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pm.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN psikolog_data_diri_klien pd on pd.no_rawat=pm.no_rawat WHERE "
                        + "pm.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.rekam_psikologis='perkawinan' and pm.no_rawat like ? or "
                        + "pm.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.rekam_psikologis='perkawinan' and p.no_rkm_medis like ? or "
                        + "pm.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.rekam_psikologis='perkawinan' and p.nm_pasien like ? or "
                        + "pm.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.rekam_psikologis='perkawinan' and pm.manifestasi like ? "
                        + "order by pm.no_rawat desc");
            } else {
                ps13 = koneksi.prepareStatement("SELECT pm.no_rawat, p.no_rkm_medis, p.nm_pasien, pm.manifestasi FROM psikolog_manifestasi_fungsi pm "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pm.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN psikolog_data_diri_klien pd on pd.no_rawat=pm.no_rawat WHERE "
                        + "pm.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.rekam_psikologis='perkawinan' and pm.no_rawat like ? or "
                        + "pm.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.rekam_psikologis='perkawinan' and p.no_rkm_medis like ? or "
                        + "pm.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.rekam_psikologis='perkawinan' and p.nm_pasien like ? or "
                        + "pm.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.rekam_psikologis='perkawinan' and pm.manifestasi like ? "
                        + "order by pm.no_rawat desc limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {
                ps13.setString(1, "%" + TCari.getText().trim() + "%");
                ps13.setString(2, "%" + TCari.getText().trim() + "%");
                ps13.setString(3, "%" + TCari.getText().trim() + "%");
                ps13.setString(4, "%" + TCari.getText().trim() + "%");
                rs13 = ps13.executeQuery();
                while (rs13.next()) {
                    tabMode8.addRow(new Object[]{
                        rs13.getString("no_rawat"),
                        rs13.getString("no_rkm_medis"),
                        rs13.getString("nm_pasien"),                        
                        rs13.getString("manifestasi")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs13 != null) {
                    rs13.close();
                }
                if (ps13 != null) {
                    ps13.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode8.getRowCount());
    }
    
    private void tampilDiagnosis() {
        Valid.tabelKosong(tabMode9);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps14 = koneksi.prepareStatement("SELECT pd.no_rawat, p.no_rkm_medis, p.nm_pasien, pd.kesan_awal, pd.diagnosa_utama, "
                        + "pd.diagnosa_banding FROM psikolog_diagnosis pd INNER JOIN reg_periksa rp ON rp.no_rawat=pd.no_rawat "
                        + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis INNER JOIN psikolog_data_diri_klien pdd on pdd.no_rawat=pd.no_rawat WHERE "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pdd.rekam_psikologis='perkawinan' and pd.no_rawat like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pdd.rekam_psikologis='perkawinan' and p.no_rkm_medis like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pdd.rekam_psikologis='perkawinan' and p.nm_pasien like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pdd.rekam_psikologis='perkawinan' and pd.kesan_awal like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pdd.rekam_psikologis='perkawinan' and pd.diagnosa_utama like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pdd.rekam_psikologis='perkawinan' and pd.diagnosa_banding like ? "
                        + "order by pd.no_rawat desc");
            } else {
                ps14 = koneksi.prepareStatement("SELECT pd.no_rawat, p.no_rkm_medis, p.nm_pasien, pd.kesan_awal, pd.diagnosa_utama, "
                        + "pd.diagnosa_banding FROM psikolog_diagnosis pd INNER JOIN reg_periksa rp ON rp.no_rawat=pd.no_rawat "
                        + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis INNER JOIN psikolog_data_diri_klien pdd on pdd.no_rawat=pd.no_rawat WHERE "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pdd.rekam_psikologis='perkawinan' and pd.no_rawat like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pdd.rekam_psikologis='perkawinan' and p.no_rkm_medis like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pdd.rekam_psikologis='perkawinan' and p.nm_pasien like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pdd.rekam_psikologis='perkawinan' and pd.kesan_awal like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pdd.rekam_psikologis='perkawinan' and pd.diagnosa_utama like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pdd.rekam_psikologis='perkawinan' and pd.diagnosa_banding like ? "
                        + "order by pd.no_rawat desc limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {
                ps14.setString(1, "%" + TCari.getText().trim() + "%");
                ps14.setString(2, "%" + TCari.getText().trim() + "%");
                ps14.setString(3, "%" + TCari.getText().trim() + "%");
                ps14.setString(4, "%" + TCari.getText().trim() + "%");
                ps14.setString(5, "%" + TCari.getText().trim() + "%");
                ps14.setString(6, "%" + TCari.getText().trim() + "%");
                rs14 = ps14.executeQuery();
                while (rs14.next()) {
                    tabMode9.addRow(new Object[]{
                        rs14.getString("no_rawat"),
                        rs14.getString("no_rkm_medis"),
                        rs14.getString("nm_pasien"),                        
                        rs14.getString("kesan_awal"),
                        rs14.getString("diagnosa_utama"),
                        rs14.getString("diagnosa_banding")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs14 != null) {
                    rs14.close();
                }
                if (ps14 != null) {
                    ps14.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode9.getRowCount());
    }
    
    private void tampilPrognosis() {
        Valid.tabelKosong(tabMode10);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps15 = koneksi.prepareStatement("SELECT pp.no_rawat, p.no_rkm_medis, p.nm_pasien, pp.prognosis FROM psikolog_prognosis pp "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pp.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN psikolog_data_diri_klien pd on pd.no_rawat=pp.no_rawat WHERE "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.rekam_psikologis='perkawinan' and pp.no_rawat like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.rekam_psikologis='perkawinan' and p.no_rkm_medis like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.rekam_psikologis='perkawinan' and p.nm_pasien like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.rekam_psikologis='perkawinan' and pp.prognosis like ? "
                        + "order by pp.no_rawat desc");
            } else {
                ps15 = koneksi.prepareStatement("SELECT pp.no_rawat, p.no_rkm_medis, p.nm_pasien, pp.prognosis FROM psikolog_prognosis pp "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pp.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN psikolog_data_diri_klien pd on pd.no_rawat=pp.no_rawat WHERE "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.rekam_psikologis='perkawinan' and pp.no_rawat like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.rekam_psikologis='perkawinan' and p.no_rkm_medis like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.rekam_psikologis='perkawinan' and p.nm_pasien like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.rekam_psikologis='perkawinan' and pp.prognosis like ? "
                        + "order by pp.no_rawat desc limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {
                ps15.setString(1, "%" + TCari.getText().trim() + "%");
                ps15.setString(2, "%" + TCari.getText().trim() + "%");
                ps15.setString(3, "%" + TCari.getText().trim() + "%");
                ps15.setString(4, "%" + TCari.getText().trim() + "%");
                rs15 = ps15.executeQuery();
                while (rs15.next()) {
                    tabMode10.addRow(new Object[]{
                        rs15.getString("no_rawat"),
                        rs15.getString("no_rkm_medis"),
                        rs15.getString("nm_pasien"),                        
                        rs15.getString("prognosis")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs15 != null) {
                    rs15.close();
                }
                if (ps15 != null) {
                    ps15.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode10.getRowCount());
    }
    
    private void tampilRencanaTritmen() {
        Valid.tabelKosong(tabMode12);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps16 = koneksi.prepareStatement("SELECT pr.no_rawat, p.no_rkm_medis, p.nm_pasien, pr.tritmen, pr.pertemuan_selanjutnya, "
                        + "pr.cttn_tambahan, pr.waktu_simpan FROM psikolog_rencana_tritmen pr "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pr.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN psikolog_data_diri_klien pd on pd.no_rawat=pr.no_rawat WHERE "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.rekam_psikologis='perkawinan' and pr.no_rawat like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.rekam_psikologis='perkawinan' and p.no_rkm_medis like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.rekam_psikologis='perkawinan' and p.nm_pasien like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.rekam_psikologis='perkawinan' and pr.tritmen like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.rekam_psikologis='perkawinan' and pr.pertemuan_selanjutnya like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.rekam_psikologis='perkawinan' and pr.cttn_tambahan like ? "
                        + "order by pr.no_rawat desc");
            } else {
                ps16 = koneksi.prepareStatement("SELECT pr.no_rawat, p.no_rkm_medis, p.nm_pasien, pr.tritmen, pr.pertemuan_selanjutnya, "
                        + "pr.cttn_tambahan, pr.waktu_simpan FROM psikolog_rencana_tritmen pr "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pr.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN psikolog_data_diri_klien pd on pd.no_rawat=pr.no_rawat WHERE "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.rekam_psikologis='perkawinan' and pr.no_rawat like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.rekam_psikologis='perkawinan' and p.no_rkm_medis like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.rekam_psikologis='perkawinan' and p.nm_pasien like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.rekam_psikologis='perkawinan' and pr.tritmen like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.rekam_psikologis='perkawinan' and pr.pertemuan_selanjutnya like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pd.rekam_psikologis='perkawinan' and pr.cttn_tambahan like ? "
                        + "order by pr.no_rawat desc limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {
                ps16.setString(1, "%" + TCari.getText().trim() + "%");
                ps16.setString(2, "%" + TCari.getText().trim() + "%");
                ps16.setString(3, "%" + TCari.getText().trim() + "%");
                ps16.setString(4, "%" + TCari.getText().trim() + "%");
                ps16.setString(5, "%" + TCari.getText().trim() + "%");
                ps16.setString(6, "%" + TCari.getText().trim() + "%");
                rs16 = ps16.executeQuery();
                while (rs16.next()) {
                    tabMode12.addRow(new Object[]{
                        rs16.getString("no_rawat"),
                        rs16.getString("no_rkm_medis"),
                        rs16.getString("nm_pasien"),                        
                        rs16.getString("tritmen"),
                        rs16.getString("pertemuan_selanjutnya"),
                        rs16.getString("cttn_tambahan"),
                        rs16.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs16 != null) {
                    rs16.close();
                }
                if (ps16 != null) {
                    ps16.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode12.getRowCount());
    }
    
    public void emptText() {
        TNoRW.setText("");
        TNoRM.setText("");
        TPasien.setText("");
        tgl_datang.setText("");
        Tjk.setText("");
        emptTextDD();
        emptTextIP();
        emptTextIA();
        emptTextKP();
        emptTextH();
        emptTextPDP();
        emptTextPPK();
        emptTextTP();
        emptTextMF();
        emptTextD();
        emptTextP();
    }
    
    private void emptTextDD() {
        Ttl.setText("");
        TAgama.setText("");
        TSuku.setText("");
        TPekerjaan.setText("");
        TPendidikan.setText("");
        TSttsKawin.setText("");
        THobi.setText("");
        TAlamat.setText("");
        TnoTelp.setText("");
    }
    
    private void emptTextIP() {
        cmbPasangan.setSelectedIndex(0);
        cmbPasangan.requestFocus();
        TnamaPas.setText("");        
        TumurPas.setText("");
        cmbPendidikan.setSelectedIndex(0);
        TpekerPas.setText("");
        cmbagama.setSelectedIndex(0);
        Tanakke.setText("");
        Tdarike.setText("");
        Talamatpasangan.setText("");
        TnohpPas.setText("");
        TPernikahan.setText("");
        TtmpMenikah.setText("");
        tgl_nikah.setDate(new Date());
        TlmNikah.setText("");
        TlmKenal.setText("");
        TlmPacaran.setText("");
    }
    
    private void emptTextIA() {
        TanakKe.setText("");
        TanakKe.requestFocus();
        cmbJK.setSelectedIndex(0);        
        cmbKetAnak.setSelectedIndex(0);        
        TnmAnak.setText("");
        TumurAnak.setText("");
        cmbidumur.setSelectedIndex(0);
        TpekerjaanSek.setText("");
    }
    
    private void emptTextKP() {
        TpermasalahanNikah.setText("");
        TpermasalahanNikah.requestFocus();
        TsudahBerapa.setText("");
        TalasanBantuan.setText("");
        RSangatSeriusKP.setSelected(false);
        RSeriusKP.setSelected(false);
        RKurangSeriusKP.setSelected(false);
        Tkarena.setText("");
    }   
    
    private void emptTextH() {
        Tharapan.setText("");
        Tharapan.requestFocus();
        TperubahanSendiri.setText("");
        TperubahanPasangan.setText("");
        Tkemungkinan.setText("");
    }
    
    private void emptTextPDP() {
        cmbPasanganKL.setSelectedIndex(0);
        cmbPasanganKL.requestFocus();
        cmbPenilaian.setSelectedIndex(0);
        TdeskripsiNilai.setText("");
        wktSimpan = "";
        nomorR = "";
    }

    private void emptTextPPK() {
        Twawancara.setText("");
        Twawancara.requestFocus();
        Tobservasi.setText("");
    }
    
    private void emptTextTP() {
        tgl_rencana.setDate(new Date());
        tgl_rencana.requestFocus();
        TTesPsiko.setText("");
        TWaktu.setText("");
        TTester.setText("");
    }
    
    private void emptTextMF() {
        TManifes.setText("");
        TManifes.requestFocus();
    }
    
    private void emptTextD() {
        TKesan.setText("");
        TKesan.requestFocus();
        TDiagUtama.setText("");
        TDiagBanding.setText("");
    }
    
    private void emptTextP() {
        Tprognosis.setText("Faktor Penghambat : \n\nFaktor Pendukung : \n");
        Tprognosis.requestFocus();
    }
    
    private void emptTextRT() {
        Valid.tabelKosong(tabMode11);
        BtnPilihTritmen.requestFocus();
        wktSimpan = "";
        TPertemuan.setText("");
        TCatatan.setText("");
    }

    private void getDataDD() {
        tglKedatangan = "";
        if (tbdatadiri.getSelectedRow() != -1) {            
            TNoRW.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 0).toString());
            tglKedatangan = tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 1).toString();
            TNoRM.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 2).toString());            
            tgl_datang.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 3).toString());
            TPasien.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 4).toString());
            Tjk.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 5).toString());
            Ttl.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 6).toString());
            TAgama.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 7).toString());
            TSuku.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 8).toString());
            TPendidikan.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 9).toString());
            TPekerjaan.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 10).toString());            
            TSttsKawin.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 11).toString());
            THobi.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 12).toString());            
            TAlamat.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 13).toString());
            TnoTelp.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 14).toString());
        }
    }
    
    private void getDataIP() {
        wktSimpan = "";
        if (tbIdentitasPas.getSelectedRow() != -1) { 
            cmbPasangan.setSelectedItem(tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 2).toString());
            TnamaPas.setText(tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 3).toString());
            TumurPas.setText(tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 16).toString());
            cmbPendidikan.setSelectedItem(tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 5).toString());
            TpekerPas.setText(tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 6).toString());
            cmbagama.setSelectedItem(tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 7).toString());
            Tanakke.setText(tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 17).toString());
            Tdarike.setText(tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 18).toString());
            Talamatpasangan.setText(tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 9).toString());
            TnohpPas.setText(tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 10).toString());
            TPernikahan.setText(tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 11).toString());
            TtmpMenikah.setText(tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 19).toString());
            Valid.SetTgl(tgl_nikah, tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 20).toString());
            TlmNikah.setText(tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 13).toString());
            TlmKenal.setText(tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 14).toString());
            TlmPacaran.setText(tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 15).toString());            
            wktSimpan = tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 21).toString();            
        }
    }
    
    private void getDataIA() {
        wktSimpan = "";
        if (tbIdentitasAnak.getSelectedRow() != -1) {            
            TanakKe.setText(tbIdentitasAnak.getValueAt(tbIdentitasAnak.getSelectedRow(), 2).toString());
            TnmAnak.setText(tbIdentitasAnak.getValueAt(tbIdentitasAnak.getSelectedRow(), 3).toString());
            cmbJK.setSelectedItem(tbIdentitasAnak.getValueAt(tbIdentitasAnak.getSelectedRow(), 4).toString());
            TumurAnak.setText(tbIdentitasAnak.getValueAt(tbIdentitasAnak.getSelectedRow(), 8).toString());
            cmbidumur.setSelectedItem(tbIdentitasAnak.getValueAt(tbIdentitasAnak.getSelectedRow(), 9).toString());
            cmbKetAnak.setSelectedItem(tbIdentitasAnak.getValueAt(tbIdentitasAnak.getSelectedRow(), 6).toString());
            TpekerjaanSek.setText(tbIdentitasAnak.getValueAt(tbIdentitasAnak.getSelectedRow(), 7).toString());
            wktSimpan = tbIdentitasAnak.getValueAt(tbIdentitasAnak.getSelectedRow(), 10).toString();
        }
    }
    
    private void getDataKeluhan() {
        nomorR = "";
        dataMasalah = "";
        if (tbKeluhan.getSelectedRow() != -1) {            
            nomorR = tbKeluhan.getValueAt(tbKeluhan.getSelectedRow(), 0).toString();
            TpermasalahanNikah.setText(tbKeluhan.getValueAt(tbKeluhan.getSelectedRow(), 3).toString());
            TsudahBerapa.setText(tbKeluhan.getValueAt(tbKeluhan.getSelectedRow(), 4).toString());
            TalasanBantuan.setText(tbKeluhan.getValueAt(tbKeluhan.getSelectedRow(), 5).toString());
            dataMasalah = tbKeluhan.getValueAt(tbKeluhan.getSelectedRow(), 6).toString();
            Tkarena.setText(tbKeluhan.getValueAt(tbKeluhan.getSelectedRow(), 7).toString());
            
            if (dataMasalah.equals("Sangat serius")) {
                RSangatSeriusKP.setSelected(true);
                RSeriusKP.setSelected(false);
                RKurangSeriusKP.setSelected(false);
            } else if (dataMasalah.equals("Serius")) {
                RSangatSeriusKP.setSelected(false);
                RSeriusKP.setSelected(true);
                RKurangSeriusKP.setSelected(false);
            } else if (dataMasalah.equals("Kurang serius")) {
                RSangatSeriusKP.setSelected(false);
                RSeriusKP.setSelected(false);
                RKurangSeriusKP.setSelected(true);
            }
        }
    }
    
    private void getDataHarapan() {
        nomorR = "";
        if (tbHarapan.getSelectedRow() != -1) {            
            nomorR = tbHarapan.getValueAt(tbHarapan.getSelectedRow(), 0).toString();
            Tharapan.setText(tbHarapan.getValueAt(tbHarapan.getSelectedRow(), 3).toString());
            TperubahanSendiri.setText(tbHarapan.getValueAt(tbHarapan.getSelectedRow(), 4).toString());
            TperubahanPasangan.setText(tbHarapan.getValueAt(tbHarapan.getSelectedRow(), 5).toString());
            Tkemungkinan.setText(tbHarapan.getValueAt(tbHarapan.getSelectedRow(), 6).toString());
        }
    }
    
    private void getDataSL() {
        wktSimpan = "";
        nomorR = "";
        if (tblebihSuami.getSelectedRow() != -1) {   
            nomorR = tblebihSuami.getValueAt(tblebihSuami.getSelectedRow(), 0).toString();            
            TdeskripsiNilai.setText(tblebihSuami.getValueAt(tblebihSuami.getSelectedRow(), 2).toString());
            wktSimpan = tblebihSuami.getValueAt(tblebihSuami.getSelectedRow(), 3).toString();
            cmbPasanganKL.setSelectedItem(tblebihSuami.getValueAt(tblebihSuami.getSelectedRow(), 4).toString());
            cmbPenilaian.setSelectedItem(tblebihSuami.getValueAt(tblebihSuami.getSelectedRow(), 5).toString());
        }
    }
    
    private void getDataSK() {
        wktSimpan = "";
        nomorR = "";
        if (tbkurangSuami.getSelectedRow() != -1) {   
            nomorR = tbkurangSuami.getValueAt(tbkurangSuami.getSelectedRow(), 0).toString();            
            TdeskripsiNilai.setText(tbkurangSuami.getValueAt(tbkurangSuami.getSelectedRow(), 2).toString());
            wktSimpan = tbkurangSuami.getValueAt(tbkurangSuami.getSelectedRow(), 3).toString();
            cmbPasanganKL.setSelectedItem(tbkurangSuami.getValueAt(tbkurangSuami.getSelectedRow(), 4).toString());
            cmbPenilaian.setSelectedItem(tbkurangSuami.getValueAt(tbkurangSuami.getSelectedRow(), 5).toString());
        }
    }
    
    private void getDataIL() {
        wktSimpan = "";
        nomorR = "";
        if (tblebihIstri.getSelectedRow() != -1) {   
            nomorR = tblebihIstri.getValueAt(tblebihIstri.getSelectedRow(), 0).toString();            
            TdeskripsiNilai.setText(tblebihIstri.getValueAt(tblebihIstri.getSelectedRow(), 2).toString());
            wktSimpan = tblebihIstri.getValueAt(tblebihIstri.getSelectedRow(), 3).toString();
            cmbPasanganKL.setSelectedItem(tblebihIstri.getValueAt(tblebihIstri.getSelectedRow(), 4).toString());
            cmbPenilaian.setSelectedItem(tblebihIstri.getValueAt(tblebihIstri.getSelectedRow(), 5).toString());
        }
    }
    
    private void getDataIK() {
        wktSimpan = "";
        nomorR = "";
        if (tbkurangIstri.getSelectedRow() != -1) {   
            nomorR = tbkurangIstri.getValueAt(tbkurangIstri.getSelectedRow(), 0).toString();            
            TdeskripsiNilai.setText(tbkurangIstri.getValueAt(tbkurangIstri.getSelectedRow(), 2).toString());
            wktSimpan = tbkurangIstri.getValueAt(tbkurangIstri.getSelectedRow(), 3).toString();
            cmbPasanganKL.setSelectedItem(tbkurangIstri.getValueAt(tbkurangIstri.getSelectedRow(), 4).toString());
            cmbPenilaian.setSelectedItem(tbkurangIstri.getValueAt(tbkurangIstri.getSelectedRow(), 5).toString());
        }
    }
    
    private void getDataPPK() {
        nomorR = "";
        if (tbPemeriksaan.getSelectedRow() != -1) {            
            nomorR = tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 0).toString();
            Twawancara.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 3).toString());
            Tobservasi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 4).toString());            
        }
    }
    
    private void getDataTesPsiko() {
        nomorR = "";
        if (tbTesPsiko.getSelectedRow() != -1) {            
            nomorR = tbTesPsiko.getValueAt(tbTesPsiko.getSelectedRow(), 0).toString();            
            TTesPsiko.setText(tbTesPsiko.getValueAt(tbTesPsiko.getSelectedRow(), 4).toString());
            TWaktu.setText(tbTesPsiko.getValueAt(tbTesPsiko.getSelectedRow(), 5).toString());
            TTester.setText(tbTesPsiko.getValueAt(tbTesPsiko.getSelectedRow(), 6).toString());
            Valid.SetTgl(tgl_rencana, tbTesPsiko.getValueAt(tbTesPsiko.getSelectedRow(), 7).toString());
        }
    }
    
    private void getDataManifes() {
        nomorR = "";
        if (tbManifestasi.getSelectedRow() != -1) {            
            nomorR = tbManifestasi.getValueAt(tbManifestasi.getSelectedRow(), 0).toString();            
            TManifes.setText(tbManifestasi.getValueAt(tbManifestasi.getSelectedRow(), 3).toString());
        }
    }
    
    private void getDataDiagnosis() {
        nomorR = "";
        if (tbDiagnosis.getSelectedRow() != -1) {            
            nomorR = tbDiagnosis.getValueAt(tbDiagnosis.getSelectedRow(), 0).toString();            
            TKesan.setText(tbDiagnosis.getValueAt(tbDiagnosis.getSelectedRow(), 3).toString());
            TDiagUtama.setText(tbDiagnosis.getValueAt(tbDiagnosis.getSelectedRow(), 4).toString());
            TDiagBanding.setText(tbDiagnosis.getValueAt(tbDiagnosis.getSelectedRow(), 5).toString());
        }
    }
    
    private void getDataPrognosis() {
        nomorR = "";
        if (tbPrognosis.getSelectedRow() != -1) {            
            nomorR = tbPrognosis.getValueAt(tbPrognosis.getSelectedRow(), 0).toString();            
            Tprognosis.setText(tbPrognosis.getValueAt(tbPrognosis.getSelectedRow(), 3).toString());
        }
    }
    
    private void getDataTritmen() {
        wktSimpan = "";
        nomorR = "";
        if (tbTritmen.getSelectedRow() != -1) {
            nomorR = tbTritmen.getValueAt(tbTritmen.getSelectedRow(), 0).toString();
            TPertemuan.setText(tbTritmen.getValueAt(tbTritmen.getSelectedRow(), 4).toString());
            TCatatan.setText(tbTritmen.getValueAt(tbTritmen.getSelectedRow(), 5).toString());
            wktSimpan = tbTritmen.getValueAt(tbTritmen.getSelectedRow(), 6).toString();
            tampilItemTritmen();
        }
    }

    private void simpanDD() {
        if (Ttl.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Data diri pasien/klien masih belum lengkap...!!!!");
        } else if (THobi.getText().equals("")) {
            Valid.textKosong(THobi, "Hobi/Kegemaran");
            THobi.requestFocus();
        } else if (TnoTelp.getText().equals("")) {
            Valid.textKosong(TnoTelp, "No. HP (kontak yang bisa dihubungi)");
            TnoTelp.requestFocus();
        } else {
            Sequel.menyimpan("psikolog_data_diri_klien", "'" + TNoRW.getText() + "',"
                    + "'" + tglKedatangan + "','-','" + THobi.getText() + "','" + TnoTelp.getText() + "','perkawinan'", "Data Diri Pasien/Klien");
            tampilDataDiri();
            emptTextDD();
        }
    }
    
    private void simpanIP() {
        if (TnamaPas.getText().trim().equals("")) {
            Valid.textKosong(TnamaPas, "Nama pasangan");
            TnamaPas.requestFocus();
        } else if (TumurPas.getText().trim().equals("")) {
            Valid.textKosong(TumurPas, "Umur pasangan");
            TumurPas.requestFocus();
        } else if (Tanakke.getText().trim().equals("")) {
            Valid.textKosong(Tanakke, "Anak Ke");
            Tanakke.requestFocus();
        } else if (Tdarike.getText().trim().equals("")) {
            Valid.textKosong(Tdarike, "Dari bersaudara");
            Tdarike.requestFocus();
        } else {
            Sequel.menyimpan("psikolog_perkawinan_identitas_pas",
                    "'" + TNoRM.getText() + "','" + cmbPasangan.getSelectedItem().toString() + "','" + TnamaPas.getText() + "',"
                    + "'" + TumurPas.getText() + "','" + cmbPendidikan.getSelectedItem().toString() + "','" + TpekerPas.getText() + "',"
                    + "'" + cmbagama.getSelectedItem().toString() + "','" + Tanakke.getText() + "','" + Tdarike.getText() + "',"
                    + "'" + Talamatpasangan.getText() + "','" + TnohpPas.getText() + "','" + TPernikahan.getText() + "',"
                    + "'" + TtmpMenikah.getText() + "','" + Valid.SetTgl(tgl_nikah.getSelectedItem() + "") + "','" + TlmNikah.getText() + "',"
                    + "'" + TlmKenal.getText() + "','" + TlmPacaran.getText() + "','" + Sequel.cariIsi("select now()") + "'", "Identitas Pasangan Pasien/Klien");
            tampilIdentitasPasangan();
            emptTextIP();
        }
    }
    
    private void simpanIA() {
        if (TanakKe.getText().trim().equals("")) {
            Valid.textKosong(TanakKe, "Anak Ke");
            TanakKe.requestFocus();
        } else if (TnmAnak.getText().trim().equals("")) {
            Valid.textKosong(TnmAnak, "Nama Anak");
            TnmAnak.requestFocus();
        } else if (TumurAnak.getText().trim().equals("")) {
            Valid.textKosong(TumurAnak, "Umur Anak");
            TumurAnak.requestFocus();
        } else if (TpekerjaanSek.getText().trim().equals("")) {
            Valid.textKosong(TpekerjaanSek, "Pekerjaan/Sekolah");
            TpekerjaanSek.requestFocus();
        } else {
            Sequel.menyimpan("psikolog_perkawinan_identitas_anak", 
                    "'" + TNoRM.getText() + "','" + TanakKe.getText() + "','" + TnmAnak.getText() + "',"
                    + "'" + cmbJK.getSelectedItem().toString() + "','" + TumurAnak.getText() + "','" + cmbidumur.getSelectedItem().toString() + "',"
                    + "'" + cmbKetAnak.getSelectedItem().toString() + "','" + TpekerjaanSek.getText() + "','" + Sequel.cariIsi("select now()") + "'", "Identitas Anak Pasien/Klien");
            tampilIdentitasAnak();
            emptTextIA();
        }
    }
    
    private void simpanKP() {
        if (TpermasalahanNikah.getText().equals("")) {
            Valid.textKosong(TpermasalahanNikah, "Permasalahan dalam Pernikahan");
            TpermasalahanNikah.requestFocus();
        } else if (TsudahBerapa.getText().equals("")) {
            Valid.textKosong(TsudahBerapa, "Sudah Berapa Lama Permasalahan Ini Terjadi");
            TsudahBerapa.requestFocus();
        } else if (TalasanBantuan.getText().equals("")) {
            Valid.textKosong(TalasanBantuan, "Alasan Yang Membuat Mencari Bantuan Kali ini");
            TalasanBantuan.requestFocus();
        } else if (Tkarena.getText().equals("")) {
            Valid.textKosong(Tkarena, "Karena");
            Tkarena.requestFocus();
        } else {
            dataMasalah = "";
            if (RSangatSeriusKP.isSelected() == true) {
                dataMasalah = "Sangat serius";
            } else if (RSeriusKP.isSelected() == true) {
                dataMasalah = "Serius";
            } else if (RKurangSeriusKP.isSelected() == true) {
                dataMasalah = "Kurang serius";
            }
            
            Sequel.menyimpan("psikolog_perkawinan_keluhan",
                    "'" + TNoRW.getText() + "','" + TpermasalahanNikah.getText() + "','" + TsudahBerapa.getText() + "',"
                    + "'" + TalasanBantuan.getText() + "','" + dataMasalah + "','" + Tkarena.getText() + "'", "Keluhan/Permasalahan Pasien/Klien");
            tampilKeluhanMasalah();
            emptTextKP();
        }
    }
    
    private void simpanH() {
        if (Tharapan.getText().equals("")) {
            Valid.textKosong(Tharapan, "Harapan terhadap Pernikahan");
            Tharapan.requestFocus();
        } else if (TperubahanSendiri.getText().equals("")) {
            Valid.textKosong(TperubahanSendiri, "Perubahan yang diinginkan dari diri sendiri");
            TperubahanSendiri.requestFocus();
        } else if (TperubahanPasangan.getText().equals("")) {
            Valid.textKosong(TperubahanPasangan, "Perubahan yang diinginkan dari diri pasangan");
            TperubahanPasangan.requestFocus();
        } else if (Tkemungkinan.getText().equals("")) {
            Valid.textKosong(Tkemungkinan, "Kemungkinan perbaikan pernikahan");
            Tkemungkinan.requestFocus();
        } else {
            Sequel.menyimpan("psikolog_wawancara_klinis", 
                    "'" + TNoRW.getText() + "','Perkawinan','" + Tkemungkinan.getText() + "','-','-','-',"
                    + "'" + Tharapan.getText() + "','-','" + TperubahanSendiri.getText() + "',"
                    + "'" + TperubahanPasangan.getText() + "'", "Harapan Pasien/Klien");
            tampilHarapan();
            emptTextH();
        }
    }
    
    private void simpanPDP() {
        if (cmbPenilaian.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu jenis penilaian dengan benar...!!!!");
            cmbPenilaian.requestFocus();
        } else if (cmbPenilaian.getSelectedIndex() != 0 && TdeskripsiNilai.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan isi dulu deskripsi penilaian pasangan dengan benar...!!!!");
            TdeskripsiNilai.requestFocus();
        } else {
            Sequel.menyimpan("psikolog_perkawinan_pemahaman_pas",
                    "'" + TNoRW.getText() + "','" + cmbPasanganKL.getSelectedItem().toString() + "',"
                    + "'" + cmbPenilaian.getSelectedItem().toString() + "','" + TdeskripsiNilai.getText() + "',"
                    + "'" + Sequel.cariIsi("select now()") + "'", "Data Penilaian Kelebihan Suami Pasien/Klien");

            //kelebihan suami
            if (cmbPasanganKL.getSelectedIndex() == 0 && cmbPenilaian.getSelectedIndex() == 1) {
                tampilLebihSuami();

                //kekurangan suami
            } else if (cmbPasanganKL.getSelectedIndex() == 0 && cmbPenilaian.getSelectedIndex() == 2) {
                tampilKurangSuami();

                //kelebihan istri
            } else if (cmbPasanganKL.getSelectedIndex() == 1 && cmbPenilaian.getSelectedIndex() == 1) {
                tampilLebihIstri();

                //kekurangan istri
            } else if (cmbPasanganKL.getSelectedIndex() == 1 && cmbPenilaian.getSelectedIndex() == 2) {
                tampilKurangIstri();
            }

            cmbPasanganKL.setSelectedIndex(0);
            cmbPasanganKL.requestFocus();
            cmbPenilaian.setSelectedIndex(0);
            TdeskripsiNilai.setText("");
            wktSimpan = "";
            nomorR = "";
        }
    }
    
    private void simpanPPK() {
        if (Twawancara.getText().equals("")) {
            Valid.textKosong(Twawancara, "Wawancara");
            Twawancara.requestFocus();
        } else if (Tobservasi.getText().equals("")) {
            Valid.textKosong(Twawancara, "Observasi");
            Tobservasi.requestFocus();
        } else {
            Sequel.menyimpan("psikolog_perkawinan_pemeriksaan_klinis",
                    "'" + TNoRW.getText() + "', '" + Twawancara.getText() + "',"
                    + "'" + Tobservasi.getText() + "'", "Pemeriksaan Psikologi Klinis Pasien/Klien");
            tampilPemeriksaanKlinis();
            emptTextPPK();
        }
    }
    
    private void simpanTP() {
        if (TTesPsiko.getText().equals("")) {
            Valid.textKosong(TTesPsiko, "Tes Psikologi");
            TTesPsiko.requestFocus();
        } else if (TWaktu.getText().equals("")) {
            Valid.textKosong(TWaktu, "Waktu");
            TWaktu.requestFocus();
        } else if (TTester.getText().equals("")) {
            Valid.textKosong(TWaktu, "Tester");
            TTester.requestFocus();
        } else {
            Sequel.menyimpan("psikolog_tes_psikologi",
                    "'" + TNoRW.getText() + "','perkawinan','" + Valid.SetTgl(tgl_rencana.getSelectedItem() + "") + "',"
                    + "'" + TTesPsiko.getText() + "','" + TWaktu.getText() + "','" + TTester.getText() + "'", "Tes Psikologi Pasien/Klien");
            tampilTesPsikologi();
            emptTextTP();
        }
    }
    
    private void simpanMF() {
        Sequel.menyimpan("psikolog_manifestasi_fungsi",
                "'" + TNoRW.getText() + "','" + TManifes.getText() + "'", "Manifestasi Fungsi Psikologis Pasien/Klien");
        tampilManifestasiFungsi();
        emptTextMF();
    }
    
    private void simpanD() {
        if (TDiagUtama.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika diagnosis utama (dx) kosong, isi dengan strip (-)...!!!!");
            TDiagUtama.requestFocus();
        } else if (TDiagBanding.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika diagnosis banding (dd) kosong, isi dengan strip (-)...!!!!");
            TDiagBanding.requestFocus();
        } else {
            Sequel.menyimpan("psikolog_diagnosis",
                    "'" + TNoRW.getText() + "','" + TKesan.getText() + "','" + TDiagUtama.getText() + "',"
                    + "'" + TDiagBanding.getText() + "'", "Diagnosis (berdasarkan DSM / ICD / PPDGJ) Pasien/Klien");
            tampilDiagnosis();
            emptTextD();
        }
    }
    
    private void simpanP() {
        Sequel.menyimpan("psikolog_prognosis",
                "'" + TNoRW.getText() + "','" + Tprognosis.getText() + "'", "Prognosis Pasien/Klien");
        tampilPrognosis();
        emptTextP();
    }
    
    private void simpanRT() {
        if (tabMode11.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item rencana tritmen masih kosong...!!!!");
            BtnPilihTritmen.requestFocus();        
        } else if (TPertemuan.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika Pertemuan Selanjutnya kosong, isi dengan strip (-)...!!!!");
            TPertemuan.requestFocus();
        } else if (TCatatan.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika Catatan Tambahan kosong, isi dengan strip (-)...!!!!");
            TCatatan.requestFocus();
        } else {
            dataTritmen = "";
            for (i = 0; i < tbItemTritmen.getRowCount(); i++) {
                if (dataTritmen.equals("")) {
                    dataTritmen = tbItemTritmen.getValueAt(i, 1).toString() + " " + tbItemTritmen.getValueAt(i, 2).toString();
                } else {
                    dataTritmen = dataTritmen + " , " + tbItemTritmen.getValueAt(i, 1).toString() + " " + tbItemTritmen.getValueAt(i, 2).toString();
                }
            }

            Sequel.menyimpan("psikolog_rencana_tritmen", "'" + TNoRW.getText() + "','" + dataTritmen + "',"
                    + "'" + TPertemuan.getText() + "','" + TCatatan.getText() + "','" + Sequel.cariIsi("SELECT NOW()") + "'", "Rencana Tritmen");

            for (i = 0; i < tbItemTritmen.getRowCount(); i++) {
                Sequel.menyimpan("psikolog_detail_rencana_tritmen", "'" + TNoRW.getText() + "',"
                        + "'" + Sequel.cariIsi("SELECT NOW()") + "',"
                        + "'" + tbItemTritmen.getValueAt(i, 0).toString() + "',"
                        + "'" + tbItemTritmen.getValueAt(i, 2).toString() + "'", "Detail Rencana Tritmen");
            }
            
            tampilRencanaTritmen();
            emptTextRT();
        }
    }
    
    private void hapusDD() {
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else {
            Sequel.queryu("delete from psikolog_data_diri_klien where no_rawat='" + TNoRW.getText() + "'");
            tampilDataDiri();
            emptTextDD();
            tglKedatangan = "";
        }
    }
    
    private void hapusIP() {
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data identitas pasangan pada tabel...!!!!");
            tbIdentitasPas.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_perkawinan_identitas_pas where waktu_simpan='" + wktSimpan + "'");
            tampilIdentitasPasangan();
            emptTextIP();
            wktSimpan = "";
        }
    }
    
    private void hapusIA() {
        if (tabMode3.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data identitas anak pada tabel...!!!!");
            tbIdentitasAnak.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_perkawinan_identitas_anak where waktu_simpan='" + wktSimpan + "'");
            tampilIdentitasAnak();
            emptTextIA();
            wktSimpan = "";
        }
    }
    
    private void hapusKP() {
        if (tabMode4.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (nomorR.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data keluhan/permasalahan pada tabel...!!!!");
            tbKeluhan.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_perkawinan_keluhan where no_rawat='" + nomorR + "'");            
            tampilKeluhanMasalah();
            emptTextKP();
        }
    }
    
    private void hapusH() {
        if (tabMode5.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (nomorR.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data harapan pada tabel...!!!!");
            tbHarapan.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_wawancara_klinis where no_rawat='" + nomorR + "'");            
            tampilHarapan();
            emptTextH();
            wktSimpan = "";
        }
    }
    
    private void hapusPDP() {
        if (tabModeSL.getRowCount() == 0 && tabModeSK.getRowCount() == 0 && tabModeIL.getRowCount() == 0 && tabModeIK.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (nomorR.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data pemahaman diri & pasangan pada tabel...!!!!");
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah data kelebihan & kekurangan suami/istri yakin semuanya akan dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                Sequel.queryu("delete from psikolog_perkawinan_pemahaman_pas where no_rawat='" + nomorR + "'");
            } else {
                JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data kelebihan & kekurangan dg. cara klik kanan pada tabel utk. menghapus...!!!!");
            }
            
            tampilLebihSuami();
            tampilKurangSuami();
            tampilLebihIstri();
            tampilKurangIstri();
            wktSimpan = "";
            nomorR = "";
        }
    }
    
    private void hapusPPK() {
        if (tabMode6.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (nomorR.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data pemeriksaan psikologi klinis pada tabel...!!!!");
            tbPemeriksaan.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_perkawinan_pemeriksaan_klinis where no_rawat='" + nomorR + "'");
            
            tampilPemeriksaanKlinis();
            emptTextPPK();
            wktSimpan = "";
        }
    }
    
    private void hapusTP() {
        if (tabMode7.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (nomorR.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data tes psikologi pasien pada tabel...!!!!");
            tbTesPsiko.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_tes_psikologi where no_rawat='" + nomorR + "'");
            
            tampilTesPsikologi();
            emptTextTP();
            wktSimpan = "";
        }
    }
    
    private void hapusMF() {
        if (tabMode8.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (nomorR.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data manifestasi fungsi psikologis pasien pada tabel...!!!!");
            tbManifestasi.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_manifestasi_fungsi where no_rawat='" + nomorR + "'");
            
            tampilManifestasiFungsi();
            emptTextMF();
            wktSimpan = "";
        }
    }
    
    private void hapusD() {
        if (tabMode9.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (nomorR.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data diagnosis pasien pada tabel...!!!!");
            tbDiagnosis.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_diagnosis where no_rawat='" + nomorR + "'");
            
            tampilDiagnosis();
            emptTextD();
            wktSimpan = "";
        }
    }
    
    private void hapusP() {
        if (tabMode10.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (nomorR.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data prognosis pasien pada tabel...!!!!");
            tbPrognosis.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_prognosis where no_rawat='" + nomorR + "'");
            
            tampilPrognosis();
            emptTextP();
            wktSimpan = "";
        }
    }
    
    private void hapusRT() {
        if (tabMode12.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (nomorR.equals("") && wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data rencana tritmen pada tabel...!!!!");
            tbTritmen.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_detail_rencana_tritmen "
                    + "where no_rawat='" + nomorR + "' and waktu_simpan='" + wktSimpan + "'");

            Sequel.queryu("delete from psikolog_rencana_tritmen "
                    + "where no_rawat='" + nomorR + "' and waktu_simpan='" + wktSimpan + "'");
            
            tampilRencanaTritmen();
            emptTextRT();
        }
    }
    
    private void gantiDD() {
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (tglKedatangan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel...!!!!");
            tbdatadiri.requestFocus();
        } else {
            Sequel.mengedit("psikolog_data_diri_klien", "no_rawat='" + TNoRW.getText() + "'", 
                    "tgl_kedatangan='" + tglKedatangan + "', hobi='" + THobi.getText() + "', no_hp_bisa_dihubungi='" + TnoTelp.getText() + "'");
            tampilDataDiri();
            emptTextDD();
        }
    }
    
    private void gantiIP() {
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data identitas pasangan pada tabel...!!!!");
            tbIdentitasPas.requestFocus();
        } else if (TnamaPas.getText().trim().equals("")) {
            Valid.textKosong(TnamaPas, "Nama pasangan");
            TnamaPas.requestFocus();
        } else if (TumurPas.getText().trim().equals("")) {
            Valid.textKosong(TumurPas, "Umur pasangan");
            TumurPas.requestFocus();
        } else if (Tanakke.getText().trim().equals("")) {
            Valid.textKosong(Tanakke, "Anak Ke");
            Tanakke.requestFocus();
        } else if (Tdarike.getText().trim().equals("")) {
            Valid.textKosong(Tdarike, "Dari bersaudara");
            Tdarike.requestFocus();
        } else {
            Sequel.mengedit("psikolog_perkawinan_identitas_pas", "waktu_simpan='" + wktSimpan + "'",
                    "no_rkm_medis='" + TNoRM.getText() + "',pasangan='" + cmbPasangan.getSelectedItem().toString() + "',nama='" + TnamaPas.getText() + "',"
                    + "umur='" + TumurPas.getText() + "',pendidikan='" + cmbPendidikan.getSelectedItem().toString() + "',"
                    + "pekerjaan='" + TpekerPas.getText() + "',agama='" + cmbagama.getSelectedItem().toString() + "',anak_ke='" + Tanakke.getText() + "',"
                    + "dari_bersaudara='" + Tdarike.getText() + "',alamat='" + Talamatpasangan.getText() + "',no_hp='" + TnohpPas.getText() + "',"
                    + "pernikahan_ke='" + TPernikahan.getText() + "',tmpt_menikah='" + TtmpMenikah.getText() + "',tgl_menikah='" + Valid.SetTgl(tgl_nikah.getSelectedItem() + "") + "',"
                    + "lama_menikah='" + TlmNikah.getText() + "',lama_kenal_sebelum_nikah='" + TlmKenal.getText() + "',lama_pacrn_sebelum_nikah='" + TlmPacaran.getText() + "'");
            tampilIdentitasPasangan();
            emptTextIP();
        }
    }
    
    private void gantiIA() {
        if (tabMode3.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data identitas anak pada tabel...!!!!");
            tbIdentitasAnak.requestFocus();
        } else if (TanakKe.getText().trim().equals("")) {
            Valid.textKosong(TanakKe, "Anak Ke");
            TanakKe.requestFocus();
        } else if (TnmAnak.getText().trim().equals("")) {
            Valid.textKosong(TnmAnak, "Nama Anak");
            TnmAnak.requestFocus();
        } else if (TumurAnak.getText().trim().equals("")) {
            Valid.textKosong(TumurAnak, "Umur Anak");
            TumurAnak.requestFocus();
        } else if (TpekerjaanSek.getText().trim().equals("")) {
            Valid.textKosong(TpekerjaanSek, "Pekerjaan/Sekolah");
            TpekerjaanSek.requestFocus();
        } else {
            Sequel.mengedit("psikolog_perkawinan_identitas_anak", "waktu_simpan='" + wktSimpan + "'",
                    "no_rkm_medis='" + TNoRM.getText() + "',anak_ke='" + TanakKe.getText() + "', nama='" + TnmAnak.getText() + "',"
                    + "jk='" + cmbJK.getSelectedItem().toString() + "', umur='" + TumurAnak.getText() + "',"
                    + "id_umur='" + cmbidumur.getSelectedItem().toString() + "', keterangan='" + cmbKetAnak.getSelectedItem().toString() + "',"
                    + "pekerjaan_sekolah='" + TpekerjaanSek.getText() + "'");
            tampilIdentitasAnak();
            emptTextIA();
        }
    }
    
    private void gantiKP() {
        if (tabMode4.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (nomorR.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data keluhan/permasalahan pada tabel...!!!!");
            tbKeluhan.requestFocus();
        } else if (TpermasalahanNikah.getText().equals("")) {
            Valid.textKosong(TpermasalahanNikah, "Permasalahan dalam Pernikahan");
            TpermasalahanNikah.requestFocus();
        } else if (TsudahBerapa.getText().equals("")) {
            Valid.textKosong(TsudahBerapa, "Sudah Berapa Lama Permasalahan Ini Terjadi");
            TsudahBerapa.requestFocus();
        } else if (TalasanBantuan.getText().equals("")) {
            Valid.textKosong(TalasanBantuan, "Alasan Yang Membuat Mencari Bantuan Kali ini");
            TalasanBantuan.requestFocus();
        } else if (Tkarena.getText().equals("")) {
            Valid.textKosong(Tkarena, "Karena");
            Tkarena.requestFocus();
        } else {
            dataMasalah = "";
            if (RSangatSeriusKP.isSelected() == true) {
                dataMasalah = "Sangat serius";
            } else if (RSeriusKP.isSelected() == true) {
                dataMasalah = "Serius";
            } else if (RKurangSeriusKP.isSelected() == true) {
                dataMasalah = "Kurang serius";
            }

            Sequel.mengedit("psikolog_perkawinan_keluhan", "no_rawat='" + nomorR + "'",
                    "masalah_dalam_nikah='" + TpermasalahanNikah.getText() + "',sudah_brp_lama='" + TsudahBerapa.getText() + "', "
                    + "alasan_bantuan='" + TalasanBantuan.getText() + "', permasalahan_dinilai='" + dataMasalah + "', "
                    + "karena='" + Tkarena.getText() + "'");
            tampilKeluhanMasalah();
            emptTextKP();
        }
    }
    
    private void gantiH() {
        if (tabMode5.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (nomorR.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data harapan pada tabel...!!!!");
            tbHarapan.requestFocus();
        } else if (Tharapan.getText().equals("")) {
            Valid.textKosong(Tharapan, "Harapan terhadap Pernikahan");
            Tharapan.requestFocus();
        } else if (TperubahanSendiri.getText().equals("")) {
            Valid.textKosong(TperubahanSendiri, "Perubahan yang diinginkan dari diri sendiri");
            TperubahanSendiri.requestFocus();
        } else if (TperubahanPasangan.getText().equals("")) {
            Valid.textKosong(TperubahanPasangan, "Perubahan yang diinginkan dari diri pasangan");
            TperubahanPasangan.requestFocus();
        } else if (Tkemungkinan.getText().equals("")) {
            Valid.textKosong(Tkemungkinan, "Kemungkinan perbaikan pernikahan");
            Tkemungkinan.requestFocus();
        } else {
            Sequel.mengedit("psikolog_wawancara_klinis", "no_rawat='" + nomorR + "'",
                    "permasalahan_saat_ini='" + Tkemungkinan.getText() + "', harapan='" + Tharapan.getText() + "',"
                    + "perubahan_diinginkan_diri_sendiri='" + TperubahanSendiri.getText() + "', "
                    + "perubahan_diinginkan_keluarga='" + TperubahanPasangan.getText() + "'");
            tampilHarapan();
            emptTextH();
        }
    }
    
    private void gantiPDP() {
        if (tabModeSL.getRowCount() == 0 && tabModeSK.getRowCount() == 0 && tabModeIL.getRowCount() == 0 && tabModeIK.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (nomorR.equals("") && wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data pemahaman diri & pasangan pada tabel...!!!!");
        } else if (cmbPenilaian.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu jenis penilaian dengan benar...!!!!");
            cmbPenilaian.requestFocus();
        } else if (cmbPenilaian.getSelectedIndex() != 0 && TdeskripsiNilai.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan isi dulu deskripsi penilaian pasangan dengan benar...!!!!");
            TdeskripsiNilai.requestFocus();
        } else {
            Sequel.mengedit("psikolog_perkawinan_pemahaman_pas", "waktu_simpan='" + wktSimpan + "'",
                    "jenis_pasangan='" + cmbPasanganKL.getSelectedItem().toString() + "', "
                    + "jenis_penilaian='" + cmbPenilaian.getSelectedItem().toString() + "',"
                    + "deskripsi_penilaian='" + TdeskripsiNilai.getText() + "'");

            //kelebihan suami
            if (cmbPasanganKL.getSelectedIndex() == 0 && cmbPenilaian.getSelectedIndex() == 1) {
                tampilLebihSuami();

                //kekurangan suami
            } else if (cmbPasanganKL.getSelectedIndex() == 0 && cmbPenilaian.getSelectedIndex() == 2) {
                tampilKurangSuami();

                //kelebihan istri
            } else if (cmbPasanganKL.getSelectedIndex() == 1 && cmbPenilaian.getSelectedIndex() == 1) {
                tampilLebihIstri();

                //kekurangan istri
            } else if (cmbPasanganKL.getSelectedIndex() == 1 && cmbPenilaian.getSelectedIndex() == 2) {
                tampilKurangIstri();
            }

            cmbPasanganKL.setSelectedIndex(0);
            cmbPasanganKL.requestFocus();
            cmbPenilaian.setSelectedIndex(0);
            TdeskripsiNilai.setText("");
            wktSimpan = "";
            nomorR = "";
        }
    }
    
    private void gantiPPK() {
        if (tabMode6.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (nomorR.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data pemeriksaan psikologi klinis pada tabel...!!!!");
            tbPemeriksaan.requestFocus();
        } else if (Twawancara.getText().equals("")) {
            Valid.textKosong(Twawancara, "Wawancara");
            Twawancara.requestFocus();
        } else if (Tobservasi.getText().equals("")) {
            Valid.textKosong(Twawancara, "Observasi");
            Tobservasi.requestFocus();
        } else {
            Sequel.mengedit("psikolog_perkawinan_pemeriksaan_klinis", "no_rawat='" + nomorR + "'",
                    "wawancara='" + Twawancara.getText() + "',observasi='" + Tobservasi.getText() + "'");
            tampilPemeriksaanKlinis();
            emptTextPPK();
        }
    }
    
    private void gantiTP() {
        if (tabMode7.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (nomorR.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data tes psikologi pasien pada tabel...!!!!");
            tbTesPsiko.requestFocus();
        } else {
            Sequel.mengedit("psikolog_tes_psikologi", "no_rawat='" + nomorR + "'",
                    "rencana_tes='" + Valid.SetTgl(tgl_rencana.getSelectedItem() + "") + "',"
                    + "tes_psikologis='" + TTesPsiko.getText() + "', "
                    + "waktu='" + TWaktu.getText() + "',tester='" + TTester.getText() + "'");
            tampilTesPsikologi();
            emptTextTP();
        }
    }
    
    private void gantiMF() {
        if (tabMode8.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (nomorR.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data manifestasi fungsi psikologis pasien pada tabel...!!!!");
            tbManifestasi.requestFocus();
        } else {
            Sequel.mengedit("psikolog_manifestasi_fungsi", "no_rawat='" + nomorR + "'", "manifestasi='" + TManifes.getText() + "'");
            tampilManifestasiFungsi();
            emptTextMF();
        }
    }
    
    private void gantiD() {
        if (tabMode9.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (nomorR.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data diagnosis pasien pada tabel...!!!!");
            tbDiagnosis.requestFocus();
        } else if (TDiagUtama.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika diagnosis utama (dx) kosong, isi dengan strip (-)...!!!!");
            TDiagUtama.requestFocus();
        } else if (TDiagBanding.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika diagnosis banding (dd) kosong, isi dengan strip (-)...!!!!");
            TDiagBanding.requestFocus();
        } else {
            Sequel.mengedit("psikolog_diagnosis", "no_rawat='" + nomorR + "'", 
                    "kesan_awal='" + TKesan.getText() + "',diagnosa_utama='" + TDiagUtama.getText() + "',diagnosa_banding='" + TDiagBanding.getText() + "'");
            tampilDiagnosis();
            emptTextD();
        }
    }
    
    private void gantiP() {
        if (tabMode10.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (nomorR.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data prognosis pasien pada tabel...!!!!");
            tbPrognosis.requestFocus();
        } else {
            Sequel.mengedit("psikolog_prognosis", "no_rawat='" + nomorR + "'", "prognosis='" + Tprognosis.getText() + "'");
            tampilPrognosis();
            emptTextP();
        }
    }
    
    private void gantiRT() {
        if (tabMode12.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (nomorR.equals("") && wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data rencana tritmen pada tabel...!!!!");
            tbTritmen.requestFocus();
        } else if (tabMode11.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, item rencana tritmen belum terisi...!!!!");
            BtnPilihTritmen.requestFocus();
        } else if (TPertemuan.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika Pertemuan Selanjutnya kosong, isi dengan strip (-)...!!!!");
            TPertemuan.requestFocus();
        } else if (TCatatan.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika Catatan Tambahan kosong, isi dengan strip (-)...!!!!");
            TCatatan.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_detail_rencana_tritmen "
                    + "where no_rawat='" + nomorR + "' and waktu_simpan='" + wktSimpan + "'");

            Sequel.queryu("delete from psikolog_rencana_tritmen "
                    + "where no_rawat='" + nomorR + "' and waktu_simpan='" + wktSimpan + "'");
            simpanRT();
        }
    }
    
    private void getItemTritmen() {
        wktSimpan = "";
        if (tbItemTritmen.getSelectedRow() != -1) {
            kdtritmen.setText(tbItemTritmen.getValueAt(tbItemTritmen.getSelectedRow(), 0).toString());
            nmtritmen.setText(tbItemTritmen.getValueAt(tbItemTritmen.getSelectedRow(), 1).toString());
            tritmenLain.setText(tbItemTritmen.getValueAt(tbItemTritmen.getSelectedRow(), 2).toString());
            wktSimpan = tbItemTritmen.getValueAt(tbItemTritmen.getSelectedRow(), 3).toString();
        }
    }
    
    private void tampilItemTritmen() {
        Valid.tabelKosong(tabMode11);
        try {
            ps17 = koneksi.prepareStatement("SELECT pd.kd_rencana, pm.deskripsi, pd.ket_lainnya, "
                    + "pd.waktu_simpan FROM psikolog_detail_rencana_tritmen pd "
                    + "INNER JOIN psikolog_master_rencana_tritmen pm ON pm.kd_rencana=pd.kd_rencana "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat=pd.no_rawat "
                    + "INNER JOIN psikolog_data_diri_klien pdd on pdd.no_rawat=pd.no_rawat "
                    + "WHERE pd.no_rawat LIKE '%" + nomorR + "%' and rp.umurdaftar>= 15 and rp.sttsumur='Th' and pdd.rekam_psikologis='perkawinan'");
            try {
                rs17 = ps17.executeQuery();
                while (rs17.next()) {
                    tabMode11.addRow(new Object[]{
                        rs17.getString("kd_rencana"),
                        rs17.getString("deskripsi"), 
                        rs17.getString("ket_lainnya"), 
                        rs17.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs17 != null) {
                    rs17.close();
                }
                if (ps17 != null) {
                    ps17.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilPreview() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));        
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                rsc1 = koneksi.prepareStatement("SELECT rp.no_rawat, rp.tgl_registrasi, p.no_rkm_medis, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_dtg, "
                        + "p.nm_pasien, if(p.jk = 'L','Laki-laki','Perempuan') jk, CONCAT(p.tmp_lahir,', ',DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y'),' / ',rp.umurdaftar,' tahun') ttl, "
                        + "p.agama, sb.nama_suku_bangsa, p.pekerjaan, p.pnd, p.stts_nikah, pd.hobi, CONCAT(p.alamat,', ', kl.nm_kel,', ', kc.nm_kec,', ',kb.nm_kab) almt, "
                        + "pd.no_hp_bisa_dihubungi FROM psikolog_data_diri_klien pd INNER JOIN reg_periksa rp on rp.no_rawat = pd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis = rp.no_rkm_medis INNER JOIN kelurahan kl on kl.kd_kel = p.kd_kel "
                        + "INNER JOIN kecamatan kc on kc.kd_kec = p.kd_kec INNER JOIN kabupaten kb on kb.kd_kab = p.kd_kab INNER JOIN suku_bangsa sb on sb.id=p.suku_bangsa WHERE "
                        + "p.no_rkm_medis='" + TNoRM.getText() + "' and rp.umurdaftar>= 15 and rp.sttsumur='Th' "
                        + "and rp.no_rawat ='" + TNoRW.getText() + "' and pd.rekam_psikologis='perkawinan'").executeQuery();
                
                while (rsc1.next()) {
                    //data diri klien
                    htmlContent.append(
                            "<table width='100%' class='adaborder'>"
                            + "<thead>"
                            + "  <tr>"
                            + "    <th colspan='2'><b>Identitas Diri Klien</b></th>"
                            + "  </tr>"
                            + "</thead>"
                            + "<tbody>"
                            + "  <tr>"
                            + "    <td valign='top' width='210px'>Nomor Registrasi Klien</td>"
                            + "    <td valign='top'>: " + rsc1.getString("no_rkm_medis") + "</td>"
                            + "  </tr>"
                            + "  <tr>"
                            + "    <td valign='top'>Tanggal Kedatangan</td>"
                            + "    <td valign='top'>: " + Sequel.cariIsi("select DATE_FORMAT('" + rsc1.getString("tgl_registrasi") + "','%d')") + " "
                            + Sequel.bulanINDONESIA("select DATE_FORMAT('" + rsc1.getString("tgl_registrasi") + "','%m')") + " "
                            + Sequel.cariIsi("select DATE_FORMAT('" + rsc1.getString("tgl_registrasi") + "','%Y')") + "</td>"
                            + "  </tr>"
                            + "  <tr>"
                            + "    <td valign='top'>Nama Klien</td>"
                            + "    <td valign='top'>: " + rsc1.getString("nm_pasien") + "</td>"
                            + "  </tr>"
                            + "  <tr>"
                            + "    <td valign='top'>Jenis Kelamin</td>"
                            + "    <td valign='top'>: " + rsc1.getString("jk") + "</td>"
                            + "  </tr>"
                            + "  <tr>"
                            + "    <td valign='top'>Tempat & Tanggal Lahir</td>"
                            + "    <td valign='top'>: " + rsc1.getString("ttl") + "</td>"
                            + "  </tr>"
                            + "  <tr>"
                            + "    <td valign='top'>Agama</td>"
                            + "    <td valign='top'>: " + rsc1.getString("agama") + "</td>"
                            + "  </tr>"
                            + "  <tr>"
                            + "    <td valign='top'>Suku</td>"
                            + "    <td valign='top'>: " + rsc1.getString("nama_suku_bangsa") + "</td>"
                            + "  </tr>"
                            + "  <tr>"
                            + "    <td valign='top'>Pendidikan</td>"
                            + "    <td valign='top'>: " + rsc1.getString("pnd") + "</td>"
                            + "  </tr>"
                            + "  <tr>"
                            + "    <td valign='top'>Pekerjaan</td>"
                            + "    <td valign='top'>: " + rsc1.getString("pekerjaan") + "</td>"
                            + "  </tr>"                            
                            + "  <tr>"
                            + "    <td valign='top'>Status Perkawinan</td>"
                            + "    <td valign='top'>: " + rsc1.getString("stts_nikah") + "</td>"
                            + "  </tr>"
                            + "  <tr>"
                            + "    <td valign='top'>Hobi / Kegemaran</td>"
                            + "    <td valign='top'>: " + rsc1.getString("hobi") + "</td>"
                            + "  </tr>"
                            + "  <tr>"
                            + "    <td valign='top'>Alamat Lengkap</td>"
                            + "    <td valign='top'>: " + rsc1.getString("almt") + "</td>"
                            + "  </tr>"
                            + "  <tr>"
                            + "    <td valign='top'>No. HP (Kontak yang bisa dihubungi)</td>"
                            + "    <td valign='top'>: " + rsc1.getString("no_hp_bisa_dihubungi") + "</td>"
                            + "  </tr>"
                            + "</tbody>"
                            + "</table>"
                            + "<br>"
                    );
                    
                    //identitas pasangan
                    try {
                        rsc2 = koneksi.prepareStatement("SELECT ifnull(pip.pasangan,'') pasangan, ifnull(pip.nama,'') nama, concat(ifnull(pip.umur,''),' Tahun') umurpas, "
                                + "ifnull(pip.pendidikan,'') pendidikan, ifnull(pip.pekerjaan,'') pekerjaan, ifnull(pip.agama,'') agama, "
                                + "concat(ifnull(pip.anak_ke,''),' dari ',ifnull(pip.dari_bersaudara,''),' bersaudara') anakke, ifnull(pip.alamat,'') alamat, "
                                + "ifnull(pip.no_hp,'') no_hp, ifnull(pip.pernikahan_ke,'') pernikahan_ke, concat(ifnull(pip.tmpt_menikah,''),', ',date_format(pip.tgl_menikah,'%d-%m-%Y')) ttm, "
                                + "ifnull(pip.lama_menikah,'') lama_menikah, ifnull(pip.lama_kenal_sebelum_nikah,'') lama_kenal_sebelum_nikah, "
                                + "ifnull(pip.lama_pacrn_sebelum_nikah,'') lama_pacrn_sebelum_nikah from psikolog_perkawinan_identitas_pas pip "
                                + "inner join reg_periksa rp on rp.no_rkm_medis = pip.no_rkm_medis inner join psikolog_data_diri_klien pd on pd.no_rawat = rp.no_rawat WHERE "
                                + "pip.no_rkm_medis ='" + TNoRM.getText() + "' and rp.no_rawat ='" + TNoRW.getText() + "' and rp.umurdaftar>= 15 and rp.sttsumur='Th' "
                                + "and pd.rekam_psikologis='perkawinan'").executeQuery();

                        if (rsc2.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th colspan='2'>Identitas Pasangan</th>"
                                    + "    <th>Pernikahan</th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "  <tr>"
                                    + "    <td valign='top' colspan='2' align='center'><b>" + rsc2.getString("pasangan") + "</b></td>"
                                    + "    <td valign='top'>Pernikahan ke : <br>" + rsc2.getString("pernikahan_ke") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top' width='100px'>Nama</td>"
                                    + "    <td valign='top'>: " + rsc2.getString("nama") + "</td>"
                                    + "    <td valign='top'>Tempat dan Tanggal Menikah : <br>" + rsc2.getString("ttm") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Umur</td>"
                                    + "    <td valign='top'>: " + rsc2.getString("umurpas") + "</td>"
                                    + "    <td valign='top'>Lama Pernikahan : <br>" + rsc2.getString("lama_menikah") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Pendidikan</td>"
                                    + "    <td valign='top'>: " + rsc2.getString("pendidikan") + "</td>"
                                    + "    <td valign='top'>Lama Berkenalan Sebelum Menikah : <br>" + rsc2.getString("lama_kenal_sebelum_nikah") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Pekerjaan</td>"
                                    + "    <td valign='top'>: " + rsc2.getString("pekerjaan") + "</td>"
                                    + "    <td valign='top'>Lama Berpacaran Sebelum Menikah : <br>" + rsc2.getString("lama_pacrn_sebelum_nikah") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Agama</td>"
                                    + "    <td>: " + rsc2.getString("agama") + "</td>"
                                    + "    <td rowspan='4'></td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Anak Ke -</td>"
                                    + "    <td>: " + rsc2.getString("anakke") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Alamat</td>"
                                    + "    <td>: " + rsc2.getString("alamat") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>No. Hp</td>"
                                    + "    <td>: " + rsc2.getString("no_hp") + "</td>"
                                    + "  </tr>"
                                    + "</tbody>"
                                    + "</table>"
                                    + "<br>"
                            );                            
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsc2 != null) {
                            rsc2.close();
                        }
                    }
                    
                    //identitas anak
                    try {
                        rsc3 = koneksi.prepareStatement("SELECT ifnull(anak_ke,'') anak_ke, ifnull(nama,'') nama, ifnull(jk,'') jk, concat(ifnull(umur,''),' ',ifnull(id_umur,'')) usia, "
                                + "ifnull(keterangan,'') keterangan, ifnull(pekerjaan_sekolah,'') pekerjaan_sekolah FROM psikolog_perkawinan_identitas_anak "
                                + "WHERE no_rkm_medis ='" + TNoRM.getText() + "'").executeQuery();

                        if (rsc3.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th colspan='6'><b>Identitas Anak</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td width='20px'><b>Anak Ke-</b></td>"
                                    + "    <td width='75px'><b>Nama</b></td>"
                                    + "    <td width='30px'><b>Jenis Kelamin</b></td>"
                                    + "    <td width='75px'><b>Usia</b></td>"
                                    + "    <td width='75px'><b>K/A/T</b></td>"
                                    + "    <td width='75px'><b>Pekerjaan/Sekolah</b></td>"
                                    + "  </tr>"
                            );
                            rsc3.beforeFirst();
                            while (rsc3.next()) {
                                htmlContent.append(
                                        "<tr>"
                                        + "<td valign='top' width='20px'>" + rsc3.getString("anak_ke") + "</td>"
                                        + "<td valign='top' width='75px'>" + rsc3.getString("nama") + "</td>"
                                        + "<td valign='top' width='30px'>" + rsc3.getString("jk") + "</td>"
                                        + "<td valign='top' width='75px'>" + rsc3.getString("usia") + "</td>"
                                        + "<td valign='top' width='75px'>" + rsc3.getString("keterangan") + "</td>"
                                        + "<td valign='top' width='75px'>" + rsc3.getString("pekerjaan_sekolah") + "</td>"
                                        + "</tr>");
                            }
                            htmlContent.append(
                                    "</tbody>"
                                    + "</table>"
                                    + "<br>"
                            );
                        }                         
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsc3 != null) {
                            rsc3.close();
                        }
                    } 
                    
                    //keluhan permasalahan
                    try {
                        rsc4 = koneksi.prepareStatement("SELECT ifnull(masalah_dalam_nikah,'') masalah_dalam_nikah, ifnull(sudah_brp_lama,'') sudah_brp_lama, "
                                + "ifnull(alasan_bantuan,'') alasan_bantuan, ifnull(permasalahan_dinilai,'') permasalahan_dinilai, ifnull(karena,'') karena "
                                + "FROM psikolog_perkawinan_keluhan WHERE no_rawat ='" + TNoRW.getText() + "'").executeQuery();

                        if (rsc4.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th><b>Keluhan / Permasalahan</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Permasalahan Dalam Pernikahan : <br>" + rsc4.getString("masalah_dalam_nikah").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Sudah Berapa Lama Permasalahan Ini Terjadi ? (Dalam hubungan minggu, bulan, tahun) <br>" + rsc4.getString("sudah_brp_lama").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                    + "  </tr>" 
                                    + "  <tr>"
                                    + "    <td valign='top'>Alasan Yang Membuat Mencari Bantuan Kali Ini : <br>" + rsc4.getString("alasan_bantuan").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                    + "  </tr>" 
                                    + "  <tr>"
                                    + "    <td valign='top'>Permasalahan Saat Ini Dinilai : " + rsc4.getString("permasalahan_dinilai") + "</td>"
                                    + "  </tr>" 
                                    + "  <tr>"
                                    + "    <td valign='top'>Karena <br>" + rsc4.getString("karena").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                    + "  </tr>"
                                    + "</tbody>"
                                    + "</table>"
                                    + "<br>"
                            );
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsc4 != null) {
                            rsc4.close();
                        }
                    }
                    
                    //harapan
                    try {
                        rsc5 = koneksi.prepareStatement("SELECT ifnull(pw.harapan,'') harapan, ifnull(pw.perubahan_diinginkan_diri_sendiri,'') perubahan_diinginkan_diri_sendiri, "
                                + "ifnull(pw.perubahan_diinginkan_keluarga,'') perubahan_diinginkan_keluarga, ifnull(pw.permasalahan_saat_ini,'') permasalahan_saat_ini "
                                + "FROM psikolog_wawancara_klinis pw INNER JOIN psikolog_data_diri_klien pd on pd.no_rawat=pw.no_rawat WHERE "
                                + "pw.no_rawat ='" + TNoRW.getText() + "' and pd.rekam_psikologis='perkawinan'").executeQuery();

                        if (rsc5.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th><b>Harapan</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td>Harapan Terhadap Pernikahan : <br>"
                                    + "" + rsc5.getString("harapan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Perubahan Yang Diinginkan Dari Diri Sendiri : <br>"
                                    + "" + rsc5.getString("perubahan_diinginkan_diri_sendiri").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Perubahan Yang Diinginkan Dari Pasangan : <br>"
                                    + "" + rsc5.getString("perubahan_diinginkan_keluarga") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Kemungkinan Perbaikan Pernikahan<br>"
                                    + "" + rsc5.getString("permasalahan_saat_ini").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"                                    
                                    + "</tbody>"
                                    + "</table>"
                                    + "<br>"
                            );
                        }                            
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsc5 != null) {
                            rsc5.close();
                        }
                    } 
                    
                    //riwayat hidup singkat
                    try {
                        rsc6 = koneksi.prepareStatement("SELECT ifnull(masa_kanak,'-') masa_kanak, ifnull(masa_remaja,'-') masa_remaja, "
                                + "ifnull(masa_dewasa,'-') masa_dewasa, ifnull(riw_penyakit_fisik,'-') riw_penyakit_fisik, ifnull(riw_pengobatan,'-') riw_pengobatan "
                                + "FROM psikolog_umum_dewasa_riw_hidup WHERE no_rawat ='" + TNoRW.getText() + "'").executeQuery();

                        if (rsc6.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th><b>Riwayat Hidup Singkat<br>(Masa Kanak-kanak, Remaja, Dewasa)</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"                                    
                                    + "  <tr>"
                                    + "    <td>&#10004 Masa Kanak-kanak :<br><br>"
                                    + "" + rsc6.getString("masa_kanak").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>&#10004 Masa Remaja :<br><br>"
                                    + "" + rsc6.getString("masa_remaja").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>&#10004 Masa Dewasa :<br><br>"
                                    + "" + rsc6.getString("masa_dewasa").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>&#10004 Riwayat Penyakit Fisik / Psikologis :<br><br>"
                                    + "" + rsc6.getString("riw_penyakit_fisik").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>&#10004 Riwayat Pengobatan / Konsultasi :<br><br>"
                                    + "" + rsc6.getString("riw_pengobatan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"
                                    + "</tbody>"
                                    + "</table>"
                                    + "<br>"
                            );
                        }                            
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsc6 != null) {
                            rsc6.close();
                        }
                    } 
                    
                    //observasi kondisi psikologis pasien secara umum
                    try {
                        rsc7 = koneksi.prepareStatement("SELECT ifnull(penampilan,'') penampilan, ifnull(ekspresi_wajah,'') ekspresi_wajah, "
                                + "ifnull(perasaan_suasana_hati,'') perasaan_suasana_hati, ifnull(tingkah_laku,'') tingkah_laku, ifnull(fungsi_umum,'') fungsi_umum, "
                                + "ifnull(fungsi_intelektual,'') fungsi_intelektual, ifnull(pengalaman_kerja,'') pengalaman_kerja, ifnull(lainlain,'') lainlain "
                                + "FROM psikolog_observasi WHERE no_rawat ='" + TNoRW.getText() + "' and rekam_psikologis='umum_dewasa'").executeQuery();

                        if (rsc7.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th colspan='2'><b>Observasi<br>Kondisi Psikologis Pasien Secara Umum</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td valign='top' width='180px'>Penampilan</td>"
                                    + "    <td valign='top'>: " + rsc7.getString("penampilan") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Ekspresi Wajah</td>"
                                    + "    <td valign='top'>: " + rsc7.getString("ekspresi_wajah") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Perasaan / Suasana Hati</td>"
                                    + "    <td valign='top'>: " + rsc7.getString("perasaan_suasana_hati") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Tingkah Laku</td>"
                                    + "    <td valign='top'>: " + rsc7.getString("tingkah_laku") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Fungsi Umum</td>"
                                    + "    <td valign='top'>: " + rsc7.getString("fungsi_umum") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Fungsi Intelektual</td>"
                                    + "    <td valign='top'>: " + rsc7.getString("fungsi_intelektual") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Pengalaman Kerja</td>"
                                    + "    <td valign='top'>: " + rsc7.getString("pengalaman_kerja") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Lain-lain</td>"
                                    + "    <td valign='top'>: " + rsc7.getString("lainlain") + "</td>"
                                    + "  </tr>"
                                    + "</tbody>"
                                    + "</table>"
                                    + "<br>"
                            );
                        }                            
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsc7 != null) {
                            rsc7.close();
                        }
                    } 
                    
                    //kondisi psikopatologis pasien
                    try {
                        rsc8 = koneksi.prepareStatement("SELECT ifnull(delusi_waham,'') delusi_waham, ifnull(proses_pikiran,'') proses_pikiran, ifnull(halusinasi,'') halusinasi, "
                                + "ifnull(afek,'') afek, ifnull(insight,'') insight, ifnull(kesadaran,'') kesadaran, ifnull(orientasi_waktu,'') orientasi_waktu, "
                                + "ifnull(orientasi_tempat,'') orientasi_tempat, ifnull(orientasi_ruang,'') orientasi_ruang, ifnull(perhatian,'') perhatian, "
                                + "ifnull(penilaian,'') penilaian, ifnull(kontrol_thdp_impuls,'') kontrol_thdp_impuls FROM psikolog_umum_dewasa_psikopatologis WHERE "
                                + "no_rawat ='" + TNoRW.getText() + "'").executeQuery();

                        if (rsc8.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th colspan='2'><b>Kondisi Psikopatologis Pasien</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td valign='top' width='180px'>Delusi / Waham</td>"
                                    + "    <td valign='top'>: " + rsc8.getString("delusi_waham") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Proses Pikiran</td>"
                                    + "    <td valign='top'>: " + rsc8.getString("proses_pikiran") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Halusinasi</td>"
                                    + "    <td valign='top'>: " + rsc8.getString("halusinasi") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Afek</td>"
                                    + "    <td valign='top'>: " + rsc8.getString("afek") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'><i>Insight</i></td>"
                                    + "    <td valign='top'>: " + rsc8.getString("insight") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Kesadaran</td>"
                                    + "    <td valign='top'>: " + rsc8.getString("kesadaran") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Orientasi Waktu</td>"
                                    + "    <td valign='top'>: " + rsc8.getString("orientasi_waktu") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Orientasi Tempat</td>"
                                    + "    <td valign='top'>: " + rsc8.getString("orientasi_tempat") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Orientasi Ruang</td>"
                                    + "    <td valign='top'>: " + rsc8.getString("orientasi_ruang") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Perhatian</td>"
                                    + "    <td valign='top'>: " + rsc8.getString("perhatian") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Penilaian</td>"
                                    + "    <td valign='top'>: " + rsc8.getString("penilaian") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Kontrol Terhadap Impuls</td>"
                                    + "    <td valign='top'>: " + rsc8.getString("kontrol_thdp_impuls") + "</td>"
                                    + "  </tr>"
                                    + "</tbody>"
                                    + "</table>"
                                    + "<br>"
                            );
                        }                            
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsc8 != null) {
                            rsc8.close();
                        }
                    } 
                    
                    //tes psikologi
                    try {
                        rsc9 = koneksi.prepareStatement("SELECT DATE_FORMAT(ifnull(rencana_tes,date(now())),'%d-%m-%Y') tglRencana, "
                                + "ifnull(tes_psikologis,'') tes_psikologis, ifnull(waktu,'') waktu, ifnull(tester,'') tester FROM psikolog_tes_psikologi WHERE "
                                + "no_rawat ='" + TNoRW.getText() + "' and rekam_psikologis='umum_dewasa'").executeQuery();

                        if (rsc9.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th colspan='5'><b>Tes Psikologi</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td align='center' rowspan='2'><b>Rencana Tes</b></td>"
                                    + "    <td align='center' colspan='3'><b>Pelaksanaan Tes</b></td>"
                                    + "    <td align='center' rowspan='2'><b>Hasil</b></td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td align='center'><b>Tes Psikologi</b></td>"
                                    + "    <td align='center'><b>Waktu</b></td>"
                                    + "    <td align='center'><b>Tester</b></td>"
                                    + "  </tr>"         
                            );
                            rsc9.beforeFirst();
                            while (rsc9.next()) {
                                htmlContent.append(
                                        "<tr>"
                                        + "<td align='center'>" + rsc9.getString("tglRencana") + "</td>"
                                        + "<td>" + rsc9.getString("tes_psikologis") + "</td>"
                                        + "<td>" + rsc9.getString("waktu") + "</td>"
                                        + "<td>" + rsc9.getString("tester") + "</td>"
                                        + "<td></td>"
                                        + "</tr>");
                            }
                            htmlContent.append(
                                    "</tbody>"
                                    + "</table>"
                                    + "<br>"
                            );
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsc9 != null) {
                            rsc9.close();
                        }
                    }
                    
                    //manifestasi fungsi psikologis
                    try {
                        rsc10 = koneksi.prepareStatement("SELECT ifnull(pm.manifestasi,'') manifestasi FROM psikolog_manifestasi_fungsi pm "
                                + "INNER JOIN reg_periksa rp ON rp.no_rawat=pm.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                                + "pm.no_rawat ='" + TNoRW.getText() + "' and rp.umurdaftar>= 15 and rp.sttsumur='Th'").executeQuery();

                        if (rsc10.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th><b>Manifestasi Fungsi Psikologis</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td valign='top'>" + rsc10.getString("manifestasi").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"                                    
                                    + "</tbody>"
                                    + "</table>"
                                    + "<br>"
                            );
                        }                            
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsc10 != null) {
                            rsc10.close();
                        }
                    } 
                    
                    //diagnosis (berdasarkan DSM/ICD/PPDGJ)
                    try {
                        rsc11 = koneksi.prepareStatement("SELECT IFNULL(pd.kesan_awal,'') kesan_awal, ifnull(pd.diagnosa_utama,'') diagnosa_utama, "
                                + "ifnull(pd.diagnosa_banding,'') diagnosa_banding FROM psikolog_diagnosis pd INNER JOIN reg_periksa rp ON rp.no_rawat=pd.no_rawat "
                                + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE pd.no_rawat ='" + TNoRW.getText() + "' and "
                                + "rp.umurdaftar>= 15 and rp.sttsumur='Th'").executeQuery();

                        if (rsc11.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th colspan='2'><b>Diagnosis (Berdasarkan DSM / ICD / PPDGJ)</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td valign='top' width='230px'>Kesan Awal</td>"
                                    + "    <td>: " + rsc11.getString("kesan_awal").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Diagnosis Utama (dx)</td>"
                                    + "    <td valign='top'>: " + rsc11.getString("diagnosa_utama") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Diagnosis Banding (dd)</td>"
                                    + "    <td valign='top'>: " + rsc11.getString("diagnosa_banding") + "</td>"
                                    + "  </tr>"                                    
                                    + "</tbody>"
                                    + "</table>"
                                    + "<br>"
                            );
                        }                            
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsc11 != null) {
                            rsc11.close();
                        }
                    } 
                    
                    //prognosis
                    try {
                        rsc12 = koneksi.prepareStatement("SELECT ifnull(pp.prognosis,'') prognosis FROM psikolog_prognosis pp "
                                + "INNER JOIN reg_periksa rp ON rp.no_rawat=pp.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                                + "pp.no_rawat ='" + TNoRW.getText() + "' and rp.umurdaftar>= 15 and rp.sttsumur='Th'").executeQuery();

                        if (rsc12.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th><b>Prognosis</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td valign='top'>" + rsc12.getString("prognosis").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"                                    
                                    + "</tbody>"
                                    + "</table>"
                                    + "<br>"
                            );
                        }                            
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsc12 != null) {
                            rsc12.close();
                        }
                    } 
                    
                    //rencana tritmen
                    try {
                        rsc13 = koneksi.prepareStatement("SELECT ifnull(pr.tritmen,'') tritmen, ifnull(pr.pertemuan_selanjutnya,'') pertemuan_selanjutnya, "
                                + "ifnull(pr.cttn_tambahan,'') cttn_tambahan FROM psikolog_rencana_tritmen pr "
                                + "INNER JOIN reg_periksa rp ON rp.no_rawat=pr.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                                + "pr.no_rawat ='" + TNoRW.getText() + "' and rp.umurdaftar>= 15 and rp.sttsumur='Th'").executeQuery();

                        if (rsc13.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th colspan='2'><b>Rencana Tritmen</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td valign='top' width='230px'>Deskripsi Tritmen</td>"
                                    + "    <td valign='top'>: " + rsc13.getString("tritmen") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Pertemuan Selanjutnya</td>"
                                    + "    <td valign='top'>: " + rsc13.getString("pertemuan_selanjutnya") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Catatan Tambahan</td>"
                                    + "    <td valign='top'>: " + rsc13.getString("cttn_tambahan") + "</td>"
                                    + "  </tr>"
                                    + "</tbody>"
                                    + "</table>"
                                    + "<br>"
                                    + "<br>"
                            );
                            
                        }                            
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsc13 != null) {
                            rsc13.close();
                        }
                    }
                    
                    //tertanda psikolog pemeriksa
                    htmlContent.append("<table width='100%' class='noborder'"
                            + "<tbody>"
                            + "  <tr>"
                            + "    <td colspan='4'></td>"
                            + "    <td width='350px' colspan='1'>" + akses.getkabupatenrs() + ", "
                            + Sequel.cariIsi("select DATE_FORMAT('" + rsc1.getString("tgl_registrasi") + "','%d')") + " "
                            + Sequel.bulanINDONESIA("select DATE_FORMAT('" + rsc1.getString("tgl_registrasi") + "','%m')") + " "
                            + Sequel.cariIsi("select DATE_FORMAT('" + rsc1.getString("tgl_registrasi") + "','%Y')") + "</td>"
                            + "  </tr>"
                            + "  <tr>"
                            + "    <td colspan='4'></td>"
                            + "    <td colspan='1'><b>Psikolog Pemeriksa,</b><br><br><br><br><br></td>"
                            + "  </tr>"
                            + "  <tr>"
                            + "    <td colspan='4'></td>"
                            + "    <td colspan='1'>( " + TnmPsikolog.getText() + " )</td>"
                            + "  </tr>"
                            + "</tbody>"
                            + "</table>"
                            + "<br>"
                    );

                }
                LoadHTML.setText(
                        "<html>"                        
                        + "<body>"
                        + "<head></head>"     
                        + htmlContent.toString()
                        + "</body>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsc1 != null) {
                    rsc1.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    private void cetakRPP() {
        try {
            File f = new File("rekam_psikologis_umum_dewasa.html");
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(LoadHTML.getText().replaceAll("<head>", "<head>"
                    + "<style>"
                    + "table { font: 14px tahoma;}"
                    + ".adaborder {"
                    + "    width: 100%;"
                    + "  border-collapse: collapse;"
                    + "}"
                    + ""
                    + ".adaborder th{"
                    + "  border: 1px solid black;"
                    + "  padding: 5px;"
                    + "}"
                    + ""
                    + ".adaborder td{"
                    + "  border: 1px solid black;"
                    + "  padding: 5px;"
                    + "}"
                    + ""
                    + ".noborder th{"
                    + "  border: 0px;"
                    + "  padding: 5px;"
                    + "}"
                    + ""
                    + ".noborder td{"
                    + "  border: 0px;"
                    + "  padding: 5px;"
                    + "}"
                    + "</style>"
                            
                    + "<table width='100%' class='noborder'"
                            +"<tr>"
                            +"<td></td>"
                            +"<td></td>"
                            +"<td align='right'><font size='4' face='Tahoma'><b>RAHASIA</font></b></td>"
                            +"</tr>"
                    + "<tr>"
                    + "<td width='30%'>"
                    + "<img src='setting/logo.jpg' height=60px align='right'>"
                    + "</td>"
                    + "<td valign='top' align='center' width='45%'>"
                    + "<font size='4' face='Tahoma'><b>" + akses.getnamars() + "</b></font><br>"
                    + "<font size='3' face='Tahoma'>" + akses.getalamatrs() + ", " + akses.getkabupatenrs() + ", " + akses.getpropinsirs() + "<br>"
                    + akses.getkontakrs() + "e-Mail : " + akses.getemailrs() + "<br></font>"
                    + "</td>"
                    + "<td width='40%'>"
                    + "</td>"
                    + "</tr>"
                    + "<tr class='isi2'>"
                    + "<td colspan=3>"
                    + "<hr><center><font size='3' face='Tahoma'><b>REKAM PSIKOLOGI PERKAWINAN<br>Poliklinik Psikologi<br><br></font>"
                    + "</td>"
                    + "</tr>"
                    + "</table>")
            );
            bw.close();
            Desktop.getDesktop().browse(f.toURI());
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
}
