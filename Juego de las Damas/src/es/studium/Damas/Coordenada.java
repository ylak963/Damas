package es.studium.Damas;

public class Coordenada
{
	private int fila;
	private int columna;
	
	public Coordenada(int fila, int columna)
	{
		super();
		this.fila=fila;
		this.columna=columna;
	}

	public int getfila()
	{
		return fila;
	}

	public void setfila(int fila)
	{
		this.fila = fila;
	}

	public int getcolumna()
	{
		return columna;
	}

	public void setcolumna(int columna)
	{
		this.columna = columna;
	}

	@Override
	public String toString()
	{
		return "Coordenada [fila=" + fila + ", columna=" + columna + "]";
	}
}
