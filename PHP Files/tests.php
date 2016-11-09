<?php
include 'Students.php';
include 'Service.php';
include 'Conn.php';
include 'Admins.php';
include 'hours.php';
include 'Messages.php';
$c = new Service();
$c->openConnection();
$lists = array(array());
$lists = messages::getMessages(Admins::getAdminFamily("kara@gmail.com"));
print_r($lists);
$c->closeConnection();
?>