package com.datagrokr.assignment.multipledb.primary.service;

import com.datagrokr.assignment.multipledb.primary.dao.PrimaryUserRepository;
import com.datagrokr.assignment.multipledb.primary.entity.PrimaryUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PrimaryUserServiceTest {

    @Mock PrimaryUserRepository primaryUserRepository;

    private PrimaryUserService underTest;

    @BeforeEach
    void setUp() {
        underTest = new PrimaryUserService(primaryUserRepository);
    }

    @Test
    void getAllPrimaryUsers() {
        //when this happened
        underTest.getAllPrimaryUsers();
        //then we'll verify that findAll() was called - which is tested by JPA repository
        verify(primaryUserRepository).findAll();
    }

    @Test
    void getPrimaryUserById() {
        //when this happened
        underTest.getPrimaryUserById(1);
        //then we'll verify that findById() was called - which is tested by JPA repository
        verify(primaryUserRepository).findById(1);
    }

    @Test
    void addPrimaryUser() {
        // given
        PrimaryUser testUser = new PrimaryUser(14, "User 1");

        //when this happened
        underTest.addPrimaryUser(testUser);
        //then we'll verify that save() was called - which is tested by JPA repository
        verify(primaryUserRepository).save(testUser);
    }

    @Test
    @Disabled
    void addOrUpdatePrimaryUser() {
        // given
        PrimaryUser testUser = new PrimaryUser(14, "User 1");

        //when this happened
        underTest.addOrUpdatePrimaryUser(14,testUser);
        //then we'll verify that save() was called - which is tested by JPA repository
        verify(primaryUserRepository).save(testUser);
    }

    @Test
    void deletePrimaryUser() {
        Integer id = 1;

        //when this happened
        underTest.deletePrimaryUser(id);
        //then we'll verify that deleteById() was called  with same Id
        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(primaryUserRepository).deleteById(argumentCaptor.capture());
        Integer capturedId = argumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
    }
}