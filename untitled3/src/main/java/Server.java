import lombok.Synchronized;
import org.json.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) {
        ExecutorService executorService= Executors.newCachedThreadPool();
        new Server().start();
    }

    class SocketClient {}
    private int sequence;
    List<Product> list;
    ExecutorService executorService=Executors.newCachedThreadPool();

    public void start() {
        ServerSocket serverSocket=null;
        Socket socket=null;

        try {
            serverSocket=new ServerSocket(9999);
            executorService=Executors.newCachedThreadPool();
            list=Collections.synchronizedList(new ArrayList<Product>());
            System.out.println("server start!!");
            while(true) {
                socket=serverSocket.accept();
                System.out.println(socket.getInetAddress()+":"+socket.getPort()+" connected");
                ServerReceiver serverReceiver=new ServerReceiver(socket);
                serverReceiver.start();

            }
        } catch (IOException e) {
        }
    }


    class ServerReceiver extends Thread {
        Socket socket;
        DataInputStream inputStream;
        DataOutputStream outputStream;

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

            String status;

            executorService.execute(()-> {
            try {
                while (true) {
                    String all = inputStream.readUTF();
                    JSONObject JsonRoot = new JSONObject(all);
                    String menu=JsonRoot.getString("menu");
                    switch (menu) {
                        case "0":
                            JSONArray data0=new JSONArray();
                            for (Product p : list) {
                                JSONObject product0 = new JSONObject();
                                product0.put("no", p.getNo());
                                product0.put("name", p.getName());
                                product0.put("price", p.getPrice());
                                product0.put("stock", p.getStock());
                                data0.put(product0);
                            }
                                JSONObject response0 = new JSONObject();
                                response0.put("status", "success");
                                response0.put("data", data0);
                                outputStream.writeUTF(response0.toString());
                                outputStream.flush();
                            break;
                        case "1":
                            JSONObject jsonObject1 =JsonRoot.getJSONObject("data");
                            Product product1=new Product();
                            product1.setNo(++sequence);
                            product1.setName(jsonObject1.getString("name"));
                            product1.setPrice(jsonObject1.getInt("price"));                                // mname 값
                            product1.setStock(jsonObject1.getInt("stock"));
                            list.add(product1);

                            JSONObject response1 = new JSONObject();
                            response1.put("status", "success");
                            response1.put("data", new JSONObject());
                            outputStream.writeUTF(response1.toString());
                            outputStream.flush();
                            break;
                        case "2":
                            JSONObject jsonObject2 =JsonRoot.getJSONObject("data");
                            int i2=jsonObject2.getInt("no");
                            for(int i=0; i<list.size(); i++) {
                                Product product2 = list.get(i);
                                if (product2.getNo() == i2) {
                                    product2.setName(jsonObject2.getString("name"));
                                    product2.setPrice(jsonObject2.getInt("price"));                                // mname 값
                                    product2.setStock(jsonObject2.getInt("stock"));
                                }
                            }
                            JSONObject response2 = new JSONObject();
                            response2.put("status", "success");
                            response2.put("data", new JSONObject());
                            outputStream.writeUTF(response2.toString());
                            outputStream.flush();
                            break;
                        case "3":
                            JSONObject jsonObject3 =JsonRoot.getJSONObject("data");
                            Product product3=new Product();
                            int i3=jsonObject3.getInt("no");
                            Iterator<Product> iterator = list.iterator();
                            while(iterator.hasNext()) {
                                Product product = iterator.next();
                                if(product.getNo()==i3) {
                                    iterator.remove();
                                }
                            }
                            JSONObject response3 = new JSONObject();
                            response3.put("status", "success");
                            response3.put("data", new JSONObject());
                            outputStream.writeUTF(response3.toString());
                            outputStream.flush();
                            break;
                        case "4":
                            break;
                        default:
                            break;
                        }
                    }
                } catch (Exception e) {
            }
            });
        }
    }
}


