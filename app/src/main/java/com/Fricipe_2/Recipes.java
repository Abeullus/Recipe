package com.Fricipe_2;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.Fricipe_2.Helper.DatabaseHelper;
import com.Fricipe_2.Helper.PictureHelper;
import com.Fricipe_2.Model.RecipeItem;

import java.util.ArrayList;
import java.util.Date;


public class Recipes extends Fragment {
    PictureHelper pictureHelper = new PictureHelper();
    ArrayList<RecipeItem> bestList;
    ArrayList<RecipeItem> newList;

    Date today = new Date();

    public Recipes() {
    }

    //Rezepte wurden manuel eingepflegt und könnten bei Bedarf mit einer API erweitert werden.

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);


        GridView newGridView = view.findViewById(R.id.GridView_New);

        //Verbindung mit der Datenbank wird hergestellt
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext(), "Recipes.db", null, 1);

        ArrayList<RecipeItem> defaultDataList = databaseHelper.allRecipes();
        if (defaultDataList == null || defaultDataList.size() == 0) {
            Drawable drawable = getResources().getDrawable(R.drawable.eiernudeln, getActivity().getTheme());
            byte[] eiernudeln = pictureHelper.getByteArrayFromDrawable(drawable);

            databaseHelper.insertRecipe("Deutsch", "Eiernudeln", "thomas", today.toString(),
                    "1. Nudeln \n 2.Eier \n 3. Mehl", "traditionelles deutsches Essen",
                    eiernudeln, eiernudeln, 0);

            int eiernudelnId = databaseHelper.GetIdByRecipeName("Eiernudeln");
            ArrayList<String> eiernudelnIngre = new ArrayList<>();
            eiernudelnIngre.add("Pasta");
            eiernudelnIngre.add("egg");
            eiernudelnIngre.add("Mehl");


            for (int i = 0; i < eiernudelnIngre.size(); i++) {
                databaseHelper.insertIngredients(eiernudelnId, eiernudelnIngre.get(i));
            }


            //Rezept 1
            drawable = getResources().getDrawable(R.drawable.lachsnudeln, getActivity().getTheme());
            byte[] Lachsnudeln = pictureHelper.getByteArrayFromDrawable(drawable);

            databaseHelper.insertRecipe("Italienisch", "Lachsnudeln", "Fabian", today.toString(),
                    "1. Die Ziebeln und den Knoblauch in kleine Stücke schneiden und in einer Pfanne anbraten.\n" +
                            "2. Den Lachs in die Pfanne dazugeben und scharf anbraten:\n" +
                            "3.Wasser aufsetzen und salzen.\n" +
                            "4. Nachdem das Wasser kocht Nudeln hinzufügen und 10 Minuten kochen lassen.\n" +
                            "5. Nudeln in die Pfanne dazugeben und gut durchmengen.\n" +
                            "6. Milch dazugeben und aufkochen lassen.\n" +
                            "7. Mit Pfeffer und Salz abschmecken", "Studentenessen",
                    Lachsnudeln, Lachsnudeln, 4);

            int LachsnudelnId = databaseHelper.GetIdByRecipeName("Lachsnudeln");
            ArrayList<String> lachsnudelnIngre = new ArrayList<>();
            lachsnudelnIngre.add("Fisch");
            lachsnudelnIngre.add("Zwiebeln");
            lachsnudelnIngre.add("Nudeln");
            lachsnudelnIngre.add("Milch");
            lachsnudelnIngre.add("Knoblauch");


            for (int i = 0; i < lachsnudelnIngre.size(); i++) {
                databaseHelper.insertIngredients(LachsnudelnId, lachsnudelnIngre.get(i));
            }


            //Rezept 2
            drawable = getResources().getDrawable(R.drawable.bolognese, getActivity().getTheme());
            byte[] bolognese = pictureHelper.getByteArrayFromDrawable(drawable);

            databaseHelper.insertRecipe("Italienisch", "Bolognese", "thomas", today.toString(),
                    "1.Zwiebeln und Knoblauch kleinschneiden und in der Pfanne anbraten.\n" +
                            "2. Wasser aufsetzen und Nudeln 10 Minuten Kochen lassen.\n" +
                            "3. Tomaten kleinschneiden und pyrieren.\n" +
                            "4. Das Hackfleisch scharf anbraten und Tomaten dazugeben. \n" +
                            "5. Etwas Milch dazugeben und gut umrühren.\n" +
                            "5. Nudeln abseihen und servieren.\n",
                    "klassisches italienisches Rezept.",
                    bolognese, bolognese, 6);

            int bologneseId = databaseHelper.GetIdByRecipeName("Bolognese");
            ArrayList<String> bologneseIngre = new ArrayList<>();
            bologneseIngre.add("Zwiebeln");
            bologneseIngre.add("Nudeln");
            bologneseIngre.add("Rind");
            bologneseIngre.add("Schwein");
            bologneseIngre.add("Tomaten");
            bologneseIngre.add("Knoblauch");
            bologneseIngre.add("Milch");

            for (int i = 0; i < bologneseIngre.size(); i++) {
                databaseHelper.insertIngredients(bologneseId, bologneseIngre.get(i));
            }


            //Rezept 3
            drawable = getResources().getDrawable(R.drawable.huenchen_nudeln, getActivity().getTheme());
            byte[] HühnchnNudeln = pictureHelper.getByteArrayFromDrawable(drawable);

            databaseHelper.insertRecipe("Italienisch", "HühnchenNudeln ", "fabian", today.toString(),
                    "1.Zwiebeln kleinschneiden und in der Pfanne anbraten.\n" +
                            "2. Wasser aufsetzen und Nudeln 10 Minuten Kochen lassen.\n" +
                            "4. Hühnchen anbraten und würzen.\n" +
                            "5. Tomaten kleinschneiden und pyrieren.\n" +
                            "6. Paprika kleinschneiden und anbraten." +
                            "7. Schwammerl kleinschneiden und anbraten." +
                            "8. Etwas Milch dazugeben und gut umrühren.\n" +
                            "9. Nudeln abseihen und servieren.\n",
                    "klassisches italienisches Rezept.",
                    HühnchnNudeln, HühnchnNudeln, 2);

            int HühnchnNudelnId = databaseHelper.GetIdByRecipeName("HühnchenNudeln ");
            ArrayList<String> HühnchnNudelnIngre = new ArrayList<>();
            HühnchnNudelnIngre.add("Hühnchen");
            HühnchnNudelnIngre.add("Schwammerl");
            HühnchnNudelnIngre.add("Nudeln");
            HühnchnNudelnIngre.add("Milch");
            HühnchnNudelnIngre.add("Paprika");
            HühnchnNudelnIngre.add("Tomaten");
            HühnchnNudelnIngre.add("Zwiebeln");

            for (int i = 0; i < HühnchnNudelnIngre.size(); i++) {
                databaseHelper.insertIngredients(HühnchnNudelnId, HühnchnNudelnIngre.get(i));
            }


            //Rezept 4
            drawable = getResources().getDrawable(R.drawable.chilli_con_carne, getActivity().getTheme());
            byte[] chili = pictureHelper.getByteArrayFromDrawable(drawable);

            databaseHelper.insertRecipe("Mexikaisch", "ChiliConCarne", "thomas", today.toString(),
                    "1. Knobluauch und Zwiebeln in kleine Stücke schneiden und gut anbraten.\n" +
                            "2. Wasser aufsetzen und Kartoffeln kochen.\n" +
                            "3. Fleisch nach wahl anbraten.\n" +
                            "4. Paprika und Tomaten klein würfeln und in die Pfanne dazugeben.\n" +
                            "5. Mais dazugeben und gut ene Stune köcheln lassen.\n" +
                            "6. Kartoffeln abseien und klein herschneiden und auch in die Pfanne dazugeben.\n" +
                            "7. Alles gut mit Salz und Pfeffer würzen.\n",

                    "auch am darauffolgnden Tag sehr lecker.",
                    chili, chili, 3);

            int chiliId = databaseHelper.GetIdByRecipeName("ChiliConCarne");
            ArrayList<String> chiliIngre = new ArrayList<>();
            chiliIngre.add("Rind");
            chiliIngre.add("Schwein");
            chiliIngre.add("Paprika");
            chiliIngre.add("Mais");
            chiliIngre.add("Tomate");
            chiliIngre.add("Kartoffeln");
            chiliIngre.add("Milch");
            chiliIngre.add("Knoblauch");

            for (int i = 0; i < chiliIngre.size(); i++) {
                databaseHelper.insertIngredients(chiliId, chiliIngre.get(i));
            }


            //Rezept 5
            drawable = getResources().getDrawable(R.drawable.wokpfanne, getActivity().getTheme());
            byte[] Wokpfanne = pictureHelper.getByteArrayFromDrawable(drawable);

            databaseHelper.insertRecipe("Chinesisch", "Wokpfanne", "fabian", today.toString(),
                    "1. Zutaten(Paprika,Schwammerl,Zwiebeln, Karotten) alle kleinschneiden .\n" +
                            "2. Wasser aufsetzen und Reis kochen lassen .\n" +
                            "3. Hühnchen würzen und scharf in der Pfanne anbraten.\n" +
                            "4. Die kleingeschnittenen Zutaten der Reihen nach in die Pfanne dazugeben und anbraten .\n" +
                            "5. Den Reis noch dazgeben und zehn Minuten köcheln lassen.\n",
                    "Sehr gesund und zum abnehen geeignet.",
                    Wokpfanne, Wokpfanne, 8);

            int WokpfanneId = databaseHelper.GetIdByRecipeName("Wokpfanne");
            ArrayList<String> WokpfanneIngre = new ArrayList<>();
            WokpfanneIngre.add("Hühnchen");
            WokpfanneIngre.add("Paprika");
            WokpfanneIngre.add("Schwammerl");
            WokpfanneIngre.add("Zwiebeln");
            WokpfanneIngre.add("Mais");
            WokpfanneIngre.add("Karotten");
            WokpfanneIngre.add("Reis");


            for (int i = 0; i < WokpfanneIngre.size(); i++) {
                databaseHelper.insertIngredients(WokpfanneId, WokpfanneIngre.get(i));
            }
        }


        TextView resultTextView = view.findViewById(R.id.txt_DBresult);

        newList = databaseHelper.recipeSelection();

        newGridView.setAdapter(new RecipeAdapter(this.getContext(), newList, R.layout.fragment_home_recipeitem));

        newGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                RecipeItem selectRecipe = newList.get(position);
                Intent intent = new Intent(getActivity(), RecipeActivity.class);
                intent.putExtra("Fricipe_2", selectRecipe.get_recipeName());
                startActivity(intent);
            }
        });
        return view;
    }

}
