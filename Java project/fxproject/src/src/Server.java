package fxproject.src.src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket; 

class Server{
    ServerSocket server;
    Socket socket;

    //to read data
    BufferedReader br;
    //to write data
    PrintWriter out;


    public Server(){
        try {
            server = new ServerSocket(1111);
            System.out.println("Server is ready to accept the connection");
            System.out.println("Waiting....");
            socket = server.accept();
            System.out.println("no more wait...");

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();

        } catch (Exception e) {
            e.printStackTrace();
        }
     }

     public void startReading()
{
  //thread keeps reading the data
  // using lambda
  Runnable r1 =()->{
    System.out.println("Started reading...");

    try{

    while(true){

        String msg = br.readLine();

        //if msg is BYE chat will exit
        if(msg.equals("Bye"))
        {
            System.out.println("Client terminated chat.");
            socket.close();
            break;
        }
        System.out.println("Client: "+msg);
    } 
}catch(Exception e)
    {
        System.out.println("Connection terminated.");
    }
   
  };

  new Thread(r1).start();
}

public void startWriting()
{
  //thread takes data , sends to clint

  Runnable r2=()->{
    
    try{

    while(!socket.isClosed())
    {
            BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
            String content = br1.readLine();
            out.println(content);
            out.flush();

            if(content.equals("Bye"))
        {
            socket.close();
            break;
        }
    }    
    }catch(Exception e)
    {
        System.out.println("Connection terminated.");
    }

  };
  new Thread(r2).start();
}

public static void main(String[] args){
    System.out.println("Server is ready.");
    new Server();
}
}