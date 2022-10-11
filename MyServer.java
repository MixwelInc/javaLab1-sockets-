package lab1;

import java.net.*;  
import java.io.*;  
class MyServer{  
    public static void main(String args[])throws Exception {  
        ServerSocket ss=new ServerSocket(3333);  
        System.out.print("Waiting for client\n");
        Socket s=ss.accept();  
        System.out.print("Connection established\n");
        DataInputStream din=new DataInputStream(s.getInputStream());  
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());
        boolean boilerState = false;

        String clMsg="",answer="",welcomeStr = "Hello!\nThe boiler is turned off.\nType /help to see the commands.\n",
        helpStr = "To turn off the boiler type -off\nTo turn on the boiler type -on\nTo stop server type /stop\nTo find out the state of boiler type /state\n",
        gbStr = "The server is turned off\n";

        dout.writeUTF(welcomeStr);
        dout.flush();

        while(true){  
            clMsg=din.readUTF().toString();                                       //reading what client sends
            System.out.println("client says: \n"+clMsg);                 //printing client message
            if(clMsg.equals("/stop")) {                      // checking for /stop
                dout.writeUTF(gbStr);
                break;                                              //if /stop then break
            }
            else if(clMsg.equals("/help")) {                             //if /help send helpMsg
                answer = helpStr;
            }
            else if(clMsg.equals("-on") && !boilerState ) {
                boilerState = true;
                answer = "The boiler is now turned on\n";
            }
            else if(clMsg.equals("-on") && boilerState ) {
                answer = "The boiler is already turned on\n";
            }
            else if(clMsg.equals("-off") && !boilerState ) {
                answer = "The boiler is already turned off\n";
            }
            else if(clMsg.equals("-off") && boilerState ) {
                answer = "The boiler is now turned off\n";
                boilerState = false;
            }
            else if(clMsg.equals("/state")) {
                if(boilerState) {
                    answer = "The boiler is turned on";
                }
                else {
                    answer = "The boiler is turned off";
                }
            }
            
            //clMsg=din.readUTF().toString(); 
            dout.writeUTF(answer);
            dout.flush();                                           //writing buffered answer
        }
        s.close();
        ss.close();
    }
}  