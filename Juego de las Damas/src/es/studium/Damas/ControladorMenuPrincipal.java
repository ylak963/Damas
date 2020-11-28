package es.studium.Damas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ControladorMenuPrincipal implements WindowListener, ActionListener
{
	VistaMenuPrincipal vmp = null;
	Modelo modelo=null;

	public ControladorMenuPrincipal(VistaMenuPrincipal vmp, Modelo modelo)
	{
		this.vmp = vmp;
		this.modelo = modelo;
		
		
		vmp.addWindowListener(this);
			
		vmp.btnJugar.addActionListener((ActionListener)this);
		vmp.btnSalir.addActionListener((ActionListener)this);
	}

	public void actionPerformed(ActionEvent arg0)
	{		
		if(arg0.getSource().equals(vmp.btnJugar)) 
		{
				new VistaJugar();
		}
		else if(arg0.getSource().equals(vmp.btnSalir)) 
		{
			
		}
		else if(arg0.getSource().equals(vmp.btnSalir)) 
		{

		}
	}	
	public void windowActivated(WindowEvent arg0){}
	public void windowClosed(WindowEvent arg0){}
	public void windowClosing(WindowEvent arg0)
	{
		System.exit(0);		
	}
	public void windowDeactivated(WindowEvent arg0){}
	public void windowDeiconified(WindowEvent arg0){}
	public void windowIconified(WindowEvent arg0){}
	public void windowOpened(WindowEvent arg0){}
	
	/*public static void main(String[] args)
	{
		new VistaMenuPrincipal();
	}*/

}
