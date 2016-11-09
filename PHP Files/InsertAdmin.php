<?php
require_once 'Admins.php';
require_once 'Conn.php';
require_once 'Service.php';
try {
	if (isset ( $_POST ['firstName'] ) && isset ( $_POST ['lastName'] ) && isset ( $_POST ['organization'] ) && isset ( $_POST ['email'] ) && isset ( $_POST ['family'] ) && isset ( $_POST ['password'] )) {
		Service::openConnection ();
		$firstName = $_POST ['firstName'];
		$organization = $_POST ['organization'];
		$lastName = $_POST ['lastName'];
		$Email = $_POST ['email'];
		$family = $_POST ['family'];
		$password = $_POST ['password'];
		Admins::insertAdmin ( $firstName, $lastName, $organization, $Email, $family, $password );
		echo "success \xA";
	} else {
		echo new Exception ( "No post exists" );
	}
} catch ( Exception $e ) {
	echo 'Caught exception: ', $e->getMessage (), "\n";
} finally{
	if(mysqli_ping(Service::$conn)){
		echo "X_X...";
	Service::closeConnection();
}
}
?>