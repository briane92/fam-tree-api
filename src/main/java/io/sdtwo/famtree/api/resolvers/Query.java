package io.sdtwo.famtree.api.resolvers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import io.sdtwo.famtree.api.dao.MemberRepository;
import io.sdtwo.famtree.api.types.Member;

@Component
public class Query implements  GraphQLQueryResolver{
	
	@Autowired
	private MemberRepository memberRepository;
	
	public List<Member> allMembers() {
		return memberRepository.getMembers();
	}
	
	public Member member(int id){
		// returns the member if present or null otherwise
		return memberRepository.get(id).orElse(null);
	}
	
}
