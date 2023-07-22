package User;

import java.util.List;

import community.BoardDTO;
import travelspot.CommentsDTO;
import travelspot.PlaceDTO;

public interface UserService {

	public void signup(UserDTO dto);

	public UserDTO login(String userid, String userpw);

	UserDTO getUserById(int id);

	void updateUser(UserDTO dto);

	void withdrawUser(String userid); // 회원 탈퇴

	UserDTO findUserId(String email, String phone);

	UserDTO findUserPw(String userid, String email);

	void resetPassword(String userid, String email, String temporaryPassword);

	String generateTemporaryPassword();

	List<VisitedDTO> getRecentVisitedPages(String user_id, int limit);

	void addVisitedPage(String user_id, String pageurl);

	List<BoardDTO> getBoardListByWriter(String writer);

	List<CommentsDTO> getCommentListByWriter(String writer);

}
