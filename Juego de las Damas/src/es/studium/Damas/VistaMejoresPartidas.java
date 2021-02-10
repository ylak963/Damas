package es.studium.Damas;

import java.awt.Button;
//import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
//import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JLabel;

public class VistaMejoresPartidas extends Frame
{
	Modelo modelo;
	Image imagenPunt;
	Toolkit herramienta;
	
	Button btnVolver = new Button("Volver");
	private static final long serialVersionUID = 1L;
	
	public VistaMejoresPartidas()
	{
		setTitle("Mejores Puntuaciones");
		setLayout(new GridLayout(0,4));
		setLocation(700,300);
		setSize(500,445);
		modelo = new Modelo();
		btnVolver.setBounds(150, 320, 200, 50);
		//setSize(300,300);
		setResizable(true);
		setLocationRelativeTo(null);
		//Establecer el método de trabajo con imagenes
		herramienta = getToolkit();
		//Especificamos la ruta de la imagen
		imagenPunt = herramienta.getImage("imagen_podium.jpg");
		setVisible(true);
	}
	public void pintarRecords(ArrayList <Record> records)
	{	
		Font font = new Font("CALIBRI", Font.BOLD, 15);
		Font font2 = new Font("Lucida Console",Font.BOLD,14);
		
		for(int x=0; x<records.size();x++)
		{
			JLabel etiquetaJugadorr = new JLabel("Jugador-->");
			JLabel etiquetaPuntuacionn = new JLabel("Puntuacion:");
			JLabel etiquetaJugador = new JLabel(records.get(x).getJugador(), JLabel.CENTER);
			JLabel etiquetaPuntuacion = new JLabel(String.valueOf(records.get(x).getPuntuacion()), JLabel.CENTER);
			etiquetaJugador.setFont(font);
			//etiquetaJugador(Color.blue);
			etiquetaPuntuacion.setFont(font2);
			add(etiquetaJugadorr);
			add(etiquetaJugador);
			add(etiquetaPuntuacionn);
			add(etiquetaPuntuacion);
		}
		//add(btnVolver);
	}
	/*
	public void paint(Graphics g)
	{
		//Dibujar la imagen
		g.drawImage(imagenPunt, 4, 23, this);		
	}*/
}
