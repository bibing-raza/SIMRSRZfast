
<?php
   $_sql         = "SELECT * FROM set_tahun";
   $hasil        = bukaquery($_sql);
   $baris        = mysql_fetch_row($hasil);
   $tahun         = $baris[0];
   $bln_leng=strlen($baris[1]);
   $hari          =$baris[2];
   $bulan="0";
   $bulanindex=$baris[1];
   if ($bln_leng==1){
    	$bulan="0".$baris[1];
   }else{
	$bulan=$baris[1];
   }

   $_sqllibur  = "select `tanggal`, `ktg` from set_hari_libur where tanggal like '%".$tahun."-".$bulan."%' ORDER BY tanggal";
   $hasillibur =bukaquery($_sqllibur);
   $jumlahlibur=mysql_num_rows($hasillibur);
?>

<div id="post">
    <div class="entry">

    <div align="center" class="link">
        <a href=?act=InputSetJagaMalam&action=TAMBAH>| Set Jaga Malam |</a>
	<a href=?act=InputSetTambahJaga&action=TAMBAH>| Set Tambah Jaga |</a>
	<a href=?act=InputSetTunjanganHadir&action=TAMBAH>| Set Tnj.Hadir |</a>
        <a href=?act=InputSetLemburHB&action=TAMBAH>| Set Lembur HB |</a>
        <a href=?act=InputSetLemburHR&action=TAMBAH>| Set Lembur HR |</a>
        <a href=?act=ListLampiran&action=LIHAT>| List Lampiran |</a>
        <a href=?act=HomeAdmin>| Menu Utama |</a>
    </div>
        <form name="frm_aturadmin" onsubmit="return validasiIsi();" method="post" action="" enctype=multipart/form-data>
        <?php
                $action      =isset($_GET['action'])?$_GET['action']:NULL;
                $keyword     =isset($_GET['keyword'])?$_GET['keyword']:NULL;
                echo "<input type=hidden name=keyword value=$keyword><input type=hidden name=action value=CARI>";
        ?>
            <table width="100%" align="center">
                <tr class="head">
                    <td width="25%" >Keyword</td><td width="">:</td>
                    <td width="74%"><input name="keyword" class="text" onkeydown="setDefault(this, document.getElementById('MsgIsi1'));" type=text id="TxtIsi1" value="<?php echo $keyword;?>" size="65" maxlength="250" />
                        <input name=BtnCari type=submit class="button" value="&nbsp;&nbsp;Cari&nbsp;&nbsp;">
                    </td>
                </tr>
            </table><br>
    <div style="width: 100%; height: 78%; overflow: auto;">
    <?php
        $keyword=trim(isset($_POST['keyword']))?trim($_POST['keyword']):NULL;
        $action =isset($_POST['action'])?$_POST['action']:NULL;
        $say=" pegawai.pendidikan=pendidikan.tingkat
                and pegawai.stts_kerja =stts_kerja.stts
                and pegawai.jnj_jabatan=jnj_jabatan.kode
                and pegawai.stts_aktif<>'KELUAR'";
        $_sql = "select pegawai.id,
                pegawai.nik,
                pegawai.nama,
                pegawai.jbtn,
                pegawai.jnj_jabatan,
                pegawai.departemen,
                pegawai.indexins,
                CONCAT(FLOOR(PERIOD_DIFF(DATE_FORMAT('$tahun-$bulan-$hari', '%Y%m'),DATE_FORMAT(mulai_kerja, '%Y%m'))/12), ' Tahun ',MOD(PERIOD_DIFF(DATE_FORMAT('$tahun-$bulan-$hari', '%Y%m'), DATE_FORMAT(mulai_kerja, '%Y%m')),12), ' Bulan ') as lama,
                pendidikan.indek as index_pendidikan,
                (To_days('$tahun-$bulan-$hari')-to_days(mulai_kerja))/365 as masker,
                stts_kerja.indek as index_status,
                pegawai.indek as index_struktural,
                pegawai.pengurang,
                pegawai.wajibmasuk,
		pegawai.gapok,
                jnj_jabatan.tnj,
                pegawai.pendidikan,
                (To_days('$tahun-$bulan-$hari')-to_days(mulai_kontrak))/365 as maskon                
                from pegawai inner join pendidikan
                inner join stts_kerja
                inner join jnj_jabatan
                where ".$say." and pegawai.nik like '%".$keyword."%' or
                ".$say." and pegawai.nama like '%".$keyword."%' or
                ".$say." and pegawai.jbtn like '%".$keyword."%' or
                ".$say." and pegawai.jnj_jabatan like '%".$keyword."%' or
                ".$say." and pegawai.indexins like '%".$keyword."%'
                order by pegawai.id ASC ";
        $hasil=bukaquery($_sql);
        $jumlah=mysql_num_rows($hasil);	
	$hasilcari=bukaquery($_sql);
		
		//untuk mencari nilai referensinya
                if ($action!="CARI") {
                    hapusinput("delete from  indekref");
                }		
		while($baris = mysql_fetch_array($hasilcari)) {
			$masa_kerja=0;
                          if($baris[9]<1){
                             $masa_kerja=0;
                          }else if(($baris[9]>=1)&&($baris[9]<2)){
                             $masa_kerja=2;
                          }else if(($baris[9]>=2)&&($baris[9]<3)){
                             $masa_kerja=4;
                          }else if(($baris[9]>=3)&&($baris[9]<4)){
                             $masa_kerja=6;
                          }else if(($baris[9]>=4)&&($baris[9]<5)){
                             $masa_kerja=8;
                          }else if(($baris[9]>=5)&&($baris[9]<6)){
                             $masa_kerja=10;
                          }else if(($baris[9]>=6)&&($baris[9]<7)){
                             $masa_kerja=12;
                          }else if($baris[9]>=7){
                             $masa_kerja=14;
                          }

                          $total=0;
                          if($baris[12]==0){
                              $total=round(($baris[8]+$masa_kerja+$baris[10]+$baris[11]),2);
                          }else if($baris[12]>0){
                              $total=round(($baris[8]+$masa_kerja+$baris[10]+$baris[11])*($baris[12]/100),2);
                          }                          

                         $_sql2         = "SELECT normal-$jumlahlibur,jmlhr,normal FROM set_tahun";
			 $hasil2        = bukaquery($_sql2);
			 $baris2        = mysql_fetch_row($hasil2);
			 $jmlmsk         = $baris2[0];
			 if($baris[13]==-1){
			     $jmlmsk=0;
			 }else if($baris[13]==-2){
			     $jmlmsk=$baris2[1]-4;
			 }else if($baris[13]==-3){
			     $jmlmsk=$baris2[1]-2-$jumlahlibur;
			 }else if($baris[13]==-4){
			     $jmlmsk=$baris2[2];
			 }else if($baris[13]==-5){
			     $jmlmsk=getOne("select (if(h1='',0,1)+if(h2='',0,1)+if(h3='',0,1)+if(h4='',0,1)+if(h5='',0,1)+"
                                           ."if(h6='',0,1)+if(h7='',0,1)+if(h8='',0,1)+if(h9='',0,1)+if(h10='',0,1)+"
                                           ."if(h11='',0,1)+if(h12='',0,1)+if(h13='',0,1)+if(h14='',0,1)+if(h15='',0,1)+"
                                           ."if(h16='',0,1)+if(h17='',0,1)+if(h18='',0,1)+if(h19='',0,1)+if(h20='',0,1)+"
                                           ."if(h21='',0,1)+if(h22='',0,1)+if(h23='',0,1)+if(h24='',0,1)+if(h25='',0,1)+"
                                           ."if(h26='',0,1)+if(h27='',0,1)+if(h28='',0,1)+if(h29='',0,1)+if(h30='',0,1)+"
                                           ."if(h31='',0,1)) from jadwal_pegawai where id='$baris[0]' and tahun='$tahun' and bulan='$bulan'");
			 }else if($baris[13]!=0){
			     $jmlmsk=$baris[13];
			 }else if(!($baris[13]==0)){
			     $jmlmsk=$baris2[0];
			 }

                            $_sql3    = "SELECT sum(jml)
                            from jgmlm  where id='$baris[0]'
                            and tgl like '%".$tahun."-".$bulan."%' group by id";
                            $hasil3   = bukaquery($_sql3);
                            $baris3   = mysql_fetch_row($hasil3);
                            $jgmlm    = $baris3[0];
                            $sisamlm  =0;
                            if($baris3[0]<=0){
                                $jgmlm=0;
                                $sisamlm=0;
                            }else if($baris3[0]>0){
                                $jgmlm=$baris3[0];
                                $sisamlm=$baris3[0]-0;
                            }

                            $_sql4    = "SELECT sum(jml)
                            from ketidakhadiran  where id='$baris[0]'
                            and tgl like '%".$tahun."-".$bulan."%' and jns='A' group by id";
                            $hasil4   = bukaquery($_sql4);
                            $baris4   = mysql_fetch_row($hasil4);
                            $ttla     = $baris4[0];
                            if(empty ($baris4[0])){
                                $ttla=0;
                            }
                            
                            $_sql5    = "SELECT sum(jml)
                            from ketidakhadiran  where id='$baris[0]'
                            and tgl like '%".$tahun."-".$bulan."%' and jns='S' group by id";
                            $hasil5   = bukaquery($_sql5);
                            $baris5   = mysql_fetch_row($hasil5);
                            $ttls     = $baris5[0];
                            if(empty ($baris5[0])){
                                $ttls=0;
                            }

                            $_sql6    = "SELECT sum(jml)
                            from ketidakhadiran  where id='$baris[0]'
                            and tgl like '%".$tahun."-".$bulan."%' and jns='C' group by id";
                            $hasil6   = bukaquery($_sql6);
                            $baris6   = mysql_fetch_row($hasil6);
                            $ttlc     = $baris6[0];
                            if(empty ($baris6[0])){
                                $ttlc=0;
                            }

                            $_sql7    = "SELECT sum(jml)
                            from ketidakhadiran  where id='$baris[0]'
                            and tgl like '%".$tahun."-".$bulan."%' and jns='I' group by id";
                            $hasil7   = bukaquery($_sql7);
                            $baris7   = mysql_fetch_row($hasil7);
                            $ttli     = $baris7[0];
                            if(empty ($baris7[0])){
                                $ttli=0;
                            }

                            $_sql8    = "SELECT sum(jml)
                            from tambahjaga  where id='$baris[0]'
                            and tgl like '%".$tahun."-".$bulan."%' group by id";
                            $hasil8   = bukaquery($_sql8);
                            $baris8   = mysql_fetch_row($hasil8);
                            $ttltmb   = $baris8[0];
                            if(empty ($baris8[0])){
                                $ttltmb=0;
                            }					
							
                            $ttln=getOne("select count(rekap_presensi.id) from rekap_presensi where rekap_presensi.id='$baris[0]' and rekap_presensi.jam_datang like '%$tahun-$bulan%'")+$ttltmb;
                            if ($action!="CARI") {
                                bukainput("insert into indekref values('$baris[6]','$ttln','$total')");
                            }			    
		}
		
		//insert data ke total index
		$hasilindex=bukaquery("select kdindex,n,ttl from indekref");		
		//untuk mencari nilai referensinya
                if ($action!="CARI") {
                    hapusinput("delete from  indextotal");
                }
		while($barisindex = mysql_fetch_array($hasilindex)) {		
		    $_sql22  ="SELECT ($barisindex[1]/sum(n))*100 from indekref where kdindex='$barisindex[0]'";
		    $hasil22 =bukaquery($_sql22);
		    $baris22 = mysql_fetch_array($hasil22);
		    $indexjaga=round($baris22[0],2);                   
                    
				
		    $ttlindex=$barisindex[2]+$indexjaga;
                    if ($action!="CARI") {
                        bukainput("insert into indextotal  values('$barisindex[0]','$ttlindex')");
                    }
		}
		
                $ttlgapok=0;
                $ttltnjjbtn=0;
                $ttltnjtnj=0;
                $ttltmbhjgmlm=0;
                $ttltmbahanjg=0;
                $ttltnjhadir=0;
                $ttljmlgaji=0;
                $ttllemburhb=0;
                $ttllemburhr=0;
                $ttltotal=0;
                $ttlindexjaga=0;
                $ttlttlinsentif=0;
                $ttljm=0;
                $ttljmltmb=0;
                $ttlttlgaji=0;
                $ttljamsostek=0;
                $ttlbpjs=0;
                $ttldansos=0;
                $ttlsimwajib=0;
                $ttlangkop=0;
                $ttlangla=0;
                $ttltelpri=0;
                $ttlpajak=0;
                $ttlpribadi=0;
                $ttllain=0;
                $ttlttlditerima=0;
                $ttljasalain=0;
        if(mysql_num_rows($hasil)!=0) {
            echo "<table width='4900px' border='0' align='center' cellpadding='0' cellspacing='0' class='tbl_form'>
                    <tr class='head'>
                         <td width='80px'><div align='center'>NIP</div></td>
                         <td width='180px'><div align='center'>Nama</div></td>
                         <td width='100px'><div align='center'>Jabatan</div></td>
                         <td width='60px'><div align='center'>Kode Jenjang</div></td>
                         <td width='60px'><div align='center'>Depart</div></td>
                         <td width='60px'><div align='center'>Kode Index</div></td>
                         <td width='60px'><div align='center'>Index Kary</div></td>
                         <td width='200px'><div align='center'>Jumlah Hari</div></td>
                         <td width='70px'><div align='center'>Jml.Index Lembur</div></td>
                         <td width='910px'><div align='center'>Gaji & Tunjangan diterima</div></td>
                         <td width='310px'><div align='center'>Lembur Diterima</div></td>
                         <td width='630px'><div align='center'>Tambahan Gaji Diterima</div></td>
                         <td width='100px'><div align='center'><font size='2' face='Tahoma' color='green'><strong>Total Gaji</div></td>
                         <td width='920px'><div align='center'>Potongan Gaji</div></td>
                         <td width='100px'><div align='center'><font size='2' face='Tahoma' color='green'><strong>Total Gaji Diterima</div></td>
                    </tr>";
                    while($baris = mysql_fetch_array($hasil)) {
                         $_sqlgp    = "SELECT `gapok1`, `kenaikan`, `maksimal`
                          from pendidikan  where tingkat='$baris[16]' ";
                          $hasilgp    = bukaquery($_sqlgp);
                          $barisgp    = mysql_fetch_array($hasilgp);
                          $gapokgp    = 0;
                          @$gapok1    = $barisgp["gapok1"];
                          @$kenaikan  = $barisgp["kenaikan"];
                          @$maksimal  = $barisgp["maksimal"];
                          
                          if($baris[17]<$maksimal){
                             $gapokgp=$gapok1+($kenaikan*round($baris[17]));
                          }elseif($baris[17]>=$maksimal){
                             $gapokgp=$gapok1+($kenaikan*$maksimal);
                          }

                          $masa_kerja=0;
                          if($baris[9]<1){
                             $masa_kerja=0;
                          }else if(($baris[9]>=1)&&($baris[9]<2)){
                             $masa_kerja=2;
                          }else if(($baris[9]>=2)&&($baris[9]<3)){
                             $masa_kerja=4;
                          }else if(($baris[9]>=3)&&($baris[9]<4)){
                             $masa_kerja=6;
                          }else if(($baris[9]>=4)&&($baris[9]<5)){
                             $masa_kerja=8;
                          }else if(($baris[9]>=5)&&($baris[9]<6)){
                             $masa_kerja=10;
                          }else if(($baris[9]>=6)&&($baris[9]<7)){
                             $masa_kerja=12;
                          }else if($baris[9]>=7){
                             $masa_kerja=14;
                          }
                          
                          $total=0;
                          if($baris[12]==0){
                              $total=round(($baris[8]+$masa_kerja+$baris[10]+$baris[11]),2);
                          }else if($baris[12]>0){
                              $total=round(($baris[8]+$masa_kerja+$baris[10]+$baris[11])*($baris[12]/100),2);
                          }
                          $ttltotal=$ttltotal+$total;

                         $_sql2         = "SELECT normal-$jumlahlibur,jmlhr,normal FROM set_tahun";
			 $hasil2        = bukaquery($_sql2);
			 $baris2        = mysql_fetch_row($hasil2);
			 $jmlmsk         = $baris2[0];
			 if($baris[13]==-1){
			     $jmlmsk=0;
			 }else if($baris[13]==-2){
			     $jmlmsk=$baris2[1]-4;
			 }else if($baris[13]==-3){
			     $jmlmsk=$baris2[1]-2-$jumlahlibur;
			 }else if($baris[13]==-4){
			     $jmlmsk=$baris2[2];
			 }else if($baris[13]==-5){
			     $jmlmsk=getOne("select (if(h1='',0,1)+if(h2='',0,1)+if(h3='',0,1)+if(h4='',0,1)+if(h5='',0,1)+"
                                           ."if(h6='',0,1)+if(h7='',0,1)+if(h8='',0,1)+if(h9='',0,1)+if(h10='',0,1)+"
                                           ."if(h11='',0,1)+if(h12='',0,1)+if(h13='',0,1)+if(h14='',0,1)+if(h15='',0,1)+"
                                           ."if(h16='',0,1)+if(h17='',0,1)+if(h18='',0,1)+if(h19='',0,1)+if(h20='',0,1)+"
                                           ."if(h21='',0,1)+if(h22='',0,1)+if(h23='',0,1)+if(h24='',0,1)+if(h25='',0,1)+"
                                           ."if(h26='',0,1)+if(h27='',0,1)+if(h28='',0,1)+if(h29='',0,1)+if(h30='',0,1)+"
                                           ."if(h31='',0,1)) from jadwal_pegawai where id='$baris[0]' and tahun='$tahun' and bulan='$bulan'");
			 }else if($baris[13]!=0){
			     $jmlmsk=$baris[13];
			 }else if(!($baris[13]==0)){
			     $jmlmsk=$baris2[0];
			 }

                            $_sql3    = "SELECT sum(jml)
                            from jgmlm  where id='$baris[0]'
                            and tgl like '%".$tahun."-".$bulan."%' group by id";
                            $hasil3   = bukaquery($_sql3);
                            $baris3   = mysql_fetch_row($hasil3);
                            $jgmlm    = $baris3[0];
                            $sisamlm  =0;
                            if($baris3[0]<=0){
                                $jgmlm=0;
                                $sisamlm=0;
                            }else if($baris3[0]>0){
                                $jgmlm=$baris3[0];
                                $sisamlm=$baris3[0]-0;
                            }

                            $_sql4    = "SELECT sum(jml)
                            from ketidakhadiran  where id='$baris[0]'
                            and tgl like '%".$tahun."-".$bulan."%' and jns='A' group by id";
                            $hasil4   = bukaquery($_sql4);
                            $baris4   = mysql_fetch_row($hasil4);
                            $ttla     = $baris4[0];
                            if(empty ($baris4[0])){
                                $ttla=0;
                            }
                            
                            $_sql5    = "SELECT sum(jml)
                            from ketidakhadiran  where id='$baris[0]'
                            and tgl like '%".$tahun."-".$bulan."%' and jns='S' group by id";
                            $hasil5   = bukaquery($_sql5);
                            $baris5   = mysql_fetch_row($hasil5);
                            $ttls     = $baris5[0];
                            if(empty ($baris5[0])){
                                $ttls=0;
                            }

                            $_sql6    = "SELECT sum(jml)
                            from ketidakhadiran  where id='$baris[0]'
                            and tgl like '%".$tahun."-".$bulan."%' and jns='C' group by id";
                            $hasil6   = bukaquery($_sql6);
                            $baris6   = mysql_fetch_row($hasil6);
                            $ttlc     = $baris6[0];
                            if(empty ($baris6[0])){
                                $ttlc=0;
                            }

                            $_sql7    = "SELECT sum(jml)
                            from ketidakhadiran  where id='$baris[0]'
                            and tgl like '%".$tahun."-".$bulan."%' and jns='I' group by id";
                            $hasil7   = bukaquery($_sql7);
                            $baris7   = mysql_fetch_row($hasil7);
                            $ttli     = $baris7[0];
                            if(empty ($baris7[0])){
                                $ttli=0;
                            }

                            $_sql8    = "SELECT sum(jml)
                            from tambahjaga  where id='$baris[0]'
                            and tgl like '%".$tahun."-".$bulan."%' group by id";
                            $hasil8   = bukaquery($_sql8);
                            $baris8   = mysql_fetch_row($hasil8);
                            $ttltmb   = $baris8[0];
                            if(empty ($baris8[0])){
                                $ttltmb=0;
                            }						
							

                            $ttln=getOne("select count(rekap_presensi.id) from rekap_presensi where rekap_presensi.id='$baris[0]' and rekap_presensi.jam_datang like '%$tahun-$bulan%'")+$ttltmb;
                            $tmbh=$ttltmb;
							
			    $_sql9    = "SELECT id,jmlks,bsr from kasift  where id='$baris[0]'";
                            $hasil9   = bukaquery($_sql9);
                            $baris9   = mysql_fetch_row($hasil9);
                            $ks   = $baris9[1];
                            $bsrkasift=$baris9[2];
                            if($baris9[1]!=0){
                                $ks=$baris9[1];
                            }else if($baris9[1]==0){
				$ks=$ttln;
			    }
                            $ttlkasift=$ks*$bsrkasift;
							
			    $_sql10="select sum(presensi.lembur)
                                from presensi
                                where presensi.id='$baris[0]' and presensi.tgl like '%".$tahun."-".$bulan."%'
                                and presensi.jns='HB'
                                group by presensi.id";
			    $hasil10=bukaquery($_sql10);
			    $baris10 = mysql_fetch_array($hasil10);
			    $hb   = $baris10[0];
			    if(empty ($baris10[0])){
                                $hb=0;
                            }else {
				$hb=$baris10[0];
			    }
							
			    $_sql11="select sum(presensi.lembur)
                                from presensi
                                where presensi.id='$baris[0]' and presensi.tgl like '%".$tahun."-".$bulan."%'
                                and presensi.jns='HR'
                                group by presensi.id";
			    $hasil11=bukaquery($_sql11);
			    $baris11 = mysql_fetch_array($hasil11);
			    $hr   = $baris11[0];
			    if(empty ($baris11[0])){
                                $hr=0;
                            }else {
				$hr=$baris11[0];
			    }
							
			    $gapok=0;
			    if(empty ($gapokgp)){
                                $gapok=0;
                            }else {
				$gapok=$gapokgp;
			    }
                            $ttlgapok=$ttlgapok+$gapok;
							
			    $tnjjbtn=0;
		            if(empty ($baris[15])){
                                $tnjjbtn=0;
                            }else {
				$tnjjbtn=$baris[15];
			    }
                            $ttltnjjbtn=$ttltnjjbtn+$tnjjbtn;

                            $_sql17  ="SELECT tnj from set_jgmlm ";
			    $hasil17 =bukaquery($_sql17);
			    $baris17 = mysql_fetch_array($hasil17);
			    $tmbhjgmlm = $sisamlm*$baris17[0];
                            $ttltmbhjgmlm=$ttltmbhjgmlm+$tmbhjgmlm;
				
			    $_sql18  ="SELECT tnj from set_jgtambah where pendidikan='$baris[16]' ";
			    $hasil18 =bukaquery($_sql18);
			    $baris18 = mysql_fetch_array($hasil18);
			    $tmbahanjg =0;
                            $alpha=$baris18[0]*$ttla;
			    if(($tmbh>0)){
				 $tmbahanjg=$tmbh*$baris18[0];
			    }
                            $ttltmbahanjg=$ttltmbahanjg+$tmbahanjg;
				
			    $_sql19  ="SELECT tnj from set_hadir ";
			    $hasil19 =bukaquery($_sql19);
			    $baris19 = mysql_fetch_array($hasil19);
			    $tnjhadir =0;
			    if(($ttln>=$jmlmsk)&&($jmlmsk!=0)){
				 $tnjhadir=$baris19[0];
			    }
                            $ttltnjhadir=$ttltnjhadir+$tnjhadir;
                            
                            //potongan kurang kehadiran 25.000
                            $ptg_krghadir=0;
                            if($ttln<$jmlmsk){
                                $ptg_krghadir=25000;
                            }

                            $_sql20  ="SELECT tnj from set_lemburhb";
			    $hasil20 =bukaquery($_sql20);
			    $baris20 = mysql_fetch_array($hasil20);
			    $lemburhb=$hb*$baris20[0];
                            $ttllemburhb=$ttllemburhb+$lemburhb;

                            $_sql21  ="SELECT tnj from set_lemburhr";
			    $hasil21 =bukaquery($_sql21);
			    $baris21 = mysql_fetch_array($hasil21);
			    $lemburhr=$hr*$baris21[0];
                            $ttllemburhr=$ttllemburhr+$lemburhr;

                            $_sql22="";
                            if($baris[6]!="DIR"){
                                $_sql22  ="SELECT ($ttln/sum(n))*100 from indekref where kdindex='$baris[6]'";
                            }else if($baris[6]=="DIR"){
                                $_sql22  ="SELECT ($ttln/sum(n))*100 from indekref where kdindex='MNJ'";
                            }
                            
			    $hasil22 =bukaquery($_sql22);
			    $baris22 = mysql_fetch_array($hasil22);
			    $indexjaga=round($baris22[0],2);

                            /*if($baris[0]=="1"){
                                $indexjaga=0;
                            }*/                            
                            
                            $ttlindexjaga=$ttlindexjaga+$indexjaga;
                            
                            $_sqlpassum  ="select sum(jumpasien.jml) from jumpasien  
                                 where thn='".$tahun."' and bln='".$bulanindex."'";
                            $hasilpassum =bukaquery($_sqlpassum);
                            $barispassum = mysql_fetch_array($hasilpassum);
                            $indexpassum=$barispassum[0];
                            $indexpas   =0;
                            if(!empty ($indexpassum)){
                                $_sqlpas  ="select (jumpasien.jml/$indexpassum)*100
                                from jumpasien  where id='$baris[0]' 
			        and thn='".$tahun."' and bln='".$bulanindex."'";
                                $hasilpas =bukaquery($_sqlpas);
                                $barispas = mysql_fetch_array($hasilpas);
                                $indexpas=$barispas[0];
                            }  
				
			    $ttlindex=$total+$indexjaga+$indexpas;

                            $_sql24="select sum(tindakan.jm)
                                from tindakan
                                where tindakan.id='$baris[0]' and tindakan.tgl like '%".$tahun."-".$bulan."%'
                                group by tindakan.id";
			    $hasil24=bukaquery($_sql24);
			    $baris24 = mysql_fetch_array($hasil24);
			    $jm   = $baris24[0];
			    if(empty ($baris24[0])){
                                $jm=0;
                            }else {
				$jm=$baris24[0];
			    }
                            $ttljm=$ttljm+$jm;

                            $_sql26="select sum(jasa_lain.bsr_jasa)
                                from jasa_lain  where id='$baris[0]' 
			        and thn='".$tahun."' and bln='".$bulanindex."'
                                group by jasa_lain.id";
			    $hasil26=bukaquery($_sql26);
			    $baris26 = mysql_fetch_array($hasil26);

                            $_sql27="select (pembagian_akte.persen/100)*set_akte.bagian_kry
                                from pembagian_akte,set_akte  where pembagian_akte.id='$baris[0]'
			        and set_akte.tahun='".$tahun."' and set_akte.bulan='".$bulanindex."'";
			    $hasil27=bukaquery($_sql27);
			    $baris27 = mysql_fetch_array($hasil27);

                            $_sql28="select (pembagian_resume.persen/100)*set_resume.pendapatan_resume
                                from pembagian_resume,set_resume  where pembagian_resume.id='$baris[0]'
			        and set_resume.tahun='".$tahun."' and set_resume.bulan='".$bulanindex."'";
			    $hasil28=bukaquery($_sql28);
			    $baris28 = mysql_fetch_array($hasil28);

                            $_sql29="select (pembagian_tuslah.persen/100)*set_tuslah.pendapatan_tuslah
                                from pembagian_tuslah,set_tuslah  where pembagian_tuslah.id='$baris[0]'
			        and set_tuslah.tahun='".$tahun."' and set_tuslah.bulan='".$bulanindex."'";
			    $hasil29=bukaquery($_sql29);
			    $baris29 = mysql_fetch_array($hasil29);

                            $_sql31="SELECT ($ttlindex/sum(indextotal.ttl))*set_warung.bagian_kry
                                from indextotal,set_warung,pembagian_warung where pembagian_warung.id='$baris[0]'
                                and set_warung.tahun='$tahun' and set_warung.bulan='$bulanindex'
                                and indextotal.kdindex='$baris[6]'";
			    $hasil31=bukaquery($_sql31);
			    $baris31 = mysql_fetch_array($hasil31);

			    $jl   = $baris26[0]+$baris27[0]+$baris28[0]+$baris29[0]+$baris31[0];
			    if(empty($baris26[0])&&empty($baris27[0])&&empty($baris28[0])&&empty($baris29[0])&&empty($baris31[0])){
                                $jl=0;
                            }else {
				$jl   = $baris26[0]+$baris27[0]+$baris28[0]+$baris29[0]+$baris31[0];
			    }
                            $ttljasalain=$ttljasalain+$jl;

                        echo "<tr class='isi' title='$baris[1] $baris[2]'>
                                 <td valign='top'><a target=_blank href=../penggajian/pages/lampiran/SlipGaji.php?&id=$baris[0]>- $baris[1]</a></td>
                                 <td valign='top'><a target=_blank href=../penggajian/pages/lampiran/SlipDokter.php?&id=$baris[0]>$baris[2]</a></td>
                                 <td valign='top'><a target=_blank href=../penggajian/pages/lampiran/SlipGajisp.php?&id=$baris[0]>$baris[3]</a></td>
                                 <td valign='top'><a target=_blank href=../penggajian/pages/lampiran/SlipGajisp2.php?&id=$baris[0]>$baris[4]</a></td>
                                 <td valign='top'><a target=_blank href=../penggajian/pages/lampiran/SlipThr.php?&id=$baris[0]&jmlmsk=$jmlmsk&gapokgp=$gapokgp>$baris[5]</a></td>
                                 <td valign='top'>$baris[6]</td>
                                 <td valign='top'>$total</td>
                                 <td valign='top'>
                                     <table width='100%' cellpadding='0' cellspacing='0'>
                                       <TR class='isi'>
                                         <TD width='20px'>WJB</TD>
                                         <TD width='20px'>N</TD>
                                         <TD width='20px'><a href=?act=InputTambahJaga&action=TAMBAH&id=$baris[0]>+<a>/<a href=?act=InputTidakHadir&action=TAMBAH&id=$baris[0]>-</a></TD>
                                         <TD width='20px'><a href=?act=InputJagaMalam&action=TAMBAH&id=$baris[0]>MLM</a></TD>
                                         <TD width='20px'>+/-</TD>
                                         <TD width='20px'>KS</TD>
                                         <TD width='20px'>A</TD>
                                         <TD width='20px'>S</TD>
                                         <TD width='20px'>C</TD>
                                         <TD width='20px'>I</TD>
                                       </TR>
                                       <TR class='isi'>
                                         <TD valign='top'>$jmlmsk</TD> 
                                         <TD valign='top'>$ttln</TD>
                                         <TD valign='top'>$tmbh</TD>
                                         <TD valign='top'>$jgmlm</TD>
                                         <TD valign='top'>$sisamlm</TD>
                                         <TD valign='top'>$ks</TD>
                                         <TD valign='top'>$ttla</TD>
                                         <TD valign='top'>$ttls</TD>
                                         <TD valign='top'>$ttlc</TD>
                                         <TD valign='top'>$ttli</TD>
                                       </TR>
                                     </table>
				 </td>
                                 <td valign='top'>
                                     <table width='100%' cellpadding='0' cellspacing='0'>
                                       <TR class='isi'>
                                         <TD width='20px'><a href=?act=DetailPresensi&action=TAMBAH&id=$baris[0]>HB</a></TD>
                                         <TD width='20px'><a href=?act=DetailPresensi&action=TAMBAH&id=$baris[0]>HR</a></TD>
                                       </TR>
				       <TR class='isi'>
                                         <TD valign='top'>$hb</TD>
                                         <TD valign='top'>$hr</TD>
                                       </TR>
                                     </table>
				  </td>
				  <td valign='top'>
                                     <table width='100%' cellpadding='0' cellspacing='0'>
                                       <TR class='isi'>
                                         <TD width='100px'>Gaji Pokok</TD>
					 <TD width='100px'>Tnj.Jabatan</TD>
                                         <TD width='100px'>Tunjangan</TD>
                                         <TD width='100px'>Tmbh.Jg.Mlm</TD>
					 <TD width='100px'>Tmbh.jaga</TD>
					 <TD width='100px'>Tnj.Kehadiran</TD>
					 <TD width='120px'><i>Jml.Gaji & Tunj</i></TD>
                                       </TR>
				       <TR class='isi'>
                                         <TD valign='top'>".formatDuit($gapok)."</TD>
					 <TD valign='top'>".formatDuit($tnjjbtn)."</TD>
                                         <TD valign='top'>
                                            <table width='100%' cellpadding='0' cellspacing='0'>";
                                            echo "<tr class='isi3' cellpadding='0' cellspacing='0'><td width='150px' valign='top'>Tunjangan KaSift</td><td valign='top'>: ".formatDuit($ttlkasift)."</td></tr>";

                                            $_sql16="select master_tunjangan_harian.nama,
						    master_tunjangan_harian.tnj,master_tunjangan_harian.id
						    from pnm_tnj_harian,master_tunjangan_harian
						    where pnm_tnj_harian.id_tnj=master_tunjangan_harian.id
						    and pnm_tnj_harian.id='$baris[0]'";
                                            $hasil16=bukaquery($_sql16);
                                            $tnjtnj=0;
                                            while($baris16 = mysql_fetch_array($hasil16)) {
                                                $tunjanganpengurang=0;
						$_sqltnjpengurang="select master_tunjangan_bulanan.tnj 
                                                    from master_tunjangan_bulanan inner join harian_kurangi_bulanan
                                                    inner join pnm_tnj_bulanan on master_tunjangan_bulanan.id=harian_kurangi_bulanan.bulanan
						    and pnm_tnj_bulanan.id_tnj=harian_kurangi_bulanan.bulanan where
						    harian_kurangi_bulanan.harian='".$baris16["id"]."' and pnm_tnj_bulanan.id='".$baris[0]."' ";
						$hasilpengurang=bukaquery($_sqltnjpengurang);
						$barispengurang=mysql_fetch_array($hasilpengurang);
						$tunjanganpengurang=$barispengurang["tnj"];						
						$nilaitunjangan=0;
						$nilaitunjangan=($ttln*$baris16[1])-$tunjanganpengurang;
						if($nilaitunjangan<0){
                                                    $nilaitunjangan=0;
						}
												 
                                                $tnjtnj=$tnjtnj+$nilaitunjangan;
                                                echo "<tr class='isi3'><td width='150px'>$baris16[0]</td><td>: ".formatDuit($nilaitunjangan)."</td></tr>";
                                            }
                                            
                                            $_sql50="select master_tunjangan_bulanan.nama,
				             master_tunjangan_bulanan.tnj
					     from pnm_tnj_bulanan,master_tunjangan_bulanan
					     where pnm_tnj_bulanan.id_tnj=master_tunjangan_bulanan.id
					     and pnm_tnj_bulanan.id='$baris[0]'";
                                            $hasil50=bukaquery($_sql50);
                                            $tnjtnjbln=0;
                                            while($baris50 = mysql_fetch_array($hasil50)) {
                                                $tnjtnjbln=$tnjtnjbln+$baris50[1];
                                                echo "<tr class='isi3' cellpadding='0' cellspacing='0'><td width='150px' valign='top'>$baris50[0]</td><td valign='top'>: ".formatDuit($baris50[1])."</td></tr>";
                                            }
                                            $ttltnjtnj=$ttltnjtnj+$tnjtnj+$tnjtnjbln+$ttlkasift;
                                            $ttljmlgaji=$ttljmlgaji+$gapok+$tnjjbtn+$tnjtnj+$ttlkasift+$tmbhjgmlm+$tmbahanjg+$tnjhadir;
                                            echo"
                                            </table>
                                         </TD>
                                         <TD valign='top'>".formatDuit($tmbhjgmlm)."</TD>
					 <TD valign='top'>".formatDuit($tmbahanjg)."</TD>
					 <TD valign='top'>".formatDuit($tnjhadir)."</TD>
					 <TD valign='top'><i>".formatDuit($gapok+$tnjjbtn+$tnjtnj+$tnjtnjbln+$ttlkasift+$tmbhjgmlm+$tmbahanjg+$tnjhadir)."</i></TD>
                                       </TR>
                                     </table>
				 </td>
                                 <td valign='top'>
                                     <table width='100%' cellpadding='0' cellspacing='0'>
                                       <TR class='isi'>
                                         <TD width='100px'><a href=?act=DetailPresensi&action=TAMBAH&id=$baris[0]>Hari/Libur Biasa</a></TD>
                                         <TD width='100px'><a href=?act=DetailPresensi&action=TAMBAH&id=$baris[0]>Hari Raya</a></TD>
                                         <TD width='100px'><a href=?act=DetailPresensi&action=TAMBAH&id=$baris[0]><i>Jumlah Lembur</i></a></TD>
                                       </TR>
				       <TR class='isi'>
                                         <TD valign='top'>".formatDuit($lemburhb)."</TD>
                                         <TD valign='top'>".formatDuit($lemburhr)."</TD>
                                         <TD valign='top'><i>".formatDuit($lemburhb+$lemburhr)."</i></TD>
                                       </TR>
                                     </table>
				  </td>
				  <td valign='top'>
                                     <table width='100%' cellpadding='0' cellspacing='0'>
                                       <TR class='isi'>
                                         <TD width='65px'>Index Kary</TD>
					 <TD width='65px'><a href=?act=InputPasien&action=TAMBAH&id=$baris[0]>Index Jaga</a></TD>
					 <TD width='65px'>Total Index</TD>
                                         <TD width='100px'>Total Insentif</TD>
                                         <TD width='100px'><a href=?act=InputTindakan&action=TAMBAH&id=$baris[0]>Tindakan Medis</a></TD>
                                         <TD width='100px'><a href=?act=InputJasaLain&action=TAMBAH&id=$baris[0]>Jasa Lain</a></TD>
                                         <TD width='100px'><i>Jml.Tambahan</i></TD>
                                       </TR>
				       <TR class='isi'>
                                         <TD valign='top'>$total</TD>
					 <TD valign='top'>$indexjaga</TD>
					 <TD valign='top'>$ttlindex</TD>
                                         <TD width='100px' valign='top'>";
                                            $_sql23="";
                                            if($baris[6]!="DIR"){
                                                $_sql23="SELECT ($ttlindex/sum(indextotal.ttl))*((indexins.persen/100)*total_insentif)
                                                    from indextotal,indexins,set_insentif where
                                                    set_insentif.tahun='$tahun' and set_insentif.bulan='$bulanindex' and
                                                    indextotal.kdindex=indexins.dep_id and
                                                    indextotal.kdindex='$baris[6]'";
                                                
                                            }else if($baris[6]=="DIR"){
                                                $_sql23="SELECT ($ttlindex/sum(indextotal.ttl))*((indexins.persen/100)*total_insentif)*2.3
                                                    from indextotal,indexins,set_insentif where
                                                    set_insentif.tahun='$tahun' and set_insentif.bulan='$bulanindex' and
                                                    indextotal.kdindex=indexins.dep_id and
                                                    indextotal.kdindex='MNJ'";
                                                if($baris[0]=="43"){
                                                    $_sql23="SELECT ($ttlindex/sum(indextotal.ttl))*((indexins.persen/100)*total_insentif)*1.80
                                                    from indextotal,indexins,set_insentif where
                                                    set_insentif.tahun='$tahun' and set_insentif.bulan='$bulanindex' and
                                                    indextotal.kdindex=indexins.dep_id and
                                                    indextotal.kdindex='MNJ'";                                                    
                                                }

                                            }
                                            
                                            $hasil23=bukaquery($_sql23);                                            
                                            $baris23 = mysql_fetch_array($hasil23);
                                            $ttlinsentif=$baris23[0];
                                            $ttlttlinsentif=$ttlttlinsentif+$ttlinsentif;
                                            $jmltmb=$ttlinsentif+$jm+$jl;
                                            $ttljmltmb=$ttljmltmb+$jmltmb;

                                            $ttlgaji=$jmltmb+$lemburhb+$lemburhr+$gapok+$tnjjbtn+$tnjtnj+$tnjtnjbln+$ttlkasift+$tmbhjgmlm+$tmbahanjg+$tnjhadir;
                                            $ttlttlgaji=$ttlttlgaji+$ttlgaji;
                                            echo formatDuit($ttlinsentif)."
                                         </TD>
                                         <TD valign='top'>".formatDuit($jm)."</TD>
                                         <TD valign='top'>".formatDuit($jl)."</TD>
                                         <TD valign='top'><i>".formatDuit($jmltmb)."</i></TD>
                                       </TR>
                                     </table>
				  </td>
                                  <td valign='top'><font size='2' color='green'><i>".formatDuit($ttlgaji)."</i></font></td>
                                  <td valign='top'>";
                                        $_sql25="select potongan.jamsostek,
                                                potongan.dansos,
                                                potongan.simwajib,
                                                potongan.angkop,
                                                potongan.angla,
                                                potongan.telpri,
                                                potongan.pajak,
                                                potongan.pribadi,
                                                potongan.lain,
                                                potongan.bpjs from potongan
                                                where potongan.id='$baris[0]' and
                                                potongan.tahun='$tahun' and potongan.bulan='$bulanindex' ";
                                        $hasil25=bukaquery($_sql25);
                                        $baris25 = mysql_fetch_array($hasil25);
                                        $jamsostek   = $baris25[0];
                                        $dansos      = $baris25[1];
                                        $simwajib    = $baris25[2];
                                        $angkop      = $baris25[3];
                                        $angla       = $baris25[4];
                                        $telpri      = $baris25[5];
                                        $pajak       = $baris25[6];
                                        $pribadi     = $baris25[7];
                                        $lain        = $baris25[8];
                                        $bpjs        = $baris25[9];
                                        
                                        if(empty ($baris25[0])){
                                            $jamsostek=0;
                                        }else {
                                            $jamsostek=$baris25[0];
                                        }
                                        $ttljamsostek=$ttljamsostek+$jamsostek;
                                        
                                        if(empty ($baris25[1])){
                                            $dansos=0;
                                        }else {
                                            $dansos=$baris25[1];
                                        }
                                        $ttldansos=$ttldansos+$dansos;

                                        if(empty ($baris25[2])){
                                            $simwajib=0;
                                        }else {
                                            $simwajib=$baris25[2];
                                        }
                                        $ttlsimwajib=$ttlsimwajib+$simwajib;

                                        if(empty ($baris25[3])){
                                            $angkop=0;
                                        }else {
                                            $angkop=$baris25[3];
                                        }
                                        $ttlangkop=$ttlangkop+$angkop;

                                        if(empty ($baris25[4])){
                                            $angla=0;
                                        }else {
                                            $angla=$baris25[4];
                                        }
                                        $ttlangla=$ttlangla+$angla;

                                        if(empty ($baris25[5])){
                                            $telpri=0;
                                        }else {
                                            $telpri=$baris25[5];
                                        }
                                        $ttltelpri=$ttltelpri+$telpri;

                                        if(empty ($baris25[6])){
                                            $pajak=0;
                                        }else {
                                            $pajak=$baris25[6];
                                        }
                                        $ttlpajak=$ttlpajak+$pajak;

                                        if(empty ($baris25[7])){
                                            $pribadi=0;
                                        }else {
                                            $pribadi=$baris25[7];
                                        }
                                        $ttlpribadi=$ttlpribadi+$pribadi;

                                        if(empty ($baris25[8])){
                                            $lain=0+$alpha;
                                        }else {
                                            $lain=$baris25[8]+$alpha;
                                        }
                                        $ttllain=$ttllain+$lain;
                                        
                                        if(empty ($baris25[9])){
                                            $bpjs=0;
                                        }else {
                                            $bpjs=$baris25[9];
                                        }
                                        $ttlbpjs=$ttlbpjs+$bpjs;

                                        $ttlditerima=$ttlgaji-($bpjs+$jamsostek+$dansos+$simwajib+$angkop+$angla+$telpri+$pajak+$pribadi+$lain);
                                        $ttlttlditerima=$ttlttlditerima+$ttlditerima;
			         echo "
                                     <table width='100%' cellpadding='0' cellspacing='0'>
                                       <TR class='isi'>
                                         <TD width='100px' valign='top'><a href=?act=InputPotongan&action=UBAH&id=$baris[0]>BPJS</a></TD>
                                         <TD width='100px' valign='top'><a href=?act=InputPotongan&action=UBAH&id=$baris[0]>Jamsostek</a></TD>
                                         <TD width='100px' valign='top'><a href=?act=InputPotongan&action=UBAH&id=$baris[0]>Dana Sosial</a></TD>
                                         <TD width='100px' valign='top'><a href=?act=InputPotongan&action=UBAH&id=$baris[0]>Simp.Wajib</a></TD>
                                         <TD width='100px' valign='top'><a href=?act=InputPotongan&action=UBAH&id=$baris[0]>Ang.Koperasi</a></TD>
                                         <TD width='100px' valign='top'><a href=?act=InputPotongan&action=UBAH&id=$baris[0]>Ang.Lain</a></TD>
                                         <TD width='100px' valign='top'><a href=?act=InputPotongan&action=UBAH&id=$baris[0]>Telp.Pribadi</a></TD>
                                         <TD width='100px' valign='top'><a href=?act=InputPotongan&action=UBAH&id=$baris[0]>Pajak</a></TD>
                                         <TD width='100px' valign='top'><a href=?act=InputPotongan&action=UBAH&id=$baris[0]>Pribadi</a></TD>
                                         <TD width='100px' valign='top'><a href=?act=InputPotongan&action=UBAH&id=$baris[0]>Potongan Lain</a></TD>
                                       </TR>
				       <TR class='isi'>
                                         <TD valign='top'>".formatDuit($bpjs)."</TD>
                                         <TD valign='top'>".formatDuit($jamsostek)."</TD>
                                         <TD valign='top'>".formatDuit($dansos)."</TD>
                                         <TD valign='top'>".formatDuit($simwajib)."</TD>
                                         <TD valign='top'>".formatDuit($angkop)."</TD>
                                         <TD valign='top'>".formatDuit($angla)."</TD>
                                         <TD valign='top'>".formatDuit($telpri)."</TD>
                                         <TD valign='top'>".formatDuit($pajak)."</TD>
                                         <TD valign='top'>".formatDuit($pribadi)."</TD>
                                         <TD valign='top'>".formatDuit($lain)."</TD>
                                     </table>
				  </td>
                                  <TD valign='top'><font size='2' color='green'><i>".formatDuit($ttlditerima)."</i></font></TD>
                               </tr>";
                    }
                    $ttlttlindex=$ttlindexjaga+$ttltotal;
            echo "<tr class='head'>
                         <td COLSPAN='9' valign='top' ><div align='right'>Total :&nbsp; </div></td>
                         <td valign='top'>
                                     <table cellpadding='0' cellspacing='0' width='100%'>
				       <TR class='isi5'>
                                         <TD width='100px' valign='top'>".formatDuit($ttlgapok)."</TD>
					 <TD width='100px' valign='top'>".formatDuit($ttltnjjbtn)."</TD>
                                         <TD width='300px' valign='top'>".formatDuit($ttltnjtnj)."</TD>
                                         <TD width='100px' valign='top'>".formatDuit($ttltmbhjgmlm)."</TD>
					 <TD width='100px' valign='top'>".formatDuit($ttltmbahanjg)."</TD>
					 <TD width='100px' valign='top'>".formatDuit($ttltnjhadir)."</TD>
					 <TD width='120px' valign='top'><i>".formatDuit($ttljmlgaji)."</i></TD>
                                       </TR>
                                     </table>
			 </td>
                         <td valign='top'>
                                     <table cellpadding='0' cellspacing='0' width='100%'>
				       <TR class='isi5'>
                                         <TD width='100px' valign='top'>".formatDuit($ttllemburhb)."</TD>
                                         <TD width='100px' valign='top'>".formatDuit($ttllemburhr)."</TD>
                                         <TD width='100px' valign='top'><i>".formatDuit($ttllemburhb+$ttllemburhr)."</i></TD>
                                       </TR>
                                     </table>
			 </td>
                         <td valign='top'>
                                     <table cellpadding='0' cellspacing='0' width='100%'>
				       <TR class='isi5'>
                                         <TD width='65px' valign='top'>$ttltotal</TD>
					 <TD width='65px' valign='top'>$ttlindexjaga</TD>
					 <TD width='65px' valign='top'>$ttlttlindex</TD>
                                         <TD width='105px' valign='top'>".formatDuit($ttlttlinsentif)."</TD>
                                         <TD width='105px' valign='top'>".formatDuit($ttljm)."</TD>
                                         <TD width='105px' valign='top'>".formatDuit($ttljasalain)."</TD>
                                         <TD width='105px' valign='top'><i>".formatDuit($ttljmltmb)."</i></TD>
                                       </TR>
                                     </table>
			 </td>
                         <td width='100px' valign='top'><div align='center'><font size='2' color='green'><i>".formatDuit($ttlttlgaji)."</i></font></div></td>
                         <td valign='top'>
                                     <table cellpadding='0' cellspacing='0' width='100%'>
				       <TR class='isi5'>
                                         <TD width='100px' valign='top'>".formatDuit($ttlbpjs)."</TD>
                                         <TD width='100px' valign='top'>".formatDuit($ttljamsostek)."</TD>
                                         <TD width='100px' valign='top'>".formatDuit($ttldansos)."</TD>
                                         <TD width='100px' valign='top'>".formatDuit($ttlsimwajib)."</TD>
                                         <TD width='100px' valign='top'>".formatDuit($ttlangkop)."</TD>
                                         <TD width='100px' valign='top'>".formatDuit($ttlangla)."</TD>
                                         <TD width='100px' valign='top'>".formatDuit($ttltelpri)."</TD>
                                         <TD width='100px' valign='top'>".formatDuit($ttlpajak)."</TD>
                                         <TD width='100px' valign='top'>".formatDuit($ttlpribadi)."</TD>
                                         <TD width='100px' valign='top'>".formatDuit($ttllain)."</TD>
                                     </table>
			 </td>
                         <TD valign='top'><font size='2' color='green'><i>".formatDuit($ttlttlditerima)."</i></font></TD>
                    </tr>
                  </table>";

        } else {echo "Data Lampiran masih kosong !";}
    ?>
    </div>
            </form>
       <?php
            if(mysql_num_rows($hasil)!=0) {
                echo("<table width='99.6%' border='0' align='center' cellpadding='0' cellspacing='0' class='tbl_form'>
                    <tr class='head'>
                        <td valign='top'><div align='left'>Data : $jumlah <a target=_blank href=../penggajian/pages/lampiran/LaporanLampiran.php?&keyword=$keyword>| Laporan1 </a>
                      <a target=_blank href=../penggajian/pages/lampiran/LaporanLampiran2.php?&keyword=$keyword>| Laporan2 </a>
                      <a target=_blank href=../penggajian/pages/lampiran/LaporanLampiran3.php?&keyword=$keyword>| Laporan3 </a>
                      <a target=_blank href=../penggajian/pages/lampiran/LaporanLampiran4.php?&keyword=$keyword>| Laporan4 </a>
                      <a target=_blank href=../penggajian/pages/lampiran/LaporanLampiran5.php?&keyword=$keyword>| Laporan5 </a>
                      <a target=_blank href=../penggajian/pages/lampiran/LaporanTransfer.php?&keyword=$keyword>| Transfer1 </a>
                      <a target=_blank href=../penggajian/pages/lampiran/LaporanTransfer2.php?&keyword=$keyword>| Transfer2 </a>
                      <a target=_blank href=../penggajian/pages/lampiran/LaporanCash.php?&keyword=$keyword>| Cash1 </a>
                      <a target=_blank href=../penggajian/pages/lampiran/LaporanCash2.php?&keyword=$keyword>| Cash2 </a>
                      <a target=_blank href=../penggajian/pages/lampiran/LaporanTHR.php?&keyword=$keyword>| Laporan THR </a>
                      <a target=_blank href=../penggajian/pages/lampiran/LaporanTHR2.php?&keyword=$keyword>| Laporan THR2 </a>
                      <a target=_blank href=../penggajian/pages/lampiran/LaporanTHR3.php?&keyword=$keyword>| Laporan THR3 </a>
                      <a target=_blank href=../penggajian/pages/lampiran/listlampiran2.php?&keyword=$keyword>| Browser Penggajian </a></div></td>                        
                    </tr>     
                 </table>");
             }
       ?>
    </div>
</div>
