<?php
require_once("Admins.php");
class messages{
	
	//works
	public static function getMessages($family){
	return Query::getInformationArray("MESSAGES", "Message", "Family", $family);
	}
	//works
	public static function insertMessage($Message, $Email){
		$sql = 	"INSERT INTO MESSAGES(message,Dates,family)VALUES(?,NOW(),?);";
		$statement = mysqli_prepare(Service::$conn, $sql);
		$family = Admins::getAdminFamily($Email);
		if (is_null($family)|| empty($family))
			throw new Exception("No such admin email exists - No family found!");
		mysqli_stmt_bind_param($statement,"ss",$Message,$family);
		if(!($returnValue = mysqli_stmt_execute($statement))){
			echo mysqli_error(Service::$conn);
		}
		return $returnValue;
	}
	
}
?>