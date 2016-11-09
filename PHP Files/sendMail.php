<?php 
// the message 
if (isset($_POST['email'])){
$headers =  'MIME-Version: 1.0' . "\r\n"; 
$headers .= 'From: ultimateweapon9@gmail.com' . "\r\n";
$headers .= 'Content-type: text/html; charset=iso-8859-1' . "\r\n"; 
$email=$_POST['email']; 
$msg = "Hello World! \xA  http://192.168.1.90/applehours.html";
$msg  = wordwrap($msg,70);
$to = $email;
$subject ="Community Service Response needed.";
$headers = "From:ultimateweapon9@gmail.com";
mail($to,$subject,$msg,$headers);
echo "check";
}
 else {
 	echo "No email selected";
 }
 ?>