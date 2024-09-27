import java.util.HashSet;

public class Agenda {
	private HashSet<Persona> listado = new HashSet<>();
	
	public Agenda() {}
	
	public void add(Persona persona) {
		 listado.add(persona);
	}
	
	public HashSet<Persona> getAgenda() {
		return listado;
	}
	
}
