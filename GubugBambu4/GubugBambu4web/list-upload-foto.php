<?php
        require_once 'koneksi.php';

        $hasilCetak = array(
                                "data" => []
                           );
        
        $id = $_POST['id'];

         $sql="SELECT * FROM users ";
         $hasil = $db->query($sql);
         if($hasil->num_rows > 0){
            while($baris = $hasil->fetch_assoc()){
                if($id == $baris['id']){
                    continue;
                }else{
                    $hasilCetak["data"][] = $baris;
                }
                
            }
        }
        echo json_encode($hasilCetak);

?>