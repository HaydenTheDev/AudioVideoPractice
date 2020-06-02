package hayden.project.audiovideopractice;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.net.URI;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private VideoView myVideoView;
    private Button myButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myVideoView = findViewById(R.id.myVideoView);
        myButton = findViewById(R.id.button);
        myVideoView.setOnClickListener(MainActivity.this);



    }

    @Override
    public void onClick(View view) {

        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video);

        myVideoView.setVideoURI(videoUri);
        myVideoView.start();

    }
}