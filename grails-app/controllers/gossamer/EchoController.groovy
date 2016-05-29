package gossamer

import grails.converters.JSON

class EchoController {

    def index() {
        def resp = [
            color: "green",
            message: "Echo of a command",
            notify: "false",
            message_format: "text"
        ]

        render resp as JSON

    }
}
