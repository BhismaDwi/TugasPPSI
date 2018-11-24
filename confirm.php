<?php
	include "koneksi.php";
	
	$id_kunjungan	    = $_POST['id_kunjungan'];
	$id_satpam	        = $_POST['id_satpam'];
	
	class emp{}
	
	if (empty($id_kunjungan) || empty($id_satpam)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Kolom ada yang kosong "; 
		die(json_encode($response));
	}

	else {
	    date_default_timezone_set('Asia/Jakarta');
				
		$query = mysql_query("UPDATE db_kunjungan SET status = 'Dapat Menemui' where id_kujungan = '".$id_kunjungan."'");

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