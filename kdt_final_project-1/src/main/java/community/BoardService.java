package community;

import java.util.List;

public interface BoardService {
	void createBoard(BoardDTO board);
	List<BoardDTO> getAllBoards(); // 모든 Board 목록을 가져오는 메서드
	List<BoardDTO> getTop10Boards(); // Top 10 Board 목록을 가져오는 메서드
	BoardDTO getBoardById(int boardId);
	void updateBoard(BoardDTO board);
	void deleteBoard(BoardDTO board);
	
	// 게시글 조회수 증가 메서드 추가
    void increaseViewCount(int boardId);
    
 // 최신순으로 게시글 가져오기
    List<BoardDTO> getNewestBoards();
}