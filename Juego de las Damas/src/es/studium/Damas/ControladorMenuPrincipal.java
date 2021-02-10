package es.studium.Damas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
//Import de clases

public class ControladorMenuPrincipal implements WindowListener, ActionListener
{
	VistaMenuPrincipal vmp = null;
	VistaMejoresPartidas vrank = null;
	Modelo modelo = new Modelo();
	
	public ControladorMenuPrincipal(VistaMenuPrincipal vmp, Modelo modelo)
	{		
		this.vmp = vmp;
		this.modelo = modelo;
		
		vmp.addWindowListener(this);
		
		vmp.btnJugar.addActionListener((ActionListener)this);
		vmp.btnMejoresPartidas.addActionListener((ActionListener)this);
		vmp.btnAyuda.addActionListener((ActionListener)this);
		vmp.btnSalir.addActionListener((ActionListener)this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{		
		if(arg0.getSource().equals(vmp.btnJugar)) 
		{			
			VistaJugar vj = new VistaJugar();
			new ControladorJugar(vj, modelo);
			//vmp.setVisible();
		}
		else if(arg0.getSource().equals(vmp.btnMejoresPartidas)) 
		{
			VistaMejoresPartidas vmp = new VistaMejoresPartidas();
			new ControladorMejoresPartidas(vmp,modelo);
			//vmp.setVisible(false);
		}
		else if(arg0.getSource().equals(vmp.btnAyuda)) 
		{			
			new Ayuda();
			
		}
		else if(arg0.getSource().equals(vmp.btnSalir)) 
		{
			System.exit(0);
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
}
