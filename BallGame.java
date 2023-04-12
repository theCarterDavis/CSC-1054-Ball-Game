import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.util.*;
import javafx.geometry.*;
import javafx.scene.paint.*;
import javafx.scene.image.*;
import javafx.event.*;
import javafx.animation.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.util.*;
import javafx.geometry.*;
import javafx.scene.paint.*;
import javafx.scene.image.*;
import javafx.scene.canvas.*;
import javafx.scene.input.*;

public class BallGame extends Application{
   
   //Establishing each of the panes to go in the border
   BorderPane root = new BorderPane();
   FlowPane center = new FlowPane();  
   Label topLabel = new Label("Balls Left: 15  Moves: 2");
   StackPane top = new StackPane();  
   FlowPane left = new FlowPane();  
   FlowPane right = new FlowPane();  
   FlowPane bottom = new FlowPane();  
      
   ArrayList<GamePane> arr = new ArrayList();

   //Stage setup
   public void start(Stage stage){
      
      //Creating the panes and putting them in an arry list
      for(int i = 0; i < 16; i++){
         
        arr.add(new GamePane(i));         
      }
      
      //Adding the panes to the center pane
      for(int i = 0; i < 16; i++){
         
        center.getChildren().add(arr.get(i));       
      }
      
                 
       //Setitng up the root to be corect proportions
       root.setLeft(left);
       root.setRight(right);
       root.setBottom(bottom);
       top.getChildren().add(topLabel);
       root.setCenter(center); 
       center.setPrefSize(480,480);
       left.setPrefSize(60,480);
       right.setPrefSize(60,480);
       bottom.setPrefSize(480,60);
       top.setPrefSize(480,40);
       root.setTop(top);
             
      Scene scene = new Scene(root, 600, 600); //other scene code here
      stage.setScene(scene);
      stage.setTitle("Ball Game");
      stage.show();

}
   

   public class GamePane extends GridPane{
     
     //Establishing the variables for the ball class
     GridPane pane = new GridPane();
     private boolean isShown;
     private int num;
         
     //creating buttons
     private Button bt = new Button();
     private Button bl = new Button();
     private Button br = new Button();
     private Button bb = new Button();

     public GamePane(int numIn){
         //Setting up a border pane and adding a canvas to the center of it
         //GridPane pane = new GridPane();
         
         isShown = true;
         num = numIn;
         Canvas canvas = new Canvas(80,80);   
         bt.setPrefSize(80,20);
         bl.setPrefSize(20,80);
         br.setPrefSize(20,80);
         bb.setPrefSize(80,20);

         add(bt, 1, 0);
         add(bl, 0, 1);
         add(br, 2, 1);
         add(bb, 1, 2);
         
         //Setting the button on actions
         bt.setOnAction(new ButtonListener(num,"bt"));
         bl.setOnAction(new ButtonListener(num,"bl"));
         br.setOnAction(new ButtonListener(num,"br"));
         bb.setOnAction(new ButtonListener(num,"bb"));
         
         //inital start up visibility
         if(num == 8){
            
            isShown = false;
            setVisible(false);

         }
         
         //starting all buttons as false
         bt.setVisible(false);
         bl.setVisible(false);
         br.setVisible(false);
         bb.setVisible(false);
         
         //making the inital play possible
         if(num == 0){
            bt.setVisible(true);
            
         }
         
          if(num == 10){
            br.setVisible(true);
         }
         
         //Drawing the circle
         GraphicsContext gc = canvas.getGraphicsContext2D();
   
         add(canvas,1,1);
          
         gc.fillOval(0,0,80,80);       
      }     
      
      //method to swap visibility boolean
      public void setInvisible(){
         
         isShown = !isShown;
            
      }
      
      
      //setting button visibility 
      public void clearButtons(){
         
         bt.setVisible(false);
         bl.setVisible(false);
         br.setVisible(false);
         bb.setVisible(false);

         
      }
      
      //methods to swap button visibility
      public void setButtonVisT(){
         
         bt.setVisible(true);
      
      }
      
      public void setButtonVisL(){
         
         bl.setVisible(true);
      
      }
      
      public void setButtonVisR(){
         
         br.setVisible(true);
      
      }
      
      public void setButtonVisB(){
         
         bb.setVisible(true);
      
      }

      //returning the visibility of the pane as a whole
      public boolean getVis(){
         
         return isShown;
      
      }
           
     
   }
   
   public class ButtonListener implements EventHandler<ActionEvent>  {
      
      //Variables to be used in the listener
      private int pane;
      String loc;
      private int moves;
      private int score;
      
      //Taking in the input from the pane class
      public ButtonListener(int paneIn, String locIn){
         
         pane = paneIn;
         loc = locIn;
         
      }
      
      
      public void handle(ActionEvent e) {
        moves = 0;
        score = 0;
        
        //setting all buttons to not visible
        for(int i = 0; i < 16;i++){
         
         arr.get(i).clearButtons();
                  
         }
 
         //swaping visibilities for top button
         if(loc.equals("bt")){
               
            arr.get(pane).setInvisible();
            arr.get(pane+4).setInvisible();
            arr.get(pane+8).setInvisible();                 
            
         }
         
         //swaping visibilities for left button
         if(loc.equals("bl")){
            
            arr.get(pane).setInvisible();
            arr.get(pane+1).setInvisible();
            arr.get(pane+2).setInvisible(); 
            
         }
         
         //swaping visibilities for right button
         if(loc.equals("br")){
            
             arr.get(pane).setInvisible();
             arr.get(pane-1).setInvisible();
             arr.get(pane-2).setInvisible(); 
            
            
         }
         
         //swaping visibilities for bottom button
         if(loc.equals("bb")){
            
            arr.get(pane).setInvisible();
            arr.get(pane-4).setInvisible();
            arr.get(pane-8).setInvisible(); 
               
         }
      
         //setting button visibility after the jump
         for(int i = 0; i < 16;i++){
            
            arr.get(i).setVisible(arr.get(i).getVis());
            
            if( arr.get(i).getVis() == true){
               
               score++;
               
            }
         }
         
         //setting button visibility
         for(int i = 0; i < 16;i++){
            
            //top button vis
            if(i < 8){
            
               if(arr.get(i).getVis() == true && arr.get(i+4).getVis() == true && arr.get(i+8).getVis() == false){
                  
                  arr.get(i).setButtonVisT();
                  moves++;
                  
               }
               
            }
            
            //Left button vis
            if(i == 0 || i == 1 || i == 4 || i == 5 || i == 8 || i == 9 || i == 12 || i == 13){
            
               if(arr.get(i).getVis() == true && arr.get(i+1).getVis() == true && arr.get(i+2).getVis() == false){
                  
                  arr.get(i).setButtonVisL();
                  moves++;
                  
               }
               
            }
            
            //Right button vis
            if(i == 2 || i == 3 || i == 6 || i == 7 || i == 10 || i == 11 || i == 14 || i == 15){
            
               if(arr.get(i).getVis() == true && arr.get(i-1).getVis() == true && arr.get(i-2).getVis() == false){
                  
                  arr.get(i).setButtonVisR();
                  moves++;
               }
               
            }
           
            //bottom button vis
            if(i >= 8){
            
               if(arr.get(i).getVis() == true && arr.get(i-4).getVis() == true && arr.get(i-8).getVis() == false){
                  
                  arr.get(i).setButtonVisB();
                  moves++;
                  
               }
               
            }
         
         }
         
         //Setting the labes to the correct output
         String scoreS =String.valueOf(score);
         String movesS =String.valueOf(moves);

         String lS = "Balls left: " + scoreS + " ";
         String mS = "Possible Moves: " + movesS;
         
         topLabel.setText(lS + mS);
         
         //label for a loss
         if(moves == 0){
            
            topLabel.setText("You Lose:(");
            
         }
         
         //label for a win
         if(score == 1){
            
            topLabel.setText("You WIN!!!");
            
         }
         
      }
      
   } 
   
   public static void main(String[] args){
   
      launch(args);
   
   }


}
