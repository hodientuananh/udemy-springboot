package com.benkinmat.restful.webservices.udemyspringboot.todo;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoJpaRepositoryIntergrationTest {

	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private TodoJpaRepository todoJpaRepository;
 
    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        Todo todo = new Todo(Long.valueOf(1), "Tuan Anh", "This is a test", new Date(), true);
        entityManager.merge(todo);
        entityManager.flush();
     
        // when
        List<Todo> found = todoJpaRepository.findByUsername("Tuan Anh");
     
        // then
        assertEquals(found.get(0).getUsername(), todo.getUsername());
    }
}
