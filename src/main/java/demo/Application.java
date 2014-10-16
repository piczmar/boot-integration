package demo;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@ImportResource({
        "classpath:/application-context.xml",
        "classpath:/integration.xml"
})
public class Application {

    @Value("${ajpPort}")
    private int ajpPort;
    @Value("${server.port}")
    private int ajpRedirectPort;

    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder(Application.class)
                .showBanner(false)
                .run(args);
    }

    /**
     * creates a ssl connector and adds it to the factory
     *
     * @return
     */
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {

        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();

        Connector ajpConnector = new Connector(
                "org.apache.coyote.ajp.AjpProtocol");
        try {
            ajpConnector.setScheme("AJP/1.3");
            ajpConnector.setRedirectPort(ajpRedirectPort);
            ajpConnector.setPort(ajpPort);
           // AjpProtocol protocol = (AjpProtocol) ajpConnector.getProtocolHandler();
            ajpConnector.setEnableLookups(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        tomcat.addAdditionalTomcatConnectors(ajpConnector);

        return tomcat;
    }

}
