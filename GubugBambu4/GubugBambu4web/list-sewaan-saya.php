<?php
        require_once 'koneksi.php';

        $hasilCetak = array(
                                "data" => []
                           );
						   
						   $idPengguna = $_POST['idPengguna'];
						   
         $sql="SELECT * FROM uploadfoto WHERE idPengguna='$idPengguna'";
         $hasil = $db->query($sql);
         if($hasil->num_rows > 0){
            while($baris = $hasil->fetch_assoc()){
                $hasilCetak["data"][] = $baris;
            }
        }
        echo json_encode($hasilCetak);

?>