<!-- start home page -->
<!--Booking Area Start-->
<div class="w3-padding w3-col l6 m8" style="margin-top:-370px; z-index:100;">
    <div class="w3-container w3-green">
      <h2><i class="fa fa-bed w3-margin-right"></i>Hotel California</h2>
    </div>
    <div class="w3-container w3-white w3-padding-16" style="border: 1px solid #4CAF50;">
        <form action="post" target="<?php echo SITE; ?>/reservation?type=random">
            <?php echo $controller->createDataGroup('avaibleSearch'); ?>
            <div class="w3-row-padding" style="margin:0 -16px;">
                <div class="w3-half w3-margin-bottom">
                    <label><i class="fa fa-calendar-o"></i> Check In</label>
                    <input class="w3-input w3-border" type="text" placeholder="DD MM YYYY" name="checkIn" required>
                </div>
                <div class="w3-half">
                    <label><i class="fa fa-calendar-o"></i> Check Out</label>
                    <input class="w3-input w3-border" type="text" placeholder="DD MM YYYY" name="checkOut" required>
                </div>
            </div>
            <div class="w3-row-padding" style="margin:8px -16px;">
                <div class="w3-half w3-margin-bottom">
                    <label><i class="fa fa-male"></i> Adults</label>
                    <input class="w3-input w3-border" type="number" value="1" name="adults" min="1" max="6">
                </div>
                <div class="w3-half">
                    <label><i class="fa fa-child"></i> Kids</label>
                    <input class="w3-input w3-border" type="number" value="0" name="kids" min="0" max="6">
                </div>
            </div>
            <button class="w3-button w3-dark-grey" type="submit" name="search"><i class="fa fa-search w3-margin-right"></i> Search availability</button>
        </form>
    </div>
</div>
<!--Booking Area End-->
<!-- start room showoff -->
<div class="w3-row-padding w3-padding-16">
    <h2>Avaible rooms</h2>
    <div class="w3-third w3-margin-bottom">
        <img src="<?php echo SITE; ?>/media/images/placeholder/room3.jpg" alt="Norway" style="width:100%">
        <div class="w3-container w3-white">
            <h3>Single Room</h3>
            <h6 class="w3-opacity">From $99</h6>
            <p>Single bed</p>
            <p>15m<sup>2</sup></p>
            <p class="w3-large"><i class="fa fa-bath"></i> <i class="fa fa-phone"></i> <i class="fa fa-wifi"></i></p>
            <a href="<?php echo SITE; ?>/reservation?type=single"><input class="w3-button w3-block w3-black w3-margin-bottom" type="button" value="Book now"></a>
        </div>
    </div>
    <div class="w3-third w3-margin-bottom">
        <img src="<?php echo SITE; ?>/media/images/placeholder/room1.jpg" alt="Norway" style="width:100%">
        <div class="w3-container w3-white">
            <h3>Double Room</h3>
            <h6 class="w3-opacity">From $149</h6>
            <p>Queen-size bed</p>
            <p>25m<sup>2</sup></p>
            <p class="w3-large"><i class="fa fa-bath"></i> <i class="fa fa-phone"></i> <i class="fa fa-wifi"></i> <i class="fa fa-tv"></i></p>
            <a href="<?php echo SITE; ?>/reservation?type=double"><input class="w3-button w3-block w3-black w3-margin-bottom" type="button" value="Book now"></a>
        </div>
    </div>
    <div class="w3-third w3-margin-bottom">
        <img src="<?php echo SITE; ?>/media/images/placeholder/room2.jpg" alt="Norway" style="width:100%">
        <div class="w3-container w3-white">
            <h3>Deluxe Room</h3>
            <h6 class="w3-opacity">From $199</h6>
            <p>King-size bed</p>
            <p>40m<sup>2</sup></p>
            <p class="w3-large"><i class="fa fa-bath"></i> <i class="fa fa-phone"></i> <i class="fa fa-wifi"></i> <i class="fa fa-tv"></i> <i class="fa fa-glass"></i> <i class="fa fa-cutlery"></i></p>            
            <a href="<?php echo SITE; ?>/reservation?type=deluxe"><input class="w3-button w3-block w3-black w3-margin-bottom" type="button" value="Book now"></a>
        </div>
    </div>
</div>
<!-- end room showoff -->
<!-- end home page -->