package blog.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import blog.entity.Result;
import blog.entity.TypeInfo;
import blog.excepion.MyException;
import blog.service.ArticleInfoService;
import blog.service.TypeInfoService;


@Controller
@RequestMapping("type_info")
public class TypeInfoAction {
	// 在类下添加这句话  日志信息
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private ArticleInfoService ArticleInfoService;
	
	@Autowired
	private TypeInfoService typeInfoService;
	/**
	 * 查询所有文章分类
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list.action")
	public String list(ModelMap map) throws Exception {
		List<TypeInfo> list = typeInfoService.list();
		map.put("list", list);
		return "admin/type_info/list";
	}
	/**
	 * 
	 * @param idArr
	 * @param sortArr
	 * @param nameArr
	 * @return
	 */
	@RequestMapping(value="/save.json",method=RequestMethod.POST)
	@ResponseBody
	public Result save (
			@RequestParam(value="idArr")String[] idArr,
			@RequestParam(value="sortArr")String[] sortArr,
			@RequestParam(value="nameArr")String[] nameArr) throws MyException{
		
		typeInfoService.save(idArr,sortArr,nameArr);
		return Result.success();
	}
	/**
	 * 根据主键删除文章分类
	 * @param idArr 
	 * @return
	 */
	@RequestMapping("delete.json")
	@ResponseBody
	public Result delete(
			@RequestParam(value = "idArr") String[] idArr) throws MyException {
		
		typeInfoService.delete(idArr);
		
		return Result.success();
	}
}



