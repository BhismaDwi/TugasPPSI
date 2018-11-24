<?php
	include "koneksi.php";
	
	$nama 	    = $_POST['nama'];
	$alamat     = $_POST['alamat'];
	$instansi   = $_POST['instansi'];
	$no_ktp     = $_POST['no_ktp'];
	$keperluan  = $_POST['keperluan'];
	$no_pol     = $_POST['no_pol'];
	$id_security= $_POST['id_satpam'];
	$image      = $_POST['image'];
	$ktemuSiapa = $_POST['KtemuSiapa'];
	
	class emp{}
	
	if (empty($nama) || empty($alamat) || empty($instansi) || empty($no_ktp) || empty($keperluan) || empty($no_pol)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Kolom isian tidak boleh kosong"; 
		die(json_encode($response));
	}
// 	else if(empty($images)){
// 		$response = new emp();
// 		$response->success = 0;
// 		$response->message = "Foto tidak boleh kosong"; 
// 		die(json_encode($response));
// 	} 
	else {
	    $random = random_word(20);
		
		$path = "android/upload_image/images/".$random.".png";
		
		// sesuiakan ip address laptop/pc atau URL server
		$actualpath = "http://192.168.0.16:50/ppsi/$path";
		
	    date_default_timezone_set('Asia/Jakarta');
		// $query = mysqli_query($con,"INSERT INTO db_kunjungan (nama,alamat,instansi,no_ktp,keperluan,no_pol,jam_masuk,tanggal,id_security,foto,id_employee) VALUES('".$nama."','".$alamat."','".$instansi."','".$no_ktp."','".$keperluan."','".$no_pol."','".date("h:i:sa")."','".date("Y-m-d")."','".$id_security."','$actualpath','".$ktemuSiapa."')");
				
		$query = mysql_query("INSERT INTO db_kunjungan (nama,alamat,instansi,no_ktp,keperluan,no_pol,tanggal,id_security,foto,id_employee) VALUES('".$nama."','".$alamat."','".$instansi."','".$no_ktp."','".$keperluan."','".$no_pol."','".date("Y-m-d")."','".$id_security."','$actualpath','".$ktemuSiapa."')");

		if ($query) {
		    file_put_contents($path,base64_decode($image));
		    $query2 = mysql_query("SELECT * FROM db_kunjungan where nama ='".$nama."' and alamat ='".$alamat."' and instansi = '".$instansi."' and keperluan = '".$keperluan."' and id_security = '".$id_security."'");
		    $row = mysql_fetch_array($query2);
			$response = new emp();
			$response->success = 1;
			$response->message = "Data berhasil di simpan";
			$response->id_kunjungan = $row['id_kujungan'];
			$response->gambar = $actualpath;
			die(json_encode($response));
			
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error simpan Data";
			die(json_encode($response)); 
		}	
	}
	
	function random_word($id = 20){
		$pool = '1234567890abcdefghijkmnpqrstuvwxyz';
		
		$word = '';
		for ($i = 0; $i < $id; $i++){
			$word .= substr($pool, mt_rand(0, strlen($pool) -1), 1);
		}
		return $word; 
	}
	
?>