package selab1;
import java.util.Date;

public class Hashtag {
private String hashtag;
private Date createdDate;

public String getHashtag()
{
	return this.hashtag;
}

public Date getcreatedDate()
{
	return this.createdDate; 
}

public void setHashtag(String htag)
{
	this.hashtag=htag;
}

public void setcreatedDate(Date crDate)
{
	this.createdDate=crDate;
}

}
