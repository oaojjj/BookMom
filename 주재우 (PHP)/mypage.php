<?php
include ('dbcon.php');
$name=$_SESSION['name'];
echo"$name 님 로그인 되셨습니다<br />\n";
$mysql="select * from rental where name='$name'";
$result=mysqli_query($con,$mysql);
$count=mysqli_num_rows($result);
if($count!=0){
while($row=mysqli_fetch_array($result)){
  extract($row);
  echo "".$row['bno']."". $row['title']. " " .$row['date']. "<br />\n <br />\n";
}}
else{
echo "대여 목록이 없습니다<br />\n";
}
$mysql1="select * from favorites where name='$name'";
$result1=mysqli_query($con,$mysql1);
$count1=mysqli_num_rows($result1);
if($count1!=0){
while($row1=mysqli_fetch_array($result1)){
  extract($row1);
  echo $row1['title']. " " .$row1['bno']. " ";
}}
else{
echo "즐겨찾기 목록이 없습니다";
}
mysqli_close($con);
?>
<html>
<script>

</script>
  <table border='1' size='100' id = "rnt">
    <tr><td colspan="3">대여 목록</td></tr>
    <tr><td>책번호</td><td>책제목</td><td>반납일</td></tr>
  </table>
  <br>
  <table border='1' size='100' id = "fav">
    <tr><td colspan="2">즐겨찾기 목록</td></tr>
    <tr><td>책번호</td><td>책제목</td></tr>
  </table>
<div>
<input type="button" value="메인" onclick="location.href='main.php'">
</div>
</html>
