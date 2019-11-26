package com.example.bandile.pickme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button pick;
    Button clear;
    ImageButton add;
    ListView listview;
    TextView name;
    ArrayList<String> names;
    ArrayList<String> namesToPick;
    ArrayList<String> groups;
    ArrayAdapter<String> adapterList;
    Random r;
    int random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pick = (Button) findViewById(R.id.pick);
        clear = (Button) findViewById(R.id.clear);
        add = (ImageButton) findViewById(R.id.imageButtonAdd);
        listview = (ListView) findViewById(R.id.list);
        name = (TextView) findViewById(R.id.name);
        names = new ArrayList<>();
        namesToPick = new ArrayList<>();
        groups = new ArrayList<>();
        r = new Random();

        adapterList = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, names);
        listview.setAdapter(adapterList);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText() != null && !name.getText().toString().trim().equals("")){
                    names.add(name.getText().toString());
                    adapterList.notifyDataSetChanged();
                    name.setText("");
                }
            }
        });

        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = 0;
                String groupNames = "";
                //namesToPick = names;
                //namesToPick.containsAll(names);
                for(int j = 0; j < names.size(); j++){
                    namesToPick.add(names.get(j));
                }

                while(!namesToPick.isEmpty()){
                    random = r.nextInt(namesToPick.size());
                    groupNames = names.get(i);
                    while(groupNames.contains(namesToPick.get(random))){
                        random = r.nextInt(namesToPick.size());
                    }
                    groupNames = groupNames + " - " + namesToPick.get(random);
                    namesToPick.remove(random);
                    groups.add(groupNames);
                    i++;
                }

                adapterList = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, groups);
                listview.setAdapter(adapterList);

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                names.clear();
                random = 0;
                groups.clear();

                adapterList = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, names);
                listview.setAdapter(adapterList);
            }
        });
    }
}
