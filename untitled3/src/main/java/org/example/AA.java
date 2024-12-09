package org.example;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class AA {
    public static void main(String[] args) {
        Book book=new Book("자바",30000,"A출판사",900);
        Gson gson=new Gson();
        String str=gson.toJson(book); //book객체를 json문자열로 변환
        System.out.println(str);

        //json -> 객체
        Book book1=gson.fromJson(str, Book.class);
        // json 문자열을 다시 book 객체로 역직렬화 시킴 -> json 기반으로 book객체 생성됨
        System.out.println(book1);
        System.out.println(book1.getCompany()+" "+book1.getTitle());
        System.out.println();

        // 객체 -> json
        List<Book> list=new ArrayList<>();
        list.add(new Book("db",20000,"B출판사",800));
        list.add(new Book("web",30000,"C출판사",500));
        list.add(new Book("os",40000,"D출판사",600));

        //리스트 전체를 json 문자열로 변환
        String str2=gson.toJson(list);
        System.out.println(str2);

        // json -> 리스트
        List<Book> list2=gson.fromJson(str2,new TypeToken<List<Book>>(){}.getType());
        for(Book bo : list2) {
            System.out.println(bo);
        }
        
    }
}
