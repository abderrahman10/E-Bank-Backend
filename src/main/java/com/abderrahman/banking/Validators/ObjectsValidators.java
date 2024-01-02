package com.abderrahman.banking.Validators;

import com.abderrahman.banking.Exceptions.ObjectValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;
@Component
public class ObjectsValidators<T> {
// validatorfactory et comme usine qui permet de creer des instance a partir de lui ,
// et validator c'est lui qui responsable de valider les object selon les contraints
    private  final ValidatorFactory factory= Validation.buildDefaultValidatorFactory();
    private  final Validator validator= factory.getValidator();

public  void validate(T objectToValidate){
    //L'ensemble violations contient des détails complets sur chaque violation de contrainte,
    // y compris des informations telles que le message d'erreur, le nom du champ ou de la propriété concerné
   Set<ConstraintViolation<T>>  violations = validator.validate(objectToValidate);
   if (!violations.isEmpty()){

       Set<String> errorMessages = violations.stream()
               .map(ConstraintViolation::getMessage) //extrayez uniquement le message d'erreur de chaque violation en utilisant la méthode getMessage()
               .collect(Collectors.toSet());
       throw  new ObjectValidationException(errorMessages,objectToValidate.getClass().getName());


   }

}
}
