package es.studium.Damas;

public class Fichas 
{
	private char color;
	private boolean esDama;
	
	public Fichas(char color, boolean esDama)
	{
		this.color = color;
		this.esDama = esDama;
	}

	public char getColor()
	{
		return color;
	}

	public void setColor(char color)
	{
		this.color = color;
	}

	public boolean isEsDama()
	{
		return esDama;
	}

	public void setEsDama(boolean esDama)
	{
		this.esDama = esDama;
	}

	@Override
	public String toString() //Imprime los valores
	{
		return "Fichas [color=" + color + ", esDama=" + esDama + "]";
	}
	
	
}
	