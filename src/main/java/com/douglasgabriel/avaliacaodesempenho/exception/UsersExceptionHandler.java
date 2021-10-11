package com.douglasgabriel.avaliacaodesempenho.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public final class UsersExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String CONST_FIELD_ERRORS = "Campo ";
    private static final String CONST_GLOBAL_ERRORS_OBJECT = "Objeto ";
    private static final String CONST_FIELD_ESPACO = " ";
    private static final String CONST_MENSAGEM_VALIDACAO_ERRADA = "Validação das Informações Obteve um Erro ";
    private static final String CONST_JSON_BODY_ERROR = "JSON Mal-escrito, ou Houve um Problema em Algum Campo ";


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(final EntityNotFoundException exception){
        return constroiResponseEntity(HttpStatus.NOT_FOUND,
                exception.getMessage(),
                Collections.singletonList(exception.getMessage()));
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<Object> handleEntityExistsException(final EntityExistsException exception){
        return constroiResponseEntity(HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                Collections.singletonList(exception.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> listaErros = new ArrayList<>();

        exception.getBindingResult().
                getFieldErrors().
                forEach(fieldError -> listaErros.add(CONST_FIELD_ERRORS + fieldError.getField().toUpperCase() + CONST_FIELD_ESPACO + fieldError.getDefaultMessage()));

        exception.getBindingResult().
                getGlobalErrors().
                forEach(globalErrors -> listaErros.add(CONST_GLOBAL_ERRORS_OBJECT + globalErrors.getObjectName() + CONST_FIELD_ESPACO + globalErrors.getDefaultMessage()));



        return constroiResponseEntity(HttpStatus.BAD_REQUEST, CONST_MENSAGEM_VALIDACAO_ERRADA , listaErros);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return constroiResponseEntity(HttpStatus.BAD_REQUEST,
                CONST_JSON_BODY_ERROR,
                Collections.singletonList(exception.getLocalizedMessage()));
    }

    private ResponseEntity<Object> constroiResponseEntity(
            final HttpStatus httpStatus, final String mensagem, List<String> erros) {

        ApiError apiError = ApiError.builder()
                .codigoErro(httpStatus.value())
                .status(httpStatus.getReasonPhrase())
                .mensagemErro(mensagem)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(httpStatus).body(apiError);
    }
}
