<pre>
<?php
$ip = $_GET['link'];
exec('ping -n 3 '. $ip, $output, $status);
print_r($output);
if ($status)
echo "Ping unsuccessful!";
else
echo "Ping successful!";
?>
<pre>
