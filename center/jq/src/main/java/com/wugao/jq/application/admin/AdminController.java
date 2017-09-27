package com.wugao.jq.application.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wugao.center.infrastruture.mybatis.Pagination;
import com.wugao.jq.domain.goods.Goods;
import com.wugao.jq.domain.goods.GoodsRepo;
import com.wugao.jq.domain.goods.GoodsService;
import com.wugao.jq.domain.hotGoods.GoodsHotService;
import com.wugao.jq.domain.vo.search.SearchVo;

@RestController
@RequestMapping(value = "admin")
public class AdminController {
	
	private static final Integer pageSize = 20;
	private static final Integer showPageSize = 5;
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	GoodsHotService goodsHotService;
	
	@Autowired
	GoodsRepo goodsRepo;

	@RequestMapping(value = "home", method = RequestMethod.GET, produces = "text/html")
	public ModelAndView toAdminPage() {
		return new ModelAndView("admin/home");
	}
	
	@RequestMapping(value = "goods", method = RequestMethod.GET)
	public ModelAndView toGoodsPage(Pagination pagination, SearchVo searchVo) {
		List<Goods> goodsList = goodsRepo.getListBySearch(searchVo, pagination);
		pagination.setRows(goodsList);
		ModelAndView mav = new ModelAndView("admin/goods");
		return setPagedView(mav, pagination);
	}
	
	@RequestMapping(value = "activity", method = RequestMethod.GET)
	public ModelAndView toActivityPage() {
		return new ModelAndView("admin/activity");
	}
	
	@RequestMapping(value = "user", method = RequestMethod.GET)
	public ModelAndView toUserPage() {
		return new ModelAndView("admin/user");
	}
	
	@RequestMapping(value = "point", method = RequestMethod.GET)
	public ModelAndView toPointPage() {
		return new ModelAndView("admin/point");
	}
	
	@RequestMapping(value = "exchange", method = RequestMethod.GET)
	public ModelAndView toExchangePage() {
		return new ModelAndView("admin/exchange");
	}
	
	@RequestMapping(value = "collectGoods", method = RequestMethod.GET)
	public void collectGoods(String type) {
		try {
			if("hot".equals(type)) {
				goodsHotService.saveBatchFromFavorite();
			}else if("favorite".equals(type)) {
				goodsService.saveBatchFromFavorite();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private ModelAndView setPagedView(ModelAndView mav, Pagination pagination) {
		mav.addObject("currPage", pagination.getPage());
		mav.addObject("beginPage", pagination.getPage() % showPageSize == 0 ? pagination.getPage() - (showPageSize - 1) : pagination.getPage() / showPageSize * showPageSize + 1);
		mav.addObject("endPage", pagination.getPage() % showPageSize == 0 ? pagination.getPage() : (pagination.getPage() + showPageSize) / showPageSize * showPageSize);
		mav.addObject("pages", pagination.getPages());
		mav.addObject("objs", pagination.getRows());
		return mav;
	}
	
}