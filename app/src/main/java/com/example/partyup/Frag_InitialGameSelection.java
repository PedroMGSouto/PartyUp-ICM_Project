package com.example.partyup;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.google.firebase.database.core.utilities.Tree;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Frag_InitialGameSelection extends Fragment {
    private RecyclerView recyclerView;
    //OAuth Token zbek1nb33pn3zzadc9js3z6cc0yl7j
    private TreeSet<String> gameList = new TreeSet<>();
    private static TreeSet<String> selectedGames = new TreeSet<>();


    public Frag_InitialGameSelection() {
        // Required empty public constructor
        gameList.add("Sword Art Online");
        gameList.add("Among Us");
        gameList.add("GTA V");
        gameList.add("League of Legends");
        gameList.add("Hearthstone");
        gameList.add("CS:GO");
        gameList.add("Genshin Impact");
        gameList.add("OSU!");
        gameList.add("Dota");
        gameList.add("Pokemon Go");
        gameList.add("Apex Legends");
        gameList.add("Rocket League");
        gameList.add("PubG");
        gameList.add("Minecraft");
        gameList.add("COD: Warzone");
        gameList.add("Overwatch");
        gameList.add("Destiny 2");
        gameList.add("Rainbow Six Siege");
        gameList.add("Black Desert Online");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_frag__initial_game_selection, container, false);


        SearchView sv = (SearchView) view.findViewById(R.id.searchGame);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Stream<String> filteredgameList = gameList.stream().filter(word -> word.toLowerCase().startsWith(newText.toLowerCase()));
                TreeSet<String> set = filteredgameList.collect(Collectors.toCollection(TreeSet::new));
                recyclerView.setAdapter(new GameListAdapter(getContext(),set));
                return false;
            }
        });
        String filter = sv.getQuery().toString();

         

        // Add the following lines to create RecyclerView
        recyclerView = view.findViewById(R.id.gameRecicler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new GameListAdapter(getContext(),gameList));


        return view;
    }
    public static void addSelectedGames(String game){
        selectedGames.add(game);
    }

}