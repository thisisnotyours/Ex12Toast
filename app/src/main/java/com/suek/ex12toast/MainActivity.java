package com.suek.ex12toast;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;




// <자바로 토스트 만들기>


public class MainActivity extends AppCompatActivity {               //Activity 클래스는 이미 Context 를 상속받음(Context 의 능력을 가지고있음)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);       //사실은 setContentView 를 하면 스트림을 만들어 보낸다
    }//onCreate method

    // 1)  onClick 속성이 부여된 view 가 클릭되면 자동으로 실행되는 메소드
    public void clickBtn(View v){
        // 2)  버튼이 눌러졌을때 실행된= Toast 객체생성
        //     토스트는 new 키워드 대신에 Toast '클래스'의 makeText()메소드를 통해 토스트객체 생성
        //Context: 안드로이드 운영체제의 기능을 사용할 수 있도록 만드는 클래스[운영체제 대리인]
        //Activity 가 Context 를 상속받았기에 Context 가 필요할때 이 액티비티를 전달해도됨.
        Toast t= Toast.makeText(this, "clicked", Toast.LENGTH_SHORT);      //운영체제(Android)를 대신해서 대리인(Context)이 app/computer 에 명령함=(Toast 를 쓰고싶을때 대리인에 허락받기)
                                                               // CharSequence -문자열을 차례로
                                                               //1. this. 는 Context 의 능력을 가진 Activity                      *****(context 는 운영체제의 기능을 가지고싶을때 사용- 스트림기능)*****
                                                                                                            //어떤뷰던 context 가 필요함= this
                                                               //2. Toast.LENGTH_LONG= 1/ Toast.LENGTH_SHORT= 0
        //만들어진 토스트객체 화면에 보여라!!
        //t.show();             위에 text 안에 직접 문자열을 넣어 보여줘도 되지만

        // 2) res/values/string.xml 안에 문자열을 작성하고 토스트에서 문자열을 읽어와 보여주기를 현업에서는 일반적으로 더 많이씀
        t= Toast.makeText(this, R.string.msg, Toast.LENGTH_SHORT);   //msg는 string.xml 에 쓰여있는 '식별자'

        // 3) 토스트가 보여질 위치 지정
        //t.setGravity(Gravity.CENTER, 0,0);    //offset 위치의 편차 (0,0)= 정가운데
        //t.setGravity(Gravity.CENTER, -100,200);    //화면상의 좌표는 위에가 0임 (그래서 y측을 아래로 내리려면 +, 위로 올리려면 -/ x측은 반대)
        //t.show();
        t.setGravity(Gravity.CENTER, 0,0);

        // 4) 토스트에 문자열이 아닌 원하는 모양의 view 보여주기
        //    일단, 빈 문자열로 된  Toast 객체 생성
        t= Toast.makeText(this, "", Toast.LENGTH_LONG);     //사실 두번째 파라미터에 이미지를 넣고싶은데 일단 빈문자열""을 넣어줌
        // 위 토스트가 보여줄 view 를 설정 [기본은 TextView 로 되어있음]

        // 이미지뷰 객체생성
        ImageView iv= new ImageView(this);
        //iv.setImageResource(R.drawable.ic_launcher_foreground);     //이렇게 해서 이미지를 불러와도 되지만..
        iv.setImageResource(android.R.drawable.ic_lock_silent_mode);    //android.R = 안드로이드의 리소스

        // 6) 텍스트 뷰 객체 생성
        TextView tv= new TextView(this);
        tv.setText("음소거");

        //t.setView(iv);    //toast 에 set 하기/ set 은 설정을 바꾸는 성질이 있음(여러개를 할수가없다= 한번에 하나만 보여줄수있음)
        //t.setView(tv);
        // 토스트는 하나의 뷰만 설정 가능함
        // 그래서 ViewGroup 을 만들어서 그 안에 View 들을 넣고
        // 토스트에는 ViewGroup 1개를 설정
        LinearLayout layout= new LinearLayout(this);    //방향성을 설정하지않으면 기본적으로 horizontal
        layout.setOrientation(LinearLayout.VERTICAL);      //기본적으로 v=0, h=1인데 이걸 외울수는 없으니까
                                                           //그 클래스명. 이나 V 라고만 써도 내가 LinearLayout 의
                                                           //Vertical 을 쓸것이라는 것을 알고 보여줌
        layout.addView(iv);
        layout.addView(tv);

        //토스트에는 ViewGroup 1개를 설정
        t.setView(layout);    //뷰 보여주기



        // 5) 토스트 보여주기
        t.setGravity(Gravity.CENTER,0,0);   //중앙에 보여주기
        //t.show();







        // 6) XML 로 View 객체의 모양을 설계하고 이를 객체로 만들어 사용
        //   layout 폴더안에 toast.xml 문서를 만들고 그 안에 뷰들을 배치

        //   토스트에 View 로 설정하기
        t= Toast.makeText(this, "", Toast.LENGTH_LONG);   //위에 toast 객체 생성했으니 여기서는 그냥 참조변수 t만..

        // xml 에 설계된 뷰들을 java 의 view 객체로 만들어주는 객체에게 만들어달라고(부풀리다: inflate) 요청
        // -> 누구한테? LayoutInflater(부풀리다) ==> input/outputStream 대신에 LayoutInflater 가 해줌
        LayoutInflater inflater= this.getLayoutInflater();   //this= MainActivity.java  //this. 생략가능
        View view= inflater.inflate(R.layout.toast, null);   //View.view == LinearLayout.view
        t.setView(view);

        t.setGravity(Gravity.CENTER,0,0);
        t.show();

    }

}
