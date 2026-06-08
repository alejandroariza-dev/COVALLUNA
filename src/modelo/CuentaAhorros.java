package modelo;

import java.sql.Date;

public class CuentaAhorros {

    private String numeroCuenta;
    private Date fechaApertura;
    private String estado;
    private String cedulaAsociado;
    private String codigoAgencia;

    public CuentaAhorros() {
    }

    public CuentaAhorros(
            String numeroCuenta,
            Date fechaApertura,
            String estado,
            String cedulaAsociado,
            String codigoAgencia
    ) {
        this.numeroCuenta = numeroCuenta;
        this.fechaApertura = fechaApertura;
        this.estado = estado;
        this.cedulaAsociado = cedulaAsociado;
        this.codigoAgencia = codigoAgencia;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCedulaAsociado() {
        return cedulaAsociado;
    }

    public void setCedulaAsociado(String cedulaAsociado) {
        this.cedulaAsociado = cedulaAsociado;
    }

    public String getCodigoAgencia() {
        return codigoAgencia;
    }

    public void setCodigoAgencia(String codigoAgencia) {
        this.codigoAgencia = codigoAgencia;
    }
}