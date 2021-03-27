package com.ssb.onlinelibrary.service.impl;

import com.ssb.onlinelibrary.dao.UserDao;
import com.ssb.onlinelibrary.model.ImageModel;
import com.ssb.onlinelibrary.model.Librarian;
import com.ssb.onlinelibrary.model.Request;
import com.ssb.onlinelibrary.model.User;
import com.ssb.onlinelibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    public User createUser(User user) {
        return userDao.createUser(user);
    }

    @Override
    public Request updateStatus(Request request) {
        return userDao.updateStatus(request);
    }

    @Override
    public ImageModel saveImg(ImageModel img) {
        return userDao.saveImg(img);
    }

    @Override
    public User getName(User user) {
        return userDao.getName(user.getName());
    }

    @Override
    public ImageModel getImage(String imageName) {
        return userDao.getImage(imageName);
    }

    @Override
    public boolean name(User user) {
        return userDao.name(user);
    }

    @Override
    public boolean emailId(User user) {
        return userDao.emailId(user);
    }

    @Override
    public boolean contactNumberVerification(User user) {
        return userDao.contactNumberVerification(user);
    }

    @Override
    public boolean passwordVerify(User user){
        if(user.getPassword().equals(user.getConfirm_password())){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<User> viewUser() {
        return userDao.viewUser();

    }

    @Override
    public List<Request> getRequestList(String username) {
        return userDao.requestList(username);
    }
    @Override
    public void passwordReset(User user) {
        userDao.passwordReset(user);

    }

    @Override
    public User getContactNumber(String contact_number) {
        return userDao.getContactNumber(contact_number);
    }

    @Override
    public void disableAccount(User user) {
        userDao.createUser(user);

    }

    @Override
    public User getUserId(int id) {
        return userDao.getUserId(id);
    }

    @Override
    public Librarian getLibrarianId(int id) {
        return userDao.getLibrarianId(id);
    }

    @Override
    public Request getRequestId(int id) {
        return userDao.getRequestId(id);
    }

    @Override
    public User getUserEmail(String email_id) {
        return userDao.getUserEmail(email_id);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public User getUserByUsername(User user) {
        User userObj = userDao.getUserByUsername(user.getName());
        if(userObj.getPassword().equals(user.getPassword()) && userObj.getName().equals(user.getName())){
            return userDao.getUserByUsername(user.getName());
        }
        return null;
    }

    @Override
    public Librarian getLibrarianByname(Librarian librarian) {
        Librarian librarianObj = userDao.getLibrarianName(librarian.getName());
        if(librarianObj.getPassword().equals(librarian.getPassword()) && librarianObj.getName().equals(librarian.getName())){
            return userDao.getLibrarianName(librarian.getName());
        }
        return null;
    }

    @Override
    public Librarian createLibrarian(Librarian librarian) {
        return userDao.createLibrarian(librarian);
    }

    @Override
    public boolean libraryNameVerify(Librarian librarian) {
        return userDao.libraryNameVerify(librarian);
    }

    @Override
    public boolean librarianEmailId(Librarian librarian) {
        return userDao.librarianEmailId(librarian);
    }

    @Override
    public boolean librarianContactNumberVerification(Librarian librarian) {
        return userDao.librarianContactNumberVerification(librarian);
    }
    @Override
    public List<User> viewLibrarian() {
        return userDao.viewLibrarian();
    }

    @Override
    public boolean librarianName(Librarian librarian) {
        return userDao.librarianName(librarian);
    }

//    @Override
//    public boolean librarianPasswordVerify(Librarian librarian) {
//        return false;
//    }
}
