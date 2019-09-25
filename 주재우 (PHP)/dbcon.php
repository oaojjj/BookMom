<?php
$hostname = "localhost"; //아아피 혹은 도메인이름
$username= "root";   //아이디 (root)
$password = "1234"; //root 비번
$dbname = "book_db";   //데이터베이스 이름 중 하나
$options = array(PDO::MYSQL_ATTR_INIT_COMMAND => 'SET NAMES utf8');
$con=mysqli_connect("localhost","root","1234","book_db");
if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }

  # $con->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  # $con->setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, PDO::FETCH_ASSOC);

   if(function_exists('get_magic_quotes_gpc') && get_magic_quotes_gpc()) {
       function undo_magic_quotes_gpc(&$array) {
           foreach($array as &$value) {
               if(is_array($value)) {
                   undo_magic_quotes_gpc($value);
               }
               else {
                   $value = stripslashes($value);
               }
           }
       }

       undo_magic_quotes_gpc($_POST);
       undo_magic_quotes_gpc($_GET);
       undo_magic_quotes_gpc($_COOKIE);
   }

   header('Content-Type: text/html; charset=utf-8');
   $_SESSION['name']=null;
   session_start();
?>
