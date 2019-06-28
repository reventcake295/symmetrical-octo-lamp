<?php
class Room {

    public function index() {
        global $database;
        $database->select('');
    }

    public function edit($id) {

    }

    public function new() {

    }

    public function show($id) {
        global $database;
        $room = $database->select('room', ['*'])->where(['roomID'], [$id])->excecuteStatement();
        $roomCard = '<div class="card item-card" style="width:200px;">
        </div>';

    }

    private function imagecarousel($ID, $imageArray = [['noImage', 'noImage']], $indicators = TRUE, $controlButtons = TRUE) {
        $carouselID = 'carousel'.$ID;
        $carousel = '<div id="$carouselID" class="carousel slide">';
        //indecators setup
        if ($indicators) {
            $carousel .= '<ol class="carousel-indicators">';
            for ($i = 0; $i < count($imageArray); $i++) {
                if ($i === 0) {
                    $carousel .= '<li data-target="'.$ID.'" data-slide-to="'.$i.'" class="active"></li>';
                } else {
                    $carousel .= '<li data-target="'.$ID.'" data-slide-to="'.$i.'"></li>';
                }
            }
            $carousel .= '</ol>';
        }
        //images
        $carousel .= '<div class="carousel-inner">';
        for ($i = 0; $i < count($imageArray); $i++) {
            if ($i === 0) {
                $carousel .= '<div class="carousel-item active">
                       <img class="d-block w-100" src="'.$imageArray[$i][0].'" alt="'.$imageArray[$i][1].'">
                     </div>';
            } else {
                $carousel .= '<div class="carousel">
                <img class="d-block w-100" src="'.$imageArray[$i][0].'" alt="'.$imageArray[$i][1].'">
              </div>';
            }
        }
        $carousel .= '</div>';
        //buttons
        if ($controlButtons) {
            $carousel .= '
              <a class="carousel-control-prev" href="'.$ID.' role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
              </a>
              <a class="carousel-control-next" href="'.$ID.'" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
              </a>';
        }
        $carousel .= '</div>';
    }

    public function delete($id) {

    }

    public function roomMetaData($id) {

    }
}