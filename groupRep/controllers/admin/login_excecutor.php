<?php
// Check if the user is already logged in, if yes then redirect him to welcome page
if($_SESSION['login']['loggedIn']) {
    $pages->page = $_POST['target'];
    return; 
}
// Define variables and initialize with empty values
$username = $password = "";
 
// Check if username is empty
if(empty(trim($_POST["username"]))){
    $language->errors['username'] = "empty";
} else {
    $username = trim($_POST["username"]);
}
    
// Check if password is empty
if(empty(trim($_POST["password"]))){
    $language->errors['password'] = "empty";
} else{
    $password = trim($_POST["password"]);
}
    
// Validate credentials
if(!$language->checkError()) {
    $user->userLogin($username, $password);
}

