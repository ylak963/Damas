package es.studium.Damas;
//import java.awt.Button;
//import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;

public class VistaMenuPrincipal extends Frame 
{
	private static final long serialVersionUID = 1L;

	//Creaci�n de componentes

	JButton btnJugar = new JButton("Jugar");
	JButton btnMejoresPartidas = new JButton("Mejores partidas!");
	JButton btnAyuda = new JButton("Ayuda");
	JButton btnSalir = new JButton("Salir");

	//Se declara el objeto image
	Image imagenDamasMenu;
	//Declarar el objeto Toolkit para el menaje de im�genes
	Toolkit herramienta;

	public VistaMenuPrincipal()
	{
		setTitle("Damas");
		setLayout(null);
		setLocation(700,300);
		setSize(500,445);

		btnJugar.setBounds(150, 80, 200, 50);
		//btnJugar.addActionListener(this);
		add(btnJugar);

		btnMejoresPartidas.setBounds(150, 160, 200, 50);
		add(btnMejoresPartidas);

		btnAyuda.setBounds(150, 240, 200, 50);
		add(btnAyuda);

		btnSalir.setBounds(150, 320, 200, 50);
		add(btnSalir);
		
		setResizable(true);
		//addWindowListener(this);

		//Establecer el m�todo de trabajo con imagenes
		herramienta = getToolkit();
		//Especificamos la ruta de la imagen
		imagenDamasMenu = herramienta.getImage("Damas_MenuPrincipal.jpg");
		setVisible(true);
	}

	public void paint(Graphics g)
	{
		//Dibujar la imagen
		g.drawImage(imagenDamasMenu, 4, 23, this);
	}

	
	
}


