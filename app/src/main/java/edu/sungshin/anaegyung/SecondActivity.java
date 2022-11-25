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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class SecondActivity extends AppCompatActivity {
    private DatabaseReference mDatabaseRef;
    EditText editText;
    Button ok,back;
    String directList[] = {"좌측", "전방", "우측"};
    String detectList[] = {"사람", "자전거", "자동차", "오토바이", "비행기",
            "버스", "기차", "트럭", "배", "신호등",
            "소화기", "표지판", "정지 표지판", "주차권 자동 판매기", "벤치",
            "새", "고양이", "개", "말", "양",
            "소", "코끼리", "곰", "얼룩말", "기린",
            "모자", "가방", "우산", "신발", "안경",
            "가방", "넥타이", "정장", "원반", "스키",
            "스노우보드", "공", "연", "야구 배트", "야구 글러브",
            "스케이트보드", "서핑보드", "테니스 라켓", "병", "접시",
            "와인잔", "컵", "포크", "칼", "숟가락",
            "그릇", "바나나", "사과", "샌드위치", "오렌지",
            "브로콜리", "당근", "핫도그", "피자", "도넛",
            "케이크", "의자", "소파", "화분", "침대",
            "거울", "부엌", "식탁", "창문", "책상",
            "변기", "문", "티비","노트북", "마우스",
            "리모콘", "키보드", "핸드폰", "전자레인지", "오븐",
            "토스터", "싱크대", "냉장고", "믹서기", "책",
            "시계", "화분", "가위", "곰인형", "드라이기",
            "칫솔", "빗"};
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
        tts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i!=ERROR){
                    tts.setLanguage(Locale.KOREAN);
                }
            }
        });

        userAccount=new UserAccount(1,0,0,0);

        mDatabaseRef= FirebaseDatabase.getInstance().getReference("Anaegyung");
        mDatabaseRef.child("UserAccount").child("info").setValue(userAccount)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                        Toast.makeText(SecondActivity.this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show();
                        //tts.speak(detect+" "+direct, TextToSpeech.QUEUE_FLUSH, null);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        Toast.makeText(SecondActivity.this, "저장을 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tts.speak(String.valueOf("확인"), TextToSpeech.QUEUE_FLUSH, null);

                for(int i=0;i<detectList.length;i++){
                    if(editText.getText().toString().equals(detectList[i])){
                        userAccount.setObIndex(i+1);
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
                                    //tts.speak(detect+" "+direct, TextToSpeech.QUEUE_FLUSH, null);

                                    mDatabaseRef.child("UserAccount").child("info").addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if(snapshot.getValue(UserAccount.class)!=null){
                                                UserAccount userAccount=snapshot.getValue(UserAccount.class);
                                                int obIndex = userAccount.getObIndex() -1;
                                                int obDirect = userAccount.getObDirect();

                                                String sent = directList[obDirect] + "에 " + detectList[obIndex] + " 있습니다.";
                                                tts.speak(String.valueOf(sent), TextToSpeech.QUEUE_FLUSH, null);
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });


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


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAccount.setObBool(0);
                userAccount.setObDirect(0);
                userAccount.setObIndex(0);
                userAccount.setObSec(0);

                tts.speak(String.valueOf("뒤로가기"), TextToSpeech.QUEUE_FLUSH, null);

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

                Intent intent=new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}