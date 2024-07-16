package com.aluraforohub.forohub.controllers;

import com.aluraforohub.forohub.domain.topico.TopicoRepository;
import com.aluraforohub.forohub.persistence.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@PreAuthorize("denyAll")
public class TopicoController {

    @Autowired
    private com.aluraforohub.forohub.repository.TopicoRepository topicoRepository;

    // Guardar un registro nuevo
    @PostMapping
    @PreAuthorize("hasAuthority('CREATE') or hasAuthority('REFACTOR')")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico));
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(
                topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFechaCreacion(), topico.getStatus(), topico.getAutor(),
                topico.getCurso(), topico.getRespuestas() );
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    // Eliminar un registro por medio de su ID
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE') or hasAuthority('REFACTOR')")
    @Transactional
    public ResponseEntity<String> eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topicoRepository.delete(topico);
        return ResponseEntity.ok("Topico: "+id+" - "+topico.getTitulo()+" eliminado de BD");
    }
    // Listar toda la lista existente en la tabla Topicos de las base de datos.
    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    //public ResponseEntity<Page<DatosListadoTopico>> listadoTopicos(@PageableDefault(size = 2) Pageable paginacion) {
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicos(@PageableDefault() Pageable paginacion) {//
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosListadoTopico::new));
    }
    // Actualizar registro por medio de su ID.
    @PutMapping
    @PreAuthorize("hasAuthority('UPDATE')")
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarDatos(datosActualizarTopico);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(), topico.getTitulo(),
                topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(),
                topico.getAutor(), topico.getCurso(), topico.getRespuestas()));
    }

}