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
import uol.compass.sprint3.repository.EstadoRepository;

@RestController
@RequestMapping("/estados")
public class EstadosController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public Page<EstadoDto> listar(@RequestParam(required = false) String nome,
            @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 5) Pageable paginacao) {

        Page<Estado> estados;

        if (nome == null) {
            estados = estadoRepository.findAll(paginacao);
        } else {
            estados = estadoRepository.findByNome(nome, paginacao);
        }

        return EstadoDto.converter(estados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoDto> expand(@PathVariable Long id) {
        Optional<Estado> estado = estadoRepository.findById(id);

        if (estado.isPresent()) {
            return ResponseEntity.ok(new EstadoDto(estado.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<EstadoDto> cadastrar(@Valid @RequestBody EstadoForm form, UriComponentsBuilder uriBuilder) {
        Estado estado = form.getEstado();
        estadoRepository.save(estado);

        URI uri = uriBuilder.path("/estados/{id}").buildAndExpand(estado.getId()).toUri();

        return ResponseEntity.created(uri).body(new EstadoDto(estado));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EstadoDto> atualizar(@PathVariable Long id, @Valid @RequestBody EstadoForm form) {
        Optional<Estado> optional = estadoRepository.findById(id);

        if (optional.isPresent()) {
            Estado estado = form.atualizar(id, estadoRepository);
            return ResponseEntity.ok(new EstadoDto(estado));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Estado> optional = estadoRepository.findById(id);

        if (optional.isPresent()) {
            estadoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
