package selab1;
import java.sql.*;

import java.util.ArrayList;

public class HashtagDAO {
	
public void insertHashtag(Hashtag ht) throws Exception
{
	try
	{
		Connection con=null;
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/selab1", "root", "virgo");
		//Calendar calendar = Calendar.getInstance();
	    
	    java.sql.Timestamp sqlDate = new java.sql.Timestamp(ht.getcreatedDate().getTime());
	    //System.out.println(sqlDate.toString());
		String sql="Insert into hashtag(hashtagname,createdDate)"+"values(?,?)";
		PreparedStatement preparedStmt = con.prepareStatement(sql);
		preparedStmt.setString(1, ht.getHashtag());
		preparedStmt.setTimestamp(2, sqlDate);
		preparedStmt.execute();
	    con.close();
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
}

public boolean searchHashtag(Hashtag ht)
{
	boolean exists=false;
	try
	{
		Connection con=null;
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/selab1", "root", "virgo");
		
		String sql="Select * from hashtag where hashtagname=?";
		PreparedStatement preparedStmt = con.prepareStatement(sql);
		preparedStmt.setString(1, ht.getHashtag());
		ResultSet re=preparedStmt.executeQuery();
		if(re.next())
			exists=true;
		else
			exists=false;
			
		con.close();
		
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	return exists;
}

public void insertHashtagFeed(HashtagFeed htf)
{
	try
	{
	Connection con=null;
	Class.forName("com.mysql.jdbc.Driver");
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/selab1", "root", "virgo");
	
	java.sql.Timestamp sqlDate = new java.sql.Timestamp(htf.getpostedDate().getTime());
	String sql="Insert into hashtagfeed(postedTime,parentFeedID,hashtagname,content)"+"values(?,?,?,?)";
	PreparedStatement preparedStmt = con.prepareStatement(sql);
	
	preparedStmt.setTimestamp(1, sqlDate);
	preparedStmt.setInt(2, htf.getParentId());
	preparedStmt.setString(3, htf.getHashtag());
	preparedStmt.setString(4, htf.getcontent());
	preparedStmt.execute();
    con.close();
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
}

public ArrayList<HashtagFeed> fetchHashtagFeed(String htf)
{
	
	try
	{
	Connection con=null;
	Class.forName("com.mysql.jdbc.Driver");
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/selab1", "root", "virgo");
	
	String sql="Select * from hashtagfeed where hashtagname=? order by postedTime desc";
	PreparedStatement preparedStmt = con.prepareStatement(sql);

	
	preparedStmt.setString(1, htf);

	ResultSet re=preparedStmt.executeQuery();
	
	//HashtagFeed[] htf2;

	//if(size>0)
	//htf2=new HashtagFeed[size];
	ArrayList<HashtagFeed> htf2 = new ArrayList<HashtagFeed>(); 
	 
	while(re.next())
	{
		
		//System.out.println(re.getInt("id"));
		//System.out.println(re.getTimestamp("postedTime"));
		/*
		System.out.println(re.getInt("parentFeedID"));
		System.out.println(re.getString("hashtagname"));
		System.out.println(re.getString("content"));
		*/
		HashtagFeed hashtagfeed=new HashtagFeed();
		hashtagfeed.setId(re.getInt("id"));
		hashtagfeed.setcontent(re.getString("content"));
		hashtagfeed.setHashtag(re.getString("hashtagname"));
		hashtagfeed.setParentId(re.getInt("parentFeedID"));
		hashtagfeed.setpostedDate(re.getTimestamp("postedTime"));
		htf2.add(hashtagfeed);
	}
	con.close();
	
	return htf2;
	}
	catch(Exception e)
	{
		System.out.println(e);
		return null;
	}
}
}
