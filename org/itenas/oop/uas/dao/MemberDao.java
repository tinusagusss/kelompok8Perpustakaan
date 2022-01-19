package org.itenas.oop.uas.dao;

import org.itenas.oop.uas.entity.Member;

public interface MemberDao {

    public Member getMemberByNip(int nip);
    public void getMemberByNama(String nama);
    public void getMemberById(String id);

}
