package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.upload.model.vo.Board;
import com.kh.upload.model.vo.Paging;

@Mapper
public interface BoardMapper {
	void createBoard(Board board);
	List<Board> showList(Paging paging);
	Board view(int no);
	void update(Board board);
	void delete(int no);
}
