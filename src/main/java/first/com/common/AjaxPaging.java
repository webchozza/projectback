package first.com.common;

public class AjaxPaging {

	private int totalPage;
	private int startCount;
	private int endCount;
	private int startPage;
	private int endPage;
	private String fullPath;

	private StringBuffer pagingHtml;

	public AjaxPaging(String path, int currentPage, int totalCount, int blockCount, int blockPage, String search, int n) {

		fullPath = "/dokky/"+path+".do";//jsp에 모델에 path라는 이름으로 전달해준 후  Ajax 통신시 data로 컨트롤러로 쏴준다

		path = "\"/dokky/" + path + ".do\"";

		totalPage = (int) Math.ceil((double) totalCount / blockCount);
		if (totalPage == 0) {
			totalPage = 1;
		}

		if (currentPage > totalPage) {
			currentPage = totalPage;
		}

		startCount = (currentPage - 1) * blockCount;
		endCount = startCount + blockCount - 1;

		startPage = (int) ((currentPage - 1) / blockPage) * blockPage + 1;
		endPage = startPage + blockPage - 1;

		if (endPage > totalPage) {
			endPage = totalPage;
		}

		pagingHtml = new StringBuffer();

		if (currentPage > blockPage) {
			if(search.equals("")){
					pagingHtml.append(
							"<a href='javascript:;' onclick='paging("+path+","+(startPage - 1)+","+"\"\""+","+n+")'>");
					pagingHtml.append("[PREV]");
					pagingHtml.append("</a>");
			} else {
				pagingHtml.append(
						"<a href='javascript:;' onclick='paging("+path+","+(startPage - 1)+","+search+","+n+")'>");
				pagingHtml.append("[PREV]");
				pagingHtml.append("</a>");
			}
		}

		for (int i = startPage; i <= endPage; i++) {
			if (i > totalPage) {
				break;
			}
			if (i == currentPage) {
				pagingHtml.append("&nbsp;<b> <font color='red'>");
				pagingHtml.append(i);
				pagingHtml.append("</font></b>");
			} else {
				if(search.equals("")){
					pagingHtml.append(
							"&nbsp;<a href='javascript:;' onclick='paging("+path+","+i+","+"\"\""+","+n+")'>");
					pagingHtml.append(i);
					pagingHtml.append("</a>");
					
				} else{
				pagingHtml.append(
						"&nbsp;<a href='javascript:;' onclick='paging("+path+","+i+","+search+","+n+")'>");
				pagingHtml.append(i);
				pagingHtml.append("</a>");
				}
			}

			pagingHtml.append("&nbsp;");
		}

		if (totalPage - startPage >= blockPage) {
			if(search.equals("")){
			pagingHtml.append(
					"<a href='javascript:;' onclick='paging("+path+","+(endPage + 1)+","+"\"\""+","+n+")'>");
			pagingHtml.append("[NEXT]");
			pagingHtml.append("</a>");
			} else {
				pagingHtml.append(
						"<a href='javascript:;' onclick='paging("+path+","+(endPage + 1)+","+search+","+n+")''>");
				pagingHtml.append("[NEXT]");
				pagingHtml.append("</a>");
			}
		}
	}

	public StringBuffer getPagingHtml() {
		return pagingHtml;
	}

	public int getStartCount() {
		return startCount;
	}

	public int getEndCount() {
		return endCount;
	}
	public String getFullPath() {
		return fullPath;
	}

}
