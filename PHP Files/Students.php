<?php
require_once 'Query.php';
class Students {
	
	//works
	public static  function insertStudent($fullName, $password, $Email, $grade, $age, $Family) {
		$sql = "INSERT INTO STUDENTS(Name, Password,Email,Grade,Age,Hours) Values(?,?,?,?, STR_TO_DATE(?,'%M %d, %Y'),?);";
		$statement = mysqli_prepare(Service::$conn, $sql );
		if (! $statement)
			throw new Exception ( $statement->error );
				$hours = 0;
		$statement->bind_param ( "sssisi", $fullName, $password, $Email, $grade, $age, $hours );
		if(!mysqli_stmt_execute($statement))
			throw new Exception ( $statement->error );
	}
	public static function insertStudentWithFamily($fullName, $password, $Email, $grade, $age, $Family) {
		$sql = "INSERT INTO STUDENTS(Name, Password,Email,Grade,Age,Hours,Family) Values(?,?,?,?, STR_TO_DATE(?,'%M %d, %Y'),?,?);";
		$statement = mysqli_prepare(Service::$conn, $sql );
		if (! $statement)
			throw new Exception ( $statement->error );
				$hours = 0;
		$statement->bind_param ( "sssisis", $fullName, $password, $Email, $grade, $age, $hours,$Family);
		if(!mysqli_stmt_execute($statement))
			throw new Exception ( $statement->error );
	}
	//works
	public static function getStudentId($email) {
		$q =new Query();
		return $q->getInformationPiece ( "students", "ID", "EMAIL", $email );
	}
	//works
	public static function getStudentsStatsByPlace($studentId) {
		$q =new Query();
		return $q->getInformationArrayBroadOrder ( "HOURS", "GRP", "DATE_WORKED", "HOURS_WORKED", "EMAIL", $studentId, "grp" );
	}
	public static function getStudentStatsByDate($studentId) {
	$q =new Query();
		return $q->getInformationArrayBroadOrder ( "Hours", "grp", "DATE_WORKED", "HOURS_WORKED", "STUDENT_ID", $studentId, "DATE_WORKED" );
	}
	public static function getStudentStatsByHours($studentId) {
	$q =new Query();
		return $q->getInformationArrayBroadOrder ( "Hours", "grp", "date_worked", "hours_worked", "id", $studentId, "HOURS_WORKED" );
	}
	//works
	public static function getTotalStudentHours($email) {
	$q =new Query();
		return $q->getInformationPiece ( "Students", "HOURS", "EMAIL", $email );
	}
	//works
	public static function getStudentName($email) {
	$q =new Query();
		return $q->getInformationPiece( "Students", "NAME", "EMAIL", $email );
	}
	//works
	public static function getStudentFamily($email) {
	$q =new Query();
		return $q->getInformationPiece ( "Students", "FAmily", "EMAIL", $email );
	}
}
?>