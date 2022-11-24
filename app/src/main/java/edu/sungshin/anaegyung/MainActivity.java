package edu.sungshin.anaegyung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.speech.tts.TextToSpeech;
import static android.speech.tts.TextToSpeech.ERROR;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public String TAG="TAG";
    private DatabaseReference mDatabaseRef;
    private TextToSpeech tts;
    Button button1,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);

        tts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i!=ERROR){
                    tts.setLanguage(Locale.KOREAN);
                }
            }
        });

        mDatabaseRef= FirebaseDatabase.getInstance().getReference("Anaegyung");

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.speak("실시간 물체 찾기 기능으로, 길게 클릭 후 찾으려는 물체를 입력해주세요.",TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        button1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
                finish();
                return false;
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.speak("실시간 음성 안내 기능으로, 길게 클릭 시 주변 물체 중 가장 가까이 있는 물체를 알려드립니다.",TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        button2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent=new Intent(MainActivity.this,ThirdActivity.class);
                startActivity(intent);
                finish();
                return false;
            }
        });
    }
}