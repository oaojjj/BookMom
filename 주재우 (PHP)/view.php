<?php
include ('dbcon.php');
$bno=$_GET['bno'];
$data=array();
$mysql="select * from book where bno='$bno'";
$result=mysqli_query($con,$mysql);
while($row=mysqli_fetch_array($result)){
extract($row);
array_push($data,array('bno'=>$row['bno'],'title'=>$row['title'],'kind'=>$row['kind'],'available'=>$row['available']));}
header('Content-Type: application/json; charset=utf8');
$json = json_encode(array("book"=>$data), JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
echo $json;
?>
