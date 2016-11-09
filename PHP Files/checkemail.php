<?php
require_once 'Conn.php';
require_once 'Service.php';
require_once 'Query.php';
if(isset($_POST['email'])){
$email = $_POST['email'];
if(Query::isDuplicateEmail($email) == true){
	echo "true";
}else{
	echo "false";
}
}

?>