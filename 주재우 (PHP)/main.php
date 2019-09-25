<?php
include ('dbcon.php');
if(!$_SESSION){
  echo "1";
}
else{
$id=$_SESSION['id'];
 echo"0";
}
?>
