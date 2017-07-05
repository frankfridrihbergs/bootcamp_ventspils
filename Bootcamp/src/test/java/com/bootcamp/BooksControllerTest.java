package com.bootcamp;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.id.IdentifierGenerationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

import com.bootcamp.controllers.BooksController;
import com.bootcamp.models.Books;
import com.bootcamp.repositories.BooksRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@WebAppConfiguration
@ContextConfiguration("/applicationContext.xml")
public class BooksControllerTest {

	private Books bk = new Books();
	
	@Autowired
	private BooksRepository br;

	@Autowired
	private BooksController bc; // not working

	@Test
	public void addBookIncorrectRatingCheck() { // rating check
		bk.setCondition("godlike");
		bk.setCount(1);
		bk.setIsbn("0000000000000");
		bk.setRating(-33f);
		bk.setYear(2012);
		bk.setTitle("gut");
		bk.setAuthor("VeryGytauthorjaja");
		bk.setPic_url("disbutgreatpic");
		br.save(bk);
		Iterable<Books> testBook = br.findAll();
		for (Books i : testBook) {
			Assert.isTrue((i.getRating() >= 0 && i.getRating() <= 10), "Rating should be from 0 - 10");
		}
	}

	@Test
	public void addBookIncorrectIsbn() {// Excpect to fail
		String str = null;
		bk.setCondition("godlike");
		bk.setCount(1);
		bk.setIsbn("00d0000000000");
		bk.setRating(-33f);
		bk.setYear(2012);
		bk.setTitle("gut");
		bk.setAuthor("VeryGytauthorjaja");
		bk.setPic_url("disbutgreatpic");
		br.save(bk);
		Iterable<Books> testBook = br.findAll();
		for (Books i : testBook) {
			str = bk.getIsbn().toString();
		}
		Assert.isTrue(str.matches("[0-9]{13}"), "ISBN is 13 digits and does not contain any letter or symbols");
	}

	@Test
	public void addEmptyBook() {// Excpect to fail
		System.out.println("Exception message!!!");
		try {
			br.save(bk);
		} catch (JpaSystemException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Exception message!!");
	}
	
	@Test
	public void addBookThrouBookController() {// Should work fiiiiineeeeeee

		Assert.isTrue(bc.addNewBook("title", "isbn", "author", 3).equals("Saved"), "should work fine");

	}

	@Test
	public void addBookDirectly() { // Should work fine, testing basic saving
		bk.setCondition("godlike");
		bk.setCount(1);
		bk.setIsbn("0000000000000");
		bk.setRating(3);
		bk.setYear(2012);
		bk.setTitle("gut");
		bk.setAuthor("VeryGytauthorjaja");
		bk.setPic_url("disbutgreatpic");
		br.save(bk);
		Iterable<Books> testBook = br.findAll();
		for (Books i : testBook) {
			Assert.isTrue(i.getAuthor().equals("VeryGytauthorjaja"), "should work fine");
		}
	}

}
