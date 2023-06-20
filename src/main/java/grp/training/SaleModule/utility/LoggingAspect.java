package grp.training.SaleModule.utility;

import grp.training.SaleModule.exception.SaleModuleException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    public static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);


    @AfterThrowing(pointcut = "execution(* grp.training.SaleModule.service.*Impl.*(..))", throwing = "exception")
    public void logServiceException(SaleModuleException exception){
        LOGGER.error(exception.getMessage(), exception);
    }


}
