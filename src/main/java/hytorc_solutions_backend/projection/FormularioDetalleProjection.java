package hytorc_solutions_backend.projection;

public interface FormularioDetalleProjection {

    Integer getIdRegCab();

    String getNombreFormulario();
    String getCodigoFormulario();
    String getVersionFormulario();
    String getFechaLlenado();

    String getNombreCliente();
    String getEmpresaCliente();
    String getTelefonoCliente();
    String getCorreoCliente();
    String getFechaVenta();
    String getProductoServicio();

    String getFechaContacto();
    String getMedioContacto();
    String getMotivoContacto();
    String getAtendidoPor();
    String getObservacionesCliente();
    String getAccionInmediata();

    String getFechaSeguimiento();
    String getAspectosVerificados();
    String getHallazgos();
    String getAccionTomada();
    String getObservacionesSeguimiento();
    String getFechaCierre();

    String getNivelSatisfaccion();
    String getObservacionesSatisfaccion();
    String getRecomendaciones();
    String getFechaCierreFinal();

    String getNombreResponsable();
    String getCargoResponsable();
    String getFirmaResponsable();
    String getFechaFirma();
}