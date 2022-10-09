package com.devsuperior.uri2611;

import com.devsuperior.uri2611.dto.MovieMinDTO;
import com.devsuperior.uri2611.projections.MovieMinProjection;
import com.devsuperior.uri2611.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2611Application implements CommandLineRunner {

	@Autowired
	private MovieRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2611Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<MovieMinProjection> list1 = repository.search1("Action");
		List<MovieMinDTO> result1 = list1.stream().map(MovieMinDTO::new).collect(Collectors.toList());

		System.out.println("SQL Raiz");
		result1.forEach(System.out::println);


		System.out.println("\n\nJPQL");
		List<MovieMinDTO> result2 = repository.search2("Action");
		result2.forEach(System.out::println);
	}
}
