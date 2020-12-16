/*
 * -----------------------------------------------------------------------------
 *      Homework Assignment #10 - TIC TAC TOE game with GUI in Java
 * -----------------------------------------------------------------------------
 */
package tictactoe;

import java.awt.Color;
import javax.swing.JFrame;
/**
 *
 * @author ir at T3500
 */
public class TicTacToe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[]args) {
	TicTacToeFrontcons tc=new TicTacToeFrontcons();
	tc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	tc.getContentPane().setBackground(Color.yellow);
	tc.setSize(270,420);
	tc.setResizable(false);
    tc.setVisible(true);
}
    
}
