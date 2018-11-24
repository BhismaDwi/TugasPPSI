<?php
	include "koneksi.php";

	$id = $_POST['id_kujungan'];
	$query = mysql_query("SELECT ku.`id_kujungan`, ku.`nama` as Tamu, ku.`alamat`, ku.`instansi`, ku.`foto`, ku.`no_ktp`, ku.`keperluan`, ku.`no_pol`, ku.`jam_masuk`, ku.`jam_keluar`, ku.`tanggal`, ku.`status`, au.`nama` as satpam, dua.`nama` as pegawai FROM akun_user as au inner join db_kunjungan as ku on au.`id_user` = ku.`id_security`, (select ku.`id_kujungan`, au.`nama` from akun_user as au inner join db_kunjungan as ku on au.`id_user` = ku.`id_employee`) as dua where ku.`id_kujungan` = dua.`id_kujungan` and ku.`id_kujungan`='".$id."'");
	while ($row = mysql_fetch_array($query)){
		$char ='"';
		// $tgl	= date("d M Y", strtotime($row['date']));
		// $string = $row['value'];
		$json = '{
				"id_kujungan": "'.str_replace($char,'`',strip_tags($row['id_kujungan'])).'", 
				"nama": "'.str_replace($char,'`',strip_tags($row['Tamu'])).'",
				"alamat": "'.str_replace($char,'`',strip_tags($row['alamat'])).'",
				"instansi": "'.str_replace($char,'`',strip_tags($row['instansi'])).'",
				"no_ktp": "'.str_replace($char,'`',strip_tags($row['no_ktp'])).'",
				"keperluan": "'.str_replace($char,'`',strip_tags($row['keperluan'])).'",
				"no_pol": "'.str_replace($char,'`',strip_tags($row['no_pol'])).'",
				"jam_masuk": "'.str_replace($char,'`',strip_tags($row['jam_masuk'])).'",
				"jam_keluar": "'.str_replace($char,'`',strip_tags($row['jam_keluar'])).'",
				"tanggal": "'.str_replace($char,'`',strip_tags($row['tanggal'])).'",
				"status": "'.str_replace($char,'`',strip_tags($row['status'])).'",
				"id_security": "'.str_replace($char,'`',strip_tags($row['satpam'])).'",
				"id_employee": "'.str_replace($char,'`',strip_tags($row['pegawai'])).'",
				"gambar": "'.$row['foto'].'"}';
	}
	echo $json;
	// mysql_close($connect);

	// include_once "koneksi.php";
	// $id = $_POST['id'];
	// $query = mysqli_query($con, "SELECT * FROM news WHERE id='".$id."'");
	// while ($row = mysqli_fetch_array($query)){
	// 	$char = '"';
	// 	$tgl = date("d M Y", strtotime($row['date']));
	// 	$string = $row['value'];
	// 	$json = '{
	// 			"id": "'.str_replace($char,'`',strip_tags($row['id'])).'", 
	// 			"judul": "'.str_replace($char,'`',strip_tags($row['title'])).'",
	// 			"tgl": "'.str_replace($char,'`',strip_tags($tgl)).'", 
	// 			"isi": "'.str_replace($char,'`', $string).'",
	// 			"gambar": "'.$row['images'].'"}';
	// }
	// echo $json;
	// mysqli_close($con);
?>