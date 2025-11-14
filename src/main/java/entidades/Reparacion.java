package entidades;

import java.time.LocalDate;

public class Reparacion {
	private int idReparacion;
	private LocalDate fechaInicio;
	private String descripcion;
	private int costeEstimado;
	private String estado;
	
	public Reparacion(LocalDate fechaInicio, String descripcion, int costeEstimado, String estado) {
		this.fechaInicio = fechaInicio;
		this.descripcion = descripcion;
		this.costeEstimado = costeEstimado;
		this.estado = estado;
	}

	public int getIdReparacion() {
		return idReparacion;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCosteEstimado() {
		return costeEstimado;
	}

	public void setCosteEstimado(int costeEstimado) {
		this.costeEstimado = costeEstimado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
