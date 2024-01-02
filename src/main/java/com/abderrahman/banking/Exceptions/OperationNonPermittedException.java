package com.abderrahman.banking.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OperationNonPermittedException extends  RuntimeException {
    private  final String errorMessage;
    private  final  String operationId;
    private  final String source;
    private  final  String Dependency;

}
