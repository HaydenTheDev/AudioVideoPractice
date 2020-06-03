package hayden.project.audiovideopractice;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.VolumeAutomation;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //UI Components
    private SeekBar seekBar;
    private VideoView myVideoView;
    private Button myVideoButton, playMusicBtn, pauseMusicBtn;
    private MediaController mediaController;
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private TextView volumeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializations
        myVideoView = findViewById(R.id.myVideoView);
        playMusicBtn = findViewById(R.id.playMusicBtn);
        pauseMusicBtn = findViewById(R.id.pauseMusicBtn);
        myVideoButton = findViewById(R.id.videoButton);
        volumeText = findViewById(R.id.volumeText);
        seekBar = findViewById(R.id.seekBar);
        myVideoButton.setOnClickListener(MainActivity.this);
        playMusicBtn.setOnClickListener(MainActivity.this);
        pauseMusicBtn.setOnClickListener(MainActivity.this);

        mediaController = new MediaController(MainActivity.this);
        mediaPlayer = new MediaPlayer();

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ukulele);
        audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);//cast to audioManager

        //setting up seekbar for volume control
        int maximumVolumeOfUserDevice = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolumeOfUserDevice = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        seekBar.setMax(maximumVolumeOfUserDevice);
        seekBar.setProgress(currentVolumeOfUserDevice);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                   Toast.makeText(MainActivity.this,
                           Integer.toString(progress),
                           Toast.LENGTH_SHORT).show();
                   volumeText.setText(progress);
                   audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    @Override
    public void onClick(View buttonView) {

        switch (buttonView.getId()){
            case R.id.videoButton:
                Uri videoUri = Uri.parse("android.resource://" + getPackageName()
                        + "/" + R.raw.video1);

                //setupVideoPlayer with MediaController
                myVideoView.setVideoURI(videoUri);
                myVideoView.setMediaController(mediaController);
                mediaController.setAnchorView(myVideoView);
                myVideoView.start();
                break;
            case R.id.playMusicBtn:
                mediaPlayer.start();
                break;
            case R.id.pauseMusicBtn:
                onPause();
                break;
        }


    }
    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mediaPlayer.start();
    }
}