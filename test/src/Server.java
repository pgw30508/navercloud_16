import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class Server {
    HashMap clientMap;

    Server() {
        clientMap=new HashMap(); // 연결된 정보 저장하기 위해
        Collections.synchronizedMap(clientMap);
        // 동기화된 맵으로 초기화 - 멀티 스레드 환경에서도 안전하게 접근가능
    }
    public void start() {
        ServerSocket serverSocket=null;
        Socket socket=null;

        try {
            serverSocket=new ServerSocket(9999); // 9999포트에서 클라이언트 연결 기다림
            System.out.println("server start!!");
            while(true) {
                socket=serverSocket.accept();
                System.out.println(socket.getInetAddress()+":"+socket.getPort()+" connected");
                ServerReceiver serverReceiver=new ServerReceiver(socket);
                serverReceiver.start();
                // 각각의 클라이언트 통신 독립적으로 처리하기 위해 스레드 생성
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //모든 클라이언트에게 메시지를 전송하는 기능
    void sendAll(String msg) {
        Iterator iterator=clientMap.keySet().iterator(); //키값만 반복
        // 각각 클라이언트 맴의 모든 값을 순차적으로 돌면서
        // 각각의 클라이언트의 정보들을 가져온다 (이름,메시지)
        while(iterator.hasNext()) {
            try {
                DataOutputStream outputStream = (DataOutputStream) clientMap.get(iterator.next());
                outputStream.writeUTF(msg);
            } catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }
    class ServerReceiver extends Thread { //각각의 클라이언트들을 독립적으로 처리하기 위해 만든 쓰레드
        Socket socket;
        DataInputStream inputStream; //클라이언트로 부터 데이터를 읽는 스트림
        DataOutputStream outputStream; //클라이언트에게 데이터를 보내는 스트림

        ServerReceiver(Socket socket) {
            this.socket=socket;
            try {
                inputStream=new DataInputStream(socket.getInputStream());
                outputStream=new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        @Override
        public void run() {
            String name="";
            try {
                name=inputStream.readUTF();
                if(clientMap.get(name)!=null) {
                    outputStream.writeUTF("already name : " + name);
                    outputStream.writeUTF("please reconnect");
                    System.out.println(socket.getInetAddress()+" : "+socket.getPort()+" disconnect!!!");
                    inputStream.close();
                    outputStream.close();
                    socket.close();
                    socket=null;
                }
                else { // 동일한 이름이 아니라면(해시맵에 이름이 없다면)
                    sendAll(name+" come in");  // 모든 클라이언트한테 ~~들어왔어
                    clientMap.put(name,outputStream); // 해당 클라이언트를 맵에 추가
                    while (inputStream!=null) {
                        sendAll(inputStream.readUTF());
                        //클라이언트가 보낸 메시지 읽어와서 다른 모든 클라이언트한테 전송(브로드캐스트)
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            finally {
                if(socket!=null) { // 예외발생시 or 연결종료되면
                    sendAll(name+" exit!"); // 모든 클라이언트한테
                    clientMap.remove(name); // 해당 클라이언트 삭제
                    System.out.println(socket.getInetAddress()+" : "+socket.getPort()+" disconnect!!!");
                }
            }
        }
    }
    public static void main(String[] args) {
        new Server().start();
    }
}