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
	 * ��ѯ��������
	 */
	public List<ArticleInfo> list(Map<String , Object > param) throws Exception{
		return iArticleInfoDao.list(param);
		
	}
	/**
	 * ��ѯ��������
	 * @param id ����
	 * @return
	 * @throws Exception
	 */
	public ArticleInfo selectById(String id) throws Exception{
		ArticleInfo articleInfo=iArticleInfoDao.selectById(id);
		if (articleInfo!=null) {
			//�������������
			 Integer viewCount = articleInfo.getViewCount();
			 viewCount++;
			//���������  �˴������� ��Ϊ�༭���µ�ʱ��Ҳ���õ������������Ҳ������һ��			
			 iArticleInfoDao.updateViewCount(id, viewCount);
		}
		return articleInfo;
	}
	/**
	 * ���±���
	 * @param articleInfo
	 * @throws Exception
	 */
	public void save(ArticleInfo articleInfo) throws Exception{
		Date current=new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now=simpleDateFormat.format(current);
	//�ж����������Ǹ�������
		if (StringUtils.isEmpty(articleInfo.getId())) {
			//����
			articleInfo.setStatus(1);
			articleInfo.setUpdateTime(now);
			articleInfo.setViewCount(1);
			iArticleInfoDao.insert(articleInfo);
		}else {
			//����
			articleInfo.setUpdateTime(now);
			iArticleInfoDao.update(articleInfo);
		}
		
	}
	/**
	 * �����ƶ���ĳ������  �����ǻ���վ
	 * @param idArr  ��������
	 * @param typeId ���·����Ӧ������
	 * @param status ����״̬
	 */
	public void batchUpdate(Map<String, Object> param) {
		// TODO Auto-generated method stub
		iArticleInfoDao.batchUpdate(param);
	}
	/**
	 * ����ɾ������
	 * @param idArr
	 */
	public void batchDelete(String[] idArr) {
		iArticleInfoDao.batchDelete(idArr);
	}
}
