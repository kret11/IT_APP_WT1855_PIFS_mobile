package pl.wroc.pwr.pifs;

import android.content.Context;
import android.graphics.Typeface;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Vreon-PC on 16.05.2016.
 */
public class state_adapter extends BaseExpandableListAdapter{
    private Context ctx;
    private HashMap<String, List<String>> state_category;
    private List<String> state_list;

    public state_adapter(Context ctx, HashMap<String, List<String>> state_category, List<String> state_list)
    {
        this.ctx = ctx;
        this.state_category = state_category;
        this.state_list = state_list;
    }

    @Override
    public int getGroupCount() {
        return state_list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return state_category.get(state_list.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return state_list.get(groupPosition);
    }

    @Override
    public Object getChild(int parent, int child) {
        return state_category.get(state_list.get(parent)).get(child);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int parent, int child) {
        return child;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int parent, boolean isExpanded, View convertView, ViewGroup parentview) {
        String group_title = (String) getGroup(parent);
        if(convertView == null)
        {
            LayoutInflater inflator = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.parent_layout, parentview, false);
        }
        TextView parent_textview = (TextView) convertView.findViewById(R.id.parent_txt);
        parent_textview.setTypeface(null, Typeface.BOLD);
        parent_textview.setText(group_title);
        return convertView;
    }

    @Override
    public View getChildView(int parent, int child, boolean lastChild, View convertView, ViewGroup parentview) {
        String child_title = (String) getChild(parent, child);
        if(convertView == null){
            LayoutInflater inflator = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.child_layout, parentview, false);
        }
        TextView child_textview = (TextView) convertView.findViewById(R.id.child_txt);
        child_textview.setText(child_title);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
