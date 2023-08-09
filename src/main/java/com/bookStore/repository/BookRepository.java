package com.bookStore.repository;

import com.bookStore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    @Override
    Optional<Book> findById(Integer integer);

    List<Book> findByNameContainingIgnoreCase(String searchText);

    List<Book> findByAuthorContainingIgnoreCase(String searchText);

    @Modifying
    @Query("UPDATE Book b SET b.likeCount = b.likeCount + 1 WHERE b.id = :bookId")
    void increaseLikeCount(int bookId);
}

