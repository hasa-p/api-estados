package uol.compass.sprint3.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import uol.compass.sprint3.model.Estado;
import uol.compass.sprint3.model.Regiao;

/**
 * Repositório de domínio para persistência de objetos {@code Estado}.
 *
 * @author Pedro Amorim
 *
 */
public interface EstadoRepository extends JpaRepository<Estado, Long> {

    // assinatura para busca por região
    Estado findByRegiao(Regiao regiao);

    // assinatura para busca por região com paginação
    Page<Estado> findByRegiao(Regiao regiao, Pageable paginacao);
}
