<?php
        
        require_once 'koneksi.php';


       $email = $_POST['email'];
       $password = md5($_POST['password']);


        $sql = "SELECT * FROM `users` WHERE email='$email' AND password='$password'";
        $result = $db->query($sql);

        if($result->num_rows > 0){
            $hasil['kode']=1;
            $hasil['pesan']="sukses login";
        }else{
            $hasil['kode']=0;
            $hasil['pesan']="gagal login";
        }

        echo json_encode($hasil);

?>
