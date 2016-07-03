package gossamer

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts

/**
 * Created by neo on 7/2/16.
 */
class BaseController {

    def oauthService

    def withJwt = {closure ->
        def headerAuth = request.getHeader('authorization')
        def requestData = request.JSON
        def jwt = headerAuth[4..-1]
        def validToken = true
        Claims claims = null

        def secret = oauthService.getSecret(requestData.oauth_client_id)
        println "secret:${secret}"

        try {
            if (secret) {
                claims = Jwts.parser()
                    .setSigningKey(secret.bytes)
                    .parseClaimsJws(jwt)
                    .body
            } else {
                validToken = false
            }

        } catch(exception) {
            validToken = false
        }

        if (validToken) {
            println "ValidToken"
            closure(requestData, claims)
        } else {
            return response.status = 401
        }
    }

    def withMessage = {requestData, closure ->
        if (requestData) {
            def message = requestData?.item?.message?.message?.split(" ")
            closure(message)
        }
    }
}
