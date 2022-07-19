package Entity;
public class Page {
    private int pageIndex; // 第几页，从1开始
    private Integer pageSize; // 页面大小
    private Integer totalCounts; // 总条数，数据库中符合查询条件记录条数
    private Integer totalPages; // 总页数，总条数/页面大小
    private Integer startRows; // 起始条

    public Page(Integer pageIndex, Integer pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.setStartRows((pageIndex - 1) * pageSize);
    }

//    public Page(Integer pageIndex) {
//        Page(pageIndex,10);
//
//    }

    

	public Integer getPageIndex() {
        return this.pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(Integer totalCounts) {

        this.totalCounts = totalCounts;
        //System.out.println(this.totalCounts+"aaaaaaaaaaaaaaaaaaaaaaaa");
        this.setTotalPages(totalCounts % pageSize == 0 ? totalCounts / pageSize : totalCounts / pageSize + 1);
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getStartRows() {
        return startRows;
    }

    public void setStartRows(Integer startRows) {
        this.startRows = startRows;
    }

}
