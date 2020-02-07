package com.system.bibliotec.service.operations;



public interface IBiblotecMailSend{

  
    public boolean sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml);
    
}