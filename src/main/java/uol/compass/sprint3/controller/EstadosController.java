package uol.compass.sprint3.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import uol.compass.sprint3.controller.dto.EstadoDto;
import uol.compass.sprint3.controller.form.EstadoForm;
import uol.compass.sprint3.model.Estado;
import uol.compass.sprint3.model.Regiao;
import uol.compass.sprint3.repository.EstadoRepository;

/**
 * Controlador REST para gerenciamento de requisições e respostas da API, e
 * integração com o back-end.
 *
 * @author Pedro Amorim
 */
@RestController
@RequestMapping("/api/states")
public class EstadosController {

    @Autowired
    private EstadoRepository estadoRepository;

    /**
     * Controla requisições do método GET e retorna uma página de resultados.
     *
     * @param regiao    Parâmetro de consulta opcional na requisição para filtragem
     *                  dos resultados por região.
     * @param paginacao Parâmetros de paginação e ordenação. Por padrão, as páginas
     *                  são ordenadas de forma crescente por ID, sem limitação de
     *                  tamanho.
     * @return {@link Page} de resultados.
     */
    @GetMapping
    public Page<EstadoDto> listar(@RequestParam(required = false) String regiao,
            @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = Integer.MAX_VALUE) Pageable paginacao) {

        Page<Estado> estados;

        if (regiao == null) {
            // sem filtro por região
            estados = estadoRepository.findAll(paginacao);
        } else {
            // com filtro por região, quando especificado na requisição
            estados = estadoRepository.findByRegiao(Regiao.forValues(regiao), paginacao);
        }

        return EstadoDto.converter(estados);
    }

    /**
     * Controla requisições do método GET com especificação de ID.
     *
     * @param id ID do Estado buscado.
     * @return {@link ResponseEntity} com objeto e status code 200, se ID for
     *         encontrado; senão, retorna status code 404.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EstadoDto> expand(@Valid @PathVariable Long id) {
        Optional<Estado> estado = estadoRepository.findById(id);

        // verificar se o ID existe
        if (estado.isPresent()) {
            return ResponseEntity.ok(new EstadoDto(estado.get()));
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * Controla requisições do método POST para persistência de objeto.
     *
     * @param form       Objeto criado via formulário, com base no modelo
     *                   {@link Estado}.
     * @param uriBuilder Construtor de path.
     * @return {@link ResponseEntity} com objeto criado.
     */
    @PostMapping
    @Transactional
    public ResponseEntity<EstadoDto> cadastrar(@Valid @RequestBody EstadoForm form, UriComponentsBuilder uriBuilder) {
        Estado estado = form.getEstado();
        estadoRepository.save(estado);

        URI uri = uriBuilder.path("/estados/{id}").buildAndExpand(estado.getId()).toUri();

        return ResponseEntity.created(uri).body(new EstadoDto(estado));
    }

    /**
     * Controla requisições do método PUT para atualização de registro.
     *
     * @param id   ID do Estado a atualizar.
     * @param form Objeto criado via formulário com dados atualizados, com base no
     *             modelo {@link Estado}.
     * @return {@link ResponseEntity} com objeto atualizado, se ID existir; caso
     *         contrário, retorna status code 404.
     */
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EstadoDto> atualizar(@PathVariable Long id, @Valid @RequestBody EstadoForm form) {
        Optional<Estado> optional = estadoRepository.findById(id);

        // verificar se o ID existe
        if (optional.isPresent()) {
            Estado estado = form.atualizar(id, estadoRepository);
            return ResponseEntity.ok(new EstadoDto(estado));
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * Controla requisições do método DELETE para remoção de registro.
     *
     * @param id ID do Estado a atualizar.
     * @return {@link ResponseEntity} com objeto removido, se ID existir; caso
     *         contrário, retorna status code 404.
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Estado> optional = estadoRepository.findById(id);

        // verificar se o ID existe
        if (optional.isPresent()) {
            estadoRepository.deleteById(id);
            return ResponseEntity.ok(new EstadoDto(optional.get()));
        }

        return ResponseEntity.notFound().build();
    }
}
