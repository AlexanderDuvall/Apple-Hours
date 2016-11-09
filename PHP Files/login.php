<?php

require_once 'Students.php';
require_once 'Query.php';
require_once 'Conn.php';
require_once 'Service.php';
$a = new Query ();
if(isset($_POST["email"]) && isset($_POST["password"])){
	$password = $_POST["password"];
	$email = $_POST["email"]; 
	Service::openConnection();

	if($a->isDuplicateEmail($email) == false){
		echo "<h>no such email</h>";
	}else {
		 if ( Query::checkEmailS($email)==true AND Query::checkEmailA($email)==true) {
			throw new Exception("fatal error....Email > 1.... admin support needed");
			} else if( Query::checkEmailS($email)==false AND Query::checkEmailA($email)==true ) {
			if (Query::loginA($email,$password)) {
			echo "admin";
			return "admin";
			}
		} else if ( Query::checkEmailS($email)==true AND Query::checkEmailA($email)==false) {
			if(Query::loginS($email,$password)){
			echo "student";
			return "student";
		}
		} 
	}
}
else {
	echo "<h2 style=\"color:brown\"> Sorry not right</h2>";
}

?>