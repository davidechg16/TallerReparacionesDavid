package entidades;

import java.time.LocalDate;

public class Reparacion {
	private int idReparacion;
	private static int id = 0;
	private String descripcion;
	private LocalDate fechaEntrada;
	private double costeEstimado;
	private String estado;
	
	
	public Reparacion(String descripcion, LocalDate fechaEntrada, double costeEstimado, String estado) {
		this.idReparacion = id++;
		this.descripcion = descripcion;
		this.fechaEntrada = fechaEntrada;
		this.costeEstimado = costeEstimado;
		this.estado = estado;
	}
	
	
	public Reparacion(int idReparacion, String descripcion, LocalDate fechaEntrada, double costeEstimado,
			String estado) {
		this.idReparacion = idReparacion;
		this.descripcion = descripcion;
		this.fechaEntrada = fechaEntrada;
		this.costeEstimado = costeEstimado;
		this.estado = estado;
	}



	public int getIdReparacion() {
		return idReparacion;
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getCosteEstimado() {
		return costeEstimado;
	}

	public void setCosteEstimado(double costeEstimado) {
		this.costeEstimado = costeEstimado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}	
}
