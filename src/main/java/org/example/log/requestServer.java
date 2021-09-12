package org.example.log;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author lizhen created on 2021-06-08 15:32
 */

@Aspect
@Component
public class requestServer {
    private final static Logger log = LoggerFactory.getLogger(requestServer.class);



}
