package com.wugao.center.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.GsonBuilder;

@RestController
@RequestMapping
public class LoginConttoller {
	
	private static final Log logger = LogFactory.getLog(LoginConttoller.class);

	@Value("${taobao.baichuan.appkey:}")
	private String baichuanAppkey;
	
	@Value("${taobao.baichuan.secretKey:}")
	private String baichuanSecretKey;
	
	@RequestMapping(value = "doLogin", method = RequestMethod.POST)
	public String doLogin(String username, String password){
		return "authentication success";
	}
	
	@RequestMapping(value = "taobaoLogin", method = RequestMethod.GET)
	public String doLogin(String code) {
        HttpPost httpGet = new HttpPost("https://oauth.taobao.com/token?grant_type=authorization_code&response_type=code&client_id=" + baichuanAppkey +"&client_secret=" + baichuanSecretKey + "&redirect_uri=http://www.vankeda.com&code=" + code);
        HttpClient client = null;
        try {
            client = new DefaultHttpClient();
            HttpResponse response = client.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == 200){
                HttpEntity entity = response.getEntity();
                logger.error(EntityUtils.toString(entity));
//                Map<String, String> gson = new GsonBuilder().create().fromJson(EntityUtils.toString(entity), Map.class);
                return EntityUtils.toString(entity);
            }
            return "getNull";
        } catch (Exception e) {
        	logger.error(e);
        	 return "error";
        }
       
	}
}
