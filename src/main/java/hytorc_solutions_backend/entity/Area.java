package hytorc_solutions_backend.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "AREA")
public class Area {

    @Id
    @Column(name = "id_area")
    private Integer idArea;

    @Column(name = "nombre_area", nullable = false, length = 30)
    private String nombreArea;

    @Column(name = "descripcion_area", nullable = false, length = 100)
    private String descripcionArea;

    @Column(name = "estado_area", nullable = false)
    private Integer estadoArea;

    // Getters y setters
    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public String getDescripcionArea() {
        return descripcionArea;
    }

    public void setDescripcionArea(String descripcionArea) {
        this.descripcionArea = descripcionArea;
    }

    public Integer getEstadoArea() {
        return estadoArea;
    }

    public void setEstadoArea(Integer estadoArea) {
        this.estadoArea = estadoArea;
    }
}