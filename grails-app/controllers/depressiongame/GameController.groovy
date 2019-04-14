package depressiongame

import grails.util.Environment
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class GameController {

    GameService gameService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        def execFilename
        if (Environment.current == Environment.DEVELOPMENT) {
            execFilename = 'areyoudepressed.exe'
        } else {
            execFilename = 'areyoudepressed.o'
        }
//        def subdir = new File('core/sessions/' + session.getId())
        def subdir = grailsApplication.mainContext.getResource('core/sessions/' + session.getId()).file

        if (!subdir.exists()) {
            subdir.mkdir()
            log.error("creating session folder: " + subdir.getAbsolutePath())
        }
        def coreExe
        coreExe = grailsApplication.mainContext.getResource("core/" + execFilename).file


        def sessionExe = new File(subdir.getPath() + '/' + execFilename)
        if (!sessionExe.exists()) {
            sessionExe << coreExe.bytes
        }
        def sessionExeTemp = new File(subdir.getPath() + '/_' + execFilename)
        if (!sessionExeTemp.exists()) {
            sessionExeTemp << coreExe.bytes
        }

        if (Environment.current == Environment.PRODUCTION) {

            def subdirPath = subdir.getAbsolutePath()
            "/bin/chmod +x $subdirPath/areyoudepressed.o".execute()
            "/bin/chmod +x $subdirPath/_areyoudepressed.o".execute()

        }

        render(view: 'index', model: [sessionExe: sessionExe])
    }

    def runDepressionGameOnceAjax() {

        def execFilename
        if (Environment.current == Environment.DEVELOPMENT) {
            execFilename = 'areyoudepressed.exe'
        } else {
            execFilename = 'areyoudepressed.o'
        }

//      def subdir = new File('core/sessions/' + session.getId())
        def subdir = grailsApplication.mainContext.getResource('core/sessions/' + session.getId()).file
        def sessionExe = new File(subdir.getPath() + '/' + execFilename)
        def sessionExeTemp = new File(subdir.getPath() + '/_' + execFilename)

        def sout = new StringBuilder()
        def serr = new StringBuilder()

        def args = [sessionExe.getPath(), '-p', sessionExeTemp.getPath()]
        def proc = new ProcessBuilder(args)
        Process process
        def areyoudepressed
        def areyoudepressedQuote = ""
        def binaryText
        def destroyedIndex = "00"
        def result = "0"

        process = proc.start()
        process.consumeProcessOutput(sout, serr)
        process.waitForOrKill(20000)
        log.error('sout:\n' + sout)
        log.error('serr:\n' + serr)

        areyoudepressed = sout.toString().split('\n')

        try {
            if (!areyoudepressed[0].isEmpty()) areyoudepressedQuote = areyoudepressed[0]
            binaryText = areyoudepressed[1]
            destroyedIndex = areyoudepressed[2]
            result = areyoudepressed[3]

            new Game(sessionID: session.getId(), quote: areyoudepressedQuote, destroyedIndex: Long.valueOf(destroyedIndex), result: result).save()

        } catch (Exception e) {
            log.error('Exception :\n' + e.toString())
            areyoudepressedQuote = areyoudepressedQuote + e.toString()
        }

        if (!result.equals('1')) {
            session.invalidate()
        }
        sessionExe.bytes = sessionExeTemp.bytes
//        log.println "Timeout: ${session.getMaxInactiveInterval()} seconds"

        render(template: 'depression_game', model: [sessionID: session.getId(), areyoudepressedQuote: areyoudepressedQuote, binaryText: binaryText, destroyedIndex: destroyedIndex, result: result])
    }

    def resetSession() {
        session.invalidate()
        redirect(uri: '/')
    }

    def index_grails(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond gameService.list(params), model: [gameCount: gameService.count()]
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
            respond game.errors, view: 'create'
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
            respond game.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'game.label', default: 'Game'), game.id])
                redirect game
            }
            '*' { respond game, [status: OK] }
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
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'game.label', default: 'Game'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
