package andersonarmani.bowling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import andersonarmani.bowling.model.Game;
import andersonarmani.bowling.model.GameImpl;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> listPlayerName = new ArrayList<>();
        listPlayerName.add("Player 1 Test");
        listPlayerName.add("Player 2 Test");

        Intent it = new Intent(this, GameScoreActivity.class);
        it.putExtra(GameScoreActivity.INTENT_EXTRA, (Serializable) listPlayerName);
        startActivity(it);
    }
}
