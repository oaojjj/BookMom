<?php

    error_reporting(E_ALL);
    ini_set('display_errors',1);

    include('dbcon.php');

    #if(($_SERVER['REQUEST_METHOD'] == 'POST') && isset($_POST['submit']))
    #{
        $id=$_POST['id'];
        $name=$_POST['name'];
        $password=md5($_POST['password']);
        if(!isset($errMSG))
        {
          $sql="insert into user(id,name,password) values('$id','$name','$password')";
          if (!mysqli_query($con,$sql))
    {echo "1";
    die('Error: ' . mysqli_error($con));
    }
    else echo "0";
    mysqli_close($con);
        }
      #}
?>
