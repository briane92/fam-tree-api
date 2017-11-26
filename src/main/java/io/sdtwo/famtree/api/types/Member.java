package io.sdtwo.famtree.api.types;

public class Member {
	private String name;
	private String relation;
	private String bio;
	private int id;
	
	public Member(String name, String relation) {
		super();
		this.name = name;
		this.relation = relation;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.name);
		builder.append("\n");
		builder.append(this.relation);
		builder.append("\n");
		builder.append(this.bio);
		return builder.toString();
	}
}
