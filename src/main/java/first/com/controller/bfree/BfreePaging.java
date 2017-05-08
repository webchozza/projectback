package first.com.controller.bfree;

public class BfreePaging {

	private int totalPage;
	private int startCount;
	private int endCount;
	private int startPage;
	private int endPage;

	private StringBuffer pagingHtml;

	public BfreePaging(String path, int currentPage, int totalCount, int blockCount, int blockPage, String search, int n, String sort) {

		path = "/dokky/" + path;

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
			pagingHtml.append(
					"<a href='" + path + ".do?sort="+sort+"&currentPage=" + (startPage - 1) + "&search=" + search + "&n=" + n + "'>");
			pagingHtml.append("[이전]");
			pagingHtml.append("</a>");
		}

		for (int i = startPage; i <= endPage; i++) {
			if (i > totalPage) {
				break;
			}
			if (i == currentPage) {//안돼면 여기서 클래스 뺴기
				pagingHtml.append("&nbsp;<b> <font color='#f56a6a' class='page active'>");
				pagingHtml.append(i);
				pagingHtml.append("</font></b>");
			} else {
				pagingHtml.append(
						"&nbsp;<a href='" + path + ".do?sort="+sort+"&currentPage=" + i + "&search=" + search + "&n=" + n + "'>");
				pagingHtml.append(i);
				pagingHtml.append("</a>");
			}

			pagingHtml.append("&nbsp;");
		}

		if (totalPage - startPage >= blockPage) {
			pagingHtml.append(
					"<a href='" + path + ".do?sort="+sort+"&currentPage=" + (endPage + 1) + "&search=" + search + "&n=" + n + "'>");
			pagingHtml.append("[다음]");
			pagingHtml.append("</a>");
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

}
