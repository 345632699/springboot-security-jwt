package com.hotel.common;

import java.io.Serializable;
import java.util.List;

/**
 * 分页模型
 * @author herry
 *
 * @param <T>
 */
public class PageModel<T> implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -2410343965381720697L;
	public static Integer DEFAULT_PAGESIZE = 10;
    public static Integer DEFAULT_CURRENTPAGE = 1;
	/**
	 * 总条数
	 */
	private Integer totalCount;
	
	/**
	 * 当前页
	 */
	private Integer currentPage;
	
	/**
	 * 分页大小
	 */
	private Integer limit;
	
	/**
	 * 数据内容
	 */
	private List<T>data;

	public PageModel(){
	    limit = DEFAULT_PAGESIZE;
	    //currentPage = DEFAULT_CURRENTPAGE;
	}
	public Integer getTotalCount() {
		return totalCount;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getCurrentPage() {
		return currentPage==null?DEFAULT_CURRENTPAGE:currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	
	
	
}
