package blog.dao;

import org.apache.ibatis.annotations.Param;

import blog.entity.UserInfo;

public interface IUserInfoDao {
	/**
	 * 校验用户
	 * @param name 登录名
	 * @param password 登录密码
	 * @return
	 */
	//有两个以上的参数  必须写上参数注解
	UserInfo selectUser(@Param("name") String name, @Param("password") String password);
}
