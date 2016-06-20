package io.snowcrash.gossamer

class Installation {

    Long id
    Date dateCreated
    Date lastUpdated
    Long version

    String oauthid
    String oauthSecret
    Long groupId
    String capabilitiesUrl
    String tokenUrl
    String apiUrl
    Long roomId

    Date dateInstalled
    Date dateUninstalled
    Boolean active = true

    static hasMany = [tokens: Token]

    static mapping = {
        table 'installation'
        version true
        id column: 'id', generator: 'sequence', params: [sequence: 'seq_installation']
    }

    static constraints = {
        dateUninstalled nullable: true
    }

    String toString() {
        return "id: ${id} | capabilitiesUrl: ${capabilitiesUrl} | tokenUrl: ${tokenUrl} | oauthid: ${oauthid}"
    }
}
