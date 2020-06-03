package hayden.project.audiovideopractice;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private VideoView myVideoView;
    private Button myVideoButton, playMusicBtn, pauseMusicBtn;
    private MediaController mediaController;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myVideoView = findViewById(R.id.myVideoView);
        playMusicBtn = findViewById(R.id.playMusicBtn);
        pauseMusicBtn = findViewById(R.id.pauseMusicBtn);
        myVideoButton = findViewById(R.id.videoButton);
        myVideoButton.setOnClickListener(MainActivity.this);
        playMusicBtn.setOnClickListener(MainActivity.this);
        pauseMusicBtn.setOnClickListener(MainActivity.this);

        mediaPlayer = new MediaPlayer();

        mediaController = new MediaController(MainActivity.this);



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
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ukulele);
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

}