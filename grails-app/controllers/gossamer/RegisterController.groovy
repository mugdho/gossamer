package gossamer

import grails.converters.JSON
import org.grails.config.PropertySourcesConfig
import org.grails.config.yaml.YamlPropertySourceLoader
import org.springframework.core.io.ClassPathResource

class RegisterController {

    def registerService

    def descriptor() {

        def mapPropertySource = new YamlPropertySourceLoader().load(
            "config", new ClassPathResource('capabilities.yml'), null)

        def conf = new PropertySourcesConfig(mapPropertySource.getSource())

        render conf.descriptor as JSON
    }

    def installed() {
        registerService.register(request.JSON)
        render "success"
    }

    def uninstalled() {

    }
}
