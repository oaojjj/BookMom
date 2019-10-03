<?php
include ('dbcon.php');
$id=$_GET['id'];
$bno=$_GET['bno'];
$date=$_GET['date'];
$sql1="select * from book where bno='$bno'";
$result=mysqli_query($con,$sql1);
$row=mysqli_fetch_array($result);
mysqli_query($con,"UPDATE book SET available = 1 where bno='$bno'");
$title=$row['title'];
$sql="insert into rental(id,bno,date,title) values('$id','$bno','$date','$title')";
if ($con->query($sql) === FALSE){
echo "0";
}
else echo"1";
  mysqli_close($con);
?>
