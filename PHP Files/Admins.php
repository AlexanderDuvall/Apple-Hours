<?php
require_once 'Query.php';
class Admins {
	public static function getAdminFamily($email) {
		return Query::getInformationPiece ( "Admins", "FAMILY", "Email", $email );
	}
	// work
	public static function getStudentsByName($family) {
		return Query::getInformationArrayBroadOrder ( "Students", "Name", "hours", "grade", "Family", $family, "NAME" );
	}
	// work
	public static function getStudentsByHours($family) {
		return Query::getInformationArrayBroadOrder ( "Students", "Name", "hours", "grade", "Family", $family, "HOURS" );
	}
	// work
	public static function getStudentsByGrade($family) {
		echo "asdf";
		return Query::getInformationArrayBroadOrder ( "Students", "Name", "hours", "grade", "Family", $family, "GRADE" );
	}
	// works
	public static function insertAdmin($firstName, $lastName, $organization, $Email, $FamilyPassword, $ProfilePassword) {
		$sql = "INSERT INTO ADMINS (FIRST_NAME,LAST_NAME,EMAIL,ORGANIZATION,FAMILY,PASSWORD)" . "VALUES(?,?,?,?,?,?)";
		$statement = mysqli_prepare ( Service::$conn, $sql );
		mysqli_stmt_bind_param ( $statement, "ssssss", $firstName, $lastName, $Email, $organization, $FamilyPassword, $ProfilePassword );
		if(!($resultValue = mysqli_stmt_execute ( $statement) )){
			echo mysqli_error(Service::$conn);
		}
		$id = self::getAdminID ( $Email );
		Query::insertFamily ( $FamilyPassword, $firstName . " " . $lastName, $id );
	}
	// works
	public static function getFamilyMembers($family) {
		return Query::getInformationArrayBroad ( "Students", "Name", "Hours", "Grade", "FAMILY", $family );
	}
	// work
	public static function getAdminID($email) {
		return Query::getInformationPiece ( "Admins", "ID", "Email", $email );
	}
}
?>