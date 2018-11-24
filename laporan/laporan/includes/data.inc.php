<?php
class Data{
	
	private $conn;
	private $table_name = "db_kunjungan";
	
	public $id;
	public $nm;
	public $almt;
	public $instansi;
	public $foto;
	public $no_ktp;
	public $keperluan;
	public $no_pol;
	public $jam_masuk;
	public $jam_keluar;
	public $tanggal;
	public $ids;
	public $ide;
	
	public function __construct($db){
		$this->conn = $db;
	}
	
	// function insert(){
		
	// 	$query = "insert into ".$this->table_name." values(?,?,'','')";
	// 	$stmt = $this->conn->prepare($query);
	// 	$stmt->bindParam(1, $this->id);
	// 	$stmt->bindParam(2, $this->nm);
		
	// 	if($stmt->execute()){
	// 		return true;
	// 	}else{
	// 		return false;
	// 	}
		
	// }
	
	function readAll(){

		$query = "SELECT * FROM ".$this->table_name." ORDER BY id_kujungan ASC";
		$stmt = $this->conn->prepare( $query );
		$stmt->execute();
		
		return $stmt;
	}
	function countAll(){

		$query = "SELECT * FROM ".$this->table_name." ORDER BY id_kujungan ASC";
		$stmt = $this->conn->prepare( $query );
		$stmt->execute();
		
		return $stmt->rowCount();
	}
	
	function readOne(){
		
		$query = "SELECT * FROM " . $this->table_name . " WHERE id_kujungan=? LIMIT 0,1";

		$stmt = $this->conn->prepare( $query );
		$stmt->bindParam(1, $this->id);
		$stmt->execute();

		$row = $stmt->fetch(PDO::FETCH_ASSOC);
		
		$this->id 			= $row['id_kujungan'];
		$this->nm 			= $row['nama'];
		$this->almt 		= $row['alamat'];
		$this->$instansi	= $row['instansi'];
		$this->$foto 		= $row['foto'];
		$this->$no_ktp		= $row['no_ktp'];
		$this->$keperluan	= $row['keperluan'];
		$this->$no_pol		= $row['no_pol'];
		$this->$jam_masuk	= $row['jam_masuk'];
		$this->$jam_keluar	= $row['jam_keluar'];
		$this->$tanggal		= $row['tanggal'];
		$this->$ids 		= $row['id_security'];
		$this->$ide 		= $row['id_employee'];
	}
	function readSatu($a){
		
		$query = "SELECT * FROM " . $this->table_name . " WHERE id_kujungan='$a' LIMIT 0,1";

		$stmt = $this->conn->prepare( $query );
		$stmt->execute();

		return $stmt;
	}
	
	// // update the product
	// function update(){

	// 	$query = "UPDATE 
	// 				" . $this->table_name . " 
	// 			SET 
	// 				nama_kriteria = :nm
	// 			WHERE
	// 				id_kriteria = :id";

	// 	$stmt = $this->conn->prepare($query);

	// 	$stmt->bindParam(':nm', $this->nm);
	// 	$stmt->bindParam(':id', $this->id);
		
	// 	// execute the query
	// 	if($stmt->execute()){
	// 		return true;
	// 	}else{
	// 		return false;
	// 	}
	// }
	
	// // delete the product
	// function delete(){
	
	// 	$query = "DELETE FROM " . $this->table_name . " WHERE id_kriteria = ?";
		
	// 	$stmt = $this->conn->prepare($query);
	// 	$stmt->bindParam(1, $this->id);

	// 	if($result = $stmt->execute()){
	// 		return true;
	// 	}else{
	// 		return false;
	// 	}
	// }
	// function hapusell($ax){
	
	// 	$query = "DELETE FROM " . $this->table_name . " WHERE id_kriteria in $ax";
		
	// 	$stmt = $this->conn->prepare($query);

	// 	if($result = $stmt->execute()){
	// 		return true;
	// 	}else{
	// 		return false;
	// 	}
	// }
}
?>