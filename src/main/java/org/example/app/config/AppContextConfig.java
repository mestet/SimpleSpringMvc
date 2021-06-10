package org.example.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <?xml version="1.0" encoding="UTF-8"?>
 * <beans xmlns="http://www.springframework.org/schema/beans"
 *        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 *        xmlns:context="http://www.springframework.org/schema/context"
 *        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
 *
 *     <context:component-scan base-package="org.example.app"/>
 *
 * </beans>
 */

@Configuration
@ComponentScan("org.example.app")
public class AppContextConfig {

}
