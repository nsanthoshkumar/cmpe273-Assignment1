package edu.sjsu.cmpe.library.dto;


import java.util.ArrayList;

import edu.sjsu.cmpe.library.domain.Authors;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Reviews;

public class ReviewDto extends LinksDto{
private Reviews review;
//public ArrayList<Reviews> reviews=new ArrayList<Reviews>();
	public ReviewDto() {
		// TODO Auto-generated constructor stub
	}
	   public ReviewDto(Reviews review) {
			super();
			this.review = review;
		    }

		    /**
		     * @return the book
		     */
		    public Reviews getReviews() {
			return review;
		    }

		    /**
		     * @param book
		     *            the book to set
		     */
		    public void setReview(Reviews review) {
			this.review = review;
		    }
			public  ArrayList<Reviews> getReviewsByIsbn(Book b) {
			      
				ArrayList<Reviews> review1;
				ArrayList<Reviews> review=new ArrayList<Reviews>();
				review1=b.getReviews();
				int j;
				
					for(j=1;j<=review1.size();j++)
					{
						review.add(review1.get(j-1));
					
					}
					return review;
				}
		

}
