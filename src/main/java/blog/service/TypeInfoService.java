package blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import blog.dao.IArticleInfoDao;
import blog.dao.ITypeInfoDao;
import blog.entity.TypeInfo;
import blog.excepion.MyException;

@Service("TypeInfoService")
public class TypeInfoService {
	@Autowired
	private ITypeInfoDao iTypeInfoDao;
	@Autowired
	private IArticleInfoDao iArticleInfoDao;
	/**
	 * 查询所有文章分类
	 */
	public List<TypeInfo> list() throws Exception{
		return iTypeInfoDao.list();
		
	}
	/**
	 * 批量更新/插入文章
	 * @param idArr
	 * @param sortArr
	 * @param nameArr
	 */
	public void save(String[] idArr, String[] sortArr, String[] nameArr) {
		// TODO Auto-generated method stub
		for (int i = 0; i <idArr.length; i++) {
			//根据id是否为空来判断此条数据是更新还是插入
			if (StringUtils.isEmpty(idArr[i])) {
				//掺入
				iTypeInfoDao.insert(sortArr[i],nameArr[i]);
			}else {
				//更新
				iTypeInfoDao.update(idArr[i],sortArr[i],nameArr[i]);
			}
		}
	}
	/**
	 * 根据主键删除 文章分类
	 * @param idArr
	 * @throws MyException 
	 */
	public void delete(String[] idArr) throws MyException {
		// TODO Auto-generated method stub
		
		//判断该分类id有没有被使用     即有无关联article_info表中type_id
		int count=iArticleInfoDao.countByTypeIdArr("1",idArr);
		if (count>0) {
			//有正常状态文章 禁止删除
			throw new MyException("该分类下存在相关文章，无法删除");
		}
		//先删除该分类下所有回收站的文章  根据分类的主键
		iArticleInfoDao.batchDeleteByTypeIdArr(idArr);
		//删除该分类
		iTypeInfoDao.delete(idArr);
	}
	/**
	 * 根据主键查询分类
	 * @param typeId
	 * @return
	 */
	public TypeInfo selectById(String id) {
		// TODO Auto-generated method stub
		return iTypeInfoDao.selectById(id);
	}
}










