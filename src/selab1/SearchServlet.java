package selab1;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
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
		String msg="";
		try
		{
		String hashtag=request.getParameter("searchHashtag");
		
		if(HashtagValidator(hashtag))
		{
			HashtagDAO h=new HashtagDAO();
			Hashtag hash=new Hashtag();
			hash.setHashtag(hashtag);
			request.setAttribute("hash", hash);
			if(h.searchHashtag(hash))
			{
				ArrayList<HashtagFeed> Hashtagvalues=new ArrayList<HashtagFeed>(); 
				Hashtagvalues=h.fetchHashtagFeed(hashtag);
				if(Hashtagvalues.size()<1)
				{
					msg="No posts created so far";
					request.setAttribute("msg", msg);
				}
				String msg2="";
				request.setAttribute("msg2", msg2);
				request.setAttribute("Hashtagvalues", Hashtagvalues);
				request.getRequestDispatcher("viewHashtag.jsp").forward(request, response);
			}
			else
			{
				errormessage="Requested hashtag does not exists";
				request.setAttribute("errormessage", errormessage);
				request.getRequestDispatcher("search.jsp").forward(request, response);
			}
		}
		else
		{
			errormessage="Please provide a valid Hashtag name (No spl characters & Limit: 50char)";
			request.setAttribute("errormessage", errormessage);
			request.getRequestDispatcher("search.jsp").forward(request, response);
			
		}
		}
		catch(Exception e)
		{
			
		}
	}
	public boolean HashtagValidator(String hashtag)
	{
		if(hashtag!="" && hashtag.length()<50 && hashtag.matches("^[a-zA-Z0-9]*$"))
			return true;
		else
			return false;
	}

}
