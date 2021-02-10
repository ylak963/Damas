package es.studium.Damas;

public class Record
{
	private int id;
	private String jugador;
	private int puntuacion;
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getJugador()
	{
		return jugador;
	}

	public void setJugador(String jugador)
	{
		this.jugador = jugador;
	}

	public int getPuntuacion()
	{
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion)
	{
		this.puntuacion = puntuacion;
	}

	public Record(int id, String jugador, int puntuacion)
	{
		super();
		this.id = id;
		this.jugador = jugador;
		this.puntuacion = puntuacion;
	}
}
