<?php
        
        require_once './koneksi.php';


        $email = $_POST['email'];
        $fullname = $_POST['fullname'];
        $username = $_POST['username'];
        $password = md5($_POST['password']);
		$telepon = $_POST['telepon'];
		$foto = $_POST['foto'];

        $sql = "INSERT INTO users(email,fullname,username,password, telepon, foto) VALUES('$email','$fullname','$username','$password', '$telepon', '$foto')";
        
        if($db->query($sql)==TRUE){
            $hasil['kode']=1;
            $hasil['pesan']="data berhasil di tambahkan";
        }else{
            $hasil['kode']=0;
            $hasil['pesan']="data gagal di tambahkan";
        }

        echo json_encode($hasil);

?>