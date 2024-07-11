package com.aluraforohub.forohub.domain.topico;

public record DatosActualizarTopico(
        Long idTopico,
        String tituloTco,
        String mensajeTco,
        String fechaCreacionTco,
        String statusTco,
        Integer idUsuario,
        Integer idCurso,
        Integer idRespuesta
) {

}
