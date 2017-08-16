package com.wugao.jq.application.pub;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkDgItemCouponGetRequest;
import com.taobao.api.response.TbkDgItemCouponGetResponse;
import com.taobao.api.response.TbkDgItemCouponGetResponse.TbkCoupon;
import com.wugao.center.infrastruture.mybatis.Pagination;
import com.wugao.jq.domain.goods.GoodsService;

@RestController
@RequestMapping
public class TicketController {
	
	@Value("${taobao.api.url}")
	private String url;
	
	@Value("${taobao.lianmeng.appKey}")
	private String lianmengAppKey;
	
	@Value("${taobao.lianmeng.secretKey}")
	private String lianmengSecretKey;
	
	@Value("${tao.adzone.id}")
	private String adzoneId;
	
	@Autowired
	private GoodsService goodsService;

	@RequestMapping(value = "v/ticket", method = RequestMethod.GET, produces = "text/html")
	public ModelAndView toTaobaoTickPage() {
		ModelAndView mav = new ModelAndView("ticket");
		return mav;
	}
	
	@RequestMapping(value = "searchTicket", method = RequestMethod.GET)
	public Pagination searchInTicket(String title, Pagination pagination){
		TaobaoClient client = new DefaultTaobaoClient(url, lianmengAppKey, lianmengSecretKey);
		TbkDgItemCouponGetRequest req = new TbkDgItemCouponGetRequest();
		req.setAdzoneId(Long.valueOf(adzoneId));
		req.setPlatform(1L);
		req.setQ(title);
		req.setPageSize(Long.valueOf(pagination.getPageSize()));
		req.setPageNo(Long.valueOf(pagination.getPage()));
		TbkDgItemCouponGetResponse rsp;	
		try {
			rsp = client.execute(req);
			pagination.setTotal(rsp.getTotalResults().intValue());
			List<TbkCoupon> items = rsp.getResults();
			pagination.setRows(goodsService.parseCouponToGoods(items));
			return pagination;
		} catch (ApiException e) {
			e.printStackTrace();
			return null;
		}
	}
}
