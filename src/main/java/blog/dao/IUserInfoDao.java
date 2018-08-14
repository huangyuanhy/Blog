package blog.dao;

import org.apache.ibatis.annotations.Param;

import blog.entity.UserInfo;

public interface IUserInfoDao {
	/**
	 * У���û�
	 * @param name ��¼��
	 * @param password ��¼����
	 * @return
	 */
	//���������ϵĲ���  ����д�ϲ���ע��
	UserInfo selectUser(@Param("name") String name, @Param("password") String password);
}
