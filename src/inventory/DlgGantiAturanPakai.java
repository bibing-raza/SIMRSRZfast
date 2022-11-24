package inventory;
import laporan.*;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author dosen
 */
public final class DlgGantiAturanPakai extends javax.swing.JDialog {    
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private final Properties prop = new Properties();
    private PreparedStatement ps1, ps2;
    private ResultSet rs1, rs2;    
    private String nmPrinter1 = "", nmPrinter2 = "", norwt = "", kdobt = "", tgl_resep = "", jam_resep = "";
        
    /** Creates new form DlgPenyakit
     * @param parent
     * @param modal */
    
    public DlgGantiAturanPakai(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(10,2);
        setSize(628,674);  
        
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            nmPrinter1 = koneksiDB.NAMAPRINTER1();
            nmPrinter2 = koneksiDB.NAMAPRINTER2();
        } catch (Exception e) {
            System.out.println("NAMA PRINTER : "+e);
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

        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnGanti = new widget.Button();
        jLabel16 = new widget.Label();
        cmblabel = new widget.ComboBox();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        kdobat = new widget.TextBox();
        jLabel9 = new widget.Label();
        nmobat = new widget.TextBox();
        jLabel4 = new widget.Label();
        norawat = new widget.TextBox();
        norm = new widget.TextBox();
        nmpasien = new widget.TextBox();
        jLabel5 = new widget.Label();
        tglresep = new widget.TextBox();
        jLabel6 = new widget.Label();
        jamresep = new widget.TextBox();
        cmbaturan1 = new widget.ComboBox();
        jLabel10 = new widget.Label();
        cmbaturan2 = new widget.ComboBox();
        jLabel11 = new widget.Label();
        cmbaturan3 = new widget.ComboBox();
        jLabel12 = new widget.Label();
        cmbwaktu1 = new widget.ComboBox();
        jLabel13 = new widget.Label();
        cmbwaktu2 = new widget.ComboBox();
        jLabel14 = new widget.Label();
        jLabel15 = new widget.Label();
        cmbket = new widget.ComboBox();
        cmbmasasimpan = new widget.ComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Labeling Aturan Pakai Obat ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 50));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 8));

        BtnGanti.setForeground(new java.awt.Color(0, 0, 0));
        BtnGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnGanti.setMnemonic('S');
        BtnGanti.setText("Simpan");
        BtnGanti.setToolTipText("Alt+S");
        BtnGanti.setName("BtnGanti"); // NOI18N
        BtnGanti.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnGanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnGanti);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Jns. Label :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass8.add(jLabel16);

        cmblabel.setBackground(new java.awt.Color(248, 253, 243));
        cmblabel.setForeground(new java.awt.Color(0, 0, 0));
        cmblabel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Obat Minum", "Obat Luar" }));
        cmblabel.setName("cmblabel"); // NOI18N
        cmblabel.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass8.add(cmblabel);

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
        panelGlass8.add(BtnPrint);

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
        panelGlass8.add(BtnKeluar);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(611, 240));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(611, 100));
        FormInput.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Kode Obat : ");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(0, 40, 100, 23);

        kdobat.setEditable(false);
        kdobat.setForeground(new java.awt.Color(0, 0, 0));
        kdobat.setName("kdobat"); // NOI18N
        FormInput.add(kdobat);
        kdobat.setBounds(103, 40, 150, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Aturan 1 : ");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(0, 96, 100, 23);

        nmobat.setEditable(false);
        nmobat.setForeground(new java.awt.Color(0, 0, 0));
        nmobat.setName("nmobat"); // NOI18N
        FormInput.add(nmobat);
        nmobat.setBounds(258, 40, 344, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Pasien : ");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 12, 100, 23);

        norawat.setEditable(false);
        norawat.setForeground(new java.awt.Color(0, 0, 0));
        norawat.setName("norawat"); // NOI18N
        FormInput.add(norawat);
        norawat.setBounds(103, 12, 150, 23);

        norm.setEditable(false);
        norm.setForeground(new java.awt.Color(0, 0, 0));
        norm.setName("norm"); // NOI18N
        FormInput.add(norm);
        norm.setBounds(258, 12, 70, 23);

        nmpasien.setEditable(false);
        nmpasien.setForeground(new java.awt.Color(0, 0, 0));
        nmpasien.setName("nmpasien"); // NOI18N
        FormInput.add(nmpasien);
        nmpasien.setBounds(332, 12, 270, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tgl. Resep : ");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 68, 100, 23);

        tglresep.setEditable(false);
        tglresep.setForeground(new java.awt.Color(0, 0, 0));
        tglresep.setName("tglresep"); // NOI18N
        FormInput.add(tglresep);
        tglresep.setBounds(103, 68, 100, 23);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Jam : ");
        jLabel6.setName("jLabel6"); // NOI18N
        FormInput.add(jLabel6);
        jLabel6.setBounds(205, 68, 40, 23);

        jamresep.setEditable(false);
        jamresep.setForeground(new java.awt.Color(0, 0, 0));
        jamresep.setName("jamresep"); // NOI18N
        FormInput.add(jamresep);
        jamresep.setBounds(248, 68, 70, 23);

        cmbaturan1.setBackground(new java.awt.Color(248, 253, 243));
        cmbaturan1.setForeground(new java.awt.Color(0, 0, 0));
        cmbaturan1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbaturan1.setName("cmbaturan1"); // NOI18N
        cmbaturan1.setPreferredSize(new java.awt.Dimension(100, 23));
        FormInput.add(cmbaturan1);
        cmbaturan1.setBounds(103, 96, 110, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Aturan 2 : ");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 124, 100, 23);

        cmbaturan2.setBackground(new java.awt.Color(248, 253, 243));
        cmbaturan2.setForeground(new java.awt.Color(0, 0, 0));
        cmbaturan2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbaturan2.setName("cmbaturan2"); // NOI18N
        cmbaturan2.setPreferredSize(new java.awt.Dimension(100, 23));
        FormInput.add(cmbaturan2);
        cmbaturan2.setBounds(103, 124, 60, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Aturan 3 : ");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 152, 100, 23);

        cmbaturan3.setBackground(new java.awt.Color(248, 253, 243));
        cmbaturan3.setForeground(new java.awt.Color(0, 0, 0));
        cmbaturan3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbaturan3.setName("cmbaturan3"); // NOI18N
        cmbaturan3.setPreferredSize(new java.awt.Dimension(100, 23));
        FormInput.add(cmbaturan3);
        cmbaturan3.setBounds(103, 152, 110, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Waktu 1 : ");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(230, 96, 70, 23);

        cmbwaktu1.setBackground(new java.awt.Color(248, 253, 243));
        cmbwaktu1.setForeground(new java.awt.Color(0, 0, 0));
        cmbwaktu1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbwaktu1.setName("cmbwaktu1"); // NOI18N
        cmbwaktu1.setPreferredSize(new java.awt.Dimension(100, 23));
        FormInput.add(cmbwaktu1);
        cmbwaktu1.setBounds(303, 96, 190, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Waktu 2 : ");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(230, 124, 70, 23);

        cmbwaktu2.setBackground(new java.awt.Color(248, 253, 243));
        cmbwaktu2.setForeground(new java.awt.Color(0, 0, 0));
        cmbwaktu2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbwaktu2.setName("cmbwaktu2"); // NOI18N
        cmbwaktu2.setPreferredSize(new java.awt.Dimension(100, 23));
        FormInput.add(cmbwaktu2);
        cmbwaktu2.setBounds(303, 124, 160, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Keterangan : ");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(0, 180, 100, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Masa Simpan : ");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 208, 100, 23);

        cmbket.setBackground(new java.awt.Color(248, 253, 243));
        cmbket.setForeground(new java.awt.Color(0, 0, 0));
        cmbket.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbket.setName("cmbket"); // NOI18N
        cmbket.setPreferredSize(new java.awt.Dimension(100, 23));
        FormInput.add(cmbket);
        cmbket.setBounds(103, 180, 350, 23);

        cmbmasasimpan.setBackground(new java.awt.Color(248, 253, 243));
        cmbmasasimpan.setForeground(new java.awt.Color(0, 0, 0));
        cmbmasasimpan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbmasasimpan.setName("cmbmasasimpan"); // NOI18N
        cmbmasasimpan.setPreferredSize(new java.awt.Dimension(100, 23));
        FormInput.add(cmbmasasimpan);
        cmbmasasimpan.setBounds(103, 208, 300, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        emptteks();
        norwt = "";
        kdobt = "";
        tgl_resep = "";
        jam_resep = "";
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (norawat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu data obat yg. akan diperbaiki aturan pakainya...!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            norwt = "";
            kdobt = "";
            tgl_resep = "";
            jam_resep = "";

            Sequel.mengedit("aturan_pakai", "no_rawat='" + norawat.getText() + "' and tgl_perawatan='" + tglresep.getText() + "' "
                    + "and jam='" + jamresep.getText() + "' and kode_brng='" + kdobat.getText() + "'",
                    "aturan1='" + cmbaturan1.getSelectedItem().toString() + "', "
                    + "aturan2='" + cmbaturan2.getSelectedItem().toString() + "', "
                    + "aturan3='" + cmbaturan3.getSelectedItem().toString() + "', "
                    + "waktu1='" + cmbwaktu1.getSelectedItem().toString() + "', "
                    + "waktu2='" + cmbwaktu2.getSelectedItem().toString() + "', "
                    + "keterangan='" + cmbket.getSelectedItem().toString() + "', "
                    + "waktu_simpan='" + cmbmasasimpan.getSelectedItem().toString() + "'");
            
            norwt = norawat.getText();
            kdobt = kdobat.getText();
            tgl_resep = tglresep.getText();
            jam_resep = jamresep.getText();            
            
            JOptionPane.showMessageDialog(null, "Aturan pakai obat berhasil disimpan, bisa dilanjutkan dg. mencetak labelnya...!!");
            cmblabel.requestFocus();
            this.setCursor(Cursor.getDefaultCursor());
        }        
}//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (norawat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu data obat yg. akan diperbaiki aturan pakainya...!!");
        } else if (cmblabel.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu jenis label aturan pakai yg. akan dicetak...!!");
            cmblabel.requestFocus();
        } else if (norwt.equals("") && kdobt.equals("")) {
            JOptionPane.showMessageDialog(null, "Data aturan pakai obat masih kosong...!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if (cmblabel.getSelectedIndex() == 1) {
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("logo", Sequel.cariGambar("select logo_hitam_putih from setting"));
                param.put("jns_label", "");
                Valid.MyReport("rptAturanPakai.jasper", "report", "::[ Labeling Obat Minum ]::",
                        "select ap.no_rawat, ap.kode_brng, concat(p.no_rkm_medis,' - ',p.nm_pasien) pasien, date_format(ap.tgl_perawatan,'%d/%m/%Y') tgl, "
                        + "d.nama_brng, ap.aturan1, ap.aturan2, ap.aturan3, ap.waktu1, ap.waktu2, ap.keterangan, ap.waktu_simpan, ap.tgl_perawatan, "
                        + "ap.jam from aturan_pakai ap inner join databarang d on d.kode_brng=ap.kode_brng "
                        + "inner join reg_periksa rp on rp.no_rawat=ap.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "ap.no_rawat='" + norwt + "' and "
                        + "ap.kode_brng='" + kdobt + "' and "
                        + "ap.tgl_perawatan='" + tgl_resep + "' and "
                        + "ap.jam='" + jam_resep + "'", param);

//                Valid.AutoPrintMulti("rptAturanPakai.jasper", "report", "::[ Labeling Obat Minum ]::",
//                        "select ap.no_rawat, ap.kode_brng, concat(p.no_rkm_medis,' - ',p.nm_pasien) pasien, date_format(ap.tgl_perawatan,'%d/%m/%Y') tgl, "
//                        + "d.nama_brng, ap.aturan1, ap.aturan2, ap.aturan3, ap.waktu1, ap.waktu2, ap.keterangan, ap.waktu_simpan, ap.tgl_perawatan, "
//                        + "ap.jam from aturan_pakai ap inner join databarang d on d.kode_brng=ap.kode_brng "
//                        + "inner join reg_periksa rp on rp.no_rawat=ap.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
//                        + "ap.no_rawat='" + norwt + "' and "
//                        + "ap.kode_brng='" + kdobt + "' and "
//                        + "ap.tgl_perawatan='" + tgl_resep + "' and "
//                        + "ap.jam='" + jam_resep + "'", param, nmPrinter1);
                BtnKeluarActionPerformed(null);

            } else if (cmblabel.getSelectedIndex() == 2) {
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("logo", Sequel.cariGambar("select logo_hitam_putih from setting"));
                param.put("jns_label", "OBAT LUAR");
                Valid.MyReport("rptAturanPakai.jasper", "report", "::[ Labeling Obat Luar ]::",
                        "select ap.no_rawat, ap.kode_brng, concat(p.no_rkm_medis,' - ',p.nm_pasien) pasien, date_format(ap.tgl_perawatan,'%d/%m/%Y') tgl, "
                        + "d.nama_brng, ap.aturan1, ap.aturan2, ap.aturan3, ap.waktu1, ap.waktu2, ap.keterangan, ap.waktu_simpan, ap.tgl_perawatan, "
                        + "ap.jam from aturan_pakai ap inner join databarang d on d.kode_brng=ap.kode_brng "
                        + "inner join reg_periksa rp on rp.no_rawat=ap.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "ap.no_rawat='" + norwt + "' and "
                        + "ap.kode_brng='" + kdobt + "' and "
                        + "ap.tgl_perawatan='" + tgl_resep + "' and "
                        + "ap.jam='" + jam_resep + "'", param);

//                Valid.AutoPrintMulti("rptAturanPakai.jasper", "report", "::[ Labeling Obat Luar ]::",
//                        "select ap.no_rawat, ap.kode_brng, concat(p.no_rkm_medis,' - ',p.nm_pasien) pasien, date_format(ap.tgl_perawatan,'%d/%m/%Y') tgl, "
//                        + "d.nama_brng, ap.aturan1, ap.aturan2, ap.aturan3, ap.waktu1, ap.waktu2, ap.keterangan, ap.waktu_simpan, ap.tgl_perawatan, "
//                        + "ap.jam from aturan_pakai ap inner join databarang d on d.kode_brng=ap.kode_brng "
//                        + "inner join reg_periksa rp on rp.no_rawat=ap.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
//                        + "ap.no_rawat='" + norwt + "' and "
//                        + "ap.kode_brng='" + kdobt + "' and "
//                        + "ap.tgl_perawatan='" + tgl_resep + "' and "
//                        + "ap.jam='" + jam_resep + "'", param, nmPrinter2);                
                BtnKeluarActionPerformed(null);
            }
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnGanti, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        norwt = "";
        kdobt = "";
        tgl_resep = "";
        jam_resep = "";
    }//GEN-LAST:event_formWindowOpened

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgGantiAturanPakai dialog = new DlgGantiAturanPakai(new javax.swing.JFrame(), true);
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
    private widget.Button BtnGanti;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.PanelBiasa FormInput;
    private javax.swing.JPanel PanelInput;
    private widget.ComboBox cmbaturan1;
    private widget.ComboBox cmbaturan2;
    private widget.ComboBox cmbaturan3;
    private widget.ComboBox cmbket;
    private widget.ComboBox cmblabel;
    private widget.ComboBox cmbmasasimpan;
    private widget.ComboBox cmbwaktu1;
    private widget.ComboBox cmbwaktu2;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel3;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox jamresep;
    private widget.TextBox kdobat;
    private widget.TextBox nmobat;
    private widget.TextBox nmpasien;
    private widget.TextBox norawat;
    private widget.TextBox norm;
    private widget.panelisi panelGlass8;
    private widget.TextBox tglresep;
    // End of variables declaration//GEN-END:variables

    public JTextField getTextField(){
        return kdobat;
    }
    
    public void setData(String norw, String kodeobat, String tgl, String jam) {
        norawat.setText(norw);
        isPasien();
        kdobat.setText(kodeobat);
        nmobat.setText(Sequel.cariIsi("select nama_brng from databarang where kode_brng='" + kodeobat + "'"));
        tglresep.setText(tgl);
        jamresep.setText(jam);
        isAturanPakai();
        norwt = norw;
        kdobt = kodeobat;
        tgl_resep = tgl;
        jam_resep = jam;
    }
    
    private void isPasien() {
        try {
            ps1 = koneksi.prepareStatement("select p.no_rkm_medis, p.nm_pasien from reg_periksa rp "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where rp.no_rawat='" + norawat.getText() + "'");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    norm.setText(rs1.getString("no_rkm_medis"));
                    nmpasien.setText(rs1.getString("nm_pasien"));
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
    }
    
    private void isAturanPakai() {
        Sequel.cariIsiComboDB("select nama from master_aturan_pakai where opsi = 'aturan pakai 1'", cmbaturan1);
        Sequel.cariIsiComboDB("select nama from master_aturan_pakai where opsi = 'aturan pakai 2'", cmbaturan2);
        Sequel.cariIsiComboDB("select nama from master_aturan_pakai where opsi = 'aturan pakai 3'", cmbaturan3);
        Sequel.cariIsiComboDB("select nama from master_aturan_pakai where opsi = 'waktu 1'", cmbwaktu1);
        Sequel.cariIsiComboDB("select nama from master_aturan_pakai where opsi = 'waktu 2'", cmbwaktu2);
        Sequel.cariIsiComboDB("select nama from master_aturan_pakai where opsi = 'keterangan'", cmbket);
        Sequel.cariIsiComboDB("select nama from master_aturan_pakai where opsi = 'masa simpan'", cmbmasasimpan);
        
        try {
            ps2 = koneksi.prepareStatement("select aturan1, aturan2, aturan3, waktu1, waktu2, keterangan, waktu_simpan from aturan_pakai "
                    + "where no_rawat='" + norawat.getText() + "' and kode_brng='" + kdobat.getText() + "' and "
                    + "tgl_perawatan='" + tglresep.getText() + "' and jam='" + jamresep.getText() + "'");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    cmbaturan1.setSelectedItem(rs2.getString("aturan1"));
                    cmbaturan2.setSelectedItem(rs2.getString("aturan2"));
                    cmbaturan3.setSelectedItem(rs2.getString("aturan3"));
                    cmbwaktu1.setSelectedItem(rs2.getString("waktu1"));
                    cmbwaktu2.setSelectedItem(rs2.getString("waktu2"));
                    cmbket.setSelectedItem(rs2.getString("keterangan"));
                    cmbmasasimpan.setSelectedItem(rs2.getString("waktu_simpan"));
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
    }
    
    private void emptteks() {
        norawat.setText("");
        norm.setText("");
        nmpasien.setText("");
        kdobat.setText("");
        nmobat.setText("");
        tglresep.setText("");
        jamresep.setText("");
        cmbaturan1.setSelectedIndex(0);
        cmbaturan2.setSelectedIndex(0);
        cmbaturan3.setSelectedIndex(0);
        cmbwaktu1.setSelectedIndex(0);
        cmbwaktu2.setSelectedIndex(0);
        cmbket.setSelectedIndex(0);
        cmbmasasimpan.setSelectedIndex(0);
    }
}
