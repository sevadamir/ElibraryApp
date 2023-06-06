package az.orient.elibrarydemoboot.service.impl;

import az.orient.elibrarydemoboot.dto.request.ReqBook;
import az.orient.elibrarydemoboot.dto.request.ReqToken;
import az.orient.elibrarydemoboot.dto.response.RespBook;
import az.orient.elibrarydemoboot.dto.response.RespStatus;
import az.orient.elibrarydemoboot.dto.response.Response;
import az.orient.elibrarydemoboot.entity.Book;
import az.orient.elibrarydemoboot.entity.User;
import az.orient.elibrarydemoboot.entity.UserToken;
import az.orient.elibrarydemoboot.enums.EnumAvailableStatus;
import az.orient.elibrarydemoboot.exception.ExceptionConstants;
import az.orient.elibrarydemoboot.exception.LibraryException;
import az.orient.elibrarydemoboot.repository.BookRepository;
import az.orient.elibrarydemoboot.repository.UserRepository;
import az.orient.elibrarydemoboot.repository.UserTokenRepository;
import az.orient.elibrarydemoboot.service.BookService;
import az.orient.elibrarydemoboot.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final UserTokenRepository userTokenRepository;

    private final UserRepository userRepository;

    private final Utility utility;

    @Override
    public Response<List<RespBook>> getBookList(ReqToken reqToken) {
        Response <List<RespBook>> response = new Response<>();
        try {
            utility.checkToken(reqToken);
            List<Book> bookList = bookRepository.findAllByActive(EnumAvailableStatus.ACTIVE.value);
            if (bookList.isEmpty()) {
                throw new LibraryException(ExceptionConstants.BOOK_NOT_FOUND, "Book not found");
            }
            List<RespBook> respBookList = bookList.stream().map(book -> mapping(book)).collect(Collectors.toList());
            response.setT(respBookList);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (LibraryException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.ITERNAL_EXCEPTION, ex.getMessage()));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response<RespBook> getBookById(ReqBook reqBook ) {
        Response<RespBook> response = new Response<>();
        try {
            Long bookId = reqBook.getBookId();
            utility.checkToken(reqBook.getReqToken());
            if (bookId == null) {
                throw new LibraryException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Book book = bookRepository.findBookByIdAndActive(bookId, EnumAvailableStatus.ACTIVE.value);
            if (book == null) {
                throw new LibraryException(ExceptionConstants.BOOK_NOT_FOUND, "Book not found");
            }
            RespBook respBook = mapping(book);
            response.setT(respBook);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (LibraryException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.ITERNAL_EXCEPTION, ex.getMessage()));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response addBook(ReqBook reqBook) {
        Response response = new Response<>();
        try {
            ReqToken reqToken = reqBook.getReqToken();
            utility.checkToken(reqToken);
            String name = reqBook.getName();
            if (name == null) {
                throw new LibraryException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Book book = Book.builder().
                    name(name).
                    author(reqBook.getAuthor()).
                    lang(reqBook.getLang()).
                    amount(reqBook.getAmount()).
                    year(reqBook.getYear()).
                    build();
            bookRepository.save(book);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (LibraryException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.ITERNAL_EXCEPTION, ex.getMessage()));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response updateBook(ReqBook reqBook) {
        Response response = new Response<>();
        try {
            ReqToken reqToken = reqBook.getReqToken();
            utility.checkToken(reqToken);
            String name = reqBook.getName();
            Long bookId = reqBook.getBookId();
            if (name == null || bookId == null) {
                throw new LibraryException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Book book = bookRepository.findBookByIdAndActive(bookId, EnumAvailableStatus.ACTIVE.value);
            if (bookId == null) {
                throw new LibraryException(ExceptionConstants.BOOK_NOT_FOUND, "Book not found");
            }
            book.setName(name);
            book.setAuthor(reqBook.getAuthor());
            book.setLang(reqBook.getLang());
            book.setYear(reqBook.getYear());
            book.setAmount(reqBook.getAmount());
            bookRepository.save(book);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (LibraryException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.ITERNAL_EXCEPTION, ex.getMessage()));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response deleteBook(ReqBook reqBook) {
        Response response = new Response<>();
        try {
            Long bookId = reqBook.getBookId();
            utility.checkToken(reqBook.getReqToken());
            if (bookId == null) {
                throw new LibraryException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Book book = bookRepository.findBookByIdAndActive(bookId, EnumAvailableStatus.ACTIVE.value);
            if (bookId == null) {
                throw new LibraryException(ExceptionConstants.BOOK_NOT_FOUND, "Book not found");
            }
            book.setActive(EnumAvailableStatus.DEACTIVE.value);
            bookRepository.save(book);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (LibraryException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.ITERNAL_EXCEPTION, ex.getMessage()));
            ex.printStackTrace();
        }
        return response;
    }

    private RespBook mapping (Book book) {
        RespBook respBook = RespBook.builder().
                bookId(book.getId()).
                name(book.getName()).
                author(book.getAuthor()).
                lang(book.getLang()).
                year(book.getYear()).
                amount(book.getAmount()).
                build();
        return respBook;
    }


}
