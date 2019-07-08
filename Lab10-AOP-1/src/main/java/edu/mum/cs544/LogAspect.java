package edu.mum.cs544;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LogManager.getLogger(LogAspect.class.getName());

    @After("execution(* edu.mum.cs544.EmailSender.sendEmail(..))")
    public void logAfter(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String address = (String) args[0];
        String message = (String) args[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd hh:mm:ss z yyyy");
        System.out.println(ZonedDateTime.now().format(formatter) + " method= " + joinPoint.getSignature().getName()
            + " address= " + address + " message= " + message + " outgoing mail server= " + joinPoint.getTarget());
    }

    @Around("execution(* edu.mum.cs544.CustomerDAO.*(..))")
    public Object invoke(ProceedingJoinPoint call) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object retVal = call.proceed();
        sw.stop();
        long totalTime = sw.getLastTaskTimeMillis();
        System.out.println("Time to execute save = " + totalTime);
        return retVal;
    }
}