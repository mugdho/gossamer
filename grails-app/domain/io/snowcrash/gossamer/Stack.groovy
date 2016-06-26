package io.snowcrash.gossamer

import grails.converters.JSON
import net.kaleidos.hibernate.usertype.JsonMapType

class Stack {

    UUID id
    Date dateCreated
    Date lastUpdated
    Long version

    String name
    Map details
    String region

    static mapping = {
        id generator : 'uuid2', type: 'pg-uuid'
        details type: JsonMapType
    }

    static constraints = {
        details nullable: true
    }
}
