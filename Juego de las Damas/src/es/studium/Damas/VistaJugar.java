package es.studium.Damas;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class VistaJugar extends Frame implements ActionListener
{	
	private static final long serialVersionUID = 1L;
	JButton tablero [][];
	GridLayout glTablero;
	Icon fichaBlanca;
	Icon fichaNegra;
	JButton botonesPulsados[];
	int contadorPulsados =1;

	public VistaJugar()
	{
		tablero = new JButton [8][8];
		fichaBlanca = new ImageIcon("blancas.png");
		fichaNegra = new ImageIcon("negras.png");
		glTablero= new GridLayout(8,8);
		JPanel panel = new JPanel();
		panel.setLayout(glTablero);
		setTitle("Damas");

		inicializarTablero(tablero,panel);
		setSize(810,810);
		setLocationRelativeTo(null);
		add(panel);
		setVisible(true);

		botonesPulsados= new JButton[2];
		botonesPulsados[0]=null;
		botonesPulsados[1]=null;

	}

	public void inicializarTablero (JButton tablero[][], JPanel panel)
	{

		panel.setSize(610,610);
		for(int i=0; i<tablero.length; i++)
		{
			for(int j=0; j<tablero[i].length; j++)
			{

				//tablero[i][j]= new JButton(fichaNegra);

				if(i>=5)
				{
					if(i%2!=0 && j%2!=0)
					{
						tablero[i][j]= new JButton(fichaNegra);
					}
					else if(i%2==0 && j%2==0)
					{
						tablero[i][j]= new JButton(fichaNegra);
					}
					else
					{
						tablero[i][j]= new JButton();
					}

				}
				else if(i<3)
				{
					if(i%2==0 && j%2==0)
					{
						tablero[i][j]= new JButton(fichaBlanca);
					}
					else if(i%2!=0 && j%2!=0)
					{
						tablero[i][j]= new JButton(fichaBlanca);
					}
					//Todos los demas huecos
					else
					{
						tablero[i][j]= new JButton();
					}
				}

				else
				{
					tablero[i][j]= new JButton();
				}
				tablero[i][j].setBounds(150, 240, 200, 50);


				if(i%2==0)
				{
					if(j%2==0)
					{
						tablero[i][j].setBackground(Color.WHITE);

					}
					else
					{
						tablero[i][j].setBackground(Color.BLACK);

					}

				}
				else
				{
					if(j%2==0)
					{
						tablero[i][j].setBackground(Color.BLACK);
					}
					else
					{
						tablero[i][j].setBackground(Color.WHITE);
					}
				}

				tablero[i][j].addActionListener(this);
				tablero[i][j].putClientProperty("coordernada", new Coordenada(i,j)); //Crear propiedad propia de coordenadas

				panel.add(tablero[i][j]);		
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton boton = (JButton) e.getSource();
		//System.out.println("Click " + boton.getClientProperty("coordernada").toString());


		//Si el primer boton ha sido pulsado y el segundo boton tambien
		if(botonesPulsados[0]!=null && botonesPulsados[1]!=null)
		{

			//System.out.println("Boton 0 distinto null - Boton 1 distinto null");
			botonesPulsados[0] = botonesPulsados[1];
			botonesPulsados[1]= (JButton) e.getSource();
			//botonesPulsados[0].setFocusable(false);

		}
		else
		{
			if(botonesPulsados[0]==null)
			{
				//System.out.println("Boton 0 null");
				botonesPulsados[0]=(JButton) e.getSource();
			}
			else if(botonesPulsados[1]==null)
			{
				//System.out.println("Boton 1 null");
				botonesPulsados[1]=(JButton) e.getSource();
			}
		}


		if(contadorPulsados<2)
		{
			//System.out.println(contadorPulsados);

			contadorPulsados++;
		}
		else if(contadorPulsados==2)
		{

			contadorPulsados=1;
			//Mover la ficha

			moverFicha(botonesPulsados[0], botonesPulsados[1]);

			/*System.out.println(botonesPulsados[0].getClientProperty("coordernada").toString()+ " - " + 
					botonesPulsados[1].getClientProperty("coordernada").toString());*/

			botonesPulsados[0]=null;
			botonesPulsados[1]=null;
		}

	}
	public void moverFicha (JButton botonPulsado1 , JButton botonPulsado2)//Recibir el color del boton pulsado
	{
		if(movimientosPosibles(botonPulsado1,botonPulsado2)==true)
		{
			Icon pulsado1 = botonPulsado1.getIcon();
			botonPulsado1.setIcon(null);
			botonPulsado2.setIcon(pulsado1);
		}

	}
	/*
	 * Siempre hacia adelante!!
		No saltar si hay dos fichas en el camino!!
		Blancas
		1 movimiento diagonal en el mismo color de casilla
		Negras
		Si tiene una ficha delante debe comerla
	 */
	public boolean movimientosPosibles(JButton bp1 , JButton bp2)
	{
		//Conocer el color de la ficha pulsada y su posición
		Icon fichaPulsada = bp1.getIcon();
		Coordenada fichaPosicionActual = (Coordenada) bp1.getClientProperty("coordernada");
		Coordenada fichaPosicionSiguiente = (Coordenada) bp2.getClientProperty("coordernada");

		//Si es blanca se movera hacía abajo
		if(fichaPulsada==fichaBlanca)
		{
			//Si la fila del segundo boton es igual a la fila del primer boton +1. Se puede mover hacia delante
			if(fichaPosicionSiguiente.getfila()==fichaPosicionActual.getfila()+1)
			{
				// Si la columna del segundo boton es igual a la columna del primer boton + 1 o la columan del segundo boton es igual a la columna del primer boton -1
				if(fichaPosicionSiguiente.getcolumna()==fichaPosicionActual.getcolumna()+1 || fichaPosicionSiguiente.getcolumna()==fichaPosicionActual.getcolumna()-1)
				{
					//Si en las siguientes casillas posibles no hay un icono
					//Coordenada CoordIzq=new Coordenada(fichaPosicionActual.getfila()+1, fichaPosicionActual.getcolumna()-1);
					//Coordenada CoordDerec= new Coordenada(fichaPosicionActual.getfila()+1,fichaPosicionActual.getcolumna()+1);
					if(casillasPosiblesVaciasBlancas(bp1,bp2))
					{
						return true;
					}
				}
			}
		}
		//Si es negra se movera hacía arriba
		else if(fichaPulsada==fichaNegra)
		{
			//Si la fila del segundo boton es igual a la fila del primer boton -1
			if(fichaPosicionSiguiente.getfila()==fichaPosicionActual.getfila()-1)
			{
				//Si la columna del segundo boton es igual a la columna del primer boton -1 o la columna del segundo boton es igual a la columna del primer boton +1
				if(fichaPosicionSiguiente.getcolumna()==fichaPosicionActual.getcolumna()-1 || fichaPosicionSiguiente.getcolumna()==fichaPosicionActual.getcolumna()+1)
				{
					return true;
				}
			}
		}

		return false;
	}

	public boolean mismoColor(JButton botonPul1, JButton botonPul2)
	{
		if(botonPul1.getBackground()==botonPul2.getBackground())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean casillasPosiblesVaciasBlancas (JButton bp1, JButton bp2) //FALTA QUE NO SE SALGA DE TABLERO********************
	{
		Coordenada fichaPosicionActual = (Coordenada) bp1.getClientProperty("coordernada");
		Coordenada fichaPosicionSiguiente = (Coordenada) bp2.getClientProperty("coordernada");

		JButton botonPosibleIzq=null;
		JButton botonPosibleDerec=null;

		for(int fila=0; fila<tablero.length; fila++)
		{
			for(int columna=0; columna<tablero[fila].length; columna++)
			{
				//System.out.println("Ficha posicion Derecha [ "+(fichaPosicionActual.getfila()+1) +"]["+ (fichaPosicionActual.getcolumna()+1)+"]");
				//System.out.println("Ficha posicion Izquierda [ "+(fichaPosicionActual.getfila()+1) +"]["+ (fichaPosicionActual.getcolumna()-1)+"]");

				//Que se pueda mover la ficha dentro de los siguientes parametros del tablero. Que la columna sea mayor que 0 y menor que 7
				if((fichaPosicionActual.getcolumna()+1)>=0 && (fichaPosicionActual.getcolumna()+1)<=7)
				{
					botonPosibleDerec = tablero[fichaPosicionActual.getfila()+1][fichaPosicionActual.getcolumna()+1];
				}
				if((fichaPosicionActual.getcolumna()-1)>=0 && (fichaPosicionActual.getcolumna()-1)<=7)
				{
					botonPosibleIzq = tablero[fichaPosicionActual.getfila()+1][fichaPosicionActual.getcolumna()-1];
				}
				//Falta comprobar filas						 
			}
		}
		//
		boolean posible =false;
		if(botonPosibleIzq!=null && botonPosibleIzq.getIcon()==null)
		{
			posible=true;
		}
		System.out.println(botonPosibleIzq.getIcon());

		if(botonPosibleDerec!=null && botonPosibleDerec.getIcon()==null)
		{
			posible=true;

		}
		System.out.println(botonPosibleDerec.getIcon());
		System.out.println(posible);
		if(posible)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean casillasPosiblesVaciasNegras (JButton bp1, JButton bp2) //FALTA QUE NO SE SALGA DE TABLERO********************
	{
		Coordenada fichaPosicionActual = (Coordenada) bp1.getClientProperty("coordernada");
		Coordenada fichaPosicionSiguiente = (Coordenada) bp2.getClientProperty("coordernada");

		JButton botonPosibleIzq=null;
		JButton botonPosibleDerec=null;

		for(int fila=0; fila<tablero.length; fila++)
		{
			for(int columna=0; columna<tablero[fila].length; columna++)
			{
				//System.out.println("Ficha posicion Derecha [ "+(fichaPosicionActual.getfila()+1) +"]["+ (fichaPosicionActual.getcolumna()+1)+"]");
				//System.out.println("Ficha posicion Izquierda [ "+(fichaPosicionActual.getfila()+1) +"]["+ (fichaPosicionActual.getcolumna()-1)+"]");

				//Que se pueda mover la ficha dentro de los siguientes parametros del tablero. Que la columna sea mayor que 0 y menor que 7
				if((fichaPosicionActual.getcolumna()+1)>=0 && (fichaPosicionActual.getcolumna()+1)<=7)
				{
					botonPosibleDerec = tablero[fichaPosicionActual.getfila()+1][fichaPosicionActual.getcolumna()+1];
				}
				if((fichaPosicionActual.getcolumna()-1)>=0 && (fichaPosicionActual.getcolumna()-1)<=7)
				{
					botonPosibleIzq = tablero[fichaPosicionActual.getfila()+1][fichaPosicionActual.getcolumna()-1];
				}
				//Falta comprobar filas						 
			}
		}
		//
		boolean posible =false;
		if(botonPosibleIzq!=null && botonPosibleIzq.getIcon()==null)
		{
			posible=true;
		}
		System.out.println(botonPosibleIzq.getIcon());

		if(botonPosibleDerec!=null && botonPosibleDerec.getIcon()==null)
		{
			posible=true;

		}
		System.out.println(botonPosibleDerec.getIcon());
		System.out.println(posible);
		if(posible)
		{
			return true;
		}
		else
		{
			return false;
		}

	}
}
