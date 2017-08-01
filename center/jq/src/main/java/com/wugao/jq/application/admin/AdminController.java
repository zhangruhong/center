package com.wugao.jq.application.admin;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.taobao.api.ApiException;
import com.wugao.jq.domain.goods.GoodsService;

@RestController
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	GoodsService goodsService;

	@RequestMapping(value = "admin", method = RequestMethod.GET, produces = "text/html")
	public ModelAndView toAdminPage() {
		return new ModelAndView("admin/admin");
	}
	
	@RequestMapping(value = "collectGoodsA", method = RequestMethod.GET)
	public void collectGoodsFromXsl() {
		goodsService.saveBatch(new HSSFWorkbook());
	}
	
	@RequestMapping(value = "collectGoods", method = RequestMethod.GET)
	public void collectGoodsFromRepo() throws Exception {
		goodsService.saveBatchFromRepo();
	}
}
