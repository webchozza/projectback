package first.com.controller.bcode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import first.com.dao.TagDAO;
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

	@Resource
	private TagDAO tagService;	//by jinjoo
	
	private int totalCount; // 珥� �닔
	private int blockCount = 10; // �븳 �럹�씠吏��쓽 寃뚯떆臾쇱쓽 �닔
	private int blockPage = 5; // �븳 �솕硫댁뿉 蹂댁뿬以� �럹�씠吏� �닔
	private String pagingHtml; // �럹�씠吏뺤쓣 援ы쁽�븳 HTML
	private Paging page; // �럹�씠吏� �겢�옒�뒪
	private String[] kind = { "JAVA", "SPRING", "SQL" };

	ModelAndView Clist;
	
	// 1.�삤�뵂 �냼�뒪 寃뚯떆�뙋 由ъ뒪�듃 
	// 6.�젙�젹
	// 9.寃��깋
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

		page = new Paging("bcodelist", currentPage, totalCount, blockCount, blockPage, search, n);
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

		Clist.addObject("pagingHtml", pagingHtml);
		Clist.addObject("n", n);

		return Clist;
	}

	// 2.�긽�꽭蹂닿린
	// 2.1 議고쉶�닔 利앷�
	// 10.�뙎湲� 由ъ뒪�듃
	// 10.1.�뙎湲� �벐湲�
	@RequestMapping(value = "/bcodedetail.do")
	public ModelAndView bcodeDetail(HttpServletRequest request,BoardDTO dTO,BfileDTO dTOFile,BcommentDTO dTOComment,
									@RequestParam(value="session_id", defaultValue="-1") int session_id) throws Exception {

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
        
        if(request.getParameter("bcomment_content") != null){
      	  bcode.bcodeCommentinsert(dTOComment);
      	  
      	//by eongoo, comment noti
      		noti.insertCommentNoti(board_id2, session_id, "/bcodedetail");
      		//
        }
		
        BoardDTO detail = (BoardDTO)bcode.bcodeDetail(board_id2);
        BfileDTO detail2 = (BfileDTO)bcode.bcodeDetailfile(board_id2);
        List<BcommentDTO> detail3 = (List<BcommentDTO>) bcode.bcodeComment(board_id2);
                
        ModelAndView Cdetail = new ModelAndView("Cdetail");
        
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
		
        Cdetail.addObject("detail", detail);
		Cdetail.addObject("detail2", detail2);
		Cdetail.addObject("detail3", detail3);

		return Cdetail;
	}

	// 3.湲��벐湲�(�뤌)
	// 4.�닔�젙(�뤌)
	@RequestMapping(value="/bcodewrite.do")
	public ModelAndView bcodeWrite(HttpServletRequest request,@ModelAttribute BoardDTO dTO) throws Exception {
		
		ModelAndView Cwrite = new ModelAndView();
		Cwrite.setViewName("Cwrite");
		
		if(request.getParameter("board_id") != null){
			
			String update = request.getParameter("board_id").trim();
			int update2 = Integer.parseInt(update);
			
			BoardDTO updateform = (BoardDTO)bcode.bcodeUpdateform(update2);			
			updateform.setBoard_tag(tagService.modifyFormView(updateform.getBoard_tag(), 1));//bgroup_id=1
			
			BfileDTO detail2 = (BfileDTO)bcode.bcodeDetailfile(update2);
			Cwrite.addObject("updateform", updateform);
			Cwrite.addObject("file", detail2);
			
			return Cwrite;
		}else{
			return Cwrite;
		}
}

	// 3.1湲��벐湲�(�옉�꽦�셿猷�)
	// 4.1�닔�젙(�옉�꽦�셿猷�)
	// 7.�뾽濡쒕뱶
	@RequestMapping(value="/bcodeinsert.do")
	public ModelAndView bcodeInsert(MultipartHttpServletRequest request,@ModelAttribute BoardDTO dTO,@ModelAttribute BfileDTO dTOFile) 
			throws Exception {// 洹몃읆 �뿬湲곗꽑  member_id�옉 bgroup_id 2媛� �뱾�뼱�샂
		Date date = new Date();
		if(request.getParameter("board_id") != null){
			dTO.setBoard_tag(tagService.insertTag(dTO.getBoard_tag(), 1));//bgroup_id=1
			bcode.bcodeUpdate(dTO);
			if(request.getFile("file").getSize() != 0){
				File file = new File("C:\\workspace\\dokky\\src\\main\\webapp\\resources\\upload/"+request.getFile("file").getOriginalFilename()+date.getTime());
				request.getFile("file").transferTo(file);
				dTOFile.setBoard_id(dTO.getBoard_id());
				dTOFile.setBfile_size(request.getFile("file").getSize());
				dTOFile.setBfile_src(request.getFile("file").getOriginalFilename());
				
				bcode.bcodeUpload(dTOFile);
				
			}
	
			return new ModelAndView("redirect:/bcodelist.do");
			
		}else if(request.getFile("file").getSize()!= 0){
			dTO.setBoard_tag(tagService.insertTag(dTO.getBoard_tag(), 1));//bgroup_id=1
			bcode.bcodeInsert(dTO);
			
			File file = new File("C:\\workspace\\dokky\\src\\main\\webapp\\resources\\upload/"+request.getFile("file").getOriginalFilename()+date.getTime());
			request.getFile("file").transferTo(file);
			dTOFile.setBoard_id(dTO.getBoard_id());
			dTOFile.setBfile_size(request.getFile("file").getSize());
			dTOFile.setBfile_src(request.getFile("file").getOriginalFilename());
			
			bcode.bcodeUpload(dTOFile);
			
			// by eongoo, new board noti
			noti.insertNewBoardNoti(Integer.parseInt(request.getParameter("member_id")), "/bcodedetail", 1);
			//
			
			return new ModelAndView("redirect:/bcodelist.do");
		}else{
			dTO.setBoard_tag(tagService.insertTag(dTO.getBoard_tag(), 1));//bgroup_id=1
			bcode.bcodeInsert(dTO);

			// by eongoo, new board noti
			noti.insertNewBoardNoti(Integer.parseInt(request.getParameter("member_id")), "/bcodedetail", 1);
			//
			
			return new ModelAndView("redirect:/bcodelist.do");
		}

	}
	// 8.�떎�슫濡쒕뱶
	@RequestMapping(value="/bcodedownload.do")
	public void bcodeFiledown(HttpServletRequest request,HttpServletResponse response,BfileDTO dTOfile) throws Exception{
		
		int board_id2 = (int)Integer.parseInt(request.getParameter("board_id"));
		
		BfileDTO detail2 = (BfileDTO)bcode.bcodeDetailfile(board_id2);
		String orgname = detail2.getBfile_src();
		
		response.setContentType("application/octet-stream");
		
		orgname=new String(orgname.getBytes("UTF-8"),"iso-8859-1");
		
		response.setHeader("Content-Disposition","attachment; filename=\""+orgname+"\"");
		
		OutputStream os = response.getOutputStream();
		
		
		String path = "C:\\workspace\\dokky\\src\\main\\webapp\\resources\\upload/";
		FileInputStream fis = new FileInputStream(path+File.separator+orgname);
		
		int n = 0;
		byte[] b = new byte[512];
		while((n=fis.read(b)) != -1){
			os.write(b,0,n);
		}
		fis.close();
		os.close();
	}
	
	// 13.�궘�젣�븯湲�
	@RequestMapping(value="/bcodedelete.do") 
	public ModelAndView bcodeDelete(HttpServletRequest request,@RequestParam("session_id") int session_id) throws Exception{
		
		int delete = (int)Integer.parseInt(request.getParameter("board_id"));
		bcode.bcodeDelete(delete);
		
		return new ModelAndView("redirect:/bcodelist.do?session_id="+session_id);
	}
	
	// 13.�궘�젣�븯湲�
		@RequestMapping(value="/bcodedeletecomment.do") 
		public ModelAndView bcodeDeleteComment(HttpServletRequest request,@RequestParam("session_id") int session_id,
				@RequestParam("board_id") int board_id,@RequestParam("bcomment_id") int bcomment_id) throws Exception{
			
			bcode.delco(bcomment_id);
			
			return new ModelAndView("redirect:/bcodedetail.do?session_id="+session_id+"&board_id="+board_id);
		}
	
	
	// �뀓�뒪�듃 �뿉�뵒�꽣 �뾽濡쒕뱶
	@RequestMapping(value = "/file_uploader_html5.do", method = RequestMethod.POST)
	@ResponseBody
	public String multiplePhotoUpload(HttpServletRequest request) {
		// �뙆�씪�젙蹂�
		StringBuffer sb = new StringBuffer();
		try {
			// �뙆�씪紐낆쓣 諛쏅뒗�떎 - �씪諛� �썝蹂명뙆�씪紐�
			String oldName = request.getHeader("file-name");
			// �뙆�씪 湲곕낯寃쎈줈 _ �긽�꽭寃쎈줈
			String filePath = "C:\\workspace\\dokky\\src\\main\\webapp\\resources\\photoupload/";
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
			// �젙蹂� 異쒕젰
			sb = new StringBuffer();
			sb.append("&bNewLine=true").append("&sFileName=").append(oldName).append("&sFileURL=")
					.append("C:\\workspace\\dokky\\src\\main\\webapp\\resources\\photoupload/").append(saveName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	// 14.�뾽濡쒕뱶 �뙆�씪 �궘�젣
	@RequestMapping(value="/bcodeuploaddelete.do")
	public String bcodeUploaddelete(HttpServletRequest request) throws Exception{
		
		int uploaddelete = Integer.parseInt(request.getParameter("uploaddelete_id"));
		bcode.bcodeUploaddelete(uploaddelete);
		
		/*"forward:/bcodewrite.do";*/
		
		return "redirect:/bcodewrite.do?board_id="+request.getParameter("uploaddelete_id"); 
		
	}

}