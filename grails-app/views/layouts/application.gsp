<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>
        <g:layoutTitle default="Depression Game"/>
    </title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">
    <meta property="og:site_name" content="depressionga.me"/>
    <meta property="og:title" content="The Depression Game" />
    <meta property="og:description" content="Depression is not a Game" />
    <meta property="og:type" content="org">
    <meta property="og:url" content="http://depressionga.me"/>
    <meta property="og:image" content="https://images.agoramedia.com/everydayhealth/gcms/5-Health-Risks-Linked-to-Depression-01-RM-722x406.jpg"/>

    <asset:link rel="apple-touch-icon" sizes="180x180" href="apple-touch-icon.png"/>
    <asset:link rel="icon" type="image/png" sizes="32x32" href="favicon-32x32.png"/>
    <asset:link rel="icon" type="image/png" sizes="16x16" href="favicon-16x16.png"/>
    <asset:link rel="manifest" href="site.webmanifest"/>
    <asset:link rel="mask-icon" href="safari-pinned-tab.svg" color="#5bbad5"/>
    <meta name="msapplication-TileColor" content="#ffc40d">
    <meta name="theme-color" content="#ffffff">

    <link href="https://fonts.googleapis.com/css?family=Khula:700" rel="stylesheet">

    <asset:stylesheet src="bootstrap.min.css"/>
    <asset:stylesheet src="application.css"/>
    <asset:stylesheet src="bootstrap-glyphicons.min.css"/>

    <asset:javascript src="jquery-3.3.1.min.js"/>
    <asset:javascript src="bootstrap.min.js"/>

    <g:render template="/layouts/ganalytics"/>

    <g:layoutHead/>
</head>

<body class="bg-dark">

<asset:javascript src="application.js"/>

<div id="fb-root"></div>
<script async defer crossorigin="anonymous" src="https://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v3.2"></script>

<g:layoutBody/>

<!-- Footer -->
<footer class="page-footer bg-secondary font-small fixed-bottom">
    <div class="container-fluid">
        <div class="row align-items-center">
            <div class="col-lg-2 col-md-12"></div>

            <div class="col-lg-8 col-md-12">
                <!-- Copyright -->
                <div class="footer-copyright text-light text-center py-3">© 2019 Copyright - Open Source licensed under MIT License:
                    <a class="text-danger" href="https://github.com/spirosbond/DepressionGame" target="_blank">Depression Game</a><br/>
                    <a rel="license" href="http://creativecommons.org/licenses/by/4.0/" target="_blank">
                        <img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by/4.0/80x15.png"/>
                    </a>This work is licensed under a
                    <a class="text-danger" rel="license" href="http://creativecommons.org/licenses/by/4.0/"
                       target="_blank">Creative Commons Attribution 4.0 International License</a>
                    <!-- Copyright -->

                </div>
            </div>

            <div class="col-lg-2 col-md-12 centered">
                <div class="fb-like" data-href="http://depressionga.me/" data-layout="button" data-action="like" data-size="small" data-show-faces="true" data-share="true">
                </div>

                %{--<div class="align-bottom">
                    <a class="twitter-share-btn" target="_blank" href="https://twitter.com/intent/tweet?text=Check%20this%20out:%20The%20Depression%20Game"><span
                            class="label">Tweet</span></a>
                </div>--}%

            </div>
        </div>
    </div>

</footer>
<!-- Footer -->

</body>
</html>
