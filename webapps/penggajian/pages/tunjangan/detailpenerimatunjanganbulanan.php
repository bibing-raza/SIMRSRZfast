
<div id="post">
    <div class="entry">
        <form name="frm_aturadmin" onsubmit="return validasiIsi();" method="post" action="" enctype=multipart/form-data>
            <?php
                echo "";
                $action             =isset($_GET['action'])?$_GET['action']:NULL;
		$id                 =isset($_GET['id'])?$_GET['id']:NULL;
                $id_tnj             =isset($_GET['id_tnj'])?$_GET['id_tnj']:NULL;
                echo "<input type=hidden name=id  value=$id><input type=hidden name=action value=$action>";
				$_sql = "SELECT nik,nama FROM pegawai where id='$id'";
                $hasil=bukaquery($_sql);
                $baris = mysql_fetch_row($hasil);

                $_sqlnext         	= "SELECT id FROM pegawai WHERE id>'$id' order by id asc limit 1";
                $hasilnext        	= bukaquery($_sqlnext);
                $barisnext        	= mysql_fetch_row($hasilnext);
                $next               = $barisnext[0];

                $_sqlprev         	= "SELECT id FROM pegawai WHERE id<'$id' order by id desc limit 1";
                $hasilprev        	= bukaquery($_sqlprev);
                $barisprev        	= mysql_fetch_row($hasilprev);
                $prev               = $barisprev[0];
                
                if(empty($prev)){
                    $prev=$next;
                }

                echo "<div align='center' class='link'>
                          <a href=?act=DetailPenerimaTunjanganBulanan&action=TAMBAH&id=$prev><<--</a>
                          <a href=?act=ListTunjangan>| List Penerima |</a>
                          <a href=?act=HomeAdmin>| Menu Utama |</a>
                          <a href=?act=DetailPenerimaTunjanganBulanan&action=TAMBAH&id=$next>-->></a>
                          </div>";
            ?>
            <table width="100%" align="center">
				<tr class="head">
                    <td width="31%" >NIP</td><td width="">:</td>
                    <td width="67%"><?php echo $baris[0];?></td>
                </tr>
				<tr class="head">
                    <td width="31%">Nama</td><td width="">:</td>
                    <td width="67%"><?php echo $baris[1];?></td>
                </tr>
                <tr class="head">
                    <td width="31%" >Nama Tunjangan</td><td width="">:</td>
                    <td width="67%">
                        <select name="id_tnj" class="text2" onkeydown="setDefault(this, document.getElementById('MsgIsi1'));" id="TxtIsi1">
                            <!--<option id='TxtIsi12' value='null'>- Ruang -</option>-->
                            <?php
                                $_sql = "SELECT id,nama,tnj FROM master_tunjangan_bulanan ORDER BY nama";
                                $hasil=bukaquery($_sql);
                                while($baris = mysql_fetch_array($hasil)) {
                                    echo "<option id='TxtIsi1' value='$baris[0]'>$baris[1]  ".formatDuit($baris[2])."</option>";
                                }
                            ?>
                        </select>
                        <span id="MsgIsi1" style="color:#CC0000; font-size:10px;"></span>
                    </td>
                </tr>
            </table>
            <div align="center"><input name=BtnSimpan type=submit class="button" value="SIMPAN">&nbsp<input name=BtnKosong type=reset class="button" value="KOSONG"></div><br>
            <?php
                $BtnSimpan=isset($_POST['BtnSimpan'])?$_POST['BtnSimpan']:NULL;
                if (isset($BtnSimpan)) {
		    $id                 =trim(isset($_POST['id']))?trim($_POST['id']):NULL;
                    $id_tnj             =trim(isset($_POST['id_tnj']))?trim($_POST['id_tnj']):NULL;
                    if ((!empty($id))&&(!empty($id_tnj))) {
                        switch($action) {
                            case "TAMBAH":
                                Tambah(" pnm_tnj_bulanan ","'$id','$id_tnj'", " Detail tunjangan bulanan diterima " );
                                echo"<meta http-equiv='refresh' content='1;URL=?act=DetailPenerimaTunjanganBulanan&action=TAMBAH&id=$id'>";
                                break;
                        }
                    }else if ((empty($id))||(empty($id_tnj))){
                        echo 'Semua field harus isi..!!!';
                    }
                }
            ?>
            <div style="width: 100%; height: 67%; overflow: auto;">
            <?php
                $_sql = "SELECT pnm_tnj_bulanan.id,
				         pnm_tnj_bulanan.id_tnj,
				         master_tunjangan_bulanan.nama,
						 master_tunjangan_bulanan.tnj 
						 from master_tunjangan_bulanan inner join pnm_tnj_bulanan
						 where pnm_tnj_bulanan.id_tnj=master_tunjangan_bulanan.id
						 and pnm_tnj_bulanan.id='$id'
						 ORDER BY master_tunjangan_bulanan.nama ASC ";
                $hasil=bukaquery($_sql);
                $jumlah=mysql_num_rows($hasil);

                if(mysql_num_rows($hasil)!=0) {
                    echo "<table width='99.6%' border='0' align='center' cellpadding='0' cellspacing='0' class='tbl_form'>
                            <tr class='head'>
                                <td width='10%'><div align='center'>Proses</div></td>
                                <td width='55%'><div align='center'>Nama Tunjangan</div></td>
                                <td width='35%'><div align='center'>Besar Tunjangan</div></td>
                            </tr>";
                    while($baris = mysql_fetch_array($hasil)) {
                      echo "<tr class='isi'>
                                <td>
                                    <center>";?>
                                    <a href="?act=DetailPenerimaTunjanganBulanan&action=HAPUS&id=<?php print $baris[0] ?>&id_tnj=<?php print $baris[1]; ?>" >[hapus]</a>
                            <?php
                            echo "</center>
                                </td>
                                <td>$baris[2]</td>
                                <td>".formatDuit($baris[3])."</td>
                           </tr>";
                    }
                echo "</table>";

            } else {echo "Data detail tunjangan bulanan yang diterima masih kosong !";}
        ?>
        </div>
        </form>
        <?php
            if ($action=="HAPUS") {
                Hapus(" pnm_tnj_bulanan "," id ='".$id."' and id_tnj='".$id_tnj."'  ","?act=DetailPenerimaTunjanganBulanan&action=TAMBAH&id=".$id);
            }

                echo("<table width='99.6%' border='0' align='center' cellpadding='0' cellspacing='0' class='tbl_form'>
                    <tr class='head'>
                        <td><div align='left'>Data : $jumlah</div></td>                        
                    </tr>     
                 </table>");
        
        ?>
    </div>

</div>
