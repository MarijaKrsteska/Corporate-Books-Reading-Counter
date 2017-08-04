package com.epam.library.service.impl;
import java.sql.SQLException; 
import java.util.*;
import com.epam.library.bean.Employee_Book;
import com.epam.library.dao.impl.BookDAOImpl;
import com.epam.library.dao.impl.EmployeeDAOImpl;
import com.epam.library.service.IRandomSeed;

public class RandomSeed implements IRandomSeed{
	private static Random rnd;
	private static int numOfPairs = 299;
	
	private static EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
	private static BookDAOImpl bookDAO = new BookDAOImpl();
	
	public void addPairs(List<Employee_Book> pairs, int bookCount, int numOfEmployees, int numOfBooks){
		//books -> list with bookIDs (0,numOfBooks-1)
		List<Integer> books = new ArrayList<>();
		for(int i=0; i<numOfBooks; i++){
			books.add(i);
		}
		
		//bookCount times generate random number in this interval (0, numOfBooks-1) (0, books.size()-1)	
		//bookCount -> number of books that were read by the (numOfEmployees-1) employee
		while(bookCount > 0){
			int bookID = rnd.nextInt(books.size()); 
			
			Employee_Book newPair = new Employee_Book(bookID, numOfEmployees-1);
			pairs.add(newPair);
			
			books.remove(bookID);
			bookCount--;
		}
	}
	
	public List<Employee_Book> generatePairs() throws SQLException, ClassNotFoundException{
		rnd = new Random();
		
		int numOfEmployees = employeeDAO.numOfEmployees();
		int numOfBooks = bookDAO.numOfBooks();
		
		List<Employee_Book> pairs = new ArrayList<>();
		
		
		int threePlus = rnd.nextInt(numOfBooks-2) + 3; //at least one user with 3+ Book relations
		addPairs(pairs, threePlus, numOfEmployees, numOfBooks);
		numOfPairs -= threePlus;		
				
		while(numOfEmployees > 0 && numOfPairs > 0){
			int bookCount = 0;
			
			if(numOfPairs > 200){
				
				//to be sure that we will have at least 100 relations
				//the current employee must read at least		numOfPairs-200 <= numOfEployees-minValue  	BOOKS
				int numerator = numOfPairs - 200;
				int minValue = 0;
					
				if(numerator % numOfEmployees == 0)
					minValue = numerator / numOfEmployees;
				else
					minValue = numerator / numOfEmployees + 1; //the min number of books that the employee should read
					
				bookCount = rnd.nextInt(numOfBooks - minValue + 1) + minValue;
			}
			else{
				//if we have 100 relations
				bookCount = rnd.nextInt(Math.min(numOfPairs + 1, numOfBooks + 1));
			}
			numOfPairs -= bookCount;
			
			if(bookCount > 0){
				addPairs(pairs, bookCount, numOfEmployees, numOfBooks);
			}

			numOfEmployees--;		
		}
		//System.out.println("Pairs: " + pairs.size());
		return pairs;
	}
	
}
