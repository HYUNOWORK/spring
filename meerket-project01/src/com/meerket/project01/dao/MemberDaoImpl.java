package com.meerket.project01.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.meerket.project01.domain.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

	private SqlSessionTemplate sqlSession;
	
	
	private final String NAME_SPACE = "com.meerket.project01.repository.mappers.MemberMapper";
	
	
	@Autowired
	private void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	

	@Override
	public List<Member> memberList() {
	
		return sqlSession.selectList(NAME_SPACE + ".memberList");
	}
	
	
	@Override
	public int login(String userId, String pass) {

		Member m  = sqlSession.selectOne(NAME_SPACE + ".login", userId);
		int result = -1;
		
		// id ������ -1
		if(m==null) {
			return result;
		}
		
		// ����
		if(m.getPass().equals(pass)) {
			result = 1;
		// ����
		} else {
			result = 0;
		}
		return result;
	}

	// id�� �ش��ϴ� ȸ������ �б�
	@Override
	public Member getMember(String userId) {

		return sqlSession.selectOne(NAME_SPACE + ".getMember",userId);
	}
	
	
	// ȸ������ ȸ�� ���̺� �����ϱ�
	@Override
	public void addMember(Member member) {
	sqlSession.insert(NAME_SPACE + ".addMember", member);
	}

	@Override
	public boolean memberPassCheck(String userId, String pass) {
		boolean result = false;
		// memberPassCheck ���� ������ ȣ���ϸ鼭 ȸ�� ���̵� �Ķ���ͷ� �����ߴ�.
		
		String dbPass = sqlSession.selectOne(
		NAME_SPACE + ".memberPassCheck",userId);
		
		
		// ��й�ȣ�� ������ true�� ��ȯ
		if(dbPass.equals(pass)) {
		
			result = true;
			
		}
		
		return result;
	}

	//ȸ�� ���̺� ���� �޼���
	@Override
	public void updateMember(Member member) {
		sqlSession.update(NAME_SPACE + ".updateMember", member);
		
	}

	
}
