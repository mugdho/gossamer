package gossamer

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
        "/build"(controller: "tasks", action: "build")
        "/status"(controller: "tasks", action: "status")
        "/descriptor"(controller: "register", action: "descriptor")
        "/installed"(controller: "register", action: "installed")
        "/uninstalled"(controller: "register", action: "uninstalled")

        "/stack" (controller: "stack") {
            action = [GET:"list", POST:"register"]
        }
        "/stack/${id}" (controller: "stack") {
            action = [GET: "show"]
        }
    }
}
