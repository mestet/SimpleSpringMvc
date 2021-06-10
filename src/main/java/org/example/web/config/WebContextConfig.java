package org.example.web.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * <?xml version="1.0" encoding="UTF-8"?>
 * <beans xmlns="http://www.springframework.org/schema/beans"
 *        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 *        xmlns:mvc="http://www.springframework.org/schema/mvc"
 *        xmlns:context="http://www.springframework.org/schema/context"
 *
 *        xsi:schemaLocation="http://www.springframework.org/schema/beans
 *            http://www.springframework.org/schema/beans/spring-beans.xsd
 *            http://www.springframework.org/schema/mvc
 *            http://www.springframework.org/schema/mvc/spring-mvc.xsd
 *            http://www.springframework.org/schema/context
 *            https://www.springframework.org/schema/context/spring-context.xsd">
 *
 *     <context:component-scan base-package="org.example.web"/>
 *     <mvc:resources mapping="/**" location="classpath:images"/>
 *     <mvc:annotation-driven>
 *         <mvc:message-converters>
 *             <bean class="org.springframework.http.converter.ByteArrayHttpMessageConverter">
 *                 <property name="supportedMediaTypes">
 *                     <list>
 *                         <value>image/jpeg</value>
 *                         <value>image/png</value>
 *                     </list>
 *                 </property>
 *             </bean>
 *         </mvc:message-converters>
 *     </mvc:annotation-driven>
 *
 *     <bean class="org.thymeleaf.spring5.view.ThymeleafViewResolver">
 *         <property name="templateEngine" ref="templateEngine"/>
 *         <property name="order" value="1"/>
 *     </bean>
 *
 *     <bean id="templateEngine"
 *           class="org.thymeleaf.spring5.SpringTemplateEngine">
 *         <property name="templateResolver" ref="templateResolver"/>
 *         <property name="enableSpringELCompiler" value="true"/>
 *     </bean>
 *
 *     <bean id="templateResolver"
 *           class="org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver">
 *         <property name="prefix" value="/WEB-INF/views/"/>
 *         <property name="suffix" value=".html"/>
 *         <!-- Cache for pages is enabled by default. Set false if it needed to update pages on fly -->
 *         <property name="cacheable" value="true"/>
 *     </bean>
 *
 * </beans>
 */

@Configuration
@EnableWebMvc
@ComponentScan("org.example.web")
public class WebContextConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:images");
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(true);

        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);

        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setOrder(1);

        return viewResolver;
    }

}
