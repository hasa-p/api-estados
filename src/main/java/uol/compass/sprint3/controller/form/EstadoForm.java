package uol.compass.sprint3.controller.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import uol.compass.sprint3.model.Estado;
import uol.compass.sprint3.model.Regiao;
import uol.compass.sprint3.repository.EstadoRepository;

/**
 * Classe para criação de objetos e validação de valores em campos recebidos via
 * formulário/API.
 *
 * Implementou-se o Bean Validation para os campos {@code nome}, {@code regiao},
 * {@code populacao} e {@code area}.
 *
 * @author Pedro Amorim
 */
public class EstadoForm {

    @NotNull
    @NotEmpty
    @Size(min = 4)
    private String nome;

    // validar e restringir o valor do campo 'regiao' aos valores do enum Regiao
    @NotNull(message = "o nome da região deve ser um dos seguintes valores: "
            + "''Centro-Oeste'', ''Nordeste'', ''Norte'', ''Sul'', ''Sudeste''")
    private Regiao regiao;

    @Min(0)
    private Long populacao;

    private String capital;

    @Min(0)
    private double area;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Regiao getRegiao() {
        return regiao;
    }

    public void setRegiao(Regiao regiao) {
        this.regiao = regiao;
    }

    public Long getPopulacao() {
        return populacao;
    }

    public void setPopulacao(Long populacao) {
        this.populacao = populacao;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    /**
     * Instancia um objeto {@link Estado} com base nos valores recebidos via API.
     *
     * @return Objeto {@code Estado}.
     */
    public Estado getEstado() {
        return new Estado(nome, regiao, populacao, capital, area);
    }

    /**
     * Atualiza um registro em persistência com base nos atributos recebidos via
     * API.
     *
     * @param id ID do registro.
     * @param estadoRepository Objeto {@link EstadoRepository}.
     * @return Objeto {@link Estado} pós-atualização.
     */
    public Estado atualizar(Long id, EstadoRepository estadoRepository) {
        Estado estado = estadoRepository.getById(id);

        estado.setNome(this.nome);
        estado.setRegiao(this.regiao);
        estado.setPopulacao(this.populacao);
        estado.setCapital(this.capital);
        estado.setArea(this.area);

        return estado;
    }
}
