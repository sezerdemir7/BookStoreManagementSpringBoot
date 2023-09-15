package com.bookStore.service;

import com.bookStore.entity.Book;
import com.bookStore.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public void save(Book book){
        bookRepository.save(book);

    }

    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }

    public Book getBookById(int id){
        return bookRepository.findById(id).get();
    }

    public void deleteById(int id){
        bookRepository.deleteById(id);
    }

    public List<Book> kitapAra(String  name){
        List<Book> bookList=bookRepository.findByNameContainingIgnoreCase(name);

        if(bookList.isEmpty()){
            return bookRepository.findByAuthorContainingIgnoreCase(name);
        }
        return bookList;
    }

    public void readExcelAndSetObjects(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);

        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            String kitapAdi = row.getCell(0).getStringCellValue();
            String yazarAdi = row.getCell(1).getStringCellValue();
            String sayfaSayisiString = row.getCell(2).getStringCellValue();



            Book kitap = new Book();
            kitap.setName(kitapAdi);
            kitap.setAuthor(yazarAdi);
            kitap.setPrice(String.valueOf(sayfaSayisiString));


            bookRepository.save(kitap);
        }

        workbook.close();
        fis.close();
    }


    public void increaseLikeCount(int bookId) {
        bookRepository.increaseLikeCount(bookId);
    }






}
