<?php
require_once 'Service.php';
class images {
	public static function insertBlob($emails, $file) {
		$blob = fopen ( $filePath, 'rb' );
		$sql = "INSERT INTO images(emails,images) VALUES(:email,:data)";
		$stmt = $this->pdo->prepare ( $sql );
		$stmt->bindParam ( ':email', $emails );
		$stmt->bindParam ( ':data', $blob, PDO::PARAM_LOB );
		return $stmt->execute ();
	}
	public static function updateBlob($id, $filePath, $mime) {
		Service::openConnection ();
		
		$blob = fopen ( $filePath, 'rb' );
		$sql = "UPDATE files
                SET mime = :mime,
                    data = :data
                WHERE id = :id;";
		$stmt = $this->pdo->prepare ( $sql );
		$stmt->bindParam ( ':mime', $mime );
		$stmt->bindParam ( ':data', $blob, PDO::PARAM_LOB );
		$stmt->bindParam ( ':id', $id );
		Service::closeConnection ();
		return $stmt->execute ();
	}
	public static function getImageBytes($email) {
		$sql = "SELECT IMAGE FROM IMAGES WHERE EMAILS = $email";
	$mysqli_query= mysqli_query(Service::$conn,$sql);
		$img = mssql_fetch_array($mysqli_query);
		return base64_encode($img);

	}
	public function selectBlob($id) {
		Service::openConnection ();
		$sql = "SELECT mime,
                        data
                   FROM files
                  WHERE id = :id;";
		$stmt = $this->pdo->prepare ( $sql );
		$stmt->execute ( array (
				":id" => $id 
		) );
		$stmt->bindColumn ( 1, $mime );
		$stmt->bindColumn ( 2, $data, PDO::PARAM_LOB );
		$stmt->fetch ( PDO::FETCH_BOUND );
		Service::closeConnection ();
		return array (
				"mime" => $mime,
				"data" => $data 
		);
	}
}
?>