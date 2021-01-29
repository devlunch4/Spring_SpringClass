package kr.or.ddit.user.repository;

import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVo;

//<bean id="" class=""
//@Repository���� ���ٸ� ������ ���� ������ ������ �� �̸��� class �̸����� ù���ڸ� �ҹ��ڷ� ��
// ���ڿ��� ������ ���� �̸����� �����ȴ�
// UserDaoImpl ==> userDaoImpl


//UserDao /UserDaoImpl ==> @resourse(name="UserDaoImpl") 
//UserDaoI /UserDao ==> @resourse(name="UserDao) 

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Override
	public UserVo getUser(String userid) {
		// ������ ������ ���̽����� ��ȸ�� �ؾ��ϳ�, ���� �ʱ�ܰ��
		// ������ �Ϸ���� ����, ���� Ȯ���Ϸ��� �ϴ� ����� ������ �����̳ʿ� ������ ���߱� ����
		// new �����ڸ� ���� ������ VO ��ü�� ��ȯ

		// UserVo user = new UserVo("brown", "����");

		return new UserVo("brown", "����");
	}

}
