<?php
include('dbcon.php');
if($_SERVER["REQUEST_METHOD"]=="POST"){
$name=$_POST['name'];
$pwd=md5($_POST['password']);
$sql="SELECT * FROM user WHERE name='$name' and password='$pwd'";
$result=mysqli_query($con,$sql);
$count=mysqli_num_rows($result);
if($count==1){
  $_SESSION['name']=$name;
  header("location:main.php");
}
else {
  echo "<script>alert(\"아이디 비밀번호를 확인하세요.\");</script>";
}

}
mysqli_close($con);
?>
<html>

<head>

<meta http-equiv="Content-Type" content="text/html" charset="utf-8"> <!--utf-8설정-->

<title>Login Page</title>

</head>



<br>

<br>
<form action="login.php" method="post">

<label>아 이 디   :</label><input type="text" name="name" required /><br>

<label>패스워드 :</label><input type="password" name="password" required /><br>

<input type="submit" value="로그인"/>
<input type="button" value="회원가입" name="btn1" onclick="location.href='./insert.php'"><br />
</form>
</body>



</html>
