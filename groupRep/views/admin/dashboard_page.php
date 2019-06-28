<div class="wrapper">
<?php
if (isset($_GET['id'])) {
    if (file_exists(MODELS.$_GET['id'].'_model.php')) {
        include_once(MODELS.$_GET['id'].'_model.php');
    }
}
?>
</div>