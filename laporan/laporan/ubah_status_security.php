<?php
include "includes/config.php";
$id = isset($_GET['id']) ? $_GET['id'] : die('ERROR: missing ID.');

include_once 'includes/nilai.inc_security.php';
$config = new Config();
$db = $config->getConnection();
$eks = new Nilai($db);

$eks->id = $id;

$eks->readOne();

// if($_POST){

	// $eks->jm = $_POST[$id];
	
	if($eks->update()){
		echo "<script>location.href='konfirmasiSecurity.php?status=sukses'</script>";
	} else{
?>
<script type="text/javascript">
window.onload=function(){
	showStickyErrorToast();
};
</script>
<?php
	}
// }
?>