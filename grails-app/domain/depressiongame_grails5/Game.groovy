package depressiongame_grails5

class Game {

    Date dateCreated
    Date lastUpdated

    String sessionID
    String quote
    Long destroyedIndex
    String result

    static mapping = {
        quote type:"text"
    }

    static constraints = {
    }
}
