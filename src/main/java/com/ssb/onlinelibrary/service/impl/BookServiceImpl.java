package com.ssb.onlinelibrary.service.impl;

import com.ssb.onlinelibrary.dao.BookDao;
import com.ssb.onlinelibrary.model.*;
import com.ssb.onlinelibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDao bookDao;

	/**
	 * add book in list
	 * 
	 * @param book object
	 * @return
	 * @throws Exception
	 */
	public Book add(Book book) {
		return bookDao.add(book);
	}

	@Override
	public FavouriteBook addFavouriteBook(FavouriteBook favouriteBook) {
		return bookDao.addFavouriteBook(favouriteBook);
	}

	@Override
	public Request addRequest(Request request) {
		return bookDao.addRequest(request);
	}

	@Override
	public AdminReply addAdminReply(AdminReply reply) {
		return bookDao.addAdminReply(reply);
	}

	@Override
	public Notification addNotification(Notification notification) {
		return bookDao.addNotification(notification);
	}

	/**
	 * List of Book
	 * 
	 * @return boolean value
	 * @throws Exception
	 */
	public List<Book> viewBook() {
		return bookDao.viewBook();
	}

	@Override
	public List<FavouriteBook> viewFavouriteBookList(String uname) {
		return bookDao.viewFavouriteBookList(uname);
	}

	public List<Request> viewRequest() {
		return bookDao.viewRequest();
	}

	@Override
	public List<AdminReply> viewAdminReply() {
		return bookDao.viewAdminReply();
	}

	@Override
	public List<LibrarianRequest> viewLibrarianRequest() {
		return bookDao.viewLibrarianRequest();
	}

	@Override
	public List<Notification> viewNotification() {
		return bookDao.viewNotification();
	}

	/**
	 * delete book
	 *
	 * @return boolean value
	 * @throws Exception
	 */
	public void disableBook(Book book) {
		bookDao.disableBook(book);
	}

	@Override
	public Book getBookId(int id) {
		return bookDao.getBookId(id);
	}

	@Override
	public FavouriteBook getFavouriteBook(int id) {
		return bookDao.getFavouriteBook(id);
	}

	@Override
	public FavouriteBook deleteFavouriteBook(FavouriteBook favouriteBook) {
		return bookDao.deleteFavouriteBook(favouriteBook);
	}

	@Override
	public void updateBook(Book book) {
		bookDao.updateBook(book);

	}

	@Override
	public Book getFileName(String fileName) {
		return bookDao.getFileName(fileName);
	}

	@Override
	public LibrarianRequest addLibrarianRequest(LibrarianRequest librequest) {
		return bookDao.addLibrarianRequest(librequest);
	}
}
