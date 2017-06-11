<?php
        require_once 'koneksi.php';

        $hasilCetak = array(
                                "data" => []
                           );
						   
						   $id = $_POST['id'];
						   
         $sql="SELECT * FROM uploadfoto WHERE jenisToko='Kost'";
         $hasil = $db->query($sql);
         if($hasil->num_rows > 0){
            while($baris = $hasil->fetch_assoc()){
                $hasilCetak["data"][] = $baris;
            }
        }
        echo json_encode($hasilCetak);

?>