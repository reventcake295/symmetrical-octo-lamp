<?php
class Menu {
    private $search = '';

    private $login = '';

    private $register = '';

    private $dashboard = '';

    public function Menu() {// make sure the the table is avaible
        global $database;
        try {
            $database->createTable('menu', "CREATE TABLE `menu` (
                `ID` INT NOT NULL AUTO_INCREMENT,
                `name` VARCHAR(20) NOT NULL,
                `link` VARCHAR(100) NOT NULL,
                `parent` INT NULL DEFAULT 0,
                `loggedIn` TINYINT NOT NULL DEFAULT 0,
                `adminLevel` INT NULL DEFAULT 0,
                PRIMARY KEY (`ID`));")->excecuteStatement();//TODO create the menu table create statement
                return $this;    
        } catch (Throwable $th) {
            $errorCode = $th->getCode();
            if ($errorCode === 7) {
                //Houston we're done here!
                return $this;
            } else {
                //Houston we've a problem
                $language->errors['database'] = ['unknown', $th->getMessage()];
                return;
            }
        }   
    }
    //structure of the table in mysql and thus in the array
    // 'ID' =>          "1"         (ID for identifacation of parent)
    // 'name' =>        "room"      (value to be identified as in the bar)
    // 'parent' =>      "0"         (0 = no parent, 1+ = child of parent ID)
    // 'link' =>        "admin/hoi" (SITE + / + value)
    // 'loggedIn' =>    "0"         (0 = not loggedIn, 1 = loggedIn, 2 = either loggedIn or not)
    // 'adminLevel' =>  "0"         (0 = dummy var, 1 = admin)

    private function my_ksort(&$arg) {
        $keys = array_keys($arg);
        sort($keys);
        foreach($keys as $key) {
            $val = $arg[$key];
            unset($arg[$key]);
            $arg[$key] = $val;
        }
    }
    
    private function prepareMenu($array) {
        $this->my_ksort($array);
        foreach ($array as $k => &$item) {
            $allowed = TRUE;
            //remove the items that or not allowd to be seen by the user due to wrong admin level
            if (((int)$item['loggedIn']) === 0) {
                if ($_SESSION['login']['loggedin'] === 1) {
                    unset($array[$k]);
                    $allowed = FALSE;
                }
            } else if (((int)$item['loggedIn']) === 1 && $_SESSION['login']['loggedin'] === 1) {
                //the item may only be viable to loggedin users and mayby whit certain clarification
                if (((int)$item['adminLevel']) !== $_SESSION['login']['adminLevel']) {
                    if (!((int)$item['adminLevel']) === -1) {
                        unset($array[$k]);
                        $allowed = FALSE;
                    }
                }
            } else if (((int)$item['loggedIn']) === 2) {
                //the item must be visable to all
            } else {
                unset($array[$k]);
                $allowed = FALSE;
            }
            //if the parent variable is not set to 0 and that it is allowed by acces level
            if (((int)$item['parent']) !== 0 && $allowed) {
                $parent = $item['parent'];
                //create the childs array so that the sub menu items can be determined later on
                if (!(isset($array[$parent]['childs']) && is_array($array[$parent]['childs']))) {
                    $array[$parent]['childs'] = array();
                }
                //shift the menu item into the childs of the parent
                array_unshift($array[$parent]['childs'],$item);
                unset($array[$k]);
            }
            
        }
        $this->my_ksort($array);
        // echo '<pre>';
        // var_dump($array);
        // echo '</pre>';
        return $array;
    }

    private function buildMenu($array) {
        global $pages;
        $menu = '';
        foreach ($array as $item) {
            $menu .= '<a href="'.SITE.'/'.$item['link'].'" class="w3-bar-item w3-button w3-light-grey w3-mobile">'.$item['name'].'</a>';
        }
        return $menu;
    }

    public function search() {
        global $pages;
        $this->search = '
        <form class="form-inline my-2 my-lg-0">
            <input type="hidden" name="datGroup" value="search">
            <input type="hidden" name="orign" value="'.$pages->page.'">
            <input class="form-control mr-sm-2" name="search" type="search" placeholder="Search" aria-label="Search">
            <input class="form-control btn btn-outline-success my-2 my-sm-0" type="submit" value="Search">
        </form>';
        return $this;
    }

    public function login() {
        if ($_SESSION['login']['loggedin'] === 1) {
            return $this;
        }
        $this->login = '<a href="'.SITE.'/admin/login" class="w3-bar-item w3-button w3-right w3-light-grey w3-mobile">Login</a>';
        return $this;
    }

    public function register() {
        if ($_SESSION['login']['loggedin'] === 1) {
            return $this;
        }
        $this->register = '<a href="'.SITE.'/admin/register" class="w3-bar-item w3-button w3-right w3-light-grey w3-mobile">Register</a>';
        return $this;
    }

    public function dashboard() {
        if ($_SESSION['login']['loggedin'] === 1) {
            $this->dashboard = '<a href="'.SITE.'/'.DASHBOARDS[$_SESSION['login']['adminLevel']].'" class="w3-bar-item w3-button w3-right w3-light-grey w3-mobile">Dashboard</a>';
            return $this;
        }
        return $this;
    }

    public function completeLoginSystem() {
        $this->login();
        $this->register();
        $this->dashboard();
        return $this;
    }

    private function subMenu($array) {
        $subMenu = '';
        foreach ($array as $item) {
            if (!empty($item['childs'])) {
                $subMenu .= '
                <li class="nav-item"><a href="'.SITE.'/'.$item['link'].'">'.$item['name'].'</a>
                    <!--Dropdown Menu Start-->
                    <ul>
                        '.$this->subMenu($item['childs']).'
                    </ul>
                    <!--Dropdown Menu End-->
                </li>';
            } else {
                $subMenu .= '<li class="nav-item'.($pages->pageName === $item['link'] ? ' active' : '').'"><a class="nav-link" href="'.SITE.'/'.$item['link'].'">H'.$item['name'].'</a></li>';
            }
        }
        return $subMenu;
    }

    public function init($name, $logo) {
        global $database;
        // construct the start of the menu
        $completeMenu = '<!-- Navigation Bar -->
                        <div class="w3-bar w3-white w3-large">
                            <a href="'.SITE.'/home" class="w3-bar-item w3-button w3-green w3-mobile"><i class="'.$logo.' w3-margin-right"></i>'.$name.'</a>';
        //get the menu from the database
        $menuItems = $database->select('menu', ['*'])->excecuteStatement();
        //sort out the menu items on acces level and sub item
        $menu = $this->prepareMenu($menuItems);
        // build the menu
        $completeMenu .= $this->buildMenu($menu);

        //finish of the menu and if there has been a set for search place it then
        return $completeMenu.$this->search.$this->login.$this->register.$this->dashboard.'</div>';
    }
}
