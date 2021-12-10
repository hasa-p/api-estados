package uol.compass.sprint3.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import uol.compass.sprint3.model.Estado;
import uol.compass.sprint3.model.Regiao;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

    Estado findByRegiao(Regiao regiao);
    Page<Estado> findByRegiao(Regiao regiao, Pageable paginacao);
}
