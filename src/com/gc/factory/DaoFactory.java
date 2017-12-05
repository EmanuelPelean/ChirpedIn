/**
 * 
 */
package com.gc.factory;

import com.gc.dao.UserDaoImpl;
import com.gc.dao.UsersDao;

/**
 * @author Manu
 *
 */
public class DaoFactory {

	public static final String USERSDAO = "userdao";

	public static UsersDao getInstance(String type) {
		UserDaoImpl daoimpl = null;
		switch (type) {
		case USERSDAO: 
			daoimpl = new UserDaoImpl();
			break;
		default:
			break;
		}
		return daoimpl;
	}

}

