<?php
class Lang {

    private $langFolder;
    
    public $errors;

    public $succes;

    public function Lang() {//get the lang folder that is required for this user
        $this->langFolder = FILES['LOCALE'].(file_exists(FILES['LOCALE'].((locale_accept_from_http($_SERVER['HTTP_ACCEPT_LANGUAGE']) !== null) ? 
        locale_accept_from_http($_SERVER['HTTP_ACCEPT_LANGUAGE']) : "en_US")."/global.lang") ?  locale_accept_from_http($_SERVER['HTTP_ACCEPT_LANGUAGE']) : "en_US")."/";
        return $this;
    }

    public function globalAlert() {//create a global alert if these are set
        if ($this->checkError()) {//check for error messages
            if ($this->errorIsSet('database')) {//check if a database error was set
                return '<div class="alert alert-danger" role="alert"><i class="fas fa-exclamation-circle"></i>'
                .$this->getDatabaseError().'</div>';
            }
        }

        if ($this->checkSucces()) {//check for succes messages
            if ($this->succesIsSet('login')) {//check for any login message
                return '<div class="alert alert-success" role="alert"><i class="far fa-check-circle"></i>'
                    .$this->getGlobalSuccesMessage('login').'</div>';
            }

            if ($this->succesIsSet('register')) {//check for any register message
                return '<div class="alert alert-success" role="alert"><i class="far fa-check-circle"></i>'
                    .$this->getGlobalSuccesMessage('register').'</div>';
            }
            
            if ($this->succesIsSet('logout')) {//check for any logout message
                return '<div class="alert alert-success" role="alert"><i class="far fa-check-circle"></i>'
                    .$this->getGlobalSuccesMessage('logout').'</div>';
            }
        }
        return '';
    }

    public function checkError() {//check wheter or not the errors array has any errors set
        return is_array($this->errors);
    }

    public function checkSucces() {//check wheter or not the succes array has any succeses set
        return is_array($this->succes);
    }

    public function errorIsSet($errorCode) {//check wheter or not a error is set
        return isset($this->errors[$errorCode]);
    }

    public function succesIsSet($succesCode) {//check wheter or not a succes is set
        return isset($this->succes[$succesCode]);
    }

    //messageCode: login.fillInRequest
    public function getPageMessage(String $messageCode) {//get a certain message set for this page
        global $pages;
        return $this->getMessage($pages->page, $messageCode);
    }

    public function getDatabaseError() {
        if (DEBUG === 1) {//check if the debug mode is turned on or not
            //if debug is turned on replace the placeholder whith the message
            return str_replace('%errorMsg', $this->errors['database'][1], $this->getMessage('global', 'debug.'.$this->errors['database'][0]));
        } else {
            return $this->getMessage('global', $this->errors['database'][0]);
        }
    }

    //messageCode: global.footer.copyright
    public function getGlobalMessage(String $messageCode) {//get a global message
        return $this->getMessage("global", $messageCode);
    }

    //messageCode: login.password.empty
    public function getPageErrorMessage($errorCode) {//get a page error message specified in the errors array
        global $pages;
        if (isset($this->errors[$errorCode])) {
            return $this->getMessage($pages->page, $errorCode.'.'.$this->errors[$errorCode]);
        } else {
            return '';
        }
    }

    //messageCode: global.error.unknown
    public function getGlobalErrorMessage($errorCode) {//get a global error message set in the errors array
        if (isset($this->errors[$errorCode])) {
            return $this->getMessage('global', $this->$errors[$errorCode].'.'.$this->errors[$errorCode]);
        } else {
            return '';
        }
    }

    //messageCode: register.register.registerd
    public function getPageSuccesMessage($succesCode) {//get a page succes message set in the succes array
        global $pages;
        if (isset($this->succes[$succesCode])) {
            return $this->getMessage($pages->page, $succesCode.'.'.$this->succes[$succesCode]);
        } else {
            return '';
        }
    }

    //messageCode: global.header.welcome
    public function getGlobalSuccesMessage($succesCode) {//get a global succes message from the succes array
        if (isset($this->succes[$succesCode])) {
            return $this->getMessage('global', $succesCode.'.'.$this->succes[$succesCode]);
        } else {
            return '';
        }
    }

    private function getMessage(String $page, String $messageCode) {//get a message from the one of the files in the correct locale folder specified in the langFolder var
        $locale = fopen($this->langFolder.$page.'.lang', 'r');
        $messageCode .= ': ';
        while (!feof($locale)) {
            $line = fgets($locale);
            if (startsWith($line, $messageCode)) {
                fclose($locale);
                return substr($line, strpos($messageCode, ':') + 2, strlen($line));
            }
        }
        fclose($locale);
        return '';
    }

    
}