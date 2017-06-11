<?php
        
        require_once 'koneksi.php';


       $id = $_POST['id'];


        $sql = "SELECT foto FROM `users` WHERE id='$id'";
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
