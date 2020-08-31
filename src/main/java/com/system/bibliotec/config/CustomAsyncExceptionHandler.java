package com.system.bibliotec.config;

import java.lang.reflect.Method;
import java.util.Arrays;

import com.system.bibliotec.service.operacoes.auditor.IAuditorTokenDeUsuarioDoContexto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.system.bibliotec.service.ServicoDoSistema;
import org.springframework.stereotype.Component;

@Component
public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler, IAuditorTokenDeUsuarioDoContexto {

    @Autowired
    private ApplicationContext applicationContext;

    
    private final Logger log = LoggerFactory.getLogger(CustomAsyncExceptionHandler.class);


    
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        // TODO Auto-generated method stub

        this.applicationContext = applicationContext;
    }

    
  @Override
  public void handleUncaughtException(final Throwable ex, final Method method, final Object... objects) {
    final String msg = "Async Biliotec Exception**************************************************************"
        + "\nmethod happen: " + method
        + "\nmethod params: " + Arrays.toString(objects)
        + "\nException class: {}" + ex.getClass().getName()
        + "\nex.getMessage(): {}" + ex.getMessage()
        + "\n***************************************************************************************************";
    
    ServicoDoSistema serviceSystemError = this.applicationContext.getBean("servicoDoSistema", ServicoDoSistema.class);
     log.error(msg, ex);
     serviceSystemError.saveContextErrorAssyncException(ex, method, getCurrentAuditorUser(),objects);
  }

  
  private String getCurrentAuditorUser() {
      return obterUsuarioDoContextoPeloToken();
  }





    
}