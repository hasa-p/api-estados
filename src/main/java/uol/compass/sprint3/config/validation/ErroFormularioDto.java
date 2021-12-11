package uol.compass.sprint3.config.validation;

import net.bytebuddy.build.Plugin.Engine.ErrorHandler;

/**
 * Classe para validação de dados e tratamento de erros, utilizada com
 * {@link ErrorHandler}. Cria um objeto para identificação do campo com erro e
 * do erro em si.
 *
 * @author Pedro Amorim
 */
public class ErroFormularioDto {

    private String campo;
    private String erro;

    public ErroFormularioDto(String campo, String erro) {
        super();
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
