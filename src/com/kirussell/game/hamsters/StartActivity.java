package com.kirussell.game.hamsters;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends BaseActivity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initButtons();
    }

    private void initButtons() {
        findViewById(R.id.btnStartGame).setOnClickListener(this);
        findViewById(R.id.btnGameOptions).setOnClickListener(this);
        findViewById(R.id.btnAbout).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStartGame:
                //TODO start game seletion activity
                startActivity(new Intent(this,GameActivity.class));
                break;
            case R.id.btnGameOptions:
                //TODO start options activity
                break;
            case R.id.btnAbout:
                //TODO start about activity
                break;
            default:
                break;
        }
    }
}
