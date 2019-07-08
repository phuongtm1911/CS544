package edu.mum.cs544.bank.service.aop;

import edu.mum.cs544.bank.logging.ILogger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DAOLogAdvice {
    @Autowired
    private ILogger logger;

    @Before("execution(* edu.mum.cs544.bank.dao.AccountDAO.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.log("Call DAO method");
    }
}
