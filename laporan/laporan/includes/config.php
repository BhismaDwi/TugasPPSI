<?php
class Config{
	
	private $host = "mysql.hostinger.co.id";
	private $db_name = "u157331241_dbvis";
	private $username = "u157331241_usvis";
	private $password = "tamboen13";
	public $conn;
	
	public function getConnection(){
	
		$this->conn = null;
		
		try{
			$this->conn = new PDO("mysql:host=" . $this->host . ";dbname=" . $this->db_name, $this->username, $this->password);
		}catch(PDOException $exception){
			echo "Connection error: " . $exception->getMessage();
		}
		
		return $this->conn;
	}
}
?>
