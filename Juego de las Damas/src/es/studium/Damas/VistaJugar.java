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
				//Clave valor, Clave= String coordenada y valor= Objeto Coordenada
				//Crea un instancia de la clase Coordenada con la fila y columna de ese botón
				tablero[i][j].putClientProperty("coordernada", new Coordenada(i,j)); //Crear propiedad propia de coordenadas llamada coordenada

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
		//Cambiar tambien lo de true
		if(movimientosPosibles(botonPulsado1,botonPulsado2)==1)
		{
			Icon pulsado1 = botonPulsado1.getIcon();
			botonPulsado1.setIcon(null);
			botonPulsado2.setIcon(pulsado1);
			//Si come, cambiar movimiento
		}
		//Para comer fichas
		else if(movimientosPosibles(botonPulsado1,botonPulsado2)==2)
		{
			
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
	//Int 0 --> Movimiento no posible   int 1--> Movimiento posible int 2 --> Movimiento posible comiendo ficha
	public int movimientosPosibles(JButton bp1 , JButton bp2)
	{
		//Conocer el color de la ficha pulsada y su posición
		Icon fichaPulsada = bp1.getIcon();
		Coordenada fichaPosicionActual = (Coordenada) bp1.getClientProperty("coordernada");
		Coordenada fichaPosicionSiguiente = (Coordenada) bp2.getClientProperty("coordernada");

		//Si es blanca se movera hacía abajo
		if(fichaPulsada==fichaBlanca)
		{
			//Si la fila del segundo boton es igual a la fila del primer boton +1. Se puede mover hacia delante
			if(fichaPosicionSiguiente.getfila()==fichaPosicionActual.getfila()+1 || fichaPosicionSiguiente.getfila()==fichaPosicionActual.getfila()+2)
			{
				// Si la columna del segundo boton es igual a la columna del primer boton + 1 o la columna del segundo boton es igual a la columna del primer boton -1
				if(fichaPosicionSiguiente.getcolumna()==fichaPosicionActual.getcolumna()+1 ||
						fichaPosicionSiguiente.getcolumna()==fichaPosicionActual.getcolumna()-1 || 
						fichaPosicionSiguiente.getcolumna()==fichaPosicionActual.getcolumna()+2 ||
						fichaPosicionSiguiente.getcolumna()==fichaPosicionActual.getcolumna()-2)
				{
					//Si en las siguientes casillas posibles no hay un icono
					//Coordenada CoordIzq=new Coordenada(fichaPosicionActual.getfila()+1, fichaPosicionActual.getcolumna()-1);
					//Coordenada CoordDerec= new Coordenada(fichaPosicionActual.getfila()+1,fichaPosicionActual.getcolumna()+1);
					int posible =casillasPosiblesVaciasBlancas(bp1,bp2,fichaPulsada);
					return posible;
				}
			}
		}
		//Si es negra se movera hacía arriba
		else if(fichaPulsada==fichaNegra)
		{
			//Si la fila del segundo boton es igual a la fila del primer boton -1
			if(fichaPosicionSiguiente.getfila()==fichaPosicionActual.getfila()-1 || fichaPosicionSiguiente.getfila()==fichaPosicionActual.getfila()-2)
			{
				//Si la columna del segundo boton es igual a la columna del primer boton -1 o la columna del segundo boton es igual a la columna del primer boton +1
				if(fichaPosicionSiguiente.getcolumna()==fichaPosicionActual.getcolumna()-1 ||
						fichaPosicionSiguiente.getcolumna()==fichaPosicionActual.getcolumna()+1 ||
						fichaPosicionSiguiente.getcolumna()==fichaPosicionActual.getcolumna()-2 ||
						fichaPosicionSiguiente.getcolumna()==fichaPosicionActual.getcolumna()+2)
				{
					int posible = casillasPosiblesVaciasNegras(bp1,bp2,fichaPulsada);
					return posible;
				}
			}
		}

		return 0;
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
	//Int 0 --> Movimiento no posible   int 1--> Movimiento posible int 2 --> Movimiento posible comiendo ficha
	public int casillasPosiblesVaciasBlancas (JButton bp1, JButton bp2, Icon fichaPulsada) 
	{
		Coordenada fichaPosicionActual = (Coordenada) bp1.getClientProperty("coordernada");
		Coordenada fichaPosicionSiguiente = (Coordenada) bp2.getClientProperty("coordernada");

		JButton botonPosibleIzq=null;
		JButton botonPosibleDerec=null;
		JButton botonPosibleIzqBaja=null;
		JButton botonPosibleDerecBaja=null;
		
		for(int fila=0; fila<tablero.length; fila++)
		{
			for(int columna=0; columna<tablero[fila].length; columna++)
			{
				//System.out.println("Ficha posicion Derecha [ "+(fichaPosicionActual.getfila()+1) +"]["+ (fichaPosicionActual.getcolumna()+1)+"]");
				//System.out.println("Ficha posicion Izquierda [ "+(fichaPosicionActual.getfila()+1) +"]["+ (fichaPosicionActual.getcolumna()-1)+"]");

				//Que se pueda mover la ficha dentro de los siguientes parametros del tablero. Que la columna sea mayor que 0 y menor que 7
				if((fichaPosicionActual.getcolumna()+1)>=0 && (fichaPosicionActual.getcolumna()+1)<=7)
				{
					JButton boton = tablero[fichaPosicionActual.getfila()+1][fichaPosicionActual.getcolumna()+1];
					if(boton.getIcon()!=null)
					{
						botonPosibleDerecBaja=tablero[fichaPosicionActual.getfila()+2][fichaPosicionActual.getcolumna()+2];
					}
					else
					{
						botonPosibleDerec=boton;
					}
				}
				if((fichaPosicionActual.getcolumna()-1)>=0 && (fichaPosicionActual.getcolumna()-1)<=7)
				{
					JButton boton = tablero[fichaPosicionActual.getfila()+1][fichaPosicionActual.getcolumna()-1];
					if(boton.getIcon()!=null)
					{
						botonPosibleIzqBaja=tablero[fichaPosicionActual.getfila()+2][fichaPosicionActual.getcolumna()-2];
					}
					else
					{
						botonPosibleIzq=boton;
					}
				}
				//Falta comprobar filas						 
			}
		}	
		//
		int posible =0;
		
		//Si estoy en el borde que no pase nada si quiero ir a la izquierda porque esta fuera del tablero
		if(botonPosibleIzq==null || fichaPosicionActual.getcolumna()==0)
		{
			posible=1;
		}
		//
		else if(botonPosibleDerec==null || fichaPosicionActual.getcolumna()==7)
		{
			posible=1;
		}
		else if(botonPosibleDerecBaja==null || fichaPosicionActual.getcolumna()==7)
		{
			posible=1;
		}
		else if(botonPosibleIzqBaja==null || fichaPosicionActual.getcolumna()==0)
		{
			posible=1;
		}
		//Si no estoy en los bordes
		else if(botonPosibleIzq!=null && botonPosibleDerec!=null && botonPosibleIzqBaja!=null && botonPosibleDerecBaja!=null)
		{
			//Si tengo un icono a la izquierda 
			if(botonPosibleIzq.getIcon()!=null)
			{
				// Y si ese icono es distinto al color de nuestra ficha
				if(botonPosibleIzq.getIcon()!=fichaPulsada)
				{
					Coordenada coordenadaBotonPosibleDerec=(Coordenada) botonPosibleDerec.getClientProperty("coordernada");
					//Entonces no me permita moverme a la derecha y he pulsado en el botón de la derecha
					if(coordenadaBotonPosibleDerec==fichaPosicionSiguiente)
					{
						posible=0;
					}
					else
					{
						//Comiendo ficha
						posible=2;
					}
				}
				else
				{
					posible=1;
				}
				
			}
			//Si en la casilla existe un icono
			else if(botonPosibleDerec.getIcon()!=null)
			{
				//Si en la casilla derecha existe un icono distinto al mio
				if(botonPosibleDerec.getIcon()!=fichaPulsada)
				{
					//No me permite moverme a la izquierda si tengo un icono y he pulsado en el botón de la izquierda
					Coordenada coordenadaBotonPosibleIzq = (Coordenada) botonPosibleIzq.getClientProperty("coordernada");
					if(coordenadaBotonPosibleIzq==fichaPosicionSiguiente)
					{
						posible=0;
					}
					//Comiendo ficha
					else
					{
						posible=2;
					}
				}
				else
				{
					posible=1;
				}
			}
			else
			{
				posible=1;
			}
			
		}
		
		System.out.println(posible);
		return posible;
	}
	//Int 0 --> Movimiento no posible   int 1--> Movimiento posible int 2 --> Movimiento posible comiendo ficha
	public int casillasPosiblesVaciasNegras (JButton bp1, JButton bp2, Icon fichaPulsada) 
	{
		Coordenada fichaPosicionActual = (Coordenada) bp1.getClientProperty("coordernada");
		Coordenada fichaPosicionSiguiente = (Coordenada) bp2.getClientProperty("coordernada");

		JButton botonPosibleIzq=null;
		JButton botonPosibleDerec=null;
		JButton botonPosibleIzqSube=null;
		JButton botonPosibleDerecBaja=null;

		for(int fila=0; fila<tablero.length; fila++)
		{
			for(int columna=0; columna<tablero[fila].length; columna++)
			{
				//System.out.println("Ficha posicion Derecha [ "+(fichaPosicionActual.getfila()+1) +"]["+ (fichaPosicionActual.getcolumna()+1)+"]");
				//System.out.println("Ficha posicion Izquierda [ "+(fichaPosicionActual.getfila()+1) +"]["+ (fichaPosicionActual.getcolumna()-1)+"]");

				//Que se pueda mover la ficha dentro de los siguientes parametros del tablero. Que la columna sea mayor que 0 y menor que 7
				if((fichaPosicionActual.getcolumna()+1)>=0 && (fichaPosicionActual.getcolumna()+1)<=7 )
				{
					JButton boton = tablero[fichaPosicionActual.getfila()-1][fichaPosicionActual.getcolumna()+1];
					if(boton.getIcon()!=null)
					{
						botonPosibleDerecBaja=tablero[fichaPosicionActual.getfila()-2][fichaPosicionActual.getcolumna()+2];
					}
					else
					{
						botonPosibleDerec=boton;
					}
				}
				if((fichaPosicionActual.getcolumna()-1)>=0 && (fichaPosicionActual.getcolumna()-1)<=7)
				{
					JButton boton = tablero[fichaPosicionActual.getfila()-1][fichaPosicionActual.getcolumna()-1];
					if(boton.getIcon()!=null)
					{
						botonPosibleIzqSube = tablero[fichaPosicionActual.getfila()-2][fichaPosicionActual.getcolumna()-2];
					}
					else
					{
						botonPosibleIzq=boton;
					}
				
				}
				
				//Falta comprobar filas						 
			}
		}
		//
		int posible =0;
				
		//Si estoy en el borde o cerca que no pase nada si quiero ir a la izquierda porque esta fuera del tablero
		if(botonPosibleIzq==null || fichaPosicionActual.getcolumna()==0)
		{
			posible=1;
		}
		else if(botonPosibleDerec==null || fichaPosicionActual.getcolumna()==7)
		{
			posible=1;
		}
		else if(botonPosibleDerecBaja==null || fichaPosicionActual.getcolumna()==7)
		{
			posible=1;
		}
		else if(botonPosibleIzqSube==null || fichaPosicionActual.getcolumna()==0)
		{
			posible=1;
		}
		//Si, no estoy en los bordes
		else if (botonPosibleIzq!=null && botonPosibleDerec!=null && botonPosibleDerecBaja!=null && botonPosibleIzqSube!=null)
		{ 
		
			//Si tengo un icono a la derecha
			if(botonPosibleDerec.getIcon()!=null)
			{
				// Y si ese icono es distinto al color de nuestra ficha
				if(botonPosibleDerec.getIcon()!=fichaPulsada)
				{
					Coordenada coordenadaBotonPosibleIzq=(Coordenada) botonPosibleIzq.getClientProperty("coordenada");
					if(coordenadaBotonPosibleIzq==fichaPosicionSiguiente)
					{
						posible=0;
					}
					else
					{
						//Comiendo ficha
						posible=2;
					}
				}
				//
				else
				{
					posible=1;
				}
			}
			//Si en la casilla existe un icono
			else if(botonPosibleIzq.getIcon()!=null)
			{
				//Si en la casilla izquierda existe un icono distinto al mio
				if(botonPosibleIzq.getIcon()!=fichaPulsada)
				{
					//No me permite moverme a la derecha si tengo un icono y he pulsado en el botón de la derecha
					Coordenada coordenadaBotonPosibleDerec = (Coordenada) botonPosibleDerec.getClientProperty("coordernada");
					if(coordenadaBotonPosibleDerec==fichaPosicionSiguiente)
					{
						posible=0;
					}
					//Comer ficha
					else
					{
						posible=2;
					}
				}
				else
				{
					posible=1;
				}
			}
			else
			{
				posible = 1;
			}
		}
		
		System.out.println(posible);
		return posible;
	}
}
