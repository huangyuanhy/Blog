package blog.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import blog.entity.ArticleInfo;
import blog.entity.Result;
import blog.entity.TypeInfo;
import blog.excepion.MyException;
import blog.service.ArticleInfoService;
import blog.service.TypeInfoService;


@Controller
@RequestMapping("article_info")
public class ArticleInfoAction {
	// �����������仰  ��־��Ϣ
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private TypeInfoService typeInfoService;
	@Autowired
	private ArticleInfoService articleInfoService;
	/**
	 * ��ѯ�������� ����
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list_normal.action")
	public String listNormal(ModelMap map,
			@RequestParam(value="typeId", required=false) String  typeId,
			@RequestParam(value="startDate", required=false) String  startDate,
			@RequestParam(value="endDate", required=false) String  endDate,
			@RequestParam(value="keyWord", required=false) String  keyWord,
			
			@RequestParam(value="pageNum", defaultValue="1") int pageNum,
			@RequestParam(value="pageSize", defaultValue="6") int pageSize
			
			) throws Exception {
		Map<String, Object> param=new HashMap<>();
		param.put("typeId",typeId);
		param.put("startDate",startDate);
		param.put("endDate",endDate);
		//�ǿ��ж�
		if (!StringUtils.isEmpty(keyWord)) {
			
			param.put("keyWord","%"+keyWord.trim()+"%");
		}
		param.put("status",1);
		
		// pageHelper��ҳ���
		// ֻ��Ҫ�ڲ�ѯ֮ǰ���ã����뵱ǰҳ�룬�Լ�ÿһҳ��ʾ������
		PageHelper.startPage(pageNum, pageSize);
		
		List<ArticleInfo> listNormal = articleInfoService.list(param);
		
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>(listNormal);
		map.put("pageInfo", pageInfo);
		//��ѯ�������·���
		map.addAttribute("typeList", typeInfoService.list());
		//���ݻ���
		map.addAttribute("typeId", typeId);
		map.addAttribute("startDate", startDate);
		map.addAttribute("endDate", endDate);
		map.addAttribute("keyWord", keyWord);
		
		
		return "admin/article_info/list_normal";
	}

	
	/**
	 * ��ѯ�������� ����վ
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list_recycle.action")
	public String listRecycle(ModelMap map,
			@RequestParam(value="typeId", required=false) String  typeId,
			@RequestParam(value="startDate", required=false) String  startDate,
			@RequestParam(value="endDate", required=false) String  endDate,
			@RequestParam(value="keyWord", required=false) String  keyWord,
			
			@RequestParam(value="pageNum", defaultValue="1") int pageNum,
			@RequestParam(value="pageSize", defaultValue="6") int pageSize
			
			) throws Exception {
		Map<String, Object> param=new HashMap<>();
		param.put("typeId",typeId);
		param.put("startDate",startDate);
		param.put("endDate",endDate);
		//�ǿ��ж�
		if (!StringUtils.isEmpty(keyWord)) {
			
			param.put("keyWord","%"+keyWord.trim()+"%");
		}
		param.put("status","0");
		
		// pageHelper��ҳ���
		// ֻ��Ҫ�ڲ�ѯ֮ǰ���ã����뵱ǰҳ�룬�Լ�ÿһҳ��ʾ������
		PageHelper.startPage(pageNum, pageSize);
		
		List<ArticleInfo> listNormal = articleInfoService.list(param);
		
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>(listNormal);
		map.put("pageInfo", pageInfo);
		//��ѯ�������·���
		map.addAttribute("typeList", typeInfoService.list());
		//���ݻ���
		map.addAttribute("typeId", typeId);
		map.addAttribute("startDate", startDate);
		map.addAttribute("endDate", endDate);
		map.addAttribute("keyWord", keyWord);
		
		
		return "admin/article_info/list_recycle";
	}
	
	
	
	/**
	 * ���±༭
	 * @param  ����
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/edit.action")
	public String edit(ModelMap map,@RequestParam(value="id",required=false) String id) throws Exception {
		//��ѯ�������¼�¼  
		if (!StringUtils.isEmpty(id)) {
			//id��Ϊ�� ˵���Ǹ�����������
			ArticleInfo articleInfo = articleInfoService.selectById(id);
			map.put("articleInfo", articleInfo);
		}
		map.addAttribute("id", id);
		//idΪ��  ���ѯ�������·���
		map.addAttribute("typeList", typeInfoService.list());

		return "admin/article_info/edit";
	}
	
	/**
	 * �ϴ��ļ������̣�����·����
	 * @throws IOException 
	 */
	@RequestMapping("upload.json")
	@ResponseBody
	public Result upload(MultipartFile file, HttpServletRequest request) throws IOException {
		
		// �ļ�ԭ����
		String szFileName = file.getOriginalFilename();
		// ����������ļ�����
		String szNewFileName = "";
		// ���������Զ�����3��Ŀ¼
		String szDateFolder = "";
		
		// �ϴ��ļ�
		if (file!=null && szFileName!=null && szFileName.length()>0) {
			Date date = new Date();
			szDateFolder = new SimpleDateFormat("yyyy/MM/dd").format(date);
			// �洢�ļ�������·��
			//String szFilePath = "D:\\upload\\" + szDateFolder;
			// ��ȡ�ĵ�tomcat��·����������Ŀ���൱����Ŀ��·��
			String szFilePath = request.getSession().getServletContext().getRealPath("upload")+"\\"+szDateFolder+"\\";
			/*String contextPath = request.getContextPath();
			String szFilePath =contextPath+"/static/upload/" + szDateFolder;*/
			// �Զ�����Ŀ¼
			File f = new File(szFilePath);
			if (!f.exists()) {
				f.mkdirs();
			}
			
			// �µ��ļ�����
			szNewFileName = UUID.randomUUID() + szFileName.substring(szFileName.lastIndexOf("."));
			// ���ļ�
			File newFile = new File(szFilePath+"/"+szNewFileName);
			
			// ���ڴ��е�����д�����
			file.transferTo(newFile);
		}
		
		return Result.success().add("imgUrl", szDateFolder+"/"+szNewFileName);
	}
	/**
	 * ���±���
	 * @param articleInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/save.json")
	@ResponseBody
	public Result save(ArticleInfo articleInfo) throws Exception{
		articleInfoService.save(articleInfo);
		return Result.success();
		
	}
	
	/**
	 * �����ƶ���ĳ������
	 * @param articleInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/move.json")
	@ResponseBody
	public Result move(
			@RequestParam(value="idArr") String[] idArr,
			@RequestParam(value="typeId") String typeId
			) throws Exception{
		Map<String, Object> param=new HashMap<>();
		param.put("typeId",typeId);
		param.put("idArr",idArr);
		
		articleInfoService.batchUpdate(param);
		return Result.success();
		
	}
	
	/**
	 * ����״̬�ı�
	 * @param articleInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/update_status.json")
	@ResponseBody
	public Result update_status(
			@RequestParam(value="idArr") String[] idArr,
			@RequestParam(value="status") String status
			) throws Exception{
		Map<String, Object> param=new HashMap<>();
		param.put("status",status);
		param.put("idArr",idArr);
		
		articleInfoService.batchUpdate(param);
		return Result.success();
		
	}
	/**
	 * ��������ɾ��
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/batchDelete.json")
	@ResponseBody
	public Result batchDelete(
			@RequestParam(value="idArr") String[] idArr)
			 throws Exception{
		
		
		articleInfoService.batchDelete(idArr);
		return Result.success();
		
	}
}



