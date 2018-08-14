package blog.controller.portal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import blog.entity.ArticleInfo;
import blog.entity.Result;
import blog.entity.TypeInfo;
import blog.service.ArticleInfoService;
import blog.service.TypeInfoService;


@Controller
@RequestMapping("portal")
public class PortalAction {
	// �����������仰  ��־��Ϣ
	private Logger log = Logger.getLogger(this.getClass());

	
	@Autowired
	private TypeInfoService typeInfoService;
	@Autowired
	private ArticleInfoService articleInfoService;
	
	/**
	 * �����������¾�������
	 * @param id ����
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/content.action")
	public String selectById(ModelMap map,@RequestParam(value="id")String id) throws Exception {
		// TODO Auto-generated method stub
		ArticleInfo articleInfo=articleInfoService.selectById(id);
		if (articleInfo==null) {
			return "redirect:/WEB-INF/404.jsp";
		}
		map.put("articleInfo", articleInfo);
		return "portal/content";
	}

	/**
	 * ��ѯ�������� ����
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/index.action")
	public String listNormal(ModelMap map,	
			@RequestParam(value="pageNum", defaultValue="1") int pageNum,
			@RequestParam(value="pageSize", defaultValue="5") int pageSize
			
			) throws Exception {
		Map<String, Object> param=new HashMap<>();
		param.put("status",1);
		
		// pageHelper��ҳ���
		// ֻ��Ҫ�ڲ�ѯ֮ǰ���ã����뵱ǰҳ�룬�Լ�ÿһҳ��ʾ������
		PageHelper.startPage(pageNum, pageSize);
		
		List<ArticleInfo> listNormal = articleInfoService.list(param);
		
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>(listNormal);
		map.put("pageInfo", pageInfo);
		//��ѯ�������·���
		map.addAttribute("typeList", typeInfoService.list());
		
		
		return "portal/index";
	}
	/**
	 * �������·���    id ��ѯ�������� ����
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/type.action")
	public String type(ModelMap map,	
			@RequestParam(value="typeId") String typeId,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum,
			@RequestParam(value="pageSize", defaultValue="5") int pageSize
			
			) throws Exception {
		Map<String, Object> param=new HashMap<>();
		param.put("status",1);
		param.put("typeId",typeId);
		
		// pageHelper��ҳ���
		// ֻ��Ҫ�ڲ�ѯ֮ǰ���ã����뵱ǰҳ�룬�Լ�ÿһҳ��ʾ������
		PageHelper.startPage(pageNum, pageSize);
		
		List<ArticleInfo> listNormal = articleInfoService.list(param);
		
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>(listNormal);
		map.put("pageInfo", pageInfo);
		//����������ѯ����
		map.put("typeInfo", typeInfoService.selectById(typeId));
		
		return "portal/type";
	}
	/**
	 * ҳ���Ѽ���    ��ѯ���·���
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getType.json")
	@ResponseBody
	public Result getType() throws Exception {		
		//��ѯ�������·���
		List<TypeInfo> list = typeInfoService.list();
		return Result.success().add("list", list);
	}
	/**
	 * ������ҳ��
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/aboutMe.action")
	public String abountMe() throws Exception {		
		//ҳ����ת
		return "portal/about";
	}
	/**
	 * ���ݹؼ����������±��� ����ȫ������
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/search.action")
	public String search(ModelMap map,	
			
			@RequestParam(value="keyWord", required=true) String  keyWord,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum,
			@RequestParam(value="pageSize", defaultValue="6") int pageSize
			
			) throws Exception {
		Map<String, Object> param=new HashMap<>();
		if (!StringUtils.isEmpty(keyWord)) {
			param.put("keyWord", "%"+keyWord.trim()+"%");
		}
		param.put("status",1);
		
		// pageHelper��ҳ���
		// ֻ��Ҫ�ڲ�ѯ֮ǰ���ã����뵱ǰҳ�룬�Լ�ÿһҳ��ʾ������
		PageHelper.startPage(pageNum, pageSize);
		
		List<ArticleInfo> listNormal = articleInfoService.list(param);
		
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>(listNormal);
		map.put("pageInfo", pageInfo);
		map.put("keyWord", keyWord);
	
		return "portal/search";
	}
}
