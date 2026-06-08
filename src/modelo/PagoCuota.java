package modelo;

import java.sql.Date;

public class PagoCuota {

    private int idPago;
    private int numeroCuota;
    private Date fechaPago;
    private double valorPagado;
    private String estadoPago;
    private String numeroRadicado;

    public PagoCuota() {
    }

    public PagoCuota(
            int idPago,
            int numeroCuota,
            Date fechaPago,
            double valorPagado,
            String estadoPago,
            String numeroRadicado
    ) {
        this.idPago = idPago;
        this.numeroCuota = numeroCuota;
        this.fechaPago = fechaPago;
        this.valorPagado = valorPagado;
        this.estadoPago = estadoPago;
        this.numeroRadicado = numeroRadicado;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(
            int idPago
    ) {
        this.idPago = idPago;
    }

    public int getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(
            int numeroCuota
    ) {
        this.numeroCuota = numeroCuota;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(
            Date fechaPago
    ) {
        this.fechaPago = fechaPago;
    }

    public double getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(
            double valorPagado
    ) {
        this.valorPagado = valorPagado;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(
            String estadoPago
    ) {
        this.estadoPago = estadoPago;
    }

    public String getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(
            String numeroRadicado
    ) {
        this.numeroRadicado = numeroRadicado;
    }
}