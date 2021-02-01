package kr.or.ddit.user.repository;

import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVo;

//<bean id="" class=""
//@Repository에서 별다른 설정을 하지 않으면 스프링 빈 이름을 class 이름에서 첫글자를 소문자로 한
// 문자열이 스프링 빈의 이름으로 설정된다
// UserDaoImpl ==> userDaoImpl


//UserDao /UserDaoImpl ==> @resourse(name="UserDaoImpl") 
//UserDaoI /UserDao ==> @resourse(name="UserDao) 

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Override
	public UserVo getUser(String userid) {
		// 원래는 데이터 베이스에서 조회를 해야하나, 개발 초기단계라
		// 설정이 완료되지 않음, 현재 확인하려고 하는 기능은 스프링 컨테이너에 초점을 맞추기 위해
		// new 연산자를 통해 생성한 VO 객체를 반환

		// UserVo user = new UserVo("brown", "브라운");

		return new UserVo("brown", "브라운", "brownPass");
	}

}
