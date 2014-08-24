class UrlMappings {

	static mappings = {

        "/users(.$format)?"(resources:'user') {
            format = "json"
        }

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
