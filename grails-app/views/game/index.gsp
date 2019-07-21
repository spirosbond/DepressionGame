<!DOCTYPE html>
<head>
    <meta name="layout" content="application"/>

</head>

<body>

<span id="binary-text-background" class="background-text text-justify"></span>

<div class="h-100 row align-items-center">

    <div id="icon-info" class="col-1 glyphicon-container text-muted">
        <span class="glyphicon glyphicon-info-sign" data-toggle="modal" data-target="#info-modal"></span>
    </div>


    <div class="col-10" id="depression-game">

    </div>

    <div class="col-1 glyphicon-container text-danger">
        <span id="icon-run-game" class="glyphicon glyphicon-circle-arrow-right" onclick="runDepressionGameOnceAjax($('#depression-game'))"></span>
        <span id="icon-refresh-game" class="glyphicon glyphicon-refresh display-none" onclick="location.reload();"></span>
    </div>
</div>

<script>

    runDepressionGameOnceAjax($('#depression-game'))
</script>

<!-- Modal -->
<div class="modal fade" id="info-modal" tabindex="-1" role="dialog" aria-labelledby="info-modal-title" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="info-modal-long-title">The Depression Game</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p class="text-justify font-weight-light">During this "Game" you are interacting with a depressed self-destroying program.
                Every time you click 'Next' it talks to you, but one random tiny piece of it get's corrupted, until it cannot function anymore and crashes&hellip;</p>
                <br/>
                <p class="text-justify font-weight-light">This is what depression does to someone, until the end; piece by piece.
                Depression is not a game, neither is this website.
                We hope to raise awareness and asking your support to help others.</p>
                <br/>
                <p class="text-justify font-weight-light">Our main credo is that depression can be treated.
                Visit <a href="https://ifightdepression.com" class="text-danger" target="_blank">ifightdepression.com</a> for more resources and to seek help.</p>
                <br/>
                <h2>Donation Links:</h2>
                <ul class="list-group">
                    <li class="list-group-item">
                        <a href="http://www.eaad.net/mainmenu/about/support-us/" class="text-danger" target="_blank">European Alliance Against Depression (EAAD)</a>
                    </li>
                    <li class="list-group-item">
                        <a href="https://adaa.org/donate" class="text-danger" target="_blank">Anxiety and Depression Association of America (ADAA)</a>
                    </li>
                </ul>
            </div>
            <div class="modal-footer">
                <div class="fb-like mr-auto" data-href="http://depressionga.me/" data-layout="button" data-action="like" data-size="small" data-show-faces="true" data-share="true">
                </div>
                <button type="button" class="btn btn-secondary"><a class="text-white" href="mailto:bigappledev@gmail.com" target="_blank">Want to be listed?</a></button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>