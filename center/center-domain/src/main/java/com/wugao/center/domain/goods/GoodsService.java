package com.wugao.center.domain.goods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import com.wugao.center.infrastruture.exception.AppException;

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
		try {
			workbook = new HSSFWorkbook(this.getClass().getClassLoader().getResourceAsStream("classpath: xlsx/女装-2017-07-27.xls"));
			HSSFSheet sheet = workbook.getSheetAt(0);
			sheet.getFirstRowNum();
			for(int i = sheet.getFirstRowNum() + 1; i < sheet.getLastRowNum(); i++) {
				HSSFRow row = sheet.getRow(i);
				Goods goods = new Goods();
				goods.setId(row.getCell(0).getStringCellValue());
				goods.setName(row.getCell(1).getStringCellValue());
				goods.setMainImageUrl(row.getCell(2).getStringCellValue());
				goods.setDetailUrl(row.getCell(3).getStringCellValue());
				goods.setShopName(row.getCell(4).getStringCellValue());
				goods.setOriginalPrice(row.getCell(5).getNumericCellValue());
				goods.setSoldCountPerMonth(Integer.valueOf(row.getCell(6).getStringCellValue()));
				goods.setIncomingRate(Double.valueOf(row.getCell(7).getStringCellValue())/ 100);
				goods.setIncoming(row.getCell(8).getNumericCellValue());
				goods.setSalerWang(row.getCell(9).getStringCellValue());
				goods.setTbkShortUrl(row.getCell(10).getStringCellValue());
				goods.setTbkLongUrl(row.getCell(11).getStringCellValue());
				goods.setTaoToken(row.getCell(12).getStringCellValue());
				goods.setTicketTotal(Integer.valueOf(row.getCell(13).getStringCellValue()));
				goods.setTicketLeft(Integer.valueOf(row.getCell(14).getStringCellValue()));
				goods.setTicketValue(row.getCell(15).getStringCellValue());
				goods.setTicketStartTime(row.getCell(16).getDateCellValue());
				goods.setTicketEndTime(row.getCell(17).getDateCellValue());
				goods.setTicketUrl(row.getCell(18).getStringCellValue());
				goods.setTicketTaoToken(row.getCell(19).getStringCellValue());
				goods.setTicketShortUrl(row.getCell(20).getStringCellValue());
				goods.setIsPromotion(YES_CHINESE.equals(row.getCell(21).getStringCellValue()));
				toBeAddList.add(goods);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
