package uol.compass.sprint3.config.validation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import uol.compass.sprint3.controller.form.EstadoForm;
import uol.compass.sprint3.model.Regiao;

/**
 * Classe de validação para a anotação {@link RegiaoEnumeration}.
 *
 * @deprecated Esta classe não é utilizada na API e a validação é feita
 *             diretamente em {@link EstadoForm} e {@link Regiao}.
 *
 * @author Pedro Amorim
 */
@Deprecated
public class RegiaoEnumValidation implements ConstraintValidator<RegiaoEnumeration, String> {

    private List<String> acceptedValues;

    @Override
    public void initialize(RegiaoEnumeration annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants()).map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return acceptedValues.contains(value.toString());
    }
}
