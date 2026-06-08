package modelo;

import java.math.BigDecimal;
import java.sql.Date;

public class Empleado {

    private String cedula;
    private String nombres;
    private String apellidos;
    private Date fechaIngreso;
    private BigDecimal salarioBase;
    private String correoCorporativo;
    private String estadoLaboral;
    private String codigoAgencia;
    private int idTipo;
    private String cedulaSupervisor;

    public Empleado() {
    }

    public Empleado(
            String cedula,
            String nombres,
            String apellidos,
            Date fechaIngreso,
            BigDecimal salarioBase,
            String correoCorporativo,
            String estadoLaboral,
            String codigoAgencia,
            int idTipo,
            String cedulaSupervisor
    ) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaIngreso = fechaIngreso;
        this.salarioBase = salarioBase;
        this.correoCorporativo = correoCorporativo;
        this.estadoLaboral = estadoLaboral;
        this.codigoAgencia = codigoAgencia;
        this.idTipo = idTipo;
        this.cedulaSupervisor = cedulaSupervisor;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public BigDecimal getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(BigDecimal salarioBase) {
        this.salarioBase = salarioBase;
    }

    public String getCorreoCorporativo() {
        return correoCorporativo;
    }

    public void setCorreoCorporativo(String correoCorporativo) {
        this.correoCorporativo = correoCorporativo;
    }

    public String getEstadoLaboral() {
        return estadoLaboral;
    }

    public void setEstadoLaboral(String estadoLaboral) {
        this.estadoLaboral = estadoLaboral;
    }

    public String getCodigoAgencia() {
        return codigoAgencia;
    }

    public void setCodigoAgencia(String codigoAgencia) {
        this.codigoAgencia = codigoAgencia;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getCedulaSupervisor() {
        return cedulaSupervisor;
    }

    public void setCedulaSupervisor(String cedulaSupervisor) {
        this.cedulaSupervisor = cedulaSupervisor;
    }
}