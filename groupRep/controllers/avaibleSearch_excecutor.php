<?php
if (!(isset($_POST['adults']) && isset($_POST['kids']) && isset($_POST['checkIn']) && isset($_POST['checkOut']) && isset($_POST['search']))) return;
$adults = $_POST['adults'];
$kids = $_POST['kids'];
$checkIn = $_POST['checkIn'];
$checkOut = $_POST['checkOut'];
$data['checkIn'] = $checkIn;
$data['checkOut'] = $checkOut;
$data['adults'] = $adults;
$data['kids'] = $kids;

