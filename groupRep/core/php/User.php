<?php
class User {
    
    function User() {// make sure the the table is avaible
        global $language, $database;
        try {
            $result = $database->createTable('user', "CREATE TABLE `user` (
                `ID` INT NOT NULL AUTO_INCREMENT,
                `name` VARCHAR(16) NOT NULL,
                `password` CHAR(60) NOT NULL,
                `email` VARCHAR(255) NULL,
                `adminLevel` INT NOT NULL DEFAULT 0,
                PRIMARY KEY (`ID`));");
            if ($result === TRUE) {
                return $this;
            }
            $database->excecuteStatement();
            return $this;    
        } catch (Throwable $th) {
            $errorCode = $th->getCode();
            //make sure that what is thrown not the already exist return code is
            if ($errorCode === 7) {
                //Houston we're done here!
                return $this;
            } else {
                //Houston we've a problem
                $language->errors['database'] = ['userTableCreate', $th->getMessage()];
                return $this;
            }
        }
    }

    function getUser($ID) {// get the user whit the correct ID
        global $language, $database;
        try {
            return $database->select('user', ['*'])->where(['ID'], [$ID])->excecuteStatement();
        } catch (Throwable $th) {
            $language->errors['database'] = ['getUser', $th->getMessage()];
            return FALSE;
        }
    }

    function getUsers() {// get all the users from the user table
        global $language, $database;
        try {
            return $database->select('user', ['*'])->excecuteStatement();
        } catch (Exception $exception) {
            //give of an error because of an exception
            $language->errors['database'] = ['getUsers', $exception->getMessage()];   
            return FALSE;
        }
    }

    function checkUser($name) {// check whether or not the user exist
        global $language, $database;
        try {
            $boolean = (count($database->select('user', ['*'])->where(['name'], [$name])->excecuteStatement()) === 1);
            return $boolean;
        } catch (Exception $exception) {
            //give of an error because of an exception
            $language->errors['database'] = ['checkUser', $exception->getMessage()];   
            return FALSE;
        }
    }

    function getAdminLevel($ID) {//get the adminLevel as set in the database
        global $language, $database;
        try {
            $adminLevel = $database->select('user', ['adminLevel'])->where(['ID'], [$ID])->excecuteStatement();
            return $adminLevel[0]['adminLevel'];
        } catch (Exception $exception) {
            //give of an error because of an exception
            $language->errors['database'] = ['getAdminLevel', $exception->getMessage()];   
            return FALSE;
        }
    }

    function userRegister($username, $password, $adminLevel) {//register the user in the database
        global $language, $database;
        try {
            $hased_password = password_hash(md5($password), PASSWORD_DEFAULT);
            $database->insert('user', ['name', 'password', 'adminLevel'], [$username, $hased_password, $adminLevel])->excecuteStatement();
            $language->succes['register'] = "registerd";
            return TRUE;
        } catch (Exception $exception) {
            //give of an error because of an exception
            $language->errors['database'] = ['register', $exception->getMessage()];   
            return FALSE;
        }
    }

    function userLogin($username, $password) {
        global $pages, $language, $database;
        try {
            $stmt = $database->select('user', ['*'])->where(['name'], [trim($_POST["username"])])->excecuteStatement();

            if(count($stmt) == 1) {
                if (password_verify(md5($password), $stmt[0]["password"])) {
                    // Store data in session variables
                    $_SESSION['login']["loggedin"] = 1;
                    $_SESSION['login']["id"] = $stmt[0]["ID"];
                    $_SESSION['login']["username"] = $stmt[0]["name"];                            
                    $_SESSION['login']["adminLevel"] = $stmt[0]['adminLevel'];
                    $pages->page = $_POST['target'];//and then redirect to the targeted page
                    $language->succes['login'] = 'loggedIn';
                    return TRUE;//Houston we have found the spoon!
                } else {
                    // give of an error if the password is wrong
                    $language->errors['login'] = 'passwordWrong';
                    return FALSE;
                }
            } else {
                // give of an error if no acount is found
                $language->errors['login'] = 'usernameUnknown';
                return FALSE;
            }
        } catch (Exception $exception) {
            //give of an error because of an exception
            $language->errors['database'] = ['login', $exception->getMessage()];   
            return FALSE;
        }
        return TRUE;
    }

    function userLogout($redirect) {
        global $pages, $language;
        // Unset all of the login session variables
        unset($_SESSION['login']); 
        //set the loggedin and adminLevel to zero so nothing crashes because of a unset variable
        $_SESSION['login']['loggedin'] = 0;
        $_SESSION['login']['adminLevel'] = 0;
        // Redirect to home page
        $pages->page = $redirect;
        $language->succes['logout'] = 'loggedOut';
        return TRUE;
    }

    function userDelete($ID) {
        global $language, $database;
        try {
            $database->delete('user')->where(['ID'], [$ID])->excecuteStatement();
            return TRUE;
        } catch (Throwable $th) {
            $language->errors['database'] = ['userDelete', $th->getMessage()];
            return FALSE;
        }
    }
}