package org.tbs.tst.VO;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;
import org.tbs.entity.Bdf2User;

import com.bstek.bdf2.core.orm.hibernate.HibernateDao;


public class Bdf2UserDao_tst extends HibernateDao {

    public Collection<Bdf2User> getCnameById(int keyin_id) {
	List<Bdf2User> bdf2Users = this.query("from Bdf2User where id =" + keyin_id);
	return bdf2Users;
    }
}
