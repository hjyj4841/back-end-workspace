package mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kh.ajax.model.vo.Member;

@Mapper
public interface MemberMapper {
	Member idCheck(String id);
	void serial(Member member);
}
