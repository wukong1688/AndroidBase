package com.jack.androidbase.activity;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jack.androidbase.R;

public class MediaMainActivity extends AppCompatActivity {
    private boolean isRelease = true;
    private MediaPlayer mPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_main);

        initMedia();
    }

    private void initMedia() {
        if (isRelease) {
            mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.a001);
            mPlayer.setLooping(true);
            isRelease = false;
        }
    }

    /**
     * 简单播放 MediaPlayer
     *
     * @param v
     */
    protected void audioOptBase(View v) {
        switch (v.getId()) {
            case R.id.media_audio_play:
                mPlayer.start();
                break;
            case R.id.media_audio_pause:
                if (mPlayer.isPlaying()) {
                    mPlayer.pause();
                }
                break;
            case R.id.media_audio_stop:
                if (mPlayer.isPlaying()) {
                    mPlayer.reset();
                    isRelease = true;
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
        }
    }
}
