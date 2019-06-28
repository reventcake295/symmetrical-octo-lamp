<?php
class Js {
   
    private $script = '<js>
    <script src="'.SITE.'/js/jquery-1.12.4.min.js"></script>
    <script src="'.SITE.'/js/main.js"></script>
    <script src="'.SITE.'/js/easterEgg.js">';

    private $looseLines = '<script>const VERSION = "true Object Oriented version: 1.0";';

    private $form = '<script>function post(path,params,method){method = method || "post";let form = document.createElement("form");form.setAttribute("method", method);form.setAttribute("action", path);for(let key in params){if(params.hasOwnProperty(key)){let hiddenField=document.createElement("input");hiddenField.setAttribute("type","hidden");hiddenField.setAttribute("name",key);hiddenField.setAttribute("value", params[key]);form.appendChild(hiddenField);}}document.body.appendChild(form);form.submit();return;}';

    public function Js() {
        return;
    }
    public function addFile($file) {
        $this->script = '<script src="'.$file.'"></script><br>';
    }

    public function addLine($line) {
        $this->looseLines .= $line;
    }

    //post(&#39;'.SITE.'/shop?group='.$group.'&#39;, {dataGroup:&#39;shop&#39;, product: &#39;'.$products[$i]['ID'].'&#39;});
    public function constructForm($target, $params, $method) {
        $line = 'post("'.$target."{";
        foreach ($params as $key => $value) {
            $line .= $key.":'".$value."',";
        }
        $line = substr($line, 0, -1)."},".$method.");";
        $this->form .= $line;
    }

    public function buildJs() {
        return $this->form.'</script>'.$this->script.$this->looseLines .= '</script></js>';
    }
}
