
<?php
require_once 'Service.php';
require_once 'Conn.php';
require_once 'hours.php';
require_once 'Students.php';
if (isset ( $_POST ['email'] ) && isset ( $_POST ['group'] )  && isset ( $_POST ['explanation'] ) 
		&& isset ( $_POST ['dateworked'] ) && isset ( $_POST ['hoursworked'] )
		&& isset ( $_POST ['activityleaderemail'] )) {
		try {
		Service::openConnection();
			$Email = $_POST['email'];
			$name = Students::getStudentName($Email);
			$group = $_POST['group'];
			$explanation = $_POST['explanation'];
			$Date_Worked = $_POST['dateworked'];
			$hours_worked = $_POST['hoursworked'];
			$activity_leader_email = $_POST['activityleaderemail'];
		hours::insertHours1($Email, $name, $group,$explanation, $Date_Worked, $hours_worked,$activity_leader_email);	
		echo "success";
		} catch (Exception $e){
			echo 'Caught exception: ',  $e->getMessage(), "\n";
		} finally{
			if(mysqli_ping(Service::$conn)){
				echo "X_X...";
				Service::closeConnection();
			}
		}
} else {
	echo new Exception("No such posts exist.");
}
?>