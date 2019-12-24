/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joncasas.spring.croydon.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author jonathan
 */
@Documented
@Constraint(validatedBy = ExtensionFileValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtensionFiles {

    String[] types();

    String message() default "Debe seleccionar unicamente csv";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
