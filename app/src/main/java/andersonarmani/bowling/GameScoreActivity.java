package andersonarmani.bowling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import andersonarmani.bowling.model.FinalFrame;
import andersonarmani.bowling.model.Game;
import andersonarmani.bowling.model.GameImpl;
import andersonarmani.bowling.model.Player;

public class GameScoreActivity extends AppCompatActivity {
    private static final String TAG = GameScoreActivity.class.getSimpleName();
    public static final String INTENT_EXTRA = "IT Extras";
    private Button mBtnShot;
    private TextView mTxtPlayerName;
    private TextView mTxtText;

    private Random mRandom;
    private Game mGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_score);

        mBtnShot = (Button) findViewById(R.id.btnShot);
        mTxtPlayerName = (TextView) findViewById(R.id.txtPlayerName);
        mTxtText = (TextView) findViewById(R.id.txtPlayerTxt);

        List<String> listUsers = (List<String>) getIntent().getSerializableExtra(INTENT_EXTRA);

        mGame = new GameImpl();
        mGame.createPlayers(listUsers);
        mGame.start();
        //getNewShot(); //Remove it from here

        mRandom = new Random();
        mBtnShot.setOnClickListener(OnClickCallback);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    void getNewShot() {
        /*Log.d(TAG, "getNewShot()");
        Intent it = new Intent(this, NewShotActivity.class);
        startActivityForResult(it, NewShotActivity.NEW_SHOT);*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*Log.d(TAG, "onActivityResult = " + requestCode);
        if(requestCode == NewShotActivity.NEW_SHOT) {
            int randomNumber = data.getIntExtra(NewShotActivity.RESULT, 0);
            Log.d(TAG, "onActivityResult Random number = " + randomNumber);
            gameNewShot(randomNumber);
        }*/
    }

    /**
     * Click handler
     */
    private View.OnClickListener OnClickCallback = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int randomNumber = mRandom.nextInt(11); //0 to 10
            gameNewShot(randomNumber);
        }
    };

    void gameNewShot(int numberOfPins) {
        Log.d(TAG, "gameNewShot = " + numberOfPins);
        int playerIndex = mGame.getPlayerIndex();
        int roundIndex = mGame.getRoundIndex();
        Log.d(TAG, "Round INDEX = " + roundIndex);

        if(mGame.isFinish()) {
           //ShowResult
            Log.d(TAG, "GAME FINISH");
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("GAME FINSHED\n");

            for(int i = 0; i < mGame.getPlayers().size(); i++) {
                Player player = mGame.getPlayers().get(i);
                Log.d(TAG, "Player " + player.getName());
                Log.d(TAG, "Score TOTAL =  " + player.getTotalScore());

                stringBuffer.append("Player ");
                stringBuffer.append(player.getName());
                stringBuffer.append("\tScore TOTAL =  ");
                stringBuffer.append(player.getTotalScore());
                stringBuffer.append("\n");
            }
            mTxtText.setText(stringBuffer);
        }
        else {
            mGame.newShot(numberOfPins);
            showScore(playerIndex, roundIndex);
            getNewShot();
        }
    }

    void showScore(int playerIndex, int roundIndex) {
        FinalFrame frame = (FinalFrame) mGame.getPlayerFrame(playerIndex, roundIndex);

        Player player = mGame.getPlayers().get(playerIndex);
        Log.d(TAG, "PLAYER = " + player.getName());
        Log.d(TAG, "Score = " + frame.getScore());

        mTxtPlayerName.setText(player.getName());
        //mTxtText.setText();
    }
}
