<?php
  $x = htmlspecialchars($_POST["x"]);
  $y = htmlspecialchars($_POST["y"]);
  $r = htmlspecialchars($_POST["r"]);
  $currentTime = date("H:i:s", strtotime('0 hour'));
  $start = microtime(true);

  function check($x, $y, $r){
    return(($y<=0 && $x >=0 && $y>=-$r/2 && $x <= $r) ||
          ($x<=0 && $y >=0 && $x >= $y - $r/2) ||
          ($x<=0 && $y <=0 && $x*$x + $y*$y <= $r*$r));
  }
  if(!is_numeric($x) || !in_array($x, array(-4,-3,-2, -1, 0, 1, 2, 3, 4)) || !is_numeric($y) || $y >= 5 || $y <= -5 || !in_array($r, array(1, 1.5, 2, 2.5, 3))){
    http_response_code(400);
    return;
  }

  $time = microtime(true) - $start;
?>
<html>
  <body>
    <table>
      <tr>
        <td>
          <?php $res = check($x, $y, $r);
            if($res){
              echo("Point fits");
            }else{
              echo("Point not fits");
            }
          ?>
        </td>
        <td>
          <p>
          <?php
          echo "x = ";
          echo($x);
          ?>
          <p>
          <?php
          echo "y = ";
          echo($y);
          ?>
          <p>
          <?php
          echo "r = ";
          echo($r); ?>
          <p>
          <?php
          echo "time of start: ";
          echo($currentTime);
           ?>
           <p>
           <?php
           echo "time of action in microseconds:  ";
           echo($time);
            ?>
        </td>
    </tr>

  </table>
  </body>
</html>
