<?php
include ('dbcon.php');
$id=$_SESSION['id'];
$bno=$_GET['bno'];
$sql1="select * from book where bno='$bno'";
$result=mysqli_query($con,$sql1);
$row=mysqli_fetch_array($result);
mysqli_query($con,"UPDATE book SET available = 0 where bno='$bno'");
$title=$row['title'];
$sql="delete from rental where bno='$bno'";
if ($con->query($sql) === FALSE){
echo "1";
}
else echo "0";
  mysqli_close($con);
?>
