package com.datagrokr.assignment.multipledb.secondary.service;

import com.datagrokr.assignment.multipledb.secondary.dao.SecondaryUserRepository;
import com.datagrokr.assignment.multipledb.secondary.entity.SecondaryUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SecondaryUserServiceTest {

    @Mock SecondaryUserRepository secondaryUserRepository;

    private SecondaryUserService underTest;

    @BeforeEach
    void setUp() {
        underTest = new SecondaryUserService(secondaryUserRepository);
    }

    @Test
    void getAllSecondaryUsers() {
        //when this happened
        underTest.getAllSecondaryUsers();
        //then we'll verify that findAll() was called - which is tested by JPA repository
        verify(secondaryUserRepository).findAll();
    }

    @Test
    void getSecondaryUserById() {
        //when this happened
        underTest.getSecondaryUserById(1);
        //then we'll verify that findById() was called - which is tested by JPA repository
        verify(secondaryUserRepository).findById(1);
    }

    @Test
    void addSecondaryUser() {
        // given
        SecondaryUser testUser = new SecondaryUser(14, "User 1");

        //when this happened
        underTest.addSecondaryUser(testUser);
        //then we'll verify that save() was called - which is tested by JPA repository
        verify(secondaryUserRepository).save(testUser);
    }

    @Test
    @Disabled
    void addOrUpdateSecondaryUser() {
        // given
        SecondaryUser testUser = new SecondaryUser(14, "User 1");

        //when this happened
        underTest.addOrUpdateSecondaryUser(14,testUser);
        //then we'll verify that save() was called - which is tested by JPA repository
        verify(secondaryUserRepository).save(testUser);
    }

    @Test
    void deleteSecondaryUser() {
        Integer id = 1;

        //when this happened
        underTest.deleteSecondaryUser(id);
        //then we'll verify that deleteById() was called  with same Id
        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(secondaryUserRepository).deleteById(argumentCaptor.capture());
        Integer capturedId = argumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
    }
}