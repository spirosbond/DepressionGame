package depressiongame

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class GameController {

    GameService gameService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        def subdir = new File('core/sessions/' + session.getId())
        if (!subdir.exists()) {
            subdir.mkdir()
        }
        def coreExe = new File('core/areyoudepressed.exe')
        def sessionExe = new File(subdir.getPath() + '/areyoudepressed.exe')
        if (!sessionExe.exists()) {
            sessionExe << coreExe.bytes
        }

        render(view: 'index', model: [sessionExe: sessionExe])
    }

    def runDepressionGameOnceAjax() {
        def subdir = new File('core/sessions/' + session.getId())
        def sessionExe = new File(subdir.getPath() + '/areyoudepressed.exe')

        def sout = new StringBuilder()
        def serr = new StringBuilder()

        def args = [sessionExe.getPath(), '-p', sessionExe.getPath()]
        def proc = new ProcessBuilder(args)
        Process process
        def areyoudepressed
        def areyoudepressedQuote
        def binaryText
        def destroyedIndex
        def result

        process = proc.start()
        process.consumeProcessOutput(sout, serr)
        process.waitForOrKill(20000)
        println 'sout:\n' + sout
        println 'serr:\n' + serr

        areyoudepressed = sout.toString().split('\n')

        areyoudepressedQuote = areyoudepressed[0]
        binaryText = areyoudepressed[1]
        destroyedIndex = areyoudepressed[2]
        result = areyoudepressed[3]

        new Game(sessionID: session.getId(), quote: areyoudepressedQuote, destroyedIndex: Long.valueOf(destroyedIndex), result: result).save()

        if (!result.equals('1')) {
            session.invalidate()
        }
//        log.println "Timeout: ${session.getMaxInactiveInterval()} seconds"

        render(template: 'depression_game', model: [sessionID: session.getId(), areyoudepressedQuote: areyoudepressedQuote, binaryText: binaryText, destroyedIndex: destroyedIndex, result: result])
    }

    def resetSession(){
        session.invalidate()
        redirect(uri:'/')
    }

    def index_grails(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond gameService.list(params), model:[gameCount: gameService.count()]
    }

    def show(Long id) {
        respond gameService.get(id)
    }

    def create() {
        respond new Game(params)
    }

    def save(Game game) {
        if (game == null) {
            notFound()
            return
        }

        try {
            gameService.save(game)
        } catch (ValidationException e) {
            respond game.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'game.label', default: 'Game'), game.id])
                redirect game
            }
            '*' { respond game, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond gameService.get(id)
    }

    def update(Game game) {
        if (game == null) {
            notFound()
            return
        }

        try {
            gameService.save(game)
        } catch (ValidationException e) {
            respond game.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'game.label', default: 'Game'), game.id])
                redirect game
            }
            '*'{ respond game, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        gameService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'game.label', default: 'Game'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'game.label', default: 'Game'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
