package modelo;

import java.sql.Timestamp;

public class Movimiento {

    private String numeroTransaccion;
    private String tipoMovimiento;
    private double valor;
    private Timestamp fechaHora;
    private String canal;
    private String numeroCuenta;

    public Movimiento() {
    }

    public Movimiento(
            String numeroTransaccion,
            String tipoMovimiento,
            double valor,
            Timestamp fechaHora,
            String canal,
            String numeroCuenta
    ) {
        this.numeroTransaccion = numeroTransaccion;
        this.tipoMovimiento = tipoMovimiento;
        this.valor = valor;
        this.fechaHora = fechaHora;
        this.canal = canal;
        this.numeroCuenta = numeroCuenta;
    }

    public String getNumeroTransaccion() {
        return numeroTransaccion;
    }

    public void setNumeroTransaccion(
            String numeroTransaccion
    ) {
        this.numeroTransaccion = numeroTransaccion;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(
            String tipoMovimiento
    ) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(
            double valor
    ) {
        this.valor = valor;
    }

    public Timestamp getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(
            Timestamp fechaHora
    ) {
        this.fechaHora = fechaHora;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(
            String canal
    ) {
        this.canal = canal;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(
            String numeroCuenta
    ) {
        this.numeroCuenta = numeroCuenta;
    }
}