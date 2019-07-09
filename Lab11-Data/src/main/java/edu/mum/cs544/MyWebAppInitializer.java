package edu.mum.cs544;

import javax.servlet.*;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MyWebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        // Create the Spring 'root' application context
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(Config.class);

        // Manage the lifecycle of the root application context
        container.addListener(new ContextLoaderListener(rootContext));
        
		ServletRegistration.Dynamic appServlet = container.addServlet("mvc",
				new DispatcherServlet(new GenericWebApplicationContext()));
		appServlet.setLoadOnStartup(1);
		appServlet.addMapping("/");

        OpenEntityManagerInViewFilter entityManagerInViewFilter = new OpenEntityManagerInViewFilter();
        FilterRegistration.Dynamic filter = container.addFilter("openEntityManagerInViewFilter", entityManagerInViewFilter);
        filter.addMappingForUrlPatterns(null, false, "/*");

    }

}