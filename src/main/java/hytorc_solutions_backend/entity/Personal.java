package hytorc_solutions_backend.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PERSONAL")
public class Personal {

    @Id
    @Column(name = "id_empleado")
    private Integer idEmpleado;

    @Column(name = "nom_empleado", nullable = false, length = 50)
    private String nomEmpleado;

    @Column(name = "apell_empleado", nullable = false, length = 50)
    private String apellEmpleado;

    @Column(name = "dni_empleado", nullable = false, length = 8, unique = true)
    private String dniEmpleado;

    @Column(name = "telef_empleado", nullable = false, length = 9)
    private String telefEmpleado;

    @Column(name = "direccion", nullable = false, length = 100)
    private String direccion;

    @Column(name = "email_empleado", nullable = false, length = 50, unique = true)
    private String emailEmpleado;

    @Column(name = "fech_ingreso", nullable = false)
    private LocalDate fechIngreso;

    @Column(name = "fech_nacimiento", nullable = false)
    private LocalDate fechNacimiento;

    @Column(name = "fech_cese")
    private LocalDate fechCese;

    @Column(name = "estado_empleado", nullable = false)
    private Integer estadoEmpleado;

    @Column(name = "id_area", nullable = false)
    private Integer idArea;

    // 🔹 Relación ManyToOne con Area
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_area", referencedColumnName = "id_area", insertable = false, updatable = false)
    private Area area;

    // Getters y setters

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNomEmpleado() {
        return nomEmpleado;
    }

    public void setNomEmpleado(String nomEmpleado) {
        this.nomEmpleado = nomEmpleado;
    }

    public String getApellEmpleado() {
        return apellEmpleado;
    }

    public void setApellEmpleado(String apellEmpleado) {
        this.apellEmpleado = apellEmpleado;
    }

    public String getDniEmpleado() {
        return dniEmpleado;
    }

    public void setDniEmpleado(String dniEmpleado) {
        this.dniEmpleado = dniEmpleado;
    }

    public String getTelefEmpleado() {
        return telefEmpleado;
    }

    public void setTelefEmpleado(String telefEmpleado) {
        this.telefEmpleado = telefEmpleado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmailEmpleado() {
        return emailEmpleado;
    }

    public void setEmailEmpleado(String emailEmpleado) {
        this.emailEmpleado = emailEmpleado;
    }

    public LocalDate getFechIngreso() {
        return fechIngreso;
    }

    public void setFechIngreso(LocalDate fechIngreso) {
        this.fechIngreso = fechIngreso;
    }

    public LocalDate getFechNacimiento() {
        return fechNacimiento;
    }

    public void setFechNacimiento(LocalDate fechNacimiento) {
        this.fechNacimiento = fechNacimiento;
    }

    public LocalDate getFechCese() {
        return fechCese;
    }

    public void setFechCese(LocalDate fechCese) {
        this.fechCese = fechCese;
    }

    public Integer getEstadoEmpleado() {
        return estadoEmpleado;
    }

    public void setEstadoEmpleado(Integer estadoEmpleado) {
        this.estadoEmpleado = estadoEmpleado;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}