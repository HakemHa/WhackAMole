import java.util.*;

public class WhackAMole {
  int score;
  int molesLeft;
  int attemptsLeft;
  char[][] moleGrid;
  /* Constructor, set default values */
  public WhackAMole(int numAttempts, int gridDimension){
    this.score = 0;
    this.molesLeft = 0;
    this.attemptsLeft = numAttempts;
    this.moleGrid = new char[gridDimension][gridDimension];
    for(char[] r : moleGrid){
      Arrays.fill(r,'X');
    }
  }
  /* Set Moles Location */
  public boolean place(int x, int y){
    if (moleGrid[x][y] == 'M')
    {
      return false;
    }
    else{
      moleGrid[x][y] = 'M';
      molesLeft++;
      return true;
      }
    }
  /* Whack a Mole */
  public void whack(int x, int y){
    if(moleGrid[x][y] == 'M' || moleGrid[x][y] == 'W'){
      score++;
      moleGrid[x][y] = 'W';
      molesLeft--;
    }
    else{
      moleGrid[x][y] = 'O';
      attemptsLeft--;
    }
  }
  /* display empty grid to user */
  public void printGridtoUser(){
    for(char[] r : moleGrid){
      System.out.print("                                                   ");
      for (char e : r) {
        if (e != 'W' && e != 'O')
        {
          System.out.print("X ");
        }
        else if (e != 'W') {
          System.out.print("O ");
        }
        else{
          System.out.print("W ");
        }
      }
      System.out.println();
    }
  }
  /* Show answer */
  public void printGrid(){
    for(char[] r : moleGrid){
      System.out.print("                                                   ");
      for(char e : r){
        if(e == 'M'){
          System.out.print("M ");
        } else if (e == 'W') {
          System.out.print("W ");
        } else {
          System.out.print("O ");
        }
      }
      System.out.println();
    }
  }
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int difficulty = 0;
    System.out.println("//////////*Welcome To Whack A Mole!*//////////");
    System.out.println();
    while (difficulty < 1 || difficulty > 10){
      boolean catchDifficulty = true;
      do {
        try {
          catchDifficulty = false;
          System.out.print("Choose your difficulty level: 1 (Easy) - 10 (!Super Hard!)--- ");
          difficulty = scanner.nextInt();
      } catch (InputMismatchException e) {
          scanner.next();
          catchDifficulty = true;
          System.out.print("                                               ");
          System.out.println("Invalid Input (only integers allowed)");
        }
      } while (catchDifficulty);
    }
    int attempts = 0;
    int grid = 0;
    for(int i = 0; i < difficulty; i++){
      grid += 2;
      attempts += grid*2 -1;
      if (grid > 6) {
        attempts -= grid/2 + difficulty;
      }
    }
    WhackAMole settings = new WhackAMole(attempts,grid);
    Random random = new Random();
    for(int i = 0; i < settings.moleGrid.length; i++){
      settings.place(random.nextInt(settings.moleGrid.length),random.nextInt(settings.moleGrid.length));
      if(difficulty > 1 && i % 2 == 0){
      settings.place(random.nextInt(settings.moleGrid.length),random.nextInt(settings.moleGrid.length));
      }
    }
    for(int i = 0; i < 100; i++){
      System.out.println();
    }
    System.out.print("                                               ");
    System.out.println("Game Start (press '0' to quit)");
    System.out.println();
    while (settings.attemptsLeft > 0 && settings.molesLeft > 0){
      System.out.print("                                            ");
      System.out.print("attemptsLeft: " + settings.attemptsLeft);
      System.out.println(" // Score: " + settings.score);
      System.out.println();
      settings.printGridtoUser();
      int x = -2;
      int y = -2;
      boolean catchX = true;
      boolean catchY = true;
        while (x < -1 || x >= settings.moleGrid.length) {
          System.out.println("Choose valid X coordinates to whack... 1 - " + settings.moleGrid.length);
          do{
            System.out.print("X: ");
            try {
              catchX = false;
              x = scanner.nextInt() - 1;
            } catch (InputMismatchException e) {
              scanner.next();
              catchX = true;
              System.out.print("Invalid Input (only integers allowed): \n");
            }
        }while(catchX);
    }
      System.out.println();
       while ( y < -1 || y >= settings.moleGrid.length){
         System.out.println("Choose valid Y coordinates to whack... 1 - " + settings.moleGrid.length);
         do{
           System.out.print("Y: ");
          try {
            catchY = false;
            y = scanner.nextInt() - 1;
          } catch(InputMismatchException e) {
            scanner.next();
            catchY = true;
            System.out.print("Invalid Input (only integers allowed): \n");
          }
         } while(catchY);
       }
      System.out.println();
      if(x == -1 || y == -1){
        settings.printGrid();
        System.out.println();
        System.out.print("                                                   ");
        System.out.print("Giving up??? (try a easier level?(y/n)): ");
        char tryAgain = scanner.next().charAt(0);
        System.out.println();
        if(tryAgain == 'y')
        {
          main(args);
        }
        System.out.println();
        System.out.println();
        return;
      }
      settings.whack(y,x);
      if(settings.molesLeft == 0){
        settings.printGrid();
      }
    }
    if(settings.molesLeft == 0){
      System.out.print("                                                   ");
      System.out.print("You Win!");
    }
    else{
      System.out.println("                                            Answer:");
      settings.printGrid();
      System.out.println();
      System.out.print("                                                   ");
      System.out.print("Game Over! Try Again?(y/n): ");
      char tryAgain = scanner.next().charAt(0);
      System.out.println();
      if(tryAgain == 'y')
      {
        main(args);
      }
    }
    System.out.println();
    return;
  }
}
