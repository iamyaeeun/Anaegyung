package edu.sungshin.anaegyung;

import static android.speech.tts.TextToSpeech.ERROR;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class ThirdActivity extends AppCompatActivity {
    private DatabaseReference mDatabaseRef;
    Button back;
    TextView textView;
    String sent;
    String nameList[] = {"사람이", "자전거가", "차가", "오토바이가", "버스가", "트럭이", "스케이트 보드가"};
    String directList[] = {"좌측", "전방", "우측"};
    private TextToSpeech tts;
    TestAccount testAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        textView=findViewById(R.id.textView);
        back=findViewById(R.id.back);
        tts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i!=ERROR){
                    tts.setLanguage(Locale.KOREAN);
                }
            }
        });

        testAccount =new TestAccount(1,0,0,0);

        mDatabaseRef= FirebaseDatabase.getInstance().getReference("Anaegyung");;
        mDatabaseRef.child("TestAccount").child("info").setValue(testAccount)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                        Toast.makeText(ThirdActivity.this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show();
                        //tts.speak(detect+" "+direct, TextToSpeech.QUEUE_FLUSH, null);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        Toast.makeText(ThirdActivity.this, "저장을 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

        mDatabaseRef.child("TestAccount").child("info").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue(TestAccount.class)!=null){
                    TestAccount testAccount=snapshot.getValue(TestAccount.class);
                    int index = testAccount.getIndex();
                    int direct = testAccount.getDirect();

                    sent = directList[direct] + "에 " + nameList[index] + " 있습니다.";
                    textView.setText(String.valueOf(testAccount.getIndex())+" "+String.valueOf(testAccount.getDirect()));
                    tts.speak(String.valueOf(sent), TextToSpeech.QUEUE_FLUSH, null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testAccount.setBool(0);
                testAccount.setDirect(0);
                testAccount.setIndex(0);
                testAccount.setSec(0);

                mDatabaseRef.child("TestAccount").child("info").setValue(testAccount)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Write was successful!
                                Toast.makeText(ThirdActivity.this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show();
                                //tts.speak(detect+" "+direct, TextToSpeech.QUEUE_FLUSH, null);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Write failed
                                Toast.makeText(ThirdActivity.this, "저장을 실패했습니다.", Toast.LENGTH_SHORT).show();
                            }
                        });

                Intent intent=new Intent(ThirdActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}