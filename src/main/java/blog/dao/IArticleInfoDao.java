package blog.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import blog.entity.ArticleInfo;
import blog.entity.TypeInfo;


public interface IArticleInfoDao {
	/**
	 * 查询所有文章
	 * @param param
	 * @return
	 */
	public List<ArticleInfo> list(Map<String, Object> param);
	/**
	 * 根据主键查询单个文章信息
	 * @param id
	 * @return
	 */
	public ArticleInfo selectById(String id);
	/**
	 * 插入文章
	 * @param articleInfo
	 */
	public void insert(ArticleInfo articleInfo);
	/**
	 * 更新文章
	 * @param articleInfo
	 */
	public void update(ArticleInfo articleInfo);
	/**
	 * 批量移动到某个分类  或者是回收站
	 * @param idArr  主键数组
	 * @param typeId 文章分类对应的主键
	 * @param status 文章状态
	 */
	public void batchUpdate(Map< String, Object> param);
	/**
	 * 批量删除文章
	 * @param idArr
	 */
	public void batchDelete(@Param("idArr")String[] idArr) ;

	/**
	 * 根据文章分类 查询文章数量
	 * @param typeIdArr
	 * @param status 文章的状态
	 * @return
	 */
	public int countByTypeIdArr(@Param("status")String status,@Param( "typeIdArr")String[] typeIdArr);

	/**
	 * 批量删除回收站的文章
	 * @param idArr 分类id数组
	 */
	public void batchDeleteByTypeIdArr(@Param("typeIdArr")String[] typeIdArr );
	/**
	 * 跟新浏览次数
	 * @param id
	 * @param viewCount
	 */
	public void updateViewCount(@Param("id")String id,@Param("viewCount")int viewCount);
}
