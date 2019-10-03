<?php
include ('dbcon.php');
$id = $_GET["id"];
$sql="select * from user where id='$id'";
$result=mysqli_query($con,$sql);
$count=mysqli_num_rows($result);
if($count==0)
  echo "0";
  else
  echo"1";
  ?>
