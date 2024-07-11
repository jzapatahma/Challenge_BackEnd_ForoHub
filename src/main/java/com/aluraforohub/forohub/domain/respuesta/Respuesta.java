package com.aluraforohub.forohub.domain.respuesta;

import com.aluraforohub.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "respuestas")
@Entity(name="Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="idRespuesta")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRespuesta;
    private String mensajeRta;
    private String solucionRta;
    private String fechaCreacionRta;
    private Integer idUsuario;
    private Integer idTopico;
    @Transient
    private List<Usuario> usuarios;
}
