package kr.or.mn.dto;

public class PageDTO {
	//검색
	private String search;
	private String searchtxt;
	
	//한페이지
	private int currpage;   //현재페이지 (받기)
	private int totalcount; //총 게시글 수 (받기)
	private int pageSize;	//한페이지에 보여질 자료수	 (받기)
	private int totalpage;
	private int startrow;
	private int endrow;
	
//	if(endrow>totalcount) {
//		endrow=totalcount;
//	}
	
	//블럭
	int pageblock; //한번에 보여줄 페이지 수 5로 통일
	int startblock;
	int endblock;
//	if(endblock>totalpage) {
//		endblock=totalpage;
//	}
	
	public PageDTO(String search, String searchtxt, int currpage,int totalcount, int pageSize) {
		this.search=search;
		this.searchtxt=searchtxt;
		
		this.currpage=currpage;
		this.totalcount=totalcount;
		this.pageSize=pageSize;
		this.totalpage=(int) Math.ceil((float)totalcount/pageSize);
		this.startrow=(currpage-1)*pageSize+1; 
		this.endrow=startrow+pageSize-1;
		if(endrow>totalcount) 
			endrow=totalcount;
		
		
		this.pageblock = 5; //한번에 보여줄 페이지 수 5로 통일
		this.startblock=(currpage-1)/pageblock*pageblock+1;
		this.endblock=startblock+pageblock-1;
		if(endblock>totalpage) 
			endblock=totalpage;
		
	}

	
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSearchtxt() {
		return searchtxt;
	}

	public void setSearchtxt(String searchtxt) {
		this.searchtxt = searchtxt;
	}

	public int getCurrpage() {
		return currpage;
	}

	public void setCurrpage(int currpage) {
		this.currpage = currpage;
	}

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public int getStartrow() {
		return startrow;
	}

	public void setStartrow(int startrow) {
		this.startrow = startrow;
	}

	public int getEndrow() {
		return endrow;
	}

	public void setEndrow(int endrow) {
		this.endrow = endrow;
	}

	public int getPageblock() {
		return pageblock;
	}

	public void setPageblock(int pageblock) {
		this.pageblock = pageblock;
	}

	public int getStartblock() {
		return startblock;
	}

	public void setStartblock(int startblock) {
		this.startblock = startblock;
	}

	public int getEndblock() {
		return endblock;
	}

	public void setEndblock(int endblock) {
		this.endblock = endblock;
	}
	
	

	
}
