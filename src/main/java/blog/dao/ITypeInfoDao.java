package blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import blog.entity.TypeInfo;


public interface ITypeInfoDao {
	/**
	 * ��ѯ�������·���
	 * @return
	 * @throws Exception
	 */
	List<TypeInfo> list() throws Exception;
/**
 * ����һ���µļ�¼
 * @param sort ����
 * @param name ��������
 * @return Ӱ��ļ�¼��
 */
	//�����������Ͼͱ����в���ע�� �����ݿ��е��ֶν��ж�Ӧ
	int insert(@Param("sort")String sort, @Param("name")String name);
/**
 * ����һ���µļ�¼
 * @param id ���
 * @param sort
 * @param name
 * @return
 */
	int update(@Param("id")String id, @Param("sort")String sort, @Param("name")String name);
	/**
	 * ��������ɾ��
	 * @param idArr ��������
	 */
	void delete(@Param("idArr")String[] idArr);
	/**
	 * 	����������ѯ����
	 * @param id
	 * @return
	 */
	TypeInfo selectById(String id);
}
