<?php
require_once 'Students.php';
require_once 'Conn.php';
require_once 'Service.php';
$a = new Students ();
try {
	if (isset ( $_POST ['month'] ) and isset ( $_POST ['day'] ) and isset ( $_POST ['year'] )) {
		$month = $_POST ['month'];
		$year = $_POST ['year'];
		$day = $_POST ['day'];
		$birthday = "{$month} {$day}, {$year}";
		echo "$birthday";

		if (isset ( $_POST ['name'] ) && isset ( $_POST ['password'] ) && isset ( $_POST ['email'] ) && isset ( $_POST ['grade'] ) && (isset ( $_POST ['family'] ) && strlen($_POST['family'])==0) ) {
			Service::openConnection ();
			$fullName = $_POST ['name'];
			$password = $_POST ['password'];
			$Email = $_POST ['email'];
			$grade = intval ( $_POST ['grade'] );
			$family ="";
			$a->insertStudent ( $fullName, $password, $Email, $grade, $birthday,$family);
			echo "success";
		} else if (isset ( $_POST ['name'] ) && isset ( $_POST ['password'] ) && isset ( $_POST ['email'] ) && isset ( $_POST ['grade'] ) 
			&& (isset ( $_POST ['family'] ) && strlen($_POST['family'])>0))
		{
			Service::openConnection ();
			$fullName = $_POST ['name'];
			$password = $_POST ['password'];
			$Email = $_POST ['email'];
			$grade =  intval ( $_POST ['grade'] );
			$family = $_POST['family'];
			$a->insertStudentWithFamily ( $fullName, $password, $Email, $grade, $birthday,$family);
			echo "success";
		}
		else {
			throw new Exception ( "No such posts exist" );
		}
	}
} catch ( Exception $e ) {
	echo 'Caught exception: ', $e->getMessage (), "\n";
} finally{
	if (mysqli_ping ( Service::$conn )) {
		echo "X_X...";
		Service::closeConnection ();
	}
}
?>
