package hytorc_solutions_backend.dto;

import java.time.LocalDate;

public class UsuarioDto {

    private Integer idUsuario; 
    private String usuario;
    private String nombreCompleto;
    private String dni;
    private String telefono;
    private String direccion;
    private String email;
    private LocalDate fechaNacimiento;
    private String tipoUsu;
    private Integer idArea;
    private String nombreArea;
    private String firmaDigital; // 🔹 nuevo campo para la firma

    // Constructor completo
    public UsuarioDto(Integer idUsuario, String usuario, String nombreCompleto, String dni, String telefono,
            String direccion, String email, LocalDate fechaNacimiento, String tipoUsu, Integer idArea,
            String nombreArea, String firmaDigital) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.nombreCompleto = nombreCompleto;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoUsu = tipoUsu;
        this.idArea = idArea;
        this.nombreArea = nombreArea;
        this.firmaDigital = firmaDigital;
    }

    // 🔹 Getters y Setters
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTipoUsu() {
        return tipoUsu;
    }

    public void setTipoUsu(String tipoUsu) {
        this.tipoUsu = tipoUsu;
    }

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

    public String getFirmaDigital() {
        return firmaDigital;
    }

    public void setFirmaDigital(String firmaDigital) {
        this.firmaDigital = firmaDigital;
    }
}