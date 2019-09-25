<?php
include ('dbcon.php');
$name=$_SESSION['name'];
$bno=$_GET['bno'];
$date=$_GET['date'];
$sql1="select * from book where bno='$bno'";
$result=mysqli_query($con,$sql1);
$row=mysqli_fetch_array($result);
mysqli_query($con,"UPDATE book SET available = 1 where bno='$bno'");
$title=$row['title'];
$sql="insert into rental(name,bno,date,title) values('$name','$bno','$date','$title')";
if ($con->query($sql) === FALSE){
echo "Error: " . $sql . "
" . $con->error;
}

  mysqli_close($con);
?>
  <div style='font-family:"malgun gothic"';><?php echo $title; ?> 대여 완료.</div>
  <button value="닫기" onclick="window.close()">닫기</button>
