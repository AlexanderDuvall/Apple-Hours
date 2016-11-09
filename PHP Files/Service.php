<?php
class Service {
	//C:\Program Files (x86)\MySQL\MySQL Fabric 1.5 & MySQL Utilities 1.5
	//C:\Program Files (x86)\MySQL\MySQL Fabric 1.5 & MySQL Utilities 1.5\Doctrine extensions for PHP
	public static $dbhost = null;
	public static $dbuser = null;
	public static $dbpass = null;
	public static $conn = null;
	public static $dbname = null;
	public static $result = null;
	function __construct() {
		self::$dbhost = Conn::$dbhost;
		self::$dbuser = Conn::$dbuser;
		self::$dbpass = Conn::$dbpass;
		self::$dbname = Conn::$dbname;
	}
	public static function openConnection() {
		self::$conn = new mysqli ( "192.168.1.90:3306", "Kara", "!LEftGrIpS2#7AyyLMaoDuvy2I3F?", "nhs");
		if (mysqli_connect_errno ())
			echo new Exception ( "Could not establish connection with database" );
	}
	public static function getConnection() {
		return Service::$conn;
	}
	public static function closeConnection() {
		if (self::$conn != null)
			self::$conn->close ();
	}
}
?>

