package hytorc_solutions_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CAMPO_FORMULARIO")
public class CampoFormulario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_campo")
    private Integer idCampo;

    @Column(name = "nombre_campo")
    private String nombreCampo;

    @Column(name = "tipo_campo")
    private String tipoCampo;

    @Column(name = "obligacion")
    private Boolean obligacion;

    @Column(name = "opciones")
    private String opciones;

    // GETTERS Y SETTERS

    public Integer getIdCampo() {
        return idCampo;
    }

    public void setIdCampo(Integer idCampo) {
        this.idCampo = idCampo;
    }

    public String getNombreCampo() {
        return nombreCampo;
    }

    public void setNombreCampo(String nombreCampo) {
        this.nombreCampo = nombreCampo;
    }

    public String getTipoCampo() {
        return tipoCampo;
    }

    public void setTipoCampo(String tipoCampo) {
        this.tipoCampo = tipoCampo;
    }

    public Boolean getObligacion() {
        return obligacion;
    }

    public void setObligacion(Boolean obligacion) {
        this.obligacion = obligacion;
    }

    public String getOpciones() {
        return opciones;
    }

    public void setOpciones(String opciones) {
        this.opciones = opciones;
    }
}