
public class Persona {
	// Objeto persona que contiene los datos de contacto
	private String nombre;
	private String telefono;
	private String mail;

	public Persona(String nombre, String telf, String mail) {
		this.nombre = nombre;
		this.telefono = telf;
		this.mail = mail;
	}
	
	public Persona(String nombre) {
		this.nombre = nombre;
	}
	
	public Persona() {} // La libreria Jackson requiere un constructor vacio

	// GETTERS

	public String getNombre() {
		return nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getMail() {
		return mail;
	}

	// SETTERS

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int setTelefono(String telefono) {

		// Verificaion telf.
		if (telefono.length() < 8) {
			return 0;
		}
		if (telefono.length() > 12) {
			return 0;
		}

		// Telf. valido
		this.telefono = telefono;
		return 1;
	}

	public int setMail(String mail) {

		// Verificacion mail
		if (!mail.contains("@")) {
			return 0;
		}
		if (!mail.contains(".")) {
			return 0;
		}
		if (mail.length() < 5) {
			return 0;
		}

		// mail valido
		this.mail = mail;
		return 1;
	}
}
