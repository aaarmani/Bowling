package andersonarmani.bowling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class NewShotActivity extends AppCompatActivity {
    private static final String TAG = NewShotActivity.class.getSimpleName();
    public static final int NEW_SHOT = 1;
    public static final String RESULT = "result";
    private Button mBtnNewShot;
    private TextView mTxtShotText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_shot);

        mBtnNewShot = (Button) findViewById(R.id.btnNewShot);
        mTxtShotText = (TextView) findViewById(R.id.btnNewShot);
        mBtnNewShot.setOnClickListener(onClickCallback);
    }

    //Get random number and return it for the previous activity
    private void getRandom() {
        Random random = new Random();
        int randomNumber = random.nextInt(10);
        Log.d(TAG, "getRandom = " + randomNumber);

        String strRandom = getString(R.string.generate_value) + randomNumber;
        mTxtShotText.setText(strRandom);

        returnResult(randomNumber);
    }

    private void returnResult(int randomNumber) {
        Log.d(TAG, "returnResult = " + randomNumber);
        Intent data = new Intent();
        data.putExtra(RESULT, randomNumber);
        setResult(RESULT_OK, data);
        finish();
    }

    /**
     * Button action
     */
    View.OnClickListener onClickCallback = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getRandom();
        }
    };
}
