package edu.mum.cs544.bank.service.aop;

import edu.mum.cs544.bank.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class JMSLogAdvice {
    @Autowired
    private ILogger logger;

    @After("execution(* edu.mum.cs544.bank.jms.JMSSender.sendJMSMessage(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("JMS Message is sent");
    }
}
