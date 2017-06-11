<?php

	require_once './koneksi.php';


        $email = $_POST['email'];
        $username = $_POST['username'];
        $password = md5($_POST['password']);
		
        $sql = "UPDATE `users` SET password='$password' WHERE email='$email' AND username='$username'";
        
        if($db->query($sql)==TRUE){
            $hasil['kode']=1;
            $hasil['pesan']="Berhasil Reset Password";
        }else{
            $hasil['kode']=0;
            $hasil['pesan']="Gagal Reset Password";
        }

        echo json_encode($hasil);

?>