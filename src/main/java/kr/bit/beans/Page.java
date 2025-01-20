package kr.bit.beans;

public class Page {

    private int min; //페이지 최소번호
    private int max; //페이지 최대번호
    private int prePage; //이전 페이지
    private int nextPage; //다음 페이지
    private int pageCnt; //페이지 수
    private int currentPage; //현재 페이지

    //전체게시글수, 현재페이지, 페이지당 게시글수, 페이지 버튼개수
    public Page(int contentCnt, int currentPage, int contentPageCnt, int pa) {
        this.currentPage = currentPage;

        pageCnt = contentCnt / contentPageCnt;

        if (contentCnt % contentPageCnt > 0) {
            pageCnt++;
        }
        min = ((currentPage - 1) / contentPageCnt) * contentPageCnt + 1;
        max = min + pa - 1;

        if (max > pageCnt) {
            max = pageCnt;
        }
        prePage = min - 1;
        nextPage = max + 1;

        if (nextPage > pageCnt) {
            nextPage = pageCnt;
        }
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getPageCnt() {
        return pageCnt;
    }

    public void setPageCnt(int pageCnt) {
        this.pageCnt = pageCnt;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}


