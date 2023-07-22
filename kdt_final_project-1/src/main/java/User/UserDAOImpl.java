package User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import community.BoardDTO;
import travelspot.CommentsDTO;
import travelspot.PlaceDTO;;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	public SqlSession sqlSession;

	@Override
	public void signup(UserDTO dto) {
		sqlSession.insert("signup", dto);
	}

	@Override
	public UserDTO findByUserId(String userid) {
		UserDTO user = sqlSession.selectOne("findByUserId", userid);
		if (user != null) {
			return user;
		}
		return null;
	}

	@Override
	public UserDTO getUserById(int id) {
		return sqlSession.selectOne("getUserById", id);
	}

	@Override
	public void updateUser(UserDTO dto) {
		sqlSession.update("updateUser", dto);

	}

	@Override
	public void withdrawUser(String userid) {
		sqlSession.delete("withdrawUser", userid);

	}

	@Override
	public UserDTO selectfindid(String email, String phone) {
		Map<String, String> params = new HashMap<>();
		params.put("email", email);
		params.put("phone", phone);
		return sqlSession.selectOne("selectfindid", params);
	}

	@Override
	public UserDTO selectfindpw(String userid, String email) {
		Map<String, String> params = new HashMap<>();
		params.put("userid", userid);
		params.put("email", email);
		return sqlSession.selectOne("selectfindpw", params);
	}

	@Override
	public void updatePassword(UserDTO dto) {
		sqlSession.update("updatePassword", dto);

	}

	@Override
	public void addVisitedPage(VisitedDTO dto) {
		sqlSession.insert("addVisitedPage", dto);

	}

	@Override
	public List<VisitedDTO> getRecentVisitedPages(String user_id, int limit) {
		Map<String, Object> params = new HashMap<>();
		params.put("user_id", user_id);
		params.put("limit", limit);
		return sqlSession.selectList("getRecentVisitedPages", params);
	}

	@Override
	public List<BoardDTO> getBoardListByWriter(String writer) {
		return sqlSession.selectList("getBoardListByWriter", writer);
	}

	@Override
	public List<CommentsDTO> getCommentListByWriter(String writer) {
		return sqlSession.selectList("getCommentListByWriter", writer);
	}

	@Override
	public List<LikesDTO> getLikesByUserId(int user_id) {
		return sqlSession.selectList("getLikesByUserId",user_id);
	}

	@Override
	public List<PlaceDTO> getPlacesByContentIds(List<Integer> content_id) {
		return sqlSession.selectList("getPlaceByContentIds",content_id);
	}

	
}
