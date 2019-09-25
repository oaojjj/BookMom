<?php
include ('dbcon.php');
$name = $_GET["name"];
$sql="select * from user where name='$name'";
$result=mysqli_query($con,$sql);
$count=mysqli_num_rows($result);
if($count==0){
  ?>
  	<div style='font-family:"malgun gothic"';><?php echo $name; ?>는 사용가능한 아이디입니다.</div>
  <?php
  	}else{
  ?>
  <div style='font-family:"malgun gothic"; color:red;'><?php echo $name; ?>는 중복된아이디입니다.<div>
  <?php
}
  ?>
  <button value="닫기" onclick="window.close()">닫기</button>
  <script>
  </script>
