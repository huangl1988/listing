package com.pfq.deal.trans_listing.service.intfc.impl;

import com.pfq.deal.trans_listing.bean.output.BaseOutput;
import com.pfq.deal.trans_listing.exception.handler.MyPathCheckException;
import com.pfq.deal.trans_listing.service.IBaseService;
import com.pfq.deal.trans_listing.service.RegionService;
import com.pfq.deal.trans_listing.service.intfc.MyPathavalibe;
import com.pfq.deal.trans_listing.util.SpringContextUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.method.support.UriComponentsContributor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.method.annotation.PathVariableMethodArgumentResolver;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by steven on 2017/11/16.
 */
public class MyPathavalibeHandlerResolverImpl extends AbstractNamedValueMethodArgumentResolver implements UriComponentsContributor {

    protected Object resolveName(String name, MethodParameter parameter, NativeWebRequest request) throws Exception {
        Map<String, String> uriTemplateVars = (Map)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, 0);
        Object value =  uriTemplateVars != null?(String)uriTemplateVars.get(name):null;
        this.isValid(value.toString(),parameter.getParameterAnnotation(MyPathavalibe.class),name);
        return value;
    }

    private static final TypeDescriptor STRING_TYPE_DESCRIPTOR = TypeDescriptor.valueOf(String.class);


    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(MyPathavalibe.class);

    }

    protected NamedValueInfo createNamedValueInfo(MethodParameter parameter) {
        MyPathavalibe annotation = (MyPathavalibe)parameter.getParameterAnnotation(MyPathavalibe.class);
        return new MyPathavalibeHandlerResolverImpl.PathVariableNamedValueInfo(annotation);
    }


    protected void handleMissingValue(String name, MethodParameter parameter) throws ServletRequestBindingException {
        throw new MissingPathVariableException(name, parameter);
    }

    protected void handleResolvedValue(Object arg, String name, MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest request) {
        String key = View.PATH_VARIABLES;
        int scope = 0;
        Map<String, Object> pathVars = (Map)request.getAttribute(key, scope);
        if(pathVars == null) {
            pathVars = new HashMap();
            request.setAttribute(key, pathVars, scope);
        }

        ((Map)pathVars).put(name, arg);
    }

    public void contributeMethodArgument(MethodParameter parameter, Object value1, UriComponentsBuilder builder, Map<String, Object> uriVariables, ConversionService conversionService) {
        if(!Map.class.isAssignableFrom(parameter.getNestedParameterType())) {
            MyPathavalibe ann = (MyPathavalibe)parameter.getParameterAnnotation(MyPathavalibe.class);
            String name = ann != null && !org.springframework.util.StringUtils.isEmpty(ann.value())?ann.value():parameter.getParameterName();
            Object value = this.formatUriValue(conversionService, new TypeDescriptor(parameter), value1);
            uriVariables.put(name, value);
        }
    }

    protected String formatUriValue(ConversionService cs, TypeDescriptor sourceType, Object value) {
        return value == null?null:(value instanceof String?(String)value:(cs != null?(String)cs.convert(value, sourceType, STRING_TYPE_DESCRIPTOR):value.toString()));
    }

    private static class PathVariableNamedValueInfo extends NamedValueInfo {
        public PathVariableNamedValueInfo(MyPathavalibe annotation) {
            super(annotation.value(), true, "\n\t\t\n\t\t\n\ue000\ue001\ue002\n\t\t\t\t\n");
        }
    }

    private void isValid(String id,MyPathavalibe myPathavalibe,String name) {

        IBaseService regionService= SpringContextUtils.getBean(myPathavalibe.clz());

        if(!regionService.isExist(id)){
            throw new MyPathCheckException(name+":"+id+myPathavalibe.message());
        }
    }
}
