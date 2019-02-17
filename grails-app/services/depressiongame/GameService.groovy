package depressiongame

import grails.gorm.services.Service

@Service(Game)
interface GameService {

    Game get(Serializable id)

    List<Game> list(Map args)

    Long count()

    void delete(Serializable id)

    Game save(Game game)

}