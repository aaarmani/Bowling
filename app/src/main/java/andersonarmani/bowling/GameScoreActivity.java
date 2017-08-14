package andersonarmani.bowling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import andersonarmani.bowling.model.FinalFrame;
import andersonarmani.bowling.model.Game;
import andersonarmani.bowling.model.GameImpl;
import andersonarmani.bowling.model.Player;

public class GameScoreActivity extends AppCompatActivity {
    private static final String TAG = GameScoreActivity.class.getSimpleName();
    public static final String INTENT_EXTRA = "IT Extras";

    private Game mGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_score);

        List<String> listUsers = (List<String>) getIntent().getSerializableExtra(INTENT_EXTRA);

        mGame = new GameImpl();
        mGame.createPlayers(listUsers);
        mGame.start();
        getNewShot(); //Remove it from here
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    void getNewShot() {
        Log.d(TAG, "getNewShot()");
        Intent it = new Intent(this, NewShotActivity.class);
        startActivityForResult(it, NewShotActivity.NEW_SHOT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult = " + requestCode);
        if(requestCode == NewShotActivity.NEW_SHOT) {
            int randomNumber = data.getIntExtra(NewShotActivity.RESULT, 0);
            Log.d(TAG, "onActivityResult Random number = " + randomNumber);
            gameNewShot(randomNumber);
        }
    }

    void gameNewShot(int numberOfPins) {
        int playerIndex = mGame.getPlayerIndex();
        mGame.newShot(numberOfPins);

        if(mGame.isFinish()) {
           //ShowResult
        }
        else {
            showScore(playerIndex);
            getNewShot();
        }
    }

    void showScore(int playerIndex) {
        FinalFrame frame = (FinalFrame) mGame.getPlayerFrame(playerIndex, mGame.getRoundIndex());

        Player player = mGame.getPlayers().get(playerIndex);
        Log.d(TAG, "PLAYER = " + player.getName());
        Log.d(TAG, "Score = " + frame.getScore());
    }
}
