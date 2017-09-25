package com.wugao.jq.application.mobile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wugao.center.infrastruture.mybatis.Pagination;
import com.wugao.jq.domain.category.Category;
import com.wugao.jq.domain.category.CategoryRepo;
import com.wugao.jq.domain.goods.GoodsRepo;
import com.wugao.jq.domain.vo.search.SearchVo;

@RestController("mobile_tenyuan")
@RequestMapping
public class TenyuanController {
	
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
	private CategoryRepo categoryRepo;
	
	
	
	@RequestMapping(value = "m/tenyuan", method = RequestMethod.GET, produces = "text/html")
	public ModelAndView toSearchPage() {
		ModelAndView mav = new ModelAndView("mobile/tenyuan");
		mav.addObject("topCategories", categoryRepo.getTopCategory());
		return mav;
	}
	
	@RequestMapping(value = "m/tenyuan/search", method = RequestMethod.GET)
	public Pagination search(SearchVo searchVo, Pagination pagination) {
		searchVo.setType("tenYuan");
		return pagination.setRows(goodsRepo.getListBySearch(searchVo, pagination));
	}
	
	@RequestMapping(value = "m/tenyuan/getChildrenCategory", method = RequestMethod.GET)
	public List<Category> getChildrenCategory(String id){
		return categoryRepo.getChildren(id);
	}
	
}
