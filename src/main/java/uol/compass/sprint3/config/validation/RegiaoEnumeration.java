package uol.compass.sprint3.config.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, CONSTRUCTOR })
@Retention(RUNTIME)
@Constraint(validatedBy = RegiaoEnumValidation.class)
@Documented
public @interface RegiaoEnumeration {

    String message() default "{uol.compass.sprint3.config.validation.RegiaoEnumeration.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    Class<? extends Enum<?>> enumClass();
}
