package az.orient.elibrarydemoboot.service;

import az.orient.elibrarydemoboot.dto.request.ReqBook;
import az.orient.elibrarydemoboot.dto.request.ReqToken;
import az.orient.elibrarydemoboot.dto.response.RespBook;
import az.orient.elibrarydemoboot.dto.response.Response;

import java.util.List;

public interface BookService {

    Response<List<RespBook>> getBookList(ReqToken reqToken);

    Response<RespBook> getBookById(ReqBook reqBook);

    Response addBook(ReqBook reqBook);

    Response updateBook(ReqBook reqBook);

    Response deleteBook(ReqBook reqBook);
}
