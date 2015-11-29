<?php
/*
 * Author : Shivakumar Soppannavar
 * Start Date: 11/25/2015
 * Last edited :  11/29/2015
 * Central server program which opens the socket and connnects to host
 * to get network related information and displays the data
*/

$domain=$_GET["host"];		//Srore the host IP in domain
$portno="8888";				// Port number on which resident program on each system is running

$addr = gethostbyname("$domain");
$starttime = microtime(true);
$client = @stream_socket_client("tcp://$addr:$portno", $errno, $errorMessage);
//   echo stream_get_contents($client);

if ($client === false) {
   // throw new UnexpectedValueException("Failed to connect: $errorMessage");
   //echo "{error: Unable to connect to host ", $domain, "}" ,"</br>";
	$e= new errorClass();
	$e->Error="Unable to connnect";
	$e->Host_IP=$domain;	
	echo json_encode($e);
}
else {
  $stoptime  = microtime(true);
  $pingtime = floor (($stoptime - $starttime) * 1000);
  
  $pt=new pingTimeClass();
  $pt->Message="Successfully connected to ".$domain;
  $pt->Ping_Time=$pingtime." ms";	//concatenation
  echo json_encode($pt), "</br>";
  
  fwrite($client, "GET / HTTP/1.0\r\nHost: $domain\r\nAccept: */*\r\n\r\n");
  //echo stream_get_contents($client);
  $clientOutput = stream_get_contents($client);
  $tcp=new connectionInfoClass();
  $tcp->Info="Number of open TCP connections";
  $tcp->Value= substr_count($clientOutput,"TCP");
  $udp=new connectionInfoClass();
  $udp->Info="Number of open UDP connections";
  $udp->Value=substr_count($clientOutput,"UDP");
  echo json_encode($tcp), "</br>";
  echo json_encode($udp), "</br>";
  echo $clientOutput;
  //
}
@fclose($client);

class errorClass{
	public $Error;
	public $Host_IP;
}
class pingTimeClass{
	public $Message;
	public $Ping_Time;
}
class connectionInfoClass{
	public $Info;
	public $Value;
}
?>