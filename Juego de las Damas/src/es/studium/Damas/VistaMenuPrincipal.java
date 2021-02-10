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

	// Creación de componentes

	JButton btnJugar = new JButton("Jugar");
	JButton btnMejoresPartidas = new JButton("Mejores partidas!");
	JButton btnAyuda = new JButton("Ayuda");
	JButton btnSalir = new JButton("Salir");

	// Se declara el objeto image
	Image imagenDamasMenu;
	// Declarar el objeto Toolkit para el mensaje de imágenes
	Toolkit herramienta;

	public VistaMenuPrincipal()
	{
		setTitle("Damas");
		setLayout(null);
		setLocation(700, 300);
		setSize(500, 445);

		// Indicamos la posición del botón
		btnJugar.setBounds(150, 80, 200, 50);
		add(btnJugar);

		btnMejoresPartidas.setBounds(150, 160, 200, 50);
		add(btnMejoresPartidas);

		btnAyuda.setBounds(150, 240, 200, 50);
		add(btnAyuda);

		btnSalir.setBounds(150, 320, 200, 50);
		add(btnSalir);

		setResizable(true);

		setVisible(true);
		// Establecer el método de trabajo con imagenes
		herramienta = getToolkit();
		// Especificamos la ruta de la imagen
		imagenDamasMenu = herramienta.getImage("Damas_MenuPrincipal.jpg");

	}
	public void paint(Graphics g)
	{
		// Dibujar la imagen
		g.drawImage(imagenDamasMenu, 4, 23, this);
	}
}
