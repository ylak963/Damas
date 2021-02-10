package es.studium.Damas;

import java.io.IOException;

public class Ayuda
{
	public Ayuda() 
	{
		try 
		{
			Runtime.getRuntime().exec("hh.exe Ayuda//Manual de uso Damas 2021.chm");
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
	}
}
