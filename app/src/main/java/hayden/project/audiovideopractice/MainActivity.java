package hayden.project.audiovideopractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private VideoView myVideoView;
    private Button myButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myVideoView = findViewById(R.id.myVideoView);
        myButton = findViewById(R.id.button);



    }

    @Override
    public void onClick(View view) {
        
    }
}