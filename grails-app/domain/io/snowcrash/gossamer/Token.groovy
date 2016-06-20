package io.snowcrash.gossamer

class Token {

    Long id
    Date dateCreated
    Date lastUpdated
    Long version

    String accessToken
    Long expiresIn
    String groupId
    String groupName
    String tokenType

    static belongsTo = [installation: Installation]

    static mapping = {
        table 'token'
        version true
        id column: 'id', generator: 'sequence', params: [sequence: 'seq_installation']
    }

    static constraints = {
    }
}
