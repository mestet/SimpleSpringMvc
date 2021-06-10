package org.example.app.services;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class IdProvider implements InitializingBean, DisposableBean, BeanPostProcessor {

    private final Logger logger = Logger.getLogger(IdProvider.class);

    public String provideStringId(Object obj) {
        return this.hashCode() + "_" + obj.hashCode();
    }

    @PostConstruct
    public void postConstructIdProvider() {
        logger.info("Invoke " + this.getClass().getSimpleName() + ".postConstructIdProvider()");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("Invoke " + this.getClass().getSimpleName() + ".afterPropertiesSet()");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        logger.info("Invoke " + this.getClass().getSimpleName() + ".postProcessBeforeInitialization()");
        logger.info("beanName: " + beanName);
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.info("Invoke " + this.getClass().getSimpleName() + ".postProcessAfterInitialization()");
        logger.info("beanName: " + beanName);
        return null;
    }

    @PreDestroy
    public void preDestroyIdProvider() {
        logger.info("Invoke " + this.getClass().getSimpleName() + ".preDestroyIdProvider()");
    }

    @Override
    public void destroy() throws Exception {
        logger.info("Invoke " + this.getClass().getSimpleName() + ".destroy()");
    }
}
