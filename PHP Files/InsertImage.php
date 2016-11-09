<?php
require_once 'Service.php';
require_once 'Conn.php';
require_once 'Images.php';
require_once 'Students.php';
try{
			Service::openConnection();
	echo $_POST['directory'];
	if (isset($_POST['email'])&&isset($_POST['directory'])){
		echo "1";
		$email = $_POST['email'];
		$directory = $_POST['directory'];
		Images::insertBlob($email, $directory);
		echo "success";
	}
	else {
		echo "2";
	}
} catch(Exception $e){
		echo 'Caught exception: ',  $e->getMessage(), "\n";
} finally{
	Service::closeConnection();
}

?>