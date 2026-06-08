package modelo;

public class TipoEmpleado {

    private int idTipo;
    private String nombreTipo;

    public TipoEmpleado() {
    }

    public TipoEmpleado(
            int idTipo,
            String nombreTipo
    ) {
        this.idTipo = idTipo;
        this.nombreTipo = nombreTipo;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }
}