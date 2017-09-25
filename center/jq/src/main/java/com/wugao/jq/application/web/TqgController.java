package com.wugao.jq.application.web;

import java.util.Calendar;

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

@RestController
@RequestMapping
public class TqgController {
	
	private static final Log logger = LogFactory.getLog(TqgController.class);
	
	private static int TOTAL_PAGE_SIZE = 1000;
	@Value("${taobao.api.url}")
	private String url;
	
	@Value("${taobao.lianmeng.appKey}")
	private String lianmengAppKey;
	
	@Value("${taobao.lianmeng.secretKey}")
	private String lianmengSecretKey;
	
	@Value("${tao.adzone.id}")
	private String adzoneId;

	@RequestMapping(value = "v/tqg", method = RequestMethod.GET)
	public ModelAndView ToTqgPage(Integer page) {
		ModelAndView mav = new ModelAndView("web/tqg");
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
			if(rsp.getResults().size() > 0 && rsp.getResults().size() < req.getPageSize()) {
				TOTAL_PAGE_SIZE = page;
			}
			mav.addObject("objs", rsp.getResults());
			mav.addObject("currPage", req.getPageNo());
			mav.addObject("beginPage", req.getPageNo() % 5 == 0 ? req.getPageNo() - 4 : req.getPageNo() / 5 * 5 + 1);
			mav.addObject("endPage", req.getPageNo() % 5 == 0 ? req.getPageNo() : (req.getPageNo() + 5) / 5 * 5);
			mav.addObject("totalPageSize", TOTAL_PAGE_SIZE);
			return mav;
		} catch (ApiException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Scheduled(cron = "0 0 0 * * ? ")
	public void refreshTotalPageSize() {
		for(int i = 1 ; i <= 1000; i++) {
			TaobaoClient client = new DefaultTaobaoClient(url, lianmengAppKey, lianmengSecretKey);
			TbkJuTqgGetRequest req = new TbkJuTqgGetRequest();
			req.setAdzoneId(Long.parseLong(adzoneId));
			req.setFields("click_url,pic_url,reserve_price,zk_final_price,total_amount,sold_num,title,category_name,start_time,end_time");
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -6);
			req.setStartTime(calendar.getTime());
			calendar.add(Calendar.MONDAY, 12);
			req.setEndTime(calendar.getTime());
			req.setPageNo((long)i);
			req.setPageSize(40L);
			TbkJuTqgGetResponse rsp;
			try {
				rsp = client.execute(req);
				if(rsp.getResults().size() > 0 && rsp.getResults().size() < req.getPageSize()) {
					TOTAL_PAGE_SIZE = i;
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
		}
	}
}
