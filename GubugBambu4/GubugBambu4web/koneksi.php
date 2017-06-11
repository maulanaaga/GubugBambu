<?php
        
        $databse = 'tamitasewa1';
        $host ='localhost';
        $user ='root';
        $pass ='';

        $db = new mysqli($host,$user,$pass,$databse);


        if($db->connect_errno !=0){
		  die('eror : '.$db->connect_error);		
        }	


?>