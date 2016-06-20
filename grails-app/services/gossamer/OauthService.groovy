package gossamer

import grails.plugins.rest.client.RestBuilder
import grails.transaction.Transactional
import io.snowcrash.gossamer.Installation
import io.snowcrash.gossamer.Token
import org.springframework.util.LinkedMultiValueMap

@Transactional
class OauthService {

    private Token fetchToken(Installation installation) {
        def form = new LinkedMultiValueMap<String, String>()
        form.add("grant_type", "client_credentials")
        form.add("scope", "send_notification")
        def rest = new RestBuilder()

        def resp = rest.post(installation.tokenUrl) {
            header 'Authorization', 'Basic ' + new String(Base64.encoder.encode("${installation.oauthid}:${installation.oauthSecret}".bytes))
            contentType "application/x-www-form-urlencoded"
            body form
        }.json

        def token = new Token()
        token.accessToken = resp.access_token
        token.expiresIn = resp.expires_in
        token.groupId = resp.group_id
        token.groupName = resp.group_name
        token.tokenType = resp.token_type
        token.installation = installation

        token.save(failOnError: true)

        return token
    }

    def getAccessToken(Installation installation) {
        def token = Token.findByInstallationAndExpiresInLessThan(installation, new Date().time)

        if (!token) {
            token = fetchToken(installation)
        }

        return token.accessToken
    }
}
