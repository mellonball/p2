package umbc.veggie;

import java.util.HashMap;
import java.util.List;
import java.lang.Object;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class RestaurantsAdapter extends BaseExpandableListAdapter {

	private Context ctx;
	private HashMap<String, List<String>> Restaurants_Category;
	private List<String> Restaurants_List;
	
	public RestaurantsAdapter(Context ctx, HashMap<String, List<String>> Restaurants_Category, List<String> restaurants_list)
	{
		this.ctx = ctx;
		this.Restaurants_Category = Restaurants_Category;
		this.Restaurants_List = restaurants_list;
	}
	@Override
	public int getGroupCount() {

		return Restaurants_List.size();
	}

	@Override
	public int getChildrenCount(int arg0) {
		
		System.out.println("arg0="+arg0);
		System.out.println("Restaurant_list.get(arg0)="+Restaurants_List.get(arg0));
		System.out.println(Restaurants_Category.get(Restaurants_List.get(arg0)));
		return Restaurants_Category.get(Restaurants_List.get(arg0)).size() ;
	}

	@Override
	public Object getGroup(int arg0) {

		return Restaurants_List.get(arg0);
	}

	@Override
	public Object getChild(int parent, int child) {
		//returns the current child as object
		return Restaurants_Category.get(Restaurants_List.get(parent)).get(child);
	}

	@Override
	public long getGroupId(int arg0) {

		return arg0;
	}

	@Override
	public long getChildId(int parent, int child) {
		
		return child;
	}

	@Override
	public View getGroupView(int parent, boolean isExpanded,
			View convertview, ViewGroup parentview) {
		// Create a string 
		String group_title = (String) getGroup(parent);
		//getgroup will return the current title of the group
		//check whether convertview is null
		if (convertview == null)
		{
			//create convertview for the newgroup
			LayoutInflater inflator = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertview = inflator.inflate(R.layout.parent_layout,  parentview, false);
		}
		//using convertview get textview on parent xml
		//need to cast because these are objects
		TextView parent_textview = (TextView) convertview.findViewById(R.id.parent_txt);
		//need to make bold
		parent_textview.setTypeface(null, Typeface.BOLD);
		//set text for txtview
		parent_textview.setText(group_title);
		return convertview;
	}

	@Override
	public View getChildView(int parent, int child,
			boolean lastChild, View convertview, ViewGroup parentview) {
		String child_title = (String) getChild(parent, child);
		
		//create convertview; if no view then create view
		if (convertview == null)
		{
			//make convertview
			LayoutInflater inflator = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertview = inflator.inflate(R.layout.child_layout, parentview, false);
			
		}
		TextView child_textview = (TextView) convertview.findViewById(R.id.child_txt );
		//set text fir textview
		child_textview.setText(child_title);
		
		return convertview;
	}

	@Override
	public boolean hasStableIds() {

		return false;
	}
	
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {

		return true;
	}
	
}
