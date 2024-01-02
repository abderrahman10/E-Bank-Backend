package com.abderrahman.banking.Handlers;

import com.abderrahman.banking.Exceptions.ObjectValidationException;
import com.abderrahman.banking.Exceptions.OperationNonPermittedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //on dit a spring que cest notre gestionnaire d'exception global

public class GlobalExceptionHandler  {
    @ExceptionHandler(ObjectValidationException.class)// il permet de catch une exception //cette methode va absorber touts exception  de ObjectValidationException

   public ResponseEntity<ExceptionRepresentation> handleException(ObjectValidationException exception){
          //le traitement que je vais appliqué apres le catche d'une exception de type  ObjectValidationException
        ExceptionRepresentation representation = ExceptionRepresentation
                .builder()
                .errorMessage("Object not Valid exception had occurred")
                .errorSource(exception.getViolationSource())
                .validationErrors(exception.getViolations())
                .build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(representation);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionRepresentation> handlerException(EntityNotFoundException exception){
        ExceptionRepresentation representation = ExceptionRepresentation
                .builder()
                .errorMessage(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(representation);

    }

    @ExceptionHandler(OperationNonPermittedException.class)
    public ResponseEntity<ExceptionRepresentation> handlerException(OperationNonPermittedException exception){
        ExceptionRepresentation representation = ExceptionRepresentation
                .builder()
                .errorMessage(exception.getErrorMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(representation);

    }
    @ExceptionHandler(DataIntegrityViolationException.class)  //pour gerer les erreur de models(email=unique)
    public ResponseEntity<ExceptionRepresentation> handlerException( ){
        ExceptionRepresentation representation = ExceptionRepresentation
                .builder()
                .errorMessage("A user already exists with the provided email ")
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(representation);

    }
    @ExceptionHandler(DisabledException.class)  //pour gerer les erreur de models(email=unique)
    public ResponseEntity<ExceptionRepresentation> handlerDisableException( ){
        ExceptionRepresentation representation = ExceptionRepresentation
                .builder()
                .errorMessage("you can not access your account because it is not yet activated  ")
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(representation);

    }
    @ExceptionHandler(BadCredentialsException.class)  //pour gerer les erreur de models(email=unique)
    public ResponseEntity<ExceptionRepresentation> handlerBadCredentialsException( ){
        ExceptionRepresentation representation = ExceptionRepresentation
                .builder()
                .errorMessage("the Email or the password is Incorrect    ")
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(representation);

    }

}
