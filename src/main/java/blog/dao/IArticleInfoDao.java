package blog.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import blog.entity.ArticleInfo;
import blog.entity.TypeInfo;


public interface IArticleInfoDao {
	/**
	 * ��ѯ��������
	 * @param param
	 * @return
	 */
	public List<ArticleInfo> list(Map<String, Object> param);
	/**
	 * ����������ѯ����������Ϣ
	 * @param id
	 * @return
	 */
	public ArticleInfo selectById(String id);
	/**
	 * ��������
	 * @param articleInfo
	 */
	public void insert(ArticleInfo articleInfo);
	/**
	 * ��������
	 * @param articleInfo
	 */
	public void update(ArticleInfo articleInfo);
	/**
	 * �����ƶ���ĳ������  �����ǻ���վ
	 * @param idArr  ��������
	 * @param typeId ���·����Ӧ������
	 * @param status ����״̬
	 */
	public void batchUpdate(Map< String, Object> param);
	/**
	 * ����ɾ������
	 * @param idArr
	 */
	public void batchDelete(@Param("idArr")String[] idArr) ;

	/**
	 * �������·��� ��ѯ��������
	 * @param typeIdArr
	 * @param status ���µ�״̬
	 * @return
	 */
	public int countByTypeIdArr(@Param("status")String status,@Param( "typeIdArr")String[] typeIdArr);

	/**
	 * ����ɾ������վ������
	 * @param idArr ����id����
	 */
	public void batchDeleteByTypeIdArr(@Param("typeIdArr")String[] typeIdArr );
	/**
	 * �����������
	 * @param id
	 * @param viewCount
	 */
	public void updateViewCount(@Param("id")String id,@Param("viewCount")int viewCount);
}
