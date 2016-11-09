<?php 
require_once 'Students.php';
require_once 'Query.php';
require_once 'Conn.php';
require_once 'Service.php';
if(isset($_POST["email"])){
	Service::openConnection();
	$email = $_POST["email"];
	$r = 0;
	$lists=array(array());
	$sql = "Select * from hours where email ='$email'";
				$result = mysqli_query ( Service::$conn, $sql );
				if ($result != null && (mysqli_num_rows ( $result ) >= 1)) {
					while ( $row = mysqli_fetch_assoc ( $result ) ) {
						if (! empty ( $row )) {
							$lists[$r][0]=$row;
						}
						$r++;
					}
				}
				for($x = 0; $x < count($lists);$x++){
					for ($i=0; $i < count($lists[$x]); $i++) {
							echo "(".implode(",", $lists[$x][$i]).")";
					}
					echo "\xA";
				}
				Service::closeConnection();
				echo "success";
} else{
	echo "not quite";
}

 ?>
