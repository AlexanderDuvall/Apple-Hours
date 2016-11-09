<?php
require_once 'Messages.php';
require_once 'Service.php';
require_once 'Conn.php';
try{
Service::openConnection();
if (isset($_GET['family'])){
	$family = $_GET['family'];
	$a= messages::getMessages($family);
	for ($i=0; $i < count($a); $i++) { 
		if($i!=(count($a)-1))
		echo $a[$i].",";
		else 
		echo $a[$i];
	}
} else {
	echo new Exception("No such post exist");
}
} catch(Exception $e){
		echo 'Caught exception: ',  $e->getMessage(), "\n";
} finally{

	Service::closeConnection();

}
?>