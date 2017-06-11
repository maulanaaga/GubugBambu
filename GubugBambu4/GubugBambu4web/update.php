<?php

	require_once './koneksi.php';


        $id = $_POST['id'];
        $fullname = $_POST['fullname'];
        $username = $_POST['username'];
        $password = md5($_POST['password']);
		$telepon = $_POST['telepon'];
		$foto= $_POST['foto'];

        $sql = "UPDATE `users` SET fullname='$fullname', username='$username', password='$password', telepon='$telepon', foto='$foto' WHERE id='$id'";
        
        if($db->query($sql)==TRUE){
            $hasil['kode']=1;
            $hasil['pesan']="data berhasil diubah";
        }else{
            $hasil['kode']=0;
            $hasil['pesan']="data gagal diubah";
        }

        echo json_encode($hasil);

?>