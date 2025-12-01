package controlador;

import dao.MySQLDAOFactory;

import java.util.ArrayList;
import java.util.Collections;

import dao.DAOFactory;
import dao.interfaces.ClienteDAO;
import dao.interfaces.ReparacionDAO;
import dao.interfaces.UsuarioDAO;
import dao.interfaces.VehiculoDAO;
import entidades.Cliente;
import entidades.Reparacion;
import entidades.Usuario;
import entidades.Vehiculo;

public class ControladorTaller {
	
	private static ControladorTaller instance;
	
	public static ControladorTaller getInstance() {
		if(instance == null) {
			instance = new ControladorTaller();
		}
		return instance;
	}

	
	private DAOFactory daoFactory;
	private UsuarioDAO usuarioDAO;
	private ClienteDAO clienteDAO;
	private VehiculoDAO vehiculoDAO;
	private ReparacionDAO reparacionDAO;

	//SESION DEL USER
	private Usuario usuarioActual;
	
	private ControladorTaller() {
		
		daoFactory = new MySQLDAOFactory();
		
		//Obtenemos los DAOs
		usuarioDAO = daoFactory.getUsuarioDAO();
		clienteDAO = daoFactory.getClienteDAO();
		vehiculoDAO = daoFactory.getVehiculoDAO();
		reparacionDAO = daoFactory.getReparacionDAO();
	}
	
	//GESTION DE LA SESION
	
	public boolean login(String nombreUsuario, String password) {
		boolean ok = usuarioDAO.login(nombreUsuario,  password);
		
		if(ok) {
			usuarioActual = usuarioDAO.findByNombre(nombreUsuario);
		} else {
			usuarioActual = null;
		}
		
		return ok;
	}
	
	public void logout() {
		usuarioActual = null;
	}

	
	public Usuario getUsuarioActual() {
		return usuarioActual;
	}
	
	public String getRolActual() {
		if(usuarioActual == null) {
			return "INVITADO";
		}
		return usuarioActual.getRol();
	}
	
	//A esto me ayudo la IA
	public ArrayList<Usuario> obtenerUsuarios() {
	    return usuarioDAO.findall();
	}

	public Usuario buscarUsuarioPorNombre(String nombre) {
	    return usuarioDAO.findByNombre(nombre);
	}

	public boolean crearUsuario(Usuario u) {
	    return usuarioDAO.insert(u) == 1;
	}

	public boolean borrarUsuario(Usuario u) {
	    return usuarioDAO.delete(u) == 1;
	}
	
	
	//CU1:Reparaciones finalizadas
	
	public ArrayList<Reparacion> obtenerReparacionesFinalizadas(){
			
		return reparacionDAO.findByEstado("finalizada");
	}
	
	//CU3: Registrar reparacion
	
	public boolean registrarReparacion(Reparacion r) {
		return reparacionDAO.insert(r) == 1;
	}
	
	//CU4: Cambiar estado reparacion (mecanico)
	
	public boolean cambiarEstadoReparacion(Reparacion r, String estado) {
		r.setEstado(estado);
		return reparacionDAO.update(r) == 1;
	}
	
	//CU5: Gestion de clientes (admin)
	
	public ArrayList<Cliente> obtenerClientes(){
		return clienteDAO.findall();
	}
	
	public Cliente buscarClientePorDni(String dni) {
		return clienteDAO.findByDni(dni);
	}
	
	public boolean crearCliente(Cliente c) {
		return clienteDAO.insert(c)==1;
	}
	
	public boolean actualizarCliente(Cliente c) {
		return clienteDAO.update(c) == 1;
	}
	
	public void borrarCliente(Cliente c) {
		clienteDAO.delete(c);
	}
	
	//CU5: Gestion vehiculos(admin)
	
	public ArrayList<Vehiculo>obtenerVehiculos(){
		return vehiculoDAO.findall();
	}
	
	public Vehiculo buscarVehiculoPorMatricula(String matricula) {
		return vehiculoDAO.findByMatricula(matricula);
	}
	public Reparacion buscarReparacionPorIdVehiculo(int idVehiculo) {
	    return reparacionDAO.finbyIdVehiculo(idVehiculo);
	}
	
	public boolean crearVehiculo(Vehiculo v) {
		return vehiculoDAO.insert(v)== 1;
	}
	
	public boolean borrarVehiculo(Vehiculo v) {
		return vehiculoDAO.delete(v) == 1;
	}
	
	//CU6: Estadisticas (admin)
	
	public int contarReparacionesPorEstado (String estado) {
		return reparacionDAO.findByEstado(estado).size();
	}
	
	public double calcularCosteMedioReparaciones () {
		ArrayList<Reparacion> lista = reparacionDAO.findall();
		
		if(lista.isEmpty()) {
			return 0.0;
		}
		
		double suma = 0;
		for(Reparacion r : lista) {
			suma += r.getCosteEstimado();
		}
		
		return suma / lista.size();
	}
}
