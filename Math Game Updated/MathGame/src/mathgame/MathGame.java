/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mathgame;

/**
 *
 * @author yji inlong
 */
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;


public class MathGame extends JFrame implements ActionListener {
    
    static final String  DB_URL = "jdbc:mysql://localhost:3306/";
    static final String USER = "root";
    static final String PASS = "";
    
    Random random = new Random();
    int difficulty = 0;
    int score = 0;
    double answer = 0;
    double playerAnswer = 0;
    String playerName;
    String question;
    
    JTextField quests, ans, results, name;
    JButton start, enter, end;
    JLabel title, questHere, ansHere, over;
    JFrame starting, gaming, ending, leaderboard;
    


    public void algo()
    {
        int num1 = 1+random.nextInt(100);
        int num2 = 1+random.nextInt(100);
        int ques = 1+random.nextInt(4);
        
        if(ques == 1)
        {
            question = ("The sum of " + num1 + " and " + num2 + " is?");
            answer = (num1 + num2);
        }
        if(ques == 2)
        {
            question = ("The difference of " + num1 + " and " + num2 + " is?");
            answer = (num1 - num2);
        }
        if(ques == 3)
        {
            question = ("The product of " + num1 + " and " + num2 + " is?");
            answer = (num1 * num2);
        }
        if(ques == 4)
        {
            question = ("The quotient of " + num1 + " and " + num2 + " is?");
            answer = (num1 / num2);
        }
      }
    
    MathGame()
    { 
        //starting = new JFrame("Brain Twister");
        //title = new JLabel("BRAIN TWISTER");
        //title.setBounds(350, 100, 250, 100);
        //start = new JButton("START");
        //start.setBounds(350, 360, 100, 50);
        //start.addActionListener(this);
        
        //starting.add(title);
        //starting.add(start);
        
        //starting.setSize(800, 600);
        //starting.setLayout(null);
        //starting.setResizable(false);
        //starting.setVisible(true);
        //starting.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        gaming = new JFrame("Brain Twister");
        quests = new JTextField("");
        quests.setBounds(100, 30, 570, 300 );
        quests.setEditable(false);
        ans = new JTextField();
        ans.setBounds(120, 360, 250, 100 );
        questHere = new JLabel("Question:");
        ansHere = new JLabel("Answer:");
        enter = new JButton("ENTER");
        enter.setBounds(390, 360, 250, 100);
        
        
        
        gaming.add(quests);
        gaming.add(ans);
        gaming.add(enter);
        
        gaming.setSize(800, 600);
        gaming.setLayout(null);
        gaming.setResizable(false);
        gaming.setVisible(true);
        gaming.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        enter.addActionListener(this);
        algo();
        
        quests.setText(question); 
        
        ending = new JFrame ("Brain Twister");
        results = new JTextField("");
        results.setBounds(300, 200, 225, 100 );
        results.setEditable(false);
        results.setText("Congratulations! Your score was " + score);
        name = new JTextField("Insert Name");
        name.setBounds(120, 360, 250, 100 );
        end = new JButton ("Okay");
        end.setBounds(390, 360, 250, 100);
        over = new JLabel();
        
        
        ending.add(end);
        ending.add(results);
        ending.add(name);
        
        ending.setSize(800, 600);
        ending.setLayout(null);
        ending.setResizable(false);
        ending.setVisible(false);
        ending.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        leaderboard = new JFrame("BrainTwister");
        
        leaderboard.setSize(800, 600);
        leaderboard.setLayout(null);
        leaderboard.setResizable(false);
        leaderboard.setVisible(false);
        leaderboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        playerAnswer = Double.parseDouble(ans.getText());
        
        if(e.getSource() == start)
        {
            starting.setVisible(false);
            gaming.setVisible(true);
        }
        
        if(e.getSource() == enter)
        {
            if(playerAnswer == answer)
            {
                score++;
                algo();
                quests.setText(question);
                
            }else
        {
            gaming.setVisible(false);
            ending.setVisible(true);
            results.setText("Congratulations! Your score was " + score);
        }
        }
    }
    public static void main(String[] args) 
    {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
        ){
            String sql = "CREATE DATABASE Players";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfuly");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        new MathGame();
        
    }

}
