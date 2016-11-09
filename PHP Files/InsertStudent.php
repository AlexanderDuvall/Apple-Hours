<?php
require_once 'Students.php';
require_once 'Conn.php';
require_once 'Service.php';
$a = new Students ();
try{
if (isset($_POST['name']) && isset($_POST['password'])&& isset($_POST['email']) 
		&& isset($_POST['grade']) && isset($_POST['age']) && isset($_POST['family']) ){
			Service::openConnection();
			$fullName = $_POST ['name'];
			$password = $_POST ['password'];
			$Email = $_POST ['email'];
			$grade = intval($_POST ['grade']);
			$age = $_POST ['age'];
			$Family = $_POST ['family'];
			$a->insertStudent ( $fullName, $password, $Email, $grade, $age,$Family);
			echo "success";
}
else {
	throw new Exception ( "No such posts exist" );
}
} catch(Exception $e){
		echo 'Caught exception: ',  $e->getMessage(), "\n";
} finally{
	if(mysqli_ping(Service::$conn)){
		echo "X_X...";
	Service::closeConnection();
}
}
?>
