<?php
class Query {
	// !LEftGrIpS2#7AyyLMaoDuvy2I3F?
	// works... somehow
	public static function updateInformation($table, $column, $value, $column2, $value2) {
		$sql = "UPDATE ? SET ?= ? WHERE ? = ?;";
		if (! is_int ( $value ) && ! is_int ( $value2 )) {
			$sql = "UPDATE $table SET $column = '$value' WHERE $column2 = '$value2';";
		}
		if (! is_int ( $value ) && is_int ( $value2 )) {
			$sql = "UPDATE $table SET $column = '$value' WHERE $column2 = $value2;";
		}
		if (is_int ( $value ) && ! is_int ( $value2 ))
			$sql = "UPDATE $table SET $column = $value WHERE $column2 = '$value2';";
		if (is_int ( $value ) && is_int ( $value2 ))
			$sql = "UPDATE $table SET $column = $value WHERE $column2 = $value2;";
		mysqli_query(Service::$conn, $sql);
	}
	// works
	public static function insertFamily($password, $leader, $id) {
		// /$sql = "INSERT INTO FAMILY(FAMILYPASSWORD,FAMILYLEADERNAME,FAMILYLEADERID) VALUES('" . $password . "','" . $leader . "'," . $id . ");";
		$sql = "INSERT INTO FAMILY(FAMILYPASSWORD,FAMILYLEADERNAME,FAMILYLEADERID) VALUES(?,?,?);";
		$statement = mysqli_prepare ( Service::$conn, $sql );
		if (! $statement)
			throw new Exception ( $statement->error );
		$statement->bind_param ( "ssi", $password, $leader, $id );
		mysqli_execute($statement);
	}
	// works
	public static function getInfomation($table, $column) {
		$returnValue = array ();
		$col=0;
		$sql = "SELECT " . $column . " FROM " . $table .";";
		echo $sql;
		$result = mysqli_query ( Service::$conn, $sql );
		if ($result != null && (mysqli_num_rows ( $result ) >= 1)) {
			
			while ( $row = mysqli_fetch_assoc ( $result ) ) {
				array_push ( $returnValue, $row );
			}
		}
		return $returnValue;
	}

	// works
	public static function getInformationBroad($table, $column, $column2, $value) {
		$returnValue = array ();
		$sql = "";
		if (is_int ( $value )) {
			$sql = "SELECT " . $column . " FROM " . $table . " WHERE " . $column2 . " = " . $value . "";
		} else {
			$sql = "SELECT " . $column . " FROM " . $table . " WHERE " . $column2 . " = '" . $value . "'";
		}
		
		$result = mysqli_query ( Service::$conn, $sql );
		if ($result != null && (mysqli_num_rows ( $result ) >= 1)) {
			while ( $row = mysqli_fetch_assoc ( $result ) ) {
				if (! empty ( $row )) {
					array_push ( $returnValue, $row [$column] );
				}
			}
		}
		
		return $returnValue;
	}
	//works
	public static function getInformationPiece($table, $column, $column2, $value) {
		$returnValue = "";
		$sql = "";
		if (is_int ( $value )) {
			$sql = "SELECT " . $column . " FROM " . $table . " WHERE " . $column2 . " = " . $value . "";
		} else {
			$sql = "SELECT " . $column . " FROM " . $table . " WHERE " . $column2 . " = '" . $value . "'";
		}
		$result = mysqli_query ( Service::$conn, $sql );
		if ($result != null && (mysqli_num_rows ( $result ) >= 1)) {
			while ( $row = mysqli_fetch_assoc ( $result ) ) {
				if (! empty ( $row )) {
				$returnValue = $row[$column];
				}
			}
		}
		return $returnValue;
	}
	// works 
	//makes shorter
	public static function isDuplicateEmail($email) {
		Service::openConnection();
		$lists = array ();
		$sql = "SELECT EMAIL FROM STUDENTS where Email = '$email' UNION SELECT EMAIL FROM ADMINS where Email = '$email'; ";
		$result = mysqli_query(Service::$conn,$sql);
		if($result != null && (mysqli_num_rows ( $result ) >= 1)){
			return true;
		}
		return false;
		Service::closeConnection();
	}
		public static function isDuplicateFamily($family) {
		Service::openConnection();
		$lists = array ();
		$sql = "SELECT FAMILYPASSWORD FROM family where FAMILYPASSWORD = '$family'; ";
		$result = mysqli_query(Service::$conn,$sql);
		if($result != null && (mysqli_num_rows ( $result ) == 1)){
			return true;
		}		
		if($result != null && (mysqli_num_rows ( $result ) > 1)){
			throw new Exception("Fatal error... Email>1...admin support needed");
		}
		return false;
		Service::closeConnection();
	}
	// works
	public  static function getInformationArray($Table, $column, $column2, $value) {
		$returnValue = array ();
		if (is_int ( $value ))
			$sql = "SELECT " . $column . " FROM " . $Table . " WHERE " . $column2 . " = " . $value . "Order By Dates;";
		if (! is_int ( $value ))
			$sql = "SELECT " . $column . " FROM " . $Table . " WHERE " . $column2 . " = '" . $value . "' Order By Dates;";
		$result = mysqli_query ( Service::$conn, $sql );
		if ($result != null && (mysqli_num_rows ( $result ) >= 1)) {
			while ( $row = mysqli_fetch_assoc ( $result ) ) {
				if (! empty ( $row ) && $row!=null) {
					array_push ( $returnValue, $row [$column] );
				}
			}
		}
		return $returnValue;
	}
	// works
	public static function getInformationArrayBroad($Table, $column, $column2, $column3, $IFcolumn, $value) {
		$lists = array (array());
		$r =0;
		$sql = "";
		$sql = "SELECT " . $column . " , " . $column2 . " , " . $column3 . " FROM " . $Table . " WHERE " . $IFcolumn . " = " . $value . ";";
		if (is_int ( $value ))
			$sql = "SELECT " . $column . " , " . $column2 . " , " . $column3 . " FROM " . $Table . " WHERE " . $IFcolumn . " = " . $value . ";";
		if (! is_int ( $value ))
			$sql = "SELECT " . $column . " , " . $column2 . " , " . $column3 . " FROM " . $Table . " WHERE " . $IFcolumn . " = '" . $value . "';";
		$result = mysqli_query ( Service::$conn, $sql );
		if ($result != null && (mysqli_num_rows ( $result ) >= 1)) {
			while ( $row = mysqli_fetch_assoc ( $result ) ) {
				if (! empty ( $row )) {
					$lists[$r][0]=$row[$column];
					$lists[$r][1]=$row[$column2];
					$lists[$r][2]=$row[$column3];
				}
				$r++;
			}
		}
		return $lists;
	}
	public static function getInformationArrayBroadComputer($Table, $column, $column2, $column3,$column4, $IFcolumn, $value,$order ){
		$lists = array();
		$r =0;
		$sql = "";
		$sql = "SELECT " . $column . " , " . $column2 . " , " . $column3 .",". $column4 . " FROM " . $Table . " WHERE " . $IFcolumn . " = " . $value . " ORDER BY $order;";
		if (is_int ( $value ))
			$sql = "SELECT " . $column . " , " . $column2 . " , " . $column3 . "," . $column4 . " FROM " . $Table . " WHERE " . $IFcolumn . " = " . $value . " ORDER BY $order;";
		if (! is_int ( $value ))
			$sql = "SELECT " . $column . " , " . $column2 . " , " . $column3 . "," . $column4 . " FROM " . $Table . " WHERE " . $IFcolumn . " = '" . $value . "' ORDER BY $order;";
		$result = mysqli_query ( Service::$conn, $sql );
		if ($result != null && (mysqli_num_rows ( $result ) >= 1)) {
			while ( $row = mysqli_fetch_assoc ( $result ) ) {
				if (! empty ( $row )) {
					array_push($lists, $row[$column],$row[$column2],$row[$column3],$row[$column4]);
				}
				$r++;
			}
		}
		return $lists;
	}

	//works
	public static function getInformationArrayBroadOrder($Table, $column, $column2, $column3, $IFcolumn, $value,$order) {
		$lists = array(array());
		$r =0;
		$sql = "";
		if (is_int ( $value ))
			$sql = "SELECT " . $column . " , " . $column2 . " , " . $column3 . " FROM " . $Table . " WHERE " . $IFcolumn . " = " . $value . " Order By $order;";
			if (! is_int ( $value ))
				$sql = "SELECT " . $column . " , " . $column2 . " , " . $column3 . " FROM " . $Table . " WHERE " . $IFcolumn . " = '" . $value . "' Order By $order;";
				$result = mysqli_query ( Service::$conn, $sql );
				if ($result != null && (mysqli_num_rows ( $result ) >= 1)) {
					while ( $row = mysqli_fetch_assoc ( $result ) ) {
						if (! empty ( $row )) {
							$lists[$r][0]=$row[$column];
							$lists[$r][1]=$row[$column2];
							$lists[$r][2]=$row[$column3];
						}
						$r++;
					}
				}
				return $lists;
	}
	public static function loginA($email,$password){
		$lists = array ();
		$sql = "SELECT email FROM ADMINS where password = '$password' AND Email = '$email';";
		$result = mysqli_query(Service::$conn,$sql);
		if($result != null && (mysqli_num_rows ( $result ) == 1)){
			return true;
		} 
		if($result != null && (mysqli_num_rows ( $result ) > 1)){
			throw new Exception("Duplicate email and password match!!");
			return true;
		} 
		if($result != null && (mysqli_num_rows ( $result ) == 0)){
			echo "no match";
			return false;
		} 
		return false;
	}

		public static function loginS($email,$password){
		$lists = array ();
		$sql = "SELECT email FROM STUDENTS where password = '$password' AND Email = '$email';";
		$result = mysqli_query(Service::$conn,$sql);
		if($result != null && (mysqli_num_rows ( $result ) ==1)){
			return true;
		}		
		if($result != null && (mysqli_num_rows ( $result ) > 1)){
			throw new Exception("Duplicate email and password match!!");
			return true;
		} 
		if($result != null && (mysqli_num_rows ( $result ) == 0)){
			echo "no match";
			return false;
		} 
		return false;
	}
	public static function checkEmailA($email){
		$lists = array ();
		$sql = "SELECT email from ADMINS where email = '$email';";
		$result = mysqli_query(Service::$conn,$sql);
		if($result != null && (mysqli_num_rows ( $result ) == 1)){
			return true;
		}
		if($result != null && (mysqli_num_rows ( $result ) ==0)){
			return false;
		}
		if($result != null && (mysqli_num_rows ( $result ) > 1)){
			throw new  Exception("Fatal error... Email>1... admin support needed");
		}
		return false;
}
	public static function checkEmailS($email){
		$lists = array ();
		$sql = "SELECT email from Students where email = '$email';";
		$result = mysqli_query(Service::$conn,$sql);
		if($result != null && (mysqli_num_rows ( $result ) == 1)){
			return true;
		}
		else if($result != null && (mysqli_num_rows ( $result ) ==0)){
			return false;
		}
		 else if($result != null && (mysqli_num_rows ( $result ) > 1)){
			throw new  Exception("Fatal error... Email>1... admin support needed");
		}
		else{
		return false;
}
}


}
?>