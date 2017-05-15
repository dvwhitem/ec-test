<?php

$ch = curl_init();

curl_setopt($ch, CURLOPT_URL, "https://spring-boot-rest-app-167308.appspot.com/oauth/token");
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, "grant_type=password&username=guest&password=guest123");
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_USERPWD, "phpclient" . ":" . "phpclient123");

$headers = array();
$headers[] = "Content-Type: application/x-www-form-urlencoded";
curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);

$response = curl_exec($ch);

if (curl_errno($ch)) {
    echo 'Error1:' . curl_error($ch);
}
curl_close ($ch);

$result = json_decode($response);
curl_close($curl);
if ( isset($result->error) ) {
    die($result->error_message);
}
$token = $result->access_token;

//------------------------------------------------------------------------------------------------

$ch = curl_init();

curl_setopt($ch, CURLOPT_URL, "https://spring-boot-rest-app-167308.appspot.com/counter");
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "GET");


$headers = array();
$headers[] = "Authorization: Bearer ".$token;
curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);

$result = curl_exec($ch);

if (curl_errno($ch)) {
    echo 'Error2:' . curl_error($ch);
}
curl_close ($ch);

//----------------------------------------------------------------------------------------------------

$ch = curl_init();

curl_setopt($ch, CURLOPT_URL, "https://spring-boot-client-app-167709.appspot.com/". $result);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
$result = curl_exec($ch);
if (curl_errno($ch)) {
    echo 'Error3:' . curl_error($ch);
}
curl_close ($ch);