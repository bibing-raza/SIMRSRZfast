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
                $no_sep         =isset($_GET['no_sep'])?$_GET['no_sep']:NULL;
                $norawat        =isset($_GET['norawat'])?$_GET['norawat']:NULL;
                $codernik       =isset($_GET['codernik'])?$_GET['codernik']:NULL;
                $keyword        =isset($_GET['keyword'])?$_GET['keyword']:NULL;
                echo "<input type=hidden name=codernik  value=$codernik><input type=hidden name=keyword value=$keyword>";
        ?>
    <div style="width: 100%; height: 90%; overflow: auto;">
    <?php
        $BtnCari  = isset($_POST['BtnCari'])?$_POST['BtnCari']:NULL;
        $keyword  = isset($_POST['keyword'])?trim($_POST['keyword']):NULL;
        $keyword  = validTeks($keyword);
        if (isset($BtnCari)) {      
                $tahunawal      =trim($_POST['tahunawal']);
                $bulanawal      =trim($_POST['bulanawal']);
                $tanggalawal    =trim($_POST['tanggalawal']);
                $tahunakhir     =trim($_POST['tahunakhir']);
                $bulanakhir     =trim($_POST['bulanakhir']);
                $tanggalakhir   =trim($_POST['tanggalakhir']);
                $codernik       =trim($_POST['codernik']);                
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
        $_sql = "SELECT bs.no_sep, bs.no_rawat, bs.nomr, bs.nama_pasien, bs.tglsep, bs.tglrujukan, bs.no_rujukan, bs.kdppkrujukan, bs.nmppkrujukan, bs.kdppkpelayanan, bs.nmppkpelayanan, rp.kd_dokter, d.nm_dokter,
				IF (bs.jnspelayanan = '1','1. Rawat Inap','2. Rawat Jalan') AS jenispelayanan, bs.catatan, bs.diagawal, bs.nmdiagnosaawal, bs.kdpolitujuan, bs.nmpolitujuan,
				IF (bs.klsrawat = '1','1. Kelas 1', IF (bs.klsrawat = '2','2. Kelas 2','3. Kelas 3')) AS kelas,
				IF (bs.lakalantas = '0','2. Bukan Kasus Kecelakaan','1. Kasus Kecelakaan') AS lakalantas, bs.user, bs.tanggal_lahir, bs.peserta, bs.jkel, bs.no_kartu, bs.tglpulang
				FROM bridging_sep bs INNER JOIN reg_periksa rp on rp.no_rawat = bs.no_rawat INNER JOIN dokter d ON rp.kd_dokter = d.kd_dokter WHERE 
				bs.tglsep BETWEEN '".$tahunawal."-".$bulanawal."-".$tanggalawal." 00:00:00' AND '".$tahunakhir."-".$bulanakhir."-".$tanggalakhir." 23:59:59' AND bs.no_sep LIKE '%".$keyword."%' OR 
				bs.tglsep BETWEEN '".$tahunawal."-".$bulanawal."-".$tanggalawal." 00:00:00' AND '".$tahunakhir."-".$bulanakhir."-".$tanggalakhir." 23:59:59' AND bs.nomr LIKE '%".$keyword."%' OR 
				bs.tglsep BETWEEN '".$tahunawal."-".$bulanawal."-".$tanggalawal." 00:00:00' AND '".$tahunakhir."-".$bulanakhir."-".$tanggalakhir." 23:59:59' AND bs.nama_pasien LIKE '%".$keyword."%' OR 
				bs.tglsep BETWEEN '".$tahunawal."-".$bulanawal."-".$tanggalawal." 00:00:00' AND '".$tahunakhir."-".$bulanakhir."-".$tanggalakhir." 23:59:59' AND bs.no_rawat LIKE '%".$keyword."%' OR 
				bs.tglsep BETWEEN '".$tahunawal."-".$bulanawal."-".$tanggalawal." 00:00:00' AND '".$tahunakhir."-".$bulanakhir."-".$tanggalakhir." 23:59:59' AND bs.no_kartu LIKE '%".$keyword."%' ORDER BY bs.tglsep";
        $hasil=bukaquery($_sql);
        $jumlah=mysql_num_rows($hasil);
        if(mysql_num_rows($hasil)!=0) {
            echo "<table width='100%' border='0' align='center' cellpadding='0' cellspacing='0' class='tbl_form'>
                    <tr class='head2'>
                        <td width='22%'><div align='center'>SEP</div></td>
                        <td width='23%'><div align='center'>Pasien</div></td>
                        <td width='23%'><div align='center'>Dokter</div></td>
                        <td width='22%'><div align='center'>Diagnosa & Prosedur</div></td>
                        <td width='10%'><div align='center'>Status Data</div></td>
                    </tr>";
                    while($baris = mysql_fetch_array($hasil)) {
                        $status="<a href='?act=KlaimBaruManual&action=Kirim&no_sep=".$baris["no_sep"]."&tahunawal=$tahunawal&bulanawal=$bulanawal&tanggalawal=$tanggalawal&tahunakhir=$tahunakhir&bulanakhir=$bulanakhir&tanggalakhir=$tanggalakhir&codernik=$codernik'>[Kirim]</a>";
                        if(getOne("select count(no_sep) from inacbg_klaim_baru where no_sep='".$baris["no_sep"]."'")>0){
                            $status="<a href='?act=KlaimBaruManual&action=Kirim&no_sep=".$baris["no_sep"]."&tahunawal=$tahunawal&bulanawal=$bulanawal&tanggalawal=$tanggalawal&tahunakhir=$tahunakhir&bulanakhir=$bulanakhir&tanggalakhir=$tanggalakhir&codernik=$codernik'>[Kirim Ulang]</a>";
                        }
                        echo "<tr class='isi' title='".$baris["no_rawat"].", ".$baris["no_sep"].", ".$baris["tglsep"].", ".$baris["no_kartu"].", ".$baris["nomr"].", ".$baris["nama_pasien"]."'>
                                <td valign='top'>Tgl.SEP : ".$baris["tglsep"]."<br>No.SEP : ".$baris["no_sep"]."<br>No.Kartu : ".$baris["no_kartu"]."</td>
                                <td valign='top'>No.Rawat : ".$baris["no_rawat"]."<br>No.MR : ".$baris["nomr"]."<br>Nama Pasien : ".$baris["nama_pasien"]."</td>
                                <td bgcolor='#FFFFFF' valign='top'>".$baris["nm_dokter"]."<br>Status : ".$baris["jenispelayanan"]."<br>Ruang : ".$baris["nmpolitujuan"]."</td>
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
                                echo $prosedur;
                         echo  "</td>
                                <td valign='top' align='center'><a href='?act=KlaimBaruManual&action=InputDiagnosa&norawat=".$baris["no_rawat"]."&tahunawal=$tahunawal&bulanawal=$bulanawal&tanggalawal=$tanggalawal&tahunakhir=$tahunakhir&bulanakhir=$bulanakhir&tanggalakhir=$tanggalakhir&codernik=$codernik&keyword=$keyword'>[Input Diagnosa]</a><br>".$status."</td>
                             </tr>";
                    }
            echo "</table>";           
        }else{
            echo "<table width='100%' border='0' align='center' cellpadding='0' cellspacing='0' class='tbl_form'>
                    <tr class='head2'>
                        <td width='22%'><div align='center'>SEP</div></td>
                        <td width='23%'><div align='center'>Pasien</div></td>
                        <td width='23%'><div align='center'>Dokter</div></td>
                        <td width='22%'><div align='center'>Diagnosa & Prosedur</div></td>
                        <td width='10%'><div align='center'>Status Data</div></td>
                    </tr>
                   </table>";
        }         
        
        if($action=="Kirim") {
            $_sql   = "select no_kartu,no_sep,nomr,nama_pasien,tanggal_lahir,jkel,CONCAT(tglsep,' 00:00:00') tglsep,
					   IF(jnspelayanan='1',tglpulang,CONCAT(tglsep,' 00:00:00')) as tglpulang,jnspelayanan,klsrawat,no_rawat from bridging_sep where no_sep='".$no_sep."'";
            $hasil  = bukaquery($_sql);
            $baris  = mysql_fetch_array($hasil);
            $gender = "";
            if($baris["jkel"]=="L"){
                $gender="1";
            }else{
                $gender="2";
            }
            
            $prosedur="";
            $a=1;
            $hasilprosedur=bukaquery("select kode from prosedur_pasien where no_rawat='".$baris["no_rawat"]."' order by prioritas asc");
            while($barisprosedur = mysql_fetch_array($hasilprosedur)) {
                if($a==1){
                    $prosedur=$barisprosedur["kode"];
                }else{
                    $prosedur=$prosedur."#".$barisprosedur["kode"];
                }                
                $a++;
            }            
            
            $penyakit="";
            $a=1;
            $hasilpenyakit=bukaquery("select kd_penyakit from diagnosa_pasien where no_rawat='".$baris["no_rawat"]."' order by prioritas asc");
            while($barispenyakit = mysql_fetch_array($hasilpenyakit)) {
                if($a==1){
                    $penyakit=$barispenyakit["kd_penyakit"];
                }else{
                    $penyakit=$penyakit."#".$barispenyakit["kd_penyakit"];
                }                
                $a++;
            } 
            
			$discharge_status="5";
			if(getOne("select count(no_rawat) from kamar_inap where stts_pulang='Sembuh/BLPL' and no_rawat='".$baris["no_rawat"]."'")>0){
  				$discharge_status="1";
			}else if(getOne("select count(no_rawat) from kamar_inap where stts_pulang='Dirujuk' and no_rawat='".$baris["no_rawat"]."'")>0){
  				$discharge_status="2";
			}else if(getOne("select count(no_rawat) from kamar_inap where stts_pulang='APS' and no_rawat='".$baris["no_rawat"]."'")>0){
  				$discharge_status="3";
			}else if(getOne("select count(no_rawat) from kamar_inap where stts_pulang='Meninggal >= 48 Jam' and no_rawat='".$baris["no_rawat"]."'")>0){
  				$discharge_status="4";
			}else if(getOne("select count(no_rawat) from kamar_inap where stts_pulang='Meninggal < 48 Jam' and no_rawat='".$baris["no_rawat"]."'")>0){
  				$discharge_status="4";
			}else if(getOne("select count(no_rawat) from kamar_inap where stts_pulang='Kabur' and no_rawat='".$baris["no_rawat"]."'")>0){
  				$discharge_status="5";
			}else{
  				$discharge_status="1";
			}
                          
            $nm_dokter    = getOne("SELECT d.nm_dokter FROM reg_periksa rp INNER JOIN dokter d ON rp.kd_dokter = d.kd_dokter WHERE rp.no_rawat = '".$baris["no_rawat"]."'");
            $nm_dokter2   = getOne("select d.nm_dokter from dpjp_ranap dr inner join dokter d on dr.kd_dokter=d.kd_dokter where dr.no_rawat='".$baris["no_rawat"]."'");
            if(!empty($nm_dokter2)){
                $nm_dokter=$nm_dokter2;
            }
            BuatKlaimBaru($baris["no_kartu"],$baris["no_sep"],$baris["nomr"],$baris["nama_pasien"],$baris["tanggal_lahir"], $gender);
			EditUlangKlaim($baris["no_sep"]);
            UpdateDataKlaim($baris["no_sep"],$baris["no_kartu"],$baris["tglsep"],$baris["tglpulang"],$baris["jnspelayanan"],$baris["klsrawat"],"","","","","","","","","",
							getOne("select berat_badan_benar from pasien_bayi where no_rkm_medis='".$baris["nomr"]."'"),$discharge_status,$penyakit,$prosedur, 
							getOne("select biaya_reg from reg_periksa where no_rawat='".$baris["no_rawat"]."'"), $nm_dokter,getKelasRS(),"","","#",$codernik,$baris["no_rawat"]);
            
            echo "<meta http-equiv='refresh' content='1;URL=?act=KlaimBaruManual&tahunawal=$tahunawal&bulanawal=$bulanawal&tanggalawal=$tanggalawal&tahunakhir=$tahunakhir&bulanakhir=$bulanakhir&tanggalakhir=$tanggalakhir&codernik=$codernik&action=no&keyword=$keyword'>";
        }if($action=="InputDiagnosa") {
            HapusAll("temppanggilnorawat");
            InsertData2("temppanggilnorawat","'$norawat'");
            echo "<meta http-equiv='refresh' content='1;URL=?act=KlaimBaruManual&tahunawal=$tahunawal&bulanawal=$bulanawal&tanggalawal=$tanggalawal&tahunakhir=$tahunakhir&bulanakhir=$bulanakhir&tanggalakhir=$tanggalakhir&codernik=$codernik&action=no&keyword=$keyword'>";
        }
        
        $BtnKeluar=isset($_POST['BtnKeluar'])?$_POST['BtnKeluar']:NULL;
        if (isset($BtnKeluar)) {
            echo"<meta http-equiv='refresh' content='1;URL=?act=KlaimBaruManual&action=Keluar'>";
        }

    ?>
    </div>
            <table width="100%" align="center" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr class="head3">					
                    <td width="720px">
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
                        Keyword : <input name="keyword" class="text" onkeydown="setDefault(this, document.getElementById('MsgIsi1'));" type=text id="TxtIsi1" value="<?php echo $keyword;?>" size="25" maxlength="250" autofocus />
                        <input name=BtnCari type=submit class="button" value="&nbsp;&nbsp;Cari&nbsp;&nbsp;" />
                    </td>
                    <td width="120px" >Record : <?php echo $jumlah; ?> </td>
                    <td><input name=BtnKeluar type=submit class="button" value="&nbsp;&nbsp;&nbsp;Keluar&nbsp;&nbsp;&nbsp;" /> </td>
                </tr>
            </table>
	</form>
    </div>
</div>
