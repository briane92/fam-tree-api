package io.sdtwo.famtree.api.dao;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import io.sdtwo.famtree.api.types.Member;
import io.sdtwo.famtree.api.types.MemberInput;

@Component
public class MemberRepository {

	private final Map<Integer, Member> members;
	private final String DATA = "MOCK_DATA.csv";

	protected Member createMember(String s) {
		String[] values = s.split(",", 4);
		if (values.length != 4) {
			return null;
		}
		Member member = new Member(values[1], values[2]);
		member.setId(Integer.parseInt(values[0]));
		member.setBio(values[3]);
		return member;
	}

	public MemberRepository() {
		members = new HashMap();
		URL url = this.getClass().getClassLoader().getResource(DATA);
		try (Stream<String> stream = Files.lines(Paths.get(url.toURI()))) {
			stream.map(s -> createMember(s)).forEach(m -> members.put(m.getId(), m));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public List<Member> getMembers() {
		return this.members.values().stream().collect(Collectors.toList());
	}

	public Optional<Member> get(int id) {
		System.out.println("Getting member with id:" + id);
		if (this.members.containsKey(id)) {
			System.out.println("Member with id:" + id + " found");
			System.out.println("Member name is " + members.get(id).getName());
		}
		return Optional.ofNullable(this.members.get(id));
	}

	public void saveMember(Member member) {
		this.members.put(member.getId(), member);
	}

	public Member updateMember(int id, MemberInput input) {
		System.out.println("member input: " + input.getName() + " " + input.getBio() + " " + input.getRelation());
		System.out.println("Updating member with id:" + id);
		Optional<Member> member = this.get(id);
		if (member.isPresent()) {
			System.out.println("Member with id:" + id + " found");
			Member m = member.get();
			String bio = input.getBio();
			if (bio != null) {
				m.setBio(bio);
			}
			String name = input.getName();
			if (name != null) {
				m.setName(name);
			}
			String relation = input.getRelation();
			if (relation != null) {
				m.setRelation(relation);
			}
			this.members.put(m.getId(), m);
			return m;

		} else {
			System.out.println("Member with id:" + id + " not found");
			return null;
		}
	}

}
