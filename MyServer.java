package lab1;

import java.net.*;  
import java.io.*;  
class MyServer{  
    public static void main(String args[])throws Exception {  
        ServerSocket ss=new ServerSocket(3333);  
        Socket s=ss.accept();  
        DataInputStream din=new DataInputStream(s.getInputStream());  
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());
        boolean lampState = false;

        String clMsg="",answer="",welcomeStr = "Hello!\nThe lamp is turned off.\nType /help to see the commands.\n",
        helpStr = "To turn off the lamp type -off\nTo turn on the lamp type -on\nTo stop server type /stop\n",
        gbStr = "The server is turned off\n"; 

        dout.writeUTF(welcomeStr);
        dout.flush();

        while(true){  
            clMsg=din.readUTF().toString();                                       //reading what client sends
            System.out.println("client says: \n"+clMsg);                 //printing client message
            if(clMsg.equals("/stop")) {                      // checking for /stop
                dout.writeUTF(gbStr);
                break;                                              //if /stop the break
            }
            else if(clMsg.equals("/help")) {                             //if /help send helpMsg
                answer = helpStr;
            }
            else if(clMsg.equals("-on") && !lampState ) {
                lampState = true;
                answer = "The lamp is now turned on\n";
            }
            else if(clMsg.equals("-on") && lampState ) {
                answer = "The lamp is already turned on\n";
            }
            else if(clMsg.equals("-off") && !lampState ) {
                answer = "The lamp is already turned off\n";
            }
            else if(clMsg.equals("-off") && lampState ) {
                answer = "The lamp is now turned off\n";
                lampState = false;
            }
            //clMsg=din.readUTF().toString(); 
            dout.writeUTF(answer);
            dout.flush();                                           //writing buffered answer
        }
    }
}  