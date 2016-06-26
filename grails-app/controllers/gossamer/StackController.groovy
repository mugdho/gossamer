package gossamer

import grails.converters.JSON

class StackController {

    def stackService

    def register() {
        def data = request.JSON
        def stack = stackService.register(data.name, data.region)

        def resp = [
            id: stack.id.toString(),
            name: stack.name,
            region: stack.region
        ]

        render resp as JSON
    }

    def list() {
        def stacks = stackService.getAll()

        def resp = stacks.collect {
            [
                id: it.id.toString(),
                name: it.name,
                region: it.region
            ]
        }

        render resp as JSON
    }

    def show(String id) {
        def stack = stackService.get(UUID.fromString(id))

        if (stack) {
            def resp = [
                id: stack.id.toString(),
                name: stack.name,
                region: stack.region,
                resources: stack.details.resources
            ]

            render resp as JSON
        } else {
            response.status = 404
        }
    }

}
