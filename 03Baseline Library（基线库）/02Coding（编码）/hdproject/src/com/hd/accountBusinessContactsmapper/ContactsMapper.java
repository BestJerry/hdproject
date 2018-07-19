package com.hd.accountBusinessContactsmapper;

import java.util.List;
import com.hd.beans.Contacts;

public interface ContactsMapper {
	/**
	  * ������ϵ��
	  * @param contacts
	  * @return
	  * throws Exception
	  */
    public int insertContacts(Contacts contacts) throws Exception;
    
    /**
     * �޸���ϵ��
     * @param contacts
     * @param con_id
     * @return
     * @throws Exception
     * 
     */
    public int updateContacts(Contacts contacts,int con_id) throws Exception;
    
    /**
     * ɾ����ϵ��
     * @param contacts
     * @param con_id
     * @return 
     * @throws Exception
     */
    public int deleteContacts(int con_id)throws Exception;
    
    /**
     * ����con_id��ѯ��ϵ����Ϣ
     * @param con_id
     * @return
     * @throws Exception
     */
    public Contacts selectContactsById(int con_id)throws Exception;
    
    /**
     * ��ѯ���е���ϵ����Ϣ
     * 
     */
    public List<Contacts> selectAllContacts() throws Exception;
}
