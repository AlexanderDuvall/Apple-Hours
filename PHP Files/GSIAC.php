<?php
require_once 'Admins.php';
require_once 'Service.php';
require_once 'Conn.php';
try {
	$arg;

	Service::openConnection ();
	if (isset ( $_GET ['family'] ) && isset ( $_GET ['order'] )) {
		$family = $_GET ['family'];
		$order = $_GET ['order'];
		if (strcmp ( $order, "grade" ))
			$arg = Query::getInformationArrayBroadComputer("students", "Name", "Email", "Grade","Hours", "family", $family,  $order );
		else if (strcmp ( $order, "hours" ))
			$arg = Query::getInformationArrayBroadComputer("students", "Name", "Email", "Grade","Hours", "family", $family,  $order );
		else if (strcmp ( $order, "name" ))
			$arg = Query::getInformationArrayBroadComputer("students", "Name", "Email", "Grade","Hours", "family", $family,  $order );
		else
			echo new Exception ( "no such method found!" );
	} else{
		echo new Exception ( "No such posts exist" );
	}
		echo json_encode($arg);
} catch ( Exception $e ) {
	echo 'Caught exception: ', $e->getMessage (), "\n";
} finally{
	Service::closeConnection();
}
?>