package com.example.votingsystem.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditLogAspect {
    
    private static final Logger logger = LoggerFactory.getLogger(AuditLogAspect.class);

    // Log before any service method execution
    @Before("execution(* com.example.votingsystem.service.*.*(..))")
    public void logBeforeServiceMethods(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        logger.info("Entering method: {} with arguments: {}", methodName, java.util.Arrays.toString(args));
    }

    // Log after any service method execution and capture the returned result
    @AfterReturning(pointcut = "execution(* com.example.votingsystem.service.*.*(..))", returning = "result")
    public void logAfterServiceMethods(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().toShortString();
        logger.info("Exiting method: {} with result: {}", methodName, result);
    }
}
