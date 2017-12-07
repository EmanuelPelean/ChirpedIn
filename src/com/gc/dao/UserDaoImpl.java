/**
 * 
 */
package com.gc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.gc.dto.UserDto;

/**
 * @author Manu
 *
 */
public class UserDaoImpl implements UsersDao {

	/* (non-Javadoc)
	 * @see com.gc.dao.UsersDao#insertUser(com.gc.dto.UserDto)
	 */
	@Override
	public void insertUser(UserDto newUser) {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(newUser);
		tx.commit();
		session.close();
	}

	/* (non-Javadoc)
	 * @see com.gc.dao.UsersDao#getMatches(com.gc.dto.UserDto)
	 */
	@Override
	public List<UserDto> getMatches(UserDto newUser, Model model) {
		System.out.println("Running getMatches method");
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		
		Criteria crit = session.createCriteria(UserDto.class);
		crit.add(Restrictions.like("menteeSkillsPhp", true));
		
//		CriteriaBuilder builder = session.getCriteriaBuilder();
//		CriteriaQuery criteria = (CriteriaQuery) builder.createQuery(UserDto.class);
		
		ArrayList<UserDto> list = (ArrayList<UserDto>)crit.list();
		tx.commit();
				
		session.close();
//		new ModelAndView("matches", "matchresults", list);
		String test = "Tim is a genius";
		model.addAttribute("matchresults", list);
		return list;
		
	}

	@Override
	public List<UserDto> matchMentor(UserDto newUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> matchMentee(UserDto newUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> matchNetworking(UserDto newUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> getChirped(UserDto newUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void chirp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveAsFav(String userID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String deleteFav(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> getFavs(UserDto NewUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> getMatches(UserDto newUser) {
		// TODO Auto-generated method stub
		return null;
	}

}
