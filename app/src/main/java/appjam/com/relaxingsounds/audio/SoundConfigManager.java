package appjam.com.relaxingsounds.audio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import appjam.com.relaxingsounds.R;

public class SoundConfigManager {

    private static SoundConfigManager soundConfigManager;

    private Map<String, SoundConfig> soundConfigs;

    private SoundConfigManager(){
        this.soundConfigs = parseSoundConfigs();
    }

    public static SoundConfigManager getInstance(){
        if(soundConfigManager == null){
            soundConfigManager = new SoundConfigManager();
        }
        return soundConfigManager;
    }

    public List<SoundConfig> getSoundConfigs() {
        return new ArrayList<>(soundConfigs.values());
    }

    public List<String> getSoundConfigNames(){
        return new ArrayList<>(soundConfigs.keySet());
    }

    public SoundConfig getSoundConfig(String name){
        return soundConfigs.get(name);
    }

    private Map<String, SoundConfig> parseSoundConfigs(){
        //TODO parse sound configs from soundConfigs.json
        Map<String, SoundConfig> soundConfigMap = new HashMap<>();
        soundConfigMap.put("ocean", new SoundConfig("ocean", R.raw.ship_at_sea_ambience));
        soundConfigMap.put("glocken", new SoundConfig("glocken", R.raw.glocken));
        return soundConfigMap;
    }

}
