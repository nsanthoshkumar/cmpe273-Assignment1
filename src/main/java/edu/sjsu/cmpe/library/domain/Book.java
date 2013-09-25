package edu.sjsu.cmpe.library.domain;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {
    private long isbn;
    private String title;
    @JsonProperty("publication-date")
    private String publicationdate;
    private String language;
    @JsonProperty("num-pages")
    private int num_pages;
    private String status;
    public ArrayList<Authors> authors=new ArrayList<Authors>();
    public ArrayList<Reviews> reviews=new ArrayList<Reviews>();
    public static int review_counter=1;
    public static int author_counter=1;
    // add more fields here

    /**
     * @return the isbn
     */
    public long getIsbn() {
	return isbn;
    }

    /**
     * @param isbn
     *            the isbn to set
     */
    public void setIsbn(long isbn) {
	this.isbn = isbn;
    }

    /**
     * @return the title
     */
    public String getTitle() {
	return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
	this.title = title;
    }
    @JsonProperty("publication-date")
    public void setPublicationDate(String publicationdate)
    {
    	 this.publicationdate=publicationdate;
    
    }
    @JsonProperty("publication-date")
    public String getPublicationDate()
    {
    	return publicationdate;
    }
    public void setLanguage(String language) {
    	this.language = language;
        }
    public String getLanguage() {
    	return language;
        }
    @JsonProperty("num-pages")
    public void setNumOfPages(int num_pages)
    {
    	this.num_pages=num_pages;
    }
    @JsonProperty("num-pages")
    public int getNumOfPages()
    {
    	return num_pages;
    }
    public void setStatus(String status)
    {
    	this.status=status;
    	
    }
    public String getStatus()
    {
    	return status;
    }

	public ArrayList<Reviews> getReviews() {
		return reviews;
	}

	public void setReviews(ArrayList<Reviews> reviews) {
		this.reviews = reviews;
	}

	
	
	public ArrayList<Reviews> addReviews(Reviews review){
		
		review.setId(review_counter);
		if(reviews.size()==0)
		{
			reviews=new ArrayList<Reviews>();	
		}
		reviews.add(review);
		review_counter=review_counter+1;
		return reviews;
		
	}

	public Reviews fetchReviewById(int id) {
      
	Reviews rv=new Reviews();
	int j;
	System.out.println("ReviewBy ID"+id+"review_counter="+review_counter);
		for(j=1;j<=reviews.size();j++)
		{
			if(j==id)
			{
				System.out.println("in if i"+j+"id="+id+"reviews.size()"+reviews.size());
			rv= reviews.get(id-1);
			}
		}
		System.out.println("after for:i"+j+"id="+id+"reviews.size()"+reviews.size());
		return rv;
	}
	public ArrayList<Authors> getAuthors() {
		return authors;
	}

	public void setAuthors(ArrayList<Authors> authors) {
		
		for(int i=1;i<=authors.size();i++)
		{
			authors.get(i-1).setId(author_counter);
			author_counter++;
		}
		this.authors = authors;
	}
/*	public  ArrayList<Reviews> getReviewsByIsbn() {
	      
		ArrayList<Reviews> review=new ArrayList<Reviews>();
		int j;
		
			for(j=1;j<=reviews.size();j++)
			{
				review.add(reviews.get(j-1));
			
			}
			return review;
		}
*/
	

	
	}
    
	

