package es.studium.Damas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;

public class ControladorMejoresPartidas implements WindowListener,ActionListener
{
	VistaMejoresPartidas vmp;
	VistaMenuPrincipal vmpr;
	Modelo modelo;
	Connection con;
	
	public  ControladorMejoresPartidas(VistaMejoresPartidas vmp, Modelo modelo)
	{
		this.vmp = vmp;
		this.modelo = modelo;
		vmp.addWindowListener(this);
		//vmpr.addWindowListener(this);
		vmp.btnVolver.addActionListener((ActionListener)this);
		con = modelo.connectDB();
		vmp.pintarRecords(modelo.consultarRecord(con));		
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(arg0.getSource().equals(vmp.btnVolver)) 
		{				
				vmp.dispose();
		}
	}
	@Override
	public void windowActivated(WindowEvent arg0){}
	@Override
	public void windowClosed(WindowEvent arg0){}
	@Override
	public void windowClosing(WindowEvent arg0)
	{
		if(vmp.hasFocus())
		{
			vmp.setVisible(false);
		}
		else
		{
			System.exit(0);
		}
	}
	@Override
	public void windowDeactivated(WindowEvent arg0){}
	@Override
	public void windowDeiconified(WindowEvent arg0){}
	@Override
	public void windowIconified(WindowEvent arg0){}
	@Override
	public void windowOpened(WindowEvent arg0){}
}
