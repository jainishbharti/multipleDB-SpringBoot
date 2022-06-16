package com.datagrokr.assignment.multipledb.primary.service;

import com.datagrokr.assignment.multipledb.primary.dao.PrimaryBookRepository;
import com.datagrokr.assignment.multipledb.primary.entity.PrimaryBook;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PrimaryBookServiceTest {

    @Mock private PrimaryBookRepository primaryBookRepository;

    PrimaryBookService underTest;

    @BeforeEach
    void setUp() {
        underTest = new PrimaryBookService(primaryBookRepository);
    }

    @Test
    void getAllPrimaryBooks() {
        //when this happened
        underTest.getAllPrimaryBooks();
        //then we'll verify that findAll() was called - which is tested by JPA repository
        verify(primaryBookRepository).findAll();
    }

    @Test
    void getPrimaryBookById() {
        //when this happened
        underTest.getPrimaryBookById(1);
        //then we'll verify that findById() was called - which is tested by JPA repository
        verify(primaryBookRepository).findById(1);
    }

    @Test
    void
    addPrimaryBook() {
        // create a primary book with some data
        PrimaryBook testBook = new PrimaryBook(14, "Book 1", "Author 1", "Genre 1");

        //when this happened
        underTest.addPrimaryBook(testBook);
        //then we'll verify that save() was called - which is tested by JPA repository
        verify(primaryBookRepository).save(testBook);
    }

    @Test
    @Disabled
    void addOrUpdatePrimaryBook() {
        // given
        PrimaryBook testBook2 = new PrimaryBook(14, "Book 14", "Author 14", "Genre 13");
        given(primaryBookRepository.existsById(14))
                .willReturn(true);
        //when this happened
        underTest.addOrUpdatePrimaryBook(14, testBook2);

        //we'll check whether there is book available of this id or not
//        assertThat(primaryBookRepository.findById(14)).isEqualTo(testBook2);
        verify(primaryBookRepository).save(testBook2);
    }

    @Test
    void deletePrimaryBook() {
        //given
        Integer id = 1;

        //when this happened
        underTest.deletePrimaryBook(id);
        //then we'll verify that deleteById() was called  with same Id
        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(primaryBookRepository).deleteById(argumentCaptor.capture());
        Integer capturedId = argumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);

    }
}