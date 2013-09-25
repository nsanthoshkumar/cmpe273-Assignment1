package edu.sjsu.cmpe.library.api.resources;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.POST;

import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Authors;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Reviews;
import edu.sjsu.cmpe.library.dto.AuthorsDto;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.dto.ReviewDto;

@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
	
	public static HashMap<Integer,Book> bookGrp=new HashMap<Integer,Book>();
	   static int counter=1;
	   
    public BookResource() {
	// do nothing
    }

    @GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    public BookDto getBookByIsbn(@PathParam("isbn") int isbn) {
	// FIXME - Dummy code
    	System.out.println(isbn);
     	BookDto bookResponse = new BookDto(bookGrp.get(isbn));
     	Book book=bookGrp.get(isbn);
     	int author_num=book.authors.size();
     	for(int i=1;i<=author_num;i++)
     	{
     		bookResponse.addLink(new LinkDto("view-author","/books/authors/"+i,"GET"));
     	}
     	bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(),"GET"));
     	bookResponse.addLink(new LinkDto("update-book","/books/" + book.getIsbn(), "PUT"));
     	bookResponse.addLink(new LinkDto("delete-book", "/books/"+book.getIsbn(),"DELETE"));
     	bookResponse.addLink(new LinkDto("create-review","/books/" + book.getIsbn(), "POST"));
     	bookResponse.addLink(new LinkDto("view-all-reviews","/books/" + book.getIsbn()+"reviews", "GET"));
	// add more links

	return bookResponse;
    }
    /**
     * @param request
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(Book request)
    {
    	 	request.setIsbn(counter);
        	bookGrp.put(counter, request);
    	counter=counter+1;
    	LinksDto bookResponse = new LinksDto();
    
    	bookResponse.addLink(new LinkDto("view-book", "/books/" + request.getIsbn(),
    		"GET"));
    	bookResponse.addLink(new LinkDto("update-book","/books/" + request.getIsbn(), "PUT"));
    	bookResponse.addLink(new LinkDto("delete-book","/books/" + request.getIsbn(), "DELETE"));
    	bookResponse.addLink(new LinkDto("create-review","/books/" + request.getIsbn(), "POST"));
    	return Response.status(201).entity(bookResponse).build();
    	//return Response.
    	
    }
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{isbn}")
    public Response deleteBook(@PathParam("isbn") int isbn ){
    	bookGrp.remove(isbn);
    	LinksDto bookResponse = new LinksDto();
    	bookResponse.addLink(new LinkDto("create-book", "/books/","POST"));
    	return Response.status(200).entity(bookResponse).build();
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{isbn}")
    public Response updateStatus(@PathParam("isbn") int isbn,@QueryParam("status") String status){
    
    	
       	Book book=bookGrp.get(isbn);
       	book.setStatus(status);
       	bookGrp.put(isbn, book);
     	LinksDto bookResponse = new LinksDto();
       	bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(),"GET"));
     	bookResponse.addLink(new LinkDto("update-book","/books/" + book.getIsbn(), "PUT"));
     	bookResponse.addLink(new LinkDto("delete-book", "/books/"+book.getIsbn(),"DELETE"));
     	bookResponse.addLink(new LinkDto("create-review","/books/" + book.getIsbn(), "POST"));
     	bookResponse.addLink(new LinkDto("view-all-reviews","/books/" + book.getIsbn()+"reviews", "GET"));
     	
     	return Response.status(200).entity(bookResponse).build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{isbn}/reviews")
    public Response addReview(@PathParam("isbn") int isbn,Reviews reviews){
    	
    	Book book=bookGrp.get(isbn);
    	
    	ArrayList<Reviews> review=book.addReviews(reviews);
    	book.setReviews(review);
    	bookGrp.put(isbn,book);
    	LinksDto reviewResponse = new LinksDto();
    	reviewResponse.addLink(new LinkDto("view-review", "/books/"+book.getIsbn()+"/reviews/"+reviews.getId(),"GET"));
    	return Response.status(201).entity(reviewResponse).build();
    }
    
    @GET
    @Path("{isbn}/reviews/{id}")
    public Response viewReview(@PathParam("isbn") int isbn,@PathParam("id") int id){
		System.out.println("Path Param"+id);
		
    	Book book=	bookGrp.get(isbn);
    	Reviews review=new Reviews();
    	review=book.fetchReviewById(id);
    	ReviewDto reviewResponse=new ReviewDto(review);
    	reviewResponse.addLink(new LinkDto("view-review", "/books/"+book.getIsbn()+"/reviews/"+review.getId(),"GET"));
    	return Response.status(200).entity(reviewResponse).build();
    	
    
    }
    
    @GET
    @Path("{isbn}/reviews")
    public Response viewReviewByIsbn(@PathParam("isbn") int isbn){
    	Book book=	bookGrp.get(isbn);
    	ArrayList<Reviews> rev;
    	ReviewDto reviewResponse=new ReviewDto();
    	rev=reviewResponse.getReviewsByIsbn(book);
    	/*for(int i=0;i<rev.size();i++)
    	{
    		reviewResponse=new ReviewDto(rev.get(i));
    		//reviewResponse=new ReviewDto();
    	}*/
       	 	LinksDto ld=new LinksDto();
    	return Response.status(200).entity(rev).build();
    	
    
    }
    @GET
    @Path("{isbn}/authors/{id}")
    public Response viewBookAuthor(@PathParam("isbn") int isbn,@PathParam("id") int id){
		
    	Book book=	bookGrp.get(isbn);
    	Authors author=new Authors();
    	AuthorsDto authorResponse=new AuthorsDto(author);
    	bookGrp.get(isbn).reviews.size();
    	author=authorResponse.getAuthorById(book,id);
    	AuthorsDto authorResponse1=new AuthorsDto(author);
    	authorResponse1.addLink(new LinkDto("view-author", "/books/" + book.getIsbn()+"/authors/"+id,"GET"));
    	return Response.status(200).entity(authorResponse1).build();
    
    }
    
    @GET
    @Path("{isbn}/authors")
    public Response viewBookAllAuthor(@PathParam("isbn") int isbn){
		
    	Book book=	bookGrp.get(isbn);
    	ArrayList<Authors> author;
    	AuthorsDto authorResponse=new AuthorsDto();
    	author=authorResponse.getAllAuthorsById(book);
    	for(int i=0;i<author.size();i++)
    	{
    		authorResponse=new AuthorsDto(author.get(i));
    	}
    	return Response.status(200).entity(author).build();
    
    }
    
   }


