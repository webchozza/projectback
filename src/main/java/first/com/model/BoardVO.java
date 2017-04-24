package first.com.model;

public class BoardVO {
	
	private int code = 1;//오픈소스 게시판
	private int free = 2;//자유게시판(커뮤니티)
	private int job = 3;//구인구직 게시판
	private int qna = 4;//Qna 게시판
	
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
