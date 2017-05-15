package first.com.model;

public class TagDTO {
	
	private String sort;
	private String tag;
	private String tag2;
	private String tag3;
	private String tag4;

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTag2() {
		return tag2;
	}

	public void setTag2(String tag) {
		this.tag2 = tag+",";
	}

	public String getTag3() {
		return tag3;
	}

	public void setTag3(String tag) {
		this.tag3 = ","+tag;
	}

	public String getTag4() {
		return tag4;
	}

	public void setTag4(String tag) {
		this.tag4 = ","+tag+",";
	}

	
}
