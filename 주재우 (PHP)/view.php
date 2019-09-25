<?php
include ('dbcon.php');
$check;
if($_SESSION){
$name=$_SESSION['name'];$check=0;}
else{
  ?><html>
  <input type="button" value="로그인" onclick="location.href='./login.php'">
  </html><?php $check=1;
}
$bno=$_GET['bno'];
$mysql="select * from book where bno='$bno'";
$result=mysqli_query($con,$mysql);
$book=mysqli_fetch_array($result);
if($book['available']==0){
$available="가능";}
else {
  $available="불가능";
}
echo "책번호 : ". $book['bno'] ."<br>\n 책 제목 : ". $book['title'] ."<br>\n 책 저자 : " .$book['author']. "<br>\n 출판사 : " .$book['publisher']. "<br>\n 대여가능 : " .$available. "";
?>
<html>
<script>
var bno ='<?=$bno?>';
var check ='<?=$check?>'
var available='<?=$available?>';
function favorites(){
  var url="f_reg.php?bno="+bno;
    window.open(url,"favorites","width=300,height=100");
}
function rental(){
  var date1=document.getElementById("date").value;
  if(date1==""){
    alert("날짜를 선택하세요")
  }
  else{
  var url="r_reg.php?bno="+bno+"&date="+date1;
    window.open(url,"rental","width=300,height=100");
    }
}
</script>
<div>
<input type="date" id="date" >
<input type=button id="btn1" value="대여" onclick=rental()>
<input type=button value="즐겨찾기" onclick=favorites()>
<input type=button value="마이페이지" onclick="location.href='mypage.php'">
<input type=button value="목록" onclick="location.href='list.php?title='">
</div>
<script>
if(available=="불가능"||check==1){
  var btn=document.getElementById("btn1");
  btn.disabled=true;
}
</script>
</html>
