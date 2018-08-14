package appjam.com.relaxingsounds.audio;

public class SoundConfig {

    private String name;
    private int audioId;


    public SoundConfig(String name, int audioId){
        this.name = name;
        this.audioId = audioId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAudioId() {
        return audioId;
    }

    public void setAudioId(int audioId) {
        this.audioId = audioId;
    }

}
