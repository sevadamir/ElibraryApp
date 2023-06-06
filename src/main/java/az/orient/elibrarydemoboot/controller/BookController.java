package az.orient.elibrarydemoboot.controller;

import az.orient.elibrarydemoboot.dto.request.ReqBook;
import az.orient.elibrarydemoboot.dto.request.ReqLogin;
import az.orient.elibrarydemoboot.dto.request.ReqToken;
import az.orient.elibrarydemoboot.dto.response.RespBook;
import az.orient.elibrarydemoboot.dto.response.Response;
import az.orient.elibrarydemoboot.service.BookService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


    @PostMapping  ("/GetBookList")
    public Response <List<RespBook>> getBookList(@RequestBody ReqToken reqToken) {
        return bookService.getBookList(reqToken);
    }

    @PostMapping ("/GetBookById")
    public Response<RespBook> getBookById(@RequestBody ReqBook reqBook) {
        return bookService.getBookById(reqBook);
    }

    @PostMapping("/AddBook")
    public Response addBook(@RequestBody ReqBook reqBook) {
        return bookService.addBook(reqBook);
    }

    @PutMapping("/UpdateBook")
    public Response updateBook(@RequestBody ReqBook reqBook) {
        return bookService.updateBook(reqBook);
    }

    @PutMapping("/DeleteBook")
    public Response deleteBook (@RequestBody ReqBook reqBook){
        return bookService.deleteBook(reqBook);
    }

}
