<?php
// Skrip berikut ini adalah skrip yang bertugas untuk meng-export data tadi ke excell
header("Content-type: application/vnd-ms-excel");
header("Content-Disposition: attachment; filename=Data Kunjungan.xls");
?>
<h3>Data Kunjungan Tamu Non-Rutin</h3>
    
<table border="1" cellpadding="5">
  <tr>
    <th>NO</th>
    <th>ID</th>
    <th>Nama</th>
    <th>Alamat</th>
    <th>Instansi</th>
    <th>Foto</th>
    <th>No KTP</th>
    <th>Keperluan</th>
    <th>No Polisi</th>
    <th>Jam Masuk</th>
    <th>Jam Keluar</th>
    <th>Tanggal</th>
    <th>Security</th>
    <th>Pegawai</th>
    <th>Status</th>
  </tr>
  <?php
  // Load file koneksi.php
  $host = 'mysql.hostinger.co.id'; // Nama hostnya
  $username = 'u157331241_usvis'; // Username
  $password = 'tamboen13'; // Password (Isi jika menggunakan password)
  $database = 'u157331241_dbvis'; // Nama databasenya
  // Koneksi ke MySQL dengan PDO
  $pdo = new PDO('mysql:host='.$host.';dbname='.$database, $username, $password);
  // Buat query untuk menampilkan semua data siswa
  $sql = $pdo->prepare("SELECT ku.`id_kujungan`, ku.`nama` as Tamu, ku.`alamat`, ku.`instansi`, ku.`foto`, ku.`no_ktp`, ku.`keperluan`, ku.`no_pol`, ku.`jam_masuk`, ku.`jam_keluar`, ku.`tanggal`, p.`nama`, se.`nama_security` , ku.`status` FROM db_kunjungan as ku join db_pegawai as p on ku.`id_employee`= p.`id_pegawai` join db_security as se on ku.`id_security`= se.`id_security`");
  $sql->execute(); // Eksekusi querynya
  
  $no = 1; // Untuk penomoran tabel, di awal set dengan 1
  while($data = $sql->fetch()){ // Ambil semua data dari hasil eksekusi $sql
    echo "<tr>";
    echo "<td>".$no."</td>";
    echo "<td>".$data['id_kujungan']."</td>";
    echo "<td>".$data['Tamu']."</td>";
    echo "<td>".$data['alamat']."</td>";
    echo "<td>".$data['instansi']."</td>";
    echo "<td>".$data['foto']."</td>";
    echo "<td>".$data['no_ktp']."</td>";
    echo "<td>".$data['keperluan']."</td>";
    echo "<td>".$data['no_pol']."</td>";
    echo "<td>".$data['jam_masuk']."</td>";
    echo "<td>".$data['jam_keluar']."</td>";
    echo "<td>".$data['tanggal']."</td>";
    echo "<td>".$data['nama_security']."</td>";
    echo "<td>".$data['nama']."</td>";
    echo "<td>".$data['status']."</td>";
    echo "</tr>";
    
    $no++; // Tambah 1 setiap kali looping
  }
  ?>
</table>