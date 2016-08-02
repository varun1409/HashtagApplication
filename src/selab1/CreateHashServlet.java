package selab1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Date;

/**
 * Servlet implementation class CreateHashServlet
 */
public class CreateHashServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CreateHashServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String errormessage="";
		try
		{
		Hashtag hash=new Hashtag();
		
		String msg="";
		request.setAttribute("errormessage", errormessage);
		request.setAttribute("msg", msg);
		String newHashtag=request.getParameter("newHashtag");
		Date createdDate=new Date();
		hash.setcreatedDate(createdDate);
		hash.setHashtag(newHashtag);
		
		if(newHashtagValidator(newHashtag))
		{
			HashtagDAO h=new HashtagDAO();
			ArrayList<HashtagFeed> Hashtagvalues=new ArrayList<HashtagFeed>(); 
			//Hashtagvalues=h.fetchHashtagFeed(hash.getHashtag());
			boolean exists=h.searchHashtag(hash);
			//System.out.print(exists);
			if(!exists)
			{
			request.setAttribute("Hashtagvalues", Hashtagvalues);
			
			msg="No posts created so far";
			request.setAttribute("msg", msg);
			h.insertHashtag(hash);
			String msg2="";
			request.setAttribute("msg2", msg2);
			request.setAttribute("hash", hash);
			request.getRequestDispatcher("viewHashtag.jsp").forward(request, response);
			}
			else
			{
				errormessage="Hashtag already Exists";
				request.setAttribute("errormessage", errormessage);
				request.getRequestDispatcher("create.jsp").forward(request, response);
			}
		}
		else
		{
			errormessage="Please provide a valid Hashtag name (No spl characters & Limit: 50char)";
			request.setAttribute("errormessage", errormessage);
			request.getRequestDispatcher("create.jsp").forward(request, response);
		}
		}
		catch(Exception ex)
		{
			errormessage="Something went wrong. Sorry!";
			request.setAttribute("errormessage", errormessage);
			request.getRequestDispatcher("create.jsp").forward(request, response);
		}
	}


	public boolean newHashtagValidator(String hashtag)
	{
		if(hashtag!="" && hashtag.length()<50 && hashtag.matches("^[a-zA-Z0-9]*$"))
			return true;
		else
			return false;
	}
}
