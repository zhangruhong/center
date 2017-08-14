package com.wugao.jq.application.pub;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.internal.util.StringUtils;
import com.taobao.api.request.TbkJuTqgGetRequest;
import com.taobao.api.response.TbkJuTqgGetResponse;
import com.wugao.center.infrastruture.mybatis.Pagination;

@RestController
@RequestMapping
public class TqgController {
	
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
		TaobaoClient client = new DefaultTaobaoClient(url, lianmengAppKey, lianmengSecretKey);
		TbkJuTqgGetRequest req = new TbkJuTqgGetRequest();
		req.setAdzoneId(Long.parseLong(adzoneId));
		req.setFields("click_url,pic_url,reserve_price,zk_final_price,total_amount,sold_num,title,category_name,start_time,end_time");
		req.setStartTime(StringUtils.parseDateTime("2017-08-01 09:00:00"));
		req.setEndTime(StringUtils.parseDateTime("2017-09-20 16:00:00"));
		req.setPageNo((long)(page == null ? 1 : page));
		req.setPageSize(40L);
		TbkJuTqgGetResponse rsp;
		try {
			rsp = client.execute(req);
			return new ModelAndView("searchTqg", "objs", rsp.getResults());
		} catch (ApiException e) {
			e.printStackTrace();
			return null;
		}
	}
}
