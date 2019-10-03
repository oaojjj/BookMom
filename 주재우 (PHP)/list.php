<?php
include ('dbcon.php');
$title=$_GET['title'];
$option=$_GET['option'];
$title="";
$data=array();
if($title==""||$title==null){
  if($option==0){
$mysql="select * from book";
$result=mysqli_query($con,$mysql);
while($row=mysqli_fetch_array($result)){
extract($row);
array_push($data,array('bno'=>$row['bno'],'title'=>$row['title'],'kind'=>$row['kind'],'available'=>$row['available']));
}}
else if($option==1){
  $mysql="select * from book where available='0'";
  $result=mysqli_query($con,$mysql);
  while($row=mysqli_fetch_array($result)){
  extract($row);
  array_push($data,array('bno'=>$row['bno'],'title'=>$row['title'],'kind'=>$row['kind'],'available'=>$row['available']));
  }
}
else{
  $mysql="select * from book where available='1'";
  $result=mysqli_query($con,$mysql);
  while($row=mysqli_fetch_array($result)){
  extract($row);
  array_push($data,array('bno'=>$row['bno'],'title'=>$row['title'],'kind'=>$row['kind'],'available'=>$row['available']));
  }

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
