<?php
//works but needs to be changed
//will need cautious testing.
require_once 'Service.php';
class hours{
	//works
	public static function insertHours1( $Email,  $name,  $group, $explanation,
			$Date_Worked,  $hours_worked,  $activity_leader_email){
	$sql="INSERT INTO HOURS(Email,Name,Grp,explanation,date_worked,hours_worked,date_submitted,ACTIVITY_LEADER_EMAIL)"
							."Values(?,?,?,?,STR_TO_DATE(?,'%M %d, %Y'),?,NOW(),?);";
	$statement = mysqli_prepare(Service::$conn, $sql);
	$statement->bind_param("sssssis",$Email,$name,$group,$explanation,$Date_Worked,$hours_worked,$activity_leader_email);
	$resultValue = mysqli_execute($statement);
	if (!$resultValue)
	echo mysqli_error(Service::$conn);
	}
	//works
	public static function insertHours2( $punctiality_and_Attendance, $effort_and_Commitment,
			 $Further_comments,  $Completion,  $Activity_Leader_Name,  $Result,
			 $Student_Email){
		//$id = Query::getInformationPiece("Students","ID", "EMAIL", $Student_Email);
		$sql = "UPDATE Hours SET PUNCTIALITY_AND_ATTENDANCE = '$punctiality_and_Attendance', 
		EFFORT_AND_COMMITMENT = '$effort_and_Commitment', Further_Comments = '$Further_comments',
		Completion = '$Completion', Activity_Leader_Name = '$Activity_Leader_Name',RESULT = '$Result' WHERE Email = '$Student_Email';";
		if(mysqli_query(Service::$conn, $sql))
			echo "Update Success";
		else 
			echo mysqli_error(Service::$conn);
	}

}
?>