package entidades;

public class Cliente {
	private int idCliente;
	private String nombre;
	private String email;
	private String dni;
	private String telefono;
	
	
	
	public Cliente(String nombre, String email, String dni, String telefono) {
		this.nombre = nombre;
		this.email = email;
		this.dni = dni;
		this.telefono = telefono;
	}
	
	public int getidCliente() {
		return idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
}
