<!-- start  reservation page -->
<?php
$values = ['kids' => 2, 'adults' => 3, 'checkIn' => '', 'checkOut' => ''];
$maxumums = ['kids' => 6, 'adults' => 6];
switch ($_GET['type']) {
    case 'single':
        $values = ['kids' => 0, 'adults' => 1, 'checkIn' => '', 'checkOut' => ''];
        $maxumums = ['kids' => 1, 'adults' => 1];
        break;
    case 'double':
        $values = ['kids' => 1, 'adults' => 2, 'checkIn' => '', 'checkOut' => ''];
        $maxumums = ['kids' => 4, 'adults' => 4];
        break;
    case 'deluxe':
        break;
    case 'random'://arrived here through a search procces so serveral vars are set which normally aren't
        $values = ['kids' => $data['kids'], 'adults' => $data['adults'], 'checkIn' => $data['checkIn'], 'checkOut' => $data['checkOut']];
        break;
    default:
        echo 'no type of room found under that name';
        break;
}
?>
<form action="<?php echo SITE; ?>/reservation?type=random" method="post">
    <div class="w3-padding w3-col l6 m8" style="margin-top:-370px; z-index:100;">
        <div class="w3-container w3-green">
            <h2><i class="fa fa-bed w3-margin-right"></i>Hotel California room finder</h2>
        </div>
        <div class="search">
            <div class="w3-container w3-white w3-padding-16" style="border: 1px solid #4CAF50;">
                <?php 
                $controller = new Controller();
                echo $controller->createDataGroup('reserve');
                ?>
                <div class="w3-row-padding" style="margin:0 -16px;">
                    <div class="w3-half w3-margin-bottom">
                        <label><i class="fa fa-calendar-o"></i> Check In</label>
                        <input id="checkIn" class="w3-input w3-border" type="text" placeholder="DD MM YYYY" name="checkIn" value="<?php echo $values['checkIn']?>" required>
                    </div>
                    <div class="w3-half">
                        <label><i class="fa fa-calendar-o"></i> Check Out</label>
                        <input id="checkOut" class="w3-input w3-border" type="text" placeholder="DD-MM-YYYY" value="<?php echo $values['checkOut']?>" name="checkOut" required>
                    </div>
                </div>
                <div class="w3-row-padding" style="margin:8px -16px;">
                    <div class="w3-half w3-margin-bottom">
                        <label><i class="fa fa-male"></i> Adults</label>
                        <input id="adults" class="w3-input w3-border search" type="number" value="<?php echo $values['adults']?>" name="adults" min="1" max="<?php echo $maximums['adults']?>">
                    </div>
                    <div class="w3-half">
                        <label><i class="fa fa-child"></i> Kids</label>
                        <input id="kids" class="w3-input w3-border search" type="number" value="<?php echo $values['kids']?>" name="kids" min="0" max="<?php echo $maximums['kids']?>">
                    </div>
                </div>
                
            </div>
        </div>
    </div>
    <div id="options">
        <h2>Possible rooms for you:</h2>
        <div class="w3-third w3-margin-bottom option" data-kids="1" data-adults="1">
            <img src="<?php echo SITE; ?>/media/images/placeholder/room3.jpg" alt="Norway" style="width:100%">
            <div class="w3-container w3-white">
                <h3>Single Room</h3>
                <h6 class="w3-opacity">From $99</h6>
                <p>Single bed</p>
                <p>15m<sup>2</sup></p>
                <i class="fa fa-male"></i> 1 <i class="fa fa-child"></i> 1
                <p class="w3-large"><i class="fa fa-bath"></i> <i class="fa fa-phone"></i> <i class="fa fa-wifi"></i></p>
                <a href="<?php echo SITE; ?>/reservation?type=single"><input class="w3-button w3-block w3-black w3-margin-bottom" type="button" value="Book now"></a>
            </div>
        </div>
        <div class="w3-third w3-margin-bottom option" data-kids="4" data-adults="4">
            <img src="<?php echo SITE; ?>/media/images/placeholder/room1.jpg" alt="Norway" style="width:100%">
            <div class="w3-container w3-white">
                <h3>Double Room</h3>
                <h6 class="w3-opacity">From $149</h6>
                <p>Queen-size bed</p>
                <p>25m<sup>2</sup></p>
                <i class="fa fa-male"></i> 4 <i class="fa fa-child"></i> 4
                <p class="w3-large"><i class="fa fa-bath"></i> <i class="fa fa-phone"></i> <i class="fa fa-wifi"></i> <i class="fa fa-tv"></i></p>
                <input class="w3-button w3-block w3-black w3-margin-bottom" type="button" value="Book now">
            </div>
        </div>
        <div class="w3-third w3-margin-bottom option" data-kids="6" data-adults="6">
            <img src="<?php echo SITE; ?>/media/images/placeholder/room2.jpg" alt="Norway" style="width:100%">
            <div class="w3-container w3-white">
                <h3>Deluxe Room</h3>
                <h6 class="w3-opacity">From $199</h6>
                <p>King-size bed</p>
                <p>40m<sup>2</sup></p>
                <i class="fa fa-male"></i> 6 <i class="fa fa-child"></i> 6
                <p class="w3-large"><i class="fa fa-bath"></i> <i class="fa fa-phone"></i> <i class="fa fa-wifi"></i> <i class="fa fa-tv"></i> <i class="fa fa-glass"></i> <i class="fa fa-cutlery"></i></p>            
                <input class="w3-button w3-block w3-black w3-margin-bottom" type="button" value="Book now">
            </div>
        </div>
        <!-- <input class="w3-button w3-dark-grey" type="submit" name="submit" value="1" placeholder="Book one of these!"> -->    
    </div>    
</form>
<!-- end reservation page -->