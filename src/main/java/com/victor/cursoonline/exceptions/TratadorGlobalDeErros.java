package com.victor.cursoonline.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.victor.cursoonline.exceptions.dtos.ErroPadrao;
import com.victor.cursoonline.exceptions.dtos.ErroValidacao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class TratadorGlobalDeErros {
	
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErroPadrao> recursoNaoEncontrado(ResourceNotFoundException e, HttpServletRequest request) {
        ErroPadrao erro = new ErroPadrao(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ErroPadrao> erroDeBanco(DatabaseException e, HttpServletRequest request) {
        ErroPadrao erro = new ErroPadrao(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de banco de dados",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroValidacao> erroDeValidacao(MethodArgumentNotValidException e, HttpServletRequest request) {
        ErroValidacao erro = new ErroValidacao(
                Instant.now(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação",
                "Um ou mais campos estão inválidos.",
                request.getRequestURI()
        );

        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            erro.addErro(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErroPadrao> violacaoDeConstraint(ConstraintViolationException e, HttpServletRequest request) {
        ErroPadrao erro = new ErroPadrao(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Violação de restrição",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}

