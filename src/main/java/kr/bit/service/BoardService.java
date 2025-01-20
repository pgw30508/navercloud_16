package kr.bit.service;

import kr.bit.beans.Content;
import kr.bit.beans.Page;
import kr.bit.beans.User;
import kr.bit.dao.BoardDao;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class BoardService {

    @Value("${page.listcount}")
    private int page_listcount;

    @Value("${page.pa}")
    private int page_pa;

    @Autowired
    private BoardDao boardDao;

    @Resource(name="loginBean")
    private User loginBean;

    public void addContent(Content writeContentBean) {
        //로그인한 사람의 idx값 가져와 글작성자 idx값에 세팅
        writeContentBean.setContent_writer_idx(loginBean.getUser_idx());
        boardDao.addContent(writeContentBean);
    }

    public String getBoardInfoName(int board_info_idx) {
        return boardDao.getBoardInfoName(board_info_idx);
    }

    public List<Content> getContent(int board_info_idx, int page) {

        int start=(page-1) * page_listcount;
        RowBounds rowBounds = new RowBounds(start,page_listcount);
        return boardDao.getContent(board_info_idx, rowBounds);
    }

    public Content getInfo(int content_idx) {
        return boardDao.getInfo(content_idx);
    }

    public void modifyInfo(Content modifyBean) {
        boardDao.modifyInfo(modifyBean);
    }
    public void deleteInfo(int content_idx) {
        boardDao.deleteInfo(content_idx);
    }

    public Page getCnt(int content_board_idx, int currentPage){
        int content_cnt=boardDao.getCnt(content_board_idx);

        Page page=new Page(content_cnt, currentPage, page_listcount, page_pa);

        return page;
    }

}
