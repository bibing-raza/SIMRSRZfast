<?php
    include '../../../conf/conf.php';
?>
<html>
    <head>
        <link href="../../css/default.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
   <?php
        $keyword=$_GET['keyword'];
        $_sql = "SELECT stts,ktg,indek FROM stts_kerja where stts like '%".$keyword."%' or ktg like '%".$keyword."%' ORDER BY indek desc";
        $hasil=bukaquery($_sql);
        $jumlah=mysql_num_rows($hasil);
        $no=1;
        if(mysql_num_rows($hasil)!=0) {
            echo "<table width='100%' border='0' align='center' cellpadding='0' cellspacing='0' class='tbl_form'>
                    <caption><h3><font color='999999'>Laporan Master Status Kerja</font></h3></caption>
                    <tr class='head'>
                        <td width='10%'><div align='center'>No.</strong></div></td>
                        <td width='28%'><div align='center'>Status</div></td>
                        <td width='40%'><div align='center'>Keterangan</div></td>
                        <td width='20%'><div align='center'>Index Status</div></td>
                    </tr>";
                    while($baris = mysql_fetch_array($hasil)) {
                        echo "<tr class='isi'>
								<td>$no</td>  
                                <td>$baris[0]</td>
                                <td>$baris[1]</td>
                                <td>$baris[2]</td>   
                             </tr>";$no++;
                    }
            echo "</table>";
        } 
    ?>
    </body>
</html>

