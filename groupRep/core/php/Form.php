<?php
class Form {

    public function formGroup($type, $name, $icon = '') {
        global $language;
        $formGroup = '<span class="help-block">'.$language->getPageErrorMessage($name).'</span>
        <label for="'.$name.'">'.$language->getPageMessage($name).'</label>
        <div class="input-group mb-3">';
    
        if ($icon !== '') {
            $formGroup .= '<div class="input-group-prepend">
                    <span class="input-group-text" id="'.$name.'Addon"><i class="'.$icon.'"></i></span>
                </div>';
        }
        return $formGroup.'
                <input type="'.$type.'" id="'.$name.'" name="'.$name.'" class="form-control" aria-label="'.$name.'" aria-describedby="'.$name.'Addon">
            </div>
            <span class="help-block">'.$language->getPageErrorMessage($name).'</span>';
    }

    public function passwordGroup($name) {
        global $language;
        return '<span class="help-block">'.$language->getPageErrorMessage($name).'</span>
            <label for="'.$name.'">'.$language->getPageMessage($name).'</label>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="'.$name.'Addon"><i class="fas fa-key"></i></span>
                </div>
                <input type="password" id="'.$name.'" name="'.$name.'" class="form-control" aria-label="'.$name.'" aria-describedby="'.$name.'Addon">
            </div>
            <span class="help-block">'.$language->getPageErrorMessage($name).'</span>';
    }
}