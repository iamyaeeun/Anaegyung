package edu.sungshin.anaegyung;

import static android.speech.tts.TextToSpeech.ERROR;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class SecondActivity extends AppCompatActivity {
    private DatabaseReference mDatabaseRef;
    EditText editText;
    Button ok,back;
    TextView textView;
    String detectList[]={"persona", "bicycle", "car", "motorcycle", "airplane", "bus", "train", "truck", "boat", "traffic light", "fire hydrant", "street sign", "stop sign", "parking meter","bench", "bird", "cat", "dog", "horse", "sheep", "cow", "elephant","bear", "zebra", "giraffe", "hat", "backpack", "umbrella", "shoe","eye glasses", "handbag", "tie", "suitcase", "frisbee", "skis","snowboard", "sports ball", "kite", "baseball bat"," baseball glove","skateboard", "surfboard", "tennis racket", "bottle", "plate","wine glass", "cup", "fork", "knife", "spoon", "bowl", "banana", "apple","sandwich", "orange", "broccoli", "carrot", "hot dog", "pizza", "donut","cake", "chair", "couch", "potted plant", "bed", "mirror", "dining", "table","window", "desk", "toilet", "door", "tv", "laptop", "mouse", "remote", "keyboard","cell phone", "microwave", "oven", "toaster", "sink", "refrigerator", "blender","book", "clock", "vase", "scissors", "teddy bear","hair drier", "toothbrush", "hair brush"};
    Boolean exist=false;
    UserAccount userAccount;
    private TextToSpeech tts;
    String detect,direct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editText=findViewById(R.id.editText);
        ok=findViewById(R.id.ok);
        back=findViewById(R.id.back);
        textView=findViewById(R.id.textView);
        tts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i!=ERROR){
                    tts.setLanguage(Locale.KOREAN);
                }
            }
        });

        userAccount=new UserAccount(1,0,0);

        mDatabaseRef= FirebaseDatabase.getInstance().getReference("Anaegyung");

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<detectList.length;i++){
                    if(editText.getText().toString().equals(detectList[i])){
                        userAccount.setObIndex(i);
                        exist=true;
                        detect=detectList[i];
                    }
                }
                if(exist){
                    mDatabaseRef.child("UserAccount").child("info").setValue(userAccount)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // Write was successful!
                                    Toast.makeText(SecondActivity.this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show();
                                    tts.speak(detect+" "+direct, TextToSpeech.QUEUE_FLUSH, null);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Write failed
                                    Toast.makeText(SecondActivity.this, "저장을 실패했습니다.", Toast.LENGTH_SHORT).show();
                                }
                            });
                }else{
                    Toast.makeText(SecondActivity.this,"해당 물체는 탐지할 수 없습니다.",Toast.LENGTH_SHORT).show();
                }

                textView.setText(String.valueOf(userAccount.getObIndex())+" "+String.valueOf(userAccount.getObDirect()));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAccount.setObBool(0);
                userAccount.setObDirect(0);
                userAccount.setObIndex(0);

                Intent intent=new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}