package com.ssb.onlinelibrary.service;

import com.ssb.onlinelibrary.model.*;

import java.util.List;

public interface BookService {
	Book add(Book book);

	FavouriteBook addFavouriteBook(FavouriteBook favouriteBook);

	Request addRequest(Request request);

	AdminReply addAdminReply(AdminReply reply);

	Notification addNotification(Notification notification);

	List<Book> viewBook();

	List<FavouriteBook> viewFavouriteBookList(String uname);

	List<Request> viewRequest();

	List<AdminReply> viewAdminReply();

	List<LibrarianRequest> viewLibrarianRequest();

	List<Notification> viewNotification();

	void disableBook(Book book);

	Book getBookId(int id);

	FavouriteBook getFavouriteBook(int id);

	FavouriteBook deleteFavouriteBook(FavouriteBook favouriteBook);

	void updateBook(Book book);

	Book getFileName(String fileName);

    LibrarianRequest addLibrarianRequest(LibrarianRequest librequest);

}
