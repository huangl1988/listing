package com.pfq.deal.trans_listing.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.pfq.deal.trans_listing.bean.output.BaseOutput;
import com.pfq.deal.trans_listing.bean.output.commody.RetCreateVo;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Configuration
@Slf4j
public class ListingCtrAop {

	@Pointcut("execution(* com.pfq.deal.trans_listing.controller..*.*(..) )")
	public void excute(){}
	@Around("excute()")
	public Object doAround(ProceedingJoinPoint pjp) {
		long time = System.currentTimeMillis();
		Object[] args = pjp.getArgs();
		Class<?> clazz = pjp.getTarget().getClass();
		MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
		Method targetMethod = methodSignature.getMethod();
		Object result = null;
		try {
			doBefore(clazz, targetMethod, args);
			result = pjp.proceed();
			log.info(targetMethod.getName() +" cost "+(System.currentTimeMillis()-time)+"! result:"+(result==null?"empty":new Gson().toJson(result)));
		} catch (Throwable e) {
			log.error("error",e);
			return new BaseOutput("fail", "system error");
		}
		return result;
	}

	private void doBefore(Class<?> clz, Method targetMethod, Object[] args) {

		log.info(clz.getSimpleName()+":"+targetMethod.getName()+(args==null?"empty":new Gson().toJson(args)));

	}
}
