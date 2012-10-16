package br.com.senac.ccs.thinkfast;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import static javax.servlet.ServletRegistration.Dynamic;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 * @author ecarrara
 */
public class ThinkFastGameWebInitializer implements WebApplicationInitializer {
    
    @Override
    public void onStartup ( ServletContext servletContext ) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        
        context.register( AppConfig.class );
        
        servletContext.addListener( new ContextLoaderListener( context ) );
        servletContext.setInitParameter( "defaultHtmlEscape", "true");
        
        Dynamic dispatcher = servletContext.addServlet( "dispatcherServlet", 
                new DispatcherServlet( context ) );
        dispatcher.setLoadOnStartup( 1 );
        dispatcher.setAsyncSupported( true );
        dispatcher.addMapping( "/" );
        
    }
    
}
