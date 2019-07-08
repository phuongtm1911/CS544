package edu.mum.cs544.bank.service.aop;

import edu.mum.cs544.bank.logging.ILogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class StopWatchAdvice {
    @Autowired
    private ILogger logger;

    @Around("execution(* edu.mum.cs544.bank.service.AccountService.*(..))")
    public Object invoke(ProceedingJoinPoint call) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object retVal = call.proceed();
        sw.stop();
        long totalTime = sw.getLastTaskTimeMillis();
        logger.log("Time to execute method " + call.getSignature().getName() + " = " + totalTime);
        return retVal;
    }
}
