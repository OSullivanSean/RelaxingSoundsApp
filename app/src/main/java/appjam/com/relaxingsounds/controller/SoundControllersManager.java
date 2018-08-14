package appjam.com.relaxingsounds.controller;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import appjam.com.relaxingsounds.R;

/**
 * Manages a list of SoundControllers
 */
public class SoundControllersManager {

    private static SoundControllersManager soundControllersManager;

    private Context context;
    private List<SoundController> soundControllers;

    private SoundControllersManager(Context context){
        this.context = context;
        soundControllers = new ArrayList<>();
    }

    public static SoundControllersManager getInstance(Context context){
        if(soundControllersManager == null){
            soundControllersManager = new SoundControllersManager(context);
        }
        return soundControllersManager;
    }

    public void setupSoundControllers(){ ;

        createSoundController(R.id.spin_sound_001, R.id.seek_volume_001);
        createSoundController(R.id.spin_sound_002, R.id.seek_volume_002);
        createSoundController(R.id.spin_sound_003, R.id.seek_volume_003);
        createSoundController(R.id.spin_sound_004, R.id.seek_volume_004);
        createSoundController(R.id.spin_sound_005, R.id.seek_volume_005);
        createSoundController(R.id.spin_sound_006, R.id.seek_volume_006);
    }

    private void createSoundController(int spinnerId, int seekbarId){
        soundControllers.add(new SoundController(context, spinnerId, seekbarId));
    }

}
