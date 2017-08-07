package com.wugao.center.support.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.taobao.api.internal.util.WebUtils;
import com.wugao.center.domain.user.User;
import com.wugao.center.domain.user.UserRepo;
import com.wugao.center.domain.user.UserService;
import com.wugao.center.infrastruture.constant.SessionConstant;
import com.wugao.center.infrastruture.spring.security.PasswordEncoder;
import com.wugao.center.support.spring.session.Context;

@RestController
@RequestMapping
public class LoginConttoller {
	
	private static final Log logger = LogFactory.getLog(LoginConttoller.class);

	@Value("${taobao.lianmeng.appKey:}")
	private String lianmengAppkey;
	
	@Value("${taobao.lianmeng.secretKey:}")
	private String lianmengSecretKey;
	
	@Autowired
	UserRepo userRepo;
	
	PasswordEncoder passwordEncoder = new PasswordEncoder();
	
	@RequestMapping(value = "doLogin", method = RequestMethod.POST)
	public String doLogin(String username, String password, HttpServletRequest request){
		User user = userRepo.getByUsername(username);
		if(user != null) {
			if(passwordEncoder.matches(password, user.getPassword())) {
				Context context = new Context();
				context.setUser(user);
				if(request.getSession().getAttribute(SessionConstant.CONTEXT) == null) {
					request.getSession().setAttribute(SessionConstant.CONTEXT, context);
				}
				return "authentication success";
			}
		}
		return "authentication failed";
		
	}
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "taobaoLogin", method = RequestMethod.GET)
	public ModelAndView doLogin(String code, HttpServletRequest request) {
        try {
        	String url="https://oauth.taobao.com/token";
        	Map<String,String> props=new HashMap<String,String>();
            props.put("grant_type","authorization_code");
            props.put("code",code);
            props.put("client_id",lianmengAppkey);
            props.put("client_secret",lianmengSecretKey);
            props.put("redirect_uri","http://www.vankeda.com");
            props.put("view","web");
            String s = WebUtils.doPost(url, props, 30000, 30000);;
            
            Map<String, String> maps = (Map)JSON.parse(s); 
            String username = maps.get("taobao_user_nick");
            User user = userRepo.getByUsername(username);
            if(user == null) {
            	user = new User();
            	user.setUsername(username);
            	user.setEnabled(true);
            	user.setNickname(username);
            	userService.saveUser(user);
            }
            Context context = new Context();
			context.setUser(user);
			if(request.getSession().getAttribute(SessionConstant.CONTEXT) == null) {
				request.getSession().setAttribute(SessionConstant.CONTEXT, context);
			}
            return new ModelAndView("redirect:/");
        } catch (Exception e) {
        	e.printStackTrace();
        	logger.error(e);
        	return null;
        }
       
	}
	
	public static void main(String[] args) {
		HttpPost httpPost = new HttpPost("https://oauth.taobao.com/token");
        HttpClient client = null;
        try {
            client = new DefaultHttpClient();
            List<NameValuePair> list = new ArrayList<NameValuePair>(); 
            list.add(new BasicNameValuePair("grant_type", "authorization_code"));
            list.add(new BasicNameValuePair("response_type", "code"));
            list.add(new BasicNameValuePair("client_id", "24566675"));
            list.add(new BasicNameValuePair("client_secret", "b56978f198f135323af471331699333f"));
            list.add(new BasicNameValuePair("redirect_uri", "http://www.vankeda.com"));
            list.add(new BasicNameValuePair("code", "ou8OsvuagwEJj5QEeZzkycf9444320"));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list); 
            httpPost.setEntity(entity);
            
            HttpResponse response = client.execute(httpPost);
            System.out.println(EntityUtils.toString(response.getEntity()));
            if(response.getStatusLine().getStatusCode() == 200){
                HttpEntity resEntity = response.getEntity();
                System.out.println(EntityUtils.toString(resEntity));
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
}
