package uol.compass.sprint3.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import uol.compass.sprint3.config.validation.RegiaoEnumeration;
import uol.compass.sprint3.model.Estado;
import uol.compass.sprint3.model.Regiao;
import uol.compass.sprint3.repository.EstadoRepository;

public class EstadoForm {

    @NotNull
    @NotEmpty
    @Size(min = 4)
    private String nome;

    @NotNull
    @RegiaoEnumeration(enumClass = Regiao.class)
    private Regiao regiao;
    private Long populacao;
    private String capital;
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

    public Estado getEstado() {
        return new Estado(nome, regiao, populacao, capital, area);
    }

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
