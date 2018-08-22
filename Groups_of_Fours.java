import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.FileReader;      // both needed
import java.io.BufferedReader;  // for line input
import java.io.IOException;

public class Groups_of_Fours extends Frame implements ActionListener{
    // File Parameters
    String dataFilePath = null;
    String dataFileName = null;
    String Jabout="A program that receives a array of numbers that would highlights words that appears in groups of 4+";
    int[][] Data;
    int[][] Tag;
    int row = 0;
    int column = 0;
    String command = "";
    static final long serialVersionUID = 1;
        
    public static void main(String[] args){
        Frame frame = new  Groups_of_Fours();
        frame.setResizable(true);
        frame.setSize(1000,700);
        frame.setVisible(true);
        frame.setBackground(Color.BLACK);
    }
    
    public Groups_of_Fours(){
        setTitle("2D Arrays");
        
        MenuBar mb = new MenuBar();
        setMenuBar(mb);
        
        Menu fileMenu=new Menu("File");
        mb.add(fileMenu);
        
        MenuItem miAbout=new MenuItem("About");
        miAbout.addActionListener(this);
        fileMenu.add(miAbout);
        
        MenuItem miRead=new MenuItem("Read Data");
        miRead.addActionListener(this);
        fileMenu.add(miRead);
        
        MenuItem miRandom=new MenuItem("Random");
        miRandom.addActionListener(this);
        fileMenu.add(miRandom);
        
        MenuItem miDisplay=new MenuItem("Display");
        miDisplay.addActionListener(this);
        fileMenu.add(miDisplay);
               
        MenuItem miProcess=new MenuItem("Process");
        miProcess.addActionListener(this);
        fileMenu.add(miProcess);
        
        MenuItem miExit=new MenuItem("Exit");
        miExit.addActionListener(this);
        fileMenu.add(miExit);
        
        
        WindowListener l = new WindowAdapter(){
                        
            public void windowClosing(WindowEvent ev){
                System.exit(0);
            }
            
            public void windowActivated(WindowEvent ev){
                repaint();
            }
            
            public void windowStateChanged(WindowEvent ev){
                repaint();
            }
        
        };
        
        ComponentListener k = new ComponentAdapter(){
            public void componentResized(ComponentEvent e){
                repaint();           
            }
        };
        
        this.addWindowListener(l);
        this.addComponentListener(k);
    }
    
    public void actionPerformed (ActionEvent ev){
            command = ev.getActionCommand();
                        
            if("Read Data".equals(command)){
                dataFilePath = null;
                dataFileName = null;
                
                  JFileChooser chooser = new JFileChooser();
                  chooser.setDialogType(JFileChooser.OPEN_DIALOG );
                  chooser.setDialogTitle("Open Data File");
                  
                  int returnVal = chooser.showOpenDialog(null);
                  if( returnVal == JFileChooser.APPROVE_OPTION){
                      dataFilePath = chooser.getSelectedFile().getPath();
                      dataFileName = chooser.getSelectedFile().getName();
                    }
                  try{   
                      FileReader fr = new FileReader(chooser.getSelectedFile().getPath());
                      BufferedReader br = new BufferedReader(fr);
                      String range=br.readLine();
                      
                      //System.out.println(range);//
                      range=range.replace(" ","");
                      //System.out.println(range);//
                      
                      int r = range.indexOf("C:");
                      
                      //System.out.println(range.substring(2,r));//
                      //System.out.println(range.substring(r+2));//
                      
                      //Gets the Number of Rows
                      row=Integer.parseInt(range.substring(2,r));
                      //Gets the Number of Columns
                      column=Integer.parseInt(range.substring(r+2));
                      
                      
                      Tag = new int [row][column];
                      Data= new int [row][column];
                      
                      for (int i=0; i < row; i++){
                           range=br.readLine();
                           range=range.replace(" ","");
                           for (int j = 1 ; j <= column; j++){
                                Data[i][j-1]=Integer.parseInt(range.substring(j-1,j));
                                Tag[i][j-1] = 0;
                            }
                        }
                      
                      //Close the stream
                      fr.close();
                      br.close();
                    }
                    
                    catch(IOException ioe){
                        System.exit(0);
                    }
                  
                  
                repaint();
            }
            else if("Random".equals(command)){
                row=column=0;
                String input=JOptionPane.showInputDialog(null, "Please enter an integer:","Enter the amount of rows", JOptionPane.QUESTION_MESSAGE);
                if(input!=null)row=Integer.parseInt(input);
                input=JOptionPane.showInputDialog(null, "Please enter an integer:","Enter the amount of column", JOptionPane.QUESTION_MESSAGE);
                if(input!=null)column=Integer.parseInt(input);
                if(row>0&&column>0){
                  Tag = new int [row][column];
                  Data= new int [row][column];
                      
                  for (int i=0; i<row; i++)
                    for (int j=0; j<column; j++){
                        Data[i][j]=(int)(Math.random()*9)+1;
                           Tag[i][j] = 0;
                    }
                }
            }
            else if("Process".equals(command)){
              //originally printed the values out so I can weed out a bug that was previously in the program
                for (int i=0; i<=row-1; i++)
                    for (int j=0; j<=column-1; j++){
                        // Horizontal block
                        if((j+3)<=column-1 && Data[i][j]==Data[i][j+1])
                            if(Data[i][j+1]==Data[i][j+2])
                                if(Data[i][j+2]==Data[i][j+3])
                                	Tag[i][j]=Tag[i][j+1]=Tag[i][j+2]=Tag[i][j+3]=1;
                                
                        // Vertical block
                        if((i+3)<=row-1 && Data[i][j]==Data[i+1][j])
                            if(Data[i+1][j]==Data[i+2][j])
                                if(Data[i+2][j]==Data[i+3][j])
                                	Tag[i][j]=Tag[i+1][j]=Tag[i+2][j]=Tag[i+3][j]=2;
                        
                        // Diagonal downward block
                        if((i+3)<=row-1 && (j+3)<=column-1 && Data[i][j]==Data[i+1][j+1])
                            if(Data[i+1][j+1]==Data[i+2][j+2])
                                if(Data[i+2][j+2]==Data[i+3][j+3])
                                	Tag[i][j]=Tag[i+1][j+1]=Tag[i+2][j+2]=Tag[i+3][j+3]=3;
                                
                        //Diagonal upward block
                        if(((i-3)>=0 && (j+3)<=column-1) && Data[i][j]==Data[i-1][j+1])
                            if(Data[i-1][j+1]==Data[i-2][j+2])
                                if(Data[i-2][j+2]==Data[i-3][j+3])
                                	Tag[i][j]=Tag[i-1][j+1]=Tag[i-2][j+2]=Tag[i-3][j+3]=4;
                    }
                    repaint();
                }
                
             else if("Display".equals(command)||"About".equals(command)){         
                    repaint();
                }
                
            else if("Exit".equals(command)){
                    System.exit(0);
                }

        }
        
    public void paint(Graphics g){
            
            int ww = (int)this.getWidth();
            int wh = (int)this.getHeight() - 40;
            g.setColor(Color.WHITE);
            
            if("Read Data".equals(command)){
                if (dataFileName != null)g.drawString("File --  "+dataFileName+"  -- was successfully opened", ww/2-150, wh/8);
                else g.drawString("NO File is Open", 400, 400);
            }
            
            if("Random".equals(command)){
                if (row != 0&&column!=0)g.drawString("File was successfully created", ww/2-150, wh/8);
                else if (row!=0)g.drawString("Processing...", ww/2-150, wh/2);
                else g.drawString("Error:", 400, 400);
            }
            
            if("Process".equals(command)){
                int x = (ww-column*20)/2;
                int y = (wh-row*20)/2;
                g.drawLine(x,(y-15),((ww-column*20)/2)+column*20,(y-15));
                for (int i=0; i<row; i++){
                    for (int j=0; j<column; j++){
                        g.setColor(Color.GRAY);
                        if (Tag[i][j] == 1)
                            g.setColor(Color.RED);
                        else if (Tag[i][j] == 2)
                            g.setColor(Color.CYAN);
                        else if (Tag[i][j] == 3)
                            g.setColor(Color.YELLOW);
                        else if (Tag[i][j] == 4)
                            g.setColor(Color.GREEN);
                        g.drawString(((Integer)Data[i][j]).toString(), x+5, y);
                        x=x+20;
                        g.setColor(Color.WHITE);  
                        g.drawLine(((ww-column*20)/2+(20*j)),(wh-row*20)/2-15,((ww-column*20)/2+(20*j)),((wh-row*20)/2)+(row*20)-15);
                        if(j==column-1)g.drawLine(((ww-column*20)/2+(20*column)),(wh-row*20)/2-15,((ww-column*20)/2+(20*column)),((wh-row*20)/2)+(row*20)-15);
                    }
                    x =(ww-column*20)/2;
                    g.drawLine(x,(y+5),((ww-column*20)/2)+column*20,(y+5));
                    y=y+20;
                }
            }
            else if("About".equals(command)){
              int x = (ww-column*20)/2-300;
              int y = (wh-row*20)/2;
              g.setColor(Color.GRAY);
              g.drawString(Jabout, x, y);
            }
                       
            else if((dataFileName != null && "Read Data".equals(command)) || "Display".equals(command) || "Random".equals(command)){
                int x = (ww-column*20)/2;
                int y = (wh-row*20)/2;
                g.setColor(Color.WHITE);
                g.drawLine(x,(y-15),((ww-column*20)/2)+column*20,(y-15));
                for (int i=0; i<row; i++){
                    for (int j=0; j<column; j++){
                        g.drawString( ((Integer)Data[i][j]).toString(), x+5, y);
                        x=x+20;
                        g.drawLine(((ww-column*20)/2+(20*j)),(wh-row*20)/2-15,((ww-column*20)/2+(20*j)),((wh-row*20)/2)+(row*20)-15);
                        if(j==column-1)g.drawLine(((ww-column*20)/2+(20*column)),(wh-row*20)/2-15,((ww-column*20)/2+(20*column)),((wh-row*20)/2)+(row*20)-15);
                        }
                    x = (ww-column*20)/2;
                    g.drawLine(x,(y+5),((ww-column*20)/2)+column*20,(y+5));
                    y=y+20;
                }
            }
        }
}