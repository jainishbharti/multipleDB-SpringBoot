package com.datagrokr.assignment.multipledb.secondary.service;

import com.datagrokr.assignment.multipledb.secondary.dao.SecondaryBookRepository;
import com.datagrokr.assignment.multipledb.secondary.entity.SecondaryBook;
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
class SecondaryBookServiceTest {

    @Mock private SecondaryBookRepository secondaryBookRepository;

    SecondaryBookService underTest;

    @BeforeEach
    void setUp() {
        underTest = new SecondaryBookService(secondaryBookRepository);
    }

    @Test
    void getAllSecondaryBooks() {
        //when this happened
        underTest.getAllSecondaryBooks();
        //then we'll verify that findAll() was called - which is tested by JPA repository
        verify(secondaryBookRepository).findAll();
    }

    @Test
    void getSecondaryBookById() {
        //when this happened
        underTest.getSecondaryBookById(1);
        //then we'll verify that findById() was called - which is tested by JPA repository
        verify(secondaryBookRepository).findById(1);
    }

    @Test
    void
    addSecondaryBook() {
        // create a secondary book with some data
        SecondaryBook testBook = new SecondaryBook(14, "Book 1", "Author 1", "Genre 1");

        //when this happened
        underTest.addSecondaryBook(testBook);
        //then we'll verify that save() was called - which is tested by JPA repository
        verify(secondaryBookRepository).save(testBook);
    }

    @Test
    @Disabled
    void addOrUpdateSecondaryBook() {
        // given
        SecondaryBook testBook2 = new SecondaryBook(14, "Book 14", "Author 14", "Genre 13");
        given(secondaryBookRepository.existsById(14))
                .willReturn(true);
        //when this happened
        underTest.addOrUpdateSecondaryBook(14, testBook2);

        //we'll check whether there is book available of this id or not
//        assertThat(secondaryBookRepository.findById(14)).isEqualTo(testBook2);
        verify(secondaryBookRepository).save(testBook2);
    }

    @Test
    void deleteSecondaryBook() {
        //given
        Integer id = 1;

        //when this happened
        underTest.deleteSecondaryBook(id);
        //then we'll verify that deleteById() was called  with same Id
        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(secondaryBookRepository).deleteById(argumentCaptor.capture());
        Integer capturedId = argumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);

    }
}