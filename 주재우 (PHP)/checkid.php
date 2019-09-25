<?php
include ('dbcon.php');
$id = $_GET["id"];
$sql="select * from user where id='$id'";
$result=mysqli_query($con,$sql);
$count=mysqli_num_rows($result);
if($count==0){
  ?>
  	<div style='font-family:"malgun gothic"';><?php echo $id; ?>는 사용가능한 아이디입니다.</div>
  <?php
  	}else{
  ?>
  <div style='font-family:"malgun gothic"; color:red;'><?php echo $id; ?>는 중복된아이디입니다.<div>
  <?php
}
  ?>
  <button value="닫기" onclick="window.close()">닫기</button>
  <script>
  </script>
