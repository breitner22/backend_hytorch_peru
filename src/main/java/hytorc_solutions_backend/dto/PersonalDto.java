package hytorc_solutions_backend.dto;

public class PersonalDto {
    private Integer idEmpleado;
    private String nomEmpleado;
    private String apellEmpleado;

    private Integer idArea;
    private String nombreArea;
    private String descripcionArea;

    public PersonalDto(Integer idEmpleado, String nomEmpleado, String apellEmpleado,
            Integer idArea, String nombreArea, String descripcionArea) {
        this.idEmpleado = idEmpleado;
        this.nomEmpleado = nomEmpleado;
        this.apellEmpleado = apellEmpleado;
        this.idArea = idArea;
        this.nombreArea = nombreArea;
        this.descripcionArea = descripcionArea;
    }

    public PersonalDto() {
    }

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
}
