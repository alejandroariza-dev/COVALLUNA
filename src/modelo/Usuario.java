package modelo;

public class Usuario {

    private int idUsuario;
    private String username;
    private String password;
    private String rol;
    private String cedula;

    public Usuario() {
    }

    public Usuario(
            int idUsuario,
            String username,
            String password,
            String rol,
            String cedula
    ) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.cedula = cedula;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(
            int idUsuario
    ) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(
            String username
    ) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(
            String password
    ) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(
            String rol
    ) {
        this.rol = rol;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(
            String cedula
    ) {
        this.cedula = cedula;
    }
}