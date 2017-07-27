package com.wugao.center.domain.goods;

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
@CacheConfig(cacheNames = "query:center:com.wugao.center.domain.user.UserRepo")
public class GoodsRepo {

	private static final String NS = "com.wugao.center.domain.user.UserRepo.";

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
	public Goods getByUsername(String username) {
		return StringUtils.isEmpty(username) ? null : sqlSessionTemplate.selectOne(NS + "getByUsername", username);
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

}
