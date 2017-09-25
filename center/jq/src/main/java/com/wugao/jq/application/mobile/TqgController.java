package com.wugao.jq.application.mobile;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkJuTqgGetRequest;
import com.taobao.api.response.TbkJuTqgGetResponse;
import com.taobao.api.response.TbkJuTqgGetResponse.Results;

@RestController("mobile_tqg")
@RequestMapping
public class TqgController {
	
	private static final Log logger = LogFactory.getLog(TqgController.class);
	
	@Value("${taobao.api.url}")
	private String url;
	
	@Value("${taobao.lianmeng.appKey}")
	private String lianmengAppKey;
	
	@Value("${taobao.lianmeng.secretKey}")
	private String lianmengSecretKey;
	
	@Value("${tao.adzone.id}")
	private String adzoneId;

	@RequestMapping(value = "m/tqg", method = RequestMethod.GET)
	public ModelAndView ToTqgPage() {
		return new ModelAndView("mobile/tqg");
		
	}
	
	@RequestMapping(value = "m/getTqg", method = RequestMethod.GET)
	public List<Results> getTqg(Integer page){
		TaobaoClient client = new DefaultTaobaoClient(url, lianmengAppKey, lianmengSecretKey);
		TbkJuTqgGetRequest req = new TbkJuTqgGetRequest();
		req.setAdzoneId(Long.parseLong(adzoneId));
		req.setFields("click_url,pic_url,reserve_price,zk_final_price,total_amount,sold_num,title,category_name,start_time,end_time");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -2);
		req.setStartTime(calendar.getTime());
		calendar.add(Calendar.MONDAY, 12);
		req.setEndTime(calendar.getTime());
		req.setPageNo((long)(StringUtils.isEmpty(page) ? 1 : page));
		req.setPageSize(40L);
		TbkJuTqgGetResponse rsp;
		try {
			rsp = client.execute(req);
			return rsp.getResults();
		} catch (ApiException e) {
			e.printStackTrace();
			return null;
		}
	} 
}
