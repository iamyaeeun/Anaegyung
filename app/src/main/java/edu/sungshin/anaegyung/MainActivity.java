package edu.sungshin.anaegyung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i!=ERROR){
                    tts.setLanguage(Locale.KOREAN);
                }
            }
        });

        mDatabaseRef= FirebaseDatabase.getInstance().getReference("Anaegyung");

        mDatabaseRef.child("TestAccount").child("info").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue(TestAccount.class)!=null){
                    TestAccount testAccount=snapshot.getValue(TestAccount.class);
                    Log.d("TAG",String.valueOf(testAccount.getArea()));
                    Log.d("TAG",String.valueOf(testAccount.getDirect()));
                } else{
                    Toast.makeText(MainActivity.this, "데이터 없음", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        tts.speak("테스트",TextToSpeech.QUEUE_FLUSH, null);


    }
}