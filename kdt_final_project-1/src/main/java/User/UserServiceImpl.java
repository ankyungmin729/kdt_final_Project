package User;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import community.BoardDTO;
import travelspot.CommentsDTO;
import travelspot.PlaceDTO;;

@Service

public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO dao;

	@Override
	public void signup(UserDTO dto) {
		dao.signup(dto);
	}

	@Override
	public UserDTO login(String userid, String userpw) {
		UserDTO dto = dao.findByUserId(userid);
		if (dto != null && dto.getUserpw().equals(userpw)) {
			return dto;
		} else {
			return null;
		}
	}

	@Override
	public UserDTO getUserById(int id) {
		return dao.getUserById(id);
	}

	@Override
	@Transactional
	public void updateUser(UserDTO dto) {
//		System.out.println("Before Update - DTO: " + dto.toString());
		dao.updateUser(dto);
//		System.out.println("After Update - DTO: " + dto.toString());
	}

	@Override
	@Transactional
	public void withdrawUser(String userid) {
		dao.withdrawUser(userid);
	}

	@Override
	public UserDTO findUserId(String email, String phone) {
		return dao.selectfindid(email, phone);
	}

	@Override
	public UserDTO findUserPw(String userid, String email) {
		UserDTO dto = dao.selectfindpw(userid, email);
		return dto;
	}

	@Override
	@Transactional
	public void resetPassword(String userid, String email, String temporaryPassword) {
		UserDTO dto = dao.selectfindpw(userid, email);
		if (dto != null) {
			dto.setUserpw(temporaryPassword);
			dao.updatePassword(dto);
		}
	}

	@Override
	public String generateTemporaryPassword() {
		// 임시 비밀번호 생성 로직
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			int index = random.nextInt(characters.length());
			sb.append(characters.charAt(index));
		}
		return sb.toString();
	}

	@Override
	public void addVisitedPage(String user_id, String pageurl) {
		VisitedDTO dto = new VisitedDTO(user_id, pageurl);
		dao.addVisitedPage(dto);
	}

	@Override
	public List<VisitedDTO> getRecentVisitedPages(String user_id, int limit) {
		return dao.getRecentVisitedPages(user_id, limit);
	}

	@Override
	public List<BoardDTO> getBoardListByWriter(String writer) {
		return dao.getBoardListByWriter(writer);
	}

	@Override
	public List<CommentsDTO> getCommentListByWriter(String writer) {
		return dao.getCommentListByWriter(writer);
	}
}