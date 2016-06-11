package com.csclab.hc.socketsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import android.util.Log;

public class MainActivity extends Activity implements android.view.View.OnClickListener{
    /** Init Variable for IP page **/
    EditText inputIP;
    Button ipSend;
    public static String ipAdd;
    public static Socket socket;

    /** Init Variable for Page 1 **/
    Button plus,minus,m,t,clean;

    //if the Number Should be used in another Page use"static"
    public static float result;
    public static EditText num1;
    public static EditText num2;
    public static float n1,n2;
    public static String oper = "";
    public static PrintWriter writer;
    public static boolean r = false;
    OutputStream out;
    BufferedReader br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(r==false) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.ip_page);
            inputIP = (EditText) findViewById(R.id.edIP);
            ipSend = (Button) findViewById(R.id.ipButton);

            ipSend.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /** Func() for setup page 1 **/
                    ipAdd = inputIP.getText().toString();
                    Log.d("Client", "Client Send");
                    System.out.println("IN click");
                    Thread t = new thread();
                    t.start();
                    jumpToMainLayout();

                }
            });
        }
        else {
            super.onCreate(savedInstanceState);
            //Thread t = new thread();
            //t.start();
            jumpToMainLayout();
        }
        };

    /** Function for page 1 setup */
    public void jumpToMainLayout() {
        //TODO: Change layout to activity_main
        setContentView(R.layout.activity_main);

        //name the things in layout
         plus = (Button) findViewById(R.id.plus);
        plus.setOnClickListener(this);
        minus = (Button) findViewById(R.id.minus);
        minus.setOnClickListener(this);
        t = (Button) findViewById(R.id.times);
        t.setOnClickListener(this);
        m = (Button) findViewById(R.id.mol);
        m.setOnClickListener(this);
        clean = (Button)findViewById(R.id.c);
        num1 = (EditText) findViewById(R.id.num1);
        num1.setOnClickListener(this);
        num2 = (EditText) findViewById(R.id.num2);
        num2.setOnClickListener(this);


    //if click the operation button , assign the numer and answer and then change Page
        plus.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view){
                n1 =  Float.parseFloat(num1.getText().toString());
                n2 =  Float.parseFloat(num2.getText().toString());
                        result = n1 + n2;
                        oper = "+";
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ResultActivity.class);
                MainActivity.this.startActivity(intent);


            }
            });
        minus.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view){
                n1 =  Float.parseFloat(num1.getText().toString());
                n2 =  Float.parseFloat(num2.getText().toString());
                result = n1 - n2;
                oper = "-";

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ResultActivity.class);
                MainActivity.this.startActivity(intent);
            }

        });
        m.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                n1 =  Float.parseFloat(num1.getText().toString());
                n2 =  Float.parseFloat(num2.getText().toString());
                if(n2 != 0) {

                    result = n1 / n2;
                    oper = "/";

                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, ResultActivity.class);
                    MainActivity.this.startActivity(intent);
                }
            }

        });
        t.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                n1 =  Float.parseFloat(num1.getText().toString());
                n2 =  Float.parseFloat(num2.getText().toString());
                result = n1 * n2;
                oper = "*";

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ResultActivity.class);
                MainActivity.this.startActivity(intent);
            }

        });

        //if click Clean Button Clean the text num1 and num2
        clean.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
               num1.setText("");
                n1 =0 ;
                num2.setText("");
                n2=0;
            }

        });

        }

    /** Function for onclick() implement */
    @Override
    public void onClick(View v) {
        Log.d("Client", "Client Send");
        System.out.println("IN click");
        Thread t = new thread();
        t.start();
    }

    class thread extends Thread{
        public void run(){

            try{

                System.out.println("Client: Waiting to connect...");
                int serverPort = 7000;

                // Create socket connect server
                socket = new Socket(ipAdd, serverPort);
                System.out.println("Connected!");

                // Create stream communicate with server
                OutputStream out = socket.getOutputStream();
                String strToSend = "Hi I'm client";
                writer =new PrintWriter( new OutputStreamWriter(socket.getOutputStream()));

                byte[] sendStrByte = new byte[1024];
                System.arraycopy(strToSend.getBytes(), 0, sendStrByte, 0, strToSend.length());
                //writer.println(sendStrByte);
                //writer.flush();


            }catch (Exception e){
                    System.out.println("Error" + e.getMessage());

                }

        }
    }
}
