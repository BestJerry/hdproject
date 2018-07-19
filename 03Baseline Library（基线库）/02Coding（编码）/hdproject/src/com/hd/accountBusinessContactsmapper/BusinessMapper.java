package com.hd.accountBusinessContactsmapper;

import java.util.List;
import com.hd.beans.Business;

public interface BusinessMapper {
	/**
	  * �����̼�
	  * @param business
	  * @return
	  * throws Exception
	  */
     public int insertBusiness(Business business) throws Exception;
     
     /**
      * �޸��̼�
      * @param business
      * @param bus_id
      * @return
      * @throws Exception
      * 
      */
     public int updateBusiness(Business business,int bus_id) throws Exception;
     
     /**
      * ɾ���̼�
      * @param business
      * @param bus_id
      * @return 
      * @throws Exception
      */
     public int deleteBusiness(int bus_id)throws Exception;
     
     /**
      * ����bus_id��ѯ�̼���Ϣ
      * @param bus_id
      * @return
      * @throws Exception
      */
     public Business selectBusinessById(int bus_id)throws Exception;
     
     /**
      * ��ѯ���е��̼���Ϣ
      * 
      */
     public List<Business> selectAllBusiness() throws Exception;
}
