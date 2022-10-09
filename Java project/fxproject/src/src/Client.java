package fxproject.src.src;


import java.net.*;
import java.io.*;


class Client {
    Socket socket;
    //to read data
    BufferedReader br;
    //to write data
    PrintWriter out; 


   public Client()
    {
        try {
            System.out.println("Sending request to server");
            socket = new Socket("192.168.158.131",1111);
            System.out.println("Connection has been established.");

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
            
           
//start reading

    public void startReading()
{
  //thread keeps reading the data
  // using lambda
  Runnable r1 =() -> {
    System.out.println("Started reading...");

  try{
     while(true){

        String msg = br.readLine();

        //if msg is BYE chat will exit
        if(msg.equals("Bye"))
        {
            System.out.println("Server terminated chat.");
            socket.close();
            break;
        }
        System.out.println("Server: "+msg);
    }
   } catch(Exception e){

    System.out.println("Connection terminated.");
   }
  };

  new Thread(r1).start();
}
//start writing

public void startWriting()
{
  //thread takes data , sends to clint

  Runnable r2=()->{
System.out.println("Writer started....");
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
    
}catch(Exception e){

    System.out.println("Connection terminated.");
}

  };
  new Thread(r2).start();
}
    public static void main(String[] args){
        System.out.println("Client is ready.");
        new Client();
    }
    
}
    
