<?php
include('dbcon.php');
if($_SERVER["REQUEST_METHOD"]=="POST"){
$id=$_POST['id'];
$pwd=md5($_POST['password']);
$sql="SELECT * FROM user WHERE id='$id' and password='$pwd'";
$result=mysqli_query($con,$sql);
$count=mysqli_num_rows($result);
if($count==1){
  echo "1"; // 1이면 로그인 성공
}
else {
  echo "0"; //0 이면 로그인 실패
}
}
mysqli_close($con);
?>
