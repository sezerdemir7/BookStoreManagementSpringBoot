package com.bookStore.service;

import com.bookStore.entity.MyBookList;
import com.bookStore.repository.MyBookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MyBookListService {

    private final MyBookRepository myBookRepository;

    public void saveMyBooks(MyBookList myBookList){
        myBookRepository.save(myBookList);

    }

    public List<MyBookList> getAllMyBooks(){
        return myBookRepository.findAll();
    }

    public void deleteById(int id){
        myBookRepository.deleteById(id);
    }

}
