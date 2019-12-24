/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joncasas.spring.croydon.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.validation.Constraint;
import javax.validation.Payload;

import org.hibernate.annotations.Target;

/**
 * @author jonathan
 */
@Documented
@Constraint(validatedBy = ExtensionFileValidator.class)
//@Target(ElementType .METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtensionFiles {

    String[] types();

    String message() default "Debe seleccionar unicamente csv";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
