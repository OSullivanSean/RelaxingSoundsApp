package appjam.com.relaxingsounds.controller;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import java.util.List;
import appjam.com.relaxingsounds.R;
import appjam.com.relaxingsounds.audio.LoopMediaPlayer;
import appjam.com.relaxingsounds.config.SoundConfigManager;

public class SoundController {

    private Context context;
    private SoundConfigManager soundConfigManager;
    private LoopMediaPlayer mediaPlayer;
    private SeekBar seekbar;
    private Spinner spinner;

    private int seekbarId;
    private int spinnerId;


    public SoundController(Context context, int spinnerId, int seekbarId){
        this.context = context;
        this.seekbarId = seekbarId;
        this.spinnerId = spinnerId;
        setup();
    }

    private void setup(){
        setupSoundConfigManager();
        setupSpinner();
        setupMediaPlayer();
        setupSeekbar();
    }

    private void setupSoundConfigManager() {
        soundConfigManager = SoundConfigManager.getInstance();
    }

    private void setupSpinner(){
        spinner = (Spinner)((Activity)context).findViewById(spinnerId);
        setupSpinnerEntries();
        setupSpinnerChangeListener();
    }

    private void setupSpinnerEntries(){
        List<String> names = soundConfigManager.getSoundConfigNames();
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(context,
                android.R.layout.simple_spinner_dropdown_item,
                names);
        spinner.setAdapter(spinnerArrayAdapter);
    }

    private void setupSpinnerChangeListener(){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mediaPlayer.stop();
                setupMediaPlayer();
                seekbar.setProgress(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setupMediaPlayer(){
        int audioId = getSelectedItemAudioId();
        mediaPlayer = LoopMediaPlayer.create(context, audioId);

    }

    private int getSelectedItemAudioId(){
        String name = spinner.getSelectedItem().toString();
        if(name == null || name.isEmpty()){
            return R.raw.ship_at_sea_ambience;
        }
        return soundConfigManager.getSoundConfig(name).getAudioId();
    }

    private void setupSeekbar() {
        seekbar = (SeekBar) ((Activity)context).findViewById(seekbarId);
        setupSeekbarChangeListener();
    }

    private void setupSeekbarChangeListener(){
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mediaPlayer.setVolume((float)progress/100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
