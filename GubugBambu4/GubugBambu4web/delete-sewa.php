<?php
        
        require_once './koneksi.php';


        $id = $_POST['id'];

        $sql = "DELETE FROM uploadfoto WHERE id='$id'";
        
        if($db->query($sql)==TRUE){
            $hasil['kode']=1;
            $hasil['pesan']="Sewaan Dihapus";
        }else{
            $hasil['kode']=0;
            $hasil['pesan']="Sewaan Gagal Dihapus";
        }

        echo json_encode($hasil);

?>