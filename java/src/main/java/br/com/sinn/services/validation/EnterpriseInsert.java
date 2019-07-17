package br.com.sinn.services.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = EnterpriseInsertValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnterpriseInsert {
	
	String message() default "Enterprise validation error";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default{};
	
}
