package kr.bit.mapper;

import kr.bit.beans.Content;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BoardMapper {

    //데이터베이스가 자동으로 생성하는 키값을 mybatis가 사용할 수 있도록 허용하여 keyProperty 속성을 통해 content_dix에 삽입
    //@Insert 어노테이션 실행전에 수를 생성한다.
    //addContent 메소드가 실행되면 숫자가 1씩 증가하고 그 숫자가 게시글 번호로 대입된다.
    //useGeneratedKeys = true : 자동으로 생성하는 키 값을 mybatis가 사용할 수 있도록 허용
    @Options(useGeneratedKeys = true, keyProperty = "content_idx")
    @Insert("insert into content_table(content_subject, content_text, content_writer_idx, " +
            "content_board_idx, content_date) values(#{content_subject}, #{content_text}, #{content_writer_idx}," +
            "#{content_board_idx},current_date())")
    void addContent(Content content);

    @Select("select board_info_name from board_info_table where board_info_idx=#{board_info_idx}")
    String getBoardInfoName(int board_info_idx);


    @Select("select a1.content_idx, a1.content_subject, a2.user_name as content_writer_name, " +
            "date_format(a1.content_date, '%Y-%m-%d') as content_date " +
            "from content_table a1 join user_table a2 on a1.content_writer_idx=a2.user_idx " +
            "where a1.content_board_idx=#{board_info_idx} " +
            "order by a1.content_idx desc limit #{offset},#{limit}")
    List<Content> getContent(@Param("board_info_idx") int board_info_idx,
                             @Param("offset") int offset, @Param("limit") int limit);


    @Select("select a2.user_name as content_writer_name, " +
            "date_format(a1.content_date, '%Y-%m-%d') as content_date, a1.content_subject, " +
            "a1.content_text, a1.content_writer_idx from content_table a1 join user_table a2 " +
            "on a1.content_writer_idx=a2.user_idx where content_idx=#{content_idx}")
    Content getInfo(int content_idx);

    @Update("update content_table set content_subject=#{content_subject}, content_text=#{content_text} " +
            "where content_idx=#{content_idx}")
    void modifyInfo(Content modifyBean);

    @Delete("delete from content_table where content_idx=#{content_idx}")
    void deleteInfo(int content_idx);

    @Select("select count(*) from content_table where content_board_idx=#{content_board_idx}")
    int getCnt(int content_board_idx);


}
