package blog.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import blog.dao.IArticleInfoDao;
import blog.dao.ITypeInfoDao;
import blog.entity.ArticleInfo;
import blog.entity.TypeInfo;

@Service("ArticleInfoService")
public class ArticleInfoService {
	@Autowired
	private IArticleInfoDao iArticleInfoDao;

	/**
	 * 查询所有文章
	 */
	public List<ArticleInfo> list(Map<String , Object > param) throws Exception{
		return iArticleInfoDao.list(param);
		
	}
	/**
	 * 查询单个文章
	 * @param id 主键
	 * @return
	 * @throws Exception
	 */
	public ArticleInfo selectById(String id) throws Exception{
		ArticleInfo articleInfo=iArticleInfoDao.selectById(id);
		if (articleInfo!=null) {
			//文章浏览量自增
			 Integer viewCount = articleInfo.getViewCount();
			 viewCount++;
			//跟新浏览量  此处有问题 因为编辑文章的时候也是用的这个方法所有也会自增一次			
			 iArticleInfoDao.updateViewCount(id, viewCount);
		}
		return articleInfo;
	}
	/**
	 * 文章保存
	 * @param articleInfo
	 * @throws Exception
	 */
	public void save(ArticleInfo articleInfo) throws Exception{
		Date current=new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now=simpleDateFormat.format(current);
	//判断是新增还是跟新文章
		if (StringUtils.isEmpty(articleInfo.getId())) {
			//插入
			articleInfo.setStatus(1);
			articleInfo.setUpdateTime(now);
			articleInfo.setViewCount(1);
			iArticleInfoDao.insert(articleInfo);
		}else {
			//跟新
			articleInfo.setUpdateTime(now);
			iArticleInfoDao.update(articleInfo);
		}
		
	}
	/**
	 * 批量移动到某个分类  或者是回收站
	 * @param idArr  主键数组
	 * @param typeId 文章分类对应的主键
	 * @param status 文章状态
	 */
	public void batchUpdate(Map<String, Object> param) {
		// TODO Auto-generated method stub
		iArticleInfoDao.batchUpdate(param);
	}
	/**
	 * 批量删除文章
	 * @param idArr
	 */
	public void batchDelete(String[] idArr) {
		iArticleInfoDao.batchDelete(idArr);
	}
}
