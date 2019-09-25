<?php
include ('dbcon.php');
if($_SESSION){
$id=$_SESSION['id'];}
else{
  ?><html>
  <input type="button" value="로그인" onclick="location.href='./login.php'">
  </html><?php
}
$title=$_GET['title'];
$data=array();
if($title==""||$title==null){
$mysql="select * from book";
$result=mysqli_query($con,$mysql);
while($row=mysqli_fetch_array($result)){
extract($row);
array_push($data,array('bno'=>$row['bno'],'title'=>$row['title'],'kind'=>$row['kind'],'available'=>$row['available']));
}
}
else{
  $mysql="select * from book where title='$title'";
  $result=mysqli_query($con,$mysql);
  $count=mysqli_num_rows($result);
  if($count!=0){
  while($row=mysqli_fetch_array($result)){
    extract($row);
    array_push($data,array('bno'=>$row['bno'],'title'=>$row['title'],'kind'=>$row['kind'],'available'=>$row['available']));
}}
else{  echo "검색 목록이 없습니다";
}
}
header('Content-Type: application/json; charset=utf8');
$json = json_encode(array("book"=>$data), JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
echo $json;
mysqli_close($con);
?>
