package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.upload.model.vo.Board;

@Mapper
public interface BoardMapper {
	void createBoard(Board board);
	List<Board> showList();
}
