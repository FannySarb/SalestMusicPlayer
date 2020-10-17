package salest.com.salest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        //Animaciones

        Animation animacion1= AnimationUtils.loadAnimation(this, R.anim.despup);
        Animation animacion2= AnimationUtils.loadAnimation(this,R.anim.despdown);

       // TextView miText=findViewById(R.id.mi);
        //TextView nombreApp=findViewById(R.id.calculadorapp);
        ImageView logo=findViewById(R.id.logo);

        logo.setAnimation(animacion1);
       // miText.setAnimation(animacion2);
        //nombreApp.setAnimation(animacion2);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intent = new Intent(splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            };
        },4000);

    }

}
