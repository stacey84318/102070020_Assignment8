package com.example;

import java.awt.Container;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Created by Gary on 16/5/28.
 */
public class Server implements Runnable{
    private Thread thread;
    private ServerSocket servSock;
    BufferedReader br ;

    //to generate a window for anser
    private JFrame window = new JFrame("Result From App");
    private JLabel s = new JLabel();
    private Container contain = this.window.getContentPane();
    
    public Server(){

        //set the window and container and text in construct
    	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);
		window.setSize(500	, 200);
		contain.setLayout(null);
		this.s.setText("The Result From App is : " );
		this.s.setBounds(20, 20, 400, 50);
		this.s.setVisible(true);
		contain.add(s);
        try {
            // Detect server ip
            InetAddress IP = InetAddress.getLocalHost();
            System.out.println("IP of my system is := "+IP.getHostAddress());
            System.out.println("Waitting to connect......");

            // Create server socket
            servSock = new ServerSocket(7000);
            
            // Create socket thread
            thread = new Thread(this);
            thread.start();
        } catch (java.io.IOException e) {
            System.out.println("Socket啟動有問題 !");
            System.out.println("IOException :" + e.toString());
        } finally{

        }
    }

    @Override
    public void run(){
        // Running for waitting multiple client
        while(true){
            try{
            	System.out.println("Waiting");
                // After client connected, create client socket connect with client
                Socket clntSock = servSock.accept();

                System.out.println("Connected!!");
                br = new BufferedReader(new InputStreamReader(clntSock.getInputStream()));

                // read line from client
                String line = br.readLine();
                
                if(!line.isEmpty()){
            		//re set the text
            		this.s.setText("The Result From App is : " +line);
            		//repaint window
            		window.repaint();
            		
                	
                }
            }
            catch(Exception e){
                //System.out.println("Error: "+e.getMessage());
            }
        }
    }
}
