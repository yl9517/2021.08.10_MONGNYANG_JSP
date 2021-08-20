package kr.or.mn.dto;

public class PagingDTO {
	private String search;
	private String searchtxt;
	private int totalcount;
	private int pagepercount;
	private int totalpage;
	private int startrow;
	private int endrow;
	private int blockcount;
	private int startblock;
	private int endblock;
	private int currpage;
	
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
	public int getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	public int getPagepercount() {
		return pagepercount;
	}
	public void setPagepercount(int pagepercount) {
		this.pagepercount = pagepercount;
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
	public int getBlockcount() {
		return blockcount;
	}
	public void setBlockcount(int blockcount) {
		this.blockcount = blockcount;
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
	public int getCurrpage() {
		return currpage;
	}
	public void setCurrpage(int currpage) {
		this.currpage = currpage;
	}
	
	
}
