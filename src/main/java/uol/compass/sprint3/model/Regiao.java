package uol.compass.sprint3.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Regiao {

    NORTE("Norte"),
    NORDESTE("Nordeste"),
    SUL("Sul"),
    SUDESTE("Sudeste"),
    CENTRO_OESTE("Centro-Oeste");

    private String valor;

    Regiao(String valor) {
        this.valor = valor;
    }

    @JsonValue
    public String getValor() {
        return valor;
    }

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
