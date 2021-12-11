package uol.compass.sprint3.config.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.validation.Constraint;
import javax.validation.Payload;

import uol.compass.sprint3.controller.form.EstadoForm;
import uol.compass.sprint3.model.Regiao;

/**
 * Anotação personalizada para validação de valores de região recebidos via
 * camada de API junto ao enum {@link Regiao}.
 *
 * @deprecated Esta anotação não é utilizada na API e a validação é feita
 *             diretamente em {@link EstadoForm} e {@link Regiao}.
 *
 * @author Pedro Amorim
 */
@Deprecated
@Documented
@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, CONSTRUCTOR, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = RegiaoEnumValidation.class)
public @interface RegiaoEnumeration {

    Class<? extends Enum<?>> enumClass();

    String message() default "must be any of enum {enumClass}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
