package com.aluraforohub.forohub.domain.topico;

import com.aluraforohub.forohub.domain.curso.Curso;
import com.aluraforohub.forohub.domain.respuesta.Respuesta;
import com.aluraforohub.forohub.domain.usuario.UsuarioEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "topicos")
@Entity(name="Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="idTopico")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTopico;
    private String tituloTco;
    private String mensajeTco;
    private String fechaCreacionTco;
    private String statusTco;
    private Integer idUsuario;
    private Integer idCurso;
    private Integer idRespuesta;

    @Transient
    private List<UsuarioEntity> usuarioEntities;
    @Transient
    private List<Curso> cursos;
    @Transient
    private List<Respuesta> respuestas;

    public Topico(DatosActualizarTopico datosActualizarTopico) {
        this.tituloTco = datosActualizarTopico.tituloTco();
        this.mensajeTco = datosActualizarTopico.mensajeTco();
        this.fechaCreacionTco = datosActualizarTopico.fechaCreacionTco();
        this.statusTco = datosActualizarTopico.statusTco();
        //this.activo = true; tenerlo encuenta
    }


    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico) {
        this.tituloTco = datosActualizarTopico.tituloTco();
        this.mensajeTco = datosActualizarTopico.mensajeTco();
        this.fechaCreacionTco = datosActualizarTopico.fechaCreacionTco();
        this.statusTco = datosActualizarTopico.statusTco();
        this.idUsuario = datosActualizarTopico.idUsuario();
        this.idCurso = datosActualizarTopico.idCurso();
        this.idRespuesta = datosActualizarTopico.idRespuesta();
    }
}
