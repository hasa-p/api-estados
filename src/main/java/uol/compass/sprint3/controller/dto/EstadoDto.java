package uol.compass.sprint3.controller.dto;

import org.springframework.data.domain.Page;

import uol.compass.sprint3.model.Estado;
import uol.compass.sprint3.model.Regiao;

/**
 * Classe para conversão de entidades internas da aplicação em objetos para a
 * camada de API.
 *
 * @author Pedro Amorim
 */
public class EstadoDto {
    private Long id;
    private String nome;
    private Regiao regiao;
    private Long populacao;
    private String capital;
    private double area;

    /**
     * Construtor que recebe um objeto da entidade {@link Estado} e obtém seus
     * atributos.
     *
     * @param estado Objeto do modelo {@code Estado}.
     */
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

    /**
     * Mapeia e converte uma {@link Page} de objetos do tipo {@link Estado} em uma
     * {@code Page} de objetos {@link EstadoDto}.
     *
     * @param estados {@code Page} de objetos {@code Estado}.
     * @return {@code Page} de objetos {@code EstadoDto}.
     */
    public static Page<EstadoDto> converter(Page<Estado> estados) {
        return estados.map(EstadoDto::new);
    }
}
