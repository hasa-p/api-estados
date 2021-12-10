package uol.compass.sprint3.controller.dto;

import org.springframework.data.domain.Page;

import uol.compass.sprint3.model.Estado;
import uol.compass.sprint3.model.Regiao;

public class EstadoDto {
    private Long id;
    private String nome;
    private Regiao regiao;
    private Long populacao;
    private String capital;
    private double area;

    public EstadoDto(Estado estado) {
        this.id = estado.getId();
        this.nome = estado.getNome();
        this.regiao = estado.getRegiao();
        this.populacao = estado.getPopulacao();
        this.capital = estado.getCapital();
        this.area = estado.getArea();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Regiao getRegiao() {
        return regiao;
    }

    public Long getPopulacao() {
        return populacao;
    }

    public String getCapital() {
        return capital;
    }

    public double getArea() {
        return area;
    }

    public static Page<EstadoDto> converter(Page<Estado> estados) {
        return estados.map(EstadoDto::new);
    }
}
