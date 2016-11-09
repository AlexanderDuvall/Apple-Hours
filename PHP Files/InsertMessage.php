<?php
require_once 'Messages.php';
require_once 'Service.php';
require_once 'Conn.php';
require_once 'Query.php';
try{
if (isset ( $_POST ['message'] ) && isset ( $_POST ['email'] )) {
	Service::openConnection ();
	$Message = $_POST ['message'];
	$Email = $_POST ['email'];
	messages::insertMessage ( $Message, $Email );
	echo "success\xA";
} else {
	echo mysqli_error ( Service::$conn );
	echo new Exception ( "No such posts exist" );
}
} catch (Exception $e){
		echo 'Caught exception: ',  $e->getMessage(), "\n";
} finally{
	if(mysqli_ping(Service::$conn)){
		echo "X_X...";
	Service::closeConnection();
}
}
?>