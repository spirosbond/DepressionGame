// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better
// to create separate JavaScript files as needed.
//
//= require jquery-3.3.1.min
//= require bootstrap
//= require_tree .
//= require_self

function runDepressionGameOnceAjax(divToUpdate, sessionid) {
    $.ajax({
        url: 'game/runDepressionGameOnceAjax',
        data: { sessionId: sessionid },
        success: function (result) {
            divToUpdate.html(result);
        }
    });
}

function consoleText(words, id, colors, autoclear, typespeedms, blinkspeedms, waittimems) {

    if (colors === undefined) colors = ['#fff'];
    if (blinkspeedms <=100) blinkspeedms = 100;
    var visible = true;
    var con = document.getElementById('console');
    var conclass = con.className;
    var letterCount = 1;
    var x = 1;
    var waiting = false;
    var target = document.getElementById(id);
    target.setAttribute('style', 'color:' + colors[0]);

    window.setInterval(function () {

        if (letterCount === 0 && waiting === false) {
            waiting = true;
            target.innerHTML = words[0].substring(0, letterCount);

            window.setTimeout(function () {

                var usedColor = colors.shift();
                colors.push(usedColor);
                var usedWord = words.shift();
                words.push(usedWord);
                x = 1;
                target.setAttribute('style', 'color:' + colors[0]);
                letterCount += x;
                waiting = false;
            }, waittimems);

        } else if (autoclear && letterCount === words[0].length + 1 && waiting === false) {
            waiting = true;

            window.setTimeout(function () {

                x = -1;
                letterCount += x;
                waiting = false;
            }, waittimems);

        } else if (waiting === false) {
            target.innerHTML = words[0].substring(0, letterCount);
            letterCount += x;
        }
    }, typespeedms);

    window.setInterval(function () {

        if (visible === true) {
            con.className = conclass + ' hidden';
            visible = false;
        } else {
            con.className = conclass;
            visible = true;
        }
    }, blinkspeedms);

}

String.prototype.replaceAt = function (index, replacement) {
    return this.substr(0, index) + replacement + this.substr(index + replacement.length);
};

if (typeof jQuery !== 'undefined') {
    (function ($) {
        $(document).ajaxStart(function () {
            $('#spinner').fadeIn();
        }).ajaxStop(function () {
            $('#spinner').fadeOut();
        });
    })(jQuery);
}
