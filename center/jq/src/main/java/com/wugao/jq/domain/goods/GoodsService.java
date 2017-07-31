package com.wugao.jq.domain.goods;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import com.taobao.api.response.TbkDgItemCouponGetResponse.TbkCoupon;

@Validated
@Service
public class GoodsService {
	
	private static final String YES_CHINESE = "是";
	private static final String NO_CHINESE = "否";

	@Autowired
	private GoodsRepo goodsRepo;

	public Goods saveGoods(@Valid Goods goods) {
		return goodsRepo.save(goods);
	}

	public void updateGoods(String id, @Valid Goods goods) {
		// 保存更新
		goodsRepo.update(goods);
	}
	
	public void saveBatch(HSSFWorkbook workbook) {
		List<Goods> toBeAddList = new ArrayList<>();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		try {
			workbook = new HSSFWorkbook(this.getClass().getClassLoader().getResourceAsStream("xlsx/女装-2017-07-27.xls"));
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
		} catch (Exception e) {
			e.printStackTrace();
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

}
