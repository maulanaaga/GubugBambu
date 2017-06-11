<?php
        require_once 'koneksi.php';

        $hasilCetak = array(
                                "data" => []
                           );
						   
						   $namatoko = $_POST['namatoko'];
						   
         $sql="SELECT * FROM uploadfoto WHERE namatoko='$namatoko'";
         $hasil = $db->query($sql);
         if($hasil->num_rows > 0){
            while($baris = $hasil->fetch_assoc()){
                $hasilCetak["data"][] = $baris;
            }
        }
        echo json_encode($hasilCetak);

?>