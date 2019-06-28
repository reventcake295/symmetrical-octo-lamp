<?php
class Controller {

    public function excecutorCall() {
        //set these vars to global vars so they are updated directly and may be used later on whit their information intact
        global $script, $language, $pages, $user, $controller, $language, $database, $data;
        if($_SERVER["REQUEST_METHOD"] == "POST") {
            //make sure the post is being called and that dataGroup isset
            if (!isset($_POST["dataGroup"])) {
                return;
            }
            $dataGroup = $_POST["dataGroup"];
            if (strpos($dataGroup, '/') !== 0 && in_array(substr($dataGroup, 0, strpos($dataGroup, '/')), LOCKEDAREAS)) {
                if (in_array($_SESSION['login']['adminLevel'], ACCESLEVEL[substr($dataGroup, 0, strpos($dataGroup, '/'))])) {
                    require_once(FILES['CONTROLLER'].$dataGroup.'_excecutor.php');
                    return;
                } else if (is_array(LOCKEDAREAALLOWEDPAGES[substr($dataGroup, 0, strpos($dataGroup, '/'))]) && 
                    in_array($dataGroup, LOCKEDAREAALLOWEDPAGES[substr($dataGroup, 0, strpos($dataGroup, '/'))])) {
                    require_once(FILES['CONTROLLER'].$dataGroup.'_excecutor.php');
                    return;
                }
            }
        }
        return;
    }

    public function createDataGroup($excecutor) {
        if (strpos($excecutor, '/') !== 0 && in_array(substr($excecutor, 0, strpos($excecutor, '/')), LOCKEDAREAS)) {
            if (in_array($_SESSION['login']['adminLevel'], ACCESLEVEL[substr($excecutor, 0, strpos($excecutor, '/'))])) {
                return '<input type="hidden" name="dataGroup" value="'.$excecutor.'">';
            } else if (is_array(LOCKEDAREAALLOWEDPAGES[substr($excecutor, 0, strpos($excecutor, '/'))]) && 
                in_array($excecutor, LOCKEDAREAALLOWEDPAGES[substr($excecutor, 0, strpos($excecutor, '/'))])) {
                return '<input type="hidden" name="dataGroup" value="'.$excecutor.'">';
            }
        }
        return '';
    }
}