<?php
require_once 'Students.php';
require_once 'Query.php';
require_once 'Conn.php';
require_once 'Service.php';
$a = new Query ();
if($_POST["email"]=="" || $_POST["password"] == ""){
header("Location: http://192.168.1.90:80/login.html");
	echo "<h2 style =\"color:red\">uh oh</h2>";
die();
}
else if(isset($_POST["email"]) && isset($_POST["password"])){
	$password = $_POST["password"];
	$email = $_POST["email"]; 
	Service::openConnection();

	if($a->isDuplicateEmail($email) == false){
		header("Location: http://192.168.1.90:80/login.html");
	echo "<h2 style =\"color:red\">No such email</h2>";
die();
	}else {
		 if ( Query::checkEmailS($email)==true AND Query::checkEmailA($email)==true) {
			throw new Exception("fatal error....Email > 1.... admin support needed");
			} else if( Query::checkEmailS($email)==false AND Query::checkEmailA($email)==true ) {
			if (Query::loginA($email,$password)) {
			echo "<p>admin</p>";
			header("Location: http://192.168.1.90:80/applehours.html");
				echo "<h2 style =\"color:red\">admin</h2>";
			die();			
			}
		} else if ( Query::checkEmailS($email)==true AND Query::checkEmailA($email)==false) {
			if(Query::loginS($email,$password)){
			header("Location: http://192.168.1.90:80/applehours.html");
				echo "<h2 style =\"color:red\">student</h2>";
			die();					return "student";
		}
		} 
	}
}
else {
	http_redirect("login.html");
}

?>