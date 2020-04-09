package com.yshu.aop;

import com.yshu.annotation.BeforeTransactional;
import com.yshu.configuration.DynamicDataSourceContextHolder;
import com.yshu.enums.DataSourceKey;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by chenlei on 2020/4/8.
 */
@Order(1)
@Aspect
@Component
public class BeforeTransactionalAspect {

    private static final Logger logger = LoggerFactory.getLogger(BeforeTransactionalAspect.class);

    @Pointcut("@annotation(com.yshu.annotation.BeforeTransactional)")
    public void BeforeTransactionalCut() {
    }

    @Before("BeforeTransactionalCut()")
    public void aspectBefore(JoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        BeforeTransactional beforeTransactional = method.getAnnotation(BeforeTransactional.class);
        if(beforeTransactional.value().equals(DataSourceKey.master.toString())){
            DynamicDataSourceContextHolder.useSlaveDataSource(DataSourceKey.master);
            logger.info("BeforeTransactionalAspect switchBaseDataSource to [{}] in Method [{}]",
                    DynamicDataSourceContextHolder.getDataSourceKey(), joinPoint.getSignature());
        }
        if(beforeTransactional.value().equals(DataSourceKey.messagebase.toString())){
            DynamicDataSourceContextHolder.useSlaveDataSource(DataSourceKey.messagebase);
            logger.info("BeforeTransactionalAspect switchBaseDataSource to [{}] in Method [{}]",
                    DynamicDataSourceContextHolder.getDataSourceKey(), joinPoint.getSignature());
        }

    }

    @After("BeforeTransactionalCut()")
    public void restoreDataSource(JoinPoint point)throws Exception {
        DynamicDataSourceContextHolder.clearDataSourceKey();
        logger.debug("BeforeTransactionalAspect Restore DataSource to [{}] in Method [{}]",
                DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
    }
}
