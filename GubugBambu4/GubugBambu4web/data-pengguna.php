<?php
    require_once 'koneksi.php';
    
    $email = $_POST['email'];
    $password = md5($_POST['password']);
    
    $sql = "SELECT * FROM users WHERE email='$email' AND password='$password' LIMIT 1";
    $hasil = $db->query($sql);
    if($hasil->num_rows > 0){
        $baris = $hasil->fetch_assoc();
        $hasilCetak['id'] = $baris['id'];
        $hasilCetak['username'] = $baris['username'];
        $hasilCetak['fullname'] = $baris['fullname'];
		$hasilCetak['foto'] = $baris['foto'];

    }else{
         $hasilCetak['id'] = '';
        $hasilCetak['username'] = '';
        $hasilCetak['fullname'] = '';
		$hasilCetak['foto'] = '';
    }
   
    echo json_encode($hasilCetak);
?>