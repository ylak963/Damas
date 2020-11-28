package es.studium.Damas;

public class ClasePrincipal
{
	public static void main(String[] args)
	{
		VistaMenuPrincipal Vmp = new VistaMenuPrincipal();
		
		Modelo Modelo = new Modelo();
		
		new ControladorMenuPrincipal(Vmp, Modelo);
	}
}
