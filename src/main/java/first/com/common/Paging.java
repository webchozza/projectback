package first.com.common;

public class Paging {

	private int totalPage;
	private int startCount;
	private int endCount;
	private int startPage;
	private int endPage;

	private StringBuffer pagingHtml;

	public Paging(String path, int currentPage, int totalCount, int blockCount, int blockPage, String search, int n) {

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
					"<a href='" + path + ".do?currentPage=" + (startPage - 1) + "&search=" + search + "&n=" + n + "'>");
			pagingHtml.append("[PREV]");
			pagingHtml.append("</a>");
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
				pagingHtml.append(
						"&nbsp;<a href='" + path + ".do?currentPage=" + i + "&search=" + search + "&n=" + n + "'>");
				pagingHtml.append(i);
				pagingHtml.append("</a>");
			}

			pagingHtml.append("&nbsp;");
		}

		if (totalPage - startPage >= blockPage) {
			pagingHtml.append(
					"<a href='" + path + ".do?currentPage=" + (endPage + 1) + "&search=" + search + "&n=" + n + "'>");
			pagingHtml.append("[NEXT]");
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
