package com.aluraforohub.forohub.controllers;

import ch.qos.logback.classic.pattern.RelativeTimeConverter;
import com.aluraforohub.forohub.domain.topico.DatosActualizarTopico;
import com.aluraforohub.forohub.domain.topico.Topico;
import com.aluraforohub.forohub.domain.topico.TopicoRecord;
import com.aluraforohub.forohub.domain.topico.TopicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
//@PreAuthorize("denyAll()") // niega el acceso a todos.
public class TopicoController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/hello-security")
    public String helloSecure(){
        return "Hello security";
    }


//    @Autowired
//    private TopicoRepository topicoRepository;
//
//    @PostMapping
//    @PreAuthorize("permitAll") // permite todos
//    public ResponseEntity<DatosActualizarTopico> registrarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico, UriComponentsBuilder uriComponentsBuilder){
//        Topico topico = topicoRepository.save(new Topico(datosActualizarTopico));
//        DatosActualizarTopico dtactualizar = new DatosActualizarTopico(
//                topico.getIdTopico(),
//                topico.getTituloTco(),
//                topico.getMensajeTco(),
//                topico.getFechaCreacionTco(),
//                topico.getStatusTco(),
//                topico.getIdUsuario(),
//                topico.getIdCurso(),
//                topico.getIdRespuesta()
//        );
//        // Que retorna? el registro ingresado
//        DatosActualizarTopico respuestaTopico = new DatosActualizarTopico(
//                topico.getIdTopico(), topico.getTituloTco(), topico.getMensajeTco(),
//                topico.getFechaCreacionTco(), topico.getStatusTco(), topico.getIdUsuario(),
//                topico.getIdCurso(), topico.getIdRespuesta()
//        );
//
//        URI uri;
//        uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getIdTopico()).toUri();
//
//        return ResponseEntity.created(uri).body(respuestaTopico);
//    }
//
//    @GetMapping("/hello")
//    @PreAuthorize("hasAuthority('READ')")
//    public ResponseEntity<Page<TopicoRecord>> listadoTopicos(@PageableDefault() Pageable paginacion){
//        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(TopicoRecord::new));
//    }
//
//    @PutMapping
//    @Transactional
//    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
//        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.idTopico());
//        topico.actualizarDatos(datosActualizarTopico);
//        return ResponseEntity.ok().build();
//
//    }
}
