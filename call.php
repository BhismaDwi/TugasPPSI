<?php
	include "koneksi.php";
	
	$id_satpam 	    = $_POST['id_satpam'];
	
	class emp{}
	
	if (empty($id_satpam)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "id_satpam kosong"; 
		die(json_encode($response));
	}
	else {
	 
		 $query = mysql_query("SELECT * FROM akun_user where id_user = '".$id_satpam."'");
	 	 $row = mysql_fetch_array($query);
		if (!empty($row)) {
			$response = new emp();
			$response->success = 1;
			$response->message = "Data berhasil di simpan";
			$response->nama_satpam = $row['nama'];
			$response->notelp = $row['no_telp'];
			$response->email = $row['email'];
			die(json_encode($response));
			
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error simpan Data";
			die(json_encode($response)); 
		}	
	}
	
?>