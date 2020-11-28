package es.studium.Damas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ControladorJugar implements WindowListener, ActionListener
{
	VistaJugar vj /*= null*/ ;
	Modelo modelo /*= null*/;
	
	public ControladorJugar(VistaJugar vj, Modelo modelo)
	{
		/*this.vj = vj;*/
		/*this.modelo = modelo;*/
		vj= new VistaJugar();
		
		/*for(int i=0; i<vj.tablero.length; i++)
		{
			for(int j=0; j<vj.tablero[i].length; i++)
			{
				vj.tablero[i][j].addActionListener((ActionListener)this);
			}
		}*/
		modelo = new Modelo();
		
		
		vj.addWindowListener(this);
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println(" click " + e.getSource());
		
	}

	@Override
	public void windowActivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		
		vj.setVisible(true);
		//vj.dispose();
		
	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
}
