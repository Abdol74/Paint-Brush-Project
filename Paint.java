import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseAdapter;
import java.awt.Button;
import java.util.Vector;
import java.lang.Math;
import java.awt.Color;
import java.awt.Checkbox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.lang.Exception;
import java.lang.Integer;

public class Paint extends Applet{
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private int width = 0;
	private int height = 0;
	private int top_Left_x;
	private int top_left_y;
	private int fillPressed = 2; 
	String current_Shape = "shape";
	Vector<Shape> vector ;
	Vector<Pencil> vectorPencil ;
	Vector<Eraser> vectorEraser;
	int count =0;
	Color color ; 
	Color shape_color;
	Button btLine;
	Button btRectangle;
	Button btCircle;
	Button btFreeDrawer;
	Button btEraser;
	boolean colorPressed = false;
	boolean firstMove = true ;
	boolean clearPressed = false ;
	Button btColorGreen;
	Button btColorRed;
	Button btColorBlue;
	Button btClearAll ;
	Checkbox check_box;
	public static final Color BACKGROUND_COLOR = Color.WHITE;
	Line myLine ;
	Rectangle myRectangle ; 
	Circle myCircle ;
	Pencil myPencil;
	Eraser myEraser;
	public void init(){
		vector = new Vector<Shape>();
		vectorPencil =  new Vector<Pencil>();
		vectorEraser = new Vector<Eraser>();
		shape_color = Color.BLACK;
		btLine = new Button("Line");
		btRectangle = new Button("Rectangle");
		btLine.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				current_Shape = "Line";
				System.out.println("Pressed");
			}
		});
		add(btLine);
		
		    btRectangle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				current_Shape = "Rectangle";
				System.out.println("Pressed");
			}
		});
		add(btRectangle);
		
		btCircle = new Button("Circle");
		btCircle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				current_Shape = "Circle";
				System.out.println("Pressed");
			}
		});
		add(btCircle);
		addMouseMotionListener(new MyMouseAdapter());
		addMouseListener(new MyMouseAdapter());
		
		btColorGreen = new Button("Green Color");
		btColorGreen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				shape_color = Color.GREEN;
				
			}
		});
		add(btColorGreen);
		
		btColorRed = new Button("Red Color");
		btColorRed.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				shape_color = Color.RED;
				
			}
		});
		add(btColorRed);
		
		btColorBlue = new Button("Blue Color");
		btColorBlue.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				shape_color = Color.BLUE;
				
			}
		});
		add(btColorBlue);
		
		check_box = new Checkbox();
		check_box.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				 fillPressed = e.getStateChange();
				 System.out.println(fillPressed);
				
				
			}
		});
		add(check_box);
		
		btFreeDrawer = new Button("Free Draw");
		btFreeDrawer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				current_Shape = "Free_Drawer";
				System.out.println("Pressed");
			}
		});
		
		add(btFreeDrawer);
		btEraser = new Button("Erase");
		btEraser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				current_Shape = "Eraser";
				System.out.println("Pressed");
			}
		});
		add(btEraser);
		
		btClearAll = new Button("Clear");
		btClearAll.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			clearPressed = true ;
			current_Shape = "shape";
			repaint();
			System.out.println(current_Shape);
		}
		});
		add(btClearAll);
		
	}
	public void paint(Graphics g){
		if(clearPressed){
			vector = new Vector<Shape>();
			vectorPencil  = new Vector<Pencil>();
			vectorEraser = new Vector<Eraser>();
			System.out.println(vector.size());
			System.out.println(vectorPencil.size());
			clearPressed = false;
		}
		
		for(int j=0;j<vector.size();j++){
			vector.get(j).drawMyShape(g);
		}
		
		for(int i =0 ; i<vectorPencil.size();i++){
			vectorPencil.get(i).drawMyShape(g);
		}
		
		for(int k=0 ; k<vectorEraser.size();k++){
			vectorEraser.get(k).drawMyShape(g);
		}
		
		switch (current_Shape){
			case "Line": myLine = new Line(x1,y1,x2,y2,"Line",shape_color);
						 myLine.drawMyShape(g);
								break;
			case "Rectangle":myRectangle = new Rectangle(top_Left_x,top_left_y,width,height,"Rectangle",shape_color,fillPressed);
							 myRectangle.drawMyShape(g);
								break;
			case "Circle" : myCircle = new Circle(top_Left_x,top_left_y,width,height,"Circle",shape_color,fillPressed);
							myCircle.drawMyShape(g);
								break;
			case "Free_Drawer"://myPencil = new Pencil ();
								myPencil.drawMyShape(g);
								break;
			case "Eraser" :myEraser.drawMyShape(g);
								break;
		}
	
	}
	
	
class MyMouseAdapter extends MouseAdapter{
    public void mousePressed(MouseEvent e){
		myPencil = new Pencil (shape_color);
		myEraser = new Eraser();
		x1 = x2 = e.getX();
		y1 = y2 = e.getY();
		//repaint();
		}
		public void mouseDragged(MouseEvent e){
			if(current_Shape == "Free_Drawer"){
					if(firstMove){
					x2 = e.getX();
					y2 = e.getY();	
					myPencil.v_pencil.add(new Line(x1,y1,x2,y2,"Pencil",shape_color));
					firstMove = false;
				}
			
				  if(!firstMove){
					x1 = x2 ; 
					y1 = y2;
					x2 = e.getX();
					y2 = e.getY();
					myPencil.v_pencil.add(new Line(x1,y1,x2,y2,"Pencil",shape_color));
					
				}
				repaint();
			}
			else if(current_Shape == "Eraser"){
					x1 = e.getX() ; 
					y1 = e.getY();
					myEraser.v_Eraser.add(new Rectangle(x1,y1,10,10,"Eraser",Color.WHITE,1));
				    repaint();
			}
			else{
			x2 = e.getX();
			y2 = e.getY();
			top_Left_x = Math.min(x1,x2);
			top_left_y = Math.min(y1,y2);
			width = Math.abs(x1-x2);
			height = Math.abs(y1-y2);
			repaint();
			}
		
		}
		public void mouseReleased(MouseEvent e){
			x2 = e.getX();
			y2 = e.getY();
			top_Left_x = Math.min(x1,x2);
			top_left_y = Math.min(y1,y2);
			width = Math.abs(x1-x2);
			height = Math.abs(y1-y2);
			/*
			if(current_Shape == "Line"){
			   vector.add(new Line(x1,y1,x2,y2,"Line",shape_color));
			   }
			else if(current_Shape == "Rectangle"){
			   vector.add(new Rectangle(top_Left_x,top_left_y,width,height,"Rectangle",shape_color,fillPressed));
			}
			else if(current_Shape == "Circle"){
				vector.add(new Circle(top_Left_x,top_left_y,width,height,"Circle",shape_color,fillPressed));
			}
			else if(current_Shape == "Free_Drawer"){
				vectorPencil.add((myPencil));
				System.out.println(vectorPencil.size());
			}
			else if(current_Shape == "Eraser"){
				vectorEraser.add(myEraser);
			}*/
			
			switch(current_Shape){
				case "Line" : vector.add(myLine);break;
				case "Rectangle" :vector.add(myRectangle);break;
				case "Circle" :vector.add(myCircle);break;
				case "Free_Drawer":vectorPencil.add((myPencil));break;
				case "Eraser" : vectorEraser.add(myEraser);break;
			}
			firstMove = true;
			System.out.println("added");
			count+=1;
			repaint();
		}
	}
	
	
}

abstract class Shape {
	//variables
	protected int startX;
	protected int startY;
	protected int endX;
	protected int endY;
	protected String curr_shape;
	protected  Color color;
	protected int fill_pressed;
	protected boolean intialMouseMove = true;
	//constructor
	public Shape(int x1,int y1,int x2,int y2,String currentShape,Color color,int fillPressed){
		startX = x1;
		startY = y1;
		endX = x2;
		endY = y2;
		curr_shape = currentShape;
		this.color = color;
		fill_pressed = fillPressed;
	}
	public Shape(int x1,int y1,int x2,int y2,String currentShape,Color color){
		startX = x1;
		startY = y1;
		endX = x2;
		endY = y2;
		curr_shape = currentShape;
		this.color = color;
		
	}
	public Shape(int x1,int y1,int x2,int y2,String currentShape){
		startX = x1;
		startY = y1;
		endX = x2;
		endY = y2;
		curr_shape = currentShape;
	
	}
	public Shape(){};
	public Shape(Color color){this.color = color;}
	//methods
	abstract public void drawMyShape(Graphics g);
	//getters
	public String getCurrShape(){return curr_shape;}
	public  Color getCurrentColor(){return this.color;}
}

class Line extends Shape{
	//parameterized constructor to calling object from shape class
	Color color;
	public Line(int x1 , int y1, int x2 , int y2 , String currentShape,Color color){
		super(x1,y1,x2,y2,currentShape,color);
	}
	public Line(Color color){super(color);this.color = color;}
	public Color getLineChildColor(){return color;}
	public void drawMyShape(Graphics g){
		 g.setColor(super.getCurrentColor());
		 g.drawLine(startX,startY,endX,endY);
	}
	
	
	
}

class Circle extends Shape{
	//parameterized constructor
	public Circle(int x , int y , int width , int height , String currentShape,Color color,int fillPressed){
		super(x,y,width,height,currentShape,color,fillPressed);
	};
	public void drawMyShape(Graphics g){
		g.setColor(super.getCurrentColor());
		if(fill_pressed == 2){
			g.drawOval(startX,startY,endX,endY);
		}
		else if (fill_pressed ==1){
		    g.fillOval(startX,startY,endX,endY);
		}
	}
	
}

class Rectangle extends Shape{
	
	//parameterized constructor
	public Rectangle(int x , int y , int width , int height,String currentShape,Color color,int fillPressed){
		super(x,y,width,height,currentShape,color,fillPressed);
	}
	
	public Rectangle(int x , int y , int width , int height,String currentShape){
		super(x,y,width,height,currentShape);
	}
	
	public Rectangle(){}
	
	public void drawMyShape(Graphics g){
		g.setColor(super.getCurrentColor());
		if(fill_pressed == 2){
		  g.drawRect(super.startX,super.startY,super.endX,super.endY);
		 }
		else if(fill_pressed == 1){
		  g.fillRect(super.startX,super.startY,super.endX,super.endY);
		}
	}
}

class Pencil extends Line{
	Vector<Line> v_pencil = new Vector<Line>();
	public Pencil(Color color){super(color);}
	public void drawMyShape(Graphics g){
		g.setColor(super.getLineChildColor());
		for(int i = 0 ; i< v_pencil.size() ; i++){
		g.drawLine(v_pencil.get(i).startX,v_pencil.get(i).startY,v_pencil.get(i).endX,v_pencil.get(i).endY);
		}
		
	}
}

class Eraser extends Rectangle{
	Vector<Rectangle> v_Eraser = new Vector<Rectangle>();
	public void drawMyShape(Graphics g){
		g.setColor(Color.WHITE);
		for(int i = 0 ; i< v_Eraser.size() ; i++){
		g.fillRect(v_Eraser.get(i).startX,v_Eraser.get(i).startY,10,10);
		}
	}
}


