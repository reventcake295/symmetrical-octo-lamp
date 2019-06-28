<div class="w3-padding w3-col l4 m8" style="margin-top:-470px; z-index:100;" id="login">
    <div class="w3-container w3-green">
      <h2><i class="fas fa-user-lock w3-margin-right"></i>Sign Up</h2>
    </div>
    <div class="w3-container w3-white w3-padding-16" style="border: 1px solid #4CAF50;">
        <form action="<?php echo SITE; ?>/admin/login" method="post">
            <?php echo $controller->createDataGroup('admin/login'); ?>
            <input type="hidden" name="target" value="admin/dashboard">
            <div class="w3-row-padding" style="margin:0 -16px;">
                <div class="w3-margin-bottom">
                    <label><i class="fa fa-user"></i> Username</label>
                    <input class="w3-input w3-border" type="text" placeholder="Enter your username" name="username" required>
                </div>
            </div>
            <div class="w3-row-padding" style="margin:8px -16px;">
                <div class="w3-margin-bottom">
                    <label><i class="fa fa-key"></i> Password</label>
                    <input class="w3-input w3-border" type="password" placeholder="Enter your password" name="password">
                </div>
            </div>
            <div class="w3-row-padding" style="margin:8px -16px;">
                <div class="w3-margin-bottom">
                    <label><i class="fa fa-key"></i> Confirm Password</label>
                    <input class="w3-input w3-border" type="password" placeholder="Confirm your password" name="confirmPassword">
                </div>
            </div>
            <button class="w3-button w3-green" type="submit" name="submit">Confirm</button>
            <?php echo '<p>'.$language->getPageErrorMessage('register').'</p>'; ?>
            <p><?php echo $language->getPageMessage('loginMessage'); ?><a href="<?php echo SITE; ?>/admin/login"><?php echo $language->getPageMessage('loginButton'); ?></a>.</p>
        </form>
    </div>
</div>