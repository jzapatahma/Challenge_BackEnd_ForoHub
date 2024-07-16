package com.aluraforohub.forohub.persistence.topico;

public record DatosRespuestaTopico(

        Long id,
        String titulo,
        String mensaje,
        String fechaCreacion,
        String status,
        Integer autor,
        Integer curso,
        Integer respuesta

) {

}
