package lab1;

import java.net.*;  
import java.io.*;  
class MyClient{  
public static void main(String args[])throws Exception {  
    Socket s=new Socket("localhost",3333);  
    DataInputStream din=new DataInputStream(s.getInputStream());  
    DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
    
    String str="",str2="";
    while(true) { 
        str2=din.readUTF();  
        System.out.println("Server says: "+str2);  
        str=br.readLine().toString();  
        dout.writeUTF(str);  
        dout.flush(); 
        if(str.equals("/stop")) break;
    }  
    
    dout.close();  
    s.close();  
}
}  