package com.system.bibliotec.exception.problem;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.zalando.problem.spring.web.advice.ProblemHandling;
@ControllerAdvice
public class ProblemExceptionHandlerZalango implements ProblemHandling  {}
