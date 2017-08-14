package com.wugao.jq.application.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wugao.jq.domain.goods.GoodsService;

@RestController
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	GoodsService goodsService;

	@RequestMapping(value = "admin", method = RequestMethod.GET, produces = "text/html")
	public ModelAndView toAdminPage() {
		return new ModelAndView("admin");
	}
	
	@RequestMapping(value = "collectGoods", method = RequestMethod.GET)
	public void collectGoodsFromXsl(String type) {
		try {
			if("favorite".equals(type)) {
				goodsService.saveBatchFromFavorite();
			}else if("event".equals(type)) {
				goodsService.saveBatchFromEvent();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}