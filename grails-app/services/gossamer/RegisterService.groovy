package gossamer

import grails.plugins.rest.client.RestBuilder
import grails.transaction.Transactional
import io.snowcrash.gossamer.Installation

@Transactional
class RegisterService {


    def register(data) {
        def installation = new Installation()
        installation.oauthid = data.oauthId
        installation.oauthSecret = data.oauthSecret
        installation.capabilitiesUrl = data.capabilitiesUrl
        installation.groupId = data.groupId
        installation.roomId = data.roomId
        installation.dateInstalled = new Date()

        def restClient = new RestBuilder()
        def resp = restClient.get(data.capabilitiesUrl).json

        installation.tokenUrl = resp.capabilities.oauth2Provider.tokenUrl
        installation.apiUrl = resp.capabilities.hipchatApiProvider.url

        installation.save(failOnError: true)

        return true
    }
}
