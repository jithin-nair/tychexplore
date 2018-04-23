package net.tychecash.explorer.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SchedulerWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { SchedulerWebConfiguration.class, SchedulerWebSocketConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
