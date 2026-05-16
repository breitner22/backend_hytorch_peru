package hytorc_solutions_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "usuario", nullable = false, unique = true, length = 20)
    private String usuario;

    @Column(name = "password", nullable = false, length = 200)
    private String password;

    @Column(name = "estado_usuario", nullable = false)
    private Integer estadoUsuario;

    @Column(name = "tipo_usu", nullable = false, length = 1)
    private String tipoUsu;

    @OneToOne
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    private Personal empleado;



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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(Integer estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public String getTipoUsu() {
        return tipoUsu;
    }

    public void setTipoUsu(String tipoUsu) {
        this.tipoUsu = tipoUsu;
    }

    public Personal getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Personal empleado) {
        this.empleado = empleado;
    }

    @Column(name = "foto_perfil", length = 255)
private String fotoPerfil;

@Column(name = "firma_digital", length = 255)
private String firmaDigital;

// Getters y Setters
public String getFotoPerfil() {
    return fotoPerfil;
}

public void setFotoPerfil(String fotoPerfil) {
    this.fotoPerfil = fotoPerfil;
}

public String getFirmaDigital() {
    return firmaDigital;
}

public void setFirmaDigital(String firmaDigital) {
    this.firmaDigital = firmaDigital;
}
}