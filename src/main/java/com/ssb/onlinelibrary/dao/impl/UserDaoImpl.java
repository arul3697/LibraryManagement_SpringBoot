package com.ssb.onlinelibrary.dao.impl;


import com.ssb.onlinelibrary.dao.UserDao;
import com.ssb.onlinelibrary.model.ImageModel;
import com.ssb.onlinelibrary.model.Librarian;
import com.ssb.onlinelibrary.model.Request;
import com.ssb.onlinelibrary.model.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssb.onlinelibrary.util.HibernateUtil.createSession;


@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private CommonDaoImpl commonDao;
	// save users
	public User createUser(User user) {
		commonDao.saveOrUpdate(user);
		return user;
	}

	@Override
	public Librarian createLibrarian(Librarian librarian) {
		commonDao.saveOrUpdate(librarian);
		return librarian;
	}

	@Override
	public Request updateStatus(Request request) {
		commonDao.saveOrUpdate(request);
		return request;
	}

	@Override
	public ImageModel saveImg(ImageModel img) {
		commonDao.saveOrUpdate(img);
		return img;
	}

	@Override
	public ImageModel getImage(String imageName) {
		Session session = createSession();
		Criteria criteria = session.createCriteria(ImageModel.class).add(Restrictions.eq("name", imageName));
		ImageModel retrievedImage = (ImageModel) criteria.uniqueResult();
		return retrievedImage;
	}

	/**
	 * retrieve user
	 * 
	 * @return user
	 * @throws Exception
	 */
	public List<User> viewUser() {
		Session session = createSession();
			Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("account_enabled", true));
		    List<User> user = criteria.list();
			return user;
	}

	@Override
	public List<User> viewLibrarian() {
		Session session = createSession();
		Criteria criteria = session.createCriteria(Librarian.class);
		//criteria.add(Restrictions.eq("role_Id()", 2));
		criteria.add(Restrictions.eq("account_enabled", true));
		List<User> user = criteria.list();
		return user;
	}

	@Override
	public List<Request> requestList(String username) {
		Session session = createSession();
		Criteria criteria = session.createCriteria(Request.class).add(Restrictions.eq("username", username));
		List<Request> requests = criteria.list();
		return requests;
	}

	/**
	 * @param user
	 * @return boolean
	 * @throws Exception
	 */
	public void disableAccount(User user) {
		commonDao.saveOrUpdate(user);
	}

	public User userLogin(User user) {
		Session session = createSession();
			Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("name", user.getName()))
					.add(Restrictions.eq("account_enabled", true));
			user = (User) criteria.uniqueResult();
			return user;
	}
	
	/**
	 * <p>
	 * checking for EmailId already exists or not
	 * </p>
	 */
	public User getName(String name) {
		Session session = createSession();
			Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("name", name));
			User user = (User) criteria.uniqueResult();
			return user;
	}

	public boolean name(User user) {
		Session session = createSession();
		Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("name", user.getName()));
		user = (User) criteria.uniqueResult();
		if (user != null) {
			return false;
		}
		return true;
	}

	public boolean contactNumberVerification(User user) {
		Session session = createSession();
		Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("contact_number", user.getContact_number()));
		user = (User) criteria.uniqueResult();
		if (user != null) {
			return false;
		}
		return true;
	}

	/**
	 * <p>
	 * checking for EmailId already exists or not
	 * </p>
	 */
	public boolean emailId(User user) {
		Session session = createSession();
			Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("email_id", user.getEmail_id()));
			user = (User) criteria.uniqueResult();
			if (user != null) {
				return false;
			}
			return true;
	}

	/**
	 * <p>
	 * Checks the mobile already exists or not
	 * </p>
	 */
	public User getContactNumber(String contact_number) {
		Session session = createSession();
		User user = new User();
			Criteria criteria = session.createCriteria(User.class)
					.add(Restrictions.eq("contact_number",contact_number));
			user = (User) criteria.uniqueResult();
		return user;
	}

	public void passwordReset(User user) {
		Session session = createSession();
		commonDao.saveOrUpdate(user);
	}

	/**
	 * <p>
	 * Admin gets the User details by Id
	 * </p>
	 */
	@Override
	public User getUserId(int id) {
		Session session = createSession();
		User user = new User();
			Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("id", id));
			user = (User) criteria.uniqueResult();
		return user;
	}

	@Override
	public Librarian getLibrarianId(int id) {
		Session session = createSession();
		Librarian librarian = new Librarian();
		Criteria criteria = session.createCriteria(Librarian.class).add(Restrictions.eq("id", id));
		librarian = (Librarian) criteria.uniqueResult();
		return librarian;
	}
	@Override
	public boolean libraryNameVerify(Librarian librarian) {
		Session session = createSession();
		Criteria criteria = session.createCriteria(Librarian.class).add(Restrictions.eq("libraryName", librarian.getLibraryName()));
		librarian = (Librarian) criteria.uniqueResult();
		if (librarian != null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean librarianEmailId(Librarian librarian) {
		Session session = createSession();
		Criteria criteria = session.createCriteria(Librarian.class).add(Restrictions.eq("email_id", librarian.getEmail_id()));
		librarian = (Librarian) criteria.uniqueResult();
		if (librarian != null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean librarianContactNumberVerification(Librarian librarian) {
		Session session = createSession();
		Criteria criteria = session.createCriteria(Librarian.class).add(Restrictions.eq("contact_number", librarian.getContact_number()));
		librarian = (Librarian) criteria.uniqueResult();
		if (librarian != null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean librarianName(Librarian librarian) {
		Session session = createSession();
		Criteria criteria = session.createCriteria(Librarian.class).add(Restrictions.eq("name", librarian.getName()));
		librarian = (Librarian) criteria.uniqueResult();
		if (librarian != null) {
			return false;
		}
		return true;
	}

	@Override
	public Librarian getLibrarianName(String name) {
		Session session = createSession();
		Criteria criteria = session.createCriteria(Librarian.class).add(Restrictions.eq("name", name));
		Librarian librarian = (Librarian) criteria.uniqueResult();
		return librarian;
	}

	@Override
	public Request getRequestId(int id) {
		Session session = createSession();
		Request request = new Request();
		Criteria criteria = session.createCriteria(Request.class).add(Restrictions.eq("id", id));
		request = (Request) criteria.uniqueResult();
		return request;
	}

	public User getUserEmail(String email_id) {
		Session session = createSession();
		User user = new User();
			Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("email_id", email_id));
			user = (User) criteria.uniqueResult();
		return user;
	}

	/**
	 * <p>
	 * Admin updates the user
	 * </p>
	 */
	public void updateUser(User user) {
		Session session = createSession();
		commonDao.saveOrUpdate(user);
	}
	/**
	 * getting user details from user table by using username and password
	 *
	 * @param name
	 * @return user
	 */
	public User getUserByUsername(String name) {
		Session session = createSession();
		Criteria criteria = session.createCriteria(User.class)
				.add(Restrictions.eq("name", name))
		        .add(Restrictions.eq("account_enabled", true));
		User user = (User) criteria.uniqueResult();
		session.close();
		return user;
	}
//	public String getUserRow_Count() {
//		Session session = createSession();
//		Query q=session.createQuery("select count(*) from user where account_enabled = true");
//		System.out.println("-------count---------"+q);
//		return q;
//	}
}
