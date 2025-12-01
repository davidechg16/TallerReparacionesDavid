package vista;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import controlador.ControladorTaller;
import entidades.Cliente;
import entidades.Reparacion;
import entidades.Usuario;
import entidades.Vehiculo;

public class VistaTaller {
	private Scanner sc;
	private ControladorTaller controlador;

	public VistaTaller() {
		sc = new Scanner(System.in);
		controlador = ControladorTaller.getInstance();
	}

	public static void main(String[] args) {
		VistaTaller v = new VistaTaller();
		v.inicio();
	}

	public void inicio() { 
		boolean cerrar = false;
		do {
			System.out.println();
			System.out.println("TALLER DE REPARACIONES");
			mostrarCabeceraSesion();
			String rol = controlador.getRolActual().toUpperCase();
			switch (rol) {
			case "INVITADO":
				cerrar = menuInvitado();
				break;
			case "MECANICO":
				menuMecanico();
				break;
			case "ADMINISTRADOR":
				menuAdmin();
				break;
			default:
				System.out.println("Usuario inválido");
			}
		}while(!cerrar);
		System.out.println("Cerrando programa...");
		sc.close();
	}

	private void mostrarCabeceraSesion() {
		Usuario u = controlador.getUsuarioActual();

		if (u == null) {
			System.out.println(" Usuario actual: INVITADO");
		} else {
			System.out.println("Nombre del usuario actual: " + u.getNombreUsuario() + " (" + u.getRol() + ")");
		}
		System.out.println("");
	}

	private boolean menuInvitado() {
		boolean cerrar = false;
		System.out.println("MENÚ INVITADO");
		System.out.println("1. Ver reparaciones finalizadas");
		System.out.println("2. Iniciar sesión (usuario/administrador)");
		System.out.println("0. Salir");
		System.out.print("Elija una opción: ");
		int opcion = sc.nextInt();

		switch (opcion) {
		case 1:
			verReparacionesFinalizadas();
			break;
		case 2:
			hacerLogin();
			break;
		case 0:
			cerrar = true;
			break;
		default:
			System.out.println("Opcion inválida");
		}
		return cerrar;
	}

	private void menuMecanico() {
		System.out.println("\n MENU MECANICO");
		System.out.println("1. Registrar reparacion");
		System.out.println("2. Cambiar estado de una reparación");
		System.out.println("3. Ver reparaciones finalizadas");
		System.out.println("4. Cerrar sesion");
		System.out.println("0. Salir");
		System.out.print("Elija una opción: ");
		int opcion = sc.nextInt();

		switch (opcion) {
		case 1:
			registrarReparacion();
			break;
		case 2:
			cambiarEstadoReparacion();
			break;
		case 3:
			verReparacionesFinalizadas();
			break;
		case 4: 
			controlador.logout();
			System.out.println("Sesion cerrada");
			break;
		default:
			System.out.println("Opcion inválida");
		}
	}

	private void menuAdmin() {
		System.out.println("\nMENU ADMINISTRADOR");
		System.out.println("1. Registrar reparacion");
		System.out.println("2. Cambiar estado de una reparación");
		System.out.println("3. Ver reparaciones finalizadas");
		System.out.println("4. Gestion de clientes");
		System.out.println("5. Gestion de vehiculos");
		System.out.println("6. Gestion de usuarios");
		System.out.println("7. Cerrar sesion");
		System.out.print("Elija una opción: ");

		int opcion = sc.nextInt();

		switch (opcion) {
		case 1:
			registrarReparacion();
			break;
		case 2:
			cambiarEstadoReparacion();
			break;
		case 3:
			verReparacionesFinalizadas();
			break;
		case 4:
			menuClientes();
			break;
		case 5:
			menuVehiculos();
			break;
		case 6:
			menuUsuarios();
			break;
		case 7: 
			controlador.logout();
			System.out.println("Sesion cerrada");
			break;
		default:
			System.out.println("Opcion inválida");
		}
	}

	private void verReparacionesFinalizadas() {
		ArrayList<Reparacion> lista = controlador.obtenerReparacionesFinalizadas();
		System.out.println("REPARACIONES FINALIZADAS");
		if (lista.isEmpty()) {
			System.out.println("No existen reparaciones finalizadas");
			return;
		}

		for (int i = 0; i < lista.size(); i++) {
			Reparacion r = lista.get(i);
			System.out.println("ID: " + r.getIdReparacion());
			System.out.println("Descripcion: " + r.getDescripcion());
			System.out.println("Fecha: " + r.getFechaEntrada());
			System.out.println("Coste: " + r.getCosteEstimado() + "€");
			System.out.println("Estado: " + r.getEstado());
		}
	}

	private void hacerLogin() {
		sc.nextLine();
		System.out.print("Nombre de usuario: ");
		String nombreUsuario = sc.nextLine();
		
		System.out.print("Contraseña: ");
		String password = sc.nextLine();

		if (controlador.login(nombreUsuario, password)) {
			System.out.println("Login correcto");
		} else {
			System.out.println("Credenciales inválidas");
		}
	}

	private void registrarReparacion() {
		System.out.println("Registrar nueva reparación: ");

		System.out.println("Descripción: ");
		String descripcion = sc.nextLine();

		System.out.println("Coste estimado: ");
		double coste = Double.parseDouble(sc.nextLine());

		Reparacion r = new Reparacion(descripcion, LocalDate.now(), coste, "pendiente");

		if (controlador.registrarReparacion(r)) {
			System.out.println("Reparacion registrada.");
		} else {
			System.out.println("Error al registrar");
		}
	}

	private void cambiarEstadoReparacion() {
		
		System.out.print("Introduce ID reparacion: ");
		int id = sc.nextInt();
		
		Reparacion r = controlador.buscarReparacionPorIdVehiculo(id);

		if (r == null) {
			System.out.println("No existe ninguna reparacion asociada a ese vehiculo");
			return;
		}

		System.out.println("Reparación encontrada:");
		System.out.println("ID reparación: " + r.getIdReparacion());
		System.out.println("Descripción: " + r.getDescripcion());
		System.out.println("Estado actual: " + r.getEstado());
		System.out.println();

		System.out.println("Introduce el nuevo estado: ");
		String nuevoEstado = sc.nextLine();
		if (controlador.cambiarEstadoReparacion(r, nuevoEstado)) {
			System.out.println("Estado actualizado correctamente");
		} else {
			System.out.println("Error al actualizar la reparacion");
		}
	}

	private void menuClientes() {
		System.out.println("\nGESTION DE CLIENTES");
		System.out.println("1. Ver todos los clientes");
		System.out.println("2. Buscar cliente por DNI");
		System.out.println("3. Crear cliente");
		System.out.println("4. Actualizar cliente");
		System.out.println("5. Borrar cliente");
		
		System.out.print("Elija una opción: ");
		int opcion = sc.nextInt();
		switch (opcion) {
		
	           	case 1: verClientes();
	           			break;
	           	case 2: buscarClientePorDni();
	           			break;
	            case 3: crearCliente();
	            		 break;
	            case 4: actualizarCliente();
	            		break;
	            case 5: borrarCliente();
	            		break;
	            default: System.out.println("Opcion invalida");
	        }
		}
	
	    private void verClientes() {
	        ArrayList<Cliente> lista = controlador.obtenerClientes();

	        if (lista.isEmpty()) {
	            System.out.println("No hay clientes");
	            return;
	        }

	        for (Cliente c : lista) {
	            System.out.println(c.getNombre() + "-" + c.getDni() + "-" + c.getEmail()
	                    + "-" + c.getTelefono());
	        }
	    }

	    private void buscarClientePorDni() {
	        System.out.print("DNI: ");
	        String dni = sc.nextLine();

	        Cliente c = controlador.buscarClientePorDni(dni);

	        if (c == null) {
	            System.out.println("No existe cliente con ese DNI");
	        } else {
	            System.out.println("Cliente encontrado: " + c.getNombre() + "-" + c.getEmail());
	        }
	    }

	    private void crearCliente() {
	        System.out.print("Nombre: ");
	        String nombre = sc.nextLine();
	        System.out.print("Email: ");
	        String email = sc.nextLine();
	        System.out.print("DNI: ");
	        String dni = sc.nextLine();
	        System.out.print("Teléfono: ");
	        String tel = sc.nextLine();

	        Cliente c = new Cliente(nombre, email, dni, tel);

	        if (controlador.crearCliente(c)) {
	            System.out.println("Cliente creado correctamente.");
	        } else {
	            System.out.println("Error al crear el cliente.");
	        }
	    }

	    private void actualizarCliente() {
	        System.out.print("DNI del cliente a actualizar: ");
	        String dni = sc.nextLine();

	        Cliente c = controlador.buscarClientePorDni(dni);

	        if (c == null) {
	            System.out.println("No existe cliente con ese DNI");
	            return;
	        }
	        
	        System.out.print("Nuevo email: ");
	        c.setEmail(sc.nextLine());
	        System.out.print("Nuevo teléfono: ");
	        c.setTelefono(sc.nextLine());

	        if (controlador.actualizarCliente(c)) {
	            System.out.println("Cliente actualizado");
	        } else {
	            System.out.println("Error al actualizar el cliente");
	        }
	    }

	    private void borrarCliente() {
	        System.out.print("DNI del cliente a borrar: ");
	        String dni = sc.nextLine();

	        Cliente c = controlador.buscarClientePorDni(dni);

	        if (c == null) {
	            System.out.println("No existe cliente con ese DNI");
	            return;
	        }

	        controlador.borrarCliente(c);
	        System.out.println("Cliente borrado");
	    }
	    
	    
	    private void menuVehiculos() {
	    	System.out.println("\nGESTION DE VEHICULOS");
	    	System.out.println("1. Ver todos los vehiculos");
	    	System.out.println("2. Buscar vehiculo por matricula");
	    	System.out.println("3. Crear vehiculo");
	    	System.out.println("4. Borrar vehiculo");
	    	System.out.print("Elija una opción: ");
			int opcion = sc.nextInt();

	        switch (opcion) {
	       
	            case 1: verVehiculos();
	            break;
	            case 2: buscarVehiculoPorMatricula();
	            break;
	            case 3: crearVehiculo();
	            break;
	            case 4: borrarVehiculo();
	            break;
	            default: System.out.println("Opción invalida");
	        }
	    }
	    
	    private void verVehiculos() {
	        ArrayList<Vehiculo> lista = controlador.obtenerVehiculos();

	        if (lista.isEmpty()) {
	            System.out.println("No hay vehículos");
	            return;
	        }

	        for (Vehiculo v : lista) {
	            System.out.println(v.getMatricula() + "-" + v.getMarca() + "-" + v.getModelo());
	        }
	    }

	    private void buscarVehiculoPorMatricula() {
	        System.out.print("Matricula: ");
	        String matricula = sc.nextLine();

	        Vehiculo v = controlador.buscarVehiculoPorMatricula(matricula);

	        if (v == null) {
	            System.out.println("No existe vehículo con esa matrícula");
	        } else {
	            System.out.println("Vehículo: " + v.getMarca() + " " + v.getModelo());
	        }
	    }

	    private void crearVehiculo() {
	        System.out.print("Matrícula: ");
	        String matricula = sc.nextLine();
	        System.out.print("Marca: ");
	        String marca = sc.nextLine();
	        System.out.print("Modelo: ");
	        String modelo = sc.nextLine();

	        Vehiculo v = new Vehiculo(matricula, marca, modelo);

	        if (controlador.crearVehiculo(v)) {
	            System.out.println("Vehiculo creado correctamente");
	        } else {
	            System.out.println("Error al crear el vehiculo");
	        }
	    }

	    private void borrarVehiculo() {
	        System.out.print("Matrícula del vehiculo a borrar: ");
	        String matricula = sc.nextLine();

	        Vehiculo v = controlador.buscarVehiculoPorMatricula(matricula);

	        if (v == null) {
	            System.out.println("No existe vehiculo con esa matricula");
	            return;
	        }

	        if (controlador.borrarVehiculo(v)) {
	            System.out.println("Vehiculo borrado");
	        } else {
	            System.out.println("Error al borrar el vehiculo");
	        }
	    }
	    
	    private void menuUsuarios() {
	        System.out.println("\nGESTIÓN DE USUARIOS");
	        System.out.println("1. Ver todos los usuarios");
	        System.out.println("2. Crear usuario");
	        System.out.println("3. Borrar usuario");
	        System.out.print("Elija una opción: ");
			int opcion = sc.nextInt();

	        switch (opcion) {
	            case 1: verUsuarios();
	            break;
	            case 2: crearUsuario();
	            break;
	            case 3: borrarUsuario();
	            break;
	            default: System.out.println("Opción invalida");
	        }
	    }
	    
	    //Estos metodos de Usuario los implemente con IA

	    private void verUsuarios() {
	        ArrayList<Usuario> lista = controlador.obtenerUsuarios();

	        if (lista.isEmpty()) {
	            System.out.println("No hay usuarios");
	            return;
	        }

	        for (Usuario u : lista) {
	            System.out.println(u.getNombreUsuario() + "-" + u.getRol() + "-" + u.getDni());
	        }
	    }

	    private void crearUsuario() {
	        System.out.print("Nombre de usuario: ");
	        String nombre = sc.nextLine();
	        System.out.print("Contraseña: ");
	        String pass = sc.nextLine();
	        System.out.print("Rol (mecanico/administrador): ");
	        String rol = sc.nextLine().toUpperCase();
	        System.out.print("DNI: ");
	        String dni = sc.nextLine();

	        Usuario u = new Usuario(nombre, pass, rol, dni);

	        if (controlador.crearUsuario(u)) {
	            System.out.println("Usuario creado correctamente");
	        } else {
	            System.out.println("Error al crear el usuario");
	        }
	    }

	    private void borrarUsuario() {
	        System.out.print("Nombre de usuario a borrar: ");
	        String nombre = sc.nextLine();

	        Usuario u = controlador.buscarUsuarioPorNombre(nombre);

	        if (u == null) {
	            System.out.println("No existe usuario con ese nombre");
	            return;
	        }

	        if (controlador.borrarUsuario(u)) {
	            System.out.println("Usuario borrado");
	        } else {
	            System.out.println("Error al borrar el usuario");
	        }
	    }
}
