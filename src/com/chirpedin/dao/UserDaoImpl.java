/**
 * 
 */
package com.chirpedin.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.chirpedin.dto.UserDto;

/**
 * @author Manu
 *
 */
public class UserDaoImpl implements UsersDao {

	/*
	 * (non-Javadoc)
	 * 
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

	/*****
	 *
	 * needed to create updateUser for updating UserDto.favorites as a String)
	 *
	 **********/

	@Override
	public void updateUser(UserDto updatedUser) {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(updatedUser);
		tx.commit();
		session.close();
	}

	/*
	 * (non-Javadoc)
	 * 
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

		ArrayList<UserDto> list = (ArrayList<UserDto>) crit.list();
		tx.commit();

		session.close();

		return list;

	}

	@Override
	public List<UserDto> findMentor(UserDto userCriteriaDto) {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		Criteria crit = session.createCriteria(UserDto.class);
		Criterion Php = Restrictions.like("menteeSkillsPhp", userCriteriaDto.getMentorSkillsPhp());
		Criterion Java = Restrictions.like("menteeSkillsJava", userCriteriaDto.getMentorSkillsJava());
		Criterion Css = Restrictions.like("menteeSkillsCSS", userCriteriaDto.getMentorSkillsCSS());
		Criterion Html = Restrictions.like("menteeSkillsHTML", userCriteriaDto.getMentorSkillsHTML());
		Criterion Jsp = Restrictions.like("menteeSkillsJsp", userCriteriaDto.getMentorSkillsJsp());
		Criterion Jstl = Restrictions.like("menteeSkillsJstl", userCriteriaDto.getMentorSkillsJstl());
		Criterion Sql = Restrictions.like("menteeSkillsSql", userCriteriaDto.getMentorSkillsSql());
		Criterion Jdbc = Restrictions.like("menteeSkillsJdbc", userCriteriaDto.getMentorSkillsJdbc());
		Criterion SpringMvc = Restrictions.like("menteeSkillsSpringMVC", userCriteriaDto.getMentorSkillsSpringMVC());
		Criterion Hibernate = Restrictions.like("menteeSkillsHibernate", userCriteriaDto.getMentorSkillsHibernate());
		Criterion JavaScript = Restrictions.like("menteeSkillsJavaScript", userCriteriaDto.getMentorSkillsJavaScript());

		// To get records matching with OR conditions
		Disjunction orExp = Restrictions.or(Php, Java, Css, Html, Jsp, Jstl, Sql, Jdbc, SpringMvc, Hibernate,
				JavaScript);
		crit.add(orExp);

		ArrayList<UserDto> list = (ArrayList<UserDto>) crit.list();

		tx.commit();
		session.close();

		return list;
	}

	@Override
	public List<UserDto> findMentee(UserDto userCriteriaDto) {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		Criteria crit = session.createCriteria(UserDto.class);
		Criterion Php = Restrictions.like("mentorSkillsPhp", userCriteriaDto.getMenteeSkillsPhp());
		Criterion Java = Restrictions.like("mentorSkillsJava", userCriteriaDto.getMenteeSkillsJava());
		Criterion Css = Restrictions.like("mentorSkillsCSS", userCriteriaDto.getMenteeSkillsCSS());
		Criterion Html = Restrictions.like("mentorSkillsHTML", userCriteriaDto.getMenteeSkillsHTML());
		Criterion Jsp = Restrictions.like("mentorSkillsJsp", userCriteriaDto.getMenteeSkillsJsp());
		Criterion Jstl = Restrictions.like("mentorSkillsJstl", userCriteriaDto.getMenteeSkillsJstl());
		Criterion Sql = Restrictions.like("mentorSkillsSql", userCriteriaDto.getMenteeSkillsSql());
		Criterion Jdbc = Restrictions.like("mentorSkillsJdbc", userCriteriaDto.getMenteeSkillsJdbc());
		Criterion SpringMvc = Restrictions.like("mentorSkillsSpringMVC", userCriteriaDto.getMenteeSkillsSpringMVC());
		Criterion Hibernate = Restrictions.like("mentorSkillsHibernate", userCriteriaDto.getMenteeSkillsHibernate());
		Criterion JavaScript = Restrictions.like("mentorSkillsJavaScript", userCriteriaDto.getMenteeSkillsJavaScript());

		// To get records matching with OR conditions
		Disjunction orExp = Restrictions.or(Php, Java, Css, Html, Jsp, Jstl, Sql, Jdbc, SpringMvc, Hibernate,
				JavaScript);
		crit.add(orExp);

		ArrayList<UserDto> list = (ArrayList<UserDto>) crit.list();

		tx.commit();
		session.close();

		return list;
	}

	@Override
	public List<UserDto> matchNetworking(UserDto userCriteriaDto) {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		Criteria crit = session.createCriteria(UserDto.class);
		Criterion Php = Restrictions.like("networkingSkillsPhp", userCriteriaDto.getNetworkingSkillsPhp());
		Criterion Java = Restrictions.like("networkingSkillsJava", userCriteriaDto.getNetworkingSkillsJava());
		Criterion Css = Restrictions.like("networkingSkillsCSS", userCriteriaDto.getNetworkingSkillsCSS());
		Criterion Html = Restrictions.like("networkingSkillsHTML", userCriteriaDto.getNetworkingSkillsHTML());
		Criterion Jsp = Restrictions.like("networkingSkillsJsp", userCriteriaDto.getNetworkingSkillsJsp());
		Criterion Jstl = Restrictions.like("networkingSkillsJstl", userCriteriaDto.getNetworkingSkillsJstl());
		Criterion Sql = Restrictions.like("networkingSkillsSql", userCriteriaDto.getNetworkingSkillsSql());
		Criterion Jdbc = Restrictions.like("networkingSkillsJdbc", userCriteriaDto.getNetworkingSkillsJdbc());
		Criterion SpringMvc = Restrictions.like("networkingSkillsSpringMVC", userCriteriaDto.getNetworkingSkillsSpringMVC());
		Criterion Hibernate = Restrictions.like("networkingSkillsHibernate", userCriteriaDto.getNetworkingSkillsHibernate());
		Criterion JavaScript = Restrictions.like("networkingSkillsJavaScript", userCriteriaDto.getNetworkingSkillsJavaScript());
		Criterion Foodie = Restrictions.like("networkingFoodie", userCriteriaDto.getNetworkingFoodie());
		Criterion Gaming = Restrictions.like("networkingGaming", userCriteriaDto.getNetworkingGaming());
		Criterion Sports = Restrictions.like("networkingSports", userCriteriaDto.getNetworkingSports());
		Criterion Anime = Restrictions.like("networkingAnime", userCriteriaDto.getNetworkingAnime());
		Criterion Fun = Restrictions.like("networkingFun", userCriteriaDto.getNetworkingFun());
		
		
		// To get records matching with OR conditions
		Disjunction orExp = Restrictions.or(Php, Java, Css, Html, Jsp, Jstl, Sql, Jdbc, SpringMvc, Hibernate,
				JavaScript, Foodie, Gaming, Sports, Anime, Fun);
		crit.add(orExp);

		ArrayList<UserDto> list = (ArrayList<UserDto>) crit.list();

		tx.commit();
		session.close();

		return list;
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

	@Override
	public List<UserDto> matchMentor(UserDto newUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> matchMentor(UserDto newUser, Model model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> matchMentee(UserDto newUser) {
		// TODO Auto-generated method stub
		return null;
	}

}
