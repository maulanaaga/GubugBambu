<?php
        
        require_once './koneksi.php';


        $idPengguna = $_POST['idPengguna'];
        $namaFoto = $_POST['namaFoto'];
		$namaFoto1 = $_POST['namaFoto1'];
		$jenisToko = $_POST['jenisToko'];
        $tanggal = $_POST['tanggal'];
        $namatoko = $_POST['namatoko'];
        $hargasewa = $_POST['hargasewa'];
        $deskripsi = $_POST['deskripsi'];
		$telepon = $_POST['telepon'];
		$alamat = $_POST['alamat'];

        $sql = "INSERT INTO uploadfoto(idPengguna,namaFoto, namaFoto1,jenisToko,tanggal,namatoko,hargasewa,deskripsi, telepon, alamat) VALUES('$idPengguna','$namaFoto', '$namaFoto1', '$jenisToko','$tanggal','$namatoko','$hargasewa','$deskripsi', '$telepon', '$alamat')";
        
        if($db->query($sql)==TRUE){
            $hasil['kode']=1;
            $hasil['pesan']="foto berhasil di tambahkan";
        }else{
            $hasil['kode']=0;
            $hasil['pesan']="foto gagal di tambahkan";
        }

        echo json_encode($hasil);

?>