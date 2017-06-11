<?php

	require_once './koneksi.php';


        $idPengguna = $_POST['idPengguna'];
		$namaFoto1= $_POST['namaFoto1'];

        $sql = "UPDATE `uploadfoto` SET namaFoto1='$namaFoto1' WHERE idPengguna='$idPengguna'";
        
        if($db->query($sql)==TRUE){
            $hasil['kode']=1;
            $hasil['pesan']="foto baru";
        }else{
            $hasil['kode']=0;
            $hasil['pesan']="data gagal diubah";
        }

        echo json_encode($hasil);

?>