package com.ssb.onlinelibrary.dao;


import com.ssb.onlinelibrary.model.*;

import java.util.List;



public interface BookDao {
	List<Book> viewBook();

	void disableBook(Book book);

	Book add(Book book);

	Book getBookId(int id);

	void updateBook(Book book);

	Book getFileName(String fileName);

    Request addRequest(Request request);

	List<Request> viewRequest();

    LibrarianRequest addLibrarianRequest(LibrarianRequest librequest);

	List<LibrarianRequest> viewLibrarianRequest();

	AdminReply addAdminReply(AdminReply reply);

	List<AdminReply> viewAdminReply();

	Notification addNotification(Notification notification);

	List<Notification> viewNotification();

	FavouriteBook addFavouriteBook(FavouriteBook favouriteBook);

	List<FavouriteBook> viewFavouriteBookList(String uname);

	FavouriteBook deleteFavouriteBook(FavouriteBook favouriteBook);

	FavouriteBook getFavouriteBook(int id);
}
