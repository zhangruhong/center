package com.wugao.jq.application.pub;

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
import com.taobao.api.request.TbkDgItemCouponGetRequest;
import com.taobao.api.response.TbkDgItemCouponGetResponse;
import com.taobao.api.response.TbkDgItemCouponGetResponse.TbkCoupon;
import com.wugao.center.infrastruture.mybatis.Pagination;
import com.wugao.jq.domain.category.Category;
import com.wugao.jq.domain.category.CategoryRepo;
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
	
	@RequestMapping(value = "search/searchInTicket", method = RequestMethod.GET)
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
			return pagination.setRows(goodsService.parseCouponToGoods(items));
		} catch (ApiException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
