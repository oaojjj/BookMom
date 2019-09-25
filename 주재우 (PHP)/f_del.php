<?php
include ('dbcon.php');
$name=$_SESSION['name'];
$bno=$_GET['bno'];
$sql1="select * from book where bno='$bno'";
$result=mysqli_query($con,$sql1);
$row=mysqli_fetch_array($result);
$title=$row['title'];
$sql="delete from favorites where bno='$bno'";
if ($con->query($sql) === FALSE){
echo "Error: " . $sql . "
" . $con->error;
}

  mysqli_close($con);
?>
  <div style='font-family:"malgun gothic"';><?php echo $title; ?> 반납 완료.</div>
  <button value="닫기" onclick="window.close()">닫기</button>
