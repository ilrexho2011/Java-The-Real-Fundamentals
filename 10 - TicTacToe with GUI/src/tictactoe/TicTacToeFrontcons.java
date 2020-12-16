/*
 * -----------------------------------------------------------------------------
 *      Homework Assignment #10 - TIC TAC TOE game with GUI in Java
 * -----------------------------------------------------------------------------
 */
package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 *
 * @author ir at T3500
 */

class Point {
int x,y;
public Point(int x,int y) {
	this.x=x;
	this.y=y;
}
public String toString() {
    return "[" + x + ", " + y + "]";
}
}

 class PointAndScore {
	 int score;
	    Point point;

	    PointAndScore(int score, Point point) {
	        this.score = score;
	        this.point = point;
	    }

 }

 class Board {
	    List<Point> availablePoints;
	    Scanner scan = new Scanner(System.in);
	    int[][] board = new int[3][3];

	    public Board() {
	    }
	    
	    public void MakeMove(Point point,int player) {
	    	board[point.x][point.y]=player;
	    }

	    public boolean isGameOver() {
	        //Game is over if someone has won, or board is full (draw)
	        return (hasXWon() || hasOWon() || getAvailableStates().isEmpty());
	    }

	    public boolean hasXWon() {
	        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == 1) || (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == 1)) {
	            //System.out.println("X Diagonal Win");
	            return true;
	        }
	        for (int i = 0; i < 3; ++i) {
	            if (((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 1)
	                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 1))) {
	                // System.out.println("X Row or Column win");
	                return true;
	            }
	        }
	        return false;
	    }

	    public boolean hasOWon() {
	        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == 2) || (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == 2)) {
	            // System.out.println("O Diagonal Win");
	            return true;
	        }
	        for (int i = 0; i < 3; ++i) {
	            if ((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 2)
	                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 2)) {
	                //  System.out.println("O Row or Column win");
	                return true;
	            }
	        }

	        return false;
	    }

	    public List getAvailableStates() {
	        availablePoints = new ArrayList<>();
	        for (int i = 0; i < 3; ++i) {
	            for (int j = 0; j < 3; ++j) {
	                if (board[i][j] == 0) {
	                    availablePoints.add(new Point(i, j));
	                }
	            }
	        }
	        return availablePoints;
	    }


        public Point get_computer_move() {
        	return computersMove;
        }

	    public void placeAMove(Point point, int player) {
	        board[point.x][point.y] = player;   // player = 1 for X, 2 for O
	    }

	    void takeHumanInput() {
	        System.out.println("Your move: ");
	        int x = scan.nextInt();
	        int y = scan.nextInt();
	        Point point = new Point(x, y);
	        placeAMove(point, 2);
	    }

	    public void displayBoard() {
	        System.out.println();

	        for (int i = 0; i < 3; ++i) {
	            for (int j = 0; j < 3; ++j) {
	                if(board[i][j]==1){
	                    System.out.print("X ");
	                }else if(board[i][j]==2){
	                    System.out.print("O ");
	                }else{
	                    System.out.print("- ");
	                }
	            }
	            System.out.println();
	        }
	    }

	    Point computersMove;

	    public int minimax(int depth,int turn) {
	        if (hasXWon()) return +10-depth;
	        if (hasOWon()) return -10-depth;

	        List<Point> pointsAvailable = getAvailableStates();
	        if (pointsAvailable.isEmpty()) return 0;

	        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

	        for (int i = 0; i < pointsAvailable.size(); ++i) {
	            Point point = pointsAvailable.get(i);
	            if (turn == 1) {
	                placeAMove(point, 1);
	                int currentScore = minimax(depth+1,2);
                     if (currentScore>max) {
                    	 max=currentScore;
                     } 
                     if(currentScore >= 0){ 
	                	  
	                		computersMove = point;
	                	}
	                          
	                if(i == pointsAvailable.size()-1 && max < 0){
	                	
	                		computersMove = point;
	                	}
	            } else if (turn == 2) {
	                placeAMove(point, 2);
	                int currentScore = minimax(depth+1,1);
	                if (currentScore<min) {
	                	min=currentScore;
	                }
	               
	            }
	            board[point.x][point.y] = 0; // Reset this point
	        }
	        return turn == 1?max:min;
	    }
 }

public class TicTacToeFrontcons extends JFrame {
	//Declarations.
private JButton but1,but2,but3,but4,but5,but6,but7,but8,but0,play_again,but10,but12,but13,but14,but15,but16,but17,but18,but11;
private JLabel heading;
private int game_won,game_lost,game_tied,game_played;

private JOptionPane jpane;
Board brd=new Board();
private JLabel Author;

int [][]board=new int[3][3];

JButton[]button= {but10,but11,but12,but13,but14,but15,but16,but17,but18};
Icon x=new ImageIcon(getClass().getResource("X.png"));
Icon b=new ImageIcon(getClass().getResource("B.jpg"));
//The Constructor
public TicTacToeFrontcons() {
	super("TicTacToe");
	setLayout(null);
	heading=new JLabel("TIC TAC TOE");
	heading.setForeground(Color.RED);
	heading.setBounds(90, 10, 250, 40);
	add(heading);
	but0=new JButton("");
	but0.setBounds(5, 55, 70, 70);
	but0.setBackground(Color.WHITE);
	add(but0);
    
    but1=new JButton("");
	but1.setBounds(85, 55, 70, 70);
	but1.setBackground(Color.WHITE);
    add(but1);

    but2=new JButton("");
	but2.setBounds(165, 55, 70, 70);
	but2.setBackground(Color.WHITE);
    add(but2);
  
    but3=new JButton("");
	but3.setBounds(5, 135, 70, 70);
	but3.setBackground(Color.WHITE);
    add(but3);
  
    but4=new JButton("");
   	but4.setBounds(85, 135, 70, 70);
   	but4.setBackground(Color.WHITE);
    add(but4);
    
    but5=new JButton("");
   	but5.setBounds(165, 135, 70, 70);
   	but5.setBackground(Color.WHITE);
    add(but5);
    
    but6=new JButton("");
	but6.setBounds(5, 215, 70, 70);
	but6.setBackground(Color.WHITE);
    add(but6);
    
    but7=new JButton("");
   	but7.setBounds(85, 215, 70, 70);
   	but7.setBackground(Color.WHITE);
    add(but7);
   
    but8=new JButton("");
   	but8.setBounds(165, 215, 70, 70);
   	but8.setBackground(Color.WHITE);
    add(but8);
   
    play_again=new JButton("PLAY AGAIN");
    play_again.setBounds(55,300,120,40);
    add(play_again);
    
    Author=new JLabel("  Pirple Fundamentals of JAVA");
    Author.setForeground(Color.RED);
    Author.setBounds(0,350,200,40);
    add(Author);
    but0.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent event) {
    		    if(brd.hasXWon()==true) JOptionPane.showMessageDialog(null,"*** I won. ***");
    		    else if(brd.hasOWon()==true) JOptionPane.showMessageDialog(null,"*** You won.***"); 
    		   
    		    Icon o=new ImageIcon(getClass().getResource("0.png"));
    	        but0.setIcon(o);
    	        brd.MakeMove(new Point(0,0),2);
    	        brd.minimax(0,1);
    	        Point cmove=brd.get_computer_move();
    	        brd.MakeMove(cmove, 1);
    	        setX(cmove);
    	        if(brd.hasXWon()==true) JOptionPane.showMessageDialog(null,"*** I won. ***");
    		    else if(brd.hasOWon()==true) JOptionPane.showMessageDialog(null,"*** You won.***"); 
    	}
    	
    });

    but1.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent event) {
    		    if(brd.hasXWon()==true) JOptionPane.showMessageDialog(null,"*** I won. ***");
    		    else if(brd.hasOWon()==true) JOptionPane.showMessageDialog(null,"*** You won.***"); 
    		    
    		    Icon o=new ImageIcon(getClass().getResource("0.png"));
    	        but1.setIcon(o);
    	        brd.MakeMove(new Point(0,1),2);
    	        brd.minimax(0,1);
    	        Point cmove=brd.get_computer_move();
    	        brd.MakeMove(cmove, 1);
    	        setX(cmove);
    	        if(brd.hasXWon()==true) JOptionPane.showMessageDialog(null,"*** I won. ***");
    		    else if(brd.hasOWon()==true) JOptionPane.showMessageDialog(null,"*** You won.***"); 
    	}
    	
    });
    
    but2.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent event) {
    		    if(brd.hasXWon()==true) JOptionPane.showMessageDialog(null,"*** I won. ***");
    		    else if(brd.hasOWon()==true) JOptionPane.showMessageDialog(null,"*** You won.***");
    		   
    		    Icon o=new ImageIcon(getClass().getResource("0.png"));
    	        but2.setIcon(o);
    	        brd.MakeMove(new Point(0,2),2);
    	        brd.minimax(0,1);
    	        Point cmove=brd.get_computer_move();
    	        brd.MakeMove(cmove, 1);
    	        setX(cmove);
    	        if(brd.hasXWon()==true) JOptionPane.showMessageDialog(null,"*** I won. ***");
    		    else if(brd.hasOWon()==true) JOptionPane.showMessageDialog(null,"*** You won.***"); 
    	}
    	
    });
    
    but3.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent event) {
    		    if(brd.hasXWon()==true) JOptionPane.showMessageDialog(null,"*** I won. ***");
    		    else if(brd.hasOWon()==true) JOptionPane.showMessageDialog(null,"*** You won.***"); 
    		    
    		    Icon o=new ImageIcon(getClass().getResource("0.png"));
    	        but3.setIcon(o);
    	        brd.MakeMove(new Point(1,0),2);
    	        brd.minimax(0,1);
    	        Point cmove=brd.get_computer_move();
    	        brd.MakeMove(cmove, 1);
    	        setX(cmove);
    	        if(brd.hasXWon()==true) JOptionPane.showMessageDialog(null,"*** I won. ***");
    		    else if(brd.hasOWon()==true) JOptionPane.showMessageDialog(null,"*** You won.***"); 
    	}
    	
    });
    
    but4.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent event) {
    		    if(brd.hasXWon()==true) JOptionPane.showMessageDialog(null,"*** I won. ***");
    		    else if(brd.hasOWon()==true) JOptionPane.showMessageDialog(null,"*** You won.***"); 
    		    
    		    Icon o=new ImageIcon(getClass().getResource("0.png"));
    	        but4.setIcon(o);
    	        brd.MakeMove(new Point(1,1),2);
    	        brd.minimax(0,1);
    	        Point cmove=brd.get_computer_move();
    	        brd.MakeMove(cmove, 1);
    	        setX(cmove);
    	        if(brd.hasXWon()==true) JOptionPane.showMessageDialog(null,"*** I won. ***");
    		    else if(brd.hasOWon()==true) JOptionPane.showMessageDialog(null,"*** You won.***"); 
    	}
    	
    });
   
    but5.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent event) {
    		    if(brd.hasXWon()==true) JOptionPane.showMessageDialog(null,"*** I won. ***");
    		    else if(brd.hasOWon()==true) JOptionPane.showMessageDialog(null,"*** You won.***");
    		    
    		    Icon o=new ImageIcon(getClass().getResource("0.png"));
    	        but5.setIcon(o);
    	        brd.MakeMove(new Point(1,2),2);
    	        brd.minimax(0,1);
    	        Point cmove=brd.get_computer_move();
    	        brd.MakeMove(cmove, 1);
    	        setX(cmove);
    	        if(brd.hasXWon()==true) JOptionPane.showMessageDialog(null,"*** I won. ***");
    		    else if(brd.hasOWon()==true) JOptionPane.showMessageDialog(null,"*** You won.***"); 
    	}
    	
    });
    
    but6.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent event) {
    		    if(brd.hasXWon()==true) JOptionPane.showMessageDialog(null,"*** I won. ***");
    		    else if(brd.hasOWon()==true) JOptionPane.showMessageDialog(null,"*** You won.***"); 
    		    
    		    Icon o=new ImageIcon(getClass().getResource("0.png"));
    	        but6.setIcon(o);
    	        brd.MakeMove(new Point(2,0),2);
    	        brd.minimax(0,1);
    	        Point cmove=brd.get_computer_move();
    	        brd.MakeMove(cmove, 1);
    	        setX(cmove);
    	        if(brd.hasXWon()==true) JOptionPane.showMessageDialog(null,"*** I won. ***");
    		    else if(brd.hasOWon()==true) JOptionPane.showMessageDialog(null,"*** You won.***"); 
    	}
    	
    });
    
    but7.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent event) {
    		    if(brd.hasXWon()==true) JOptionPane.showMessageDialog(null,"*** I won. ***");
    		    else if(brd.hasOWon()==true) JOptionPane.showMessageDialog(null,"*** You won.***"); 
    		    
    		    Icon o=new ImageIcon(getClass().getResource("0.png"));
    	        but7.setIcon(o);
    	        brd.MakeMove(new Point(2,1),2);
    	        brd.minimax(0,1);
    	        Point cmove=brd.get_computer_move();
    	        brd.MakeMove(cmove, 1);
    	        setX(cmove);
    	        if(brd.hasXWon()==true) JOptionPane.showMessageDialog(null,"*** I won. ***");
    		    else if(brd.hasOWon()==true) JOptionPane.showMessageDialog(null,"*** You won.***"); 
    	}
    	
    });
    
    but8.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent event) {
    		    if(brd.hasXWon()==true) JOptionPane.showMessageDialog(null,"*** I won. ***");
    		    else if(brd.hasOWon()==true) JOptionPane.showMessageDialog(null,"*** You won.***");
    		    
    		    Icon o=new ImageIcon(getClass().getResource("0.png"));
    	        but8.setIcon(o);
    	        brd.MakeMove(new Point(2,2),2);
    	        brd.minimax(0,1);
    	        Point cmove=brd.get_computer_move();
    	        brd.MakeMove(cmove, 1);
    	        setX(cmove);
    	        if(brd.hasXWon()==true) JOptionPane.showMessageDialog(null,"*** I won. ***");
    		    else if(brd.hasOWon()==true) JOptionPane.showMessageDialog(null,"*** You won.***"); 
    	}
    	
    	
    });
    
    //Play Again.
    play_again.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent event) {
    		for (int i=0;i<3;i++) {
    			for (int j=0;j<3;j++) {
    				board[i][j]=0;
    			}
    			
    		}
    		but0.setIcon(b);
    		but1.setIcon(b);
    		but2.setIcon(b);
    		but3.setIcon(b);
    		but4.setIcon(b);
    		but5.setIcon(b);
    		but6.setIcon(b);
    		but7.setIcon(b);
    		but8.setIcon(b);
    		}
    	
    });
   
	
	}
//Algorithms and methods.

public  void setX(Point cmove) {
	if ((cmove.x==0)&&(cmove.y==0)) {
		but0.setIcon(x);
	}
	else if ((cmove.x==0)&&(cmove.y==1)) {
		but1.setIcon(x);
	}
	else if ((cmove.x==0)&&(cmove.y==2)) {
		but2.setIcon(x);
	}
	else if ((cmove.x==1)&&(cmove.y==0)) {
		but3.setIcon(x);
	}
	else if ((cmove.x==1)&&(cmove.y==1)) {
		but4.setIcon(x);
	}
	else if ((cmove.x==1)&&(cmove.y==2)) {
		but5.setIcon(x);
	}
	else if ((cmove.x==2)&&(cmove.y==0)) {
		but6.setIcon(x);
	}
	else if ((cmove.x==2)&&(cmove.y==1)) {
		but7.setIcon(x);
	}
	else if ((cmove.x==2)&&(cmove.y==2)) {
		but8.setIcon(x);
	}
}
	
}







