package stackpackage;

import static com.mongodb.client.model.Filters.regex;
import static com.mongodb.client.model.Filters.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.bson.Document;
import org.jsoup.Jsoup;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/*
 * Main servlet class which initiate all other servlet
 */

public class StackConnector extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
			{
		           doPost(request,response);
			}
	 @SuppressWarnings({ "unused", "resource", "unchecked" })
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException
	 {
		 String uri = request.getRequestURI();
		 String connectionString = null;
		 RequestDispatcher view = null;
		 MongoCollection<Document> collection=null;
		  MongoCursor<Document> cursor=null;
		  MongoDatabase sampleDB =null;
		   
		  /*
		   * Servlet for connection 
		   */
		 if(uri.endsWith("connection.do"))
		 {
			 HttpSession session  = request.getSession(true);
			 try
				{
				   MongoClient clienttest=new MongoClient("ec2-54-164-16-226.compute-1.amazonaws.com",27017);
					String connectPoint=clienttest.getConnectPoint();      //To check the connection point
					System.out.println("\nConnection Point:"+connectPoint);
					System.out.println("Connected to Server successfully. \n");
					sampleDB=clienttest.getDatabase("StackOverflow");
					System.out.println("Database connection Completed.\n");
					collection=sampleDB.getCollection("QA");
					System.out.println("Collection obtained successfully\n" );
					if(connectPoint != "null")
					{
						request.getRequestDispatcher("/search.jsp").forward(request, response);
						clienttest.close();
					}
					else 
					{
						request.getRequestDispatcher("/connection.jsp").forward(request, response);
					}
						
				}
				catch(Exception e)
				{
					String errormessage = "ERROR Connecting to Server!";
					request.setAttribute("errors",errormessage);
					System.out.println("*******Error on Mongo Database Connection*********\n");
					System.out.println(e.getMessage());
					System.out.println(e.toString());
					e.printStackTrace();
					request.getRequestDispatcher("/connection.jsp").forward(request, response);
				}
					
					
			}
		 /*
		  * Servlet for serach string in database and displaying the questions 
		  */
		 if(uri.endsWith("search.do"))
		 {
            
			 	HttpSession session  = request.getSession(true);
			 	MongoClient clienttest=new MongoClient("ec2-54-164-16-226.compute-1.amazonaws.com",27017);
				String connectPoint=clienttest.getConnectPoint();      //To check the connection point
				System.out.println("\nConnection Point:"+connectPoint);
				System.out.println("Connected to Server successfully. \n");
				sampleDB=clienttest.getDatabase("StackOverflow");
				System.out.println("Database connection Completed.\n");
				collection=sampleDB.getCollection("QA");
				
			 String searchString = request.getParameter("searchTerm");
			 
			 if (searchString.isEmpty())
			 {
				 request.setAttribute("emptyMessage","Enter Search Term");
			 }
				 
            System.out.println("search termm"+searchString);
            String regexPattern = "\\b" + searchString + ".*";
	         int cnt = 0;
	         cursor = collection.find(regex("Title", regexPattern, "i")).limit(25).iterator();
	         System.out.println("collection==="+collection);  
	         System.out.println("werer"+cursor);
	         QABean qabean =null;
	         List<QABean> questionsList=new ArrayList<QABean>();
	         if(request.getParameter("clearButton") == null){	 
	         try {
	                while (cursor.hasNext()) 
	                {
	                  System.out.println("inside while");
	                  Document d = cursor.next();
	                  qabean = new QABean();
	                  qabean.setId((int) d.get("Id"));
	                  qabean.setTitle(d.getString("Title"));
	         	      String s1 = d.getString("Body");
	                  String target= Jsoup.parse(s1).text();
	                  
	                  if(target.length()>100){
	                  String targetTemp = target.substring(0, 100).concat(".....");
	                  qabean.setQuestionBody(targetTemp);
	                  }
	                  else{
	                	  qabean.setQuestionBody(target);  
	                  }
	                 questionsList.add(qabean); 
	                // System.out.println(d.getInteger("Id"));
	                 
	                 
	                } 
	               
	                
	                if(questionsList.size()!= 0)
	              {
	            	  request.setAttribute("questionList",questionsList);
	            	  session.setAttribute("questionList", questionsList);
	            	  request.getRequestDispatcher("/questions.jsp").forward(request, response);
	            	  clienttest.close();
	            	  System.out.println("insidelist");
  
	              }
	              else
	              { 
	            	  String noresults = "No Results Found";
	            	  System.out.println("no results");
	            	  questionsList.clear();
	            	  request.setAttribute("questionList",questionsList);
	            	  request.setAttribute("noquestions",noresults);
	            	  request.getRequestDispatcher("/questions.jsp").forward(request, response);

	              }	        
	              }
	            catch (MongoException e){
					System.out.println("Error on next()");
					System.out.println(e.getMessage());
					System.out.println(e.toString());
					e.printStackTrace();
	         }
		         finally {
		            cursor.close();
		         }
	         }
	         else
	         {
	        	 System.out.println("inside clear seaction");
	        	 questionsList.clear();
	        	 request.setAttribute("questionList",questionsList);
           	     request.getRequestDispatcher("/questions.jsp").forward(request, response);
	         }
	         
		         
		    
		 }
		 /*
		  * Servlet for displaying answers for a particular question and adding comments 
		  */
		 if(uri.endsWith("answers.do"))
		 {
			 HttpSession session  = request.getSession(true);	
			 int parentID = Integer.parseInt(request.getParameter("questionid"));
			 session.setAttribute("questionid", parentID);
			 String tempId = Integer.toString(parentID);
			 MongoClient clienttest=new MongoClient("ec2-54-164-16-226.compute-1.amazonaws.com",27017);
			 String connectPoint=clienttest.getConnectPoint();      //To check the connection point
			sampleDB = clienttest.getDatabase("StackOverflow");
			collection = sampleDB.getCollection("QA");
            Document d2 = collection.find(eq("Id",parentID)).first();
            QABean qabean1 =null;
	         List<String> answersList=new ArrayList<String>();
	         System.out.println("collection===anser"+collection);
	         try {
	        	    qabean1 = new QABean();
	        	    System.out.println("344445555555"+d2.getString("Title"));
	                qabean1.setTitle(d2.getString("Title"));

	         	   String s1 = d2.getString("Body");
	               String target= Jsoup.parse(s1).text();
	               qabean1.setFullQuestionBody(target);
	               String temp = d2.getString("link");
	               if(temp != null){
	                	String temp2 = temp.substring(34,(temp.length()));
	                	System.out.println("temp2222"+temp2);
	                	qabean1.setImageLink(temp2);
	                }
	                ArrayList<Document> answer =  ( ((ArrayList<Document>) d2.get("ans")));
	                for (Object o : answer){
	                	Document ans = (Document) o;
	                	String s2 = ans.getString("Body");
	                	String target2= Jsoup.parse(s2).text();
	                	System.out.println("target2"+target2);
	                	answersList.add(target2);
	                }
	                qabean1.setAnswerBody(answersList);
	                session.setAttribute("answerscomment", qabean1);
	             if(answersList != null)
	              {
	            	 request.setAttribute("answers",qabean1);
	            	 request.getRequestDispatcher("/answers.jsp").forward(request, response);
	            	 clienttest.close();
	              }
	              else
	              {
	            	  request.getRequestDispatcher("/questions.jsp").forward(request, response);

	              }

	         }
	            catch (MongoException e){
					System.out.println("Error on next()");
					System.out.println(e.getMessage());
					System.out.println(e.toString());
					e.printStackTrace();
	         }	         
		 }
		 /*
		  * Servlet for adding comment for a question and to insert into database 
		  * 
		  */
		 if(uri.endsWith("comment.do"))
		 {
			 System.out.println("Comment sections");
			 HttpSession session=request.getSession(false);  
			 String comment = request.getParameter("comment"); 
			 QABean qabean2 = (QABean) session.getAttribute("answerscomment");
			  //int parentID =0;
			 System.out.println(request.getParameter("cancel"));
             List questionsList = (List) session.getAttribute("questionsList");
			 System.out.println("======="+questionsList);
			 request.setAttribute("questionList",questionsList);
			if ((comment != "null") && (! comment.isEmpty()) && (request.getParameter("cancel") == null)){
				int parentID=(Integer)session.getAttribute("questionid"); 
			    
				
				 //String comment = "Hello world";
				 System.out.println("Check comment"+comment);
				 System.out.println("Check comment1232"+parentID);
				 List <String> commentAnswer = qabean2.getAnswerBody() ;
				 commentAnswer.add(comment);
				 qabean2.setAnswerBody(commentAnswer);
				 MongoClient clienttest=new MongoClient("ec2-54-164-16-226.compute-1.amazonaws.com",27017);
				 String connectPoint=clienttest.getConnectPoint();      //To check the connection point
				sampleDB = clienttest.getDatabase("StackOverflow");
                 Document doc = new Document();
                 doc.append("Body", comment);
                 sampleDB.getCollection("QA").findOneAndUpdate(new Document("Id", parentID), new Document("$push", new Document("ans", doc)));
                 System.out.println("Id:"+parentID);
                 System.out.println("Document inserting "+doc.toString());
                 // if database update successful
              
	            	 request.setAttribute("answers",qabean2);
	            	 request.getRequestDispatcher("/answers.jsp").forward(request, response);
	            	 
	            	 clienttest.close();
                 
			 }else{
				 request.setAttribute("answers",qabean2);
				 request.getRequestDispatcher("/answers.jsp").forward(request, response); 
			 }
		 }
		 
	 }
}
	



