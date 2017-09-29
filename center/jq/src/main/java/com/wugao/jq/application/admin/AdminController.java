package com.wugao.jq.application.admin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.wugao.center.infrastruture.mybatis.Pagination;
import com.wugao.center.infrastruture.utils.ServletUtil;
import com.wugao.jq.domain.activity.Activity;
import com.wugao.jq.domain.activity.ActivityRepo;
import com.wugao.jq.domain.activity.ActivityService;
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
	
	@Autowired
	ActivityRepo activityRepo;
	
	@Autowired
	ActivityService activityService;

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
	public ModelAndView toActivityPage(String startDate, String endDate, Boolean status, Pagination pagination) {
		List<Activity> activities = activityRepo.getList(startDate, endDate, status, pagination);
		pagination.setRows(activities);
		ModelAndView mav = new ModelAndView("admin/activity");
		return setPagedView(mav, pagination);
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
	
	@RequestMapping(value = "fileupload", method = RequestMethod.POST)
	public void fileupload(MultipartHttpServletRequest request, HttpServletResponse resp) throws Exception{
		MultipartFile file = request.getFile("files");
		File f = new File(File.separator + "usr" + File.separator + "local" + File.separator + "file" + File.separator + System.currentTimeMillis() + "_"+ file.getOriginalFilename());
		if(!f.exists()) {
			f.mkdirs();
		}
		file.transferTo(f);
		Map<String, String> fileInfo = new HashMap<>();
		fileInfo.put("fileName", f.getName());
		fileInfo.put("filePath", f.getPath());
		ServletUtil.respondObjectAsJson(resp, fileInfo);
	}
	
	@RequestMapping(value = "removefile", method = RequestMethod.POST)
	public void removeFile(String filePath) {
		File file = new File(filePath);
		if(file.exists()) {
			file.delete();
		}
	}
	
}






