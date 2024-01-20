package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name , String language , String address)
	{
		
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number("9646532022");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName(name);
		List<String> mylist = new ArrayList<String>();
		mylist.add("Shop");
		mylist.add("shoe park");
		
		p.setTypes(mylist);
		
		Location l = new Location();
		l.setLang(33.427362);
		l.setLat(-38.383494);	
		p.setLocation(l);
		
		return p;
	}
}
