package Project.Wcode;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long idUsuarios;

    private String nombreUsuario;
    private long telefono;
    private String email;
    private String contrasena;

    public long getIdUsuarios() {
        return idUsuarios;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public long getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setIdUsuarios(Integer idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}