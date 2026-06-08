package modelo;

import java.sql.Date;

public class Garantia {

    private String numeroRadicado;
    private String cedulaTitular;
    private String cedulaCodeudor;
    private Date fechaFirmaPagare;
    private double valorAprobado;
    private String estadoCredito;

    public Garantia() {
    }

    public String getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(
            String numeroRadicado
    ) {
        this.numeroRadicado = numeroRadicado;
    }

    public String getCedulaTitular() {
        return cedulaTitular;
    }

    public void setCedulaTitular(
            String cedulaTitular
    ) {
        this.cedulaTitular = cedulaTitular;
    }

    public String getCedulaCodeudor() {
        return cedulaCodeudor;
    }

    public void setCedulaCodeudor(
            String cedulaCodeudor
    ) {
        this.cedulaCodeudor = cedulaCodeudor;
    }

    public Date getFechaFirmaPagare() {
        return fechaFirmaPagare;
    }

    public void setFechaFirmaPagare(
            Date fechaFirmaPagare
    ) {
        this.fechaFirmaPagare = fechaFirmaPagare;
    }

    public double getValorAprobado() {
        return valorAprobado;
    }

    public void setValorAprobado(
            double valorAprobado
    ) {
        this.valorAprobado = valorAprobado;
    }

    public String getEstadoCredito() {
        return estadoCredito;
    }

    public void setEstadoCredito(
            String estadoCredito
    ) {
        this.estadoCredito = estadoCredito;
    }
}