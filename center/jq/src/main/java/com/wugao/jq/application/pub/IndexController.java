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
	
	@Resource
	GoodsRepo goodsRepo;
	
	@RequestMapping(value = "getGoodsByHighReturn", method = RequestMethod.GET)
	public Pagination getGoodsByHighReturn(Pagination pagination) {
		return pagination.setRows(goodsRepo.getListByHighReturn(pagination));
	}
	
	@RequestMapping(value = "getGoodsByTopSale", method = RequestMethod.GET)
	public Pagination getGoodsByTopSale(Pagination pagination) {
		return pagination.setRows(goodsRepo.getListByTopSale(pagination));
	}

}
