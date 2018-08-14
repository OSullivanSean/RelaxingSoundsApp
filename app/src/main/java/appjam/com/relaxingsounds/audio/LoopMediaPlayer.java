package appjam.com.relaxingsounds.audio;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

/**
 * Manages the seamless looping of an audio track
 */
public class LoopMediaPlayer {
    public static final String TAG = LoopMediaPlayer.class.getSimpleName();

    private Context mContext = null;
    private MediaPlayer mCurrentPlayer = null;
    private MediaPlayer mNextPlayer = null;

    private int mResId = 0;
    private int mCounter = 1;
    private float currentVolume = 0;

    public static LoopMediaPlayer create(Context context, int resId) {
        return new LoopMediaPlayer(context, resId);
    }

    public void setVolume(float volume){
        mCurrentPlayer.setVolume(volume, volume);
        mNextPlayer.setVolume(volume, volume);
        currentVolume = volume;
    }

    public void stop(){
        mCurrentPlayer.stop();
        mNextPlayer.stop();
    }

    private LoopMediaPlayer(Context context, int resId) {
        mContext = context;
        mResId = resId;

        mCurrentPlayer = MediaPlayer.create(mContext, mResId);
        mCurrentPlayer.setVolume(currentVolume, currentVolume);
        mCurrentPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mCurrentPlayer.start();
            }
        });

        createNextMediaPlayer();
    }

    private void createNextMediaPlayer() {
        mNextPlayer = MediaPlayer.create(mContext, mResId);
        mNextPlayer.setVolume(currentVolume, currentVolume);
        mCurrentPlayer.setNextMediaPlayer(mNextPlayer);
        mCurrentPlayer.setOnCompletionListener(onCompletionListener);
    }

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.release();
            mCurrentPlayer = mNextPlayer;

            createNextMediaPlayer();

            Log.d(TAG, String.format("Loop #%d", ++mCounter));
        }
    };
}
