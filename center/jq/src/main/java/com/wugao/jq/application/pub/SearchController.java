package com.wugao.jq.application.pub;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.taobao.api.domain.NTbkItem;
import com.taobao.api.request.TbkDgItemCouponGetRequest;
import com.taobao.api.request.TbkItemGetRequest;
import com.taobao.api.request.TbkJuTqgGetRequest;
import com.taobao.api.request.TbkUatmEventItemGetRequest;
import com.taobao.api.response.TbkDgItemCouponGetResponse;
import com.taobao.api.response.TbkDgItemCouponGetResponse.TbkCoupon;
import com.taobao.api.response.TbkItemGetResponse;
import com.taobao.api.response.TbkJuTqgGetResponse;
import com.taobao.api.response.TbkUatmEventItemGetResponse;
import com.wugao.center.infrastruture.exception.AppException;
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
	
	@RequestMapping(value = "search/searchInTao", method = RequestMethod.GET)
	public Pagination searchInTao(String title, Pagination pagination){
		TaobaoClient client = new DefaultTaobaoClient(url, lianmengAppKey, lianmengSecretKey);
		TbkDgItemCouponGetRequest req = new TbkDgItemCouponGetRequest();
		req.setAdzoneId(Long.valueOf(adzoneId));
		req.setPlatform(1L);
		req.setPageSize(Long.valueOf(pagination.getPageSize()));
		req.setPageNo(Long.valueOf(pagination.getPage()));
		TbkDgItemCouponGetResponse rsp;
		try {
			rsp = client.execute(req);
			pagination.setTotal(rsp.getTotalResults().intValue());
			List<TbkCoupon> items = rsp.getResults();
			return pagination.setRows(goodsService.parseCouponToGoods(items));
		} catch (ApiException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static void main(String[] args) throws ApiException, ParseException {
		GoodsService goodsService = new GoodsService();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String url = "http://gw.api.taobao.com/router/rest";
		String appkey = "24567059";
		String secret = "4268ffd18a509977d95095092fb610bc";
		String adzoneId = "122394295";
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		TbkJuTqgGetRequest req = new TbkJuTqgGetRequest();
		req.setAdzoneId(Long.valueOf(adzoneId));
		req.setFields("click_url,pic_url,reserve_price,zk_final_price,total_amount,sold_num,title,category_name,start_time,end_time");
		req.setStartTime(simpleDateFormat.parse("2016-07-01 00:00:00"));
		req.setEndTime(simpleDateFormat.parse("2017-09-01 00:00:00"));
		req.setPageNo(1L);
		req.setPageSize(96L);
		TbkJuTqgGetResponse rsp = client.execute(req);
		System.out.println(rsp.getResults().get(1).getClickUrl());
	}
	
}
