package com.wugao.center.infrastruture.filter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.wugao.center.infrastruture.utils.XssEncoder;


public class ParameterRequestWrapper extends HttpServletRequestWrapper{

	private Map<String , String[]> params = new HashMap<String, String[]>();
	
	public ParameterRequestWrapper(HttpServletRequest request) {
		super(request);
		this.params.putAll(request.getParameterMap());
		this.modifyParameterValues(); 
	}

	private void modifyParameterValues() {
		Set<String> set =params.keySet();  
        Iterator<String> it=set.iterator();  
        while(it.hasNext()){  
           String key= (String) it.next();  
           String[] values = params.get(key);
           for(int i = 0; i< values.length; i++){
        	   //替换参数中的<和>,避免展示是出现页面错乱
        	   values[i] = XssEncoder.encode(values[i].trim());
           }
           params.put(key, values);  
         }  
	}
	
	 @Override  
    public String getParameter(String name) {//重写getParameter，代表参数从当前类中的map获取  
        String[] values = params.get(name);  
        if(values == null || values.length == 0) {  
            return null;  
        }  
        return values[0];  
    }  
	 
	 @Override
    public String[] getParameterValues(String name) {  
         return params.get(name);  
    }  
	 
}
