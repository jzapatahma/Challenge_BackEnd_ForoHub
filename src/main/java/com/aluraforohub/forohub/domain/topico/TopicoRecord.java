package com.aluraforohub.forohub.domain.topico;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

public record TopicoRecord(
        Long idTopico,
        String tituloTco,
        String mensajeTco,
        String fechaCreacionTco,
        String statusTco,
        Integer idUsuario,
        Integer idCurso,
        Integer idRespuesta
) {
    public TopicoRecord(Topico topico){
        this(topico.getIdTopico(), topico.getTituloTco(), topico.getMensajeTco(),
                topico.getFechaCreacionTco(), topico.getStatusTco(), topico.getIdUsuario(), topico.getIdCurso(), topico.getIdRespuesta());
    }
}
