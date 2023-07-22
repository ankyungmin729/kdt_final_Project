package User;

import java.sql.Timestamp;

public class VisitedDTO {
	public int id;
	public String user_id;
	public String pageurl;
	public Timestamp visited;

	public VisitedDTO() {
	}

	public VisitedDTO(String user_id, String pageurl) {
		this.user_id = user_id;
		this.pageurl = pageurl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPageurl() {
		return pageurl;
	}

	public void setPageurl(String pageurl) {
		this.pageurl = pageurl;
	}

	public Timestamp getVisited() {
		return visited;
	}

	public void setVisited(Timestamp visited) {
		this.visited = visited;
	}

	@Override
	public String toString() {
		return "VisitedDTO [id=" + id + ", user_id=" + user_id + ", pageurl=" + pageurl + ", visited=" + visited + "]";
	}

}