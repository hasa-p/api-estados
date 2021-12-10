package uol.compass.sprint3.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import uol.compass.sprint3.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

    Estado findByNome(String nome);
    Page<Estado> findByNome(String nome, Pageable paginacao);
}
