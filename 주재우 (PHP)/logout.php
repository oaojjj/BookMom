<?php
include ('dbcon.php');
echo $_SESSION['name'];
if($_SESSION['name']){
  session_destroy();
}
echo "<script>location.href='main.php';</script>";
?>
