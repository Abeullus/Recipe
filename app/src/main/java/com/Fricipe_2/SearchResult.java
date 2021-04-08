package com.Fricipe_2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;

public class SearchResult extends AppCompatActivity {
    ArrayList<String> listItems = new ArrayList<>();
    GridView gridView;
    ArrayAdapter<String> adapter;
    ArrayList<Integer> savedReceiveMatches;
    ArrayList<Integer> savedRecipeList;
    MediaPlayer player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        player = MediaPlayer.create(this, R.raw.musik);
        Intent intent = getIntent();
        play();
        final ArrayList<Integer> reciveRecipeList = intent.getIntegerArrayListExtra("idrecipes");
        final ArrayList<Integer> receiveMatches = intent.getIntegerArrayListExtra("matches");
        this.savedReceiveMatches = receiveMatches;
        this.savedRecipeList = reciveRecipeList;
        final ArrayList<Integer> receiveMatchesNoDuplicates = intent.getIntegerArrayListExtra("matchesNoDuplicates");

        ArrayList<String> receiveMatchesNoDuplicatesString = createString(receiveMatchesNoDuplicates);

        gridView = findViewById(R.id.idGridSearchResult);
        adapter = new ArrayAdapter<String>(this, R.layout.box_searchresult, receiveMatchesNoDuplicatesString);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Integer positionView = gridView.getPositionForView(view);
                Intent intent = new Intent(getApplicationContext(), RecipeListActivity.class);

                intent.putExtra("title", "Matching: " + receiveMatchesNoDuplicates.get(positionView) + " ingredients");
                ArrayList<Integer> positions = new ArrayList<Integer>();

                int valuePosition = receiveMatchesNoDuplicates.get(positionView);
                positions = findPositionIdResearch(valuePosition);

                ArrayList<Integer> idtosend = new ArrayList<Integer>();
                for (int i = 0; i < positions.size(); i++) {
                    idtosend.add(savedRecipeList.get(positions.get(i)));
                }
                intent.putExtra("list", idtosend);
                startActivity(intent);

            }
        });
    }

    public void play() {
        if (player == null) {
            player = MediaPlayer.create(this, R.raw.musik);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {

                }
            });
        }
        player.start();
    }


    public ArrayList<String> createString(ArrayList<Integer> array) {
        ArrayList<String> stringToShow = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {

            if (array.get(i) == 1) {
                stringToShow.add("Enthält\n" + array.get(i) + "\nZutat");
            } else {
                stringToShow.add("Enthält\n" + array.get(i) + "\nZutaten");
            }

        }
        return stringToShow;
    }


    public ArrayList<Integer> findPositionIdResearch(int idIngredientMatched) {
        ArrayList<Integer> idResearched = new ArrayList<>();
        for (int i = 0; i < savedReceiveMatches.size(); i++) {
            if (savedReceiveMatches.get(i) == idIngredientMatched) {
                idResearched.add((i));
            }
        }
        return idResearched;

    }


}

