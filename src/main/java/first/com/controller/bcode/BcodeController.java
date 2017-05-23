package first.com.controller.bcode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import first.com.common.Paging;
import first.com.dao.AlramDAO;
import first.com.dao.RecommendDAO;
import first.com.dao.ScrapDAO;
import first.com.model.BcommentDTO;
import first.com.model.BfileDTO;
import first.com.model.BoardDTO;

@Controller
public class BcodeController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private BcodeService bcode;
	
	//by eongoo
	@Resource 
	private ScrapDAO Scrap;
	@Resource 
	private RecommendDAO recommendSerivce;
	@Resource 
	private AlramDAO noti;
	//
	
	private int totalCount;
	private int blockCount = 10;
	private int blockPage = 5;
	private String pagingHtml;
	private Paging page;
	private String path = "bcodelist";// if (RequestMapping("/here.do")) => here
										// = path
	private String[] kind = { "JAVA", "SPRING", "SQL" };

	ModelAndView Clist;
	
	@RequestMapping(value = "/bcodelist.do")
	public ModelAndView bcodeList(@RequestParam(value = "search", defaultValue = "") String search,
			@RequestParam(value = "n", defaultValue = "0") int n,
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
			@RequestParam(value="line", defaultValue = "1") int lineup,
			@RequestParam(value="ap", required=false) String ap,
			BoardDTO dTO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("search", search);
		map.put("lineup", lineup);
		List<BoardDTO> list = (List<BoardDTO>) bcode.bcodeList(map);

		totalCount = list.size();

		page = new Paging(path, currentPage, totalCount, blockCount, blockPage, search, n);
		pagingHtml = page.getPagingHtml().toString();

		int lastCount = totalCount;
		if (page.getEndCount() < totalCount) {
			lastCount = page.getEndCount() + 1;
		}
		list = list.subList(page.getStartCount(), lastCount);
		
		if(ap == null || ap.equals("")){
		Clist = new ModelAndView("Clist");
		}else{
		Clist = new ModelAndView("code/CodeList");
		}
		
		Clist.addObject("list", list);

		Clist.addObject("page", pagingHtml);
		Clist.addObject("n", n);

		return Clist;
	}

	@RequestMapping(value = "/bcodedetail.do")
	public ModelAndView bcodeDetail(HttpServletRequest request,BoardDTO dTO,BfileDTO dTOFile,BcommentDTO dTOComment,
			@RequestParam(value="member_id", defaultValue="-1") int session_id) throws Exception {
	   
		int board_id2 = (int)Integer.parseInt(request.getParameter("board_id"));
	    
        if(request.getParameter("board_like") != null){
        	//by eongoo, recommend
    		Map<String, Object> map = new HashMap<String, Object>();
    		map.put("member_id", session_id);
    		map.put("board_id", board_id2);
    		recommendSerivce.addRecommend(map);
    		//
        	int like = Integer.parseInt(request.getParameter("board_like"));
        	bcode.bcodeInreaselike(like);
        }

		bcode.bcodeInreasehit(board_id2);
			

        BoardDTO detail = (BoardDTO)bcode.bcodeDetail(board_id2);
        BfileDTO detail2 = (BfileDTO)bcode.bcodeDetailfile(board_id2);
        List<BcommentDTO> detail3 = (List<BcommentDTO>) bcode.bcodeComment(board_id2);
        
        //insert comment
        if(request.getParameter("bcomment_content") != null){
        	  bcode.bcodeCommentinsert(dTOComment);
        	  
        	//by eongoo, comment noti
      		noti.insertCommentNoti(board_id2, session_id, "/bcodedetail");
      		//
        }
      
        ModelAndView Cdetail = new ModelAndView("Cdetail");
		Cdetail.addObject("detail", detail);
		Cdetail.addObject("detail2", detail2);
		Cdetail.addObject("detail3", detail3);
		
		//add by eongoo
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("session_id", session_id);
		map.put("board_id", board_id2);
		if(session_id == -1){ 
			Cdetail.addObject("scrapCheck", "-1");
			Cdetail.addObject("recommendCheck", "-1");
		} else {
			Cdetail.addObject("scrapCheck", Scrap.scrapCheck(map));
			Cdetail.addObject("recommendCheck", recommendSerivce.recommendCheck(map));
		}
		/////
		
		
		return Cdetail;
	}

	@RequestMapping(value="/bcodewrite.do")
	public ModelAndView bcodeWrite(HttpServletRequest request,@ModelAttribute BoardDTO dTO) throws Exception {
		
		ModelAndView Cwrite = new ModelAndView();
		Cwrite.setViewName("Cwrite");
		
		if(request.getParameter("board_id") != null){
			
			String update = request.getParameter("board_id").trim();
			int update2 = Integer.parseInt(update);
			
			BoardDTO updateform = (BoardDTO)bcode.bcodeUpdateform(update2);
			BfileDTO detail2 = (BfileDTO)bcode.bcodeDetailfile(update2);
			Cwrite.addObject("updateform", updateform);
			Cwrite.addObject("file", detail2);
			
			//by eongoo, new board noti
			noti.insertNewBoardNoti(Integer.parseInt(request.getParameter("member_id")), "/bcodedetail", 1);
			//
			
			return Cwrite;
		}else{
			return Cwrite;
		}

	}

	
	@RequestMapping(value="/bcodeinsert.do")
	public ModelAndView bcodeInsert(MultipartHttpServletRequest request,@ModelAttribute BoardDTO dTO,@ModelAttribute BfileDTO dTOFile) throws Exception {// 洹몃읆 �뿬湲곗꽑  member_id�옉 bgroup_id 2媛� �뱾�뼱�샂
		
		if(request.getParameter("board_id") != null){
			bcode.bcodeUpdate(dTO);
			if(request.getFile("file").getSize() != 0){
				File file = new File("C:\\Users\\Administrator\\Desktop\\upload/"+request.getFile("file").getOriginalFilename());
				request.getFile("file").transferTo(file);
				dTOFile.setBoard_id(dTO.getBoard_id());
				dTOFile.setBfile_size(request.getFile("file").getSize());
				dTOFile.setBfile_src(request.getFile("file").getOriginalFilename());
				
				bcode.bcodeUpload(dTOFile);
			}
			
			return new ModelAndView("redirect:/bcodelist.do");
		
		}else if(request.getFile("file").getSize()!= 0){
			bcode.bcodeInsert(dTO);
			
			File file = new File("C:\\upload/"+request.getFile("file").getOriginalFilename());
			request.getFile("file").transferTo(file);
			dTOFile.setBoard_id(dTO.getBoard_id());
			dTOFile.setBfile_size(request.getFile("file").getSize());
			dTOFile.setBfile_src(request.getFile("file").getOriginalFilename());
			
			bcode.bcodeUpload(dTOFile);
			
			
			return new ModelAndView("redirect:/bcodelist.do");
		}else{
			bcode.bcodeInsert(dTO);
			return new ModelAndView("redirect:/bcodelist.do");
		}

	}

	@RequestMapping(value="/bcodedownload.do")
	 public void bcodeFiledown(HttpServletRequest request,HttpServletResponse response,BfileDTO dTOfile) throws Exception{
	  
	  int board_id2 = (int)Integer.parseInt(request.getParameter("board_id"));
	  
	  BfileDTO detail2 = (BfileDTO)bcode.bcodeDetailfile(board_id2);
	  String orgname = detail2.getBfile_src();
	  
	  response.setContentType("application/octet-stream");
	  
	  orgname=new String(orgname.getBytes("UTF-8"),"iso-8859-1");
	  
	  response.setHeader("Content-Disposition","attachment; filename=\""+orgname+"\"");
	  
	  OutputStream os = response.getOutputStream();
	  
	  String path = "C:\\Users\\Administrator\\Desktop\\upload";
	  FileInputStream fis = new FileInputStream(path+File.separator+orgname);
	  
	  int n = 0;
	  byte[] b = new byte[512];
	  while((n=fis.read(b)) != -1){
	   os.write(b,0,n);
	  }
	  fis.close();
	  os.close();
	}
	
	@RequestMapping(value="/bcodedelete.do") 
	public ModelAndView bcodeDelete(HttpServletRequest request) throws Exception{
		
		int delete = (int)Integer.parseInt(request.getParameter("board_id"));
		bcode.bcodeDelete(delete);
		
		return new ModelAndView("redirect:/bcodelist.do");
	}
	
	
	@RequestMapping(value = "/file_uploader_html5.do", method = RequestMethod.POST)
	@ResponseBody
	public String multiplePhotoUpload(HttpServletRequest request) {

		StringBuffer sb = new StringBuffer();
		try {

			String oldName = request.getHeader("file-name");

			String filePath = "C:/Users/Administrator/Desktop/Bang/dokky/src/main/webapp/resources/photoUpload/";
			String saveName = sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis()))
					.append(UUID.randomUUID().toString()).append(oldName.substring(oldName.lastIndexOf(".")))
					.toString();
			InputStream is = request.getInputStream();
			OutputStream os = new FileOutputStream(filePath + saveName);
			int numRead;
			byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
			while ((numRead = is.read(b, 0, b.length)) != -1) {
				os.write(b, 0, numRead);
			}
			os.flush();
			os.close();

			sb = new StringBuffer();
			sb.append("&bNewLine=true").append("&sFileName=").append(oldName).append("&sFileURL=")
					.append("C:/Users/Administrator/Desktop/Bang/dokky/src/main/webapp/resources/photoUpload/").append(saveName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	@RequestMapping(value="/bcodeuploaddelete.do")
	public String bcodeUploaddelete(HttpServletRequest request) throws Exception{
		
		int uploaddelete = Integer.parseInt(request.getParameter("uploaddelete_id"));
		bcode.bcodeUploaddelete(uploaddelete);
		
		return "redirect:/bcodewrite.do?board_id="+request.getParameter("uploaddelete_id"); 
		
	}

}