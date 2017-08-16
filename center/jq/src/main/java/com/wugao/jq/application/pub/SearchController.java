package com.wugao.jq.application.pub;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.internal.util.StringUtils;
import com.taobao.api.request.TbkDgItemCouponGetRequest;
import com.taobao.api.request.TbkJuTqgGetRequest;
import com.taobao.api.response.TbkDgItemCouponGetResponse;
import com.taobao.api.response.TbkDgItemCouponGetResponse.TbkCoupon;
import com.taobao.api.response.TbkJuTqgGetResponse;
import com.taobao.api.response.TbkJuTqgGetResponse.Results;
import com.wugao.center.infrastruture.mybatis.Pagination;
import com.wugao.jq.domain.category.Category;
import com.wugao.jq.domain.category.CategoryRepo;
import com.wugao.jq.domain.goods.Goods;
import com.wugao.jq.domain.goods.GoodsRepo;
import com.wugao.jq.domain.goods.GoodsService;
import com.wugao.jq.domain.vo.search.SearchVo;

@RestController
@RequestMapping
public class SearchController {
	
	@Value("${taobao.api.url}")
	private String url;
	
	@Value("${taobao.lianmeng.appKey}")
	private String lianmengAppKey;
	
	@Value("${taobao.lianmeng.secretKey}")
	private String lianmengSecretKey;
	
	@Value("${tao.adzone.id}")
	private String adzoneId;
	
	@Autowired
	private GoodsRepo goodsRepo;
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	
	@RequestMapping(value = "v/search", method = RequestMethod.GET, produces = "text/html")
	public ModelAndView toSearchPage() {
		ModelAndView mav = new ModelAndView("search");
		mav.addObject("topCategories", categoryRepo.getTopCategory());
		return mav;
	}
	
	@RequestMapping(value = "v/search/{type}", method = RequestMethod.GET, produces = "text/html")
	public ModelAndView toSearchPage(@PathVariable("type") String type) {
		ModelAndView mav = new ModelAndView("search");
		mav.addObject("topCategories", categoryRepo.getTopCategory());
		mav.addObject("type", type);
		return mav;
	}

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public Pagination search(SearchVo searchVo, Pagination pagination) {
		return pagination.setRows(goodsRepo.getListBySearch(searchVo, pagination));
	}
	
	@RequestMapping(value = "search/getChildrenCategory", method = RequestMethod.GET)
	public List<Category> getChildrenCategory(String id){
		return categoryRepo.getChildren(id);
	}
	
	public static void main(String[] args) throws ApiException {
		String url = "http://gw.api.taobao.com/router/rest";
		String appkey = "24567059";
		String secret = "4268ffd18a509977d95095092fb610bc";
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		TbkJuTqgGetRequest req = new TbkJuTqgGetRequest();
		req.setAdzoneId(Long.parseLong("122394295"));
		req.setFields("click_url,pic_url,reserve_price,zk_final_price,total_amount,sold_num,title,category_name,start_time,end_time");
		req.setStartTime(StringUtils.parseDateTime("2017-08-01 09:00:00"));
		req.setEndTime(StringUtils.parseDateTime("2017-09-20 16:00:00"));
		req.setPageNo(1L);
		req.setPageSize(40L);
		TbkJuTqgGetResponse rsp = client.execute(req);
		System.out.println(rsp.getResults().size());
	}
	
}
