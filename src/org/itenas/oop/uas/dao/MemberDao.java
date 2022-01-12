package org.itenas.oop.uas.dao;

import java.util.List;

import org.itenas.oop.uas.actor.Member;

public interface MemberDao {

    public Member getMemberByNip(int nip);
    public void getMemberByNama(String nama);
    public void getMemberById(String id);

}
