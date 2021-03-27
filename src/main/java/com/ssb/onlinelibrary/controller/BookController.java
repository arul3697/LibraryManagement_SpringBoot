package com.ssb.onlinelibrary.controller;


import org.apache.commons.io.IOUtils;
import com.ssb.onlinelibrary.model.*;
import com.ssb.onlinelibrary.service.impl.BookServiceImpl;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.ssb.onlinelibrary.util.HibernateUtil.createSession;


@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value="/api")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @PostMapping("/saveReply")
    public AdminReply addAdminReply (@RequestBody AdminReply reply) {
        String username = "Admin";
        reply.setUsername(username);
        System.out.println("------subject----"+reply.getSubject());
        System.out.println("----message----"+reply.getMessage());
        return bookService.addAdminReply(reply);
    }
    @PostMapping("/librarianRequest")
    public LibrarianRequest addLibrarianRequest (@RequestBody LibrarianRequest librequest) {
        String username = "librarian";
        librequest.setUsername(username);
        System.out.println("------subject----"+librequest.getSubject());
        System.out.println("----message----"+librequest.getMessage());
        return bookService.addLibrarianRequest(librequest);
    }
    @PostMapping("/addNotification")
    public Notification addNotification (@RequestBody Notification notification) {
        String username = "librarian";
        notification.setUsername(username);
        System.out.println("------subject----"+notification.getSubject());
        System.out.println("----message----"+notification.getMessage());
        return bookService.addNotification(notification);
    }
    @PostMapping("/addRequest")
    public Request addRequest (@RequestBody Request request) {
        System.out.println("-----username-----"+request.getUsername());
        System.out.println("------Book Name----"+request.getBook_name());
        System.out.println("----Author Name----"+request.getAuthor_name());
        String reqStatus=null;
        String status ="Pending";

        request.setDate(LocalDate.now());
        request.setDateTime(LocalDateTime.now());

        if(reqStatus == null){
            request.setStatus(status);
        }
        return bookService.addRequest(request);
    }
    @GetMapping("/book/{id}")
    public Book allBookById(@PathVariable int id,Book book) {
        return bookService.getBookId(id);
    }

    @GetMapping("/viewFavouriteBookList/{uname}")
    public List<FavouriteBook> viewFavouriteBookList(@PathVariable String uname,FavouriteBook favouriteBook) {
        return bookService.viewFavouriteBookList(uname);
    }

    @GetMapping("/viewUserRequest")
    public List<Request> viewUserRequest() {
        return bookService.viewRequest();
    }
    @GetMapping("/viewLibrarianRequest")
    public List<LibrarianRequest> viewLibrarianRequest() {
        return bookService.viewLibrarianRequest();
    }
    @GetMapping("/viewAdminReply")
    public List<AdminReply> viewAdminReply() {
        return bookService.viewAdminReply();
    }
    @GetMapping("/viewNotification")
    public List<Notification> viewNotification() {
        return bookService.viewNotification();
    }
    @GetMapping("/viewBook")
    public List<Book> viewBooks(){
        return bookService.viewBook();
    }

    @PostMapping("/addbook/{name}/{author_name}/{publisher_name}/{contact_number}")
    public Book uploadMultipartFile(@RequestParam("file") MultipartFile file,@PathVariable String name,@PathVariable String author_name,
                                      @PathVariable String publisher_name,@PathVariable String contact_number,Book book) {
//    @PostMapping("/addbook")
//    public Book addBook(@RequestBody Book book, @RequestParam("file") MultipartFile file){
//        if(book.getId() == 0) {
//
//
            System.out.println("File:" + file.getName());
            System.out.println("ContentType:" + file.getContentType());

            Session currentSession = createSession();
            Blob blob = Hibernate.getLobCreator(currentSession).createBlob(new byte[0]);

            book.setFileName(file.getOriginalFilename());
            book.setContent(blob);
            book.setPath(file.getContentType());

               book.setName(name);
            book.setAuthor_name(author_name);
            book.setPublisher_name(publisher_name);
            book.setContact_number(contact_number);

            book.setAccount_enabled(true);
            book.setDownload_count(0);
            bookService.add(book);

//        }else {
//            book.setAccount_enabled(true);
//            book.setDownload_count(0);
//            bookService.add(book);
//        }
        return book;
    }
    @DeleteMapping("/deleteBook/{id}")
    public Book deleteBook(@PathVariable int id, Book book) {
        book = bookService.getBookId(id);
        book.setAccount_enabled(false);
        return bookService.add(book);
    }
    @DeleteMapping("/deleteFavouriteBook/{id}")
    public FavouriteBook deleteFavouriteBook(@PathVariable int id, FavouriteBook favouriteBook) {
        favouriteBook = bookService.getFavouriteBook(id);
        return bookService.deleteFavouriteBook(favouriteBook);
    }
    @PostMapping("/addFavourite/{bookName}/{uname}")
    public FavouriteBook addFavouriteBook(@PathVariable String bookName,@PathVariable String uname,FavouriteBook favouriteBook){
        System.out.println("-----------BookName------"+bookName);
        System.out.println("-----------User Name------"+uname);
        favouriteBook.setBookName(bookName);
        favouriteBook.setUname(uname);
        return bookService.addFavouriteBook(favouriteBook);
    }

    @GetMapping("/download/{id}")
    public String download(@PathVariable int id, HttpServletResponse response) {

       Book book = bookService.getBookId(id);
        try {
            response.setHeader("Content-Disposition", "inline;filename=\"" +book.getFileName()+ "\"");
            OutputStream out = response.getOutputStream();
            response.setContentType(book.getPath());
            IOUtils.copy(book.getContent().getBinaryStream(), out);
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    @PostMapping("/addbook/{name}/{author_name}/{publisher_name}/{contact_number}")
//    public String uploadMultipartFile(@RequestParam("file") MultipartFile file,@PathVariable String name,@PathVariable String author_name,
//                                      @PathVariable String publisher_name,@PathVariable String contact_number,Book book) {
//        System.out.println("-----name----" +name);
//        System.out.println("-----author_name----" +author_name);
//
//        System.out.println("-----publisher_name----" +publisher_name);
//        System.out.println("-----contact_number----" +contact_number);
//
//
//        try {
//            // save file to PostgreSQL
//             book = new Book();
//            book.setFileName(file.getOriginalFilename());
//            book.setFile(file.getBytes());
//            book.setPath(file.getContentType());
//
//            book.setName(name);
//            book.setAuthor_name(author_name);
//            book.setPublisher_name(publisher_name);
//            book.setContact_number(contact_number);
//            book.setAccount_enabled(true);
//            book.setDownload_count(0);
//            bookService.add(book);
//            return "File uploaded successfully! -> filename = " + file.getOriginalFilename();
//        } catch (  Exception e) {
//            return "FAIL! Maybe You had uploaded the file before or the file's size > 500KB";
//        }
//    }
//    @GetMapping("/download/{id}")
//    public String downloadBook(@PathVariable int id,HttpServletRequest request,
//                               HttpServletResponse response,ModelMap modelMap) throws IOException, ServletException {
//
//        int BUFFER_SIZE = 1024 * 100;
//        String UPLOAD_DIR = "resource";
//        String fileName = null;
//        try {
//            Book book = bookService.getBookId(id);
//            if(book.getId()!=0){
//                book.setFileName(book.getFileName());
//                book.setPath(book.getPath());
//                int initial = book.getDownload_count();
//                int sum = initial+1;
//                book.setDownload_count(sum);
//                bookService.add(book);
//            }
//            fileName = book.getFileName();
//
//            System.out.println("--------fileName--------"+fileName);
//
//            if (fileName == null || fileName.equals("")) {
//                /**
//                 * *** Set Response Content Type ****
//                 */
//                response.setContentType("text/html");
//
//                /**
//                 * *** Print The Response ****
//                 */
//                response.getWriter().println("<h3>File " + fileName + " Is Not Present .....!</h3>");
//            }else {
//                String applicationPath = request.getServletContext().getRealPath("");
//                String downloadPath = applicationPath + File.separator + UPLOAD_DIR;
//                String filePath = downloadPath + File.separator + fileName;
//                System.out.println(fileName);
//                System.out.println(filePath);
//                System.out.println("fileName:" + fileName);
//                System.out.println("filePath :" + filePath);
//                File file = new File(filePath);
//                OutputStream outStream = null;
//                FileInputStream inputStream = null;
//
//                // if (file.exists()) {
//
//                /**
//                 * ** Setting The Content Attributes For The Response Object
//                 * ***
//                 */
//                String mimeType = "application/octet-stream";
//                response.setContentType(mimeType);
//
//                /**
//                 * ** Setting The Headers For The Response Object ***
//                 */
//                String headerKey = "Content-Disposition";
//                String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
//                response.setHeader(headerKey, headerValue);
//
//                try {
//
//                    /**
//                     * ** Get The Output Stream Of The Response ***
//                     */
//                    outStream = response.getOutputStream();
//                    inputStream = new FileInputStream(file);
//                    byte[] buffer = new byte[BUFFER_SIZE];
//                    int bytesRead = -1;
//
//                    /**
//                     * ** Write Each Byte Of Data Read From The Input Stream
//                     * Write Each Byte Of Data Read From The Input Stream Into
//                     * The Output Stream ***
//                     */
//                    while ((bytesRead = inputStream.read(buffer)) != -1) {
//                        outStream.write(buffer, 0, bytesRead);
//                    }
//                } catch (IOException ioExObj) {
//                    System.out.println("Exception While Performing The I/O Operation?= " + ioExObj.getMessage());
//                } finally {
//                    if (inputStream != null) {
//                        inputStream.close();
//                    }
//
//                    outStream.flush();
//                    if (outStream != null) {
//                        outStream.close();
//                    }
//                }
//                //  } else {
//
//                /**
//                 * *** Set Response Content Type ****
//                 */
//                response.setContentType("text/html");
//
//                /**
//                 * *** Print The Response ****
//                 */
//                response.getWriter().println("<h3>File " + fileName + " Is Not Present .....!</h3>");
//            }
//
//            // }
//
//        } catch (Exception ex) {
//            System.out.println(ex);
//            modelMap.addAttribute("error", Constants.DOWNLOAD_FAILED);
//            return "redirect:/book/userViewBook";
//        }
//        return null;
//    }
//@PostMapping("/addbook")
//public Book addOrUpdateBook(@RequestBody Book book) {
//
//    System.out.println("--------book id-----------"+book.getId());
//    if(book.getId() == 0) {
//        book.setAccount_enabled(true);
//        book.setDownload_count(0);
//
////            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
////            System.out.println("--------------fileName---------"+fileName);
////            try {
////                if(fileName.contains("..")) {
////                    throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
////                }
////                book.setFileName(fileName);
//        bookService.add(book);
////            } catch (IOException ex) {
////                throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
//
////        String folderName = "resource";
////        String uploads = request.getServletContext().getRealPath("") + File.separator + folderName;
////        File dir = new File(uploads);
////        if(!dir.exists()) {
////            dir.mkdirs();
////        }
////        Part filePart = request.getPart("booksPdf");
////        String fileName =multipartFile.getOriginalFilename();
//        //      String author = book.getAuthor_name();
//        //     String path = folderName + File.separator + fileName;
//
////        book.setFileName(fileName);
////        book.setPath(path);
////        InputStream inputStream = filePart.getInputStream();
////        Files.copy(inputStream, Paths.get(uploads + File.separator + fileName ), StandardCopyOption.REPLACE_EXISTING);
//        //modelMap.addAttribute("success", Constants.BOOK_ADDED);
//
//    }else {
//        book.setAccount_enabled(true);
//        book.setDownload_count(0);
////            String folderName = "resource";
////            String uploads = request.getServletContext().getRealPath("") + File.separator + folderName;
////            File dir = new File(uploads);
////            if(!dir.exists()) {
////                dir.mkdirs();
////            }
////            Part filePart = request.getPart("booksPdf");
////            String fileName =multipartFile.getOriginalFilename();
////            String author = book.getAuthor_name();
////            String path = folderName + File.separator + fileName;
////
////            book.setFileName(fileName);
////            book.setPath(path);
////            InputStream inputStream = filePart.getInputStream();
////            Files.copy(inputStream, Paths.get(uploads + File.separator + fileName ), StandardCopyOption.REPLACE_EXISTING);
//        bookService.add(book);
//        //modelMap.addAttribute("success", Constants.BOOK_UPDATED);
//    }
//    return book;
//}
}
