package com.wugao.jq.domain.goods;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.TbkFavorites;
import com.taobao.api.domain.UatmTbkItem;
import com.taobao.api.request.TbkUatmFavoritesGetRequest;
import com.taobao.api.request.TbkUatmFavoritesItemGetRequest;
import com.taobao.api.response.TbkDgItemCouponGetResponse.TbkCoupon;
import com.taobao.api.response.TbkUatmFavoritesGetResponse;
import com.taobao.api.response.TbkUatmFavoritesItemGetResponse;
import com.wugao.jq.domain.category.Category;
import com.wugao.jq.domain.category.CategoryRepo;

@Validated
@Service
public class GoodsService {
	
	private static final String YES_CHINESE = "是";
	private static final String NO_CHINESE = "否";
	
	@Value("${taobao.api.url}")
	private String url;
	
	@Value("${taobao.lianmeng.appKey}")
	private String lianmengAppKey;
	
	@Value("${taobao.lianmeng.secretKey}")
	private String lianmengSecretKey;
	
	@Value("${tao.adzone.id}")
	private String adzoneId;

	@Autowired
	private GoodsRepo goodsRepo;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	private DecimalFormat decimalFormat = new DecimalFormat("0.00");

	public Goods saveGoods(@Valid Goods goods) {
		return goodsRepo.save(goods);
	}
	
	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

	public void updateGoods(String id, @Valid Goods goods) {
		// 保存更新
		goodsRepo.update(goods);
	}

	public void saveBatchFromFavorite() throws Exception {
		//清空数据库
		goodsRepo.removeAll();
		//顶级分类
		Map<String, Category> categoryMap = getCategoryMap(categoryRepo.getTopCategory());
		//最终商品列表
		List<Goods> list = new ArrayList<>();
		TaobaoClient client = new DefaultTaobaoClient(url, lianmengAppKey, lianmengSecretKey);
		//选品库请求
		TbkUatmFavoritesGetRequest req = new TbkUatmFavoritesGetRequest();
		//选聘库参数
		req.setPageNo(1L);
		req.setPageSize(200L);
		req.setFields("favorites_title,favorites_id,type");
		req.setType(-1L);
		//选聘库响应
		TbkUatmFavoritesGetResponse favoriteResponse = client.execute(req);
		List<TbkFavorites> favorites = favoriteResponse.getResults();
		//遍历选品库
		for(TbkFavorites f : favorites) {
			Long favoriteId = f.getFavoritesId();
			//选品库命名规则 一级分类-二级分类-选品库编号
			String[] favoriteFileds = f.getFavoritesTitle().split("-");
			//对应本地数据库分类
			Category category = categoryMap.get(favoriteFileds[0]); 
			Map<String, Category> subCategoryMap = new HashMap<>();
			if(category != null) {
				subCategoryMap = getCategoryMap(categoryRepo.getChildren(category.getId()));
			}
			Category subCategory = subCategoryMap.get(favoriteFileds[1]); 
			//遍历该选品库的商品
			for(long i = 1; i <= 2; i++) {
				TbkUatmFavoritesItemGetRequest req2 = new TbkUatmFavoritesItemGetRequest();
				req2.setPlatform(1L);
				req2.setPageSize(100L);
				req2.setAdzoneId(Long.valueOf(adzoneId));
				req2.setFavoritesId(favoriteId);
				req2.setPageNo(i);
				req2.setFields("num_iid,title,pict_url,click_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,seller_id,volume,nick,shop_title,zk_final_price_wap,event_start_time,event_end_time,tk_rate,status,type,category, commission_rate, coupon_click_url,coupon_start_time, coupon_end_time, coupon_info, coupon_remain_count, coupon_total_count");
				TbkUatmFavoritesItemGetResponse rsp2 = client.execute(req2);
				List<UatmTbkItem> items = rsp2.getResults();
				Map<String, Goods> map = new HashMap<String, Goods>();
				//连接异常时自动重连10次
				int tryTimes = 0;
				while(items == null) {
					rsp2 = client.execute(req2);
					items = rsp2.getResults();
					tryTimes++;
					if(tryTimes == 10) {
						break;
					}
				}
				//由响应的UatmTbkItem转换为本地Goods类
				if(items != null && items.size() > 0) {
					for(UatmTbkItem item : items) {
						if(!map.containsKey(item.getNumIid().toString())) {
							Goods goods = new Goods();
							goods.setId(item.getNumIid().toString());
							goods.setDetailUrl(item.getItemUrl());
							goods.setOriginalPrice(Double.valueOf(item.getZkFinalPrice()));
							goods.setShopName(item.getShopTitle());
							goods.setSoldCountPerMonth(item.getVolume().intValue());
							goods.setIncomingRate(Double.valueOf(item.getTkRate()) / 100);
							goods.setName(item.getTitle());
							goods.setIncoming(Double.valueOf(decimalFormat.format(goods.getOriginalPrice() * goods.getIncomingRate())));
							goods.setMainImageUrl(item.getPictUrl());
							goods.setTbkLongUrl(item.getClickUrl());
							goods.setTaoToken(null);
							goods.setTicketTotal(item.getCouponTotalCount() == null ? 0 : item.getCouponTotalCount().intValue());
							goods.setTicketLeft(item.getCouponRemainCount() == null ? 0 : item.getCouponRemainCount().intValue());
							goods.setTicketStartTime(StringUtils.isEmpty(item.getCouponStartTime()) ? null : fmt.parse(item.getCouponStartTime()));
							goods.setTicketEndTime(StringUtils.isEmpty(item.getCouponEndTime())? null : fmt.parse(item.getCouponEndTime()));
							goods.setTicketValue(item.getCouponInfo());
							goods.setTicketUrl(item.getCouponClickUrl());
							goods.setStatus(item.getStatus() == 1L);
							if(category != null) {
								goods.setCategoryPid(category.getId());
							}
							if(subCategory != null) {
								goods.setCategoryId(subCategory.getId());
							}
							map.put(goods.getId(), goods);
							
							list.add(goods);
						}
					}
				}
			}
		}
		goodsRepo.saveBatch(list);
	}

	public List<Goods> parseCouponToGoods(List<TbkCoupon> items){
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		List<Goods> list = new ArrayList<Goods>();
		for(TbkCoupon item : items) {
			Goods goods = new Goods();
			goods.setId(item.getNumIid().toString());
			goods.setName(item.getTitle());
			goods.setMainImageUrl(item.getPictUrl());
			goods.setDetailUrl(item.getItemUrl());
			goods.setOriginalPrice(Double.valueOf(item.getZkFinalPrice()));
			goods.setShopName(item.getShopTitle());
			goods.setSoldCountPerMonth(item.getVolume().intValue());
			goods.setIncomingRate(Double.valueOf(item.getCommissionRate()) / 100);
			goods.setIncoming(Double.valueOf(decimalFormat.format(goods.getOriginalPrice() * goods.getIncomingRate())));
			goods.setSalerWang(item.getNick());
			goods.setTbkLongUrl(item.getCouponClickUrl());
			goods.setTbkShortUrl(item.getCouponClickUrl());
			goods.setTaoToken(null);
			goods.setTicketTotal(item.getCouponTotalCount().intValue());
			goods.setTicketLeft(item.getCouponRemainCount().intValue());
			goods.setTicketValue(item.getCouponInfo());
			goods.setTicketUrl(item.getCouponClickUrl());
			list.add(goods);
		} 
		return list;
	}

	public void saveBatchFromEvent() {
		
	}
	
	public Map<String, Category> getCategoryMap(List<Category> categories){
		Map<String, Category> result = new HashMap<>();
		for(Category c : categories) {
			result.put(c.getName(), c);
		}
		return result;
	}
	
	
}
