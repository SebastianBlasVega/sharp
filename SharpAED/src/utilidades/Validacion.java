package utilidades;

import gui.Home;
import gui.Login;

public class Validacion {
	private static boolean validarUsuario(String usuario) {
		return usuario.length() > 0;
	}

	private static boolean validarContrasena(String contrasena) {
		return contrasena.length() > 0;
	}

	public static boolean validarCredenciales(String usuario, String contrasena, Login login) {
		if (validarUsuario(usuario) && validarContrasena(contrasena)) {
			if (usuario.equals("admin") && contrasena.equals("admin")) {
				System.out.println("Credenciales correctas");
				Home.abrirVentana();
				login.dispose();
				return true;
			} else if (usuario.equals("user") && contrasena.equals("user")) {
				System.out.println("Credenciales correctas");
				Home.abrirVentana();
				login.dispose();
				return true;
			} else {
				System.out.println("Credenciales incorrectas");
				return false;
			}
		} else {
			System.out.println("Credenciales incorrectas");
			return false;
		}
	}

	public static boolean validarPrecio(double precio) {
		return precio > 0;
	}

	public static boolean validarStock(int stock) {
		return stock > 0;
	}

	public static boolean validarNombre(String nombre) {
		return nombre.length() > 0;
	}

	public static boolean validarCodigo(String codigo) {
		return codigo.length() > 0;
	}

	public static boolean validarStockMinimo(int stockMinimo) {
		return stockMinimo > 0;
	}

	public static boolean validarStockMaximo(int stockMaximo) {
		return stockMaximo > 0;
	}

	public static boolean validarStockMinMax(int stockMinimo, int stockMaximo) {
		return stockMinimo < stockMaximo;
	}

	public static boolean validarStockActual(int stockActual, int stockMinimo, int stockMaximo) {
		return stockActual >= stockMinimo && stockActual <= stockMaximo;
	}
}