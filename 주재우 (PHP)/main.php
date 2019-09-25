<?php
include ('dbcon.php');
if(!$_SESSION){
  ?><html>
  <input type="button" value="로그인" onclick="location.href='./login.php'">
  </html><?php
}
else{
$name=$_SESSION['name'];
 echo"$name 님 로그인 되셨습니다";?><html><input type="button" value="로그아웃" onclick="location.href='./logout.php'"></html><?php
}
?>
<html>
<script>
function search(){
  var title=document.getElementById("title").value;
  var url="list.php?title="+title;
    location.href=url;
}
</script>
<div>
<input type="text" id="title" placeholder="검색어를 입력하세요"><button onclick=search()>검색</button>
</div>
<div>
<input type="button" value="마이페이지" onclick="location.href='./mypage.php'">
<input type="button" value="책 목록" onclick="location.href='list.php?title='">
</div>
</html>
