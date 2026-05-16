package hytorc_solutions_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "FORMULARIO")
public class Formulario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_formulario")
    private Integer idFormulario;

    @Column(name = "nombre_formulario")
    private String nombreFormulario;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "ruta_formulario")
    private String rutaFormulario;


    public Integer getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(Integer idFormulario) {
        this.idFormulario = idFormulario;
    }

    public String getNombreFormulario() {
        return nombreFormulario;
    }

    public void setNombreFormulario(String nombreFormulario) {
        this.nombreFormulario = nombreFormulario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRutaFormulario() {
        return rutaFormulario;
    }

    public void setRutaFormulario(String rutaFormulario) {
        this.rutaFormulario = rutaFormulario;
    }
}