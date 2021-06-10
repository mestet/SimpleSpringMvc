package org.example;


import org.apache.log4j.Logger;
import org.example.app.config.AppContextConfig;
import org.example.web.config.WebContextConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * <!DOCTYPE web-app PUBLIC
 * "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 * "http://java.sun.com/dtd/web-app_2_3.dtd" >
 * <p>
 * <web-app>
 * <display-name>Archetype Created Web Application</display-name>
 * <p>
 * <context-param>
 * <param-name>contextConfigLocation</param-name>
 * <param-value>classpath:app-config.xml</param-value>
 * </context-param>
 *
 * <listener>
 * <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
 * </listener>
 *
 * <servlet>
 * <servlet-name>my-dispatcher-servlet</servlet-name>
 * <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
 * <init-param>
 * <param-name>contextConfigLocation</param-name>
 * <param-value>classpath:web-config.xml</param-value>
 * </init-param>
 * <load-on-startup>1</load-on-startup>
 * </servlet>
 * <p>
 * <servlet-mapping>
 * <servlet-name>my-dispatcher-servlet</servlet-name>
 * <url-pattern>/</url-pattern>
 * </servlet-mapping>
 * </web-app>
 */
public class WebAppInitializer implements WebApplicationInitializer {

    private final Logger logger = Logger.getLogger(WebAppInitializer.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        logger.info("Loading app config");
//        XmlWebApplicationContext appContext = new XmlWebApplicationContext();
//        appContext.setConfigLocation("classpath:app-config.xml");
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(AppContextConfig.class);
        servletContext.addListener(new ContextLoaderListener(appContext));

        logger.info("Loading web config");
//        XmlWebApplicationContext webContext = new XmlWebApplicationContext();
//        webContext.setConfigLocation("classpath:web-config.xml");
        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.register(WebContextConfig.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(webContext);

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        logger.info("Dispatcher ready");
    }
}
