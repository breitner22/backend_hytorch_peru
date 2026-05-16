package hytorc_solutions_backend.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "REG_FORMULARIO_CAB")
public class RegFormularioCab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reg_cab")
    private Integer idRegCab;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "id_formulario")
    private Formulario formulario;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "version")
    private Integer version;

    @Column(name = "estado")
    private String estado = "P";

    @PrePersist
    public void prePersist() {
        this.fecha = LocalDateTime.now();
    }

    public Integer getIdRegCab() {
        return idRegCab;
    }

    public void setIdRegCab(Integer idRegCab) {
        this.idRegCab = idRegCab;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Formulario getFormulario() {
        return formulario;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

@OneToMany(mappedBy = "regFormularioCab")
private List<RegFormularioDet> detalles;

    
}