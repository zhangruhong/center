package com.wugao.jq.domain.goods;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.TbkFavorites;
import com.taobao.api.domain.UatmTbkItem;
import com.taobao.api.request.TbkItemGetRequest;
import com.taobao.api.request.TbkUatmFavoritesGetRequest;
import com.taobao.api.request.TbkUatmFavoritesItemGetRequest;
import com.taobao.api.response.TbkDgItemCouponGetResponse.TbkCoupon;
import com.taobao.api.response.TbkItemGetResponse;
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

	public Goods saveGoods(@Valid Goods goods) {
		return goodsRepo.save(goods);
	}

	public void updateGoods(String id, @Valid Goods goods) {
		// 保存更新
		goodsRepo.update(goods);
	}
	
	public void saveBatchFromExcel() {
		List<Goods> toBeAddList = new ArrayList<>();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			File directory = new File(this.getClass().getClassLoader().getResource("").getPath() + File.separator + "xlsx" );
			if(directory.isDirectory()) {
				System.out.println(directory.getAbsolutePath());
			}else {
				directory.mkdir();
			}
			File[] excelFiles = directory.listFiles();
			for(File f : excelFiles) {
				HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(f));
				HSSFSheet sheet = workbook.getSheetAt(0);
				sheet.getFirstRowNum();
				for(int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
					HSSFRow row = sheet.getRow(i);
					Goods goods = new Goods();
					goods.setId(row.getCell(0).getStringCellValue());
					goods.setName(row.getCell(1).getStringCellValue());
					goods.setMainImageUrl(row.getCell(2).getStringCellValue());
					goods.setDetailUrl(row.getCell(3).getStringCellValue());
					goods.setShopName(row.getCell(4).getStringCellValue());
					goods.setOriginalPrice(Double.valueOf(row.getCell(5).getStringCellValue()));
					goods.setSoldCountPerMonth(Integer.valueOf(row.getCell(6).getStringCellValue()));
					goods.setIncomingRate(Double.valueOf(row.getCell(7).getStringCellValue())/ 100);
					goods.setIncoming(Double.valueOf(row.getCell(8).getStringCellValue()));
					goods.setSalerWang(row.getCell(9).getStringCellValue());
					goods.setTbkShortUrl(row.getCell(10).getStringCellValue());
					goods.setTbkLongUrl(row.getCell(11).getStringCellValue());
					goods.setTaoToken(row.getCell(12).getStringCellValue());
					goods.setTicketTotal(Integer.valueOf(row.getCell(13).getStringCellValue()));
					goods.setTicketLeft(Integer.valueOf(row.getCell(14).getStringCellValue()));
					goods.setTicketValue(row.getCell(15).getStringCellValue());
					goods.setTicketStartTime(StringUtils.isEmpty(row.getCell(16) == null ? null : row.getCell(16).getStringCellValue()) ? null : fmt.parse(row.getCell(16).getStringCellValue()));
					goods.setTicketEndTime(StringUtils.isEmpty(row.getCell(17) == null ? null : row.getCell(17).getStringCellValue()) ? null : fmt.parse(row.getCell(17).getStringCellValue()));
					goods.setTicketUrl(row.getCell(18) == null ? null : row.getCell(18).getStringCellValue());
					goods.setTicketTaoToken(row.getCell(19) == null ? null : row.getCell(19).getStringCellValue());
					goods.setTicketShortUrl(row.getCell(20) == null ? null : row.getCell(20).getStringCellValue());
					goods.setIsPromotion(YES_CHINESE.equals(row.getCell(21) == null ? null : row.getCell(21).getStringCellValue().trim()));
					toBeAddList.add(goods);
				}
				goodsRepo.saveBatch(toBeAddList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

	public void saveBatchFromFavorite() throws ApiException {
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		List<Goods> list = new ArrayList<>();
		TaobaoClient client = new DefaultTaobaoClient(url, lianmengAppKey, lianmengSecretKey);
		TbkUatmFavoritesGetRequest req = new TbkUatmFavoritesGetRequest();
		req.setPageNo(1L);
		req.setPageSize(200L);
		req.setFields("favorites_title,favorites_id,type");
		req.setType(-1L);
		TbkUatmFavoritesGetResponse rsp = client.execute(req);
		List<TbkFavorites> favorites = rsp.getResults();
		for(TbkFavorites f : favorites) {
			Long favoriteId = f.getFavoritesId();
			for(long i = 1; i <= 2; i++) {
				TbkUatmFavoritesItemGetRequest req2 = new TbkUatmFavoritesItemGetRequest();
				req2.setPlatform(1L);
				req2.setPageSize(100L);
				req2.setAdzoneId(Long.valueOf(adzoneId));
				req2.setFavoritesId(favoriteId);
				req2.setPageNo(i);
				req2.setFields("num_iid,title,pict_url,click_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,seller_id,volume,nick,shop_title,zk_final_price_wap,event_start_time,event_end_time,tk_rate,status,type");
				TbkUatmFavoritesItemGetResponse rsp2 = client.execute(req2);
				List<UatmTbkItem> items = rsp2.getResults();
				for(UatmTbkItem item : items) {
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
					goods.setTicketValue(item.getCouponInfo());
					goods.setTicketUrl(item.getCouponClickUrl());
					list.add(goods);
				}
				goodsRepo.saveBatch(list);
			}
		}
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
		// TODO Auto-generated method stub
		
	}
	

}
