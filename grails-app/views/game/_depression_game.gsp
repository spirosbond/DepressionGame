<%@ page import="depressiongame.Game" %>
<div class="console-container">
    <span id="text"></span>

    <div class="console-underscore text-danger" id="console"><span class="glyphicon glyphicon-heart" aria-hidden="true"></span></div>
</div>
<script type="text/javascript">

    var destroyedIndexList = [];
    <g:each in="${Game.findAllBySessionID(sessionID,[sort: "destroyedIndex", order: "desc"])}" var="game">
    destroyedIndexList.push(${game.destroyedIndex});
    </g:each>
    console.log("destroying List: " + destroyedIndexList);

    var result = '${result}';
    if (result !== '1') {
        $("#icon-run-game").toggleClass('display-none');
        $("#icon-refresh-game").toggleClass('display-none');
        $("#icon-info").removeClass('text-muted');
        $("#icon-info").addClass('text-light');
        consoleText(['${areyoudepressedQuote}'], 'text', ['LightCoral'], false, 100, 999999999, 1000);
    } else {

        consoleText(['${areyoudepressedQuote}'], 'text', ['LightCoral'], false, 100, 800 - 100 * destroyedIndexList.length, 1000);

        var binaryTextSpan = document.getElementById('binary-text-background'); //This span must exist in parent view
        binaryTextSpan.innerHTML = '${binaryText}';

        destroyedIndexList.forEach(function (destroyedIndex) {

            var tempDestroyedIndex = destroyedIndex * 2 + Math.floor(destroyedIndex * 0.5);

            console.log("destroying byte: " + destroyedIndex);
            console.log("destroying char: " + tempDestroyedIndex);

            binaryTextSpan.innerHTML = binaryTextSpan.innerHTML.replaceAt(tempDestroyedIndex, "!!");
            binaryTextSpan.innerHTML = binaryTextSpan.innerHTML.substring(0, tempDestroyedIndex) + '<span class="bg-danger destroyed-byte text-warning" id="' + destroyedIndex + '">' + binaryTextSpan.innerHTML.substring(tempDestroyedIndex, tempDestroyedIndex + 2) + '</span>' + binaryTextSpan.innerHTML.substring(tempDestroyedIndex + 2)
        });
        try {
            $("#binary-text-background").animate({
                scrollTop: $("#${destroyedIndex}").offset().top + $("#binary-text-background").scrollTop() - screen.height / 3
            }, 2000);
        } catch (err) {
            console.log(err.message);
        }
    }



</script>