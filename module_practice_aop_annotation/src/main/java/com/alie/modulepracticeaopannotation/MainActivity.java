package com.alie.modulepracticeaopannotation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private MainPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter();
        initView();
    }

    private void initView() {
        findViewById(R.id.btn1).setOnClickListener(v -> {
            presenter.onBtn1Click();
        });
        findViewById(R.id.btn2).setOnClickListener(v -> {
            presenter.onBtn2Click("西瓜");
        });
        findViewById(R.id.btn3).setOnClickListener(v -> {
            presenter.onBtn3Click("大西瓜", (short) 26);
        });
        findViewById(R.id.btn4).setOnClickListener(v -> {
            presenter.onBtn4Click(new Person("南瓜",56));
        });
    }
}