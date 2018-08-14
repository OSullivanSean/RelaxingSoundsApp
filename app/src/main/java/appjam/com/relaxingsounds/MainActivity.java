package appjam.com.relaxingsounds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import appjam.com.relaxingsounds.controller.SoundControllersManager;

public class MainActivity extends AppCompatActivity {

    private SoundControllersManager soundControllersManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        soundControllersManager = SoundControllersManager.getInstance(this);
        soundControllersManager.setupSoundControllers();
    }


}
