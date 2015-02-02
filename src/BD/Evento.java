package BD;

public class Evento {

	
	String titulo;
	String descripcion;
	String fecha;
	String hora;
	public Evento(String titulo, String descripcion, String fecha, String hora) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.hora = hora;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	@Override
	public String toString() {
		

		return titulo;
	}
	
	
}
