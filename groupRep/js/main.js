function acountCreationOption(boolean) {
    if (boolean) {
        $('#acountCreation').show();
    } else {
        $('#acountCreation').hide();
    }
    return;
}

//this funtion is already embbed in the JS class in the php core
//  /**
//  * sends a request to the specified url from a form. this will change the window location.
//  * @param {string} path the path to send the post request to
//  * @param {object} params the paramiters to add to the url
//  * @param {string} [method=post] the method to use on the form
//  */

// function post(path, params, method) {
//     method = method || "post";
//     let form = document.createElement("form");
//     form.setAttribute("method", method);
//     form.setAttribute("action", path);
//     for(let key in params) {
//         if(params.hasOwnProperty(key)) {
//             let hiddenField=document.createElement("input");
//             hiddenField.setAttribute("type","hidden");
//             hiddenField.setAttribute("name",key);
//             hiddenField.setAttribute("value", params[key]);
//             form.appendChild(hiddenField);
//         }
//     }
//     document.body.appendChild(form);
//     form.submit();
//     return;
// }

function checkVideo(event) {
    let file = event.files[0]
    let type = file.type
    let videoNode = document.querySelector('#videotester');
    let canPlayResult = videoNode.canPlayType(type);
    let canPlay = canPlayResult === "" ? false : true;
    $("#submit").attr("disabled", !canPlay);
    if (canPlay) {
        $('#videoTestHelp').css("display","none");
    } else {
        $('#videoTestHelp').css("display","block"); 
    }
}

$(function() {
    $('.search').on('change', () => {
        let kids = $('#kids').val();
        let adults = $('#adults').val();
        // let checkIn = $('#checkIn').val();
        // let checkOut = $('#checkOut').val();
        $('.option').each(function() {
            let adultData = $(this).data('adults');
            let kidsData = $(this).data('kids');
            if (adultData >= adults && kids <= kidsData) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
    });
});