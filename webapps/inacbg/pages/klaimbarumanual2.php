<div id="post">
    <div class="entry">   
	<form name="frm_aturadmin" onsubmit="return validasiIsi();" method="post" action="" enctype=multipart/form-data>
        <?php
                echo "";
                $tahunawal      =isset($_GET['tahunawal'])?$_GET['tahunawal']:NULL;
                $bulanawal      =isset($_GET['bulanawal'])?$_GET['bulanawal']:NULL;
                $tanggalawal    =isset($_GET['tanggalawal'])?$_GET['tanggalawal']:NULL;
                $tahunakhir     =isset($_GET['tahunakhir'])?$_GET['tahunakhir']:NULL;
                $bulanakhir     =isset($_GET['bulanakhir'])?$_GET['bulanakhir']:NULL;
                $tanggalakhir   =isset($_GET['tanggalakhir'])?$_GET['tanggalakhir']:NULL;  
                $action         =isset($_GET['action'])?$_GET['action']:NULL;
                $norawat        =isset($_GET['norawat'])?$_GET['norawat']:NULL;
                $codernik       =isset($_GET['codernik'])?$_GET['codernik']:NULL;
                $carabayar      =str_replace("_"," ",isset($_GET['carabayar']))?str_replace("_"," ",$_GET['carabayar']):NULL;
                $keyword        =isset($_GET['keyword'])?$_GET['keyword']:NULL;
                echo "<input type=hidden name=codernik  value=$codernik><input type=hidden name=keyword value=$keyword>";
        ?>
    <div style="width: 100%; height: 85%; overflow: auto;">
    <?php
        $BtnCari  =isset($_POST['BtnCari'])?$_POST['BtnCari']:NULL;
        $keyword  =isset($_POST['keyword'])?trim($_POST['keyword']):NULL;
        $keyword  = validTeks($keyword);
        if (isset($BtnCari)) {      
                $tahunawal      =trim($_POST['tahunawal']);
                $bulanawal      =trim($_POST['bulanawal']);
                $tanggalawal    =trim($_POST['tanggalawal']);
                $tahunakhir     =trim($_POST['tahunakhir']);
                $bulanakhir     =trim($_POST['bulanakhir']);
                $tanggalakhir   =trim($_POST['tanggalakhir']);
                $codernik       =trim($_POST['codernik']);
                $carabayar      =isset($_POST['carabayar'])?trim($_POST['carabayar']):NULL;
        }
        if(empty($tahunawal)){
            $tahunawal=date('Y');
        }
        if(empty($bulanawal)){
            $bulanawal=date('m');
        }
        if(empty($tanggalawal)){
            $tanggalawal=date('d');
        }
        if(empty($tahunakhir)){
            $tahunakhir=date('Y');
        }
        if(empty($bulanakhir)){
            $bulanakhir=date('m');
        }
        if(empty($tanggalakhir)){
            $tanggalakhir=date('d');
        }
        $_sql = "SELECT rp.no_reg, rp.no_rawat, rp.tgl_registrasi, rp.jam_reg, rp.kd_dokter, d.nm_dokter, rp.no_rkm_medis, p.nm_pasien,
				IF (p.jk = 'L','Laki-Laki','Perempuan') AS jk, p.umur, pl.nm_poli, rp.p_jawab, rp.almt_pj, rp.hubunganpj, rp.biaya_reg, rp.stts_daftar, pj.png_jawab
				FROM reg_periksa rp INNER JOIN dokter d ON rp.kd_dokter = d.kd_dokter INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis
				INNER JOIN poliklinik pl ON rp.kd_poli = pl.kd_poli INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj
				WHERE rp.stts <> 'Batal' AND pj.png_jawab LIKE '%".$carabayar."%' AND rp.tgl_registrasi BETWEEN '".$tahunawal."-".$bulanawal."-".$tanggalawal."' AND '".$tahunakhir."-".$bulanakhir."-".$tanggalakhir."' AND rp.no_reg LIKE '%".$keyword."%'
				OR rp.stts <> 'Batal' AND pj.png_jawab LIKE '%".$carabayar."%' AND rp.tgl_registrasi BETWEEN '".$tahunawal."-".$bulanawal."-".$tanggalawal."' AND '".$tahunakhir."-".$bulanakhir."-".$tanggalakhir."' AND rp.no_rawat LIKE '%".$keyword."%'
				OR rp.stts <> 'Batal' AND pj.png_jawab LIKE '%".$carabayar."%' AND rp.tgl_registrasi BETWEEN '".$tahunawal."-".$bulanawal."-".$tanggalawal."' AND '".$tahunakhir."-".$bulanakhir."-".$tanggalakhir."' AND rp.tgl_registrasi LIKE '%".$keyword."%'
				OR rp.stts <> 'Batal' AND pj.png_jawab LIKE '%".$carabayar."%' AND rp.tgl_registrasi BETWEEN '".$tahunawal."-".$bulanawal."-".$tanggalawal."' AND '".$tahunakhir."-".$bulanakhir."-".$tanggalakhir."' AND rp.kd_dokter LIKE '%".$keyword."%'
				OR rp.stts <> 'Batal' AND pj.png_jawab LIKE '%".$carabayar."%' AND rp.tgl_registrasi BETWEEN '".$tahunawal."-".$bulanawal."-".$tanggalawal."' AND '".$tahunakhir."-".$bulanakhir."-".$tanggalakhir."' AND d.nm_dokter LIKE '%".$keyword."%'
				OR rp.stts <> 'Batal' AND pj.png_jawab LIKE '%".$carabayar."%' AND rp.tgl_registrasi BETWEEN '".$tahunawal."-".$bulanawal."-".$tanggalawal."' AND '".$tahunakhir."-".$bulanakhir."-".$tanggalakhir."' AND rp.no_rkm_medis LIKE '%".$keyword."%'
				OR rp.stts <> 'Batal' AND pj.png_jawab LIKE '%".$carabayar."%' AND rp.tgl_registrasi BETWEEN '".$tahunawal."-".$bulanawal."-".$tanggalawal."' AND '".$tahunakhir."-".$bulanakhir."-".$tanggalakhir."' AND rp.stts_daftar LIKE '%".$keyword."%'
				OR rp.stts <> 'Batal' AND pj.png_jawab LIKE '%".$carabayar."%' AND rp.tgl_registrasi BETWEEN '".$tahunawal."-".$bulanawal."-".$tanggalawal."' AND '".$tahunakhir."-".$bulanakhir."-".$tanggalakhir."' AND p.nm_pasien LIKE '%".$keyword."%'
				OR rp.stts <> 'Batal' AND pj.png_jawab LIKE '%".$carabayar."%' AND rp.tgl_registrasi BETWEEN '".$tahunawal."-".$bulanawal."-".$tanggalawal."' AND '".$tahunakhir."-".$bulanakhir."-".$tanggalakhir."' AND pl.nm_poli LIKE '%".$keyword."%'
				OR rp.stts <> 'Batal' AND pj.png_jawab LIKE '%".$carabayar."%' AND rp.tgl_registrasi BETWEEN '".$tahunawal."-".$bulanawal."-".$tanggalawal."' AND '".$tahunakhir."-".$bulanakhir."-".$tanggalakhir."' AND pj.png_jawab LIKE '%".$keyword."%'
				ORDER BY rp.tgl_registrasi, rp.jam_reg DESC";
        $hasil=bukaquery($_sql);
        $jumlah=mysql_num_rows($hasil);
        if(mysql_num_rows($hasil)!=0) {
            echo "<table width='100%' border='0' align='center' cellpadding='0' cellspacing='0' class='tbl_form'>
                    <tr class='head2'>
                        <td width='30%'><div align='center'>Data Pasien</div></td>
                        <td width='20%'><div align='center'>Registrasi</div></td>
                        <td width='20%'><div align='center'>Dokter</div></td>
                        <td width='20%'><div align='center'>Diagnosa</div></td>
                        <td width='10%'><div align='center'>Status</div></td>
                    </tr>";
                    while($baris = mysql_fetch_array($hasil)) {
                        $statuscovid="Bukan Corona";
                        $aksi="BukanCorona";
                        if(getOne("select count(no_rawat) from perawatan_corona where no_rawat='".$baris["no_rawat"]."'")>0){
                            $statuscovid="Pasien Corona";
                            $aksi="PasienCorona";
                        }
                        
                        $carabayar =str_replace(" ","_",$carabayar)?str_replace(" ","_",$carabayar):NULL;
                        $status="<a href='?act=DetailKirim&corona=$aksi&norawat=".$baris["no_rawat"]."&codernik=$codernik&tahunawal=$tahunawal&bulanawal=$bulanawal&tanggalawal=$tanggalawal&tahunakhir=$tahunakhir&bulanakhir=$bulanakhir&tanggalakhir=$tanggalakhir&carabayar=$carabayar'>[Kirim]</a>";
                        if(getOne("select count(no_rawat) from inacbg_klaim_baru2 where no_rawat='".$baris["no_rawat"]."'")>0){
                            $status="<a href='?act=DetailKirim&corona=$aksi&norawat=".$baris["no_rawat"]."&codernik=$codernik&tahunawal=$tahunawal&bulanawal=$bulanawal&tanggalawal=$tanggalawal&tahunakhir=$tahunakhir&bulanakhir=$bulanakhir&tanggalakhir=$tanggalakhir&carabayar=$carabayar'>[Kirim Ulang]</a>";
                        }
                        echo "<tr class='isi' title='".$baris["no_rawat"].", ".$baris["no_rkm_medis"].", ".$baris["nm_pasien"]."'>
                                <td bgcolor='#FFFFFF' valign='top'>".$baris["no_rkm_medis"]." ".$baris["nm_pasien"]."<br>".$baris["almt_pj"]."<br>".$baris["jk"].", Usia ".$baris["umur"]."</td>
                                <td bgcolor='#FFFFFF' valign='top'>".$baris["no_rawat"]." ".$baris["no_reg"]."<br>".$baris["tgl_registrasi"]." ".$baris["jam_reg"]."<br>Status : ".$baris["stts_daftar"]."</td>
                                <td bgcolor='#FFFFFF' valign='top'>".$baris["nm_dokter"]."<br>".$baris["nm_poli"]."<br>Cara Bayar : ".$baris["png_jawab"]."</td>
                                <td valign='top'>";
                                $penyakit="";
                                $a=1;
                                $hasilpenyakit=bukaquery("select kd_penyakit from diagnosa_pasien where no_rawat='".$baris["no_rawat"]."' order by prioritas asc");
                                while($barispenyakit = mysql_fetch_array($hasilpenyakit)) {
                                    if($a==1){
                                        $penyakit=$barispenyakit["kd_penyakit"];
                                    }else{
                                        $penyakit=$penyakit.", ".$barispenyakit["kd_penyakit"];
                                    }                
                                    $a++;
                                }
                                echo $penyakit."<br>";
                                
                                $prosedur="";
                                $a=1;
                                $hasilprosedur=bukaquery("select kode from prosedur_pasien where no_rawat='".$baris["no_rawat"]."' order by prioritas asc");
                                while($barisprosedur = mysql_fetch_array($hasilprosedur)) {
                                    if($a==1){
                                        $prosedur=$barisprosedur["kode"];
                                    }else{
                                        $prosedur=$prosedur.", ".$barisprosedur["kode"];
                                    }                
                                    $a++;
                                } 
                                echo $prosedur." ".$keyword;
                         echo  "</td>
                                <td valign='center' align='center'>
                                    <a href='?act=KlaimBaruManual2&action=InputCorona&norawat=".$baris["no_rawat"]."&tahunawal=$tahunawal&bulanawal=$bulanawal&tanggalawal=$tanggalawal&tahunakhir=$tahunakhir&bulanakhir=$bulanakhir&tanggalakhir=$tanggalakhir&codernik=$codernik&keyword=$keyword'>[$statuscovid]</a><br>
                                    <a href='?act=KlaimBaruManual2&action=InputDiagnosa&norawat=".$baris["no_rawat"]."&tahunawal=$tahunawal&bulanawal=$bulanawal&tanggalawal=$tanggalawal&tahunakhir=$tahunakhir&bulanakhir=$bulanakhir&tanggalakhir=$tanggalakhir&codernik=$codernik&keyword=$keyword'>[Input Diagnosa]</a><br>
                                    ".$status."
                                </td>                                
                             </tr>";
                    }
            echo "</table>";           
        }else{
            echo "<table width='100%' border='0' align='center' cellpadding='0' cellspacing='0' class='tbl_form'>
                    <tr class='head2'>
                        <td width='30%'><div align='center'>Data Pasien</div></td>
                        <td width='20%'><div align='center'>Registrasi</div></td>
                        <td width='20%'><div align='center'>Dokter</div></td>
                        <td width='20%'><div align='center'>Diagnosa</div></td>
                        <td width='10%'><div align='center'>Status</div></td>
                    </tr>
                   </table>";
        }         
        
        if($action=="InputDiagnosa") {
            HapusAll("temppanggilnorawat");
            InsertData2("temppanggilnorawat","'$norawat'");
            echo "<meta http-equiv='refresh' content='1;URL=?act=KlaimBaruManual2&tahunawal=$tahunawal&bulanawal=$bulanawal&tanggalawal=$tanggalawal&tahunakhir=$tahunakhir&bulanakhir=$bulanakhir&tanggalakhir=$tanggalakhir&codernik=$codernik&action=no&keyword=$keyword'>";
        }else if($action=="InputCorona") {
            HapusAll("temppanggilnorawat");
            InsertData2("temppanggilnorawat","'$norawat'");
            echo "<meta http-equiv='refresh' content='1;URL=?act=KlaimBaruManual2&tahunawal=$tahunawal&bulanawal=$bulanawal&tanggalawal=$tanggalawal&tahunakhir=$tahunakhir&bulanakhir=$bulanakhir&tanggalakhir=$tanggalakhir&codernik=$codernik&action=no&keyword=$keyword'>";
        }
        
        $BtnKeluar=isset($_POST['BtnKeluar'])?$_POST['BtnKeluar']:NULL;
        if (isset($BtnKeluar)) {
            echo"<meta http-equiv='refresh' content='1;URL=?act=KlaimBaruManual2&action=Keluar'>";
        }

    ?>
    </div>
            <table width="100%" align="center" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr class="head3">					
                    <td width="690px">
                        Periode : 
                        <select name="tanggalawal" class="text" onkeydown="setDefault(this, document.getElementById('MsgIsi3'));" id="TxtIsi3">
                             <?php
                                if(!$tanggalawal==""){
                                    echo "<option id='TxtIsi3' value=$tanggalawal>$tanggalawal</option>";
                                }                                    
                                loadTglnow();
                             ?>
                        </select>                        
                        <select name="bulanawal" class="text" onkeydown="setDefault(this, document.getElementById('MsgIsi2'));" id="TxtIsi2">
                             <?php
                                if(!$bulanawal==""){
                                    echo "<option id='TxtIsi2' value=$bulanawal>$bulanawal</option>";
                                }                                    
                                loadBlnnow();
                             ?>
                        </select>                        
                        <select name="tahunawal" class="text" onkeydown="setDefault(this, document.getElementById('MsgIsi1'));" id="TxtIsi1">
                             <?php
                                if(!$tahunawal==""){
                                    echo "<option id='TxtIsi1' value=$tahunawal>$tahunawal</option>";
                                }                                    
                                loadThnnow();
                             ?>
                        </select>
                        &nbsp;s.d.&nbsp;
                        <select name="tanggalakhir" class="text" onkeydown="setDefault(this, document.getElementById('MsgIsi6'));" id="TxtIsi6">
                             <?php
                                if(!$tanggalakhir==""){
                                    echo "<option id='TxtIsi6' value=$tanggalakhir>$tanggalakhir</option>";
                                }                                    
                                loadTglnow();
                             ?>
                        </select>                        
                        <select name="bulanakhir" class="text" onkeydown="setDefault(this, document.getElementById('MsgIsi5'));" id="TxtIsi5">
                             <?php
                                if(!$bulanakhir==""){
                                    echo "<option id='TxtIsi5' value=$bulanakhir>$bulanakhir</option>";
                                }                                    
                                loadBlnnow();
                             ?>
                        </select>
                        <select name="tahunakhir" class="text" onkeydown="setDefault(this, document.getElementById('MsgIsi4'));" id="TxtIsi4">
                             <?php
                                if(!$tahunakhir==""){
                                    echo "<option id='TxtIsi4' value=$tahunakhir>$tahunakhir</option>";
                                }                                    
                                loadThnnow();
                             ?>
                        </select> 
                        &nbsp;
                        Cara Bayar : 
                        <select name="carabayar" class="text4">
                            <?php
                                $_sql = "SELECT png_jawab FROM penjab where status='1' ORDER BY png_jawab";
                                $hasil=bukaquery($_sql);
                                if(!empty($carabayar)){
                                    echo "<option value='$carabayar'>$carabayar</option>";
                                }
                                echo "<option value=''></option>";
                                while($baris = mysql_fetch_array($hasil)) {
                                    echo "<option value='$baris[0]'>$baris[0]</option>";
                                }
                            ?>
                        </select>                        
                    </td>
                </tr>
                <tr class="head3">					
                    <td width="690px">
                        Keyword : <input name="keyword" class="text" onkeydown="setDefault(this, document.getElementById('MsgIsi1'));" type=text id="TxtIsi1" value="<?php echo $keyword;?>" size="55" maxlength="250" autofocus />
                        <input name=BtnCari type=submit class="button" value="&nbsp;&nbsp;Cari&nbsp;&nbsp;" />
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        Record : <?php echo $jumlah; ?>
                        &nbsp;&nbsp;&nbsp;
                        <input name=BtnKeluar type=submit class="button" value="&nbsp;&nbsp;&nbsp;Keluar&nbsp;&nbsp;&nbsp;" />
                    </td>
                </tr>
            </table>
	</form>
    </div>
</div>