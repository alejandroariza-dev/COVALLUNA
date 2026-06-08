package modelo;

import java.sql.Date;

public class Agencia {

    private String codigoAgencia;
    private String nombre;
    private String direccion;
    private String municipio;
    private String telefono;
    private Date fechaApertura;

    public Agencia() {
    }

    public Agencia(
            String codigoAgencia,
            String nombre,
            String direccion,
            String municipio,
            String telefono,
            Date fechaApertura
    ) {
        this.codigoAgencia = codigoAgencia;
        this.nombre = nombre;
        this.direccion = direccion;
        this.municipio = municipio;
        this.telefono = telefono;
        this.fechaApertura = fechaApertura;
    }

    public String getCodigoAgencia() {
        return codigoAgencia;
    }

    public void setCodigoAgencia(String codigoAgencia) {
        this.codigoAgencia = codigoAgencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }
}