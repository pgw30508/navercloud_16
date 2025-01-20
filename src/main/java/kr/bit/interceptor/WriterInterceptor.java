package kr.bit.interceptor;

import kr.bit.beans.BoardInfo;
import kr.bit.beans.Content;
import kr.bit.beans.User;
import kr.bit.service.BoardService;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriterInterceptor implements HandlerInterceptor {

    private User loginBean;
    private BoardService boardService;

    public WriterInterceptor(User loginBean, BoardService boardService) {
        this.loginBean = loginBean;
        this.boardService = boardService;
    }

    //게시글 작성자가 아닌 다른 사람이 수정, 삭제 못하도록
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String str=request.getParameter("content_idx");
        int content_idx=Integer.parseInt(str);

        Content content=boardService.getInfo(content_idx);
        //하나의 게시글 조회
        if(content.getContent_writer_idx() != loginBean.getUser_idx()) {
            String str2=request.getContextPath();

            response.sendRedirect(str2+"/board/not_writer");
            return false;
        }
        return true;
    }
}
