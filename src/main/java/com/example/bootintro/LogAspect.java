package com.example.bootintro;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
   @Around("execution(* com.example.bootintro.repositories.*+.*(..))")
    public Object allmightyAroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println(">>>>>>>>>>>>"+pjp);
        return pjp.proceed();
    }
}
