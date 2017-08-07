package com.wugao.jq.application.pub;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wugao.center.infrastruture.mybatis.Pagination;
import com.wugao.jq.domain.category.CategoryRepo;
import com.wugao.jq.domain.goods.GoodsRepo;
import com.wugao.jq.domain.vo.search.SearchVo;

@RestController
@RequestMapping(value = "index")
public class IndexController {
	
	private static final String TYPE_HIGH_RETURN = "highReturn";
	private static final String TYPE_SUPER_TICKET = "superTicket";
	private static final String TYPE_TEN_YUAN = "TenYuan";
	
	@Resource
	GoodsRepo goodsRepo;
	
	@Resource
	CategoryRepo categoryRepo;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView toIndexPage() {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("categories", categoryRepo.getTopCategory());
		return mav;
	}
	
	@RequestMapping(value = "getGoods", method = RequestMethod.GET)
	public Pagination getGoods(SearchVo searchVo, Pagination pagination) {
//		if(TYPE_HIGH_RETURN.equals(type)) {
//			return pagination.setRows(goodsRepo.getListByHighReturn(pagination));
//		}else if(TYPE_SUPER_TICKET.equals(type)) {
//			return pagination.setRows(goodsRepo.getListBySuperTicket(pagination));
//		}else if(TYPE_TEN_YUAN.equals(type)){
//			return pagination.setRows(goodsRepo.getListByTenYuan(pagination));
//		}
		if(!StringUtils.isEmpty(searchVo.getCategoryPid())) {
			return pagination.setRows(goodsRepo.getListBySearch(searchVo, pagination));
		}
		
		return pagination.setRows(goodsRepo.getListByTopSale(pagination));
	}
	
}
