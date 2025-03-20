package com.wp.TmallMarket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
/**
 * <p>标题 </p>
 * <p>功能：</p>
 * <p>所属模块：XYFT_FUND</p>
 * <p>作者：王云龙</p>
 * <p>创建日期：2025/3/20 10:19</p>
 * <p>公司: 厦门象屿股份有限公司</p>
 * <p>类全名：com.wp.TmallMarket.entity.Password</p>
 */
@Data
@Entity
@Table(name = Password.TableName)
public class Password
{
	public static final String TableName        = "user_pwd";
	@Id
	private             Long   id;
	private String original_pwd;
	private String encrypted_password;
}
