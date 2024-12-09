

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    public static void main(String[] args) {

        //서버로 보내기
        DataOutputStream outputStream;
        DataInputStream inputStream;
        try{
            Socket socket=new Socket("localhost",9999);
            outputStream=new DataOutputStream(socket.getOutputStream());
            inputStream=new DataInputStream(socket.getInputStream());
            Scanner s=new Scanner(System.in);
            //ExecutorService executorService= Executors.newCachedThreadPool();
            //Thread sender=new Thread(new ClientSender(socket,name)); // 서버에 메세지를 보내는 역할
            //Thread receiver=new Thread((new ClientReceiver(socket))); // 서버로부터 메시지 수신하는 역할
            // 각각 스레드는 메시지를 동시에 송수신 할 수 있게 하기 위해
            while (true) {
                System.out.println("[상품 목록]");
                System.out.println("----------------------------------------------------");
                System.out.println("no      name                     price      stock   ");
                System.out.println("----------------------------------------------------");
                JSONObject request=new JSONObject();
                request.put("menu","0");
                request.put("data",new JSONObject());
                outputStream.writeUTF(request.toString());
                outputStream.flush();
                //
                JSONObject response=new JSONObject(inputStream.readUTF());
                if(response.getString("status").equals("success")) {
                    JSONArray data0=response.getJSONArray("data");
                    for(int i=0; i<data0.length(); i++) {
                        JSONObject product0=data0.getJSONObject(i);
                        System.out.println(product0.getInt("no")+" "+
                                product0.getString("name")+" "+
                                product0.getInt("price")+" "+
                                product0.getInt("stock"));
                    }
                }
                System.out.println("메뉴 : 1.Create | 2. Update | 3.Delete | 4.Exit      ");
                System.out.println("선택 : ");
                String menu = s.nextLine();
                //sender.start();
                //receiver.start();
                switch (menu) {
                    case "1":
                        System.out.println("[상품 생성]");
                        Product product1 = new Product();
                        System.out.print("상품 이름: ");
                        product1.setName(s.nextLine());
                        System.out.print("상품 가격: ");
                        product1.setPrice(Integer.parseInt(s.nextLine()));
                        System.out.print("상품 재고: ");
                        product1.setStock(Integer.parseInt(s.nextLine()));

                        JSONObject data1 = new JSONObject();
                        data1.put("name", product1.getName());
                        data1.put("price", product1.getPrice());
                        data1.put("stock", product1.getStock());

                        JSONObject request1 = new JSONObject();
                        request1.put("menu", "1");
                        request1.put("data", data1);

                        outputStream.writeUTF(request1.toString());
                        outputStream.flush();

                        JSONObject response11 = new JSONObject(inputStream.readUTF());
                        if(response11.getString("status").equals("success")) {
                            break;
                        }
                        else {
                            return;
                        }
                    case "2":
                        System.out.println("[상품 수정]");
                        Product product2 = new Product();
                        System.out.print("상품 번호: ");
                        product2.setNo(Integer.parseInt(s.nextLine()));
                        System.out.print("상품 이름: ");
                        product2.setName(s.nextLine());
                        System.out.print("상품 가격: ");
                        product2.setPrice(Integer.parseInt(s.nextLine()));
                        System.out.print("상품 재고: ");
                        product2.setStock(Integer.parseInt(s.nextLine()));

                        JSONObject data2 = new JSONObject();
                        data2.put("no", product2.getNo());
                        data2.put("name", product2.getName());
                        data2.put("price", product2.getPrice());
                        data2.put("stock", product2.getStock());

                        JSONObject request2 = new JSONObject();
                        request2.put("menu", "2");
                        request2.put("data", data2);

                        outputStream.writeUTF(request2.toString());
                        outputStream.flush();
                        JSONObject response22 = new JSONObject(inputStream.readUTF());
                        if(response22.getString("status").equals("success")) {
                            break;
                        }
                        else {
                            return;
                        }
                    case "3":
                        System.out.println("[상품 삭제]");
                        Product product3 = new Product();
                        System.out.print("상품 번호: ");
                        JSONObject data3=new JSONObject();
                        data3.put("no",Integer.parseInt(s.nextLine()));

                        JSONObject request3=new JSONObject();
                        request3.put("menu", "3");
                        request3.put("data", data3);

                        outputStream.writeUTF(request3.toString());
                        outputStream.flush();
                        JSONObject response33 = new JSONObject(inputStream.readUTF());
                        if(response33.getString("status").equals("success")) {
                            break;
                        }
                        else {
                            return;
                        }
                    case "4":
                        return;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
