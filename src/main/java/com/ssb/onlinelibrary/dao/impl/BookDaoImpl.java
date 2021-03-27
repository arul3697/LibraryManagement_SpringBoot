package com.ssb.onlinelibrary.dao.impl;

import com.ssb.onlinelibrary.dao.BookDao;
import com.ssb.onlinelibrary.model.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssb.onlinelibrary.util.HibernateUtil.createSession;

@Repository
public class BookDaoImpl implements BookDao {

	@Autowired
	private CommonDaoImpl commonDao;
	/**
	 * save book
	 * 
	 * @param book object
	 * @return boolean
	 * @throws Exception
	 */
	public Book add(Book book) {
		Session session = createSession();
		commonDao.saveOrUpdate(book);
		return book;
	}

	@Override
	public FavouriteBook addFavouriteBook(FavouriteBook favouriteBook) {
		Session session = createSession();
		commonDao.saveOrUpdate(favouriteBook);
		return favouriteBook;
	}

	@Override
	public List<FavouriteBook> viewFavouriteBookList(String uname) {
		Session session = createSession();
		Criteria criteria = session.createCriteria(FavouriteBook.class).add(Restrictions.eq("uname", uname));
		List<FavouriteBook> favouriteBook = criteria.list();
		return favouriteBook;
	}

	@Override
	public FavouriteBook deleteFavouriteBook(FavouriteBook favouriteBook) {
		commonDao.delete(favouriteBook);
		return favouriteBook;
	}
	/**
	 * retrieve book
	 * 
	 * @return bookList
	 * @throws Exception
	 */
	public List<Book> viewBook() {
		Session session = createSession();
			Criteria criteria = session.createCriteria(Book.class).add(Restrictions.eq("account_enabled", true));
			List<Book> book = criteria.list();
			return book;
	}

	/**
	 * @param book
	 * @return boolean
	 * @throws Exception
	 */
	public void disableBook(Book book) {
		Session session = createSession();
		commonDao.saveOrUpdate(book);
	}

	public Book getBookId(int id) {
		Session session = createSession();
		Book book = new Book();
			Criteria criteria = session.createCriteria(Book.class).add(Restrictions.eq("id", id));
			book = (Book) criteria.uniqueResult();
		return book;
	}
	@Override
	public FavouriteBook getFavouriteBook(int id) {
		Session session = createSession();
		FavouriteBook favouriteBook = new FavouriteBook();
		Criteria criteria = session.createCriteria(FavouriteBook.class).add(Restrictions.eq("id", id));
		favouriteBook = (FavouriteBook) criteria.uniqueResult();
		return favouriteBook;
	}

	/**
	 * <p>
	 * Admin updates the book
	 * </p>
	 */
	public void updateBook(Book book) {
		Session session =createSession();
		commonDao.saveOrUpdate(book);
	}

	@Override
	public Book getFileName(String fileName) {
		Session session = createSession();
		Book book = new Book();
		Criteria criteria = session.createCriteria(Book.class).add(Restrictions.eq("fileName", fileName));
		book = (Book) criteria.uniqueResult();
		return book;
	}

	@Override
	public Request addRequest(Request request) {
		Session session = createSession();
		commonDao.saveOrUpdate(request);
		return request;
	}

	@Override
	public List<Request> viewRequest() {
		Session session = createSession();
		Criteria criteria = session.createCriteria(Request.class);
		List<Request> request = criteria.list();
		return request;
	}

	@Override
	public LibrarianRequest addLibrarianRequest(LibrarianRequest librequest) {
		Session session = createSession();
		commonDao.saveOrUpdate(librequest);
		return librequest;
	}

	@Override
	public List<LibrarianRequest> viewLibrarianRequest() {
		Session session = createSession();
		Criteria criteria = session.createCriteria(LibrarianRequest.class);
		List<LibrarianRequest> librarianRequest = criteria.list();
		return librarianRequest;
	}

	@Override
	public AdminReply addAdminReply(AdminReply reply) {
		Session session = createSession();
		commonDao.saveOrUpdate(reply);
		return reply;
	}

	@Override
	public List<AdminReply> viewAdminReply() {
		Session session = createSession();
		Criteria criteria = session.createCriteria(AdminReply.class);
		List<AdminReply> adminReply = criteria.list();
		return adminReply;
	}

	@Override
	public Notification addNotification(Notification notification) {
		Session session = createSession();
		commonDao.saveOrUpdate(notification);
		return notification;
	}

	@Override
	public List<Notification> viewNotification() {
		Session session = createSession();
		Criteria criteria = session.createCriteria(Notification.class);
		List<Notification> notification = criteria.list();
		return notification;
	}
}