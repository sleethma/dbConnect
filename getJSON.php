<?php


$host="localhost";
$database="n5n0l7n9_users";
$username="n5n0l7n9_sleethm";
$password="SleethMasterUser00";

$con = mysqli_connect($host,$username,$password,$database);

$sql = "select * from users;";

$result = mysqli_query($con, $sql);
$response = array();

while($row = mysqli_fetch_array($result))
{
	array_push($response, array("name"=>$row[0], "password"=>$row[1],
		"contact"=>$row[2], "country"=>$row[3]));
}
echo  json_encode(array("server_response"=> $response));
mysqli_close($con);
?>