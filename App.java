import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
 
public class App extends JFrame implements KeyListener
{
    public class Ponto
    {
        private int x, y;
        
        Ponto(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
 
        public int getX() {
            return x;
        }
 
        public void setX(int x) {
            this.x = x;
        }
 
        public int getY() {
            return y;
        }
 
        public void setY(int y) {
            this.y = y;
        }
    }
    
    public class Tela extends Canvas
    {
        private List<Ponto> pontos = new ArrayList<Ponto>();
        
        Tela ()
        {
            setBackground(new Color(0xffffff));
        }
        
        public void setPontos (List pontos)
        {
            this.pontos = pontos;
        }
        
        @Override
        public void paint(Graphics g) 
        {
            super.paint(g);
            
            for (int i = 1; i < pontos.size(); i++)
            {               
                g.drawLine(pontos.get(i - 1).getX(), pontos.get(i - 1).getY(), pontos.get(i).getX(), pontos.get(i).getY());
            }
        }
    }
    
    private Tela tela;
    private int contador;
    private JLabel rotulo;
    private List<Ponto> lista = new ArrayList<Ponto>();
    
    App ()
    {
    	rotulo = new JLabel("Vel: ");
        rotulo.setBounds(10, 10, 100, 25);
        add(rotulo);
    	
        tela = new Tela();
        tela.addKeyListener(this);
        tela.setBounds(0, 0, 1000, 500);
        add(tela);       
                
        setSize(1000, 500);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        Timer tempo = new Timer();
        tempo.scheduleAtFixedRate(new TimerTask() 
        {
			@Override
			public void run() 
			{
				System.out.println("contador: " + contador);
				
				int eixoY = 450 - 18*contador;
				
				rotulo.setText("Vel: " + contador + " key/s");
				
				contador = 0;
				
				if (lista.isEmpty())
				{
					lista.add(new Ponto(50, eixoY));
				}
				else 
				{
					lista.add(new Ponto(lista.get(lista.size() - 1).getX() + 50, eixoY));
				}
				
				if (lista.size() == 15)
				{
					lista.remove(0);
					
					for (Ponto x : lista)
					{
						x.setX(x.getX() - 50);
					}
				}
				
				tela.setPontos(lista);
				tela.repaint();
			}
        }, 0, 500);
    }
    
    public static void main (String args[])
    {
        new App();
    }

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		contador++;
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
