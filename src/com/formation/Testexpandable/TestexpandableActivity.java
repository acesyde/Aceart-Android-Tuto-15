package com.formation.Testexpandable;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

public class TestexpandableActivity extends Activity {

	private ExpandableListView expandableList = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		expandableList = (ExpandableListView) findViewById(R.id.expandableView);

		ArrayList<Groupe> groupes = new ArrayList<Groupe>();

		for (int i = 1; i < 10; i++) {

			Groupe groupe = new Groupe("Groupe " + i);

			ArrayList<Objet> donnees = new ArrayList<Objet>();

			for (int x = 1; x < 10; x++) {
				donnees.add(new Objet(groupe, "Objet " + x));
			}

			groupe.setObjets(donnees);

			groupes.add(groupe);
		}

		ELVAdapter adapter = new ELVAdapter(this, groupes);

		expandableList.setAdapter(adapter);
	}
}