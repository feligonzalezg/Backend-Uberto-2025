package uberto.backendgrupo72025.Bootstrap

import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import uberto.backendgrupo72025.BackendGrupo72025Application

class ServeLetInitializer : SpringBootServletInitializer(){

    override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
        return application.sources(BackendGrupo72025Application::class.java)
    }
}