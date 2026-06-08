package modelo;

public class Beneficiario {

    private String numeroDocumento;
    private String nombreCompleto;
    private String parentesco;
    private double porcentajeParticipacion;
    private String telefono;
    private String cedulaAsociado;

    public Beneficiario() {
    }

    public Beneficiario(
            String numeroDocumento,
            String nombreCompleto,
            String parentesco,
            double porcentajeParticipacion,
            String telefono,
            String cedulaAsociado
    ) {
        this.numeroDocumento = numeroDocumento;
        this.nombreCompleto = nombreCompleto;
        this.parentesco = parentesco;
        this.porcentajeParticipacion = porcentajeParticipacion;
        this.telefono = telefono;
        this.cedulaAsociado = cedulaAsociado;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public double getPorcentajeParticipacion() {
        return porcentajeParticipacion;
    }

    public void setPorcentajeParticipacion(double porcentajeParticipacion) {
        this.porcentajeParticipacion = porcentajeParticipacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCedulaAsociado() {
        return cedulaAsociado;
    }

    public void setCedulaAsociado(String cedulaAsociado) {
        this.cedulaAsociado = cedulaAsociado;
    }
}