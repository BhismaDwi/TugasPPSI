<?php 
	/* ===== www.dedykuncoro.com ===== */
	include "koneksi.php";
	
	$query = mysql_query("SELECT id_user, nama FROM `akun_user` WHERE `level`='pegawai' ORDER By `id_user` asc");
	
	$json = array();	
	
	while($row = mysql_fetch_assoc($query)){
		$json[] = $row;
	}
	
	echo json_encode($json);
	
	mysql_close();
	

	/*=================== KALAU PAKAI MYSQLI YANG ATAS SEMUA DI REMARK, TERUS YANG INI RI UNREMARK ========*/
	// include_once "koneksi.php";

	// $query = mysqli_query($con, "SELECT * FROM menu ORDER BY id ASC");
	
	// $json = array();	
	
	// while($row = mysqli_fetch_assoc($query)){
	// 	$json[] = $row;
	// }
	
	// echo json_encode($json);
	
	// mysqli_close($con);
?>