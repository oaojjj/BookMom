<?php

    error_reporting(E_ALL);
    ini_set('display_errors',1);

    include('dbcon.php');


    if( ($_SERVER['REQUEST_METHOD'] == 'POST') && isset($_POST['submit']))
    {
        $id=$_POST['id'];
        $name=$_POST['name'];
        $password=md5($_POST['password']);
        if(!isset($errMSG))
        {
          $sql="insert into user(id,name,password) values('$id','$name','$password')";
          if (!mysqli_query($con,$sql))
    {
    die('Error: ' . mysqli_error($con));
    }
    mysqli_close($con);
        }
      }
?>
<html>
<script>
function checkid(){
  var id=document.getElementById("id").value;
  if(id!==""&&id!=null){
    url="checkid.php?id="+id;
    window.open(url,"chkid","width=300,height=100");
  }
  else{alert("아이디를 입력하세요")}
}
</script>
<title> 회원가입 </title>
   <body>
        <?php
        if (isset($errMSG)) echo $errMSG;
        if (isset($successMSG)) echo $successMSG;
        ?>
        <form action="insert.php" method="POST">
            id: <input type = "text" name = "id" id="id" required /> <input type="button" value="중복검사" onclick="checkid();"><br>
            name : <input type = "text" name = "name" id="name" required /><br>
            password: <input type = "password" name = "password" required/>
            <input type = "submit" value="회원가입" name = "submit" /> <br>
            <input type="button" value="로그인창" onclick="location.href='./login.php'">
        </form>

   </body>
</html>
