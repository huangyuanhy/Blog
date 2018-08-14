package blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.dao.IUserInfoDao;
import blog.entity.UserInfo;

@Service("UserInfoService")
public class UserInfoService {
	@Autowired
	private IUserInfoDao iUserInfoDao;
	/**
	 * 查询所有文章分类
	 */
	public UserInfo selectUser(String name,String password) {
		return iUserInfoDao.selectUser(name, password);
	}
}
