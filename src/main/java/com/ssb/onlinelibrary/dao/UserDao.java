package com.ssb.onlinelibrary.dao;

import com.ssb.onlinelibrary.model.ImageModel;
import com.ssb.onlinelibrary.model.Librarian;
import com.ssb.onlinelibrary.model.Request;
import com.ssb.onlinelibrary.model.User;

import java.util.List;


public interface UserDao {
	User createUser(User user);

	User userLogin(User user);

	List<User> viewUser();

	List<User> viewLibrarian();
	
	User getName(String name);

	boolean name(User user);

	boolean emailId(User user);

	boolean contactNumberVerification(User user);

	void passwordReset(User user);

	User getContactNumber(String contact_number);

	void disableAccount(User user);

	User getUserId(int id);
	
	User getUserEmail(String email_id);

	void updateUser(User user);

	User getUserByUsername(String name);

	ImageModel saveImg(ImageModel img);

	ImageModel getImage(String imageName);

    Request getRequestId(int id);

	Request updateStatus(Request request);

	List<Request> requestList(String username);

    boolean libraryNameVerify(Librarian librarian);

	Librarian createLibrarian(Librarian librarian);

	Librarian getLibrarianId(int id);

	boolean librarianEmailId(Librarian librarian);

	boolean librarianContactNumberVerification(Librarian librarian);

	boolean librarianName(Librarian librarian);

    Librarian getLibrarianName(String name);
}