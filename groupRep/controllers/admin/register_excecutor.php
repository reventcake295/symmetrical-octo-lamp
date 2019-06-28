<?php 
// Validate username
$username;
if(empty(trim($_POST["username"]))){
    $language->error['username'] = "empty";
} else if (!$user->checkUser($_POST['username'])) {
    $username = $_POST['username'];
}
if (!$user->checkUser($username)) {
    $language->errors['register'] = 'nameInUse';
}

// //mail check
// if(empty(trim($_POST["email"]))){
//     $language->error['email'] = "empty";     
// } else {
//     $email = trim($_POST["email"]);
// }

// //home addres test
// $postcode;
// if(empty(trim($_POST["postcode"])) || empty(trim($_POST["postcode2"]))){
//     $language->error['postcode'] = "empty";  
// } else {
//     $postcode = trim($_POST["postcode"]) . trim($_POST["postcode2"]);
// }
// 
// $houseNumber;
// $housenumber;
// if(empty(trim($_POST["houseNumber"]))){
//     $language->error['houseNumber'] = "empty";     
// } else {
//     $houseNumber = trim($_POST["houseNumber"]);
// }
// if(!(empty(trim($_POST["extra"])))){     
//     $extra = trim($_POST["extra"]);
// }

// Validate password
$password;
if(empty(trim($_POST["password"]))){
    $language->error['password'] = "empty";     
} else if (strlen(trim($_POST["password"])) < 8){
    $language->error['password'] = "<8";
} else {
    $password = trim($_POST["password"]);
}
    
// Validate confirm password
if (!$language->checkError()) {
    if(empty(trim($_POST["confirmPassword"]))){
        $language->error['confirmPassword'] = "empty";     
    } else {
        $confirm_password = trim($_POST["confirmPassword"]);
        if(empty($password_err) && ($password != $confirm_password)){
            $language->error['password'] = "noMatch";
        }
    }
}
    
// Check input errors before inserting in database
if(!$language->checkError()) {
    $user->userRegister($username, $password, 0);
    $pages->page = 'admin/login';
}
