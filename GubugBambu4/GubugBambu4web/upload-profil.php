<?php

/*$result = array("success" => $_FILES["file"]["name"]);
$file_path = basename( $_FILES['file']['name']);
if(move_uploaded_file($_FILES['file']['tmp_name'], $file_path)) {
    $result = array("success" => "File successfully uploaded");
} else{
    $result = array("success" => "error uploading file");
}
echo json_encode($result, JSON_PRETTY_PRINT);*/
$target_dir = "profil/";
$target_file_name = $target_dir .basename($_FILES["file"]["name"]);
$response = array();
 
// Check if image file is a actual image or fake image
if (isset($_FILES["file"])) 
{
 if (move_uploaded_file($_FILES["file"]["tmp_name"], $target_file_name)) 
 {
            $hasil['kode']=1;
            $hasil['pesan']="berhasil upload foto";
 }
 else 
 {
  $hasil['kode']=0;
            $hasil['pesan']="gagal upload foto";
 }
}
else 
{
            $hasil['kode']=0;
            $hasil['pesan']="gagal berhasil di hapus";
}

echo json_encode($hasil);



?>