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
	 * ��ѯ�������·���
	 */
	public List<TypeInfo> list() throws Exception{
		return iTypeInfoDao.list();
		
	}
	/**
	 * ��������/��������
	 * @param idArr
	 * @param sortArr
	 * @param nameArr
	 */
	public void save(String[] idArr, String[] sortArr, String[] nameArr) {
		// TODO Auto-generated method stub
		for (int i = 0; i <idArr.length; i++) {
			//����id�Ƿ�Ϊ�����жϴ��������Ǹ��»��ǲ���
			if (StringUtils.isEmpty(idArr[i])) {
				//����
				iTypeInfoDao.insert(sortArr[i],nameArr[i]);
			}else {
				//����
				iTypeInfoDao.update(idArr[i],sortArr[i],nameArr[i]);
			}
		}
	}
	/**
	 * ��������ɾ�� ���·���
	 * @param idArr
	 * @throws MyException 
	 */
	public void delete(String[] idArr) throws MyException {
		// TODO Auto-generated method stub
		
		//�жϸ÷���id��û�б�ʹ��     �����޹���article_info����type_id
		int count=iArticleInfoDao.countByTypeIdArr("1",idArr);
		if (count>0) {
			//������״̬���� ��ֹɾ��
			throw new MyException("�÷����´���������£��޷�ɾ��");
		}
		//��ɾ���÷��������л���վ������  ���ݷ��������
		iArticleInfoDao.batchDeleteByTypeIdArr(idArr);
		//ɾ���÷���
		iTypeInfoDao.delete(idArr);
	}
	/**
	 * ����������ѯ����
	 * @param typeId
	 * @return
	 */
	public TypeInfo selectById(String id) {
		// TODO Auto-generated method stub
		return iTypeInfoDao.selectById(id);
	}
}










