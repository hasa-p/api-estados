package uol.compass.sprint3.model;

public enum Regiao {

    NORTE("Norte"), NORDESTE("Nordeste"), SUL("Sul"), SUDESTE("Sudeste"), CENTRO_OESTE("Centro-Oeste"), NAO_DEFINIDO("ND");

    private String regiao;

    Regiao(String regiao) {
        this.regiao = regiao;
    }

    public String getValue() {
        return this.regiao;
    }
}
