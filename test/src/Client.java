import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) {

        try{
            Socket socket=new Socket("localhost",9999);
            Scanner s=new Scanner(System.in);
            System.out.println("name : ");
            String name=s.nextLine();
            Thread sender=new Thread(new ClientSender(socket,name)); // 서버에 메세지를 보내는 역할
            Thread receiver=new Thread((new ClientReceiver(socket))); // 서버로부터 메시지 수신하는 역할
            // 각각 스레드는 메시지를 동시에 송수신 할 수 있게 하기 위해
            sender.start();
            receiver.start();
        } catch (Exception e) {

        }
    }
    //inner class
    static class ClientSender extends Thread { //메시지를 서버로 보내는 클래스
        Socket socket;
        String name;
        DataOutputStream outputStream;

        ClientSender(Socket socket, String name) {
            this.socket=socket;
            this.name=name;
            try {
                outputStream=new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void run() {
            Scanner s=new Scanner(System.in);
            try {
                if(outputStream!=null) {
                    outputStream.writeUTF(name);
                }
                while (outputStream!=null) {
                    String msg=s.nextLine();
                    if(msg.equals("quit")) {
                        break;
                    }
                    outputStream.writeUTF("["+name+"]"+msg);
                }
                outputStream.close();
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    static class ClientReceiver extends Thread { // 서버로부터 데이터를 수신하는 역할
        Socket socket;
        DataInputStream inputStream; // 서버로부터 데이터를 읽기 위한 입력 스트림

        ClientReceiver(Socket socket) {
            this.socket=socket;
            try{
                inputStream=new DataInputStream(socket.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (inputStream!=null) {
                try {
                    System.out.println(inputStream.readUTF());
                    // 서버로부터 메시지를 계속 읽어서 콘솔에 출력
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
            try {
                inputStream.close();
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}