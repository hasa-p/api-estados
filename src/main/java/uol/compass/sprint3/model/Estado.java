package uol.compass.sprint3.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Regiao regiao;

    private Long populacao;
    private String capital;
    private double area;

    public Estado() {
        // construtor-padrão
    }

    /**
     * Construtor da classe de unidades federativas, sem especificação de id.
     *
     * @param nome      Nome da unidade federativa
     * @param regiao    Nome da região
     * @param populacao População total do Estado
     * @param capital   Nome da capital do Estado
     * @param area      Área total do Estado em km^2
     */
    public Estado(String nome, Regiao regiao, Long populacao, String capital, double area) {
        this.nome = nome;
        this.regiao = regiao;
        this.populacao = populacao;
        this.capital = capital;
        this.area = area;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Estado other = (Estado) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
