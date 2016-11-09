<?php
require_once 'Service.php';
require_once 'Conn.php';
require_once 'Images.php';
require_once 'Students.php';
if (isset($_POST['email'])){
	$email = $_POST['email'];
	$blob =Images::getImage(Students::getStudentId($email));
	echo $blob;
}
?>