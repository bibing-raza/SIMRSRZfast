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
        $_sql = "SELECT namabank FROM bank where namabank like '%".$keyword."%' ORDER BY namabank ";
        $hasil=bukaquery($_sql);
        $jumlah=mysql_num_rows($hasil);
        $no=1;
        if(mysql_num_rows($hasil)!=0) {
            echo "<table width='100%' border='0' align='center' cellpadding='0' cellspacing='0' class='tbl_form'>
                    <caption><h3><font color='999999'>Laporan Master Bank</font></h3></caption>
                    <tr class='head'>
                        <td width='10%'><div align='center'>No.</strong></div></td>
                        <td width='88%'><div align='center'>Nama Bank</div></td>
                    </tr>";
                    while($baris = mysql_fetch_array($hasil)) {
                        echo "<tr class='isi'>
				<td>$no</td>  
                                <td>$baris[0] &nbsp;</td>
                             </tr>";$no++;
                    }
            echo "</table>";
        } 
    ?>
    </body>
</html>
