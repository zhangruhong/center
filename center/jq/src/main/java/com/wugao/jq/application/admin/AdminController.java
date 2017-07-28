package com.wugao.jq.application.admin;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wugao.center.domain.goods.GoodsService;

@RestController
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	GoodsService goodsService;

	@RequestMapping(value = "admin", method = RequestMethod.GET, produces = "text/html")
	public ModelAndView toAdminPage() {
		return new ModelAndView("admin/admin");
	}
	
	@RequestMapping(value = "collectGoods", method = RequestMethod.GET)
	public void collectGoods() {
		goodsService.saveBatch(new HSSFWorkbook());
	}
}