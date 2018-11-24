<?php
	include "koneksi.php";
	
	$id_kunjungan	    = $_POST['id_kujungan'];
	
	class emp{}
	
	if (empty($id_kunjungan)){ 
		$response = new emp();
		$response->success = 0;
		$response->message = "Kolom ada yang kosong "; 
		die(json_encode($response));
	}

	else {
	    date_default_timezone_set('Asia/Jakarta');
				
		$query = mysql_query("UPDATE db_kunjungan SET jam_masuk = '".date("h:i:sa")."' where id_kujungan = '".$id_kunjungan."'");

		if ($query) {
			$response = new emp();
			$response->success = 1;
			$response->message = "Data berhasil di simpan";
			die(json_encode($response));
			
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error simpan Data";
			die(json_encode($response)); 
		}	
	}
	
?>