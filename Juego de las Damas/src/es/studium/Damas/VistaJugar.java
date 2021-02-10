package es.studium.Damas;


import java.awt.Color;
//import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VistaJugar extends Frame implements ActionListener
{	
	private static final long serialVersionUID = 1L;
	
	//tablero de botones bidiomensional
	JButton tablero [][]; 
	GridLayout glTablero; //layout del tablero
	//Iconos de las fichas
	Icon fichaBlanca;
	Icon fichaNegra;
	
	JButton botonesPulsados[];//Botones pulsados
	//contador de botones pulsados
	int contadorPulsados;
	//Turnos, false blancas, true negras
	Boolean turno;
	
	//Contador fichas comidas
	int fichasComidasJugador1;
	int fichasComidasJugador2;
		
	//Aqui guardaremos el nombre de cada jugador
	String nombreJugador1;
	String nombreJugador2;
	
	//Etiqueta con el nombre una vez rellenado
	JLabel movimientosJugador1Label;
	JLabel movimientosJugador2Label;
	
	//Contador de movimientos
	int movimientosJugador1;
	int movimientosJugador2;
	
	//Etiqueta y campo de texto del diálogo
	JLabel etiquetaJugador1;
	JLabel etiquetaJugador2;	
	JTextField textoJugador1;
	JTextField textoJugador2;
	
	//Gestión imagen
	Image imagenMadera;
	Toolkit herramienta;
	
	// objetos gestores de conexión
	Modelo modelo;
	Connection conexion;
	
	JButton btnFinalizar;
	
	public VistaJugar()
	{
		this.setLayout(new GridBagLayout());
		tablero = new JButton [8][8];
		fichaBlanca = new ImageIcon("blancas.png");
		fichaNegra = new ImageIcon("negras.png");
		glTablero= new GridLayout(8,8);
		JPanel panel = new JPanel();
		panel.setLayout(glTablero);
		
		GridBagConstraints gbcpanel = new GridBagConstraints();
		gbcpanel.gridx=0;
		gbcpanel.gridy=1;
		gbcpanel.gridwidth=1;
		gbcpanel.gridheight=1;
		setTitle("DAMAS");
		setSize(810,810);
		setLocationRelativeTo(null);
		
		iniciarDialogo();
		
		etiquetaJugador1 = new JLabel(nombreJugador1);
		//Panel del jugador 1
		JPanel panelJugador1 = new JPanel();
		GridBagConstraints gbc1 = new GridBagConstraints();
		gbc1.gridx=0;
		gbc1.gridy=0;
		gbc1.gridwidth=3;
		gbc1.gridheight=1;
		gbc1.weighty=50.0;
		panelJugador1.setBackground(Color.CYAN);
		panelJugador1.setLocation(50, 100);
		btnFinalizar = new JButton("Finalizar");
		panelJugador1.add(etiquetaJugador1);
		
		movimientosJugador1=0;
		movimientosJugador1Label= new JLabel("movimientos:"+ movimientosJugador1);
		panelJugador1.add(movimientosJugador1Label);
		panelJugador1.add(btnFinalizar);
		etiquetaJugador2 = new JLabel(nombreJugador2);
		//Panel del jugador 2
		JPanel panelJugador2 = new JPanel();
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.gridx=0;
		gbc2.gridy=2;
		gbc2.gridwidth=1;
		gbc2.gridheight=1;
		gbc2.weighty=50.0;
		panelJugador2.setBackground(Color.RED);
		panelJugador2.add(etiquetaJugador2);
		movimientosJugador2=0;
		movimientosJugador2Label= new JLabel("movimientos:"+ movimientosJugador2);
		panelJugador2.add(movimientosJugador2Label);
		
		//Llamos al método de inicializar el tablero pasando 
		inicializarTablero(tablero,panel);
			
		add(panelJugador1,gbc1);
		add(panel,gbcpanel);
		add(panelJugador2,gbc2);
		herramienta = getToolkit();
		imagenMadera = herramienta.getImage("madera.jpg");
		setVisible(true);
		
		
		botonesPulsados= new JButton[2];
		botonesPulsados[0]=null;
		botonesPulsados[1]=null;
		turno=false;
		fichasComidasJugador1=0;
		fichasComidasJugador2=0;
		modelo = new Modelo();
		conexion = modelo.connectDB();
			
		contadorPulsados=1;
	}
	/*
	public void paint(Graphics g)
	{
		//Dibujar la imagen
		g.drawImage(imagenMadera, 0, 0, this);
	}*/
	//Diálogo para introducir los nombres de los jugadores
	public void iniciarDialogo()
	{
		
		JLabel etiquetaFichaN = new JLabel("Ficha Negra");
		JLabel etiquetaFichaB = new JLabel("Ficha Blanca");
		
		 textoJugador1 = new JTextField(13);
         textoJugador2 = new JTextField(13);
       
        
        TextPrompt placerHolder1 = new TextPrompt("Nombre del jugador",textoJugador1);
        placerHolder1.changeAlpha(0.75F);
        
        TextPrompt placerHolder2 = new TextPrompt("Nombre del jugador",textoJugador2);
        placerHolder2.changeAlpha(0.75F);
        
		JPanel panel = new JPanel(new GridLayout(0,1,0,10));
		
		panel.add(etiquetaFichaN);
        panel.add(textoJugador1);
        panel.add(etiquetaFichaB);
        panel.add(textoJugador2);
        //String nombre = JOptionPane.showInputDialog("Jugador Fichas Negras");
		  
        
        int result = JOptionPane.showConfirmDialog(null,panel,"Nueva Partida",
        		JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) 
        {  
        	nombreJugador1 = textoJugador1.getText();
        	nombreJugador2 = textoJugador2.getText();        	
        }
	}
	public void acabarPartida()
	{
		
		int opcion=JOptionPane.showConfirmDialog(null, "¿Segur@ quieres terminar la partida?");
		if(JOptionPane.OK_OPTION==opcion)
		{	
			this.setVisible(false);			
		}
	}
	public void inicializarTablero (JButton tablero[][], JPanel panel)
	{		
		for(int i=0; i<tablero.length; i++)
		{
			for(int j=0; j<tablero[i].length; j++)
			{
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
			botonesPulsados[0]=null;
			botonesPulsados[1]=null;
		}

	}
	public void moverFicha (JButton botonPulsado1 , JButton botonPulsado2)//Recibir el color del boton pulsado
	{
		Icon pulsado1 = botonPulsado1.getIcon();
		
		//Cambiar tambien lo de true
		if(movimientosPosibles(botonPulsado1,botonPulsado2)==1)
		{			
			botonPulsado1.setIcon(null);
			botonPulsado2.setIcon(pulsado1);
			//Alternar turnos
		turno=!turno;//Si come, cambiar movimiento		
			
		}
		//Para comer fichas
		else if(movimientosPosibles(botonPulsado1,botonPulsado2)==2)
		{
			//Objetos coordenada para sacar la coordenada y con getClientProperty consulta la coordenada de ese botón
			Coordenada coordenadaBtn2 = (Coordenada) botonPulsado2.getClientProperty("coordernada");
			Coordenada coordenadaBtn1 = (Coordenada) botonPulsado1.getClientProperty("coordernada");
			if(pulsado1.equals(fichaBlanca))
			{
				//En blancas hacia la izquierda y hacia abajo
				if(coordenadaBtn1.getcolumna()>coordenadaBtn2.getcolumna())
				{
					tablero[coordenadaBtn2.getfila()-1][coordenadaBtn2.getcolumna()+1].setIcon(null);
				}
				else
				{
					tablero[coordenadaBtn2.getfila()-1][coordenadaBtn2.getcolumna()-1].setIcon(null);
				}
				fichasComidasJugador1++;
			}
			else if(pulsado1.equals(fichaNegra))
			{
				if(coordenadaBtn1.getcolumna()>coordenadaBtn2.getcolumna())
				{
					tablero[coordenadaBtn2.getfila()+1][coordenadaBtn2.getcolumna()+1].setIcon(null);
				}
				else
				{
					tablero[coordenadaBtn2.getfila()+1][coordenadaBtn2.getcolumna()-1].setIcon(null);
				}
				fichasComidasJugador2++;
			}
			botonPulsado1.setIcon(null);
			botonPulsado2.setIcon(pulsado1);
			turno=!turno; //Aqui va el cambio de color dependiendo del turno
			System.out.println("Fichas comidas del jugador 1"+"="+fichasComidasJugador1);
			System.out.println("Fichas comidas del jugador 2"+"="+fichasComidasJugador2);
		}
		//El jugador 1 ha ganado
		if(fichasComidasJugador1==12)
		{
			guardarDatos(nombreJugador1,movimientosJugador1);
			acabarPartida();
			//Incluir un dialgo por ejemplo
		}
		//El jugador 2 ha ganado
		else if(fichasComidasJugador2==12)
		{
			guardarDatos(nombreJugador2,movimientosJugador2);
			acabarPartida();
		}		
	}
	public void guardarDatos(String nombreJugador, int puntuacionGanador)
	{
		modelo.insertarRecord(conexion, nombreJugador, puntuacionGanador);
	}
	/*
	 * Siempre hacia adelante!!
		No saltar si hay dos fichas en el camino!!
		Blancas
		1 movimiento diagonal en el mismo color de casilla
		Negras
		Si tiene una ficha delante debe comerla
	 */
	//Int 0 --> Movimiento no posible   int 1--> Movimiento posible int 2 --> Movimiento posible comiendo ficha blancas false y negras true
	public int movimientosPosibles(JButton bp1 , JButton bp2)
	{
		//Conocer el color de la ficha pulsada y su posición
		Icon fichaPulsada = bp1.getIcon();
		Coordenada fichaPosicionActual = (Coordenada) bp1.getClientProperty("coordernada");
		Coordenada fichaPosicionSiguiente = (Coordenada) bp2.getClientProperty("coordernada");

		//Si es blanca se movera hacía abajo
		if(turno==false && fichaPulsada==fichaBlanca)
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
					movimientosJugador1++;
					movimientosJugador1Label.setText("movimientos: "+movimientosJugador1);
					return posible;
				}
			}
		}
		//Si es negra se movera hacía arriba
		else if(turno==true && fichaPulsada==fichaNegra)
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
					movimientosJugador2++;
					movimientosJugador2Label.setText("movimientos:"+ movimientosJugador2);
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
		//Coordenada fichaPosicionSiguiente = (Coordenada) bp2.getClientProperty("coordernada");

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
					if(boton.getIcon()!=fichaPulsada && boton.getIcon()!=null &&(fichaPosicionActual.getcolumna()+2)>=0 
							&& (fichaPosicionActual.getcolumna()+2)<=7 &&
							fichaPosicionActual.getfila()+2<=7)
					{
						
						botonPosibleDerecBaja=tablero[fichaPosicionActual.getfila()+2][fichaPosicionActual.getcolumna()+2];
						if(botonPosibleDerecBaja.getIcon()!=null)
						{
							botonPosibleDerecBaja=null;
						}
					}
					else
					{
						botonPosibleDerec=boton;
					}
				}
				if((fichaPosicionActual.getcolumna()-1)>=0 && (fichaPosicionActual.getcolumna()-1)<=7)
				{
					JButton boton = tablero[fichaPosicionActual.getfila()+1][fichaPosicionActual.getcolumna()-1];
					if(boton.getIcon()!=fichaPulsada && boton.getIcon()!=null 
							&& (fichaPosicionActual.getcolumna()-2)>=0 && (fichaPosicionActual.getcolumna()-2)<=7 &&
							fichaPosicionActual.getfila()+2<=7)
					{
						botonPosibleIzqBaja=tablero[fichaPosicionActual.getfila()+2][fichaPosicionActual.getcolumna()-2];
						if(botonPosibleIzqBaja.getIcon()!=null)
						{
							botonPosibleIzqBaja=null;
						}
					}
					else
					{
						botonPosibleIzq=boton;
					}
				}								 
			}
		}	
		//
		int posible =0;
		//Movimientos blancas del medio
		if(botonPosibleIzqBaja==null && botonPosibleDerecBaja==null)
		{
			
			if(bp2==botonPosibleIzq || bp2==botonPosibleDerec)
			{
				posible=1;
			}
		}
		else if(botonPosibleIzqBaja!=null && botonPosibleDerecBaja ==null)
		{
			if(bp2==botonPosibleIzqBaja)
			{
				posible=2;
			}
			else
			{
				posible=1;
			}
		}
		else if(botonPosibleIzqBaja==null && botonPosibleDerecBaja!=null)
		{
			if(bp2==botonPosibleDerecBaja)
			{
				posible=2;
			}
			else
			{
				posible=1;
			}
		}
		System.out.println(posible+" - "+turno);
		return posible;
	}
	//Int 0 --> Movimiento no posible   int 1--> Movimiento posible int 2 --> Movimiento posible comiendo ficha
	//Cambiar el código como en las blancas********************
	public int casillasPosiblesVaciasNegras (JButton bp1, JButton bp2, Icon fichaPulsada) 
	{
		Coordenada fichaPosicionActual = (Coordenada) bp1.getClientProperty("coordernada");
		//Coordenada fichaPosicionSiguiente = (Coordenada) bp2.getClientProperty("coordernada");

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
				if((fichaPosicionActual.getcolumna()+1)>=0 && (fichaPosicionActual.getcolumna()+1)<=7)
				{
					JButton boton = tablero[fichaPosicionActual.getfila()-1][fichaPosicionActual.getcolumna()+1];
					if(boton.getIcon()!=fichaPulsada && boton.getIcon()!=null &&(fichaPosicionActual.getcolumna()+2)>=0 
							&& (fichaPosicionActual.getcolumna()+2)<=7 &&
							fichaPosicionActual.getfila()-2>=0)
					{
						botonPosibleDerecBaja=tablero[fichaPosicionActual.getfila()-2][fichaPosicionActual.getcolumna()+2];
						if(botonPosibleDerecBaja.getIcon()!=null)
						{
							botonPosibleDerecBaja=null;
						}
					}
					else
					{
						botonPosibleDerec=boton;
					}
				}
				if((fichaPosicionActual.getcolumna()-1)>=0 && (fichaPosicionActual.getcolumna()-1)<=7)
				{
					JButton boton = tablero[fichaPosicionActual.getfila()-1][fichaPosicionActual.getcolumna()-1];
					if(boton.getIcon()!=fichaPulsada && boton.getIcon()!=null
							&&(fichaPosicionActual.getcolumna()-2)>=0 && (fichaPosicionActual.getcolumna()-2)<=7
							&&fichaPosicionActual.getfila()-2>=0)
					{
						botonPosibleIzqSube = tablero[fichaPosicionActual.getfila()-2][fichaPosicionActual.getcolumna()-2];
						if(botonPosibleIzqSube.getIcon()!=null)
						{
							botonPosibleIzqSube=null;
						}
					}
					else
					{
						botonPosibleIzq=boton;
					}
				}
			}
		}
		//
		int posible =0;
		//Movimientos negra del medio
		if(botonPosibleIzqSube==null && botonPosibleDerecBaja==null)
		{
			if(bp2==botonPosibleIzq || bp2==botonPosibleDerec)
			{
				posible=1;
			}
		}
		else if(botonPosibleIzqSube!=null && botonPosibleDerecBaja==null)
		{
			if(bp2==botonPosibleIzqSube)
			{
				posible=2;
			}
			else
			{
				posible=1;
			}
		}
		else if(botonPosibleIzqSube==null && botonPosibleDerecBaja!=null)
		{
			if(bp2==botonPosibleDerecBaja)
			{
				posible=2;
			}
			else
			{
				posible=1;
			}
		}
		System.out.println(posible+" - "+turno);
		return posible;
	}
}
