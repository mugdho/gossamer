package gossamer

import grails.plugin.springsecurity.annotation.Secured
import grails.converters.JSON


class BuildsController {

    @Secured(['ROLE_ADMIN'])
    def index() {
        def resp = [
            "accessed": true
        ]

        render resp as JSON
    }
}
