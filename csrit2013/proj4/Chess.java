import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/*
 * Chess.java
 *
 * Version:
 *     $Id: Chess.java,v 1.5 2013/12/13 02:43:14 rhv5251 Exp rhv5251 $
 *
 * Revisions:
 *     $Log: Chess.java,v $
 *     Revision 1.5  2013/12/2 02:43:14  rhv5251
 *     Finished working program 
 *
 *     Revision 1.4  2013/12/9 05:15:26  rhv5251
 *     gui and chess finished
 *      
 *     Revision 1.3  2013/12/9 03:05:53  rhv5251
 *     second
 *
 *     Revision 1.2  2013/12/8 01:34:10  rhv5251
 *     first
 *
 */
 
/*
 * 
 * 
 * @author Ryan Vogt
 * A class made to represent a chess game and play it 
 */
public class Chess extends JFrame implements Puzzle<ArrayList<Integer>> {
	//class variables
	private int[][] chessBoard;
	private int moveCounter = 0;
	private BufferedReader myReader;
	private int yDimension;
	private int xDimension;
	private int pieceCounter = 0;
	private ArrayList<Integer> baseConfig;
	private ArrayList<ArrayList<Integer>> basicConfig;
	int[][] originalBoard;
	private JPanel head;
	private JPanel center;
	private JPanel bottom;
	private JButton reset;
	private JButton cheat;
	private JButton quit;
	private JButton undo;
	private JLabel moveLabel;
	private Container myContainer;
	private Solver mySolver;
	private ArrayList<ArrayList<Integer>> myConfig;
	private JButton[][] chessButtons;
	private ArrayList<Integer> chessBoardList;
	private JButton firstClickedButton;
	private JButton secondClickedButton;
	private int clickCounter = 0;
	private JButton[][] OriginalJButtonCollection;
	private int firstButtonx = 0;
	private int firstButtony = 0;
	private int secondButtonx = 0;
	private int secondButtony = 0;
	private JTextField messageField;
	private ArrayList<ArrayList<Integer>> currentConfig = null;
	private Chess myChess;
	private ArrayList<int[][]> undoList;
	private boolean hasUndone = false;
	private boolean noCheat=false;
	private boolean gameOver=false;
	
	//class constructor
	//@param inputFile - the file holding configs of the original 
	//chess board
	public Chess(String inputFile) throws IOException {
		myChess = this;
		mySolver = new Solver(this);
		myConfig = new ArrayList<ArrayList<Integer>>();
		setReader(inputFile);
		setBasicBoard();
		myConfig.add(changeTwoDimensiontoOneDimension(chessBoard,
				fillWithZeros(new ArrayList<Integer>())));
		System.out.println(mySolver.BFSSolver(myConfig));
		makeGui();
		// lastConfigBoard = new int[yDimension][xDimension];
		undoList = new ArrayList<int[][]>();
	}
	//method to fill the body of a frame with the chess board
	//@param myPanel the center panel which holds the board
	public void fillCenterWithButtons(JPanel myPanel) {
		JButton current = null;
		int counter = 0;
		int colorCounter = 0;
		chessBoardList = changeTwoDimensiontoOneDimension(chessBoard,
				fillWithZeros(new ArrayList<Integer>()));
		chessButtons = new JButton[yDimension][xDimension];
		for (int y = 0; y < yDimension; y++) {
			for (int x = 0; x < xDimension; x++) {

				if (chessBoardList.get(counter) != 0) {

					current = new JButton(
							changeNumberToStringRepresentation(chessBoardList
									.get(counter)));
					current.setForeground(Color.BLUE);
					current.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							if (clickCounter == 0) {
								firstClickedButton = (JButton) e.getSource();
								clickCounter++;

							} else {

								secondClickedButton = (JButton) e.getSource();
								clickCounter = 0;

								if (validMove(firstClickedButton,
										secondClickedButton)) {

									makeMoveHappen();
									updateGUI();
									if (getStart() == 1) {
										messageField.setText("You win!");
									

									}
								} else {
									messageField.setText("Bad Move");

								}

							}

						}

					});

					if (colorCounter % 2 == 0) {

						current.setBackground(Color.black);

					} else {

						current.setBackground(Color.white);
					}

				} else {

					current = new JButton("");
					current.setForeground(Color.BLUE);
					current.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							if (clickCounter == 0) {
								firstClickedButton = (JButton) e.getSource();
								clickCounter++;

							} else {

								secondClickedButton = (JButton) e.getSource();
								clickCounter = 0;
								if (validMove(firstClickedButton,
										secondClickedButton)) {

									makeMoveHappen();
									updateGUI();

									// System.out.println(mySolver.BFSSolver(originalConfig))
									if (getStart() == 1) {
										messageField.setText("You win!");

									}
								} else {

									messageField.setText("Bad Input");

								}

							}

						}

					});

					if (colorCounter % 2 == 0) {

						current.setBackground(Color.black);

					} else {

						current.setBackground(Color.white);
					}

				}

				counter++;
				colorCounter++;
				myPanel.add(current);
				chessButtons[y][x] = current;

			}
		}
		OriginalJButtonCollection = chessButtons;

	}
	//When undo is done, call this method which takes
	//the board a move back
	public void updateLastConfig() {
		if (moveCounter == 0) {
			// System.out.println("0");
		} else {
			
			if(gameOver)
			{
				gameOver=false;

				moveCounter--;
				moveLabel.setText("Moves so far " + moveCounter);
				messageField.setText("Good Move");

				int[][] setLast = undoList.get(undoList.size() - 1);
				undoList.remove(undoList.size() - 1);
				chessBoard = setLast;

				for (int y = 0; y < yDimension; y++) {

					for (int x = 0; x < xDimension; x++) {
						JButton currentButton = chessButtons[y][x];
						if (setLast[y][x] != 0) {

							currentButton
									.setText(changeNumberToStringRepresentation(setLast[y][x]));

						} else {
							currentButton.setText("");

						}

					}

				}
				hasUndone = true;
			}
			else
			{
				
				moveCounter--;
				moveLabel.setText("Moves so far " + moveCounter);
				messageField.setText("Good Move");

				int[][] setLast = undoList.get(undoList.size() - 1);
				undoList.remove(undoList.size() - 1);
				chessBoard = setLast;

				for (int y = 0; y < yDimension; y++) {

					for (int x = 0; x < xDimension; x++) {
						JButton currentButton = chessButtons[y][x];
						if (setLast[y][x] != 0) {

							currentButton
									.setText(changeNumberToStringRepresentation(setLast[y][x]));

						} else {
							currentButton.setText("");

						}

					}

				}
				hasUndone = true;

			}
			}
			// System.out.println("1");
			hasUndone = true;
			
			
	}
	//updates the gui when something changes in the state of the game
	public void updateGUI() {
		moveCounter++;
		moveLabel.setText("Moves so far " + moveCounter);
		messageField.setText("Good Move");
		for (int y = 0; y < yDimension; y++) {

			for (int x = 0; x < xDimension; x++) {
				JButton currentButton = chessButtons[y][x];
				if (chessBoard[y][x] != 0) {

					currentButton
							.setText(changeNumberToStringRepresentation(chessBoard[y][x]));

				} else {
					currentButton.setText("");

				}

			}

		}
	}
	//if a move is determined to be valid
	//for a specific piece to another piece
	//then make the piece move to that piece
	public void makeMoveHappen() {

		undoList.add(copyTwoDimensionalArray(chessBoard));
		chessBoard[firstButtony][firstButtonx] = 0;
		chessBoard[secondButtony][secondButtonx] = changeLetterToNumberRepresentation(firstClickedButton
				.getText());
		ArrayList<ArrayList<Integer>> newConfig = new ArrayList<ArrayList<Integer>>();
		newConfig.add(changeTwoDimensiontoOneDimension(
				copyTwoDimensionalArray(chessBoard),
				fillWithZeros(new ArrayList<Integer>())));
		Solver thisSolver = new Solver(myChess);
		thisSolver.BFSSolver(newConfig);
		Solver newSolver = new Solver(myChess);
		System.out.println(newSolver.BFSSolver(newConfig));

	}
	//constructs the original gui of the board
	//adding listeners to repsond when things are clicked
	//to play the game
	public void makeGui() {
		messageField = new JTextField();
		messageField.setSize(300, 100);
		messageField.setText("Game Messages");
		head = new JPanel();
		center = new JPanel(new GridLayout(yDimension, xDimension));
		bottom = new JPanel();
		reset = new JButton("Reset");
		cheat = new JButton("Cheat Move");
		quit = new JButton("Quit");
		undo = new JButton("Undo");
		moveLabel = new JLabel("Moves so far " + moveCounter);
		myContainer = new Container();
		myContainer.setLayout(new BorderLayout());
		fillCenterWithButtons(center);
		
		//Construct listeners on buttons
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				moveCounter = 0;
				moveLabel.setText("Moves so far " + moveCounter);
				messageField.setText("Game Reset");
				chessBoard = copyTwoDimensionalArray(originalBoard);
				makeGUIOriginal();
				ArrayList<ArrayList<Integer>> newConfig = new ArrayList<ArrayList<Integer>>();
				newConfig.add(changeTwoDimensiontoOneDimension(
						copyTwoDimensionalArray(chessBoard),
						fillWithZeros(new ArrayList<Integer>())));
				Solver thisSolver = new Solver(myChess);
				thisSolver.BFSSolver(newConfig);
				undoList.clear();
				hasUndone = false;
				noCheat=false;
				gameOver=false;

			}

		});
		cheat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					
					if (isThereNoSolution(chessBoard)) {
						messageField.setText("No Cheat Avaliable:Game Over");
						noCheat=true;
						gameOver=true;

					} 
					
					if(!gameOver)	
					{
						undoList.add(chessBoard);
						

						if (hasUndone && moveCounter == 0) {
							hasUndone = false;
							chessBoard = copyTwoDimensionalArray(changeOneDimensiontoTwoDimension(
									new int[yDimension][xDimension],
									currentConfig.get(1)));

						} else if (hasUndone) {
							hasUndone = false;
							chessBoard = copyTwoDimensionalArray(changeOneDimensiontoTwoDimension(
									new int[yDimension][xDimension],
									currentConfig.get(1)));

						} else {
							chessBoard = copyTwoDimensionalArray(changeOneDimensiontoTwoDimension(
									new int[yDimension][xDimension],
									currentConfig.get(1)));
						}

						updateGUI();
						if (getStart() == 1) {
							messageField.setText("You win!");
							gameOver=true;
						}
						ArrayList<ArrayList<Integer>> aConfig = new ArrayList<ArrayList<Integer>>();
						aConfig.add(changeTwoDimensiontoOneDimension(
								copyTwoDimensionalArray(chessBoard),
								fillWithZeros(new ArrayList<Integer>())));
						Solver myCurrentSolver = new Solver(myChess);
						myCurrentSolver.BFSSolver(aConfig);
					}
				} catch (IndexOutOfBoundsException s) {
					System.out.println("Game Over");
				}

			}

		});
		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}

		});
		undo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateLastConfig();

			}

		});
		head.add(moveLabel);
		bottom.add(reset);
		bottom.add(cheat);
		bottom.add(quit);
		bottom.add(undo);
		bottom.add(messageField);
		myContainer.add(head, BorderLayout.NORTH);
		myContainer.add(center, BorderLayout.CENTER);
		myContainer.add(bottom, BorderLayout.SOUTH);
		this.add(myContainer);
		this.setTitle("Chess Ryan Vogt rhv5251");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		JOptionPane
				.showMessageDialog(
						null,
						"Try to take pieces, click a piece then click on another to take it if valid, game over when only one piece is left or impossible to end game",
						"How To Play", JOptionPane.INFORMATION_MESSAGE);

	}
	
	//sees if there is a solution based on the current gameboard
	//@param gameboard the current state of the board game
	public boolean isThereNoSolution(int[][] gameboard) {
		ArrayList<ArrayList<Integer>> aConfig = new ArrayList<ArrayList<Integer>>();
		aConfig.add(changeTwoDimensiontoOneDimension(
				copyTwoDimensionalArray(gameboard),
				fillWithZeros(new ArrayList<Integer>())));
		Solver myCurrentSolver = new Solver(myChess);
		String result = myCurrentSolver.BFSSolver(aConfig);
		if (result.equals("No Solutions")) {
			return true;
		} else {
			return false;
		}

	}
	//when reset is pressed, reverts the GUI to basic config status
	public void makeGUIOriginal() {
		for (int y = 0; y < yDimension; y++) {

			for (int x = 0; x < xDimension; x++) {
				JButton current = chessButtons[y][x];
				if (originalBoard[y][x] != 0) {

					current.setText(changeNumberToStringRepresentation(originalBoard[y][x]));

				} else {
					current.setText("");

				}

			}

		}

	}
	//makes a buffer to read the input file
	//@param inputFile the file to be read
	public void setReader(String inputFile) {

		try {
			myReader = new BufferedReader(new FileReader(inputFile));

		}

		catch (FileNotFoundException e) {
			System.out.println(inputFile + " not found.");
			System.exit(0);

		}

	}
		//creates the data structure from input text which will be 
		// a 2d array representing pieces by numbers
		//this will be the representation of the game board
		//which will be mnaipulated throughout the program
	public void setBasicBoard() throws IOException {
		int lineCounter = -1;
		String currentLine;
		while ((currentLine = myReader.readLine()) != null) {

			if (lineCounter == -1) {
				String[] firstLine = currentLine.split(" ");
				if (firstLine.length > 2) {
					System.out.println("Bad Board Dimenions");
					System.exit(0);

				}
				yDimension = Integer.parseInt(firstLine[0]);
				xDimension = Integer.parseInt(firstLine[1]);
				chessBoard = new int[yDimension][xDimension];

				lineCounter++;
			} else {
				String[] row = currentLine.split(" ");

				if (row.length != xDimension || lineCounter > yDimension) {
					System.out
							.println("Board dimensions doesnt work with board input lines");
					System.exit(0);

				}
				for (int x = 0; x < row.length; x++) {

					if (changeLetterToNumberRepresentation(row[x]) != 100) {
						chessBoard[lineCounter][x] = changeLetterToNumberRepresentation(row[x]);
					} else {

						System.out
								.println("Bad input for board, non chess piece found");
						System.exit(0);
					}

				}

				lineCounter++;

			}

		}

		originalBoard = copyTwoDimensionalArray(chessBoard);

	}
	//sees based on which buttons pressed if a move from one to other is
	//valid based on what sort of piece the first pressed is
	//@param one the first button pressed
	//@param two the second button pressed
	public boolean validMove(JButton one, JButton two) {
		firstButtonx = 0;
		firstButtony = 0;
		secondButtonx = 0;
		secondButtony = 0;
		String oneText = one.getText();

		for (int y = 0; y < yDimension; y++) {

			for (int x = 0; x < xDimension; x++) {

				if (chessButtons[y][x] == one) {
					firstButtonx = x;
					firstButtony = y;

				}
				if (chessButtons[y][x] == two) {
					secondButtonx = x;
					secondButtony = y;

				}

			}

		}

		if (firstButtonx == secondButtonx && firstButtony == secondButtony) {
			return false;

		} else {
			return checkValidityOfMove(one.getText(), firstButtonx,
					firstButtony, secondButtonx, secondButtony);

		}

	}
	//sees if a move of the first clicked button based on what sort of piece it is is ineed
	//valid
	//@param firstButton the name of the first button
	//@param firstX - the x cor of firstbutton
	//@param firstY the y cor of firstbutton
	//@param secondY the y cor of secondbutton
	//@param secondX the x cor of secondbutton
	public boolean checkValidityOfMove(String firstButtonName, int firstX,
			int firstY, int secondX, int secondY) {
		if (firstButtonName.equals("B")) {
			return checkBishop(firstX, firstY, secondX, secondY);

		} else if (firstButtonName.equals("K")) {
			return checkKing(firstX, firstY, secondX, secondY);

		} else if (firstButtonName.equals("N")) {
			return checkKnight(firstX, firstY, secondX, secondY);

		} else if (firstButtonName.equals("P")) {
			return checkPawn(firstX, firstY, secondX, secondY);

		} else if (firstButtonName.equals("R")) {
			return checkRook(firstX, firstY, secondX, secondY);

		} else if (firstButtonName.equals("Q")) {
			return checkQueen(firstX, firstY, secondX, secondY);

		}

		return false;
	}
	//sees if a bishop move is valid
	//@param firstX - the x cor of firstbutton
		//@param firstY the y cor of firstbutton
		//@param secondY the y cor of secondbutton
		//@param secondX the x cor of secondbutton
	
	public boolean checkBishop(int firstX, int firstY, int secondX, int secondY) {

		for (int i = 1; i < giveBiggerDimension(); i++) {
			int minfirstx = firstX - i;
			int maxfirstx = firstX + i;
			int minfirsty = firstY - i;
			int maxfirsty = firstY + i;

			if (minfirsty >= 0 && maxfirstx < xDimension) {
				if (chessBoard[minfirsty][maxfirstx] != 0
						&& secondX == maxfirstx && secondY == minfirsty) {
					return true;

				}
			}
			if (maxfirsty < yDimension && maxfirstx < xDimension) {
				if (chessBoard[maxfirsty][maxfirstx] != 0
						&& secondX == maxfirstx && secondY == maxfirsty) {
					return true;

				}
			}

			if (minfirsty >= 0 && minfirstx >= 0) {
				if (chessBoard[minfirsty][minfirstx] != 0
						&& secondX == minfirstx && secondY == minfirsty) {
					return true;

				}
			}
			if (maxfirsty < yDimension && minfirstx >= 0) {
				if (chessBoard[maxfirsty][minfirstx] != 0
						&& secondX == minfirstx && secondY == maxfirsty) {
					return true;

				}
			}

		}

		return false;
	}
	//sees if a Knight move is valid
		//@param firstX - the x cor of firstbutton
			//@param firstY the y cor of firstbutton
			//@param secondY the y cor of secondbutton
			//@param secondX the x cor of secondbutton
	public boolean checkKnight(int firstX, int firstY, int secondX, int secondY) {
		int firstXmax2 = firstX + 2;
		int firstXmin2 = firstX - 2;
		int firstYmax2 = firstY + 2;
		int firstYmin2 = firstY - 2;
		int firstXmax1 = firstX + 1;
		int firstXmin1 = firstX - 1;
		int firstYmax1 = firstY + 1;
		int firstYmin1 = firstY - 1;
		if (firstYmin2 >= 0 && firstXmax1 < xDimension && firstYmin2 == secondY
				&& firstXmax1 == secondX
				&& chessBoard[firstYmin2][firstXmax1] != 0) {
			return true;

		} else if (firstYmin1 >= 0 && firstXmax2 < xDimension
				&& firstYmin1 == secondY && firstXmax2 == secondX
				&& chessBoard[firstYmin1][firstXmax2] != 0) {
			return true;
		} else if (firstYmax1 < yDimension && firstXmax2 < xDimension
				&& firstYmax1 == secondY && firstXmax2 == secondX
				&& chessBoard[firstYmax1][firstXmax2] != 0) {
			return true;
		} else if (firstYmax2 < yDimension && firstXmax1 < xDimension
				&& firstYmax2 == secondY && firstXmax1 == secondX
				&& chessBoard[firstYmax2][firstXmax1] != 0) {
			return true;
		} else if (firstYmax2 < yDimension && firstXmin1 >= 0
				&& firstYmax2 == secondY && firstXmin1 == secondX
				&& chessBoard[firstYmax2][firstXmin1] != 0) {
			return true;
		} else if (firstYmax1 < yDimension && firstXmin2 >= 0
				&& firstYmax1 == secondY && firstXmin2 == secondX
				&& chessBoard[firstYmax1][firstXmin2] != 0) {
			return true;
		} else if (firstYmin1 >= 0 && firstXmin2 >= 0 && firstYmin1 == secondY
				&& firstXmin2 == secondX
				&& chessBoard[firstYmin1][firstXmin2] != 0) {
			return true;
		} else if (firstYmin2 >= 0 && firstXmin1 >= 0 && firstYmin2 == secondY
				&& firstXmin1 == secondX
				&& chessBoard[firstYmin2][firstXmin1] != 0) {
			return true;
		}
		return false;
	}
	//sees if a king move is valid
		//@param firstX - the x cor of firstbutton
			//@param firstY the y cor of firstbutton
			//@param secondY the y cor of secondbutton
			//@param secondX the x cor of secondbutton
	public boolean checkKing(int firstX, int firstY, int secondX, int secondY) {

		if (firstX + 1 < xDimension && firstY + 1 < yDimension) {
			if (firstX + 1 == secondX && firstY + 1 == secondY
					&& chessBoard[secondY][secondX] != 0) {
				return true;
			}
		}

		if (firstX + 1 < xDimension && firstY - 1 >= 0) {
			if (firstX + 1 == secondX && firstY - 1 == secondY
					&& chessBoard[secondY][secondX] != 0) {

				return true;

			}
		}
		if (firstX - 1 >= 0 && firstY + 1 < yDimension) {
			if (firstX - 1 == secondX && firstY + 1 == secondY
					&& chessBoard[secondY][secondX] != 0) {
				return true;
			}
		}
		if (firstX - 1 >= 0 && firstY - 1 >= 0) {
			if (firstX - 1 == secondX && firstY - 1 == secondY
					&& chessBoard[secondY][secondX] != 0) {
				return true;
			}
		}
		// rook proeperty
		if (firstX + 1 < xDimension) {
			if (firstX + 1 == secondX && firstY == secondY
					&& chessBoard[secondY][secondX] != 0)

			{
				return true;
			}
		}
		if (firstY + 1 < yDimension) {
			if (firstX == secondX && firstY + 1 == secondY
					&& chessBoard[secondY][secondX] != 0) {
				return true;
			}
		}
		if (firstX - 1 >= 0) {
			if (firstX - 1 == secondX && firstY == secondY
					&& chessBoard[secondY][secondX] != 0) {
				return true;
			}
		}
		if (firstY - 1 >= 0) {
			if (firstX == secondX && firstY - 1 == secondY
					&& chessBoard[secondY][secondX] != 0) {
				return true;
			}
		}

		return false;

	}
	//sees if a pawn move is valid
		//@param firstX - the x cor of firstbutton
			//@param firstY the y cor of firstbutton
			//@param secondY the y cor of secondbutton
			//@param secondX the x cor of secondbutton
	public boolean checkPawn(int firstX, int firstY, int secondX, int secondY) {
		

		if (firstX + 1 < xDimension && firstY + 1 < yDimension) {
			if (firstX + 1 == secondX && firstY + 1 == secondY
					&& chessBoard[firstY + 1][firstX + 1] != 0) {
				return true;
			}
		}

		if (firstX + 1 < xDimension && firstY - 1 >= 0) {
			if (firstX + 1 == secondX && firstY - 1 == secondY
					&& chessBoard[firstY - 1][firstX + 1] != 0) {

				return true;

			}
		}
		if (firstX - 1 >= 0 && firstY + 1 < yDimension) {
			if (firstX - 1 == secondX && firstY + 1 == secondY
					&& chessBoard[firstY + 1][firstX - 1] != 0) {
				return true;
			}
		}
		if (firstX - 1 >= 0 && firstY - 1 >= 0) {
			if (firstX - 1 == secondX && firstY - 1 == secondY
					&& chessBoard[firstY - 1][firstX - 1] != 0) {
				return true;
			}
		}

		return false;
	}
	//sees if a rook move is valid
		//@param firstX - the x cor of firstbutton
			//@param firstY the y cor of firstbutton
			//@param secondY the y cor of secondbutton
			//@param secondX the x cor of secondbutton
	public boolean checkRook(int firstX, int firstY, int secondX, int secondY) {
		for (int i = 1; i < giveBiggerDimension(); i++) {

			if (firstX + i < xDimension) {

				if (firstX + i == secondX && firstY == secondY
						&& chessBoard[firstY][firstX + i] != 0) {

					return true;

				}

			}
			if (firstX - 1 >= 0) {

				if (firstX - i == secondX && firstY == secondY
						&& chessBoard[firstY][firstX - i] != 0) {

					return true;

				}

			}
			if (firstY + 1 < yDimension) {

				if (firstX == secondX && firstY + i == secondY
						&& chessBoard[firstY + i][firstX] != 0) {

					return true;

				}

			}
			if (firstY - 1 >= 0) {

				if (firstX == secondX && firstY - i == secondY
						&& chessBoard[firstY - i][firstX] != 0) {

					return true;

				}

			}

		}

		return false;
	}
	//sees if a queen move is valid
		//@param firstX - the x cor of firstbutton
			//@param firstY the y cor of firstbutton
			//@param secondY the y cor of secondbutton
			//@param secondX the x cor of secondbutton
	public boolean checkQueen(int firstX, int firstY, int secondX, int secondY) {
		for (int i = 1; i < giveBiggerDimension(); i++) {

			if (firstX + i < xDimension) {

				if (firstX + i == secondX && firstY == secondY
						&& chessBoard[firstY][firstX + i] != 0) {

					return true;

				}

			}
			if (firstX - 1 >= 0) {

				if (firstX - i == secondX && firstY == secondY
						&& chessBoard[firstY][firstX - i] != 0) {

					return true;

				}

			}
			if (firstY + 1 < yDimension) {

				if (firstX == secondX && firstY + i == secondY
						&& chessBoard[firstY + i][firstX] != 0) {

					return true;

				}

			}
			if (firstY - 1 >= 0) {

				if (firstX == secondX && firstY - i == secondY
						&& chessBoard[firstY - i][firstX] != 0) {

					return true;

				}

			}

		}

		for (int i = 1; i < giveBiggerDimension(); i++) {
			int minfirstx = firstX - i;
			int maxfirstx = firstX + i;
			int minfirsty = firstY - i;
			int maxfirsty = firstY + i;

			if (minfirsty >= 0 && maxfirstx < xDimension) {
				if (chessBoard[minfirsty][maxfirstx] != 0
						&& secondX == maxfirstx && secondY == minfirsty) {
					return true;

				}
			}
			if (maxfirsty < yDimension && maxfirstx < xDimension) {
				if (chessBoard[maxfirsty][maxfirstx] != 0
						&& secondX == maxfirstx && secondY == maxfirsty) {
					return true;

				}
			}

			if (minfirsty >= 0 && minfirstx >= 0) {
				if (chessBoard[minfirsty][minfirstx] != 0
						&& secondX == minfirstx && secondY == minfirsty) {
					return true;

				}
			}
			if (maxfirsty < yDimension && minfirstx >= 0) {
				if (chessBoard[maxfirsty][minfirstx] != 0
						&& secondX == minfirstx && secondY == maxfirsty) {
					return true;

				}
			}

		}

		return false;
	}
	//returns a string representation of a 2d array
	//@param toPrint the array wanted to be prnted
	public String printArray(int[][] toPrint) {
		String finalString = "";

		for (int y = 0; y < yDimension; y++) {
			if (y > 0) {
				finalString = finalString + "\n";
			}
			for (int x = 0; x < xDimension; x++) {

				finalString = finalString
						+ changeNumberToStringRepresentation(toPrint[y][x]);

			}

		}
		return finalString;
	}
	//takes a letter and changes it to a number representation
	//@param letter the letter wanted to be converted to a number
	public int changeLetterToNumberRepresentation(String letter) {

		if (letter.equals(".")) {
			return 0;
		} else if (letter.equals("B")) {
			return 1;
		} else if (letter.equals("K")) {
			return 2;
		} else if (letter.equals("N")) {
			return 3;
		} else if (letter.equals("P")) {
			return 4;
		} else if (letter.equals("R")) {
			return 5;
		} else if (letter.equals("Q")) {
			return 6;
		}

		return 100;

	}
	//changes a number to string representation
	//@param numer the number wanting to be changed to string representation
	public String changeNumberToStringRepresentation(int number) {

		if (number == 0) {
			return ".";
		} else if (number == 1) {

			return "B";
		} else if (number == 2) {
			return "K";

		} else if (number == 3) {

			return "N";
		} else if (number == 4) {

			return "P";
		} else if (number == 5) {

			return "R";
		} else if (number == 6) {

			return "Q";
		}

		return "No Conversion";

	}

	//checks if an arraylist is the goal of having only one piece
	//@param myList the list containing the representation of the chess
	//board
	//@retun isGoal
	@Override
	public boolean isGoal(ArrayList<ArrayList<Integer>> myList) {
		int pieceCounter = 0;
		Iterator<ArrayList<Integer>> it = myList.iterator();
		while (it.hasNext()) {
			ArrayList<Integer> current = it.next();

			Iterator<Integer> it1 = current.iterator();
			while (it1.hasNext()) {
				int currentInt = it1.next();
				if (currentInt != 0) {
					pieceCounter++;
				}

			}
			if (pieceCounter <= 1) {

				return true;
			}
			pieceCounter = 0;

		}

		return false;
	}

	//gets the neighbors of a all possible steps that can be taken based on
	//myList for future chess board
	//@param myList the arraylist representation of a chess board
	//@return neighbors
	@Override
	public ArrayList<ArrayList<Integer>> getNeighbors(ArrayList<Integer> myList) {
		int[][] tempChessBoard = new int[yDimension][xDimension];
		ArrayList<ArrayList<Integer>> neighbors = new ArrayList<ArrayList<Integer>>();
		tempChessBoard = changeOneDimensiontoTwoDimension(tempChessBoard,
				myList);
		for (int y = 0; y < yDimension; y++) {

			for (int x = 0; x < xDimension; x++) {
				int chessNumber = tempChessBoard[y][x];
				if (chessNumber != 0) {

					neighbors.addAll(getConfigsforAnElement(chessNumber, y, x,
							tempChessBoard));

				}

			}

		}

		return neighbors;

	}
	//gets the configs for the neighbor method to be called
	//goes through one piece at a time and see what all possible
	//valid moves can be made by that piece, this will result in a new config
	//@param chessNumber, the number in the chessboard array for the piece
	//@param yPos the y pos of the piece
	//@param xPos the x pos of the piece
	//@param tempBoard a represntation that is a copy of the current chessboard
	//@return configs
	public ArrayList<ArrayList<Integer>> getConfigsforAnElement(
			int chessNumber, int yPos, int xPos, int[][] tempBoard) {

		if (chessNumber == 1) {
			return bishopConfig(yPos, xPos, tempBoard);

		} else if (chessNumber == 2) {

			return kingConfig(yPos, xPos, tempBoard);

		} else if (chessNumber == 3) {

			return knightConfig(yPos, xPos, tempBoard);

		} else if (chessNumber == 4) {

			return pawnConfig(yPos, xPos, tempBoard);

		} else if (chessNumber == 5) {

			return rookConfig(yPos, xPos, tempBoard);

		} else if (chessNumber == 6) {

			return queenConfig(yPos, xPos, tempBoard);

		}

		return null;

	}
	//makes a copy of a two dimensional array that will make refrence changes not happen
	//@param arraytoBeCopied, the array to be copied
	//@return the copied array
	public int[][] copyTwoDimensionalArray(int[][] arrayToBeCopied) {
		int[][] copyArray = new int[yDimension][xDimension];
		for (int y = 0; y < yDimension; y++) {
			for (int x = 0; x < xDimension; x++) {
				copyArray[y][x] = arrayToBeCopied[y][x];

			}

		}

		return copyArray;

	}
	//returns the bigger dimension of the board
	public int giveBiggerDimension() {
		if (xDimension > yDimension) {
			return xDimension;
		}

		else
			return yDimension;
	}
		//exausts all possible moves of a piece that moves this way
		//@param yPos the y pos of the piece
		//@param xPos the x pos of the piece
		//@param tempBoard a represntation that is a copy of the current chessboard
	public ArrayList<ArrayList<Integer>> oneLine(int yPos, int xPos,
			int[][] tempBoard) {
		ArrayList<ArrayList<Integer>> finalConfigs = new ArrayList<ArrayList<Integer>>();
		int[][] copyBoard = null;
		int upperX = xPos + 1;
		int lowerX = xPos - 1;
		int upperY = yPos + 1;
		int lowerY = yPos - 1;
		if (lowerY >= 0) {
			if (tempBoard[lowerY][xPos] != 0) {
				copyBoard = copyTwoDimensionalArray(tempBoard);
				copyBoard[lowerY][xPos] = copyBoard[yPos][xPos];
				copyBoard[yPos][xPos] = 0;
				finalConfigs.add(changeTwoDimensiontoOneDimension(copyBoard,
						fillWithZeros(new ArrayList<Integer>())));
			}

		}
		if (upperY <= yDimension - 1) {
			if (tempBoard[upperY][xPos] != 0) {
				copyBoard = copyTwoDimensionalArray(tempBoard);
				copyBoard[upperY][xPos] = copyBoard[yPos][xPos];
				copyBoard[yPos][xPos] = 0;
				finalConfigs.add(changeTwoDimensiontoOneDimension(copyBoard,
						fillWithZeros(new ArrayList<Integer>())));
			}

		}
		if (lowerX >= 0) {
			if (tempBoard[yPos][lowerX] != 0) {
				copyBoard = copyTwoDimensionalArray(tempBoard);
				copyBoard[yPos][lowerX] = copyBoard[yPos][xPos];
				copyBoard[yPos][xPos] = 0;
				finalConfigs.add(changeTwoDimensiontoOneDimension(copyBoard,
						fillWithZeros(new ArrayList<Integer>())));
			}

		}
		if (upperX <= xDimension - 1) {
			if (tempBoard[yPos][upperX] != 0) {
				copyBoard = copyTwoDimensionalArray(tempBoard);
				copyBoard[yPos][upperX] = copyBoard[yPos][xPos];
				copyBoard[yPos][xPos] = 0;
				finalConfigs.add(changeTwoDimensiontoOneDimension(copyBoard,
						fillWithZeros(new ArrayList<Integer>())));
			}

		}

		return finalConfigs;

	}
	//exausts all possible moves of a piece that moves this way
			//@param yPos the y pos of the piece
			//@param xPos the x pos of the piece
			//@param tempBoard a represntation that is a copy of the current chessboard
	public ArrayList<ArrayList<Integer>> oneDiagonal(int yPos, int xPos,
			int[][] tempBoard) {
		ArrayList<ArrayList<Integer>> finalConfigs = new ArrayList<ArrayList<Integer>>();
		int[][] copyBoard = null;
		int upperX = xPos + 1;
		int lowerX = xPos - 1;
		int upperY = yPos + 1;
		int lowerY = yPos - 1;

		if (upperX <= xDimension - 1 && upperY <= yDimension - 1) {

			if (tempBoard[upperY][upperX] != 0) {
				copyBoard = copyTwoDimensionalArray(tempBoard);
				copyBoard[upperY][upperX] = copyBoard[yPos][xPos];
				copyBoard[yPos][xPos] = 0;
				finalConfigs.add(changeTwoDimensiontoOneDimension(copyBoard,
						fillWithZeros(new ArrayList<Integer>())));

			}

		}
		if (lowerX >= 0 && upperY <= yDimension - 1) {

			if (tempBoard[upperY][lowerX] != 0) {
				copyBoard = copyTwoDimensionalArray(tempBoard);
				copyBoard[upperY][lowerX] = copyBoard[yPos][xPos];
				copyBoard[yPos][xPos] = 0;
				finalConfigs.add(changeTwoDimensiontoOneDimension(copyBoard,
						fillWithZeros(new ArrayList<Integer>())));

			}

		}
		if (lowerX >= 0 && lowerY >= 0) {

			if (tempBoard[lowerY][lowerX] != 0) {
				copyBoard = copyTwoDimensionalArray(tempBoard);
				copyBoard[lowerY][lowerX] = copyBoard[yPos][xPos];
				copyBoard[yPos][xPos] = 0;
				finalConfigs.add(changeTwoDimensiontoOneDimension(copyBoard,
						fillWithZeros(new ArrayList<Integer>())));

			}

		}
		if (upperX <= xDimension - 1 && lowerY >= 0) {

			if (tempBoard[lowerY][upperX] != 0) {
				copyBoard = copyTwoDimensionalArray(tempBoard);
				copyBoard[lowerY][upperX] = copyBoard[yPos][xPos];
				copyBoard[yPos][xPos] = 0;
				finalConfigs.add(changeTwoDimensiontoOneDimension(copyBoard,
						fillWithZeros(new ArrayList<Integer>())));

			}

		}

		return finalConfigs;

	}
	//exausts all possible moves of a piece that moves this way
			//@param yPos the y pos of the piece
			//@param xPos the x pos of the piece
			//@param tempBoard a represntation that is a copy of the current chessboard
	public ArrayList<ArrayList<Integer>> allDiagonal(int yPos, int xPos,
			int[][] tempBoard) {
		ArrayList<ArrayList<Integer>> finalConfigs = new ArrayList<ArrayList<Integer>>();
		int[][] copyBoard = null;

		for (int i = 1; i < giveBiggerDimension(); i++) {
			int upperX = xPos + i;
			int lowerX = xPos - i;
			int upperY = yPos + i;
			int lowerY = yPos - i;
			if (upperX <= xDimension - 1 && upperY <= yDimension - 1
					&& chessBoard[upperY][upperX] != 0) {

				if (tempBoard[upperY][upperX] != 0) {
					copyBoard = copyTwoDimensionalArray(tempBoard);
					copyBoard[upperY][upperX] = copyBoard[yPos][xPos];
					copyBoard[yPos][xPos] = 0;
					finalConfigs
							.add(changeTwoDimensiontoOneDimension(copyBoard,
									fillWithZeros(new ArrayList<Integer>())));
					break;

				}

			}
			if (lowerX >= 0 && upperY <= yDimension - 1
					&& chessBoard[upperY][lowerX] != 0) {

				if (tempBoard[upperY][lowerX] != 0) {
					copyBoard = copyTwoDimensionalArray(tempBoard);
					copyBoard[upperY][lowerX] = copyBoard[yPos][xPos];
					copyBoard[yPos][xPos] = 0;
					finalConfigs
							.add(changeTwoDimensiontoOneDimension(copyBoard,
									fillWithZeros(new ArrayList<Integer>())));
					break;

				}

			}
			if (lowerX >= 0 && lowerY >= 0 && chessBoard[lowerY][lowerX] != 0) {

				if (tempBoard[lowerY][lowerX] != 0) {
					copyBoard = copyTwoDimensionalArray(tempBoard);
					copyBoard[lowerY][lowerX] = copyBoard[yPos][xPos];
					copyBoard[yPos][xPos] = 0;
					finalConfigs
							.add(changeTwoDimensiontoOneDimension(copyBoard,
									fillWithZeros(new ArrayList<Integer>())));
					break;

				}

			}
			if (upperX <= xDimension - 1 && lowerY >= 0
					&& chessBoard[lowerY][upperX] != 0) {

				if (tempBoard[lowerY][upperX] != 0) {
					copyBoard = copyTwoDimensionalArray(tempBoard);
					copyBoard[lowerY][upperX] = copyBoard[yPos][xPos];
					copyBoard[yPos][xPos] = 0;
					finalConfigs
							.add(changeTwoDimensiontoOneDimension(copyBoard,
									fillWithZeros(new ArrayList<Integer>())));
					break;

				}

			}

		}
		return finalConfigs;

	}
	//exausts all possible moves of a piece that moves this way
			//@param yPos the y pos of the piece
			//@param xPos the x pos of the piece
			//@param tempBoard a represntation that is a copy of the current chessboard
	public ArrayList<ArrayList<Integer>> allLines(int yPos, int xPos,
			int[][] tempBoard) {
		ArrayList<ArrayList<Integer>> finalConfigs = new ArrayList<ArrayList<Integer>>();
		int[][] copyBoard = null;
		for (int i = 1; i < giveBiggerDimension(); i++) {
			if (xPos + i < xDimension - 1) {

				if (tempBoard[yPos][xPos + i] != 0) {

					copyBoard = copyTwoDimensionalArray(tempBoard);
					copyBoard[yPos][xPos + i] = copyBoard[yPos][xPos];
					copyBoard[yPos][xPos] = 0;
					finalConfigs
							.add(changeTwoDimensiontoOneDimension(copyBoard,
									fillWithZeros(new ArrayList<Integer>())));
					break;

				}

			}
			if (xPos - i >= 0) {

				if (tempBoard[yPos][xPos - i] != 0) {

					copyBoard = copyTwoDimensionalArray(tempBoard);
					copyBoard[yPos][xPos - i] = copyBoard[yPos][xPos];
					copyBoard[yPos][xPos] = 0;
					finalConfigs
							.add(changeTwoDimensiontoOneDimension(copyBoard,
									fillWithZeros(new ArrayList<Integer>())));
					break;

				}

			}
			if (yPos + i <= yDimension - 1) {

				if (tempBoard[yPos + i][xPos] != 0) {

					copyBoard = copyTwoDimensionalArray(tempBoard);
					copyBoard[yPos + i][xPos] = copyBoard[yPos][xPos];
					copyBoard[yPos][xPos] = 0;
					finalConfigs
							.add(changeTwoDimensiontoOneDimension(copyBoard,
									fillWithZeros(new ArrayList<Integer>())));
					break;

				}

			}
			if (yPos - i >= 0) {

				if (tempBoard[yPos - i][xPos] != 0) {

					copyBoard = copyTwoDimensionalArray(tempBoard);
					copyBoard[yPos - i][xPos] = copyBoard[yPos][xPos];
					copyBoard[yPos][xPos] = 0;
					finalConfigs
							.add(changeTwoDimensiontoOneDimension(copyBoard,
									fillWithZeros(new ArrayList<Integer>())));
					break;

				}

			}

		}
		return finalConfigs;

	}
	//exausts all possible moves of a piece that moves this way
			//@param yPos the y pos of the piece
			//@param xPos the x pos of the piece
			//@param tempBoard a represntation that is a copy of the current chessboard
	public ArrayList<ArrayList<Integer>> Lshift(int yPos, int xPos,
			int[][] tempBoard) {
		ArrayList<ArrayList<Integer>> finalConfigs = new ArrayList<ArrayList<Integer>>();
		int[][] copyBoard = null;
		int xmax2 = xPos + 2;
		int xmax1 = xPos + 1;
		int xmin1 = xPos - 1;
		int xmin2 = xPos - 2;
		int ymax1 = yPos + 1;
		int ymin1 = yPos - 1;
		int ymin2 = yPos - 2;
		int ymax2 = yPos + 2;

		if (ymin2 >= 0 && xmin1 >= 0) {

			if (tempBoard[ymin2][xmin1] != 0) {

				copyBoard = copyTwoDimensionalArray(tempBoard);
				copyBoard[ymin2][xmin1] = copyBoard[yPos][xPos];
				copyBoard[yPos][xPos] = 0;
				finalConfigs.add(changeTwoDimensiontoOneDimension(copyBoard,
						fillWithZeros(new ArrayList<Integer>())));

			}

		}
		if (ymin2 >= 0 && xmax1 <= xDimension - 1) {

			if (tempBoard[ymin2][xmax1] != 0) {

				copyBoard = copyTwoDimensionalArray(tempBoard);
				copyBoard[ymin2][xmax1] = copyBoard[yPos][xPos];
				copyBoard[yPos][xPos] = 0;
				finalConfigs.add(changeTwoDimensiontoOneDimension(copyBoard,
						fillWithZeros(new ArrayList<Integer>())));

			}

		}
		if (ymin1 >= 0 && xmin2 >= 0) {

			if (tempBoard[ymin1][xmin2] != 0) {

				copyBoard = copyTwoDimensionalArray(tempBoard);
				copyBoard[ymin1][xmin2] = copyBoard[yPos][xPos];
				copyBoard[yPos][xPos] = 0;
				finalConfigs.add(changeTwoDimensiontoOneDimension(copyBoard,
						fillWithZeros(new ArrayList<Integer>())));

			}

		}
		if (ymax1 <= yDimension - 1 && xmin2 >= 0) {

			if (tempBoard[ymax1][xmin2] != 0) {

				copyBoard = copyTwoDimensionalArray(tempBoard);
				copyBoard[ymax1][xmin2] = copyBoard[yPos][xPos];
				copyBoard[yPos][xPos] = 0;
				finalConfigs.add(changeTwoDimensiontoOneDimension(copyBoard,
						fillWithZeros(new ArrayList<Integer>())));

			}

		}
		if (ymax1 < yDimension - 1 && xmin2 >= 0) {

			if (tempBoard[ymax1][xmin2] != 0) {

				copyBoard = copyTwoDimensionalArray(tempBoard);
				copyBoard[ymax1][xmin2] = copyBoard[yPos][xPos];
				copyBoard[yPos][xPos] = 0;
				finalConfigs.add(changeTwoDimensiontoOneDimension(copyBoard,
						fillWithZeros(new ArrayList<Integer>())));

			}

		}
		if (ymax2 <= yDimension - 1 && xmin1 >= 0) {

			if (tempBoard[ymax2][xmin1] != 0) {

				copyBoard = copyTwoDimensionalArray(tempBoard);
				copyBoard[ymax2][xmin1] = copyBoard[yPos][xPos];
				copyBoard[yPos][xPos] = 0;
				finalConfigs.add(changeTwoDimensiontoOneDimension(copyBoard,
						fillWithZeros(new ArrayList<Integer>())));

			}

		}
		if (ymax2 <= yDimension - 1 && xmax1 <= xDimension - 1) {

			if (tempBoard[ymax2][xmax1] != 0) {

				copyBoard = copyTwoDimensionalArray(tempBoard);
				copyBoard[ymax2][xmax1] = copyBoard[yPos][xPos];
				copyBoard[yPos][xPos] = 0;
				finalConfigs.add(changeTwoDimensiontoOneDimension(copyBoard,
						fillWithZeros(new ArrayList<Integer>())));

			}

		}
		if (ymin1 >= 0 && xmax2 <= xDimension - 1) {

			if (tempBoard[ymin1][xmax2] != 0) {

				copyBoard = copyTwoDimensionalArray(tempBoard);
				copyBoard[ymin1][xmax2] = copyBoard[yPos][xPos];
				copyBoard[yPos][xPos] = 0;
				finalConfigs.add(changeTwoDimensiontoOneDimension(copyBoard,
						fillWithZeros(new ArrayList<Integer>())));

			}

		}
		if (ymax1 <= yDimension - 1 && xmax2 <= xDimension - 1) {

			if (tempBoard[ymax1][xmax2] != 0) {

				copyBoard = copyTwoDimensionalArray(tempBoard);
				copyBoard[ymax1][xmax2] = copyBoard[yPos][xPos];
				copyBoard[yPos][xPos] = 0;
				finalConfigs.add(changeTwoDimensiontoOneDimension(copyBoard,
						fillWithZeros(new ArrayList<Integer>())));

			}

		}

		return finalConfigs;

	}
			//gives all configs of a bishop moving on the board
			//@param yPos the y pos of the piece
			//@param xPos the x pos of the piece
			//@param tempBoard a represntation that is a copy of the current chessboard
	public ArrayList<ArrayList<Integer>> bishopConfig(int yPos, int xPos,
			int[][] tempBoard) {
		ArrayList<ArrayList<Integer>> allBishopConfigs = new ArrayList<ArrayList<Integer>>();
		allBishopConfigs.addAll(allDiagonal(yPos, xPos, tempBoard));
		return allBishopConfigs;

	}
	//gives all configs of a bishop moving on the board
	//@param yPos the y pos of the piece
	//@param xPos the x pos of the piece
	//@param tempBoard a represntation that is a copy of the current chessboard
	public ArrayList<ArrayList<Integer>> kingConfig(int yPos, int xPos,
			int[][] tempBoard) {
		ArrayList<ArrayList<Integer>> allKingConfigs = new ArrayList<ArrayList<Integer>>();
		allKingConfigs.addAll(oneLine(yPos, xPos, tempBoard));
		allKingConfigs.addAll(oneDiagonal(yPos, xPos, tempBoard));

		return allKingConfigs;
	}
	//gives all configs of a knight moving on the board
	//@param yPos the y pos of the piece
	//@param xPos the x pos of the piece
	//@param tempBoard a represntation that is a copy of the current chessboard
	public ArrayList<ArrayList<Integer>> knightConfig(int yPos, int xPos,
			int[][] tempBoard) {
		ArrayList<ArrayList<Integer>> allKnightConfigs = new ArrayList<ArrayList<Integer>>();
		allKnightConfigs.addAll(Lshift(yPos, xPos, tempBoard));

		return allKnightConfigs;
	}
	//gives all configs of a pawn moving on the board
	//@param yPos the y pos of the piece
	//@param xPos the x pos of the piece
	//@param tempBoard a represntation that is a copy of the current chessboard
	public ArrayList<ArrayList<Integer>> pawnConfig(int yPos, int xPos,
			int[][] tempBoard) {

		ArrayList<ArrayList<Integer>> allPawnConfigs = new ArrayList<ArrayList<Integer>>();
		allPawnConfigs.addAll(oneDiagonal(yPos, xPos, tempBoard));
		return allPawnConfigs;
	}
	//gives all configs of a rook moving on the board
	//@param yPos the y pos of the piece
	//@param xPos the x pos of the piece
	//@param tempBoard a represntation that is a copy of the current chessboard
	public ArrayList<ArrayList<Integer>> rookConfig(int yPos, int xPos,
			int[][] tempBoard) {
		ArrayList<ArrayList<Integer>> allRookConfigs = new ArrayList<ArrayList<Integer>>();
		allRookConfigs.addAll(allLines(yPos, xPos, tempBoard));
		return allRookConfigs;

	}
	//gives all configs of a queen moving on the board
	//@param yPos the y pos of the piece
	//@param xPos the x pos of the piece
	//@param tempBoard a represntation that is a copy of the current chessboard
	public ArrayList<ArrayList<Integer>> queenConfig(int yPos, int xPos,
			int[][] tempBoard) {
		ArrayList<ArrayList<Integer>> allQueenConfigs = new ArrayList<ArrayList<Integer>>();
		allQueenConfigs.addAll(allLines(yPos, xPos, tempBoard));
		allQueenConfigs.addAll(allDiagonal(yPos, xPos, tempBoard));

		return allQueenConfigs;
	}
	//changes a arraylist into a 2d array
	public int[][] changeOneDimensiontoTwoDimension(int[][] tempBoard,
			ArrayList<Integer> myList) {
		int ArrayListCounter = 0;
		int tempY = 0;
		int tempX = 0;

		while (ArrayListCounter < myList.size()) {

			if (tempX == xDimension) {

				tempX = 0;
				tempY++;

			}

			tempBoard[tempY][tempX] = myList.get(ArrayListCounter);
			tempX++;
			ArrayListCounter++;
		}

		return tempBoard;
	}
	//changes a 2d array into 1 dimension arraylist
	public ArrayList<Integer> changeTwoDimensiontoOneDimension(
			int[][] tempBoard, ArrayList<Integer> myList) {
		int listCounter = 0;
		for (int y = 0; y < yDimension; y++) {

			for (int x = 0; x < xDimension; x++) {

				myList.set(listCounter, tempBoard[y][x]);
				listCounter++;

			}
		}
		return myList;

	}
	//	fills a arraylist with zeros
	public ArrayList<Integer> fillWithZeros(ArrayList<Integer> myList) {
		int counter = 1;
		while (counter <= xDimension * yDimension) {

			myList.add(0);
			counter++;

		}

		return myList;

	}
	//basic config of the state of the game to get the neighbors of
	public ArrayList<ArrayList<Integer>> baseCon() {
		basicConfig = new ArrayList<ArrayList<Integer>>();
		baseConfig = new ArrayList<Integer>();
		for (int y = 0; y < yDimension; y++) {

			for (int x = 0; x < xDimension; x++) {

				baseConfig.add(chessBoard[y][x]);

			}

		}
		basicConfig.add(baseConfig);

		return basicConfig;

	}
	//get the start of how many pieces exist
	@Override
	public int getStart() {
		// TODO Auto-generated method stub
		for (int y = 0; y < yDimension; y++) {

			for (int x = 0; x < xDimension; x++) {

				if (chessBoard[y][x] != 0) {
					pieceCounter++;

				}

			}

		}
		int myPieces = pieceCounter;
		pieceCounter = 0;
		return myPieces;
	}
	//prints out the solution for current puzzle config
	@Override
	public String showResults(ArrayList<ArrayList<Integer>> input) {
		currentConfig = input;
		String finalString = "Solution for current Config: " + "\n";
		int counter = 1;
		Iterator<ArrayList<Integer>> it = input.iterator();

		while (it.hasNext()) {
			ArrayList<Integer> step = it.next();

			finalString = finalString
					+ "Step "
					+ counter
					+ ":"
					+ "\n"
					+ printArray(changeOneDimensiontoTwoDimension(
							new int[yDimension][xDimension], step)) + "\n";
			counter++;

		}

		return finalString;
	}

	//starts game
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {

			System.out.println("% usage: java Chess input-file");
			System.exit(0);

		}
		//makes the game start
		Chess myGame = new Chess(args[0]);

	}

}
