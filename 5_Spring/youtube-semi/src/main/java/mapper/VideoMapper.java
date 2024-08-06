package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.semi.youtube.model.vo.Video;

@Mapper
public interface VideoMapper {
	List<Video> allVideo();
	Video detail(int videoCode);
}
