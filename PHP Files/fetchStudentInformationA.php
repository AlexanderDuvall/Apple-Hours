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
			$arg = Admins::getStudentsByGrade ( $family );
		else if (strcmp ( $order, "hours" ))
			$arg = Admins::getStudentsByHours ( $family );
		else if (strcmp ( $order, "name" ))
			$arg = Admins::getStudentsByName ( $family );
		else
			echo new Exception ( "no such method found!" );
	} else
		echo new Exception ( "No such posts exist" );
	for($i = 0; $i < count ( $arg ); $i ++) {
		echo $arg [$i] [0] . "," . $arg [$i] [1] . "," . $arg [$i] [2] . "\xA";
	}
} catch ( Exception $e ) {
	echo 'Caught exception: ', $e->getMessage (), "\n";
} finally{
	Service::closeConnection();
}
?>