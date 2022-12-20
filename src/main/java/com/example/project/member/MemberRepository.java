package com.example.project.member;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

//Member의 PK가 String이라서 <Member, String>
public interface MemberRepository extends JpaRepository<Member, String>{
	Optional<Member> findById(String id);
	Optional<Member> findByIdAndPw(String id, String pw);
	
	Optional<Member> findByNameAndEmail(String name, String email);
	Optional<Member> findByIdAndEmail(String id, String email);

}
