package uol.compass.sprint3.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum de regiões do IBGE, para facilitar a validação de dados cadastrados via
 * API.
 *
 * @author Pedro Amorim
 *
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Regiao {

    NORTE("Norte"), NORDESTE("Nordeste"), SUL("Sul"), SUDESTE("Sudeste"), CENTRO_OESTE("Centro-Oeste");

    // valor padronizado em String
    private String valor;

    /**
     * Define o valor em String do nome.
     *
     * @param valor String com o nome da região
     */
    Regiao(String valor) {
        this.valor = valor;
    }

    /**
     * Devolve o nome padronizado para serialização e criação de um objeto JSON.
     *
     * @return String com o nome da região
     */
    @JsonValue
    public String getValor() {
        return valor;
    }

    /**
     * Serializa e vincula uma String à sua respectiva constante Enum.
     *
     * @param valor Nome da região obtido no campo "regiao".
     * @return Enum associado à String, se seus valores forem iguais; caso contrário, {@code null}
     */
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Regiao forValues(@JsonProperty("regiao") String valor) {
        for (Regiao regiao : Regiao.values()) {
            if (regiao.valor.equalsIgnoreCase(valor)) {
                return regiao;
            }
        }

        return null;
    }
}
