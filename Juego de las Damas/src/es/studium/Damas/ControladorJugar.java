package es.studium.Damas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ControladorJugar implements WindowListener, ActionListener
{	
	VistaJugar vj = null;
	Modelo modelo = null;
		
	public ControladorJugar(VistaJugar vj, Modelo modelo)
	{
		this.vj = vj;
		this.modelo = modelo;
				
		vj.addWindowListener(this);
		vj.btnFinalizar.addActionListener((ActionListener)this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{				
		if(e.getSource().equals(vj.btnFinalizar))
		{
			if(vj.fichasComidasJugador1>vj.fichasComidasJugador2)
			{
				vj.guardarDatos(vj.nombreJugador1, vj.movimientosJugador1);
			}
			else
			{
				vj.guardarDatos(vj.nombreJugador2, vj.movimientosJugador2);
			}
			vj.acabarPartida();
		}
		
		//System.out.println(" click " + e.getSource());
	}
	@Override
	public void windowActivated(WindowEvent e){}
	@Override
	public void windowClosed(WindowEvent e){}
	@Override
	public void windowClosing(WindowEvent e)
	{		
		if(vj.hasFocus())
		{
			vj.setVisible(false);
		}
		else
		{
			System.exit(0);
		}		
	}
	@Override
	public void windowDeactivated(WindowEvent e){}
	@Override
	public void windowDeiconified(WindowEvent e){}
	@Override
	public void windowIconified(WindowEvent e){}
	@Override
	public void windowOpened(WindowEvent e){}	
}
