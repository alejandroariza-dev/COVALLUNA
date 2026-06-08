package modelo;

import java.sql.Timestamp;

public class SolicitudActualizacion {

    private int idSolicitud;
    private String cedulaAsociado;
    private String nuevoTelefono;
    private String nuevoCorreo;
    private String nuevaDireccion;
    private String estado;
    private Timestamp fechaSolicitud;

    public SolicitudActualizacion() {
    }

    public SolicitudActualizacion(
            int idSolicitud,
            String cedulaAsociado,
            String nuevoTelefono,
            String nuevoCorreo,
            String nuevaDireccion,
            String estado,
            Timestamp fechaSolicitud
    ) {
        this.idSolicitud = idSolicitud;
        this.cedulaAsociado = cedulaAsociado;
        this.nuevoTelefono = nuevoTelefono;
        this.nuevoCorreo = nuevoCorreo;
        this.nuevaDireccion = nuevaDireccion;
        this.estado = estado;
        this.fechaSolicitud = fechaSolicitud;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(
            int idSolicitud
    ) {
        this.idSolicitud = idSolicitud;
    }

    public String getCedulaAsociado() {
        return cedulaAsociado;
    }

    public void setCedulaAsociado(
            String cedulaAsociado
    ) {
        this.cedulaAsociado = cedulaAsociado;
    }

    public String getNuevoTelefono() {
        return nuevoTelefono;
    }

    public void setNuevoTelefono(
            String nuevoTelefono
    ) {
        this.nuevoTelefono = nuevoTelefono;
    }

    public String getNuevoCorreo() {
        return nuevoCorreo;
    }

    public void setNuevoCorreo(
            String nuevoCorreo
    ) {
        this.nuevoCorreo = nuevoCorreo;
    }

    public String getNuevaDireccion() {
        return nuevaDireccion;
    }

    public void setNuevaDireccion(
            String nuevaDireccion
    ) {
        this.nuevaDireccion = nuevaDireccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(
            String estado
    ) {
        this.estado = estado;
    }

    public Timestamp getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(
            Timestamp fechaSolicitud
    ) {
        this.fechaSolicitud = fechaSolicitud;
    }
}