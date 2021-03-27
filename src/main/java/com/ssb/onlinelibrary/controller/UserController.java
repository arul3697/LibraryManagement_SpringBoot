package com.ssb.onlinelibrary.controller;


import com.ssb.onlinelibrary.model.*;
import com.ssb.onlinelibrary.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value="/api")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/addOrUpdateUser")
    public User addOrUpdateUser(@RequestBody User user,ModelMap modelMap) {
        try {
            System.out.println("--------User id-----------" + user.getId());
            if (user.getId() == 0) {
                Role role = new Role();
                role.setId(3);
                user.setRole(role);
                user.setAccount_enabled(true);

//                boolean isverificationEmail = userService.emailId(user);
//
//                if (!isverificationEmail) {
//                    throw new  Exception ("Email is Already taken, Try another..!! ");
//                }
//                boolean isVerification = userService.contactNumberVerification(user);
//                if (!isVerification) {
//                    throw new  Exception ("Contact number is Already taken, Try another..!! ");
//                }
//                boolean isverificationName = userService.name(user);
//                if (!isverificationName) {
//                    throw new  Exception (" User name is Already taken, Try another..!! ");
//                }
//                boolean isverificationPassword = userService.passwordVerify(user);
//                if (!isverificationPassword) {
//                    throw new Exception("Both password mismatched...!!");
//                }
                return userService.createUser(user);

            } else {
                Role role = new Role();
                role.setId(3);
                user.setRole(role);
                user.setAccount_enabled(true);

                return userService.createUser(user);
            }
        } catch (Exception exception) {
            System.out.println(exception);
//            return "adduser";
        }
        return user;
//        return "redirect:/user/viewUser";
    }
    @PostMapping("/addOrUpdateLibrarian")
    public Librarian addOrUpdateLibrarian(@RequestBody Librarian librarian) {
        try {
            System.out.println("--------User id-----------" + librarian.getId());

            if (librarian.getId() == 0) {
                Role role = new Role();
                role.setId(2);
                librarian.setRole(role);
                librarian.setAccount_enabled(true);

                return userService.createLibrarian(librarian);

            } else {
                Role role = new Role();
                role.setId(2);
                librarian.setRole(role);
                librarian.setAccount_enabled(true);

                return userService.createLibrarian(librarian);
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return librarian;
    }

    @GetMapping("/viewUser")
    public List<User> viewUsers() {
        return userService.viewUser();
    }

    @GetMapping("/viewLibrarian")
    public List<User> viewLibrarians() {
        return userService.viewLibrarian();
    }

    @GetMapping("/requestList/{username}")
    public List<Request> requestList(@PathVariable String username, Request request) {
        return userService.getRequestList(username);
    }

    @DeleteMapping("/deleteUser/{id}")
    public User deleteUser(@PathVariable int id, User user) {
        // user.setId(id);
        user = userService.getUserId(id);
        user.setAccount_enabled(false);
        return userService.createUser(user);
    }

    @DeleteMapping("/deleteLibrarian/{id}")
    public Librarian deleteLibrarian(@PathVariable int id, Librarian librarian) {
        librarian = userService.getLibrarianId(id);
        librarian.setAccount_enabled(false);
        return userService.createLibrarian(librarian);
    }

    @GetMapping("/user/{id}")
    public User allUserByID(@PathVariable int id, User user) {
        return userService.getUserId(id);
    }

    @GetMapping("/librarian/{id}")
    public Librarian allLibrarianByID(@PathVariable int id, Librarian librarian) {
        return userService.getLibrarianId(id);
    }

    @GetMapping("/updateStatus/{id}")
    public Request updateStatus(@PathVariable int id, Request request) {
        request =  userService.getRequestId(id);
        String status = "Accepted";
        request.setStatus(status);
        return  userService.updateStatus(request);
    }
    @GetMapping("/rejectStatus/{id}")
    public Request rejectStatus (@PathVariable int id, Request request) {
        request =  userService.getRequestId(id);
        String status = "Rejected";
        request.setStatus(status);
        return  userService.updateStatus(request);
    }

    @PostMapping("/updateUser/{id}")
    public User updateUser(@RequestBody User user, @PathVariable int id) {
        System.out.println("----name----" + user.getName());
        System.out.println("----pass----" + user.getPassword());
        System.out.println("----name----" + user.getConfirm_password());
        System.out.println("----name----" + user.getAddress());
        System.out.println("----name----" + user.getContact_number());
        System.out.println("----name----" + user.getFull_name());
        System.out.println("----name----" + user.getId());
        Role role = new Role();
        role.setId(3);
        user.setRole(role);
        user.setAccount_enabled(true);

        return userService.createUser(user);
    }

    @PostMapping("/updateLibrarian/{id}")
    public Librarian updateLibrarian(@RequestBody Librarian librarian, @PathVariable int id) {
        System.out.println("----name----" + librarian.getName());
        System.out.println("----pass----" + librarian.getPassword());
        System.out.println("----confPass----" + librarian.getConfirm_password());
        System.out.println("----address----" + librarian.getAddress());
        System.out.println("----cont_number----" + librarian.getContact_number());
        System.out.println("----full_name----" + librarian.getFull_name());
        System.out.println("----id----" + librarian.getId());
        Role role = new Role();
        role.setId(3);
        librarian.setRole(role);
        librarian.setAccount_enabled(true);

        return userService.createLibrarian(librarian);
    }

    @PostMapping("/auth")
    public User auth(@RequestBody User user) throws Exception {
        System.out.println("------user name----" + user.getName());
        System.out.println("----password----" + user.getPassword());
        String uname = user.getName();
        String upass = user.getPassword();
        User userobj = null;
        if (uname != null && upass != null) {
            userobj = userService.getUserByUsername(user);
        }
        if (userobj == null) {
            throw new Exception("Bad credential");
        }
        return userobj;
    }

    @PostMapping("/librarianAuth")
    public Librarian librarianAuth(@RequestBody Librarian librarian) throws Exception {
        System.out.println("------Librarian name----" + librarian.getName());
        System.out.println("---- Librarian password----" + librarian.getPassword());
        String lname = librarian.getName();
        String lpass = librarian.getPassword();
        Librarian librarianobj = null;
        if (lname != null && lpass != null) {
            librarianobj = userService.getLibrarianByname(librarian);
        }
        if (librarianobj == null) {
            throw new Exception("Bad credential");
        }
        return librarianobj;
    }

    @PostMapping("/userDetails")
    public User userDetails(@RequestBody User user) throws Exception {
            System.out.println("--------UserName-----------" + user.getName());

        boolean isverificationEmail = userService.emailId(user);

        if (!isverificationEmail) {
            throw new  Exception ("Email is Already taken, Try another..!! ");
        }
        boolean isVerification = userService.contactNumberVerification(user);
        if (!isVerification) {
            throw new  Exception ("Contact number is Already taken, Try another..!! ");
        }
        boolean isverificationName = userService.name(user);
        if (!isverificationName) {
            throw new  Exception (" User name is Already taken, Try another..!! ");
        }
//        boolean isverificationPassword = userService.passwordVerify(user);
//        if (!isverificationPassword) {
//            throw new Exception("Both password mismatched...!!");
//        }
//        boolean isverificationLibraryName = userService.libraryNameVerify(user);
//        if (!isverificationLibraryName) {
//            throw new Exception("Library name is Already taken, Try another..!!");
//        }
            return user;
    }
    @PostMapping("/librarianDetails")
    public Librarian librarianDetails(@RequestBody Librarian librarian) throws Exception {
        System.out.println("--------Librarian Name-----------" + librarian.getName());

        boolean isverificationLibrarianEmail = userService.librarianEmailId(librarian);

        if (!isverificationLibrarianEmail) {
            throw new Exception("Email is Already taken, Try another..!! ");
        }
        boolean isContactNumberVerification = userService.librarianContactNumberVerification(librarian);
        if (!isContactNumberVerification) {
            throw new Exception("Contact number is Already taken, Try another..!! ");
        }
        boolean isverificationName = userService.librarianName(librarian);
        if (!isverificationName) {
            throw new Exception(" User name is Already taken, Try another..!! ");
        }
//        boolean isverificationPassword = userService.librarianPasswordVerify(librarian);
//        if (!isverificationPassword) {
//            throw new Exception("Both password mismatched...!!");
//        }
        boolean isverificationLibraryName = userService.libraryNameVerify(librarian);
        if (!isverificationLibraryName) {
            throw new Exception("Library name is Already taken, Try another..!!");
        }
        return librarian;
    }
}