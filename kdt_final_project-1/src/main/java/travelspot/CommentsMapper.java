package travelspot;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper 	//매퍼 파일이야, @MapperScan 필요 
@Repository //객체 생성, @ComponentScan 필요
public interface CommentsMapper {
	 public List<CommentsDTO> getComments(int place_id);
	 public void insertComments(CommentsDTO CommentsDTO);
	 public void deleteComments(int id);
	 public CommentsDTO getOneContent(int id);
	 public void updateComments(CommentsDTO CommentsDTO);
	 public void insertReply(CommentsDTO CommentsDTO);
}
