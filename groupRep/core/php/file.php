<?php
class File {

    private $uploadOk = 1;

    private $imageFormats = ['jpg', 'png', 'jpeg', 'gif', 'tif', 'tiff', 'gif', 'jpeg', 'jpg', 'jif', 'jfif', 
                            'jp2', 'jpx', 'j2k', 'j2c', 'fpx', 'pcd', 'png', 'pdf'];

    private $videoFormats = ['webm', 'mkv', 'vob', 'ogv', 'ogg', 'drc', 'gif', 'gifv', 'mng', 'avi', 'MTS', 'M2TS', 'mov',
                            'qt', 'wmv', 'yuv', 'rm', 'rmvb', 'asf', 'amv', 'mp4', 'mp2', 'mpeg', 'mpe', 'mpv', 'mpg', 
                            'm2v', 'm4v', 'svi', '3gp', '3g2', 'mxf', 'roq', 'nsv', 'flv', 'f4v', 'f4p', 'f4a', 'f4b'];

    private $audioFormats = ['3gp', 'aa', 'aac', 'aax', 'act', 'aiff', 'amr', 'ape', 'au', 'awb', 'dct', 'dss', 'dvf', 
                            'flac', 'gsm', 'iklax', 'ivs', 'm4a', 'm4b', 'm4p', 'mmf', 'mp3', 'mpc', 'msv', 'nmf', 'nsf', 
                            'ogg', 'oga', 'mogg', 'opus', 'ra', 'rm', 'raw', 'sln', 'tta', 'voc', 'vox', 'wav', 'wma', 'wv', 'webm', '8svx'];
    
    public function createFileForm($name, $fileName) {
        global $language;
        return '<div class="form-group">
            <label for="groupImage">'.$name.'</label><br>
            <!-- MAX_FILE_SIZE must precede the file input field -->
            <input type="hidden" name="MAX_FILE_SIZE" value="524288" />
            <input type="file" name="'.$fileName.'" id="'.$fileName.'" class="form-control-file"><br>
            <span class="help-block">'.$language->getGlobalErrorMessage('file').'</span>
        </div>';
    }

    public function createVideoFileForm($name, $fileName) {
        global $language;
        return '<div class="form-group">
            <label for="groupImage">'.$name.'</label><br>
            <!-- MAX_FILE_SIZE must precede the file input field -->
            <input type="hidden" name="MAX_FILE_SIZE" value="524288" />
            <input type="file" accept="video/*" onchange="checkVideo(this)" name="'.$fileName.'" id="'.$fileName.'" class="form-control-file"><br>
            <span class="help-block" style="display:none" id="videoTestHelp">'.$language->getglobalMessage('videoTest').'</span>
            <span class="help-block">'.$language->getGlobalErrorMessage('file').'</span>
            <div hidden>
                <video controls autoplay id="videotester"></video>
            </div>
        </div>';

    }

    public function imageHandler($subDir, $fileName) {
        global $language;
        $targetFile = FILES['IMAGES'].$subDir.basename($_FILES[$fileName]["name"]);
        //check wheter or not it is a image file
        $imageFileType = strtolower(pathinfo($targetFile,PATHINFO_EXTENSION));
        if (!in_array($imageFileType, $this->imageFormats)) {
            $language->errors['file'] = "noImage.";
            return FALSE;
        }
        // Check if image file is a actual image or fake image
        if(!exif_imagetype($_FILES["groupImage"]["tmp_name"])) {
            $language->errors['file'] = 'fakeImage';
            return FALSE;
        }
        $this->fileHandler($targetFile, $fileName);
    }

    public function videoHandler($subDir, $fileName) {
        global $language;
        $target_file = FILES['VIDEO'].$subDir.basename($_FILES[$fileName]["name"]);
        //check whether or not it is a video file
        $videoFileType = strtolower(pathinfo($target_file,PATHINFO_EXTENSION));
        if (!in_array($videoFileType, $this->videoFormats)) {
            $language->errors['file'] = "noVideo.";
            return FALSE;
        }
        $this->fileHandler($targetFile, $fileName);
    }

    public function audioHandler($subDir, $fileName) {
        global $language;
        $target_file = FILES['VIDEO'].$subDir.basename($_FILES[$fileName]["name"]);
        //check wheter or not it is a image file
        $audioFileType = strtolower(pathinfo($target_file,PATHINFO_EXTENSION));
        if (!in_array($audioFileType, $this->audioFormats)) {
            $language->errors['file'] = "noAudio.";
            return FALSE;
        }
        $this->fileHandler($targetFile, $fileName);
    }

    public function binaryHandler($subDir, $fileName) {
        global $language;
        $target_file = FILES['VIDEO'].$subDir.basename($_FILES[$fileName]["name"]);

        $this->fileHandler($targetFile, $fileName);
    }

    public function textHandler($subDir, $fileName) {
        global $language;
        $target_file = FILES['VIDEO'].$subDir.basename($_FILES[$fileName]["name"]);

        $this->fileHandler($targetFile, $fileName);
    }

    private function fileHandler($file, $fileName) {
        global $language;
        //Check if file already exists
        if (file_exists($target_file)) {
            $language->errors['file'] = 'exists';
            return FALSE;
        }
        // Check file size
        if ($_FILES[$fileName]["size"] > 524288) {
            $language->errors['file'] = 'tooLarge';
            return FALSE;
        }
        //-------------------------------------------------------------------------TODO: implement virus scanner clamav
        // if everything is ok, try to upload file
        if (!(move_uploaded_file($_FILES[$fileName]["tmp_name"], $file))) {
            $language->errors['file'] = 'uploadError';
            return FALSE;
        } else {
            $language->succes['file'] = 'fileUploaded';
            return ['', ''];
        }
    }
}