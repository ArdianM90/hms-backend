package com.project.hms.common.utils;

import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

@IdGeneratorType( ShortIdGenerator.class )
@Retention( RetentionPolicy.RUNTIME )
@Target( {FIELD, METHOD} )
public @interface ShortId {

}
