package org.itenas.oop.uas.entity;

import java.sql.Date;

public class Member extends Person{

	public Date memberExp;
	
	public void setMemberExp(Date memberExp) {
		this.memberExp = memberExp;
	}

	public Date getMemberExp() {
		return memberExp;
	}
	
}
