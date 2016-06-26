package gossamer

import grails.converters.JSON
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import io.snowcrash.gossamer.Stack
import grails.transaction.Transactional

@Transactional
class StackService {

    def infrastructureService

    private final String GL_STACK_PREFIX = "gl-stack"

    Stack register(String name, String region) {
        def stack = new Stack()
        stack.name = name
        stack.region = region

        def details = infrastructureService.getStack(name, region)
        def resources = []

        details.each {
            resources += it.collect {
                [
                    instanceId     : it.instanceId,
                    publicDnsName  : it.publicDnsName,
                    publicIpAddress: it.publicIpAddress,
                    instanceType   : "ec2",
                    applicationType: "web",
                    state          : it.state.name
                ]
            }
        }

        stack.details = [resources: resources]

        stack.save(failOnError: true)

        return stack
    }

    def getAll() {
        return Stack.getAll()
    }

    def get(UUID id) {
        return Stack.get(id)
    }

}
