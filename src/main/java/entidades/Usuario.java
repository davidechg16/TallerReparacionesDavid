package entidades;

public class Usuario {
	private int idUsuario;
	private String nombreUsuario;
	private String password;
	private String rol;
	private String dni;
	
	public Usuario(String nombreUsuario, String password, String rol, String dni) {
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.rol = rol;
		this.dni = dni;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	@Override
	public String toString() {

		return "Usuario id " + getIdUsuario() + " nombre de usuario " + getNombreUsuario() + " rol " + getRol()  + " DNI " + getDni();
	}
	
}
