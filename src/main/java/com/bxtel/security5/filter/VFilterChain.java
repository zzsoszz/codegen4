package com.bxtel.security5.filter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class VFilterChain implements   FilterChain{
	 private List<Filter> filterList=new ArrayList<Filter>();
	 private int index=0;
	 public FilterChain addFilter(Filter filter){  
	        this.filterList.add(filter);  
	        return this;  
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
		if(index==filterList.size()){
			index=0;
			return;//如果链条里没有filter或是链条里的filter都调用过了（有点象递归）
		}else{
			filterList.get(index++).doFilter(request, response,this);
		}
	}
}
