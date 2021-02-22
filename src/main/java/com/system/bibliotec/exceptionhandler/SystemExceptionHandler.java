package com.system.bibliotec.exceptionhandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintViolationException;

import com.system.bibliotec.exception.*;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SystemExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    private static final String mensagem_error_interno_usuario = "ERROR INTERNO NA APLICAÇÃO";

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> NotFoundException(RuntimeException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("recurso.nao-encontrado", null,
                LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({UnsatisfiedServletRequestParameterException.class})
    public ResponseEntity<Object> unsatisfiedServletRequestParameterException(RuntimeException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("recurso.solicitado.atendido", null,
                LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


    @ExceptionHandler({TrabalhoException.class})
    public ResponseEntity<Object> trabalhoException(TrabalhoException ex, WebRequest request) {
        String mensagemUsuario = ex.getMessage();
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    //@ExceptionHandler({ HttpMessageNotReadableException.class })
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }


    //@ExceptionHandler({ MethodArgumentNotValidException.class })
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Erro> erros = criarListaDeErros(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({CpfInvalidoException.class})
    public ResponseEntity<Object> cpfInvalidoOuInexistenteException(RuntimeException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("recurso.cpf.invalido", null,
                LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ClienteInexistenteException.class})
    public ResponseEntity<Object> clienteInexistenteException(RuntimeException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("recurso.cliente.inexistente-ou-inativo", null,
                LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({DocumentoInvalidoException.class})
    public ResponseEntity<Object> documentoInvalidoException(DocumentoInvalidoException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("recurso.cliente.documento.operacao-nao-permitida", null,
                LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({LivroInvalidoOuInexistenteException.class})
    public ResponseEntity<Object> livroInvalidoOuInexistenteException(LivroInvalidoOuInexistenteException ex,
                                                                      WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("recurso.livro.nao-encontrado", null,
                LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({LivroLocadoException.class})
    public ResponseEntity<Object> livroAlugadoException(LivroLocadoException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("recurso.livro.locado", null,
                LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({LivroReservadoException.class})
    public ResponseEntity<Object> livroReservadoException(LivroReservadoException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("recurso.livro.reservado", null,
                LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ReservaUpdateException.class})
    public ResponseEntity<Object> reservaUpdateException(ReservaUpdateException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("regra.reserva.atualizar", null,
                LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ReservaInexistenteException.class})
    public ResponseEntity<Object> reservaInexistenteException(ReservaInexistenteException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("regra.reserva.inexistente", null,
                LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({UsuarioNaoEncontrado.class})
    public ResponseEntity<Object> usernameNotFoundException(RuntimeException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("usuario.nao.encontrado", null,
                LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({AccountResourceException.class})
    public ResponseEntity<Object> userNotFoundException(RuntimeException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("usuario.nao.encontrado.error.interno", null,
                LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> resourceNotFoundException(RuntimeException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("recurso.nao-encontrado", null,
                LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> dataIntegrityViolationException(RuntimeException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("recurso.operacao-nao-permitida", null,
                LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    public ResponseEntity<Object> sqlIntegrityConstraintViolationException(RuntimeException ex, WebRequest request) {
        String mensagemUsuario = mensagem_error_interno_usuario;
        String mensagemDesenvolvedor = messageSource.getMessage("error.interno.formatacao-sql", null,
                LocaleContextHolder.getLocale());
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({CodigoAtivaCaoInvalidoOuInexistenteException.class})
    public ResponseEntity<Object> codigoAtivaCaoInvalidoOuInexistenteException(RuntimeException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("conta.codigo.ativacao.invalido", null,
                LocaleContextHolder.getLocale());
        //String mensagemDesenvolvedor = messageSource.getMessage("error.interno.formatacao-sql", null,
        //		LocaleContextHolder.getLocale());

        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> constraintViolationException(RuntimeException ex, WebRequest request) {
        String mensagemUsuario = mensagem_error_interno_usuario;
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({EmailAlreadyUsedException.class})
    public ResponseEntity<Object> emailAlreadyUsedException(RuntimeException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("conta.email-ja-em-uso", null,
                LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({UsernameAlreadyUsedException.class})
    public ResponseEntity<Object> usernameAlreadyUsedException(RuntimeException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("conta.username-ja-em-uso", null,
                LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({EmailInvalidoOuInexistenteException.class})
    public ResponseEntity<Object> emailInvalidoOuInexistenteException(RuntimeException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("conta.email.invalido.ou.inexistente", null,
                LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({SchedulerException.class})
    public ResponseEntity<Object> schedulerException(RuntimeException ex, WebRequest request) {
        String mensagemUsuario = ExceptionUtils.getRootCauseMessage(ex);
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
	
/*	@ExceptionHandler({ HttpMessageNotReadableException.class })
	public ResponseEntity<Object> httpMessageNotReadableException(RuntimeException ex, WebRequest request) {
		String mensagemUsuario = messageSource.getMessage("error.servidor.falha.leitura", null,
				LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}*/

    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<Object> usernameNotFoundException2(RuntimeException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("conta.username.invalido.ou.inexistente", null,
                LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


    //@ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {
        // TODO Auto-generated method stub
        String mensagemUsuario = messageSource.getMessage("metodo_solicitado_invalido", null,
                LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, headers, HttpStatus.METHOD_NOT_ALLOWED, request);

    }


    private List<Erro> criarListaDeErros(BindingResult bindingResult) {
        List<Erro> erros = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String mensagemDesenvolvedor = fieldError.toString();
            erros.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        }

        return erros;
    }


    public static class Erro {

        private String mensagemUsuario;
        private String mensagemDesenvolvedor;

        public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
            this.mensagemUsuario = mensagemUsuario;
            this.mensagemDesenvolvedor = mensagemDesenvolvedor;
        }

        public String getMensagemUsuario() {
            return mensagemUsuario;
        }

        public String getMensagemDesenvolvedor() {
            return mensagemDesenvolvedor;
        }

    }

}
