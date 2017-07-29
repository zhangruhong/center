package com.wugao.jq.application.pub;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wugao.center.infrastruture.mybatis.Pagination;
import com.wugao.center.support.utils.ClassGenarator;
import com.wugao.center.support.utils.SqlGenerator;
import com.wugao.jq.domain.category.Category;
import com.wugao.jq.domain.category.CategoryRepo;
import com.wugao.jq.domain.goods.GoodsRepo;
import com.wugao.jq.domain.vo.search.SearchVo;

@RestController
@RequestMapping
public class SearchController {
	
	@Autowired
	private GoodsRepo goodsRepo;
	
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
	
}
