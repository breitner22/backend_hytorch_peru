package hytorc_solutions_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "HISTORIAL_FORMULARIO")
public class HistorialFormulario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private Integer idHistorial;

    @Column(name = "id_reg_cab", nullable = false)
    private Integer idRegCab;

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "fecha_mod")
    private LocalDateTime fechaMod;

    @Column(name = "cambios", columnDefinition = "TEXT")
    private String cambios;

    // ==============================
    // GETTERS Y SETTERS
    // ==============================

    public Integer getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Integer getIdRegCab() {
        return idRegCab;
    }

    public void setIdRegCab(Integer idRegCab) {
        this.idRegCab = idRegCab;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDateTime getFechaMod() {
        return fechaMod;
    }

    public void setFechaMod(LocalDateTime fechaMod) {
        this.fechaMod = fechaMod;
    }

    public String getCambios() {
        return cambios;
    }

    public void setCambios(String cambios) {
        this.cambios = cambios;
    }

  
    @PrePersist
    protected void onCreate() {
        if (fechaMod == null) {
            fechaMod = LocalDateTime.now();
        }
    }
}