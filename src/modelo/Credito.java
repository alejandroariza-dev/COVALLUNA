package modelo;

import java.sql.Date;

public class Credito {

    private String numeroRadicado;
    private double valorSolicitado;
    private double valorAprobado;
    private int plazoMeses;
    private double tasaInteresMensual;
    private Date fechaAprobacion;
    private Date fechaPrimerVencimiento;
    private String estadoCredito;
    private String cedulaAsociado;
    private int idLinea;
    private String codigoAgencia;

    public Credito() {
    }

    public Credito(
            String numeroRadicado,
            double valorSolicitado,
            double valorAprobado,
            int plazoMeses,
            double tasaInteresMensual,
            Date fechaAprobacion,
            Date fechaPrimerVencimiento,
            String estadoCredito,
            String cedulaAsociado,
            int idLinea,
            String codigoAgencia
    ) {
        this.numeroRadicado = numeroRadicado;
        this.valorSolicitado = valorSolicitado;
        this.valorAprobado = valorAprobado;
        this.plazoMeses = plazoMeses;
        this.tasaInteresMensual = tasaInteresMensual;
        this.fechaAprobacion = fechaAprobacion;
        this.fechaPrimerVencimiento = fechaPrimerVencimiento;
        this.estadoCredito = estadoCredito;
        this.cedulaAsociado = cedulaAsociado;
        this.idLinea = idLinea;
        this.codigoAgencia = codigoAgencia;
    }

    public String getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(
            String numeroRadicado
    ) {
        this.numeroRadicado = numeroRadicado;
    }

    public double getValorSolicitado() {
        return valorSolicitado;
    }

    public void setValorSolicitado(
            double valorSolicitado
    ) {
        this.valorSolicitado = valorSolicitado;
    }

    public double getValorAprobado() {
        return valorAprobado;
    }

    public void setValorAprobado(
            double valorAprobado
    ) {
        this.valorAprobado = valorAprobado;
    }

    public int getPlazoMeses() {
        return plazoMeses;
    }

    public void setPlazoMeses(
            int plazoMeses
    ) {
        this.plazoMeses = plazoMeses;
    }

    public double getTasaInteresMensual() {
        return tasaInteresMensual;
    }

    public void setTasaInteresMensual(
            double tasaInteresMensual
    ) {
        this.tasaInteresMensual = tasaInteresMensual;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(
            Date fechaAprobacion
    ) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public Date getFechaPrimerVencimiento() {
        return fechaPrimerVencimiento;
    }

    public void setFechaPrimerVencimiento(
            Date fechaPrimerVencimiento
    ) {
        this.fechaPrimerVencimiento = fechaPrimerVencimiento;
    }

    public String getEstadoCredito() {
        return estadoCredito;
    }

    public void setEstadoCredito(
            String estadoCredito
    ) {
        this.estadoCredito = estadoCredito;
    }

    public String getCedulaAsociado() {
        return cedulaAsociado;
    }

    public void setCedulaAsociado(
            String cedulaAsociado
    ) {
        this.cedulaAsociado = cedulaAsociado;
    }

    public int getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(
            int idLinea
    ) {
        this.idLinea = idLinea;
    }

    public String getCodigoAgencia() {
        return codigoAgencia;
    }

    public void setCodigoAgencia(
            String codigoAgencia
    ) {
        this.codigoAgencia = codigoAgencia;
    }
}