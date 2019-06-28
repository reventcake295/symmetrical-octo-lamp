<?php
//turn html compression on
ob_start("ob_gzhandler");
//set all the global variables so it can be used by the rest of the website
date_default_timezone_set('UTC');//otherwise somethings aren't liked by the system
define('FILES', ['ABSPATH'=>__DIR__.'/', 'VIEWS'=> __DIR__.'/views/', 'ADMINVIEWS'=>__DIR__.'/views/admin/', 'MODELS'=>__DIR__.'/models/', 
'ELEMENTS'=>__DIR__.'/elements/', 'CONTROLLER'=>__DIR__.'/controllers/', 'LIBS'=>__DIR__.'/libs/', 'MEDIA'=>__DIR__.'/media/', 'IMAGES'=>__DIR__.'/media/images/', 
'SOUND'=>__DIR__.'/media/sound/', 'VIDEO'=>__DIR__.'/media/video/', 'JS'=>__DIR__.'/js/', 'CSS'=>__DIR__.'/CSS/', 'CORE'=>__DIR__.'/core/', 
'TEXT'=>__DIR__.'/madia/text/','ERRORS'=>__DIR__.'/media/text/errors/', 'LOCALE'=>__DIR__.'/locale/']);
require(FILES['ABSPATH']. 'config.php');// get the config variables from its file
require(FILES['CORE']. 'php/functions.php');//get the core function required for operation of the website
//setup the global objects
session_start();
$_SESSION['login']['loggedin'] = isset($_SESSION['login']['loggedin']) ? $_SESSION['login']['loggedin'] : 0;
$_SESSION['login']['adminLevel'] = isset($_SESSION['login']['adminLevel']) ? $_SESSION['login']['adminLevel'] : 0;
//create the base line classes
$pages = new Page();//init already the base vars
$script = new Js();
$language = new Lang();
$database = new DataBase();
$user = new User();
$controller = new Controller();
$data = [];
foreach (glob("libs/*/function.php") as $dir) { include_once($dir); } // get the libs from its dirs
//check for acces to possible forbidden area's and mayby the excecutor call
$controller->excecutorCall();//call to the excecutor controller so the data may be proccessed
$pages->areaLock();//make sure that the user is allowed to acces that page

$pages->headerAndFooter();//get the specific header and footer for that page
$pages->buildPage();//build the page whit the global vars passed along so they may be used by the page itself
