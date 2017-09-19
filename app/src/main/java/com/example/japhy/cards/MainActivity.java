package com.example.japhy.cards;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.util.Collections;
import java.util.Arrays;
import android.widget.TextView;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    ImageView down;
    ImageView up;
    TextView tryCounter;
    TextView counter;
    TextView scoreCounter;
    ImageView image1;
    Button leftButton;
    Button rightButton;
    Button again;
    Button shuffle;
    String number1 = "";
    String letter1 = "";
    String number2 = "";
    String letter2 = "";
    int score;
    int i = 0;
    int c = 0;
    int tries = 1;
    final String arrayName = "arrayName";
    final String[] deck = {"hearts1","hearts2","hearts3","hearts4","hearts5","hearts6","hearts7","hearts8","hearts9","hearts10","hearts11","hearts12","hearts13",
            "diamonds1","diamonds2","diamonds3","diamonds4","diamonds5","diamonds6","diamonds7","diamonds8","diamonds9","diamonds10","diamonds11","diamonds12","diamonds13",
            "clubs1","clubs2","clubs3","clubs4","clubs5","clubs6","clubs7","clubs8","clubs9","clubs10","clubs11","clubs12","clubs13",
            "spades1","spades2","spades3","spades4","spades5","spades6","spades7","spades8","spades9","spades10","spades11","spades12","spades13"};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        String[] array = deck;
        SharedPreferences prefs = getSharedPreferences("deck", 0);
        if (!prefs.contains("initialized")){
            Collections.shuffle(Arrays.asList(deck));
            // arrayName = "arrayName";
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("initialized", true);
            editor.putInt(arrayName +"_size", array.length);
            for(int i=0;i<deck.length;i++)
                editor.putString(arrayName + "_" + i, deck[i]);
                System.out.println(deck[i]);
            editor.apply();
        }
        //fucks everything up ofc they arent equal
        //SharedPreferences prefs = getSharedPreferences("deck", 0);
        //SharedPreferences.Editor editor = prefs.edit();
        int size = prefs.getInt(arrayName + "_size", deck.length);
        for(int i=0;i<size;i++)
            deck[i] = prefs.getString(arrayName + "_" + i, null);




        counter=(TextView)findViewById(R.id.counterID);
        image1=(ImageView)findViewById(R.id.imageId);
        leftButton=(Button)findViewById(R.id.button1);
        rightButton=(Button)findViewById(R.id.button2);
        again=(Button)findViewById(R.id.againID);
        shuffle=(Button)findViewById(R.id.shuffleID);
        tryCounter=(TextView)findViewById(R.id.triesID);
        scoreCounter=(TextView)findViewById(R.id.scoreID);
        down=(ImageView)findViewById(R.id.downId);
        up=(ImageView)findViewById(R.id.upId);
        SharedPreferences sharedPref= getSharedPreferences("score", 0);
        SharedPreferences Pref= getSharedPreferences("tries", 0);
        tries = Pref.getInt("tries", 0);
        score = sharedPref.getInt("score", 0);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                down.setVisibility(View.VISIBLE);
                up.setVisibility(View.VISIBLE);

                if (i == 0) {

                    String variableValue = (deck[i]);
                    image1.setImageResource(getResources().getIdentifier(variableValue, "drawable", getPackageName()));
                    i = i + 1;
                    counter.setText(c + "/52");
                    if(tries > 1){
                        tryCounter.setText(tries + " Tries");
                    }
                    if(score > 1){
                        scoreCounter.setText("Highscore: " + score);
                    }
                    scoreCounter.setVisibility(View.VISIBLE);
                    tryCounter.setVisibility(View.VISIBLE);
                    System.out.println("counter should say " + c + "/52");
                    c++;
                } else if (i < 52) {

                    for (int n = 0; n < deck[i-1].length(); n++) {
                        char a = deck[i-1].charAt(n);
                        if (Character.isDigit(a)) {
                            number1 = number1 + a;

                        } else {
                            letter1 = letter1 + a;
                        }

                    }
                    for (int n = 0; n < deck[i].length(); n++) {
                        char a = deck[i].charAt(n);
                        if (Character.isDigit(a)) {
                            number2 = number2 + a;

                        } else {
                            letter2 = letter2 + a;
                        }

                    }
                    if(Integer.parseInt(number1) == 1){
                        number1 = "14";
                    }
                    if(Integer.parseInt(number2) == 1){
                        number2 = "14";
                    }

                    if(Integer.parseInt(number1) >= Integer.parseInt(number2)){
                        String variableValue = (deck[i]);
                        image1.setImageResource(getResources().getIdentifier(variableValue, "drawable", getPackageName()));
                        counter.setText(c + "/52");
                        System.out.println("last number was: " + number1 + "  number is: " + number2);
                        if(c > score){
                            score++;
                            SharedPreferences sharedPref= getSharedPreferences("score", 0);
                            //now get Editor
                            SharedPreferences.Editor mEditor= sharedPref.edit();
                            //put your value
                            mEditor.putInt("score", score);
                            //commits your edits
                            mEditor.commit();
                            scoreCounter.setText("Highscore: " + score);
                        }
                        c++;
                        i++;
                        number1 = "";
                        letter1 = "";
                        number2 = "";
                        letter2 = "";
                    }
                    else{
                        System.out.println("last number was: " + number1 + "  number is: " + number2);
                        image1.setImageResource(getResources().getIdentifier("lose", "drawable", getPackageName()));
                        again.setVisibility(View.VISIBLE);
                        shuffle.setVisibility(View.VISIBLE);
                        down.setVisibility(View.INVISIBLE);
                        up.setVisibility(View.INVISIBLE);
                        leftButton.setEnabled(false);
                        rightButton.setEnabled(false);


                    }

                } else {
                    counter.setText("52/52");
                    image1.setImageResource(getResources().getIdentifier("trophy", "drawable", getPackageName()));
                    again.setVisibility(View.VISIBLE);
                    //shuffle.setVisibility(View.VISIBLE);
                    down.setVisibility(View.INVISIBLE);
                    up.setVisibility(View.INVISIBLE);
                    leftButton.setEnabled(false);
                    rightButton.setEnabled(false);
                }

            }

        });

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                down.setVisibility(View.VISIBLE);
                up.setVisibility(View.VISIBLE);
                if (i == 0) {
                    String variableValue = (deck[i]);
                    image1.setImageResource(getResources().getIdentifier(variableValue, "drawable", getPackageName()));
                    i = i + 1;
                    counter.setText(c + "/52");
                    c++;
                    if(score > 1){
                        scoreCounter.setText("Score: " + score);
                    }
                    //I wasnt sober for the next if statement
                    if(tries > 1){
                        tryCounter.setText(tries + " Tries");
                    }
                    scoreCounter.setVisibility(View.VISIBLE);
                    tryCounter.setVisibility(View.VISIBLE);
                } else if (i < 52) {

                    for (int n = 0; n < deck[i-1].length(); n++) {
                        char a = deck[i-1].charAt(n);
                        if (Character.isDigit(a)) {
                            number1 = number1 + a;

                        } else {
                            letter1 = letter1 + a;
                        }

                    }
                    for (int n = 0; n < deck[i].length(); n++) {
                        char a = deck[i].charAt(n);
                        if (Character.isDigit(a)) {
                            number2 = number2 + a;

                        } else {
                            letter2 = letter2 + a;
                        }

                    }
                    if(Integer.parseInt(number1) == 1){
                        number1 = "14";
                    }
                    if(Integer.parseInt(number2) == 1){
                        number2 = "14";
                    }

                    if(Integer.parseInt(number1) <= Integer.parseInt(number2)){
                        String variableValue = (deck[i]);
                        image1.setImageResource(getResources().getIdentifier(variableValue, "drawable", getPackageName()));
                        i = i + 1;
                        System.out.println("last number was: " + number1 + " next number is: " + number2);
                        counter.setText(c + "/52");
                        if(c > score){
                            score++;
                            SharedPreferences sharedPref= getSharedPreferences("score", 0);
                            //now get Editor
                            SharedPreferences.Editor mEditor= sharedPref.edit();
                            //put your value
                            mEditor.putInt("score", score);
                            //commits your edits
                            mEditor.commit();
                            scoreCounter.setText("Highscore: " + score);
                        }
                        c++;
                        number1 = "";
                        letter1 = "";
                        number2 = "";
                        letter2 = "";
                    }
                    else{
                        System.out.println("last number was: " + number1 + "  number is: " + number2);
                        image1.setImageResource(getResources().getIdentifier("lose", "drawable", getPackageName()));
                        again.setVisibility(View.VISIBLE);
                        shuffle.setVisibility(View.VISIBLE);
                        down.setVisibility(View.INVISIBLE);
                        up.setVisibility(View.INVISIBLE);
                        leftButton.setEnabled(false);
                        rightButton.setEnabled(false);
                    }

                } else {
                    counter.setText("52/52");
                    image1.setImageResource(getResources().getIdentifier("trophy", "drawable", getPackageName()));
                    again.setVisibility(View.VISIBLE);
                    //shuffle.setVisibility(View.VISIBLE);
                    down.setVisibility(View.INVISIBLE);
                    up.setVisibility(View.INVISIBLE);
                    leftButton.setEnabled(false);
                    rightButton.setEnabled(false);
                }

            }

        });


    }
    public void reset(View view){
        i = 0;
        c = 0;
        String variableValue = (deck[i]);
        image1.setImageResource(getResources().getIdentifier(variableValue, "drawable", getPackageName()));
        i = i + 1;
        counter.setText(c + "/52");
        again.setVisibility(View.INVISIBLE);
        shuffle.setVisibility(View.INVISIBLE);
        down.setVisibility(View.VISIBLE);
        up.setVisibility(View.VISIBLE);
        c++;
        tries++;
        SharedPreferences Pref= getSharedPreferences("tries", 0);
        //now get Editor
        SharedPreferences.Editor mEditor= Pref.edit();
        //put your value
        mEditor.putInt("tries", tries);
        //commits your edits
        mEditor.commit();
        number1 = "";
        letter1 = "";
        number2 = "";
        letter2 = "";
        tryCounter.setText(tries + " Tries");
        leftButton.setEnabled(true);
        rightButton.setEnabled(true);

    }
    public void shuffle(View view){
        String[] array = deck;
        SharedPreferences prefs = getSharedPreferences("deck", 0);
        Collections.shuffle(Arrays.asList(deck));
        // arrayName = "arrayName";
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("initialized", true);
        editor.putInt(arrayName +"_size", array.length);
        for(int j=0;j<deck.length;j++)
            editor.putString(arrayName + "_" + j, deck[j]);
        editor.apply();
        i = 0;
        c = 0;
        String variableValue = (deck[i]);
        image1.setImageResource(getResources().getIdentifier(variableValue, "drawable", getPackageName()));
        i = i + 1;
        counter.setText(c + "/52");
        again.setVisibility(View.INVISIBLE);
        shuffle.setVisibility(View.INVISIBLE);
        down.setVisibility(View.VISIBLE);
        up.setVisibility(View.VISIBLE);
        c++;
        tries = 1;
        SharedPreferences Pref= getSharedPreferences("tries", 0);
        //now get Editor
        SharedPreferences.Editor mEditor= Pref.edit();
        //put your value
        mEditor.putInt("tries", tries);
        //commits your edits
        mEditor.commit();
        number1 = "";
        letter1 = "";
        number2 = "";
        letter2 = "";
        tryCounter.setText(tries + " Tries");
        leftButton.setEnabled(true);
        rightButton.setEnabled(true);

    }
}
