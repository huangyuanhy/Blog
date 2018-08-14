package blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import blog.entity.TypeInfo;


public interface ITypeInfoDao {
	/**
	 * 查询所有文章分类
	 * @return
	 * @throws Exception
	 */
	List<TypeInfo> list() throws Exception;
/**
 * 插入一条新的记录
 * @param sort 排序
 * @param name 分类名称
 * @return 影响的记录数
 */
	//两个参数以上就必须有参数注解 和数据库中的字段进行对应
	int insert(@Param("sort")String sort, @Param("name")String name);
/**
 * 跟新一条新的记录
 * @param id 组件
 * @param sort
 * @param name
 * @return
 */
	int update(@Param("id")String id, @Param("sort")String sort, @Param("name")String name);
	/**
	 * 根据主键删除
	 * @param idArr 主键数组
	 */
	void delete(@Param("idArr")String[] idArr);
	/**
	 * 	根据主键查询分类
	 * @param id
	 * @return
	 */
	TypeInfo selectById(String id);
}
