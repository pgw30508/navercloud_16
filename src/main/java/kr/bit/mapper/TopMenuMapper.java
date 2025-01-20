package kr.bit.mapper;

import kr.bit.beans.BoardInfo;
import org.apache.ibatis.annotations.Select;

public interface TopMenuMapper {

    //board_info_idx, board_info_name 추출
    @Select("select board_info_idx, board_info_name from board_info_table")
    BoardInfo getTopMenu();
    //함수 호출시 위 쿼리문이 실행된다 -> 쿼리문 결과값이 BoardInfo에 저장됨
}
