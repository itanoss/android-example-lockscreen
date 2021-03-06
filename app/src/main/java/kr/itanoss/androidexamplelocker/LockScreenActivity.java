package kr.itanoss.androidexamplelocker;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LockScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        makeFullScreen();
        super.onCreate(savedInstanceState);


        startService(new Intent(this, LockService.class));

        setContentView(R.layout.activity_lockscreen);
        ButterKnife.bind(this);
    }

    public void makeFullScreen() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|WindowManager.LayoutParams.FLAG_FULLSCREEN|
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        int visibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        if (Build.VERSION.SDK_INT < 19) { //View.SYSTEM_UI_FLAG_IMMERSIVE is only on API 19+
            visibility |= View.SYSTEM_UI_FLAG_IMMERSIVE;
        }
        this.getWindow().getDecorView().setSystemUiVisibility(visibility);
    }

    @OnClick(R.id.unlock)
    public void unlock() {
        finish();
    }
}
