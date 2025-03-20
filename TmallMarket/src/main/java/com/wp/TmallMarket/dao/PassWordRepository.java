package com.wp.TmallMarket.dao;

import com.wp.TmallMarket.entity.Password;
import com.wp.TmallMarket.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * <p>标题 </p>
 * <p>功能：</p>
 * <p>所属模块：XYFT_FUND</p>
 * <p>作者：王云龙</p>
 * <p>创建日期：2025/3/20 10:23</p>
 * <p>公司: 厦门象屿股份有限公司</p>
 * <p>类全名：com.wp.TmallMarket.dao.PassWordRepository</p>
 */
@Repository
public interface PassWordRepository extends JpaRepository<Password, String>
{
	@Override
	<S extends Password> List<S> saveAll(Iterable<S> entities);
}
