package first.com.model;

public class FollowDTO {
	
	private int member_id;
	private int follow_member_id;
	private int following_count;//자신이 팔로잉한 수
	private int follower_count;//자신을 팔로우한 팔로워의 수
	

	public int getFollowing_count() {
		return following_count;
	}
	public void setFollowing_count(int following_count) {
		this.following_count = following_count;
	}
	public int getFollower_count() {
		return follower_count;
	}
	public void setFollower_count(int follower_count) {
		this.follower_count = follower_count;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public int getFollow_member_id() {
		return follow_member_id;
	}
	public void setFollow_member_id(int follow_member_id) {
		this.follow_member_id = follow_member_id;
	}

}
