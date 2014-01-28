package com.formation.Testexpandable;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ELVAdapter extends BaseExpandableListAdapter {

	private Context context;
	private ArrayList<Groupe> groupes;
	private LayoutInflater inflater;
	
	public ELVAdapter(Context context, ArrayList<Groupe> groupes) {
		this.context = context;
		this.groupes = groupes;
		inflater = LayoutInflater.from(context);
	}
	
	@Override
	public boolean areAllItemsEnabled() {
		return true;
	}
	
	public Object getChild(int gPosition, int cPosition) {
		return groupes.get(gPosition).getObjets().get(cPosition);
	}

	public long getChildId(int gPosition, int cPosition) {
		return cPosition;
	}

	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		final Objet objet = (Objet) getChild(groupPosition, childPosition);
		
		ChildViewHolder childViewHolder;
		
        if (convertView == null) {
        	childViewHolder = new ChildViewHolder();
        	
            convertView = inflater.inflate(R.layout.group_child, null);
            
            childViewHolder.textViewChild = (TextView) convertView.findViewById(R.id.TVChild);
            childViewHolder.buttonChild = (Button) convertView.findViewById(R.id.BTChild);
            
            convertView.setTag(childViewHolder);
        } else {
        	childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        
        childViewHolder.textViewChild.setText(objet.getNom());
        
        childViewHolder.buttonChild.setText(objet.getNom());
        
        childViewHolder.buttonChild.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Toast.makeText(context, "Groupe : " + objet.getGroupe().getNom() + " - Bouton : " + objet.getNom(), Toast.LENGTH_SHORT).show();				
			}
		});
        
        return convertView;
	}

	public int getChildrenCount(int gPosition) {
		return groupes.get(gPosition).getObjets().size();
	}

	public Object getGroup(int gPosition) {
		return groupes.get(gPosition);
	}

	public int getGroupCount() {
		return groupes.size();
	}

	public long getGroupId(int gPosition) {
		return gPosition;
	}

	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		GroupViewHolder gholder;
		
		Groupe group = (Groupe) getGroup(groupPosition);
		
        if (convertView == null) {
        	gholder = new GroupViewHolder();
        	
        	convertView = inflater.inflate(R.layout.group_row, null);
        	
        	gholder.textViewGroup = (TextView) convertView.findViewById(R.id.TVGroup);
        	
        	convertView.setTag(gholder);
        } else {
        	gholder = (GroupViewHolder) convertView.getTag();
        }
        
        gholder.textViewGroup.setText(group.getNom());
        
        return convertView;
	}

	public boolean hasStableIds() {
		return true;
	}

	public boolean isChildSelectable(int arg0, int arg1) {
		return true;
	}
	
	class GroupViewHolder {
		public TextView textViewGroup;
	}
	
	class ChildViewHolder {
		public TextView textViewChild;
		public Button buttonChild;
	}

}
