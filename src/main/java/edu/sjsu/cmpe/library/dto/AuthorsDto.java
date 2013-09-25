package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Authors;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Reviews;
@JsonPropertyOrder(alphabetic = true)
public class AuthorsDto extends LinksDto {
	
	public AuthorsDto() {
		// TODO Auto-generated constructor stub
	}
	   public AuthorsDto(Authors author) {
				super();
				this.author = author;
			    }

	private Authors author;
	public Authors getAuthor() {
		return author;
	}
	public void setAuthor(Authors author) {
		this.author = author;
	}


	public ArrayList<Authors> getAllAuthorsById(Book b) {
		
		ArrayList<Authors> author1;
		ArrayList<Authors> author=new ArrayList<Authors>();
		author1=b.getAuthors();
		int j;
		
			for(j=1;j<=author1.size();j++)
			{
				author.add(author1.get(j-1));
			
			}
			return author;
		}
	
	public Authors getAuthorById(Book b,int id) {
		
		ArrayList<Authors> author1;
		ArrayList<Authors> author=new ArrayList<Authors>();
		Authors a=new Authors();
		author1=b.getAuthors();
		int j=author1.size();
		if(id==1)
		{
			a= author1.get(id-1);
		}
		else if(id>2)
		{
			a= author1.get(id-1);
		}
			return a;
	}

	


	
}
