<?php
require_once'Service.php'; 
try{
	if (isset($_FILES["myimage"])){
		echo "cookies";
$imagename = $_FILES["myimage"]["name"];
$imagetmp = addslashes(file_get_contents($_FILES['myimage']['tmp_name']));
$insert_image ="INSERT INTO IMAGES (EMAILS,IMAGE) VALUES ('$imagename','$imagetmp');"; 
Service::openConnection();
if(mysqli_query(Service::$conn,$insert_image)===true){
	echo "successful";
} else {
	echo "error";
}
Service::closeConnection();
} 
else 
echo "getting there";
}  catch(Exception $e){
		echo 'Caught exception: ',  $e->getMessage(), "\n";
}

 ?>
