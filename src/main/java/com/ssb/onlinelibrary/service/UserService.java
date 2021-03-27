package com.ssb.onlinelibrary.service;

import com.ssb.onlinelibrary.model.ImageModel;
import com.ssb.onlinelibrary.model.Librarian;
import com.ssb.onlinelibrary.model.Request;
import com.ssb.onlinelibrary.model.User;

import java.util.List;

public interface UserService {
	User createUser(User user);

	Librarian createLibrarian(Librarian librarian);

	Request updateStatus(Request request);

	ImageModel saveImg(ImageModel img);

	List<User> viewUser();

	List<User> viewLibrarian();

	List<Request> getRequestList(String username);
	
	User getName(User user);

	Request getRequestId(int id);

	ImageModel getImage(String imageName);

	boolean name(User user);

	boolean libraryNameVerify(Librarian librarian);


	boolean emailId(User user);

	boolean contactNumberVerification(User user);

	boolean passwordVerify(User user);

	void passwordReset(User user);

	User getContactNumber(String  contact_number);

	void disableAccount(User user);

	User getUserId(int id);

	Librarian getLibrarianId(int id);
	
	User getUserEmail(String email_id);

	void updateUser(User user);

	User getUserByUsername(User user);

	Librarian getLibrarianByname(Librarian librarian);

	boolean librarianEmailId(Librarian librarian);

	boolean librarianContactNumberVerification(Librarian librarian);

	boolean librarianName(Librarian librarian);

	//boolean librarianPasswordVerify(Librarian librarian);
}
