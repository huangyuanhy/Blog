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
	// 在类下添加这句话  日志信息
	private Logger log = Logger.getLogger(this.getClass());

	
	@Autowired
	private TypeInfoService typeInfoService;
	@Autowired
	private ArticleInfoService articleInfoService;
	
	/**
	 * 根据主键文章具体内容
	 * @param id 主键
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
	 * 查询所有文章 正常
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
		
		// pageHelper分页插件
		// 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
		PageHelper.startPage(pageNum, pageSize);
		
		List<ArticleInfo> listNormal = articleInfoService.list(param);
		
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>(listNormal);
		map.put("pageInfo", pageInfo);
		//查询所有文章分类
		map.addAttribute("typeList", typeInfoService.list());
		
		
		return "portal/index";
	}
	/**
	 * 根据文章分类    id 查询所有文章 正常
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
		
		// pageHelper分页插件
		// 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
		PageHelper.startPage(pageNum, pageSize);
		
		List<ArticleInfo> listNormal = articleInfoService.list(param);
		
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>(listNormal);
		map.put("pageInfo", pageInfo);
		//根据主键查询分类
		map.put("typeInfo", typeInfoService.selectById(typeId));
		
		return "portal/type";
	}
	/**
	 * 页面已加载    查询文章分类
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getType.json")
	@ResponseBody
	public Result getType() throws Exception {		
		//查询所有文章分类
		List<TypeInfo> list = typeInfoService.list();
		return Result.success().add("list", list);
	}
	/**
	 * 关于我页面
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/aboutMe.action")
	public String abountMe() throws Exception {		
		//页面跳转
		return "portal/about";
	}
	/**
	 * 根据关键字搜索文章标题 不是全文搜索
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
		
		// pageHelper分页插件
		// 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
		PageHelper.startPage(pageNum, pageSize);
		
		List<ArticleInfo> listNormal = articleInfoService.list(param);
		
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>(listNormal);
		map.put("pageInfo", pageInfo);
		map.put("keyWord", keyWord);
	
		return "portal/search";
	}
}
