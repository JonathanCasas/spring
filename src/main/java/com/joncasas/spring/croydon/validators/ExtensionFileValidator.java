/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joncasas.spring.croydon.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

/**
 * @author jonathan
 */
public class ExtensionFileValidator implements ConstraintValidator<ExtensionFiles, MultipartFile> {

    ExtensionFiles extensionFiles;

    @Override
    public void initialize(ExtensionFiles constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation); //To change body of generated methods, choose Tools | Templates.
        this.extensionFiles = constraintAnnotation;
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        if (this.extensionFiles.types().length <= 0)
            return true;
        for (String type : this.extensionFiles.types()) {
            if (getFileExtension(value).equals(type)) {
                return true;
            }
        }
        return false;
    }


    private String getFileExtension(MultipartFile value) {
        return Objects.requireNonNull(value.getOriginalFilename()).substring(value.getOriginalFilename().lastIndexOf(".") + 1);
    }
}
