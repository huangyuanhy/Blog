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
	// 在类下添加这句话  日志信息
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private TypeInfoService typeInfoService;
	@Autowired
	private ArticleInfoService articleInfoService;
	/**
	 * 查询所有文章 正常
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
		//非空判断
		if (!StringUtils.isEmpty(keyWord)) {
			
			param.put("keyWord","%"+keyWord.trim()+"%");
		}
		param.put("status",1);
		
		// pageHelper分页插件
		// 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
		PageHelper.startPage(pageNum, pageSize);
		
		List<ArticleInfo> listNormal = articleInfoService.list(param);
		
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>(listNormal);
		map.put("pageInfo", pageInfo);
		//查询所有文章分类
		map.addAttribute("typeList", typeInfoService.list());
		//数据回显
		map.addAttribute("typeId", typeId);
		map.addAttribute("startDate", startDate);
		map.addAttribute("endDate", endDate);
		map.addAttribute("keyWord", keyWord);
		
		
		return "admin/article_info/list_normal";
	}

	
	/**
	 * 查询所有文章 回收站
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
		//非空判断
		if (!StringUtils.isEmpty(keyWord)) {
			
			param.put("keyWord","%"+keyWord.trim()+"%");
		}
		param.put("status","0");
		
		// pageHelper分页插件
		// 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
		PageHelper.startPage(pageNum, pageSize);
		
		List<ArticleInfo> listNormal = articleInfoService.list(param);
		
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>(listNormal);
		map.put("pageInfo", pageInfo);
		//查询所有文章分类
		map.addAttribute("typeList", typeInfoService.list());
		//数据回显
		map.addAttribute("typeId", typeId);
		map.addAttribute("startDate", startDate);
		map.addAttribute("endDate", endDate);
		map.addAttribute("keyWord", keyWord);
		
		
		return "admin/article_info/list_recycle";
	}
	
	
	
	/**
	 * 文章编辑
	 * @param  主键
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/edit.action")
	public String edit(ModelMap map,@RequestParam(value="id",required=false) String id) throws Exception {
		//查询单个文章记录  
		if (!StringUtils.isEmpty(id)) {
			//id不为空 说明是更新文章内容
			ArticleInfo articleInfo = articleInfoService.selectById(id);
			map.put("articleInfo", articleInfo);
		}
		map.addAttribute("id", id);
		//id为空  则查询所有文章分类
		map.addAttribute("typeList", typeInfoService.list());

		return "admin/article_info/edit";
	}
	
	/**
	 * 上传文件到磁盘（物理路径）
	 * @throws IOException 
	 */
	@RequestMapping("upload.json")
	@ResponseBody
	public Result upload(MultipartFile file, HttpServletRequest request) throws IOException {
		
		// 文件原名称
		String szFileName = file.getOriginalFilename();
		// 重命名后的文件名称
		String szNewFileName = "";
		// 根据日期自动创建3级目录
		String szDateFolder = "";
		
		// 上传文件
		if (file!=null && szFileName!=null && szFileName.length()>0) {
			Date date = new Date();
			szDateFolder = new SimpleDateFormat("yyyy/MM/dd").format(date);
			// 存储文件的物理路径
			//String szFilePath = "D:\\upload\\" + szDateFolder;
			// 获取的的tomcat的路径，部署项目后相当于项目的路径
			String szFilePath = request.getSession().getServletContext().getRealPath("upload")+"\\"+szDateFolder+"\\";
			/*String contextPath = request.getContextPath();
			String szFilePath =contextPath+"/static/upload/" + szDateFolder;*/
			// 自动创建目录
			File f = new File(szFilePath);
			if (!f.exists()) {
				f.mkdirs();
			}
			
			// 新的文件名称
			szNewFileName = UUID.randomUUID() + szFileName.substring(szFileName.lastIndexOf("."));
			// 新文件
			File newFile = new File(szFilePath+"/"+szNewFileName);
			
			// 将内存中的数据写入磁盘
			file.transferTo(newFile);
		}
		
		return Result.success().add("imgUrl", szDateFolder+"/"+szNewFileName);
	}
	/**
	 * 文章保存
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
	 * 文章移动到某个分类
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
	 * 文章状态改变
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
	 * 文章批量删除
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



