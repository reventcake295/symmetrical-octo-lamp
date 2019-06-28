<!DOCTYPE html>
<html lang="en">
<head>
    <metaData>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
    </metaData>
    <css>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="<?php echo SITE;?>/css/main.css">
    </css>
    <title><?php echo $pages->pageName?> - Hotel California</title>
</head>
<body>
    <div class="wrapper">
        <?php
        $menu = new Menu();
        echo $menu->login()->dashboard()->init('Hotel California', 'fa fa-bed');
        echo $language->globalAlert();
        ?> 
        <header class="w3-display-container w3-content">
            <img class="w3-image" src="<?php echo SITE; ?>/media/images/placeholder/5.jpg" alt="The Hotel">
        </header><!-- end header element -->
        <!-- start main page content -->
        <main id="main" class="w3-content <?php echo $pages->pageName;?>">
            <!-- end header file -->