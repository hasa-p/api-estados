package uol.compass.sprint3.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import uol.compass.sprint3.controller.form.EstadoForm;

/**
 * Classe para tratamento de erros na camada da API.
 *
 * @author Pedro Amorim
 */
@RestControllerAdvice
public class ErroHandler {

    @Autowired
    private MessageSource messageSource;

    /**
     * Trata a exceção {@code MethodArgumentNotValidException}, em requisições com valores inválidos.
     * @param ex Exceção a tratar.
     * @return {@code List} de objetos {@link ErroFormularioDto}.
     * @see {@link EstadoForm}
     */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroFormularioDto> handle(MethodArgumentNotValidException ex) {
        List<ErroFormularioDto> dto = new ArrayList<>();

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        fieldErrors.forEach(e -> {
            String msg = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroFormularioDto erro = new ErroFormularioDto(e.getField(), msg);
            dto.add(erro);
        });

        return dto;
    }
}
