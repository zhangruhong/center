package com.wugao.jq.application.pub;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wugao.center.domain.goods.GoodsRepo;
import com.wugao.center.infrastruture.mybatis.Pagination;

@RestController
@RequestMapping(value = "index")
public class IndexController {
	
	private static final String TYPE_HIGH_RETURN = "highReturn";
	private static final String TYPE_SUPER_TICKET = "superTicket";
	private static final String TYPE_TEN_YUAN = "TenYuan";
	
	@Resource
	GoodsRepo goodsRepo;
	
	@RequestMapping(value = "getGoods", method = RequestMethod.GET)
	public Pagination getGoodsByHighReturn(String type, Pagination pagination) {
		if(TYPE_HIGH_RETURN.equals(type)) {
			return pagination.setRows(goodsRepo.getListByHighReturn(pagination));
		}else if(TYPE_SUPER_TICKET.equals(type)) {
			return pagination.setRows(goodsRepo.getListBySuperTicket(pagination));
		}else if(TYPE_TEN_YUAN.equals(type)){
			return pagination.setRows(goodsRepo.getListByTenYuan(pagination));
		}
		return null;
	}
	
	@RequestMapping(value = "getGoodsByTopSale", method = RequestMethod.GET)
	public Pagination getGoodsByTopSale(Pagination pagination) {
		return pagination.setRows(goodsRepo.getListByTopSale(pagination));
	}

}
