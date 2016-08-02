package selab1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;




import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewHashtagServlet
 */
public class ViewHashtagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewHashtagServlet() {
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
		String msg2="";
		try
		{
		int parentId=Integer.parseInt(request.getParameter("parentid"));
		HashtagFeed hashFeed=new HashtagFeed();
		Hashtag hash=new Hashtag();
		
		HashtagDAO h=new HashtagDAO();
		//System.out.println(parentId);
		String cont;
		String hashtag=request.getParameter("hash");
		hash.setHashtag(hashtag);
		hashFeed.setHashtag(hashtag);
		if(parentId==0)
			cont=request.getParameter("postContent");
		else
			cont=request.getParameter("replyContent");
		
		if(validateContent(cont))
		{
		Date createdDate=new Date();
		hashFeed.setcontent(cont);
		
		hashFeed.setParentId(parentId);
		//SimpleDateFormat sd=new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		//System.out.println("Date:"+createdDate);
		hashFeed.setpostedDate(createdDate);
		//System.out.println(hashFeed.getpostedDate());
		h.insertHashtagFeed(hashFeed);
		}
		else
			msg2="Please enter a valid content/reply";
		
		ArrayList<HashtagFeed> Hashtagvalues=new ArrayList<HashtagFeed>(); 
		Hashtagvalues=h.fetchHashtagFeed(hashFeed.getHashtag());
		/*for(int i=0;i<Hashtagvalues.size();i++)
		{
			System.out.println(Hashtagvalues.get(i).getpostedDate());
		}*/
		request.setAttribute("hash", hash);
		request.setAttribute("Hashtagvalues", Hashtagvalues);
		request.setAttribute("msg2",msg2);
		request.getRequestDispatcher("viewHashtag.jsp").forward(request, response);
		
		
		}
		catch(Exception e)
		{
			
		}
		//HashtagFeed[] has=new HashtagFeed[10];
	}
	public boolean validateContent(String content)
	{
		if((content!=null)&&(!content.isEmpty()))
		{
		if(content.length()<150)
			return true;
		else
			return false;
		}
		else
			return false;
	}

}
