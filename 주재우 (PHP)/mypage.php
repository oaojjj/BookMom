<?php
include ('dbcon.php');
$id=$_GET['id'];
$mysql="select * from rental where id='$id'";
$result=mysqli_query($con,$mysql);
$count=mysqli_num_rows($result);
$data=array();
if($count!=0){
while($row=mysqli_fetch_array($result)){
  extract($row);
  array_push($data,array('bno'=>$row['bno'],'title'=>$row['title'],'date'=>$row['date']));
}
header('Content-Type: application/json; charset=utf8');
$json = json_encode(array("book"=>$data), JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
echo $json;}
else{
echo "1";
}
mysqli_close($con);
?>
