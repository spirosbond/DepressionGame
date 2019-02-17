package depressiongame

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: "game", action: "index")
        "/index_admin"(view: '/index_admin')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
