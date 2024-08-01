import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class TicTacToe {
  
  int boardWidth=600;
  int boardHeight=650; //50px restantes para o painel no topo do ecra de jogo

  JFrame frame= new JFrame("Jogo do Galo");
  JLabel textLabel= new JLabel();
  JPanel textPanel= new JPanel();
  JPanel boardPanel= new JPanel();

  JButton [][] board= new JButton[3][3];
  String playerX= "X";
  String player0= "O";
  String currentPlayer= playerX; //faz tracking da vez de cada jogador e portanto o primeiro jogador é o "X"

  boolean gameOver= false;
  int turns= 0;


  TicTacToe(){
    frame.setVisible(true);
    frame.setSize(boardWidth,boardHeight);
    frame.setLocationRelativeTo(null); // centraliza a tela de jogo no nosso ecrã
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //serve para definição de ao clicarmos no X a tela fecha
    frame.setLayout(new BorderLayout());


    textLabel.setBackground(Color.DARK_GRAY); //faz o set da cor que queremos no nosso background
    textLabel.setForeground(Color.white);
    textLabel.setFont(new Font("Arial", Font.BOLD,50));
    textLabel.setHorizontalAlignment(JLabel.CENTER); //a orientação do texto seja ao centro
    textLabel.setText("Jogo do Galo");
    textLabel.setOpaque(true);

    textPanel.setLayout(new BorderLayout());
    textPanel.add(textLabel); //estamos a dizer que vamos adicionar o nosso Label ao nosso Panel
    frame.add(textPanel,BorderLayout.NORTH); //vamos adicionar o nosso Panel à nossa Frame e TAMBEM através de um outro                parametro "BorderLayout.NORTH" definimo "Jogo do Galo" como o titulo do nosso jogo

    boardPanel.setLayout(new GridLayout(3,3)); //vai criar um boardPanel de 3x3
    boardPanel.setBackground(Color.darkGray);
    frame.add(boardPanel); //AQUI adicionamos o nosso boardPanel à nossa frame do Jogo do Galo

    for (int r= 0; r<3; r++){ //neste for loop o que estamos a fazer é basicamente percorrer o array bidimensional e a cada linha adicionamos uma coluna
      for(int c= 0; c<3; c++){
        JButton tile= new JButton();
        board [r][c]= tile;
        boardPanel.add(tile);  //construídos os vários "tiles" adicionamo-los ao nosso "boardPanel"
        
        //VAMOS AGORA ADICIONAR PROPRIEDADES PARA ESTILIZAR OS NOSSOS BUTTONS aka TILES

        tile.setBackground(Color.darkGray);
        tile.setForeground(Color.white);
        tile.setFont(new Font("Arial",Font.BOLD,120));
        tile.setFocusable(false);
        //tile.setText(currentPlayer);

        //Agora o que quero fazer é estabelecer em termos de texto se é um "X" ou um "O" ao clicar num dos tiles

        tile.addActionListener(new ActionListener() {

          public void actionPerformed(ActionEvent e){
            if(gameOver) return; //isto não deixas os players alterarem o estado do jogo
            JButton tile= (JButton) e.getSource();
            
            //NOTA: temos um problema... Neste momento, podemos sobrepor "X" ou "O" em cada tile e nao queremos isso...

            if(tile.getText()== ""){

              //com esta condição então já não podemos substituir X por O's ou vice-versa
              tile.setText(currentPlayer);
              turns ++;
              checkWinner(); //evocamos a função checkWinner  conforme as condições...
              if(!gameOver){
                  currentPlayer= currentPlayer == playerX ? player0 : playerX;
                  textLabel.setText(currentPlayer + " -é a vez de jogar!");
              } 
            }

          }          
        });
      
      }
    }    
  }
  void checkWinner(){
    //CONDIÇÕES PARA GANHAR O JOGO
    //Fazer o check da vitória na horizontal
    for(int r=0; r<3; r++){
      if (board[r][0].getText()== "") continue;

      if (board[r][0].getText()== board[r][1].getText() && 
        board[r][1].getText()== board[r][2].getText()){
        for (int i= 0; i<3; i++){
          setWinner(board[r][i]);
        }
        gameOver= true;
        return;
      }


    }

    //CONDIÇÕES PARA GANHAR O JOGO
    //Fazer o check da vitória na vertical
    for (int c=0; c<3; c++){
      if (board[0][c].getText()=="") continue;

      if(board[0][c].getText()== board[1][c].getText() &&
          board[1][c].getText()== board[2][c].getText()){
            
            for(int i= 0; i<3; i++){
              setWinner(board[i][c]);
            }
            
            gameOver= true;
            return;
          }
    }

    //CONDIÇÕES PARA GANHAR O JOGO
    //Fazer o check da vitória na diagonal
    if (board[0][0].getText() == board[1][1].getText() &&
        board[1][1].getText() == board[2][2].getText() &&
        board[0][0].getText() != ""){
          for (int i = 0; i<3; i++){
            setWinner(board[i][i]);
          }
          gameOver= true;
          return;
    }

    //CONDIÇÕES PARA GANHAR O JOGO
    //Fazer o check da vitória na anti-diagonal

    if(board[0][2].getText() == board[1][1].getText() &&
    board[1][1].getText() == board[2][0].getText() &&
     board[0][2].getText() != ""){
      setWinner(board[0][2]);
      setWinner(board[1][1]);
      setWinner(board[2][0]);
      gameOver=true;
      return;
     }

     //CONDIÇÕES PARA HAVER EMPATE

    if (turns == 9){
      for (int r= 0; r<3; r++){
        for (int c=0; c<3; c++){
          setTie(board[r][c]);
        }
      }
      gameOver= true;
    } 
    
  }
  void setWinner(JButton tile){
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText( currentPlayer + " é o vencedor!");

      }
  

  void setTie(JButton tile){
    tile.setForeground(Color.orange);
    tile.setBackground(Color.gray);
    textLabel.setText("Empate!");
  }

}
