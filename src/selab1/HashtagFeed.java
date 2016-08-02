package selab1;
import java.util.Date;
public class HashtagFeed {
private int id;
private int parentID;
private String content;
private String hashtag;
private Date postedDate;


public String getHashtag()
{
	return this.hashtag;
}

public Date getpostedDate()
{
	return this.postedDate; 
}

public int getParentId()
{
	return this.parentID;
}

public int getId()
{
	return this.id;
}

public String getcontent()
{
	return this.content;
}

public void setHashtag(String htag)
{
	this.hashtag=htag;
}

public void setpostedDate(Date pDate)
{
	this.postedDate=pDate; 
}

public void setParentId(int pId)
{
	this.parentID=pId;
}

public void setId(int id)
{
	this.id=id;
}

public void setcontent(String cont)
{
	this.content=cont;
}

}
