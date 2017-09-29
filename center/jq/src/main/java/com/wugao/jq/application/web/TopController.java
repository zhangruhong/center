package com.wugao.jq.application.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wugao.center.infrastruture.mybatis.Pagination;
import com.wugao.jq.domain.hotGoods.GoodsHot;
import com.wugao.jq.domain.hotGoods.GoodsHotRepo;

@RestController
public class TopController {
	
	@Resource
	GoodsHotRepo goodsHotRepo;

	@RequestMapping(value = "v/top", method = RequestMethod.GET)
	public ModelAndView toTopPage(String keyword, Integer page) {
		ModelAndView mav = new ModelAndView("web/top");
		if(StringUtils.isEmpty(page)) {page = 1;}
		Pagination pagination = new Pagination();
		pagination.setPage(page);
		pagination.setPageSize(24);
		List<GoodsHot> goodsList = goodsHotRepo.getHighCommision(pagination);
		pagination.setRows(goodsList);
		mav.addObject("objs", goodsList);
		mav.addObject("currPage", page);
		mav.addObject("beginPage", page % 5 == 0 ? page - 4 : page / 5 * 5 + 1);
		mav.addObject("endPage", page % 5 == 0 ? page : (page + 5) / 5 * 5);
		mav.addObject("totalPageSize", pagination.getTotal());
		return mav;
	}
}
