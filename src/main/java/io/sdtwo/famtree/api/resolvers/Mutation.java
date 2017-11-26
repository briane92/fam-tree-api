package io.sdtwo.famtree.api.resolvers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import io.sdtwo.famtree.api.dao.MemberRepository;
import io.sdtwo.famtree.api.types.Member;
import io.sdtwo.famtree.api.types.MemberInput;

@Component
public class Mutation implements GraphQLMutationResolver {
	
	
	@Autowired
	private MemberRepository memberRepository;
	
	public Member createMember(String name, String relation) {
		Member member = new Member(name, relation);
		memberRepository.saveMember(member);
		System.out.println("Member saved");
		return member;
	}

	public Member updateMember(int id, MemberInput input) {
		System.out.println("member input: " + input.getName() + " " + input.getBio() + " " + input.getRelation());
		return memberRepository.updateMember(id, input);
	}

}
