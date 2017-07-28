package com.wugao.center.domain.goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.wugao.center.infrastruture.mybatis.Pagination;

@Repository
@CacheConfig(cacheNames = "query:center:com.wugao.center.domain.goods.GoodsRepo")
public class GoodsRepo {

	private static final String NS = "com.wugao.center.domain.goods.GoodsRepo.";
	private static final int BATCH_SIZE = 200;

	@Autowired
	
	private SqlSessionTemplate sqlSessionTemplate;

	@CacheEvict(allEntries = true)
	public Goods save(Goods goods) {
		goods.setId(UUID.randomUUID().toString());
		sqlSessionTemplate.insert(NS + "save", goods);
		return goods;
	}

	@CacheEvict(allEntries = true)
	public void update(Goods goods) {
		sqlSessionTemplate.update(NS + "update", goods);
	}

	@CacheEvict(allEntries = true)
	public void remove(String id) {
		sqlSessionTemplate.delete(NS + "remove", id);
	}

	@Cacheable
	public Goods getById(String id) {
		return StringUtils.isEmpty(id) ? null : sqlSessionTemplate.selectOne(NS + "getById", id);
	}

	@Cacheable
	public List<Goods> getList(String search) {
		Map<String, Object> param = new HashMap<>();
		param.put("search", search);
		return sqlSessionTemplate.selectList(NS + "getList", param);
	}
	
	@Cacheable
	public List<Goods> getListByPage(String search, Boolean enabled, Pagination pagination) {
		Map<String, Object> param = new HashMap<>();
		param.put("search", search);
		param.put("enabled", enabled);
		return sqlSessionTemplate.selectList(NS + "getList", param, pagination.toRowBounds());
	}

	@CacheEvict(allEntries = true)
	public void saveBatch(List<Goods> toBeAddList) {
		List<Goods> temp = new ArrayList<>();
		for(int i = 0; i < toBeAddList.size(); i++) {
			temp.add(toBeAddList.get(i));
			if(temp.size() % BATCH_SIZE == 0 || i == toBeAddList.size() -1) {
				sqlSessionTemplate.insert(NS + "saveBatch", temp);
				temp.clear();
			}
		}
		
	}

	public List<Goods> getListByHighReturn(Pagination pagination) {
		try {
			if(pagination != null) {
				return sqlSessionTemplate.selectList(NS + "getGoodsByHighReturn", null, pagination.toRowBounds());
			}
			return sqlSessionTemplate.selectList(NS + "getGoodsByHighReturn");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<Goods> getListByTopSale(Pagination pagination) {
		if(pagination != null) {
			return sqlSessionTemplate.selectList(NS + "getListByTopSale", null, pagination.toRowBounds());
		}
		return sqlSessionTemplate.selectList(NS + "getListByTopSale");
	}

	public List<Goods> getListBySuperTicket(Pagination pagination) {
		if(pagination != null) {
			return sqlSessionTemplate.selectList(NS + "getListBySuperTicket", null, pagination.toRowBounds());
		}
		return sqlSessionTemplate.selectList(NS + "getListBySuperTicket");
	}

	public List<Goods> getListByTenYuan(Pagination pagination) {
		if(pagination != null) {
			return sqlSessionTemplate.selectList(NS + "getListByTenYuan", null, pagination.toRowBounds());
		}
		return sqlSessionTemplate.selectList(NS + "getListByTenYuan");
	}

}
