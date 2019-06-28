<?php
class Page {
    
    //set them already to the standard values
    public $page = "home";

    public $pageName = "home";

    public $headerVersion = "";

    public $footerVersion = "";

    public function Page() {
        $urlExist = FALSE;
        if (isset($_GET['url'])) {// get the page from the url and if empty set the url and page to home
            $this->page = $_GET['url'];
            $urlExist = TRUE;
        } else {
            $_GET['url'] = $this->page;
        }
        
        // define the base url for this website 

        $site =substr(str_replace($this->page, "", (isset($_SERVER['HTTPS']) && $_SERVER['HTTPS'] === 'on' ? "https" : "http")."://".
        $_SERVER['HTTP_HOST'].$_SERVER['REQUEST_URI']), 0, -1);
        if (strpos($site, '?')) {
            $site = substr($site, 0, strpos($site, '?'));
        }
        define('SITE', $site);
        return;
    }

    /**
     * get the correct controller if the user has acces to that folder
     */
    public function areaLock() {//prevent the user from accesing folders he has no acces to
        if (strpos($_GET["url"], '/') !== 0 && in_array(substr($_GET["url"], 0, strpos($_GET["url"], '/')), LOCKEDAREAS)) {
            if (!in_array($_SESSION['login']['adminLevel'], ACCESLEVEL[substr($_GET["url"], 0, strpos($_GET["url"], '/'))])) {
                if (!is_array(LOCKEDAREAALLOWEDPAGES[substr($_GET["url"], 0, strpos($_GET["url"], '/'))]) && !in_array($_GET['url'], LOCKEDAREAALLOWEDPAGES[substr($_GET["url"], 0, strpos($_GET["url"], '/'))])) {
                    $this->page = LOCKEDAREAREDIRECTPAGE[substr($_GET["url"], 0, strpos($_GET["url"], '/'))];
                }
            }
        }
        return;
    }

    /**
     * check what kind of header and/or footer must be used for the page based on the url
     */
    public function headerAndFooter() {
        if (strpos($_GET['url'], '/') !== false) {// if the url acceses a sub folder of the views map check if a special header and footer are needed
            $this->versionName = substr($_GET['url'], 0, strpos($_GET['url'], '/'));
            $this->pageName = substr($this->page, strpos($this->page, '/') + 1, strlen($this->page));
            if (in_array($this->versionName, PAGES)) {
                if (file_exists(FILES['ABSPATH']. "elements/".$this->versionName."_header_element.php")) 
                    $this->headerVersion = $this->versionName. "_";
                if (file_exists(FILES['ABSPATH']. "elements/".$this->versionName."_footer_element.php"))
                    $this->footerVersion = $this->versionName. "_";
            }
        } else {//set the page name correct because otherwise some esthetic stuff do not work correctly
            $this->pageName = $this->page;
        }
        return;
    }

    /**
     * build the page itself
     */
    public function buildPage() {
        global $script, $language, $pages, $user, $controller, $language, $database, $data;
        //start building the page out of the files required
        //get the header for this pageGroup
        require_once(FILES['ABSPATH']. "elements/".$this->headerVersion."header_element.php");
        //if the view does not exist then redirect to the 404 page
        require_once(FILES['ABSPATH']. "views/" .(file_exists(FILES['ABSPATH']. "views/" .$this->page. "_page.php") ? $this->page : '404'). "_page.php");
        //get the footer for this pageGroup
        require_once(FILES['ABSPATH']. "elements/".$this->footerVersion."footer_element.php");
        return;
    }
}
