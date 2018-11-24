    <?php
include "includes/config.php";

session_start();
if(!isset($_SESSION['nama'])){
    echo "<script>location.href='login.php'</script>";
}
$config = new Config();
$db = $config->getConnection();
// include_once 'includes/data.inc.php';
// $pro2 = new Data($db);
// $stmt2 = $pro2->readAll();


if(isset($_GET['search'])){
    if(isset($_GET['search']))
    {
        $valueToSearch = $_GET['valueToSearch'];
        $query = "SELECT * FROM `db_pegawai` WHERE nama LIKE '%".$valueToSearch."%' order by id_pegawai asc ";
        $search_result = filterTable($query);
        
    }else {
       $query = "SELECT * FROM `db_pegawai` order by id_pegawai asc";
    $search_result = filterTable($query);
    }
}else{
    $query = "SELECT * FROM `db_pegawai` order by id_pegawai asc";
    $search_result = filterTable($query);
}




function filterTable($query)
{
    $connect = mysqli_connect("mysql.hostinger.co.id", "u157331241_usvis", "tamboen13", "u157331241_dbvis");
    $filter_Result = mysqli_query($connect, $query);
    return $filter_Result;
}

if(isset($_GET['status'])){
        ?>
            <script type="text/javascript">
            window.onload=function(){
              sweet();
            };
            </script>
        <?php
    }

if(isset($_GET['status_hapus'])){
        ?>
            <script type="text/javascript">
            window.onload=function(){
              notsweet();
            };
            </script>
        <?php
    }

?>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <link rel="icon" type="image/png" href="assets/img/favicono.ico">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

    <title>Laporan Manajemen Visitor</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />


    <!-- Bootstrap core CSS     -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" />

    <!-- Animation library for notifications   -->
    <link href="assets/css/animate.min.css" rel="stylesheet"/>

    <!--  Light Bootstrap Table core CSS    -->
    <link href="assets/css/light-bootstrap-dashboard.css" rel="stylesheet"/>


    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="assets/css/demo.css" rel="stylesheet" />


    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
    <link href="assets/css/pe-icon-7-stroke.css" rel="stylesheet" />
    <script type="text/javascript" src="DatePicker/js/jquery-1.10.2.min.js"></script> 
    <link rel="stylesheet" type="text/css" href="dist/sweetalert.css">
    <script type="text/javascript" src="dist/sweetalert.min.js"></script>
</head>
<body>

<div class="wrapper">
    <div class="sidebar" data-color="orange" data-image="">

    <!--

        Tip 1: you can change the color of the sidebar using: data-color="blue | azure | green | orange | red | purple"
        Tip 2: you can also add an image using data-image tag

    -->

        <div class="sidebar-wrapper">
            <div class="logo">
                <a href="index.php" class="simple-text">
                    Member Area
                </a>
            </div>

            <ul class="nav">
                <li>
                    <a href="index.php">
                        <i class="pe-7s-graph"></i>
                        <p>Dashboard</p>
                    </a>
                </li>
                <li>
                    <a href="user.php">
                        <i class="pe-7s-user"></i>
                        <p>User Profile</p>
                    </a>
                </li>
                <li>
                    <a href="table.php">
                        <i class="pe-7s-note2"></i>
                        <p>Data Detail</p>
                    </a>
                </li>
                <li>
                    <a href="konfirmasiSecurity.php">
                        <i class="pe-7s-users"></i>
                        <p>Akun Security</p>
                    </a>
                </li>
                <li class="active">
                    <a href="konfirmasiPegawai.php">
                        <i class="pe-7s-users"></i>
                        <p>Akun Pegawai</p>
                    </a>
                </li>
            </ul>
        </div>
    </div>

    <div class="main-panel">
        <nav class="navbar navbar-default navbar-fixed">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation-example-2">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.php">Dashboard</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-left">
                        <li>
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="fa fa-dashboard"></i>
                                <p class="hidden-lg hidden-md">Dashboard</p>
                            </a>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                           <a href="user.php">
                               <p><?php echo $_SESSION['nama'] ?></p>
                            </a>
                        </li>
                        <li>
                            <a href="logout.php">
                                <p>Log out</p>
                            </a>
                        </li>
                        <li class="separator hidden-lg hidden-md"></li>
                    </ul>
                </div>
            </div>
        </nav>
        
        <!-- <div class="content">
            <div class="container-fluid">
                
            </div>
        </div> -->

        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-6">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Nama Pegawai</h4>
                                <!-- <p class="category">Here is a subtitle for this table</p> -->
                                <div class="content">
                                    <form method="GET">                            
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Search</label>
                                                <input type="text" class="form-control" placeholder="Nama" name="valueToSearch" value="">
                                            </div>
                                        </div>
                                    </div>
                                    <button type="submit" name="search" class="btn btn-info btn-fill pull-right">Cari</button>
                                    <div class="clearfix"></div>
                                </form>
                                </div>
                            </div>
                        </div>
                    </div>                 
                </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Data Akun Security</h4>
                                <!-- <p class="category">Here is a subtitle for this table</p> -->
                            </div>
                            <div class="content table-responsive table-full-width">
                                <table class="table table-hover table-striped">
                                    <thead>
                                        <th>ID</th>
                                        <th>Nama</th>
                                        <th>Username</th>
                                        <th>Status</th>
                                        <th>Konfirmasi</th>
                                    </thead>
                                    <tbody>
                                        <?php
                                                while($row = mysqli_fetch_array($search_result)):
                                            ?>
                                        <tr>
                                            <td><?php echo $row['id_pegawai'] ?></td>
                                            <td><?php echo $row['nama'] ?></td>
                                            <td><?php echo $row['username'] ?></td>
                                            <td><?php 
                                                if($row['konfirmasi']==0){
                                                    ?>
                                                    <img src="images/error.png">
                                                    <?php
                                                }else{
                                                    ?>
                                                    <img src="images/success.png">
                                                    <?php
                                                } 
                                                ?></td>
                                            <td><a href="ubah_status.php?id=<?php echo $row['id_pegawai'] ?>" class="btn btn-warning"><span aria-hidden="true">Beri Akses</a>
                                            <a href="ubah_status_hapus.php?id=<?php echo $row['id_pegawai'] ?>" class="btn btn-danger"><span aria-hidden="true">Hapus Akses</span></a></td>
                                        </tr>
                                        <?php
                                                endwhile;
                                            ?>
                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <footer class="footer">
            <div class="container-fluid">
                <p class="copyright pull-right">
                    &copy; <script>document.write(new Date().getFullYear())</script> <a href="https://www.cpp.co.id/">PT Central Proteina Prima</a>, Laporan Manajemen Visitor.
                </p>
            </div>
        </footer>
        </div>
    </div>
    </div>
<script>
    function normal () {
        alert("Normal Alert!");
    }
    function sweet (){
        swal("Give!", "You already Give The Access!", "success");
    }
    function notsweet(){
        swal("Erase!", "You already Erase The Access!", "warning");
    }
</script>
</body>

    <!--   Core JS Files   -->
    <script src="assets/js/jquery-1.10.2.js" type="text/javascript"></script>
    <script src="assets/js/bootstrap.min.js" type="text/javascript"></script>

    <!--  Checkbox, Radio & Switch Plugins -->
    <script src="assets/js/bootstrap-checkbox-radio-switch.js"></script>

    <!--  Charts Plugin -->
    <script src="assets/js/chartist.min.js"></script>

    <!--  Notifications Plugin    -->
    <script src="assets/js/bootstrap-notify.js"></script>

    <!--  Google Maps Plugin    -->
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>

    <!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
    <script src="assets/js/light-bootstrap-dashboard.js"></script>

    <!-- Light Bootstrap Table DEMO methods, don't include it in your project! -->
    <script src="assets/js/demo.js"></script>


</html>