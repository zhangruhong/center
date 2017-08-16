package com.wugao.jq.domain.hotGoods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.wugao.center.infrastruture.mybatis.Pagination;
import com.wugao.jq.domain.vo.search.SearchVo;

@Repository
@CacheConfig(cacheNames = "query:center:com.wugao.jq.domain.hotGoods.GoodsHotRepo")
public class GoodsHotRepo {

	private static final String NS = "com.wugao.jq.domain.hotGoods.GoodsHotRepo.";
	private static final int BATCH_SIZE = 200;

	@Autowired
	
	private SqlSessionTemplate sqlSessionTemplate;

	@CacheEvict(allEntries = true)
	public GoodsHot save(GoodsHot goods) {
		sqlSessionTemplate.insert(NS + "save", goods);
		return goods;
	}

	@CacheEvict(allEntries = true)
	public void update(GoodsHot goods) {
		sqlSessionTemplate.update(NS + "update", goods);
	}

	@CacheEvict(allEntries = true)
	public void remove(String id) {
		sqlSessionTemplate.delete(NS + "remove", id);
	}

	@Cacheable
	public GoodsHot getById(String id) {
		return StringUtils.isEmpty(id) ? null : sqlSessionTemplate.selectOne(NS + "getById", id);
	}

	@Cacheable
	public List<GoodsHot> getList(String search) {
		Map<String, Object> param = new HashMap<>();
		param.put("search", search);
		return sqlSessionTemplate.selectList(NS + "getList", param);
	}
	
	@CacheEvict(allEntries = true)
	public void saveBatch(List<GoodsHot> toBeAddList) {
		for(int i = 0; i < toBeAddList.size(); i++) {
			try {
				save(toBeAddList.get(i));
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public List<GoodsHot> getListByTopSale(Pagination pagination) {
		if(pagination != null) {
			return sqlSessionTemplate.selectList(NS + "getListByTopSale", null, pagination.toRowBounds());
		}
		return sqlSessionTemplate.selectList(NS + "getListByTopSale");
	}


	public List<GoodsHot> getListBySearch(SearchVo searchVo, Pagination pagination) {
		Map<String, Object> param = new HashMap<>();
		param.put("searchVo", searchVo);
		if(pagination != null) {
			return sqlSessionTemplate.selectList(NS + "getListBySearch", param, pagination.toRowBounds());
		}
		return sqlSessionTemplate.selectList(NS + "getListBySearch", param);
	}

	public void removeAll() {
		sqlSessionTemplate.delete(NS + "removeAll");
		
	}

}
