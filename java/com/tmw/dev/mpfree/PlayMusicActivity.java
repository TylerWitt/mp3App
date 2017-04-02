package com.tmw.dev.mpfree;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class PlayMusicActivity extends AppCompatActivity {
    private static MediaPlayer gMediaPlayer;
    private static int gintCounter;
    private static Button gbtnPlay;
    private static Button gbtnPause;

    //region Protected overrides
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        gMediaPlayer = new MediaPlayer();
        Intent ittPassedMusic = getIntent();
        Uri uriSong = ittPassedMusic.getParcelableExtra(MainActivity.MUSIC_URI);
        gbtnPlay = (Button) findViewById(R.id.btnPlay);
        gbtnPause = (Button) findViewById(R.id.btnPause);
        gintCounter = 0;

        gMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try{
            gMediaPlayer.setDataSource(getApplicationContext(), uriSong);
            gMediaPlayer.prepare();
        }
        catch (IOException e) {
            System.out.println(e.toString());
            System.out.println("File not valid: " + uriSong.toString());
        }
        playMusic();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        gMediaPlayer.stop();
        gMediaPlayer.release();
        gMediaPlayer = null;
    }
    //endregion

    //region OnClick Functions
    public void ockPause(View pbtnView){
        if (gMediaPlayer.isPlaying()){
            pauseMusic();
        }
    }

    public void ockPlay(View pbtnView){
        if (!gMediaPlayer.isPlaying()){
            playMusic();
        }
    }

    public void ockNext(View pbtnView){
        //TODO: Figure out how to get next song
    }

    public void ockPrev(View pbtnView){
        //TODO: Figure out how to get previous song. Store URIs? Depends on how next song is found.
    }
    //endregion

    private void playMusic(){
        gMediaPlayer.seekTo(gintCounter);
        gMediaPlayer.start();
        gbtnPlay.setVisibility(View.GONE);
        gbtnPause.setVisibility(View.VISIBLE);
    }

    private void pauseMusic(){
        gMediaPlayer.pause();
        gintCounter = gMediaPlayer.getCurrentPosition();
        gbtnPlay.setVisibility(View.VISIBLE);
        gbtnPause.setVisibility(View.GONE);
    }
}
