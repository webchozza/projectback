package first.com.model;

public class BoardVO {
	
	private int qna = 0;//Qna 게시판
	private int free = 0;//자유게시판(커뮤니티)
	private int code = 0;//오픈소스 게시판
	private int job = 0;//구인구직 게시판
	
	
	public int getQna() {
		return qna;
	}
	public int getFree() {
		return free;
	}
	public int getCode() {
		return code;
	}
	public int getJob() {
		return job;
	}
}
