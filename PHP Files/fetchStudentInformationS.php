<?php
require_once 'Students.php';
require_once 'Service.php';
require_once 'Conn.php';
try{
$arg;
Service::openConnection();
if (isset ( $_GET ['email'] )) {
	$email = $_GET ['email'];
	$arg = Students::getStudentsStatsByPlace ( $email );

} 
else
	echo new Exception ( "No such posts exist" );

	for ($i=0; $i < count($arg); $i++) { 
		echo $arg[$i][0].",". $arg[$i][1] .",".$arg[$i][2]."\xA";
	}

} catch(Exception $e){
	echo 'Caught exception: ',  $e->getMessage(), "\n";
} finally{
	Service::closeConnection();
}
?>