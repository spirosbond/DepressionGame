package depressiongame

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class GameServiceSpec extends Specification {

    GameService gameService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Game(...).save(flush: true, failOnError: true)
        //new Game(...).save(flush: true, failOnError: true)
        //Game game = new Game(...).save(flush: true, failOnError: true)
        //new Game(...).save(flush: true, failOnError: true)
        //new Game(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //game.id
    }

    void "test get"() {
        setupData()

        expect:
        gameService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Game> gameList = gameService.list(max: 2, offset: 2)

        then:
        gameList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        gameService.count() == 5
    }

    void "test delete"() {
        Long gameId = setupData()

        expect:
        gameService.count() == 5

        when:
        gameService.delete(gameId)
        sessionFactory.currentSession.flush()

        then:
        gameService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Game game = new Game()
        gameService.save(game)

        then:
        game.id != null
    }
}
