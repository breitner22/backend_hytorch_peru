package hytorc_solutions_backend.dto;

public class FormularioListadoDTO {

    private Integer idCab;
    private String fecha;
    private String estado;
    private String servicio;
    private String motivo;

    public FormularioListadoDTO() {
    }

       public FormularioListadoDTO(Integer idRegCab, String fecha, String estado, String servicio, String motivo) {
        this.idCab = idRegCab;
        this.fecha = fecha;
        this.estado = estado;
        this.servicio = servicio;
        this.motivo = motivo;
    }
    // GETTERS Y SETTERS

    public Integer getIdCab() {
        return idCab;
    }

    public void setIdCab(Integer idCab) {
        this.idCab = idCab;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}