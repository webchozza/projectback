package first.com.controller.bcode;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.Multipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import first.com.common.Paging;
import first.com.model.BcommentDTO;
import first.com.model.BfileDTO;
import first.com.model.BoardDTO;

@Controller
public class BcodeController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private BcodeService bcode;

	private int totalCount; // 珥� �닔
	private int blockCount = 10; // �븳 �럹�씠吏��쓽 寃뚯떆臾쇱쓽 �닔
	private int blockPage = 5; // �븳 �솕硫댁뿉 蹂댁뿬以� �럹�씠吏� �닔
	private String pagingHtml; // �럹�씠吏뺤쓣 援ы쁽�븳 HTML
	private Paging page; // �럹�씠吏� �겢�옒�뒪
	private String path = "bcodelist";// if (RequestMapping("/here.do")) => here
										// = path
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

	// 2.�긽�꽭蹂닿린
	// 2.1 議고쉶�닔 利앷�
	// 10.�뙎湲� 由ъ뒪�듃
	// 10.1.�뙎湲� �벐湲�
	@RequestMapping(value = "/bcodedetail.do")
	public ModelAndView bcodeDetail(HttpServletRequest request,BoardDTO dTO,BfileDTO dTOFile,BcommentDTO dTOComment) throws Exception {
	    int board_id2 = (int)Integer.parseInt(request.getParameter("board_id"));
	    
        if(request.getParameter("board_like") != null){
        	int like = (int)Integer.parseInt(request.getParameter("board_like"));
        	bcode.bcodeInreaselike(like);
        }

		bcode.bcodeInreasehit(board_id2);
		

        BoardDTO detail = (BoardDTO)bcode.bcodeDetail(board_id2);
        BfileDTO detail2 = (BfileDTO)bcode.bcodeDetailfile(board_id2);
        List<BcommentDTO> detail3 = (List<BcommentDTO>) bcode.bcodeComment(board_id2);
        
        
        System.out.println(request.getParameter("board_like"));

        
        if(request.getParameter("bcomment_content") != null){
        	  bcode.bcodeCommentinsert(dTOComment);
        }
      
        ModelAndView Cdetail = new ModelAndView("Cdetail");
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
			Cwrite.addObject("updateform", updateform);
			
			return Cwrite;
		}else{
			return Cwrite;
		}

	}

	
	@RequestMapping(value="/bcodeinsert.do")
	public ModelAndView bcodeInsert(MultipartHttpServletRequest request,@ModelAttribute BoardDTO dTO,@ModelAttribute BfileDTO dTOFile) throws Exception {// 洹몃읆 �뿬湲곗꽑  member_id�옉 bgroup_id 2媛� �뱾�뼱�샂
		
		if(request.getParameter("board_id") != null){
			bcode.bcodeUpdate(dTO);
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
		
		
		String path = "C:\\upload";
		FileInputStream fis = new FileInputStream(path+File.separator+orgname);
		
		int n = 0;
		byte[] b = new byte[512];
		while((n=fis.read(b)) != -1){
			os.write(b,0,n);
		}
		fis.close();
		os.close();
	}
	
	/*

	 * //�궘�젣�븯湲�
	 * 
	 * @RequestMapping(value="/bcodeDelete.do") public ModelAndView
	 * bcodeDelete(BoardDTO dTO) throws Exception{
	 * bcodeService.bcodeDelete(dTO);
	 * 
	 * ModelAndView Cdelete = new ModelAndView();
	 * Cdelete.setViewName("redirect:/bcodelist.do");
	 * 
	 * return Cdelete; }
	 */

}