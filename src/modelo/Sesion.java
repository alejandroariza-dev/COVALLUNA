package modelo;

public class Sesion {

    private static Usuario usuarioActivo;

    public static Usuario getUsuarioActivo() {
        return usuarioActivo;
    }

    public static void setUsuarioActivo(
            Usuario usuario
    ) {
        usuarioActivo = usuario;
    }

    public static String getCedulaAsociadoActivo() {

        if (
            usuarioActivo != null
            &&
            usuarioActivo.getRol()
                    .equals("asociado")
        ) {
            return usuarioActivo.getCedula();
        }

        return null;
    }

    public static void cerrarSesion() {
        usuarioActivo = null;
    }
}